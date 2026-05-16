package com.project.app.dao;

import com.project.app.model.Student;
import com.project.app.util.DBUtil;

import java.sql.*;

public class StudentDAO {

	public boolean addStudent(Student student) throws SQLException {
		String query = "INSERT INTO student (id, name, age, branch) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getBranch());

			return ps.executeUpdate() > 0;
		}
	}

	public boolean exists(int id) throws SQLException {
		String query = "SELECT id FROM student WHERE id=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}
	}

	public boolean updateStudent(int id, String name, String branch) throws SQLException {
		String query = "UPDATE student SET name=?, branch=? WHERE id=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, name);
			ps.setString(2, branch);
			ps.setInt(3, id);

			return ps.executeUpdate() > 0;
		}
	}

	public boolean deleteStudent(int id, Connection conn) throws SQLException {
		String query = "DELETE FROM student WHERE id=?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		}
	}

	public ResultSet getAll(Connection conn) throws SQLException {
		String query = "SELECT s.id, s.name, s.branch, r.course_name, r.fees_paid "
				+ "FROM student s LEFT JOIN registration r ON s.id=r.student_id";
		PreparedStatement ps = conn.prepareStatement(query);
		return ps.executeQuery();
	}

	public ResultSet getById(int id, Connection conn) throws SQLException {
		String query = "SELECT s.*, r.course_name, r.fees_paid FROM student s "
				+ "LEFT JOIN registration r ON s.id=r.student_id WHERE s.id=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		return ps.executeQuery();
	}
}