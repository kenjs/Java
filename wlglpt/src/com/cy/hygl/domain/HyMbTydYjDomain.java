package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_MB_TYD_YJ is created by tools.
 * @author HJH
 */

public class HyMbTydYjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String mbDjxh;                           // ģ��Ǽ����
	private Double yjHj;                             // �˼�_���˼�
	private Double yjYj;                             // �˼�_�½�
	private Double yjXf;                             // �˼�_�ָ�
	private Double yjHdf;                            // �˼�_������
	private Double yjThf;                            // �˼�_�����
	private Double yjHf;                             // �˼�_�ص���
	private Double yjHk;                             // �˼�_�ؿ�

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyMbTydYjDomain() {
	}

	//��ȡģ��Ǽ����
	public String getMbDjxh() {
		return this.mbDjxh;
	}

	//����ģ��Ǽ����
	public void setMbDjxh(String mbDjxh) {
		this.mbDjxh=mbDjxh;
	}

	//��ȡ�˼�_���˼�
	public Double getYjHj() {
		return this.yjHj;
	}

	//�����˼�_���˼�
	public void setYjHj(Double yjHj) {
		this.yjHj=yjHj;
	}

	//��ȡ�˼�_�½�
	public Double getYjYj() {
		return this.yjYj;
	}

	//�����˼�_�½�
	public void setYjYj(Double yjYj) {
		this.yjYj=yjYj;
	}

	//��ȡ�˼�_�ָ�
	public Double getYjXf() {
		return this.yjXf;
	}

	//�����˼�_�ָ�
	public void setYjXf(Double yjXf) {
		this.yjXf=yjXf;
	}

	//��ȡ�˼�_������
	public Double getYjHdf() {
		return this.yjHdf;
	}

	//�����˼�_������
	public void setYjHdf(Double yjHdf) {
		this.yjHdf=yjHdf;
	}

	//��ȡ�˼�_�����
	public Double getYjThf() {
		return this.yjThf;
	}

	//�����˼�_�����
	public void setYjThf(Double yjThf) {
		this.yjThf=yjThf;
	}

	//��ȡ�˼�_�ص���
	public Double getYjHf() {
		return this.yjHf;
	}

	//�����˼�_�ص���
	public void setYjHf(Double yjHf) {
		this.yjHf=yjHf;
	}

	//��ȡ�˼�_�ؿ�
	public Double getYjHk() {
		return this.yjHk;
	}

	//�����˼�_�ؿ�
	public void setYjHk(Double yjHk) {
		this.yjHk=yjHk;
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
