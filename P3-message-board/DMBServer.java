import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.LinkedList;
import java.util.regex.*;

public class DMBServer {
    static Configuration c_; // configuration .properties file
    static int port; // port number
    static Queue<Socket> clientQueue = new LinkedList<>();

    /**
     * Main method for handling connections and sending requests on
     * @param args
     */
    public static void main(String[] args) {
        c_ = new Configuration("cs2003-net2.properties"); //config
        try { //Try getting port from config
            port = c_.serverPort; //port number
        } catch (NumberFormatException e) {
            System.out.println("++ Can't configure port: " + e.getMessage());
            return;
        }

        //Try creating server socket for connecting
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("++ Server is listening on port " + port);
            while (true) { //while server is live
                Socket clientSocket = serverSocket.accept(); //accept connection
                System.out.println("++ Accepted connection from " + clientSocket.getInetAddress());
                clientQueue.add(clientSocket); // Add the client to the client queue
                System.out.println("++ Client queue size: " + clientQueue.size());
                handleClientRequests(); // Handle client requests in queue
            }
        } catch (IOException e) {
            System.err.println("++ Error starting the server: " + e.getMessage());
        }
    }

    /**
     * Handle client requests from the client queue
     * This can be problematic if one connection is hung because the next connections will be stuck behind them.
     */
    private static void handleClientRequests() {
        while (!clientQueue.isEmpty()) {
            Socket clientSocket = clientQueue.poll(); // Dequeue a client
            handleClientRequest(clientSocket); // Handle the client's request
        }
    }

    /**
     * Manages all types of client requests
     * 
     * @param clientSocket client socket
     */
    private static void handleClientRequest(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) { // Try reading in from client connection
            String clientMessage = reader.readLine(); // read client message
            if (Pattern.matches("^%%from\\s(\\w+)\\s(.+)$", clientMessage)) { // %from command
                handleFromCommand(clientMessage, clientSocket.getOutputStream());
            } else if (Pattern.matches("^%%fetch\\s(.+)$", clientMessage)) { // %%fetch command
                handleFetchCommand(clientMessage, clientSocket.getOutputStream());
            } else { // Message
                handleRegularMessage(clientMessage, clientSocket.getOutputStream());
            }
        } catch (IOException e) {
            System.err.println("++ Error handling client request: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("++ Error closing client socket: " + e.getMessage());
            }
        }
    }

    /**
     * Handles %%from command
     * 
     * @param clientMessage the message to be sent
     */
    private static void handleFromCommand(String clientMessage, OutputStream clientOutput) {
        System.out.println(clientMessage);
        clientMessage = clientMessage.replace("%%", ""); // Remove '%%' at the start of the message
        String todayDirectory = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // todays date for directory
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS").format(new Date()); // date format for file
        DirAndFile.createMessage(c_.boardDirectory + todayDirectory, timestamp, clientMessage); // send on to create message
        try { 
            clientOutput.write(("++ Message sent successfully: " + clientMessage + "\n").getBytes());
        } catch (IOException e) {
            System.err.println("++ Error sending confirmation: " + e.getMessage());
        }
    }

    /**
     * Handles %%fetch command
     * 
     * @param clientMessage the message to be sent
     * @param clientOutput  the output stream to send responses to the client
     * @throws IOException
     */
    private static void handleFetchCommand(String clientMessage, OutputStream clientOutput) throws IOException {
        System.out.println(clientMessage);
        clientMessage = clientMessage.replace("%%fetch", "").trim(); // remove %%fetch to get directory name
        System.out.println("++ Fetching messages from: " + clientMessage);
        File dir = new File(c_.boardDirectory + clientMessage); // directory where messages are stored
        if (dir.exists() && dir.isDirectory()) { // directory exists and is a directory
            File[] files = dir.listFiles(); // file list
            if (files != null && files.length > 0) { // if files exist
                clientOutput.write(("%%messages " + clientMessage + "\n").getBytes());
                for (File file : files) { // iterate through files
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            clientOutput.write(("  " + line).getBytes()); // Write the contents of the file to the client's output stream
                            clientOutput.write('\n');
                        }
                    } catch (IOException e) {
                        System.err.println("++ Error reading file: " + e.getMessage());
                    }
                }
                clientOutput.write("%%end".getBytes());
            } else {
                System.out.println("%%none");
                clientOutput.write("%%none\n".getBytes()); // Send "%%none" to the client to indicate no messages found
            }
        } else {
            System.out.println("%%error");
            clientOutput.write("%%error\n".getBytes()); // Send "%%error" to the client to indicate an invalid directory or date
        }
    }

    /**
     * Handles regular messages
     * 
     * @param clientMessage the message to be sent
     */
    private static void handleRegularMessage(String clientMessage, OutputStream clientOutput) {
        System.out.println(clientMessage);
        String todayDirectory = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // todays date for directory
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS").format(new Date()); // date format for file
        DirAndFile.createMessage(c_.boardDirectory + todayDirectory, timestamp, clientMessage); // send on to create message
        try { 
            clientOutput.write(("++ Message sent successfully: " + clientMessage + "\n").getBytes());
        } catch (IOException e) {
            System.err.println("++ Error sending confirmation: " + e.getMessage());
        }
    }
}
