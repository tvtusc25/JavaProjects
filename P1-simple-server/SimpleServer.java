import java.io.*;
import java.net.*;

//This class acts as the server in a simple client server interaction
public class SimpleServer {

    // Initialise the server state to Greeting and create port and socket variables
    private static ServerState currentState = ServerState.Greeting;
    static int serverPort;
    static ServerSocket serverSocket;

    /**
     * This is the main method, which takes <port> (the port number to connect to) as a command line argument.
     * @param args
     */ 
    public static void main (String[] args) {
    	
        //Ensure argument (<port>) is given and a number
        if (args.length != 1) {
            System.err.println("Usage: java SimpleServer <port>");
            System.exit(1);
        } else {
            try {
            	 //Assign argument (<port>) to local variable
                serverPort = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Port must be a number");
                System.exit(0);
            }
        }

        try {
            //Create a server socket for clients to connect to
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Waiting for connection on port: " + serverSocket + "...");

            //Continuously listen for client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress());

                //Create input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage;
                while (currentState != ServerState.Restart) {
                    // Read the client's message
                    clientMessage = in.readLine();
                    System.out.println("Client says: " + clientMessage);
                    
                    //Get the current state and expected message for this state
                    String response = currentState.getServerPhrase();
                    String expectedMessage = currentState.getExpectedMessage();

                    // Check if the client's message matches the expected message for the current state
                    if (!clientMessage.equals(expectedMessage)) {
                        // Respond with an error message indicating the expected message
                        response = "Incorrect input... Expecting: " + expectedMessage;
                        out.println(response);
                        continue; // Continue the loop without changing the state
                    }

                    //Send the server's response and update state 
                    out.println(response);
                    currentState = currentState.nextState();
                }
                
                //Close client connection to server
                clientSocket.close();
                System.out.println("Client disconnected");

                //Keep server open and return to greeting state
                currentState = ServerState.Greeting;
            }
            //Error handling
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * This enumeration is the state machine used for server responses.
     * The states are Greeting, Advising, Closing, and Restart
     */
    public enum ServerState {
        //Server States
        Greeting("HELLO ADVISER", "HELLO ADVISEE"),
        Advise("ADVISE ME ON TO CS2003", "YOU ARE ADVISED ON TO CS2003"),
        Closing("THANK YOU", "YOU'RE WELCOME"),
        Restart("","");

        private final String expectedMessage;
        private final String serverPhrase;

        /**
         * Setter for server state phrases and expected messages
         * @param expectedMessage message expected by server
         * @param serverPhrase phrase returned by server
         */
        ServerState(String expectedMessage, String serverPhrase) {
            this.expectedMessage = expectedMessage;
            this.serverPhrase = serverPhrase;
        }

        /**
         * Getter for expected client message
         * @return client message expected by server
         */
        public String getExpectedMessage() {
            return expectedMessage;
        }
        
        /**
         * Getter for server phrase
         * @return phrase returned by server
         */
        public String getServerPhrase() {
            return serverPhrase;
        }
        
        /**
         * Moves the state machine on to the next state.
         * @return next state (Greeting, Advise, Closing, Restart)
         */
        public ServerState nextState() {
            return values()[ordinal() + 1];
        }
    }
}