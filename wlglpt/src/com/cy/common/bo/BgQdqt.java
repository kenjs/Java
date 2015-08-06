package com.cy.common.bo;




import java.io.Serializable;

/**
 * The persistent class For BG_QDQT is created by tools.
 * @author HJH
 */

public class BgQdqt  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bgDjxh;                           // �칫�Ǽ����(SEQ_BG_DJXH)
	private String czyDjxh;                          // ����Ա�Ǽ����
	private String rq;                               // ����(YYYY-MM-DD)
	private String qdqtDm;                           // ǩ��ǩ�˴���
	private String yQdqtSj;                          // Ӧǩ��ǩ��ʱ��
	private String sjQdqtSj;                         // ʵ��ǩ��ǩ��ʱ��
	private String cdztbz;                           // �ٵ����˱�־(Y/N)
	private Double cdztsj;                           // �ٵ�����ʱ��(����)

	public BgQdqt() {
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

	//��ȡǩ��ǩ�˴���
	public String getQdqtDm() {
		return this.qdqtDm;
	}

	//����ǩ��ǩ�˴���
	public void setQdqtDm(String qdqtDm) {
		this.qdqtDm=qdqtDm;
	}

	//��ȡӦǩ��ǩ��ʱ��
	public String getYQdqtSj() {
		return this.yQdqtSj;
	}

	//����Ӧǩ��ǩ��ʱ��
	public void setYQdqtSj(String yQdqtSj) {
		this.yQdqtSj=yQdqtSj;
	}

	//��ȡʵ��ǩ��ǩ��ʱ��
	public String getSjQdqtSj() {
		return this.sjQdqtSj;
	}

	//����ʵ��ǩ��ǩ��ʱ��
	public void setSjQdqtSj(String sjQdqtSj) {
		this.sjQdqtSj=sjQdqtSj;
	}

	//��ȡ�ٵ����˱�־(Y/N)
	public String getCdztbz() {
		return this.cdztbz;
	}

	//���óٵ����˱�־(Y/N)
	public void setCdztbz(String cdztbz) {
		this.cdztbz=cdztbz;
	}

	//��ȡ�ٵ�����ʱ��(����)
	public Double getCdztsj() {
		return this.cdztsj;
	}

	//���óٵ�����ʱ��(����)
	public void setCdztsj(Double cdztsj) {
		this.cdztsj=cdztsj;
	}
}