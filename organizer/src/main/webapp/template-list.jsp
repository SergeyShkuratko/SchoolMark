<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<div class="container-fluid">
    <div class="row placeholders">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                <tr class="active">
                    <th>Название шаблона</th>
                    <th>Дата создания</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Контрольная по теме сложения</td>
                    <td>2017-03-12</td>
                </tr>
                <tr>
                    <td>Контрольная по теме вычитания</td>
                    <td>2017-03-13</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <span>
                <form action="/templates/constr" method="get">
                    <button type="submit" class="btn btn-primary pull-right">Создать</button>
                </form>
                <form action="/templates/use" method="get">
                    <button type="submit" class="btn btn-primary pull-right">Вернуться</button>
                </form>
    </span>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="../../assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
