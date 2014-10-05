package com.dms.common;

/**
 * Json对象数据，传递Json对象
 * 
 * @author SemF
 * 
 * @create 2014年7月19日 下午3:46:47
 * 
 * @version 1.0
 */
public class JsonAjaxObject<T> {
	private boolean success;
	private T obj;
	private String message;

	/**
	 * 设置 JsonAjaxObject<T>信息
	 * 
	 * @param success
	 * @param obj
	 * @param message
	 */
	public void setJsonAjaxObject(boolean success, T obj, String message) {
		this.success = success;
		this.obj = obj;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
