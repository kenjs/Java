package com.cy.hygl.domain;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_PC_HD is created by tools.
 * @author HJH
 */

public class HyPcHddjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdDjxh;
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double szHwSl;                           // 实装_货物_数量
	private Double szHwZl;                           // 实装_货物_重量
	private Double szHwTj;                           // 实装_货物_体积
	private Date yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private Double szJsSl;                           // 实装_结算数量
	private String hdbh;                             // 回单编号(多个已逗号分隔)
	private Date hdjsrq;                           // 回单接收日期
	private String bz;                               // 备注
	private String spbz;
	private String wsspztDm;
	private String wsSpxh;
	
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	private String jsJldwMc;
	private String wlssHwSl;
	private String wlssHwZl;
	private String wlssHwTj;
	private String zhwSl;
	private String zhwZl;
	private String zhwTj;
	private String wlssDjxh;
	private String hwmc;
	
	private String djrCzyDjxh;
	private String djrq;
	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getWlssDjxh() {
		return wlssDjxh;
	}

	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}

	public String getZhwZl() {
		return zhwZl;
	}

	public void setZhwZl(String zhwZl) {
		this.zhwZl = zhwZl;
	}

	public String getZhwTj() {
		return zhwTj;
	}

	public void setZhwTj(String zhwTj) {
		this.zhwTj = zhwTj;
	}
	public String getWlssHwZl() {
		return wlssHwZl;
	}

	public void setWlssHwZl(String wlssHwZl) {
		this.wlssHwZl = wlssHwZl;
	}

	public String getWlssHwTj() {
		return wlssHwTj;
	}

	public void setWlssHwTj(String wlssHwTj) {
		this.wlssHwTj = wlssHwTj;
	}

	private String pljsStr;
	
	private HyPcHwxxDomain pcHwDomain;
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	/*****查询条件********/
	private String ssJgbm;
	private String pcJgbm;
	private String pcrCzyDjxh;
	private String pcrqq;
	private String pcrqz;
	private String fhrMc;
	private String fhrDjxh;
	private String pcdh;
	private String zt;
	private int pageXh;
	private String sfXsFgs;
	public String getSfXsFgs() {
		return sfXsFgs;
	}

	public void setSfXsFgs(String sfXsFgs) {
		this.sfXsFgs = sfXsFgs;
	}

	/********************系统参数*********************/
	private String xtcsSfsp;						//回单登记变动是否需要审批
	private String xtcs20016;
	public HyPcHddjDomain() {
	}

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取未发货登记序号
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置未发货登记序号
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取回单编号
	public String getHdbh() {
		return this.hdbh;
	}

	//设置回单编号
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号(货物或计划单明细序号)
	public String getXh() {
		return this.xh;
	}

	//设置序号(货物或计划单明细序号)
	public void setXh(String xh) {
		this.xh=xh;
	}

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

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getPcJgbm() {
		return pcJgbm;
	}

	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm = pcJgbm;
	}

	public String getPcrCzyDjxh() {
		return pcrCzyDjxh;
	}

	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh = pcrCzyDjxh;
	}

	public String getPcrqq() {
		return pcrqq;
	}

	public void setPcrqq(String pcrqq) {
		this.pcrqq = pcrqq;
	}

	public String getPcrqz() {
		return pcrqz;
	}

	public void setPcrqz(String pcrqz) {
		this.pcrqz = pcrqz;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public Double getSzHwSl() {
		return szHwSl;
	}

	public void setSzHwSl(Double szHwSl) {
		this.szHwSl = szHwSl;
	}

	public Double getSzHwZl() {
		return szHwZl;
	}

	public void setSzHwZl(Double szHwZl) {
		this.szHwZl = szHwZl;
	}

	public Double getSzHwTj() {
		return szHwTj;
	}

	public void setSzHwTj(Double szHwTj) {
		this.szHwTj = szHwTj;
	}

	public Date getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public Double getSzJsSl() {
		return szJsSl;
	}

	public void setSzJsSl(Double szJsSl) {
		this.szJsSl = szJsSl;
	}

	public Date getHdjsrq() {
		return hdjsrq;
	}

	public void setHdjsrq(Date hdjsrq) {
		this.hdjsrq = hdjsrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getHwSlJldwMc() {
		return hwSlJldwMc;
	}

	public void setHwSlJldwMc(String hwSlJldwMc) {
		this.hwSlJldwMc = hwSlJldwMc;
	}

	public String getHwZlJldwMc() {
		return hwZlJldwMc;
	}

	public void setHwZlJldwMc(String hwZlJldwMc) {
		this.hwZlJldwMc = hwZlJldwMc;
	}

	public String getHwTjJldwMc() {
		return hwTjJldwMc;
	}

	public void setHwTjJldwMc(String hwTjJldwMc) {
		this.hwTjJldwMc = hwTjJldwMc;
	}

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public List<BaseBusinessDomain> getDataList() {
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public HyPcHwxxDomain getPcHwDomain() {
		if (pcHwDomain == null) {
			pcHwDomain = new HyPcHwxxDomain();
		}
		return pcHwDomain;
	}

	public void setPcHwDomain(HyPcHwxxDomain pcHwDomain) {
		this.pcHwDomain = pcHwDomain;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public int getPageXh() {
		return pageXh;
	}

	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getWsspztDm() {
		return wsspztDm;
	}

	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}

	public String getWsSpxh() {
		return wsSpxh;
	}

	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}

	public String getXtcsSfsp() {
		return xtcsSfsp;
	}

	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}

	public String getWlssHwSl() {
		return wlssHwSl;
	}

	public void setWlssHwSl(String wlssHwSl) {
		this.wlssHwSl = wlssHwSl;
	}

	public String getZhwSl() {
		return zhwSl;
	}

	public void setZhwSl(String zhwSl) {
		this.zhwSl = zhwSl;
	}

	public String getPljsStr() {
		return pljsStr;
	}

	public void setPljsStr(String pljsStr) {
		this.pljsStr = pljsStr;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public String getXtcs20016() {
		return xtcs20016;
	}

	public void setXtcs20016(String xtcs20016) {
		this.xtcs20016 = xtcs20016;
	}

}
