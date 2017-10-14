<%--
  Created by IntelliJ IDEA.
  User: mars
  Date: 12.10.17
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Просмотр контрольной</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row border mt-3">
        <div class="container-fluid">
            <nav class="nav nav-tabs" id="myTab" role="tablist">
                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                   aria-controls="nav-home" aria-expanded="true">Описание работы</a>
                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
                   aria-controls="nav-profile">Критери оценки</a>
            </nav>
            <div class="tab-content m-3" id="nav-tabContent">
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    Текст с описанием заданий контрольной работы
                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    Текст с описанием критериев оценки контрольной работы
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Ученик</th>
                <th>Оценка</th>
                <th>Была ли переоценка</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Лебедев Артемий</td>
                <td>3</td>
                <td>не было</td>
            </tr>
            <tr>
                <td>Дуров Павел</td>
                <td>4</td>
                <td>была</td>
            </tr>
            <tr>
                <td>Касперский Евгений</td>
                <td>5</td>
                <td>не было</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
