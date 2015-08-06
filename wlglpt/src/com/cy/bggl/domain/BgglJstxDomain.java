package com.cy.bggl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR BG_GZLX2 is created by tools.
 * @author HJH
 */

public class BgglJstxDomain  extends BaseBusinessDomain {
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
	
	private String fsrCzyMc;
	private String czyMc;
	
	private String jsrqQ;
	private Long lxrDjxh;

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public BgglJstxDomain() {
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

	public String getJsrqQ() {
		return jsrqQ;
	}

	public void setJsrqQ(String jsrqQ) {
		this.jsrqQ = jsrqQ;
	}

	public String getFsrCzyMc() {
		return fsrCzyMc;
	}

	public void setFsrCzyMc(String fsrCzyMc) {
		this.fsrCzyMc = fsrCzyMc;
	}

	public String getCzyMc() {
		return czyMc;
	}

	public void setCzyMc(String czyMc) {
		this.czyMc = czyMc;
	}

	public Long getLxrDjxh() {
		return lxrDjxh;
	}

	public void setLxrDjxh(Long lxrDjxh) {
		this.lxrDjxh = lxrDjxh;
	}

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
}
