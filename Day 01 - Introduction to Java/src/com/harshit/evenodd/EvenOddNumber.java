package com.harshit.evenodd;

import java.util.Scanner;

public class EvenOddNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner evenOddNumber = new Scanner(System.in);
		int number = evenOddNumber.nextInt();

		if (number % 2 == 0) {
			System.out.println("The entered number " + (number) + " is Even");
		} else {
			System.out.println("The entered number " + (number) + " is Odd");
		}

		evenOddNumber.close();

	}

}
