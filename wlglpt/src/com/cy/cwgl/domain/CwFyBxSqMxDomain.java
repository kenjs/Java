package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_FYLB is created by tools.
 * @author HJH
 */

public class CwFyBxSqMxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String mxDjxh;
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String jgMc;
	private String fylbDjxh;
	private String fyxmDjxh;
	private String fyJe;
	private String bxJe;
	private String bz;
	public String getJgMc() {
		return jgMc;
	}

	public void setJgMc(String jgMc) {
		this.jgMc = jgMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��������(�ܹ�˾)
	

	//��ȡ�����������
	

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	public String getMxDjxh() {
		return mxDjxh;
	}

	public void setMxDjxh(String mxDjxh) {
		this.mxDjxh = mxDjxh;
	}

	public String getFylbDjxh() {
		return fylbDjxh;
	}

	public void setFylbDjxh(String fylbDjxh) {
		this.fylbDjxh = fylbDjxh;
	}

	public String getFyxmDjxh() {
		return fyxmDjxh;
	}

	public void setFyxmDjxh(String fyxmDjxh) {
		this.fyxmDjxh = fyxmDjxh;
	}

	public String getFyJe() {
		return fyJe;
	}

	public void setFyJe(String fyJe) {
		this.fyJe = fyJe;
	}

	public String getBxJe() {
		return bxJe;
	}

	public void setBxJe(String bxJe) {
		this.bxJe = bxJe;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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
