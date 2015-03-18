<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>  
    <title> <tiles:getAsString name="title"></tiles:getAsString> </title>
    
    <jsp:include page="/WEB-INF/jsp/common/include_common.jsp"></jsp:include>
    
</head>
  
<body>
  
  <div class="easyui-layout" 
  	style="width: auto;height: 100%; margin: 0 auto;">
  	
  	<div data-options="region:'north'"
  		 style="width:auto; height: 10%;">
  		<tiles:insertAttribute name="top" />
  	</div>
  	<div data-options="region:'west',split:true" 
  		style="width: 10%;padding-left: 5px;height:85%; float: left;" >
  		<tiles:insertAttribute name='left' />
  	</div>
  	<div data-options="region:'center'" 
  		style="width: auto;padding-left: 5px;height: 85%; overflow:hidden;"> 
  		 <tiles:insertAttribute name="right" /> 
  	</div>
  	<div data-options="region:'south'"
  		 style="width:auto; height: 5%;float: left;text-align: center;margin: 0 auto;" >
  		<tiles:insertAttribute name="bottom" />
  	</div>
  </div>
   
</body>
</html>