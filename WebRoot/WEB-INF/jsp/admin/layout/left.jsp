<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.util.Map.*,org.jdom2.*" %>
<%@ page import="com.easyui.util.*" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map<String, Object> dataMap =SysUtil.getMemoryData();

Map<String, List<Element>> leftMenuMap = (Map<String, List<Element>>)dataMap.get("leftMenuMap");

Iterator<Entry<String, List<Element>>> iter = leftMenuMap.entrySet().iterator();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
 <style type="text/css">
 	
 </style>
 <script>
 
 </script> 
</head>

<body> 
    <ul id="leftTree" class="easyui-tree" >  
 <%
 while(iter.hasNext()) { 
	 
	    Entry<String, List<Element>> entry = iter.next();   
	    
	    List<Element> leftMenuSet = entry.getValue();
 
	    for (int i = 0; i < leftMenuSet.size(); i++) { 
			   //取一个leftMenu
			  Element childElementSet =  (Element) leftMenuSet.get(i);
			  //取其中h1a
			  Element childElement = childElementSet.getChild("h1a");
			  //取h1a对应的值
			  String h1aValue = childElement.getText();

%><%-- 关闭状态
		<li data-options="state:'closed'"> 
		--%>
		<li> 
			<span><%=h1aValue %></span>  <%--一级菜单 --%>
<%			   
			  
			  //取其中lia集合
			  List liaElementSet = childElementSet.getChildren("lia");
			  if(liaElementSet.size() > 0){
		
%>
			<ul>
		
<%
	}		 
			  for (int j = 0; j < liaElementSet.size(); j++) { 
				  //取一个lia
				  Element liaElement = (Element) liaElementSet.get(j);
				  //取lia对应的值
				   String hrefValue = liaElement.getAttributeValue("href");
				  String liaElementValue = liaElement.getText();
%>
					  <li>
					  <%-- 二级菜单 --%>
					  	<span><a href="<%=basePath +hrefValue%>"><%=liaElementValue %></a></span>
					  </li>
<%					  
			  } 
			  if(liaElementSet.size() > 0){
%>

			</ul>
		</li>
<%
			  }
		}
	}
 %>   
     
    </ul>
     
</body>
</html>

