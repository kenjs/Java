package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_ZLGX_FJ is created by tools.
 * @author HJH
 */

public class BgZlgxFj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zlgxDjxh;                         // ���Ϲ���Ǽ����(SEQ_BG_DJXH)
	private String xh;                               // ���
	private String fjmc;                             // ��������
	private byte[] fjnr;                             // ��������
	private String yxbz;                             // ��Ч��־(Y/N)

	public BgZlgxFj() {
	}

	//��ȡ���Ϲ���Ǽ����(SEQ_BG_DJXH)
	public String getZlgxDjxh() {
		return this.zlgxDjxh;
	}

	//�������Ϲ���Ǽ����(SEQ_BG_DJXH)
	public void setZlgxDjxh(String zlgxDjxh) {
		this.zlgxDjxh=zlgxDjxh;
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