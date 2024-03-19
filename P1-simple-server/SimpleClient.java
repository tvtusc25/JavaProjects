import java.io.*;
import java.net.*;

/**
 * This class acts as the client in a simple client-server interaction.
 * 
 */
public class SimpleClient {
	
	//create port, socket, and IP variables
	static int serverPort;
    static Socket serverSocket;
    static String serverIP;
    public static ClientState currentState = ClientState.Greeting;
    
    /**
     * This is the main method, which takes <IPaddress> (IP address of a server) and <port> (the port number to connect to)
     * as command line arguments.
     * @param args
     */
    public static void main (String[] args) {
        //Ensure both IPaddress and port are given
        if (args.length != 2) {
            System.err.println("Usage: java SimpleClient <IPaddress> <port>");
            System.exit(1);
        } else {
            try {
            	 //Assign argument (<port>) to local variable
                serverPort = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Port must be a number");
                System.exit(0);
            }
        }

        //Assign arguments (<IPaddress>) to local variable
        serverIP = args[0];

        try {
            //Create a socket to connect to the server
            serverSocket = new Socket(serverIP, serverPort);

            //Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);

            while (true) {
                //Read a message from the user and send it to the server
		        String phrase = currentState.getClientPhrase();
		        out.println(phrase);

                //Receive and display the server's response
                String serverResponse = in.readLine();
                System.out.println("Server says: " + serverResponse);

                if (serverResponse.equals("YOU'RE WELCOME")) {
		            serverSocket.close();
                    break; //End the conversation when "YOU'RE WELCOME" is received
                }
		
		        currentState = currentState.nextState();
            }
        }
        //Error handling
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    /**
     * This enumeration is the state machine used for client responses.
     * The states are Greeting, Advising, and Closing
     */
     public enum ClientState {
        //Client States
        Greeting("HELLO ADVISER"),
        Advise("ADVISE ME ON TO CS2003"),
        Closing("THANK YOU");

        private final String clientPhrase;

        /**
         * Setter for client state phrases and expected messages.
         * @param clientPhrase phrase returned by client
         */
        ClientState(String clientPhrase) {
            this.clientPhrase = clientPhrase;
	}
        
        /**
         * Getter for server phrase.
         * @return phrase given to client by server.
         */
        public String getClientPhrase() {
            return clientPhrase;
        }
        
        /**
         * Moves the state machine on to the next state.
         * @return next state (Greeting, Advising, or Closing)
         */
        public ClientState nextState() {
            return values()[ordinal() + 1];
        }
    }
}
