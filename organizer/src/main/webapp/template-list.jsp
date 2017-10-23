<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/js/question-form.js" type="text/javascript"></script>
    <!--<link href="css/form.css" rel="stylesheet" type="text/css">-->
    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/bootstrap-responsive.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>

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
</head>

<body>

<%int rownum = 0; %>

<form action="#" method="post">
    <div class="container">
        <h2>Список созданных шаблонов</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>Название шаблона</th>
            <th>Предмет</th>
            <th>Класс</th>
            <th>Дата создания</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${templates}" var="template">
            <tr>
                <td><%=++rownum%></td>
                <td><c:out value="${template.topic}"/></td>
                <td><c:out value="${template.getSubject().name}"/></td>
                <td><c:out value="${template.classNum}"/></td>
                <td><c:out value="${template.creationDate}"/></td>
                <td><button type="submit" class="btn btn-link" name="templateId" value="${template.id}"/>Выбрать</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <div class="form-actions">
            <%--<a href="/test">--%>
                <button type="button" class="btn btn-primary " onclick="history.back();">Отмена</button>
            <%--</a>--%>
        </div>
    </div>
</form>

</body>
</html>
