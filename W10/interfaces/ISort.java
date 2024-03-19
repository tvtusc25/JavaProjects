/**
 * Simple sorting interface.
 *
 */
public interface ISort {

    /**
     * Sorts the array and tracks the time it takes in milliseconds.
     * 
     * @param arr the integer array to be sorted
     */
    void sort(int[] arr);

    /**
     * Returns the time it took to sort the array.
     * 
     * @return time in milliseconds.
     */
    long getTime();
}
