package com.dms.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sem
 * 
 * @create 2014年7月20日 下午3:56:14
 * 
 * @version 1.0
 */
public class BasePath {
	/**
	 * 获取项目路径 http://localhost:8080/BBS
	 * 
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}
}
