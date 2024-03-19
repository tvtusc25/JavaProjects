
/**
 * This class implements the selection sorting algorithm with a timer.
 * 
 */
public class SelectionSort implements ISort {
    private long time; // time of sort

    @Override
    public void sort(int[] arr) {
        //Divides the input array into a sorted and an unsorted region.
        //In each iteration, it finds the minimum element in the unsorted region and swaps 
        //it with the first element of the unsorted region.
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }

    @Override
    public long getTime() {
        return time;
    }
}

