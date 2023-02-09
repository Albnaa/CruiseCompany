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
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/fragments/userNavbar.jsp"/>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="ticket.header"/>#${requestScope.ticket.id}</h2>
    <div class="row justify-content-center">
        <div class="col-4 text-center">
            <form method="post" action="controller">
                <input type="hidden" name="action" value="pay_for_ticket">
                <input type="hidden" name="ticketId" value="${requestScope.ticket.id}">
                <input type="hidden" name="ticketPrice" value="${requestScope.ticket.price}">
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
                    <input type="text" class="form-control"
                           value="<fd:formatDate date="${requestScope.ticket.ship.route.startOfCruise}"/>" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.passengersCount"/></label>
                    <input type="number" class="form-control" value="${requestScope.ticket.passengersCount}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.price"/></label>
                    <input type="number" class="form-control" value="${requestScope.ticket.price}" readonly>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-50"><fmt:message key="common.status"/></label>
                    <input type="text" class="form-control" value="${requestScope.ticket.status}" readonly>
                </div>
                <c:if test="${not empty sessionScope.error}">
                    <div class="alert alert-danger alert-dismissible">
                        <fmt:message key="${sessionScope.error}"/>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-warning w-50" ${requestScope.ticket.status == 'UNCHECKED'
                || requestScope.ticket.status == 'PAID'
                || requestScope.ticket.status == 'COMPLETED' ? 'disabled' : ''}>
                    <fmt:message key="common.button.pay"/>
                </button>
            </form>
        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
