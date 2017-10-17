<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <title>Controller</title>
    <link href="${context}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/dashboard.css" rel="stylesheet">
    <link href="${context}/css/ratingbar.css" rel="stylesheet">
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
                    <%=Math.random()%>
                </li>
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
