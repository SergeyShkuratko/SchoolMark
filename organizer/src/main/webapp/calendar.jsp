<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 12.10.2017
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Календарь контрольных работ</title>
    <script>

        function newCell(title) {
            var cell = new Object();
            cell.title = title;
            cell.subjects = new Array();
            cell.addSubject = function (subject) {
                this.subjects.push(subject);
            }
            cell.getSubject = function (key) {
                return this.subjects[key];
            }
            return cell;
        }

        function showCell(cell) {
            if (document.myDiv == null) {
                document.myDiv = document.createElement('div');
            }
            while (document.myDiv.firstElementChild != null) document.myDiv.removeChild(document.myDiv.firstElementChild);

            document.myDiv.style = "z-index:2; position:absolute; top: 10%; left: 40%";
            var iframe = document.createElement('iframe');
            iframe.style.width = '300';
            iframe.style.height = '400';
            document.myDiv.appendChild(iframe);
            document.body.appendChild(document.myDiv);

            var src = "<html><head><title>" + cell.title + "</title></head><body bgcolor='#fff5ee'><div align='right'><input type='image' value='close' src='${pageContext.request.contextPath}/organizer/calendar/images/close.png' width='50' onClick='while (window.parent.document.myDiv.firstElementChild!=null)window.parent.document.myDiv.removeChild(window.parent.document.myDiv.firstElementChild);'/></div>"
                + "<div align='center'>";
            for (var key in cell.subjects) {
                src += cell.getSubject(key) + "<br>";
            }
            src += "</div></body></html>"
            iframe.srcdoc = src;

        }

        document.cells = new Object();
        <c:forEach items="${calendar}" var="item">
        document.cells["${item.getDate().toString()}"] = newCell("${item.getDate().toString()}");
        <c:forEach items="${item.getSubjects()}" var="subject">
        document.cells["${item.getDate().toString()}"].addSubject("${subject}");
        </c:forEach>
        </c:forEach>
    </script>
</head>
<form method="post" action="${pageContext.request.contextPath}/organizer/calendar" id="mainForm">
    <input type="hidden" id="command" name="command" value=""/>
    <input type="hidden" name="beginData" value="${beginData}"/>
    <table cellspacing="5" border="0" align="center">
        <tr>
            <td></td>
            <td>
                <input type="image" src="${pageContext.request.contextPath}/organizer/calendar/images/navigate-left.png" width="70" alt="<"
                       onclick="document.getElementById('command').value='timeBack';this.form.submit();"/>
            </td>
            <td colspan="3" align="center"><c:out value="${monthName}"/></td>
            <td>
                <input type="image" src="${pageContext.request.contextPath}/organizer/calendar/images/navigate-right.png" width="70" alt=">"
                       onclick="document.getElementById('command').value='timeForward';this.form.submit();"/>
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

</html>
