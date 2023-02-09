<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="ship.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/fragments/adminNavbar.jsp"/>

<c:url value="controller" var="link" scope="request">
    <c:param name="action" value="view_ship"/>
    <c:param name="sort" value="${sessionScope.sort}"/>
    <c:param name="order" value="${sessionScope.order}"/>
    <c:param name="id" value="${requestScope.ship.id}"/>
</c:url>

<div class="container">
    <h2 class="text-center p-3"><fmt:message key="ship.header"/></h2>
    <div class="row align-items-center">
        <div class="col-6">
            <img src="controller?action=file&path=${requestScope.ship.imagePath}" class="rounded-3 img-fluid"
                 alt="ship"/>
        </div>
        <div class="col-6">
            <form method="post" action="controller" class="border-bottom border-primary mb-2">
                <button type="button" class="btn btn-primary w-100 mb-2"
                        data-bs-toggle="modal" data-bs-target="#createShipModal">
                    <fmt:message key="common.button.create"/>
                </button>
                <div class="btn-group w-100">
                    <button type="submit" name="action" value="update_ship" class="btn btn-warning w-50 mb-2">
                        <fmt:message key="common.button.update"/>
                    </button>
                    <button type="submit" name="action" value="delete_ship" class="btn btn-danger w-50 mb-2">
                        <fmt:message key="common.button.delete"/>
                    </button>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="id" entity="update.ship" labelKey="common.id" width="25" type="number"
                                    readonly="readonly" value="${requestScope.ship.id}"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="name" entity="update.ship" labelKey="common.name" width="25" type="text"
                                    value="${requestScope.ship.name}" placeholder="ship.name.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="capacity" entity="update.ship" labelKey="common.capacity" width="25"
                                    type="number" value="${requestScope.ship.capacity}"
                                    placeholder="ship.capacity.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="visitedPorts" entity="update.ship" labelKey="common.visitedPorts"
                                    width="25"
                                    type="number" value="${requestScope.ship.visitedPorts}"
                                    placeholder="ship.visitedPorts.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="staff" entity="update.ship" labelKey="common.staff" width="25"
                                    type="number"
                                    value="${requestScope.ship.staff}" placeholder="ship.staff.placeholder"/>
                </div>
                <div class="mb-2">
                    <tag:inputField fieldName="routeName" entity="update.ship" labelKey="common.routeName" width="25"
                                    type="text" value="${requestScope.ship.route.name}" readonly="readonly"/>
                </div>
            </form>
            <form method="post" action="controller" enctype="multipart/form-data">
                <input type="hidden" name="action" value="update_ship_image">
                <input type="hidden" name="id" value="${requestScope.ship.id}">
                <button type="submit" class="btn btn-warning mb-2 w-100">Update image</button>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25"><fmt:message key="common.image"/></span>
                    <input type="file" name="image" class="form-control">
                </div>
            </form>
        </div>
    </div>
    <div class="border-bottom border-primary my-3">
        <form method="post" class="" action="controller">
            <input type="hidden" name="id" value="${requestScope.ship.id}">
            <div class="row align-items-center">
                <div class="col-6">
                    <button type="submit" name="action" value="unlink_route"
                            class="btn btn-secondary w-100 mb-2">
                        <fmt:message key="common.button.unLink"/>
                    </button>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25"><fmt:message key="common.id"/></span>
                        <input type="number" class="form-control" value="${requestScope.ship.route.id}" readonly>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25"><fmt:message key="common.name"/></span>
                        <input type="text" class="form-control" value="${requestScope.ship.route.name}" readonly>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <div class="input-group">
                                <span class="input-group-text" style="width: 159px">
                                    <fmt:message key="common.duration"/>
                                </span>
                                <input type="text" class="form-control" value="${requestScope.ship.route.duration}"
                                       readonly>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="input-group">
                                <span class="input-group-text w-50"><fmt:message key="common.numberOfPorts"/></span>
                                <input type="text" class="form-control" value="${requestScope.ship.route.numOfPorts}"
                                       readonly>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <button type="button" class="btn btn-primary w-100 mb-2"
                            onclick="location.href = 'controller?action=view_route&id=${requestScope.ship.route.id}';"
                    ${empty requestScope.ship.route.id ? 'disabled' : ''}>
                        <fmt:message key="common.button.more"/>
                    </button>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25"><fmt:message key="common.startOfCruise"/></span>
                        <input type="date" class="form-control" value="${requestScope.ship.route.startOfCruise}"
                               readonly>
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text w-25"><fmt:message key="common.endOfCruise"/></span>
                        <input type="date" class="form-control" value="${requestScope.ship.route.endOfCruise}" readonly>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text w-25"><fmt:message key="common.price"/></span>
                        <input type="number" class="form-control" value="${requestScope.ship.route.price}" readonly/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="row mt-3">
        <form method="get" action="controller">
            <div class="row">
                <input type="hidden" name="action" value="view_ship">
                <input type="hidden" name="id" value="${requestScope.ship.id}">
                <div class="col">
                    <tag:inputField fieldName="nameF" entity="route" labelKey="common.name" width="25" type="text"
                                    placeholder="ship.search.route.placeholder"/>
                </div>
                <div class="col">
                    <select class="form-select mb-2" name="sort">
                        <option value="" ${empty sessionScope.sort ? 'selected' : ''}>
                            <fmt:message key="common.sort.default"/>
                        </option>
                        <option value="route.id" ${sessionScope.sort == 'route.id' ? 'selected' : ''}>
                            <fmt:message key="common.id"/>
                        </option>
                        <option value="route.name" ${sessionScope.sort == 'route.name' ? 'selected' : ''}>
                            <fmt:message key="common.name"/>
                        </option>
                        <option value="route.start_of_cruise" ${sessionScope.sort == 'route.startOfCruise' ? 'selected' : ''}>
                            <fmt:message key="common.startOfCruise"/>
                        </option>
                        <option value="route.end_of_cruise" ${sessionScope.sort == 'route.endOfCruise' ? 'selected' : ''}>
                            <fmt:message key="common.endOfCruise"/>
                        </option>
                        <option value="route.price" ${sessionScope.sort == 'route.price' ? 'selected' : ''}>
                            <fmt:message key="common.price"/>
                        </option>
                        <option value="route.number_of_ports" ${sessionScope.sort == 'route.numberOfPorts' ? 'selected' : ''}>
                            <fmt:message key="common.numberOfPorts"/>
                        </option>
                    </select>
                </div>
                <div class="col">
                    <select class="form-select mb-2" name="order">
                        <option value="" ${empty sessionScope.order ? 'selected' : ''}>
                            <fmt:message key="common.order.default"/>
                        </option>
                        <option value="asc" ${sessionScope.order == 'asc' ? 'selected' : ''}>
                            <fmt:message key="common.order.asc"/>
                        </option>
                        <option value="desc" ${sessionScope.order == 'desc' ? 'selected' : ''}>
                            <fmt:message key="common.order.desc"/>
                        </option>
                    </select>
                </div>
                <div class="col-2">
                    <div class="input-group mb-2">
                        <span class="input-group-text w-75"><fmt:message key="common.rows"/></span>
                        <input type="number" id="rows" name="rows" class="form-control" min="1"
                               value="${requestScope.rows}">
                    </div>
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary w-100"><fmt:message
                            key="common.button.submit"/></button>
                </div>
            </div>
        </form>

        <form method="post" action="controller">
            <input type="hidden" name="action" value="link_route">
            <input type="hidden" name="id" value="${requestScope.ship.id}">
            <c:choose>
                <c:when test="${not empty requestScope.routes}">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="text-center"><fmt:message key="common.id"/></th>
                            <th class="text-center"><fmt:message key="common.name"/></th>
                            <th class="text-center"><fmt:message key="common.startOfCruise"/></th>
                            <th class="text-center"><fmt:message key="common.endOfCruise"/></th>
                            <th class="text-center"><fmt:message key="common.price"/></th>
                            <th class="text-center"><fmt:message key="common.numberOfPorts"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="route" items="${requestScope.routes}">
                            <tr class="${route}">
                                <td class="text-center">${route.id}</td>
                                <td class="text-center">${route.name}</td>
                                <td class="text-center"><fd:formatDate date="${route.startOfCruise}"/></td>
                                <td class="text-center"><fd:formatDate date="${route.endOfCruise}"/></td>
                                <td class="text-center">${route.price}</td>
                                <td class="text-center">${route.numOfPorts}</td>
                                <td class="text-center">
                                    <button type="submit" name="routeId" value="${route.id}"
                                            class="btn btn-primary px-2 py-0" style="width: auto">
                                        <fmt:message key="common.button.link"/>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <jsp:include page="/WEB-INF/fragments/pagination.jsp"/>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-primary text-center">
                        <fmt:message key="ship.table.error"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/fragments/createShipModal.jsp"/>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>