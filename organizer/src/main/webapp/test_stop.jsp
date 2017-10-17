<%--
  Created by IntelliJ IDEA.
  classes.User: Sergey
  Date: 09.10.2017
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Проведение контрольной работы</title>
    <c:set var="context" value="${pageContext.request.contextPath}"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- DataTable -->
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

    <!-- maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css-->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">

    <!-- <link rel="stylesheet" href="static/dashboard.css" type="text/css"> -->
    <script src="static/script.js"></script>

    <meta http-equiv="Cache-Control" content="no-cache"/>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="jumbotron">
            <h1>Контрольная работа завершена!</h1>
            <p>Данные отправлены на проверку случайным учителям. Узнать информацию о проверке работ Вы можете
            в "Личном кабинете".</p>
        </div>
    </div>
</div>
</body>
</html>
