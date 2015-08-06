package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_JS is created by tools.
 * @author ylp
 * @since 2013-1-10 ����8:32:41 
 * @version
 */

public class QyJsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDjxh;                           // ��ɫ����(SEQ_JS_DJXH��DM_JS.JS_DM)
	private String jsMc;                             // ��ɫ����
	private String jsJc;                             // ��ɫ���
	private String jsDm;                             // ��ɫ����
	private String lybz;                             // ��Դ��־(Y��ҵ����/N��ɫ����)
	private String bzsm;                             // ��ע˵��
	private String ssJgbm;                           // ������������(�ܹ�˾��ֹ�˾���ǲ���)
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String qystr;							 //��ʾ���ñ�־
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String sjMc;    						 //�ϼ�����
	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyJsDomain() {
	}

	//��ȡ��ɫ����(SEQ_JS_DJXH��DM_JS.JS_DM)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//���ý�ɫ����(SEQ_JS_DJXH��DM_JS.JS_DM)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//��ȡ��ɫ����
	public String getJsMc() {
		return this.jsMc;
	}

	//���ý�ɫ����
	public void setJsMc(String jsMc) {
		this.jsMc=jsMc;
	}

	//��ȡ��ɫ���
	public String getJsJc() {
		return this.jsJc;
	}

	//���ý�ɫ���
	public void setJsJc(String jsJc) {
		this.jsJc=jsJc;
	}

	//��ȡ��ɫ����
	public String getJsDm() {
		return this.jsDm;
	}

	//���ý�ɫ����
	public void setJsDm(String jsDm) {
		this.jsDm=jsDm;
	}

	//��ȡ��Դ��־(Y��ҵ����/N��ɫ����)
	public String getLybz() {
		return this.lybz;
	}

	//������Դ��־(Y��ҵ����/N��ɫ����)
	public void setLybz(String lybz) {
		this.lybz=lybz;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ������������(�ܹ�˾��ֹ�˾���ǲ���)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//����������������(�ܹ�˾��ֹ�˾���ǲ���)
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
	
	public String getQystr() {
		if(qybz!=null)
		{
			if(qybz.equals("Y"))
			{
				qystr="����";
			}
			else {
				qystr="ͣ��";
			}
		}
		return qystr;
	}

	public void setQystr(String qystr) {
		this.qystr = qystr;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	
	
}
