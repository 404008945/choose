<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/4/1
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .contain{
            width: 100px;
            height: 100px;
            background:#4DC741;
            border-radius: 50%;
            overflow: hidden;
            margin:0 auto;
            text-align: center;
            text-align: center;

        }
    </style>
    <title>Title</title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
    <div class="contain" style="margin-top: 50px">
        <span class="glyphicon glyphicon-ok" style="font-size: 90px;color: white;line-height: 100px;"></span>
    </div>
<p class="text-center text-info" style="font-size: 20px">${message}</p>

</body>
<script>
    $(window).load(function () {
        setTimeout(function () {
            location="${url}";
        },2000)
    });
</script>
</html>
