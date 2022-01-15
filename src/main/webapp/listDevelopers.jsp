
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<ul>--%>
<%-- <c:forEach var="developer" items="${list}">--%>
<%--     <li>${developer.getFirstName()}--%>
<%--        ${developer.getLastName()}--%>
<%--        ${developer.getAge()}</li>--%>
<%--     <form method="post" action="<c:url value="/Delete" />">--%>
<%--         <input type="number" hidden name="id" value="${developer.getId()}">--%>
<%--         <input type="submit" name="delete" value="Delete">--%>
<%--     </form>--%>

<%--     <form method="post" action="<c:url value="/Update" />">--%>
<%--         <input type="number" hidden name="id" value="${developer.getId()}">--%>
<%--         <input type="submit" name="Update" value="Update">--%>
<%--     </form>--%>
<%-- </c:forEach>--%>
<%--</ul>--%>

<table>
    <thead>
    <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.developers}" var="developer">
        <tr>
            <td><c:out value="${developer.getId()}" /></td>
            <td><c:out value="${developer.getFirstName()}" /></td>
            <td><c:out value="${developer.getLastName()}" /></td>
            <td><c:out value="${developer.getAge()}" /></td>
            <td>
                <form method="post" action="<c:url value="/deleteDeveloper" />">
                    <input type="text" hidden name="id" value="${developer.getId()}">
                    <input type="submit" name="delete" value="Delete">
                </form>
            </td>
            <td>
                <form method="get" action="<c:url value="/updateDeveloper"/>">
                    <input type="number" hidden name="id" value="${developer.getId()}">
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
