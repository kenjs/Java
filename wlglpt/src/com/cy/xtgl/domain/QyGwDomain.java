package com.cy.xtgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_GW is created by tools.
 * @author HJH
 */

public class QyGwDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gwDjxh;                           // 岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	private String gwMc;                             // 岗位名称
	private String gwJc;                             // 岗位简称
	private String bzsm;                             // 备注说明
	private String lybz;                             // 来源标志(Y企业创建/N岗位代码)
	private String gwDm;                               // 岗位代码
	private String ssJgbm;                             // 所属机构编码(部门)
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                         // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                         // 修改人
	private String xgrq;                             // 修改日期
	private String dwDjxh;
	private String bmMc;
	private List<BaseBusinessDomain> dataList; 		//查询列表
	private List<BaseBusinessDomain> xtgwList;

	private String cjrMc;
	private String xgrMc;
	private String xybz;

	private String lybzStr;
	private String qybzStr;
	
	private String str;
	
	public QyGwDomain() {
	}
	
	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getQybzStr() {
		if("Y".equals(qybz)){
			qybzStr = "启用";
		}else{
			qybzStr = "停用";
		}
		return qybzStr;
	}

	public void setQybzStr(String qybzStr) {
		this.qybzStr = qybzStr;
	}

	public String getLybzStr() {
		if("Y".equals(lybz)){
			lybzStr = "系统";
		}else{
			lybzStr = "自建";
		}
		return lybzStr;
	}

	public void setLybzStr(String lybzStr) {
		this.lybzStr = lybzStr;
	}

	//获取岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//设置岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//获取岗位名称
	public String getGwMc() {
		return this.gwMc;
	}

	//设置岗位名称
	public void setGwMc(String gwMc) {
		this.gwMc=gwMc;
	}

	//获取岗位简称
	public String getGwJc() {
		return this.gwJc;
	}

	//设置岗位简称
	public void setGwJc(String gwJc) {
		this.gwJc=gwJc;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取来源标志(Y企业创建/N岗位代码)
	public String getLybz() {
		return this.lybz;
	}

	//设置来源标志(Y企业创建/N岗位代码)
	public void setLybz(String lybz) {
		this.lybz=lybz;
	}

	//获取岗位代码
	public String getGwDm() {
		return this.gwDm;
	}

	//设置岗位代码
	public void setGwDm(String gwDm) {
		this.gwDm=gwDm;
	}

	//获取所属机构编码(部门)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构编码(部门)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//获取创建日期
	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	//设置创建日期
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//获取修改人
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//获取修改日期
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
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

	public String getXybz() {
		return xybz;
	}

	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

	public List<BaseBusinessDomain> getXtgwList() {
		if(xtgwList == null){
			xtgwList = new ArrayList<BaseBusinessDomain>();
		}
		return xtgwList;
	}

	public void setXtgwList(List<BaseBusinessDomain> xtgwList) {
		this.xtgwList = xtgwList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getDwDjxh() {
		return dwDjxh;
	}

	public void setDwDjxh(String dwDjxh) {
		this.dwDjxh = dwDjxh;
	}

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}
}
