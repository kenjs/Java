package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * The DOMAIN class FOR JS_KPSQ is created by tools.
 * @author HCM
 */

public class JsKpDzqdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqDjxh;                         // ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	private String khDjxh;                           // �ͻ��Ǽ����
	private String khmc;
	private Double sqKpjeHj;                         // ���뿪Ʊ���ϼ�
	private String sqKprq;                           // ���뿪Ʊ����
	private String fpkjbz;							 // ��Ʊ���߱�־
	private Double fpkjje;                           // ��Ʊ���߽��
	private String fpkjrq;                           // ��Ʊ��������
	private String ykpQdhx;                          // Ԥ��Ʊ�嵥������־(Y/N)
	private Double yhxje;                            // �Ѻ������
	private String djrCzyDjxh;                       //�Ǽ��˲���Ա�Ǽ����
	private String djrMc;                            //�Ǽ�������
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String djJgmc;                           // �Ǽǲ���
	private String ssJgbm;                           // ������λ
	private String ssJgmc;                           // ������λ
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<BaseBusinessDomain> yhxList; 		 //��ѯ�б�
	
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH) 
	private String qdmc;                             // �嵥����
	private Double heJe;                             // �ϼƽ��
	private Double ysqKpJe;                          // �����뿪Ʊ���
	private Double wsqKpJe;                          // δ���뿪Ʊ���
	private String queryName;                        //������ѯ��  qdDjxh+qdmc
	private String xhStr;                            //����qdDjxh��ɵ��ַ���
	
	private List<String> kpsqmxDjxhs;                //�������
	
	private String kpsqmxDjxh;                       // ��Ʊ����Ǽ����
	private String kpsqMxflDm;                       // ��Ʊ������ϸ�������
	private String ywDjxh;                           // �嵥�Ǽ����
	private Double sqKpje;                           // ���뿪Ʊ���
	private String bzsm;                             // ��ע˵��
	private String yxbz;                             // ��Ч��־(Y/N)
	
	
	private String djrqQ;
	private String djrqZ;
	
	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public List<BaseBusinessDomain> getYhxList() {
		if(yhxList==null){
			yhxList=new ArrayList<BaseBusinessDomain>();
		}
		return yhxList;
	}
	public void setYhxList(List<BaseBusinessDomain> yhxList) {
		this.yhxList = yhxList;
	}
	public String getKpsqDjxh() {
		return kpsqDjxh;
	}
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh = kpsqDjxh;
	}
	public String getKhDjxh() {
		return khDjxh;
	}
	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}
	public Double getSqKpjeHj() {
		return sqKpjeHj;
	}
	public void setSqKpjeHj(Double sqKpjeHj) {
		this.sqKpjeHj = sqKpjeHj;
	}
	public String getSqKprq() {
		return sqKprq;
	}
	public void setSqKprq(String sqKprq) {
		this.sqKprq = sqKprq;
	}
	public String getFpkjbz() {
		return fpkjbz;
	}
	public void setFpkjbz(String fpkjbz) {
		this.fpkjbz = fpkjbz;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public Double getFpkjje() {
		return fpkjje;
	}
	public void setFpkjje(Double fpkjje) {
		this.fpkjje = fpkjje;
	}
	public String getFpkjrq() {
		return fpkjrq;
	}
	public void setFpkjrq(String fpkjrq) {
		this.fpkjrq = fpkjrq;
	}
	public String getYkpQdhx() {
		return ykpQdhx;
	}
	public void setYkpQdhx(String ykpQdhx) {
		this.ykpQdhx = ykpQdhx;
	}
	public Double getYhxje() {
		return yhxje;
	}
	public void setYhxje(Double yhxje) {
		this.yhxje = yhxje;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getDjrMc() {
		return djrMc;
	}
	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
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
	public String getDjrqQ() {
		return djrqQ;
	}
	public void setDjrqQ(String djrqQ) {
		this.djrqQ = djrqQ;
	}
	public String getDjrqZ() {
		return djrqZ;
	}
	public void setDjrqZ(String djrqZ) {
		this.djrqZ = djrqZ;
	}
	public String getQdDjxh() {
		return qdDjxh;
	}
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh = qdDjxh;
	}
	public String getQdmc() {
		return qdmc;
	}
	public void setQdmc(String qdmc) {
		this.qdmc = qdmc;
	}
	public Double getHeJe() {
		return heJe;
	}
	public void setHeJe(Double heJe) {
		this.heJe = heJe;
	}
	public Double getYsqKpJe() {
		return ysqKpJe;
	}
	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}
	public Double getWsqKpJe() {
		return wsqKpJe;
	}
	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getXhStr() {
		return xhStr;
	}
	public void setXhStr(String xhStr) {
		this.xhStr = xhStr;
	}
	public Double getSqKpje() {
		return sqKpje;
	}
	public void setSqKpje(Double sqKpje) {
		this.sqKpje = sqKpje;
	}
	public String getBzsm() {
		return bzsm;
	}
	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}
	public String getKpsqmxDjxh() {
		return kpsqmxDjxh;
	}
	public void setKpsqmxDjxh(String kpsqmxDjxh) {
		this.kpsqmxDjxh = kpsqmxDjxh;
	}
	public String getKpsqMxflDm() {
		return kpsqMxflDm;
	}
	public void setKpsqMxflDm(String kpsqMxflDm) {
		this.kpsqMxflDm = kpsqMxflDm;
	}
	public String getYwDjxh() {
		return ywDjxh;
	}
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public List<String> getKpsqmxDjxhs() {
		return kpsqmxDjxhs;
	}
	public void setKpsqmxDjxhs(List<String> kpsqmxDjxhs) {
		this.kpsqmxDjxhs = kpsqmxDjxhs;
	}
	
}
