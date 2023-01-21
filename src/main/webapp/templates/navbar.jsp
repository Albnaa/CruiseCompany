<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg bg-info">
    <div class="container-fluid">
        <a class="navbar-brand" href="catalog.jsp">Cruise Company</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav"
                aria-controls="mainNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="catalog.jsp">Catalog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">My tickets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active">Profile</a>
                </li>

                <c:if test="${sessionScope.role == 'ADMIN'}">
                
                <li class="nav-item">
                    <a class="nav-link active" href="users.jsp">Manage Users</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Manage cruise
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Ships</a></li>
                        <li><a class="dropdown-item" href="#">Ports</a></li>
                        <li><a class="dropdown-item" href="#">Routes</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Tickets</a></li>
                    </ul>
                </li>
                </c:if>
            </ul>
            <ul class="navbar-nav justify-content-end">
                <li class="nav-item">
                    <a class="nav-link active">Balance ${sessionScope.user.balance}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="controller?action=sign out">Sign out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
