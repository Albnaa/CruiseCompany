<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="routes.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<c:set var="link"
       value="controller?action=manage_route&sort=${sessionScope.sort}&order=${sessionScope.order}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="routes.header"/></h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_route">
        <div class="row justify-content-center g-2">
            <div class="col">
                <select class="form-select" name="sort">
                    <option><fmt:message key="common.sort.default"/></option>
                    <option value="route.id"><fmt:message key="common.id"/></option>
                    <option value="route.name"><fmt:message key="common.name"/></option>
                    <option value="route.start_of_cruise"><fmt:message key="common.startOfCruise"/></option>
                    <option value="route.end_of_cruise"><fmt:message key="common.endOfCruise"/></option>
                    <option value="route.price"><fmt:message key="common.price"/></option>
                    <option value="route.duration"><fmt:message key="common.duration"/></option>
                    <option value="route.number_of_ports"><fmt:message key="common.numberOfPorts"/></option>
                </select>
            </div>
            <div class="col">
                <select class="form-select" name="order">
                    <option value="" ${empty sessionScope.order ? 'selected' : ''}><fmt:message
                            key="common.order.default"/></option>
                    <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}><fmt:message
                            key="common.order.asc"/></option>
                    <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}><fmt:message
                            key="common.order.desc"/></option>
                </select>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text"><fmt:message key="common.filter.startDate.short"/></span>
                    <input type="date" placeholder="dd/MM/yyyy" class="form-control" name="startDate">
                </div>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text"><fmt:message key="common.rows"/></span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1"
                           value="${requestScope.rows}">
                </div>
            </div>
            <div class="col-1">
                <button type="button" class="btn btn-primary w-100"
                        data-bs-toggle="modal" data-bs-target="#createRouteModal">
                    <fmt:message key="common.button.create"/>
                </button>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=manage_port';">
                    <fmt:message key="common.button.reset"/>
                </button>
                <button type="submit" class="btn btn-primary w-100"><fmt:message key="common.button.submit"/></button>
            </div>
        </div>
    </form>

    <form method="get" action="controller">
        <c:choose>
            <c:when test="${not empty requestScope.routes}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="common.id"/></th>
                        <th><fmt:message key="common.name"/></th>
                        <th><fmt:message key="common.startOfCruise"/></th>
                        <th><fmt:message key="common.endOfCruise"/></th>
                        <th><fmt:message key="common.price"/></th>
                        <th><fmt:message key="common.duration"/></th>
                        <th><fmt:message key="common.numberOfPorts"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="route" items="${requestScope.routes}">
                        <tr class="${route}">
                            <td>${route.id}</td>
                            <td>${route.name}</td>
                            <td><fd:formatDate date="${route.startOfCruise}"/></td>
                            <td><fd:formatDate date="${route.endOfCruise}"/></td>
                            <td>${route.price}</td>
                            <td>${route.duration}</td>
                            <td>${route.numOfPorts}</td>
                            <td>
                                <a class="btn btn-primary p-0 " style="width: 60px"
                                   href="controller?action=view_route&id=${route.id}&rows=8">
                                    <fmt:message key="common.button.more"/>
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
                    <fmt:message key="routes.table.error"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>

<jsp:include page="/WEB-INF/fragments/createRouteModal.jsp"/>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
