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
                method: "GET",
                url: "run",
                data: {
                    work_id: $(this).attr('name'),
                    presence: true
                }
            })
                .done(function () {

                });
            $(this).closest('tr').removeClass("active");

        } else {
            //Запрещаем добавление контрольной, ставим флаг отсутствия у ученика на работу
            $.ajax({
                method: "GET",
                url: "run",
                data: {
                    work_id: $(this).attr('name'),
                    presence: false
                }
            })
                .done(function () {

                });
            $(this).closest('tr').addClass("active");

        }
    });

    //Проверяем статус загрузки работы учеником
    setInterval(function () {
        $.ajax({
            method: "GET",
            url: "run",
            data: {
                get_upload_info_for_test: $('#test_id').val()
            },
        })
            .done(function (data) {
                $.each(data, function (index, entry) {
                    if (entry.status == 'uploaded') {
                        $('#work_' + entry.id + '_status').html('Да,  <a data-toggle="modal" data-target="#myModal" style="cursor: pointer;">Просмотреть </a>');
                        $('#work_' + entry.id + '_confirm').html('Нет');
                        $('#work_' + entry.id+'').addClass('info');
                    } else {
                        $('#work_' + entry.id + '_status').html('Нет');
                    }

                    if(entry.status == 'confirmed'){
                        $('#work_' + entry.id + '_status').html('Да, <a data-toggle="modal" data-target="#myModal" style="cursor: pointer;">Просмотреть </a>');
                        $('#work_' + entry.id + '_confirm').html('Да');
                        $('#work_' + entry.id+'').addClass('success');
                    }

                });

            });

    }, 5000); //15 seconds




});




