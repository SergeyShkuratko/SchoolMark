<%@ page import="java.util.List" %><%--
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="css/docs/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <!-- Bootstrap core CSS -->
    <link href="css/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <link href="${context}/ratingbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="css/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="container-fluid">
    <div class="row ifaceforworkcheck">
        <div class="col-sm-3 col-md-2 sidebar control-work-sidebar">
            <div class="panel panel-default control-work-sidebar-photo">
                <div class="panel-body">

                </div>
            </div>
            <ul class="nav nav-sidebar control-work-sidebar-number">
                <li>
                    <button onclick="window.location.href='/SM/justMenu'"
                            class="btn btn-default control-work-sidebar-button">Some
                        other(just menu)
                    </button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/start'" type="submit">На
                        проверку (<%=((List) request.getAttribute("tests")).size()%>)
                    </button>
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
        <div class="col-sm-3 col-md-3 col-md-offset-2 ifaceforworkcheck-column">
            <ul class="nav nav-sidebar control-work-sidebar-number">

                <c:forEach items="#{tests}" var="test">
                    <c:out value="<li>
                    <button testId=\"${test.id}\" class=\"btn btn-success control-work-sidebar-button test\" type=\"submit\">${test.schoolClass.num} кл. ${test.template.subject.name} ${test.startDate}</button>
                </li>" escapeXml="false"/>
                </c:forEach>
            </ul>
        </div>
        <div id="works-list-container" class="col-sm-3 col-md-3 ifaceforworkcheck-column">
            <ul class="nav nav-sidebar control-work-sidebar-number works-list">
                <%--<li>--%>
                <%--<button class="btn btn-success control-work-sidebar-button" type="submit">Button</button>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<button class="btn btn-info control-work-sidebar-button" type="submit">Button</button>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<button class="btn btn-warning control-work-sidebar-button" type="submit">Button</button>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<button class="btn btn-danger control-work-sidebar-button" type="submit">Button</button>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>--%>
                <%--</li>--%>
            </ul>
        </div>
        <div class="col-sm-4 col-md-4 ifaceforworkcheck-column">
            <div class="panel panel-default">
                <div class="panel-body description">
                    <p></p>
                    <p>

                    </p>
                </div>
            </div>
            <div class="row ifaceforworkcheck-row work-image-container">
                <ul class="horizontal-slide work-image">

                    <%--<img alt="100%x180" data-src="holder.js/100%x180"--%>
                    <%--style="height: 180px; width: 100%; display: block;"--%>
                    <%--src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTcxIiBoZWlnaHQ9IjE4MCIgdmlld0JveD0iMCAwIDE3MSAxODAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MTgwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTVmMTFhMTcxNzYgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNWYxMWExNzE3NiI+PHJlY3Qgd2lkdGg9IjE3MSIgaGVpZ2h0PSIxODAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI2MSIgeT0iOTQuOCI+MTcxeDE4MDwvdGV4dD48L2c+PC9nPjwvc3ZnPg=="--%>
                    <%--data-holder-rendered="true">--%>
                    <%--</li>--%>
                </ul>
            </div>

            <div class="modal fade" id="showImage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <img src="" alt="">
                            <%--<span id="modal-myvalue"></span> <span id="modal-myvar"></span> <span id="modal-bb"></span>--%>
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



                <div data-role="ratingbar" data-steps="10" style="font-size: 24px">
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
                    </ul>
                </div><br/>


            </div>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="css/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="css/dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="css/assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="css/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="controller.js"></script>
<script src="ratingbar.js"></script>

</body>
</html>
