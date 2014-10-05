package com.dms.pojo;

import java.util.Date;

import com.dms.common.CommonUtil;

/**
 * 寻失物品（寻物启事，失物招领）
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午11:57:21
 * 
 * @version 1.0
 */
public class LostFound {
	/**
	 * 寻失物品序列
	 */
	private Integer id;
	/**
	 * 寻失物品标签
	 */
	private String lfTag;
	/**
	 * 寻失物品内容
	 */
	private String lfContent;
	/**
	 * 寻失物品发布时间
	 */
	private Date lfDate;
	/**
	 * 寻失物品标识（0寻物启事1失物招领）
	 */
	private int lfType;
	/**
	 * 寻失物品状态（0消失1展示）
	 */
	private int state;
	/**
	 * 学生关联序列
	 */
	private int stuID;

	/**
	 * 无参数实例化
	 */
	public LostFound() {
		super();
	}

	/**
	 * 有参数实例化
	 * 
	 * @param id
	 * @param lfTag
	 * @param lfContent
	 * @param lfDate
	 * @param lfType
	 * @param state
	 * @param stuID
	 */
	public LostFound(Integer id, String lfTag, String lfContent, Date lfDate, int lfType, int state, int stuID) {
		super();
		this.id = id;
		this.lfTag = lfTag;
		this.lfContent = lfContent;
		this.lfDate = lfDate;
		this.lfType = lfType;
		this.state = state;
		this.stuID = stuID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLfTag() {
		return lfTag;
	}

	public void setLfTag(String lfTag) {
		this.lfTag = lfTag;
	}

	public String getLfContent() {
		return lfContent;
	}

	public void setLfContent(String lfContent) {
		this.lfContent = lfContent;
	}

	public Date getLfDate() {
		return lfDate;
	}

	public String getLfDate_toS() {
		return CommonUtil.formatDate(lfDate);
	}

	public void setLfDate(Date lfDate) {
		this.lfDate = lfDate;
	}

	public int getLfType() {
		return lfType;
	}

	public void setLfType(int lfType) {
		this.lfType = lfType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getStuID() {
		return stuID;
	}

	public void setStuID(int stuID) {
		this.stuID = stuID;
	}

}
