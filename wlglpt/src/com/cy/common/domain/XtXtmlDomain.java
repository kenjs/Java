package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR XT_XTML is created by tools.
 * @author HJH
 */

public class XtXtmlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long xtmlXh;                             // ϵͳĿ¼���(SEQ_XTML_XH)
	private String xtmlMc;                           // ϵͳĿ¼����
	private String xtflDm;                           // ϵͳ�������
	private Long sjXtmlXh;                           // �ϼ�Ŀ¼���
	private String pic;
	private Long sxh;                                // ˳���
	private Long jcxh;                               // �������
	private String pxxh;                             // �������(12λ���ȣ�ÿ2λΪһ��)
	private String yxbz;                             // ��Ч��־
	private String cjrDm;                            // ������
	private String cjrq;                             // ��������
	private String xgrDm;                            // �޸���
	private String xgrq;                             // �޸�����
	private List<XtGnmkDomain> gnList;
	private List<XtGnmkDomain> gnMKList;
	public List<XtGnmkDomain> getGnList() {
		if(gnList==null){
			gnList=new ArrayList<XtGnmkDomain>();
		}
		return gnList;
	}

	public List<XtGnmkDomain> getGnMKList() {
		if(gnMKList==null){
			gnMKList=new ArrayList<XtGnmkDomain>();
		}
		return gnMKList;
	}

	public void setGnMKList(List<XtGnmkDomain> gnMKList) {
		this.gnMKList = gnMKList;
	}

	public void setGnList(List<XtGnmkDomain> gnList) {
		this.gnList = gnList;
	}

	public XtXtmlDomain() {
	}

	//��ȡϵͳĿ¼���(SEQ_XTML_XH)
	public Long getXtmlXh() {
		return this.xtmlXh;
	}

	//����ϵͳĿ¼���(SEQ_XTML_XH)
	public void setXtmlXh(Long xtmlXh) {
		this.xtmlXh=xtmlXh;
	}

	//��ȡϵͳĿ¼����
	public String getXtmlMc() {
		return this.xtmlMc;
	}

	//����ϵͳĿ¼����
	public void setXtmlMc(String xtmlMc) {
		this.xtmlMc=xtmlMc;
	}

	//��ȡϵͳ�������
	public String getXtflDm() {
		return this.xtflDm;
	}

	//����ϵͳ�������
	public void setXtflDm(String xtflDm) {
		this.xtflDm=xtflDm;
	}

	//��ȡ�ϼ�Ŀ¼���
	public Long getSjXtmlXh() {
		return this.sjXtmlXh;
	}

	//�����ϼ�Ŀ¼���
	public void setSjXtmlXh(Long sjXtmlXh) {
		this.sjXtmlXh=sjXtmlXh;
	}

	//��ȡ˳���
	public Long getSxh() {
		return this.sxh;
	}

	//����˳���
	public void setSxh(Long sxh) {
		this.sxh=sxh;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	//��ȡ�������
	public Long getJcxh() {
		return this.jcxh;
	}

	//���ü������
	public void setJcxh(Long jcxh) {
		this.jcxh=jcxh;
	}

	//��ȡ�������(12λ���ȣ�ÿ2λΪһ��)
	public String getPxxh() {
		return this.pxxh;
	}

	//�����������(12λ���ȣ�ÿ2λΪһ��)
	public void setPxxh(String pxxh) {
		this.pxxh=pxxh;
	}

	//��ȡ��Ч��־
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ������
	public String getCjrDm() {
		return this.cjrDm;
	}

	//���ô�����
	public void setCjrDm(String cjrDm) {
		this.cjrDm=cjrDm;
	}

	//��ȡ��������
	public String getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ�޸���
	public String getXgrDm() {
		return this.xgrDm;
	}

	//�����޸���
	public void setXgrDm(String xgrDm) {
		this.xgrDm=xgrDm;
	}

	//��ȡ�޸�����
	public String getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}