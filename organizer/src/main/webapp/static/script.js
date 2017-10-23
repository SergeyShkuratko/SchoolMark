//Надстройка над табличкой чтобы можно было делать сортировку
$(document).ready(function () {

    $().on

    $(document)
        .on('click', '[data-toggle=modal]', function(evt){
        var id = $(this).closest('tr').first().attr('id').split("_")[1];
        $('[data-action-work]').attr('data-action-work', id);
        var fio = $(this).closest('tr').first().find('td').first().text();
        $('.modal-title').text(fio);

        $.ajax({
            type: 'get',
            url: '/organizer/run',
            data: 'pages_images='+id,
            dataType: 'json',
            success: function(response){
                var html = "";
                for(var i=0; i<response.length; i++) {
                    console.log(response[i]);
                    html += '<div class="item ' + (i==0 ? 'active' : '') + '">';
                    html += '<img src="' + response[i] + '">';
                    html += '</div>';
                }
                if(!html.length){
                    html = 'http://dailyfrontiertimes.com/UnderConstruction.png';
                }
                $('#myCarousel').carousel("pause").removeData();
                $('.carousel-inner').html(html);
                $('#myCarousel').carousel(1);
            },
            error: function (response) {
                console.log(response);
            }
        })
    }).on('click', '[data-action-work]', function(evt){
        var id = $(this).first().attr('data-action-work');
        switch($(this).data('button-role')) {
            case 'success':
                break;
            case 'decline':
                break;
        }
        $.ajax({
            type: 'get',
            url: '/organizer/run',
            data: 'action=' + $(this).data('button-role') + '&id=' + id,
            dataType: 'json',
            success: function(response){
                    $.notify("Сохранено");
            },
            error: function (response) {
                $.notify("Ошибка сохранения!");
            }
        })
    });

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




