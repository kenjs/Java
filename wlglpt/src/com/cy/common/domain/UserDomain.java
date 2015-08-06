package com.cy.common.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.framework.domain.BaseUserDomain;

/**
 * HTTP Session �д洢���û���ص���Ϣ
 * 
 * @author admin
 * 
 */

@SuppressWarnings("serial")
public class UserDomain extends BaseUserDomain {
	/**
	 * ����Ա����
	 */
	public String czyDjxh = "";

	/**
	 * ����Ա����
	 */
	public String czyMc = "";
	
	/**
	 * ��ҵע�����
	 */
	public String qyZcxh = "";
	
	/**
	 * ��ҵ����
	 */
	public String qybm = "";
	
	/**
	 * ���ű���
	 */
	public String bmbm = "";
	
	/**
	 * ���ż��
	 */
	public String bmjc = "";
	
	/**
	 * ���ż������
	 */
	public String bmjbdm = "";
	
	/**
	 * ��˾����
	 */
	public String gsbm = "";
	
	/**
	 * ��˾���
	 */
	public String gsjc = "";
	
	/**
	 * ��˾�������
	 */
	public String gsjbdm = "";
	
	/**
	 * �ܹ�˾����
	 */
	public String zgsbm = "";
	
	/**
	 * �ܹ�˾���
	 */
	public String zgsjc = "";
	
	/**
	 * �ܹ�˾�������
	 */
	public String zgsjbdm = "";
	
	/**
	 * Ȩ�޻�������
	 */
	public String qxJgbm = "";
	
	/**
	 * mac��ַ
	 */
	public String macAddr = "";
	
	/**
	 * ��¼��֤����ֵ 0 OK ������ ERROR
	 */
	public Integer rtnCode;
	
	/**
	 * ��¼��֤���صĴ�����Ϣ
	 */
	public String rtnMess;
	
	/**
	 * ��¼��֤��ʽ����
	 */
	public String dlyzfsDm = "";
	
	/**
	 * ϵͳ�û�������� 1:���������Ա;2:������ҵ��Ա;3:������ҵ��Ȩ�ͻ���Ա;4:������ҵ��Ȩ������Ա
	 */
	public String xtyhflDm = "";
	
	public String gwDjxh = "";  //��λ�Ǽ����
	
	public String gwmc = "";    //��λ����
	
	/***********************ϵͳ����ֵ*****************************/
	
	/**
	 * ��װ����������λ����
	 */
	public String hwBzJldwDm;
	/**
	 * ��������������λ����
	 */
	public String hwSlJldwDm;
	/**
	 * ��������������λ����
	 */
	public String hwZlJldwDm;
	/**
	 * �������������λ����
	 */
	public String hwTjJldwDm;
	
	/**
	 * ���������
	 */
	public Double ZlTjProportion;
	/**
	 * csxh=20001,������ҵ��ֹ�˾�ͻ��������ģʽ
	 */
	public String cs_20001;
	
	public String cs_20052;
	
	/****************************************************/
	
	/**
	 * �û����ͣ�����
	 */
	public long	userType;
	
	/**
	 * �û���¼�Ƿ�ɹ�
	 */
	private boolean isLoginSuccess = false;
	
	/**
	 * �û���¼��ʾ��Ϣ
	 */
	private String loginMessage = "";
	
	/**
	 * ϵͳ����ֵMAP����¼ʱ����ʼ����ֻ�ڵ�һ��ʹ��ʱ���г�ʼ��
	 */
	private Map<String, String> xtcsMap;
	
	private String xtmlXh;
	
	private List<XtXtmlDomain> topMenuList;
	
	private List<XtGnmkDomain> subMenuList;
	
	private List<XtGnmkDomain> latestOprMenuList;
	
	/**
	 * ÿҳ��ʾ�������ݱ�xt_xtcs��xt_xtcs_czysz��������
	 */
	protected String rowNum;
	
	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public long getUserType() {
		return userType;
	}

	public void setUserType(long userType) {
		this.userType = userType;
	}

	public boolean getIsLoginSuccess() {
		return isLoginSuccess;
	}

	public void setIsLoginSuccess(boolean isLoginSuccess) {
		this.isLoginSuccess = isLoginSuccess;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public Map<String, String> getXtcsMap() {
		if (xtcsMap == null)
			xtcsMap = new HashMap<String, String>();
		return xtcsMap;
	}

	public void setXtcsMap(Map<String, String> xtcsMap) {
		this.xtcsMap = xtcsMap;
	}

	public void setLoginSuccess(boolean isLoginSuccess) {
		this.isLoginSuccess = isLoginSuccess;
	}

	public String getCzyDjxh() {
		return czyDjxh;
	}

	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}

