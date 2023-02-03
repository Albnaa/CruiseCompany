<%@ attribute name="fieldName" required="true" description="The name of the form field" %>
<%@ attribute name="entity" required="true" description="The action of the form field" %>
<%@ attribute name="labelKey" required="true" description="The label for the form field" %>
<%@ attribute name="width" required="true" description="25-50-75-100" %>
<%@ attribute name="type" required="true" description="Type of the form field" %>
<%@ attribute name="value" required="false" description="Value of the form field" %>
<%@ attribute name="placeholder" required="false" description="Type of the form field" %>
<%@ attribute name="readonly" required="false" description="Type of the form field" %>
<%@ attribute name="required" required="false" description="Type of the form field" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale"/>

<div class="input-group">
    <span class="input-group-text w-${width}"><fmt:message key="${labelKey}"/></span>
    <input class="form-control
    ${not empty sessionScope.errors['error.'.concat(entity).concat('.').concat(fieldName)] ? 'is-invalid' : ''}"
           type="${type}" name="${fieldName}" value="${value}" placeholder="<fmt:message key="${placeholder}"/>"
           ${readonly} ${required}>
    <c:forEach var="error" items="${sessionScope.errors['error.'.concat(entity).concat('.').concat(fieldName)]}">
        <div class="invalid-feedback">
            <fmt:message key="${error}"/>
        </div>
    </c:forEach>
</div>