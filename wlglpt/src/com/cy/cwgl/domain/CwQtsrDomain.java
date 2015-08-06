package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_QTSR is created by tools.
 * @author HJH
 */

public class CwQtsrDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String rq;                               // ����
	private String xmmc;                             // ��Ŀ����
	private Double je;                               // ���
	private String zcflDm;                           // �ʲ��������
	private String yhCshDjxh;                        // ���г�ʼ���Ǽ����
	private String bz;                               // ��ע
	private String djJgbm;
	private String fkf;
	private String yxbz;                              
	private List<String> cwDjxhs;
	private List<String> xmmcs;
	private List<Double> jes;
	private List<String> zcflDms;
	private List<String> yhCshDjxhs;
	private List<String> bzs;
	private List<String> fkfs;
	
	
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public CwQtsrDomain() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ����
	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}

	//��������
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ��Ŀ����
	public String getXmmc() {
		return this.xmmc;
	}

	//������Ŀ����
	public void setXmmc(String xmmc) {
		this.xmmc=xmmc;
	}

	//��ȡ���
	public Double getJe() {
		return this.je;
	}

	//���ý��
	public void setJe(Double je) {
		this.je=je;
	}

	//��ȡ�ʲ��������
	public String getZcflDm() {
		return this.zcflDm;
	}

	//�����ʲ��������
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//��ȡ���г�ʼ���Ǽ����
	public String getYhCshDjxh() {
		return this.yhCshDjxh;
	}

	//�������г�ʼ���Ǽ����
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh=yhCshDjxh;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
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

	public List<String> getCwDjxhs() {
		return cwDjxhs;
	}

	public void setCwDjxhs(List<String> cwDjxhs) {
		this.cwDjxhs = cwDjxhs;
	}

	public List<String> getXmmcs() {
		return xmmcs;
	}

	public void setXmmcs(List<String> xmmcs) {
		this.xmmcs = xmmcs;
	}

	public List<Double> getJes() {
		return jes;
	}

	public void setJes(List<Double> jes) {
		this.jes = jes;
	}

	public List<String> getZcflDms() {
		return zcflDms;
	}

	public void setZcflDms(List<String> zcflDms) {
		this.zcflDms = zcflDms;
	}

	public List<String> getYhCshDjxhs() {
		return yhCshDjxhs;
	}

	public void setYhCshDjxhs(List<String> yhCshDjxhs) {
		this.yhCshDjxhs = yhCshDjxhs;
	}

	public List<String> getBzs() {
		return bzs;
	}

	public void setBzs(List<String> bzs) {
		this.bzs = bzs;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getFkf() {
		return fkf;
	}

	public void setFkf(String fkf) {
		this.fkf = fkf;
	}

	public List<String> getFkfs() {
		return fkfs;
	}

	public void setFkfs(List<String> fkfs) {
		this.fkfs = fkfs;
	}
}
