<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 18.10.17
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Произошла ошибка</title>
</head>
<body>
    <p>Что-то пошло не так</p>
    <p><c:out value="${errorText}"/></p>
</body>
</html>
