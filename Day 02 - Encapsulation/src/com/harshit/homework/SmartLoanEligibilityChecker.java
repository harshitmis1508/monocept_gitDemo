package com.harshit.homework;
import java.util.Scanner;

public class SmartLoanEligibilityChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);

	        int n;
	        while (true) {
	            System.out.print("Enter number of applicants: ");
	            if (scanner.hasNextInt()) {
	                n = scanner.nextInt();
	                if (n > 0) break;
	            } else scanner.next();
	            System.out.println("Invalid input! Try again.");
	        }

	        int approvals = 0, rejections = 0;
	        int bestIndex = -1;
	        int bestScore = 0;

	        for (int i = 0; i < n; i++) {

	            int creditScore;
	            while (true) {
	                System.out.print("Credit Score: ");
	                if (scanner.hasNextInt()) {
	                    creditScore = scanner.nextInt();
	                    if (creditScore >= 300 && creditScore <= 900) break;
	                } else scanner.next();
	                System.out.println("Invalid Credit Score! Try again.");
	            }

	            int income;
	            while (true) {
	                System.out.print("Monthly Income: ");
	                if (scanner.hasNextInt()) {
	                    income = scanner.nextInt();
	                    if (income > 0) break;
	                } else scanner.next();
	                System.out.println("Invalid Income! Try again.");
	            }

	            int loans;
	            while (true) {
	                System.out.print("Existing Loans: ");
	                if (scanner.hasNextInt()) {
	                    loans = scanner.nextInt();
	                    if (loans >= 0) break;
	                } else scanner.next();
	                System.out.println("Invalid Loan Count! Try again.");
	            }

	            if (creditScore < 600 || income < 25000 || loans >= 3) {
	                System.out.println("Rejected");
	                rejections++;
	            } else if (creditScore >= 800 && income > 100000) {
	                System.out.println("Instant Approval");
	                approvals++;
	            } else {
	                System.out.println("Standard Review");
	                approvals++;
	            }

	            if (creditScore > bestScore) {
	                bestScore = creditScore;
	                bestIndex = i;
	            }
	        }

	        System.out.println("Total Approvals: " + approvals);
	        System.out.println("Total Rejections: " + rejections);
	        System.out.println("Best Applicant Index: " + bestIndex);

	}

}
