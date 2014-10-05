package com.dms.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dms.constant.CommonConstant;

/**
 * @author SemF
 * 
 * @create 2014年9月15日
 * 
 * @version 1.0
 */
public class SecurityInterceptor implements Filter {
	// 不拦截urlfilter
	private String[] urlFilter = { "login.html", "logout.html", "Login.do", "Logout.do" };

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		// System.out.println("url" + url);
		boolean flag = false;
		for (String filter : urlFilter) {
			if (url.indexOf(filter) != -1) {
				flag = true;
				break;
			}
		}
		if (flag) {
			chain.doFilter(request, response);
		} else {
			Object obj = req.getSession(true).getAttribute(CommonConstant.USER_CONTEXT);
			if (obj == null || "".equals(obj)) {
				// 项目上下文路径
				String basePath = req.getContextPath() + "/";
				res.sendRedirect(basePath + "login.html");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
