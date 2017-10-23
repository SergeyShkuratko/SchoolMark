<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Произошла ошибка</title>
</head>
<body>
    <p>Что-то пошло не так</p>
    <p><c:out value="${errorText}"/></p>
</body>
</html>
