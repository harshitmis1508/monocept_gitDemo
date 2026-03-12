package service;

import config.GameConfig;
import util.InputValidator;

public class Game {

    private int targetNumber;
    private int attempts;

    public Game(int targetNumber) {
        this.targetNumber = targetNumber;
        this.attempts = 0;
    }

    public void startGame(InputValidator validator) {

        while (attempts < GameConfig.MAX_ATTEMPTS) {

            int guess = validator.getValidGuess();
            attempts++;

            if (guess < targetNumber) {
                System.out.println("Sorry, Too Low");
            }
            else if (guess > targetNumber) {
                System.out.println("Sorry, Too High");
            }
            else {

                System.out.println("\n Congratulations!");
                System.out.println("You guessed the correct number.");
                System.out.println("Attempts taken: " + attempts);

                return;
            }

            int remaining = GameConfig.MAX_ATTEMPTS - attempts;

            if (remaining > 0) {
                System.out.println("Attempts left: " + remaining);
                System.out.println("Play carefully!\n");
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Correct number was: " + targetNumber);
    }
}