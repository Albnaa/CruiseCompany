<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Ships</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="../../../templates/navbar.jsp"/>

<c:set var="link"
       value="controller?action=manage_ship&sort=${sessionScope.sort}&order=${sessionScope.order}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3">Manage ships</h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_ship">
        <div class="row justify-content-center">
            <div class="col">
            <select class="form-select" name="sort">
                <option>Sort by</option>
                <option value="ship.id">Id</option>
                <option value="ship.name">Name</option>
                <option value="ship.capacity">Capacity</option>
                <option value="ship.visited_ports">Visited ports</option>
                <option value="ship.staff">Staff</option>
                <option value="ship.route_name">Route name</option>
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
                    <span class="input-group-text">Rows per page:</span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col">
                <button type="button" class="btn btn-primary w-100" data-bs-toggle="modal"
                        data-bs-target="#createShipModal">Create</button>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_ship';">Reset</button>
                <button type="submit" class="btn btn-primary w-100">Submit</button>
            </div>
        </div>
    </form>
    <form method="post" action="controller">
        <c:choose>
            <c:when test="${not empty requestScope.ships}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Capacity</th>
                        <th>Visited ports</th>
                        <th>Staff</th>
                        <th>Route name</th>
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
                                <a class="btn btn-primary p-0 " style="width: 60px" href="controller?action=view_ship&shipId=${ship.id}">More</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="../../../templates/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary">
                    No ships found with these search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<jsp:include page="../../../templates/createShipModal.jsp"/>
</body>
</html>
