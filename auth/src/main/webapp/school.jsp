<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Редактирование школы</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script>
        function ValidateAndSubmitSchool() {
            var formValid = true;

            $('select').each(function () {
                var formGroup = $(this).parents('.form-group');
                if (this.value !== "0") {
                    formGroup.addClass('has-success').removeClass('has-error');
                } else {
                    formGroup.addClass('has-error').removeClass('has-success');
                    formValid = false;
                }
            });

            $('input').each(function () {
                var formGroup = $(this).parents('.form-group');
                if (this.checkValidity()) {
                    formGroup.addClass('has-success').removeClass('has-error');
                } else {
                    formGroup.addClass('has-error').removeClass('has-success');
                    formValid = false;
                }
            });
            if (formValid) {
                document.getElementById('validate-form').submit();
                document.getElementById('schoolTypeName').value =document.getElementById('schoolTypeName').textContent;
            }
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div style="text-align: center; font-size: 20; color: red"><c:out value="${message}"/></div>
            <div style="text-align: center; font-size: 18; color: black">
                <c:out value="${title}"/>
            </div>
            <form id="validate-form" class="form-horizontal" role="form"
                  action="${pageContext.request.contextPath}/admin/school" method="post">
                <input type="hidden" name="schoolId" value="${schoolId}"/>
                <input type="hidden" name="cityId" value="${cityId}"/>
                <input type="hidden" name="cityName" value="${cityName}"/>
                <input type="hidden" name="regionId" value="${regionId}"/>
                <input type="hidden" name="regionName" value="${regionName}"/>
                <input type="hidden" name="schoolTypeName" value=""/>
                <div class="form-group">
                    <label for="region">Регион:</label>
                    <input id="region" name="region" class="form-control" type="text" value='<c:out value="${regionName}"/>'
                           disabled/>
                </div>
                <div class="form-group">
                    <label for="city">Город:</label>
                    <input id="city" name="city" class="form-control" type="text" value='<c:out value="${cityName}"/>' disabled/>
                </div>
                <div class="form-group has-feedback">
                    <label for="schoolName">Укажите наименование школы:</label>
                    <input id="schoolName" placeholder="Наименование школы" class="form-control" type="text" name="schoolName"
                           value="${schoolName}" required/>
                </div>
                <div class="form-group has-feedback">
                    <label for="schoolType">Укажите тип школы:</label>
                    <select id="schoolType" name="schoolType" placeholder="Тип школы" class="form-control" required
                            checkVali>
                        <c:choose>
                            <c:when test="${schoolType}==null"><option value="0" selected>-</option></c:when>
                            <c:otherwise><option value="0">-</option></c:otherwise>
                        </c:choose>
                        <c:if test="${schoolType}!=null"><option value="0">-</option></c:if>
                        <c:forEach items="${schoolTypes}" var="curSchoolType">
                            <c:choose>
                                <c:when test="${curSchoolType.getId().equals(schoolType)}">
                                    <option value="${curSchoolType.getId()}" selected><c:out value="${curSchoolType.getTypeName()}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${curSchoolType.getId()}"><c:out value="${curSchoolType.getTypeName()}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12">
        <button type="button" id="login" class="btn btn-labeled btn-success" onclick="ValidateAndSubmitSchool()">
            Записать
        </button>
    </div>
</div>

</body>
</html>
