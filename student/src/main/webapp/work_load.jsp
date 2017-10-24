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

        #drop {
            width: 100px;
            height: 100px;
            padding: 5px;
            border-style: dashed;
            border-color: #ccc;
            border-width: 2px;
            background-color: #fdfbfb;
            color: #aaa;
        }

        #drop a {
            color: #aaa;
        }

        #drop a:hover {
            color: #777;
        }
    </style>

    <%@include file="/mystatic/menustyles.jsp" %>
</head>

<body>
<%@include file="/mystatic/pageheader.jsp" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="work" value="${work}"/>
<%--<c:set var="subjectName" value="${work.subject}"/>--%>

<div class="row panel panel-default margin-bottom-null">

    <div class="col-xs-12 col-sm-12 text-center">
        <h1 class="control-work-title"><c:out value="${work.topic}"/></h1>
    </div>

</div>
<div class="row placeholders ">
    <div class="text-left">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${context}/testlist">Список контрольных работ</a></li>
            <li class="breadcrumb-item active">Контрольная работа (<c:out value="${work.subject}"/>
                <c:out value="${work.date}"/>)
                <c:out value="${work.status}"/>
            </li>
            <li class="dropdown" id="variant_menu">
                <a id="drop1" href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Выбор варианта
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" >
                    <c:forEach items="${variants}" var="variant">
                        <li><a href="/error.jsp" target="_blank" onclick="saveVariant(${variant.id});return false">
                            <c:out value="${variant.name}"/>
                        </a></li>
                    </c:forEach>
                </ul>
            </li>
        </ol>
    </div>
    <script type="text/javascript">
        function saveVariant(variant)
        {
//            alert(variant)
            var object =
                {
                    'variant':variant,
                    'work':${work.workId},
                    'command':'setVariant'
                }

            $.post('/SM/workload', object, function(data){
                location.reload();
            });

        }

//        $("#variant_menu").onclick(function(event) {
//            // Предотвращаем обычную отправку формы
//            event.preventDefault();
//            $.post('ajax.php', {'login':$('#login').val(), 'password' : $('#password').val()},
//                function(data) {
//                    $('#result').html(data);
//                });
//        });
    </script>
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

                            <c:forEach items="${files}" var="file" varStatus="forLoop">
                                <li>
                                    <div style="position: relative; left: 0; top: 0;">
                                        <a class="lightbox" href="<c:out value="${file.file}"/>">
                                            <img src="${context}/<c:out value="${file.file}" />"
                                                 style="height: 100px; width: 100px; display: block;" alt="">
                                        </a>
                                        <c:if test="${work.status eq 'Новая'}">
                                            <form id="form_del_photo<c:out value="${forLoop.index}"/>" method="post"
                                                  action="${context}/workload">
                                                <input type="hidden" name="command" value="del_photo">
                                                <input type="hidden" name="file_id" value="<c:out value="${file.id}"/>">
                                                <input type="hidden" name="file" value="<c:out value="${file.file}"/>">
                                            </form>
                                            <i class="glyphicon glyphicon-remove" aria-hidden="true"
                                               onclick="document.getElementById('form_del_photo<c:out
                                                       value="${forLoop.index}"/>').submit()"
                                               style="position: absolute; margin-top: 0px; margin-right: 0px;cursor:pointer;"
                                               width="20" height="20">

                                            </i>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-sm-3">
                        <c:if test="${work.status eq 'Новая'}">
                            <div class="panel panel-default text-left">
                                <div class="panel-body">
                                    <form id="drop" class="dropzone" action="${context}/workload" method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="command" value="send_photo">
                                        <input type="hidden" name="workId" value="${work.workId}">
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
        <c:if test="${work.status eq 'Новая'}">
            <div class="panel panel-default text-left">
                <div class="panel-body">
                    <form action="${context}/workload" method="post">
                        <input type="hidden" name="workId" value="${work.workId}">
                        <button class="btn btn-success" name="command" value="send_work" type="submit"
                                formaction="${context}/workload">
                            Сдать работу
                        </button>
                    </form>

                </div>
            </div>
        </c:if>

        <%--Блок с комментарием и оценкой после проверки или перепроверки--%>
        <c:if test="${(work.status eq 'Проверена') || (work.status eq 'Перепроверена') }">
            <div class="panel panel-default text-left">
                <div class="panel-body tz-gallery">
                    <p class="bg-primary">Файлы учителя:</p>
                    <div class="row ifaceforworkcheck-row col-sm-12">
                        <ul class="horizontal-slide">
                            <c:forEach items="${teacher_files}" var="file">
                                <li>
                                    <a class="lightbox" href="<c:out value="${file}"/>">
                                        <img src="<c:out value="${file}" />"
                                             style="height: 100px; width: 100px; display: block;" alt="">
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
        <c:if test="${work.status eq 'Проверена'}">
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
        <c:if test="${(work.status eq 'Перепроверена')}">
            <div class="panel panel-default text-left">
                <div class="panel-body tz-gallery">
                    <div class="row ifaceforworkcheck-row col-sm-12">
                        <ul class="horizontal-slide">
                            <c:forEach items="${teacher_files}" var="file">
                                <li>
                                    <a class="lightbox" href="<c:out value="${file}"/>">
                                        <img src="<c:out value="${file}" />"
                                             style="height: 100px; width: 100px; display: block;" alt="">
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

<%@include file="/mystatic/pagefooter.jsp" %>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<scrip>
    $.("#id").click(function(){
        $.post("test.cgi", { name: "", time: "2pm" })
            .done(function(data) {
                alert("Data Loaded: " + data);
            });
    }
</scrip>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js"></script>
<script>
    baguetteBox.run('.tz-gallery');
</script>
</body>
</html>