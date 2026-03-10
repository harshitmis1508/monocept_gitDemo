package com.harshit.whileloop;

import java.util.Scanner;
import java.util.Random;

public class NumberGuesserGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String playAgain;

        do {

            int secretNumber = random.nextInt(100) + 1;
            int maxAttempts = 5;
            boolean guessed = false;

            System.out.println("Guess number between 1 to 100");

            for(int attempt = 1; attempt <= maxAttempts; attempt++) {

                System.out.print("Attempt " + attempt + ": Enter guess -> ");
                int guess = sc.nextInt();

                if(guess < secretNumber) {
                    System.out.println("Sorry, Too Low");
                }
                else if(guess > secretNumber) {
                    System.out.println("Sorry, Too High");
                }
                else {
                    System.out.println("You won in attempt: " + attempt);
                    guessed = true;
                    break;
                }
            }

            if(!guessed) {
                System.out.println("You lost! Number was: " + secretNumber);
            }

            System.out.print("Play again? (yes/no): ");
            playAgain = sc.next();

        } while(playAgain.equalsIgnoreCase("yes"));

        sc.close();
        System.out.println("Game Ended. Thanks for playing!");
    }
}
