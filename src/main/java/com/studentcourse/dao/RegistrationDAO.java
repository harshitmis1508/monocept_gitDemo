package com.studentcourse.dao;

import com.studentcourse.model.Registration;
import com.studentcourse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    // ---------- CREATE ----------
    public boolean addRegistration(Registration r) {
        String sql = "INSERT INTO registrations (student_id, course_id, registration_date, status) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getStudentId());
            ps.setInt(2, r.getCourseId());
            ps.setDate(3, r.getRegistrationDate());
            ps.setString(4, r.getStatus());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] addRegistration error: " + e.getMessage());
        }
        return false;
    }

    // ---------- READ ALL (with join) ----------
    public List<Registration> getAllRegistrations() {
        List<Registration> list = new ArrayList<>();
        String sql = "SELECT r.*, s.student_name, c.course_name " +
                     "FROM registrations r " +
                     "JOIN students s ON r.student_id = s.student_id " +
                     "JOIN courses  c ON r.course_id  = c.course_id " +
                     "ORDER BY r.registration_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] getAllRegistrations error: " + e.getMessage());
        }
        return list;
    }

    // ---------- READ ONE ----------
    public Registration getRegistrationById(int id) {
        String sql = "SELECT r.*, s.student_name, c.course_name " +
                     "FROM registrations r " +
                     "JOIN students s ON r.student_id = s.student_id " +
                     "JOIN courses  c ON r.course_id  = c.course_id " +
                     "WHERE r.registration_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] getRegistrationById error: " + e.getMessage());
        }
        return null;
    }

    // ---------- UPDATE STATUS ----------
    public boolean updateStatus(int registrationId, String status) {
        String sql = "UPDATE registrations SET status = ? WHERE registration_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, registrationId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] updateStatus error: " + e.getMessage());
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteRegistration(int id) {
        String sql = "DELETE FROM registrations WHERE registration_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] deleteRegistration error: " + e.getMessage());
        }
        return false;
    }

    // ---------- DUPLICATE CHECK ----------
    public boolean isDuplicateActiveRegistration(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM registrations WHERE student_id=? AND course_id=? AND status='Active'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] isDuplicateActiveRegistration error: " + e.getMessage());
        }
        return false;
    }

    // ---------- COUNT ----------
    public int countRegistrations() {
        String sql = "SELECT COUNT(*) FROM registrations";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.err.println("[RegistrationDAO] countRegistrations error: " + e.getMessage());
        }
        return 0;
    }

    // ---------- HELPER ----------
    private Registration mapRow(ResultSet rs) throws SQLException {
        Registration r = new Registration();
        r.setRegistrationId(rs.getInt("registration_id"));
        r.setStudentId(rs.getInt("student_id"));
        r.setCourseId(rs.getInt("course_id"));
        r.setRegistrationDate(rs.getDate("registration_date"));
        r.setStatus(rs.getString("status"));
        r.setStudentName(rs.getString("student_name"));
        r.setCourseName(rs.getString("course_name"));
        return r;
    }
}
