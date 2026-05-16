<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Course</title>
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
            <h2>Add New Course</h2>
            <a href="${pageContext.request.contextPath}/courses" class="btn btn-secondary">&#8592; Back</a>
        </div>

        <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="alert alert-danger">${errorMsg}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/course/add" method="post">

            <div class="form-row">
                <div class="form-group">
                    <label for="courseName">Course Name *</label>
                    <input type="text" id="courseName" name="courseName"
                           value="${courseName}" placeholder="e.g. Java Programming" required>
                </div>
                <div class="form-group">
                    <label for="duration">Duration *</label>
                    <input type="text" id="duration" name="duration"
                           value="${duration}" placeholder="e.g. 3 Months" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="fees">Fees (Rs.) *</label>
                    <input type="number" id="fees" name="fees"
                           value="${fees}" step="0.01" min="1"
                           placeholder="e.g. 5000" required>
                </div>
                <div class="form-group">
                    <label for="trainerName">Trainer Name *</label>
                    <input type="text" id="trainerName" name="trainerName"
                           value="${trainerName}" placeholder="Trainer full name" required>
                </div>
            </div>

            <button type="submit" class="btn btn-success">Save Course</button>
            <a href="${pageContext.request.contextPath}/courses"
               class="btn btn-secondary" style="margin-left:10px;">Cancel</a>
        </form>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
