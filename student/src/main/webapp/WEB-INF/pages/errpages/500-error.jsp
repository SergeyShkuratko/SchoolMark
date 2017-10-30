<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="500 Internal Server Error">
<meta name="author" content="">
<title>500 Internal Server Error</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<style>
/* Error Page Inline Styles */
body {
  padding-top: 20px;
}
/* Layout */
.jumbotron {
  font-size: 21px;
  font-weight: 200;
  line-height: 2.1428571435;
  color: inherit;
  padding: 10px 0px;
}
/* Everything but the jumbotron gets side spacing for mobile-first views */
.masthead, .body-content, {
  padding-left: 15px;
  padding-right: 15px;
}
/* Main marketing message and sign up button */
.jumbotron {
  text-align: center;
  background-color: transparent;
}
.jumbotron .btn {
  font-size: 21px;
  padding: 14px 24px;
}
/* Colors */
.green {color:#5cb85c;}
.orange {color:#f0ad4e;}
.red {color:#d9534f;}
</style>
<script type="text/javascript">
  function loadDomain() {
    var display = document.getElementById("display-domain");
    display.innerHTML = document.domain;
  }
</script>
</head>
<body onload="javascript:loadDomain();">
<!-- Error Page Content -->
<div class="container">
  <!-- Jumbotron -->
  <div class="jumbotron">
    <h1><span class="glyphicon glyphicon-fire red"></span> 500 Внутренняя ошибка сервера</h1>
    <p class="lead">Сервер вернул внутреннюю ошибку для <em><span id="display-domain"></span></em>.</p>
    <a href="javascript:document.location.reload(true);" class="btn btn-default btn-lg text-center"><span class="green">Попробовать снова</span></a>
  </div>
</div>
<div class="container">
  <div class="body-content">
    <div class="row">
      <div class="col-md-6">
        <h2>Что случилось?</h2>
        <p class="lead">
            ${whatHappened}
        </p>
      </div>
      <div class="col-md-6">
        <h2>Что я могу сделать?</h2>
        <p class="lead">Если вы посетитель сайта</p>
        <p> Вы ничего не можете сделать в данный момент. Если Вам необходима срочная помощь, пожалуйста напишите нам на электронную почту. Мы извиняемся за возможные неудобства.</p>
        <p class="lead">Если Вы собственник сайта</p>
         <p>Эта ошибка может быть устранена только администраторами сайта, пожалуйста свяжитесь с службой поддержки сайта.</p>
     </div>
    </div>
  </div>
</div>
<!-- End Error Page Content -->
<!--Scripts-->
<!-- jQuery library -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>