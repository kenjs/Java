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
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double szHwSl;                           // ʵװ_����_����
	private Double szHwZl;                           // ʵװ_����_����
	private Double szHwTj;                           // ʵװ_����_���
	private Date yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private Double szJsSl;                           // ʵװ_��������
	private String hdbh;                             // �ص����(����Ѷ��ŷָ�)
	private Date hdjsrq;                           // �ص���������
	private String bz;                               // ��ע
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
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	/*****��ѯ����********/
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

	/********************ϵͳ����*********************/
	private String xtcsSfsp;						//�ص��ǼǱ䶯�Ƿ���Ҫ����
	private String xtcs20016;
	public HyPcHddjDomain() {
	}

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡδ�����Ǽ����
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//����δ�����Ǽ����
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//��ȡ�ص����
	public String getHdbh() {
		return this.hdbh;
	}

	//���ûص����
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ���(�����ƻ�����ϸ���)
	public String getXh() {
		return this.xh;
	}

	//�������(�����ƻ�����ϸ���)
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
