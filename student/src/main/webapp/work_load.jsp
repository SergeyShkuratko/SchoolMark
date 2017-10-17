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

    <style>

        .dropzone {
            display: flex;
            justify-content: center;
            align-content: space-between;
            align-items: center;
            flex-direction: column;
        }
        #drop{
            width:100px;
            height:100px;
            padding:5px;
            border-style:dashed;
            border-color:#ccc;
            border-width:2px;
            background-color:#fdfbfb;
            color:#aaa;
        }

        #drop a{
            color:#aaa;
        }

        #drop a:hover{
            color:#777;
        }

        /*body {*/
            /*padding-top:40px;*/
        /*}*/

    </style>
</head>

<body>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<div class="container-fluid">
    <div class="row">

        <%@include file="/mystatic/justMenu.jsp" %>

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
                        <p class="bg-primary">Файлы с ответами ученика:</p>
                        <div class="row ifaceforworkcheck-row">
                             <div class="col-sm-9">
                            <ul class="horizontal-slide text-left">
                                <c:forEach items="${files}" var="file">
                                    <li>
                                        <div style="position: relative; left: 0; top: 0;">
                                            <a class="lightbox" href="<c:out value="${file}"/>">
                                                <img src="${context}/<c:out value="${file}" />" style="height: 100px; width: 100px; display: block;"  alt="">
                                            </a>
                                            <c:if test="${work.status.name.toString() eq 'Новая'}">
                                                <form id="form_del_photo" method="post" action="${context}/workload">
                                                    <input type="hidden" name="command" value="del_photo">
                                                    <input type="hidden" name="file" value="<c:out value="${file}"/>">
                                                </form>
                                                <i class="glyphicon glyphicon-remove" aria-hidden="true"
                                                   onclick="document.getElementById('form_del_photo').submit()"
                                                   style="position: absolute; margin-top: 0px; margin-right: 0px;cursor:pointer;" width="20" height="20">

                                                </i>
                                            </c:if>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            </div>
                            <div class="col-sm-3">
                            <c:if test="${work.status.name.toString() eq 'Новая'}">
                                <div class="panel panel-default text-left">
                                    <div class="panel-body">
                                        <form id="drop" class="dropzone"  action="${context}/workload" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="command" value="send_photo">
                                            <label class="btn" for="my-file-selector">
                                                <i class="fa fa fa-plus-square-o fa-5x" aria-hidden="true"></i>

                                                <input id="my-file-selector" type="file" accept="image/jpeg,image/png"
                                                       name="data" style="display:none"
                                                       onchange="document.getElementById('drop').submit()">
                                           </label>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <%--Блок с кнопкой отправки на статусе Новая--%>
                <c:if test="${work.status.name.toString() eq 'Новая'}">
                    <div class="panel panel-default text-left">
                        <div class="panel-body">
                            <form action="${context}/workload" method="post">
                            <button class="btn btn-success" name="command" value="send_work" type="submit" formaction="${context}/workload">
                                Сдать работу</button>
                            </form>
                        </div>
                    </div>
                </c:if>

                    <%--Блок с комментарием и оценкой после проверки или перепроверки--%>
                <c:if test="${(work.status.name.toString() eq 'Проверена') || (work.status.name.toString() eq 'Перепроверена') }">
                <div class="panel panel-default text-left">
                    <div class="panel-body tz-gallery">
                        <p class="bg-primary">Файлы учителя:</p>
                        <div class="row ifaceforworkcheck-row col-sm-12">
                            <ul class="horizontal-slide">
                                <c:forEach items="${teacher_files}" var="file">
                                    <li>
                                        <a class="lightbox" href="<c:out value="${file}"/>">
                                            <img src="<c:out value="${file}" />" style="height: 100px; width: 100px; display: block;"  alt="">
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-sm-8">
                                <div class="form-group">
                                    <label for="teacher_comment">Комментарии учителя:</label>
                                    <textarea id="teacher_comment" class="form-control" rows="11"></textarea>
                                </div>
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
                            <form action="${context}/workload" method="post">
                                <div class="form-group">
                                    <label for="student_comment">Причина перепроверки:</label>
                                    <textarea id="student_comment" class="form-control" rows="11"></textarea>
                                </div>
                                <button type="submit" name="command" value="recheck" class="btn btn-danger">
                                    На перепроверку
                                </button>
                            </form>
                        </div>
                    </div>
                </c:if>
                <%--Блок с комментарием и оценкой после перепроверки--%>
                <c:if test="${(work.status.name.toString() eq 'Перепроверена')}">
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
                                </ul>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <div class="form-group">
                                        <label for="teacher2_comment">Комментарии учителя:</label>
                                        <textarea id="teacher2_comment" class="form-control" rows="11"></textarea>
                                    </div>
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