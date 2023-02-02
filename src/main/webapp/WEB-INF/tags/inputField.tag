<%@ attribute name="fieldName" required="true" description="The name of the form field" %>
<%@ attribute name="actionName" required="true" description="The action of the form field" %>
<%@ attribute name="label" required="true" description="The label for the form field" %>
<%@ attribute name="width" required="true" description="25-50-75-100" %>
<%@ attribute name="type" required="true" description="Type of the form field" %>
<%@ attribute name="required" required="true" description="Type of the form field" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>

<div class="input-group">
    <span class="input-group-text w-${width}">${label}</span>
    <input class="form-control ${fn:contains(sessionScope.error, actionName.concat('.').concat(fieldName)) ? 'is-invalid' : ''}"
           type="${type}" name="${fieldName}" ${required}>
    <c:if test="${fn:contains(sessionScope.error, '')}">
        <div class="invalid-feedback">
            <fmt:message key="${sessionScope.error}" />
        </div>
    </c:if>
</div>