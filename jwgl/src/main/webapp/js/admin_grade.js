var allStats;
//建议改成分别请求
//同时请求所有资源太消耗时间,数据量大时极慢.
$(document).ready(function () {

    $('#showGrade').click(function () {
        $('#first').hide();
        $('.showStats').hide();
        $('.panel').show();
        $('#queryBtn').click(function () {
            $('#listDiv').empty();
            var schoolYear = $('#schoolYear').val();
            var term = $('#term').val();
            var url = "/admin/" + model.adminId + "/showCourse/year=" + schoolYear + "/term=" + term;
            $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json',
                success: function (data) {
                    $('#listDiv').empty();
                    $('#listDiv').append('<table class="table table-hover" > <tr> <th>课程ID</th> <th>开设ID</th> <th>课程名称</th> <th>学分</th> <th>性质</th> <th>开设院系</th> <th>学年</th> <th>学期</th> <th>人数上限</th> <th>查看</th> </tr>')
                    $('#listDiv').append('</table>')
                    var list = data["courseOpenList"]
                    for (index in list) {
                        var courseOpenInfo = list[index];
                        var table = $('#listDiv').find('table').last();
                        var targetTable = table.find('tr').last();
                        var openId = 'openId' + courseOpenInfo.openId;
                        targetTable.after(
                            '<tr>' +
                            '<td>' + courseOpenInfo.courseId + '</td>' +
                            '<td>' + courseOpenInfo.openId + '</td>' +
                            '<td>' + courseOpenInfo.courseName + '</td>' +
                            '<td>' + courseOpenInfo.credit + '</td>' +
                            '<td>' + courseOpenInfo.nature + '</td>' +
                            '<td>' + courseOpenInfo.department + '</td>' +
                            '<td>' + courseOpenInfo.schoolYear + '</td>' +
                            '<td>' + courseOpenInfo.term + '</td>' +
                            '<td>' + courseOpenInfo.peopleNum + '</td>' +
                            '<td>' + '<a class="btn btn-info" id="' + courseOpenInfo.courseId + '">选择</a>' + '</td>' +
                            '</tr>'
                        );
                        $('#' + courseOpenInfo.courseId).bind("click", {msg: openId}, showGrade)
                    }
                },
                error: function (e) {
                    alert("error")
                }
            });
        });
    });

    $('#showStats').click(function () {
        $('#first').hide();
        $('#gradeDiv').hide();
        $('.showStats').show();
    });

    $('#submitBtn').click(function () {
        var year = $('#select').val();
        $('#nav').empty();
        var url = "/admin/" + model.adminId + "/showStats/" + year + "/gpa";
        $.ajax({
            type: 'GET',
            url: url,
            dataType: 'json',
            success: function (data) {
                var stats = data["statsList"];
                if (stats.length == 0) {
                    alert("暂无数据！")
                }
                allStats = stats;
                $('#nav').append('<table>');
                for (index in stats) {
                    var stat = stats[index]
                    //alert(stat.major)
                    var temp = '<tr><td><a  style=" font-size:16px;" id="' + stat.major + '">' + stat.major + '</a></td>/tr>';
                    //var temp = '<a class="la" id="' + stat.major + '">' + stat.major + '</a><br>';
                    $('#nav').append(temp);
                    $('#' + stat.major).bind("click", {msg: stat.major}, showStat)

                }
                $('#nav').append('</table>');
            },
            error: function (e) {
                alert("error")
            }
        });
    });

});

function showStat(t) {
    //alert(t.data.msg)
    for (index in allStats) {
        if (allStats[index].major == t.data.msg) {
            //alert(t.data.msg)
            var classStatisticsList = allStats[index].classStatisticsList;
            var className = [];
            var average = [];
            for (var stat in classStatisticsList) {
                //alert(classStatisticsList[stat].className+":"+classStatisticsList[stat].average)
                className.push(classStatisticsList[stat].className)
                average.push(classStatisticsList[stat].average)
            }

            var title = {
                text: t.data.msg + '班级GPA'
            };

            var subtitle = {
                text: 'Source: admin'
            };
            var xAxis = {
                categories: className
            };
            var yAxis = {
                title: {
                    text: 'GPA'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }],
                tickInterval: 0.5
            };

            var tooltip = {
                valueSuffix: ''
            }

            var legend = {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            };

            var series = [
                {
                    name: 'GPA',
                    data: average
                }
            ];

            var json = {};

            json.title = title;
            json.subtitle = subtitle;
            json.xAxis = xAxis;
            json.yAxis = yAxis;
            json.tooltip = tooltip;
            json.legend = legend;
            json.series = series;

            $('#section').highcharts(json);

        }
    }
}

function showGrade(t) {
    var openId = t.data.msg.replace('openId', '');
    //alert(openId)
    var url = "/admin/" + model.adminId + "/showCourse/openId=" + openId;
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        success: function (data) {
            $('#chooseDiv').hide();
            $('#listDiv').empty();
            $('#listDiv').append('<table class="table table-hover"><tr><th>学号</th><th>姓名</th><th>班级</th><th>专业</th><th>成绩</th></tr>')
            var list = data["gradeList"];
            for (index in list) {
                var grade = list[index];
                var table = $('#listDiv').find('table').last();
                var targetTable = table.find('tr').last();
                targetTable.after(
                    '<tr>' +
                    '<td>' + grade.studentId + '</td>' +
                    '<td>' + grade.studentName + '</td>' +
                    '<td>' + grade.className + '</td>' +
                    '<td>' + grade.major + '</td>' +
                    '<td>' + grade.grade + '</td>' +
                    '</tr>'
                );
            }
        },
        error: function (e) {
            alert("error")
        }
    });
}