<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<nav class="navbar navbar-expand-lg bg-info">
    <div class="container-fluid">
        <a class="navbar-brand" href="controller?action=manage_catalog&rows=6">Cruise Company</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav"
                aria-controls="mainNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="controller?action=manage_catalog&rows=6">
                        <fmt:message key="navbar.catalog"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="controller?action=manage_user_tickets&userF=${sessionScope.user.id}"><fmt:message key="navbar.dropdown.tickets"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="controller?action=view_self_profile"><fmt:message key="navbar.profile"/></a>
                </li>

                <c:if test="${sessionScope.role == 'ADMIN'}">
                
                <li class="nav-item">
                    <a class="nav-link active" href="controller?action=search_user"><fmt:message key="navbar.users"/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="navbar.dropdown.default"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="controller?action=manage_ship"><fmt:message key="navbar.dropdown.ships"/></a></li>
                        <li><a class="dropdown-item" href="controller?action=manage_port"><fmt:message key="navbar.dropdown.ports"/></a></li>
                        <li><a class="dropdown-item" href="controller?action=manage_route"><fmt:message key="navbar.dropdown.routes"/></a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="controller?action=manage_tickets"><fmt:message key="navbar.dropdown.tickets"/></a></li>
                    </ul>
                </li>
                </c:if>
            </ul>
            <ul class="navbar-nav justify-content-end">
                <li class="nav-item">
                    <form method="POST" action="controller?action=set_locale">
                        <label>
                            <select class="form-select" name="locale" onchange='submit();'>
                                <option value="en" ${sessionScope.locale == 'en' ? 'selected' : ''}>EN</option>
                                <option value="ua" ${sessionScope.locale == 'ua' ? 'selected' : ''}>UA</option>
                            </select>
                        </label>
                    </form>
                </li>
                <li class="nav-item">
                    <button class="btn btn-outline-primary mx-2" data-bs-toggle="modal" data-bs-target="#topUpModal">
                        <fmt:message key="navbar.balance"/> ${sessionScope.user.balance}</button>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="controller?action=sign_out"><fmt:message key="navbar.signOut"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<jsp:include page="/templates/topUpModal.jsp"/>
