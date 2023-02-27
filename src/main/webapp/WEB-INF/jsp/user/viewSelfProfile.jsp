<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="profile.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="/WEB-INF/fragments/userNavbar.jsp"/>
</c:if>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="profile.header"/></h2>
    <div class="row justify-content-center">
        <div class="col-6">
            <form method="post" action="controller">
                <input type="hidden" name="userId" value="${requestScope.user.id}">
                <div class="mb-2">
                    <tag:inputField fieldName="login" entity="update.user" labelKey="common.login" width="25" type="text"
                                    value="${requestScope.user.login}" placeholder="profile.update.login.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="email" entity="update.user" labelKey="common.email" width="25" type="email"
                                    value="${requestScope.user.email}" placeholder="profile.update.email.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="firstName" entity="update.user" labelKey="common.firstName" width="25"
                                    type="text" value="${requestScope.user.firstName}"
                                    placeholder="profile.update.firstName.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="lastName" entity="update.user" labelKey="common.lastName" width="25"
                                    type="text" value="${requestScope.user.lastName}"
                                    placeholder="profile.update.lastName.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="role" entity="update.user" labelKey="common.role" width="25" type="text"
                                    value="${requestScope.user.role}" readonly="readonly"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="balance" entity="update.user" labelKey="common.balance" width="25" type="number"
                                    value="${requestScope.user.balance}" readonly="readonly"/>
                </div>
                <button type="submit" name="action" value="update_self_profile" class="btn btn-warning w-100">
                    <fmt:message key="common.button.update"/>
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
