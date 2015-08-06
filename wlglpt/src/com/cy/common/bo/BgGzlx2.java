package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For BG_GZLX2 is created by tools.
 * @author anq
 */

public class BgGzlx2  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long jstxXh;                           // ��ʱͨѶ���(SEQ_GZLX_XH)
	private String nr;                               // ����
	private String fsrCzyDjxh;                       // ������
	private Date fsrq;                             // ��������
	private String czyDjxh;                          // ������
	private String xtyhflDm;                         // ϵͳ�û��������
	private String jsbz;                             // ���ձ�־(Y/N)
	private Date jsrq;                             // ��������

	public BgGzlx2() {
	}

	//��ȡ��ʱͨѶ���(SEQ_GZLX_XH)
	public Long getJstxXh() {
		return this.jstxXh;
	}

	//���ü�ʱͨѶ���(SEQ_GZLX_XH)
	public void setJstxXh(Long jstxXh) {
		this.jstxXh=jstxXh;
	}

	//��ȡ����
	public String getNr() {
		return this.nr;
	}

	//��������
	public void setNr(String nr) {
		this.nr=nr;
	}

	//��ȡ������
	public String getFsrCzyDjxh() {
		return this.fsrCzyDjxh;
	}

	//���÷�����
	public void setFsrCzyDjxh(String fsrCzyDjxh) {
		this.fsrCzyDjxh=fsrCzyDjxh;
	}

	//��ȡ��������
	public Date getFsrq() {
		return this.fsrq;
	}

	//���÷�������
	public void setFsrq(Date fsrq) {
		this.fsrq=fsrq;
	}

	//��ȡ������
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ý�����
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡϵͳ�û��������
	public String getXtyhflDm() {
		return this.xtyhflDm;
	}

	//����ϵͳ�û��������
	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm=xtyhflDm;
	}

	//��ȡ���ձ�־(Y/N)
	public String getJsbz() {
		return this.jsbz;
	}

	//���ý��ձ�־(Y/N)
	public void setJsbz(String jsbz) {
		this.jsbz=jsbz;
	}

	//��ȡ��������
	public Date getJsrq() {
		return this.jsrq;
	}

	//���ý�������
	public void setJsrq(Date jsrq) {
		this.jsrq=jsrq;
	}
}