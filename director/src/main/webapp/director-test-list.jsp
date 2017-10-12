<%--
  Created by IntelliJ IDEA.
  classes.User: Sergey
  Date: 09.10.2017
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="form-check col-auto">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" value="" ${groupByOrganization ? 'checked' : ''}>
                Группировать по организации
            </label>
        </div>

        <div class="col-auto ml-auto">
            <input type="date" name="dateFrom">
            <input type="date" name="dateTo"/>
        </div>
    </div>

    <c:if test="${!groupByOrganization}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Дата</th>
                <th>Организатор</th>
                <th>Предмет</th>
                <th>Класс</th>
                <th>Средний балл</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="test" items="${tests}">
                <a href="/director-test-view.jsp">
                    <tr onclick="window.location.href='/director-test-view.jsp'">
                        <td>
                            <c:out value="${test.date}"/>
                        </td>
                        <td>
                            <c:out value="${test.organizer}"/>
                        </td>
                        <td>
                            <c:out value="${test.subject}"/>
                        </td>
                        <td>
                            <c:out value="${test.className}"/>
                        </td>
                        <td>
                            <c:out value="${test.averageMark}"/>
                        </td>
                    </tr>
                </a>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${groupByOrganization}">
        <div class="container">
            <h2>Collapsible Panel</h2>
            <p>Click on the collapsible panel to open and close it.</p>
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">Collapsible panel</a>
                        </h4>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse">
                        <div class="panel-body">Panel Body</div>
                        <div class="panel-footer">Panel Footer</div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
