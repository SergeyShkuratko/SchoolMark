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
        <th>Название региона</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Id</th>
        <th>Номер региона</th>
        <th>Название региона</th>
    </tr>
    </tfoot>
    <tbody>
    ${table}
    <!--
    <tr>
        <td>Tiger Nixon</td>
        <td>System Architect</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Garrett Winters</td>
        <td>Accountant</td>
        <td>Tokyo</td>
    </tr>
    <tr>
        <td>Ashton Cox</td>
        <td>Junior Technical Author</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Cedric Kelly</td>
        <td>Senior Javascript Developer</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Airi Satou</td>
        <td>Accountant</td>
        <td>Tokyo</td>
    </tr>
    <tr>
        <td>Brielle Williamson</td>
        <td>Integration Specialist</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Herrod Chandler</td>
        <td>Sales Assistant</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Rhona Davidson</td>
        <td>Integration Specialist</td>
        <td>Tokyo</td>
    </tr>
    <tr>
        <td>Colleen Hurst</td>
        <td>Javascript Developer</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Sonya Frost</td>
        <td>Software Engineer</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Jena Gaines</td>
        <td>Office Manager</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Quinn Flynn</td>
        <td>Support Lead</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Charde Marshall</td>
        <td>Regional Director</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Haley Kennedy</td>
        <td>Senior Marketing Designer</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Tatyana Fitzpatrick</td>
        <td>Regional Director</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Michael Silva</td>
        <td>Marketing Designer</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Paul Byrd</td>
        <td>Chief Financial Officer (CFO)</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Gloria Little</td>
        <td>Systems Administrator</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Bradley Greer</td>
        <td>Software Engineer</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Dai Rios</td>
        <td>Personnel Lead</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Jenette Caldwell</td>
        <td>Development Lead</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Yuri Berry</td>
        <td>Chief Marketing Officer (CMO)</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Caesar Vance</td>
        <td>Pre-Sales Support</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Doris Wilder</td>
        <td>Sales Assistant</td>
        <td>Sidney</td>
    </tr>
    <tr>
        <td>Angelica Ramos</td>
        <td>Chief Executive Officer (CEO)</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Gavin Joyce</td>
        <td>Developer</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Jennifer Chang</td>
        <td>Regional Director</td>
        <td>Singapore</td>
    </tr>
    <tr>
        <td>Brenden Wagner</td>
        <td>Software Engineer</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Fiona Green</td>
        <td>Chief Operating Officer (COO)</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Shou Itou</td>
        <td>Regional Marketing</td>
        <td>Tokyo</td>
    </tr>
    <tr>
        <td>Michelle House</td>
        <td>Integration Specialist</td>
        <td>Sidney</td>
    </tr>
    <tr>
        <td>Suki Burks</td>
        <td>Developer</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Prescott Bartlett</td>
        <td>Technical Author</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Gavin Cortez</td>
        <td>Team Leader</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Martena Mccray</td>
        <td>Post-Sales support</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Unity Butler</td>
        <td>Marketing Designer</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Howard Hatfield</td>
        <td>Office Manager</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Hope Fuentes</td>
        <td>Secretary</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Vivian Harrell</td>
        <td>Financial Controller</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Timothy Mooney</td>
        <td>Office Manager</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Jackson Bradshaw</td>
        <td>Director</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Olivia Liang</td>
        <td>Support Engineer</td>
        <td>Singapore</td>
    </tr>
    <tr>
        <td>Bruno Nash</td>
        <td>Software Engineer</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Sakura Yamamoto</td>
        <td>Support Engineer</td>
        <td>Tokyo</td>
    </tr>
    <tr>
        <td>Thor Walton</td>
        <td>Developer</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Finn Camacho</td>
        <td>Support Engineer</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Serge Baldwin</td>
        <td>Data Coordinator</td>
        <td>Singapore</td>
    </tr>
    <tr>
        <td>Zenaida Frank</td>
        <td>Software Engineer</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Zorita Serrano</td>
        <td>Software Engineer</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Jennifer Acosta</td>
        <td>Junior Javascript Developer</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Cara Stevens</td>
        <td>Sales Assistant</td>
        <td>New York</td>
    </tr>
    <tr>
        <td>Hermione Butler</td>
        <td>Regional Director</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Lael Greer</td>
        <td>Systems Administrator</td>
        <td>London</td>
    </tr>
    <tr>
        <td>Jonas Alexander</td>
        <td>Developer</td>
        <td>San Francisco</td>
    </tr>
    <tr>
        <td>Shad Decker</td>
        <td>Regional Director</td>
        <td>Edinburgh</td>
    </tr>
    <tr>
        <td>Michael Bruce</td>
        <td>Javascript Developer</td>
        <td>Singapore</td>
    </tr>
    <tr>
        <td>Donna Snider</td>
        <td>Customer Support</td>
        <td>New York</td>
    </tr>
    -->
    </tbody>
</table>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#appendRegionModal">
    Добавить новый
</button>
<button type="button" class="btn btn-danger btn-lg" data-role="delete-table-row">
    Удалить строку
</button>
<jsp:include page="_inputmodal.jsp"></jsp:include>