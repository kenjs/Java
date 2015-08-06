package com.cy.common.bo;

import java.io.Serializable;

public class YysDmUserBcxx implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long czryDm;                           // ������Ա����(YHBZ=YΪCTAIS�е�CZRY_DM,����SEQ_CZY_XH)
	private String czryMc;                           // ������Ա����
	private String name;                             // ��¼�û���
	private String password;                         // ��¼����
	private String yhsm;                             // �û�˵��
	private String yhbz;                             // CTAIS�û���־(Y/N)
	private String swjgDm;                           // ˰����ش���
	private String cjglybz;                          // ��������Ա��־(Y/N)
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)

	public YysDmUserBcxx() {
	}

	

	public Long getCzryDm() {
		return czryDm;
	}



	public void setCzryDm(Long czryDm) {
		this.czryDm = czryDm;
	}



	public String getCzryMc() {
		return this.czryMc;
	}

	public void setCzryMc(String czryMc) {
		this.czryMc = czryMc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQybz() {
		return this.qybz;
	}

	public void setQybz(String qybz) {
		this.qybz = qybz;
	}

	

	public String getSwjgDm() {
		return this.swjgDm;
	}

	public void setSwjgDm(String swjgDm) {
		this.swjgDm = swjgDm;
	}

	public String getYhbz() {
		return this.yhbz;
	}

	public void setYhbz(String yhbz) {
		this.yhbz = yhbz;
	}


	public String getCjglybz() {
		return cjglybz;
	}

	public void setCjglybz(String cjglybz) {
		this.cjglybz = cjglybz;
	}

	public String getYhsm() {
		return this.yhsm;
	}

	public void setYhsm(String yhsm) {
		this.yhsm = yhsm;
	}

	public String getYxbz() {
		return this.yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}