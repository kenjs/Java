package com.cy.dzgl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR QY_SPWS_SPLCSZ_ZB is created by tools.
 * @author HJH
 */

public class QySpwsSplcszZbDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // 审批流程设置序号
	private String jdxh;                             // 节点序号(从1开始递增)
	private String spjdsm;//审批节点说明
	private String spjgjbDm;//审批机构级别代码
	private String spjgjbMc;//审批机构级别名称
	private String spJgbm;                           // 审批机构编码(部门)
	private String spJgmc;
	private String gwDjxh;                           // 审批岗位登记序号
	private String gwMc;
	private String sphjsm;                           // 审批环节说明
	private String spyjl;                            // 审批意见栏
	private String spqm;                             // 审批签名
	private String yxzsBz;                           // 允许终审标志(Y/N)
	private String zstj;                             // 终审条件(可带参数设置,参数以#开头)
	private Double spsx;                             // 审批时限(天)
	private String gzrbz;                            // 工作日标志(1 工作日，2 自然日)
	private Double qzxs;                             // 权重系数
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String fsbz;//不同意允许继续发送(Y/N)
	private String dwDm;//单位
	private String dwMc;//单位名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	

	public QySpwsSplcszZbDomain() {
	}

	//获取审批流程设置序号
	public Long getSplcSzxh() {
		return this.splcSzxh;
	}

	//设置审批流程设置序号
	public void setSplcSzxh(Long splcSzxh) {
		this.splcSzxh=splcSzxh;
	}

	//获取节点序号(从1开始递增)
	public String getJdxh() {
		return this.jdxh;
	}

	//设置节点序号(从1开始递增)
	public void setJdxh(String jdxh) {
		this.jdxh=jdxh;
	}

	//获取审批机构编码(部门)
	public String getSpJgbm() {
		return this.spJgbm;
	}

	//设置审批机构编码(部门)
	public void setSpJgbm(String spJgbm) {
		this.spJgbm=spJgbm;
	}

	//获取审批岗位登记序号
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//设置审批岗位登记序号
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//获取审批环节说明
	public String getSphjsm() {
		return this.sphjsm;
	}

	//设置审批环节说明
	public void setSphjsm(String sphjsm) {
		this.sphjsm=sphjsm;
	}

	//获取审批意见栏
	public String getSpyjl() {
		return this.spyjl;
	}

	//设置审批意见栏
	public void setSpyjl(String spyjl) {
		this.spyjl=spyjl;
	}

	//获取审批签名
	public String getSpqm() {
		return this.spqm;
	}

	//设置审批签名
	public void setSpqm(String spqm) {
		this.spqm=spqm;
	}

	//获取允许终审标志(Y/N)
	public String getYxzsBz() {
		return this.yxzsBz;
	}

	//设置允许终审标志(Y/N)
	public void setYxzsBz(String yxzsBz) {
		this.yxzsBz=yxzsBz;
	}

	//获取终审条件(可带参数设置,参数以#开头)
	public String getZstj() {
		return this.zstj;
	}

	//设置终审条件(可带参数设置,参数以#开头)
	public void setZstj(String zstj) {
		this.zstj=zstj;
	}

	//获取审批时限(天)
	public Double getSpsx() {
		return this.spsx;
	}

	//设置审批时限(天)
	public void setSpsx(Double spsx) {
		this.spsx=spsx;
	}

	//获取工作日标志(1 工作日，2 自然日)
	public String getGzrbz() {
		return this.gzrbz;
	}

	//设置工作日标志(1 工作日，2 自然日)
	public void setGzrbz(String gzrbz) {
		this.gzrbz=gzrbz;
	}

	//获取权重系数
	public Double getQzxs() {
		return this.qzxs;
	}

	//设置权重系数
	public void setQzxs(Double qzxs) {
		this.qzxs=qzxs;
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
	public Date getCjrq() {
		return this.cjrq;
	}

	//设置创建日期
	public void setCjrq(Date cjrq) {
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

	public String getSpJgmc() {
		return spJgmc;
	}

	public void setSpJgmc(String spJgmc) {
		this.spJgmc = spJgmc;
	}

	public String getGwMc() {
		return gwMc;
	}

	public void setGwMc(String gwMc) {
		this.gwMc = gwMc;
	}

	//获取修改日期
	public Date getXgrq() {
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(Date xgrq) {
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

	public String getSpjdsm() {
		return spjdsm;
	}

	public void setSpjdsm(String spjdsm) {
		this.spjdsm = spjdsm;
	}

	public String getSpjgjbDm() {
		return spjgjbDm;
	}

	public void setSpjgjbDm(String spjgjbDm) {
		this.spjgjbDm = spjgjbDm;
	}

	public String getSpjgjbMc() {
		return spjgjbMc;
	}

	public void setSpjgjbMc(String spjgjbMc) {
		this.spjgjbMc = spjgjbMc;
	}

	public String getFsbz() {
		return fsbz;
	}

	public void setFsbz(String fsbz) {
		this.fsbz = fsbz;
	}

	public String getDwDm() {
		return dwDm;
	}

	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}
}
