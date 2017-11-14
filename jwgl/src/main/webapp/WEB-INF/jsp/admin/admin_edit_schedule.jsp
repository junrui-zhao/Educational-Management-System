<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phoenix
  Date: 2017/4/11
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>教务管理系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/dist/css/skins/skin-blue.min.css">
    <%@include file="../common/head.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!--页首-->
    <header class="main-header">
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">导航开关</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">

                <ul class="nav navbar-nav">
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/dist/img/avatar_ani.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">${sessionScope.get("userid")}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="/dist/img/avatar_ani.jpg" class="img-circle" alt="User Image">
                                <p>
                                    Talk is cheap show me the code.
                                </p>
                            </li>
                            <!-- Menu Body -->

                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="#" class="btn btn-default btn-flat" onclick="location.href='http://localhost:8080'">登出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!--侧边-->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/dist/img/avatar_ani.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${sessionScope.get("userid")}</p>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">排课系统</li>
                <li><a href="/admin/${sessionScope.get("userid")}"><i class="fa fa-link"></i><span>主页</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/addopen"><i class="fa fa-link"></i><span>增加开设信息</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/open"><i class="fa fa-link"></i><span>课程/教学活动安排</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/room"><i class="fa fa-link"></i><span>空教室查询</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/teacherinfo"><i class="fa fa-link"></i><span>查询教师课表</span></a></li>
                <li><a href="/admin/teacher/list"><i class="fa fa-link"></i><span>教师表管理</span></a></li>
                <li><a href="/admin/classroom/list"><i class="fa fa-link"></i><span>教室表管理</span></a></li>
                <li><a href="/admin/student/list"><i class="fa fa-link"></i><span>学生表管理</span></a></li>
                <li><a href="/admin/course/list"><i class="fa fa-link"></i><span>课程表管理</span></a></li>
                <li><a href="/admin/term/list"><i class="fa fa-link"></i><span>学期表管理</span></a></li>
                <li><a href="/admin/info/list"><i class="fa fa-link"></i><span>通知表管理</span></a></li>
                <li><a href="/admin/admin/list"><i class="fa fa-link"></i><span>管理员表管理</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/grade"><i class="fa fa-link" ></i><span>成绩统计</span></a></li>
                <li class="header">选课系统</li>
                <li><a href="/admin/${sessionScope.get("userid")}/search"><i class="fa fa-link"></i><span>信息查询</span></a></li>
                <li><a href="/admin/${sessionScope.get("userid")}/detail"><i class="fa fa-link"></i><span>情况统计</span></a></li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!--核心-->
    <div class="content-wrapper">
        <!--标题-->
        <section class="content-header">
            <h1>
                北京化工大学教务管理系统
                <small>后台系统</small>
            </h1>
            <!--位置导航-->
            <ol class="breadcrumb">
                <li><a href="#">Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <!--网页正文-->
        <section class="content">
            <div id = "add-div">
                <table>
                    <tr>
                        <td>
                            <h5>教师信息</h5>
                        </td>
                        <td>
                            <select id="select-teacher" class="form-control" style="width: auto;">
                                <c:forEach items="${teacher}" var="teacherItem">
                                    <option value="${teacherItem.teacherId}">${teacherItem.teacherId}-----${teacherItem.teacherName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><h5>教室信息</h5></td>
                        <td>
                            <select id="select-classroom" class="form-control" style="width: auto;">
                                <c:forEach items="${classroom}" var="classroomItem">
                                    <option value="${classroomItem.classroom}">${classroomItem.classroom}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><h5>面向班级</h5></td>
                        <td>
                            <select id="select-class" class="form-control" style="width: auto;">
                                <c:forEach items="${classinfo}" var="classItem">
                                    <option value="${classItem}">${classItem}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>星期</h5>
                        </td>
                        <td>
                            <select id="day" class="form-control" style="width: auto;">
                                <option value="1"">一</option>
                                <option value="2">二</option>
                                <option value="3">三</option>
                                <option value="4">四</option>
                                <option value="5">五</option>
                                <option value="6">六</option>
                                <option value="7">日</option>
                            </select>
                        </td>
                        <td>
                            <h5>第</h5>
                        </td>
                        <td>
                            <select id="from_index" class="form-control" style="width: auto;">
                                <option value="1"">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                            </select>
                        </td>
                        <td>
                            <h5>节</h5>
                        </td>
                        <td>
                            <h5>至第</h5>
                        </td>
                        <td>
                            <select id="to_index" class="form-control" style="width: auto;">
                                <option value="1"">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                            </select>
                        </td>
                        <td>
                            <h5>节</h5>
                        </td>
                        <td>
                            <h5>第</h5>
                        </td>
                        <td>
                            <select id="from_week" class="form-control" style="width: auto;">
                                <option value="1"">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                            </select>
                        </td>
                        <td>
                            <h5>周</h5>
                        </td>
                        <td>
                            <h5>至第</h5>
                        </td>
                        <td>
                            <select id="to_week" class="form-control" style="width: auto;">
                                <option value="1"">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                            </select>
                        </td>
                        <td>
                            <h5>周</h5>
                        </td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-primary edit-btn">修改</button></td>
                    </tr>
                </table>
                <br/>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!--页脚-->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            各位大佬强力驱动
        </div>
        <strong>Copyright &copy; 2017 <a href="#">CS1404</a>.</strong> All rights reserved.
    </footer>
</div>

<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script>
    $('#select-teacher').val(${result.teacherId});
    $('#select-classroom').val('${result.classroom}');
    $('#select-class').val('${result.className}');
    $('#day').val('${result.day}');
    $('#from_week').val('${result.startWeek}');
    $('#to_week').val('${result.endWeek}');
    $('#from_index').val('${result.startTime}');
    $('#to_index').val('${result.endTime}');

    $('.edit-btn').on('click', function () {
        var openId = ${result.openId};
        var teacherId = $('#select-teacher').val();
        var classroom = $('#select-classroom').val();
        var className = $('#select-class').val();
        var day = $('#day').val();
        var startWeek = $('#from_week').val();
        var endWeek = $('#to_week').val();
        var startTime = $('#from_index').val();
        var endTime = $('#to_index').val();

        var url = "http://localhost:8080/admin/editschedule";
        $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                id: ${arrid},
                openId: openId,
                teacherId: teacherId,
                classroom: classroom,
                className: className,
                day: day,
                startWeek: startWeek,
                endWeek: endWeek,
                startTime: startTime,
                endTime: endTime
            }),
            success: function (jsonResult) {
                var resType = jsonResult["result"]["resultType"];
                var clashList = jsonResult["result"]["scheduleEntities"];
                if (resType === "SUCCESS") {
                    alert("修改成功");
                    window.history.back();
                } else if(resType === "KEY_FAIL") {
                    alert("课程号不存在");
                } else if(resType === "CLASH_FAIL") {
                    alert("课程冲突，检查冲突课程");
                    $('#clash-schedule').show();
                    $('#clash-table-div').empty();
                    $('#clash-table-div').append(
                        '<table class="table table-hove clash-table"><thead><tr>'+
                        '<th>安排id</th><th>开设id</th><th>课程id</th><th>课程名称</th><th>上课教室</th><th>面向班级</th><th>上课星期</th><th>开始节次</th>'+
                        '<th>结束节次</th><th>开始周</th><th>结束周</th><th>操作</th></tr></thead>'+'</table>'
                    );
                    for (var clashIndex in clashList) {
                        var curritem = clashList[clashIndex];
                        $('.clash-table').append(
                            '<tr>'+
                            '<td>'+curritem.id+'</td>'+
                            '<td>'+curritem.openId+'</td>'+
                            '<td>'+curritem.courseId+'</td>'+
                            '<td>'+curritem.courseName+'</td>'+
                            '<td>'+curritem.classroom+'</td>'+
                            '<td>'+curritem.className+'</td>'+
                            '<td>'+curritem.day+'</td>'+
                            '<td>'+curritem.startTime+'</td>'+
                            '<td>'+curritem.endTime+'</td>'+
                            '<td>'+curritem.startWeek+'</td>'+
                            '<td>'+curritem.endWeek+'</td>'+
                            '<td>'+'<button class="btn btn-info clash-edit-btn">编辑</button>'+'\n'+
                            '<button class="btn btn-danger clash-delete-btn">删除</button>'+'</td>'+
                            '</tr>'
                        );
                    }
                    $('.clash-edit-btn').on('click', function () {
                        var adminId = ${userid};
                        var arrId = $(this).parent().parent().find("td:nth-child(1)").text();
                        var url = "http://localhost:8080/Manager/"+adminId+"/schedule/"+arrId+"/edit";
                        window.location.href = url;
                    });

                    $('.clash-delete-btn').on('click', function () {
                        var arrId = $(this).parent().parent().find("td:nth-child(1)").text();
                        var url = "http://localhost:8080/Manager/deleteschedule";
                        $(this).parent().parent().remove();
                        $.ajax({
                            type: 'POST',
                            url: url,
                            contentType: 'application/json',
                            dataType: 'json',
                            data: JSON.stringify({
                                id: arrId
                            }),
                            success: function (jsonResult) {
                                var resType = jsonResult["result"];
                                if (resType === "SUCCESS") {
                                    alert("删除成功");
                                } else if(resType === "ID_FAIL") {
                                    alert("不存在的open");
                                } else {
                                    alert("未知错误");
                                }
                            },
                            error: function (e) {
                                alert("添加异常");
                            }
                        });
                    });

                } else if(resType === "ID_FAIL") {
                    alert("不存在的open");
                } else if(resType === "TIME_FAIL") {
                    alert("时间错误");
                } else {
                    alert("未知错误");
                }
            },
            error: function (e) {
                alert("添加异常");
            }
        });
    });

</script>

</body>
</html>
