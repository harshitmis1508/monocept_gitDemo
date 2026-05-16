package com.studentcourse.dao;

import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ---------- CREATE ----------
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (student_name, email, phone, age, city) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setInt(4, s.getAge());
            ps.setString(5, s.getCity());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[StudentDAO] addStudent error: " + e.getMessage());
        }
        return false;
    }

    // ---------- READ ALL ----------
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[StudentDAO] getAllStudents error: " + e.getMessage());
        }
        return list;
    }

    // ---------- READ ONE ----------
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("[StudentDAO] getStudentById error: " + e.getMessage());
        }
        return null;
    }

    // ---------- UPDATE ----------
    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET student_name=?, email=?, phone=?, age=?, city=? WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setInt(4, s.getAge());
            ps.setString(5, s.getCity());
            ps.setInt(6, s.getStudentId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[StudentDAO] updateStudent error: " + e.getMessage());
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[StudentDAO] deleteStudent error: " + e.getMessage());
        }
        return false;
    }

    // ---------- CHECK REGISTRATION ----------
    public boolean isStudentRegistered(int studentId) {
        String sql = "SELECT COUNT(*) FROM registrations WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("[StudentDAO] isStudentRegistered error: " + e.getMessage());
        }
        return false;
    }

    // ---------- COUNT ----------
    public int countStudents() {
        String sql = "SELECT COUNT(*) FROM students";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.err.println("[StudentDAO] countStudents error: " + e.getMessage());
        }
        return 0;
    }

    // ---------- HELPER ----------
    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setStudentName(rs.getString("student_name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setAge(rs.getInt("age"));
        s.setCity(rs.getString("city"));
        return s;
    }
}
