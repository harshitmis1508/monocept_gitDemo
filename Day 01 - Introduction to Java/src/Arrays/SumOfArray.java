package Arrays;

import java.util.Scanner;

public class SumOfArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter size of array: ");
		int size = sc.nextInt();

			if (size <= 0) {
			System.out.println("Invalid size ! Please enter the postive size.");
			return;
		}

		int[] arr = new int[size];
		int sum = 0;

		System.out.println("Enter " + size + " elements:");

		for (int i = 0; i < size; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];   
			}

		System.out.println("Array elements:");
		
		for (int y : arr) {
			System.out.print(y + " ");
		}

		System.out.println("\nSum of elements = " + sum);

		sc.close();
	}
}
