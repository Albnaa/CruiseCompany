<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Catalog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="../../../templates/adminNavbar.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="../../../templates/userNavbar.jsp"/>
</c:if>

<c:set var="link"
       value="controller?action=manage_catalog&sort=${sessionScope.sort}&order=${sessionScope.order}&nameF=${requestScope.nameF}
&durationF=${sessionScope.durationF}&startDateF=${sessionScope.startDateF}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3">Catalog</h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_catalog">
        <div class="row">
            <div class="col-6">
                <div class="input-group mb-2">
                    <span class="input-group-text">Search by cruise name:</span>
                    <input type="text" name="nameF" class="form-control"
                           value="${sessionScope.nameF}">
                </div>
            </div>
            <div class="col">
                <div class="input-group mb-2">
                    <span class="input-group-text w-50">Filter by start date:</span>
                    <input type="date" name="startDateF" class="form-control"
                           value="">
                </div>
            </div>
            <div class="col">
                <div class="input-group mb-2">
                    <span class="input-group-text w-50">Filter by duration:</span>
                    <input type="number" name="durationF" class="form-control" min="1" max="31" placeholder="days"
                           value="${sessionScope.durationF}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <select class="form-select" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>Sort by</option>
                    <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>Cruise name</option>
                    <option value="ship.name" ${sessionScope.sort == 'ship.name' ? 'selected' : ''}>Ship name</option>
                    <option value="route.start_of_cruise" ${sessionScope.sort == 'route.start_of_cruise' ? 'selected' : ''}>Start date</option>
                    <option value="route.end_of_cruise" ${sessionScope.sort == 'route.end_of_cruise' ? 'selected' : ''}>End date</option>
                    <option value="route.duration" ${sessionScope.sort == 'route.duration' ? 'selected' : ''}>Duration</option>
                    <option value="route.price" ${sessionScope.sort == 'route.price' ? 'selected' : ''}>Price</option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}>Order by</option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>Ascending</option>
                    <option value="desc"${sessionScope.order == 'desc' ? 'selected' : ''}>Descending</option>
                </select>
            </div>

            <div class="col">
                <div class="input-group mb-2">
                    <span class="input-group-text w-50">Rows per page:</span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col">
                <div class="btn-group w-100">
                    <button type="button" class="btn btn-secondary w-50"
                            onclick="location.href = 'controller?action=manage_catalog&rows=6';">Reset</button>
                    <button type="submit" class="btn btn-primary w-50">Submit</button>
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
                    <img src="${pageContext.request.contextPath}/Img/Ship-3.jpg" class="card-img-top" alt="Cruise ship photo">
                    <div class="card-body">
                        <h5 class="card-title text-center">${cruise.route.name}</h5>
                        <p class="card-text">
                            <c:forEach var="waypoint" items="${cruise.route.waypoints}"><c:out
                                    value="${waypoint.port.name}"/><c:if
                                    test="${!waypoint.equals(cruise.route.waypoints[cruise.route.waypoints.size() - 1])}"><c:out
                                    value=", "/></c:if></c:forEach>
                        </p>
                        <ul class="list-group list-group-flush align-content-end">
                            <li class="list-group-item">Start date: <fd:formatDate date="${cruise.route.startOfCruise}"/></li>
                            <li class="list-group-item">End date: <fd:formatDate date="${cruise.route.endOfCruise}"/></li>
                            <li class="list-group-item">Duration: ${cruise.route.duration} days</li>
                            <li class="list-group-item">Ship name: ${cruise.name}</li>
                            <li class="list-group-item">Price: ${cruise.route.price}</li>
                        </ul>
                        <div class="text-center">
                            <button class="btn btn-outline-primary w-75 mt-2"
                                    onclick="location.href = 'controller?action=view_cruise&shipId=${cruise.id}';">View</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="my-3">
        <jsp:include page="../../../templates/pagination.jsp"/>
    </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-primary w-100 text-center">
            No cruises found with your search criteria
        </div>
    </c:otherwise>
    </c:choose>


</div>
</body>
</html>
