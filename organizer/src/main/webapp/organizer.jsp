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
    <!-- Custom styles for this template -->

    <link rel="stylesheet" href="static/dashboard.css" type="text/css">

</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar control-work-sidebar">
            <div class="panel panel-default control-work-sidebar-photo">
                <div class="panel-body">

                </div>
            </div>
            <ul class="nav nav-sidebar control-work-sidebar-number">
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
                <li class="control-work-sidebar-circle"><i class="fa fa-3x fa-circle-thin "></i></li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row panel panel-default margin-bottom-null">

                <div class="col-xs-18 col-sm-10 text-center">
                    <h1 class="control-work-title">Тема контрольной работы</h1>
                    <p class="control-work-relating-text">
                        Сопроводительный текст Сопроводительный текст
                    </p>
                    <p class="control-work-relating-text">
                        Сопроводительный текст Сопроводительный текст
                    </p>
                </div>

                <div class="col-xs-6 col-sm-2">
                    <div class="row">
                        <button type="button" class="btn btn-primary control-work-button">Старт</button>
                    </div>
                    <div class="row">
                        <button type="button" class="btn btn-primary control-work-button">Отправить</button>
                    </div>
                </div>
            </div>
            <div class="row placeholders">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="active">
                            <th>Статус</th>
                            <th>ФИО ученика</th>
                            <th>Присутствует</th>
                            <th>Загрузить</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="success">
                            <td>Принята</td>
                            <td><a href="#">Иванов Иван Иванович</a></td>
                            <td>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox">
                                    </label>
                                </div>
                            </td>
                            <td>
                                <i class="fa fa-3x  fa-download"></i>
                            </td>
                        </tr>
                        <tr class="warning">
                            <td>Загружена</td>
                            <td><a href="#">Петрович Иван Иванович</a></td>
                            <td>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox">
                                    </label>
                                </div>
                            </td>
                            <td>
                                <i class="fa fa-3x  fa-download"></i>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
