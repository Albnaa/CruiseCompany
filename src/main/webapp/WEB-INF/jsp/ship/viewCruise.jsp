<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="viewCruise.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body class="d-flex flex-column min-vh-100">

<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="/WEB-INF/fragments/userNavbar.jsp"/>
</c:if>

<div class="container">
    <h2 class="text-center p-3">${requestScope.ship.route.name}</h2>
    <div class="row">
        <div class="col">
            <div class="card mb-3">
                <img src="controller?action=file&path=${requestScope.ship.imagePath}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title text-center"><fmt:message key="common.route"/></h5>
                    <ul class="list-group list-group-flush align-content-end">
                        <li class="list-group-item">
                            <fmt:message key="common.startOfCruise"/>:
                            <fd:formatDate date="${requestScope.ship.route.startOfCruise}"/>
                        </li>
                        <li class="list-group-item">
                            <fmt:message key="common.endOfCruise"/>:
                            <fd:formatDate date="${requestScope.ship.route.endOfCruise}"/>
                        </li>
                        <li class="list-group-item">
                            <fmt:message key="common.duration"/>:
                            ${requestScope.ship.route.duration}
                            <fmt:message key="common.days"/>
                        </li>
                        <li class="list-group-item">
                            <fmt:message key="common.shipName"/>:
                            ${requestScope.ship.name}
                        </li>
                        <li class="list-group-item">
                            <fmt:message key="common.price"/>:
                            ${requestScope.ship.route.price}
                        </li>
                    </ul>
                </div>
            </div>
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title text-center"><fmt:message key="viewCruise.orderNow"/></h5>
                    <button class="btn btn-primary btn-lg w-50 p-2 m-2" data-bs-toggle="modal"
                            data-bs-target="#createTicketModal">
                        <fmt:message key="common.button.order"/>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-4 ">
            <ol class="list-group list-group-numbered">
                <c:forEach var="waypoint" items="${requestScope.ship.route.waypoints}">
                    <li class="list-group-item d-flex align-items-center">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${waypoint.port.name}</div>
                            <h6><i class="bi bi-box-arrow-in-down-right"></i>
                                 <fmt:message key="common.arrivalTime"/>:
                                <fd:formatDate date="${waypoint.arriveTime}"/>
                            </h6>
                            <h6><i class="bi bi-box-arrow-right"></i>
                                <fmt:message key="common.departureTime"/>:
                                <fd:formatDate date="${waypoint.departureTime}"/>
                            </h6>
                        </div>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/fragments/createTicketModal.jsp"/>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
