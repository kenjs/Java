package com.cy.common.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.framework.domain.BaseUserDomain;

/**
 * HTTP Session 中存储的用户相关的信息
 * 
 * @author admin
 * 
 */

@SuppressWarnings("serial")
public class UserDomain extends BaseUserDomain {
	/**
	 * 操作员代码
	 */
	public String czyDjxh = "";

	/**
	 * 操作员名称
	 */
	public String czyMc = "";
	
	/**
	 * 企业注册序号
	 */
	public String qyZcxh = "";
	
	/**
	 * 企业编码
	 */
	public String qybm = "";
	
	/**
	 * 部门编码
	 */
	public String bmbm = "";
	
	/**
	 * 部门简称
	 */
	public String bmjc = "";
	
	/**
	 * 部门级别代码
	 */
	public String bmjbdm = "";
	
	/**
	 * 公司编码
	 */
	public String gsbm = "";
	
	/**
	 * 公司简称
	 */
	public String gsjc = "";
	
	/**
	 * 公司级别代码
	 */
	public String gsjbdm = "";
	
	/**
	 * 总公司编码
	 */
	public String zgsbm = "";
	
	/**
	 * 总公司简称
	 */
	public String zgsjc = "";
	
	/**
	 * 总公司级别代码
	 */
	public String zgsjbdm = "";
	
	/**
	 * 权限机构编码
	 */
	public String qxJgbm = "";
	
	/**
	 * mac地址
	 */
	public String macAddr = "";
	
	/**
	 * 登录验证返回值 0 OK ，其它 ERROR
	 */
	public Integer rtnCode;
	
	/**
	 * 登录验证返回的错误信息
	 */
	public String rtnMess;
	
	/**
	 * 登录验证方式代码
	 */
	public String dlyzfsDm = "";
	
	/**
	 * 系统用户分类代码 1:管理服务人员;2:物流企业人员;3:物流企业授权客户人员;4:物流企业授权银行人员
	 */
	public String xtyhflDm = "";
	
	public String gwDjxh = "";  //岗位登记序号
	
	public String gwmc = "";    //岗位名称
	
	/***********************系统参数值*****************************/
	
	/**
	 * 包装基本计量单位代码
	 */
	public String hwBzJldwDm;
	/**
	 * 数量基本计量单位代码
	 */
	public String hwSlJldwDm;
	/**
	 * 重量基本计量单位代码
	 */
	public String hwZlJldwDm;
	/**
	 * 体积基本计量单位代码
	 */
	public String hwTjJldwDm;
	
	/**
	 * 重量体积比
	 */
	public Double ZlTjProportion;
	/**
	 * csxh=20001,物流企业或分公司客户货物分类模式
	 */
	public String cs_20001;
	
	public String cs_20052;
	
	/****************************************************/
	
	/**
	 * 用户类型（）。
	 */
	public long	userType;
	
	/**
	 * 用户登录是否成功
	 */
	private boolean isLoginSuccess = false;
	
	/**
	 * 用户登录提示信息
	 */
	private String loginMessage = "";
	
	/**
	 * 系统参数值MAP，登录时不初始化，只在第一次使用时进行初始化
	 */
	private Map<String, String> xtcsMap;
	
	private String xtmlXh;
	
	private List<XtXtmlDomain> topMenuList;
	
	private List<XtGnmkDomain> subMenuList;
	
	private List<XtGnmkDomain> latestOprMenuList;
	
	/**
	 * 每页显示数量根据表xt_xtcs和xt_xtcs_czysz进行设置
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
