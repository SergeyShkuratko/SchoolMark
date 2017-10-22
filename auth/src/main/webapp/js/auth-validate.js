function validateAndSubmit() {
    var formValid = true;
    $('input').each(function () {
        var error = false;
        var errorDiv = $(this).parent('.has-feedback').next('.error-text');
        var span = errorDiv.children();
        if ($(this).attr('name') === "login") {
            var regex = new RegExp(pattern="^[A-Za-z]+$");
            var regex2 = new RegExp(pattern="^[A-Za-z][A-Za-z0-9]*$");
            if (!regex.test($(this).val())) {
                errorDiv.addClass('has-error');
                var text = "Логин должен начинаться с буквы латинского алфавита";
                span.eq(0).text(text);
                error = true;
            } else if (!regex2.test($(this).val())) {
                errorDiv.addClass('has-error');
                var text = "Логин должен содержать только буквы латинского алфавита и цифры";
                span.eq(0).text(text);
                error = true;
            } 
        } else if ($(this).attr('name') === "letter") {
            var regex = new RegExp(pattern="^[а-яА-Я]?$");
            if (!regex.test($(this).val())) {
                errorDiv.addClass('has-error');
                var text = "Введенное значение должно быть кириллицей";
                span.eq(0).text(text);
                error = true;
            }
        }
        if (!error) {
            if ($(this).attr('required') != null && $(this).val().length == 0) {
                errorDiv.addClass('has-error')
                var text = "Поле обязательно для заполнения";
                span.eq(0).text(text);
                formValid = false;
            } else if ($(this).attr('minlength') > $(this).val().length) {
                errorDiv.addClass('has-error')
                var text = "Длина поля должна быть не менее " + $(this).attr("minlength") + " символов";
                span.eq(0).text(text);
                formValid = false;
            } else if ($(this).attr('min') > $(this).val()) {
                errorDiv.addClass('has-error')
                var text = "Значение должно быть больше или равно " + $(this).attr("min");
                span.eq(0).text(text);
                formValid = false;
            } else if ($(this).attr('max') < $(this).val()) {
                errorDiv.addClass('has-error')
                var text = "Значение должно быть меньше или равно " + $(this).attr("max");
                span.eq(0).text(text);
                formValid = false;
            } else {
                errorDiv.removeClass('has-error')
                span.eq(0).text("");
            }
        }
    });
    if (formValid) {
        document.getElementById('validate-form').submit();
    }
};
