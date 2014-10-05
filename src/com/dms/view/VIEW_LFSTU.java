package com.dms.view;

import java.util.Date;

import com.dms.common.CommonUtil;

/**
 * 学生寻失视图，关联表： 学生表，寻失表
 * 
 * @author SemF
 * 
 * @create 2014年9月2日 上午11:12:59
 * 
 * @version 1.0
 */
public class VIEW_LFSTU {
	private Integer ID;
	private String LFTAG;
	private String LFCONTENT;
	private Date LFDATE;
	private int LFTYPE;
	private int STATE;
	private String SNO;
	private String SNAME;
	private String SFROM;
	private String STEL;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getLFTAG() {
		return LFTAG;
	}

	public void setLFTAG(String lFTAG) {
		LFTAG = lFTAG;
	}

	public String getLFCONTENT() {
		return LFCONTENT;
	}

	public void setLFCONTENT(String lFCONTENT) {
		LFCONTENT = lFCONTENT;
	}

	public Date getLFDATE() {
		return LFDATE;
	}

	public String getLFDATE_tsS() {
		return CommonUtil.formatDate(LFDATE);
	}

	public void setLFDATE(Date lFDATE) {
		LFDATE = lFDATE;
	}

	public int getLFTYPE() {
		return LFTYPE;
	}

	public void setLFTYPE(int lFTYPE) {
		LFTYPE = lFTYPE;
	}

	public int getSTATE() {
		return STATE;
	}

	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}

	public String getSNO() {
		return SNO;
	}

	public void setSNO(String sNO) {
		SNO = sNO;
	}

	public String getSNAME() {
		return SNAME;
	}

	public void setSNAME(String sNAME) {
		SNAME = sNAME;
	}

	public String getSFROM() {
		return SFROM;
	}

	public void setSFROM(String sFROM) {
		SFROM = sFROM;
	}

	public String getSTEL() {
		return STEL;
	}

	public void setSTEL(String sTEL) {
		STEL = sTEL;
	}

}
