package pigDice;

import java.util.Scanner;
import java.util.Random;

public class PigDiceGame {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		int menuChoice = 0;

		while (true) {

			System.out.println("\n===== PIG DICE GAME =====");
			System.out.println("1. Start Game");
			System.out.println("2. Exit");
			System.out.print("Enter your choice: ");

			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input! Please enter a number.");
				scanner.next();
				continue;
			}

			menuChoice = scanner.nextInt();

			if (menuChoice == 1) {
				startGame(scanner, random);
			} else if (menuChoice == 2) {
				System.out.println("Thank you for playing!");
				break;
			} else {
				System.out.println("Invalid choice! Please select 1 or 2.");
			}
		}

		scanner.close();
	}

	public static void startGame(Scanner scanner, Random random) {

		int totalScore = 0;
		int turn = 1;

		System.out.println("\nGame Started!");

		while (totalScore < 20) {

			int turnScore = 0;
			System.out.println("\nTurn " + turn);

			while (true) {

				System.out.print("Enter 'r' to roll or 'h' to hold: ");
				String input = scanner.next().toLowerCase();

				if (input.length() != 1 || (!input.equals("r") && !input.equals("h"))) {
					System.out.println("Invalid input! Please enter 'r' or 'h'.");
					continue;
				}

				char action = input.charAt(0);

				if (action == 'r') {

					int dice = random.nextInt(6) + 1;
					System.out.println("Dice rolled: " + dice);

					if (dice == 1) {
						System.out.println("You rolled 1! Turn over. No points added.");
						turnScore = 0;
						break;
					}

					turnScore += dice;
					System.out.println("Current turn score: " + turnScore);
				}

				else if (action == 'h') {

					totalScore += turnScore;

					System.out.println("Turn score added: " + turnScore);
					System.out.println("Total score: " + totalScore);

					break;
				}
			}

			turn++;
		}

		System.out.println("\nCongratulations!");
		System.out.println("You finished the game in " + (turn - 1) + " turns.");
		System.out.println("Game Over!");
	}
}