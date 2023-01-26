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
<jsp:include page="templates/navbar.jsp"/>

<c:set var="link"
       value="controller?action=view_route&sort=${sessionScope.sort}&order=${sessionScope.order}&routeId=${requestScope.routeId}"
       scope="request"/>


<div class="container">
    <h2 class="text-center p-3">Update cruise</h2>
    <div class="row align-items-center border-bottom border-primary">
        <div class="col-6">
            <form method="post" action="controller">
                <button type="button" class="btn btn-primary w-100 mb-2"
                        data-bs-toggle="modal" data-bs-target="#createModal">Create
                </button>
                <div class="btn-group w-100">
                    <button type="submit" name="action" value="update_route" class="btn btn-warning w-50 mb-2">Update
                    </button>
                    <button type="submit" name="action" value="delete_route" class="btn btn-danger w-50 mb-2">Delete
                    </button>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Id</span>
                    <input type="number" class="form-control" name="routeId" value="${requestScope.route.id}" readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Name</span>
                    <input type="text" class="form-control" name="routeName" value="${requestScope.route.name}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Start date</span>
                    <input type="date" class="form-control" name="routeStart"
                           value="${requestScope.route.startOfCruise}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">End date</span>
                    <input type="date" class="form-control" name="routeEnd" value="${requestScope.route.endOfCruise}">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Duration</span>
                    <input type="text" class="form-control" name="routeDuration" value="${requestScope.route.duration}"
                           readonly>
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Number of ports</span>
                    <input type="text" class="form-control" name="routeNumOfPorts"
                           value="${requestScope.route.numOfPorts}"
                           readonly>
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
                        <th class="col-2 text-center">Id</th>
                        <th class="col-8 text-center">Name</th>
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
                                        class="btn btn-primary p-0" style="width: 60px">Add
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    <jsp:include page="templates/pagination.jsp"/>
                </c:when>
                <c:otherwise>
                <div class="alert alert-primary text-center">
                    Cannot found related list of ports
                </div>
                </c:otherwise>
                </c:choose>

        </div>
        <div class="col-2 justify-content-center">
            <label for="arriveDate">Arrive date</label>
            <input id="arriveDate" type="date" class="form-control" name="arriveDate" required>
            <label for="departureDate">Departure date</label>
            <input id="departureDate" type="date" class="form-control mb-2" name="departureDate" required>
            </form>
            <br>
            <form method="get" action="controller">
                <input type="hidden" name="action" value="view_route">
                <input type="hidden" name="routeId" value="${requestScope.route.id}">
                <input type="text" class="form-control mb-2" placeholder="Enter port name">
                <select class="form-select mb-2" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>Sort by</option>
                    <option value="port.id" ${sessionScope.sort == 'port.id' ? 'selected' : ''}>Id</option>
                    <option value="port.name" ${sessionScope.sort == 'port.name' ? 'selected' : ''}>Name</option>
                </select>
                <select class="form-select mb-2" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}>Order by</option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>Ascending</option>
                    <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>Descending</option>
                </select>
                <div class="input-group mb-2">
                    <span class="input-group-text">Rows per page:</span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
                <button type="submit" class="btn btn-primary w-100">Submit</button>
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
                        <th class="col-1">Id</th>
                        <th class="col-4">Name</th>
                        <th class="col-3">Arrive time</th>
                        <th class="col-3">Departure time</th>
                        <th class="col-1"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="waypoint" items="${requestScope.route.waypoints}">
                        <tr class="${waypoint}">
                            <td class="col-1">${waypoint.port.id}</td>
                            <td class="col-4">${waypoint.port.name}</td>
                            <td class="col-3">${waypoint.arriveTime}</td>
                            <td class="col-3">${waypoint.departureTime}</td>
                            <td class="col-1">
                                <button type="submit" name="waypointPortId" value="${waypoint.port.id}"
                                        class="btn btn-danger p-0" style="width: 60px">Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary text-center my-3">
                    Cannot found related waypoints
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5 text-center" id="createModalLabel">Create route</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="get">
                    <input type="hidden" name="action" value="create_route">
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">Name</span>
                        <input type="text" class="form-control" name="routeName" required>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">Start date</span>
                        <input type="date" class="form-control" name="routeStart" required>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25">End date</span>
                        <input type="date" class="form-control" name="routeEnd" required>
                    </div>
                    <div class="row justify-content-center mt-3">
                        <div class="col">
                            <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">Close</button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary w-100">Create</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
