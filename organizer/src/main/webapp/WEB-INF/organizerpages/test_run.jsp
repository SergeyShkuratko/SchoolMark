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
    <script src="${pageContext.request.contextPath}/static/script.js"></script>
    <script src="${pageContext.request.contextPath}/static/notify.min.js"></script>

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
        <div class="well">
            <label class="checkbox-inline"><input type="checkbox" name="autorefresh" value=""
                                                  checked>Автообновление</label>
            <button type="button" class="btn btn-primary btn-md" id="refresh">Обновить</button>
        </div>
        <form action="${context}/test-stop" method="GET">
            <input type="hidden" name="test_id" value="${test.id}" id="test_id">
            <button type="submit" class="btn btn-danger btn-block btn-lg">Завершить проведение контрольной работы
            </button>
        </form>
        <div class="table-responsive ">
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
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="${work.id}" checked>
                                </label>
                            </div>
                        </td>
                        <td id="work_${work.id}_status">Нет
                        </td>
                        <td id="work_${work.id}_confirm">Нет</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <!-- GALLERY HERE -->


                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="http://d1zqayhc1yz6oo.cloudfront.net/19aa47bb9d7d4e9969af72b449c9bca1.jpg"
                                     alt="Страница 1" style="width:100%;">
                            </div>

                            <div class="item">
                                <img src="http://d1zqayhc1yz6oo.cloudfront.net/19aa47bb9d7d4e9969af72b449c9bca1.jpg"
                                     alt="Страница 2" style="width:100%;">
                            </div>

                            <div class="item">
                                <img src="http://d1zqayhc1yz6oo.cloudfront.net/19aa47bb9d7d4e9969af72b449c9bca1.jpg"
                                     alt="Страница 3" style="width:100%;">
                            </div>
                        </div>

                        <!-- Left and right controls -->
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>


                    <!-- GALLERY END -->
                </div>
                <div class="modal-footer">
                    <button data-action-work="0" data-button-role="success" type="button" class="btn btn-success"
                            data-dismiss="modal">Подтвердить
                    </button>
                    <button data-action-work="0" data-button-role="decline" type="button" class="btn btn-danger"
                            data-dismiss="modal">Отклонить работу
                    </button>
                </div>
            </div>

        </div>
    </div>
    <%@include file="/mystatic/pagefooter.jsp" %>
</div>
</body>
</html>
