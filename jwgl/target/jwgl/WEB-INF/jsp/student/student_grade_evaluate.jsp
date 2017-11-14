<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="header">管理系统</li>
                <li><a href="/student/${sessionScope.get("userid")}/course"><i class="fa fa-link"></i><span>个人课表</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/recomcourse"><i class="fa fa-link"></i><span>专业推荐课表</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/grade"><i class="fa fa-link"></i><span>查看成绩</span></a></li>
                <li class="header">毕业设计系统</li>
                <li><a href="/student/${sessionScope.get("userid")}/CheckConfirmedSubject"><i class="fa fa-link"></i><span>已通过的课题</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/CheckNotConfirmedSubject"><i class="fa fa-link"></i><span>未通过的课题</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/list/1"><i class="fa fa-link"></i><span>可申请的课题</span></a></li>
                <li class="header">选课系统</li>
                <li><a href="/student/${sessionScope.get("userid")}/courselist"><i class="fa fa-link"></i><span>查看全部课程</span></a></li>
                <li><a href="/student/${sessionScope.get("userid")}/selectlist"><i class="fa fa-link"></i><span>查看已选课程</span></a></li>
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
            <div class="panel panel-default">
                <div class="callout callout-info lead">
                    <p>课程评价</p>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <div class="row clearfix">
                            <div class="col-md-6 column">
                                <p class="lead">
                                    <small>课程名称:</small> <strong>${evaluate.courseEntity.courseName}</strong>
                                    <br><small>老师 :</small>  <strong>${evaluate.teacher.name} ${evaluate.teacher.title}</strong>
                                </p>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-md-6 column">
                                <form id="form" action="/student/${evaluate.studentId}/grade/${evaluate.openId}/updateEvaluate" enctype="multipart/form-data">
                                    <table name="table" class="table table-bordered" style="word-break:break-all; word-wrap:break-word;">
                                        <thead>
                                        <tr>
                                            <th class="span1">
                                                名称
                                            </th>
                                            <th class="span5">
                                                详细说明
                                            </th>
                                            <th class="span1">
                                                打分
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                学术水平
                                            </td>
                                            <td>
                                                如对教材内容的熟悉程度，
                                                <br>对重点、难点掌握的准确程度，
                                                <br>组织课堂讨论、选用编写教材、
                                                <br>选习题及试题的水平等方面
                                            </td>
                                            <td>
                                                <select id="xueshu" name="xueshu" class="form-control">
                                                    <option selected value=0 >请选择</option>
                                                    <option>5</option>
                                                    <option>4</option>
                                                    <option>3</option>
                                                    <option>2</option>
                                                    <option>1</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                教学水平
                                            </td>
                                            <td>
                                                如突出重点、分散难点的讲授方法是否恰当
                                                <br>是否结合学生实际,
                                                <br>是否达到教学大纲要求,
                                                <br>表达与板书是否清楚与有条理等
                                            </td>
                                            <td>
                                                <select id="jiaoxue" name="jiaoxue" class="form-control">
                                                    <option selected value=0>请选择</option>
                                                    <option>5</option>
                                                    <option>4</option>
                                                    <option>3</option>
                                                    <option>2</option>
                                                    <option>1</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                教学态度
                                            </td>
                                            <td>
                                                如是否认真备课，是否执行教学计划，
                                                <br>能否不断改进教学、更新教学内容，
                                                <br>能否既教书又育人等
                                            </td>
                                            <td>
                                                <select id="taidu" name="taidu" class="form-control">
                                                    <option selected value=0 >请选择</option>
                                                    <option>5</option>
                                                    <option>4</option>
                                                    <option>3</option>
                                                    <option>2</option>
                                                    <option>1</option>
                                                </select>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div>
                                        <label for="exampleInput">其他意见</label>
                                        <input id="other" name="other" type="text" value="" placeholder="可以为空" class="form-control" id="exampleInput" />
                                    </div>
                                    <div align="right">
                                        <button  class="btn btn-default" onclick="return check()">Submit</button>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6 column">
                            </div>
                        </div>
                    </div>
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

<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script type="text/javascript">
    if(${!(evaluate.evaluate==null||evaluate.evaluate.equals(""))}){
        alert("已完成评价！")
        window.history.back();
    }
</script>
</body>
</html>
