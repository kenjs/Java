package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_FBS_YH is created by tools.
 * @author HJH
 */

public class QyFbsYhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String yhDjxh;                           // ����
	private String qybm;                             // ��ҵ����
	private String ssJgbm;                           // ������˾
	private String fbsDjxh;                          // �����ְ���
	private String mc;                               // ����
	private String zh;                               // �˺�
	private String pwd;                              // ����
	private String dlyzfsDm;                         // ��¼��֤��ʽ����
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrq;                             // ��������
	private String cjrCzyDjxh;                       // ������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String qybzStr;                          // ���ñ�־��ʾ�У���״̬�У�
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String dlyzfsMc;                         // ��¼��֤��ʽ����
	private String pwd1;                             // ȷ������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyFbsYhDomain() {
	}

	//��ȡ
	public String getYhDjxh() {
		return this.yhDjxh;
	}

	//����
	public void setYhDjxh(String yhDjxh) {
		this.yhDjxh=yhDjxh;
	}

	//��ȡ��ҵ����
	public String getQybm() {
		return this.qybm;
	}

	//������ҵ����
	public void setQybm(String qybm) {
		this.qybm=qybm;
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ
	public String getFbsDjxh() {
		return this.fbsDjxh;
	}

	//����
	public void setFbsDjxh(String fbsDjxh) {
		this.fbsDjxh=fbsDjxh;
	}

	//��ȡ����
	public String getMc() {
		return this.mc;
	}

	//��������
	public void setMc(String mc) {
		this.mc=mc;
	}

	//��ȡ�˺�
	public String getZh() {
		return this.zh;
	}

	//�����˺�
	public void setZh(String zh) {
		this.zh=zh;
	}

	//��ȡ����
	public String getPwd() {
		return this.pwd;
	}

	//��������
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	//��ȡ��¼��֤��ʽ����
	public String getDlyzfsDm() {
		return this.dlyzfsDm;
	}

	//���õ�¼��֤��ʽ����
	public void setDlyzfsDm(String dlyzfsDm) {
		this.dlyzfsDm=dlyzfsDm;
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

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
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

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getDlyzfsMc() {
		return dlyzfsMc;
	}

	public void setDlyzfsMc(String dlyzfsMc) {
		this.dlyzfsMc = dlyzfsMc;
	}

	public String getQybzStr() {
		if ("Y".equals(qybz))
			qybzStr = "����";
		else
			qybzStr = "ͣ��";
		return qybzStr;
	}

	public void setQybzStr(String qybzStr) {
		this.qybzStr = qybzStr;
	}
}
