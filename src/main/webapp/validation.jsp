<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fd" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<div class="container">
    ${sessionScope.errors}
    <div class="row justify-content-center p-5">
        <div class="col-4 text-center">
            <form method="post" action="controller">

                <input type="hidden" name="action" value="image_action">
                <tag:inputField fieldName="id" entity="port" labelKey="table.id" width="25" type="number"/>
                <tag:inputField fieldName="name" entity="port" labelKey="table.name" width="25" type="text"/>
                <button class="btn btn-primary my-3" type="submit">Submit</button>
            </form>
        </div>
    </div>

    <div class="input-group">
        <span class="input-group-text w-25">Label</span>
        <input class="form-control ${not empty sessionScope.errors['error.port.name'] ? 'is-invalid' : ''}"
               type="text" name="gg"/>
        <c:forEach var="error" items="${sessionScope.errors['error.port.name']}">
            <div class="invalid-feedback pb-1">
                <fmt:message key="${error}"/>
            </div>
        </c:forEach>

    </div>
</div>

</body>
</html>
