package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_KHYSGL_MX is created by tools.
 * @author HJH
 */

public class CwKhysglMxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String srDjxh;                           // 收入登记序号(SEQ_SR_DJXH)
	private String mxXh;                             // 应收应付登记序号
	private String khmc;                             // 运费结算方登记序号
	private String je;                               // 金额
	private String zffsDm;                           // 支付方式代码
	private String zcflDm;                           // 资产分类代码
	private String yhCshDjxh;                        // 银行初始化登记序号
	private String yhhdh;                            // 银行回单号
	private String jbrCzyDjxh;                       // 经办人
	private String jbrq;                             // 
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)
	private String czxfBz;
	private String zffsMc;
	private String zcflMc;
	private String jbrCzyDjmc;
	private String yhzh;
	private String yhmc;
	private String ysyfDjxh;
	public String getYsyfDjxh() {
		return ysyfDjxh;
	}

	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}

	public String getZffsMc() {
		return zffsMc;
	}

	public void setZffsMc(String zffsMc) {
		this.zffsMc = zffsMc;
	}

	public String getZcflMc() {
		return zcflMc;
	}

	public void setZcflMc(String zcflMc) {
		this.zcflMc = zcflMc;
	}

	public String getJbrCzyDjmc() {
		return jbrCzyDjmc;
	}

	public void setJbrCzyDjmc(String jbrCzyDjmc) {
		this.jbrCzyDjmc = jbrCzyDjmc;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getCzxfBz() {
		return czxfBz;
	}

	public void setCzxfBz(String czxfBz) {
		this.czxfBz = czxfBz;
	}

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwKhysglMxDomain() {
	}

	//获取收入登记序号(SEQ_SR_DJXH)
	public String getSrDjxh() {
		return this.srDjxh;
	}

	//设置收入登记序号(SEQ_SR_DJXH)
	public void setSrDjxh(String srDjxh) {
		this.srDjxh=srDjxh;
	}

	//获取应收应付登记序号
	public String getMxXh() {
		return this.mxXh;
	}

	//设置应收应付登记序号
	public void setMxXh(String mxXh) {
		this.mxXh=mxXh;
	}

	//获取运费结算方登记序号
	public String getKhmc() {
		return this.khmc;
	}

	//设置运费结算方登记序号
	public void setKhmc(String khmc) {
		this.khmc=khmc;
	}

	//获取金额
	

	//获取支付方式代码
	public String getZffsDm() {
		return this.zffsDm;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	//设置支付方式代码
	public void setZffsDm(String zffsDm) {
		this.zffsDm=zffsDm;
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

	//获取银行回单号
	public String getYhhdh() {
		return this.yhhdh;
	}

	//设置银行回单号
	public void setYhhdh(String yhhdh) {
		this.yhhdh=yhhdh;
	}

	//获取经办人
	public String getJbrCzyDjxh() {
		return this.jbrCzyDjxh;
	}

	//设置经办人
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh=jbrCzyDjxh;
	}

	//获取
	public String getJbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.jbrq);
		}
		catch(Exception e){
			return this.jbrq;
		}
	}

	//设置
	public void setJbrq(String jbrq) {
		this.jbrq=jbrq;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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
