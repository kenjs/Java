package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_DD_HWXX is created by tools.
 * @author HJH
 */

public class JsDdHwxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDjxh;                           // ����Ǽ����(SEQ_JS_DJXH)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ������ϸ���
	private String hwmc;                             // ��������
	private String hwDjxh;                           // ����Ǽ����
	private String hwxhDjxh;                         // �����ͺŵǼ����
	private String hwBzHldwDm;                       // ����_��װ_������λ
	private Double hwSl;                             // ����_����
	private String hwSlJldwDm;                       // ����_����_������λ
	private Double hwZl;                             // ����_����
	private String hwZlJldwDm;                       // ����_����_������λ
	private Double hwTj;                             // ����_���
	private String hwTjJldwDm;                       // ����_���_������λ
	private Double jsSl;                             // ��������
	private String jsJldwDm;                         // ���������λ
	private String jsJldwFlDm;                       // ���������λ�������
	private String hdbh;                             // �ص����
	private String sfdXzqhDm;                        // ʼ����_������������
	private String mddXzqhDm;                        // Ŀ�ĵ�_������������
	private Double dzSr;                             // ����_����
	private Double dzYj;                             // ����_�ѽ�
	private Double dzWj;                             // ����_δ��
	private String yxbz;                             // ��Ч��־(Y/N)
	private String dcjsbz;                           // ��ν����־(Y/N)
	private String qcDzDjxh;                         // ǰ�ζ��ʵǼ����
	private String dzztDm;                           // ����״̬����
	private String dzDjxh;                           // ���ʵǼ����

	public JsDdHwxx() {
	}

	//��ȡ����Ǽ����(SEQ_JS_DJXH)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//���ý���Ǽ����(SEQ_JS_DJXH)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ������ϸ���
	public String getXh() {
		return this.xh;
	}

	//���û�����ϸ���
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ��������
	public String getHwmc() {
		return this.hwmc;
	}

	//���û�������
	public void setHwmc(String hwmc) {
		this.hwmc=hwmc;
	}

	//��ȡ����Ǽ����
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//���û���Ǽ����
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
	}

	//��ȡ�����ͺŵǼ����
	public String getHwxhDjxh() {
		return this.hwxhDjxh;
	}

	//���û����ͺŵǼ����
	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh=hwxhDjxh;
	}

	//��ȡ����_��װ_������λ
	public String getHwBzHldwDm() {
		return this.hwBzHldwDm;
	}

	//���û���_��װ_������λ
	public void setHwBzHldwDm(String hwBzHldwDm) {
		this.hwBzHldwDm=hwBzHldwDm;
	}

	//��ȡ����_����
	public Double getHwSl() {
		return this.hwSl;
	}

	//���û���_����
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//��ȡ����_����_������λ
	public String getHwSlJldwDm() {
		return this.hwSlJldwDm;
	}

	//���û���_����_������λ
	public void setHwSlJldwDm(String hwSlJldwDm) {
		this.hwSlJldwDm=hwSlJldwDm;
	}

	//��ȡ����_����
	public Double getHwZl() {
		return this.hwZl;
	}

	//���û���_����
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//��ȡ����_����_������λ
	public String getHwZlJldwDm() {
		return this.hwZlJldwDm;
	}

	//���û���_����_������λ
	public void setHwZlJldwDm(String hwZlJldwDm) {
		this.hwZlJldwDm=hwZlJldwDm;
	}

	//��ȡ����_���
	public Double getHwTj() {
		return this.hwTj;
	}

	//���û���_���
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//��ȡ����_���_������λ
	public String getHwTjJldwDm() {
		return this.hwTjJldwDm;
	}

	//���û���_���_������λ
	public void setHwTjJldwDm(String hwTjJldwDm) {
		this.hwTjJldwDm=hwTjJldwDm;
	}

	//��ȡ��������
	public Double getJsSl() {
		return this.jsSl;
	}

	//���ý�������
	public void setJsSl(Double jsSl) {
		this.jsSl=jsSl;
	}

	//��ȡ���������λ
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//���ý��������λ
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//��ȡ���������λ�������
	public String getJsJldwFlDm() {
		return this.jsJldwFlDm;
	}

	//���ý��������λ�������
	public void setJsJldwFlDm(String jsJldwFlDm) {
		this.jsJldwFlDm=jsJldwFlDm;
	}

	//��ȡ�ص����
	public String getHdbh() {
		return this.hdbh;
	}

	//���ûص����
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	//��ȡʼ����_������������
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//����ʼ����_������������
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//��ȡĿ�ĵ�_������������
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//����Ŀ�ĵ�_������������
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//��ȡ����_����
	public Double getDzSr() {
		return this.dzSr;
	}

	//���ö���_����
	public void setDzSr(Double dzSr) {
		this.dzSr=dzSr;
	}

	//��ȡ����_�ѽ�
	public Double getDzYj() {
		return this.dzYj;
	}

	//���ö���_�ѽ�
	public void setDzYj(Double dzYj) {
		this.dzYj=dzYj;
	}

	//��ȡ����_δ��
	public Double getDzWj() {
		return this.dzWj;
	}

	//���ö���_δ��
	public void setDzWj(Double dzWj) {
		this.dzWj=dzWj;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ��ν����־(Y/N)
	public String getDcjsbz() {
		return this.dcjsbz;
	}

	//���ö�ν����־(Y/N)
	public void setDcjsbz(String dcjsbz) {
		this.dcjsbz=dcjsbz;
	}

	//��ȡǰ�ζ��ʵǼ����
	public String getQcDzDjxh() {
		return this.qcDzDjxh;
	}

	//����ǰ�ζ��ʵǼ����
	public void setQcDzDjxh(String qcDzDjxh) {
		this.qcDzDjxh=qcDzDjxh;
	}

	//��ȡ����״̬����
	public String getDzztDm() {
		return this.dzztDm;
	}

	//���ö���״̬����
	public void setDzztDm(String dzztDm) {
		this.dzztDm=dzztDm;
	}

	//��ȡ���ʵǼ����
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//���ö��ʵǼ����
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}
}