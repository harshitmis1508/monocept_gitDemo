package util;

import java.util.Scanner;
import config.GameConfig;

public class InputValidator {

    private Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getValidGuess() {

        while (true) {

            System.out.print("Guess a number (" + GameConfig.MIN_NUMBER + "-" + GameConfig.MAX_NUMBER + "): ");

            if (scanner.hasNextInt()) {

                int guess = scanner.nextInt();

                if (guess >= GameConfig.MIN_NUMBER && guess <= GameConfig.MAX_NUMBER) {
                    return guess;
                } else {
                    System.out.println("Invalid input. Number must be between 1 and 100.");
                }

            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    public int getMenuChoice() {

        while (true) {

            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();

                if (choice == 1 || choice == 2) {
                    return choice;
                }

                System.out.println("Invalid choice. Please enter 1 or 2.");

            } else {

                System.out.println("Invalid input. Enter a number.");
                scanner.next();
            }
        }
    }
}