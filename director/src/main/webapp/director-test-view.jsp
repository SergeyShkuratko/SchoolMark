<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Просмотр информации о контрольной</title>
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
    <div class="row mt-3">
        <a class="col-md-auto ml-auto" href="<c:url value='/director-test-list'/> ">К списку тестов</a>
    </div>

    <div class="row border mt-3">
        <div class="container-fluid">
            <nav class="nav nav-tabs" id="myTab" role="tablist">
                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                   aria-controls="nav-home" aria-expanded="true">Описание работы</a>
                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
                   aria-controls="nav-profile">Критерии оценки</a>
            </nav>
            <div class="tab-content m-3" id="nav-tabContent">
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <c:forEach var="testVariant" items="${testAndWorkInfo.testVariants}">
                        <p>
                        <b>Вариант: </b><c:out value="${testVariant.variant}"/> <br/>
                        <c:forEach var="question" items="${testVariant.questions}" varStatus="loop">
                            <p>
                                <b>Вопрос ${loop.index + 1}: </b><c:out value="${question.question}"/> <br/>
                                <b>Ответ: </b><c:out value="${question.answer}"/>
                            </p>
                        </c:forEach>
                        </p>
                    </c:forEach>
                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <c:forEach var="testVariant" items="${testAndWorkInfo.testVariants}">
                        <p>
                        <b>Вариант: </b><c:out value="${testVariant.variant}"/> <br/>
                        <c:forEach var="question" items="${testVariant.questions}" varStatus="loop">
                            <p>
                                <b>Для вопроса ${loop.index + 1}: </b> <br/>
                            <ul>
                                <c:forEach var="criterionInfo" items="${question.criterionList}">
                                    <li><c:out value="${criterionInfo.criterion}"/></li>
                                </c:forEach>
                            </ul>
                            </p>
                        </c:forEach>
                        </p>
                    </c:forEach>
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
            <c:forEach var="work" items="${testAndWorkInfo.workList}">
                <tr>
                    <td>
                        <c:out value="${work.student}"/>
                    </td>
                    <td>
                        <c:out value="${work.mark}"/>
                    </td>
                    <td>
                        <c:out value="${work.wasAppeal ? 'была' : 'не было'}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
