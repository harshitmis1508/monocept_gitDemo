<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <span class="brand">&#127979; SCM System</span>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/students">Students</a>
        <a href="${pageContext.request.contextPath}/courses">Courses</a>
        <a href="${pageContext.request.contextPath}/registrations" class="active">Registrations</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>

<div class="container">
    <div class="card">
        <div class="page-header">
            <h2>Register Student for Course</h2>
            <a href="${pageContext.request.contextPath}/registrations" class="btn btn-secondary">&#8592; Back</a>
        </div>

        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/registration/save" method="post">

            <div class="form-row">
                <div class="form-group">
                    <label for="studentId">Select Student *</label>
                    <select id="studentId" name="studentId" required>
                        <option value="0">-- Select Student --</option>
                        <c:forEach var="s" items="${studentList}">
                            <option value="${s.studentId}">${s.studentName} (ID: ${s.studentId})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="courseId">Select Course *</label>
                    <select id="courseId" name="courseId" required>
                        <option value="0">-- Select Course --</option>
                        <c:forEach var="c" items="${courseList}">
                            <option value="${c.courseId}">${c.courseName} (ID: ${c.courseId})</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="registrationDate">Registration Date *</label>
                    <input type="date" id="registrationDate" name="registrationDate" required>
                </div>
                <div class="form-group">
                    <label for="status">Status *</label>
                    <select id="status" name="status" required>
                        <option value="">-- Select Status --</option>
                        <option value="Active">Active</option>
                        <option value="Completed">Completed</option>
                        <option value="Cancelled">Cancelled</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-success">Register</button>
            <a href="${pageContext.request.contextPath}/registrations"
               class="btn btn-secondary" style="margin-left:10px;">Cancel</a>
        </form>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
