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
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>


<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="view_route"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
    <c:param name="id" value="${requestScope.route.id}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="route.header"/></h2>
    <div class="row align-items-center border-bottom border-primary">
        <div class="col-6">
            <form method="post" action="controller">
                <button type="button" class="btn btn-primary w-100 mb-2"
                        data-bs-toggle="modal" data-bs-target="#createRouteModal">
                    <fmt:message key="common.button.create"/>
                </button>
                <div class="btn-group w-100">
                    <button type="submit" name="action" value="update_route" class="btn btn-warning w-50 mb-2">
                        <fmt:message key="common.button.update"/>
                    </button>
                    <button type="submit" name="action" value="delete_route" class="btn btn-danger w-50 mb-2">
                        <fmt:message key="common.button.delete"/>
                    </button>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="id" entity="update.route" labelKey="common.id" width="25" type="number"
                                    value="${requestScope.route.id}" readonly="readonly"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="name" entity="update.route" labelKey="common.name" width="25" type="text"
                                    value="${requestScope.route.name}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="startDate" entity="update.route" labelKey="common.startOfCruise" width="25"
                                    type="date" value="${requestScope.route.startOfCruise}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="endDate" entity="update.route" labelKey="common.endOfCruise" width="25"
                                    type="date" value="${requestScope.route.endOfCruise}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="price" entity="update.route" labelKey="common.price" width="25"
                                    type="number" value="${requestScope.route.price}" placeholder=""/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="duration" entity="update.route" labelKey="common.duration" width="25"
                                    type="number" value="${requestScope.route.duration}" readonly="readonly"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="numOfPorts" entity="update.route" labelKey="common.numberOfPorts" width="25"
                                    type="text" value="${requestScope.route.numOfPorts}" readonly="readonly"/>
                </div>
            </form>
        </div>
        <div class="col-4">
            <form method="post" action="controller">
                <input type="hidden" name="action" value="add_waypoint">
                <input type="hidden" name="id" value="${requestScope.route.id}">
                <c:choose>
                <c:when test="${not empty requestScope.ports}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-2 text-center"><fmt:message key="common.id"/></th>
                        <th class="col-8 text-center"><fmt:message key="common.name"/></th>
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
                                        key="common.button.add"/>
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
            <div class="">
                <fmt:message key="common.arrivalTime"/>
                <tag:inputField fieldName="startDate" entity="add.waypoint" labelKey="common.empty" width="auto"
                                type="date"/>
            </div>
            <div class="">
                <fmt:message key="common.departureTime"/>
                <tag:inputField fieldName="endDate" entity="add.waypoint" labelKey="common.empty" width="auto"
                                type="date"/>
            </div>

            </form>
            <br>
            <form method="get" action="controller">
                <input type="hidden" name="action" value="view_route">
                <input type="hidden" name="id" value="${requestScope.route.id}">
                <input type="text" class="form-control mb-2" placeholder="<fmt:message key="route.port.placeholder"/>">
                <select class="form-select mb-2" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>
                        <fmt:message key="common.sort.default"/>
                    </option>
                    <option value="port.id" ${sessionScope.sort == 'port.id' ? 'selected' : ''}>
                        <fmt:message key="common.id"/>
                    </option>
                    <option value="port.name" ${sessionScope.sort == 'port.name' ? 'selected' : ''}>
                        <fmt:message key="common.name"/>
                    </option>
                </select>
                <select class="form-select mb-2" name="order">
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
                <div class="input-group mb-2">
                    <span class="input-group-text"><fmt:message key="common.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
                <button type="submit" class="btn btn-primary w-100"><fmt:message key="common.button.submit"/></button>
            </form>
        </div>
    </div>
    <form method="post" action="controller">
        <input type="hidden" name="action" value="delete_waypoint">
        <input type="hidden" name="id" value="${requestScope.route.id}">
        <c:choose>
            <c:when test="${not empty requestScope.route.waypoints}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="col-1"><fmt:message key="common.id"/></th>
                        <th class="col-4"><fmt:message key="common.name"/></th>
                        <th class="col-3"><fmt:message key="common.arrivalTime"/></th>
                        <th class="col-3"><fmt:message key="common.departureTime"/></th>
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
                                        key="common.button.delete"/>
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
