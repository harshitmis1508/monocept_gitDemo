package com.harshit.evenodd;

import java.util.Scanner;

public class PositiveNegativeChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner numberScanner = new Scanner(System.in);

		System.out.print("Enter number: ");
		int number = numberScanner.nextInt();

		if (number > 0) {
			System.out.println("Positive Number");
		} else if (number < 0) {
			System.out.println("Negative Number");
		} else {
			System.out.println("Zero");
		}

		numberScanner.close();
	}
}
