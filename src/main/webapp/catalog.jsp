<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="templates/navbar.jsp"/>
  <h1>Welcome</h1>
  ${user}, hello!
  <a href="controller?action=sign out">Logout</a>
  <jsp:include page="templates/footer.jsp"/>
</body>
</html>
