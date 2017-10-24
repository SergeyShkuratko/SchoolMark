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
        <th>Id</th>
        <th>Номер региона</th>
        <th>Название города</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Id</th>
        <th>Номер региона</th>
        <th>Название города</th>
    </tr>
    </tfoot>
    <tbody>
    ${table}
    </tbody>
</table>
<div class="panel panel-default" style="margin: 5px;">
    <div class="panel-body">
        <div class="dropdown" style="float: right;">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                ${dropdownTitle}
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                ${regionslist}
            </ul>
        </div>
    </div>
</div>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#appendCityModal">
    Добавить новый
</button>
<button
        type="button"
        class="btn btn-danger btn-lg"
        data-role="delete-table-row"
        data-action="/SM/admin/cities/remove"
>
    Удалить строку
</button>
<jsp:include page="_inputmodal.jsp"></jsp:include>