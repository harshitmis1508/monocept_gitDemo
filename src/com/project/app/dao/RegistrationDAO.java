package com.project.app.dao;

import com.project.app.util.DBUtil;

import java.sql.*;

public class RegistrationDAO {

	public boolean isDuplicate(int studentId, String course) throws SQLException {
		String query = "SELECT * FROM registration WHERE student_id=? AND course_name=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, studentId);
			ps.setString(2, course);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}
	}

	public boolean insert(int studentId, String course, double fee, Connection conn) throws SQLException {
		String query = "INSERT INTO registration (student_id, course_name, fees_paid) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, studentId);
			ps.setString(2, course);
			ps.setDouble(3, fee);

			return ps.executeUpdate() > 0;
		}
	}

	public boolean updateFee(int studentId, String course, double fee) throws SQLException {
		String query = "UPDATE registration SET fees_paid=? WHERE student_id=? AND course_name=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setDouble(1, fee);
			ps.setInt(2, studentId);
			ps.setString(3, course);

			return ps.executeUpdate() > 0;
		}
	}

	public boolean deleteByStudent(int id, Connection conn) throws SQLException {
		String query = "DELETE FROM registration WHERE student_id=?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		}
	}

	public boolean cancel(int id, String course) throws SQLException {
		String query = "DELETE FROM registration WHERE student_id=? AND course_name=?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, id);
			ps.setString(2, course);

			return ps.executeUpdate() > 0;
		}
	}

	public ResultSet highPaying(double amt, Connection conn) throws SQLException {
		String query = "SELECT s.name, r.course_name, r.fees_paid FROM student s "
				+ "JOIN registration r ON s.id=r.student_id WHERE r.fees_paid > ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setDouble(1, amt);
		return ps.executeQuery();
	}

	public ResultSet courseCount(Connection conn) throws SQLException {
		String query = "SELECT course_name, COUNT(*) FROM registration GROUP BY course_name";
		PreparedStatement ps = conn.prepareStatement(query);
		return ps.executeQuery();
	}
}