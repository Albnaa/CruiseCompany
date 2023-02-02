<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
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

<div class="container">
    <h2 class="text-center p-3">Ticket#${requestScope.ticket.id}</h2>
    <div class="row justify-content-center">
        <div class="col-4 text-center">
            <form method="post" action="controller">
                <input type="hidden" name="action" value="update_ticket">
                <input type="hidden" name="ticketId" value="${requestScope.ticket.id}">
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">First name</label>
                    <input type="text" class="form-control" value="${requestScope.ticket.user.firstName}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Last name</label>
                    <input type="text" class="form-control" value="${requestScope.ticket.user.lastName}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Ship name</label>
                    <input type="text" class="form-control" value="${requestScope.ticket.ship.name}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Route name</label>
                    <input type="text" class="form-control" value="${requestScope.ticket.ship.route.name}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Cruise start</label>
                    <input type="text" class="form-control" value="<fd:formatDate date="${requestScope.ticket.ship.route.startOfCruise}"/>" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Passengers</label>
                    <input type="text" class="form-control" value="${requestScope.ticket.passengersCount}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Price</label>
                    <input type="text" class="form-control" value="${requestScope.ticket.price}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50">Status</label>
                    <select class="form-select" name="ticketStatus">
                        <option value="UNCHECKED" ${requestScope.ticket.status == 'UNCHECKED' ? 'selected' : ''}>UNCHECKED</option>
                        <option value="UNPAID" ${requestScope.ticket.status == 'UNPAID' ? 'selected' : ''}>UNPAID</option>
                        <option value="PAID" ${requestScope.ticket.status == 'PAID' ? 'selected' : ''}>PAID</option>
                        <option value="COMPLETED" ${requestScope.ticket.status == 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                    </select>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-secondary w-100">View documents</button>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-warning w-100">Update</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
