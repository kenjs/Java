package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_PC_ZPAJ is created by tools.
 * @author HJH
 */

public class HyPcZpaj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ajDjxh;                           // 
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private byte[] ajzp;                             // �ɳ�����
	private String bz;                               // ��ע
	private String pcrCzyDjxh;                       // �ɳ���
	private String pcrq;                             // �ɳ�����
	private String pcJgbm;                           // �ɳ�����
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	
	private String zpdz;                             //��Ƭ��ַ

	public String getZpdz() {
		return zpdz;
	}

	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}

	public HyPcZpaj() {
	}

	//��ȡ
	public String getAjDjxh() {
		return this.ajDjxh;
	}

	//����
	public void setAjDjxh(String ajDjxh) {
		this.ajDjxh=ajDjxh;
	}

	//��ȡ�ɳ��Ǽ����(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ�����
	public byte[] getAjzp() {
		return this.ajzp;
	}

	//�����ɳ�����
	public void setAjzp(byte[] ajzp) {
		this.ajzp=ajzp;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�ɳ���
	public String getPcrCzyDjxh() {
		return this.pcrCzyDjxh;
	}

	//�����ɳ���
	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh=pcrCzyDjxh;
	}

	//��ȡ�ɳ�����
	public String getPcrq() {
		return this.pcrq;
	}

	//�����ɳ�����
	public void setPcrq(String pcrq) {
		this.pcrq=pcrq;
	}

	//��ȡ�ɳ�����
	public String getPcJgbm() {
		return this.pcJgbm;
	}

	//�����ɳ�����
	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm=pcJgbm;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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