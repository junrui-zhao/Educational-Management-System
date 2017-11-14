<%--
  Created by IntelliJ IDEA.
  User: cxd
  Date: 2017/4/12
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>teacher</title>
    <%@include file="common/header.jsp"%>
</head>
<body>
<script>alert("输出课程ID不存在或输出错误")
</script>
<script type="text/JavaScript">
    window.location.href="/admin/${sessionScope.get("userid")}/search";
</script>

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

</body>
</html>
