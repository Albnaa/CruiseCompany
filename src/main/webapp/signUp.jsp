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
                    <tag:inputField fieldName="login" entity="user" labelKey="Login" width="25"
                                    type="text" required="required"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="email" entity="user" labelKey="Email" width="25"
                                    type="text" required="required"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="password" entity="user" labelKey="Password" width="50"
                                    type="password" required="required"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="confirmPassword" entity="user" labelKey="Confirm password" width="50"
                                    type="password" required="required"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="firstName" entity="user" labelKey="First name" width="auto"
                                    type="text" required="required"/>
                </div>
                <div class="mb-3">
                    <tag:inputField fieldName="lastName" entity="user" labelKey="Last name" width="auto"
                                    type="text" required="required"/>
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
