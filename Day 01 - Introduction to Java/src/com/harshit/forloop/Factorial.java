package com.harshit.forloop;
import java.util.Scanner;
public class Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int number = scanner.nextInt();
		
		int fact = 1;
		
		for(int i=1;i<=number;i++) {
			fact = fact*i;
		}
		
		System.out.println("Factorial = " + fact);


	}

}
