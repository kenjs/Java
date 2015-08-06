package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class JsSrdzQd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String khDjxh;                           // �ͻ��Ǽ����
	private String qdmc;                             // �嵥����
	private String dzqdHzfsDm;                       // �����嵥���ܷ�ʽ����
	private Double heJe;                             // �ϼƽ��
	private Double yfJe;                             // �Ѹ����
	private Double wfJe;                             // δ�����
	private Double ysqKpJe;                             // �����뿪Ʊ���
	private Double wsqKpJe;                             // δ���뿪Ʊ���
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String sfKpBz;                           //�Ƿ�Ʊ��־(Y/N)
	private String jsdw;							//���㵥λ
	public JsSrdzQd() {
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
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
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

	//��ȡ�Ѹ����
	public Double getYfJe() {
		return this.yfJe;
	}

	//�����Ѹ����
	public void setYfJe(Double yfJe) {
		this.yfJe=yfJe;
	}

	//��ȡδ�����
	public Double getWfJe() {
		return this.wfJe;
	}

	//����δ�����
	public void setWfJe(Double wfJe) {
		this.wfJe=wfJe;
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
	public String getDjrq() {
		return this.djrq;
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
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

	public Double getWsqKpJe() {
		return wsqKpJe;
	}

	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}

	public Double getYsqKpJe() {
		return ysqKpJe;
	}

	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}

	public String getSfKpBz() {
		return sfKpBz;
	}

	public void setSfKpBz(String sfKpBz) {
		this.sfKpBz = sfKpBz;
	}

	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}
}