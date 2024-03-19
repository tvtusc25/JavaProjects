import java.io.*;

public class DirAndFile {

    /**
     * Creates the message to be sent to the server.
     * 
     * @param dirName  directory name
     * @param fileName file name
     * @param text     message
     */
    public static void createMessage(String dirName, String fileName, String text) {
        File dir = new File(dirName); // creates new file directory
        if (dir.exists()) { // Handles if the directory exists
            System.out.println("++ Directory already exists: " + dirName);
        } else if (dir.mkdir()) { // make directory
            System.out.println("++ Created directory: " + dirName);
        } else {
            System.out.println("++ Failed to create directory: " + dirName);
            return;
        }

        fileName = dirName + File.separator + fileName; // creates filename
        File file = new File(fileName);
        if (file.exists()) { // Handles if file already exists (should not)
            System.out.println("++ File already exists: " + fileName);
            return;
        }

        try { // Try writing to the file
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();
            System.out.println("++ Wrote \"" + text + "\" to file: " + fileName);
        } catch (IOException e) {
            System.out.println("++ IOException - write(): " + e.getMessage());
        }
    }
}
