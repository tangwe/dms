package com.dms.common;

/**
 * Json操作结果信息类，描述操作成功，失败
 * 
 * @author SemF
 * 
 * @create 2014年7月16日 下午3:38:40
 * 
 * @version 1.0
 */
public class JsonAjaxResult {
	private boolean success;
	private String message;

	/**
	 * 设置jsonResult信息
	 * 
	 * @param success
	 * @param message
	 */
	public void setJsonResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
