package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_HBZC_CSH is created by tools.
 * @author HJH
 */

public class CwHbzcCshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cshDjxh;                          // ��ʼ���Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String zcflDm;                           // �ʲ��������
	private String yhmc;                             // ��������
	private String hm;                               // �û���
	private String zh;                               // �˺�
	private Double csje;                             // ��ʼ���
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String qyStr;    //���ñ�־
	private String error;
	private String bz;
	private String ywxh;
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private String yhTree;
	private String flTree;
	public String getYhTree() {
		return yhTree;
	}

	public void setYhTree(String yhTree) {
		this.yhTree = yhTree;
	}

	public String getFlTree() {
		return flTree;
	}

	public void setFlTree(String flTree) {
		this.flTree = flTree;
	}

	public String getQyStr() {
		if(qybz!=null){
			if(qybz.equals("Y")){
				qyStr="����";
			}
			else {
				qyStr="ͣ��";
			}
		}
		
		return qyStr;
	}

	public void setQyStr(String qyStr) {
		this.qyStr = qyStr;
	}

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String sjMc;
	private String flMc;
	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getFlMc() {
		return flMc;
	}

	public void setFlMc(String flMc) {
		this.flMc = flMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public CwHbzcCshDomain() {
	}

	//��ȡ��ʼ���Ǽ����(SEQ_CW_DJXH)
	public String getCshDjxh() {
		return this.cshDjxh;
	}

	//���ó�ʼ���Ǽ����(SEQ_CW_DJXH)
	public void setCshDjxh(String cshDjxh) {
		this.cshDjxh=cshDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�ʲ��������
	public String getZcflDm() {
		return this.zcflDm;
	}

	//�����ʲ��������
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//��ȡ��������
	public String getYhmc() {
		return this.yhmc;
	}

	//������������
	public void setYhmc(String yhmc) {
		this.yhmc=yhmc;
	}

	//��ȡ�û���
	public String getHm() {
		return this.hm;
	}

	//�����û���
	public void setHm(String hm) {
		this.hm=hm;
	}

	//��ȡ�˺�
	public String getZh() {
		return this.zh;
	}

	//�����˺�
	public void setZh(String zh) {
		this.zh=zh;
	}

	//��ȡ��ʼ���
	public Double getCsje() {
		return this.csje;
	}

	//���ó�ʼ���
	public void setCsje(Double csje) {
		this.csje=csje;
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
