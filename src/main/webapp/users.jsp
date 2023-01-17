<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User's</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="templates/navbar.jsp"/>
<div class="container">
    <h2>User's</h2>
    <form method="get" action="controller" role="form">
        <input type="hidden" id="searchAction" name="action" value="search user by name">
        <div class="row">
            <div class="col">
                <input type="text" name="userName" id="userName" class="form-control"
                       placeholder="Type the name or lastname of the User">
            </div>
            <div class="col">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
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

<jsp:include page="templates/footer.jsp"/>
</body>
</html>
