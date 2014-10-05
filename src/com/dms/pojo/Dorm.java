package com.dms.pojo;

/**
 * 宿舍（苑）实体
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午11:17:23
 * 
 * @version 1.0
 */
public class Dorm {
	/**
	 * 宿舍（苑）序列号
	 */
	private Integer id;
	/**
	 * 宿舍（苑）名称 如：菊苑
	 */
	private String dName;
	/**
	 * 宿舍（苑）描述
	 */
	private String dDesc;
	/**
	 * 状态 0不可用 1可用
	 */
	private int state;

	/**
	 * 无参数实例化
	 */
	public Dorm() {
		super();
	}

	/**
	 * 有参数实例化
	 * 
	 * @param id
	 * @param dName
	 * @param dDesc
	 * @param state
	 */
	public Dorm(Integer id, String dName, String dDesc, int state) {
		super();
		this.id = id;
		this.dName = dName;
		this.dDesc = dDesc;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdDesc() {
		return dDesc;
	}

	public void setdDesc(String dDesc) {
		this.dDesc = dDesc;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
