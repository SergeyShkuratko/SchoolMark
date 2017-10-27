<div class="sidenav">
    <c:if test="${sessionScope.userRole == 'student'}">
        <a href="<c:url value='/testlist'/>">Текущие контрольные</a>
    </c:if>

    <c:if test="${sessionScope.userRole == 'teacher'}">
        <a href="<c:url value='/organizer/calendar'/>">Расписание контрольных</a>
        <a href="<c:url value='/test'/>">Создание контрольных</a>
        <a href="<c:url value='/controller'/>">Проверка контрольных</a>
    </c:if>

    <c:if test="${sessionScope.userRole == 'director'}">
        <a href="<c:url value='/director-test-list'/>">Статистика</a>
    </c:if>

    <a href="<c:url value='/logout'/>">Выход</a>
</div>

<div class="main">