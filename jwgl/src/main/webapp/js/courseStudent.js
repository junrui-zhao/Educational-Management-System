/**
 * Created by Phoenix on 2017/4/10.
 */
$(document).ready(function(){
    $(".download-btn").on('click', function () {
        var outputname = $(this).parent().find("div").find('h3').text();
        $(this).parent().find("table").table2excel({
            exclude: ".noExl",
            name: outputname,
            filename: outputname,
            fileext: ".xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    });
});
