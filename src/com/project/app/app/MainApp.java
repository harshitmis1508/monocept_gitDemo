package com.project.app.app;

import com.project.app.model.Student;
import com.project.app.service.StudentService;
import com.project.app.util.InputUtil;

public class MainApp {

	public static void main(String[] args) {

		StudentService service = new StudentService();

		while (true) {

			System.out.println("\n===== MENU =====");
			System.out.println("1 Add Student");
			System.out.println("2 Register Course");
			System.out.println("3 View All");
			System.out.println("4 Search Student");
			System.out.println("5 Update Student");
			System.out.println("6 Update Fee");
			System.out.println("7 Cancel Registration");
			System.out.println("8 Delete Student");
			System.out.println("9 High Paying Report");
			System.out.println("10 Course Count");
			System.out.println("11 Exit");

			int ch = InputUtil.getValidInt("Enter choice: ");

			switch (ch) {

			case 1:
				int id = InputUtil.getValidInt("Enter ID: ");
				String name = InputUtil.getValidString("Enter Name: ");
				int age;

				while (true) {
					age = InputUtil.getValidInt("Enter Age: ");
					if (age > 0)
						break;
					System.out.println("Age must be > 0");
				}

				String branch = InputUtil.getValidString("Enter Branch: ");

				service.addStudent(new Student(id, name, age, branch));
				break;

			case 2:
				int sid = InputUtil.getValidInt("Enter Student ID: ");
				String course = InputUtil.getValidString("Enter Course: ");
				double fee = InputUtil.getValidFee("Enter Fee: ");

				service.registerCourse(sid, course, fee);
				break;

			case 3:
				service.viewAll();
				break;

			case 4:
				int searchId = InputUtil.getValidInt("Enter ID: ");
				service.search(searchId);
				break;

			case 5:
				int uid = InputUtil.getValidInt("Enter ID: ");
				String newName = InputUtil.getValidString("Enter New Name: ");
				String newBranch = InputUtil.getValidString("Enter New Branch: ");

				service.updateStudent(uid, newName, newBranch);
				break;

			case 6:
				int fid = InputUtil.getValidInt("Enter Student ID: ");
				String fcourse = InputUtil.getValidString("Enter Course: ");
				double ffee = InputUtil.getValidFee("Enter New Fee: ");

				service.updateFee(fid, fcourse, ffee);
				break;

			case 7:
				int cid = InputUtil.getValidInt("Enter Student ID: ");
				String ccourse = InputUtil.getValidString("Enter Course: ");

				service.cancel(cid, ccourse);
				break;

			case 8:
				int did = InputUtil.getValidInt("Enter Student ID: ");
				service.deleteStudent(did);
				break;

			case 9:
				double amt = InputUtil.getValidFee("Enter amount: ");
				service.highPaying(amt);
				break;

			case 10:
				service.courseCount();
				break;

			case 11:
				System.out.println("Exiting...");
				return;

			default:
				System.out.println("Invalid choice, try again.");
			}
		}
	}
}