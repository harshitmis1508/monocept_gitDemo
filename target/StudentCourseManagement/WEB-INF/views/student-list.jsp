<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
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
            <h2>&#128100; Student List</h2>
            <a href="${pageContext.request.contextPath}/student/add" class="btn btn-success">+ Add Student</a>
        </div>

        <%-- Success / Error messages --%>
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">${param.error}</div>
        </c:if>
        <%-- Error set via RequestDispatcher --%>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
        </c:if>

        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Age</th>
                        <th>City</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty studentList}">
                            <tr>
                                <td colspan="8" style="text-align:center;color:#888;padding:30px;">
                                    No students found. <a href="${pageContext.request.contextPath}/student/add">Add one now.</a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="s" items="${studentList}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${s.studentId}</td>
                                    <td>${s.studentName}</td>
                                    <td>${s.email}</td>
                                    <td>${s.phone}</td>
                                    <td>${s.age}</td>
                                    <td>${s.city}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/student/edit?id=${s.studentId}"
                                           class="btn btn-warning btn-sm">Edit</a>
                                        <a href="${pageContext.request.contextPath}/student/delete?id=${s.studentId}"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Delete this student?');">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="footer">Student Course Management System &copy; 2024</div>
</body>
</html>
