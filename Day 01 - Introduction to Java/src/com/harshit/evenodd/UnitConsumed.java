package com.harshit.evenodd;
import java.util.Scanner;

public class UnitConsumed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the unit consumed: ");
		int unit = scanner.nextInt();
		int meterCost = 75;
		int cost = 0;
		
		if(unit <= 100) {
			cost = unit*5;
		}
		else if(unit <= 250) {
			cost = unit*10;
		}
		else {
			cost = unit*20;
		}

		 int total_water_bill = cost + meterCost;

	        System.out.println("Charge = " + cost);
	        System.out.println("Meter Charge = " + meterCost);
	        System.out.println("Total Water Bill = " + total_water_bill);

	        scanner.close();
	}

}
