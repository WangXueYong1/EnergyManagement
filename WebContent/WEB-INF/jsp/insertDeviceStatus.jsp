<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script>
	$(function() {
		 $("#button1").click(function(){
			 var deviceId=$("#deviceId").val();
	         var power=$("#power").val();           
	         var date=$("#date").val(); 
	         var data={"deviceId":deviceId,"power":power,"date":date};
	         $.get("http://localhost:8080/ssm0523/deviceStatus/insertDeviceStatus.action"
          			  ,data,function(obj){ 
	        	 alert("success");
	         },"json");
		 });
		 $("#button2").click(function(){
			 var roomId=$("#roomId").val();
	                 
	         var time=$("#time").val(); 
	         var data={"roomId":roomId,"time":time};
	         $.get("http://localhost:8080/ssm0523/roomInformation/insertRoomInformation.action"
          			  ,data,function(obj){ 
	        	 alert("success");
	         },"json");
		 });
	});
</script>
</head>
<body>
    <p>向deviceState表中插入数据</p>
	<select id="deviceId">
				<option value="1">设备1</option>
				<option value="2">设备2</option>
				<option value="3">设备3</option>
	</select>
	<select id="power">
				<option value="1">功耗1</option>
				<option value="2">功耗2</option>
				<option value="3">功耗3</option>
	</select>
	<input type="datetime-local" id="date" value="2018-07-05T02:20">
	<button id="button1">插入数据</button><br>
	
	<p>向roomInformation表中插入数据</p>
	<select id="roomId">
				<option value="258">房间258</option>
				<option value="2">房间2</option>
				<option value="3">房间3</option>
	</select>
	<input type="datetime-local" id="time" value="2018-07-05T02:20">
	<button id="button2">插入数据</button><br>
	
			
</body>
</html>