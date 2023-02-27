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

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<div class="container">
    ${sessionScope.error}
    <h2 class="text-center p-3"><fmt:message key="profile.header"/>#${requestScope.user.id}</h2>
    <div class="row justify-content-center">
        <div class="col-6">
            <form method="post" action="controller">
                <input type="hidden" name="userId" value="${requestScope.user.id}">
                <div class="mb-2">
                    <tag:inputField fieldName="id" entity="update.user" labelKey="common.id" width="25" type="text"
                                    value="${requestScope.user.id}" readonly="readonly"/>
                </div>
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
                    <div class="input-group">
                        <span class="input-group-text w-25"><fmt:message key="common.role"/></span>
                        <select class="form-select" name="role">
                            <option value="ADMIN" ${requestScope.user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                            <option value="USER" ${requestScope.user.role == 'USER' ? 'selected' : ''}>USER</option>
                        </select>
                    </div>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="balance" entity="update.user" labelKey="common.balance" width="25" type="number"
                                    value="${requestScope.user.balance}" readonly="readonly"/>
                </div>
                <div class="row">
                    <div class="col-6">
                        <button type="submit" name="action" value="update_user" class="btn btn-warning w-100">
                            <fmt:message key="common.button.update"/>
                        </button>
                    </div>
                    <div class="col-6">
                        <button type="submit" name="action" value="delete_user" class="btn btn-danger w-100">
                            <fmt:message key="common.button.delete"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
