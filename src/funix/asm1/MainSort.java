package funix.asm1;

import java.util.Arrays;
import java.util.List;

public class MainSort {
    private static final String INPUT_PATH = "INPUT.TXT";
    private static final String BUBBLE_SORT_OUTPUT = "OUTPUT1.TXT";
    private static final String SELECTION_SORT_OUTPUT = "OUTPUT2.TXT";
    private static final String INSERTION_SORT_OUTPUT = "OUTPUT3.TXT";
    private static final String LINEAR_SEARCH_OUTPUT = "OUTPUT4.TXT";
    private static final String BINARY_SEARCH_OUTPUT = "OUTPUT5.TXT";
    private static final Sortable sortAlgorithm = new Algorithm();
    private static final SearchAble searchAlgorithm = new Algorithm();
    private static final Writable writer = new FileProcess();
    private static final Readable reader = new FileProcess();
    private static float[] array;

    /**
     * Main function shows number of choices.
     * First it show introducing message, and create an empty INPUT.TXT file
     * (to prevent FileNotFoundException if use choose other options than input from the beginning).
     * Then the program checks if user choose algorithm options (3,4,5,6,7) when
     * array is not available - INPUT.TXT file is empty, it prompt user for input.
     * When INPUT.TXT has value, it check if user choose algorithm options (3,4,5,6,7) before
     * reading the file first (option 2), then it will automatically read the file.
     * the program loop until user choose exit.
     * @param args
     */
    public static void main(String[] args) {
        introduce();
        createEmptyInputFile();

        while (true) {
            showChoices();

            String choice = Console.readString("Choice");

            if (choice.equals("0")) {
                System.out.println("Thank you! Have a great day!");
                break;
            }

            if (isBadChoice(choice))
                continue;

            handleChoice(choice);
        }
    }

    /**
     * Print introducing message.
     */
    public static void introduce() {
        System.out.println("Welcome To Sort Algorithm Program");
    }

    /**
     * Create an empty INPUT.TXT file from the beginning
     * to prevent FileNotFoundException if user other options (2,3,4,5,6,7)
     * but not input (option 1) first.
     */
    private static void createEmptyInputFile() {
        writer.writeFile(INPUT_PATH, "");
    }

    /**
     * Print choices
     */
    public static void showChoices() {
        System.out.println("\n+-------------------Menu------------------+");
        System.out.println("|      1.Input                            |");
        System.out.println("|      2.Output                           |");
        System.out.println("|      3.Bubble sort                      |");
        System.out.println("|      4.Selection sort                   |");
        System.out.println("|      5.Insertion sort                   |");
        System.out.println("|      6.Search > value                   |");
        System.out.println("|      7.Search = value                   |");
        System.out.println("|      0.Exit                             |");
        System.out.println("+-------------------Menu------------------+\n");
    }

    /**
     * Call function base on user choice.
     * @param choice - input choice from user
     */
    private static void handleChoice(String choice) {
        switch (choice) {
            case "1" -> inputArray();
            case "2" -> outputArray();
            case "3" -> {
                System.out.println("Bubble sort");
                bubbleSort();
            }
            case "4" -> {
                System.out.println("Selection sort");
                selectionSort();
            }
            case "5" -> {
                System.out.println("Insertion sort");
                insertionSort();
            }
            case "6" -> {
                System.out.println("Linear search");
                linearSearch();
            }
            case "7" -> {
                System.out.println("Binary search");
                binarySearch();
            }
            default -> System.out.println("Invalid choice! Please try again!\n");
        }
    }

    /**
     * Prompt user for array input:
     * first it gets the array length
     * then the array element details
     * and write the array string to file INPUT.TXT.
     */
    public static void inputArray() {
        int length = Console.readInteger("Input number of elements");
        array = new float[length];

        System.out.println("Input elements: ");
        for (int i = 0; i < length; i++) {
            array[i] = Console.readFloat("Element " + (i + 1));
        }

        writer.writeFile(INPUT_PATH, array);
    }

    /**
     * Read file INPUT.TXT and assign the value to array
     * in case file is empty which mean array is null,
     * print the message and return false,
     * otherwise print the array and return true.
     * @return - true if array has values, otherwise false.
     */
    public static boolean outputArray() {
        array = reader.readFile(INPUT_PATH);

        if (array == null) {
            System.out.println("Array is empty! Please input first.");
            return false;
        }

        String arrayString = Arrays.toString(array);
        System.out.println("Read from file");
        System.out.println("Array = " +
                arrayString.substring(1, arrayString.length() - 1));

        return true;
    }

    /**
     * Copy array to a new array
     * then use bubble sort for sorting
     * and write the result into OUTPUT1.TXT.
     */
    public static void bubbleSort() {
        float[] sortedArray = copyArray();
        sortAlgorithm.bubbleSort(sortedArray);
        writer.writeFile(BUBBLE_SORT_OUTPUT, sortedArray);
    }

    /**
     * Copy array to a new array
     * then use selection sort for sorting
     * and write the result into OUTPUT2.TXT.
     */
    public static void selectionSort() {
        float[] sortedArray = copyArray();
        sortAlgorithm.selectionSort(sortedArray);
        writer.writeFile(SELECTION_SORT_OUTPUT, sortedArray);
    }

    /**
     * Copy array to a new array
     * then use insertion sort for sorting
     * and write the result into OUTPUT3.TXT.
     */
    public static void insertionSort() {
        float[] sortedArray = copyArray();
        sortAlgorithm.insertionSort(sortedArray);
        writer.writeFile(INSERTION_SORT_OUTPUT, sortedArray);
    }

    /**
     * Prompt user for search value
     * then use linear search to find value greater than search value
     * print the result indexes
     * and write the result into OUTPUT4.TXT.
     */
    public static void linearSearch() {
        float value = Console.readFloat("Input value");
        List<Integer> indexes = searchAlgorithm.linearSearch(array, value);

        if (indexes.isEmpty()) {
            System.out.println("No value greater than input value.");
            writer.writeFile(LINEAR_SEARCH_OUTPUT, "");

            return;
        }

        String indexString = indexes.toString();
        String outputString = indexString.substring(1, indexString.length() - 1);

        System.out.println("Indexes: " + outputString);
        writer.writeFile(LINEAR_SEARCH_OUTPUT, outputString);
    }

    /**
     * Prompt user for search value
     * then use binary search to find value index
     * print the result
     * and write the result into OUTPUT5.TXT.
     */
    public static void binarySearch() {
        float[] sortedArray = copyArray();
        Arrays.sort(sortedArray);
        System.out.println("Sorted array: " +
                Arrays.toString(sortedArray));

        float value = Console.readFloat("Input value");
        int index = searchAlgorithm.binarySearch(sortedArray, value);
        System.out.println("Index of first element: " + index);
        writer.writeFile(BINARY_SEARCH_OUTPUT, "" + index);
    }

    /**
     * Copy original array to a new array
     * @return - new array
     */
    private static float[] copyArray() {
        float[] newArray = new float[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    /**
     * Check if user do bad choice:
     * choose algorithm options (3,4,5,6,7)
     * before input to the file INPUT.TXT - means the file is empty, and array is null,
     * so array is not available for those options.
     * @param choice - input choice from user
     * @return - true/false
     */
    private static boolean isBadChoice(String choice) {
        String algorithmOption = "34567";

        return algorithmOption.contains(choice) &&
                                  array == null &&
                                    !outputArray();
    }
}
