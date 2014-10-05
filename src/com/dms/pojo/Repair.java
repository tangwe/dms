package com.dms.pojo;

import java.util.Date;

import com.dms.common.CommonUtil;

/**
 * 报修实体
 * 
 * @author SemF
 * 
 * @create 2014年8月30日 下午11:44:44
 * 
 * @version 1.0
 */
public class Repair {
	/**
	 * 报修序列
	 */
	private Integer id;
	/**
	 * 报修名称
	 */
	private String reName;
	/**
	 * 报修内容
	 */
	private String reContent;
	/**
	 * 发布报修时间
	 */
	private Date reStuDate;
	/**
	 * 寝室关联ID
	 */
	private Integer roomID;
	/**
	 * 学生关联序列
	 */
	private Integer stuID;
	/**
	 * 管理员审核回复报修内容
	 */
	private String reReply;
	/**
	 * 管理员审核时间
	 */
	private Date reManDate;
	/**
	 * 管理员关联序列
	 */
	private Integer manID;
	/**
	 * 报修状态0审核终止状态1提交申请报修2审核通过维修3审核未通过
	 */
	private int state;

	/**
	 * 无参数实例化
	 */
	public Repair() {
		super();
	}

	/**
	 * 有参数实例化
	 * 
	 * @param id
	 * @param reName
	 * @param reContent
	 * @param reStuDate
	 * @param stuID
	 * @param reReply
	 * @param reManDate
	 * @param manID
	 * @param state
	 */
	public Repair(Integer id, String reName, String reContent, Date reStuDate, Integer stuID, String reReply, Date reManDate, Integer manID, int state) {
		super();
		this.id = id;
		this.reName = reName;
		this.reContent = reContent;
		this.reStuDate = reStuDate;
		this.stuID = stuID;
		this.reReply = reReply;
		this.reManDate = reManDate;
		this.manID = manID;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public Date getReStuDate() {
		return reStuDate;
	}

	public String getReStuDate_toS() {
		return CommonUtil.formatDate(reStuDate);
	}

	public void setReStuDate(Date reStuDate) {
		this.reStuDate = reStuDate;
	}

	public Integer getStuID() {
		return stuID;
	}

	public void setStuID(Integer stuID) {
		this.stuID = stuID;
	}

	public String getReReply() {
		return reReply;
	}

	public void setReReply(String reReply) {
		this.reReply = reReply;
	}

	public Date getReManDate() {
		return reManDate;
	}

	public String getReManDate_toS() {
		return CommonUtil.formatDate(reManDate);
	}

	public void setReManDate(Date reManDate) {
		this.reManDate = reManDate;
	}

	public Integer getManID() {
		return manID;
	}

	public void setManID(Integer manID) {
		this.manID = manID;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

}
