package com.harshit.homework;
import java.util.Scanner;

public class ElectricityUsage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        int[] usage = new int[30];
        int highCount = 0;
        int total = 0;
        boolean overload = false;

        for (int i = 0; i < 30; i++) {
            while (true) {
                System.out.print("Enter usage for day " + (i + 1) + ": ");
                if (sc.hasNextInt()) {
                    usage[i] = sc.nextInt();
                    if (usage[i] >= 0) break;
                } else sc.next();
                System.out.println("Invalid input! Try again.");
            }

            total += usage[i];

            if (usage[i] > 500) {
                System.out.println("High Consumption");
                highCount++;
            } else if (usage[i] < 100) {
                System.out.println("Low Usage Alert");
            } else {
                System.out.println("Normal Usage");
            }

            if (i >= 2 && usage[i] > 500 && usage[i-1] > 500 && usage[i-2] > 500) {
                overload = true;
            }
        }

        double avg = total / 30.0;

        if (overload) System.out.println("Overload Warning");
        if (avg > 400) System.out.println("Heavy Month");

        System.out.println("Average Usage: " + avg);
        System.out.println("High Consumption Days: " + highCount);

	}

}
