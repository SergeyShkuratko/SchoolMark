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
    <title>Статистика по контрольным работам</title>
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

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@include file="/mystatic/justMenu.jsp" %>
    <div class="row col-md-offset-2 col-md-10">
        <form class="row mb-3 mt-3 mr-3" method="get" action="<c:url value='/director-test-list'/>">
            <div class="col-md-auto form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox"
                           name="groupByOrganization" ${param['groupByOrganization'] == "on" ? 'checked' : ''}
                           onclick="this.form.submit()">
                    Группировать по организации
                </label>
            </div>

            <div class="input-group date col-md-2 ml-auto" data-provide="datepicker">
                <input type="text" class="form-control" placeholder="Дата с" name="dateFrom"
                       value="${param['dateFrom']}">
                <div class="input-group-addon">
                    <span class="fa fa-th"></span>
                </div>
            </div>
            <div class="input-group date col-md-2 ml-2" data-provide="datepicker">
                <input type="text" class="form-control" placeholder="Дата по" name="dateTo"
                       value="${param['dateTo']}">
                <div class="input-group-addon">
                    <span class="fa fa-th"></span>
                </div>
            </div>
            <input class="button col-md-auto ml-2" type="submit" value="Отфильтровать"/>
        </form>

        <c:if test="${param['groupByOrganization'] != 'on'}">
            <table class="table table-bordered table-hover">
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
                    <c:url value='/director-test-view' var="testViewLocation">
                        <c:param name="testId" value="${test.id}"/>
                    </c:url>

                    <tr onclick="window.location.href='<c:out value='${testViewLocation}'/>'">
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
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${param['groupByOrganization'] == 'on'}">
            <div id="accordion" role="tablist">
                <c:forEach var="teacherTestsEntry" items="${teacherTestsMap}" varStatus="loop">
                    <div class="card">
                        <div class="card-header" role="tab" id="heading${loop.index}">
                            <h5 class="mb-0">
                                <a data-toggle="collapse" href="#collapse${loop.index}"
                                   aria-expanded="true" aria-controls="collapse${loop.index}">
                                        ${teacherTestsEntry.getKey()}
                                </a>
                            </h5>
                        </div>

                        <div id="collapse${loop.index}" class="collapse" role="tabpanel"
                             aria-labelledby="heading${loop.index}">
                            <div class="card-body">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Дата</th>
                                        <th>Предмет</th>
                                        <th>Класс</th>
                                        <th>Средний балл</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="test" items="${teacherTestsEntry.getValue()}">
                                        <c:url value='/director-test-view' var="testViewLocation">
                                            <c:param name="testId" value="${test.id}"/>
                                        </c:url>

                                        <tr onclick="window.location.href='<c:out value='${testViewLocation}'/>'">
                                            <td>
                                                <c:out value="${test.date}"/>
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
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
