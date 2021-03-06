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
		
		function searchGridByParam(){
			jQuery("#dg_004").datagrid('load',{
				'roleid' : jQuery("input[name='roleid']").val(),
				'rolename' : jQuery("input[name='rolename']").val()
			})
			
		}
		//定义表格
		jQuery(document).ready(function(){
			
			var screenWidth = jQuery("#layoutDivId").width();
			var screenHeight = jQuery("#layoutDivId").height();
			
			//定义方法
			//新增
			function addRole(){

				jQuery("#role_win").form('load' ,{
			    	recStat: "1"//默认值
			    })
			    
				jQuery("#role_win").window({
					title: "新增角色",
					collapsible:false,
					minimizable:false,
				    maximizable:false,
				    modal:true,
				    width:screenWidth * 2/7,
					height:screenHeight * 3/7
				});
			}
			//修改
			function editRole(){
				var row = jQuery("#dg_004").datagrid('getSelected');
				if (row){
					
				    jQuery("#role_win").form('load' ,{
				    	roleid: row.roleid,
				    	rolename: row.rolename,
				    	recStat: "1",//默认值
				    	KId: row.kid
				    })
				    
				    jQuery("#role_win").window({
						title: "修改角色",
						collapsible:false,
						minimizable:false,
					    maximizable:false,
					    modal:true,
					    width:screenWidth * 2/7,
						height:screenHeight * 3/7
					});
				    
				}else{
					jQuery.messager.alert("提示!","请选择记录后操作！");
					return;
				}
				
			}
			//批量删除
			function delRole(gridId , formAction){
				var rows = jQuery("#" + gridId).datagrid('getSelections');
				if (rows.length > 0){
					var ids=[];
					for(var i=0; i<rows.length; i++){
					    ids.push(rows[i].kid);
					}
					jQuery.ajax({
						url: basePath + formAction + "/batchDel"+'?rondom='+ getRandom(),
						method: "post",
						data: {
							"keyIds":ids.join(",")
						},
						success:function(data) {
							jQuery("#" + gridId).datagrid('reload');
							jQuery.messager.alert("提示!","删除成功！");
						}
					})
				}else{
					jQuery.messager.alert("提示!","请选择记录后操作！");
					return;
				}
			}
			
			jQuery("#dg_004").datagrid({
				url: basePath + 'roleController/getRoleList',
				loadMsg: "正在加载数据，请稍等……",
	            pagination:true,  
	            rownumbers:true,  
	            fit:true,  
	            width:'100%',    
	            height:'85%',    
	            nowrap: false,  
	            border: false,  
	            pageSize:10,  
	            //singleSelect:true,//单选
	            toolbar:[{
	            	text:'新增',
	        		iconCls: 'icon-add',
	        		handler: function(){
	        			addRole();
	        		}
	        	},'-',{
	        		text:'修改',
	        		iconCls: 'icon-edit',
	        		handler: function(){
	        			editRole();
	        		}
	        	},'-',{
	        		text:'删除',
	        		iconCls: 'icon-remove',
	        		handler: function(){
	        			delRole("dg_004" , "roleController");
	        		}
	        	}]
			});
		})
	</script>

  </head>
  
  <body>
  
    <div id="layoutDivId" class="easyui-layout" style="width:100%;height:100%;padding:10px;"   >
    	<%-- 查询条件区域 --%>
    	<div data-options="region:'north',title:'查询条件',split:true" style="height:18%;">
    		<form id="roleSearchForm"> 
    			<table> 
			    	<tr>
			    		<td>角色编号：</td>
			    		<td><input class="easyui-validatebox" type="text" name="roleid" data-options="required:false"></input></td>
			    		<td>角色名称：</td>
			    		<td><input class="easyui-validatebox" type="text" name="rolename" data-options="required:false"></input></td>
			    		<td>
			    			<a href="javascript:void(0)" class="easyui-linkbutton" 
			    				data-options="iconCls:'icon-search'"
			    				onclick="javascript:searchGridByParam()" >查询</a>
			    		</td>
			    		<td>
			    			<a href="javascript:void(0)" class="easyui-linkbutton" 
			    				data-options="iconCls:'icon-reset'"
			    				onclick="javascript:resetForm('roleSearchForm')" >重置</a>
			    		</td>
			    	</tr>
				</table>  
			</form>
    	</div>   
    	<%-- 列表区域 --%>
		<div data-options="region:'center',title:'查询列表'" style="padding:5px;background:#eee;">
			<table id="dg_004" >   
			    <thead>   
			        <tr>   
			        	<%-- 复选框 --%>
			            <th data-options="field:'ck',checkbox:true"> </th>   
			            <th data-options="field:'roleid'">角色编号</th>   
			            <th data-options="field:'rolename'">角色名称</th>   
			            <th data-options="field:'kid',hidden:true">主键</th>   
			        </tr>   
			    </thead>   
			</table>  
			
		</div>   
		<%-- 列表结束 --%>
    </div>
    
    <div id="role_win" >
    
    	 <form id="roleForm" method="post"> 
    	 	<input type="hidden" name="KId" />
    	 	<input type="hidden" name="recStat"/>
		     	<table> 		  
		     		<tr>
		    			 <td nowrap>角色编号：</td>
		  			     <td><input class="easyui-validatebox" type="text" name="roleid" data-options="required:false"></input></td>
		  			</tr>
		  			<tr>
		    			 <td nowrap>角色名称：</td>
		  			     <td><input class="easyui-validatebox" type="text" name="rolename" data-options="required:false"></input></td>
		  			</tr>
		       </table>                                                                                                                                                
		  </form>                                                                                                                                                   

	 	<div style="text-align:center;padding:5px"> 
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormData('role_win','dg_004','roleForm','roleController/saveRole')">提 交</a> 
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm('roleForm')">重 置</a> 
	    </div> 	
      
    </div>
    
  </body>
</html>
