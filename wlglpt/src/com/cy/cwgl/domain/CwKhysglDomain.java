package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_KHYSGL is created by tools.
 * @author HJH
 */

public class CwKhysglDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String srDjxh;                           // 收入登记序号(SEQ_SR_DJXH)
	private String khDjxh;                           // 应收应付登记序号
	private String je;                               // 运费结算方代码
	private String yxbz;                             // 运费结算方登记序号
	private String djrDjxh;                          // 付款方名称
	private String djrq;                             // 
	private String djJgbm;                           // 日期
	private String ssSsjg;                           // 
	private String xgrDjxh;                          // 
	private String xgrq;                             // 

	private String rqq;
	private String rqz;
	private String djBm;
	private String ssJgmc;
	private String khMc;
	private String djrMc;
	private String xgrMc;
	private String ssJgbm;
	private String czJe;
	private String zffs;
	private String zcfl;
	private String jbrCzyDjxh;
	private String jbRq;
	private String yhCshDjxh;
	private String yhhdh;
	private String bz;
	private List<CwKhysglMxDomain> mxList;
	private String jsonStr;
	private String xhs;
	private String tager;
	private String fhrMc;
	private String ssJgbm4Query;
	public String getSsJgbm4Query() {
		return ssJgbm4Query;
	}

	public void setSsJgbm4Query(String ssJgbm4Query) {
		this.ssJgbm4Query = ssJgbm4Query;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getXhs() {
		return xhs;
	}

	public void setXhs(String xhs) {
		this.xhs = xhs;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public List<CwKhysglMxDomain> getMxList() {
		if(mxList==null){
			mxList=new ArrayList<CwKhysglMxDomain>();
		}
		return mxList;
	}

	public void setMxList(List<CwKhysglMxDomain> mxList) {
		this.mxList = mxList;
	}

	public String getCzJe() {
		return czJe;
	}

	public void setCzJe(String czJe) {
		this.czJe = czJe;
	}

	public String getZffs() {
		return zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public String getZcfl() {
		return zcfl;
	}

	public void setZcfl(String zcfl) {
		this.zcfl = zcfl;
	}

	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}

	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}

	public String getJbRq() {
		return jbRq;
	}

	public void setJbRq(String jbRq) {
		this.jbRq = jbRq;
	}

	public String getYhCshDjxh() {
		return yhCshDjxh;
	}

	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}

	public String getYhhdh() {
		return yhhdh;
	}

	public void setYhhdh(String yhhdh) {
		this.yhhdh = yhhdh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getDjrMc() {
		return djrMc;
	}

	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getDjBm() {
		return djBm;
	}

	public void setDjBm(String djBm) {
		this.djBm = djBm;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getRqq() {
		return rqq;
	}

	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	public String getRqz() {
		return rqz;
	}

	public void setRqz(String rqz) {
		this.rqz = rqz;
	}

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwKhysglDomain() {
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
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置应收应付登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取运费结算方代码


	//获取运费结算方登记序号
	public String getYxbz() {
		return this.yxbz;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	//设置运费结算方登记序号
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取付款方名称
	public String getDjrDjxh() {
		return this.djrDjxh;
	}

	//设置付款方名称
	public void setDjrDjxh(String djrDjxh) {
		this.djrDjxh=djrDjxh;
	}

	//获取
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//设置
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//获取日期
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置日期
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取
	public String getSsSsjg() {
		return this.ssSsjg;
	}

	//设置
	public void setSsSsjg(String ssSsjg) {
		this.ssSsjg=ssSsjg;
	}

	//获取
	public String getXgrDjxh() {
		return this.xgrDjxh;
	}

	//设置
	public void setXgrDjxh(String xgrDjxh) {
		this.xgrDjxh=xgrDjxh;
	}

	//获取
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//设置
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
}
