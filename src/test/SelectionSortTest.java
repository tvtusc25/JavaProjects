import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Testing suite for selection sort algorithm.
 * 
 */
public class SelectionSortTest {

    /**
     * Test if selection sort instance is not null.
     * 
     */
    @Test
    public void testInstanceNotNull() {
        SelectionSort selectionSort = new SelectionSort();
        assertNotNull(selectionSort);
    }

     /**
     * Tests edge case with an array of size 0.
     * 
     */
    @Test
    public void testEmptyArray() {
        int[] arr = {};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    /**
     * Tests an array with size 1.
     * 
     */
    @Test
    public void testSingleElementArray() {
        int[] arr = {5};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    /**
     * Tests an array that is already sorted.
     * 
     */
    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        assertTrue(isSorted(arr));
    }

     /**
     * Tests an unsorted array in reverse sort order.
     * 
     */
    @Test
    public void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    /**
     * Tests a randomly unsorted array.
     * 
     */
    @Test
    public void testRandomArray() {
        int[] arr = {4, 2, 7, 1, 9, 3};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    /**
     * Checks if the array is sorted.
     * 
     * @param arr array to sort.
     * @return boolean is sorted or not.
     */
    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

