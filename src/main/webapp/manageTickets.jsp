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
       value="controller?action=view_tickets&sort=${sessionScope.sort}&order=${sessionScope.order}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3">My tickets${requestScope.ticket.id}</h2>
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
                            <td>${ticket.route.name}</td>
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
                <jsp:include page="templates/pagination.jsp"/>
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
