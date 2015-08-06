package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_FYXM_WH is created by tools.
 * @author HJH
 */

public class CwFyxmWh  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������(�ܹ�˾)
	private String fylbCwDjxh;                       // �������_����Ǽ����
	private String fyxmMc;                           // ������Ŀ����
	private String splcXmflDjxh;                     // ��������_��Ŀ����Ǽ����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public CwFyxmWh() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��������(�ܹ�˾)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������(�ܹ�˾)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�������_����Ǽ����
	public String getFylbCwDjxh() {
		return this.fylbCwDjxh;
	}

	//���÷������_����Ǽ����
	public void setFylbCwDjxh(String fylbCwDjxh) {
		this.fylbCwDjxh=fylbCwDjxh;
	}

	//��ȡ������Ŀ����
	public String getFyxmMc() {
		return this.fyxmMc;
	}

	//���÷�����Ŀ����
	public void setFyxmMc(String fyxmMc) {
		this.fyxmMc=fyxmMc;
	}

	//��ȡ��������_��Ŀ����Ǽ����
	public String getSplcXmflDjxh() {
		return this.splcXmflDjxh;
	}

	//������������_��Ŀ����Ǽ����
	public void setSplcXmflDjxh(String splcXmflDjxh) {
		this.splcXmflDjxh=splcXmflDjxh;
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