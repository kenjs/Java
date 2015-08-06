package com.cy.hygl.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
/**
 * 
 * @author hy
 *
 */
public class HyZyglFydjDomain extends BaseBusinessDomain{

	private static final long serialVersionUID = 1L;

	private String pcDjxh;
	private String pcdh;
	private String pcrq;
	private String pcfsDm;
	private String pcfsMc;
	private String zcfsDm;
	private String zcfsMc;
	private String cyrClhm;
	private String cyrGchm;
	private String cyrSjxm;
	private String yfHj;
	private String zrbmDm;
	private String zrbmMc;
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String hwmc;
	private String bz;								//包装
	private String sl;
	private String zl;
	private String tj;
	private String jsSl;
	private String fhrMc;
	private String fhrDz;
	private String yqFhrq;
	private String shrMc;
	private String shrDz;
	private String yqDdrq;
	private String pcrCzyDjxh;
	private String pcrMc;
	private String pcJgbm;
	private String pcJgmc;
	private String ssJgbm;
	private String ssJgmc;
	private String khmc;
	private String ddbh;
	private Date xdrq;
	
	private Double je;
	private String fydjxmWhXh;
	private String fydjxmWhMc;
	private String fyclfsDm;
	private String fyclfsMc;
	private String khDjxh;
	private String wfhDjxh;
	private String ddDjxh;
	private String hwmxxh;
	private String beiz;							//备注
	private String zgsbm;
	
	private String fyDjxh;                           // 费用登记序号
	private String xh;                               // 序号                    // 费用处理方式代码
	private String yxbz;                             // 有效标志(Y/N)
	
	private Double fyhj;                             // 费用合计
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsspztMc;
	private String wsSpxh;                           // 文书审批序号
	private String djJgbm;                           // 登记部门
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private int checkRes;
	
	private boolean sendBz;//发送标志，add hel
	private String hdbh;//回单编号
	private String xybz;//协议标志
	
	private String lb;
	private String dm;
	private String mc;
	private String jsonStr;
	private String clsxDm;
	private String ssSl;
	private String ssZl;
	private String ssTj;
	private String wlssLybz;
	private String wlssLymc;
	private String spbcbz;
	private String zrrDjxh;
	private String zrr;
	private List<String> wlssclfsDms;
	private List<String> wlssyyWhXhs;
	private List<String> zrrDjxhs;
	private List<String> xhs;
	private List<String> wlssMxJes;
	
	private int rtnErrCode;
	private String rtnErrMess;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<HyZyglFydjDomain> fyDjMxList;
	private List<HyZyglFydjDomain> khHwList;
	private List<HyWlssdjMxDomain> wlssMxList;
	private List<HyZyglFydjDomain> sjList;
	private List<DmbGgDomain> clfsList;
	private List<DmbGgDomain> ssyyList;
	private List<HyZyglFydjDomain> skfList;
	/********查询条件*********/
	private String pcJgbm4Query;
	private String djJgbm4Query;
	private String dwbmBz4Query;
	private String pcrCzyDjxh4Query;
	private String fhrDjxh;
	private String fhrMc4Query;
	private String pcrqQ;
	private String pcrqZ;
	private String clhm4Query;
	private String sjxm4Query;
	private String ddbh4Query;
	private String pcdh4Query;
	private String zt;
	private int pageXh;
	private String tager;
	private String selOrUpd ;
	
	private String fkfCzyDjxh;
	private String skfCzyDjxh;
	private String srje;
	private String zfje;
	
	private String qdDjxh;
	private String existBz;
	/********************系统参数*********************/
	private String xtcsSfsp;						//费用登记是否需要审批
	private String xtcs20016;
	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	private List hwList;
	private String conBz;
	private String conDm;
	private String currentObjId;
	private String currentObjName;
	private String defaultValue;
	private String wlssDjxh;
	private String fydjXmmc;
	private String dffDjxh;
	private String dfJe;
	private String khJs;
	private String dffMc;
	
