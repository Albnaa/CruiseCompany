<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="users.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="manage_users"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
    <c:param name="roleF" value="${sessionScope.roleF}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="users.header"/></h2>
    <form method="get" action="controller" role="form">
        <input type="hidden" name="action" value="manage_users">
        <div class="row">
            <div class="col">
                <select id="select-sort" name="sort" class="form-select">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>
                        <fmt:message key="common.sort.default"/>
                    </option>
                    <option value="user.id" ${sessionScope.sort == 'user.id' ? 'selected' : ''}>
                        <fmt:message key="common.id"/>
                    </option>
                    <option value="user.login" ${sessionScope.sort == 'user.login' ? 'selected' : ''}>
                        <fmt:message key="common.login"/>
                    </option>
                    <option value="user.email" ${sessionScope.sort == 'user.email' ? 'selected' : ''}>
                        <fmt:message key="common.email"/>
                    </option>
                    <option value="user.first_name" ${sessionScope.sort == 'user.first_name' ? 'selected' : ''}>
                        <fmt:message key="common.firstName"/>
                    </option>
                    <option value="user.last_name" ${sessionScope.sort == 'user.last_name' ? 'selected' : ''}>
                        <fmt:message key="common.lastName"/>
                    </option>
                    <option value="user.role_id" ${sessionScope.sort == 'user.role_id' ? 'selected' : ''}>
                        <fmt:message key="common.role"/>
                    </option>
                    <option value="user.balance" ${sessionScope.sort == 'user.balance' ? 'selected' : ''}>
                        <fmt:message key="common.balance"/>
                    </option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}>
                        <fmt:message key="common.order.default"/>
                    </option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>
                        <fmt:message key="common.order.asc"/>
                    </option>
                    <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>
                        <fmt:message key="common.order.desc"/>
                    </option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="roleF">
                    <option ${empty sessionScope.roleF ? 'selected' : ''}>
                        <fmt:message key="common.filter.role"/>
                    </option>
                    <option value="ADMIN" ${sessionScope.roleF == 'ADMIN'? 'selected' : ''}>
                        ADMIN
                    </option>
                    <option value="USER" ${sessionScope.roleF == 'USER' ? 'selected' : ''}>
                        USER
                    </option>
                </select>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text w-50"><fmt:message key="common.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1" value="${requestScope.rows}">
                </div>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100" onclick="location.href = 'controller?action=manage_users';">
                    <fmt:message key="common.button.reset"/>
                </button>
                <button type="submit" class="btn btn-primary w-100"><fmt:message key="common.button.submit"/></button>
            </div>
        </div>
    </form>

    <form method="get" action="controller">
        <input type="hidden" name="action" value="view_user">
        <c:choose>
            <c:when test="${not empty requestScope.users}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-1 text-center"><fmt:message key="common.id"/></th>
                        <th class="col-2"><fmt:message key="common.login"/></th>
                        <th class="col-2"><fmt:message key="common.email"/></th>
                        <th class="col-2"><fmt:message key="common.firstName"/></th>
                        <th class="col-2"><fmt:message key="common.lastName"/></th>
                        <th class="col-1"><fmt:message key="common.role"/></th>
                        <th class="col-1"><fmt:message key="common.balance"/></th>
                        <th class="col-1"></th>
                    </tr>
                    </thead>
                    <c:forEach var="user" items="${requestScope.users}">
                        <tr class="${user}">
                            <td class="text-center">${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role}</td>
                            <td>${user.balance}</td>
                            <td>
                                <button class="btn btn-primary px-2 py-0" type="submit" name="userId" value="${user.id}"
                                        style="width: auto">
                                    <fmt:message key="common.button.profile"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    <fmt:message key="users.table.error"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
