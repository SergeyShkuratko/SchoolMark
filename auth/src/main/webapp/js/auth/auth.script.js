var myapp;

(function() {
    myapp = {
        init: function () {
            try {
                this.initdatatable();
                this.regionappend();
                this.cityappend();
                if($('#example').length){
                    $.myapp.selectrowtableinit();
                    $.myapp.deleteselectedrowinit();
                }
                $.notify ('initialization successful', 'info');
            }
            catch(err) {
                $.notify (err.message, 'error');
                console.log ( err.message );
            }
        },
        initdatatable: function() {
            $('#example').DataTable();
        },
        validateone: function (required, value, pattern) {
            switch(required) {
                case 'true':
                    return value.length > 0;
                case 'pattern':
                    var re = new RegExp(pattern);
                    return re.exec(value) != null;
                default:
                    $.notify(required + ' not supported !!!');
                    return false;
            }
        },
        adderrormessage: function(elt) {
            $.myapp.removeerrmessage(elt);
            $(elt).closest('.input-group').addClass('has-error');
            $('<span class="help-block">'+$(elt).data('errmessage')+'</span>').insertAfter(elt);
        },
        removeerrmessage: function (elt) {
            if($(elt).next().attr('class') == 'help-block') {
                $(elt).closest('.input-group').removeClass('has-error');
                $(elt).next().remove();
            }
        },
        addregionrow: function (id, num, name) {
            var t = $('#example').DataTable();
            t.row.add( [
                id,
                num,
                name
            ]).draw(false);
        },
        selectrowtableinit: function() {
            var table = $('#example').DataTable();
            $('#example tbody').on( 'click', 'tr', function () {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
        },
        deleteselectedrowinit: function() {
            $(document).on('click', '[data-role=delete-table-row]', function(evt){
                var table = $('#example').DataTable();
                var action = $(this).data('action');
                if(table.$('tr.selected').length) {
                    var id = table.$('tr.selected').find('td').first().text();
                    $.ajax({
                        type: 'post',
                        url: action,
                        data: '' +
                        'id=' + id,
                        dataType: "json",
                        success: function (response) {
                            console.log(response);
                            if(response.error) {
                                $.notify(response.error);
                            }
                            else {
                                table.row('.selected').remove().draw( false );
                                $.notify(response.result, 'success');
                            }
                        },
                        error: function (data) {
                            console.error(data);
                            $.notify("Во время работы сервера произошла непредвиденная ошибка!");
                        }
                    });
                }
                else {
                    $.notify('Выберите строку для удаления !!!', 'info');
                }
            });
        },
        formvalidate: function(form) {
            var inputs = $(form).find('input');
            if(inputs.length) {
                var isvalid = true;
                $.each($(inputs), function (idx, elt) {
                    if($(elt).data('required')) {
                        var pattern = (typeof $(elt).data('pattern') != 'undefined')
                            ? $(elt).data('pattern') : '';
                        var val = $(elt).val();
                        var reqs = $(elt).data('required').split(",");
                        for(var key in reqs) {
                            var req = reqs[key].trim();
                            var result = $.myapp.validateone(req, val, pattern);
                            if(!result) {
                                $.notify('validation error on ' + req + ' ' + val + ' ' + pattern);
                                $.myapp.adderrormessage(elt);
                                isvalid = false;
                                return;
                            }
                            else {
                                $.myapp.removeerrmessage(elt);
                            }
                        }
                    }
                });
                if(isvalid) {
                    $.notify('validation successful', 'info');
                }
                return isvalid;
            }
            return true;
        },
        regionappend: function () {
            $(document).on('click', '[data-role=region-append]', function (evt) {
                if($.myapp.formvalidate($('#appendRegionModal'))) {
                    $.ajax({
                        type: 'post',
                        url: '/SM/admin/regions/append',
                        data: '' +
                        'region-name=' + $('[data-role=region-name-input]').val() +
                        '&region-number=' + $('[data-role=region-number-input]').val(),
                        dataType: "json",
                        success: function (response) {
                            console.error(response);
                            if(response.error) {
                                $.notify(response.error);
                            }
                            else {
                                $.myapp.addregionrow(response.id, response.num, response.name);
                                $.notify(response.result, 'success');
                            }
                        },
                        error: function (data) {
                            console.error(data);
                            $.notify("Во время работы сервера произошла непредвиденная ошибка!");
                        }
                    });
                    $('#appendRegionModal').modal('toggle');
                }
            });
        },
        cityappend: function () {
            $(document).on('click', '[data-role=city-append]', function (evt) {
                if($.myapp.formvalidate($('#appendCityModal'))) {
                    $.ajax({
                        type: 'post',
                        url: '/SM/admin/cities/append',
                        data: '' +
                        'city-name=' + $('[data-role=city-name-input]').val() +
                        '&region-number=' + $('[data-role=region-number-input]').val(),
                        dataType: "json",
                        success: function (response) {
                            console.error(response);
                            if(response.error) {
                                $.notify(response.error);
                            }
                            else {
                                $.myapp.addregionrow(response.id, response.region_id, response.name);
                                $.notify(response.result, 'success');
                            }
                        },
                        error: function (data) {
                            console.error(data);
                            $.notify("Во время работы сервера произошла непредвиденная ошибка!");
                        }
                    });
                    $('#appendCityModal').modal('toggle');
                }
            });
        }
    };
    var loader = function(){
        jQuery.myapp = myapp;
        myapp.init();
    };
    var ali = setInterval(function(){
        if (typeof jQuery !== 'function') return;
        clearInterval(ali);
        setTimeout(loader, 0);
    }, 50);
})();
