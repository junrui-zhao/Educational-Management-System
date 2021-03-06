
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script type="text/javascript">
    function ahead(){
        if(${page} == 1) {
            alert("当前已是第一页");
        } else{
            window.location.href="../list/${page-1}";
        }
    }

    function behind(){
        if(${pages} == ${page}){
            alert("当前已是最后一页");
        } else{
            window.location.href="../list/${page+1}";
        }
    }
</script>
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
                <small>毕设系统</small>
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
                <h2>课题列表</h2>
            </div>
            <%--<div class="input-group">--%>
                <%--&lt;%&ndash;<input id="keyword" type="text" class="form-control" placeholder="请输入关键字" value=${keyword}>&ndash;%&gt;--%>
                <%----%>
                <%--<span class="input-group-btn">--%>
                    <%--<button class="btn btn-default" type="button" href="../delete/${subjectItem.subjectId}">查询</button>--%>
                <%--</span>--%>
            <%--</div>--%>
            <form action="../list/1" role = "form" name="subjectForm" method="post">
                <input id = "keyword" name="keyword" type="text" class="form-control" placeholder="请输入关键字">
                <button class="btn btn-default" type="submit">查询</button>
            </form>
            <div class="table-div">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>课题号</th>
                        <th>指导教师</th>
                        <th>课题名称</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="subjectItem">
                        <tr>
                            <td>${subjectItem.subjectId}</td>
                            <td>${subjectItem.teacherName}</td>
                            <td>${subjectItem.subjectName}</td>
                            <td>
                                <c:if test="${subjectItem.subjectStatus == 0}">
                                    未选学生
                                </c:if>
                                <c:if test="${subjectItem.subjectStatus == 1}">
                                    已选学生
                                </c:if>
                            </td>
                            <td>

                                    <%--</a>--%>
                                    <c:if test="${subjectItem.subjectStatus == 0}">
                                        <a class="btn btn-info" href="../${subjectItem.subjectId}">
                                            查看
                                        </a>
                                        <a class="btn btn-danger" onclick="delete_confirm()" href="../delete/${subjectItem.subjectId}">删除</a>
                                    </c:if>
                                    <c:if test="${subjectItem.subjectStatus == 1}">
                                        <a class="btn btn-info" href="../${subjectItem.subjectId}/1">
                                            查看
                                        </a>
                                        <a class="btn btn-warning" onclick="cancel_confirm()" href="../cancel/${subjectItem.subjectId}">取消</a>
                                    </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div align="right">
                    <td><a class="btn btn-info" href="../list/1" target="_self">第一页</a></td>
                    <td><a class="btn btn-info" href="#" onclick="ahead()" target="_self">上一页</a></td>
                    <td><a class="btn btn-info" href="#" onclick="behind()" target="_self">下一页</a></td>
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
    function delete_confirm() {
        event.returnValue = confirm("删除是不可恢复的，你确认要删除吗？");
    }
    function cancel_confirm() {
        event.returnValue = confirm("点击确认后该课题将恢复为初始状态，你确认吗？");
    }
</script>
</body>
</html>
