package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * @author HJH
 */

public class HyWlSsDjGlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pageXh;
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private String pcdh;                             // 派车单号
	private String wlssDjxh;
	private String spbz;
	private String wsSpxh;
	private String wsspztDm;
	private String wsspztMc;
	private String je;
	private String wlssyyDm;
	private String wlssyyMc;
	private String wlssclfsDm;
	private String wlssclfsMc;
	private String cjrDjxh;
	private String cjrMc;
	private String cjrq;
	private String xgrDjxh;
	private String xgrMc;
	private String xgrq;
	private String pcfsDm;
	private String pcfsMc;
	private String zcfsDm;
	private String zcfsMc;
	private String cyrClhm;
	private String cyrGchm;
	private String cyrSjxm;
	private Double yfHj;
	private Double yfYfYf;
	private String zrbmDm;
	private String zrbmMc;
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String hwMc;
	private String bz;
	private String sl;
	private String zl;
	private String tj;
	private String jsSl;
	private String fhrMc;
	private String khmc;
	private String ddbh;
	private String fhrDz;
	private String yqFhrq;
	private String shrMc;
	private String shrDz;
	private String yqDdrq;
	private String pcrQ;
	private String pcrDjxh;
	private String pcrMc;
	private String pcJgbm;
	private String pcJgmc;
	private String ssJgbm;
	private String ssJgmc;
	private String ddDjxh;
	private String hwmxxh;
	
	private String wlssSl;				   			//货物物流损失数量
	private String wlssZl;							//货物物流损失重量		
	private String wlssTj;							//货物物流损失体积	
	private String ssly;							//货物物流损失来源
	private String ssyy;							//货物物流损失原因
	private String ssZrr;							//货物物流损失责任人
	
	private String wfhDjxh;
	private List<BaseBusinessDomain> dataList;
	
	private String wlssLybz;
	private String zgsbm;
	/**
	 * 查询条件
	 */
	private String dwDm;
	private String pcbm4Query;
	private String pcrqQ;
	private String pcrqZ;
	private String fhrDjxh;
	private String pcdh4Query;
	private String clhm4Query;
	private String zt;
	
	/**********************系统参数*********************/
	private String xtcsSfsp;					//物流损失是否需要审批
	private String xtcs20016;//
	private String hdbh;//回单编号
	private String xybz;//协议标志
	
	private boolean sendBz;
	
	public String getClhm4Query() {
		return clhm4Query;
	}
	public void setClhm4Query(String clhm4Query) {
		this.clhm4Query = clhm4Query;
	}
	public String getPcdh4Query() {
		return pcdh4Query;
	}
	public void setPcdh4Query(String pcdh4Query) {
		this.pcdh4Query = pcdh4Query;
	}
	
	public String getDwDm() {
		return dwDm;
	}
	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}
	public String getPcbm4Query() {
		return pcbm4Query;
	}
	public void setPcbm4Query(String pcbm4Query) {
		this.pcbm4Query = pcbm4Query;
	}
	public String getPcrqQ() {
		return pcrqQ;
	}
	public void setPcrqQ(String pcrqQ) {
		this.pcrqQ = pcrqQ;
	}
	public String getPcrqZ() {
		return pcrqZ;
	}
	public void setPcrqZ(String pcrqZ) {
		this.pcrqZ = pcrqZ;
	}
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
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
	public String getPageXh() {
		return pageXh;
	}
	public void setPageXh(String pageXh) {
		this.pageXh = pageXh;
	}
	public String getPcDjxh() {
		return pcDjxh;
	}
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}
	public String getPcdh() {
		return pcdh;
	}
	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}
	public String getWlssDjxh() {
		return wlssDjxh;
	}
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}
	public String getSpbz() {
		return spbz;
	}
	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public String getWsSpxh() {
		return wsSpxh;
	}
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}
	public String getWsspztDm() {
		return wsspztDm;
	}
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}
	public String getWsspztMc() {
		return wsspztMc;
	}
	public void setWsspztMc(String wsspztMc) {
		this.wsspztMc = wsspztMc;
	}
	public String getWlssyyDm() {
		return wlssyyDm;
	}
	public void setWlssyyDm(String wlssyyDm) {
		this.wlssyyDm = wlssyyDm;
	}
	public String getWlssyyMc() {
		return wlssyyMc;
	}
	public void setWlssyyMc(String wlssyyMc) {
		this.wlssyyMc = wlssyyMc;
	}
	public String getWlssclfsDm() {
		return wlssclfsDm;
	}
	public void setWlssclfsDm(String wlssclfsDm) {
		this.wlssclfsDm = wlssclfsDm;
	}
	public String getWlssclfsMc() {
		return wlssclfsMc;
	}
	public void setWlssclfsMc(String wlssclfsMc) {
		this.wlssclfsMc = wlssclfsMc;
	}
	public String getCjrDjxh() {
		return cjrDjxh;
	}
	public void setCjrDjxh(String cjrDjxh) {
		this.cjrDjxh = cjrDjxh;
	}
	public String getCjrMc() {
		return cjrMc;
	}
	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getXgrDjxh() {
		return xgrDjxh;
	}
	public void setXgrDjxh(String xgrDjxh) {
		this.xgrDjxh = xgrDjxh;
	}
	public String getXgrMc() {
		return xgrMc;
	}
	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}
	public String getXgrq() {
		return xgrq;
	}
	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}
	public String getPcfsDm() {
		return pcfsDm;
	}
	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
	}
	public String getPcfsMc() {
		return pcfsMc;
	}
	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}
	public String getZcfsDm() {
		return zcfsDm;
	}
	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm = zcfsDm;
	}
	public String getZcfsMc() {
		return zcfsMc;
	}
	public void setZcfsMc(String zcfsMc) {
		this.zcfsMc = zcfsMc;
	}
	public String getCyrClhm() {
		return cyrClhm;
	}
	public void setCyrClhm(String cyrClhm) {
		this.cyrClhm = cyrClhm;
	}
	public String getCyrGchm() {
		return cyrGchm;
	}
	public void setCyrGchm(String cyrGchm) {
		this.cyrGchm = cyrGchm;
	}
	public String getCyrSjxm() {
		return cyrSjxm;
	}
	public void setCyrSjxm(String cyrSjxm) {
		this.cyrSjxm = cyrSjxm;
	}
	public Double getYfHj() {
		return yfHj;
	}
	public void setYfHj(Double yfHj) {
		this.yfHj = yfHj;
	}
	public Double getYfYfYf() {
		return yfYfYf;
	}
	public void setYfYfYf(Double yfYfYf) {
		this.yfYfYf = yfYfYf;
	}
	public String getZrbmDm() {
		return zrbmDm;
	}
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm = zrbmDm;
	}
	public String getZrbmMc() {
		return zrbmMc;
	}
	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}
	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}
	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}
	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}
	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}
	public String getShrXzqhDm() {
		return shrXzqhDm;
	}
	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}
	public String getShrXzqhMc() {
		return shrXzqhMc;
	}
	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}
	public String getHwMc() {
		return hwMc;
	}
	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public String getJsSl() {
		return jsSl;
	}
	public void setJsSl(String jsSl) {
		this.jsSl = jsSl;
	}
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getFhrDz() {
		return fhrDz;
	}
	public void setFhrDz(String fhrDz) {
		this.fhrDz = fhrDz;
	}
	public String getYqFhrq() {
		return yqFhrq;
	}
	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq = yqFhrq;
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
	public String getYqDdrq() {
		return yqDdrq;
	}
	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq = yqDdrq;
	}
	public String getPcrQ() {
		return pcrQ;
	}
	public void setPcrQ(String pcrQ) {
		this.pcrQ = pcrQ;
	}
	public String getPcrDjxh() {
		return pcrDjxh;
	}
	public void setPcrDjxh(String pcrDjxh) {
		this.pcrDjxh = pcrDjxh;
	}
	public String getPcrMc() {
		return pcrMc;
	}
	public void setPcrMc(String pcrMc) {
		this.pcrMc = pcrMc;
	}
	public String getPcJgbm() {
		return pcJgbm;
	}
	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm = pcJgbm;
	}
	public String getPcJgmc() {
		return pcJgmc;
	}
	public void setPcJgmc(String pcJgmc) {
		this.pcJgmc = pcJgmc;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getXtcsSfsp() {
		return xtcsSfsp;
	}
	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}
	public boolean getSendBz() {
		return sendBz;
	}
	public void setSendBz(boolean sendBz) {
		this.sendBz = sendBz;
	}
	public String getDdDjxh() {
		return ddDjxh;
	}
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}
	public String getWfhDjxh() {
		return wfhDjxh;
	}
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}
	public String getWlssLybz() {
		return wlssLybz;
	}
	public void setWlssLybz(String wlssLybz) {
		this.wlssLybz = wlssLybz;
	}
	public String getHwmxxh() {
		return hwmxxh;
	}
	public void setHwmxxh(String hwmxxh) {
		this.hwmxxh = hwmxxh;
	}
	public String getZgsbm() {
		return zgsbm;
	}
	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}
	public String getWlssSl() {
		return wlssSl;
	}
	public void setWlssSl(String wlssSl) {
		this.wlssSl = wlssSl;
	}
	public String getWlssZl() {
		return wlssZl;
	}
	public void setWlssZl(String wlssZl) {
		this.wlssZl = wlssZl;
	}
	public String getWlssTj() {
		return wlssTj;
	}
	public void setWlssTj(String wlssTj) {
		this.wlssTj = wlssTj;
	}
	public String getSsly() {
		return ssly;
	}
	public void setSsly(String ssly) {
		this.ssly = ssly;
	}
	public String getSsyy() {
		return ssyy;
	}
	public void setSsyy(String ssyy) {
		this.ssyy = ssyy;
	}
	public String getSsZrr() {
		return ssZrr;
	}
	public void setSsZrr(String ssZrr) {
		this.ssZrr = ssZrr;
	}
	public String getXtcs20016() {
		return xtcs20016;
	}
	public void setXtcs20016(String xtcs20016) {
		this.xtcs20016 = xtcs20016;
	}
	public String getHdbh() {
		return hdbh;
	}
	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}
	public String getXybz() {
		return xybz;
	}
	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

}
