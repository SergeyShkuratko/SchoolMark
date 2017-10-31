<%--
  Created by IntelliJ IDEA.
  User: Demon
  Date: 014 14.10.17
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<%@include file="_head.jsp" %>
<body>
<%@include file="_header.jsp" %>
<%--<div class="main">--%>
    <div class="row panel panel-default margin-bottom-null">
        <div class="col-xs-12 col-sm-12 text-center">
            <h1 class="control-work-title text-danger">Выполняется контрольная работа</h1>
        </div>
    </div>
    <div class="row placeholders">
        <div class="table-responsive">
            <table class="table table-bordered text-left">
                <thead>
                <tr class="active">
                    <th>Дата</th>
                    <th>Предмет</th>
                    <th>Тема контрольной работы</th>
                    <th>Оценка</th>
                    <th>Статус</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${works}" var="work">
                    <tr>
                        <td><c:out value="${work.date}"/></td>
                        <td><c:out value="${work.subject}"/></td>
                        <td>
                            <a href="${context}/workload?id=<c:out value="${work.workId}"/>">
                                <c:out value="${work.topic}"/>
                            </a>
                        </td>
                        <td><c:out value="${work.mark}"/></td>
                        <td><c:out value="${work.status}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
<%--</div>--%>
<%@include file="/mystatic/pagefooter.jsp" %>
<%@include file="_footer.jsp" %>
</body>
</html>
