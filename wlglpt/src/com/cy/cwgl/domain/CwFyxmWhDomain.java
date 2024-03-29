package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_FYXM_WH is created by tools.
 * @author HJH
 */

public class CwFyxmWhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构(总公司)
	private String fylbCwDjxh;                       // 费用类别_财务登记序号
	private String fyxmMc;                           // 费用项目名称
	private String splcXmflDjxh;                     // 审批流程_项目分类登记序号
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private String jgMc;
	private String lcMc;
	private String lbMc;
	private String xmFl;
	public String getXmFl() {
		return xmFl;
	}

	public void setXmFl(String xmFl) {
		this.xmFl = xmFl;
	}

	public String getJgMc() {
		return jgMc;
	}

	public void setJgMc(String jgMc) {
		this.jgMc = jgMc;
	}

	public String getLcMc() {
		return lcMc;
	}

	public void setLcMc(String lcMc) {
		this.lcMc = lcMc;
	}

	public String getLbMc() {
		return lbMc;
	}

	public void setLbMc(String lbMc) {
		this.lbMc = lbMc;
	}

	public CwFyxmWhDomain() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取所属机构(总公司)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构(总公司)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取费用类别_财务登记序号
	public String getFylbCwDjxh() {
		return this.fylbCwDjxh;
	}

	//设置费用类别_财务登记序号
	public void setFylbCwDjxh(String fylbCwDjxh) {
		this.fylbCwDjxh=fylbCwDjxh;
	}

	//获取费用项目名称
	public String getFyxmMc() {
		return this.fyxmMc;
	}

	//设置费用项目名称
	public void setFyxmMc(String fyxmMc) {
		this.fyxmMc=fyxmMc;
	}

	//获取审批流程_项目分类登记序号
	public String getSplcXmflDjxh() {
		return this.splcXmflDjxh;
	}

	//设置审批流程_项目分类登记序号
	public void setSplcXmflDjxh(String splcXmflDjxh) {
		this.splcXmflDjxh=splcXmflDjxh;
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

	public String getCjrMc() {
		return this.cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return this.xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
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
}
