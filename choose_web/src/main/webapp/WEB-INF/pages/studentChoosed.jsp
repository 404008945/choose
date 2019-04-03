<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />

		<title></title>
	</head>
	<body>
	<jsp:include page="nav.jsp"/>
    <c:if test="${sessionScope.type==0}">
		<h4 class="text-center text-info">我选的所有课程 <small class="text-danger">点击课程可以取消预订课程</small></h4>
	</c:if>
	<c:if test="${sessionScope.type==1}">
		<h4 class="text-center text-info">我创建所有课程 <small class="text-danger">点击课程可以删除课程(已被预约不可删除)</small></h4>
	</c:if>
		<div id="canvasW" style="width: 1000px;height: 700px;margin: 0 auto;margin-top: 20px;position: relative;margin-bottom: 20px;">
           <canvas id="myCanvas"></canvas>
       </div>
	<script>
		function Course(startTime,endTime,courseName,dateNum,courseId)//   7:20    精确到分钟
		{
			this.startTime=startTime,
					this.endTime=endTime;
			this.courseName=courseName;
			this.dateNum=dateNum;//当前的偏移天数
			this.courseId=courseId;
		}
		var courseArr=new Array();


		 courseArr=[
			<c:forEach var="admin" items="${admins}" varStatus="status">
			<c:if test="${admins.size()>status.count}">
			new Course('${admin.beginTime}','${admin.endTime}','${admin.courseName}',${admin.day},${admin.adminId}),
			</c:if>
			<c:if test="${admins.size()==status.count}">
			new Course('${admin.beginTime}','${admin.endTime}','${admin.courseName}',${admin.day},${admin.adminId})
			</c:if>
			</c:forEach>
		];

		//还需要转换相对时间便宜
		var today=new Date().getDay();
		if(today==0)
		{
			today=7;
		}
		for(var i=0;i<courseArr.length;i++)
		{
			if(courseArr[i].dateNum>=today)
			{
				courseArr[i].dateNum-=today;//dataNum   向  数组下标的转化
			}else
			{
				courseArr[i].dateNum=7-today+ courseArr[i].dateNum;
			}
		}
		//courseArr.push(course);
	</script>
	<script src="/js/scriptforcourses.js"></script>
	<script>
		// $(".course").click(function(){
		// 	var str=$(this).html().split("<br>");
		// 	var con=confirm("您确定要取消预约"+str[0]+"吗?");
		// 	if(con)//向服务器发送取消预订的url
		// 	{
		// 		//取消课程 需要学生id和课程id  学生id通过session获得 课程的id需要带在url上
		// 		var courseId=$(this).attr("courseId");
		// 		location="";
		// 	}
		//
		// })
	</script>
	</body>
</html>
