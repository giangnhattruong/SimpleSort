package funix.asm1;

import java.util.Scanner;

/**
 * Console class contains static members,
 * it prompts for user input, then validates the input
 * and return the valid input result.
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Read String prints the prompt message,
     * then prompts user input, validates if input is not ""
     * return the input
     * @param prompt - prompt message
     * @return - valid string input from user
     */
    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        String line = scanner.nextLine();
        while (line.equals("")) {
            line = scanner.nextLine();
        }
        return line;
    }

    /**
     * Read Integer prints the prompt message,
     * then prompts user input, validates if input is not an integer
     * return the input
     * @param prompt - prompt message
     * @return - valid integer input from user
     */
    public static int readInteger(String prompt) {
        int result;

        while (true) {
            System.out.print(prompt + ": ");

            try {
                String input = scanner.nextLine();
                result = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please try again.");
                continue;
            }
        }

        return result;
    }

    /**
     * Read Float prints the prompt message,
     * then prompts user input, validates if input is not a float
     * return the input
     * @param prompt - prompt message
     * @return - valid float input from user
     */
    public static float readFloat(String prompt) {
        float result;

        while (true) {
            System.out.print(prompt + ": ");

            try {
                String input = scanner.nextLine();
                result = Float.parseFloat(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please try again.");
                continue;
            }
        }

        return result;
    }
}
