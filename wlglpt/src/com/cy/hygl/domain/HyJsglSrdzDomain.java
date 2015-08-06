package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * �������
 * @author HJH
 */

public class HyJsglSrdzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private int pageXh;
	private String jsDjxh;                           // ����Ǽ����(SEQ_JS_DJXH)
	private String dzDjxh;							//���˵Ǽ����(SEQ_DZ_DJXH)
	private String wsspxh;                           // �����������
	private String wsSpztDm;                           // ��������״̬����
	private String wsSpztMc;                           // ��������״̬����
	private String dzztDm;                            // ����״̬����
	private String dzztMc;                            // ����״̬����
	private String khDjxh;                          // �ͻ�_�Ǽ����
	private String khMc;                            // �ͻ�_����
	private Double dzsr;                        // ����_����
	private Double dzyj;                        // ����_�ѽ�
	private Double dzwj;                        // ����_δ��
	private Double jssr;                        // ��������
	private Double jsyj;                        // �����ѽ�
	private Double jswj;                        // ����δ��
	private Double dzje;							 // ���˽��
	private String dzcybz;//���˲����־
	private String dzcyje;//���˲�����
	private String ddbh;                             // �������
	private Date xdrq;								//	�µ�����
	private String hwmc;                             // ��������
	private String jssl;                             // ��������
	private String hdbh;                             // �ص����
	private String sfd;                            // ʼ����
	private String mdd;                            // Ŀ�ĵ�
	private String sl;//����
	private String zl;//����
	private String tj;//���
	private String bz;                               // ��װ
	private String fhrLxr;
	private String fhrLxdh;
	private String shrLxr;
	private String shrLxdh;
	private String shrDz;
	private String hdshFlag;						//�ص��ջر�־
	private String dzrMc;                           // ������
	private Date dzrq;                             // ��������
	private String dwDm;                           // ��λ����
	private String dwmc;//��λ
	private String bmDm;                           // ���Ŵ���
	private String bmmc;//����
	private String pcdh;//�ɳ�����
	private String pcrq;//�ɳ�����
	private String clhm;//��������
	private String sjxm;//˾��
	
	private String ddDjxh;
	private String xh;//������ϸ���
	private String DM;
	private String MC;
	
	private String yjZtDm;
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private JsSrdzDomain jsSrdzDomain;//����-�������
	private List<HyJsglSrdzDomain> wlssXlList;
	
	
	private List<JsSrdzDzcyDomain> jsSrdzcyList;//����-�������-���ʲ���list
	
	private JsSrdzDzcyDomain dzcyDomain;//���˲���
	
	private String xgbz;//�޸ı�־
	private List<String> xhs;
	private List<String> xhsAll;
	private String defultRqQ;//Ĭ��������
	private String rqQ;                           // �µ����ڣ��������ڣ���
	private String rqZ;                         // �µ����ڣ��������ڣ���
	private String dzfsDm;                         // ���˷�ʽ����
	private boolean sendBz;//���ͱ�־
	private String pldzStr;
	
	private List<Double> cyjes;
	private List<String> dzcyyyDm;
	private List<String> dzcyClfsDm;
	private List<String> wlssDjxh;
	private List<String> bzs;
	private List<String> xcJsDjxh;
	public HyJsglSrdzDomain(){
		
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public String getHdbh() {
		return hdbh;
	}


	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
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

	public String getDwDm() {
		return dwDm;
	}

	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}

	public String getDzrMc() {
		return dzrMc;
	}

	public void setDzrMc(String dzrMc) {
		this.dzrMc = dzrMc;
	}

	public Date getDzrq() {
		return dzrq;
	}

	public void setDzrq(Date dzrq) {
		this.dzrq = dzrq;
	}

	public String getDzztDm() {
		return dzztDm;
	}

	public void setDzztDm(String dzztDm) {
		this.dzztDm = dzztDm;
	}

	public String getDzztMc() {
		return dzztMc;
	}

	public void setDzztMc(String dzztMc) {
		this.dzztMc = dzztMc;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getJssl() {
		return jssl;
	}

	public void setJssl(String jssl) {
		this.jssl = jssl;
	}

	public String getKhDjxh() {
		return khDjxh;
	}

	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}


	public String getMdd() {
		return mdd;
	}

	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	public String getRqQ() {
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getSfd() {
		return sfd;
	}

	public void setSfd(String sfd) {
		this.sfd = sfd;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getWsSpztDm() {
		return wsSpztDm;
	}

	public void setWsSpztDm(String wsSpztDm) {
		this.wsSpztDm = wsSpztDm;
	}

	public String getWsSpztMc() {
		return wsSpztMc;
	}

	public void setWsSpztMc(String wsSpztMc) {
		this.wsSpztMc = wsSpztMc;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public int getPageXh() {
		return pageXh;
	}

	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}

	public String getDefultRqQ() {
		return defultRqQ;
	}

	public void setDefultRqQ(String defultRqQ) {
		this.defultRqQ = defultRqQ;
	}

	public String getJsDjxh() {
		return jsDjxh;
	}

	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh = jsDjxh;
	}

	public JsSrdzDomain getJsSrdzDomain() {
		if(null==jsSrdzDomain)
			jsSrdzDomain=new JsSrdzDomain();
		return jsSrdzDomain;
	}

	public void setJsSrdzDomain(JsSrdzDomain jsSrdzDomain) {
		this.jsSrdzDomain = jsSrdzDomain;
	}

	public String getXgbz() {
		return xgbz;
	}

	public void setXgbz(String xgbz) {
		this.xgbz = xgbz;
	}

	public List<JsSrdzDzcyDomain> getJsSrdzcyList() {
		if(null==jsSrdzcyList)
			jsSrdzcyList=new ArrayList<JsSrdzDzcyDomain>();
		return jsSrdzcyList;
	}

	public void setJsSrdzcyList(List<JsSrdzDzcyDomain> jsSrdzcyList) {
		this.jsSrdzcyList = jsSrdzcyList;
	}

	public JsSrdzDzcyDomain getDzcyDomain() {
		if(null==dzcyDomain)
			dzcyDomain=new JsSrdzDzcyDomain();
		return dzcyDomain;
	}

	public void setDzcyDomain(JsSrdzDzcyDomain dzcyDomain) {
		this.dzcyDomain = dzcyDomain;
	}

	public List<String> getXhs() {
		if(null==xhs)
			xhs=new ArrayList<String>();
		return xhs;
	}

	public void setXhs(List<String> xhs) {
		this.xhs = xhs;
	}

	public String getDzcybz() {
		return dzcybz;
	}

	public void setDzcybz(String dzcybz) {
		this.dzcybz = dzcybz;
	}

	public String getDzcyje() {
		return dzcyje;
	}

	public void setDzcyje(String dzcyje) {
		this.dzcyje = dzcyje;
	}

	public String getWsspxh() {
		return wsspxh;
	}

	public void setWsspxh(String wsspxh) {
		this.wsspxh = wsspxh;
	}

	public boolean isSendBz() {
		return sendBz;
	}

	public void setSendBz(boolean sendBz) {
		this.sendBz = sendBz;
	}

	public String getDzDjxh() {
		return dzDjxh;
	}

	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh = dzDjxh;
	}

	public String getBmDm() {
		return bmDm;
	}

	public void setBmDm(String bmDm) {
		this.bmDm = bmDm;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getClhm() {
		return clhm;
	}

	public void setClhm(String clhm) {
		this.clhm = clhm;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public Double getDzsr() {
		return dzsr;
	}

	public void setDzsr(Double dzsr) {
		this.dzsr = dzsr;
	}

	public Double getDzwj() {
		return dzwj;
	}

	public void setDzwj(Double dzwj) {
		this.dzwj = dzwj;
	}

	public Double getDzyj() {
		return dzyj;
	}

	public void setDzyj(Double dzyj) {
		this.dzyj = dzyj;
	}

	public Double getJssr() {
		return jssr;
	}

	public void setJssr(Double jssr) {
		this.jssr = jssr;
	}

	public Double getJswj() {
		return jswj;
	}

	public void setJswj(Double jswj) {
		this.jswj = jswj;
	}

	public Double getJsyj() {
		return jsyj;
	}

	public void setJsyj(Double jsyj) {
		this.jsyj = jsyj;
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

	public String getDzfsDm() {
		return dzfsDm;
	}

	public void setDzfsDm(String dzfsDm) {
		this.dzfsDm = dzfsDm;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public List<Double> getCyjes() {
		if (cyjes == null) {
			cyjes = new ArrayList<Double>();
		}
		return cyjes;
	}

	public void setCyjes(List<Double> cyjes) {
		this.cyjes = cyjes;
	}

	public List<String> getDzcyyyDm() {
		if (dzcyyyDm == null) {
			dzcyyyDm = new ArrayList<String>();
		}
		return dzcyyyDm;
	}

	public void setDzcyyyDm(List<String> dzcyyyDm) {
		this.dzcyyyDm = dzcyyyDm;
	}

	public List<String> getDzcyClfsDm() {
		if (dzcyClfsDm == null) {
			dzcyClfsDm = new ArrayList<String>();
		}
		return dzcyClfsDm;
	}

	public void setDzcyClfsDm(List<String> dzcyClfsDm) {
		this.dzcyClfsDm = dzcyClfsDm;
	}

	public List<String> getBzs() {
		if (bzs == null) {
			bzs = new ArrayList<String>();
		}
		return bzs;
	}

	public void setBzs(List<String> bzs) {
		this.bzs = bzs;
	}

	public Double getDzje() {
		return dzje;
	}

	public void setDzje(Double dzje) {
		this.dzje = dzje;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDM() {
		return DM;
	}

	public void setDM(String dM) {
		DM = dM;
	}


	public String getMC() {
		return MC;
	}

	public void setMC(String mC) {
		MC = mC;
	}

	public String getFhrLxr() {
		return fhrLxr;
	}

	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}

	public String getFhrLxdh() {
		return fhrLxdh;
	}

	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh = fhrLxdh;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getHdshFlag() {
		return hdshFlag;
	}

	public void setHdshFlag(String hdshFlag) {
		this.hdshFlag = hdshFlag;
	}

	public List<HyJsglSrdzDomain> getWlssXlList() {
		if(null==wlssXlList)
			wlssXlList=new ArrayList<HyJsglSrdzDomain>();
		return wlssXlList;
	}

	public void setWlssXlList(List<HyJsglSrdzDomain> wlssXlList) {
		this.wlssXlList = wlssXlList;
	}

	public List<String> getWlssDjxh() {
		return wlssDjxh;
	}

	public void setWlssDjxh(List<String> wlssDjxh) {
		this.wlssDjxh = wlssDjxh;
	}

	public List<String> getXcJsDjxh() {
		return xcJsDjxh;
	}

	public void setXcJsDjxh(List<String> xcJsDjxh) {
		this.xcJsDjxh = xcJsDjxh;
	}

	public List<String> getXhsAll() {
		return xhsAll;
	}

	public void setXhsAll(List<String> xhsAll) {
		this.xhsAll = xhsAll;
	}

	public String getPldzStr() {
		return pldzStr;
	}

	public void setPldzStr(String pldzStr) {
		this.pldzStr = pldzStr;
	}

	public String getYjZtDm() {
		return yjZtDm;
	}

	public void setYjZtDm(String yjZtDm) {
		this.yjZtDm = yjZtDm;
	}
}
