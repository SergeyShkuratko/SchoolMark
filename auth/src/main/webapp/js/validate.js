function validateAndSubmit() {
    var formValid = true;
    $('input').each(function () {
        var formGroup = $(this).parents('.input-group');
        var glyphicon = formGroup.find('.form-control-feedback');
        if (this.checkValidity()) {
            formGroup.addClass('has-success').removeClass('has-error');
            glyphicon.addClass('glyphicon-ok').removeClass('glyphicon-remove');
        } else {
            formGroup.addClass('has-error').removeClass('has-success');
            glyphicon.addClass('glyphicon-remove').removeClass('glyphicon-ok');
            formValid = false;
        }
    });
    if (formValid) {
        document.getElementById('auth-form').submit();
    }
}