<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Sign-up</title>
</head>
<body>
<div class="container">
    <h2 class="text-center pt-3 pb-2">Sing up form</h2>
    ${sessionScope.error}
    <div class="row justify-content-center">
        <div class="col-3">
            <form method="POST" class="was-validated" action="controller">
                <input type="hidden" name="action" value="sign up">
                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input class="form-control is-valid" type="text" name="login" id="login" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.login')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input class="form-control" type="email" name="email" id="email" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.email')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="password">Password</label>
                    <input class="form-control" type="password" name="password" id="password" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.password')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="confirm-password">Confirm password</label>
                    <input class="form-control" type="password" name="confirm-password" id="confirm-password" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.confirmPassword')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="firstname">First name</label>
                    <input class="form-control" type="text" name="firstname" id="firstname" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.fistName')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lastname">Last name</label>
                    <input class="form-control" type="text" name="lastname" id="lastname" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.lastName')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
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
