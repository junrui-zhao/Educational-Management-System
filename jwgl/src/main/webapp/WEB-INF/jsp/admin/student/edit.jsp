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
            <div id="passwordModal" class="modal fade">
                <div class="modal-dialog">

                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title text-center">
                                <span class="glyphicon glyphicon-phone"> </span>请输入新密码:
                            </h3>
                        </div>

                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-2">
                                    <input type="text" name="newPassword" id="newPassword"
                                           placeholder="填写新密码^o^" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <%--验证信息--%>
                            <span id="passwordMessage" class="glyphicon"> </span>
                            <button type="button" id="changeBtn" class="btn btn-success" onclick="changePassword()">
                                <span class="glyphicon glyphicon-phone"></span>
                                Submit
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div>
                <h3>编辑学生</h3>

                <table>
                    <tr>
                        <td>
                            <h5>学号：</h5>
                        </td>
                        <td>
                            <label>${studentId}</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>姓名：</h5>
                        </td>
                        <td>
                            <input name="name" id="name"  type="text" value="${student.studentName}"  class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>班级：</h5>
                        </td>
                        <td>
                            <input name="className" id="className"  type="text" value="${student.className}" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>专业：</h5>
                        </td>
                        <td>
                            <input name="major" id="major"  type="text" value="${student.major}" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-primary edit-btn" onclick="insertStudent();">确认编辑</button>
                        </td>
                        <td align="right">
                            <button class="btn btn-primary edit-btn" onclick="showModal()">修改密码</button>
                        </td>
                    </tr>
                </table>
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
<script type="text/javascript">
    function insertStudent(){
        var id = ${studentId};
        var name = $('#name').val();
        var className = $('#className').val();
        var major = $('#major').val();
        if(id == "" || name == "" || className == "" || major == ""  )
            alert("输入数据不能为空");
        else{
            $.ajax({
                type: 'POST',
                url: '/admin/student/updateStudent' ,
                dataType: 'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({
                    id:id,
                    name:name,
                    className:className,
                    major:major
                }),  //提交json字符串数组
                success:function(data){
                    if(data=="SUCCESS"){
                        alert("修改成功");
                        window.location.href = "/admin/student/list";
                    }
                    else if(data=="ID_LENGTH_FAIL"){
                        alert("输入学号应该为十位");
                    }
                    else{
                        alert("该同学已存在！");
                    }
                },
                error:function(){
                    alert("修改数据失败！");
                }
            });
        }

    }
    function showModal() {
        var password=$('#passwordModal');
        password.modal({
            show:true
        } );
    }
    function changePassword() {
        var id = ${studentId};
        var newpassword = $('#newPassword').val();
        if(newpassword.length < 8){
            alert("密码长度至少为八位");
        }else {
            $.ajax({
                type: 'POST',
                url: '/admin/student/changePassword' ,
                dataType: 'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({
                    id: id,
                    newPassword: newpassword
                }),  //提交json字符串数组
                success:function(data){
                    alert("修改密码成功！");
                },
                error:function(){

                    alert("修改密码失败！");
                }
            });
        }
    }
</script>
</body>
</html>
