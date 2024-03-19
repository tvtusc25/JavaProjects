import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class runs the selection and merge sorting algorithms then adds their times to a .txt file.
 * 
 */
public class TimingData {
    public static void main(String[] args) {
        try {
            writeTimingDataToFile("src/plot/timing_data.txt", 10); // write to file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the times to a .txt file
     * 
     * @param fileName name of the file to hold the times
     * @param numRuns  number of times to run the sorting algorithms
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private static void writeTimingDataToFile(String fileName, int numRuns) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int run = 0; run < numRuns; run++) {
                System.out.println("Run: " + run);
                int[][] randomArrays = generateRandomArrays(100, 100000, 1000); // 100 arrays of size 0-100,000 with increasing size by 1,000
                List<Long> selectionSortTimes = getSortingTimes(new SelectionSort(), randomArrays); // all selection sort times
                List<Long> mergeSortTimes = getSortingTimes(new MergeSort(), randomArrays); // all merge sort times
                for (int i = 0; i < selectionSortTimes.size(); i++) {
                    writer.println(selectionSortTimes.get(i) + "," + mergeSortTimes.get(i)); // writes the times in "long,long" format
                }
            }
        }
    }

    /**
     * Runs a sorting algorithm on pre-generated random sequences of different lengths and collects the time each takes.
     * 
     * @param sorter the sorting algorithm to run
     * @param arrays the 2D array of random arrays
     * @return a list of the times
     */
    private static List<Long> getSortingTimes(ISort sorter, int[][] arrays) {
        List<Long> sortingTimes = new ArrayList<>(); // list of the times
        for (int i = 0; i < arrays.length; i++) {
            int[] arr = arrays[i];
            sorter.sort(arr); // sort the array
            sortingTimes.add(sorter.getTime()); // add the time to the list
        }
        return sortingTimes;
    }

    /**
     * Generates a 2D array of random arrays with increasing sizes.
     *
     * @param numArrays number of random arrays to generate
     * @param maxSize maximum size of the random arrays
     * @param sizeIncrement size increment for each subsequent array
     * @return the 2D array of random arrays
     */
    private static int[][] generateRandomArrays(int numArrays, int maxSize, int sizeIncrement) {
        int[][] randomArrays = new int[numArrays][];
        for (int i = 0, size = 0; i < numArrays; i++, size += sizeIncrement) {
            randomArrays[i] = new int[size];
            for (int j = 0; j < size; j++) {
                randomArrays[i][j] = (int) (Math.random() * maxSize);
            }
        }
        return randomArrays;
    }
}

