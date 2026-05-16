<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Registration Status</title>
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
    <div class="card" style="max-width:500px;margin:30px auto;">
        <div class="page-header">
            <h2>Update Registration Status</h2>
            <a href="${pageContext.request.contextPath}/registrations" class="btn btn-secondary">&#8592; Back</a>
        </div>

        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
        </c:if>

        <!-- Registration Info -->
        <div style="background:#f5f7ff;padding:14px;border-radius:6px;margin-bottom:20px;font-size:.9rem;">
            <p><strong>Registration ID:</strong> ${registration.registrationId}</p>
            <p><strong>Student:</strong> ${registration.studentName}</p>
            <p><strong>Course:</strong> ${registration.courseName}</p>
            <p><strong>Date:</strong> ${registration.registrationDate}</p>
            <p><strong>Current Status:</strong> ${registration.status}</p>
        </div>

        <form action="${pageContext.request.contextPath}/registration/status" method="post">
            <input type="hidden" name="registrationId" value="${registration.registrationId}">

            <div class="form-group">
                <label for="status">New Status *</label>
                <select id="status" name="status" required>
                    <option value="Active"    ${registration.status == 'Active'    ? 'selected' : ''}>Active</option>
                    <option value="Completed" ${registration.status == 'Completed' ? 'selected' : ''}>Completed</option>
                    <option value="Cancelled" ${registration.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Update Status</button>
            <a href="${pageContext.request.contextPath}/registrations"
               class="btn btn-secondary" style="margin-left:10px;">Cancel</a>
        </form>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
