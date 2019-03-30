
//提供日期，起止时间  可以在图上绘制出课程信息    后台传来从今天开始后的7天的数据
   $(window).resize(function(){
	   update();
	     });
	    update();
 function update(){
	 $("#canvasW").html("");
	 $("#canvasW").append('<canvas id="myCanvas"></canvas>');
   function draw(sx,sy,ex,ey,ctx){
	   ctx.lineWidth=2;
	   ctx.moveTo(sx,sy);
	   ctx.lineTo(ex,ey);
	   
   }
   var dayArr=['一','二','三','四','五','六','日'];
   var nowDay=new Date().getDay();
  
	var width=$(window).width();
	var twidth=width*0.95;//表的实际宽度
	//高度采取固定   一小时高  暂定50px
	$("#canvasW").css({'width':twidth});
var canvas=document.getElementById('myCanvas');
canvas.width = twidth;
var dwidth=(twidth-45)/7;
var theight=800;
canvas.height = 800;
var ctx=canvas.getContext('2d');
ctx.fillStyle='#F2F6F7';
ctx.fillRect(0,0,twidth,30);
ctx.fillRect(0,0,45,theight);
var tw=dwidth+45;
ctx.lineWidth=1;
ctx.strokeStyle="#DBDBDB";
ctx.moveTo(0,0)
ctx.lineTo(twidth,0);
ctx.moveTo(0,30);
ctx.lineTo(twidth,30);
for(var i=0;i<7;i++)   //绘图7次
{
	ctx.fillStyle = '#000';
	ctx.font = "15px '字体','字体','微软雅黑','宋体'"; //设置字体
	ctx.textAlign = 'center';
	ctx.fillText("周"+dayArr[(nowDay+i-1)%7], tw-dwidth/2, 22);
	tw+=dwidth;
}
ctx.stroke();
//绘制时间轴  从早7:00到晚10:00
ctx.lineWidth=2;
ctx.moveTo(0,0);
ctx.lineTo(0,theight)
ctx.moveTo(twidth,0);
ctx.lineTo(twidth,theight)
ctx.stroke();
ctx.closePath();
ctx.beginPath();
ctx=canvas.getContext('2d');
var dh=50;
var  th=dh+30;   //暂定一天上课时间为12小时
 ctx.setLineDash([5]);
for(var i=0;i<6;i++)//画线
{
	ctx.fillText(7+i+":00", 20,th-dh/2+8 );
	draw(0,th,twidth,th,ctx);
	th+=50;
}
for(var i=0;i<9;i++)//画线
{
	ctx.fillText(14+i+":00", 20,th-dh/2+8);
	draw(0,th,twidth,th,ctx);
	th+=50;
}
ctx.stroke();
var colorArr=['#F05261','#FFD061','#52DB9A','#8A8E4','#4ADBC3','#3F51B5','#70D3E6','#F3DB49'];


var colorIdx=0;

for(var i=0;i<courseArr.length;i++)
{
	addCourse(courseArr[i]);
}


function  addCourse(course){
	var color=colorArr[colorIdx];
	colorIdx=(colorIdx+1)%colorArr.length;
	var base;
	var l=45+dwidth*course.dateNum;
	var s=parseInt(course.startTime.split(":")[0]);
	
	var sh=0;
	if(s<14)
	{
		base=7;
	}else{
		base=14;
		sh=6*50;
	}

    
	var m=parseInt(course.startTime.split(":")[1]);
	s-=base;
	var  t=30+sh+s*50+1.0*m/60*50;
	var s1=parseInt(course.endTime.split(":")[0]);
	var m1=parseInt(course.endTime.split(":")[1]);
	s1-=base;
    //求课程的高
	var h=s1*50+1.0*m1/60*50-s*50+1.0*m/60*50;
    //知道宽高，开始绘图
	$("#canvasW").prepend("<div class='course' courseId="+course.courseId+" style='position:absolute;border-radius:10px;background:"+color+";color:#fff;text-align:center;cursor:pointer;width:"+dwidth+"px;height:"+h+"px;left:"+l+"px;top:"+t+"px'>"+course.courseName+"<br>"+course.startTime+"-"+course.endTime+"</div>")

}
}