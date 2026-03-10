package Arrays;
import java.util.Scanner;

public class SecondMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter size of array: ");
		int size = scanner.nextInt();

			if (size <= 0) {
			System.out.println("Invalid size! Size must be positive.");
			return;
		}

		if (size < 2) {
			System.out.println("Second maximum not possible (need at least 2 elements).");
			return;
		}

		int[] arr = new int[size];

		System.out.println("Enter " + size + " elements:");

		for (int i = 0; i < size; i++) {
			arr[i] = scanner.nextInt();
		}

		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;

		for (int num : arr) {

			if (num > max) {
				secondMax = max;
				max = num;
			}
			else if (num > secondMax && num != max) {
				secondMax = num;
			}
		}

		if (secondMax == Integer.MIN_VALUE) {
			System.out.println("No second maximum element (all elements same).");
		} else {
			System.out.println("Second maximum element = " + secondMax);
		}

		scanner.close();
	}
}
