
/**
 * This class implements the merge sorting algorithm with a timer.
 * 
 */
public class MergeSort implements ISort {
    private long time; // time of sort

    @Override
    public void sort(int[] arr) {
        long startTime = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }

    /**
     * Recursive merge sort algorithm to sort an integer array.
     *
     * The algorithm divides the array into two halves, recursively sorts each half, and then
     * merges the sorted halves to produce a fully sorted array.
     *
     * @param arr   The integer array to be sorted.
     * @param left  The starting index of the portion of the array to be sorted.
     * @param right The ending index of the portion of the array to be sorted.
     */
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted array.
     *
     * @param arr   The array containing the subarrays to be merged.
     * @param left  The starting index of the left subarray.
     * @param mid   The ending index of the left subarray and the starting index of the right subarray.
     * @param right The ending index of the right subarray.
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;  // Size of the left subarray
        int n2 = right - mid;     // Size of the right subarray

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            leftArr[i] = arr[left + i];

        for (int j = 0; j < n2; ++j)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;  // Initial index of merged subarray

        // Merge the two subarrays
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }


    @Override
    public long getTime() {
        return time;
    }
}

