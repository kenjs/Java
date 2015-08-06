package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR XYJS_SRDZ_QD_MX is created by tools.
 * @author HJH
 */

public class XyjsSrdzQdMxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH)
	private String ywDjxh;                           // ����Ǽ����
	private String ywMxXh;                           // 1�����ͷѣ�2�������3�����ջ���
	private String ywLydm;                           // 1��������ˣ�2�����õǼǣ�3��������ʧ

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public XyjsSrdzQdMxDomain() {
	}

	//��ȡ�嵥�Ǽ����(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//�����嵥�Ǽ����(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//��ȡ����Ǽ����
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//���ý���Ǽ����
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//��ȡ1�����ͷѣ�2�������3�����ջ���
	public String getYwMxXh() {
		return this.ywMxXh;
	}

	//����1�����ͷѣ�2�������3�����ջ���
	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh=ywMxXh;
	}

	//��ȡ1��������ˣ�2�����õǼǣ�3��������ʧ
	public String getYwLydm() {
		return this.ywLydm;
	}

	//����1��������ˣ�2�����õǼǣ�3��������ʧ
	public void setYwLydm(String ywLydm) {
		this.ywLydm=ywLydm;
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
