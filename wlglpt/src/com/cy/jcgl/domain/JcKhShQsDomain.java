package com.cy.jcgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

public class JcKhShQsDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String sjJgbm;
	private String rqq;
	private String rqz;
	private String jgMc;
	private String fhrDjxh;
	private String fhrMc;
	private String khMc;
	private String byZl;
	private String byTj;
	private String byXj;
	private String byXf;
	private String byDj;
	private String byHf;
	private String bnZl;
	private String bnTj;
	private String bnXj;
	private String bnXf;
	private String bnDj;
	private String bnHf;
	
	private String ssjgMc;				//所属机构名称
	private String yj;					//收入月结	
	private String fyj;					//收入非月结
	private String srdd;				//收入代垫
	private String xxf;					//收入信息费
	private String wlsssr;				//物流损失收入
	private String th;					//支出提货费
	private String ys;					//支出运输费
	private String ps;					//支出配送费
	private String zcdd;				//支出代垫
	private String wlsszc;				//物流损失支出
	private String lr;					//利润
	private String lrR;					//利润率
	private String xydjbz;				//协议登记标志
	
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getSjJgbm() {
		return sjJgbm;
	}
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm = sjJgbm;
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
	public String getJgMc() {
		return jgMc;
	}
	public void setJgMc(String jgMc) {
		this.jgMc = jgMc;
	}
	private String month;
	private String zl;
	private String tj;
	private String xj;
	private String xf;
	private  String df;
	private String hf;
	private List<BaseBusinessDomain> dataList;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getXj() {
		return xj;
	}
	public void setXj(String xj) {
		this.xj = xj;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public String getHf() {
		return hf;
	}
	public void setHf(String hf) {
		this.hf = hf;
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
	public String getKhMc() {
		return khMc;
	}
	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}
	public String getByZl() {
		return byZl;
	}
	public void setByZl(String byZl) {
		this.byZl = byZl;
	}
	public String getByTj() {
		return byTj;
	}
	public void setByTj(String byTj) {
		this.byTj = byTj;
	}
	public String getByXj() {
		return byXj;
	}
	public void setByXj(String byXj) {
		this.byXj = byXj;
	}
	public String getByXf() {
		return byXf;
	}
	public void setByXf(String byXf) {
		this.byXf = byXf;
	}
	public String getByDj() {
		return byDj;
	}
	public void setByDj(String byDj) {
		this.byDj = byDj;
	}
	public String getByHf() {
		return byHf;
	}
	public void setByHf(String byHf) {
		this.byHf = byHf;
	}
	public String getBnZl() {
		return bnZl;
	}
	public void setBnZl(String bnZl) {
		this.bnZl = bnZl;
	}
	public String getBnTj() {
		return bnTj;
	}
	public void setBnTj(String bnTj) {
		this.bnTj = bnTj;
	}
	public String getBnXj() {
		return bnXj;
	}
	public void setBnXj(String bnXj) {
		this.bnXj = bnXj;
	}
	public String getBnXf() {
		return bnXf;
	}
	public void setBnXf(String bnXf) {
		this.bnXf = bnXf;
	}
	public String getBnDj() {
		return bnDj;
	}
	public void setBnDj(String bnDj) {
		this.bnDj = bnDj;
	}
	public String getBnHf() {
		return bnHf;
	}
	public void setBnHf(String bnHf) {
		this.bnHf = bnHf;
	}
	public String getSsjgMc() {
		return ssjgMc;
	}
	public void setSsjgMc(String ssjgMc) {
		this.ssjgMc = ssjgMc;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getFyj() {
		return fyj;
	}
	public void setFyj(String fyj) {
		this.fyj = fyj;
	}
	public String getSrdd() {
		return srdd;
	}
	public void setSrdd(String srdd) {
		this.srdd = srdd;
	}
	public String getXxf() {
		return xxf;
	}
	public void setXxf(String xxf) {
		this.xxf = xxf;
	}
	public String getWlsssr() {
		return wlsssr;
	}
	public void setWlsssr(String wlsssr) {
		this.wlsssr = wlsssr;
	}
	public String getTh() {
		return th;
	}
	public void setTh(String th) {
		this.th = th;
	}
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getZcdd() {
		return zcdd;
	}
	public void setZcdd(String zcdd) {
		this.zcdd = zcdd;
	}
	public String getWlsszc() {
		return wlsszc;
	}
	public void setWlsszc(String wlsszc) {
		this.wlsszc = wlsszc;
	}
	public String getLr() {
		return lr;
	}
	public void setLr(String lr) {
		this.lr = lr;
	}
	public String getLrR() {
		return lrR;
	}
	public void setLrR(String lrR) {
		this.lrR = lrR;
	}
	public String getXydjbz() {
		return xydjbz;
	}
	public void setXydjbz(String xydjbz) {
		this.xydjbz = xydjbz;
	}
}
