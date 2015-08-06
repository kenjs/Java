package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_XLWH is created by tools.
 * @author HJH
 */

public class QyXlwh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ssJgbm;                           // ��������
	private String sfdXzqhDm;                        // ʼ����_������������
	private String mddXzqhDm;                        // Ŀ�ĵ�_������������
	private Double lcs;                              // �����
	private Double ddts;                             // �ﵽ����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String fhrXzqhDm;
	private String shrXzqhDm;
	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public QyXlwh() {
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡʼ����_������������
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//����ʼ����_������������
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//��ȡĿ�ĵ�_������������
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//����Ŀ�ĵ�_������������
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//��ȡ�����
	public Double getLcs() {
		return this.lcs;
	}

	//���������
	public void setLcs(Double lcs) {
		this.lcs=lcs;
	}

	//��ȡ�ﵽ����
	public Double getDdts() {
		return this.ddts;
	}

	//���ôﵽ����
	public void setDdts(Double ddts) {
		this.ddts=ddts;
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