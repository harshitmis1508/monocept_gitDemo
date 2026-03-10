package com.harshit.evenodd;

import java.util.Scanner;

public class BillCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the height (cm): ");
		int height = scanner.nextInt();
		if(height <= 120) {
			System.out.println("Cant ride bike");
			return;
		}
		
		System.out.println("Enter the age: ");
		int age = scanner.nextInt();
		int totalBill = 0;
		
		if(age < 12) {
			totalBill += 5;
		}
		else if(age >= 12 && age >= 18) {
			totalBill += 7;
		}
		else if(age >= 45 && age <= 55) {
			totalBill += 0;
		}
		else {
			totalBill += 12;
		}
		
		 System.out.print("Want photos? (yes/no): ");
	        String photo = scanner.next();

	        if (photo.equalsIgnoreCase("yes")) {
	            totalBill += 3;
	        }

	        System.out.println("The total bill is $" + totalBill);

	        scanner.close();
	}

}
