<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>User's</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container p">
    <h2>User's</h2>
    <form method="get" action="controller" role="form">
        <input type="hidden" id="searchAction" name="action" value="search user by name">
        <input type="text" name="userName" id="userName" class="form-control"
               placeholder="Type the name or lastname of the User">
        <input type="submit" value="Search">
        <br>
        <br>
    </form>
    ${users}
    <form method="get" action="controller" role="form">
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
                    </tr>
                    </thead>
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
</body>
</html>
