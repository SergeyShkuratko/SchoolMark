<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Кабинет директора</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/collapse.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/collapse.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cabinet.js"></script>
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
                <div id='school<c:out value="${school.getId()}"/>' class="panel-collapse collapse">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a id='a-classes<c:out value="${school.getId()}"/>' onclick='loadClasses(<c:out value="${school.getId()}"/>)' href='#div-classes<c:out value="${school.getId()}"/>'>
                                Классы
                            </a>
                        </div>
                        <div id='div-classes<c:out value="${school.getId()}"/>' class="panel-collapse collapse">
                            <ul id='classes<c:out value="${school.getId()}"/>'></ul>
                            <a href="${pageContext.request.contextPath}/admin/class">Добавить класс</a>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a id='#a-teachers<c:out value="${school.getId()}"/>' onclick='loadTeachers(<c:out value="${school.getId()}"/>)' href='#teachers<c:out value="${school.getId()}"/>'>
                                Учителя
                            </a>
                        </div>
                        <div id='div-teachers<c:out value="${school.getId()}"/>' class="panel-collapse collapse">
                            <ul id='teachers<c:out value="${school.getId()}"/>'></ul>
                            <a>Добавить учителя</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
