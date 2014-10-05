package com.dms.common;

import java.util.List;

import com.dms.constant.CommonConstant;

/**
 * 
 * 分页帮助类
 * 
 * @author Sem
 * 
 * @create 2014年7月22日 上午11:40:20
 * 
 * @version 1.0
 */
public class PaginationHelper {
	private List<?> dataList;
	private Integer pageSize;
	private Long rowCount;
	private Integer totalPage;

	public PaginationHelper() {
		super();
	}

	public PaginationHelper(List<?> dataList, Long rowCount) {
		super();
		this.dataList = dataList;
		this.pageSize = CommonConstant.PAGE_SIZE;
		this.rowCount = rowCount;
	}

	public Long getRowCount() {
		return rowCount;
	}

	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		totalPage = (int) ((rowCount % pageSize == 0) ? rowCount / pageSize : rowCount / pageSize + 1);
		return totalPage;
	}
}
