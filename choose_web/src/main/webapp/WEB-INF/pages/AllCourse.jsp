
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
			.container		.nav li a{
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
			<h3 class="text-center">全部课程</h3>

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
             
					 <li index="5">
					    <a href="#">周五</a></li>
						
						 <li index="6">
						   <a href="#">周六</a></li>
						   
						   	 <li index="7">
						     <a href="#">周日</a></li>
            </ul>
			<!-- 点那天显示那一天的课程  需要传递标记才能知道当显示的日期   -->
			<div class="courses">	
				<div class="course">
					<div class="time text-center">7:00-9:00</div>
					<div class="className text-center"><h3>FCC前端培训 <small>小赵</small> </h3>
					<p style="display: inline-block; ">剩余座位:<span style="color: #E7505A;font-size: 20px;">20</span></p> 
					<button class="btn" style="float: right; float: bottom;">删除</button>
					</div>
				</div>
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
