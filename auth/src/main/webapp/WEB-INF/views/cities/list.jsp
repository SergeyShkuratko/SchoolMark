<%--
  Created by IntelliJ IDEA.
  User: vidok
  Date: 21.10.17
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="example" class="display" width="100%" cellspacing="0">
    <thead>
    <tr>
        <th>Id@</th>
        <th>Номер региона@</th>
        <th>Название города@</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Id@</th>
        <th>Номер региона@</th>
        <th>Название города@</th>
    </tr>
    </tfoot>
    <tbody>
    ${table}
    </tbody>
</table>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#appendRegionModal">
    Добавить новый
</button>
<button type="button" class="btn btn-danger btn-lg" data-role="delete-table-row">
    Удалить строку
</button>
<jsp:include page="../_inputmodal.jsp"></jsp:include>