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
    <style>
        .form-control {
            height: 41.6px;
        }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<div class="container">
    <div class="card text-center my-3">
        <div class="card-header">
            <h4><fmt:message key="profile.header"/></h4>
        </div>
        <div class="card-body">
            <h4 class="card-title">${requestScope.user.login}</h4>
        </div>
    </div>
    <div class="row">
        <div class="col col-6">
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.id"/></li>
                <li class="list-group-item w-50">${requestScope.user.id}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.login"/></li>
                <li class="list-group-item w-50">${requestScope.user.login}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.email"/></li>
                <li class="list-group-item w-50">${requestScope.user.email}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.firstName"/></li>
                <li class="list-group-item w-50">${requestScope.user.firstName}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.lastName"/></li>
                <li class="list-group-item w-50">${requestScope.user.lastName}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.role"/></li>
                <li class="list-group-item w-50">${requestScope.user.role}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50"><fmt:message key="table.balance"/></li>
                <li class="list-group-item w-50">${requestScope.user.balance}</li>
            </ul>
            <div class="list-group list-group-horizontal">
                <button class="btn list-group-item list-group-item-action list-group-item-warning"
                        type="button" data-bs-toggle="collapse" data-bs-target="#updateForm">
                    <fmt:message key="table.button.update"/>
                </button>
                <a href="controller?action=delete_user&userId=${requestScope.user.id}"
                   class="btn list-group-item list-group-item-action list-group-item-danger">
                    <fmt:message key="table.button.delete"/>
                </a>
            </div>
        </div>
        <div class="col col-6 collapse collapse" id="updateForm">
            <form method="POST" action="controller">
                <input type="hidden" name="action" value="update_user">
                <tag:inputField fieldName="id" entity="update.user" labelKey="table.id" width="25" type="text"
                                value="${requestScope.user.id}" readonly="readonly"/>
                <tag:inputField fieldName="login" entity="update.user" labelKey="table.login" width="25" type="text"
                                value="${requestScope.user.login}"/>
                <tag:inputField fieldName="email" entity="update.user" labelKey="table.email" width="25" type="email"
                                value="${requestScope.user.email}"/>
                <tag:inputField fieldName="firstName" entity="update.user" labelKey="table.firstName" width="25"
                                type="text" value="${requestScope.user.firstName}"/>
                <tag:inputField fieldName="lastName" entity="update.user" labelKey="table.lastName" width="25"
                                type="text" value="${requestScope.user.lastName}" />
                <div class="input-group">
                    <span class="input-group-text w-25"><fmt:message key="table.role"/></span>
                    <select class="form-select" name="role" style="height: 41.6px">
                        <option value="ADMIN" ${requestScope.user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                        <option value="USER" ${requestScope.user.role == 'USER' ? 'selected' : ''}>USER</option>
                    </select>
                </div>
                <tag:inputField fieldName="balance" entity="login" labelKey="table.balance" width="25" type="number"
                                value="${requestScope.user.balance}" readonly="readonly"/>
                <button type="submit" class="btn btn-primary w-100" style="height: 41.6px">
                    <fmt:message key="table.button.submit"/>
                </button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
