<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/tag.jsp"%>
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
                    <div class="panel-heading text-center">
                        <h2>课程列表</h2>
                    </div>
					<div>
							<a class = "btn btn-primary" href="/admin/course/insertInfo" target="_self">添加课程</a>
                            <a class="btn btn-primary " href = "/admin/course/download">下载报表</a>
                            <a class="btn btn-primary" href = "/admin/course/upload">上传报表</a>
                    </div>
                    <br>
					<div class="input-group">
                        <input type="text" class="form-control" placeholder="请输入关键字" id="searchKey" name="searchKey" value=${searchKey}>
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default" onclick="searchCourse()">搜索</button>
                </span>
                    </div>
                    <div class="panel-body" >
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>课程id  </th>
                                <th>课程名称</th>
                                <th>课程学分</th>
                                <th>课程性质</th>
                                <th>开设院系</th>
                                <th> 编辑/删除 </th>
                            </tr>
                            </thead>
                            <tbody>
							<c:choose>
                                    <c:when test="${list.size() != 0}">
                            <c:forEach items="${list}" var="course">
                                <tr>
                                    <td>${course.courseId}</td>
                                    <td>${course.courseName}</td>
                                    <td>${course.credit}</td>
                                    <td>${course.nature}</td>
                                    <td>${course.department}</td>
                                    <td><a class="btn btn-info" href="/admin/course/edit/${course.courseId}" target="_self">编辑</a>
                                        <a class="btn btn-danger" onclick="delCourse('${course.courseId}')" target="_blank">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
							 </c:when>
                                    <c:otherwise>
                                        <td colspan="4" align="center">对不起，没有相关数据</td>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                        <div align="right">
                            <a class="btn btn-primary" href="/admin/course/list/1" target="_self">第一页</a>
							<a class="btn btn-primary" href="#" onclick="ahead()" target="_self">前一页</a>
                            <a class="btn btn-primary" href="#" onclick="behind()" target="_self">下一页</a>
                        </div>
                    </div>
               




            <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
            <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
            </body>
            </html>
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
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/dist/js/app.min.js"></script>
<script type="text/javascript">
                    function ahead(){
						var searchKey = request('searchKey');
							if (searchKey == undefined){
								searchKey = "";
							}
                        if(${page} == 1 ) {
                            alert("当前已是第一页");
                        }
                    else{
                            window.location.href="/admin/course/list/${page-1}";
                        }
                    }
                    function behind(){
						var searchKey = request('searchKey');
						if (searchKey == undefined){
							searchKey = "";
						}
                        if(${pages} == ${page}){
                            alert("当前已是最后一页");
                        } else{
                            window.location.href="/admin/course/list/${page+1}";
                        }
                    }
                    function delCourse(courseId){
                        var page = ${page};
                        var courseId = courseId;
                        var url = "/admin/course/delete";
                        if(confirm("确定要删除该课程吗？")) {
                            $.ajax({
                                type: 'POST',
                                url: url,
                                dataType: 'json',
                                contentType: 'application/json;charset=UTF-8',
                                data: JSON.stringify({
                                    courseId: courseId,
                                    page: page,
                                }),  //提交json字符串数组
                                success:function(data){
                                    if(data=="SUCCESS"){
                                        window.location.reload();
                                    }
                                    else{
                                        alert("删除失败");
                                    }
                                },
                                error: function (textStatus, errorThrown) {
                                    console.log(textStatus);
                                    alert("删除失败");
                                }
                            });
                        }
                    }
                    function searchCourse() {
                        var searchKey = $('#searchKey').val();
                        window.location.href="/admin/course/list/1?searchKey="+searchKey;
                    }

                    function request(strParame) {
                        var args = new Object( );
                        var query = location.search.substring(1);
                        var pairs = query.split("&"); // Break at ampersand
                        for(var i = 0; i < pairs.length; i++) {
                            var pos = pairs[i].indexOf('=');
                            if (pos == -1) continue;
                            var argname = pairs[i].substring(0,pos);
                            var value = pairs[i].substring(pos+1);
                            value = decodeURIComponent(value);
                            args[argname] = value;
                        }
                        return args[strParame];
                    }
                    function backList(){
                        var searchKey = "";
                        document.cookie="courseProperty="+searchKey+";path= /admin/course/";
                        window.location.href="/admin/course/list/1";
                    }
                </script>
</body>
</html>

