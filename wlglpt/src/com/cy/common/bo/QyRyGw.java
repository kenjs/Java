package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_RY_GW is created by tools.
 * @author HJH
 */

public class QyRyGw  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;                          // ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	private String gwDjxh;                           // ��λ�Ǽ����
	private String ssJgbm;                           // ������������(����)
	private String zjbz;                             // �����־(Y��Ҫ����/N��ְ����)
	private String qxJgbm;                           // Ȩ�޻�������(����Ȩ��)

	public QyRyGw() {
	}

	//��ȡ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ��λ�Ǽ����
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//���ø�λ�Ǽ����
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//��ȡ������������(����)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//����������������(����)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�����־(Y��Ҫ����/N��ְ����)
	public String getZjbz() {
		return this.zjbz;
	}

	//���������־(Y��Ҫ����/N��ְ����)
	public void setZjbz(String zjbz) {
		this.zjbz=zjbz;
	}

	//��ȡȨ�޻�������(����Ȩ��)
	public String getQxJgbm() {
		return this.qxJgbm;
	}

	//����Ȩ�޻�������(����Ȩ��)
	public void setQxJgbm(String qxJgbm) {
		this.qxJgbm=qxJgbm;
	}
}