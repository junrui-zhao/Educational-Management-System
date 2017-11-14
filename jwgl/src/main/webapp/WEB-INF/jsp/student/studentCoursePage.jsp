<%--
  Created by IntelliJ IDEA.
  User: Phoenix
  Date: 2017/4/11
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
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
    <%@include file="../common/header.jsp"%>
    <script src="/js/studentCourse.js" type="text/javascript"></script>
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
                                    <a href="#" class="btn btn-default ">登出</a>
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
                <small>选课系统</small>
            </h1>
            <!--位置导航-->
            <ol class="breadcrumb">
                <li><a href="#">Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <section class="content">
            <div>
                <h2 align="center">学生选课列表</h2>
                <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>课程编号</th>
                            <th>课程名称</th>
                            <th>课程学分</th>
                            <th>课程性质</th>
                            <th>开设院系</th>
                            <th>课程容量</th>
                            <th>课程余量</th>
                            <th>确认/取消选课</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${requestScope.selectedcompulsory}" var="selectedcompulsory">
                            <tr>
                                <td>${selectedcompulsory.courseId}</td>
                                <td>${selectedcompulsory.courseName}</td>
                                <td>${selectedcompulsory.credit}</td>
                                <td>${selectedcompulsory.nature}</td>
                                <td>${selectedcompulsory.department}</td>
                                <td>${selectedcompulsory.peopleNum}</td>
                                <td>${selectedcompulsory.leftNum}</td>
                                <td>
                                    <button class="btn btn-danger" type="button" disabled="disabled">不可退选</button>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:forEach items="${requestScope.selectedoptional}" var="selectedoptional">
                            <tr>
                                <td>${selectedoptional.courseId}</td>
                                <td>${selectedoptional.courseName}</td>
                                <td>${selectedoptional.credit}</td>
                                <td>${selectedoptional.nature}</td>
                                <td>${selectedoptional.department}</td>
                                <td>${selectedoptional.peopleNum}</td>
                                <td>${selectedoptional.leftNum}</td>
                                <td>
                                    <button class="btn btn-warning quit-btn" type="button">退选</button>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:forEach items="${requestScope.unselected}" var="unselected">
                            <tr>
                                <td>${unselected.courseId}</td>
                                <td>${unselected.courseName}</td>
                                <td>${unselected.credit}</td>
                                <td>${unselected.nature}</td>
                                <td>${unselected.department}</td>
                                <td>${unselected.peopleNum}</td>
                                <td>${unselected.leftNum}</td>
                                <td>
                                    <button class="btn btn-info select-btn" type="button">选课</button>
                                </td>
                                <td hidden="hidden">${unselected.openId}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            </div>
        </section>

        
    </div>

    <!--页脚-->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            各位大佬强力驱动
        </div>
        <strong>Copyright &copy; 2017 <a href="#">CS1404</a>.</strong> All rights reserved.
    </footer>
    
</div>
</body>
</html>
