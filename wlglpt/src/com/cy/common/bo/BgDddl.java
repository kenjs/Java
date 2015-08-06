package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_DDDL is created by tools.
 * @author HJH
 */

public class BgDddl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dddlDjxh;                         // �����¼�Ǽ����(SEQ_BG_DJXH)
	private String jgbm;                             // ��������
	private String mc;                               // ����
	private String url;                              // ��ַ(������ַ)
	private String dlfsDm;                           // ��¼��ʽ����
	private String xjgxbz;                           // �¼������־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgDddl() {
	}

	//��ȡ�����¼�Ǽ����(SEQ_BG_DJXH)
	public String getDddlDjxh() {
		return this.dddlDjxh;
	}

	//���õ����¼�Ǽ����(SEQ_BG_DJXH)
	public void setDddlDjxh(String dddlDjxh) {
		this.dddlDjxh=dddlDjxh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ����
	public String getMc() {
		return this.mc;
	}

	//��������
	public void setMc(String mc) {
		this.mc=mc;
	}

	//��ȡ��ַ(������ַ)
	public String getUrl() {
		return this.url;
	}

	//������ַ(������ַ)
	public void setUrl(String url) {
		this.url=url;
	}

	//��ȡ��¼��ʽ����
	public String getDlfsDm() {
		return this.dlfsDm;
	}

	//���õ�¼��ʽ����
	public void setDlfsDm(String dlfsDm) {
		this.dlfsDm=dlfsDm;
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