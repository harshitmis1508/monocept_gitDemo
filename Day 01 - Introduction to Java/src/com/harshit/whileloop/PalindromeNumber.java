package com.harshit.whileloop;
import java.util.Scanner;

public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number: ");
		
		int number = scanner.nextInt();
		if(number < 0) {
			System.out.println("Invalid Number !");
			return;
		}
		
		int temp = number;
		int reverse = 0;
		while(temp > 0) {
			int digit = temp % 10;
			reverse = reverse*10 + digit;
			temp /= 10;
		}
		
		if(number == reverse) {
			System.out.println("Palindrome Number");
		}else {
			System.out.println("Not a palindrome number");
		}
		
		scanner.close();
	}

}
