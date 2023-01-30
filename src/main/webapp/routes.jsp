<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Routes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="templates/navbar.jsp"/>

<c:set var="link"
       value="controller?action=manage_route&sort=${sessionScope.sort}&order=${sessionScope.order}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3">Manage route</h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_route">
        <div class="row justify-content-center g-2">
            <div class="col">
                <select class="form-select" name="sort">
                    <option>Sort by</option>
                    <option value="route.id">Id</option>
                    <option value="route.name">Name</option>
                    <option value="route.start_of_cruise">Start of cruise</option>
                    <option value="route.end_of_cruise">End of cruise</option>
                    <option value="route.price">Price</option>
                    <option value="route.duration">Duration</option>
                    <option value="route.number_of_ports">Number of ports</option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}>Order by</option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>Ascending</option>
                    <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>Descending</option>
                </select>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text">Start date</span>
                    <input type="date" class="form-control" name="startDate">
                </div>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text">Rows per page:</span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col-1">
                <button type="button" class="btn btn-primary w-100"
                        data-bs-toggle="modal" data-bs-target="#createRouteModal">Create
                </button>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_port';">Reset
                </button>
                <button type="submit" class="btn btn-primary w-100">Submit</button>
            </div>
        </div>
    </form>

    <form method="get" action="controller">
        <c:choose>
            <c:when test="${not empty requestScope.routes}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Start of cruise</th>
                        <th>End of cruise</th>
                        <th>Price</th>
                        <th>Duration</th>
                        <th>Number of ports</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="route" items="${requestScope.routes}">
                        <tr class="${route}">
                            <td>${route.id}</td>
                            <td>${route.name}</td>
                            <td>${route.startOfCruise}</td>
                            <td>${route.endOfCruise}</td>
                            <td>${route.price}</td>
                            <td>${route.duration}</td>
                            <td>${route.numOfPorts}</td>
                            <td>
                                <a class="btn btn-primary p-0 " style="width: 60px" href="controller?action=view_route&routeId=${route.id}&rows=7">More</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary">
                    Cannot found route with this search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <jsp:include page="templates/pagination.jsp"/>
</div>

<jsp:include page="templates/createRouteModal.jsp"/>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
