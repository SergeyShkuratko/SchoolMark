<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 12.10.2017
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <title>Controller</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/dashboard.css" rel="stylesheet">
    <link href="${context}/css/ratingbar.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row ifaceforworkcheck">
        <%--<div class="col-sm-3 col-md-2 sidebar control-work-sidebar">--%>

        <%--<jsp:include page="/justMenu.jsp"/>--%>
        <%@ include file="justMenu.jsp" %>
        <%--<div class="panel panel-default control-work-sidebar-photo">--%>
        <%--<div class="panel-body">--%>

        <%--</div>--%>
        <%--</div>--%>
        <%--<ul class="nav nav-sidebar control-work-sidebar-number">--%>
        <%--<li>--%>
        <%--<button onclick="window.location.href='/SM/justMenu'"--%>
        <%--class="btn btn-default control-work-sidebar-button">Some--%>
        <%--other(just menu)--%>
        <%--</button>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<button class="btn btn-default control-work-sidebar-button"--%>
        <%--onclick="window.location.href='/SM/start'" type="submit">На--%>
        <%--проверку (<%=((List) request.getAttribute("tests")).size()%>)--%>
        <%--</button>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>--%>
        <%--</li>--%>
        <%--<li class="control-work-sidebar-circle"><i class="fa fa-3x fa-circle-thin "></i></li>--%>
        <%--<li>--%>
        <%--<button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</div>--%>


        <div class="col-sm-3 col-md-3 col-md-offset-2 ifaceforworkcheck-column">
            <ul class="nav nav-sidebar control-work-sidebar-number">

                <c:forEach items="#{tests}" var="test">
                    <c:out value="<li>
                    <button testId=\"${test.id}\" class=\"btn btn-success control-work-sidebar-button test\" type=\"submit\">${test.classNum} кл. ${test.subjectName} ${test.deadLine}</button>
                </li>" escapeXml="false"/>
                </c:forEach>
            </ul>
        </div>
        <div id="works-list-container" class="col-sm-3 col-md-3 ifaceforworkcheck-column">
            <ul class="nav nav-sidebar control-work-sidebar-number works-list">
            </ul>
        </div>
        <div class="col-sm-4 col-md-4 ifaceforworkcheck-column">
            <div class="panel panel-default">
                <div class="panel-body description">
                </div>
            </div>
            <div class="row ifaceforworkcheck-row work-image-container">
                <ul class="horizontal-slide work-image">
                </ul>
            </div>

            <div class="modal fade" id="showImage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" style="width: 745px;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <img src="" alt="" style="width: 650px; height: 650px;">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <textarea class="form-control" rows="5" id="comment"
                                      placeholder="Добавьте комментарии по работе здесь..."></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div data-role="ratingbar" data-steps="100" style="font-size: 24px">
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                    </ul>
                </div>
                <br/>
            </div>

            <%--<div class="row ifaceforworkcheck-row work-image-container">--%>
                <%--<ul class="horizontal-slide result-image">--%>
                <%--</ul>--%>
            <%--</div>--%>

            <%--<div class="row">--%>
                <%--<input id="fileupload" type="file" name="files[]" data-url="/SM/start" multiple>--%>
                <%--<button class="btn btn-default control-work-sidebar-button" type="submit">Load images</button>--%>
            <%--</div>--%>

            <div class="row">
                <button class="btn btn-default control-work-sidebar-button send-result" type="submit">Send result</button>
            </div>


        </div>
    </div>
</div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${context}/js/jquery-3.2.1.min.js"></script>
<script src="${context}/js/ratingbar.js"></script>
<script src="${context}/js/controller.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="${context}/js/vendor/jquery.ui.widget.js"></script>
<script src="${context}/js/jquery.iframe-transport.js"></script>
<script src="${context}/js/jquery.fileupload.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $('#fileupload').fileupload({
            dataType: 'json',
            done: function (e, data) {
                $.each(data.result.files, function (index, file) {
                    $('<p/>').text(file.name).appendTo(document.body);
                });
            }
        });
    });
</script>
</body>
</html>
