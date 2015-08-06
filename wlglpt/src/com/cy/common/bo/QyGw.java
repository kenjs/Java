package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_GW is created by tools.
 * @author HJH
 */

public class QyGw  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gwDjxh;                           // ��λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	private String gwMc;                             // ��λ����
	private String gwJc;                             // ��λ���
	private String bzsm;                             // ��ע˵��
	private String lybz;                             // ��Դ��־(Y��ҵ����/N��λ����)
	private Long gwDm;                               // ��λ����
	private Long ssJgbm;                             // ������������(����)
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private Long cjrCzyDjxh;                         // ������
	private String cjrq;                             // ��������
	private Long xgrCzyDjxh;                         // �޸���
	private String xgrq;                             // �޸�����

	public QyGw() {
	}

	//��ȡ��λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//���ø�λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//��ȡ��λ����
	public String getGwMc() {
		return this.gwMc;
	}

	//���ø�λ����
	public void setGwMc(String gwMc) {
		this.gwMc=gwMc;
	}

	//��ȡ��λ���
	public String getGwJc() {
		return this.gwJc;
	}

	//���ø�λ���
	public void setGwJc(String gwJc) {
		this.gwJc=gwJc;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ��Դ��־(Y��ҵ����/N��λ����)
	public String getLybz() {
		return this.lybz;
	}

	//������Դ��־(Y��ҵ����/N��λ����)
	public void setLybz(String lybz) {
		this.lybz=lybz;
	}

	//��ȡ��λ����
	public Long getGwDm() {
		return this.gwDm;
	}

	//���ø�λ����
	public void setGwDm(Long gwDm) {
		this.gwDm=gwDm;
	}

	//��ȡ������������(����)
	public Long getSsJgbm() {
		return this.ssJgbm;
	}

	//����������������(����)
	public void setSsJgbm(Long ssJgbm) {
		this.ssJgbm=ssJgbm;
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
	public Long getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(Long cjrCzyDjxh) {
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
	public Long getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(Long xgrCzyDjxh) {
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