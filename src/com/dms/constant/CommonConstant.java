package com.dms.constant;

/**
 * 整个应用通用的常量 <br>
 * <b>类描述:</b>
 * 
 * <pre>
 * |
 * </pre>
 * 
 * @see
 * @since
 */
public class CommonConstant {
	/**
	 * 用户对象放到Session中的键名称
	 */
	public static final String USER_CONTEXT = "USER_CONTEXT";

	/**
	 * 将登录前的URL放到Session中的键名称
	 */
	public static final String LOGIN_TO_URL = "toUrl";

	/**
	 * 每页的记录数
	 */
	public static final int PAGE_SIZE = 6;
	/**
	 * 管理员
	 */
	public static final int ADMIN = 0;
	/**
	 * 学生
	 */
	public static final int STUDENT = 1;
}
