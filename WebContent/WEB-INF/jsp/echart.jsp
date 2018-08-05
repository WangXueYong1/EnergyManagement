<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%-- <script type="text/javascript"/ssm0523/WebContent/WEB-INF/js/echarts.min.js /ssm0523/WebContent/WEB-INF/js/jquery-1.11.1.min.js
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script>
	 $(function () {		 
        $("#b2").mouseenter(function () {
        	$.get("http://localhost:8080/ssm0523/detail.action",{"id":12},function(data){
   			 alert(data);
  		    },"json");
        });     
	 })
</script>
</head>
<body>
	<a href="http://localhost:8080/ssm0523/list.action">list</a>
	<a href="http://localhost:8080/ssm0523/findUserByid.action?id=1">findUserByid</a>
	<button id="b1">ajax</button>
	<button id="b2">ajax</button>
	<button id="b3">ajax</button>
</body>
</html>