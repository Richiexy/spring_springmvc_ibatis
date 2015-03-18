package com.iis.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iis.common.BaseService;
import com.iis.model.User;
import com.iis.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements IUserService {

	public User getByUserId(String userid) {
		
		try {
			return (User)sqlMapClient.queryForObject("findUserByID" , userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Object> getUserList(Map<String, String> paraMap,
			User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean batchDel(String keyIds) {
		// TODO Auto-generated method stub
		return false;
	}

	public void editUser(User tmpUser, User user) {
		// TODO Auto-generated method stub
		
	}

	public void addUser(User tmpUser, User user) {
		// TODO Auto-generated method stub
		
	} 

}
