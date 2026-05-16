<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
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
            <h2>Add New Student</h2>
            <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">&#8592; Back</a>
        </div>

        <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="alert alert-danger">${errorMsg}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/student/add" method="post">

            <div class="form-row">
                <div class="form-group">
                    <label for="studentName">Student Name *</label>
                    <input type="text" id="studentName" name="studentName"
                           value="${studentName}" placeholder="Full name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email *</label>
                    <input type="email" id="email" name="email"
                           value="${email}" placeholder="email@example.com" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="phone">Phone *</label>
                    <input type="text" id="phone" name="phone"
                           value="${phone}" placeholder="10-digit mobile number" required>
                </div>
                <div class="form-group">
                    <label for="age">Age * (must be 18 or above)</label>
                    <input type="number" id="age" name="age"
                           value="${age}" min="18" placeholder="e.g. 20" required>
                </div>
            </div>

            <div class="form-group">
                <label for="city">City *</label>
                <input type="text" id="city" name="city"
                       value="${city}" placeholder="City" required>
            </div>

            <button type="submit" class="btn btn-success">Save Student</button>
            <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary"
               style="margin-left:10px;">Cancel</a>
        </form>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
