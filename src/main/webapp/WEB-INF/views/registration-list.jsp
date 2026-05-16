<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registrations</title>
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
            <h2>&#128203; Registration List</h2>
            <a href="${pageContext.request.contextPath}/registration/add" class="btn btn-success">+ New Registration</a>
        </div>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">${param.error}</div>
        </c:if>

        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>ID</th>
                        <th>Student Name</th>
                        <th>Course Name</th>
                        <th>Registration Date</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty registrationList}">
                            <tr>
                                <td colspan="7" style="text-align:center;color:#888;padding:30px;">
                                    No registrations found.
                                    <a href="${pageContext.request.contextPath}/registration/add">Register a student now.</a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="r" items="${registrationList}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${r.registrationId}</td>
                                    <td>${r.studentName}</td>
                                    <td>${r.courseName}</td>
                                    <td>${r.registrationDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${r.status == 'Active'}">
                                                <span class="badge badge-active">Active</span>
                                            </c:when>
                                            <c:when test="${r.status == 'Completed'}">
                                                <span class="badge badge-completed">Completed</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-cancelled">Cancelled</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/registration/status?id=${r.registrationId}"
                                           class="btn btn-warning btn-sm">Update Status</a>
                                        <a href="${pageContext.request.contextPath}/registration/delete?id=${r.registrationId}"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Delete this registration?');">Delete</a>
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
