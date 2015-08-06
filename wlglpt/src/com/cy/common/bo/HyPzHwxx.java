package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_PZ_HWXX is created by tools.
 * @author HJH
 */

public class HyPzHwxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // ���صǼ����
	private String wfhDjxh;                          // ���ػ������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double hwSl;                             // ����_����
	private Double hwZl;                             // ����_����
	private Double hwTj;                             // ����_���
	private Double sr;                               // ����
	private Double yjPsf;                            // Ԥ�����ͷ�
	private String bz;                         // ��ע

	public HyPzHwxx() {
	}

	//��ȡ���صǼ����
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//�������صǼ����
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//��ȡ���ػ������(δ�����Ǽ����)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�������ػ������(δ�����Ǽ����)
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

	//��ȡ���(������ϸ���)
	public String getXh() {
		return this.xh;
	}

	//�������(������ϸ���)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ����_����
	public Double getHwSl() {
		return this.hwSl;
	}

	//���û���_����
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//��ȡ����_����
	public Double getHwZl() {
		return this.hwZl;
	}

	//���û���_����
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//��ȡ����_���
	public Double getHwTj() {
		return this.hwTj;
	}

	//���û���_���
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//��ȡ����
	public Double getSr() {
		return this.sr;
	}

	//��������
	public void setSr(Double sr) {
		this.sr=sr;
	}

	//��ȡԤ�����ͷ�
	public Double getYjPsf() {
		return this.yjPsf;
	}

	//����Ԥ�����ͷ�
	public void setYjPsf(Double yjPsf) {
		this.yjPsf=yjPsf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}