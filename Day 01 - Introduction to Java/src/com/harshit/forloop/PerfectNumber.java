package com.harshit.forloop;
import java.util.Scanner;

public class PerfectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Number: ");
		
		int number = scanner.nextInt();
		int sum = 0;
		
		for(int i=1;i<number;i++) {
			if(number % i == 0) {
				sum += i;
			}
		}
		
		if(sum == number) {
			System.out.println("You have entered a Perfect Number");
		}else {
			System.out.println("The number you have entered is not a Perfect Number");
		}
		
		scanner.close();

	}

}
