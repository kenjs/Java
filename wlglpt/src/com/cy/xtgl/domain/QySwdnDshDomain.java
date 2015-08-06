package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SWDN_DSH AND QY_SWDN
 * @author HaoY
 */

public class QySwdnDshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ggDjxh;                           // 公共登记序号(SEQ_GG_DJXH)
	private String qyZcxh;                           // 企业注册序号(GL_QYZC.QY_ZCXH)
	private String mac;                              // MAC地址
	private String bzsm;                             // 备注说明
	private String czyDjxh;                          // 操作员名称
	private String sqrCzyDjxh;                       // 申请人名称
	private String sqrq;                             // 申请日期
	private String shrCzyDjxh;                       // 审核人
	private String shrq;                             // 审核日期
	private String shjg;                             // 审核结果(1 同意 ,2 不同意)
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String sqrMc;                            // 申请人名称
	private String czyMc;                            // 操作人名称
	private String shrMc;                            // 审核人名称
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QySwdnDshDomain() {
	}

	//获取公共登记序号(SEQ_GG_DJXH)
	public String getGgDjxh() {
		return this.ggDjxh;
	}

	//设置公共登记序号(SEQ_GG_DJXH)
	public void setGgDjxh(String ggDjxh) {
		this.ggDjxh=ggDjxh;
	}

	//获取企业注册序号(GL_QYZC.QY_ZCXH)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//设置企业注册序号(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//获取MAC地址
	public String getMac() {
		return this.mac;
	}

	//设置MAC地址
	public void setMac(String mac) {
		this.mac=mac;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取操作员登记序号(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取申请人
	public String getSqrCzyDjxh() {
		return this.sqrCzyDjxh;
	}

	//设置申请人
	public void setSqrCzyDjxh(String sqrCzyDjxh) {
		this.sqrCzyDjxh=sqrCzyDjxh;
	}

	//获取申请日期
	public String getSqrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sqrq);
		}
		catch(Exception e){
			return this.sqrq;
		}
	}

	//设置申请日期
	public void setSqrq(String sqrq) {
		this.sqrq=sqrq;
	}

	//获取审核人
	public String getShrCzyDjxh() {
		return this.shrCzyDjxh;
	}

	//设置审核人
	public void setShrCzyDjxh(String shrCzyDjxh) {
		this.shrCzyDjxh=shrCzyDjxh;
	}

	//获取审核日期
	public String getShrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.shrq);
		}
		catch(Exception e){
			return this.shrq;
		}
	}

	//设置审核日期
	public void setShrq(String shrq) {
		this.shrq=shrq;
	}

	//获取审核结果(1 同意 ,2 不同意)
	public String getShjg() {
		return this.shjg;
	}

	//设置审核结果(1 同意 ,2 不同意)
	public void setShjg(String shjg) {
		this.shjg=shjg;
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

	public String getCzyMc() {
		return czyMc;
	}

	public void setCzyMc(String czyMc) {
		this.czyMc = czyMc;
	}

	public String getSqrMc() {
		return sqrMc;
	}

	public void setSqrMc(String sqrMc) {
		this.sqrMc = sqrMc;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getQybz() {
		return qybz;
	}

	public void setQybz(String qybz) {
		this.qybz = qybz;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}
