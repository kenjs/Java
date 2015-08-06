package com.cy.cwgl.domain;

import java.util.ArrayList;
import java.util.List;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * THE DOMAIN FOR ����-�����ʲ���ˮ��¼
 * @author HCM
 *
 */
public class CwHbzcyeCxDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String cwDjxh;          //����Ǽ����(SEQ_CW_DJXH)
	private String zcflDm;          //�ʲ��������
	private String zcflMc;          //�ʲ���������
	private String je;              //���
	private String yhCshDjxh;       //���г�ʼ���Ǽ����
	private String ssJgbm;          //��������
	private String bmmc;            //��������
	private String yhmc;            //��������
	private String zh;              //�˺�
	private String hm;              //�û���
	
	private List<BaseBusinessDomain> dataList; 		//��ѯ�б�
	

	public String getCwDjxh() {
		return cwDjxh;
	}
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh = cwDjxh;
	}
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}
	public String getZcflMc() {
		return zcflMc;
	}
	public void setZcflMc(String zcflMc) {
		this.zcflMc = zcflMc;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getYhCshDjxh() {
		return yhCshDjxh;
	}
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
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
