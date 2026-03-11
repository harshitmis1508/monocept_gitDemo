package loops.example;

public class AllLoops {
	public static void main(String[] args) {

		int[] numbers = { 10, 20, 30, 40, 50 };

		// 1. FOR LOOP
		System.out.println("=== FOR LOOP ===");
		for (int i = 0; i < 5; i++) {
			System.out.println("Count: " + i);
		}

		// 2. WHILE LOOP
		System.out.println("\n=== WHILE LOOP ===");
		int i = 1;
		while (i <= 5) {
			System.out.println("Number: " + i);
			i++;
		}

		// 3. DO-WHILE LOOP
		System.out.println("\n=== DO-WHILE LOOP ===");
		int j = 1;
		do {
			System.out.println("Value: " + j);
			j++;
		} while (j <= 5);

		// 4. FOR-EACH LOOP
		System.out.println("\n=== FOR-EACH LOOP ===");
		for (int num : numbers) {
			System.out.println("Element: " + num);
		}
	}
}