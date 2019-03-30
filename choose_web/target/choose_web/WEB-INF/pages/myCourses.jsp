<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">

		
		<title>管理全部课程</title>
		<style>
			.nav li{
				width: 14.2%;
				text-align: center;
			}
			.nav li a{
				padding: 10px;
				
			} 
			.course{
					border: solid 1px #ccc;
			}
			.courses .time{
				border: solid 1px #ccc;
				background: #F7F7F7;
			}
			.className{
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
			<div class="courses">	
				<div class="course">
					<div class="time text-center">7:00-9:00</div>
					<div class="className text-center"><h3>FCC前端培训 <small>小赵</small> </h3>
					<p style="display: inline-block; ">我的位置:<span style="color: #E7505A;font-size: 20px;">101</span>号机</p> 
					<button class="btn" style="float: right; float: bottom;">取消</button>
				</div>
				</div>
			</div>
			
	</div>
			</div>
	</body>
</html>
