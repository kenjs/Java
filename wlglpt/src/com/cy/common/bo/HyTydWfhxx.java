package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_TYD_WFHXX is created by tools.
 * @author HJH
 */

public class HyTydWfhxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wfhDjxh;                          // δ�����Ǽ����(SEQ_FHMX_DJXH)
	private String hwztDm;                           // ����״̬����(������δ��/δ��)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ������ϸ���
	private Double hwSl;                             // ����_����
	private Double hwZl;                             // ����_����
	private Double hwTj;                             // ����_���
	private String fhrDjxh;                          // ������_�Ǽ����
	private String fhrMc;                            // ������_����
	private String fhrDz;                            // ������_��ַ
	private String fhrLxr;                           // ������_��ϵ��
	private String fhrLxdh;                          // ������_��ϵ�绰
	private String fhrXzqhDm;                        // ������_������������
	private String pcbz;                             // �ɳ���־(Y/N)
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String yxbz;                             // ��Ч��־(Y/N)

	public HyTydWfhxx() {
	}

	//��ȡδ�����Ǽ����(SEQ_FHMX_DJXH)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//����δ�����Ǽ����(SEQ_FHMX_DJXH)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//��ȡ����״̬����(������δ��/δ��)
	public String getHwztDm() {
		return this.hwztDm;
	}

	//���û���״̬����(������δ��/δ��)
	public void setHwztDm(String hwztDm) {
		this.hwztDm=hwztDm;
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

	//��ȡ������_�Ǽ����
	public String getFhrDjxh() {
		return this.fhrDjxh;
	}

	//���÷�����_�Ǽ����
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh=fhrDjxh;
	}

	//��ȡ������_����
	public String getFhrMc() {
		return this.fhrMc;
	}

	//���÷�����_����
	public void setFhrMc(String fhrMc) {
		this.fhrMc=fhrMc;
	}

	//��ȡ������_��ַ
	public String getFhrDz() {
		return this.fhrDz;
	}

	//���÷�����_��ַ
	public void setFhrDz(String fhrDz) {
		this.fhrDz=fhrDz;
	}

	//��ȡ������_��ϵ��
	public String getFhrLxr() {
		return this.fhrLxr;
	}

	//���÷�����_��ϵ��
	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr=fhrLxr;
	}

	//��ȡ������_��ϵ�绰
	public String getFhrLxdh() {
		return this.fhrLxdh;
	}

	//���÷�����_��ϵ�绰
	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh=fhrLxdh;
	}

	//��ȡ������_������������
	public String getFhrXzqhDm() {
		return this.fhrXzqhDm;
	}

	//���÷�����_������������
	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm=fhrXzqhDm;
	}

	//��ȡ�ɳ���־(Y/N)
	public String getPcbz() {
		return this.pcbz;
	}

	//�����ɳ���־(Y/N)
	public void setPcbz(String pcbz) {
		this.pcbz=pcbz;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}
}