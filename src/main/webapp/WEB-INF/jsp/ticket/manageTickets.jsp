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
<jsp:include page="../../../templates/navbar.jsp"/>

<c:set var="link"
       value="controller?action=manage_tickets&sort=${sessionScope.sort}&order=${sessionScope.order}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3">Manage tickets${requestScope.ticket.id}</h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_tickets">
        <div class="row">
            <div class="col">
                <select class="form-select" name="sort">
                    <option ${empty sessionScope.sort ? 'selected' : ''}>Sort by</option>
                    <option value="ticket.id" ${sessionScope.sort == 'ticket.id' ? 'selected' : ''}>Id</option>
                    <option value="user.first_name" ${sessionScope.sort == 'user.first_name' ? 'selected' : ''}>First name</option>
                    <option value="user.last_name" ${sessionScope.sort == 'user.last_name' ? 'selected' : ''}>Last name</option>
                    <option value="route.start_of_cruise" ${sessionScope.sort == 'route.start_of_cruise' ? 'selected' : ''}>Start of cruise</option>
                    <option value="ship.name" ${sessionScope.sort == 'ship.name' ? 'selected' : ''}>Ship name</option>
                    <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>Route name</option>
                    <option value="ticket.passengers_count" ${sessionScope.sort == 'ticket.passengers_count' ? 'selected' : ''}>Passengers count</option>
                    <option value="ticket.price" ${sessionScope.sort == 'ticket.price' ? 'selected' : ''}>Price</option>
                    <option value="ticket.status_id" ${sessionScope.sort == 'ticket.status_id' ? 'selected' : ''}>Status</option>
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
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_port';">Reset
                </button>
                <button type="submit" class="btn btn-primary w-100">Submit</button>
            </div>
        </div>
    </form>
    <form method="post" action="controller">
        <c:choose>
            <c:when test="${not empty requestScope.tickets}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Start of cruise</th>
                        <th>Ship name</th>
                        <th>Route name</th>
                        <th>Passengers count</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ticket" items="${requestScope.tickets}">
                        <tr class="${ticket}">
                            <td>${ticket.id}</td>
                            <td>${ticket.user.firstName}</td>
                            <td>${ticket.user.lastName}</td>
                            <td>${ticket.ship.route.startOfCruise}</td>
                            <td>${ticket.ship.name}</td>
                            <td>${ticket.ship.route.name}</td>
                            <td>${ticket.passengersCount}</td>
                            <td>${ticket.price}</td>
                            <td>${ticket.status}</td>
                            <td>
                                <a class="btn btn-primary p-0 " style="width: 60px"
                                   href="controller?action=view_ticket&ticketId=${ticket.id}">More</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="../../../templates/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary">
                    Cannot find tickets
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
</html>
