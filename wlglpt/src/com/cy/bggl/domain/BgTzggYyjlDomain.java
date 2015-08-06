package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_TZGG_YYJL is created by tools.
 * @author HJH
 */

public class BgTzggYyjlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // ֪ͨ�������
	private String czyDjxh;                          // ����Ա�Ǽ����
	private String ckrq;                             // �鿴����(��ʱ����)

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public BgTzggYyjlDomain() {
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
		try{
			return SysDateUtil.getYyyyMmdd(this.ckrq);
		}
		catch(Exception e){
			return this.ckrq;
		}
	}

	//���ò鿴����(��ʱ����)
	public void setCkrq(String ckrq) {
		this.ckrq=ckrq;
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
