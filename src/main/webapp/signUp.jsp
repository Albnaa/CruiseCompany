<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Sign-up</title>
</head>
<body>
<div class="container">
    <h2 class="text-center pt-3 pb-2">Sing up form</h2>
    <c:if test="${not empty sessionScope.error}">
        <div class="row justify-content-center">
        <div class="alert alert-danger alert-dismissible fade show col-3 text-center"
             role="alert">
            ${sessionScope.error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        </div>
    </c:if>
    <div class="row justify-content-center">
        <div class="col-3">
            <form method="POST" action="controller">
                <input type="hidden" name="action" value="sign up">
                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input class="form-control" type="text" name="login" id="login" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input class="form-control" type="email" name="email" id="email" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="password">Password</label>
                    <input class="form-control" type="password" name="password" id="password" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="confirm-password">Confirm password</label>
                    <input class="form-control" type="password" name="confirm-password" id="confirm-password">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="firstname">First name</label>
                    <input class="form-control" type="text" name="firstname" id="firstname" required>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lastname">Last name</label>
                    <input class="form-control" type="text" name="lastname" id="lastname" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary w-50 btn-lg">Sign up</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
