package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_QTSR is created by tools.
 * @author HJH
 */

public class CwQtsr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String rq;                               // ����
	private String xmmc;                             // ��Ŀ����
	private Double je;                               // ���
	private String zcflDm;                           // �ʲ��������
	private String yhCshDjxh;                        // ���г�ʼ���Ǽ����
	private String bz;                               // ��ע
	private String yxbz;
	private String djJgbm;
	private String fkf;
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public CwQtsr() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ����
	public String getRq() {
		return this.rq;
	}

	//��������
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ��Ŀ����
	public String getXmmc() {
		return this.xmmc;
	}

	//������Ŀ����
	public void setXmmc(String xmmc) {
		this.xmmc=xmmc;
	}

	//��ȡ���
	public Double getJe() {
		return this.je;
	}

	//���ý��
	public void setJe(Double je) {
		this.je=je;
	}

	//��ȡ�ʲ��������
	public String getZcflDm() {
		return this.zcflDm;
	}

	//�����ʲ��������
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//��ȡ���г�ʼ���Ǽ����
	public String getYhCshDjxh() {
		return this.yhCshDjxh;
	}

	//�������г�ʼ���Ǽ����
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh=yhCshDjxh;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
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

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getFkf() {
		return fkf;
	}

	public void setFkf(String fkf) {
		this.fkf = fkf;
	}
}