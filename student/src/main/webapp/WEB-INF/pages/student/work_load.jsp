<%--
  Created by IntelliJ IDEA.
  User: Demon
  Date: 014 14.10.17
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html lang="en">
<%@include file="_head.jsp" %>
<body>
<%@include file="_header.jsp" %>
<%--<div class="main">--%>
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
                <c:if test="${(work.status eq 'Новая')}">
                <li class="dropdown" id="variant_menu">
                    <a id="drop1" href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Выбор варианта
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" >
                        <c:forEach items="${variants}" var="variant">
                            <li><a href="#" target="_blank" onclick="saveVariant(${variant.id});return false">
                                <c:out value="${variant.name}"/>
                            </a></li>
                        </c:forEach>
                    </ul>
                </li>
                </c:if>
            </ol>
        </div>
        <script type="text/javascript">
            function saveVariant(variant)
            {
                var object =
                    {
                        'variant':variant,
                        'work':${work.workId},
                        'command':'setVariant'
                    }

                $.post('/SM/save_variant', object, function(data){
                    location.reload();
                });

            }
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
                        <div class="col-sm-10">
                            <div class="baguetteBoxOne gallery">
                                <c:forEach items="${files}" var="file" varStatus="forLoop">
                                    <div class="col-sm-4">
                                        <c:if test="${(work.status eq 'Новая') || (work.status eq 'Отклонена') }">
                                        <button type="button" class="close" aria-label="Close"
                                           data-fileid="<c:out value="${file.id}"/>">
                                           <span aria-hidden="true">×</span>
                                        </button>
                                        </c:if>
                                        <a href="<c:out value="${context}${file.file}"/>" title="Midnight City">
                                           <img src="${context}<c:out value="${file.file}"/>" alt="Midnight City">
                                       </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <c:if test="${(work.status eq 'Новая') || (work.status eq 'Отклонена')}">
                                <div class="panel panel-default text-left">
                                    <div class="panel-body">
                                        <form id="drop" class="dropzone" action="${context}/workload" method="post"
                                              enctype="multipart/form-data">
                                            <input type="hidden" name="workId" value="${work.workId}">
                                            <label class="btn" for="my-file-selector">
                                                <i class="fa fa fa-plus-square-o fa-5x" aria-hidden="true"></i>
                                                <input id="my-file-selector" type="file" accept="image/jpeg,image/png"
                                                       name="data" style="display:none">
                                                       <%--onchange="document.getElementById('drop').submit()">--%>
                                            </label>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div id="progress-wrp">
                <div class="progress-bar"></div>
                <div class="status">0%</div>
            </div>
            <%--Блок с кнопкой отправки на статусе Новая--%>
            <c:if test="${(work.status eq 'Новая') || (work.status eq 'Отклонена')}">
                <div class="panel panel-default text-left">
                    <div class="panel-body">
                        <form action="${context}/workload/sendwork" method="post">
                            <input type="hidden" name="workId" value="${work.workId}">
                            <button class="btn btn-success" name="command" value="send_work" type="submit"
                                    formaction="${context}/workload/sendwork">
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
                        <form action="${context}/workload/sendtorecheck" method="post">
                            <input type="hidden" name="workId" value="${work.workId}">
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
<%--</div>--%>
<%@include file="/mystatic/pagefooter.jsp" %>
<%@include file="_footer.jsp" %>
</body>
</html>