<%@ page import="classes.Student" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="classes.SchoolClass" %>
<%@ page import="classes.User" %>
<%@ page import="classes.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @ fake user with fake role ))
     */
//    final Role STUDENT_ROLE = new Role(1, 1);
//    final Role TEACHER_ROLE = new Role(2, 2);
//    final Role FAKE_ROLE = new Role(3, 3);
//    User user = new User(1, "login", LocalDate.now());
//    user.setRole(STUDENT_ROLE);

%>
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
                <%//="role is" + user.getRole().getName()%>
                <%if(true) { %>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/organizer/caledar'" type="submit">
                        Календарь организатора
                    </button>
                </li>
                <% } %>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/testlist'" type="submit">
                        Ученик
                    </button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/test'" type="submit">Проведение контрольной
                    </button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/controller'" type="submit">Проверка контрольной
                    </button>
                </li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button"
                            onclick="window.location.href='/SM/director-test-list'" type="submit">Директор
                    </button>
                </li>
                <li class="control-work-sidebar-circle"><i class="fa fa-3x fa-circle-thin "></i></li>
                <li>
                    <button class="btn btn-default control-work-sidebar-button" type="submit">Выход?</button>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
