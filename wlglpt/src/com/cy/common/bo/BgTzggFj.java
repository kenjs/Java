package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_TZGG_FJ is created by tools.
 * @author HJH
 */

public class BgTzggFj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // ֪ͨ�������
	private String xh;                               // ���
	private String fjmc;                             // ��������
	private byte[] fjnr;                             // ��������
	private String yxbz;                             // ��Ч��־(Y/N)

	public BgTzggFj() {
	}

	//��ȡ֪ͨ�������
	public String getTzggXh() {
		return this.tzggXh;
	}

	//����֪ͨ�������
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//��ȡ���
	public String getXh() {
		return this.xh;
	}

	//�������
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ��������
	public String getFjmc() {
		return this.fjmc;
	}

	//���ø�������
	public void setFjmc(String fjmc) {
		this.fjmc=fjmc;
	}

	//��ȡ��������
	public byte[] getFjnr() {
		return this.fjnr;
	}

	//���ø�������
	public void setFjnr(byte[] fjnr) {
		this.fjnr=fjnr;
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