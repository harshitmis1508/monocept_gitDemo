package com.harshit.whileloop;
import java.util.Scanner;

public class ReverseNumber {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int number;

        do {
            System.out.print("Enter a positive number: ");
            number = scanner.nextInt();

            if (number < 0) {
                System.out.println("Invalid Number! Please enter positive number.\n");
            }

        } while (number < 0);


        int temp = number;
        int reverse = 0;

        while (temp > 0) {
            int digit = temp % 10;
            reverse = reverse * 10 + digit;
            temp /= 10;
        }

        System.out.println("Reversed Number is: " + reverse);

        scanner.close();
    }
}
