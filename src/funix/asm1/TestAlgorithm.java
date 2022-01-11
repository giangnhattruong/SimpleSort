package funix.asm1;

import java.util.Random;

public class TestAlgorithm {
    private float[] array;
    private Sortable sort;

    public TestAlgorithm() {
        this(1_000);
        sort = new Algorithm();
    }

    public TestAlgorithm(int length) {
        array = new float[length];
    }

    public static void main(String[] args) {
        // Test 1000 elements array
        TestAlgorithm test = new TestAlgorithm();

//        testWorstCase(test);
        testBestCases(test);
//        testAverageCases(test);
    }

    public static void testWorstCase(TestAlgorithm test) {
        System.out.println("Worst Cases:");
        // Bubble Sort - Running Time: 9.156ms
        bubbleSortWorstCase(test);
        // Selection Sort - Running Time: 4.5544ms
        selectionSortWorstCase(test);
        // Insertion Sort - Running Time: 3.645101ms
        insertionSortWorstCase(test);
        System.out.println();
    }

    public static void testBestCases(TestAlgorithm test) {
        System.out.println("Best Cases:");
        // Bubble Sort - Running Time: 0.0224ms
        bubbleSortBestCase(test);
        // Selection Sort - Running Time: 4.0882ms
        selectionSortBestCase(test);
        // Insertion Sort - Running Time: 0.0451ms
        insertionSortBestCase(test);
        System.out.println();
    }

    public static void testAverageCases(TestAlgorithm test) {
        System.out.println("Average Cases:");
        // Bubble Sort - Running Time: 7.017901ms
        bubbleSortAverageCase(test);
        // Selection Sort - Running Time: 3.6029ms
        selectionSortAverageCase(test);
        // Insertion Sort - Running Time: 2.711901ms
        insertionSortAverageCase(test);
        System.out.println();
    }

    private static void bubbleSortWorstCase(TestAlgorithm test) {
        test.generateDecreasingOrderArray();
        bubbleSort(test.array);
    }

    private static void selectionSortWorstCase(TestAlgorithm test) {
        test.generateDecreasingOrderArray();
        selectionSort(test.array);
    }

    private static void insertionSortWorstCase(TestAlgorithm test) {
        test.generateDecreasingOrderArray();
        insertionSort(test.array);
    }

    private static void bubbleSortBestCase(TestAlgorithm test) {
        test.generateIncreasingOrderArray();
        bubbleSort(test.array);
    }

    private static void selectionSortBestCase(TestAlgorithm test) {
        test.generateIncreasingOrderArray();
        selectionSort(test.array);
    }

    private static void insertionSortBestCase(TestAlgorithm test) {
        test.generateIncreasingOrderArray();
        insertionSort(test.array);
    }

    private static void bubbleSortAverageCase(TestAlgorithm test) {
        test.generateRandomOrderArray();
        bubbleSort(test.array);
    }

    private static void selectionSortAverageCase(TestAlgorithm test) {
        test.generateRandomOrderArray();
        selectionSort(test.array);
    }

    private static void insertionSortAverageCase(TestAlgorithm test) {
        test.generateRandomOrderArray();
        insertionSort(test.array);
    }

    public static void bubbleSort(float[] array) {
        long startTime = System.nanoTime();

        for (int i = array.length - 1; i > 0; i--) {
            boolean isBestCase = true;

            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isBestCase = false;
                }
            }

            if (isBestCase)
                break;
        }

        long endTime = System.nanoTime();
        System.out.println("Bubble Sort Running Time: " +
                (endTime - startTime) / 1e6 + "ms");
    }

    private static void selectionSort(float[] array) {
        long startTime = System.nanoTime();

        for (int i = array.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > array[maxIndex])
                    maxIndex = j;
            }
            swap(array, maxIndex, i);
        }

        long endTime = System.nanoTime();
        System.out.println("Selection Sort Running Time: " +
                (endTime - startTime) / 1e6 + "ms");
    }

    private static void insertionSort(float[] array) {
        long startTime = System.nanoTime();

        for (int i = 1; i < array.length; i++) {
            float current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }

        long endTime = System.nanoTime();
        System.out.println("Insertion Sort Running Time: " +
                (endTime - startTime) / 1e6 + "ms");
    }

    private static void swap(float[] array, int index1, int index2) {
        if (array[index1] == array[index2])
            return;

        float temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void generateIncreasingOrderArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    private void generateDecreasingOrderArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
    }

    private void generateRandomOrderArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(1001);
        }
    }
}
