package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_SRDZ_DZCY_TEMP is created by tools.
 * @author HJH
 */

public class JsSrdzDzcyTemp  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // ���˵Ǽ����
	private String xh;                               // ���
	private Double dzcyje;                           // ���ʲ�����
	private String dzcyyyDm;                         // ���ʲ���ԭ�����
	private String dzcyClfsDm;                       // ���ʲ��촦��ʽ����
	private String bz;                               // ��ע˵��
	//private String preJsDjxh;                        // ���ν���Ǽ����
	private String xcJsDjxh;//�´�_����Ǽ����
	private String wlssDjxh;                         // ������ʧ�Ǽ����
	private String rq;                               // ����

	public JsSrdzDzcyTemp() {
	}

	//��ȡ���
	public String getXh() {
		return this.xh;
	}

	//�������
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ���ʲ�����
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//���ö��ʲ�����
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
	}

	//��ȡ���ʲ���ԭ�����
	public String getDzcyyyDm() {
		return this.dzcyyyDm;
	}

	//���ö��ʲ���ԭ�����
	public void setDzcyyyDm(String dzcyyyDm) {
		this.dzcyyyDm=dzcyyyDm;
	}

	//��ȡ���ʲ��촦��ʽ����
	public String getDzcyClfsDm() {
		return this.dzcyClfsDm;
	}

	//���ö��ʲ��촦��ʽ����
	public void setDzcyClfsDm(String dzcyClfsDm) {
		this.dzcyClfsDm=dzcyClfsDm;
	}

	//��ȡ��ע˵��
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע˵��
	public void setBz(String bz) {
		this.bz=bz;
	}

/*	//��ȡ���ν���Ǽ����
	public String getPreJsDjxh() {
		return this.preJsDjxh;
	}

	//���ö��ν���Ǽ����
	public void setPreJsDjxh(String preJsDjxh) {
		this.preJsDjxh=preJsDjxh;
	}*/

	//��ȡ������ʧ�Ǽ����
	public String getWlssDjxh() {
		return this.wlssDjxh;
	}

	//����������ʧ�Ǽ����
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh=wlssDjxh;
	}

	//��ȡ����
	public String getRq() {
		return this.rq;
	}

	//��������
	public void setRq(String rq) {
		this.rq=rq;
	}

	public String getDzDjxh() {
		return dzDjxh;
	}

	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh = dzDjxh;
	}

	public String getXcJsDjxh() {
		return xcJsDjxh;
	}

	public void setXcJsDjxh(String xcJsDjxh) {
		this.xcJsDjxh = xcJsDjxh;
	}
}