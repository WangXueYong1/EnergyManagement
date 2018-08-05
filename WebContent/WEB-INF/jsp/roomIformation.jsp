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
		 $("#buildingList").change(function(){
             var $buildId=$(this).val();
             $.get("http://localhost:8080/ssm0523/DeviceInformation/findFloorByBuilding.action",{"buildingId":$buildId},function(obj){
                var $city=$("#floorList");
                 $city.html("<option>-请选择-</option>");
                 if(obj!=null){
                     $(obj).each(function(){
                         $city.append($("<option value='"+this+"'>第"+this+"层</option>"));
                     });
                 } 
             },"json");
         });
		 
		 $("#floorList").change(function(){
			 var $buildId=$("#buildingList").val();
             var $floor=$("#floorList").val();
             $.get("http://localhost:8080/ssm0523/DeviceInformation/findRoomByBuildingFloor.action",{"buildingId":$buildId,"floor":$floor},function(obj){
                var $city=$("#roomList");
                 $city.html("<option>-请选择-</option>");
                 if(obj!=null){
                     $(obj).each(function(){
                         $city.append($("<option value='"+this+"'>"+this+"房间</option>"));
                     });
                 } 
             },"json");
         });
		 
		 $("#roomList").change(function(){
			 var buildId=$("#buildingList").val();
             var floor=$("#floorList").val();
             var room=$("#roomList").val();
             $.get("http://localhost:8080/ssm0523/DeviceInformation/findDeviceByDeviceInformation.action",{"buildingId":buildId,"floor":floor,"room":room},function(obj){
                var $device=$("#deviceList");
                 $device.html("<option>-请选择-</option>");
                 if(obj!=null){
                     $(obj).each(function(){
                         $device.append($("<option value='"+this.deviceId+"'>"+this.name+"</option>"));
                     });
                 } 
             },"json");
         });
		 
		 var dataLegend = [ '空调1' ];
			var xAxisData ={};
			var data ={};        				
			var myChart =echarts.init(document.getElementById('main0'));
			var option = {
					title : {
						text : '温度（摄氏度）'
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : dataLegend
					},
					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : xAxisData
					},
					yAxis : {
						type : 'value'
					},
					series : {
						name : '窗帘1',
						type : 'line',
						data : data
					}
				}
			myChart.setOption(option);	
			
			
			
			 var dataLegend2 = [ '空调1' ];
				var myChart2 =echarts.init(document.getElementById('main1'));
				var option2 = {
						title : {
							text : '光照强度'
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : dataLegend2
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : xAxisData
						},
						yAxis : {
							type : 'value'
						},
						series : {
							name : '窗帘1',
							type : 'line',
							data : data
						}
					}
				myChart2.setOption(option2);	
				
			
			
		 
		 $("#button1").click(function(){
				var roomId=$("#roomList").val();
	            var startTime=$("#startTime").val();           
	            var endTime=$("#endTime").val();            
	            var data={"startTime":startTime,"endTime":endTime,"roomId":roomId};
	            $.get("http://localhost:8080/ssm0523/roomInformation/findRoomInformationByRoomInformationSelectObj.action"
	           			  ,data,function(obj){
	           				var dataLegend = [ '空调1' ];
	           				option.xAxis.data =obj.xAxisData;
	           				option.series.data =obj.data;        				
	        				myChart.setOption(option);	
	        				
	            },"json");
	            $.get("http://localhost:8080/ssm0523/roomInformation/findRoomInformationByRoomInformationSelectObj2.action"
	         			  ,data,function(obj){
	         				var dataLegend2 = [ '空调1' ];
	         				option2.xAxis.data =obj.xAxisData;
	         				option2.series.data =obj.data;        				
	      				myChart2.setOption(option2);	
	      				
	            },"json");
         });
		 
	});		
</script>
</head>
<body>
	<div id="container" style="width: 100%">

		<div id="header" style="background-color: #FFA;">
			<h1 align="center" style="margin-bottom: 0;">杭州电子科技大学写字楼能耗管理系统</h1>
		</div>

		<div id="menu"
			style="background-color: #bae5ff; height: 1000px; width: 120px; float: left;">
			<ul class="nav nav-tabs">
				<li id="li1"><a href="#">耗电比重</a></li>
				<li id="li2"><a href="#">灯具状态</a></li>
				<li id="li3"><a href="#">空调状态</a></li>
				<li id="li4"><a href="#">窗帘状态</a></li>
				<li id="li5"><a href="#">温度</a></li>
				<li id="li6"><a href="#">光照</a></li>
			</ul>
		</div>

		<div id="content"
			style="background-color: #CCC; height: 1000px; width: 100%; position: relative;; margin-left: 120px">
			<span>位置：</span> 
			<select>
				<option>杭州电子科技大学</option>
			</select> 
			<select id="buildingList">
				<option value="1">第一教学楼</option>
				<option value="2">第二教学楼</option>
				<option value="3">第三教学楼</option>
			</select> 
			<select id="floorList">				
			</select> 
			<select id="roomList">
			</select>  
			<span> 时间:</span> 
			<input type="datetime-local" id="startTime" value="2018-07-05T00:00">
			<span>~</span> 
			<input type="datetime-local" id="endTime" value="2018-08-07T00:00">
			<button id="button1">按小时查看</button>
			
			<div id="main0" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
				<div id="main1" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			
		</div>

		<div id="footer"
			style="background-color: #FFA500; clear: both; text-align: center;">
			版权 © 杭州电子科技大学</div>

	</div>
</body>
</html>