package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_FYDJ is created by tools.
 * @author HJH
 */

public class HyFydj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String fyDjxh;                           // ������Ǽ����(SEQ_DJS_DJXH)
	private String ssJgbm;                           // ��������
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private Double fyhj;                             // ���úϼ�
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String djJgbm;                           // �Ǽǲ���
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public HyFydj() {
	}

	//��ȡ������Ǽ����(SEQ_DJS_DJXH)
	public String getFyDjxh() {
		return this.fyDjxh;
	}

	//���ô�����Ǽ����(SEQ_DJS_DJXH)
	public void setFyDjxh(String fyDjxh) {
		this.fyDjxh=fyDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�ɳ��Ǽ����(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ���úϼ�
	public Double getFyhj() {
		return this.fyhj;
	}

	//���÷��úϼ�
	public void setFyhj(Double fyhj) {
		this.fyhj=fyhj;
	}

	//��ȡ��Ҫ������־(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//������Ҫ������־(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//��ȡ��������״̬����
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//������������״̬����
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//��ȡ�����������
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//���������������
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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