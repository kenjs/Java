package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_FYXM_WH is created by tools.
 * @author HJH
 */

public class CwFyxmWhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������(�ܹ�˾)
	private String fylbCwDjxh;                       // �������_����Ǽ����
	private String fyxmMc;                           // ������Ŀ����
	private String splcXmflDjxh;                     // ��������_��Ŀ����Ǽ����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private String jgMc;
	private String lcMc;
	private String lbMc;
	private String xmFl;
	public String getXmFl() {
		return xmFl;
	}

	public void setXmFl(String xmFl) {
		this.xmFl = xmFl;
	}

	public String getJgMc() {
		return jgMc;
	}

	public void setJgMc(String jgMc) {
		this.jgMc = jgMc;
	}

	public String getLcMc() {
		return lcMc;
	}

	public void setLcMc(String lcMc) {
		this.lcMc = lcMc;
	}

	public String getLbMc() {
		return lbMc;
	}

	public void setLbMc(String lbMc) {
		this.lbMc = lbMc;
	}

	public CwFyxmWhDomain() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��������(�ܹ�˾)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������(�ܹ�˾)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�������_����Ǽ����
	public String getFylbCwDjxh() {
		return this.fylbCwDjxh;
	}

	//���÷������_����Ǽ����
	public void setFylbCwDjxh(String fylbCwDjxh) {
		this.fylbCwDjxh=fylbCwDjxh;
	}

	//��ȡ������Ŀ����
	public String getFyxmMc() {
		return this.fyxmMc;
	}

	//���÷�����Ŀ����
	public void setFyxmMc(String fyxmMc) {
		this.fyxmMc=fyxmMc;
	}

	//��ȡ��������_��Ŀ����Ǽ����
	public String getSplcXmflDjxh() {
		return this.splcXmflDjxh;
	}

	//������������_��Ŀ����Ǽ����
	public void setSplcXmflDjxh(String splcXmflDjxh) {
		this.splcXmflDjxh=splcXmflDjxh;
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

	public String getCjrMc() {
		return this.cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return this.xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
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
