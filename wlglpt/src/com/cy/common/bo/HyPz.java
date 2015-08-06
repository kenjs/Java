package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PZ is created by tools.
 * @author HJH
 */

public class HyPz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // ���صǼ����(SEQ_PZ_DJXH)
	private String hzJgbm;                           // ��վ��������
	private String clxhwhDjxh;                       // �����ͺ�ά�����
	private Double clCz;                             // ����_����(��)
	private Double clTj;                             // ����_���(��)
	private Double clCd;                             // ����_����(��)
	private Double clKd;                             // ����_���(��)
	private Double clGd;                             // ����_�߶�(��)
	private Double pzCz;                             // ����_����(��)
	private Double pzTj;                             // ����_���(��)
	private Double pzCd;                             // ����_����(��)
	private Double pzKd;                             // ����_���(��)
	private Double pzGd;                             // ����_�߶�(��)
	private Double pzsr;                             // ��������
	private Double pzcb;                             // ����Ԥ�Ƴɱ�
	private Double pzpsf;                            // ����Ԥ�����ͷ�
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����

	public HyPz() {
	}

	//��ȡ���صǼ����(SEQ_PZ_DJXH)
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//�������صǼ����(SEQ_PZ_DJXH)
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//��ȡ��վ��������
	public String getHzJgbm() {
		return this.hzJgbm;
	}

	//���û�վ��������
	public void setHzJgbm(String hzJgbm) {
		this.hzJgbm=hzJgbm;
	}

	//��ȡ�����ͺ�ά�����
	public String getClxhwhDjxh() {
		return this.clxhwhDjxh;
	}

	//���ó����ͺ�ά�����
	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh=clxhwhDjxh;
	}

	//��ȡ����_����(��)
	public Double getClCz() {
		return this.clCz;
	}

	//���ó���_����(��)
	public void setClCz(Double clCz) {
		this.clCz=clCz;
	}

	//��ȡ����_���(��)
	public Double getClTj() {
		return this.clTj;
	}

	//���ó���_���(��)
	public void setClTj(Double clTj) {
		this.clTj=clTj;
	}

	//��ȡ����_����(��)
	public Double getClCd() {
		return this.clCd;
	}

	//���ó���_����(��)
	public void setClCd(Double clCd) {
		this.clCd=clCd;
	}

	//��ȡ����_���(��)
	public Double getClKd() {
		return this.clKd;
	}

	//���ó���_���(��)
	public void setClKd(Double clKd) {
		this.clKd=clKd;
	}

	//��ȡ����_�߶�(��)
	public Double getClGd() {
		return this.clGd;
	}

	//���ó���_�߶�(��)
	public void setClGd(Double clGd) {
		this.clGd=clGd;
	}

	//��ȡ����_����(��)
	public Double getPzCz() {
		return this.pzCz;
	}

	//��������_����(��)
	public void setPzCz(Double pzCz) {
		this.pzCz=pzCz;
	}

	//��ȡ����_���(��)
	public Double getPzTj() {
		return this.pzTj;
	}

	//��������_���(��)
	public void setPzTj(Double pzTj) {
		this.pzTj=pzTj;
	}

	//��ȡ����_����(��)
	public Double getPzCd() {
		return this.pzCd;
	}

	//��������_����(��)
	public void setPzCd(Double pzCd) {
		this.pzCd=pzCd;
	}

	//��ȡ����_���(��)
	public Double getPzKd() {
		return this.pzKd;
	}

	//��������_���(��)
	public void setPzKd(Double pzKd) {
		this.pzKd=pzKd;
	}

	//��ȡ����_�߶�(��)
	public Double getPzGd() {
		return this.pzGd;
	}

	//��������_�߶�(��)
	public void setPzGd(Double pzGd) {
		this.pzGd=pzGd;
	}

	//��ȡ��������
	public Double getPzsr() {
		return this.pzsr;
	}

	//������������
	public void setPzsr(Double pzsr) {
		this.pzsr=pzsr;
	}

	//��ȡ����Ԥ�Ƴɱ�
	public Double getPzcb() {
		return this.pzcb;
	}

	//��������Ԥ�Ƴɱ�
	public void setPzcb(Double pzcb) {
		this.pzcb=pzcb;
	}

	//��ȡ����Ԥ�����ͷ�
	public Double getPzpsf() {
		return this.pzpsf;
	}

	//��������Ԥ�����ͷ�
	public void setPzpsf(Double pzpsf) {
		this.pzpsf=pzpsf;
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

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ��������
	public Date getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(Date cjrq) {
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
	public Date getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
	}
}