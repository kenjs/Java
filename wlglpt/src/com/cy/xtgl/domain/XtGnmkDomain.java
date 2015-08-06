package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR XT_GNMK is created by tools.
 * @author HJH
 */

public class XtGnmkDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gnmkDm;                           // ����ģ�����(SEQ_GNMK_XH)
	private String gnmkMc;                           // ����ģ������
	private String gnmkBz;                           // ����ģ�鱸ע
	private String url;                              // URL
	private String urlHelp;                          // URL_HELP
	private String parmbz;                           // ������־(Y/N)
	private String xybz;                             // ѡ�ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrDm;                            // ������
	private String cjrq;                             // ��������
	private String xgrDm;                            // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public XtGnmkDomain() {
	}

	//��ȡ����ģ�����(SEQ_GNMK_XH)
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//���ù���ģ�����(SEQ_GNMK_XH)
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}

	//��ȡ����ģ������
	public String getGnmkMc() {
		return this.gnmkMc;
	}

	//���ù���ģ������
	public void setGnmkMc(String gnmkMc) {
		this.gnmkMc=gnmkMc;
	}

	//��ȡ����ģ�鱸ע
	public String getGnmkBz() {
		return this.gnmkBz;
	}

	//���ù���ģ�鱸ע
	public void setGnmkBz(String gnmkBz) {
		this.gnmkBz=gnmkBz;
	}

	//��ȡURL
	public String getUrl() {
		return this.url;
	}

	//����URL
	public void setUrl(String url) {
		this.url=url;
	}

	//��ȡURL_HELP
	public String getUrlHelp() {
		return this.urlHelp;
	}

	//����URL_HELP
	public void setUrlHelp(String urlHelp) {
		this.urlHelp=urlHelp;
	}

	//��ȡ������־(Y/N)
	public String getParmbz() {
		return this.parmbz;
	}

	//���ò�����־(Y/N)
	public void setParmbz(String parmbz) {
		this.parmbz=parmbz;
	}

	//��ȡѡ�ñ�־(Y/N)
	public String getXybz() {
		return this.xybz;
	}

	//����ѡ�ñ�־(Y/N)
	public void setXybz(String xybz) {
		this.xybz=xybz;
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
	public String getCjrDm() {
		return this.cjrDm;
	}

	//���ô�����
	public void setCjrDm(String cjrDm) {
		this.cjrDm=cjrDm;
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
	public String getXgrDm() {
		return this.xgrDm;
	}

	//�����޸���
	public void setXgrDm(String xgrDm) {
		this.xgrDm=xgrDm;
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
