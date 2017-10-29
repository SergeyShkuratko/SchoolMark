<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidenav">

    <sec:authorize access="hasRole('STUDENT')">
        <a href="<c:url value='/testlist'/>">Текущие контрольные</a>
    </sec:authorize>

    <sec:authorize access="hasRole('TEACHER')">
        <a href="<c:url value='/organizer/calendar'/>">Расписание контрольных</a>
        <a href="<c:url value='/test'/>">Создание контрольных</a>
        <a href="<c:url value='/controller'/>">Проверка контрольных</a>
    </sec:authorize>

    <sec:authorize access="hasRole('DIRECTOR')">
        <a href="<c:url value='/director-test-list'/>">Статистика</a>
    </sec:authorize>

    <sec:authorize access="hasRole('ADMIN')">
        <a href="<c:url value='/admin/cabinet'/>">Кабинет администратора</a>
    </sec:authorize>

    <a href="<c:url value='/logout'/>">Выход</a>
</div>

<div class="main">