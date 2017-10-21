<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавление нового класса</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../js/validate.js"></script>
    <script type="text/javascript">
        function loadSchoolList() {
            var region = $('#region').val();
            var city = $('#city').val();
            if (region && city) {
                $('#school').html('<option value="">Loading...</option>');


                $.ajax({
                    url: 'schools?city=' + city + '&region=' + region,
                    success: function (output) {
                        $('#school').html(output);
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="container">
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
                <input id="school" class="form-control" type="text" value='<c:out value="${school.name}"/>' disabled/>
            </div>
            <div class="form-group has-feedback">
                <label for="school-class">Укажите класс:</label>
                <input id="school-class" placeholder="Класс" class="form-control" type="text" name="class_num" pattern="[1-9][1]?" required/>
            </div>
            <div class="form-group has-feedback">
                <label for="letter">Укажите букву класса:</label>
                <input id="letter" placeholder="Буква класса" class="form-control" type="text" name="letter" pattern="[А-Яа-я]" required/>
            </div>
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
