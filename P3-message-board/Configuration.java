import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Configuration {
    private Properties properties; // property type for config file
    public String serverAddress; // hostname of server
    public int serverPort; // Port number of server
    public String otherServerAddress; // empty server address
    public String boardDirectory; // directory for PHP file (displays messages online)

    /**
     * Creates Property type from config file for use in DMBClient
     * 
     * @param configFile .properties file
     */
    public Configuration(String configFile) {
        properties = new Properties();
        try { // Try assigning to variables
            FileInputStream input = new FileInputStream(configFile);
            properties.load(input);
            input.close();
            serverAddress = properties.getProperty("serverAddress");
            otherServerAddress = properties.getProperty("otherServerAddress");
            serverPort = Integer.parseInt(properties.getProperty("serverPort"));
            boardDirectory = properties.getProperty("boardDirectory");
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
        }
    }
}
