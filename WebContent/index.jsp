<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- //${pageContext.request.contextPath }/login/submit.action --%>
	<a href="${pageContext.request.contextPath }/monitor.action">roomInformationMonitor</a><br>
	<a href="${pageContext.request.contextPath }/monitor.action">monitor</a><br>
	<a href="${pageContext.request.contextPath }/roomInformation/monitor.action">roomInformationMonitor</a><br>
	<a href="${pageContext.request.contextPath }/DeviceInformation/findById.action?id=1">DeviceInformation/findById.action?id=1</a><br>
	<a href="${pageContext.request.contextPath }/deviceStatus/findById.action?id=1">deviceStatus/findById.action?id=1</a><br>
	<a href="${pageContext.request.contextPath }/DeviceInformation/findFloorByBuilding.action?buildingId=1">DeviceInformation/findFloorByBuilding.action?buildingId=1</a><br>
	<a href="${pageContext.request.contextPath }/deviceStatus/insertJsp.action">插入数据</a><br>
	<a href="${pageContext.request.contextPath }/deviceStatus/receiveJsp.action">接收数据</a><br>
	<a href="${pageContext.request.contextPath }/deviceStatus/train.action">训练神经网络数据</a><br>
</body>
</html>