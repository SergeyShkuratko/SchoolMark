<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Добавление вопросов к контрольной</title>
    <script src="js/question-form.js" type="text/javascript"></script>
    <!--<link href="css/form.css" rel="stylesheet" type="text/css">-->
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bootstrap-responsive.css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>

    <script type="text/javascript">
        var pageImages = [];
        var variantNum = 1;
        /**
         * Reset numbering on tab buttons
         */
        function reNumberVariants() {
            variantNum = 1;
            var tabCount = $('#variantTab > li').length;

            $('#variantTab > li').each(function () {
                var variantId = $(this).children('a').attr('href');
                if (variantId == "#variant1") {
                    return true;
                }
                variantNum++;
                $(this).children('a').html('Вариант ' + variantNum +
                    '<button class="close" type="button" ' +
                    'title="Remove this variant">×</button>');
                $(this).children('a').attr('href', '#variant' + variantNum);
            });
        }

        $(document).ready(function () {
            document.getElementById("variantTab").getElementsByTagName("li")[0].setAttribute("class", "active");
            document.getElementById("variantTabContent").getElementsByTagName("div")[0].setAttribute("class", "tab-pane active");


            /**
             * Add a Tab
             */
            $('#btnAddVariant').click(function () {
                variantNum++;
                $('#variantTab').append(
                    $('<li><a href="#variant' + variantNum + '">' +
                        'Вариант ' + variantNum +
                        '<button class="close" type="button" ' +
                        'title="Удалить вариант">×</button>' +
                        '</a></li>'));

                $('#variantTabContent').append($(
                    '<div class="tab-pane" id="variant' + variantNum + '">' +
                    '<button type="button" class="btn btn-success" value="Добавить вопрос" onclick="addQuestionBlock(this)">Добавить вопрос </button>'+
                    '</div>'
                ));

                var questionDiv = addQuestionBlockToTab($('#variantTabContent').children('.tab-pane')[variantNum-1]);
                //document.crea
//
//                  ("#variant" + variantNum).tab('show');

//                $('#variantTab').children('.a')[variantNum-1].tab('show');
                $('#variantTab a:last').tab('show');
            });

            /**
             * Remove a Tab
             */
            $('#variantTab').on('click', ' li a .close', function () {
                var tabId = $(this).parents('li').children('a').attr('href');
                $(this).parents('li').remove('li');
                $(tabId).remove();
                reNumberVariants();
                generateId();
                $('#variantTab a:first').tab('show');
            });

            /**
             * Click Tab to show its content
             */
            $("#variantTab").on("click", "a", function (e) {
                e.preventDefault();
                $(this).tab('show');
            });


        });
    </script>

    <c:if test="${testTemplate == null}">
        <script type="text/javascript">
            $(document).ready(function () {
                var questionDiv = addQuestionBlockToTab($('#variantTabContent').children('.tab-pane')[0]);
            });
        </script>
    </c:if>

    <style>
        .nav-tabs > li .close {
            margin: -3px 0 0 10px;
            font-size: 18px;
            padding: 5px 0;
            float: right;
        }

        .nav-tabs > li a[data-toggle=tab] {
            float: left !important;
        }

        .form-actions {
            background-color: #f5f5f5;
            padding: 19px 20px 20px;
            margin-top: 20px;
            margin-bottom: 20px;
            border-top: 1px solid #e5e5e5;
        }

    </style>
    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
