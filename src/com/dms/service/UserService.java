package com.dms.service;

import com.dms.common.PaginationHelper;
import com.dms.pojo.User;

/**
 * 用户service
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午4:56:51
 * 
 * @version 1.0
 */
public interface UserService {
	/**
	 * 用户登陆 学生或管理员登陆
	 * 
	 * @param account
	 *            用户名
	 * @param pwd
	 *            密码
	 * @param type
	 *            类型 1学生 0管理员
	 * @return
	 */
	public User login(String account, String pwd, int type);

	/**
	 * 管理员基本学生数据视图查询显示
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord1
	 * @param searchWord2
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getStuView(int pageNow, int pageSize, String searchWord1, String searchWord2) throws Exception;

	/**
	 * 管理员数据查询显示
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	public PaginationHelper getManView(int pageNow, int pageSize, String searchWord) throws Exception;

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @param newPwd
	 * @return
	 */
	public User updatePwd(User user, String oldPwd, String newPwd);
}
