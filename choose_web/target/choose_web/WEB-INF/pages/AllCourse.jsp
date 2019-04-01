<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理全部课程</title>
    <style>
        .container .nav li {
            width: 14.2%;
            text-align: center;
        }

        .container .nav li a {
            padding: 10px;

        }

        .container .course {
            border: solid 1px #ccc;
        }

        .container .courses .time {
            border: solid 1px #ccc;
            background: #F7F7F7;
        }

        .container .className {
            color: #3CD2DD;
        }
    </style>
</head>
<body>
<jsp:include page="nav.jsp"/>
<!--可以展示出所有的课程  用于管理员的管理和教师对课程的参考 -->
<div class="container">
    <h3 class="text-center">全部课程</h3>
    <c:if test="${message!=null}">
        <p>${message}</p>
    </c:if>
    <ul class="nav nav-tabs tab">
        <c:if test="${day==1}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/1">周一</a>
            </li>
        </c:if>
        <c:if test="${day!=1}">
            <li index="0">
                <a href="/admin/detailByDay/1">周一</a>
            </li>
        </c:if>
        <c:if test="${day==2}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/2">周二</a>
            </li>
        </c:if>
        <c:if test="${day!=2}">
            <li index="0">
                <a href="/admin/detailByDay/2">周二</a>
            </li>
        </c:if>
        <c:if test="${day==3}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/3">周三</a>
            </li>
        </c:if>
        <c:if test="${day!=3}">
            <li index="0">
                <a href="/admin/detailByDay/3">周三</a>
            </li>
        </c:if>
        <c:if test="${day==4}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/4">周四</a>
            </li>
        </c:if>
        <c:if test="${day!=4}">
            <li index="0">
                <a href="/admin/detailByDay/4">周四</a>
            </li>
        </c:if>
        <c:if test="${day==5}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/5">周五</a>
            </li>
        </c:if>
        <c:if test="${day!=5}">
            <li index="0">
                <a href="/admin/detailByDay/5">周五</a>
            </li>
        </c:if>
        <c:if test="${day==6}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/6">周六</a>
            </li>
        </c:if>
        <c:if test="${day!=6}">
            <li index="0">
                <a href="/admin/detailByDay/6">周六</a>
            </li>
        </c:if>
        <c:if test="${day==7}">
            <li class="active" index="0">
                <a href="/admin/detailByDay/7">周日</a>
            </li>
        </c:if>
        <c:if test="${day!=7}">
            <li index="0">
                <a href="/admin/detailByDay/7">周日</a>
            </li>
        </c:if>
    </ul>
    <!-- 点那天显示那一天的课程  需要传递标记才能知道当显示的日期   -->
    <div class="courses">
        <c:if test="${infos!=null}">
            <c:forEach var="info" items="${infos}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">剩余座位:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn" onclick="location.href='/admin/removeCourse/${info.adminId}'" style="float: right; float: bottom;">删除</button>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</div>
</body>
<script>
    $(".nav li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        $(".con>div").eq($(this).attr("index")).removeClass("hide").siblings().addClass("hide");
    })


</script>
</html>
