package com.dms.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.common.DataGridResponse;
import com.dms.common.JsonAjaxObject;
import com.dms.common.JsonAjaxResult;
import com.dms.common.PaginationHelper;
import com.dms.pojo.User;
import com.dms.service.UserService;
import com.dms.view.VIEW_MAN;
import com.dms.view.VIEW_STU;

/**
 * UserController用户控制器
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 下午5:19:49
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/UserController")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	private DataGridResponse dataGrid = new DataGridResponse();
	private JsonAjaxResult ajaxResult = new JsonAjaxResult();
	private JsonAjaxObject<User> ajaxObject = new JsonAjaxObject<User>();

	/**
	 * 学生登陆
	 * 
	 * @param account
	 * @param pwd
	 * @param userType
	 * @param request
	 * @return
	 */
	@RequestMapping("/stuLogin.do")
	public String stuLogin(String account, String pwd, Integer userType, HttpServletRequest request) {
		System.out.println("userType:  " + userType);
		System.out.println("account:   " + account);
		System.out.println("pwd:  " + pwd);
		User user = userService.login(account, pwd, userType);
		if (user != null) {
			// 保存到Session
			this.setSessionUser(request, user);
			return "redirect:/room_mark.html";
		}
		return "redirect:/login.html";
	}

	/**
	 * 管理员登陆
	 * 
	 * @param account
	 * @param pwd
	 * @param userType
	 * @param request
	 * @return
	 */
	@RequestMapping("/manLogin.do")
	public String manLogin(String account, String pwd, Integer userType, HttpServletRequest request) {
		System.out.println("userType:  " + userType);
		System.out.println("account:   " + account);
		System.out.println("pwd:  " + pwd);
		User user = userService.login(account, pwd, userType);
		if (user != null) {
			// 保存到Session
			this.setSessionUser(request, user);
			return "redirect:/admin/main.html";
		}
		return "redirect:/admin/login.html";
	}

	/**
	 * 管理员加载管理学生信息
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param searchWord1
	 * @param searchWord2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadStuView.do")
	@ResponseBody
	public DataGridResponse loadStuView(Integer page, Integer rows, String searchWord1, String searchWord2) {
		try {
			Integer pageNow = page;
			Integer pageSize = rows;
			if (searchWord1 == null || "".equals(searchWord1)) {
				searchWord1 = "";
			}
			if (searchWord2 == null || "".equals(searchWord2)) {
				searchWord2 = "";
			}
			PaginationHelper ph = userService.getStuView(pageNow, pageSize, searchWord1, searchWord2);
			List<VIEW_STU> dataList = (List<VIEW_STU>) ph.getDataList();
			long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 加载管理员数据
	 * 
	 * @param page
	 * @param rows
	 * @param searchWord
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loadManView.do")
	@ResponseBody
	public DataGridResponse loadManView(Integer page, Integer rows, String searchWord) {
		try {
			Integer pageNow = page;
			Integer pageSize = rows;
			if (searchWord == null || "".equals(searchWord)) {
				searchWord = "";
			}
			PaginationHelper ph = userService.getManView(pageNow, pageSize, searchWord);
			List<VIEW_MAN> dataList = (List<VIEW_MAN>) ph.getDataList();
			long total = ph.getRowCount();
			dataGrid.setDataGridResponse(dataList, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public JsonAjaxResult updatePwd(HttpServletRequest request, String oldPwd, String newPwd) {
		User user = userService.updatePwd(this.getSessionUser(request), oldPwd, newPwd);
		if (user == null) {
			ajaxResult.setJsonResult(false, "修改用户密码失败！");
		} else {
			ajaxResult.setJsonResult(true, "修改用户[" + user.getUserName() + "]密码成功！");
		}
		return ajaxResult;
	}

	/**
	 * 用户注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLogout.do")
	@ResponseBody
	public JsonAjaxResult doLogout(HttpServletRequest request) {
		User u = this.getSessionUser(request);
		if (u != null) {
			this.removeSessionUser(request, u);
			ajaxResult.setJsonResult(true, "用户已经注销！");
		} else {
			ajaxResult.setJsonResult(false, "用户不存在登陆信息！");
		}
		return ajaxResult;
	}

	/**
	 * 获取Session用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/loadSessionUserInfo.do")
	@ResponseBody
	public JsonAjaxObject<User> loadSessionUserInfo(HttpServletRequest request) {
		User u = this.getSessionUser(request);
		if (u != null) {
			ajaxObject.setJsonAjaxObject(true, u, "用户信息获取成功！");
		} else {
			ajaxObject.setJsonAjaxObject(false, null, "用户信息获取失败！");
		}
		return ajaxObject;
	}
}
