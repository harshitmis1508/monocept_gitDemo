package com.harshit.oops;

class StudentData{
	
	private String name;
	private int age;
	
	StudentData(){
		System.out.println("Default Constructor is called");
		
	}
	
	StudentData(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
}

public class ConstructorsExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentData s1 = new StudentData();
		StudentData s2 = new StudentData("Harshit",22);
		
		System.out.println("Name is: " + (s2.getName()));
		System.out.println("Age is: " + (s2.getAge()));
		

	}

}
