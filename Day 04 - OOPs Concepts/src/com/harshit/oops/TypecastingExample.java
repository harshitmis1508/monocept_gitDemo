package com.harshit.oops;

//public class TypecastingExample {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		  int a = 10;
//
//        double b = a;   //int → double (automatic)
//
//        System.out.println("Int value: " + a);
//        System.out.println("Double value: " + b);
//
//	}
//
//}

public class TypecastingExample {

    public static void main(String[] args) {

        double a = 10.75;

        int b = (int) a;   //double → int (manual casting)

        System.out.println("Double value: " + a);
        System.out.println("Int value: " + b);
    }
}

