<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 13.10.2017
  Time: 8:42
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
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/justMenu'" type="submit">Some other(just menu)
                    </button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Button</button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/start'" type="submit">На проверке(X)
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
    </div>
</div>
</body>
</html>
