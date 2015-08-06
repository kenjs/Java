package com.cy.xtgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR QY_RY_GW
 * @author HAOY
 */

public class QyRyGwDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;			//操作员登记序号
	private String oldGwDjxh;		//岗位登记序号
	private String gwDjxh;
	private String ssJgbm;			//所属机构编码(部门)
	private String zjbz;			//主兼标志(Y主要部门/N兼职部门)
	private String qxJgbm;			//权限机构编码(数据权限)
	private String mc;				//名称
	private String gwMc;			//岗位名称
	private String qxjgMc;			//权限机构名称
	private String ssjgMc ;			//所属机构名称
	private String gsbm;            // 所属公司
	private String sjJgbm;
	private String sjMc;
	
	private String gsmc;
	private String bmmc;
	private String gwmc;
	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getSjJgbm() {
		return sjJgbm;
	}

	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm = sjJgbm;
	}

	private String zjbzStr;
	
	private List<BaseBusinessDomain> dataList;    //查询列表

	public List<BaseBusinessDomain> getDataList() {
		if(dataList == null) {
			dataList = new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getCzyDjxh() {
		return czyDjxh;
	}

	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}

	
	public String getGwDjxh() {
		return gwDjxh;
	}

	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh = gwDjxh;
	}

	public String getOldGwDjxh() {
		return oldGwDjxh;
	}

	public void setOldGwDjxh(String oldGwDjxh) {
		this.oldGwDjxh = oldGwDjxh;
	}

	public String getGwMc() {
		return gwMc;
	}

	public void setGwMc(String gwMc) {
		this.gwMc = gwMc;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getQxJgbm() {
		return qxJgbm;
	}

	public void setQxJgbm(String qxJgbm) {
		this.qxJgbm = qxJgbm;
	}

	public String getQxjgMc() {
		return qxjgMc;
	}

	public void setQxjgMc(String qxjgMc) {
		this.qxjgMc = qxjgMc;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getSsjgMc() {
		return ssjgMc;
	}

	public void setSsjgMc(String ssjgMc) {
		this.ssjgMc = ssjgMc;
	}

	public String getZjbz() {
		return zjbz;
	}

	public void setZjbz(String zjbz) {
		this.zjbz = zjbz;
	}

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public String getZjbzStr() {
		if(null != zjbz) {
			if("Y".equals(zjbz)) {
				zjbzStr = "主部门";
			}else {
				zjbzStr = "兼职部门";
			}
		}
		return zjbzStr;
	}

	public void setZjbzStr(String zjbzStr) {
		this.zjbzStr = zjbzStr;
	}

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

}
