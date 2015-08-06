package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_RCAP is created by tools.
 * @author HJH
 */

public class BgRcap  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bgDjxh;                           // �칫�Ǽ����(SEQ_BG_DJXH)
	private String czyDjxh;                          // ����Ա�Ǽ����
	private String rq;                               // ����(YYYY-MM-DD)
	private String nr;                               // ����
	private String dxFsxh;                           // ���ŷ������
	private String ckbz;                             // �鿴��־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public BgRcap() {
	}

	//��ȡ�칫�Ǽ����(SEQ_BG_DJXH)
	public String getBgDjxh() {
		return this.bgDjxh;
	}

	//���ð칫�Ǽ����(SEQ_BG_DJXH)
	public void setBgDjxh(String bgDjxh) {
		this.bgDjxh=bgDjxh;
	}

	//��ȡ����Ա�Ǽ����
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ����(YYYY-MM-DD)
	public String getRq() {
		return this.rq;
	}

	//��������(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ����
	public String getNr() {
		return this.nr;
	}

	//��������
	public void setNr(String nr) {
		this.nr=nr;
	}

	//��ȡ���ŷ������
	public String getDxFsxh() {
		return this.dxFsxh;
	}

	//���ö��ŷ������
	public void setDxFsxh(String dxFsxh) {
		this.dxFsxh=dxFsxh;
	}

	//��ȡ�鿴��־(Y/N)
	public String getCkbz() {
		return this.ckbz;
	}

	//���ò鿴��־(Y/N)
	public void setCkbz(String ckbz) {
		this.ckbz=ckbz;
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