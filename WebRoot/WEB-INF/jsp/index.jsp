<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>easyui-demo</title>
	<jsp:include page="/WEB-INF/jsp/common/include_common.jsp"></jsp:include>
	
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
		var path='${pageContext.request.contextPath}';
		var loginMessage='${requestScope.errorMessage}'; 
		
		jQuery(document).ready(function(){
			
			jQuery("#win").form('load' , {
				userid : "admin",
				userpwd: "1"
			})
			
			if(null!=loginMessage && ''!=loginMessage){
		    	jQuery.messager.alert("提示",loginMessage,"info",function(){
		    		window.location.href= basePath;//跳转到登录页面，改变地址栏
		    	});
		    }
		})
	
		function loginBtnClick(){
			jQuery.messager.progress();
			<%--jQuery("#win").form('submit',{
				url: basePath + '/loginController/login',
				onSubmit:function(param){
					var isValid = jQuery(this).form('validate');
					if (!isValid){
						jQuery.messager.progress('close');	
					}
					return isValid;	
				},
				success:function(data){
					jQuery.messager.progress('close');	
					alert("-submit ok-" + data);
				}
			})--%>
			document.forms[0].submit();
		}
	
	</script>

	<style type="text/css">
		#win div label{
			margin: 5 5 5 5;
			width:50
		}
		#win div input{
			width:150
		}
		#win div a{
			margin: 15 15 5 15
		}
	</style>
  </head>
  
  <body>
     <div id="win" class="easyui-window" title="用户登录" style="width:300px;height:200px"   
        data-options="collapsible:false,
        		minimizable:false,
        		maximizable:false,
        		closable:false,
        		modal:true">   
	      <form id="userLoginForm" action="loginController/login" method="post">
	      	<div>
	      		<label for="name">用户名：</label>
	      		<input class="easyui-validatebox" type="text" name="userid" data-options="required:true" />
	      	</div>
	      	<div>
	      		<label for="name">密&nbsp;&nbsp;码：</label>
	      		<input class="easyui-validatebox" type="password" name="userpwd" data-options="required:true" />
	      	</div>
	      	<div>
	      		<a id="loginBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
	      			onclick="loginBtnClick()">
	      			 登&nbsp;&nbsp;&nbsp;&nbsp;录
	      		</a>
	      		<a id="resetBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reset'"
	      			 onclick="javascript:resetForm('userLoginForm')">
	      			  重&nbsp;&nbsp;&nbsp;&nbsp;置 
	      		</a>
	      	</div>
	      </form>
	</div>    
  </body>
</html>
