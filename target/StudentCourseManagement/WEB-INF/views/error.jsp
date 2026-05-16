<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container" style="margin-top:60px;text-align:center;">
    <div class="card" style="max-width:500px;margin:auto;">
        <h2 style="color:#d32f2f;">&#9888; An Error Occurred</h2>
        <p style="margin:16px 0;color:#666;">
            <% if (request.getAttribute("errorMsg") != null) { %>
                ${errorMsg}
            <% } else { %>
                Something went wrong. Please try again or contact the administrator.
            <% } %>
        </p>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">Go to Dashboard</a>
    </div>
</div>
</body>
</html>
