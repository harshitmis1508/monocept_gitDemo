package com.harshit.oops;

//public class WrapperClassExample {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		 int a = 10;   // primitive type
//
//	     Integer obj = Integer.valueOf(a);  // primitive ko object me convert
//
//	     System.out.println("Primitive: " + a);
//	     System.out.println("Wrapper Object: " + obj);
//	     
//	     
//	}
//
//}


// string to integer 


public class WrapperClassExample {

    public static void main(String[] args) {

        String s = "25";

        Integer num = Integer.valueOf(s);

        System.out.println("Number is: " + num);
    }
}

