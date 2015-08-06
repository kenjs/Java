package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_PC_HWQS is created by tools.
 * @author HJH
 */

public class HyPcHwqsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwqsDjxh;                         // 物流签收登记序号(SEQ_HWQS_DJXH)
	private String pcDjxh;                           // 派车登记序号
	private Date qsrq;                             // 签收日期
	private String qsrCzyDjxh;                       // 签收人
	private String bz;                               // 备注
	private String newDdDjxh;                        // 新订单登记序号
	private String djJgbm;                           // 派车部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	/**********************检索列表***********************/
	private Long pageXh;
	private String pcdh;
	private Double zcHj;
	private String zrbmDm;                           // 转入部门代码
	private String zrbmMc;
	private String zrbmDjxh;                         // 转入部门登记序号
	private String qsrMc;
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String khmc;
	private String ddbh;
	private String hwMc;    //货物名称
	private String hwbz;    //包装
	private Double sl;      //数量
	private Double zl;      //重量
	private Double tj;      //体积
	private String qsSl;	//签收数量
	private String qsZl;
	private String qsTj;
	private String slStr;      //数量
	private String zlStr;      //重量
	private String tjStr;      //体积
	private String jsslStr;
	private Double jssl;
	private String fhrMc;   // 发货人_名称
	private String fhrDjxh; //发货人登记序号
	private String fhrDz;   // 发货人_地址
	private String yqFhrq;    // 要求发货日期
	private String shrMc;   //收货人
	private String shrDz;   // 收货人_地址
	private String yqDdrq;    // 要求到达日期
	private Date pcrq;                             // 派车日期
	private String pcrCzyDjxh;
	private String pcrMc;
	private String pcJgbmMc;
	private String ssJgbmMc;
	private String cyrClhm;                          // 承运人_车辆号码
	private String cyrGchm;                          // 承运人_挂车号码
	private String cyrSjxm;                          // 承运人_司机姓名
	private String cyrSjsjhm;                        // 承运人_司机手机号码
	private Double psfy;
	private String wfhDjxh;
	private String wlssDjxh;
	private String xh;
	private String psfDjxh;
	private String sydwJgbm;
	private String sydwMc;
	private String hwmxXh;
	private String ddDjxh;
	private String sfqr;
	private String qrsm;
	private String wlssHwSl;        //物流损失是否登记(>0已登记，=0未登记)
	
	/*********************检索条件*********************/
	private String pcrqq;                             // 派车日期起
	private String pcrqz;                             // 派车日期止
	private String shrLxdh;
	private String sfJs;
	private String shfsMc;
	private String shfsDm;
	private String srDf;
	
	private String ljbz;
	private String xtcs20016;
	private String hdbh;
	private String xybz;
	
	public String getSrDf() {
		return srDf;
	}

	public void setSrDf(String srDf) {
		this.srDf = srDf;
	}

	public String getShfsMc() {
		return shfsMc;
	}

	public void setShfsMc(String shfsMc) {
		this.shfsMc = shfsMc;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getSfJs() {
		return sfJs;
	}

	public void setSfJs(String sfJs) {
		this.sfJs = sfJs;
	}

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyPcHwqsDomain() {
	}

	//获取物流签收登记序号(SEQ_HWQS_DJXH)
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//设置物流签收登记序号(SEQ_HWQS_DJXH)
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取签收日期
	public Date getQsrq() {
			return this.qsrq;
	}

	//设置签收日期
	public void setQsrq(Date qsrq) {
		this.qsrq=qsrq;
	}

	//获取签收人
	public String getQsrCzyDjxh() {
		return this.qsrCzyDjxh;
	}

	//设置签收人
	public void setQsrCzyDjxh(String qsrCzyDjxh) {
		this.qsrCzyDjxh=qsrCzyDjxh;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取新订单登记序号
	public String getNewDdDjxh() {
		return this.newDdDjxh;
	}

	//设置新订单登记序号
	public void setNewDdDjxh(String newDdDjxh) {
		this.newDdDjxh=newDdDjxh;
	}

	//获取派车部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置派车部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	public Long getPageXh() {
		return pageXh;
	}

	public void setPageXh(Long pageXh) {
		this.pageXh = pageXh;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public Double getZcHj() {
		return zcHj;
	}

	public void setZcHj(Double zcHj) {
		this.zcHj = zcHj;
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

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
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

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}

	public String getHwbz() {
		return hwbz;
	}

	public void setHwbz(String hwbz) {
		this.hwbz = hwbz;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
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

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
	}

	public String getPcrCzyDjxh() {
		return pcrCzyDjxh;
	}

	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh = pcrCzyDjxh;
	}

	public String getPcrMc() {
		return pcrMc;
	}

	public void setPcrMc(String pcrMc) {
		this.pcrMc = pcrMc;
	}

	public String getPcJgbmMc() {
		return pcJgbmMc;
	}

	public void setPcJgbmMc(String pcJgbmMc) {
		this.pcJgbmMc = pcJgbmMc;
	}

	public String getSsJgbmMc() {
		return ssJgbmMc;
	}

	public void setSsJgbmMc(String ssJgbmMc) {
		this.ssJgbmMc = ssJgbmMc;
	}

	public String getQsrMc() {
		return qsrMc;
	}

	public void setQsrMc(String qsrMc) {
		this.qsrMc = qsrMc;
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

	public String getCyrSjsjhm() {
		return cyrSjsjhm;
	}

	public void setCyrSjsjhm(String cyrSjsjhm) {
		this.cyrSjsjhm = cyrSjsjhm;
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
	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public String getSydwJgbm() {
		return sydwJgbm;
	}

	public void setSydwJgbm(String sydwJgbm) {
		this.sydwJgbm = sydwJgbm;
	}

	public String getSydwMc() {
		return sydwMc;
	}

	public void setSydwMc(String sydwMc) {
		this.sydwMc = sydwMc;
	}

	public String getHwmxXh() {
		return hwmxXh;
	}

	public void setHwmxXh(String hwmxXh) {
		this.hwmxXh = hwmxXh;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getPsfDjxh() {
		return psfDjxh;
	}

	public void setPsfDjxh(String psfDjxh) {
		this.psfDjxh = psfDjxh;
	}

	public String getSfqr() {
		return sfqr;
	}

	public void setSfqr(String sfqr) {
		this.sfqr = sfqr;
	}

	public String getQrsm() {
		return qrsm;
	}

	public void setQrsm(String qrsm) {
		this.qrsm = qrsm;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	public Double getZl() {
		return zl;
	}

	public void setZl(Double zl) {
		this.zl = zl;
	}

	public Double getTj() {
		return tj;
	}

	public void setTj(Double tj) {
		this.tj = tj;
	}

	public String getSlStr() {
		return slStr;
	}

	public void setSlStr(String slStr) {
		this.slStr = slStr;
	}

	public String getZlStr() {
		return zlStr;
	}

	public void setZlStr(String zlStr) {
		this.zlStr = zlStr;
	}

	public String getTjStr() {
		return tjStr;
	}

	public void setTjStr(String tjStr) {
		this.tjStr = tjStr;
	}

	public String getJsslStr() {
		return jsslStr;
	}

	public void setJsslStr(String jsslStr) {
		this.jsslStr = jsslStr;
	}

	public Double getJssl() {
		return jssl;
	}

	public void setJssl(Double jssl) {
		this.jssl = jssl;
	}

	public Double getPsfy() {
		return psfy;
	}

	public void setPsfy(Double psfy) {
		this.psfy = psfy;
	}
	
	public String getWlssDjxh() {
		return wlssDjxh;
	}

	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getQsSl() {
		return qsSl;
	}

	public void setQsSl(String qsSl) {
		this.qsSl = qsSl;
	}

	public String getQsZl() {
		return qsZl;
	}

	public void setQsZl(String qsZl) {
		this.qsZl = qsZl;
	}

	public String getQsTj() {
		return qsTj;
	}

	public void setQsTj(String qsTj) {
		this.qsTj = qsTj;
	}

	public String getWlssHwSl() {
		return wlssHwSl;
	}

	public void setWlssHwSl(String wlssHwSl) {
		this.wlssHwSl = wlssHwSl;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public String getLjbz() {
		return ljbz;
	}

	public void setLjbz(String ljbz) {
		this.ljbz = ljbz;
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
