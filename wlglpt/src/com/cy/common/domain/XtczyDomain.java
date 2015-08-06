package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR YYS_DM_USER_BCXX is created by tools.
 * @author HJH
 */

public class XtczyDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czryDm;                           // ������Ա����(YHBZ=YΪCTAIS�е�CZRY_DM,����SEQ_CZY_XH)
	private String czryMc;                           // ������Ա����
	private String name;                             // ��¼�û���
	private String password;                         // ��¼����
	private String yhsm;                             // �û�˵��
	private String yhbz;                             // �û�����(0 ��������Ա , 1 ����Ա,2 һ���û�,3 �Խ��û�)
	private String yhjb;							 //����Ա����(0 ��������Ա��1 һ������Ա��2 ��������Ա��3 ��������Ա��....)
	private String swjgDm;                           // ˰����ش���
	private String cjglybz;                          // ��������Ա��־(Y/N)
	private String ssglybz;							 //˰�չ���Ա��־
	private String  spbz;							 //����Ȩ�ޱ�־��1 ��������Ա��0 ��nullΪ�ǣ�
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	
	private String swjgMc;                           // ˰���������
	private String cjglybzMc;						 //��������Ա��־���� �� ����
	private String qybzMc;						     //���ñ�־���� �� ����
	private String yxbzMc;						     //��Ч��־���� �� ����
	
	private List<XtczyDomain> dataList;		 //��ѯ�����	
	private List swjgList;							//����˰�����

	public List getSwjgList() {
		if (swjgList == null)
			return swjgList = new ArrayList();
		return swjgList;
	}

	public void setSwjgList(List swjgList) {
		this.swjgList = swjgList;
	}

	public String getSwjgMc() {
		return swjgMc;
	}

	public void setSwjgMc(String swjgMc) {
		this.swjgMc = swjgMc;
	}

	public XtczyDomain() {
	}

	//��ȡ������Ա����(YHBZ=YΪCTAIS�е�CZRY_DM,����SEQ_CZY_XH)
	public String getCzryDm() {
		return this.czryDm;
	}

	//���ò�����Ա����(YHBZ=YΪCTAIS�е�CZRY_DM,����SEQ_CZY_XH)
	public void setCzryDm(String czryDm) {
		this.czryDm=czryDm;
	}

	//��ȡ������Ա����
	public String getCzryMc() {
		return this.czryMc;
	}

	//���ò�����Ա����
	public void setCzryMc(String czryMc) {
		this.czryMc=czryMc;
	}

	//��ȡ��¼�û���
	public String getName() {
		return this.name;
	}

	//���õ�¼�û���
	public void setName(String name) {
		this.name=name;
	}

	//��ȡ��¼����
	public String getPassword() {
		return this.password;
	}

	//���õ�¼����
	public void setPassword(String password) {
		this.password=password;
	}

	//��ȡ�û�˵��
	public String getYhsm() {
		return this.yhsm;
	}

	//�����û�˵��
	public void setYhsm(String yhsm) {
		this.yhsm=yhsm;
	}

	//��ȡCTAIS�û���־(Y/N)
	public String getYhbz() {
		return this.yhbz;
	}

	//����CTAIS�û���־(Y/N)
	public void setYhbz(String yhbz) {
		this.yhbz=yhbz;
	}

	//��ȡ˰����ش���
	public String getSwjgDm() {
		return this.swjgDm;
	}

	//����˰����ش���
	public void setSwjgDm(String swjgDm) {
		this.swjgDm=swjgDm;
	}

	//��ȡ��������Ա��־(Y/N)
	public String getCjglybz() {
		return this.cjglybz;
	}

	//���ó�������Ա��־(Y/N)
	public void setCjglybz(String cjglybz) {
		this.cjglybz=cjglybz;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public List<XtczyDomain> getDataList() {
		if(dataList == null )
			return dataList = new ArrayList<XtczyDomain>();
		return dataList;
	}

	public void setDataList(List<XtczyDomain> dataList) {
		this.dataList = dataList;
	}

	public String getCjglybzMc() {
		return cjglybzMc;
	}

	public void setCjglybzMc(String cjglybzMc) {
		this.cjglybzMc = cjglybzMc;
	}

	public String getQybzMc() {
		return qybzMc;
	}

	public void setQybzMc(String qybzMc) {
		this.qybzMc = qybzMc;
	}

	public String getYxbzMc() {
		return yxbzMc;
	}

	public void setYxbzMc(String yxbzMc) {
		this.yxbzMc = yxbzMc;
	}

	public String getYhjb() {
		return yhjb;
	}

	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}

	public String getSsglybz() {
		return ssglybz;
	}

	public void setSsglybz(String ssglybz) {
		this.ssglybz = ssglybz;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
}