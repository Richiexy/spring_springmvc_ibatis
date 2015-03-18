package com.iis.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iis.model.User;
import com.iis.service.IUserService;
import com.iis.util.JacksonUtil;
import com.iis.util.StringUtil;

/**
 * @Description: 用户登录
 * @author: 俞根海
 * @date： 2015-1-28 下午2:05:00
 */
@Controller
@RequestMapping("/loginController")
@SessionAttributes("user")
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired(required = false)
	private IUserService userService;
	
	@RequestMapping(value="/login")
	public String login(@RequestParam Map<String, String> paraMap,
			HttpServletRequest request,
			HttpSession session,
			ModelMap map){
		String userId = paraMap.containsKey("userid") ? paraMap.get("userid") : "";
		String userpwd = paraMap.containsKey("userpwd") ? paraMap.get("userpwd") : "";
		User user = userService.getByUserId(userId);
		//权限判断
		log.info(JacksonUtil.serializeObjectToJson(user , true)); 
		if(StringUtil.isNoNull(user) && userpwd.equals(user.getPassword())){
			session.setAttribute("user", user);
			return "frame_main";
		}else{
			map.put("errorMessage", "用户名或者密码有误，请确认！");
			return "index";
		}
		
	}
}
