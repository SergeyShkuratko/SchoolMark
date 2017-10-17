<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Кабинет директора</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/collapse.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
</head>
<body>

<div class="container-fluid">

    <div class="container">
        <div class="panel-group table-bordered">
        <c:forEach var="school" items="${schools}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a data-toggle="collapse" href='#school<c:out value="${school.getId()}"/>'>
                        <c:out value="${school.getName()}"/>
                    </a>
                </div>
                <div id='school<c:out value="${school.getId()}"/>' class="panel-collapse collapse in">
                    <!--
                    //TODO запилить отложенную инициализацию
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a data-toggle="collapse" href='#classes<out value="{school.getId()}"/>'>
                                Классы
                            </a>
                        </div>
                        <div id='classes<out value="{school.getId()}"/>' class="panel-collapse collapse in">
                                <ul>
                                    <forEach items="{school.getClasses()}" var="schoolClass">
                                        <li><out value="{schoolClass.getName()}"/></li>
                                    </forEach>
                                </ul>
                            </if>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a data-toggle="collapse" href='#teachers<out value="{school.getSchool().getId()}"/>'>
                                Учителя
                            </a>
                        </div>
                        <div id='teachers<out value="{school.getSchool().getId()}"/>' class="panel-collapse collapse in">
                            <if test="{!school.getTeachers().isEmpty()}">
                                <ul>
                                    <forEach items="{school.getTeachers()}" var="teacher">
                                        <li><out value="{teacher.getFullName()}"/></li>
                                    </forEach>
                                </ul>
                            </if>
                        </div>
                    </div>-->
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
