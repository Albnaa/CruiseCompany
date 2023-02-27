<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="catalog.title"/></title>
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

<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="manage_catalog"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
    <c:param name="nameF" value="${sessionScope.nameF}"/>
    <c:param name="durationF" value="${sessionScope.durationF}"/>
    <c:param name="startDateF" value="${sessionScope.startDateF}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="catalog.header"/></h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_catalog">
        <div class="row mb-2">
            <div class="col-6">
                <div class="input-group mb-2">
                    <span class="input-group-text"><fmt:message key="common.filter.cruiseName"/></span>
                    <input type="text" name="nameF" class="form-control"
                           value="${sessionScope.nameF}">
                </div>
            </div>
            <div class="col-3">
                <div class="input-group mb-2">
                    <span class="input-group-text w-50"><fmt:message key="common.filter.startDate.short"/></span>
                    <input type="date" name="startDateF" class="form-control"
                           value="${sessionScope.startDateF}">
                </div>
            </div>
            <div class="col-3">
                <div class="input-group mb-2">
                    <span class="input-group-text w-auto"><fmt:message key="common.filter.duration"/></span>
                    <input type="number" name="durationF" class="form-control" min="1" max="31"
                           placeholder="<fmt:message key="common.days"/>" value="${sessionScope.durationF}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <select class="form-select" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>
                        <fmt:message key="common.sort.default"/></option>
                    <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>
                        <fmt:message key="common.cruiseName"/>
                    </option>
                    <option value="ship.name" ${sessionScope.sort == 'ship.name' ? 'selected' : ''}>
                        <fmt:message key="common.shipName"/>
                    </option>
                    <option value="route.start_of_cruise" ${sessionScope.sort == 'route.start_of_cruise' ? 'selected' : ''}>
                        <fmt:message key="common.startOfCruise"/>
                    </option>
                    <option value="route.end_of_cruise" ${sessionScope.sort == 'route.end_of_cruise' ? 'selected' : ''}>
                        <fmt:message key="common.endOfCruise"/>
                    </option>
                    <option value="route.duration" ${sessionScope.sort == 'route.duration' ? 'selected' : ''}>
                        <fmt:message key="common.duration"/>
                    </option>
                    <option value="route.price" ${sessionScope.sort == 'route.price' ? 'selected' : ''}>
                        <fmt:message key="common.price"/>
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
                    <option value="desc"${sessionScope.order == 'desc' ? 'selected' : ''}>
                        <fmt:message key="common.order.desc"/>
                    </option>
                </select>
            </div>
            <div class="col">
                <div class="input-group mb-2">
                    <span class="input-group-text w-50"><fmt:message key="common.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col">
                <div class="btn-group w-100">
                    <button type="button" class="btn btn-secondary w-50"
                            onclick="location.href = 'controller?action=manage_catalog&rows=6';">
                        <fmt:message key="common.button.reset"/>
                    </button>
                    <button type="submit" class="btn btn-primary w-50"><fmt:message key="common.button.submit"/></button>
                </div>
            </div>
        </div>
    </form>

    <div class="row row-cols-1 row-cols-md-3 g-3 justify-content-center">
        <c:choose>
        <c:when test="${not empty requestScope.cruises}">
        <c:forEach var="cruise" items="${requestScope.cruises}">
            <div class="col">
                <div class="card border-primary h-100">
                    <img src="controller?action=file&path=${cruise.imagePath}" class="card-img-top"
                         alt="Cruise ship photo">
                    <div class="card-body">
                        <h5 class="card-title text-center">${cruise.route.name}</h5>
                        <p class="card-text">
                            <c:forEach var="waypoint" items="${cruise.route.waypoints}"><c:out
                                    value="${waypoint.port.name}"/><c:if
                                    test="${!waypoint.equals(cruise.route.waypoints[cruise.route.waypoints.size() - 1])}"><c:out
                                    value=", "/></c:if></c:forEach>
                        </p>
                        <ul class="list-group list-group-flush align-content-end">
                            <li class="list-group-item"><fmt:message key="common.startOfCruise"/>:
                                <fd:formatDate date="${cruise.route.startOfCruise}"/></li>
                            <li class="list-group-item"><fmt:message key="common.endOfCruise"/>:
                                <fd:formatDate date="${cruise.route.endOfCruise}"/></li>
                            <li class="list-group-item"><fmt:message key="common.duration"/>:
                                ${cruise.route.duration} <fmt:message key="common.days"/></li>
                            <li class="list-group-item"><fmt:message key="common.shipName"/>: ${cruise.name}</li>
                            <li class="list-group-item"><fmt:message key="common.price"/>: ${cruise.route.price}</li>
                        </ul>
                    </div>
                    <div class="card-footer">
                        <div class="text-center">
                            <button class="btn btn-outline-primary w-75"
                                    onclick="location.href = 'controller?action=view_cruise&shipId=${cruise.id}';">
                                <fmt:message key="common.button.view"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="my-3">
        <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>
    </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-primary w-100 text-center">
            No cruises found with your search criteria
        </div>
    </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
