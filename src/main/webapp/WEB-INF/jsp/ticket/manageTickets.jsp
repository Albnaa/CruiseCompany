<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="tickets.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="manage_tickets"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="tickets.title"/></h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_tickets">
        <div class="row">
            <div class="col">
                <select class="form-select" name="sort">
                    <option ${empty sessionScope.sort ? 'selected' : ''}>
                        <fmt:message key="sort.default"/>
                    </option>
                    <option value="ticket.id" ${sessionScope.sort == 'ticket.id' ? 'selected' : ''}>
                        <fmt:message key="table.id"/>
                    </option>
                    <option value="user.first_name" ${sessionScope.sort == 'user.first_name' ? 'selected' : ''}>
                        <fmt:message key="table.firstName"/>
                    </option>
                    <option value="user.last_name" ${sessionScope.sort == 'user.last_name' ? 'selected' : ''}>
                        <fmt:message key="table.lastName"/>
                    </option>
                    <option value="route.start_of_cruise" ${sessionScope.sort == 'route.start_of_cruise' ? 'selected' : ''}>
                        <fmt:message key="table.startOfCruise"/>
                    </option>
                    <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>
                        <fmt:message key="table.routeName"/>
                    </option>
                    <option value="ticket.passengers_count" ${sessionScope.sort == 'ticket.passengers_count' ? 'selected' : ''}>
                        <fmt:message key="table.passengersCount"/>
                    </option>
                    <option value="ticket.price" ${sessionScope.sort == 'ticket.price' ? 'selected' : ''}>
                        <fmt:message key="table.price"/>
                    </option>
                    <option value="ticket.status_id" ${sessionScope.sort == 'ticket.status_id' ? 'selected' : ''}>
                        <fmt:message key="table.status"/>
                    </option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}>
                        <fmt:message key="order.default"/>
                    </option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>
                        <fmt:message key="order.asc"/>
                    </option>
                    <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>
                        <fmt:message key="order.desc"/>
                    </option>
                </select>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text"><fmt:message key="sort.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_port';">
                    <fmt:message key="table.button.reset"/>
                </button>
                <button type="submit" class="btn btn-primary w-100">
                    <fmt:message key="table.button.submit"/>
                </button>
            </div>
        </div>
    </form>
    <form method="post" action="controller">
        <c:choose>
            <c:when test="${not empty requestScope.tickets}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="table.id"/></th>
                        <th><fmt:message key="table.firstName"/></th>
                        <th><fmt:message key="table.lastName"/></th>
                        <th><fmt:message key="table.startOfCruise"/></th>
                        <th><fmt:message key="table.shipName"/></th>
                        <th><fmt:message key="table.routeName"/></th>
                        <th><fmt:message key="table.passengersCount"/></th>
                        <th><fmt:message key="table.price"/></th>
                        <th><fmt:message key="table.status"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ticket" items="${requestScope.tickets}">
                        <tr class="${ticket}">
                            <td>${ticket.id}</td>
                            <td>${ticket.user.firstName}</td>
                            <td>${ticket.user.lastName}</td>
                            <td><fd:formatDate date="${ticket.ship.route.startOfCruise}"/></td>
                            <td>${ticket.ship.name}</td>
                            <td>${ticket.ship.route.name}</td>
                            <td>${ticket.passengersCount}</td>
                            <td>${ticket.price}</td>
                            <td>${ticket.status}</td>
                            <td>
                                <a class="btn btn-primary p-0" style="width: 60px"
                                   href="controller?action=view_ticket&ticketId=${ticket.id}">More
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="alert alert-primary">
                    <fmt:message key="tickets.table.error"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
</html>
