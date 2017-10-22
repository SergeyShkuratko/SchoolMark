<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title>Ссылка на регистрацию</title>
</head>
<body>
    <div class="container">
        <h3>${pageHeader}</h3>
        <div class="row">
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/class" method="post">
                <div class="form-group">
                    <label for="region">Регион:</label>
                    <input id="region" class="form-control" type="text" name="region" value="${school.region}"disabled />
                </div>
                <div class="form-group">
                    <label for="city">Город:</label>
                    <input id="city" class="form-control" type="text" name="city" value="${school.city}" disabled />
                </div>
                <div class="form-group">
                    <label for="school">Город:</label>
                    <input id="school" class="form-control" type="text" name="school" value="${school.name}" disabled />
                </div>
                <div class="form-group">
                    <span>Ссылка для регистрации <a href="${registrationLink}">${registrationLink}</a></span>
                </div>
            </form>

        </div>
    </div>
</body>
</html>
