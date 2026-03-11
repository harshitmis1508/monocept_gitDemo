package arrays.example;

public class ArraysExamples {
	public static void main(String[] args) {

		// ================================
		// 1. SINGLE DIMENSIONAL ARRAY
		// ================================
		System.out.println("=== Single Dimensional Array ===");

		int[] marks = { 85, 90, 78, 92, 88 }; // Student marks

		// Print using for-each
		for (int m : marks) {
			System.out.println("Mark: " + m);
		}

		// Array length
		System.out.println("Total Students: " + marks.length);

		// ================================
		// 2. MULTIDIMENSIONAL ARRAY
		// ================================
		System.out.println("\n=== Multi Dimensional Array ===");

		// 3 students, 2 subjects each
		int[][] studentMarks = { { 85, 90 }, // Student 1 - Math, Science
				{ 78, 88 }, // Student 2 - Math, Science
				{ 92, 95 } // Student 3 - Math, Science
		};

		for (int i = 0; i < studentMarks.length; i++) {
			System.out.println(
					"Student " + (i + 1) + " -> Math: " + studentMarks[i][0] + ", Science: " + studentMarks[i][1]);
		}

		// ================================
		// 3. BUSINESS DOMAIN EXAMPLE
		// ================================
		System.out.println("\n=== Business Domain ===");

		// Product names and their prices
		String[] products = { "Laptop", "Phone", "Tablet" };
		int[] prices = { 50000, 20000, 15000 };

		for (int i = 0; i < products.length; i++) {
			System.out.println(products[i] + " -> Rs." + prices[i]);
		}

		// Find most expensive product
		int maxPrice = prices[0];
		String maxProduct = products[0];

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > maxPrice) {
				maxPrice = prices[i];
				maxProduct = products[i];
			}
		}
		System.out.println("Most Expensive: " + maxProduct + " @ Rs." + maxPrice);
	}
}