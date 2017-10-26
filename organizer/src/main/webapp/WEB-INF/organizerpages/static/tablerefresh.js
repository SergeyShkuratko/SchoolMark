$(document).ready(function () {

    $('#school-class-table').DataTable();

    $('#refresh').click(function () {
        updateTable();
    });

    setInterval(function () {
        var autorefresh = $('input[name=autorefresh]').is(':checked');
        if (autorefresh) {
            updateTable();
        }

    }, 5000);




});