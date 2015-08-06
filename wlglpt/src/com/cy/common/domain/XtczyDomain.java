package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR YYS_DM_USER_BCXX is created by tools.
 * @author HJH
 */

public class XtczyDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czryDm;                           // 操作人员代码(YHBZ=Y为CTAIS中的CZRY_DM,否则SEQ_CZY_XH)
	private String czryMc;                           // 操作人员名称
	private String name;                             // 登录用户名
	private String password;                         // 登录密码
	private String yhsm;                             // 用户说明
	private String yhbz;                             // 用户级别(0 超级管理员 , 1 管理员,2 一般用户,3 自建用户)
	private String yhjb;							 //管理员级别(0 超级管理员，1 一级管理员，2 二级管理员，3 三级管理员，....)
	private String swjgDm;                           // 税务机关代码
	private String cjglybz;                          // 超级管理员标志(Y/N)
	private String ssglybz;							 //税收管理员标志
	private String  spbz;							 //审批权限标志（1 是审批人员，0 或null为非）
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	
	private String swjgMc;                           // 税务机关名称
	private String cjglybzMc;						 //超级管理员标志名称 是 ：否
	private String qybzMc;						     //启用标志名称 是 ：否
	private String yxbzMc;						     //有效标志名称 是 ：否
	
	private List<XtczyDomain> dataList;		 //查询结果集	
	private List swjgList;							//下拉税务机关

	public List getSwjgList() {
		if (swjgList == null)
			return swjgList = new ArrayList();
		return swjgList;
	}

	public void setSwjgList(List swjgList) {
		this.swjgList = swjgList;
	}

	public String getSwjgMc() {
		return swjgMc;
	}

	public void setSwjgMc(String swjgMc) {
		this.swjgMc = swjgMc;
	}

	public XtczyDomain() {
	}

	//获取操作人员代码(YHBZ=Y为CTAIS中的CZRY_DM,否则SEQ_CZY_XH)
	public String getCzryDm() {
		return this.czryDm;
	}

	//设置操作人员代码(YHBZ=Y为CTAIS中的CZRY_DM,否则SEQ_CZY_XH)
	public void setCzryDm(String czryDm) {
		this.czryDm=czryDm;
	}

	//获取操作人员名称
	public String getCzryMc() {
		return this.czryMc;
	}

	//设置操作人员名称
	public void setCzryMc(String czryMc) {
		this.czryMc=czryMc;
	}

	//获取登录用户名
	public String getName() {
		return this.name;
	}

	//设置登录用户名
	public void setName(String name) {
		this.name=name;
	}

	//获取登录密码
	public String getPassword() {
		return this.password;
	}

	//设置登录密码
	public void setPassword(String password) {
		this.password=password;
	}

	//获取用户说明
	public String getYhsm() {
		return this.yhsm;
	}

	//设置用户说明
	public void setYhsm(String yhsm) {
		this.yhsm=yhsm;
	}

	//获取CTAIS用户标志(Y/N)
	public String getYhbz() {
		return this.yhbz;
	}

	//设置CTAIS用户标志(Y/N)
	public void setYhbz(String yhbz) {
		this.yhbz=yhbz;
	}

	//获取税务机关代码
	public String getSwjgDm() {
		return this.swjgDm;
	}

	//设置税务机关代码
	public void setSwjgDm(String swjgDm) {
		this.swjgDm=swjgDm;
	}

	//获取超级管理员标志(Y/N)
	public String getCjglybz() {
		return this.cjglybz;
	}

	//设置超级管理员标志(Y/N)
	public void setCjglybz(String cjglybz) {
		this.cjglybz=cjglybz;
	}

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public List<XtczyDomain> getDataList() {
		if(dataList == null )
			return dataList = new ArrayList<XtczyDomain>();
		return dataList;
	}

	public void setDataList(List<XtczyDomain> dataList) {
		this.dataList = dataList;
	}

	public String getCjglybzMc() {
		return cjglybzMc;
	}

	public void setCjglybzMc(String cjglybzMc) {
		this.cjglybzMc = cjglybzMc;
	}

	public String getQybzMc() {
		return qybzMc;
	}

	public void setQybzMc(String qybzMc) {
		this.qybzMc = qybzMc;
	}

	public String getYxbzMc() {
		return yxbzMc;
	}

	public void setYxbzMc(String yxbzMc) {
		this.yxbzMc = yxbzMc;
	}

	public String getYhjb() {
		return yhjb;
	}

	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}

	public String getSsglybz() {
		return ssglybz;
	}

	public void setSsglybz(String ssglybz) {
		this.ssglybz = ssglybz;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
}