package com.cy.zygl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_XLWH is created by tools.
 * @author HJH
 */

public class QyXlwhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ssJgbm;                           // ��������
	private String sfdXzqhDm;                        // ʼ����_������������
	private String mddXzqhDm;                        // Ŀ�ĵ�_������������
	private Double lcs;                              // �����
	private Double ddts;                             // �ﵽ����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String sfdMc;
	private String mddMc;
	private String sjMc;
	private String fhrXzqhDm;
	private String shrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhMc;
	
	private String sjJgbm;
	
	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getSfdMc() {
		return sfdMc;
	}

	public void setSfdMc(String sfdMc) {
		this.sfdMc = sfdMc;
	}

	public String getMddMc() {
		return mddMc;
	}

	public void setMddMc(String mddMc) {
		this.mddMc = mddMc;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyXlwhDomain() {
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡʼ����_������������
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//����ʼ����_������������
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//��ȡĿ�ĵ�_������������
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//����Ŀ�ĵ�_������������
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//��ȡ�����
	public Double getLcs() {
		return this.lcs;
	}

	//���������
	public void setLcs(Double lcs) {
		this.lcs=lcs;
	}

	//��ȡ�ﵽ����
	public Double getDdts() {
		return this.ddts;
	}

	//���ôﵽ����
	public void setDdts(Double ddts) {
		this.ddts=ddts;
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

	public String getSjJgbm() {
		return sjJgbm;
	}

	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm = sjJgbm;
	}
}
