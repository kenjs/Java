package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_SPWS is created by tools.
 * @author HJH
 */

public class QySpws  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������
	private String wsDm;                             // �������
	private String xmflbz;                           // ��Ŀ�����־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String wsspmsDm;
	
	public QySpws() {
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ�������
	public String getWsDm() {
		return this.wsDm;
	}

	//�����������
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//��ȡ��Ŀ�����־(Y/N)
	public String getXmflbz() {
		return this.xmflbz;
	}

	//������Ŀ�����־(Y/N)
	public void setXmflbz(String xmflbz) {
		this.xmflbz=xmflbz;
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

	public String getWsspmsDm() {
		return wsspmsDm;
	}

	public void setWsspmsDm(String wsspmsDm) {
		this.wsspmsDm = wsspmsDm;
	}
}