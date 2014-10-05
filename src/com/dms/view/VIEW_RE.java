package com.dms.view;

import java.util.Date;

import com.dms.common.CommonUtil;

/**
 * 报修视图
 * 
 * @author SemF
 * 
 * @create 2014年9月3日 下午10:38:48
 * 
 * @version 1.0
 */
public class VIEW_RE {
	private Integer ID;
	private String DRENAME;
	private String RECONTENT;
	private Date RESDATE;
	private String REMREPLY;
	private Date REMDATE;
	private int STATE;
	private String RNO;
	private String RDESC;
	private String DNAME;
	private String DDESC;
	private String MNO;
	private String MNAME;
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

	public String getDRENAME() {
		return DRENAME;
	}

	public void setDRENAME(String dRENAME) {
		DRENAME = dRENAME;
	}

	public String getRECONTENT() {
		return RECONTENT;
	}

	public void setRECONTENT(String rECONTENT) {
		RECONTENT = rECONTENT;
	}

	public Date getRESDATE() {
		return RESDATE;
	}

	public String getRESDATE_toS() {
		return RESDATE == null ? "" : CommonUtil.formatDate(RESDATE);
	}

	public void setRESDATE(Date rESDATE) {
		RESDATE = rESDATE;
	}

	public String getREMREPLY() {
		return REMREPLY;
	}

	public void setREMREPLY(String rEMREPLY) {
		REMREPLY = rEMREPLY;
	}

	public Date getREMDATE() {
		return REMDATE;
	}

	public String getREMDATE_toS() {
		return REMDATE == null ? "" : CommonUtil.formatDate(REMDATE);
	}

	public void setREMDATE(Date rEMDATE) {
		REMDATE = rEMDATE;
	}

	public int getSTATE() {
		return STATE;
	}

	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}

	public String getRNO() {
		return RNO;
	}

	public void setRNO(String rNO) {
		RNO = rNO;
	}

	public String getRDESC() {
		return RDESC;
	}

	public void setRDESC(String rDESC) {
		RDESC = rDESC;
	}

	public String getDNAME() {
		return DNAME;
	}

	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}

	public String getDDESC() {
		return DDESC;
	}

	public void setDDESC(String dDESC) {
		DDESC = dDESC;
	}

	public String getMNO() {
		return MNO;
	}

	public void setMNO(String mNO) {
		MNO = mNO;
	}

	public String getMNAME() {
		return MNAME;
	}

	public void setMNAME(String mNAME) {
		MNAME = mNAME;
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
