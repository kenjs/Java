package com.cy.common.bo;
import java.io.Serializable;

/**
 * �����Ϊ  ����-�����ʲ�-�䶯��¼ cw��hbzc��bdjl
 * The persistent class For CW_HBZCYE2 is created by tools.
 * @author HJH
 */

public class CwHbzcye2  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwbdDjxh;                         // ����䶯�Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String zcflDm;                           // �ʲ��������
	private String yhCshDjxh;                        // ���г�ʼ���Ǽ����
	private Double je;                               // ���
	private String sm;                               // ˵��
	private String jbrCzyDjxh;                       // ������
	private String rq;                               // ����
	private String djJgbm;                           // ����
	private String bz;
	private String ywxh;
	private String yxbz;
	
	public CwHbzcye2() {
	}

	//��ȡ����䶯�Ǽ����(SEQ_CW_DJXH)
	public String getCwbdDjxh() {
		return this.cwbdDjxh;
	}

	//���ò���䶯�Ǽ����(SEQ_CW_DJXH)
	public void setCwbdDjxh(String cwbdDjxh) {
		this.cwbdDjxh=cwbdDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�ʲ��������
	public String getZcflDm() {
		return this.zcflDm;
	}

	//�����ʲ��������
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//��ȡ���г�ʼ���Ǽ����
	public String getYhCshDjxh() {
		return this.yhCshDjxh;
	}

	//�������г�ʼ���Ǽ����
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh=yhCshDjxh;
	}

	//��ȡ���
	public Double getJe() {
		return this.je;
	}

	//���ý��
	public void setJe(Double je) {
		this.je=je;
	}

	//��ȡ˵��
	public String getSm() {
		return this.sm;
	}

	//����˵��
	public void setSm(String sm) {
		this.sm=sm;
	}

	//��ȡ������
	public String getJbrCzyDjxh() {
		return this.jbrCzyDjxh;
	}

	//���þ�����
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh=jbrCzyDjxh;
	}

	//��ȡ����
	public String getRq() {
		return this.rq;
	}

	//��������
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ����
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���ò���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}