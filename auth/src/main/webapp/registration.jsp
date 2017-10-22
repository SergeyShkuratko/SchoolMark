<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/auth.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/auth-validate.js"></script>
</head>
<body>
<div class="vertical-center">
    <div class="container">
        <div class="row align-middle">
            <div class="col-xs-10 col-sm-6 col-md-5 col-lg-4 max-width centered">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Регистрация на сайте</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 login-box">
                                <form id="validate-form" role="form" action="${pageContext.request.contextPath}/register" method="POST">
                                    <div class="input-group has-feedback">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input type="text" name="login" class="form-control" placeholder="Ваш логин" pattern="[A-Za-z][A-Za-z0-9]{3,99}" required autofocus />
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                    <div class="error-text"><span></span></div>
                                    <div class="input-group has-feedback">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                        <input type="password" name="password" class="form-control" placeholder="Ваш пароль" required />
                                    </div>
                                    <div class="error-text"><span></span></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12">
                                <button type="button" id="login" class="centered btn btn-labeled btn-success" onclick="validateAndSubmit()">Зарегистрироваться</button>
                                <!--<button type="button" class="btn btn-labeled btn-danger">
                                    <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Выход</button>
                                -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
