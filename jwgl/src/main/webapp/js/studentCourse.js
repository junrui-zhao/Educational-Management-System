/**
 * Created by Phoenix on 2017/4/9.
 */
$(document).ready(function(){
    $('.quit-btn').on('click', function () {
        var courseId = $(this).parent().parent().find("td:nth-child(1)").text();
        var url = "http://localhost:8080/student/quitcourse";
        $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                courseId: courseId
            }),
            success: function (jsonResult) {
                alert("退课成功");
            },
            error: function (e) {
                alert("退课异常");
            }
        });
        window.location.reload();
    });

    $('.select-btn').on('click', function () {
        var openId = $(this).parent().parent().find("td:nth-child(9)").text();
        var url = "http://localhost:8080/student/select";
        $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                openId: openId
            }),
            success: function (jsonResult) {
                var res = jsonResult["result"];
                if (res == 500) {
                    alert("选课成功");
                } else if (res == 501) {
                    alert("人数已满");
                } else if (res == 502) {
                    alert("同课程已选");
                } else if (res == 503) {
                    alert("时间冲突");
                } else {
                    alert("选课异常");
                }
            },
            error: function (e) {
                alert("选课异常");
            }
        });
        window.location.reload();
    });
});



