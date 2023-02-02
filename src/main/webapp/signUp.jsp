<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
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
        <div class="col-4">
            <form method="POST" action="controller">
                <input type="hidden" name="action" value="sign_up">
                <div class="mb-3">
                    <tag:inputField fieldName="login" entity="signUp.user" labelKey="common.login" width="25"
                                    type="text" placeholder="signUp.login.placeholder"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="email" entity="signUp.user" labelKey="common.email" width="25"
                                    type="text" placeholder="signUp.email.placeholder"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="password" entity="signUp.user" labelKey="common.password" width="25"
                                    type="password" placeholder="signUp.password.placeholder"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="confirmPassword" entity="signUp.user" labelKey="common.confirmPassword"
                                    width="auto" type="password" placeholder="signUp.confirmPassword.placeholder"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="firstName" entity="signUp.user" labelKey="common.firstName" width="25"
                                    type="text" placeholder="signUp.firstName.placeholder"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="lastName" entity="signUp.user" labelKey="common.lastName" width="25"
                                    type="text" placeholder="signUp.lastName.placeholder"/>
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
