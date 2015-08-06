package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_KPSQ is created by tools.
 * @author HJH
 */

public class JsKpsq  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqDjxh;                         // ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	private String kpsqfsDm;                         // ��Ʊ���뷽ʽ����
	private String khDjxh;                           // �ͻ��Ǽ����
	private Double sqKpjeHj;                         // ���뿪Ʊ���ϼ�
	private String sqKprq;                           // ���뿪Ʊ����
	private String bzsm;                             // ��ע˵��
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String ykpQdhx;                          // Ԥ��Ʊ�嵥������־(Y/N)
	private String kpDwJgbm;                         //  ��Ʊ��λ
	private String kpDwJgMc;                         //  ��Ʊ��λ����
	private String fpkjbz;							//��Ʊ���߱�־
	private Double fpkjJe;                         // ��Ʊ���߽��
	private String shf;								//�ջ���
	private String shfSbh;							//�ջ���ʶ���
	private String fhf;								//������
	private String fhfSbh;							//������ʶ���
	private String ydrq;							//Ԥ������
	private String dj;								//����
	private String mc;								//����			
	private String sl;								//����		
	private String dkf;								//�ֿ۷�
	
	public JsKpsq() {
	}

	//��ȡ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//���ÿ�Ʊ����Ǽ����(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//��ȡ��Ʊ���뷽ʽ����
	public String getKpsqfsDm() {
		return this.kpsqfsDm;
	}

	//���ÿ�Ʊ���뷽ʽ����
	public void setKpsqfsDm(String kpsqfsDm) {
		this.kpsqfsDm=kpsqfsDm;
	}

	//��ȡ�ͻ��Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ���뿪Ʊ���ϼ�
	public Double getSqKpjeHj() {
		return this.sqKpjeHj;
	}

	//�������뿪Ʊ���ϼ�
	public void setSqKpjeHj(Double sqKpjeHj) {
		this.sqKpjeHj=sqKpjeHj;
	}

	//��ȡ���뿪Ʊ����
	public String getSqKprq() {
		return this.sqKprq;
	}

	//�������뿪Ʊ����
	public void setSqKprq(String sqKprq) {
		this.sqKprq=sqKprq;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		return this.djrq;
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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

	//��ȡԤ��Ʊ�嵥������־(Y/N)
	public String getYkpQdhx() {
		return this.ykpQdhx;
	}

	//����Ԥ��Ʊ�嵥������־(Y/N)
	public void setYkpQdhx(String ykpQdhx) {
		this.ykpQdhx=ykpQdhx;
	}

	public String getKpDwJgbm() {
		return kpDwJgbm;
	}

	public void setKpDwJgbm(String kpDwJgbm) {
		this.kpDwJgbm = kpDwJgbm;
	}

	public String getFpkjbz() {
		return fpkjbz;
	}

	public void setFpkjbz(String fpkjbz) {
		this.fpkjbz = fpkjbz;
	}

	public Double getFpkjJe() {
		return fpkjJe;
	}

	public void setFpkjJe(Double fpkjJe) {
		this.fpkjJe = fpkjJe;
	}

	public String getShf() {
		return shf;
	}

	public void setShf(String shf) {
		this.shf = shf;
	}

	public String getShfSbh() {
		return shfSbh;
	}

	public void setShfSbh(String shfSbh) {
		this.shfSbh = shfSbh;
	}

	public String getFhf() {
		return fhf;
	}

	public void setFhf(String fhf) {
		this.fhf = fhf;
	}

	public String getFhfSbh() {
		return fhfSbh;
	}

	public void setFhfSbh(String fhfSbh) {
		this.fhfSbh = fhfSbh;
	}

	public String getYdrq() {
		return ydrq;
	}

	public void setYdrq(String ydrq) {
		this.ydrq = ydrq;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getDkf() {
		return dkf;
	}

	public void setDkf(String dkf) {
		this.dkf = dkf;
	}

	public String getKpDwJgMc() {
		return kpDwJgMc;
	}

	public void setKpDwJgMc(String kpDwJgMc) {
		this.kpDwJgMc = kpDwJgMc;
	}

}