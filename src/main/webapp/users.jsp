<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h2>User's</h2>
    <form method="get" action="controller" role="form">
        <input type="hidden" id="searchAction" name="action" value="search user by name">
        <div class="row">
            <div class="col-4">
                <input type="text" name="userName" id="userName" class="form-control"
                       placeholder="Type the name or lastname of the User">
            </div>
            <div class="col-1">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
            </div>
            <div class="col-1">
                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Sort by
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="controller?action=search_user&sort=user.id&order=desc"><i
                                class="bi bi-arrow-down"></i> Id</a></li>
                        <li><a class="dropdown-item" href="controller?action=search_user&sort=user.id&order=asc"><i
                                class="bi bi-arrow-up"></i> Id</a></li>
                        <li><a class="dropdown-item" href="controller?action=search_user&sort=user.login&order=desc"><i
                                class="bi bi-arrow-down"></i> Login</a></li>
                        <li><a class="dropdown-item" href="controller?action=search_user&sort=user.login&order=asc"><i
                                class="bi bi-arrow-up"></i> Login</a></li>
                        <li><a class="dropdown-item" href="controller?action=search_user&sort=user.role_id&order=desc"><i
                                class="bi bi-arrow-down"></i> Role</a></li>
                        <li><a class="dropdown-item"
                               href="controller?action=search_user&sort=user.role_id&order=asc"><i
                                class="bi bi-arrow-up"></i> Role</a></li>
                        <li><a class="dropdown-item" href="controller?action=search_user&sort=user.balance&order=desc"><i
                                class="bi bi-arrow-down"></i> Balance</a></li>
                        <li><a class="dropdown-item"
                               href="controller?action=search_user&sort=user.balance&order=asc"><i
                                class="bi bi-arrow-up"></i> Balance</a></li>
                    </ul>
                </div>
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

<c:set var="link" value="controller?action=search_user&sort=user.id&order=desc" scope="request"/>

<jsp:include page="templates/pagination.jsp"/>

<jsp:include page="templates/footer.jsp"/>
</body>
</html>
