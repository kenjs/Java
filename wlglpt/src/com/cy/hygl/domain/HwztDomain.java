package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LYY
 */

public class HwztDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwztDjxh;					//货物自提登记序号
	private String wfhDjxh;						//未发货登记序号(SEQ_WFH_DJXH)
	private String ddDjxh;						//订单登记序号
	private String xh;							//序号(货物明细序号)
	private String jbrCzyDjxh;					//经办人
	private String thrq;						//提货日期
	private String bzsm;						//备注说明
	private String thbz;						//自提标志(Y 已提/N未提)
	private String yxbz;						//有效标志(Y/N)
	private String cjrCzyDjxh;					//创建人
	private String cjrq;						//创建日期
	private String xgrCzyDjxh;					//修改人
	private String xgrq;						//修改日期
	private String thrMc;						//提货人名称
	private String thrLxdh;						//提货人联系电话
	private String thrSfzh;						//提货人身份证号
	
	private String ddbh;							//订单编号
	
	private List<HwztDomain> dataList; 		 //查询列表

	/*****查询条件********/
	private String ssJgbm;
	private String pcJgbm;
	private String pcrCzyDjxh;
	private String thrqQ;
	private String thrqZ;
	private String fhrMc;
	private String fhrDjxh;
	private String pcdh;
	private String hwmc;
	private String shrCzyDjxh;
	private String shrMc;
	private String zt;
	private String bz;
	private String jbrmc;
	private String sl;
	private String zl;
	private String tj;
	private String jsSl;
	private String srDf;
	private String dsHk;
	private String shrDz;
	private String shrLxdh;
	private String tager;
	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getDsHk() {
		return dsHk;
	}

	public void setDsHk(String dsHk) {
		this.dsHk = dsHk;
	}

	public String getSrDf() {
		return srDf;
	}

	public void setSrDf(String srDf) {
		this.srDf = srDf;
	}

	public String getBzsm() {
		return bzsm;
	}

	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}

	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}

	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}

	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public List<HwztDomain> getDataList() {
		if(dataList == null){
			dataList = new ArrayList<HwztDomain>();
		}
		return dataList;
	}

	public void setDataList(List<HwztDomain> dataList) {
		this.dataList = dataList;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getHwztDjxh() {
		return hwztDjxh;
	}

	public void setHwztDjxh(String hwztDjxh) {
		this.hwztDjxh = hwztDjxh;
	}

	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}

	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}

	public String getThbz() {
		return thbz;
	}

	public void setThbz(String thbz) {
		this.thbz = thbz;
	}

	public String getThrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.thrq);
		}
		catch(Exception e){
			return this.thrq;
		}
	}

	public void setThrq(String thrq) {
		this.thrq = thrq;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}

	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}

	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
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

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
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

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getThrqQ() {
		return thrqQ;
	}

	public void setThrqQ(String thrqQ) {
		this.thrqQ = thrqQ;
	}

	public String getThrqZ() {
		return thrqZ;
	}

	public void setThrqZ(String thrqZ) {
		this.thrqZ = thrqZ;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJbrmc() {
		return jbrmc;
	}

	public void setJbrmc(String jbrmc) {
		this.jbrmc = jbrmc;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getJsSl() {
		return jsSl;
	}

	public void setJsSl(String jsSl) {
		this.jsSl = jsSl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getShrCzyDjxh() {
		return shrCzyDjxh;
	}

	public void setShrCzyDjxh(String shrCzyDjxh) {
		this.shrCzyDjxh = shrCzyDjxh;
	}

	public String getThrLxdh() {
		return thrLxdh;
	}

	public void setThrLxdh(String thrLxdh) {
		this.thrLxdh = thrLxdh;
	}

	public String getThrMc() {
		return thrMc;
	}

	public void setThrMc(String thrMc) {
		this.thrMc = thrMc;
	}

	public String getThrSfzh() {
		return thrSfzh;
	}

	public void setThrSfzh(String thrSfzh) {
		this.thrSfzh = thrSfzh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}


}
