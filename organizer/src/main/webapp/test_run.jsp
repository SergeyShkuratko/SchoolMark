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
        <div class="page-header">
            <h1>Контрольная работа</h1>
            <h3>По теме: ${test.topic}</h3>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">Описание работы:</div>
            <div class="panel-body">${test.description}</div>
        </div>


        <form action="${context}/stop" method="GET">
            <input type="hidden" name = "test_id" value="${test.id}" id="test_id">
            <button type="submit" class="btn btn-danger btn-block btn-lg">Завершить проведение контрольной работы</button>
            <br>
            <div class="table-responsive " >
                <table class="table table-bordered" id="school-class-table">
                    <thead>
                    <tr class="active">
                        <th>ФИО ученика</th>
                        <th>Присутствовал на уроке</th>
                        <th>Загрузил работу</th>
                        <th>Подтверждена учителем</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${works}" var="work">

                        <tr id="work_${work.id}">

                            <td>${work.studentFullname}</td>
                            <td>
                                <div class="checkbox" >
                                    <label>
                                        <input type="checkbox" name="${work.id}" checked>
                                    </label>
                                </div>
                            </td>
                            <td id="work_${work.id}_status">Нет</td>
                            <td id="work_${work.id}_confirm">Нет</td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </form>
    </div>
</div>
</body>
</html>
