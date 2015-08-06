package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_KHYSGL is created by tools.
 * @author HJH
 */

public class CwKhysglDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String srDjxh;                           // ����Ǽ����(SEQ_SR_DJXH)
	private String khDjxh;                           // Ӧ��Ӧ���Ǽ����
	private String je;                               // �˷ѽ��㷽����
	private String yxbz;                             // �˷ѽ��㷽�Ǽ����
	private String djrDjxh;                          // �������
	private String djrq;                             // 
	private String djJgbm;                           // ����
	private String ssSsjg;                           // 
	private String xgrDjxh;                          // 
	private String xgrq;                             // 

	private String rqq;
	private String rqz;
	private String djBm;
	private String ssJgmc;
	private String khMc;
	private String djrMc;
	private String xgrMc;
	private String ssJgbm;
	private String czJe;
	private String zffs;
	private String zcfl;
	private String jbrCzyDjxh;
	private String jbRq;
	private String yhCshDjxh;
	private String yhhdh;
	private String bz;
	private List<CwKhysglMxDomain> mxList;
	private String jsonStr;
	private String xhs;
	private String tager;
	private String fhrMc;
	private String ssJgbm4Query;
	public String getSsJgbm4Query() {
		return ssJgbm4Query;
	}

	public void setSsJgbm4Query(String ssJgbm4Query) {
		this.ssJgbm4Query = ssJgbm4Query;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getXhs() {
		return xhs;
	}

	public void setXhs(String xhs) {
		this.xhs = xhs;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public List<CwKhysglMxDomain> getMxList() {
		if(mxList==null){
			mxList=new ArrayList<CwKhysglMxDomain>();
		}
		return mxList;
	}

	public void setMxList(List<CwKhysglMxDomain> mxList) {
		this.mxList = mxList;
	}

	public String getCzJe() {
		return czJe;
	}

	public void setCzJe(String czJe) {
		this.czJe = czJe;
	}

	public String getZffs() {
		return zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public String getZcfl() {
		return zcfl;
	}

	public void setZcfl(String zcfl) {
		this.zcfl = zcfl;
	}

	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}

	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}

	public String getJbRq() {
		return jbRq;
	}

	public void setJbRq(String jbRq) {
		this.jbRq = jbRq;
	}

	public String getYhCshDjxh() {
		return yhCshDjxh;
	}

	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}

	public String getYhhdh() {
		return yhhdh;
	}

	public void setYhhdh(String yhhdh) {
		this.yhhdh = yhhdh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getDjrMc() {
		return djrMc;
	}

	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getDjBm() {
		return djBm;
	}

	public void setDjBm(String djBm) {
		this.djBm = djBm;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getRqq() {
		return rqq;
	}

	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	public String getRqz() {
		return rqz;
	}

	public void setRqz(String rqz) {
		this.rqz = rqz;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public CwKhysglDomain() {
	}

	//��ȡ����Ǽ����(SEQ_SR_DJXH)
	public String getSrDjxh() {
		return this.srDjxh;
	}

	//��������Ǽ����(SEQ_SR_DJXH)
	public void setSrDjxh(String srDjxh) {
		this.srDjxh=srDjxh;
	}

	//��ȡӦ��Ӧ���Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//����Ӧ��Ӧ���Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ�˷ѽ��㷽����


	//��ȡ�˷ѽ��㷽�Ǽ����
	public String getYxbz() {
		return this.yxbz;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	//�����˷ѽ��㷽�Ǽ����
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�������
	public String getDjrDjxh() {
		return this.djrDjxh;
	}

	//���ø������
	public void setDjrDjxh(String djrDjxh) {
		this.djrDjxh=djrDjxh;
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

	//��ȡ����
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//��������
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ
	public String getSsSsjg() {
		return this.ssSsjg;
	}

	//����
	public void setSsSsjg(String ssSsjg) {
		this.ssSsjg=ssSsjg;
	}

	//��ȡ
	public String getXgrDjxh() {
		return this.xgrDjxh;
	}

	//����
	public void setXgrDjxh(String xgrDjxh) {
		this.xgrDjxh=xgrDjxh;
	}

	//��ȡ
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
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
