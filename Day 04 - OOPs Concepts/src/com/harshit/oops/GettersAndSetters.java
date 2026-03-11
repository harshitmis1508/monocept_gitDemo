package com.harshit.oops;


 class Student{
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		if(age > 0) {
			this.age = age;
		}else {
			System.out.println("Please enter valid age");
		}
	}
	
}

public class GettersAndSetters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s = new Student();
		
		s.setName("Harshit");
		s.setAge(22);
		
		System.out.println("Name is: " + (s.getName()));
		System.out.println("Age is: " + (s.getAge()));

	}

}
