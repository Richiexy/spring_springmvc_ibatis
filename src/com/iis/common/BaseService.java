package com.iis.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * @Description: 通用接口
 * @author: 俞根海
 * @date： 2015-1-29 上午11:22:16
 */
public abstract class BaseService {

	@Autowired(required=false)
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired(required=false)
	protected SqlMapClient sqlMapClient;
	
	protected Logger log = LoggerFactory.getLogger(BaseService.class);
}
