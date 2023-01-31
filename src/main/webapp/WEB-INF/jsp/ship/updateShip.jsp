<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
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
       value="controller?action=view_ship&sort=${sessionScope.sort}&order=${sessionScope.order}&shipId=${requestScope.ship.id}"
       scope="request"/>


<div class="container">
    <h2 class="text-center p-3">Update ship</h2>
    <div class="row align-items-center border-bottom border-primary">
        <div class="col-6">
            <form method="post" action="controller">
                <button type="button" class="btn btn-primary w-100 mb-2"
                        data-bs-toggle="modal" data-bs-target="#createShipModal">Create
                </button>
                <div class="btn-group w-100">
                    <button type="submit" name="action" value="update_ship" class="btn btn-warning w-50 mb-2">Update
                    </button>
                    <button type="submit" name="action" value="delete_ship" class="btn btn-danger w-50 mb-2">Delete
                    </button>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Id</span>
                    <input type="number" class="form-control" name="shipId" value="${requestScope.ship.id}" readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Name</span>
                    <input type="text" class="form-control" name="shipName" value="${requestScope.ship.name}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Capacity</span>
                    <input type="number" class="form-control" name="shipCapacity"
                           value="${requestScope.ship.capacity}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Visited ports</span>
                    <input type="number" class="form-control" name="shipVisitedPorts"
                           value="${requestScope.ship.visitedPorts}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Staff</span>
                    <input type="number" class="form-control" name="shipStaff" value="${requestScope.ship.staff}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Route name</span>
                    <input type="text" class="form-control" name="shipRouteName" value="${requestScope.ship.route.name}"
                           readonly>
                </div>
            </form>
        </div>
        <div class="col-6">
            <form method="post" action="controller">
                <input type="hidden" name="shipId" value="${requestScope.ship.id}">
                <div class="row">
                    <div class="col">
                        <button type="submit" name="action" value="unlink_route"
                                class="btn btn-secondary w-100 mb-2">Unlink</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-primary w-100 mb-2"
                                onclick="location.href = 'controller?action=view_route&routeId=${requestScope.ship.route.id}';"
                                ${empty requestScope.ship.route.id ? 'disabled' : ''}>More</button>
                    </div>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Id</span>
                    <input type="number" class="form-control" name="shipId" value="${requestScope.ship.route.id}"
                           readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Name</span>
                    <input type="text" class="form-control" name="shipName" value="${requestScope.ship.route.name}"
                           readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Start of cruise</span>
                    <input type="date" class="form-control" name="shipCapacity"
                           value="${requestScope.ship.route.startOfCruise}" readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">End of cruise</span>
                    <input type="date" class="form-control" name="shipVisitedPorts"
                           value="${requestScope.ship.route.endOfCruise}" readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Price</span>
                    <input type="number" class="form-control" name="shipStaff"
                           value="${requestScope.ship.route.price}" readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Duration</span>
                    <input type="text" class="form-control" name="shipRouteName"
                           value="${requestScope.ship.route.duration}" readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Number of ports</span>
                    <input type="text" class="form-control" name="shipRouteName"
                           value="${requestScope.ship.route.numOfPorts}" readonly>
                </div>
            </form>
        </div>
    </div>

    <div class="row mt-3">
        <form method="get" action="controller">
            <div class="row">
                <input type="hidden" name="action" value="view_ship">
                <input type="hidden" name="shipId" value="${requestScope.ship.id}">
                <div class="col">
                    <input type="text" class="form-control mb-2" placeholder="Enter route name">
                </div>
                <div class="col">
                    <select class="form-select mb-2" name="sort">
                        <option value="" ${empty sessionScope.sort ? 'selected' : ''}>Sort by</option>
                        <option value="route.id" ${sessionScope.sort == 'route.id' ? 'selected' : ''}>Id</option>
                        <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>Name</option>
                        <option value="route.start_of_cruise" ${sessionScope.sort == 'route.startOfCruise' ? 'selected' : ''}>
                            Start date
                        </option>
                        <option value="route.end_of_cruise" ${sessionScope.sort == 'route.endOfCruise' ? 'selected' : ''}>
                            End date
                        </option>
                        <option value="route.price" ${sessionScope.sort == 'route.price' ? 'selected' : ''}>Price
                        </option>
                        <option value="route.number_of_ports" ${sessionScope.sort == 'route.numberOfPorts' ? 'selected' : ''}>
                            Number of ports
                        </option>
                    </select>
                </div>
                <div class="col">
                    <select class="form-select mb-2" name="order">
                        <option value="" ${empty sessionScope.order ? 'selected' : ''}>Order by</option>
                        <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>Ascending</option>
                        <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>Descending</option>
                    </select>
                </div>
                <div class="col">
                    <div class="input-group mb-2">
                        <span class="input-group-text">Rows per page:</span>
                        <input type="number" id="rows" name="rows" class="form-control" min="1"
                               value="${requestScope.rows}">
                    </div>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-primary w-100">Submit</button>
                </div>
            </div>
        </form>

        <form method="post" action="controller">
            <input type="hidden" name="action" value="link_route">
            <input type="hidden" name="shipId" value="${requestScope.ship.id}">
            <c:choose>
                <c:when test="${not empty requestScope.routes}">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Name</th>
                            <th class="text-center">Start date</th>
                            <th class="text-center">End date</th>
                            <th class="text-center">Price</th>
                            <th class="text-center">Number of ports</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="route" items="${requestScope.routes}">
                            <tr class="${route}">
                                <td class="text-center">${route.id}</td>
                                <td class="text-center">${route.name}</td>
                                <td class="text-center">${route.startOfCruise}</td>
                                <td class="text-center">${route.endOfCruise}</td>
                                <td class="text-center">${route.price}</td>
                                <td class="text-center">${route.numOfPorts}</td>
                                <td class="text-center">
                                    <button type="submit" name="routeId" value="${route.id}"
                                            class="btn btn-primary p-0" style="width: 60px">Link
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <jsp:include page="../../../templates/pagination.jsp"/>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-primary text-center">
                        Cannot find related list of routes
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</div>

<jsp:include page="../../../templates/createShipModal.jsp"/>
</body>
</html>