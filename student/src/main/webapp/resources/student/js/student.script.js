var myapp;
(function() {
    myapp = {
        init: function () {
            if($('#drop').length) {
                $.myapp.dropforminit();
                $.myapp.imageremove();
            }
            $.myapp.initbaguette();
        },
        initbaguette: function() {
            if($('.baguetteBoxOne').length) {
                baguetteBox.run('.baguetteBoxOne');
            }
        },
        imageremove: function() {
            $(document).on('click', '[aria-label=Close]', function(evt){
                var fileId = $(this).data('fileid');
                var wrapper = $(this).closest('div');
                $.ajax({
                    type: 'post',
                    url: '/SM/workload/delete',
                    data: '' +
                    'fileId=' + fileId,
                    dataType: "json",
                    success: function (data) {
                        if(data.result == 'OK') {
                            $.notify(data.message, 'success');
                            $(wrapper).remove();
                        }
                        else {
                            $.notify(data.message);
                        }
                    },
                    error: function (data) {
                        $.notify("Во время работы сервера произошла непредвиденная ошибка!");
                        console.error(data);
                    }
                });
            });
        },
        dropforminit: function () {
            $(document).on('change', '#my-file-selector', function(evt){
                var file = $(this)[0].files[0];
                var upload = new Upload(file);
                // maby check size or type here with upload.getSize() and upload.getType()
                // execute upload
                upload.doUpload();
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

var Upload = function (file) {
    this.file = file;
};

Upload.prototype.getType = function() {
    return this.file.type;
};
Upload.prototype.getSize = function() {
    return this.file.size;
};
Upload.prototype.getName = function() {
    return this.file.name;
};
Upload.prototype.doUpload = function () {
    var that = this;
    var formData = new FormData();

    // add assoc key values, this will be posts values
    formData.append("file", this.file, this.getName());
    formData.append("upload_file", true);
    formData.append("workId", $("[name=workId]").val());

    $.ajax({
        type: "POST",
        url: "/SM/workload/uploadfile",
        dataType: "json",
        xhr: function () {
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', that.progressHandling, false);
            }
            return myXhr;
        },
        success: function (data) {
            if(data.result == 'OK') {
                $.notify(data.message, 'success');
                var html = '' +
                    '<div class="col-sm-4">' +
                    '   <button type="button" class="close" aria-label="Close"' +
                    '       data-fileid="' + data.fileId + '">' +
                    '       <span aria-hidden="true">×</span>' +
                    '   </button>' +
                    '   <a href="'+data.path+'" title="Midnight City">' +
                    '       <img src="'+data.path+'" alt="Midnight City">' +
                    '   </a>' +
                    '</div>';
                $('.baguetteBoxOne').append(html);
                baguetteBox.run('.baguetteBoxOne');
            }
            else {
                $.notify(data.message);
            }
        },
        error: function (error) {
            $.notify('error');
            console.log(error);
        },
        async: true,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 60000
    });
};

Upload.prototype.progressHandling = function (event) {
    var percent = 0;
    var position = event.loaded || event.position;
    var total = event.total;
    var progress_bar_id = "#progress-wrp";
    if (event.lengthComputable) {
        percent = Math.ceil(position / total * 100);
    }
    // update progressbars classes so it fits your code
    $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
    $(progress_bar_id + " .status").text(percent + "%");
};