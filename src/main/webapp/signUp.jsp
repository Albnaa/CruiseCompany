<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <title>Sigh-up</title>
</head>
<body>
  <form method="POST" action="controller">
    <input type="hidden" name="action" value="sign up">
<%--    Login input  --%>
    <label for="login">login</label><br>
    <input type="text" name="login" id="login" required><br>
<%--    Email input--%>
    <label for="email">email</label><br>
    <input type="email" name="email" id="email" required><br>
<%--    password input--%>
    <label for="password">password</label><br>
    <input type="password" name="password" id="password" required><br>
<%--    password repeat--%>
    <label for="confirm-password">confirm password</label><br>
    <input type="password" name="confirm-password" id="confirm-password"><br>
<%--    firstname input--%>
    <label for="firstname">firstname</label><br>
    <input type="text" name="firstname" id="firstname" required><br>
<%--    lastname input--%>
    <label for="lastname">lastname</label><br>
    <input type="text" name="lastname" id="lastname" required><br>
    <input type="submit" value="Sign Up">
  </form>
</body>
</html>
