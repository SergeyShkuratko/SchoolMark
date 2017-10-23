<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Создание контрольной работы</title>
    <script src="js/question-form.js" type="text/javascript"></script>
    <!--<link href="css/form.css" rel="stylesheet" type="text/css">-->
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap-responsive.css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            document.getElementById('testDate').valueAsDate = new Date();
            document.getElementById('deadlineDate').valueAsDate = new Date();
        });
    </script>
    <style>
        .form-actions {
            background-color: #f5f5f5;
            padding: 19px 20px 20px;
            margin-top: 20px;
            margin-bottom: 20px;
            border-top: 1px solid #e5e5e5;
        }

        /*Встроеные в инпут иконки*/
        .input-icon {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 2;
            display: block;
            width: 34px;
            height: 34px;
            line-height: 34px;
            text-align: center;
            pointer-events: none;
            top: 25px;
        }
        .form-icon {
            position: relative;
        }
        .form-icon input {
            padding-left: 42.5px;
        }

    </style>

    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
<%@include file="/mystatic/pageheader.jsp" %>

<form action="<c:url value='/test'/>" method="post">
    <div class="container">
        <h2>Заполнение информации о контрольной работе</h2>
        <span class="form-inline">
        <h3>Тема контрольной работы <button type="button" class="btn btn-default">Загрузить из шаблона</button></h3>

    </span>

        <input type="text" class="input-xxlarge form-control" value="Контрольная на тему..." placeholder="Контрольная на тему..." name="testTheme"/>

        <p/>
        <div>
            <div class="form-group">
                <label for="subject">Предмет</label>
                <div class="controls">
                    <select class="form-control input-xxlarge" id="subject" name="subject">
                        <option>Математика</option>
                        <option>Физика</option>
                        <option>Литература</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="classNum">Класс</label>
                <input type="text" class="form-control input-xxlarge" id="classNum" placeholder="11" value="11" name="classNum">
            </div>
            <div class="form-group">
                <label for="className">Буква</label>
                <select class="form-control input-xxlarge" id="className" name="className">
                    <option>"А"</option>
                    <option>"Б"</option>
                    <option>"В"</option>
                </select>
            </div>
            <div class="form-group">
                <label for="difficulty">Сложность</label>
                <select class="form-control input-xxlarge" id="difficulty" name="difficulty">
                    <option>Легкая</option>
                    <option>Средняя</option>
                    <option>Сложная</option>
                </select>
            </div>
            <div class="form-group form-icon">
                <label for="testDate">Дата контрольной работы</label>
                <input type="date" class="form-control input-xxlarge" id="testDate" name="testDate">
                <i class="glyphicon glyphicon-calendar input-icon" aria-hidden="true"></i>
            </div>
            <div class="form-group form-icon">
                <label for="deadlineDate">Крайний срок проверки</label>
                <input type="date" class="form-control input-xxlarge" id="deadlineDate" name="deadlineDate">
                <i class="glyphicon glyphicon-calendar input-icon" aria-hidden="true"></i>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-default ">Создать контрольную работу</button>
            <button type="submit" class="btn btn-primary ">Перейти к вводу вопросов</button>
            <button type="button" class="btn btn-default ">Вернуться без сохранения</button>
        </div>
    </div>
</form>
<%@include file="/mystatic/pagefooter.jsp" %>
</body>
</html>