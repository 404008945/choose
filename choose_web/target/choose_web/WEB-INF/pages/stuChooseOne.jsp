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
		<h2 class="text-center">请选择您要预约的课程</h2>
<form style="margin-top: 20%;" action="/user/chooseOne">
  <div class="form-group">
   <label for="courseId">请选课程</label>
  <select id="courseId" class="form-control" name="courseId">
	  <c:forEach var="course" items="${courses}">
		  <option value="${course.id}">${course.name}</option>
	  </c:forEach>
</select>
  </div>
  <input type="submit" class="btn btn-primary" style="margin-top: 30px;" value="进入预约页面"/>
</select>
</form>
</div>
	</body>
	
</html>
