package com.harshit.homework;
import java.util.Scanner;

public class StudentPerformance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);

        int n;  // number of students
        while (true) {
            System.out.print("Enter number of students: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) break;
            } else scanner.next();
            System.out.println("Invalid input! Try again.");
        }

        int[][] marks = new int[n][5];
        int distinctionCount = 0;
        double[] subjectTotal = new double[5];

        for (int i = 0; i < n; i++) {
            int total = 0;
            boolean fail = false;

            for (int j = 0; j < 5; j++) {
                while (true) {
                    System.out.print("Student " + i + " Subject " + j + " marks: ");
                    if (scanner.hasNextInt()) {
                        marks[i][j] = scanner.nextInt();
                        if (marks[i][j] >= 0 && marks[i][j] <= 100) break;
                    } else scanner.next();
                    System.out.println("Invalid marks! Try again.");
                }

                total += marks[i][j];
                subjectTotal[j] += marks[i][j];
                if (marks[i][j] < 35) fail = true;
            }

            double avg = total / 5.0;

            if (fail) System.out.println("Fail");
            else if (avg >= 85) {
                System.out.println("Distinction");
                distinctionCount++;
            }
            else if (avg >= 60) System.out.println("First Class");
            else if (avg >= 50) System.out.println("Second Class");
            else System.out.println("Pass");
        }

        System.out.println("Total Distinctions: " + distinctionCount);

        double maxAvg = 0;
        int subjectIndex = 0;

        for (int i = 0; i < 5; i++) {
            double avg = subjectTotal[i] / n;
            if (avg > maxAvg) {
                maxAvg = avg;
                subjectIndex = i;
            }
        }

        System.out.println("Subject with highest average: " + subjectIndex);

	}

}
