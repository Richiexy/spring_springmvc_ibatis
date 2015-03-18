<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
		var path='${pageContext.request.contextPath}';
		
	</script>

  </head>
  
  <body>
    <div class="easyui-layout" style="width:100%;height:100%;padding:10px;"   >
    	<%-- 查询条件区域 --%>
    	<div data-options="region:'north',title:'查询条件',split:true" style="height:100px;">
    			<table> 
			    	<tr>
			    		<td>学校编号：</td>
			    		<td><input class="easyui-validatebox" type="text" name="userid" data-options="required:false"></input></td>
			    		<td>学校名称：</td>
			    		<td><input class="easyui-validatebox" type="text" name="username" data-options="required:false"></input></td>
			    	</tr>
				</table>  
    	</div>   
    	<%-- 列表区域 --%>
		<div data-options="region:'center',title:'查询列表'" style="padding:5px;background:#eee;">
			<table id="dg_001" class="easyui-datagrid"  data-options="fitColumns:true,pagination:true,rownumbers:true,toolbar:'#toolbarId'">   
			    <thead>   
			        <tr>   
			            <th data-options="field:'ck',checkbox:true"></th>   <%-- 复选框 --%>
			            <th data-options="field:'userid'">学校编号</th>   
			            <th data-options="field:'username'">学校名</th>   
			            <th data-options="field:'createuser'">学校地址</th>   
			        </tr>   
			    </thead>   
			    <tbody>   
			       <%-- 测试c标签,如果记录空，则表头也没显示 --%>
			       <c:if test="${!empty schoolList}">
			       		<c:forEach var="school" items="${schoolList}" >
				         	 <tr>  
				         		<td><c:out value="${school.scCode} "></c:out></td>
				         		<td><c:out value="${school.scName} "></c:out></td>
				         		<td><c:out value="${school.scAddr} "></c:out></td>
				         	</tr>   
				         </c:forEach> 
			       </c:if>
			       <c:if test="${empty schoolList}">
			       		<tr>  
			         		<td >复选框</td>
			         		<td >暂无记录2</td>
			         		<td >暂无记录3</td>
			         		<td >暂无记录4</td>
			         	</tr>  
			       </c:if>
			       
			    </tbody>   
			</table>  
			
		</div>   
		<%-- 列表结束 --%>
		 <%-- 工具栏 --%>
	    <div id="toolbarId">
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"/a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"/a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"/a>
		</div>
		<%-- 工具栏结束 --%>
    </div>
    
  </body>
</html>
