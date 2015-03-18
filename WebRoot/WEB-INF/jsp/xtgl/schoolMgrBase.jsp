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
			jQuery("#dg_001").datagrid('load',{
				'scCode' : jQuery("input[name='scCode']").val(),
				'scName' : jQuery("input[name='scName']").val()
			})
			
		}
		//定义表格
		jQuery(document).ready(function(){
			
			var screenWidth = jQuery("#layoutDivId").width();
			var screenHeight = jQuery("#layoutDivId").height();
			
			//定义方法
			//新增
			function addSchool(){
				
				jQuery("#school_win").form('load' ,{
			    	recStat: "1"//默认值
			    })
			    
				jQuery("#school_win").window({
					title: "新增学校",
					collapsible:false,
					minimizable:false,
				    maximizable:false,
				    modal:true,
				    width:screenWidth * 2/7,
					height:screenHeight * 3/7
				});
			}
			//修改
			function editSchool(){
				var row = jQuery("#dg_001").datagrid('getSelected');
				if (row){
					
				    jQuery("#school_win").form('load' ,{
				    	scCode: row.scCode,
				    	scName: row.scName,
				    	scAddr: row.scAddr,
				    	recStat: "1",//默认值
				    	KId: row.kid
				    })
				    
				    jQuery("#school_win").window({
						title: "修改学校",
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
			function delSchool(gridId , formAction){
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
			
			jQuery("#dg_001").datagrid({
				url: basePath + 'schoolController/getSchoolList',
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
	        			addSchool();
	        		}
	        	},'-',{
	        		text:'修改',
	        		iconCls: 'icon-edit',
	        		handler: function(){
	        			editSchool();
	        		}
	        	},'-',{
	        		text:'删除',
	        		iconCls: 'icon-remove',
	        		handler: function(){
	        			delSchool("dg_001" , "schoolController");
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
    		<form id="schoolSearchForm"> 
    			<table> 
			    	<tr>
			    		<td>学校编号：</td>
			    		<td><input class="easyui-validatebox" type="text" name="scCode" data-options="required:false"></input></td>
			    		<td>学校名称：</td>
			    		<td><input class="easyui-validatebox" type="text" name="scName" data-options="required:false"></input></td>
			    		<td>
			    			<a href="javascript:void(0)" class="easyui-linkbutton" 
			    				data-options="iconCls:'icon-search'"
			    				onclick="javascript:searchGridByParam()" >查询</a>
			    		</td>
			    		<td>
			    			<a href="javascript:void(0)" class="easyui-linkbutton" 
			    				data-options="iconCls:'icon-reset'"
			    				onclick="javascript:resetForm('schoolSearchForm')" >重置</a>
			    		</td> 
			    	</tr>
				</table> 
			</form> 
    	</div>   
    	<%-- 列表区域 --%>
		<div data-options="region:'center',title:'查询列表'" style="padding:5px;background:#eee;">
			<table id="dg_001" >   
			    <thead>   
			        <tr>   
			        	<%-- 复选框 --%>
			            <th data-options="field:'ck',checkbox:true"> </th>   
			            <th data-options="field:'scCode'">学校编号</th>   
			            <th data-options="field:'scName'">学校名称</th>   
			            <th data-options="field:'scAddr'">学校地址</th>   
			            <th data-options="field:'kid',hidden:true">主键</th>   
			        </tr>   
			    </thead>   
			</table>  
			
		</div>   
		<%-- 列表结束 --%>
    </div>
    
    <div id="school_win" >
    
    	 <form id="schoolForm" method="post"> 
    	 	<input type="hidden" name="KId" />
    	 	<input type="hidden" name="recStat"/>
		     	<table> 		  
		     		<tr>
		    			 <td nowrap>学校编号：</td>
		  			     <td><input class="easyui-validatebox" type="text" name="scCode" data-options="required:false"></input></td>
		  			</tr>
		  			<tr>
		    			 <td nowrap>学校名称：</td>
		  			     <td><input class="easyui-validatebox" type="text" name="scName" data-options="required:false"></input></td>
		  			</tr>
		  			<tr>
		    			 <td nowrap>学校地址：</td>
		  			     <td><input class="easyui-validatebox" type="text" name="scAddr" data-options="required:false"></input></td>
		    		 </tr>
		       </table>                                                                                                                                                
		  </form>                                                                                                                                                   

	 	<div style="text-align:center;padding:5px"> 
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormData('school_win','dg_001','schoolForm','schoolController/saveSchool')">提 交</a> 
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm('schoolForm')">重 置</a> 
	    </div> 	
      
    </div>
    
  </body>
</html>
