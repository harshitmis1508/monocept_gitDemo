package com.harshit.homework;
import java.util.Scanner;

public class FraudTransactionDetector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);

        int n;
        while (true) {
            System.out.print("Enter number of transactions: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) break;
            } else scanner.next();
            System.out.println("Invalid input! Try again.");
        }

        double[] transactions = new double[n];
        double total = 0;
        boolean fraud = false;

        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Transaction " + i + ": ");
                if (scanner.hasNextDouble()) {
                    transactions[i] = scanner.nextDouble();
                    if (transactions[i] >= 0) break;
                } else scanner.next();
                System.out.println("Invalid transaction! Try again.");
            }

            total += transactions[i];

            if (transactions[i] > 50000) {
                System.out.println("Suspicious at index " + i);
            }

            if (i > 0 && transactions[i] > 50000 && transactions[i-1] > 50000) {
                fraud = true;
            }
        }

        double avg = total / n;

        if (fraud) System.out.println("Potential Fraud Detected");
        if (avg > 40000) System.out.println("High Value Account");

        System.out.println("Average Transaction: " + avg);

	}

}
