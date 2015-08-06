package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZSJ is created by tools.
 * @author HJH
 */

public class BgGzsj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������
	private String yxqQ;                             // ��Ч����
	private String yxqZ;                             // ��Ч��ֹ
	private String amSbsjS;                          // �����ϰ�ʱ��-ʱ
	private String amSbsjF;                          // �����ϰ�ʱ��-��
	private String pmSbsjS;                          // �����°�ʱ��-ʱ
	private String pmSbsjF;                          // �����°�ʱ��-��
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgGzsj() {
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ��Ч����
	public String getYxqQ() {
		return this.yxqQ;
	}

	//������Ч����
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//��ȡ��Ч��ֹ
	public String getYxqZ() {
		return this.yxqZ;
	}

	//������Ч��ֹ
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
	}

	//��ȡ�����ϰ�ʱ��-ʱ
	public String getAmSbsjS() {
		return this.amSbsjS;
	}

	//���������ϰ�ʱ��-ʱ
	public void setAmSbsjS(String amSbsjS) {
		this.amSbsjS=amSbsjS;
	}

	//��ȡ�����ϰ�ʱ��-��
	public String getAmSbsjF() {
		return this.amSbsjF;
	}

	//���������ϰ�ʱ��-��
	public void setAmSbsjF(String amSbsjF) {
		this.amSbsjF=amSbsjF;
	}

	//��ȡ�����°�ʱ��-ʱ
	public String getPmSbsjS() {
		return this.pmSbsjS;
	}

	//���������°�ʱ��-ʱ
	public void setPmSbsjS(String pmSbsjS) {
		this.pmSbsjS=pmSbsjS;
	}

	//��ȡ�����°�ʱ��-��
	public String getPmSbsjF() {
		return this.pmSbsjF;
	}

	//���������°�ʱ��-��
	public void setPmSbsjF(String pmSbsjF) {
		this.pmSbsjF=pmSbsjF;
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