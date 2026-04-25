package com.project.app.service;

import com.project.app.dao.StudentDAO;
import com.project.app.dao.RegistrationDAO;
import com.project.app.model.Student;
import com.project.app.util.DBUtil;

import java.sql.*;

public class StudentService {

	private StudentDAO studentDAO = new StudentDAO();
	private RegistrationDAO regDAO = new RegistrationDAO();

	public void addStudent(Student s) {
		try {
			if (studentDAO.exists(s.getId())) {
				System.out.println("Duplicate ID");
				return;
			}
			studentDAO.addStudent(s);
			System.out.println("Student added");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerCourse(int id, String course, double fee) {
		if (fee <= 0) {
			System.out.println("Invalid fee");
			return;
		}

		try (Connection conn = DBUtil.getConnection()) {
			conn.setAutoCommit(false);

			if (!studentDAO.exists(id))
				throw new Exception("Student not found");
			if (regDAO.isDuplicate(id, course))
				throw new Exception("Duplicate");

			regDAO.insert(id, course, fee, conn);

			conn.commit();
			System.out.println("Registered");

		} catch (Exception e) {
			System.out.println("Rollback");
			e.printStackTrace();
		}
	}

	public void viewAll() {
		try (Connection conn = DBUtil.getConnection()) {
			ResultSet rs = studentDAO.getAll(conn);

			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("course_name")
						+ " " + rs.getDouble("fees_paid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search(int id) {
		try (Connection conn = DBUtil.getConnection()) {
			ResultSet rs = studentDAO.getById(id, conn);

			boolean found = false;
			while (rs.next()) {
				found = true;
				System.out.println(rs.getString("name") + " " + rs.getString("course_name"));
			}
			if (!found)
				System.out.println("Not found");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(int id, String name, String branch) {
		try {
			if (!studentDAO.exists(id)) {
				System.out.println("Not found");
				return;
			}
			studentDAO.updateStudent(id, name, branch);
			System.out.println("Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateFee(int id, String course, double fee) {
	    if (fee <= 0) {
	        System.out.println("Invalid fee");
	        return;
	    }

	    try {
	        boolean updated = regDAO.updateFee(id, course.trim(), fee);

	        if (updated) {
	            System.out.println("Fee updated successfully");
	        } else {
	            System.out.println("No record found (check ID or course name)");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void cancel(int id, String course) {
		try {
			regDAO.cancel(id, course);
			System.out.println("Cancelled");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {
		try (Connection conn = DBUtil.getConnection()) {
			conn.setAutoCommit(false);

			regDAO.deleteByStudent(id, conn);
			studentDAO.deleteStudent(id, conn);

			conn.commit();
			System.out.println("Deleted");

		} catch (Exception e) {
			System.out.println("Rollback");
			e.printStackTrace();
		}
	}

	public void highPaying(double amt) {
		try (Connection conn = DBUtil.getConnection()) {
			ResultSet rs = regDAO.highPaying(amt, conn);

			while (rs.next()) {
				System.out.println(rs.getString("name") + " " + rs.getDouble("fees_paid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void courseCount() {
		try (Connection conn = DBUtil.getConnection()) {
			ResultSet rs = regDAO.courseCount(conn);

			while (rs.next()) {
				System.out.println(rs.getString(1) + " -> " + rs.getInt(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}