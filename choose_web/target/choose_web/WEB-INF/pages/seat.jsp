<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
	</body>
</html>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />

		<title>选系统</title>
	</head>
	<style>
		body{
			background: #343636;
		}
		.room{
			width: 60%;
			margin: 0  auto;
		}
		.seat{
			width: 8%;
			height: 30px;
			background: #F5F5F5;
			text-align: center;
			font-size: 100%;
			display: inline-block;
			margin-left: 2%;
			margin-top: 40px;
			line-height: 30px;
			cursor:pointer;
		}
		.choosed{
			background:#0099FF; ;
		}
		.unchoosed{
				background: #F5F5F5;
		}
		.chooseing{
		background:#FE3F55;
		}
		.tipFont{
		   color: #F5F5F5;
		   font-size: 20px;
		   display:inline-block;
		   line-height: 30px;
		}
		.Num{
			margin-top: 50px;
			color:  #F5F5F5;;
		}
		.seatNum{
			color:#CFA972;
		}
		
	</style>
	<body>
	<jsp:include page="nav.jsp"/>
		<div class="room">
			<div class="tip">
				<div class='seat unchoosed yy'></div> <span class="tipFont">可选</span> 
				<div class='seat choosed yy'></div> <span class="tipFont">已被占</span>
				<div class='seat chooseing yy'></div> <span class="tipFont">已选</span>
			</div>
		</div>
         <div class="room">
			 <h3 class="Num" style="display: none;">您选择的座位是<span class="seatNum">*</span>号机</h3>
			  <h3 class="Num">您尚未选择座位</h3>
			  <button class="btn btn-primary btn-lg pull-right sub"  disabled="true">提交</button>
		 </div>		
	</body>
	<script type="text/javascript">
		var width=$(window).width();
		if(width>500)
		{
			$(".room").css({'width':width*0.6});
		}
		else{
			$(".room").css({'width':width*0.95});
		}
		//拿到座位总数,已经选择座位的数组
		var total=20;
		var oneRow=10;//一行的电脑数量
		for(var i=0;i<total;i++)
		{
			var str="";
			if(i%oneRow==0)
			{
				str="style='margin-left:0px'";
			}
			$(".room").eq(0).append("<div class='seat'"+str+">"+(i+1)+"</div>");
		}
		
		var choosed=false;
		$(".seat").click(function () //确保只能有一个座位被选择
		{
			var str=$(this).attr("class");
			if(str.search("yy")!=-1||str.search("choosed")!=-1)
			return false;
			$(this).siblings().removeClass("chooseing");
			$(this).addClass("chooseing");
		
			$(".seatNum").html($(this).html());
				$(".Num").hide();
			$(".Num").eq(0).show();
			$(".sub").attr("disabled",false);
		})
		
	</script>
</html>