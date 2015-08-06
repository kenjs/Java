package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_ZLGX is created by tools.
 * @author HJH
 */

public class BgZlgx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zlgxDjxh;                         // ���Ϲ���Ǽ����(SEQ_BG_DJXH)
	private String jgbm;                             // ��������
	private String fbrq;                             // ��������(YYYY-MM-DD)
	private String ly;                               // ��Դ
	private String zlmc;                             // ��������
	private String sm;                               // ����˵��
	private String bcztDm;                           // ����״̬����
	private String xjgxbz;                           // �¼������־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgZlgx() {
	}

	//��ȡ���Ϲ���Ǽ����(SEQ_BG_DJXH)
	public String getZlgxDjxh() {
		return this.zlgxDjxh;
	}

	//�������Ϲ���Ǽ����(SEQ_BG_DJXH)
	public void setZlgxDjxh(String zlgxDjxh) {
		this.zlgxDjxh=zlgxDjxh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ��������(YYYY-MM-DD)
	public String getFbrq() {
		return this.fbrq;
	}

	//���÷�������(YYYY-MM-DD)
	public void setFbrq(String fbrq) {
		this.fbrq=fbrq;
	}

	//��ȡ��Դ
	public String getLy() {
		return this.ly;
	}

	//������Դ
	public void setLy(String ly) {
		this.ly=ly;
	}

	//��ȡ��������
	public String getZlmc() {
		return this.zlmc;
	}

	//������������
	public void setZlmc(String zlmc) {
		this.zlmc=zlmc;
	}

	//��ȡ����˵��
	public String getSm() {
		return this.sm;
	}

	//��������˵��
	public void setSm(String sm) {
		this.sm=sm;
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

	public String getBcztDm() {
		return bcztDm;
	}

	public void setBcztDm(String bcztDm) {
		this.bcztDm = bcztDm;
	}
}