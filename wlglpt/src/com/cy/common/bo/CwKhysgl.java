package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_KHYSGL is created by tools.
 * @author HJH
 */

public class CwKhysgl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String srDjxh;                           // ����Ǽ����(SEQ_SR_DJXH)
	private String khDjxh;                           // Ӧ��Ӧ���Ǽ����
	private String je;                               // �˷ѽ��㷽����
	private String yxbz;                             // �˷ѽ��㷽�Ǽ����
	private String djrDjxh;                          // �������
	private String djrq;                             // 
	private String djJgbm;                           // ����
	private String ssSsjg;                           // 
	private String xgrDjxh;                          // 
	private String xgrq;                             // 
	private String khMc;
	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public CwKhysgl() {
	}

	//��ȡ����Ǽ����(SEQ_SR_DJXH)
	public String getSrDjxh() {
		return this.srDjxh;
	}

	//��������Ǽ����(SEQ_SR_DJXH)
	public void setSrDjxh(String srDjxh) {
		this.srDjxh=srDjxh;
	}

	//��ȡӦ��Ӧ���Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//����Ӧ��Ӧ���Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ�˷ѽ��㷽����
	

	//��ȡ�˷ѽ��㷽�Ǽ����
	public String getYxbz() {
		return this.yxbz;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	//�����˷ѽ��㷽�Ǽ����
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�������
	public String getDjrDjxh() {
		return this.djrDjxh;
	}

	//���ø������
	public void setDjrDjxh(String djrDjxh) {
		this.djrDjxh=djrDjxh;
	}

	//��ȡ
	public String getDjrq() {
		return this.djrq;
	}

	//����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ����
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//��������
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ
	public String getSsSsjg() {
		return this.ssSsjg;
	}

	//����
	public void setSsSsjg(String ssSsjg) {
		this.ssSsjg=ssSsjg;
	}

	//��ȡ
	public String getXgrDjxh() {
		return this.xgrDjxh;
	}

	//����
	public void setXgrDjxh(String xgrDjxh) {
		this.xgrDjxh=xgrDjxh;
	}

	//��ȡ
	public String getXgrq() {
		return this.xgrq;
	}

	//����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}