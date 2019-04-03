<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="/bs/css/bootstrap.css">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<script src="/bs/js/jquery.min.js"></script>
		<script src="/bs/js/bootstrap.js"></script>
		<title></title>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid"> 
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#example-navbar-collapse">
            <span class="sr-only">切换导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">图灵预约系统</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">



            <c:if test="${sessionScope.type==0}">
            <li><a href="/user/chooseByOne">选课</a></li>
            <li><a href="/user/choosed">我的课表</a></li>
            <li><a href="/user/myChoosed">我选的所有课程</a></li>
            </c:if>
        <c:if test="${sessionScope.type==1}">
            <li><a href="/admin/addPage">新建课程</a></li>
            <li><a href="/admin/detailByDay/1">管理课程</a></li>
            <li><a href="/admin/teacherCourses">我的课表</a></li>
            <li><a href="/admin/teacherSelfCourses">我创建的所有课程</a></li>
            <li><a href="/admin/registerPage">创建教师用户(管理员)</a></li>
            <li><a href="/admin/manageCoursePage">管理学科(管理员)</a></li>
        </c:if>
            <c:if test="${sessionScope.type==null}">
                <li><a href="/user/loginPage">登录</a></li>
                <li><a href="/user/registerPage">注册</a></li>
            </c:if>
            <%--<li class="dropdown">--%>
                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                    <%--Java <b class="caret"></b>--%>
                <%--</a>--%>
                <%--<ul class="dropdown-menu">--%>
                    <%--<li><a href="#">jmeter</a></li>--%>
                    <%--<li><a href="#">EJB</a></li>--%>
                    <%--<li><a href="#">Jasper Report</a></li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li><a href="#">分离的链接</a></li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li><a href="#">另一个分离的链接</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
        </ul>
    </div>
    </div>
</nav>
	</body>
</html>
