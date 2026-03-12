package main;

import java.util.Scanner;

import service.Game;
import service.NumberGenerator;
import util.InputValidator;

public class NumberGuessGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        NumberGenerator generator = new NumberGenerator();
        InputValidator validator = new InputValidator(scanner);

        while (true) {

            System.out.println("\n===== NUMBER GUESS GAME =====");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");

            int choice = validator.getMenuChoice();

            if (choice == 2) {
                System.out.println("Thanks for playing!");
                break;
            }

            int randomNumber = generator.generateNumber();

            Game game = new Game(randomNumber);

            game.startGame(validator);
        }

        scanner.close();
    }
}