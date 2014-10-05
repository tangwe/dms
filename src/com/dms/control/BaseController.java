package com.dms.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.dms.constant.CommonConstant;
import com.dms.pojo.User;

/**
 * 所有Controller的基类
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 上午9:59:21
 * 
 * @version 1.0
 */
public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * 获取保存在Session中的用户对象
	 * 
	 * @param request
	 * @return
	 */
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);

	}

	/**
	 * 保存用户对象到Session中
	 * 
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
	}

	/**
	 * 移除用户对象
	 * 
	 * @param request
	 * @param user
	 */
	protected void removeSessionUser(HttpServletRequest request, User user) {
		user = null;
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
		request.getSession().removeAttribute(CommonConstant.USER_CONTEXT);
	}

	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
}
