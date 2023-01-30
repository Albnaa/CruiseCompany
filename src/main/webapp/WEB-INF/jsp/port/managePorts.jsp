<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Ports</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="../../../templates/navbar.jsp"/>

<c:set var="link"
       value="controller?action=manage_port&sort=${sessionScope.sort}&order=${sessionScope.order}"
       scope="request"/>

<div class="container">
    <h2 class="text-center p-3">Manage ports</h2>
    <form method="get" action="controller">
        <input type="hidden" name="action" value="manage_port">
        <div class="row justify-content-center">
            <div class="col">
                <select class="form-select" name="sort">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>Sort by</option>
                    <option value="port.id" ${sessionScope.sort == 'port.id' ? 'selected' : ''}>Id</option>
                    <option value="port.name" ${sessionScope.sort == 'port.name' ? 'selected' : ''}>Name</option>
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

    <div class="row align-items-center">
        <div class="col-3">
            <h4 class="text-center">Create</h4>
            <form method="post" action="controller">
                <input type="hidden" name="action" value="create_port">
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Name</span>
                    <input type="text" class="form-control ${fn:contains(sessionScope.error, 'ports.name') ? 'is-invalid' : ''}"
                           name="name" placeholder="Enter new port name">
                    <c:if test="${fn:contains(sessionScope.error, 'ports.name')}">
                        <div class="invalid-feedback">
                            <fmt:message key="${sessionScope.error}"/>
                        </div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary w-100 mb-2">Create</button>
                <div class="" style="height: 37.6px">
                </div>
            </form>
        </div>
        <div class="col-6">
            <form method="get" action="controller">
                <c:choose>
                    <c:when test="${not empty requestScope.ports}">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th class="text-center">Id</th>
                                <th class="text-center">Name</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="port" items="${requestScope.ports}">
                                <tr class="${port}">
                                    <td class="text-center">${port.id}</td>
                                    <td class="text-center">${port.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-primary">
                            Cannot found port with this search criteria
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>

            <jsp:include page="../../../templates/pagination.jsp"/>

        </div>
        <div class="col-3">
            <h4 class="text-center">Update</h4>
            <form method="post" action="controller">
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Id</span>
                    <input type="number" class="form-control" name="id" min="1" placeholder="Enter existing id">
                </div>
                <div class="input-group mb-2">
                    <span class="input-group-text w-25">Name</span>
                    <input type="text" class="form-control" name="name" placeholder="Enter new port name">
                </div>
                <button type="submit" name="action" value="delete_port" class="btn btn-danger w-100 mb-2">Delete</button>
                <button type="submit" name="action" value="update_port" class="btn btn-warning w-100 mb-2">Update</button>
                <div class="" style="height: 37.6px">
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../../../templates/footer.jsp"/>
</body>
</html>
