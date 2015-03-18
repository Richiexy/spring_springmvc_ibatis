/**
 * 重置表单
 * @param {} formId
 */
function resetForm(formId){
	jQuery('#'+formId).form('clear');
}

/**
 * 获取随机数
 * @return {}
 */
function getRandom(){
	return Math.floor(Math.random()*99999+1);
}

/**
 * 表单提交
 * @param {} winId 
 * @param {} gridId 待刷新的表格
 * @param {} formId
 * @param {} formAction
 */
function submitFormData(winId , gridId , formId , formAction){
	jQuery("#"+formId).form('submit',{
		url: basePath + formAction+'?rondom='+ getRandom(),
		dataType:"text",
		success: function(txt){
			if(gridId){
				jQuery("#" + gridId).datagrid('reload');
			}
			jQuery("#" + winId).window('close');//默认情况下的添加窗口是关闭的
			jQuery("#" + formId).form('clear');
			jQuery.messager.alert("提示!","保存成功！");
	    }
	})
}