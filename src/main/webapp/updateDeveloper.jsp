<%--
  Created by IntelliJ IDEA.
  User: ruben040
  Date: 14.01.2022
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="<c:url value='/updateDeveloper'/>">

    <label>First Name: <input type="text" minlength="3" name="firstName" value="${requestScope.dev.getFirstName()}"/></label>
    <br>
    <br>
    <label>Last Name: <input type="text" minlength="3" name="lastName" value="${requestScope.dev.getLastName()}"/></label>
    <br>
    <br>
    <label>Age: <input type="number" min="0" minlength="1"  name="age" value="${requestScope.dev.getAge()}"/></label>
    <br>
    <br>
    <input type="submit" name="Update!"><br>
</form>
</body>
</html>
