<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <span class="brand">&#127979; SCM System</span>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/students" class="active">Students</a>
        <a href="${pageContext.request.contextPath}/courses">Courses</a>
        <a href="${pageContext.request.contextPath}/registrations">Registrations</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>

<div class="container">
    <div class="card">
        <div class="page-header">
            <h2>Edit Student</h2>
            <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">&#8592; Back</a>
        </div>

        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/student/update" method="post">
            <input type="hidden" name="studentId" value="${student.studentId}">

            <div class="form-row">
                <div class="form-group">
                    <label for="studentName">Student Name *</label>
                    <input type="text" id="studentName" name="studentName"
                           value="${student.studentName}" required>
                </div>
                <div class="form-group">
                    <label for="email">Email *</label>
                    <input type="email" id="email" name="email"
                           value="${student.email}" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="phone">Phone *</label>
                    <input type="text" id="phone" name="phone"
                           value="${student.phone}" required>
                </div>
                <div class="form-group">
                    <label for="age">Age * (must be 18 or above)</label>
                    <input type="number" id="age" name="age"
                           value="${student.age}" min="18" required>
                </div>
            </div>

            <div class="form-group">
                <label for="city">City *</label>
                <input type="text" id="city" name="city"
                       value="${student.city}" required>
            </div>

            <button type="submit" class="btn btn-primary">Update Student</button>
            <a href="${pageContext.request.contextPath}/students"
               class="btn btn-secondary" style="margin-left:10px;">Cancel</a>
        </form>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
