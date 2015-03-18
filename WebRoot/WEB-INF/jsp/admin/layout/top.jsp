<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.easyui.model.SUser " %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

SUser user = (SUser)request.getSession().getAttribute("user");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

 	<script language=JavaScript>
 		function Tshow(){
 			
 		 	 var time = new Date();
 		 	 var year = time.getFullYear() + "年";
 		 	 var month = time.getMonth() + "月" ;
 		 	 var day = time.getDay() + "日";
 		 	 var hours = time.getHours() + "-";
 		 	 var minutes= time.getMinutes() + "-";
 		 	 var seconds = time.getSeconds();
 		 	document.getElementById("curDateTime").innerHTML= year  + month +  day + hours + minutes + seconds;
 	 	}
 	 	setInterval('Tshow()',1000);
	</script>   
  </head>
<body>
	当前用户：<b><%=user.getUsername() %></b> ！<br />
	当前时间： <span id="curDateTime"> </span>
</body>
</html>