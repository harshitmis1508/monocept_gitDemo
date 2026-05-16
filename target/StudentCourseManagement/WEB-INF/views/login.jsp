<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Student Course Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-wrapper">
    <div class="login-card">
        <h1>&#127979; SCM System</h1>
        <p class="subtitle">Student Course Management</p>

        <%-- Error message --%>
        <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="alert alert-danger">${errorMsg}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login-action" method="post">

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username"
                       value="${rememberedUsername}"
                       placeholder="Enter username" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password"
                       placeholder="Enter password" required>
            </div>

            <div class="remember-row">
                <input type="checkbox" id="rememberMe" name="rememberMe"
                    <% if (request.getAttribute("rememberedUsername") != null &&
                           !request.getAttribute("rememberedUsername").toString().isEmpty()) { %>
                        checked
                    <% } %> >
                <label for="rememberMe">Remember my username</label>
            </div>

            <button type="submit" class="btn btn-primary" style="width:100%;padding:11px;">
                Login
            </button>
        </form>

        <p style="text-align:center;margin-top:16px;color:#aaa;font-size:.82rem;">
            Default: admin / admin123
        </p>
    </div>
</div>
</body>
</html>
