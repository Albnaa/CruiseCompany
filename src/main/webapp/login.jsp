<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Login page</title>
</head>
<body>
<div class="container col-md-3">
<form method="POST" action="controller">
    <input type="hidden" name="action" value="login">
    <label for="login">Login</label><br>
    <input type="text" id="login" name="login" required><br>
    <label for="password">Password</label><br>
    <input type="password" name="password" id="password" required><br>
    <input type="submit" value="Log in">
</form>
Don`t have an account?
<a href="signUp.jsp">Sign up</a>
</div>
</body>
</html>