package Arrays;

import java.util.Scanner;

public class PrintArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner  = new Scanner(System.in);
		System.out.print("Enter size of array: ");
		int size = scanner.nextInt();

			if (size <= 0) {
				System.out.println("Invalid size ! Please enter the postive size.");
			return;
		}

		int[] arr = new int[size];

		System.out.println("Enter " + size + " elements:");

		for (int i = 0; i < size; i++) {
			arr[i] = scanner.nextInt();
		}

		System.out.println("Array elements are:");

		for (int num : arr) {
			System.out.print(num + " ");
		}

		scanner.close();
	}
}
