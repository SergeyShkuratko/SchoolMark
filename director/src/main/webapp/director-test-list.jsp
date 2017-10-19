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

    <style>
        #wrapper {
            padding-left: 250px;
            transition: all 0.4s ease 0s;
        }

        #sidebar-wrapper {
            margin-left: -250px;
            left: 250px;
            width: 250px;
            background: #000;
            position: fixed;
            height: 100%;
            overflow-y: auto;
            z-index: 1000;
            transition: all 0.4s ease 0s;
        }

        #page-content-wrapper {
            width: 100%;
        }

        .sidebar-nav {
            position: absolute;
            top: 0;
            width: 250px;
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .sidebar-nav li {
            line-height: 40px;
        }

        .sidebar-nav li a {
            color: #999999;
            display: block;
            text-decoration: none;

            padding-left: 20px;
        }

        .sidebar-nav li a:hover {
            color: #fff;
            background: rgba(255,255,255,0.2);
            text-decoration: none;
        }

        .sidebar-nav li a:active,
        .sidebar-nav li a:focus {
            text-decoration: none;
        }

        .sidebar-nav > .sidebar-brand {
            height: 65px;
            line-height: 25px;
            font-size: 18px;
        }

        .sidebar-nav > .sidebar-brand a {
            color: #999999;
        }

        .sidebar-nav > .sidebar-brand a:hover {
            color: #fff;
            background: none;
        }

        .content-header {
            height: 65px;
            line-height: 65px;
        }

        .content-header h1 {
            margin: 0;
            margin-left: 20px;
            line-height: 30px;
            display: inline-block;
        }

        #menu-toggle {
            display: none;
        }

        .inset {
            padding: 20px;
        }

        @media (max-width:767px) {

            #wrapper {
                padding-left: 0;
            }

            #sidebar-wrapper {
                left: 0;
            }

            #wrapper.active {
                position: relative;
                left: 250px;
            }

            #wrapper.active #sidebar-wrapper {
                left: 250px;
                width: 250px;
                transition: all 0.4s ease 0s;
            }

            #menu-toggle {
                display: inline-block;
            }

            .inset {
                padding: 15px;
            }

        }
    </style>
    <script type="text/javascript">
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("active");
        });
    </script>
</head>
<body>
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand"><a href="#">Сервис проверки контрольных</a>
            </li>
            <li><a href="/SM/organizer/calendar">Календарь организатора</a>
            </li>
            <li><a href="/SM/testlist">Ученик</a>
            </li>
            <li><a href="/SM/test">Проведение контрольной</a>
            </li>
            <li><a href="/SM/controller">Проверка контрольной</a>
            </li>
            <li><a href="/SM/director-test-list">Директор</a>
            </li>
            <%--<li><a href="#">Выход</a>
            </li>--%>
        </ul>
    </div>

    <!-- Page content -->
    <div id="page-content-wrapper">
        <div class="content-header">
            <h1>
                <a id="menu-toggle" href="#" class="btn btn-default"><i class="fa fa-list"></i></a>
                Ститистка выполнения контрольных работ
            </h1>
        </div>
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">
            <div class="row">
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
    </div>
</div>





<%--<div class="container-fluid">
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
</div>--%>
</body>
</html>
