<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Кабинет директора</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../js/collapse.js"></script>
    <script type="text/javascript" src="../js/cabinet.js"></script>
</head>
<body>

<div class="container-fluid">

    <div class="container">
        <div class="panel-group table-bordered">
            <div class="panel-heading">
                <a href='#add${school.id}'>
                    Добавить школу
                </a>
            </div>
            <c:forEach var="school" items="${schools}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a data-toggle="collapse" href='#school${school.id}'>
                            ${school.name}
                        </a>
                    </div>

                    <div id='school${school.id}' class="panel-collapse collapse">
                        <div class="panel panel-default">
                            <div id='div-classes${school.id}' class="panel-collapse collapse">
                                <a href='${pageContext.request.contextPath}/admin/class?city=${school.cityId}&school=${school.id}'>Добавить класс</a>
                                <ul id='classes${school.id}'></ul>
                            </div>
                            <div class="panel-heading">
                                <a id='a-classes${school.id}' onclick='loadClasses(${school.id})' href='#div-classes${school.getId()}'>
                                    Классы
                                </a>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div id='div-teachers${school.id}' class="panel-collapse collapse">
                                <a href="${pageContext.request.contextPath}/admin/registrationlink?role=teacher&school=${school.id}">Регистрация учителей</a>
                                <ul id='teachers${school.id}'></ul>
                            </div>
                            <div class="panel-heading">
                                <a id='#a-teachers${school.id}' onclick='loadTeachers(${school.id})' href='#teachers${school.id}'>
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
</body>
</html>
