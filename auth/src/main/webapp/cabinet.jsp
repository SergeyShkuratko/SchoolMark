<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Кабинет администратора</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../js/collapse.js"></script>
    <script type="text/javascript" src="../js/validate.js"></script>
    <script type="text/javascript" src="../js/cabinet.js"></script>
    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
<%@include file="/mystatic/pageheader.jsp" %>
<div class="container-fluid">

    <div class="container">
        <div class="panel-group table-bordered">
            <div class="panel-heading">
                <a href='${pageContext.request.contextPath}/admin/school?cityId=1'>
                    Добавить школу
                </a>
            </div>
            <c:forEach var="school" items="${schools}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a data-toggle="collapse" href='#school<c:out value="${school.id}"/>'>
                            <c:out value="${school.name}"/>
                        </a>
                    </div>

                    <div id='school<c:out value="${school.id}"/>' class="panel-collapse collapse">
                        <div class="panel panel-default">
                            <div id='div-classes<c:out value="${school.id}"/>' class="panel-collapse collapse">
                                <ul id='classes<c:out value="${school.id}"/>'></ul>
                                <a href='${pageContext.request.contextPath}/admin/class?city=<c:out value="${school.cityId}"/>&school=<c:out value="${school.id}"/>'>Добавить
                                    класс</a>
                            </div>
                            <div class="panel-heading">
                                <a id='a-classes<c:out value="${school.id}"/>'
                                   onclick='loadClasses(<c:out value="${school.id}"/>)'
                                   href='#div-classes<c:out value="${school.getId()}"/>'>
                                    Классы
                                </a>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div id='div-teachers<c:out value="${school.id}"/>' class="panel-collapse collapse">
                                <ul id='teachers<c:out value="${school.id}"/>'></ul>
                                <a>Добавить учителя</a>
                            </div>
                            <div class="panel-heading">
                                <a id='#a-teachers<c:out value="${school.id}"/>'
                                   onclick='loadTeachers(<c:out value="${school.id}"/>)'
                                   href='#teachers<c:out value="${school.id}"/>'>
                                    Учителя
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
<%@include file="/mystatic/pagefooter.jsp" %>
</body>
</html>
