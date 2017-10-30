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

    <meta http-equiv="Cache-Control" content="no-cache"/>
    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
<%@include file="/mystatic/pageheader.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="page-header">
            <h1>Контрольная работа</h1>
            <h3>По теме: ${test.topic}</h3>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">Описание работы:</div>
            <div class="panel-body">${test.description}</div>
        </div>
        <div class="panel panel-danger">
            <div class="panel-heading">Ошибка</div>
            <div class="panel-body">Контрольная работа уже проводится! Вернитесь назад и выберите другую работу.</div>
        </div>



    </div>
</div>
<%@include file="/mystatic/pagefooter.jsp" %>
</body>
</html>
