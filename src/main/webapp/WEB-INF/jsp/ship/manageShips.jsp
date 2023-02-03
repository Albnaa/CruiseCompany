<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="ships.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="manage_ship"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="ships.header"/></h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_ship">
        <div class="row justify-content-center">
            <div class="col">
            <select class="form-select" name="sort">
                <option ${empty sessionScope.sort ? 'selected' : ''}>
                    <fmt:message key="sort.default"/>
                </option>
                <option value="ship.id" ${sessionScope.sort == 'ship.id' ? 'selected' : ''}>
                    <fmt:message key="table.id"/>
                </option>
                <option value="ship.name" ${sessionScope.sort == 'ship.name' ? 'selected' : ''}>
                    <fmt:message key="table.name"/>
                </option>
                <option value="ship.capacity" ${sessionScope.sort == 'ship.capacity' ? 'selected' : ''}>
                    <fmt:message key="table.capacity"/>
                </option>
                <option value="ship.visited_ports" ${sessionScope.sort == 'ship.visited_ports' ? 'selected' : ''}>
                    <fmt:message key="table.visitedPorts"/>
                </option>
                <option value="ship.staff" ${sessionScope.sort == 'ship.staff' ? 'selected' : ''}>
                    <fmt:message key="table.staff"/>
                </option>
                <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>
                    <fmt:message key="table.routeName"/>
                </option>
            </select>
        </div>
        <div class="col">
            <select class="form-select" name="order">
                <option value="" ${empty sessionScope.order ? 'selected' : ''}>
                    <fmt:message key="order.default"/>
                </option>
                <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>
                    <fmt:message key="order.asc"/>
                </option>
                <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>
                    <fmt:message key="order.desc"/>
                </option>
            </select>
        </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text"><fmt:message key="sort.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1" value="${requestScope.rows}">
                </div>
            </div>
            <div class="col">
                <button type="button" class="btn btn-primary w-100" data-bs-toggle="modal" data-bs-target="#createShipModal">
                    <fmt:message key="table.button.create"/>
                </button>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_ship';">
                    <fmt:message key="table.button.reset"/>
                </button>
                <button type="submit" class="btn btn-primary w-100">
                    <fmt:message key="table.button.submit"/>
                </button>
            </div>
        </div>
    </form>
    <form method="post" action="controller">
        <c:choose>
            <c:when test="${not empty requestScope.ships}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="table.id"/></th>
                        <th><fmt:message key="table.name"/></th>
                        <th><fmt:message key="table.capacity"/></th>
                        <th><fmt:message key="table.visitedPorts"/></th>
                        <th><fmt:message key="table.staff"/></th>
                        <th><fmt:message key="table.routeName"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ship" items="${requestScope.ships}">
                        <tr class="${ship}">
                            <td>${ship.id}</td>
                            <td>${ship.name}</td>
                            <td>${ship.capacity}</td>
                            <td>${ship.visitedPorts}</td>
                            <td>${ship.staff}</td>
                            <td>${ship.route.name}</td>
                            <td>
                                <a class="btn btn-primary py-0 px-2" style="width: auto"
                                   href="controller?action=view_ship&id=${ship.id}">
                                    <fmt:message key="table.button.more"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary">
                    <fmt:message key="ships.table.error"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<jsp:include page="/WEB-INF/fragments/createShipModal.jsp"/>

</body>
</html>
