<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>

<header class="p-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="controller?action=manage_catalog&rows=6" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <img src="Img/logo.png" style="height: 40px" alt="logo">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="controller?action=manage_catalog&rows=6" class="nav-link px-2 link-dark"><fmt:message key="navbar.catalog"/></a></li>
                <li><a href="controller?action=manage_user_tickets&userF=${sessionScope.user.id}" class="nav-link px-2 link-dark"><fmt:message key="navbar.myTickets"/></a></li>
                <li><button type="button" class="btn btn-outline-dark border-0" data-bs-toggle="modal"
                            data-bs-target="#topUpModal" style="padding: 8px"><fmt:message key="navbar.balance"/>:
                            ${sessionScope.user.balance}</button>
                </li>
            </ul>

            <form method="post" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="controller">
                <input type="hidden" name="action" value="set_locale">
                <select class="form-select" name="locale" onchange='submit();'>
                    <option value="en" ${sessionScope.locale == 'en' ? 'selected' : ''}>EN</option>
                    <option value="uk" ${sessionScope.locale == 'uk' ? 'selected' : ''}>UK</option>
                </select>
            </form>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown"
                   aria-expanded="false">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="#085B7F"
                         class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                        <path fill-rule="evenodd"
                              d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
                    </svg>
                </a>
                <ul class="dropdown-menu text-small">

                    <li><a class="dropdown-item" href="controller?action=view_self_profile"><fmt:message key="navbar.profile"/></a></li>
                    <li><a class="dropdown-item" href="controller?action=manage_user_tickets&userF=${sessionScope.user.id}"><fmt:message key="navbar.myTickets"/></a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="controller?action=sign_out"><fmt:message key="navbar.signOut"/></a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<jsp:include page="/templates/topUpModal.jsp"/>