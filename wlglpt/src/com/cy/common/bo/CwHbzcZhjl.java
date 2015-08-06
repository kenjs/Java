package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_HBZC_ZHJL is created by tools.
 * @author HJH
 */

public class CwHbzcZhjl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String oldZcflDm;                        // ԭ-�ʲ��������
	private String oldYhCshDjxh;                     // ԭ-���г�ʼ���Ǽ����
	private String newZcflDm;                        // Ŀ��-�ʲ��������
	private String newYhCshDjxh;                     // Ŀ��-���г�ʼ���Ǽ����
	private Double zhje;                             // ת�����
	private String pzh;                              // ƾ֤��
	private String bzsm;                             // ��ע˵��
	private String yxbz;                             // ��Ч��־(Y/N)
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���

	public CwHbzcZhjl() {
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

	//��ȡԭ-�ʲ��������
	public String getOldZcflDm() {
		return this.oldZcflDm;
	}

	//����ԭ-�ʲ��������
	public void setOldZcflDm(String oldZcflDm) {
		this.oldZcflDm=oldZcflDm;
	}

	//��ȡԭ-���г�ʼ���Ǽ����
	public String getOldYhCshDjxh() {
		return this.oldYhCshDjxh;
	}

	//����ԭ-���г�ʼ���Ǽ����
	public void setOldYhCshDjxh(String oldYhCshDjxh) {
		this.oldYhCshDjxh=oldYhCshDjxh;
	}

	//��ȡĿ��-�ʲ��������
	public String getNewZcflDm() {
		return this.newZcflDm;
	}

	//����Ŀ��-�ʲ��������
	public void setNewZcflDm(String newZcflDm) {
		this.newZcflDm=newZcflDm;
	}

	//��ȡĿ��-���г�ʼ���Ǽ����
	public String getNewYhCshDjxh() {
		return this.newYhCshDjxh;
	}

	//����Ŀ��-���г�ʼ���Ǽ����
	public void setNewYhCshDjxh(String newYhCshDjxh) {
		this.newYhCshDjxh=newYhCshDjxh;
	}

	//��ȡת�����
	public Double getZhje() {
		return this.zhje;
	}

	//����ת�����
	public void setZhje(Double zhje) {
		this.zhje=zhje;
	}

	//��ȡƾ֤��
	public String getPzh() {
		return this.pzh;
	}

	//����ƾ֤��
	public void setPzh(String pzh) {
		this.pzh=pzh;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		return this.djrq;
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}
}