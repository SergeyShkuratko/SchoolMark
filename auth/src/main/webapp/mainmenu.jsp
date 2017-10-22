<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 17.10.2017
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
<div class="container-fluid">
    <div class="row ifaceforworkcheck">
        <%@include file="/mystatic/pageheader.jsp" %>

        <%@include file="/mystatic/pagefooter.jsp" %>
    </div>
</div>
</body>
</html>
