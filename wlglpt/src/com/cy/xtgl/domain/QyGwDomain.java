package com.cy.xtgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_GW is created by tools.
 * @author HJH
 */

public class QyGwDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gwDjxh;                           // ��λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	private String gwMc;                             // ��λ����
	private String gwJc;                             // ��λ���
	private String bzsm;                             // ��ע˵��
	private String lybz;                             // ��Դ��־(Y��ҵ����/N��λ����)
	private String gwDm;                               // ��λ����
	private String ssJgbm;                             // ������������(����)
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                         // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                         // �޸���
	private String xgrq;                             // �޸�����
	private String dwDjxh;
	private String bmMc;
	private List<BaseBusinessDomain> dataList; 		//��ѯ�б�
	private List<BaseBusinessDomain> xtgwList;

	private String cjrMc;
	private String xgrMc;
	private String xybz;

	private String lybzStr;
	private String qybzStr;
	
	private String str;
	
	public QyGwDomain() {
	}
	
	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getQybzStr() {
		if("Y".equals(qybz)){
			qybzStr = "����";
		}else{
			qybzStr = "ͣ��";
		}
		return qybzStr;
	}

	public void setQybzStr(String qybzStr) {
		this.qybzStr = qybzStr;
	}

	public String getLybzStr() {
		if("Y".equals(lybz)){
			lybzStr = "ϵͳ";
		}else{
			lybzStr = "�Խ�";
		}
		return lybzStr;
	}

	public void setLybzStr(String lybzStr) {
		this.lybzStr = lybzStr;
	}

	//��ȡ��λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	public String getGwDjxh() {
		return this.gwDjxh;
	}

	//���ø�λ����(SEQ_GW_DJXH��DM_GW.GW_DM)
	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh=gwDjxh;
	}

	//��ȡ��λ����
	public String getGwMc() {
		return this.gwMc;
	}

	//���ø�λ����
	public void setGwMc(String gwMc) {
		this.gwMc=gwMc;
	}

	//��ȡ��λ���
	public String getGwJc() {
		return this.gwJc;
	}

	//���ø�λ���
	public void setGwJc(String gwJc) {
		this.gwJc=gwJc;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ��Դ��־(Y��ҵ����/N��λ����)
	public String getLybz() {
		return this.lybz;
	}

	//������Դ��־(Y��ҵ����/N��λ����)
	public void setLybz(String lybz) {
		this.lybz=lybz;
	}

	//��ȡ��λ����
	public String getGwDm() {
		return this.gwDm;
	}

	//���ø�λ����
	public void setGwDm(String gwDm) {
		this.gwDm=gwDm;
	}

	//��ȡ������������(����)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//����������������(����)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ��������
	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	//���ô�������
	public void setCjrq(String cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//��ȡ�޸�����
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
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

	public String getXybz() {
		return xybz;
	}

	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

	public List<BaseBusinessDomain> getXtgwList() {
		if(xtgwList == null){
			xtgwList = new ArrayList<BaseBusinessDomain>();
		}
		return xtgwList;
	}

	public void setXtgwList(List<BaseBusinessDomain> xtgwList) {
		this.xtgwList = xtgwList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getDwDjxh() {
		return dwDjxh;
	}

	public void setDwDjxh(String dwDjxh) {
		this.dwDjxh = dwDjxh;
	}

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}
}
