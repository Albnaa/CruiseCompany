<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .form-control {
            height: 41.6px;
        }
    </style>
</head>
<body>

<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="../../../templates/adminNavbar.jsp"/>
</c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <jsp:include page="../../../templates/userNavbar.jsp"/>
</c:if>

<div class="container">
    <div class="card text-center my-3">
        <div class="card-header">
            Users Profile
        </div>
        <div class="card-body">
            <h4 class="card-title">${requestScope.user.login}</h4>
        </div>
    </div>
    <div class="row">
        <div class="col col-6">
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">Id</li>
                <li class="list-group-item w-50">${requestScope.user.id}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">Login</li>
                <li class="list-group-item w-50">${requestScope.user.login}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">Email</li>
                <li class="list-group-item w-50">${requestScope.user.email}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">First Name</li>
                <li class="list-group-item w-50">${requestScope.user.firstName}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">Last Name</li>
                <li class="list-group-item w-50">${requestScope.user.lastName}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">Role</li>
                <li class="list-group-item w-50">${requestScope.user.role}</li>
            </ul>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item w-50">Balance</li>
                <li class="list-group-item w-50">${requestScope.user.balance}</li>
            </ul>
            <div class="list-group list-group-horizontal">
                <button class="btn list-group-item list-group-item-action list-group-item-info"
                        type="button" data-bs-toggle="collapse" data-bs-target="#updateForm"
                        aria-expanded="false" aria-controls="collapseWidthExample">
                    Update
                </button>
                <a href="controller?action=delete_user&userId=${requestScope.user.id}"
                   class="btn list-group-item list-group-item-action list-group-item-danger">
                    Delete
                </a>
            </div>
        </div>
        <div class="col col-6 collapse collapse" id="updateForm">
            <form method="POST" action="controller">
                <input type="hidden" name="action" value="update_user">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Id" name="id"
                           value="${requestScope.user.id}" aria-describedby="id" readonly>
                    <span class="input-group-text w-25" id="id">Id</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Username" name="login"
                           value="${requestScope.user.login}" aria-describedby="login">
                    <span class="input-group-text w-25" id="login">Login</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="email@example.com" name="email"
                           value="${requestScope.user.email}" aria-describedby="email">
                    <span class="input-group-text w-25" id="email">Email</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Name" name="firstName"
                           value="${requestScope.user.firstName}" aria-describedby="first-name">
                    <span class="input-group-text w-25" id="first-name">First Name</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Surname" name="lastName"
                           value="${requestScope.user.lastName}" aria-describedby="last-name">
                    <span class="input-group-text w-25" id="last-name">Last Name</span>
                </div>
                <div class="input-group">
                    <select class="form-select" aria-describedby="role" name="role" style="height: 41.6px">
                        <c:choose>
                            <c:when test="${requestScope.user.role == 'ADMIN'}">
                                <option selected>ADMIN</option>
                                <option value="USER">USER</option>
                            </c:when>
                            <c:otherwise>
                                <option selected>USER</option>
                                <option value="ADMIN">ADMIN</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                    <span class="input-group-text w-25" id="role">Role</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Balance" name="balance"
                           value="${requestScope.user.balance}" aria-describedby="balance" disabled>
                    <span class="input-group-text w-25" id="balance">Balance</span>
                </div>
                <button type="submit" class="btn btn-primary w-100" style="height: 41.6px">Confirm</button>
            </form>
        </div>
    </div>
</div>


<jsp:include page="../../../templates/footer.jsp"/>
</body>
</html>
