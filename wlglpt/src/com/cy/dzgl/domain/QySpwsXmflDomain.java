package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SPWS_XMFL is created by tools.
 * @author HJH
 */

public class QySpwsXmflDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xmflDjxh;                         // ��Ŀ����Ǽ����(SEQ_XMFL_DJXH)
	private String jgbm;                             // ��������
	private String wsDm;                             // �������
	private String xmflmc;                           // ��Ŀ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String wsMc;							//��������
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QySpwsXmflDomain() {
	}

	//��ȡ��Ŀ����Ǽ����(SEQ_XMFL_DJXH)
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//������Ŀ����Ǽ����(SEQ_XMFL_DJXH)
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ�������
	public String getWsDm() {
		return this.wsDm;
	}

	//�����������
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//��ȡ��Ŀ��������
	public String getXmflmc() {
		return this.xmflmc;
	}

	//������Ŀ��������
	public void setXmflmc(String xmflmc) {
		this.xmflmc=xmflmc;
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

	public String getWsMc() {
		return wsMc;
	}

	public void setWsMc(String wsMc) {
		this.wsMc = wsMc;
	}
}
