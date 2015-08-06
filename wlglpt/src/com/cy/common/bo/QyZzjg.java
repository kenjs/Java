package com.cy.common.bo;




import java.io.Serializable;

/**
 * The persistent class For QY_ZZJG is created by tools.
 * @author HJH
 */

public class QyZzjg  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������(SEQ_JG_DJXH)
	private String mc;                               // ����
	private String jc;                               // ���
	private String jglbDm;                           // ����������
	private String jcbm;                             // ���α���
	private String jbdm;                             // �������
	private String sjJgbm;                           // �ϼ���������
	private String qyZcxh;                           // ��ҵע�����(�ܹ�˾ʱ����Ϊ��)
	private String dz;                               // ��ַ
	private String dh;                               // �绰
	private String yb;                               // �ʱ�
	private String fzr;                              // ������
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String pyjx;                             // ƴ����д
	private String pyqp;                             // ƴ��ȫƴ

	public QyZzjg() {
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������(SEQ_JG_DJXH)
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

	//��ȡ���
	public String getJc() {
		return this.jc;
	}

	//���ü��
	public void setJc(String jc) {
		this.jc=jc;
	}

	//��ȡ����������
	public String getJglbDm() {
		return this.jglbDm;
	}

	//���û���������
	public void setJglbDm(String jglbDm) {
		this.jglbDm=jglbDm;
	}

	//��ȡ���α���
	public String getJcbm() {
		return this.jcbm;
	}

	//���ü��α���
	public void setJcbm(String jcbm) {
		this.jcbm=jcbm;
	}

	//��ȡ�������
	public String getJbdm() {
		return this.jbdm;
	}

	//���ü������
	public void setJbdm(String jbdm) {
		this.jbdm=jbdm;
	}

	//��ȡ�ϼ���������
	public String getSjJgbm() {
		return this.sjJgbm;
	}

	//�����ϼ���������
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm=sjJgbm;
	}

	//��ȡ��ҵע�����(�ܹ�˾ʱ����Ϊ��)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//������ҵע�����(�ܹ�˾ʱ����Ϊ��)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//��ȡ��ַ
	public String getDz() {
		return this.dz;
	}

	//���õ�ַ
	public void setDz(String dz) {
		this.dz=dz;
	}

	//��ȡ�绰
	public String getDh() {
		return this.dh;
	}

	//���õ绰
	public void setDh(String dh) {
		this.dh=dh;
	}

	//��ȡ�ʱ�
	public String getYb() {
		return this.yb;
	}

	//�����ʱ�
	public void setYb(String yb) {
		this.yb=yb;
	}

	//��ȡ������
	public String getFzr() {
		return this.fzr;
	}

	//���ø�����
	public void setFzr(String fzr) {
		this.fzr=fzr;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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

	//��ȡ
	public String getPyjx() {
		return this.pyjx;
	}

	//����
	public void setPyjx(String pyjx) {
		this.pyjx=pyjx;
	}

	//��ȡ
	public String getPyqp() {
		return this.pyqp;
	}

	//����
	public void setPyqp(String pyqp) {
		this.pyqp=pyqp;
	}
}