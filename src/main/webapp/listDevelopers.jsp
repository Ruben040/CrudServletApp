
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>All-Developers</title>
</head>
<body>
<a href="/Crud_war/allDevelopers">List Developers</a>
<a href="/Crud_war/addDeveloper">Add Developer</a>
<a href="/Crud_war/addLanguage">Add Language</a>
<br>
<br>
<table>
    <thead>
    <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Stack</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c
    <c:forEach items="${requestScope.developers}" var="lang">

        <tr>
            <td><c:out value="${lang.getId()}" /></td>
            <td><c:out value="${lang.getFirstName()}" /></td>
            <td><c:out value="${lang.getLastName()}" /></td>
            <td><c:out value="${lang.getAge()}" /></td>
            <td>${language.get(lang.getId())}</td>
            <td>
                <form method="post" action="<c:url value="/deleteDeveloper" />">
                    <input type="text" hidden name="id" value="${lang.getId()}">
                    <input type="submit" name="delete" value="Delete">
                </form>
            </td>
            <td>
                <form method="get" action="<c:url value="/updateDeveloper"/>">
                    <input type="number" hidden name="id" value="${lang.getId()}">
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
