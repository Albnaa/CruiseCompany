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
<jsp:include page="../../../templates/navbar.jsp"/>

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
                <input type="hidden" name="action" value="update_self_profile">
                <input type="hidden" name="balance" value="${requestScope.user.balance}">
                <input type="hidden" name="role" value="${requestScope.user.role}">
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
