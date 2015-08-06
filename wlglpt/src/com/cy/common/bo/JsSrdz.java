package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ is created by tools.
 * @author HJH
 */

public class JsSrdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // ���ʵǼ����(SEQ_DZ_DJXH)
	private String dzfsDm;                           // ���ʷ�ʽ����
	private String jsDjxh;                           // ����Ǽ����
	private String ssJgbm;                           // ��������
	private Double jsSr;                             // ����_����
	private Double jsYj;                             // ����_�ѽ�
	private Double jsWj;                             // ����_δ��
	private Double dzje;							 // ���˽��
	private String dzrCzyDjxh;                       // ������
	private String dzrq;                             // ��������
	private String dzJgbm;                           // ���ʲ���
	private String yxbz;                             // ��Ч��־(Y/N)
	private String dzCybz;                           // ���ʲ����־(Y/N)
	private Double dzcyje;                           // ���ʲ�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String qdDjxh;                           // �嵥�Ǽ����

	public JsSrdz() {
	}

	//��ȡ���ʵǼ����(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//���ö��ʵǼ����(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//��ȡ���ʷ�ʽ����
	public String getDzfsDm() {
		return this.dzfsDm;
	}

	//���ö��ʷ�ʽ����
	public void setDzfsDm(String dzfsDm) {
		this.dzfsDm=dzfsDm;
	}

	//��ȡ����Ǽ����
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//���ý���Ǽ����
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ����_����
	public Double getJsSr() {
		return this.jsSr;
	}

	//���ý���_����
	public void setJsSr(Double jsSr) {
		this.jsSr=jsSr;
	}

	//��ȡ����_�ѽ�
	public Double getJsYj() {
		return this.jsYj;
	}

	//���ý���_�ѽ�
	public void setJsYj(Double jsYj) {
		this.jsYj=jsYj;
	}

	//��ȡ����_δ��
	public Double getJsWj() {
		return this.jsWj;
	}

	//���ý���_δ��
	public void setJsWj(Double jsWj) {
		this.jsWj=jsWj;
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
	public String getDzrq() {
		return this.dzrq;
	}

	//���ö�������
	public void setDzrq(String dzrq) {
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

	public Double getDzje() {
		return dzje;
	}

	public void setDzje(Double dzje) {
		this.dzje = dzje;
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
}