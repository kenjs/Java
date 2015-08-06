package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_ZJRB is created by tools.
 * @author HJH
 */

public class CwZjrb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private Double zrKcXj;                           // ���տ���ֽ�
	private Double zrKcYh;                           // ���տ������
	private Double zrKcYk;                           // ���տ���Ϳ�
	private Double kcXj;                             // ����ֽ�
	private Double kcYh;                             // �������
	private Double kcYk;                             // ����Ϳ�
	private Double srXj;                             // �����ֽ�
	private Double srYh;                             // ��������
	private Double srYk;                             // �����Ϳ�
	private Double zcXj;                             // ֧���ֽ�
	private Double zcYh;                             // ֧������
	private Double zcYk;                             // ֧���Ϳ�
	private Double kcXjSrz;                          // ����ֽ�����ֵ
	private Double kcYhSrz;                          // �����������ֵ
	private Double srXjSrz;                          // �����ֽ�����ֵ
	private Double srYhSrz;                          // ������������ֵ
	private Double zcXjSrz;                          // ֧���ֽ�����ֵ
	private Double zcYhSrz;                          // ֧����������ֵ
	private String rq;                               // ����
	private Double xfSrXj;                           // �ָ������ֽ�
	private Double dfSrXj;                           // �����ջ��ֽ�
	private Double yjSrXj;                           // �½��ջ��ֽ�
	private Double dshkSrXj;                         // ���ջ��������ֽ�
	private Double ysSrXj;                           // Ԥ�������ֽ�
	private Double qtSrXj;                           // ���������ֽ�
	private Double xfSrYh;                           // �ָ���������
	private Double dfSrYh;                           // �����ջ�����
	private Double yjSrYh;                           // �½��ջ�����
	private Double dshkSrYh;                         // ���ջ�����������
	private Double ysSrYh;                           // Ԥ����������
	private Double qtSrYh;                           // ������������
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public CwZjrb() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ���տ���ֽ�
	public Double getZrKcXj() {
		return this.zrKcXj;
	}

	//�������տ���ֽ�
	public void setZrKcXj(Double zrKcXj) {
		this.zrKcXj=zrKcXj;
	}

	//��ȡ���տ������
	public Double getZrKcYh() {
		return this.zrKcYh;
	}

	//�������տ������
	public void setZrKcYh(Double zrKcYh) {
		this.zrKcYh=zrKcYh;
	}

	//��ȡ���տ���Ϳ�
	public Double getZrKcYk() {
		return this.zrKcYk;
	}

	//�������տ���Ϳ�
	public void setZrKcYk(Double zrKcYk) {
		this.zrKcYk=zrKcYk;
	}

	//��ȡ����ֽ�
	public Double getKcXj() {
		return this.kcXj;
	}

	//���ÿ���ֽ�
	public void setKcXj(Double kcXj) {
		this.kcXj=kcXj;
	}

	//��ȡ�������
	public Double getKcYh() {
		return this.kcYh;
	}

	//���ÿ������
	public void setKcYh(Double kcYh) {
		this.kcYh=kcYh;
	}

	//��ȡ����Ϳ�
	public Double getKcYk() {
		return this.kcYk;
	}

	//���ÿ���Ϳ�
	public void setKcYk(Double kcYk) {
		this.kcYk=kcYk;
	}

	//��ȡ�����ֽ�
	public Double getSrXj() {
		return this.srXj;
	}

	//���������ֽ�
	public void setSrXj(Double srXj) {
		this.srXj=srXj;
	}

	//��ȡ��������
	public Double getSrYh() {
		return this.srYh;
	}

	//������������
	public void setSrYh(Double srYh) {
		this.srYh=srYh;
	}

	//��ȡ�����Ϳ�
	public Double getSrYk() {
		return this.srYk;
	}

	//���������Ϳ�
	public void setSrYk(Double srYk) {
		this.srYk=srYk;
	}

	//��ȡ֧���ֽ�
	public Double getZcXj() {
		return this.zcXj;
	}

	//����֧���ֽ�
	public void setZcXj(Double zcXj) {
		this.zcXj=zcXj;
	}

	//��ȡ֧������
	public Double getZcYh() {
		return this.zcYh;
	}

	//����֧������
	public void setZcYh(Double zcYh) {
		this.zcYh=zcYh;
	}

	//��ȡ֧���Ϳ�
	public Double getZcYk() {
		return this.zcYk;
	}

	//����֧���Ϳ�
	public void setZcYk(Double zcYk) {
		this.zcYk=zcYk;
	}

	//��ȡ����ֽ�����ֵ
	public Double getKcXjSrz() {
		return this.kcXjSrz;
	}

	//���ÿ���ֽ�����ֵ
	public void setKcXjSrz(Double kcXjSrz) {
		this.kcXjSrz=kcXjSrz;
	}

	//��ȡ�����������ֵ
	public Double getKcYhSrz() {
		return this.kcYhSrz;
	}

	//���ÿ����������ֵ
	public void setKcYhSrz(Double kcYhSrz) {
		this.kcYhSrz=kcYhSrz;
	}

	//��ȡ�����ֽ�����ֵ
	public Double getSrXjSrz() {
		return this.srXjSrz;
	}

	//���������ֽ�����ֵ
	public void setSrXjSrz(Double srXjSrz) {
		this.srXjSrz=srXjSrz;
	}

	//��ȡ������������ֵ
	public Double getSrYhSrz() {
		return this.srYhSrz;
	}

	//����������������ֵ
	public void setSrYhSrz(Double srYhSrz) {
		this.srYhSrz=srYhSrz;
	}

	//��ȡ֧���ֽ�����ֵ
	public Double getZcXjSrz() {
		return this.zcXjSrz;
	}

	//����֧���ֽ�����ֵ
	public void setZcXjSrz(Double zcXjSrz) {
		this.zcXjSrz=zcXjSrz;
	}

	//��ȡ֧����������ֵ
	public Double getZcYhSrz() {
		return this.zcYhSrz;
	}

	//����֧����������ֵ
	public void setZcYhSrz(Double zcYhSrz) {
		this.zcYhSrz=zcYhSrz;
	}

	//��ȡ����
	public String getRq() {
		return this.rq;
	}

	//��������
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ�ָ������ֽ�
	public Double getXfSrXj() {
		return this.xfSrXj;
	}

	//�����ָ������ֽ�
	public void setXfSrXj(Double xfSrXj) {
		this.xfSrXj=xfSrXj;
	}

	//��ȡ�����ջ��ֽ�
	public Double getDfSrXj() {
		return this.dfSrXj;
	}

	//���õ����ջ��ֽ�
	public void setDfSrXj(Double dfSrXj) {
		this.dfSrXj=dfSrXj;
	}

	//��ȡ�½��ջ��ֽ�
	public Double getYjSrXj() {
		return this.yjSrXj;
	}

	//�����½��ջ��ֽ�
	public void setYjSrXj(Double yjSrXj) {
		this.yjSrXj=yjSrXj;
	}

	//��ȡ���ջ��������ֽ�
	public Double getDshkSrXj() {
		return this.dshkSrXj;
	}

	//���ô��ջ��������ֽ�
	public void setDshkSrXj(Double dshkSrXj) {
		this.dshkSrXj=dshkSrXj;
	}

	//��ȡԤ�������ֽ�
	public Double getYsSrXj() {
		return this.ysSrXj;
	}

	//����Ԥ�������ֽ�
	public void setYsSrXj(Double ysSrXj) {
		this.ysSrXj=ysSrXj;
	}

	//��ȡ���������ֽ�
	public Double getQtSrXj() {
		return this.qtSrXj;
	}

	//�������������ֽ�
	public void setQtSrXj(Double qtSrXj) {
		this.qtSrXj=qtSrXj;
	}

	//��ȡ�ָ���������
	public Double getXfSrYh() {
		return this.xfSrYh;
	}

	//�����ָ���������
	public void setXfSrYh(Double xfSrYh) {
		this.xfSrYh=xfSrYh;
	}

	//��ȡ�����ջ�����
	public Double getDfSrYh() {
		return this.dfSrYh;
	}

	//���õ����ջ�����
	public void setDfSrYh(Double dfSrYh) {
		this.dfSrYh=dfSrYh;
	}

	//��ȡ�½��ջ�����
	public Double getYjSrYh() {
		return this.yjSrYh;
	}

	//�����½��ջ�����
	public void setYjSrYh(Double yjSrYh) {
		this.yjSrYh=yjSrYh;
	}

	//��ȡ���ջ�����������
	public Double getDshkSrYh() {
		return this.dshkSrYh;
	}

	//���ô��ջ�����������
	public void setDshkSrYh(Double dshkSrYh) {
		this.dshkSrYh=dshkSrYh;
	}

	//��ȡԤ����������
	public Double getYsSrYh() {
		return this.ysSrYh;
	}

	//����Ԥ����������
	public void setYsSrYh(Double ysSrYh) {
		this.ysSrYh=ysSrYh;
	}

	//��ȡ������������
	public Double getQtSrYh() {
		return this.qtSrYh;
	}

	//����������������
	public void setQtSrYh(Double qtSrYh) {
		this.qtSrYh=qtSrYh;
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