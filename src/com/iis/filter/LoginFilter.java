package com.iis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iis.model.User;
import com.iis.util.StringUtil;

public class LoginFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(LoginFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String uri = request.getRequestURI().toLowerCase();
		
		//图片、样式文件等放行
		if( uri.endsWith(".jpg") || uri.endsWith(".gif") || uri.endsWith(".js")  
	        || uri.endsWith(".css") || uri.endsWith(".xml") || uri.endsWith(".html") 
	        || uri.endsWith(".htm") || uri.endsWith(".swf") || uri.endsWith(".xls")
	        || uri.endsWith(".png") || uri.endsWith(".ico")||uri.contains("audit_up.jsp")) {
			arg2.doFilter(arg0, arg1);
			return;
	    }
		
		//登录首页
		if(uri.equals(request.getContextPath() + "/") ||
				(uri.indexOf("logincontroller") != -1)) {
			arg2.doFilter(arg0, arg1);
			return;
			
		}else{
			User user = (User) request.getSession().getAttribute("user");
			if(StringUtil.isNull(user)){
				log.info("=======================非法用户======================");
				
				request.setAttribute("errorMessage", "请先登录系统！");
				//以下方式不会改变地址栏路径，需再次点击登录才行（在jquery.messager.alert方法的回调函数中再次跳转到首页）
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
				//以下接收不到errorMessage
//				response.sendRedirect(request.getContextPath()+"/");
				return;
			}else{
				arg2.doFilter(arg0, arg1);
				return;
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
