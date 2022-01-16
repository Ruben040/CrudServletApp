<%--
  Created by IntelliJ IDEA.
  User: ruben040
  Date: 14.01.2022
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<c:url value="/addDeveloper"/>">
    <label>First Name:<input type="text" minlength="3" name="firstName"></label>
    <br>
    <br>
    <label>Last Name: <input type="text" minlength="3" name="lastName"></label>
    <br>
    <br>
    <label>Age: <input type="number" min="0" minlength="1" name="age"></label>
    <br>
    <br>
    <c:forEach var="lang" items="${requestScope.listLang}">
        <label>
                ${lang}: <input type="checkbox" name="language" value="${lang}"> <br>
        </label>
    </c:forEach>
    <button type="submit">Create!</button>
</form>
</body>
</html>
