<%--
  Created by IntelliJ IDEA.
  User: ruben040
  Date: 16.01.2022
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<c:url value="/addLanguage"/>">
    <label>Language: <input type="text" minlength="1" name="language"></label>
    <br>
    <br>
    <button type="submit">Create!</button>
</form>
</body>
</html>
