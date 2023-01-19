<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--1. Offset--%>
<%--2. rows--%>
<%--3. numOfPages--%>
<%--4. currPage--%>
<%--5. numOfRows--%>
curr page = ${requestScope.currPage}
offset = ${requestScope.offset}
numOfPages = ${requestScope.numOfPages}
rows = ${requestScope.rows}
numOfRows = ${requestScope.numOfRows}
<ul class="pagination justify-content-center align-content-center">
    <c:choose>
        <c:when test="${requestScope.currPage == 1}">
            <li class="page-item disabled">
                <a class="page-link" href="" aria-label="Previous">
                    <span>&laquo;</span>
                </a>
            </li>
        </c:when>
        <c:otherwise>
            <li class="page-item">
                <a class="page-link"
                   href="${requestScope.link}&rows=${requestScope.rows}&offset=${requestScope.offset - requestScope.rows}"
                   aria-label="Previous">
                    <span>&laquo;</span>
                </a>
            </li>
        </c:otherwise>
    </c:choose>

    <c:forEach var="page" begin="${requestScope.lowerBound}" end="${requestScope.upperBound}">
        <c:choose>
            <c:when test="${page == requestScope.currPage}">
                <li class="page-item">
                    <a class="page-link disabled" href="">${page}</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link"
                       href="${requestScope.link}&rows=${requestScope.rows}&offset=${requestScope.rows * page - requestScope.rows}">${page}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${requestScope.currPage == requestScope.numOfPages}">
            <li class="page-item disabled">
                <a class="page-link" href="">
                    <span>&raquo;</span>
                </a>
            </li>
        </c:when>
        <c:otherwise>
            <li class="page-item">
                <a class="page-link"
                   href="${requestScope.link}&rows=${requestScope.rows}&offset=${requestScope.offset + requestScope.rows}">
                    <span>&raquo;</span>
                </a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>

<%--1. Offset--%>
<%--2. rows--%>
<%--3. numOfPages--%>
<%--4. currPage--%>
<%--5. numOfRows--%>
