<%@ page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="error.403.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center mt-4">
            <img src="${pageContext.request.contextPath}/image/message-in-a-bottle.png" class="rounded" alt="bottle-image"/>
            <h1 class="mt-1"><fmt:message key="error.403.header"/></h1>
            <p class="lead"><fmt:message key="error.403.description"/></p>
            <button class="btn btn-outline-primary w-50" onclick="goBack()">
                <fmt:message key="common.button.goBack"/>
            </button>
        </div>

        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </div>
</div>
</body>
</html>
