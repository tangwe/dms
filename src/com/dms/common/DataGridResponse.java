package com.dms.common;

import java.util.List;

/**
 * 用于jQuery easy UI 展现数据
 * 
 * @author SemF
 * 
 * @create 2014年9月5日 下午4:40:36
 * 
 * @version 1.0
 */
public class DataGridResponse {
	private List<?> rows;
	private long total;

	public void setDataGridResponse(List<?> rows, long total) {
		this.rows = rows;
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
