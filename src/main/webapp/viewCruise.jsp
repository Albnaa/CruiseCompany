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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="templates/navbar.jsp"/>
<div class="container">
    <h2 class="text-center p-3">${requestScope.ship.route.name}</h2>
    <div class="row">
        <div class="col">
            <div class="card mb-3">
                <img src="Img/Ship-2.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title text-center">Route</h5>
                    <ul class="list-group list-group-flush align-content-end">
                        <li class="list-group-item">Start date: ${requestScope.ship.route.startOfCruise}</li>
                        <li class="list-group-item">End date: ${requestScope.ship.route.endOfCruise}</li>
                        <li class="list-group-item">Duration: ${requestScope.ship.route.duration} days</li>
                        <li class="list-group-item">Ship name: ${requestScope.ship.name}</li>
                        <li class="list-group-item">Price: ${requestScope.ship.route.price}</li>
                    </ul>
                </div>
            </div>
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title text-center">Order now</h5>
                    <button class="btn btn-primary btn-lg w-50 p-2 m-2"
                            data-bs-toggle="modal" data-bs-target="#createModal">Order</button>
                </div>
            </div>
        </div>
        <div class="col-3">
            <ol class="list-group list-group-numbered">
                <c:forEach var="waypoint" items="${requestScope.ship.route.waypoints}">
                    <li class="list-group-item d-flex align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${waypoint.port.name}</div>
                                <h6><i class="bi bi-box-arrow-in-down-right"></i> Arrive date: ${waypoint.arriveTime}  </h6>
                                <h6><i class="bi bi-box-arrow-right"></i> Departure date: ${waypoint.departureTime}</h6>
                        </div>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>

<div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5 text-center" id="createModalLabel">Order cruise</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="controller">
                    <input type="hidden" name="action" value="create_ticket">
                    <div class="input-group mb-2">
                        <label class="input-group-text w-50">Passengers count</label>
                        <input type="number" class="form-control" name="passengersCount" min="1" required>
                    </div>
                    <div class="input-group mb-2">
                        <label class="input-group-text w-25">Documents</label>
                        <input type="file" class="form-control w-25" name="passengersCount" min="1" required>
                    </div>


                    <div class="row justify-content-center mt-3">
                        <div class="col">
                            <button type="button" class="btn btn-secondary w-100" data-bs-dismiss="modal">Close</button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary w-100">Order</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
