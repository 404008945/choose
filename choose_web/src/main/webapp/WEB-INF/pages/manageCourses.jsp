<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">

		
		<title>课程管理</title>
	</head>
	<body>
	<jsp:include page="nav.jsp"/>
		<div class="container">
			<h3 class="text-center">课程管理</h3>
			<!-- 添加课程的框-->
			
		
		

			</form>
		<table class="table table-hover table-bordered">
	<caption>全部课程管理</caption>
	<thead>
		<tr>
			<th>课程名</th>
			<th>操作</th>
			
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>FCC前端课程</td>
			<td><a href="#" class="btn btn-danger">删除</a></td>
		
		</tr>
		
	</tbody>
</table>
	<button  data-toggle="modal" data-target="#myModal" class="btn btn-info">添加课程</button>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加课程</h4>
            </div>
            <div class="modal-body">
				<form role="form" id="from1">
  <div class="form-group">
    <label for="name">课程名</label>
    <input type="text" class="form-control" id="name" placeholder="请输入课程名称">
  </div>

  <button type="submit" class="btn btn-default">提交</button>
</form>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
	</body>
	
	<script>
		$('form').submit(function(){
			     $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",  //预期服务器返回的数据类型
                url: "" ,//url
                data: $('#form1').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                   if(result.success)
				   {
					   alert('创建成功');
					   location="";
				   }
				   else{
					       alert("该课程已存在，无需重复添加");
					   location="";
				   }
                },
                error : function() {
                    alert("出错啦");
                }
            });
			
			return false;
		
		})
		
	</script>
</html>
