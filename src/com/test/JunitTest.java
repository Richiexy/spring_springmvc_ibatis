package com.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.iis.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContext-data.xml", 
		"classpath:applicationContext-kaptcha.xml", 
		"classpath:applicationContext-quartz.xml" })
public class JunitTest extends Throwable{
	
	@Autowired
	public ApplicationContext app;
	
	@Autowired(required = false)
	private JdbcTemplate jdbcTemplate;
	
	@Autowired(required=false)
	private SqlMapClient sqlMapClient ;
	
	@Before
	public void doBefore() throws Exception {
		System.out.println("----------------doBefore---------------------");
	}
	
	@Test
	@Transactional(value="transactionManager")
	public void testJdbc(){
		
		jdbcTemplate.execute("insert into t_user(username,password) values ('test2','test2')");
		
		Map<String,Object> retMap = jdbcTemplate.queryForMap("select id from t_user where username = 'admin'");
		System.out.println(retMap);
	}
	
	@Test
	@Transactional(value="transactionManager",propagation=Propagation.NEVER)
	public void testIbatis(){
		List<User> userList = null;
		try {
			userList = sqlMapClient.queryForList("findAllUser");
//			userList = sqlMapClient.queryForPaginatedList("findAllUser", 2);
			/*更新
			User u = new User(1,"admin","1");
			sqlMapClient.update("updateUser", u);
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(User user : userList){
			System.out.println("----- " + user.getUsername() + "-------");
		}
		
	}
	
	@After
	public void doAfter() throws Exception {
		System.out.println("----------------doAfter---------------------");
	}
	
}
