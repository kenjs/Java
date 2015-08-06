package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_WSDYSZ is created by tools.
 * @author HJH
 */

public class QyWsdysz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                             // ά�����(SEQ_ZY_DJXH)
	private String ssJgbm;                           // ��������(��ǰ������˾)
	private String wsDm;                             // ������룬1001:���˵�
	private Double leftMargin;                       // ��߾�
	private Double topMargin;                        // �ϱ߾�
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public QyWsdysz() {
	}

	//��ȡά�����(SEQ_ZY_DJXH)
	public String getWhXh() {
		return this.whXh;
	}

	//����ά�����(SEQ_ZY_DJXH)
	public void setWhXh(String whXh) {
		this.whXh=whXh;
	}

	//��ȡ��������(��ǰ������˾)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������(��ǰ������˾)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ������룬1001:���˵�
	public String getWsDm() {
		return this.wsDm;
	}

	//����������룬1001:���˵�
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//��ȡ��߾�
	public Double getLeftMargin() {
		return this.leftMargin;
	}

	//������߾�
	public void setLeftMargin(Double leftMargin) {
		this.leftMargin=leftMargin;
	}

	//��ȡ�ϱ߾�
	public Double getTopMargin() {
		return this.topMargin;
	}

	//�����ϱ߾�
	public void setTopMargin(Double topMargin) {
		this.topMargin=topMargin;
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