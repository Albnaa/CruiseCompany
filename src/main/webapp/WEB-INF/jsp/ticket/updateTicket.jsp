<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="ticket.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="ticket.header"/>#${requestScope.ticket.id}</h2>
    <div class="row justify-content-center">
        <div class="col-4 text-center">
            <form method="post" action="controller">
                <input type="hidden" name="ticketId" value="${requestScope.ticket.id}">
                <input type="hidden" name="path" value="${requestScope.ticket.documentPath  }">
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.firstName"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.user.firstName}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.lastName"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.user.lastName}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.shipName"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.ship.name}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.routeName"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.ship.route.name}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.startOfCruise"/></label>
                    <input type="text" class="form-control" value="<fd:formatDate date="${requestScope.ticket.ship.route.startOfCruise}"/>" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.passengersCount"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.passengersCount}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.price"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.price}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.status"/></label>
                    <select class="form-select" name="ticketStatus">
                        <option value="UNCHECKED" ${requestScope.ticket.status == 'UNCHECKED' ? 'selected' : ''}>
                            UNCHECKED
                        </option>
                        <option value="UNPAID" ${requestScope.ticket.status == 'UNPAID' ? 'selected' : ''}>
                            UNPAID
                        </option>
                        <option value="PAID" ${requestScope.ticket.status == 'PAID' ? 'selected' : ''}>
                            PAID
                        </option>
                        <option value="COMPLETED" ${requestScope.ticket.status == 'COMPLETED' ? 'selected' : ''}>
                            COMPLETED
                        </option>
                    </select>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-secondary w-100" name="action" value="file">
                            <fmt:message key="common.button.viewDocument"/>
                        </button>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-warning w-100" name="action" value="update_ticket">
                            <fmt:message key="common.button.update"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
