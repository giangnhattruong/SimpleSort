package funix.asm1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Algorithm Class contains :
 * 3 sort algorithms implementing on float array
 * - Bubble sort
 * - Selection sort
 * - Insertion sort
 * 2 search algorithms implementing on float array
 * - Linear search (search for greater values than input)
 * - Binary search
 */
public class Algorithm implements Sortable, SearchAble {
    private long bubbleSortRunningTime;

    /**
     * Bubble sort loop the array from the right to index 1 - outer loop
     * in each outer loop has another loop from 0 to the outer loop pointer minus 1 - inner loop,
     * inner loop compares the current value with the next value,
     * and swaps the greater value toward the right.
     * Print each step.
     * @param array - array to be sorted
     */
    public void bubbleSort(float[] array) {
        long startTime = System.nanoTime();

        display(array);

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
                display(array);
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Bubble Sort Running Time: " +
                (endTime - startTime) / 1e6 + "ms");
    }

    /**
     * Selection sort loop the array from the right to index 1 - outer loop
     * in each outer loop has another loop from the outer loop pointer to 0 - inner loop,
     * inner loop find the max value, and swap it to the value at the outer loop pointer.
     * Print each step.
     * @param array - array to be sorted
     */
    public void selectionSort(float[] array) {
        long startTime = System.nanoTime();

        display(array);

        for (int i = array.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > array[maxIndex])
                    maxIndex = j;
            }
            swap(array, maxIndex, i);
            display(array);
        }

        long endTime = System.nanoTime();
        System.out.println("Selection Sort Running Time: " +
                (endTime - startTime) / 1e6 + "ms");
    }

    /**
     * Insertion sort split the array into two parts: sorted part and unsorted part
     * at the beginning:
     * - sorted part contains the first element of the array
     * - unsorted part contains elements from index 1 to the right
     * this sort loops through the unsorted part - outer loop
     * each loop removes the first element from unsorted part - put it to a temporary variable - current,
     * and makes another loop from right to left of sorted part - inner loop,
     * each inner loop shift the value greater than current one index to the right
     * when inner loop get to the value less than or equal to current, it stops
     * and assign current to the position in the right of this value.
     * Print each step.
     * @param array - array to be sorted
     */
    public void insertionSort(float[] array) {
        long startTime = System.nanoTime();

        display(array);

        for (int i = 1; i < array.length; i++) {
            float current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
                display(array);
            }
            array[j + 1] = current;
            display(array);
        }

        long endTime = System.nanoTime();
        System.out.println("Insertion Sort Running Time: " +
                (endTime - startTime) / 1e6 + "ms");
    }

    /**
     * Linear search loop the array from left to right,
     * if it finds the value greater than search value,
     * it add this value to list
     * after the loop over, return the list
     * @param array - original array for searching
     * @param value - search value
     * @return - list of indexes which value greater than search value
     */
    public List<Integer> linearSearch(float[] array, float value) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < array.length; i++)
            if (array[i] > value)
                indexes.add(i);

        return indexes;
    }

    public int binarySearch(float[] array, float value) {
        return binarySearch(array, value, 0, array.length - 1);
    }

    /**
     * Binary search uses recursive function,
     * it gets the middle value of the array,
     * then compare the search value to the middle value
     * if the search value is greater than the middle value,
     * it call it self to search the right side from middle index + 1 to end
     * if the search value is smaller than the middle value,
     * it call it self to search the left side from 0 to middle index + 1
     * if the search value is equal to the middle value, return the middle index
     * the base case is when the function keep calling it self until
     * the point that start index is less than end index, then it return -1 for not found
     * @param array - an sorted array for searching
     * @param value - search value
     * @param start - start index
     * @param end - end index
     * @return - index of value, is -1 if not found
     */
    private int binarySearch(float[] array, float value, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start + 1) / 2;

        if (value < array[mid])
            return binarySearch(array, value, start, mid - 1);
        else if (value > array[mid])
            return binarySearch(array, value, mid + 1, end);

        return mid;
    }

    private void swap(float[] array, int index1, int index2) {
        if (array[index1] == array[index2])
            return;

        float temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void display(float[] array) {
        System.out.println(Arrays.toString(array));
    }
}
