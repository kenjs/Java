package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For BG_GZLX_JSR is created by tools.
 * @author HJH
 */

public class BgGzlxJsr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gzlxXh;                           // ������ϵ���(SEQ_GZLX_XH)
	private String czyDjxh;                          // ����Ա�Ǽ����
	private Long xtyhflDm;                           // ϵͳ�û��������
	private String ckbz;                             // �鿴��־(Y/N)
	private String ckrq;                             // �鿴����(��ʱ����)

	public BgGzlxJsr() {
	}

	//��ȡ������ϵ���(SEQ_GZLX_XH)
	public String getGzlxXh() {
		return this.gzlxXh;
	}

	//���ù�����ϵ���(SEQ_GZLX_XH)
	public void setGzlxXh(String gzlxXh) {
		this.gzlxXh=gzlxXh;
	}

	//��ȡ����Ա�Ǽ����
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡϵͳ�û��������
	public Long getXtyhflDm() {
		return this.xtyhflDm;
	}

	//����ϵͳ�û��������
	public void setXtyhflDm(Long xtyhflDm) {
		this.xtyhflDm=xtyhflDm;
	}

	//��ȡ�鿴��־(Y/N)
	public String getCkbz() {
		return this.ckbz;
	}

	//���ò鿴��־(Y/N)
	public void setCkbz(String ckbz) {
		this.ckbz=ckbz;
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