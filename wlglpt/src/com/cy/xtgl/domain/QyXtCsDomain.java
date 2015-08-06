package com.cy.xtgl.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.cy.framework.domain.BaseBusinessDomain;
/**
 * THE DOMAIN FOR ��ҵ-ϵͳ-������Ϣ
 * @author HCM
 */
public class QyXtCsDomain extends BaseBusinessDomain{
	private static final long serialVersionUID = 1L;
	private String ssJgbm;                         // ��������ҳ�洫�����Ļ�������(�������)
	private String ssJgmc;                         // ��������ҳ�洫�����Ļ�������
	private String jgbm;                           //��������
	private String dwmc;                           //��λ����
	private String jbdm;                           //�������
	private String csxh;                           //�������
	private String cslbDm;                         //����������
	private String csmc;                           //��������
	private String sysm;                           //ʹ��˵��
	private String sjxlbDm;                        //������������
	private String xzxmDm;                         //ѡ����Ŀ
	private String csz;                            //����ֵ

	private List<BaseBusinessDomain> dataList; 		//��ѯ�б�
	
	private List<DmXzxmDomain> dmXzxmList;                //������Ŀlist
	private String xzxmValueMc;
	private String xzxmValueDm;

	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}
	public String getJgbm() {
		return jgbm;
	}
	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getJbdm() {
		return jbdm;
	}
	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}
	public String getCsxh() {
		return csxh;
	}
	public void setCsxh(String csxh) {
		this.csxh = csxh;
	}
	public String getCslbDm() {
		return cslbDm;
	}
	public void setCslbDm(String cslbDm) {
		this.cslbDm = cslbDm;
	}
	public String getCsmc() {
		return csmc;
	}
	public void setCsmc(String csmc) {
		this.csmc = csmc;
	}
	public String getSysm() {
		return sysm;
	}
	public void setSysm(String sysm) {
		this.sysm = sysm;
	}
	public String getSjxlbDm() {
		return sjxlbDm;
	}
	public void setSjxlbDm(String sjxlbDm) {
		this.sjxlbDm = sjxlbDm;
	}
	public String getXzxmDm() {
		return xzxmDm;
	}
	public void setXzxmDm(String xzxmDm) {
		this.xzxmDm = xzxmDm;
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz;
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
	public List<DmXzxmDomain> getDmXzxmList() {
		if(dmXzxmList==null){
			dmXzxmList=new ArrayList<DmXzxmDomain>();
		}
		return dmXzxmList;
	}
	public void setDmXzxmList(List<DmXzxmDomain> dmXzxmList) {
		this.dmXzxmList = dmXzxmList;
	}

	public String getXzxmValueMc() {
		return xzxmValueMc;
	}
	public void setXzxmValueMc(String xzxmValueMc) {
		this.xzxmValueMc = xzxmValueMc;
	}
	public String getXzxmValueDm() {
		return xzxmValueDm;
	}
	public void setXzxmValueDm(String xzxmValueDm) {
		this.xzxmValueDm = xzxmValueDm;
	}
}
