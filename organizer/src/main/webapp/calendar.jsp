<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 12.10.2017
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<html>
<head>
    <title>Календарь контрольных работ</title>
    <script>

        function changeMonth(delta) {
            var dateElement = document.getElementById('dayOfMonth');
            var date = new Date(dateElement.value);
            date.setMonth(date.getMonth() + delta);
            dateElement.value = ("0000" + date.getFullYear()).slice(-4) + "-" +
                ("00" + (date.getMonth() + 1)).slice(-2) + "-01";
        }

        function newCell(title) {
            var cell = new Object();
            cell.title = title;
            cell.subjects = new Array();
            cell.tests_id = new Array();
            cell.addTest = function (subject, test_id) {
                this.subjects.push(subject);
                this.tests_id.push(test_id);
            }
            cell.getSubject = function (key) {
                return this.subjects[key];
            }
            cell.getTest = function (key) {
                return this.tests_id[key];
            }
            return cell;
        }

        function newSrcBuilder(title) {
            var object = new Object();
            object.src = "<html><head><title>" + title + "</title></head>" +
                "<body bgcolor='#fff5ee'><div align='right' style='top:0'>" +
                "<input type='image' value='close' src='${pageContext.request.contextPath}/img/close.png' width='50' " +
                "onClick='  window.parent.document.myDiv.style.width=0;" +
                "           while(window.parent.document.myDiv.firstElementChild!=null)" +
                "               window.parent.document.myDiv.removeChild(window.parent.document.myDiv.firstElementChild);'>" +
                "</div><div align='center'>" + title + "<br><br>";
            object.addTest = function (subject, id) {
                object.src += "<a href='${pageContext.request.contextPath}/test-start?test_id=" + id + "' target='_top'>" + subject + "</a>" + "</br>";
            }
            object.getSrc = function () {
                return object.src + "<br><br>" +
                    "<a href='${pageContext.request.contextPath}/test' target='_top'>+Добавить контрольную работу</a>" +
                    "</div></body></html>";
            }
            return object;
        }

        function showCell(cell) {
            if (document.myDiv == null) {
                document.myDiv = document.createElement('div');
                document.body.appendChild(document.myDiv);
            }
            while (document.myDiv.firstElementChild != null) {
                document.myDiv.removeChild(document.myDiv.firstElementChild);
            }

            document.myDiv.style = "z-index:2; position:absolute; width: 40%; height: 80%; top: 10%; left: 30%";
            var iframe = document.createElement('iframe');
            iframe.style.width = "100%";
            iframe.style.height = "100%";
            document.myDiv.appendChild(iframe);

            var srcBuilder = newSrcBuilder(cell.title);
            for (var key in cell.subjects) {
                srcBuilder.addTest(cell.getSubject(key),cell.getTest(key));
            }
            iframe.srcdoc = srcBuilder.getSrc();
        }

        document.cells = new Object();
        <c:forEach items="${calendar}" var="item">
            document.cells["${item.getDate().toString()}"] = newCell("${item.getFormatDate()}");
            <c:forEach items="${item.getTests()}" var="test">
                document.cells["${item.getDate().toString()}"].addTest("${DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(test.getStartDate())}. ${test.getSubject()}",<c:out value="${test.getId()}"/>);
            </c:forEach>
        </c:forEach>
    </script>
    <style>td, th {
        padding: 2px !important;
    }</style>

    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
    <%@include file="/mystatic/pageheader.jsp" %>

<form method="get" action="${pageContext.request.contextPath}/organizer/calendar" id="mainForm">
    <input type="hidden" name="dayOfMonth" id="dayOfMonth" value="${beginData}"/>

    <table cellspacing="5" border="0" align="center">
        <tr>
            <td></td>
            <td>
                <input type="image" src="${pageContext.request.contextPath}/img/navigate-left.png" width="70" alt="<"
                       onclick="changeMonth(-1);this.form.submit();"/>
            </td>
            <td colspan="3" align="center"><c:out value="${monthName}"/></td>
            <td>
                <input type="image" src="${pageContext.request.contextPath}/img/navigate-right.png" width="70" alt=">"
                       onclick="changeMonth(1);this.form.submit();"/>
            </td>
            <td></td>
        </tr>
        <tr align="center">
            <td>ПН</td>
            <td>ВТ</td>
            <td>СР</td>
            <td>ЧТ</td>
            <td>ПТ</td>
            <td>СБ</td>
            <td>ВС</td>
        </tr>
        <tr>
            <c:forEach items="${calendar}" var="item">
            <td>
                <div style="width: 70;height: 60; vert-align: top; border: solid; border-width: 1; border-color: black; background-color: ${item.getColor()}"
                     onclick="showCell(document.cells['${item.getDate().toString()}'])">
                    <div style="text-align: right;"><c:out value="${item.getDay()}"/></div>
                    <div style="text-align: center;">
                        <c:out escapeXml="false" value="${item.getDisplayName()}"/>
                    </div>
                </div>
            </td>

                <c:if test="${item.isEOW()}">
            </tr>
            <tr>
                </c:if>
                </c:forEach>
            </tr>
        </table>
    </form>

    <%@include file="/mystatic/pagefooter.jsp" %>
</body>
</html>
