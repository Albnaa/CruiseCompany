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
    <h2 class="text-center pt-3 pb-2">Sign up form</h2>
    <div class="row justify-content-center">
        <div class="col-3">
            <form method="POST" action="controller">
                <input type="hidden" name="action" value="sign_up">
                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input class="form-control ${fn:contains(sessionScope.error, 'signUp.login') ? 'is-invalid' : ''}"
                           type="text" name="login" id="login" value="${sessionScope.user.login}" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.login' && fn:endsWith(sessionScope.error, 'login'))}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.loginExists')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input class="form-control ${fn:contains(sessionScope.error, 'signUp.email') ? 'is-invalid' : ''}"
                           type="email" name="email" id="email" value="${sessionScope.user.email}" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.email' && fn:endsWith(sessionScope.error, 'email'))}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.emailExists')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="password">Password</label>
                    <input class="form-control ${fn:contains(sessionScope.error, 'signUp.password') ? 'is-invalid' : ''}"
                           type="password" name="password" id="password" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.password')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="confirm-password">Confirm password</label>
                    <input class="form-control ${fn:contains(sessionScope.error, 'signUp.confirmPassword') ? 'is-invalid' : ''}"
                           type="password" name="confirm-password" id="confirm-password" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.confirmPassword')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="firstname">First name</label>
                    <input class="form-control ${fn:contains(sessionScope.error, 'signUp.firstName') ? 'is-invalid' : ''}"
                           type="text" name="firstname" id="firstname" value="${sessionScope.user.firstName}" required>
                    <c:if test="${fn:contains(sessionScope.error, 'signUp.firstName')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="lastname">Last name</label>
                    <input class="form-control ${fn:contains(sessionScope.error, 'signUp.lastName') ? 'is-invalid' : ''}"
                           type="text" name="lastname" id="lastname" value="${sessionScope.user.lastName}" required>
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
