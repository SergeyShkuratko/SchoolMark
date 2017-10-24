<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../js/auth-validate.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <h3>${title}</h3>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <form id="validate-form" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/class" method="post">
                <input type="hidden" name="school" value='<c:out value="${school.id}"/>'/>
                <div class="form-group">
                    <label for="region">Регион:</label>
                    <input id="region" class="form-control" type="text" value='<c:out value="${school.regionName}"/>' disabled/>
                </div>
                <div class="form-group">
                    <label for="city">Город:</label>
                    <input id="city" class="form-control" type="text" value='<c:out value="${school.cityName}"/>' disabled/>
                </div>
                <div class="form-group">
                    <label for="school">Школа:</label>
                    <input id="school" class="form-control" type="text" value='${school.name}/>' disabled/>
                </div>
                <c:if test="${role == student}">
                    <div class="form-group">
                        <label for="school-class">Класс:</label>
                        <input id="school-class" class="form-control" type="text" value="${schoolclass.name}" disabled/>
                    </div>
                </c:if>
                <div class="form-group has-feedback">
                    <label for="lastname">Фамилия:</label>
                    <input id="lastname" placeholder="Фамилия" class="form-control" type="text" name="lastname" maxlength="100" required/>
                </div>
                <div class="error-text"><span></span></div>
                <div class="form-group has-feedback">
                    <label for="firstname">Имя:</label>
                    <input id="firstname" placeholder="Имя" class="form-control" type="text" name="firstname" maxlength="100" required/>
                </div>
                <div class="error-text"><span></span></div>
                <div class="form-group has-feedback">
                    <label for="patronymic">Отчество:</label>
                    <input id="patronymic" placeholder="Отчество" class="form-control" type="text" name="patronymic" maxlength="100" required/>
                </div>
                <div class="error-text"><span></span></div>
                <c:if test="${role == teacher}">
                    <div class="form-group has-feedback">
                        <label for="minclass">Преподавание в классах с:</label>
                        <input id="minclass" placeholder="1" class="form-control" type="number" min="1" max="11" required/>
                    </div>
                    <div class="error-text"><span></span></div>
                    <div class="form-group has-feedback">
                        <label for="maxclass">по:</label>
                        <input id="maxclass" placeholder="11" class="form-control" type="number" min="1" max="11" required/>
                    </div>
                    <div class="error-text"><span></span></div>
                    <div><a href="#">Перейти к вводу предметов</a></div>
                </c:if>
            </form>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12">
        <button type="button" id="login" class="btn btn-labeled btn-success" onclick="validateAndSubmit()">Добавить класс</button>
        <!--<button type="button" class="btn btn-labeled btn-danger">
            <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Выход</button>
        -->
    </div>
</div>
</body>
</html>
