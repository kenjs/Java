package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For XYJS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class XyjsSrdzQd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String xyDjxh;                           // �ͻ��Ǽ����
	private String qdmc;                             // �嵥����
	private String fylbDm;
	private String dzqdHzfsDm;                       // �����嵥���ܷ�ʽ����
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
	
	public XyjsSrdzQd() {
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

	public String getFylbDm() {
		return fylbDm;
	}

	public void setFylbDm(String fylbDm) {
		this.fylbDm = fylbDm;
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
}