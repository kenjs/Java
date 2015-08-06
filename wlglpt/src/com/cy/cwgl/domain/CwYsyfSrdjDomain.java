package com.cy.cwgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE DOMAIN FOR 财务收入登记
 * @author HCM
 *
 */
public class CwYsyfSrdjDomain extends BaseBusinessDomain{
	private static final long serialVersionUID = 1L;

	private int pageXh;
	private String ysyfDjxh;
	private String yfjsfDm;
	private String yfjsfMc;
	private String yfjsfDjxh;
	private String yfjsfDjmc;
	private String kmdlDm;
	private String kmdlMc;
	private String kmxlDm;
	private String kmxlMc;
	private String ysyflyDm;
	private String ysyflyMc;
	private String ywDjxh;
	private String csrq;
	private String ysyfztDm;
	private String ysyfztMc;
	private Double ysfJe;  //应收
	private Double yisfJe; //已收
	private Double wsfJe;
	private String sm;
	private String djJgbm;
	private String djJgmc;
	private String ssJgbm;
	private String ssJgmc;
	
	private String zdyKmzlDm ;		//自定义科目子类代码
	private String yxbz ;			//有效标志(Y/N)
	private String zt ;				//支付
	private String zfNum ;			//支付NUM

	private String zgsbm;
	private String syGsbm;
	private String syDdDjxh;
	/** 收入登记  **/
	private String srDjxh;
	private String fkfmc;
	private Double je;
	private String rq;
	private String zffsDm;
	private String zcflDm;
	private String yhCshDjxh;
	private String yhzh;
	private String yhhdh;
	private String jbrCzyDjxh;
	private String jbrCzyDjmc;
	private String bz;
	private String djrCzyDjxh;
	private String djrCzyDjmc;
	private String djrq;

	private String zffsMc ;				//支付方式名称
	private String zcflMc ;				//资产分类名称
	private String yhmc;
	private String ddbh;
	
	private List<String> srDjxhs;
	private List<String> ysyfDjxhs;
	private String rqQ;
	private String rqZ;
	
	private List<BaseBusinessDomain> dataList; 		//查询列表
	private List<CwYsyfSrdjDomain> yfjsfDmList; 		//查询结算方类别列表
	private List<CwYsyfSrdjDomain> yfjsfMcList; 		//查询结算方名称列表
	
