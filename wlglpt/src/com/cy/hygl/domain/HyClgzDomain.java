package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.List;


/**
 * The DOMAIN class FOR HY_PC_HD is created by tools.
 * @author HJH
 */

public class HyClgzDomain  extends BaseBusinessDomain {
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
	private String bz;
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
	private String gzcs;
	private String zhgzrq;
	
	/**跟踪信息**/
	private String clgzDjxh;
	private String rq;
	private String sm;
	private String szqyXzqhDm;
	private String szqyXzqhMc;
	private String xxdz;
	private String yjDdrq;
	private String llbz;
	private String yxbz;
	private String djJgbm;
	private String djJgmc;
	private String cjrCzyDjxh;
	private String cjrCzyMc;
	private String cjrq;
	private String xgrCzyDjxh;
	private String xgrMc;
	private String xgrq;
	private List<String> clgzDjxhs;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
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
	private int pageXh;
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
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public List<BaseBusinessDomain> getDataList() {
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
	public String getDjJgbm4Query() {
		return djJgbm4Query;
	}
	public void setDjJgbm4Query(String djJgbm4Query) {
		this.djJgbm4Query = djJgbm4Query;
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
	public String getClgzDjxh() {
		return clgzDjxh;
	}
	public void setClgzDjxh(String clgzDjxh) {
		this.clgzDjxh = clgzDjxh;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getSzqyXzqhDm() {
		return szqyXzqhDm;
	}
	public void setSzqyXzqhDm(String szqyXzqhDm) {
		this.szqyXzqhDm = szqyXzqhDm;
	}
	public String getSzqyXzqhMc() {
		return szqyXzqhMc;
	}
	public void setSzqyXzqhMc(String szqyXzqhMc) {
		this.szqyXzqhMc = szqyXzqhMc;
	}
	public String getXxdz() {
		return xxdz;
	}
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	public String getYjDdrq() {
		return yjDdrq;
	}
	public void setYjDdrq(String yjDdrq) {
		this.yjDdrq = yjDdrq;
	}
	public String getLlbz() {
		return llbz;
	}
	public void setLlbz(String llbz) {
		this.llbz = llbz;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getDjJgmc() {
		return djJgmc;
	}
	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
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
	public List<String> getClgzDjxhs() {
		return clgzDjxhs;
	}
	public void setClgzDjxhs(List<String> clgzDjxhs) {
		this.clgzDjxhs = clgzDjxhs;
	}
	public String getCjrCzyMc() {
		return cjrCzyMc;
	}
	public void setCjrCzyMc(String cjrCzyMc) {
		this.cjrCzyMc = cjrCzyMc;
	}
	public String getGzcs() {
		return gzcs;
	}
	public void setGzcs(String gzcs) {
		this.gzcs = gzcs;
	}
	public String getZhgzrq() {
		return zhgzrq;
	}
	public void setZhgzrq(String zhgzrq) {
		this.zhgzrq = zhgzrq;
	}
	public String getXgrMc() {
		return xgrMc;
	}
	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}
}
