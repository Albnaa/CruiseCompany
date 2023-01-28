<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="templates/navbar.jsp"/>
<div class="container">
    <h2 class="text-center p-3">Ticket#${requestScope.ticket.id}</h2>
    <div class="row justify-content-center">
        <div class="col-6 text-center">
            <form method="post" action="controller">
                <input type="hidden" name="ticketId" value="${requestScope.ticket.id}">
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">User id</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">First name</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Last name</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Ship id</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Ship name</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Route id</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Route name</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Cruise start</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Passengers</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Price</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="input-group mb-2">
                    <label class="input-group-text w-25">Status</label>
                    <input type="text" class="form-control w-25" disabled>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-secondary w-100">View documents</button>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-warning w-100">Update</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
