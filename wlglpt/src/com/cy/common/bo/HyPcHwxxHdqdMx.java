package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_PC_HWXX_HDQD_MX is created by tools.
 * @author HJH
 */

public class HyPcHwxxHdqdMx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdqdDjxh;                         // �ص��嵥�Ǽ����
	private String hdDjxh;                           // �ص��Ǽ����
	private String jszt;                             // ����״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	private String fsGsbm;                           // ���͹�˾����
	private String jsGsbm;                           // ���չ�˾����
	private String dqztbz;
	
	public HyPcHwxxHdqdMx() {
	}

	//��ȡ�ص��嵥�Ǽ����
	public String getHdqdDjxh() {
		return this.hdqdDjxh;
	}

	//���ûص��嵥�Ǽ����
	public void setHdqdDjxh(String hdqdDjxh) {
		this.hdqdDjxh=hdqdDjxh;
	}

	//��ȡ�ص��Ǽ����
	public String getHdDjxh() {
		return this.hdDjxh;
	}

	//���ûص��Ǽ����
	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh=hdDjxh;
	}

	//��ȡ����״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	public String getJszt() {
		return this.jszt;
	}

	//���ý���״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	public void setJszt(String jszt) {
		this.jszt=jszt;
	}

	//��ȡ���͹�˾����
	public String getFsGsbm() {
		return this.fsGsbm;
	}

	//���÷��͹�˾����
	public void setFsGsbm(String fsGsbm) {
		this.fsGsbm=fsGsbm;
	}

	//��ȡ���չ�˾����
	public String getJsGsbm() {
		return this.jsGsbm;
	}

	//���ý��չ�˾����
	public void setJsGsbm(String jsGsbm) {
		this.jsGsbm=jsGsbm;
	}

	public String getDqztbz() {
		return dqztbz;
	}

	public void setDqztbz(String dqztbz) {
		this.dqztbz = dqztbz;
	}
}