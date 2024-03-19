import java.io.*;
import java.text.SimpleDateFormat;
import java.net.*;
import java.util.regex.*;
import java.util.*;

/**
 * Daily Message Board Client
 *
 * based on code by Saleem Bhatti, 28 Aug 2019
 *
 */
public class DMBClient {
    static int maxTextLen_ = 256;
    static Configuration c_;

    // From configuration file
    static String server; // FQDN
    static int port; // server port

    /**
     * Main method
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Map<String, Integer> usernameToPortMap = CSVtoHashMap.createUserMap(); // create HashMap of usernames and ports
        c_ = new Configuration("cs2003-net2.properties"); // read from config file

        try {
            server = c_.serverAddress; // default server address from config
            port = c_.serverPort; // default port from config
        } catch (NumberFormatException e) {
            System.out.println("++ Can't configure port: " + e.getMessage());
        }

        if (args.length == 0) { // Handle 0 arguments
            System.out.println("\n++ Usage: DMBClient <message>\n");
            System.exit(0);
        }

        if (args[0].equals("%%to")) { // Handle commands
            handleToCommand(args, usernameToPortMap); // send message to address and port defined in args
        } else if (args[0].equals("%%fetch")) {
            if (args.length > 1) { // Handle the %%fetch command to fetch messages for a specific date
                String fetchDate = args[1];
                handleFetchCommand(fetchDate);
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // If no date is provided, use today's date                                                             // date
                String todayDate = dateFormat.format(new Date());
                handleFetchCommand(todayDate);
            }
        } else {
            try {
                Socket connection = startClient(server, port);
                sendAndReceiveData(connection, args[0]); // send message to default address and port
            } catch (IOException e) {
                System.err.println("++ Error handling fetch command: " + e.getMessage());
            }
        }
    }

    /**
     * Handles the command %%to, which sends messages to a different user.
     * 
     * @param args              Command line arguments
     * @param usernameToPortMap HashMap of usernames and their port numbers
     */
    private static void handleToCommand(String[] args, Map<String, Integer> usernameToPortMap) {
        if (args.length < 3) {
            System.out.println("\n++ Usage: DMBClient %%to <recipient_username> <message>\n");
            System.exit(0);
        }

        String recipientUsername = args[1]; // chosen username to send to
        String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length)); // args[2] contains message

        if (usernameToPortMap.containsKey(recipientUsername)) { // Handle if username exists
            try {
                server = recipientUsername + c_.otherServerAddress; // server address = rrrr + .teaching.cs.st-andrews.ac.uk
                port = usernameToPortMap.get(recipientUsername); // get the port from rrrr
                Socket connection = startClient(server, port);
                sendAndReceiveData(connection, "%%from tvt1 " + message); // append message with from tvt1 (me)
            } catch (IOException e) {
                System.err.println("++ Error handling fetch command: " + e.getMessage());
            }
        } else {
            System.out.println("++ Recipient username not found.");
            System.exit(0);
        }
    }

    /**
     * Handles the fetch command, which fetches the messages from the server.
     * 
     * @param date Date of messages requested
     */
    private static void handleFetchCommand(String date) {
        if (!(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", date))) {
            System.out.println("\n++ Usage: DMBClient %%fetch <YYYY-MM-DD>\n");
            System.exit(0);
        }
        try { // Try sending a fetch request to the server with the specified data
            String message = "%%fetch " + date;
            Socket connection = startClient(server, port);
            sendAndReceiveData(connection, message);
        } catch (IOException e) {
            System.err.println("++ Error handling fetch command: " + e.getMessage());
        }
    }

    /**
     * Send a message to the server and receive responses.
     *
     * @param connection The socket connection to the server
     * @param message    The message to send
     * @throws IOException If there's an error sending or receiving data
     */
    private static void sendAndReceiveData(Socket connection, String message) throws IOException {
        try (OutputStream tx = connection.getOutputStream(); // output stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) { // reader
            int maxMessageLength = 256 - 1; // Account for the newline character
            if (message.length() > maxMessageLength) {
                message = message.substring(0, maxMessageLength);
            }
            message = message + "\n"; // Append a newline character to the message
            byte[] buffer = message.getBytes(); // convert message to bytes
            System.out.println("++ Sending " + buffer.length + " bytes");
            tx.write(buffer, 0, buffer.length); // write to server
            tx.flush();

            String response;
            while ((response = reader.readLine()) != null) {
                if ("%%none".equals(response)) {
                    System.out.println(response);
                    System.out.println("++ No messages found for the specified date.");
                } else if ("%%error".equals(response)) {
                    System.out.println(response);
                    System.out.println("++ Invalid directory or date.");
                } else {
                    System.out.println(response); // Print or process the received messages
                }
            }
        } finally {
            connection.close(); // close connection
        }
    }


    /**
     * Starts the client connection to the server.
     * 
     * @param hostname   Server name to connect to
     * @param portnumber Port number for the server
     * @return socket connection
     */
    static Socket startClient(String hostname, int portnumber) {
        Socket connection = null;
        try {
            InetAddress address; // IP address
            int port; // port number
            address = InetAddress.getByName(hostname); // IP address from hostname
            port = portnumber; // assign port number
            connection = new Socket(address, port); // make a socket
            System.out.println("++ Connecting to " + hostname + ":" + port + " -> " + connection);
        }

        catch (UnknownHostException e) {
            System.err.println("++ UnknownHost Exception: " + hostname + " " + e.getMessage());
        } catch (IOException e) {
            System.err.println("++ IO Exception: " + e.getMessage());
        }

        return connection; // socket
    }
}