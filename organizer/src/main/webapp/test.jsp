<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Создание контрольной работы</title>
    <script src="js/question-form.js" type="text/javascript"></script>
    <!--<link href="css/form.css" rel="stylesheet" type="text/css">-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="../../../../../../auth/src/main/webapp/js/bootstrap.js"></script>
    <script type="text/javascript">

        //        function funcTrunk(element) {
        //            if (element.value.length > 100) {
        //                element.value = element.value.substring(0, 100);
        //            }
        //        }

        $(document).ready(function () {
            document.getElementById('testDate').valueAsDate = new Date();
            document.getElementById('deadlineDate').valueAsDate = new Date();

            $('input').on('input', function () {
                var maxLength = 100;
                if (this.value.length > maxLength) {
                    this.value = this.value.substring(0, maxLength);
                }
            });

        });

        $(document)
            .on('blur', '#testDate', function (evt) {
                console.log($(this).val());
                var chosenDate = new Date($(this).val());
                if (chosenDate == "Invalid Date") {
                    chosenDate = new Date();
                }
                console.log(chosenDate);
                var toDay = new Date();
                if (chosenDate > toDay.setDate(toDay.getDate() + 365) || chosenDate < (new Date())) {
                    chosenDate = new Date();
                }

                var currDay = ('0' + chosenDate.getDate()).slice(-2);
                var currMonth = ('0' + (chosenDate.getMonth() + 1)).slice(-2);
                var currYear = chosenDate.getFullYear();
                $('#testDate').val(currYear + "-" + currMonth + "-" + currDay);

                chosenDate.setDate(chosenDate.getDate() + 1);
                var day = ('0' + chosenDate.getDate()).slice(-2);
                var month = ('0' + (chosenDate.getMonth() + 1)).slice(-2);
                var year = chosenDate.getFullYear();
                $('#deadlineDate').val(year + "-" + month + "-" + day);
            });

        $(document)
            .on('blur', '#deadlineDate', function (evt) {
                console.log($(this).val());
                var chosenTestDate = new Date($('#testDate').val());
                var chosenDealineDate = new Date($(this).val());
                if (chosenDealineDate == "Invalid Date") {
                    chosenDealineDate = new Date();
                    chosenDealineDate.setDate(chosenTestDate.getDate() + 1);
                }
                var date = new Date(chosenTestDate);
                if (chosenDealineDate > date.setDate(chosenTestDate.getDate() + 60)) {
                    chosenDealineDate = new Date();
                    chosenDealineDate.setDate(chosenTestDate.getDate() + 1);
                }
                if (chosenDealineDate < chosenTestDate) {
                    chosenDealineDate = new Date();
                    chosenDealineDate.setDate(chosenTestDate.getDate() + 1);
                }
                var day = ('0' + chosenDealineDate.getDate()).slice(-2);
                var month = ('0' + (chosenDealineDate.getMonth() + 1)).slice(-2);
                var year = chosenDealineDate.getFullYear();
                $('#deadlineDate').val(year + "-" + month + "-" + day);
            });


    </script>
    <style>
        .form-actions {
            background-color: #f5f5f5;
            padding: 19px 20px 20px;
            margin-top: 20px;
            margin-bottom: 20px;
            border-top: 1px solid #e5e5e5;
        }

        /*Встроеные в инпут иконки*/
        .input-icon {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 2;
            display: block;
            width: 34px;
            height: 34px;
            line-height: 34px;
            text-align: center;
            pointer-events: none;
            top: 25px;
        }

        .form-icon {
            position: relative;
        }

        .form-icon input {
            padding-left: 42.5px;
        }

    </style>

    <%@include file="/mystatic/menustyles.jsp" %>
</head>
<body>
<%@include file="/mystatic/pageheader.jsp" %>

