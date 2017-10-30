<%--
  Created by IntelliJ IDEA.
  User: vidok
  Date: 27.10.17
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<spring:url value="/resources/student/js/bootstrap.min.js"/>"></script>
<script src="<spring:url value="/resources/student/js/notify.min.js"/>"></script>
<script src="<spring:url value="/resources/student/js/student.script.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js"></script>
<script>
    if(baguetteBox.length) {
        baguetteBox.run('.baguetteBoxOne');
    }
</script>
