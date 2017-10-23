function loadClasses(schoolId) {

    var id = '#classes' + schoolId;
    var aid = '#a-classes' + schoolId;
    $(id).html('<li>Loading...</li>');

    $.ajax({
        url: 'classes?school=' + schoolId,
        success: function (output) {
            $(id).html(output);
            $(aid).attr('data-toggle', "collapse");
            $(aid).attr('onclick', '').unbind('click');
            $('#div-classes'+schoolId).collapse('show');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status + " " + thrownError);
        }
    });
}

function loadTeachers(schoolId) {

    var id = '#teachers' + schoolId;
    var aid = '#a-teachers' + schoolId;

    $(id).html('<li>Loading...</li>');

    $.ajax({
        url: 'teachers?school=' + schoolId,
        success: function (output) {
            $(id).html(output);
            $(aid).attr('data-toggle', "collapse");
            $(aid).attr('onclick', '').unbind('click');
            $('#div-teachers'+schoolId).collapse('show');
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status + " " + thrownError);
        }
    });
}