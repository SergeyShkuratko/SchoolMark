<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 16.10.2017
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ошибочка</title>
</head>
<body>
<h1>Что-то пошло не так...</h1>
<c:out value="<p><a href='${pageContext.request.contextPath}'>Вернуться на главную страницу</a></p>" escapeXml="false"/>
<p>В системе сработал механизм нештатной ситуации. Бодрые программисты и их боевые подруги в обтягивающих майках словно
    спасатели уже бегут исправлять ситуацию...</p>
<c:forEach items="${errors}" var="err">
    <c:out value="<b>${err}</b><br>" escapeXml="false"/>
</c:forEach>
<p>
    <script type="text/javascript" src="http://bash.im/forweb/?u" charset="utf-8"></script>
</p>
</body>
</html>