<%@include file="/mystatic/pageheader.jsp" %>
<div class="container">
    <div>
        <c:if test="${templateSaved == true}">
            <h2 style="color:darkred;">Шаблон сохранен</h2>
            <p></p>
        </c:if>
        <h2>Создание контрольной работы</h2>
        <form action="#" id="mainform" method="post" name="mainform">
            <c:if test="${testTemplate == null}">
                <div class="container">
                    <p></p>
                    <a href="javascript:;" id="btnAddVariant" role="button">Добавить вариант</a>
                    <span class="container">
                    <ul id="variantTab" class="nav nav-tabs">
                        <li class="active"><a href="#variant1" data-toggle="tab">Вариант 1</a></li>
                    </ul>
                </span>
                    <div id="variantTabContent" class="tab-content">
                        <div class="tab-pane active" id="variant1">
                            <button type="button" class="btn btn-success" value="Добавить вопрос"
                                    onclick="addQuestionBlock(this)">Добавить вопрос
                            </button>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${testTemplate != null}">
                <div class="container">
                    <a href="javascript:;" id="btnAddVariant" role="button">Добавить вариант</a>
                    <span class="container">
                    <ul id="variantTab" class="nav nav-tabs">
                        <c:forEach items="${testTemplate.getTestVariants()}" var="variant" varStatus="tempVarCount">
                            <c:if test="${tempVarCount.count == 1}">
                                <li><a href="#variant${tempVarCount.count}" data-toggle="tab">Вариант ${tempVarCount.count}</a></li>
                            </c:if>
                            <c:if test="${tempVarCount.count != 1}">
                                <li><a href="#variant${tempVarCount.count}" data-toggle="tab">Вариант ${tempVarCount.count} <button class="close" type="button" title="Удалить вариант">×</button></a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </span>
                    <div id="variantTabContent" class="tab-content">
                        <c:forEach items="${testTemplate.getTestVariants()}" var="variant" varStatus="varCount">
                            <div class="tab-pane" id="variant${varCount.count}">
                                <button type="button" class="btn btn-success" value="Добавить вопрос"
                                        onclick="addQuestionBlock(this)">Добавить вопрос
                                </button>
                                <c:forEach items="${variant.getTestQuestions()}" var="question" varStatus="questCount">
                                    <div class="question">
                                        <hr>
                                        <button type="button" class="btn btn-default btn-sm" onclick="removeThisQuestion(this)" title="Удалить вопрос">
                                            <span aria-hidden="true" class="glyphicon glyphicon-remove" style="color:red;"></span></button>
                                        <h2>Вопрос ${questCount.count}</h2>
                                        <input required type="text" class="form-control input-xxlarge"
                                               value="${question.questionText}"
                                               name="variant1_question1_text">
                                        <p></p>
                                        <textarea required type="text" class="form-control input-xxlarge"
                                                  value="${question.answerText}"
                                                  name="variant1_question1_answer">${question.answerText}</textarea>
                                        <p></p>
                                        <label>Критерии оценки
                                            <button type="button" class="btn btn-default btn-sm" onclick="addCriteriaElement(this)" title="Добавить критерий">
                                                <span aria-hidden="true" class="glyphicon glyphicon-plus" style="color:green;"></span></button>
                                            <button type="button" class="btn btn-default btn-sm" onclick="removeCriteriaElement(this)" title="Удалить критерий">
                                                <span aria-hidden="true" class="glyphicon glyphicon-minus" style="color:red;"></span></button></label>
                                        <p></p>
                                        <c:forEach items="${question.getCriterians()}" var="criterion" varStatus="criterCount">
                                            <input required type="text" class="criterion form-control"
                                                   value="${criterion}">
                                            <p></p>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </form>
        <form id="emptyForm" action="#" method="post">
        </form>
            <div class="form-actions">
                <button form="emptyForm" class="btn btn-default" type="submit" name="templateFormButton" value="returnWithoutSave">Вернуться без сохранения</button>
                <c:if test="${testTemplate == null}">
                    <button form="mainform" class="btn btn-primary" type="submit" name="templateFormButton" value="endInputQuestions" onclick="generateId()">Завершить ввод вопросов</button>
                    <button form="mainform" class="btn btn-primary" type="submit" name="templateFormButton" value="saveAsTemplate" onclick="generateId()">Сохранить как шаблон</button>
                </c:if>
                <c:if test="${testTemplate != null}">
                    <button form="mainform" class="btn btn-primary" type="submit" name="templateFormButton" value="saveChanges" onclick="generateId()">Сохранить изменения</button>
                </c:if>
                <button form="emptyForm" class="btn btn-default" type="submit" name="templateFormButton" value="cancel">Сбросить изменения</button>
                <%--<button class="btn btn-default" type="button" onclick="generateID('variantTabContent')">Пересчитать ID</button>--%>
            </div>

    </div>
    <!--
    ========================================================================================
    Footer Div.
    ========================================================================================
    -->
    <div class="four">
        <p>2017 <a href="https://innopolis.ru"><img src="logo.png">
        </a> nkm.</p>
    </div>

</div>
<%@include file="/mystatic/pagefooter.jsp" %>
</body>
</html>