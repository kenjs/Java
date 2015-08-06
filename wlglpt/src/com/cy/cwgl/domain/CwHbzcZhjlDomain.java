package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_HBZC_ZHJL is created by tools.
 * @author HJH
 */

public class CwHbzcZhjlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;                           // ��������
	private String oldZcflDm;                        // ԭ-�ʲ��������
	private String oldYhCshDjxh;                     // ԭ-���г�ʼ���Ǽ����
	private String newZcflDm;                        // Ŀ��-�ʲ��������
	private String newYhCshDjxh;                     // Ŀ��-���г�ʼ���Ǽ����
	private String zhje;                             // ת�����
	private String pzh;                              // ƾ֤��
	private String bzsm;                             // ��ע˵��
	private String yxbz;                             // ��Ч��־(Y/N)
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	
	private String oldZcflMc;
	private String oldYhCshMc;
	private String oldYe;
	private String newZcflMc;
	private String newYhCshMc;
	private String newYe;
	
	private String cwbdDjxh;                         // ����䶯�Ǽ����(SEQ_CW_DJXH)
	private String sm;                               // ˵��
	private String jbrCzyDjxh;                       // ������
	private String rq;                               // ����   
	private String jbrCzyMc;
	private String yhDjxh;
	private String zcflDm;                           // �ʲ��������
	private String yhCshDjxh;                        // ���г�ʼ���Ǽ����
	private String je;                               // ���
	private String bdJe;
	private String bz;
	private String bzmc;
	private String ywxh;

	
	private String oldHm;
	private String oldZh;
	private String newHm;
	
	/********��ѯ����*********/
	private String lb;
	private String yhzh;
	private String rqQ;
	private String rqZ;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public CwHbzcZhjlDomain() {
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

	//��ȡԭ-�ʲ��������
	public String getOldZcflDm() {
		return this.oldZcflDm;
	}

	//����ԭ-�ʲ��������
	public void setOldZcflDm(String oldZcflDm) {
		this.oldZcflDm=oldZcflDm;
	}

	//��ȡԭ-���г�ʼ���Ǽ����
	public String getOldYhCshDjxh() {
		return this.oldYhCshDjxh;
	}

	//����ԭ-���г�ʼ���Ǽ����
	public void setOldYhCshDjxh(String oldYhCshDjxh) {
		this.oldYhCshDjxh=oldYhCshDjxh;
	}

	//��ȡĿ��-�ʲ��������
	public String getNewZcflDm() {
		return this.newZcflDm;
	}

	//����Ŀ��-�ʲ��������
	public void setNewZcflDm(String newZcflDm) {
		this.newZcflDm=newZcflDm;
	}

	//��ȡĿ��-���г�ʼ���Ǽ����
	public String getNewYhCshDjxh() {
		return this.newYhCshDjxh;
	}

	//����Ŀ��-���г�ʼ���Ǽ����
	public void setNewYhCshDjxh(String newYhCshDjxh) {
		this.newYhCshDjxh=newYhCshDjxh;
	}

	//��ȡת�����
	public String getZhje() {
		return this.zhje;
	}

	//����ת�����
	public void setZhje(String zhje) {
		this.zhje=zhje;
	}

	//��ȡƾ֤��
	public String getPzh() {
		return this.pzh;
	}

	//����ƾ֤��
	public void setPzh(String pzh) {
		this.pzh=pzh;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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

	public String getOldZcflMc() {
		return oldZcflMc;
	}

	public void setOldZcflMc(String oldZcflMc) {
		this.oldZcflMc = oldZcflMc;
	}

	public String getOldYhCshMc() {
		return oldYhCshMc;
	}

	public void setOldYhCshMc(String oldYhCshMc) {
		this.oldYhCshMc = oldYhCshMc;
	}

	public String getOldYe() {
		return oldYe;
	}

	public void setOldYe(String oldYe) {
		this.oldYe = oldYe;
	}

	public String getNewZcflMc() {
		return newZcflMc;
	}

	public void setNewZcflMc(String newZcflMc) {
		this.newZcflMc = newZcflMc;
	}

	public String getNewYhCshMc() {
		return newYhCshMc;
	}

	public void setNewYhCshMc(String newYhCshMc) {
		this.newYhCshMc = newYhCshMc;
	}

	public String getNewYe() {
		return newYe;
	}

	public void setNewYe(String newYe) {
		this.newYe = newYe;
	}

	public String getCwbdDjxh() {
		return cwbdDjxh;
	}

	public void setCwbdDjxh(String cwbdDjxh) {
		this.cwbdDjxh = cwbdDjxh;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}

	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getJbrCzyMc() {
		return jbrCzyMc;
	}

	public void setJbrCzyMc(String jbrCzyMc) {
		this.jbrCzyMc = jbrCzyMc;
	}

	public String getZcflDm() {
		return zcflDm;
	}

	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}

	public String getYhCshDjxh() {
		return yhCshDjxh;
	}

	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getRqQ() {
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getYhDjxh() {
		return yhDjxh;
	}

	public void setYhDjxh(String yhDjxh) {
		this.yhDjxh = yhDjxh;
	}

	public String getBdJe() {
		return bdJe;
	}

	public void setBdJe(String bdJe) {
		this.bdJe = bdJe;
	}

	public String getOldHm() {
		return oldHm;
	}

	public void setOldHm(String oldHm) {
		this.oldHm = oldHm;
	}

	public String getOldZh() {
		return oldZh;
	}

	public void setOldZh(String oldZh) {
		this.oldZh = oldZh;
	}

	public String getNewHm() {
		return newHm;
	}

	public void setNewHm(String newHm) {
		this.newHm = newHm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

}
