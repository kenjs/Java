package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_HWXX_SHFSBG is created by tools.
 * @author HJH
 */

public class HyHwxxShfsbg  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shbgDjxh;                         // �ͻ����-�Ǽ����
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double srHj;                             // ����_С��
	private Double srYj;                             // ����_�½�
	private Double srXf;                             // ����_�ָ�
	private Double srHdf;                            // ����_������
	private Double srThf;                            // ����_�����
	private Double srHf;                             // ����_�ص���
	private Double srHk;                             // ����_�ؿ�
	private Double srBjf;                            // ����_���۷�
	private Double srPsf;                            // ����_���ͷ�
	private Double srYsf;                            // ����_�����
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������

	public HyHwxxShfsbg() {
	}

	//��ȡ�ͻ����-�Ǽ����
	public String getShbgDjxh() {
		return this.shbgDjxh;
	}

	//�����ͻ����-�Ǽ����
	public void setShbgDjxh(String shbgDjxh) {
		this.shbgDjxh=shbgDjxh;
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

	//��ȡ����_С��
	public Double getSrHj() {
		return this.srHj;
	}

	//��������_С��
	public void setSrHj(Double srHj) {
		this.srHj=srHj;
	}

	//��ȡ����_�½�
	public Double getSrYj() {
		return this.srYj;
	}

	//��������_�½�
	public void setSrYj(Double srYj) {
		this.srYj=srYj;
	}

	//��ȡ����_�ָ�
	public Double getSrXf() {
		return this.srXf;
	}

	//��������_�ָ�
	public void setSrXf(Double srXf) {
		this.srXf=srXf;
	}

	//��ȡ����_������
	public Double getSrHdf() {
		return this.srHdf;
	}

	//��������_������
	public void setSrHdf(Double srHdf) {
		this.srHdf=srHdf;
	}

	//��ȡ����_�����
	public Double getSrThf() {
		return this.srThf;
	}

	//��������_�����
	public void setSrThf(Double srThf) {
		this.srThf=srThf;
	}

	//��ȡ����_�ص���
	public Double getSrHf() {
		return this.srHf;
	}

	//��������_�ص���
	public void setSrHf(Double srHf) {
		this.srHf=srHf;
	}

	//��ȡ����_�ؿ�
	public Double getSrHk() {
		return this.srHk;
	}

	//��������_�ؿ�
	public void setSrHk(Double srHk) {
		this.srHk=srHk;
	}

	//��ȡ����_���۷�
	public Double getSrBjf() {
		return this.srBjf;
	}

	//��������_���۷�
	public void setSrBjf(Double srBjf) {
		this.srBjf=srBjf;
	}

	//��ȡ����_���ͷ�
	public Double getSrPsf() {
		return this.srPsf;
	}

	//��������_���ͷ�
	public void setSrPsf(Double srPsf) {
		this.srPsf=srPsf;
	}

	//��ȡ����_�����
	public Double getSrYsf() {
		return this.srYsf;
	}

	//��������_�����
	public void setSrYsf(Double srYsf) {
		this.srYsf=srYsf;
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
}