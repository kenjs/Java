package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_TZGG_YYJL is created by tools.
 * @author HJH
 */

public class BgTzggYyjl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // ֪ͨ�������
	private String czyDjxh;                          // ����Ա�Ǽ����
	private String ckrq;                             // �鿴����(��ʱ����)

	public BgTzggYyjl() {
	}

	//��ȡ֪ͨ�������
	public String getTzggXh() {
		return this.tzggXh;
	}

	//����֪ͨ�������
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//��ȡ����Ա�Ǽ����
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ�鿴����(��ʱ����)
	public String getCkrq() {
		return this.ckrq;
	}

	//���ò鿴����(��ʱ����)
	public void setCkrq(String ckrq) {
		this.ckrq=ckrq;
	}
}