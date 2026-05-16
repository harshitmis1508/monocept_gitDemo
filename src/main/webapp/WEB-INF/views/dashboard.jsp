<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Student Course Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar">
    <span class="brand">&#127979; SCM System</span>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/dashboard" class="active">Dashboard</a>
        <a href="${pageContext.request.contextPath}/students">Students</a>
        <a href="${pageContext.request.contextPath}/courses">Courses</a>
        <a href="${pageContext.request.contextPath}/registrations">Registrations</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>

<div class="container">

    <!-- Welcome -->
    <div class="card" style="margin-top:10px;">
        <h2>Welcome, ${sessionScope.loggedInUser}! &#128075;</h2>
        <p style="color:#666;margin-top:4px;">Here is your system overview.</p>
    </div>

    <!-- Stats -->
    <div class="stats-grid">
        <div class="stat-card">
            <div class="stat-number">${totalStudents}</div>
            <div class="stat-label">&#128100; Total Students</div>
        </div>
        <div class="stat-card green">
            <div class="stat-number">${totalCourses}</div>
            <div class="stat-label">&#128218; Total Courses</div>
        </div>
        <div class="stat-card orange">
            <div class="stat-number">${totalRegistrations}</div>
            <div class="stat-label">&#128203; Total Registrations</div>
        </div>
    </div>

    <!-- Quick Links -->
    <div class="card">
        <h2>Quick Actions</h2>
        <div class="quick-links">
            <a href="${pageContext.request.contextPath}/student/add"   class="quick-link-btn">+ Add Student</a>
            <a href="${pageContext.request.contextPath}/students"       class="quick-link-btn">View Students</a>
            <a href="${pageContext.request.contextPath}/course/add"    class="quick-link-btn">+ Add Course</a>
            <a href="${pageContext.request.contextPath}/courses"        class="quick-link-btn">View Courses</a>
            <a href="${pageContext.request.contextPath}/registration/add" class="quick-link-btn">+ Register Student</a>
            <a href="${pageContext.request.contextPath}/registrations"  class="quick-link-btn">View Registrations</a>
        </div>
    </div>

</div>

<div class="footer">Student Course Management System &copy; 2026</div>
</body>
</html>
