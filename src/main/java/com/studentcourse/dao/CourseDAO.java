package com.studentcourse.dao;

import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // ---------- CREATE ----------
    public boolean addCourse(Course c) {
        String sql = "INSERT INTO courses (course_name, duration, fees, trainer_name) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getDuration());
            ps.setDouble(3, c.getFees());
            ps.setString(4, c.getTrainerName());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[CourseDAO] addCourse error: " + e.getMessage());
        }
        return false;
    }

    // ---------- READ ALL ----------
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            System.err.println("[CourseDAO] getAllCourses error: " + e.getMessage());
        }
        return list;
    }

    // ---------- READ ONE ----------
    public Course getCourseById(int id) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("[CourseDAO] getCourseById error: " + e.getMessage());
        }
        return null;
    }

    // ---------- UPDATE ----------
    public boolean updateCourse(Course c) {
        String sql = "UPDATE courses SET course_name=?, duration=?, fees=?, trainer_name=? WHERE course_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getDuration());
            ps.setDouble(3, c.getFees());
            ps.setString(4, c.getTrainerName());
            ps.setInt(5, c.getCourseId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[CourseDAO] updateCourse error: " + e.getMessage());
        }
        return false;
    }

    // ---------- DELETE ----------
    public boolean deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[CourseDAO] deleteCourse error: " + e.getMessage());
        }
        return false;
    }

    // ---------- CHECK ACTIVE REGISTRATION ----------
    public boolean hasCourseActiveRegistration(int courseId) {
        String sql = "SELECT COUNT(*) FROM registrations WHERE course_id = ? AND status = 'Active'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("[CourseDAO] hasCourseActiveRegistration error: " + e.getMessage());
        }
        return false;
    }

    // ---------- COUNT ----------
    public int countCourses() {
        String sql = "SELECT COUNT(*) FROM courses";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.err.println("[CourseDAO] countCourses error: " + e.getMessage());
        }
        return 0;
    }

    // ---------- HELPER ----------
    private Course mapRow(ResultSet rs) throws SQLException {
        Course c = new Course();
        c.setCourseId(rs.getInt("course_id"));
        c.setCourseName(rs.getString("course_name"));
        c.setDuration(rs.getString("duration"));
        c.setFees(rs.getDouble("fees"));
        c.setTrainerName(rs.getString("trainer_name"));
        return c;
    }
}