<div class="container">
    <c:if test="${questionsNotLoaded == true}">
        <h2 style="color:darkred;">Добавьте вопросы или загрузите шаблон</h2>
        <p></p>
    </c:if>
    <c:if test="${fieldsNotFilled == true}">
        <h2 style="color:darkred;">Заполните все поля</h2>
        <p></p>
    </c:if>
    <h2>Заполнение информации о контрольной работе</h2>
    <form action="#" method="post">
        <h3>Загрузить из шаблона
            <button type="submit" class="btn btn-primary" name="testFormButton" value="loadTemplate">Загрузить
            </button>
        </h3>
    </form>

    <form id="templateForm" action="#" method="post">
        <p/>
        <div>
            <c:if test="${testTemplate == null}">
            <div class="form-group">
                <label for="subjectInput">Предмет</label>
                <div class="controls">
                    <select required class="form-control input-xxlarge" id="subjectInput" name="subject">
                        <option value="" selected>Выберите предмет..</option>
                        <c:forEach items="${subjects}" var="subject">
                            <option><c:out value="${subject.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="subject">Тема</label>
                <input type="text" class="input-xxlarge form-control" placeholder="Тема контрольной работы..."
                       name="templateTopic" required/>
            </div>
            <div class="form-group">
                <label for="difficulty">Сложность</label>
                <select class="form-control input-xxlarge" id="difficulty" name="difficulty" required>
                    <option value="" selected>Выберите сложность..</option>
                    <option>Легкая</option>
                    <option>Средняя</option>
                    <option>Сложная</option>
                </select>
            </div>
            <div class="form-group">
                <label for="classNum">Класс</label>
                    <%--<input type="text" class="form-control input-xxlarge" id="classNum" placeholder="10" name="classNum"--%>
                    <%--required>--%>
                <select class="form-control input-xxlarge" id="classNum" name="classNum" required>
                    <option value="" selected>Выберите номер класса..</option>
                    <c:forEach items="${classNumbers}" var="classNumber">
                        <option><c:out value="${classNumber}"/></option>
                    </c:forEach>
                </select>
            </div>
            </c:if>
            <c:if test="${testTemplate != null}">
            <div class="form-group">
                <label for="subject">Предмет</label>
                <div class="controls">
                    <select class="form-control input-xxlarge" id="subject" name="subject">
                        <c:forEach items="${subjects}" var="subject">
                            <option ${subject.name == testTemplate.getSubject().name ? 'selected' : ''}>
                                <c:out value="${subject.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="subject">Тема</label>
                <input type="text" class="input-xxlarge form-control" value="${testTemplate.topic}"
                       name="templateTopic"/>
            </div>
            <div class="form-group">
                <label for="difficulty">Сложность</label>
                <select class="form-control input-xxlarge" id="difficulty" name="difficulty">
                    <option ${"Легкая" == testTemplate.difficulty ? 'selected' : ''}>Легкая</option>
                    <option ${"Средняя" == testTemplate.difficulty ? 'selected' : ''}>Средняя</option>
                    <option ${"Сложная" == testTemplate.difficulty ? 'selected' : ''}>Сложная</option>
                </select>
            </div>
            <div class="form-group">
                <label for="classNum">Класс</label>
                    <%--<input type="text" class="form-control input-xxlarge" id="classNum" placeholder="11"--%>
                    <%--value="${testTemplate.classNum}" name="classNum">--%>
                <select class="form-control input-xxlarge" id="classNum" name="classNum">
                    <c:forEach items="${classNumbers}" var="classNumber">
                        <option ${classNumber == testTemplate.classNum ? 'selected' : ''}>
                            <c:out value="${classNumber}"/></option>
                    </c:forEach>
                </select>
            </div>
            </c:if>
    </form>
    <form id="testForm" action="#" method="post">
        <div class="form-group">
            <label for="className">Буква</label>
            <select required class="form-control input-xxlarge" id="className" name="className">
                <option value="" selected>Выберите букву класса..</option>
                <c:forEach items="${classNames}" var="className">
                    <option><c:out value="${className}"/></option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group form-icon">
            <label for="testDate">Дата контрольной работы</label>
            <input type="date" oninput="addDay(this)" class="form-control input-xxlarge" id="testDate" name="testDate">
            <i class="glyphicon glyphicon-calendar input-icon" aria-hidden="true"></i>
        </div>
        <div class="form-group form-icon">
            <label for="deadlineDate">Крайний срок проверки</label>
            <input type="date" onblur="addDay(this)" class="form-control input-xxlarge" id="deadlineDate"
                   name="deadlineDate">
            <i class="glyphicon glyphicon-calendar input-icon" aria-hidden="true"></i>
        </div>

        <label for="testTheme">Название контрольной работы</label>
        <input required type="text" class="input-xxlarge form-control"
               placeholder="Название контрольной..."
               id="testTheme" name="testTheme"/>

    </form>
</div>


<div class="form-actions">
    <button form="testForm" type="submit" class="btn btn-primary " name="testFormButton" value="createTest">Создать
        контрольную
        работу
    </button>
    <c:if test="${testTemplate == null}">
        <button form="templateForm" type="submit" class="btn btn-primary " name="testFormButton" value="inputQuestions">
            Перейти к вводу
            вопросов
        </button>
    </c:if>
    <c:if test="${testTemplate != null}">
        <button form="templateForm" type="submit" class="btn btn-primary " name="testFormButton" value="inputQuestions">
            Редактировать вопросы
        </button>
    </c:if>
    <button type="button" class="btn btn-default ">Вернуться без сохранения</button>
</div>

<%@include file="/mystatic/pagefooter.jsp" %>
</body>
</html>