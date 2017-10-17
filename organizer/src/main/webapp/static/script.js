//Надстройка над табличкой чтобы можно было делать сортировку
$(document).ready(function () {
    $('#school-class-table').DataTable(
        {
            "paging": false,
            "ordering": true,
            "info": false,
            "searching": false,

            language: {
                "search": "Поиск:"
            }
        }
    );

    $("input[type='checkbox']").change(function () {
        if ($(this).is(":checked")) {
            $(this).closest('tr').removeClass("active")
        } else {
            $(this).closest('tr').addClass("active");
        }
    });


});




