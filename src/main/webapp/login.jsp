<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Login page</title>
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            /*padding-top: 40px;*/
            padding-bottom: 160px;
            background-color: #f5f5f5;
        }

        .form-signIn {
            max-width: 330px;
            padding: 10px;
        }
    </style>
</head>
<body>
<main class="form-signIn w-100 m-auto">
    <h2 class="mb-3 text-center">Please sign in</h2>
    <form method="POST" action="controller">
        <input type="hidden" name="action" value="login">
        <div class="m-0 form-floating">
            <input type="text" class="form-control" id="login" name="login" required>
            <label for="login">Login</label><br>
        </div>
        <div class="m-0 form-floating">
            <input type="password" class="form-control" name="password" id="password" required>
            <label for="password">Password</label>
        </div>
        <div class="mt-3 text-center">
            <button class="w-50 btn btn-lg btn-primary" type="submit">Sign in</button>
        </div>
    </form>
    <c:if test="${not empty sessionScope.error}">
        <div class="mt-3 text-center alert alert-info">
            ${sessionScope.error}
        </div>
    </c:if>
    <div class="mt-3 text-center">
        <h5>Don`t have an account? <a href="signUp.jsp">Sign up</a></h5>
    </div>
</main>
</body>
</html>