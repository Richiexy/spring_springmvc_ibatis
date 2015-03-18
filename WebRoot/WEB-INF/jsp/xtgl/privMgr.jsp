<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="easyui.adminLayout" flush="true"> 
	<tiles:putAttribute name="right" value="/WEB-INF/jsp/xtgl/privMgrBase.jsp" /> 
</tiles:insertDefinition>