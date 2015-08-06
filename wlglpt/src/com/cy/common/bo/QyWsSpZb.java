package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_WS_SP_ZB is created by tools.
 * @author HJH
 */

public class QyWsSpZb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wsSpxh;                           // 文书审批序号(SEQ_WS_SPXH)
	private String spxh;                             // 审批序号(从1开始递增)
	private String fsthbz;                           // 发送退回标志(1 发送,2 退回)
	private String fsrCzyDjxh;                       // 发送人
	private String fsrq;                             // 发送日期
	private String jdxh;                             // 审批人节点序号
	private String spJgbm;                           // 审批机构编码(部门)
	private String gwDjxh;                           // 审批岗位登记序号
	private String spyjl;                            // 审批意见栏
	private String spqm;                             // 审批签名
	private String yxzsBz;                           // 允许终审标志(Y/N)
	private String zstj;                             // 终审条件(可带参数设置,参数以#开头)
	private Double qzxs;                             // 权重系数
	private String spjzsj;                           // 审批截止时间
	private String sprCzyDjxh;                       // 审批人
	private String sprq;                             // 审批日期
	private String spjg;                             // 审批结果(1 同意，2 不同意)
	private String spyj;                             // 审批意见
	private String cqbz;                             // 超期标志(Y/N)
	private String spbz;                             // 审批标志(Y 已审批/N 未审批)
	private String fsbz;                             // 不同意允许继续发送(Y/N)

	public QyWsSpZb() {
	}

	//获取文书审批序号(SEQ_WS_SPXH)
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//设置文书审批序号(SEQ_WS_SPXH)
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}

	//获取审批序号(从1开始递增)
	public String getSpxh() {
		return this.spxh;
	}

	//设置审批序号(从1开始递增)
	public void setSpxh(String spxh) {
		this.spxh=spxh;
	}

	//获取发送退回标志(1 发送,2 退回)
	public String getFsthbz() {
		return this.fsthbz;
	}

	//设置发送退回标志(1 发送,2 退回)
	public void setFsthbz(String fsthbz) {
		this.fsthbz=fsthbz;
	}

	//获取发送人
	public String getFsrCzyDjxh() {
		return this.fsrCzyDjxh;
	}

	//设置发送人
	public void setFsrCzyDjxh(String fsrCzyDjxh) {
		this.fsrCzyDjxh=fsrCzyDjxh;
	}

	//获取发送日期
	public String getFsrq() {
		return this.fsrq;
	}

	//设置发送日期
	public void setFsrq(String fsrq) {
		this.fsrq=fsrq;
	}

	//获取审批人节点序号
	public String getJdxh() {
		return this.jdxh;
	}

	//设置审批人节点序号
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

	//获取权重系数
	public Double getQzxs() {
		return this.qzxs;
	}

	//设置权重系数
	public void setQzxs(Double qzxs) {
		this.qzxs=qzxs;
	}

	//获取审批截止时间
	public String getSpjzsj() {
		return this.spjzsj;
	}

	//设置审批截止时间
	public void setSpjzsj(String spjzsj) {
		this.spjzsj=spjzsj;
	}

	//获取审批人
	public String getSprCzyDjxh() {
		return this.sprCzyDjxh;
	}

	//设置审批人
	public void setSprCzyDjxh(String sprCzyDjxh) {
		this.sprCzyDjxh=sprCzyDjxh;
	}

	//获取审批日期
	public String getSprq() {
		return this.sprq;
	}

	//设置审批日期
	public void setSprq(String sprq) {
		this.sprq=sprq;
	}

	//获取审批结果(1 同意，2 不同意)
	public String getSpjg() {
		return this.spjg;
	}

	//设置审批结果(1 同意，2 不同意)
	public void setSpjg(String spjg) {
		this.spjg=spjg;
	}

	//获取审批意见
	public String getSpyj() {
		return this.spyj;
	}

	//设置审批意见
	public void setSpyj(String spyj) {
		this.spyj=spyj;
	}

	//获取超期标志(Y/N)
	public String getCqbz() {
		return this.cqbz;
	}

	//设置超期标志(Y/N)
	public void setCqbz(String cqbz) {
		this.cqbz=cqbz;
	}

	//获取审批标志(Y 已审批/N 未审批)
	public String getSpbz() {
		return this.spbz;
	}

	//设置审批标志(Y 已审批/N 未审批)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//获取不同意允许继续发送(Y/N)
	public String getFsbz() {
		return this.fsbz;
	}

	//设置不同意允许继续发送(Y/N)
	public void setFsbz(String fsbz) {
		this.fsbz=fsbz;
	}
}