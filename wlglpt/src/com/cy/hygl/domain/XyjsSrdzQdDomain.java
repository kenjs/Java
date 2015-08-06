package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR XYJS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class XyjsSrdzQdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String xyDjxh;                           // �ͻ��Ǽ����
	private String qdmc;                             // �嵥����
	private String dzqdHzfsDm;                       // �����嵥���ܷ�ʽ����
	private String fylbDm;
	private Double heJe;                             // �ϼƽ��
	private Double dzJe;
	private Double dzcyJe;
	private Double yfJe;                             // ���ս��
	private Double wfJe;                             // δ�ս��
	private Double ysqKpje;                          // �����뿪Ʊ���
	private Double wsqKpje;                          // δ���뿪Ʊ���
	private String djrCzyDjxh;                       // �Ǽ���
	private Date djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String zt;
	private String bz;
	
	private String ztStr;
	
	private Date rqQ;
	private Date rqZ;
	private String djrMc;
	private String djJgmc;
	private String ssJgmc;
	private String xyMc;
	
	private String tempFlag;
	private Date pcrqQ;
	private Date pcrqZ;
	private Date xdrqQ;
	private Date xdrqZ;
	private String pcdh;
	private String ddbh;
	private Double hwSl;
	private String hdbh;
	
	private List<String> ywDjxhs;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<XyjsPcHwxxDomain> jsxxList;
	private List<XyjsPcHwxxDomain> qdmxList;

	public XyjsSrdzQdDomain() {
	}

	//��ȡ�嵥�Ǽ����(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//�����嵥�Ǽ����(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//��ȡ�ͻ��Ǽ����
	public String getXyDjxh() {
		return this.xyDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setXyDjxh(String xyDjxh) {
		this.xyDjxh=xyDjxh;
	}

	//��ȡ�嵥����
	public String getQdmc() {
		return this.qdmc;
	}

	//�����嵥����
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//��ȡ�����嵥���ܷ�ʽ����
	public String getDzqdHzfsDm() {
		return this.dzqdHzfsDm;
	}

	//���ö����嵥���ܷ�ʽ����
	public void setDzqdHzfsDm(String dzqdHzfsDm) {
		this.dzqdHzfsDm=dzqdHzfsDm;
	}

	//��ȡ�ϼƽ��
	public Double getHeJe() {
		return this.heJe;
	}

	//���úϼƽ��
	public void setHeJe(Double heJe) {
		this.heJe=heJe;
	}

	//��ȡ���ս��
	public Double getYfJe() {
		return this.yfJe;
	}

	//�������ս��
	public void setYfJe(Double yfJe) {
		this.yfJe=yfJe;
	}

	//��ȡδ�ս��
	public Double getWfJe() {
		return this.wfJe;
	}

	//����δ�ս��
	public void setWfJe(Double wfJe) {
		this.wfJe=wfJe;
	}

	//��ȡ�����뿪Ʊ���
	public Double getYsqKpje() {
		return this.ysqKpje;
	}

	//���������뿪Ʊ���
	public void setYsqKpje(Double ysqKpje) {
		this.ysqKpje=ysqKpje;
	}

	//��ȡδ���뿪Ʊ���
	public Double getWsqKpje() {
		return this.wsqKpje;
	}

	//����δ���뿪Ʊ���
	public void setWsqKpje(Double wsqKpje) {
		this.wsqKpje=wsqKpje;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public Date getDjrq() {
			return this.djrq;
	}

	//���õǼ�����
	public void setDjrq(Date djrq) {
		this.djrq=djrq;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
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

	public Date getRqQ() {
		return rqQ;
	}

	public void setRqQ(Date rqQ) {
		this.rqQ = rqQ;
	}

	public Date getRqZ() {
		return rqZ;
	}

	public void setRqZ(Date rqZ) {
		this.rqZ = rqZ;
	}

	public String getDjrMc() {
		return djrMc;
	}

	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getXyMc() {
		return xyMc;
	}

	public void setXyMc(String xyMc) {
		this.xyMc = xyMc;
	}

	public String getTempFlag() {
		return tempFlag;
	}

	public void setTempFlag(String tempFlag) {
		this.tempFlag = tempFlag;
	}

	public Date getPcrqQ() {
		return pcrqQ;
	}

	public void setPcrqQ(Date pcrqQ) {
		this.pcrqQ = pcrqQ;
	}

	public Date getPcrqZ() {
		return pcrqZ;
	}

	public void setPcrqZ(Date pcrqZ) {
		this.pcrqZ = pcrqZ;
	}

	public Date getXdrqQ() {
		return xdrqQ;
	}

	public void setXdrqQ(Date xdrqQ) {
		this.xdrqQ = xdrqQ;
	}

	public Date getXdrqZ() {
		return xdrqZ;
	}

	public void setXdrqZ(Date xdrqZ) {
		this.xdrqZ = xdrqZ;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public Double getHwSl() {
		return hwSl;
	}

	public void setHwSl(Double hwSl) {
		this.hwSl = hwSl;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public String getFylbDm() {
		return fylbDm;
	}

	public void setFylbDm(String fylbDm) {
		this.fylbDm = fylbDm;
	}

	public Double getDzJe() {
		return dzJe;
	}

	public void setDzJe(Double dzJe) {
		this.dzJe = dzJe;
	}

	public Double getDzcyJe() {
		return dzcyJe;
	}

	public void setDzcyJe(Double dzcyJe) {
		this.dzcyJe = dzcyJe;
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

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public List<String> getYwDjxhs() {
		if (ywDjxhs == null) {
			ywDjxhs = new ArrayList<String>();
		}
		return ywDjxhs;
	}

	public void setYwDjxhs(List<String> ywDjxhs) {
		this.ywDjxhs = ywDjxhs;
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

	public List<XyjsPcHwxxDomain> getJsxxList() {
		if (jsxxList == null) {
			jsxxList = new ArrayList<XyjsPcHwxxDomain>();
		}
		return jsxxList;
	}

	public void setJsxxList(List<XyjsPcHwxxDomain> jsxxList) {
		this.jsxxList = jsxxList;
	}

	public List<XyjsPcHwxxDomain> getQdmxList() {
		if (qdmxList == null) {
			qdmxList = new ArrayList<XyjsPcHwxxDomain>();
		}
		return qdmxList;
	}

	public void setQdmxList(List<XyjsPcHwxxDomain> qdmxList) {
		this.qdmxList = qdmxList;
	}

	public String getZtStr() {
		if("1".equals(zt)){
			ztStr = "��ʼ";
		}else if("2".equals(zt)){
			ztStr = "�ѷ���";
		}else if("3".equals(zt)){
			ztStr = "��ȷ��";
		}else if("4".equals(zt)){
			ztStr = "�˻�";
		}
		return ztStr;
	}

	public void setZtStr(String ztStr) {
		this.ztStr = ztStr;
	}
}
