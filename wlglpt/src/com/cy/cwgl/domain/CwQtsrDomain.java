package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_QTSR is created by tools.
 * @author HJH
 */

public class CwQtsrDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private String rq;                               // 日期
	private String xmmc;                             // 项目名称
	private Double je;                               // 金额
	private String zcflDm;                           // 资产分类代码
	private String yhCshDjxh;                        // 银行初始化登记序号
	private String bz;                               // 备注
	private String djJgbm;
	private String fkf;
	private String yxbz;                              
	private List<String> cwDjxhs;
	private List<String> xmmcs;
	private List<Double> jes;
	private List<String> zcflDms;
	private List<String> yhCshDjxhs;
	private List<String> bzs;
	private List<String> fkfs;
	
	
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwQtsrDomain() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取日期
	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}

	//设置日期
	public void setRq(String rq) {
		this.rq=rq;
	}

	//获取项目名称
	public String getXmmc() {
		return this.xmmc;
	}

	//设置项目名称
	public void setXmmc(String xmmc) {
		this.xmmc=xmmc;
	}

	//获取金额
	public Double getJe() {
		return this.je;
	}

	//设置金额
	public void setJe(Double je) {
		this.je=je;
	}

	//获取资产分类代码
	public String getZcflDm() {
		return this.zcflDm;
	}

	//设置资产分类代码
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//获取银行初始化登记序号
	public String getYhCshDjxh() {
		return this.yhCshDjxh;
	}

	//设置银行初始化登记序号
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh=yhCshDjxh;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
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

	public List<String> getCwDjxhs() {
		return cwDjxhs;
	}

	public void setCwDjxhs(List<String> cwDjxhs) {
		this.cwDjxhs = cwDjxhs;
	}

	public List<String> getXmmcs() {
		return xmmcs;
	}

	public void setXmmcs(List<String> xmmcs) {
		this.xmmcs = xmmcs;
	}

	public List<Double> getJes() {
		return jes;
	}

	public void setJes(List<Double> jes) {
		this.jes = jes;
	}

	public List<String> getZcflDms() {
		return zcflDms;
	}

	public void setZcflDms(List<String> zcflDms) {
		this.zcflDms = zcflDms;
	}

	public List<String> getYhCshDjxhs() {
		return yhCshDjxhs;
	}

	public void setYhCshDjxhs(List<String> yhCshDjxhs) {
		this.yhCshDjxhs = yhCshDjxhs;
	}

	public List<String> getBzs() {
		return bzs;
	}

	public void setBzs(List<String> bzs) {
		this.bzs = bzs;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getFkf() {
		return fkf;
	}

	public void setFkf(String fkf) {
		this.fkf = fkf;
	}

	public List<String> getFkfs() {
		return fkfs;
	}

	public void setFkfs(List<String> fkfs) {
		this.fkfs = fkfs;
	}
}
