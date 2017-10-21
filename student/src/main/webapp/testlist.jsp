<%--
  Created by IntelliJ IDEA.
  User: Demon
  Date: 014 14.10.17
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Список контрольных</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<c:set var="context" value="${pageContext.request.contextPath}"/>
    <div class="container-fluid">
        <div class="row">
            <%--<div class="col-sm-3 col-md-2 sidebar control-work-sidebar">--%>
                <%--<div class="panel panel-default control-work-sidebar-photo">--%>
                    <%--<div class="panel-body">--%>

                    <%--</div>--%>
                <%--</div>--%>
                <%--<ul class="nav nav-sidebar control-work-sidebar-number">--%>
                    <%--<li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>--%>
                    <%--<li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>--%>
                    <%--<li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>--%>
                    <%--<li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>--%>
                    <%--<li class="control-work-sidebar-circle"><i class="fa fa-3x fa-circle-thin "></i></li>--%>
                    <%--<li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>--%>
                <%--</ul>--%>
            <%--</div>--%>
                <%@include file="/mystatic/justMenu.jsp" %>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="row panel panel-default margin-bottom-null">

                    <div class="col-xs-12 col-sm-12 text-center">
                        <h1 class="control-work-title text-danger">Выполняется контрольная работа</h1>
                    </div>

                </div>
                <div class="row placeholders">
                    <div class="table-responsive">
                        <table class="table table-bordered text-left">
                            <thead>
                            <tr class="active">
                                <th>Дата</th>
                                <th>Предмет</th>
                                <th>Тема контрольной работы</th>
                                <th>Оценка</th>
                                <th>Статус</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${works}" var="work">
                                <tr>
                                    <td><c:out value="${work.date}"/></td>
                                    <td><c:out value="${work.subject}"/></td>
                                    <td>
                                        <a href="${context}/workload?id=<c:out value="${work.work_id}"/>">
                                            <c:out value="${work.topic}"/>
                                        </a>
                                    </td>
                                    <td> <c:out value="${work.mark}"/> </td>
                                    <td> <c:out value="${work.status}"/> </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    </body>
</html>
