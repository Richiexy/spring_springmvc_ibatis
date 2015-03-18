<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
		
		jQuery(document).ready(function(){
			
			jQuery('.left .item').draggable({
				revert:true,
				proxy:'clone'
			});
			
			jQuery('.right td.drop').droppable({
				onDragEnter:function(e,source){
					//在被拖拽元素到放置区内的时候触发
					jQuery(this).addClass('over');
				},
				onDragLeave:function(e,source){
					//在被拖拽元素离开放置区的时候触发
					jQuery(this).removeClass('over');
					alert('leave' + source.id);
				},
				onDrop:function(e,source){
					//在被拖拽元素放入到放置区的时候触发
					alert('drop here ' + source.id);
					jQuery(this).removeClass('over');
					if (jQuery(source).hasClass('assigned')){
						jQuery(this).append(source);
					} else {
						var c = jQuery(source).clone().addClass('assigned');
						jQuery(this).empty().append(c);
						c.draggable({
							revert:true
						});
					}
				}
			});
		});
		
	</script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/schooltimetable.css">
	
  </head>
  
  <body>
  <div id="layoutDivId">
	   <div class="left">
	   		<table>
				<tr>
					<td><div id="c01" class="item">语文</div></td>
				</tr>
				<tr>
					<td><div id="c02" class="item">英语</div></td>
				</tr>
				<tr>
					<td><div id="c03" class="item">数学</div></td>
				</tr>
				<tr>
					<td><div id="c04" class="item">物理</div></td>
				</tr>
				<tr>
					<td><div id="c05" class="item">化学</div></td>
				</tr>
				<tr>
					<td><div id="c06" class="item">生物</div></td>
				</tr>
				<tr>
					<td><div id="c07" class="item">地理</div></td>
				</tr>
				<tr>
					<td><div id="c08" class="item">历史</div></td>
				</tr>
				<tr>
					<td><div id="c09" class="item">音乐</div></td>
				</tr>
				<tr>
					<td><div id="c10" class="item">美术</div></td>
				</tr>
				<tr>
					<td><div id="c11" class="item">体育</div></td>
				</tr>
			</table>
	   </div>
	   
	   <div class="right">
			<table>
				<tr>
					<td class="blank"></td>
					<td class="title">星期日</td>
					<td class="title">星期一</td>
					<td class="title">星期二</td>
					<td class="title">星期三</td>
					<td class="title">星期四</td>
					<td class="title">星期五</td>
					<td class="title">星期六</td>
				</tr>
				<%
					for(int i=1 ;i<8;i++){
						
				%>
					<tr>
						<td class="time">第<%=i %>节课</td>
						<td id="d<%=i %>1" class="drop"></td>
						<td id="d<%=i %>2" class="drop"></td>
						<td id="d<%=i %>3" class="drop"></td>
						<td id="d<%=i %>4" class="drop"></td>
						<td id="d<%=i %>5" class="drop"></td>
						<td id="d<%=i %>6" class="drop"></td>
						<td id="d<%=i %>7" class="drop"></td>
					</tr>
					<tr>
						<td colspan="8"></td>
					</tr>
				<%
						
					}
				%>
				
			</table>
		</div>
    </div>
  </body>
</html>
