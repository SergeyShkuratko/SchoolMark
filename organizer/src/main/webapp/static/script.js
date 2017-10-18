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
            //Запрещаем добавление контрольной, ставим флаг отсутствия у ученика на работу
            $.ajax({
                method: "POST",
                url: "{$context}/run",
                data: {
                    work_id: $(this).attr('name'),
                    presence: 1
                }
            })
                .done(function (msg) {
                    alert("Data Saved: " + msg);
                    $(this).closest('tr').removeClass("active");
                });
        } else {
            //Запрещаем добавление контрольной, ставим флаг отсутствия у ученика на работу
            $.ajax({
                method: "POST",
                url: "{$context}/run",
                data: {
                    work_id: $(this).attr('name'),
                    presence: 0
                }
            })
                .done(function (msg) {
                    alert("Data Saved: " + msg);
                    $(this).closest('tr').addClass("active");
                });
        }
    });


});




