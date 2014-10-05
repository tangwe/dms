package com.dms.test;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <b>Summary: </b> TODO Junit 基础类,加载环境
 * 
 * @author SemF
 * 
 * @create 2014年9月1日 上午10:23:10
 * 
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "file:WebContent/WEB-INF/spring-servlet.xml" })
public class JunitBase extends AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * <b>Summary: </b> 复写方法 setDataSource
	 * 
	 * @param dataSource
	 * @see org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests#setDataSource(javax.sql.DataSource)
	 */
	@Override
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
}
