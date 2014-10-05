package com.dms.common;

import java.util.List;

/**
 * Json列表数据，主要传递list数据集合
 * 
 * @author SemF
 * 
 * @create 2014年7月16日 下午3:43:00
 * 
 * @version 1.0
 * @param <E>
 */
public class JsonAjaxData<E> {
	private boolean success;
	private List<E> dataList;
	private Integer totalPage;
	private Integer totalCount;
	private String message;

	/**
	 * 设置 JsonData<E> 信息
	 * 
	 * @param success
	 * @param dataList
	 * @param message
	 */
	public void setJsonData(boolean success, List<E> dataList,  Integer totalCount, Integer totalPage,String message) {
		this.success = success;
		this.dataList = dataList;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<E> getDataList() {
		return dataList;
	}

	public void setDataList(List<E> dataList) {
		this.dataList = dataList;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
