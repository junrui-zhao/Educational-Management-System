function check() {
    var xueshu = $("#xueshu").val();
    var jiaoxue = $("#jiaoxue").val();
    var taidu = $("#taidu").val();
    if (xueshu == 0 || jiaoxue == 0 || taidu == 0) {
        alert("请为所有选项打分");
        return false;
    }
    else {
        return true;
        <!--$.ajax({
            type: "POST",
            url: "/student/updateEvaluate",
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify({
                studentId: model.studentId,
                openId: model.openId,
                xueshu: xueshu,
                jiaoxue: jiaoxue,
                taidu: taidu,
                other: other
            }),
            success: function (jsonResult) {
                alert("提交成功")
            },
            error: function (e) {
                alert("error")
            }
        });-->
    }
}