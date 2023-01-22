<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User's</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="templates/navbar.jsp"/>
<div class="container">
    <h2 class="text-center p-3">Manage users</h2>
    <form method="get" action="controller" role="form">
        <input type="hidden" id="searchAction" name="action" value="search_user">
        <div class="row">
            <div class="col">
                <select id="select-sort" name="sort" class="form-select">
                    <option value="" ${empty sessionScope.sort ? 'selected' : ''}>Sort by</option>
                    <option value="user.id" ${sessionScope.sort == 'user.id' ? 'selected' : ''}>Id</option>
                    <option value="user.login" ${sessionScope.sort == 'user.login' ? 'selected' : ''}>Login</option>
                    <option value="user.email" ${sessionScope.sort == 'user.email' ? 'selected' : ''}>Email</option>
                    <option value="user.first_name" ${sessionScope.sort == 'user.first_name' ? 'selected' : ''}>First name</option>
                    <option value="user.last_name" ${sessionScope.sort == 'user.last_name' ? 'selected' : ''}>Last name</option>
                    <option value="user.role_id" ${sessionScope.sort == 'user.role_id' ? 'selected' : ''}>Role</option>
                    <option value="user.balance" ${sessionScope.sort == 'user.balance' ? 'selected' : ''}>Balance</option>
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
                <select class="form-select" name="roleF">
                    <option value="" ${empty sessionScope.roleF ? 'selected' : ''}>Filter by role</option>
                    <option value="ADMIN" ${sessionScope.roleF == 'ADMIN'? 'selected' : ''}>ADMIN</option>
                    <option value="USER" ${sessionScope.roleF == 'USER' ? 'selected' : ''}>USER</option>
                </select>
            </div>
            <div class="col">
                <div class="input-group">
                    <span class="input-group-text">Rows per page:</span>
                    <input type="number" id="rows" name="rows" class="form-control" min="1" value="${requestScope.rows}">
                </div>
            </div>
            <div class="col btn-group" role="group">
                <button type="button" class="btn btn-secondary w-100"
                        onclick="location.href = 'controller?action=search_user';">Reset</button>
                <button type="submit" class="btn btn-primary w-100">Submit</button>
            </div>
        </div>
    </form>
    <c:if test="${not empty requestScope.message}">
        <div class="alert-success">
                ${requestScope.message}
        </div>
    </c:if>
    <form method="get" action="controller" id="userForm" role="form">
        <%--        <input type="hidden" id="updateById" name="action" value="updateById">--%>
        <%--        <input type="hidden" id="action" name="action">--%>
        <c:choose>
            <c:when test="${not empty requestScope.users}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Role</th>
                        <th>Balance</th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach var="user" items="${requestScope.users}">
                        <tr class="${user}">
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role}</td>
                            <td>${user.balance}</td>
                            <td><a class="btn btn-primary p-0 " style="width: 60px"
                                   href="controller?action=view_user&userId=${user.id}">Profile</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    Cannot found user's with this search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>


<c:set var="link" value="controller?action=search_user&sort=${sessionScope.sort}&order=${sessionScope.order}&roleF=${sessionScope.roleF}" scope="request"/>
<h6>${link}</h6>

<jsp:include page="templates/pagination.jsp"/>


<jsp:include page="templates/footer.jsp"/>
</body>
</html>
