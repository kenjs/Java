package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_JCBM_JLDW is created by tools.
 * @author HaoY
 */

public class QyJcbmJldwDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String jldwDm;                           // ������λ����
	private String jbdwbz;                           // 
	private Double hsbl;                             // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String jbdwStr;
	private String jldwMc;
	private String jldwJc;
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String czzt;
	private String jldwFlDm;
	private String strObj;
	private String zt;
	
	private String jldwFlMc;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<QyJcbmJldwDomain> jldwList;
	
	public String getCzzt() {
		return czzt;
	}

	public void setCzzt(String czzt) {
		this.czzt = czzt;
	}

	public String getJldwMc() {
		return jldwMc;
	}

	public void setJldwMc(String jldwMc) {
		this.jldwMc = jldwMc;
	}

	public String getStrObj() {
		return strObj;
	}

	public void setStrObj(String strObj) {
		this.strObj = strObj;
	}

	public QyJcbmJldwDomain() {
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ������λ����
	public String getJldwDm() {
		return this.jldwDm;
	}

	//���ü�����λ����
	public void setJldwDm(String jldwDm) {
		this.jldwDm=jldwDm;
	}

	//��ȡ
	public String getJbdwbz() {
		return this.jbdwbz;
	}

	//����
	public void setJbdwbz(String jbdwbz) {
		this.jbdwbz=jbdwbz;
	}

	//��ȡ
	public Double getHsbl() {
		return this.hsbl;
	}

	//����
	public void setHsbl(Double hsbl) {
		this.hsbl=hsbl;
	}

	//��ȡ
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//����
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//����
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
	}

	public String getZt() {
		if("Y".equals(qybz)){
			zt = "������";
		}else{
			zt = "δ����";
		}
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
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

	public String getJldwFlDm() {
		return jldwFlDm;
	}

	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm = jldwFlDm;
	}

	public String getJbdwStr() {
		if("Y".equals(jbdwbz)){
			jbdwStr = "��";
		}else{
			jbdwStr = "��";
		}
		return jbdwStr;
	}

	public void setJbdwStr(String jbdwStr) {
		this.jbdwStr = jbdwStr;
	}

	public String getJldwJc() {
		return jldwJc;
	}

	public void setJldwJc(String jldwJc) {
		this.jldwJc = jldwJc;
	}

	public List<QyJcbmJldwDomain> getJldwList() {
		if(jldwList == null){
			jldwList = new ArrayList<QyJcbmJldwDomain>();
		}
		return jldwList;
	}

	public void setJldwList(List<QyJcbmJldwDomain> jldwList) {
		this.jldwList = jldwList;
	}

	public String getJldwFlMc() {
		return jldwFlMc;
	}

	public void setJldwFlMc(String jldwFlMc) {
		this.jldwFlMc = jldwFlMc;
	}

}
