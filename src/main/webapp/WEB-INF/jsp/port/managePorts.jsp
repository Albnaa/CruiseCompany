<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="ports.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="manage_port"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="ports.header"/></h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_port">
        <div class="row justify-content-center">
            <div class="col">
                <select class="form-select" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}><fmt:message
                            key="sort.default"/></option>
                    <option value="port.id" ${sessionScope.sort == 'port.id' ? 'selected' : ''}><fmt:message
                            key="table.id"/></option>
                    <option value="port.name" ${sessionScope.sort == 'port.name' ? 'selected' : ''}><fmt:message
                            key="table.name"/></option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}><fmt:message
                            key="order.default"/></option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}><fmt:message
                            key="order.asc"/></option>
                    <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}><fmt:message
                            key="order.desc"/></option>
                </select>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text"><fmt:message key="sort.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_port';"><fmt:message
                        key="table.button.reset"/>
                </button>
                <button type="submit" class="btn btn-primary w-100"><fmt:message key="table.button.submit"/></button>
            </div>
        </div>
    </form>

    <div class="row align-items-center">
        <div class="col-3">
            <h4 class="text-center"><fmt:message key="ports.create.header"/></h4>
            <form method="post" action="controller">
                <input type="hidden" name="action" value="create_port">
                <div class="input-group mb-2">
                    <span class="input-group-text w-25"><fmt:message key="ports.create.label"/></span>
                    <input type="text"
                           class="form-control ${fn:contains(sessionScope.error, 'ports.name') ? 'is-invalid' : ''}"
                           name="name" placeholder="<fmt:message key="ports.create.placeholder"/>">
                    <c:if test="${fn:contains(sessionScope.error, 'ports.name')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary w-100 mb-2"><fmt:message
                        key="ports.create.button"/></button>
                <div class="" style="height: 37.6px">
                </div>
            </form>
        </div>
        <div class="col-6">
            <form method="get" action="controller">
                <c:choose>
                    <c:when test="${not empty requestScope.ports}">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th class="text-center"><fmt:message key="table.id"/></th>
                                <th class="text-center"><fmt:message key="table.name"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="port" items="${requestScope.ports}">
                                <tr class="${port}">
                                    <td class="text-center">${port.id}</td>
                                    <td class="text-center">${port.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-primary">
                            <fmt:message key="ports.table.error"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>

            <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>

        </div>
        <div class="col-3">
            <h4 class="text-center"><fmt:message key="ports.update.header"/></h4>
            <form method="post" action="controller">
                <div class="input-group mb-2">
                    <span class="input-group-text w-25"><fmt:message key="ports.update.label.id"/></span>
                    <input type="number" class="form-control" name="id" min="1" placeholder="<fmt:message
            key="ports.update.id.placeholder"/>" required>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="updateName" entity="ports" labelKey="ports.update.label.name" width="25"
                                    type="text" placeholder="ports.update.name.placeholder"/>
                </div>

                <button type="submit" name="action" value="delete_port" class="btn btn-danger w-100 mb-2"><fmt:message
                        key="ports.update.button.delete"/></button>
                <button type="submit" name="action" value="update_port" class="btn btn-warning w-100 mb-2"><fmt:message
                        key="table.button.update"/></button>
                <div class="" style="height: 37.6px">
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
