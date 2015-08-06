package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SWDN_DSH is created by tools.
 * @author HJH
 */

public class QySwdnDsh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ggDjxh;                           // �����Ǽ����(SEQ_GG_DJXH)
	private Long qyZcxh;                             // ��ҵע�����(GL_QYZC.QY_ZCXH)
	private String mac;                              // MAC��ַ
	private String bzsm;                             // ��ע˵��
	private String czyDjxh;                            // ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	private String sqrCzyDjxh;                         // ������
	private String sqrq;                             // ��������
	private String shrCzyDjxh;                         // �����
	private String shrq;                             // �������
	private String shjg;                             // ��˽��(1 ͬ�� ,2 ��ͬ��)
	
	private String qybm;

	public QySwdnDsh() {
	}

	//��ȡ�����Ǽ����(SEQ_GG_DJXH)
	public Long getGgDjxh() {
		return this.ggDjxh;
	}

	//���ù����Ǽ����(SEQ_GG_DJXH)
	public void setGgDjxh(Long ggDjxh) {
		this.ggDjxh=ggDjxh;
	}

	//��ȡ��ҵע�����(GL_QYZC.QY_ZCXH)
	public Long getQyZcxh() {
		return this.qyZcxh;
	}

	//������ҵע�����(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(Long qyZcxh) {
		this.qyZcxh=qyZcxh;
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

	//��ȡ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ������
	public String getSqrCzyDjxh() {
		return this.sqrCzyDjxh;
	}

	//����������
	public void setSqrCzyDjxh(String sqrCzyDjxh) {
		this.sqrCzyDjxh=sqrCzyDjxh;
	}

	//��ȡ��������
	public String getSqrq() {
		return this.sqrq;
	}

	//������������
	public void setSqrq(String sqrq) {
		this.sqrq=sqrq;
	}

	//��ȡ�����
	public String getShrCzyDjxh() {
		return this.shrCzyDjxh;
	}

	//���������
	public void setShrCzyDjxh(String shrCzyDjxh) {
		this.shrCzyDjxh=shrCzyDjxh;
	}

	//��ȡ�������
	public String getShrq() {
		return this.shrq;
	}

	//�����������
	public void setShrq(String shrq) {
		this.shrq=shrq;
	}

	//��ȡ��˽��(1 ͬ�� ,2 ��ͬ��)
	public String getShjg() {
		return this.shjg;
	}

	//������˽��(1 ͬ�� ,2 ��ͬ��)
	public void setShjg(String shjg) {
		this.shjg=shjg;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}
}