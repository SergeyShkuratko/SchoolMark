var application_context = "/SM";
var ac = application_context;
var servletName = "/getInfo"


$(":button.test").click(function () {
        $.get(ac + servletName + "?getWorksForTest=" + $(this).attr('testId'), function (data) {
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

$('#works-list-container').on('click', ':button.work', function () {
    $.get(ac + servletName + "?getImageForWork=" + $(this).attr('workId'), function (data) {
        var works = JSON.parse(data);
        console.log(works)
        $(".work-image").empty();
        $.each(works.urls, function () {
            $(".work-image").append("         <a data-toggle=\"modal\" data-target='#showImage' href='#showImage' class='open-showImage'><li>\n" +
                "                             <img  alt=\"100%x180\" " +
                "                             style=\"height: 180px; width: 180px; display: block;\"\n" +
                "                             src=\"" + this + "\"\n" +
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
    .click(function () {

        // Grab value
        // alert($(this).attr('data-value'));

        return false;
    });


$(":button.send-result").click(function () {
    var value = $('[data-role="ratingbar"]').attr('data-value')
    if (value) {
        alert(value);
    }
})
