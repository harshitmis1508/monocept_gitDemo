package com.harshit.evenodd;

import java.util.Scanner;

public class IsPrimeOrNot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner numberScanner = new Scanner(System.in);

		System.out.print("Enter number: ");
		int n = numberScanner.nextInt();

		boolean isPrime = true;

		if (n <= 1) {
			isPrime = false;
		} else {
			for (int i = 2; i * i <= n; i++) {
				if (n % i == 0) {
					isPrime = false;
					break;
				}
			}
		}

		if (isPrime)
			System.out.println("Prime Number");
		else
			System.out.println("Not Prime Number");

		numberScanner.close();
	}
}
