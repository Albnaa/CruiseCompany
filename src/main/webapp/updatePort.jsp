<%--
  Created by IntelliJ IDEA.
  User: OLEG
  Date: 24.01.2023
  Time: 03:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4 class="text-center">Update</h4>
<form method="post" action="controller">
    <input type="hidden" name="action" value="update_port">
    <div class="input-group mb-2">
        <span class="input-group-text w-25">Id</span>
        <input type="number" class="form-control" name="id" value="${}" readonly>
    </div>
    <div class="input-group mb-2">
        <span class="input-group-text w-25">Name</span>
        <input type="text" class="form-control" name="name" value="${}">
    </div>
    <button type="submit" class="btn btn-warning w-100 mb-2">Update</button>
    <button type="button" class="btn btn-danger w-100 mb-2" onclick="location.href = '';">Delete</button>
    <div class="" style="height: 37.6px">

    </div>
</form>
</body>
</html>
