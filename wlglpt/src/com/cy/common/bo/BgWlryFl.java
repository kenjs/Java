package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_WLRY_FL is created by tools.
 * @author HJH
 */

public class BgWlryFl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlryFlxh;                         // ������Ա�������(SEQ_WLRY_FLXH)
	private String jgbm;                             // ��������
	private String flmc;                             // ��������
	private String xjgxbz;                           // �¼������־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgWlryFl() {
	}

	//��ȡ������Ա�������(SEQ_WLRY_FLXH)
	public String getWlryFlxh() {
		return this.wlryFlxh;
	}

	//����������Ա�������(SEQ_WLRY_FLXH)
	public void setWlryFlxh(String wlryFlxh) {
		this.wlryFlxh=wlryFlxh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ��������
	public String getFlmc() {
		return this.flmc;
	}

	//���÷�������
	public void setFlmc(String flmc) {
		this.flmc=flmc;
	}

	//��ȡ�¼������־(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//�����¼������־(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
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