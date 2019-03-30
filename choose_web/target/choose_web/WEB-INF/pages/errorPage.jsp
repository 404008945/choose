<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">

		
		<title>错误页面</title>
		<style>
			.errPage{
				width: 80%;
				margin: 0 auto;
				margin-top: 20%;
				height: 200px;
				position: relative;
				
			}
			#text{
				 position:absolute;
				 height: 200px;
				 width:200px ;
				 top: 50px;
				 left: 30px;
				 
				 
			}
			
		</style>
	</head>
	
	<body style="background: #0091B6; width: 100%;height: 100%;">
	<jsp:include page="nav.jsp"/>
	  <div class=" errPage text-center" >
		  
		  <span class="glyphicon glyphicon-comment" style="font-size: 200px;color: white;"></span>
		  
		  <div id="text" class="text-danger"  style="font-size: 20px;">出现错误了，请重新操作</div>
	  </div>
	</body>
</html>