	/***************物流损失上传照片**********************/
	private String zpdz;
	private String zpmc;
	private String xgsj;
	
	
	public String getZpdz() {
		return zpdz;
	}

	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}

	public String getZpmc() {
		return zpmc;
	}

	public void setZpmc(String zpmc) {
		this.zpmc = zpmc;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getDffMc() {
		return dffMc;
	}

	public void setDffMc(String dffMc) {
		this.dffMc = dffMc;
	}

	public String getDffDjxh() {
		return dffDjxh;
	}

	public void setDffDjxh(String dffDjxh) {
		this.dffDjxh = dffDjxh;
	}

	public String getDfJe() {
		return dfJe;
	}

	public void setDfJe(String dfJe) {
		this.dfJe = dfJe;
	}

	public String getKhJs() {
		return khJs;
	}

	public void setKhJs(String khJs) {
		this.khJs = khJs;
	}

	public String getCurrentObjName() {
		return currentObjName;
	}

	public void setCurrentObjName(String currentObjName) {
		this.currentObjName = currentObjName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getWlssDjxh() {
		return wlssDjxh;
	}

	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}

	public List getHwList() {
		if(hwList==null){
			hwList=new ArrayList();
		}
		return hwList;
	}

	public void setHwList(List hwList) {
		this.hwList = hwList;
	}

	public String getConBz() {
		return conBz;
	}

	public void setConBz(String conBz) {
		this.conBz = conBz;
	}

	public String getConDm() {
		return conDm;
	}

	public void setConDm(String conDm) {
		this.conDm = conDm;
	}

	public String getCurrentObjId() {
		return currentObjId;
	}

	public void setCurrentObjId(String currentObjId) {
		this.currentObjId = currentObjId;
	}
	
	public String getWlssyyWhXh() {
		return wlssyyWhXh;
	}

	public void setWlssyyWhXh(String wlssyyWhXh) {
		this.wlssyyWhXh = wlssyyWhXh;
	}

	public String getWlssclfsDm() {
		return wlssclfsDm;
	}

	public void setWlssclfsDm(String wlssclfsDm) {
		this.wlssclfsDm = wlssclfsDm;
	}

	public String getHjSr() {
		return hjSr;
	}

	public void setHjSr(String hjSr) {
		this.hjSr = hjSr;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private String wlssyyWhXh;
	private String wlssyy;
	private String wlssclfsDm;
	private String wlssclfsMc;
	private String hjSr;
	private String error;
	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getPcJgbm4Query() {
		return pcJgbm4Query;
	}

	public void setPcJgbm4Query(String pcJgbm4Query) {
		this.pcJgbm4Query = pcJgbm4Query;
	}

	public String getDwbmBz4Query() {
		return dwbmBz4Query;
	}

	public void setDwbmBz4Query(String dwbmBz4Query) {
		this.dwbmBz4Query = dwbmBz4Query;
	}

	public String getPcrCzyDjxh4Query() {
		return pcrCzyDjxh4Query;
	}

	public void setPcrCzyDjxh4Query(String pcrCzyDjxh4Query) {
		this.pcrCzyDjxh4Query = pcrCzyDjxh4Query;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getFhrMc4Query() {
		return fhrMc4Query;
	}

	public void setFhrMc4Query(String fhrMc4Query) {
		this.fhrMc4Query = fhrMc4Query;
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

	public String getClhm4Query() {
		return clhm4Query;
	}

	public void setClhm4Query(String clhm4Query) {
		this.clhm4Query = clhm4Query;
	}

	public String getSjxm4Query() {
		return sjxm4Query;
	}

	public void setSjxm4Query(String sjxm4Query) {
		this.sjxm4Query = sjxm4Query;
	}

	public String getDdbh4Query() {
		return ddbh4Query;
	}

	public void setDdbh4Query(String ddbh4Query) {
		this.ddbh4Query = ddbh4Query;
	}

	public String getPcdh4Query() {
		return pcdh4Query;
	}

	public void setPcdh4Query(String pcdh4Query) {
		this.pcdh4Query = pcdh4Query;
	}

	public int getPageXh() {
		return pageXh;
	}

	public void setPageXh(int pageXh) {
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

	public String getPcrq() {
		if (StringUtils.isNotBlank(pcrq)) {
			try {
				pcrq = SysDateUtil.getYyyyMmdd(pcrq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pcrq;
	}

	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
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

	public String getYfHj() {
		return yfHj;
	}

	public void setYfHj(String yfHj) {
		this.yfHj = yfHj;
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

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getDdbh() {
		return ddbh;
	}

	public String getFydjXmmc() {
		return fydjXmmc;
	}

	public void setFydjXmmc(String fydjXmmc) {
		this.fydjXmmc = fydjXmmc;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getDjJgbm4Query() {
		return djJgbm4Query;
	}

	public void setDjJgbm4Query(String djJgbm4Query) {
		this.djJgbm4Query = djJgbm4Query;
	}

	public Double getJe() {
		return je;
	}

	public void setJe(Double je) {
		this.je = je;
	}
	
	public String getFyclfsDm() {
		return fyclfsDm;
	}

	public void setFyclfsDm(String fyclfsDm) {
		this.fyclfsDm = fyclfsDm;
	}

	public String getKhDjxh() {
		return khDjxh;
	}

	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getHwmxxh() {
		return hwmxxh;
	}

	public void setHwmxxh(String hwmxxh) {
		this.hwmxxh = hwmxxh;
	}

	public String getBeiz() {
		return beiz;
	}

	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}

	public List<HyZyglFydjDomain> getFyDjMxList() {
		if(fyDjMxList == null){
			fyDjMxList = new ArrayList<HyZyglFydjDomain>();
		}
		return fyDjMxList;
	}

	public void setFyDjMxList(List<HyZyglFydjDomain> fyDjMxList) {
		this.fyDjMxList = fyDjMxList;
	}

	public String getFyDjxh() {
		return fyDjxh;
	}

	public void setFyDjxh(String fyDjxh) {
		this.fyDjxh = fyDjxh;
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

	public Double getFyhj() {
		return fyhj;
	}

	public void setFyhj(Double fyhj) {
		this.fyhj = fyhj;
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

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}

	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}

	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}

	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}

	public String getXgrq() {
		return xgrq;
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getFyclfsMc() {
		return fyclfsMc;
	}

	public void setFyclfsMc(String fyclfsMc) {
		this.fyclfsMc = fyclfsMc;
	}

	public int getCheckRes() {
		return checkRes;
	}

	public void setCheckRes(int checkRes) {
		this.checkRes = checkRes;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public List<HyZyglFydjDomain> getKhHwList() {
		if(khHwList == null) {
			khHwList = new ArrayList<HyZyglFydjDomain>();
		}
		return khHwList;
	}

	public void setKhHwList(List<HyZyglFydjDomain> khHwList) {
		this.khHwList = khHwList;
	}

	public List<HyWlssdjMxDomain> getWlssMxList() {
		if(wlssMxList == null) {
			wlssMxList = new ArrayList<HyWlssdjMxDomain>();
		}
		return wlssMxList;
	}

	public void setWlssMxList(List<HyWlssdjMxDomain> wlssMxList) {
		this.wlssMxList = wlssMxList;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public int getRtnErrCode() {
		return rtnErrCode;
	}

	public void setRtnErrCode(int rtnErrCode) {
		this.rtnErrCode = rtnErrCode;
	}

	public String getRtnErrMess() {
		return rtnErrMess;
	}

	public void setRtnErrMess(String rtnErrMess) {
		this.rtnErrMess = rtnErrMess;
	}

	public String getWsspztMc() {
		return wsspztMc;
	}

	public void setWsspztMc(String wsspztMc) {
		this.wsspztMc = wsspztMc;
	}

	public boolean isSendBz() {
		return sendBz;
	}

	public void setSendBz(boolean sendBz) {
		this.sendBz = sendBz;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getSelOrUpd() {
		return selOrUpd;
	}

	public void setSelOrUpd(String selOrUpd) {
		this.selOrUpd = selOrUpd;
	}

	public String getXtcsSfsp() {
		return xtcsSfsp;
	}

	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}

	public String getClsxDm() {
		return clsxDm;
	}

	public void setClsxDm(String clsxDm) {
		this.clsxDm = clsxDm;
	}

	public String getFydjxmWhXh() {
		return fydjxmWhXh;
	}

	public void setFydjxmWhXh(String fydjxmWhXh) {
		this.fydjxmWhXh = fydjxmWhXh;
	}

	public String getFydjxmWhMc() {
		return fydjxmWhMc;
	}

	public void setFydjxmWhMc(String fydjxmWhMc) {
		this.fydjxmWhMc = fydjxmWhMc;
	}

	public String getSsSl() {
		return ssSl;
	}

	public void setSsSl(String ssSl) {
		this.ssSl = ssSl;
	}

	public String getSsZl() {
		return ssZl;
	}

	public void setSsZl(String ssZl) {
		this.ssZl = ssZl;
	}

	public String getSsTj() {
		return ssTj;
	}

	public void setSsTj(String ssTj) {
		this.ssTj = ssTj;
	}

	public List<DmbGgDomain> getClfsList() {
		if(clfsList == null) {
			clfsList = new ArrayList<DmbGgDomain>();
		}
		return clfsList;
	}

	public void setClfsList(List<DmbGgDomain> clfsList) {
		this.clfsList = clfsList;
	}

	public List<DmbGgDomain> getSsyyList() {
		if(ssyyList == null) {
			ssyyList = new ArrayList<DmbGgDomain>();
		}
		return ssyyList;
	}

	public void setSsyyList(List<DmbGgDomain> ssyyList) {
		this.ssyyList = ssyyList;
	}

	public List<String> getWlssclfsDms() {
		return wlssclfsDms;
	}

	public void setWlssclfsDms(List<String> wlssclfsDms) {
		this.wlssclfsDms = wlssclfsDms;
	}

	public List<String> getWlssyyWhXhs() {
		return wlssyyWhXhs;
	}

	public void setWlssyyWhXhs(List<String> wlssyyWhXhs) {
		this.wlssyyWhXhs = wlssyyWhXhs;
	}

	public List<String> getXhs() {
		return xhs;
	}

	public void setXhs(List<String> xhs) {
		this.xhs = xhs;
	}

	public List<String> getWlssMxJes() {
		return wlssMxJes;
	}

	public void setWlssMxJes(List<String> wlssMxJes) {
		this.wlssMxJes = wlssMxJes;
	}

	public String getQdDjxh() {
		return qdDjxh;
	}

	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh = qdDjxh;
	}

	public String getWlssyy() {
		return wlssyy;
	}

	public void setWlssyy(String wlssyy) {
		this.wlssyy = wlssyy;
	}

	public String getWlssclfsMc() {
		return wlssclfsMc;
	}

	public void setWlssclfsMc(String wlssclfsMc) {
		this.wlssclfsMc = wlssclfsMc;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public String getExistBz() {
		return existBz;
	}

	public void setExistBz(String existBz) {
		this.existBz = existBz;
	}

	public String getZgsbm() {
		return zgsbm;
	}

	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}

	public List<String> getZrrDjxhs() {
		return zrrDjxhs;
	}

	public void setZrrDjxhs(List<String> zrrDjxhs) {
		this.zrrDjxhs = zrrDjxhs;
	}

	public String getWlssLybz() {
		return wlssLybz;
	}

	public void setWlssLybz(String wlssLybz) {
		this.wlssLybz = wlssLybz;
	}

	public List<HyZyglFydjDomain> getSjList() {
		if(sjList == null) {
			sjList = new ArrayList<HyZyglFydjDomain>();
		}
		return sjList;
	}

	public void setSjList(List<HyZyglFydjDomain> sjList) {
		this.sjList = sjList;
	}

	public String getFkfCzyDjxh() {
		return fkfCzyDjxh;
	}

	public void setFkfCzyDjxh(String fkfCzyDjxh) {
		this.fkfCzyDjxh = fkfCzyDjxh;
	}

	public String getSkfCzyDjxh() {
		return skfCzyDjxh;
	}

	public void setSkfCzyDjxh(String skfCzyDjxh) {
		this.skfCzyDjxh = skfCzyDjxh;
	}

	public String getSrje() {
		return srje;
	}

	public void setSrje(String srje) {
		this.srje = srje;
	}

	public String getZfje() {
		return zfje;
	}

	public void setZfje(String zfje) {
		this.zfje = zfje;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public List<HyZyglFydjDomain> getSkfList() {
		if(skfList == null){
			skfList = new ArrayList<HyZyglFydjDomain>();
		}
		return skfList;
	}

	public void setSkfList(List<HyZyglFydjDomain> skfList) {
		this.skfList = skfList;
	}

	public String getWlssLymc() {
		return wlssLymc;
	}

	public void setWlssLymc(String wlssLymc) {
		this.wlssLymc = wlssLymc;
	}

	public String getSpbcbz() {
		return spbcbz;
	}

	public void setSpbcbz(String spbcbz) {
		this.spbcbz = spbcbz;
	}

	public String getZrrDjxh() {
		return zrrDjxh;
	}

	public void setZrrDjxh(String zrrDjxh) {
		this.zrrDjxh = zrrDjxh;
	}

	public String getZrr() {
		return zrr;
	}

	public void setZrr(String zrr) {
		this.zrr = zrr;
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

	public String getXtcs20016() {
		return xtcs20016;
	}

	public void setXtcs20016(String xtcs20016) {
		this.xtcs20016 = xtcs20016;
	}
	
}