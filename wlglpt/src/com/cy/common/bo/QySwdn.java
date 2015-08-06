package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SWDN is created by tools.
 * @author HaoY
 */

public class QySwdn  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ggDjxh;                           // �����Ǽ����(SEQ_GG_DJXH)
	private String qyZcxh;                           // ��ҵע�����(GL_QYZC.QY_ZCXH)
	private String czyDjxh;                          // ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	private String mac;                              // MAC��ַ
	private String bzsm;                             // ��ע˵��
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public QySwdn() {
	}

	//��ȡ�����Ǽ����(SEQ_GG_DJXH)
	public String getGgDjxh() {
		return this.ggDjxh;
	}

	//���ù����Ǽ����(SEQ_GG_DJXH)
	public void setGgDjxh(String ggDjxh) {
		this.ggDjxh=ggDjxh;
	}

	//��ȡ��ҵע�����(GL_QYZC.QY_ZCXH)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//������ҵע�����(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//��ȡ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡMAC��ַ
	public String getMac() {
		return this.mac;
	}

	//����MAC��ַ
	public void setMac(String mac) {
		this.mac=mac;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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
	public String getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(String cjrq) {
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
	public String getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}