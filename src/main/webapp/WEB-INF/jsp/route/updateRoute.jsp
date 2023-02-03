<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="route.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>


<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="view_route"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
    <c:param name="routeId" value="${requestScope.route.id}"/>
</c:url>

<%--<c:set var="link"--%>
<%--       value="controller?action=view_route&sort=${sessionScope.sort}&order=${sessionScope.order}&routeId=${requestScope.route.id}"--%>
<%--       scope="request"/>--%>


<div class="container">
    <h2 class="text-center p-3"><fmt:message key="route.header"/></h2>
    <div class="row align-items-center border-bottom border-primary">
        <div class="col-6">
            <form method="post" action="controller">
                <button type="button" class="btn btn-primary w-100 mb-2"
                        data-bs-toggle="modal" data-bs-target="#createRouteModal"><fmt:message
                        key="table.button.create"/>
                </button>
                <div class="btn-group w-100">
                    <button type="submit" name="action" value="update_route" class="btn btn-warning w-50 mb-2">
                        <fmt:message key="table.button.update"/>
                    </button>
                    <button type="submit" name="action" value="delete_route" class="btn btn-danger w-50 mb-2">
                        <fmt:message key="table.button.delete"/>
                    </button>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="id" entity="route" labelKey="table.id" width="25" type="number"
                                    value="${requestScope.route.id}" readonly="readonly"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="name" entity="route" labelKey="table.name" width="25" type="text"
                                    value="${requestScope.route.name}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="startOfCruise" entity="route" labelKey="table.startOfCruise" width="25"
                                    type="date" value="${requestScope.route.startOfCruise}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="endOfCruise" entity="route" labelKey="table.endOfCruise" width="25"
                                    type="date" value="${requestScope.route.endOfCruise}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="price" entity="route" labelKey="table.price" width="25" type="number"
                                    value="${requestScope.route.price}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="duration" entity="route" labelKey="table.duration" width="25" type="number"
                                    value="${requestScope.route.duration}" readonly="readonly"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="numOfPorts" entity="route" labelKey="table.numberOfPorts" width="25"
                                    type="text" value="${requestScope.route.numOfPorts}" readonly="readonly"/>
                </div>
            </form>
        </div>
        <div class="col-4">
            <form method="post" action="controller">
                <input type="hidden" name="action" value="add_waypoint">
                <input type="hidden" name="routeId" value="${requestScope.route.id}">
                <c:choose>
                <c:when test="${not empty requestScope.ports}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-2 text-center"><fmt:message key="table.id"/></th>
                        <th class="col-8 text-center"><fmt:message key="table.name"/></th>
                        <th class="col-2 text-center"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="port" items="${requestScope.ports}">
                        <tr class="${port}">
                            <td class="col-2 text-center">${port.id}</td>
                            <td class="col-8 text-center">${port.name}</td>
                            <td class="col-2 text-center">
                                <button type="submit" name="portId" value="${port.id}"
                                        class="btn btn-primary px-1 py-0" style="width: auto"><fmt:message
                                        key="table.button.add"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>
                </c:when>
                <c:otherwise>
                <div class="alert alert-primary text-center">
                    <fmt:message key="route.table.error"/>
                </div>
                </c:otherwise>
                </c:choose>

        </div>
        <div class="col-2 justify-content-center">
            <label for="arriveDate"><fmt:message key="table.arrivalTime"/></label>
            <input id="arriveDate" type="date" class="form-control" name="arriveDate" required>
            <label for="departureDate"><fmt:message key="table.departureTime"/></label>
            <input id="departureDate" type="date" class="form-control mb-2" name="departureDate" required>
            </form>
            <br>
            <form method="get" action="controller">
                <input type="hidden" name="action" value="view_route">
                <input type="hidden" name="routeId" value="${requestScope.route.id}">
                <input type="text" class="form-control mb-2" placeholder="<fmt:message key="route.port.placeholder"/>">
                <select class="form-select mb-2" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>
                        <fmt:message key="sort.default"/>\
                    </option>
                    <option value="port.id" ${sessionScope.sort == 'port.id' ? 'selected' : ''}>
                        <fmt:message key="table.id"/>
                    </option>
                    <option value="port.name" ${sessionScope.sort == 'port.name' ? 'selected' : ''}>
                        <fmt:message key="table.name"/>
                    </option>
                </select>
                <select class="form-select mb-2" name="order">
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
                <div class="input-group mb-2">
                    <span class="input-group-text"><fmt:message key="sort.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
                <button type="submit" class="btn btn-primary w-100"><fmt:message key="table.button.submit"/></button>
            </form>
        </div>
    </div>
    <form method="post" action="controller">
        <input type="hidden" name="action" value="delete_waypoint">
        <input type="hidden" name="routeId" value="${requestScope.route.id}">
        <c:choose>
            <c:when test="${not empty requestScope.route.waypoints}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-1"><fmt:message key="table.id"/></th>
                        <th class="col-4"><fmt:message key="table.name"/></th>
                        <th class="col-3"><fmt:message key="table.arrivalTime"/></th>
                        <th class="col-3"><fmt:message key="table.departureTime"/></th>
                        <th class="col-1"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="waypoint" items="${requestScope.route.waypoints}">
                        <tr class="${waypoint}">
                            <td class="col-1">${waypoint.port.id}</td>
                            <td class="col-4">${waypoint.port.name}</td>
                            <td class="col-3"><fd:formatDate date="${waypoint.arriveTime}"/></td>
                            <td class="col-3"><fd:formatDate date="${waypoint.departureTime}"/></td>
                            <td class="col-1">
                                <button type="submit" name="waypointPortId" value="${waypoint.port.id}"
                                        class="btn btn-danger px-1 py-0" style="width: auto"><fmt:message
                                        key="table.button.delete"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary text-center my-3">
                    <fmt:message key="route.table.waypoint.error"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<jsp:include page="/WEB-INF/fragments/createRouteModal.jsp"/>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
