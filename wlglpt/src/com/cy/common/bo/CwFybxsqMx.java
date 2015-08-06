package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_FYBXSQ_MX is created by tools.
 * @author HJH
 */

public class CwFybxsqMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String xh;                               // ��ϸ���
	private String fylbCwDjxh;                       // �������_����Ǽ����
	private String fyxmCwDjxh;                       // ������Ŀ_����Ǽ����
	private Double fyje;                             // ���ý��
	private Double bxje;                             // �������
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)

	public CwFybxsqMx() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��ϸ���
	public String getXh() {
		return this.xh;
	}

	//������ϸ���
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ�������_����Ǽ����
	public String getFylbCwDjxh() {
		return this.fylbCwDjxh;
	}

	//���÷������_����Ǽ����
	public void setFylbCwDjxh(String fylbCwDjxh) {
		this.fylbCwDjxh=fylbCwDjxh;
	}

	//��ȡ������Ŀ_����Ǽ����
	public String getFyxmCwDjxh() {
		return this.fyxmCwDjxh;
	}

	//���÷�����Ŀ_����Ǽ����
	public void setFyxmCwDjxh(String fyxmCwDjxh) {
		this.fyxmCwDjxh=fyxmCwDjxh;
	}

	//��ȡ���ý��
	public Double getFyje() {
		return this.fyje;
	}

	//���÷��ý��
	public void setFyje(Double fyje) {
		this.fyje=fyje;
	}

	//��ȡ�������
	public Double getBxje() {
		return this.bxje;
	}

	//���ñ������
	public void setBxje(Double bxje) {
		this.bxje=bxje;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}
}