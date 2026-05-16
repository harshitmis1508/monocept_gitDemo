<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
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
            <h2>&#128218; Course List</h2>
            <a href="${pageContext.request.contextPath}/course/add" class="btn btn-success">+ Add Course</a>
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
                        <th>Course Name</th>
                        <th>Duration</th>
                        <th>Fees (Rs.)</th>
                        <th>Trainer</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty courseList}">
                            <tr>
                                <td colspan="7" style="text-align:center;color:#888;padding:30px;">
                                    No courses found. <a href="${pageContext.request.contextPath}/course/add">Add one now.</a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="c" items="${courseList}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${c.courseId}</td>
                                    <td>${c.courseName}</td>
                                    <td>${c.duration}</td>
                                    <td>
                                        <fmt:formatNumber value="${c.fees}" type="number"
                                                          minFractionDigits="2" maxFractionDigits="2"/>
                                    </td>
                                    <td>${c.trainerName}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/course/edit?id=${c.courseId}"
                                           class="btn btn-warning btn-sm">Edit</a>
                                        <a href="${pageContext.request.contextPath}/course/delete?id=${c.courseId}"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Delete this course?');">Delete</a>
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
