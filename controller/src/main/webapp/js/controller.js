var application_context = "/SM";
var ac = application_context;



$(":button.test").click(function () {
        $.get(ac + "/getHelp?getWorksForTest=" + $(this).attr('testId'), function (data) {
            var tests = JSON.parse(data);
            $('.description').text(tests.description)
            $(".works-list").empty();
            $.each(tests.works, function () {
                $(".works-list").append("<li> <button workId =\"" + this.workid + "\" class=\"btn btn-success control-work-sidebar-button work\" type=\"submit\">" + this.firstname + this.surname + "</button>  </li>")
            })
        })
    }
)

$('#works-list-container').on('click', ':button.work', function () {
    $.get(ac + "/getHelp?getImageForWork=" + $(this).attr('workId'), function (data) {
        var works = JSON.parse(data);
        console.log(works)
        $(".work-image").empty();
        $.each(works, function () {
            $(".work-image").append("         <a data-toggle=\"modal\" data-target='#showImage' href='#showImage' class='open-showImage'><li>\n" +
                "                             <img  imageId=\"" + this.workImageId + "\" alt=\"100%x180\" data-src=\"holder.js/100%x180\"\n" +
                "                             style=\"height: 180px; width: 100%; display: block;\"\n" +
                "                             src=\"" + this.picture + "\"\n" +
                "                             data-holder-rendered=\"true\"></li></a>");
        })


    })
});

$('.work-image').on('click', 'img', function () {
    // console.log(($(this).attr('imageId')))
    $('.modal-body img').attr('src', $(this).attr('src'))

})

$( '[data-role="ratingbar"]' )
    .ratingbar()
    .click(function() {

        // Grab value
        alert( $( this ).attr( 'data-value' ) );

        return false;
    });
