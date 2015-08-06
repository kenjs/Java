package com.cy.common.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR XT_XTML_GNMK_CZTS is created by tools.
 * @author HJH
 */

public class XtXtmlGnmkCztsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long xtmlXh;                             // ϵͳĿ¼���
	private String gnmkDm;                           // ����ģ�����
	private String czts;                             // ������ʾ
	private String xtmlMc;							 //ϵͳĿ¼����
	private String gnmkMc;							 //����ģ������
	
	private Long xgXtmlXh;							 //�޸�ʱ�Ӹ�ҳ�洫����ҳ���ϵͳĿ¼��ţ��޸�ʱ��
	
	private String xgGnmkDm;							 //�޸ĴӸ�ҳ�洫����ҳ��Ĺ���ģ����룬�޸�ʱ��
	
	List<XtXtmlGnmkCztsDomain> domainList; 			 //����б�
	
	List<XtXtmlDomain> xtXtmlList; 			 		 //ϵͳĿ¼�����б�
	
	List<XtGnmkDomain> xtGnmkList; 			 		 //ϵͳ����ģ�������б�

	public XtXtmlGnmkCztsDomain() {
	}

	//��ȡϵͳĿ¼���
	public Long getXtmlXh() {
		return this.xtmlXh;
	}

	//����ϵͳĿ¼���
	public void setXtmlXh(Long xtmlXh) {
		this.xtmlXh=xtmlXh;
	}

	//��ȡ����ģ�����
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//���ù���ģ�����
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}

	//��ȡ������ʾ
	public String getCzts() {
		return this.czts;
	}

	//���ò�����ʾ
	public void setCzts(String czts) {
		this.czts=czts;
	}

	public List<XtXtmlGnmkCztsDomain> getDomainList() {
		if (null == domainList) {
			domainList = new ArrayList<XtXtmlGnmkCztsDomain>();
		}
		return domainList;
	}

	public void setDomainList(List<XtXtmlGnmkCztsDomain> domainList) {
		this.domainList = domainList;
	}

	public List<XtGnmkDomain> getXtGnmkList() {
		if (null == xtGnmkList) {
			xtGnmkList = new ArrayList<XtGnmkDomain>();
		}
		return xtGnmkList;
	}

	public void setXtGnmkList(List<XtGnmkDomain> xtGnmkList) {
		this.xtGnmkList = xtGnmkList;
	}

	public List<XtXtmlDomain> getXtXtmlList() {
		if (null == xtXtmlList) {
			xtXtmlList = new ArrayList<XtXtmlDomain>();
		}
		return xtXtmlList;
	}

	public void setXtXtmlList(List<XtXtmlDomain> xtXtmlList) {
		this.xtXtmlList = xtXtmlList;
	}

	public String getXtmlMc() {
		return xtmlMc;
	}

	public void setXtmlMc(String xtmlMc) {
		this.xtmlMc = xtmlMc;
	}

	public String getGnmkMc() {
		return gnmkMc;
	}

	public void setGnmkMc(String gnmkMc) {
		this.gnmkMc = gnmkMc;
	}

	public Long getXgXtmlXh() {
		return xgXtmlXh;
	}

	public void setXgXtmlXh(Long xgXtmlXh) {
		this.xgXtmlXh = xgXtmlXh;
	}

	public String getXgGnmkDm() {
		return xgGnmkDm;
	}

	public void setXgGnmkDm(String xgGnmkDm) {
		this.xgGnmkDm = xgGnmkDm;
	}
}