package com.harshit.whileloop;
import java.util.Scanner;

public class SumOfdigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number: ");		
		int number = scanner.nextInt();
		if(number < 0) {
			System.out.println("Invalid Number ! Please enter positive number");
			return;

		}

		int temp = number;
		int sum = 0;
		
		while(temp > 0) {
			int digit = temp%10;
			sum += digit;
			temp /= 10;
		}
		
		System.out.println("Sum of digits is: " + (sum));
		scanner.close();

	}

}
