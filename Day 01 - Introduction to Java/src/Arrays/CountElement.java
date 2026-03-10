package Arrays;

import java.util.Scanner;

public class CountElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter size of array: ");
		int size = scanner.nextInt();

			if (size <= 0) {
			System.out.println("Invalid size! Size must be positive.");
			return;
		}

		int[] arr = new int[size];

		System.out.println("Enter " + size + " elements:");

		for (int i = 0; i < size; i++) {
			arr[i] = scanner.nextInt();
		}

		System.out.print("Enter element to count: ");
		int key = scanner.nextInt();

		int count = 0;

		for (int num : arr) {
			if (num == key) {
				count++;
			}
		}

		System.out.println("Element " + key + " appears " + count + " times.");

		scanner.close();
	}
}
