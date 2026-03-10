package com.harshit.homework;

import java.util.Scanner;

public class InsuranceRiskPortfolioAnalyzer {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int n; // customers

		// Validate Number of Customers
		while (true) {
			System.out.println("Enter number of customers: ");

			if (!scanner.hasNextInt()) {
				System.out.println("Please enter valid customer number !! ");
				scanner.next();
				continue;
			}

			n = scanner.nextInt();

			if (n <= 0) {
				System.out.println("Please enter positive number of customers !! ");
			} else {
				break;
			}
		}

		int[] ages = new int[n];
		int[] riskScores = new int[n];

		// age validation 
		for (int i = 0; i < n; i++) {

			while (true) {
				System.out.print("Enter age for customer " + i + ": ");

				if (!scanner.hasNextInt()) {
					System.out.println("Invalid input! Enter valid age.");
					scanner.next();
					continue;
				}

				int age = scanner.nextInt();

				if (age <= 0 || age > 120) {
					System.out.println("Age must be between 1 and 120.");
				} else {
					ages[i] = age;
					break;
				}
			}
		}

		//Validate risk scores
		for (int i = 0; i < n; i++) {

			while (true) {
				System.out.print("Enter risk score (0-100) for customer " + i + ": ");

				if (!scanner.hasNextInt()) {
					System.out.println("Invalid input! Enter valid number.");
					scanner.next();
					continue;
				}

				int score = scanner.nextInt();

				if (score < 0 || score > 100) {
					System.out.println("Risk score must be between 0 and 100.");
				} else {
					riskScores[i] = score;
					break;
				}
			}
		}

		// process each cateogry risk
		int highRiskYouth = 0;
		int seniorRisk = 0;
		int veryHighRisk = 0;
		int normalRisk = 0;

		int totalRisk = 0;
		int highestIndex = 0;

		System.out.println("\nCustomer Classification:");

		for (int i = 0; i < n; i++) {

			String category;

			if (riskScores[i] >= 85) {
				category = "Very High Risk";
				veryHighRisk++;
			} else if (ages[i] >= 60) {
				category = "Senior Risk";
				seniorRisk++;
			} else if (ages[i] < 25 && riskScores[i] > 70) {
				category = "High Risk Youth";
				highRiskYouth++;
			} else {
				category = "Normal Risk";
				normalRisk++;
			}

			System.out.println("Customer " + i + ": " + category);

			totalRisk += riskScores[i];

			if (riskScores[i] > riskScores[highestIndex]) {
				highestIndex = i;
			}
		}

		double averageRisk = (double) totalRisk / n;

	
		System.out.println("\nSummary:");
		
		System.out.println("Very High Risk Count: " + veryHighRisk);
		System.out.println("Senior Risk Count: " + seniorRisk);
		System.out.println("High Risk Youth Count: " + highRiskYouth);
		System.out.println("Normal Risk Count: " + normalRisk);
		System.out.println("Average Risk Score: " + averageRisk);
		System.out.println("Highest Risk Customer Index: " + highestIndex);

		scanner.close();
	}
}
