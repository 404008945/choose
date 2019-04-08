<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">


    <title>管理全部课程</title>
    <style>
        .container	.nav li{
            width: 14.2%;
            text-align: center;
        }
        .container	.nav li a{
            padding: 10px;

        }
        .container	.course{
            border: solid 1px #ccc;
        }
        .container	.courses .time{
            border: solid 1px #ccc;
            background: #F7F7F7;
        }
        .container	.className{
            color: #3CD2DD;
        }

    </style>
</head>
<body>
<jsp:include page="nav.jsp"/>
<!--可以展示出所有的课程  用于管理员的管理和教师对课程的参考 -->
<div class="container">
    <h3 class="text-center">我的课程</h3>
    <!-- 点那天显示那一天的课程  需要传递标记才能知道当显示的日期   -->
    <ul class="nav nav-tabs tab">
        <li class="active" index="0">
            <a href="#">周一</a>
        </li>
        <li index="1">
            <a href="#">周二</a></li>
        <li index="2">
            <a href="#">周三</a></li>
        <li index="3">
            <a href="#">周四</a>
        </li>

        <li index="4">
            <a href="#">周五</a></li>

        <li index="5">
            <a href="#">周六</a></li>

        <li index="6">
            <a href="#">周日</a></li>
    </ul>
    <div class="courses">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==1}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>
    <div class="courses" style="display: none">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==2}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>
    <div class="courses" style="display: none">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==3}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="courses" style="display: none">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==4}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>
    <div class="courses" style="display: none">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==5}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>
    <div class="courses" style="display: none">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==6}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>
    <div class="courses" style="display: none">
        <c:forEach var="info" items="${infos}">
            <c:if test="${info.day==7}">
                <div class="course">
                    <div class="time text-center">${info.beginTime}-${info.endTime}</div>
                    <div class="className text-center">
                        <h3>${info.courseName}
                            <small>${info.teacherName}</small>
                        </h3>
                        <p style="display: inline-block; ">已选人数:<span style="color: #E7505A;font-size: 20px;">${info.remainSeats}</span>
                        </p>
                        <button class="btn btn-sm" onclick="location.href='/admin/removeMyCourse/${info.adminId}/${info.day}'" style="float: right; float: bottom;">取消</button>
                    </div>
                </div>
            </c:if>
        </c:forEach>

    </div>

</div>
</div>
</body>
<script>
    //点那个显示那个

    $(".container  ul li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index=$(this).attr("index");
        $(".courses").hide();
        $(".courses").eq(index).show();
        return false;
    });
    <c:if test="${day!=null}">
    $(".container  ul li").eq(${day-1}).click();
    </c:if>



</script>
</html>
