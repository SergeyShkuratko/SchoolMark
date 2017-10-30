<%--
  Created by IntelliJ IDEA.
  User: vidok
  Date: 27.10.17
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    body {
        font-family: "Lato", sans-serif;
    }

    .sidenav {
        height: 100%;
        width: 200px;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        background-color: #f5f5f5;
        border-right: 1px solid #ddd;
        overflow-x: hidden;
        padding-top: 20px;
    }

    .sidenav a {
        padding: 6px 6px 6px 32px;
        text-decoration: none;
        font-size: 18px;
        color: #000000;
        display: block;
    }

    .sidenav a:hover {
        background-color: #e6e6e6;
    }

    .main {
        margin-left: 200px;
        /* Same as the width of the sidenav */
    }

    @media screen and (max-height: 450px) {
        .sidenav {
            padding-top: 15px;
        }
        .sidenav a {
            font-size: 18px;
        }
    }

    .row {
        margin-left: 0px;
        margin-right: 0px;
    }
</style>
