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
	private String hwqsDjxh;                         // ����ǩ�յǼ����(SEQ_HWQS_DJXH)
	private String pcDjxh;                           // �ɳ��Ǽ����
	private Date qsrq;                             // ǩ������
	private String qsrCzyDjxh;                       // ǩ����
	private String bz;                               // ��ע
	private String newDdDjxh;                        // �¶����Ǽ����
	private String djJgbm;                           // �ɳ�����
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	
	/**********************�����б�***********************/
	private Long pageXh;
	private String pcdh;
	private Double zcHj;
	private String zrbmDm;                           // ת�벿�Ŵ���
	private String zrbmMc;
	private String zrbmDjxh;                         // ת�벿�ŵǼ����
	private String qsrMc;
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String khmc;
	private String ddbh;
	private String hwMc;    //��������
	private String hwbz;    //��װ
	private Double sl;      //����
	private Double zl;      //����
	private Double tj;      //���
	private String qsSl;	//ǩ������
	private String qsZl;
	private String qsTj;
	private String slStr;      //����
	private String zlStr;      //����
	private String tjStr;      //���
	private String jsslStr;
	private Double jssl;
	private String fhrMc;   // ������_����
	private String fhrDjxh; //�����˵Ǽ����
	private String fhrDz;   // ������_��ַ
	private String yqFhrq;    // Ҫ�󷢻�����
	private String shrMc;   //�ջ���
	private String shrDz;   // �ջ���_��ַ
	private String yqDdrq;    // Ҫ�󵽴�����
	private Date pcrq;                             // �ɳ�����
	private String pcrCzyDjxh;
	private String pcrMc;
	private String pcJgbmMc;
	private String ssJgbmMc;
	private String cyrClhm;                          // ������_��������
	private String cyrGchm;                          // ������_�ҳ�����
	private String cyrSjxm;                          // ������_˾������
	private String cyrSjsjhm;                        // ������_˾���ֻ�����
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
	private String wlssHwSl;        //������ʧ�Ƿ�Ǽ�(>0�ѵǼǣ�=0δ�Ǽ�)
	
	/*********************��������*********************/
	private String pcrqq;                             // �ɳ�������
	private String pcrqz;                             // �ɳ�����ֹ
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

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyPcHwqsDomain() {
	}

	//��ȡ����ǩ�յǼ����(SEQ_HWQS_DJXH)
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//��������ǩ�յǼ����(SEQ_HWQS_DJXH)
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡǩ������
	public Date getQsrq() {
			return this.qsrq;
	}

	//����ǩ������
	public void setQsrq(Date qsrq) {
		this.qsrq=qsrq;
	}

	//��ȡǩ����
	public String getQsrCzyDjxh() {
		return this.qsrCzyDjxh;
	}

	//����ǩ����
	public void setQsrCzyDjxh(String qsrCzyDjxh) {
		this.qsrCzyDjxh=qsrCzyDjxh;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�¶����Ǽ����
	public String getNewDdDjxh() {
		return this.newDdDjxh;
	}

	//�����¶����Ǽ����
	public void setNewDdDjxh(String newDdDjxh) {
		this.newDdDjxh=newDdDjxh;
	}

	//��ȡ�ɳ�����
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//�����ɳ�����
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ��������
	public Date getCjrq() {
			return this.cjrq;
	}

	//���ô�������
	public void setCjrq(Date cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//��ȡ�޸�����
	public Date getXgrq() {
			return this.xgrq;
	}

	//�����޸�����
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
