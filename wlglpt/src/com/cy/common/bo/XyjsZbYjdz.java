package com.cy.common.bo;
import java.io.Serializable;
import java.sql.Date;

/**
 * The persistent class For XYJS_ZB_YJDZ is created by tools.
 * @author XIAY
 */

public class XyjsZbYjdz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // ���ʵǼ����(SEQ_DZ_DJXH)
	private String ssJgbm;                           // ��������
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ������ϸ���
	private String zrbmDm;                           // ת�벿�Ŵ���
	private String zrbmDjxh;                         // ת�벿�ŵǼ����
	private Double jsYj;                             // ����_δ��
	private Double dzje;                             // ���˽��
	private String dzrCzyDjxh;                       // ������
	private String dzrq;                               // ��������
	private String dzJgbm;                           // ���ʲ���
	private String dzbz;							  // ���˱�־
	private String yxbz;                             // ��Ч��־(Y/N)
	private String dzCybz;                           // ���ʲ����־(Y/N)
	private Double dzcyje;                           // ���ʲ�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������

	public XyjsZbYjdz() {
	}

	//��ȡ���ʵǼ����(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//���ö��ʵǼ����(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ��������(δ�����Ǽ����)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�����ɳ��������(δ�����Ǽ����)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
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

	//��ȡת�벿�Ŵ���
	public String getZrbmDm() {
		return this.zrbmDm;
	}

	//����ת�벿�Ŵ���
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm=zrbmDm;
	}

	//��ȡת�벿�ŵǼ����
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//����ת�벿�ŵǼ����
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//��ȡ����_δ��
	public Double getJsYj() {
		return this.jsYj;
	}

	//���ý���_δ��
	public void setJsYj(Double jsYj) {
		this.jsYj=jsYj;
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

	//��ȡ��־
	public String getDzbz() {
		return dzbz;
	}

	//���ö��˱�־
	public void setDzbz(String dzbz) {
		this.dzbz = dzbz;
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
}