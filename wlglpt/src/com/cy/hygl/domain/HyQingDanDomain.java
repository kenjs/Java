package com.cy.hygl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

public class HyQingDanDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String hwMc;
	private String sl;
	private String zl;
	private String tj;
	private String shrMc;
	private String shrDz;
	private String shfsMc;
	private String fkfsMc;
	private String ds;
	private String xf;
	private String yj;
	private String hd;
	private String bz;
	private String sfd;
	private String mdd;
	private String pcdh;
	private String pcrq;
	private String sjxm;
	private String gsMc;
	private String fhr;
	private String ddbh;
	private String pcBz;
	private String mddDz;
	private String dsHk;
	private String clhm;
	private String lxdh;
	private String shrmc;
	private String shrLxdh;
	private String hwhh;
	private String srPsf;
	
	
	public String getSrPsf() {
		return srPsf;
	}
	public void setSrPsf(String srPsf) {
		this.srPsf = srPsf;
	}
	public String getDsHk() {
		return dsHk;
	}
	public void setDsHk(String dsHk) {
		this.dsHk = dsHk;
	}
	public String getMddDz() {
		return mddDz;
	}
	public void setMddDz(String mddDz) {
		this.mddDz = mddDz;
	}
	public String getPcBz() {
		return pcBz;
	}
	public void setPcBz(String pcBz) {
		this.pcBz = pcBz;
	}
	public String getFhr() {
		return fhr;
	}
	public void setFhr(String fhr) {
		this.fhr = fhr;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getGsMc() {
		return gsMc;
	}
	public void setGsMc(String gsMc) {
		this.gsMc = gsMc;
	}
	private List<HyQingDanDomain> dataList;
	public List<HyQingDanDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<HyQingDanDomain>();
		}
		return dataList;
	}
	public void setDataList(List<HyQingDanDomain> dataList) {
		this.dataList = dataList;
	}
	public String getHwMc() {
		return hwMc;
	}
	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
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

	public String getShrMc() {
		return shrMc;
	}
	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}
	public String getShrDz() {
		return shrDz;
	}
	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}
	public String getShfsMc() {
		return shfsMc;
	}
	public void setShfsMc(String shfsMc) {
		this.shfsMc = shfsMc;
	}
	public String getFkfsMc() {
		return fkfsMc;
	}
	public void setFkfsMc(String fkfsMc) {
		this.fkfsMc = fkfsMc;
	}
	public String getDs() {
		return ds;
	}
	public void setDs(String ds) {
		this.ds = ds;
	}
	public String getHd() {
		return hd;
	}
	public void setHd(String hd) {
		this.hd = hd;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSfd() {
		return sfd;
	}
	public void setSfd(String sfd) {
		this.sfd = sfd;
	}
	public String getMdd() {
		return mdd;
	}
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}
	public String getPcdh() {
		return pcdh;
	}
	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}
	public String getPcrq() {
		return pcrq;
	}
	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}
	public String getSjxm() {
		return sjxm;
	}
	public void setSjxm(String sjxm) {
		this.sjxm = sjxm;
	}
	public String getClhm() {
		return clhm;
	}
	public void setClhm(String clhm) {
		this.clhm = clhm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getShrmc() {
		return shrmc;
	}
	public void setShrmc(String shrmc) {
		this.shrmc = shrmc;
	}
	public String getShrLxdh() {
		return shrLxdh;
	}
	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}
	public String getHwhh() {
		return hwhh;
	}
	public void setHwhh(String hwhh) {
		this.hwhh = hwhh;
	}
	
}
