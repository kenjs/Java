package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_DDDL.
 * @author HaoY
 */

public class BgDddlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dddlDjxh;                         // �����¼�Ǽ����(SEQ_BG_DJXH)
	private String jgbm;                             // ��������
	private String mc;                               // ����
	private String url;                              // ��ַ(������ַ)
	private String dlfsDm;                           // ��¼��ʽ����
	private String xjgxbz;                           // �¼������־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String dlfsMc;
	private String xjgxStr;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public BgDddlDomain() {
	}

	//��ȡ�����¼�Ǽ����(SEQ_BG_DJXH)
	public String getDddlDjxh() {
		return this.dddlDjxh;
	}

	//���õ����¼�Ǽ����(SEQ_BG_DJXH)
	public void setDddlDjxh(String dddlDjxh) {
		this.dddlDjxh=dddlDjxh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ����
	public String getMc() {
		return this.mc;
	}

	//��������
	public void setMc(String mc) {
		this.mc=mc;
	}

	//��ȡ��ַ(������ַ)
	public String getUrl() {
		return this.url;
	}

	//������ַ(������ַ)
	public void setUrl(String url) {
		this.url=url;
	}

	//��ȡ��¼��ʽ����
	public String getDlfsDm() {
		return this.dlfsDm;
	}

	//���õ�¼��ʽ����
	public void setDlfsDm(String dlfsDm) {
		this.dlfsDm=dlfsDm;
	}

	//��ȡ�¼������־(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//�����¼������־(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
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

	public String getDlfsMc() {
		return dlfsMc;
	}

	public void setDlfsMc(String dlfsMc) {
		this.dlfsMc = dlfsMc;
	}

	public String getXjgxStr() {
		if("Y".equals(xjgxbz)){
			xjgxStr = "��";
		}else {
			xjgxStr = "��";
		}
		return xjgxStr;
	}

	public void setXjgxStr(String xjgxStr) {
		this.xjgxStr = xjgxStr;
	}
}
