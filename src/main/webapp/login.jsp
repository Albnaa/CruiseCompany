<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<form method="POST" class="text-end m-2" action="controller?action=set_locale">
    <label>
        <select class="p-1" name="locale" onchange='submit();'>
            <option value="en" ${sessionScope.locale == 'en' ? 'selected' : ''}>EN</option>
            <option value="ua" ${sessionScope.locale == 'ua' ? 'selected' : ''}>UA</option>
        </select>
    </label>
</form>
<main class="row justify-content-center" >
    <h2 class="m-4 pt-3 text-center"><fmt:message key="login.greetings"/></h2>
    <form class="col" style="max-width: 330px; padding: 10px;" method="POST" action="controller">
        <input type="hidden" name="action" value="sign_in">
        <div class="m-0 form-floating">
            <input type="text" class="form-control" id="login" name="login" required>
            <label for="login"><fmt:message key="login.label.login"/></label><br>
        </div>
        <div class="m-0 form-floating">
            <input type="password" class="form-control" name="password" id="password" required>
            <label for="password"><fmt:message key="login.label.password"/></label>
        </div>
        <div class="mt-3 text-center">
            <button class="w-50 btn btn-lg btn-primary" type="submit"><fmt:message key="login.button.login"/></button>
        </div>
    </form>
    <c:if test="${not empty sessionScope.error}">
        <div class="mt-3 text-center alert alert-info">
            ${sessionScope.error}
        </div>
    </c:if>
    <div class="mt-3 text-center">
        <h5><fmt:message key="login.message.signup"/> <a href="signUp.jsp"><fmt:message key="login.button.signup"/></a></h5>
    </div>
</main>
</body>
</html>