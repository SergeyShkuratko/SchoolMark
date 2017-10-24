<%--
  Created by IntelliJ IDEA.
  User: vidok
  Date: 21.10.17
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="_head.jsp"></jsp:include>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<!-- Begin page content -->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar control-work-sidebar">
            <jsp:include page="_menu.jsp"></jsp:include>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <%if(request.getAttribute("error")!=null){%>
            <div class="jumbotron" style="margin-top: 51px;">
                <div class="alert alert-danger center" role="alert">${error}</div>
            </div>
            <%}else{%>
            ${content}
            <%}%>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
