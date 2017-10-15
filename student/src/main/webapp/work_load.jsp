<%--
  Created by IntelliJ IDEA.
  User: Demon
  Date: 014 14.10.17
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Контрольная работа</title>

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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
    <link rel="stylesheet" href="fluid-gallery.css">
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
                <li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>
                <li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>
                <li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>
                <li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>
                <li class="control-work-sidebar-circle"><i class="fa fa-3x fa-circle-thin "></i></li>
                <li><button class="btn btn-default control-work-sidebar-button" type="submit">Button</button></li>
            </ul>
        </div>
        <c:set var="work" value="${work}" />
        <c:set var="subjectName" value="${work.test.template.subject.name}"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row panel panel-default margin-bottom-null">

                <div class="col-xs-12 col-sm-12 text-center">
                    <h1 class="control-work-title"><c:out value="${work.test.template.theme}"/></h1>
                </div>

            </div>
            <div class="row placeholders ">
                <div class="text-left">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/student/testlist">Список контрольных работ</a></li>
                        <li class="breadcrumb-item active">Контрольная работа (<c:out value="${subjectName}"/>
                            <c:out value="${work.test.startDate}"/>)
                            <c:out value="${work.status.name}"/>
                        </li>
                    </ol>
                </div>

                <div class="panel panel-default">
                    <div class="panel-body text-left">
                        <c:forEach items="${questions}" var="question">
                            <p><c:out value="${question}"/></p>
                        </c:forEach>
                    </div>
                <div class="panel panel-default text-left">
                    <div class="panel-body tz-gallery">
                        <div class="row ifaceforworkcheck-row">
                            <ul class="horizontal-slide">
                                <c:forEach items="${files}" var="file">
                                    <li>
                                        <a class="lightbox" href="<c:out value="${file}"/>">
                                            <img src="<c:out value="${file}" />" style="height: 100px; width: 100px; display: block;"  alt="">
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <%--Блок с кнопкой отправки на статусе Новая--%>
                <c:if test="${work.status.name.toString() eq 'Новая'}">
                    <div class="panel panel-default text-left">
                        <div class="panel-body">
                            <button class="btn btn-success" type="submit">Сдать работу</button>
                        </div>
                    </div>
                </c:if>

                    <%--Блок с комментарием и оценкой после проверки или перепроверки--%>
                <c:if test="${(work.status.name.toString() eq 'Проверена') || (work.status.name.toString() eq 'Перепроверена') }">
                <div class="panel panel-default text-left">
                    <div class="panel-body tz-gallery">
                        <div class="row ifaceforworkcheck-row col-sm-12">
                            <ul class="horizontal-slide">
                                <c:forEach items="${teacher_files}" var="file">
                                    <li>
                                        <a class="lightbox" href="<c:out value="${file}"/>">
                                            <img src="<c:out value="${file}" />" style="height: 100px; width: 100px; display: block;"  alt="">
                                        </a>
                                    </li>
                                </c:forEach>
<%--
                                <c:forEach items="${teacher_files}" var="file">
                                    <li>
                                        <img alt="+" data-src="holder.js/100%x180" style="height: 180px; width: 100%; display: block;" src="<c:out value="${file}"/>" data-holder-rendered="true">
                                    </li>
                                </c:forEach>
--%>

                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-sm-8">
                                <textarea class="form-control" rows="11"></textarea>
                            </div>
                            <div class="col-sm-4">
                                <ul class="ifaceforworkcheck-stars">
                                    <c:forEach var="item" begin="1" end="${work.mark}">
                                    <li>
                                        <i class="fa fa-3x  fa-star"></i>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>
                <%--Блок с комментарием  кнопкой отправки на перепроверку--%>
                <c:if test="${work.status.name.toString() eq 'Проверена'}">
                    <div class="panel panel-default text-left">
                        <div class="panel-body">
                            <form>
                                <div class="form-group">
                                    <label for="textarea">Причина перепроверки:</label>
                                    <textarea id="textarea" class="form-control" rows="11"></textarea>
                                </div>
                                <button type="submit" class="btn btn-danger">На перепроверку</button>
                            </form>
                        </div>
                    </div>
                </c:if>


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

<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js"></script>
<script>
    baguetteBox.run('.tz-gallery');
</script>
</body>
</html>