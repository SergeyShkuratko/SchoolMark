<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Добавление нового класса</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <script type="text/javascript">
        function loadSchoolList() {
            var region = $('#region').value;
            var city = $('#city').value;
            if (region !== "" && city !== "") {

                $('#school').html('<option value="">Loading...</option>');


                $.ajax({
                    url: 'schools?city=' + city + '&region=' + region,
                    success: function (output) {
                        $('#school').html(output);
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(xhr.status + " " + thrownError);
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/class" method="post">
            <div class="form-group">
                <label for="region">Укажите регион:</label>
                <input id="region" placeholder="Регион" class="form-control" type="text" name="region" onchange="loadSchoolList()" required/>
            </div>
            <div class="form-group">
                <label for="city">Укажите город:</label>
                <input id="city" placeholder="Город" class="form-control" type="text" name="city" onchange="loadSchoolList()" required/>
            </div>
            <div class="form-group">
                <label for="school" class="col-sm-3 col-xs-3">Выберите школу из списка </label>
                <select id="school" class="col-sm-3 col-xs-3">
                </select>
            </div>
            <div class="form-group">
                <label for="school-class">Укажите класс:</label>
                <input id="school-class" placeholder="Класс" class="form-control" type="text" name="school-class" required/>
            </div>
            <div class="form-group">
                <label for="letter">Укажите букву класса:</label>
                <input id="letter" placeholder="Буква класса" class="form-control" type="text" name="letter" required/>
            </div>
        </form>

    </div>
</div>
</body>
</html>
