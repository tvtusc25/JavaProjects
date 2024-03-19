import java.io.*;
import java.util.*;

public class CSVtoHashMap {
    /**
     * Creates HashMap for usernames and port numbers
     * @return HashMap
     */
    public static Map<String, Integer> createUserMap() {
        Map<String, Integer> usernameToPort = new HashMap<>(); //HashMap

        String csvFile = "CS2003-usernames-2023.csv"; // csv

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) { //try reading file
            br.readLine(); // Read and discard the first line (headers)

            String line;
            while ((line = br.readLine()) != null) { //iterate through and populate map
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    int port = Integer.parseInt(parts[1]);
                    usernameToPort.put(username, port);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usernameToPort; // Return the populated map
    }
}
