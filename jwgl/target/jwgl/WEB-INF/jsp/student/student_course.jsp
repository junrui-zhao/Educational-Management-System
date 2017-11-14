c <%--
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
            <div>
                <table>
                    <tr>
                        <td>
                            <h5>学年选择</h5>
                        </td>
                        <td>
                            <select id="schoolyear" class="form-control" style="width: auto;">
                                <c:forEach items="${allterm}" var="term">
                                    <option value="${term.schoolYear}">${term.schoolYear}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <h5>学期选择</h5>
                        </td>
                        <td>
                            <select id="term" class="form-control" style="width: auto;">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                        </td>
                        <td><button class="btn btn-primary sub-btn">提交</button></td>
                    </tr>
                </table>
            </div>
            <div>
                <table id="course-table" width="100%" height="800px" border="1" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
                    <tr>
                        <td align="center">节次/星期</td>
                        <td align="center">星期一</td>
                        <td align="center">星期二</td>
                        <td align="center">星期三</td>
                        <td align="center">星期四</td>
                        <td align="center">星期五</td>
                        <td align="center">星期六</td>
                        <td align="center">星期日</td>
                    </tr>
                    <%
                        String[][] course = (String[][])request.getAttribute("courseTable");
                        for(int i=1;i<=13;++i) {
                            out.print("<tr>");
                            out.print("<td align=\"center\"><strong>第"+i+"节</strong></td>");
                            for(int j=1;j<=7;++j) {
                                out.print("<td align=\"center\">"+course[i][j]+"</td>");
                            }
                            out.print("</tr>");
                        }
                    %>
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
<script>
    //函数说明：合并指定表格（表格id为_w_table_id）指定列（列数为_w_table_colnum）的相同文本的相邻单元格
    //参数说明：_w_table_id 为需要进行合并单元格的表格的id。如在HTMl中指定表格 id="data" ，此参数应为 #data
    //参数说明：_w_table_colnum 为需要合并单元格的所在列。为数字，从最左边第一列为1开始算起。
    function _w_table_rowspan(_w_table_id,_w_table_colnum){
        _w_table_firsttd = "";
        _w_table_currenttd = "";
        _w_table_SpanNum = 0;
        _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");
        _w_table_Obj.each(function(i){
            if(i==0){
                _w_table_firsttd = $(this);
                _w_table_SpanNum = 1;
            }else{
                _w_table_currenttd = $(this);
                if(_w_table_firsttd.text()==_w_table_currenttd.text() && _w_table_firsttd.text() != ""){
                    _w_table_SpanNum++;
                    _w_table_currenttd.hide(); //remove();
                    _w_table_firsttd.attr("rowSpan",_w_table_SpanNum);
                    _w_table_firsttd.attr("align","center");
                }else{
                    _w_table_firsttd = $(this);
                    _w_table_SpanNum = 1;
                }
            }
        });
    }

</script>
<script>
    $(document).ready(function(){
        _w_table_rowspan("#course-table",2);
        _w_table_rowspan("#course-table",3);
        _w_table_rowspan("#course-table",4);
        _w_table_rowspan("#course-table",5);
        _w_table_rowspan("#course-table",6);
        _w_table_rowspan("#course-table",7);
        _w_table_rowspan("#course-table",8);
    });
</script>
</body>
</html>
