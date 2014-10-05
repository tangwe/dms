package com.dms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * DAO基类
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午1:37:07
 * 
 * @version 1.0
 */
public class BaseDAO {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
