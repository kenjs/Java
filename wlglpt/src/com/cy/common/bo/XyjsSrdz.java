package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For XYJS_SRDZ is created by tools.
 * @author HJH
 */

public class XyjsSrdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // ���ʵǼ����(SEQ_DZ_DJXH)
	private String ywDjxh;                           // ����Ǽ����
	private String ywMxXh;                           // ҵ����ϸ���
	private String ssJgbm;                           // ��������
	private Double jsJe;                             // ����_δ��
	private Double dzje;                             // 
	private String dzrCzyDjxh;                       // ������
	private Date dzrq;                             // ��������
	private String dzJgbm;                           // ���ʲ���
	private String yxbz;                             // ��Ч��־(Y/N)
	private String dzCybz;                           // ���ʲ����־(Y/N)
	private Double dzcyje;                           // ���ʲ�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String qdDjxh;                           // �嵥�Ǽ����
	private String bz;

	public XyjsSrdz() {
	}

	//��ȡ���ʵǼ����(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//���ö��ʵǼ����(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//��ȡ����Ǽ����
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//���ý���Ǽ����
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//��ȡҵ����ϸ���
	public String getYwMxXh() {
		return this.ywMxXh;
	}

	//����ҵ����ϸ���
	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh=ywMxXh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ����_δ��
	public Double getJsJe() {
		return this.jsJe;
	}

	//���ý���_δ��
	public void setJsJe(Double jsJe) {
		this.jsJe=jsJe;
	}

	//��ȡ
	public Double getDzje() {
		return this.dzje;
	}

	//����
	public void setDzje(Double dzje) {
		this.dzje=dzje;
	}

	//��ȡ������
	public String getDzrCzyDjxh() {
		return this.dzrCzyDjxh;
	}

	//���ö�����
	public void setDzrCzyDjxh(String dzrCzyDjxh) {
		this.dzrCzyDjxh=dzrCzyDjxh;
	}

	//��ȡ��������
	public Date getDzrq() {
		return this.dzrq;
	}

	//���ö�������
	public void setDzrq(Date dzrq) {
		this.dzrq=dzrq;
	}

	//��ȡ���ʲ���
	public String getDzJgbm() {
		return this.dzJgbm;
	}

	//���ö��ʲ���
	public void setDzJgbm(String dzJgbm) {
		this.dzJgbm=dzJgbm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ���ʲ����־(Y/N)
	public String getDzCybz() {
		return this.dzCybz;
	}

	//���ö��ʲ����־(Y/N)
	public void setDzCybz(String dzCybz) {
		this.dzCybz=dzCybz;
	}

	//��ȡ���ʲ�����
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//���ö��ʲ�����
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
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

	//��ȡ�嵥�Ǽ����
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//�����嵥�Ǽ����
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}