	private String lbStr;
	private String mcStr;
	private Double wsJe;
	public List<String> getSrDjxhs() {
		return srDjxhs;
	}
	public void setSrDjxhs(List<String> srDjxhs) {
		this.srDjxhs = srDjxhs;
	}
	public int getPageXh() {
		return pageXh;
	}
	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}
	public String getYsyfDjxh() {
		return ysyfDjxh;
	}
	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}
	public String getYfjsfDm() {
		return yfjsfDm;
	}
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm = yfjsfDm;
	}
	public String getYfjsfMc() {
		return yfjsfMc;
	}
	public void setYfjsfMc(String yfjsfMc) {
		this.yfjsfMc = yfjsfMc;
	}
	public String getYfjsfDjxh() {
		return yfjsfDjxh;
	}
	public void setYfjsfDjxh(String yfjsfDjxh) {
		this.yfjsfDjxh = yfjsfDjxh;
	}
	public String getYfjsfDjmc() {
		return yfjsfDjmc;
	}
	public void setYfjsfDjmc(String yfjsfDjmc) {
		this.yfjsfDjmc = yfjsfDjmc;
	}
	public String getKmdlDm() {
		return kmdlDm;
	}
	public void setKmdlDm(String kmdlDm) {
		this.kmdlDm = kmdlDm;
	}
	public String getKmdlMc() {
		return kmdlMc;
	}
	public void setKmdlMc(String kmdlMc) {
		this.kmdlMc = kmdlMc;
	}
	public String getKmxlDm() {
		return kmxlDm;
	}
	public void setKmxlDm(String kmxlDm) {
		this.kmxlDm = kmxlDm;
	}
	public String getKmxlMc() {
		return kmxlMc;
	}
	public void setKmxlMc(String kmxlMc) {
		this.kmxlMc = kmxlMc;
	}
	public String getYsyflyDm() {
		return ysyflyDm;
	}
	public void setYsyflyDm(String ysyflyDm) {
		this.ysyflyDm = ysyflyDm;
	}
	public String getYsyflyMc() {
		return ysyflyMc;
	}
	public void setYsyflyMc(String ysyflyMc) {
		this.ysyflyMc = ysyflyMc;
	}
	public String getYwDjxh() {
		return ywDjxh;
	}
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getYsyfztDm() {
		return ysyfztDm;
	}
	public void setYsyfztDm(String ysyfztDm) {
		this.ysyfztDm = ysyfztDm;
	}
	public String getYsyfztMc() {
		return ysyfztMc;
	}
	public void setYsyfztMc(String ysyfztMc) {
		this.ysyfztMc = ysyfztMc;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getDjJgmc() {
		return djJgmc;
	}
	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}
	public String getZdyKmzlDm() {
		return zdyKmzlDm;
	}
	public void setZdyKmzlDm(String zdyKmzlDm) {
		this.zdyKmzlDm = zdyKmzlDm;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getSrDjxh() {
		return srDjxh;
	}
	public void setSrDjxh(String srDjxh) {
		this.srDjxh = srDjxh;
	}
	public String getFkfmc() {
		return fkfmc;
	}
	public void setFkfmc(String fkfmc) {
		this.fkfmc = fkfmc;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getZffsDm() {
		return zffsDm;
	}
	public void setZffsDm(String zffsDm) {
		this.zffsDm = zffsDm;
	}
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
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
	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getJbrCzyDjmc() {
		return jbrCzyDjmc;
	}
	public void setJbrCzyDjmc(String jbrCzyDjmc) {
		this.jbrCzyDjmc = jbrCzyDjmc;
	}
	public String getDjrCzyDjmc() {
		return djrCzyDjmc;
	}
	public void setDjrCzyDjmc(String djrCzyDjmc) {
		this.djrCzyDjmc = djrCzyDjmc;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getRqQ() {
		return rqQ;
	}
	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}
	public String getRqZ() {
		return rqZ;
	}
	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
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
	public List<CwYsyfSrdjDomain> getYfjsfDmList() {
		if(yfjsfDmList==null){
			yfjsfDmList=new ArrayList<CwYsyfSrdjDomain>();
		}
		return yfjsfDmList;
	}
	public void setYfjsfDmList(List<CwYsyfSrdjDomain> yfjsfDmList) {
		this.yfjsfDmList = yfjsfDmList;
	}
	public List<CwYsyfSrdjDomain> getYfjsfMcList() {
		if(yfjsfMcList==null){
			yfjsfMcList=new ArrayList<CwYsyfSrdjDomain>();
		}
		return yfjsfMcList;
	}
	public void setYfjsfMcList(List<CwYsyfSrdjDomain> yfjsfMcList) {
		this.yfjsfMcList = yfjsfMcList;
	}
	public String getLbStr() {
		return lbStr;
	}
	public void setLbStr(String lbStr) {
		this.lbStr = lbStr;
	}
	public String getMcStr() {
		return mcStr;
	}
	public void setMcStr(String mcStr) {
		this.mcStr = mcStr;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
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
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getZfNum() {
		return zfNum;
	}
	public void setZfNum(String zfNum) {
		this.zfNum = zfNum;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Double getYsfJe() {
		return ysfJe;
	}
	public void setYsfJe(Double ysfJe) {
		this.ysfJe = ysfJe;
	}
	public Double getYisfJe() {
		return yisfJe;
	}
	public void setYisfJe(Double yisfJe) {
		this.yisfJe = yisfJe;
	}
	public Double getWsfJe() {
		return wsfJe;
	}
	public void setWsfJe(Double wsfJe) {
		this.wsfJe = wsfJe;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public List<String> getYsyfDjxhs() {
		return ysyfDjxhs;
	}
	public void setYsyfDjxhs(List<String> ysyfDjxhs) {
		this.ysyfDjxhs = ysyfDjxhs;
	}
	public String getZgsbm() {
		return zgsbm;
	}
	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}
	public String getSyGsbm() {
		return syGsbm;
	}
	public void setSyGsbm(String syGsbm) {
		this.syGsbm = syGsbm;
	}
	public String getSyDdDjxh() {
		return syDdDjxh;
	}
	public void setSyDdDjxh(String syDdDjxh) {
		this.syDdDjxh = syDdDjxh;
	}
	public Double getWsJe() {
		return wsJe;
	}
	public void setWsJe(Double wsJe) {
		this.wsJe = wsJe;
	}
	
}
