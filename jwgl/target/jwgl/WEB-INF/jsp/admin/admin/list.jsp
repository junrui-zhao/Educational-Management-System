<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gao27024037
  Date: 2017/3/26
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <%@include file="../../common/head.jsp"%>
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
                                    <a href="#" class="btn btn-default btn-flat">登出</a>
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
                <li class="header">毕业设计系统</li>
                <li><a href="/admin/subject/list"><i class="fa fa-link"></i><span>毕设课题管理</span></a></li>
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
            <div class="panel-heading text-center">
                <h2>管理员列表</h2>
            </div>
            <div>
                <button class="btn btn-primary" onclick="window.location.href='/admin/admin/insertPage'">添加管理员</button>
            </div>
            <br>
            <div class="input-group">
                <input id="keyword" type="text" class="form-control" placeholder="请输入关键字" value=${keyword}>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="search()">查询</button>
                </span>
            </div>

            <div class="table-div">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>修改密码/删除</th>
                    </tr>
                    </thead>

                    <c:choose>
                        <c:when test="${list.size() != 0}">
                            <c:forEach var="admin" items="${list}">
                                <tr>
                                    <td>${admin.adminId}</td>
                                    <td>
                                        <button class="btn btn-primary"  onclick="showModal(${admin.adminId})">修改密码</button>
                                        <button class="btn btn-danger" onclick="delete_confirm(${admin.adminId})">删除</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <td colspan="4" align="center">对不起，没有相关数据</td>
                        </c:otherwise>
                    </c:choose>
                </table>
                <div class="panel-button" align="right">
                    <a class="btn btn-info" onclick="pagehome()">第一页</a>
                    <a class="btn btn-info" onclick="pageup()" >上一页</a>
                    <a class="btn btn-info" onclick="pagedown()">下一页</a>
                </div>
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
<%--修改密码弹出层--%>
<div id="passwordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    请输入新密码:
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="password" id="password" maxlength='20'
                               placeholder="请输入8位以上密码" class="form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span id="passwordMessage" class="glyphicon"> </span>
                <button type="button" id="changeBtn" class="btn btn-success " onclick="updatePassword()">
                    修改密码
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>

<script type="text/javascript">

    var keyword = $('#keyword').val();
    //显示弹出层
    var pwmodal=$('#passwordModal');
    var adminid;
    function showModal(adminId) {
        adminid=adminId;
        pwmodal.modal({
            show:true
        } );
    }

    //提交修改密码
    function updatePassword(){
        var password = $("#password").val();
        if(password == "" || password.length < 8){
            alert("请输入8位以上密码");
        }else{
            $.ajax({
                type: 'POST',
                url: '/admin/admin/updatePassword' ,
                dataType: 'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({
                    adminId:adminid,
                    adminPassword:password,
                }),  //提交json字符串数组
                success:function(data){
                    alert("修改密码成功");
                    $('#passwordModal').modal('hide')
                },
                error:function(){
                    alert("修改密码失败！");
                }
            });
        }
    }

    function search()
    {
        var keyword = document.getElementById("keyword").value;
        window.location.href = "/admin/admin/list/1?keyword=" + keyword;
    }

    //翻第一页
    function pagehome() {
        if(${page} == 1) {
            alert("已经是第一页了");
        } else {
            window.location.href = "/admin/admin/list/1?keyword=" + keyword;
        }
    }

    //下翻页
    function pagedown() {
        if((${page}) < ${TotalPages}) {
            window.location.href = "/admin/admin/list/${page + 1}?keyword=" + keyword;
        } else {
            alert("已经是最后一页了");
        }
    }

    //上翻页
    function pageup() {
        if(${page} == 1) {
            alert("已经是第一页了");
        } else {
            window.location.href = "/admin/admin/list/${page - 1}?keyword=" + keyword;
        }
    }

    //删除确认
    function delete_confirm(adminId){
        var page = ${page};
        var id = adminId;
        var url = "/admin/admin/delete";
        if(confirm("确定要删除该教师吗？")){

            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({
                    id:id,
                    page:page
                }),  //提交json字符串数组
                success:function(data){
                    if(data=="SUCCESS"){
                        if(${list.size()} == 1 && ${TotalPages} != 1) {
                            window.location.href = "/admin/admin/list/${page - 1}?keyword=" + keyword;
                        } else {
                            window.location.href = "/admin/admin/list/${page}?keyword=" + keyword;
                        }
                    }
                    else{
                        alert("删除失败");
                    }
                },
                error:function(){
                    alert("删除失败");
                }
            });
        }
    }
</script>

</html>