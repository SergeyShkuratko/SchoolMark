var application_context = "/SM";
var ac = application_context;
var servletName = "/getInfo"


$(":button.test").click(function () {
        $.get(ac + servletName + "?getWorksForTest=" + $(this).attr('testid'), function (data) {
            console.log(ac + servletName + "?getWorksForTest=" + $(this).attr('testid'))
            $(".works-list").empty();
            $('.description').empty();
            if (data === "") {
                return;
            }
            var tests = JSON.parse(data);
            $('.description').append(" <p>" + tests.topic + "</p><p>" + tests.description + "</p>");
            $(".works-list").empty();
            $.each(tests.workIds, function () {
                $(".works-list").append("<li> <button workId =\"" + this + "\" class=\"btn control-work-sidebar-button work not-checked\" type=\"submit\">Control work</button>  </li>")
            })
        })
    }
)
var selectedWorkId;
$('#works-list-container').on('click', ':button.work', function () {
    selectedWorkId = $(this).attr('workId');
    $.get(ac + servletName + "?getImageForWork=" + selectedWorkId, function (data) {
        var works = JSON.parse(data);
        console.log(works)
        $(".work-image").empty();
        $.each(works.urls, function () {
            var currentUrl = 'http://localhost:8080/SM/' + this
            $(".work-image").append("         <a data-toggle=\"modal\" data-target='#showImage' href='#showImage' class='open-showImage'><li>\n" +
                "                             <img  alt=\"100%x180\" " +
                "                             style=\"height: 180px; width: 180px; display: block;\"\n" +
                "                             src=\"" + 'http://localhost:8080/SM' + this + "\"\n" +
                "                             data-holder-rendered=\"true\"></li></a>");
        })


    })
});

$('.work-image').on('click', 'img', function () {
    // console.log(($(this).attr('imageId')))
    $('.modal-body img').attr('src', $(this).attr('src'))

})

$('[data-role="ratingbar"]')
    .ratingbar()


$(":button.send-result").click(function () {
    var mark = $('[data-role="ratingbar"]').attr('data-value')
    var comment = $('#comment').val();
    var data = {
        mark: mark,
        comment: comment,
        workId: selectedWorkId
    }
    $.post(ac + '/persistVerificationResult', data, function (data) {
        var result = JSON.parse(data);
        if (result.result === 'ok') {
            $.notify("Сохранено");
            $('button[workid=' + selectedWorkId + ']').remove();
            selectedWorkId = -1
        } else {
            $.notify("Ошибка сохранения");
        }
    })
})
