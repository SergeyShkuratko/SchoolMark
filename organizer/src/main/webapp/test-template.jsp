<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            });
        }

        $(document).ready(function () {
            /**
             * Add a Tab
             */
            $('#btnAddVariant').click(function () {
                variantNum++;
                $('#variantTab').append(
                    $('<li><a href="#variant' + variantNum + '">' +
                        'Вариант ' + variantNum +
                        '<button class="close" type="button" ' +
                        'title="Remove this variant">×</button>' +
                        '</a></li>'));

                $('#variantTabContent').append($(
                    '<div class="tab-pane" id="variant' + variantNum + '">' +
                    '<button type="button" class="btn btn-success" value="Добавить вопрос" onclick="addQuestionBlock(\'variant' + variantNum + '\')">Добавить вопрос</button>' +
                    '<button type="button" class="btn btn-danger" value="Удалить вопрос" onclick="addQuestionBlock()">Удалить вопрос</button>' +
                    '</div>'
                ));

//                $("#variant" + variantNum).tab('show');


            });

            /**
             * Remove a Tab
             */
            $('#variantTab').on('click', ' li a .close', function () {
                var tabId = $(this).parents('li').children('a').attr('href');
                $(this).parents('li').remove('li');
                $(tabId).remove();
                reNumberVariants();
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
</head>
<body>
<div class="container">
    <div>
        <h2>Создание контрольной работы</h2>
        <form action="#" id="mainform" method="post" name="mainform">
            <div class="container">
                <a href="javascript:;" id="btnAddVariant" role="button">Добавить вариант</a>
                <span class="container">
                    <ul id="variantTab" class="nav nav-tabs">
                        <li class="active"><a href="#variant1" data-toggle="tab">Вариант 1</a></li>
                    </ul>
                </span>
                <div id="variantTabContent" class="tab-content">
                    <div class="tab-pane active" id="variant1">
                        <button type="button" class="btn btn-success" value="Добавить вопрос" onclick="addQuestionBlock('variant1')">Добавить вопрос</button>
                        <button type="button" class="btn btn-danger" value="Удалить вопрос" onclick="addQuestionBlock()">Удалить вопрос</button>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <button class="btn btn-default" type="button">Вернуться без сохранения</button>
                <button class="btn btn-primary" type="submit">Создать контрольную работу</button>
                <button class="btn btn-default" type="button">Сохранить как шаблон</button>
            </div>
        </form>
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
</body>
</html>