package com.project.app.util;

import java.util.Scanner;

public class InputUtil {

	private static Scanner sc = new Scanner(System.in);

	public static int getValidInt(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				int val = Integer.parseInt(sc.nextLine());
				return val;
			} catch (Exception e) {
				System.out.println("Invalid number, try again.");
			}
		}
	}

	public static String getValidString(String msg) {
		while (true) {
			System.out.print(msg);
			String val = sc.nextLine().trim();

			if (!val.isEmpty())
				return val;

			System.out.println("Input cannot be empty.");
		}
	}

	public static double getValidFee(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				double val = Double.parseDouble(sc.nextLine());

				if (val > 0)
					return val;

				System.out.println("Fee must be positive.");
			} catch (Exception e) {
				System.out.println("Invalid fee, try again.");
			}
		}
	}
}