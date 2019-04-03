<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String colorArr[]={"#F05261","#FFD061","#52DB9A","#48A8E4","#4ADBC3","#3F51B5","#70D3E6","#F3DB49"};
	int count=0;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
	<h3 class="text-primary">点击课程进行选课</h3>
	<c:forEach items="${courses}" var="course" varStatus="status">
		<c:if test="${status.count==1||(status.count-1)%3==0}">
			<div class="row" style="margin-top: 20px">
		</c:if>
		<div class="col-xs-4 ">
         <div class="course" courseId="${course.id}" style="background:<%=colorArr[(count++)%colorArr.length]  %> ;cursor:pointer;text-align: center;color: white;vertical-align: center;font-size: 15px">
			${course.name}
		</div>
		</div>
		<c:if test="${(status.count)%3==0}">
			</div>
		</c:if>
	</c:forEach>
</div>
</body>
  <script>

	  update();
	  $(".course").click(function () {
	  	location="/user/chooseOne?courseId="+$(this).attr("courseId");
	  })
	  function  update() {
		  var width=$(window).width();
		  if(width>500)
		  {
			  $(".container").css({'width':'60%'});
		  }
		  else{
			  $(".container").css({'width':'95%'});
		  }

		  $(window).resize(function () {
			  update();
		  })
		  $(".course").height($(".course").width());
		  $(".course").css({'line-height': $(".course").width() + 'px'});
	  }
  </script>
</html>
