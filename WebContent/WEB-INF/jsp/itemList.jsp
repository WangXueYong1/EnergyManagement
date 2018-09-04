<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询设备列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	查询条件：
	<span>位置：</span>
	<select>
		<option>杭州电子科技大学</option>
	</select>
	<select id="buildingList">
		<option value="2">第二教学楼</option>
		<option value="1">第一教学楼</option>
		<option value="3">第三教学楼</option>
	</select>
	<select id="floorList">
	
	</select>
	<select id="roomList">
	
	</select>
	<input type="button" value="搜索" id="btSearch" class="ui right floated positive button btn-search"/> 
	<span>设备列表：</span>
	<table width="100%" border=1>
		<tr>
			<td>设备ID</td>
			<td>设备名称</td>
			<td>设备所属建筑</td>
			<td>所属楼层</td>
			<td>所属房间</td>
			<td>设备类型</td>
			<td>是否工作</td>
			<td>工作状态</td>
			<td>操作</td>
		</tr>
		<tbody id="tbody-result">
		</tbody>
	</table>
	
<script>	
$(function () {
	
	function findFloor(){
        var $buildId=$("#buildingList").val();
        $.get("http://localhost:8080/ssm0523/DeviceInformation/findFloorByBuilding.action",{"buildingId":$buildId},function(obj){
           var $city=$("#floorList");
            $city.html("");
            if(obj!=null){
                $(obj).each(function(){
                    $city.append($("<option value='"+this+"'>第"+this+"层</option>"));
                });
            } 
        },"json");
    }
	function findRoom(){
		 var $buildId=$("#buildingList").val();
	     var $floor=$("#floorList").val();
	     $.get("http://localhost:8080/ssm0523/DeviceInformation/findRoomByBuildingFloor.action",{"buildingId":$buildId,"floor":$floor},function(obj){
	        var $city=$("#roomList");
	         $city.html("");
	         if(obj!=null){
	             $(obj).each(function(){
	                 $city.append($("<option value='"+this+"'>"+this+"房间</option>"));
	             });
	         } 
	     },"json");
    }

         $("#buildingList").click(findFloor);		 
		 $("#floorList").click(findRoom);
		/*  $("#roomList").click(findRoom);
		 $("#floorList").click(findFloor); */
	$("#roomList").change(function(){
		 var buildId=$("#buildingList").val();
       var floor=$("#floorList").val();
       var room=$("#roomList").val();
       $.get("http://localhost:8080/ssm0523/DeviceInformation/findDeviceByDeviceInformation.action",
       		{"buildingId":buildId,"floor":floor,"room":room},function(obj){
          var $device=$("#deviceList");
           $device.html("<option>-请选择-</option>");
           if(obj!=null){
               $(obj).each(function(){
                   $device.append($("<option value='"+this.deviceId+"'>"+this.name+"</option>"));
               });
           } 
       },"json");
   });
    $('#btSearch').click(function () {
    	var buildId=$("#buildingList").val();
        var floor=$("#floorList").val();
        var room=$("#roomList").val();
        var tbody = window.document.getElementById("tbody-result");
   
        $.ajax({
            type: "get",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            url: "http://localhost:8080/ssm0523/DeviceInformation/findDeviceByDeviceInformation.action",
            data:{"buildingId":buildId,"floor":floor,"room":room},
            success: function (data) {
               
                   var str = "";
                  

                   for (i in data) {
                       str += "<tr>" +
                           "<td align='center'>" + data[i].deviceId + "</td>" +
                           "<td align='center'>" + data[i].type + "</td>" +
                           "<td align='center'>" + data[i].buildingId + "</td>" +
                           "<td align='center'>" + data[i].floor + "</td>" +
                           "<td align='center'>" + data[i].room + "</td>" +
                           "<td align='center'>" + data[i].name + "</td>" +
                           "<td align='center'>" + data[i].is_working + "</td>" +
                           "<td align='center'>" + data[i].energy_consumption_status + "</td>" +
                           "<td align='center'>" + '<a href="${pageContext.request.contextPath }/monitor.action">开启</a><br>' + "</td>"  +
                           "</tr>";
                   }
                   tbody.innerHTML = str;
               
            },
            error: function () {
                alert("查询失败")
            }
        });
    });
});
</script>
</body>

</html>