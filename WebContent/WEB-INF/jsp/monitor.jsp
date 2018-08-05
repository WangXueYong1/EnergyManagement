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
		/*$("#main1").hide();*/
		$("#main2").hide();
		$("#main3").hide();
		/*$("#main4").hide();*/
		$("#main5").hide();
		$("#main6").hide();

		$("#li1").mouseenter(function() {
			$(".chatMap").hide();
			$("#main1").show();
			f1();
		});

		$("#li2").mouseenter(function() {
			$(".chatMap").hide();
			$("#main2").show();
			f2();
		});

		$("#li3").mouseenter(function() {
			$(".chatMap").hide();
			$("#main3").show();
			f3();
		});

		$("#li4").mouseenter(function() {
			$(".chatMap").hide();
			$("#main4").show();
			f4();
		});

		$("#li5").mouseenter(function() {
			$(".chatMap").hide();
			$("#main5").show();
			f5();
		});

		$("#li6").mouseenter(function() {
			$(".chatMap").hide();
			$("#main6").show();
			f6();
		});

		
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
						text : '各电器功耗状态（功率kw/h）'
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
			$("#main3").show();
		 
		 $("#button1").click(function(){
			var deviceId=$("#deviceList").val();
            var startTime=$("#startTime").val();           
            var endTime=$("#endTime").val();            
            var data={"startTime":startTime,"endTime":endTime,"deviceId":deviceId};
            $.get("http://localhost:8080/ssm0523/deviceStatus/findDeviceStatusByDeviceInformation.action"
           			  ,data,function(obj){
           				var dataLegend = [ '空调1' ];
           				option.xAxis.data =obj.xAxisData;
           				option.series.data =obj.data;        				
        				myChart.setOption(option);	
        				$("#main0").show();
                     },"json");
         });
		 
		 $("#button2").click(function(){
				var deviceId=$("#deviceList").val();
	            var startTime=$("#startTime").val();           
	            var endTime=$("#endTime").val();            
	            var data={"startTime":startTime,"endTime":endTime,"deviceId":deviceId};
	            $.get("http://localhost:8080/ssm0523/deviceStatus/findDeviceStatusByDeviceInformation2.action"
	           			  ,data,function(obj){
	           				var dataLegend = [ '空调1' ];
	           				option.xAxis.data =obj.xAxisData;
	           				option.series.data =obj.data;        				
	        				myChart.setOption(option);	
	        				$("#main0").show();
	                     },"json");
	     });
		 $("#button3").click(function(){
				var deviceId=$("#deviceList").val();
	            var startTime=$("#startTime").val();           
	            var endTime=$("#endTime").val();            
	            var data={"startTime":startTime,"endTime":endTime,"deviceId":deviceId};
	            $.get("http://localhost:8080/ssm0523/deviceStatus/findDeviceStatusByDeviceInformation3.action"
	           			  ,data,function(obj){
	           				var dataLegend = [ '空调1' ];
	           				option.xAxis.data =obj.xAxisData;
	           				option.series.data =obj.data;        				
	        				myChart.setOption(option);	
	        				$("#main0").show();
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
			style="background-color: #bae5ff; height: 700px; width: 120px; float: left;">
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
			style="background-color: #CCC; height: 700px; width: 100%; position: relative;; margin-left: 120px">
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
			<select id="deviceList">
			</select>
			<span> 时间:</span> 
			<input type="datetime-local" id="startTime" value="2018-07-05T00:00">
			<span>~</span> 
			<input type="datetime-local" id="endTime" value="2018-08-07T00:00">
			<button id="button1">按小时查看</button>
			<button id="button2">按天查看</button>
			<button id="button3">按月查看</button>
			<div id="main0" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<div id="main1" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<script>
				function f1() {
					var data = [ {
						name : '灯具耗电',
						value : 1212
					}, {
						name : '空调耗电',
						value : 2323
					}, {
						name : '其他',
						value : 1919
					} ];
					var myChart = echarts
							.init(document.getElementById('main1'));
					myChart.setOption({
						series : {
							type : 'pie',
							data : data
						}
					});
				}
			</script>

			<div id="main2" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<script>
				function f2() {
					var data = [ 120, 200, 150, 80, 70, 110, 130 ];
					var myChart = echarts
							.init(document.getElementById('main2'));
					myChart.setOption({
						xAxis : {
							type : 'category',
							data : [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat',
									'Sun' ]
						},
						yAxis : {
							type : 'value'
						},
						series : [ {
							data : data,
							type : 'bar'
						} ]
					});
				}
			</script>

			<div id="main3" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<script>
				var dataLegend = [ '空调1', '空调2' ];
				var data24h = [ '0时', '1时', '2时', '3时', '4时', '5时', '6时', '7时',
						'8时', '9时', '10时', '11时', '12时', '13时', '14时', '15时',
						'16时', '17时', '18时', '19时', '20时', '21时', '22时', '23时',
						'24时' ];
				var data = [ {
					name : '空调1',
					type : 'line',
					data : [ 120, 132, 101, 134, 90, 230, 210 ]
				}, {
					name : '空调2',
					type : 'line',
					data : [ 220, 182, 191, 234, 290, 330, 310 ]
				} ];
				function f3() {
					echarts.init(document.getElementById('main3')).setOption({
						title : {
							text : '各电器功耗状态（功率kw/h）'
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
							data : data24h
						},
						yAxis : {
							type : 'value'
						},
						series : data
					});
				}
			</script>


			<div id="main4" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<script>
				function f4() {

					echarts.init(document.getElementById('main4')).setOption(
							{
								title : {
									text : '窗帘状态（功率kw/h）'
								},
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									data : [ '窗帘1', '窗帘2' ]
								},
								grid : {
									left : '3%',
									right : '4%',
									bottom : '3%',
									containLabel : true
								},
								toolbox : {
									feature : {
										saveAsImage : {}
									}
								},
								xAxis : {
									type : 'category',
									boundaryGap : false,
									data : [ '0时', '1时', '2时', '3时', '4时',
											'5时', '6时', '7时', '8时', '9时',
											'10时', '11时', '12时', '13时', '14时',
											'15时', '16时', '17时', '18时', '19时',
											'20时', '21时', '22时', '23时', '24时' ]
								},
								yAxis : {
									type : 'value'
								},
								series : [
										{
											name : '窗帘1',
											type : 'line',
											data : [ 120, 132, 101, 134, 90,
													230, 210, 132, 101, 134,
													90, 230, 210 ]
										},
										{
											name : '窗帘2',
											type : 'line',
											data : [ 220, 182, 191, 234, 290,
													330, 310, 132, 101, 134,
													90, 230, 210 ]
										} ]
							});
				}
			</script>

			<div id="main5" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<script>
				function f5() {

					echarts.init(document.getElementById('main5')).setOption(
							{
								title : {
									text : '光照强度（  ）'
								},
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									data : [ '光照强度' ]
								},
								grid : {
									left : '3%',
									right : '4%',
									bottom : '3%',
									containLabel : true
								},
								toolbox : {
									feature : {
										saveAsImage : {}
									}
								},
								xAxis : {
									type : 'category',
									boundaryGap : false,
									data : [ '0时', '1时', '2时', '3时', '4时',
											'5时', '6时', '7时', '8时', '9时',
											'10时', '11时', '12时', '13时', '14时',
											'15时', '16时', '17时', '18时', '19时',
											'20时', '21时', '22时', '23时', '24时' ]
								},
								yAxis : {
									type : 'value'
								},
								series : [ {
									name : '光照强度',
									type : 'line',
									data : [ 120, 132, 101, 134, 90, 230, 210,
											182, 191, 234, 290, 330, 310 ]
								} ]
							});
				}
			</script>



			<div id="main6" class="chatMap" display="none"
				style="width: 700px; height: 500px;"></div>
			<script>
				function f6() {

					echarts.init(document.getElementById('main6')).setOption(
							{
								title : {
									text : '温度（摄氏度）'
								},
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									data : [ '温度' ]
								},
								grid : {
									left : '3%',
									right : '4%',
									bottom : '3%',
									containLabel : true
								},
								toolbox : {
									feature : {
										saveAsImage : {}
									}
								},
								xAxis : {
									type : 'category',
									boundaryGap : false,
									data : [ '0时', '1时', '2时', '3时', '4时',
											'5时', '6时', '7时', '8时', '9时',
											'10时', '11时', '12时', '13时', '14时',
											'15时', '16时', '17时', '18时', '19时',
											'20时', '21时', '22时', '23时', '24时' ]
								},
								yAxis : {
									type : 'value'
								},
								series : [ {
									name : '温度',
									type : 'line',
									data : [ 120, 132, 101, 134, 90, 230, 210 ]
								} ]
							});
				}
			</script>
		</div>

		<div id="footer"
			style="background-color: #FFA500; clear: both; text-align: center;">
			版权 © 杭州电子科技大学</div>

	</div>
</body>
</html>