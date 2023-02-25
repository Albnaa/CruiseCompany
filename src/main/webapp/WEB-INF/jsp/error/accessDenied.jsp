<%@ page isErrorPage="true"%>
<html>
<head>
    <title>403 Forbidden</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center mt-4">
            <img src="${pageContext.request.contextPath}/image/message-in-a-bottle.png" class="rounded" alt="bottle-image"/>
            <h1 class="mt-1">Oops! Access Denied</h1>
            <p class="lead">We're Sorry, You Don't Have Permission to Access This Page.</p>
            <button class="btn btn-outline-primary w-50" onclick="goBack()">Go Back</button>
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
