<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>


    <title></title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">

    <h2 class="text-center">请输入您的课程信息</h2>
    <c:if test="${message!=null}">
        <p>${message}</p>
    </c:if>
    <form action="/admin/add">
        <div class="form-group">
            <label for="course">请选课程</label>
            <select id="course" class="form-control" name="course.id">
                <c:forEach var="course" items="${courses}">
                    <option value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
            <label for="day">请选哪一天</label>
            <select id="day" class="form-control" name="day">
                <option value="1">周一</option>
                <option value="2">周二</option>
                <option value="3">周三</option>
                <option value="4">周四</option>
                <option value="5">周五</option>
                <option value="6">周六</option>
                <option value="7">周日</option>
            </select>
        </div>
        <div class="form-group">
            <label>开始时间</label>
            <input type="time" class="form-control" id="beginTime" name="beginTime" value="19:00">
            <label style="margin-left: 10px;">结束时间</label>
            <input type="time" class="form-control" id="endTime" name="endTime" value="21:30">
        </div>
        <p id="err" class="text-danger"></p>
        <input type="submit" class="btn btn-primary" value="提交"/>
        </select>
    </form>
</div>
</body>
<script>

    function test() {
        console.log($("#endTime")[0].value);
    }

    $("form").submit(function () {//两个判断  1  起始时间小于结束时间,  2   不能占用学生的休息时间

        var start = $("#startTime")[0].value;
        var end = $("#endTime")[0].value;
        var sv = start.split(":")[0] - 0;
        var ev = end.split(":")[0] - 0;
        if (sv < 7) {

            $("#err").html("最早7点上课");

            return false;
        }
        if (ev > 22) {
            $("#err").html("最晚22点放学")


            return false;
        }
        if (start >= end) {
            $("#err").html("开课时间必须小于结束时间");
            return false;
        }
        //12-14 点为休息时间,不能够上课

        var isCo = false;//判断是否冲突
        if (sv >= 12 && sv <= 14 || ev >= 12 && ev <= 14) {
            isCo = true;
        }
        if (sv <= 12 && ev >= 14) {

            isCo = true;
        }
        if (isCo) {

            $("#err").html("上课时间不能占用休息时间");
            return false;
        }
    });

</script>
</html>
