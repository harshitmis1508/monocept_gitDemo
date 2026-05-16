<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Course</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <span class="brand">&#127979; SCM System</span>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/students">Students</a>
        <a href="${pageContext.request.contextPath}/courses" class="active">Courses</a>
        <a href="${pageContext.request.contextPath}/registrations">Registrations</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>

<div class="container">
    <div class="card">
        <div class="page-header">
            <h2>Edit Course</h2>
            <a href="${pageContext.request.contextPath}/courses" class="btn btn-secondary">&#8592; Back</a>
        </div>

        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/course/update" method="post">
            <input type="hidden" name="courseId" value="${course.courseId}">

            <div class="form-row">
                <div class="form-group">
                    <label for="courseName">Course Name *</label>
                    <input type="text" id="courseName" name="courseName"
                           value="${course.courseName}" required>
                </div>
                <div class="form-group">
                    <label for="duration">Duration *</label>
                    <input type="text" id="duration" name="duration"
                           value="${course.duration}" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="fees">Fees (Rs.) *</label>
                    <input type="number" id="fees" name="fees"
                           value="${course.fees}" step="0.01" min="1" required>
                </div>
                <div class="form-group">
                    <label for="trainerName">Trainer Name *</label>
                    <input type="text" id="trainerName" name="trainerName"
                           value="${course.trainerName}" required>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Update Course</button>
            <a href="${pageContext.request.contextPath}/courses"
               class="btn btn-secondary" style="margin-left:10px;">Cancel</a>
        </form>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
