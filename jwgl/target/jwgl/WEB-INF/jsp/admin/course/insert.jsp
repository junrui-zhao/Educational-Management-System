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
            <div>
                    <h2>添加课程</h2>
                <table>
                    <tr>
                        <td>
                            <h5>课程号：</h5>
                        </td>
                        <td>
                            <input name="courseId" id="courseId"  type="text" value="" maxlength="10"  placeholder="请输入课程号" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>课程名：</h5>
                        </td>
                        <td>
                            <input name="courseName" id="courseName"  type="text"  value=""    class="form-control">                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>课程学分：</h5>
                        </td>
                        <td>
                            <input name="credit" id="credit"  type="text"  value="" maxlength="10"  class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>课程性质：</h5>
                        </td>
                        <td>
                            <select name="nature" id = "nature" type = "commit" >
                                <option>必修</option>
                                <option>选修</option>
                                <option>辅修</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h5>开设院系：</h5>
                        </td>
                        <td>
                            <select name="department" id = "department" type = "commit">
                                <option>化学工程学院</option>
                                <option>学生工作办公室</option>
                                <option >计算机科学与技术学院</option>
                                <option>文法学院</option>
                                <option>职业技术学院</option>
                                <option>材料科学与工程学院</option>
                                <option>经济管理学院</option>
                                <option>生命科学与技术学院</option>
                                <option>马克思主义学院</option>
                                <option>机电工程学院</option>
                                <option>理学院</option>
                                <option>国际教育学院</option>
                                <option>继续教育学院</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        <button class="btn btn-primary edit-btn" onclick="insertCourse();">添加新课程</button>
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
<script type = "text/javascript">
    function insertCourse(){
        var courseId = $('#courseId').val();
        var courseName = $('#courseName').val();
        var credit = $('#credit').val();
        var nature = $('#nature').val();
        var department = $('#department').val();
        if(courseId.length != 10 ){
            alert("ID请输入10位长度");
        }else if(courseName == ""){
            alert("课程名字不能为空");
        } else if(isNaN(credit)){
            alert("学分必须为数字或不填");
        }
        else if(credit >= 10||credit < 0){
            alert("输入数据太大或太小范围0=<x<10");
        }
        else{
            if(credit == ""){
                credit = -1;
            }

            $.ajax({
                type: 'POST',
                url: '/admin/course/insert' ,
                contentType: 'application/json',
                dataType: 'json',
                data:JSON.stringify({
                    courseId:courseId,
                    courseName:courseName,
                    credit:credit,
                    nature:nature,
                    department:department
                }),  //提交json字符串数组
                success:function(data){
                    if(data=="SUCCESS"){
                        alert("添加成功");
                        window.location.href = "/admin/course/list";
                    }
                    else if(data=="ID_LENGTH_FAIL"){
                        alert("输入的长度不合法，应为十位");
                    } else if(data=="CREDIT_FAIL"){
                        alert("学分应该为大于等于0小于10的数字");
                    } else if(data=="NATURE_FAIL"){
                        alert("课程性质应为：必修，选修，辅修");
                    }else if(data=="DEPARTMENT_FAIL"){
                        alert("学院名不对");
                    }
                    else{
                        alert("该课程已存在");
                    }
                },
                error:function(){
                    alert("该课程号已存在！");
                    console.log();

                }
            });
        }

    }
</script>
</body>
</html