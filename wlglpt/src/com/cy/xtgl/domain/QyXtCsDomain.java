package com.cy.xtgl.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.cy.framework.domain.BaseBusinessDomain;
/**
 * THE DOMAIN FOR 企业-系统-参数信息
 * @author HCM
 */
public class QyXtCsDomain extends BaseBusinessDomain{
	private static final long serialVersionUID = 1L;
	private String ssJgbm;                         // 仅用来放页面传递来的机构编码(检查条件)
	private String ssJgmc;                         // 仅用来放页面传递来的机构名称
	private String jgbm;                           //机构编码
	private String dwmc;                           //单位名称
	private String jbdm;                           //级别代码
	private String csxh;                           //参数序号
	private String cslbDm;                         //参数类别代码
	private String csmc;                           //参数名称
	private String sysm;                           //使用说明
	private String sjxlbDm;                        //数据项类别代码
	private String xzxmDm;                         //选择项目
	private String csz;                            //参数值

	private List<BaseBusinessDomain> dataList; 		//查询列表
	
	private List<DmXzxmDomain> dmXzxmList;                //悬着项目list
	private String xzxmValueMc;
	private String xzxmValueDm;

	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}
	public String getJgbm() {
		return jgbm;
	}
	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getJbdm() {
		return jbdm;
	}
	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}
	public String getCsxh() {
		return csxh;
	}
	public void setCsxh(String csxh) {
		this.csxh = csxh;
	}
	public String getCslbDm() {
		return cslbDm;
	}
	public void setCslbDm(String cslbDm) {
		this.cslbDm = cslbDm;
	}
	public String getCsmc() {
		return csmc;
	}
	public void setCsmc(String csmc) {
		this.csmc = csmc;
	}
	public String getSysm() {
		return sysm;
	}
	public void setSysm(String sysm) {
		this.sysm = sysm;
	}
	public String getSjxlbDm() {
		return sjxlbDm;
	}
	public void setSjxlbDm(String sjxlbDm) {
		this.sjxlbDm = sjxlbDm;
	}
	public String getXzxmDm() {
		return xzxmDm;
	}
	public void setXzxmDm(String xzxmDm) {
		this.xzxmDm = xzxmDm;
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz;
	}
	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public List<DmXzxmDomain> getDmXzxmList() {
		if(dmXzxmList==null){
			dmXzxmList=new ArrayList<DmXzxmDomain>();
		}
		return dmXzxmList;
	}
	public void setDmXzxmList(List<DmXzxmDomain> dmXzxmList) {
		this.dmXzxmList = dmXzxmList;
	}

	public String getXzxmValueMc() {
		return xzxmValueMc;
	}
	public void setXzxmValueMc(String xzxmValueMc) {
		this.xzxmValueMc = xzxmValueMc;
	}
	public String getXzxmValueDm() {
		return xzxmValueDm;
	}
	public void setXzxmValueDm(String xzxmValueDm) {
		this.xzxmValueDm = xzxmValueDm;
	}
}
