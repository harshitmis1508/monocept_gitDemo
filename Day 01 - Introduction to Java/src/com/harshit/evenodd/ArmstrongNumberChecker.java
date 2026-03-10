package com.harshit.evenodd;

import java.util.Scanner;

public class ArmstrongNumberChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner armStrongNumber = new Scanner(System.in);
		System.out.println("Enter number: ");

		int number = armStrongNumber.nextInt();
		int originalNumber = number;

		int temp = number;
		int countDigit = 0;

		while (temp > 0) {
			countDigit++;
			temp /= 10;
		}

		int sum = 0;

		while (number > 0) {
			int digit = number % 10;

			int power = 1;
			for (int i = 0; i < countDigit; i++) {
				power *= digit;
			}
			sum += power;
			number /= 10;
		}

		if (sum == originalNumber)
			System.out.println("Armstrong Number");
		else
			System.out.println("Not Armstrong Number");

		armStrongNumber.close();
	}
}