	public String getCzyMc() {
		return czyMc;
	}

	public void setCzyMc(String czyMc) {
		this.czyMc = czyMc;
	}

	public String getQyZcxh() {
		return qyZcxh;
	}

	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh = qyZcxh;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getBmbm() {
		return bmbm;
	}

	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}

	public String getBmjc() {
		return bmjc;
	}

	public void setBmjc(String bmjc) {
		this.bmjc = bmjc;
	}

	public String getBmjbdm() {
		return bmjbdm;
	}

	public void setBmjbdm(String bmjbdm) {
		this.bmjbdm = bmjbdm;
	}

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public String getGsjc() {
		return gsjc;
	}

	public void setGsjc(String gsjc) {
		this.gsjc = gsjc;
	}

	public String getGsjbdm() {
		return gsjbdm;
	}

	public void setGsjbdm(String gsjbdm) {
		this.gsjbdm = gsjbdm;
	}

	public String getQxJgbm() {
		return qxJgbm;
	}

	public void setQxJgbm(String qxJgbm) {
		this.qxJgbm = qxJgbm;
	}

	public Integer getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(Integer rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getRtnMess() {
		return rtnMess;
	}

	public void setRtnMess(String rtnMess) {
		this.rtnMess = rtnMess;
	}

	public String getDlyzfsDm() {
		return dlyzfsDm;
	}

	public void setDlyzfsDm(String dlyzfsDm) {
		this.dlyzfsDm = dlyzfsDm;
	}

	public String getXtyhflDm() {
		return xtyhflDm;
	}

	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getZgsbm() {
		return zgsbm;
	}

	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}

	public String getZgsjc() {
		return zgsjc;
	}

	public void setZgsjc(String zgsjc) {
		this.zgsjc = zgsjc;
	}

	public String getZgsjbdm() {
		return zgsjbdm;
	}

	public void setZgsjbdm(String zgsjbdm) {
		this.zgsjbdm = zgsjbdm;
	}

	public String getXtmlXh() {
		return xtmlXh;
	}

	public void setXtmlXh(String xtmlXh) {
		this.xtmlXh = xtmlXh;
	}

	public String getHwBzJldwDm() {
		return hwBzJldwDm;
	}

	public void setHwBzJldwDm(String hwBzJldwDm) {
		this.hwBzJldwDm = hwBzJldwDm;
	}

	public String getHwSlJldwDm() {
		return hwSlJldwDm;
	}

	public void setHwSlJldwDm(String hwSlJldwDm) {
		this.hwSlJldwDm = hwSlJldwDm;
	}

	public String getHwZlJldwDm() {
		return hwZlJldwDm;
	}

	public void setHwZlJldwDm(String hwZlJldwDm) {
		this.hwZlJldwDm = hwZlJldwDm;
	}

	public String getHwTjJldwDm() {
		return hwTjJldwDm;
	}

	public void setHwTjJldwDm(String hwTjJldwDm) {
		this.hwTjJldwDm = hwTjJldwDm;
	}

	public Double getZlTjProportion() {
		return ZlTjProportion;
	}

	public void setZlTjProportion(Double zlTjProportion) {
		ZlTjProportion = zlTjProportion;
	}

	public List<XtXtmlDomain> getTopMenuList() {
		if (topMenuList == null) {
			topMenuList = new ArrayList<XtXtmlDomain>();
		}
		return topMenuList;
	}

	public void setTopMenuList(List<XtXtmlDomain> topMenuList) {
		this.topMenuList = topMenuList;
	}

	public List<XtGnmkDomain> getSubMenuList() {
		if (subMenuList == null) {
			subMenuList = new ArrayList<XtGnmkDomain>();
		}
		return subMenuList;
	}

	public void setSubMenuList(List<XtGnmkDomain> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public List<XtGnmkDomain> getLatestOprMenuList() {
		if (latestOprMenuList == null) {
			latestOprMenuList = new ArrayList<XtGnmkDomain>();
		}
		return latestOprMenuList;
	}

	public void setLatestOprMenuList(List<XtGnmkDomain> latestOprMenuList) {
		this.latestOprMenuList = latestOprMenuList;
	}

	public String getCs_20001() {
		return cs_20001;
	}

	public void setCs_20001(String cs_20001) {
		this.cs_20001 = cs_20001;
	}

	public String getGwDjxh() {
		return gwDjxh;
	}

	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh = gwDjxh;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getCs_20052() {
		return cs_20052;
	}

	public void setCs_20052(String cs_20052) {
		this.cs_20052 = cs_20052;
	}

}
