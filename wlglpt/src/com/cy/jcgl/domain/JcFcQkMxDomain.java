package com.cy.jcgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

public class JcFcQkMxDomain extends BaseBusinessDomain {
	private static final long serialVersionUID = 1L;
	private String pcrq;
	private String pcr;
	private String clhm;
	private String cx;
	private String zl;
	private String tj;
	private String xj;
	private String xf;
	private String df;
	private String hf;
	private String th;
	private String ys;
	private String ps;
	private String thZb;
	private String ml;
	private String mln;
	private String sjJgbm;
	private String sjFgs;
	private String rqq;
	private String rqz;
	
	private String pcDjxh;								//�ɳ��Ǽ����
	private String pcrMc;								//�ɳ���
	private String shrMc;								//�ջ���
	private String shrDz;								//�ջ��˵�ַ
	private String sjyf;								//˾���˷�
	private String yfyf;								//Ԥ���˷�
	private String zcHj;								//֧���ϼ�
	private String zcXf;								//֧���ָ�
	private String spzt;								//����״̬
	private String xdrq;								//�µ�����
	private String skfs;								//�µ�����
	private String ssJgbm; 								//������������
	private String pcdh; 								//�ɳ�����
	private String tydh; 								//���˵���
	private String hdh; 								//�ص���
	private String fhrDjxh; 							//�ͻ��Ǽ����
	private String fhrMc; 								//�ͻ�����
	private String sfd; 								//ʼ����
	private String mdd; 								//Ŀ�ĵ�
	private String hwmc; 								//�ͻ�����
	private String sl; 									//����
	private String yjjsfsMc;							//���㷽ʽ
	private String wfhDjxh;								//δ����
	private String xh;									//������ϸ���
	private String ddDjxh;								//�����Ǽ����
	private String khmc;								//�ͻ�����
	private String pcJgmc;								//�ɳ���������
	private String ssJgmc;								//������������
	private String pcfs;								//�ɳ���ʽ
	private int pageXh;
	
	private List<BaseBusinessDomain> dataList;
	
	public String getPcdh() {
		return pcdh;
	}
	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}
	public String getPcrq() {
		return pcrq;
	}
	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}
	public String getPcr() {
		return pcr;
	}
	public void setPcr(String pcr) {
		this.pcr = pcr;
	}
	public String getClhm() {
		return clhm;
	}
	public void setClhm(String clhm) {
		this.clhm = clhm;
	}
	public String getCx() {
		return cx;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getXj() {
		return xj;
	}
	public void setXj(String xj) {
		this.xj = xj;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public String getHf() {
		return hf;
	}
	public void setHf(String hf) {
		this.hf = hf;
	}
	public String getTh() {
		return th;
	}
	public void setTh(String th) {
		this.th = th;
	}
	public String getYs() {
		return ys;
	}
	public void setYs(String ys) {
		this.ys = ys;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getThZb() {
		return thZb;
	}
	public void setThZb(String thZb) {
		this.thZb = thZb;
	}
	public String getMl() {
		return ml;
	}
	public void setMl(String ml) {
		this.ml = ml;
	}
	public String getMln() {
		return mln;
	}
	public void setMln(String mln) {
		this.mln = mln;
	}
	public String getSjJgbm() {
		return sjJgbm;
	}
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm = sjJgbm;
	}
	public String getSjFgs() {
		return sjFgs;
	}
	public void setSjFgs(String sjFgs) {
		this.sjFgs = sjFgs;
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
	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getTydh() {
		return tydh;
	}
	public void setTydh(String tydh) {
		this.tydh = tydh;
	}
	public String getHdh() {
		return hdh;
	}
	public void setHdh(String hdh) {
		this.hdh = hdh;
	}
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getSfd() {
		return sfd;
	}
	public void setSfd(String sfd) {
		this.sfd = sfd;
	}
	public String getMdd() {
		return mdd;
	}
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}
	public String getHwmc() {
		return hwmc;
	}
	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public int getPageXh() {
		return pageXh;
	}
	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}
	public String getPcrMc() {
		return pcrMc;
	}
	public void setPcrMc(String pcrMc) {
		this.pcrMc = pcrMc;
	}
	public String getShrMc() {
		return shrMc;
	}
	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}
	public String getSjyf() {
		return sjyf;
	}
	public void setSjyf(String sjyf) {
		this.sjyf = sjyf;
	}
	public String getYfyf() {
		return yfyf;
	}
	public void setYfyf(String yfyf) {
		this.yfyf = yfyf;
	}
	public String getSpzt() {
		return spzt;
	}
	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}
	public String getXdrq() {
		return xdrq;
	}
	public void setXdrq(String xdrq) {
		this.xdrq = xdrq;
	}
	public String getSkfs() {
		return skfs;
	}
	public void setSkfs(String skfs) {
		this.skfs = skfs;
	}
	public String getPcDjxh() {
		return pcDjxh;
	}
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}
	public String getShrDz() {
		return shrDz;
	}
	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}
	public String getYjjsfsMc() {
		return yjjsfsMc;
	}
	public void setYjjsfsMc(String yjjsfsMc) {
		this.yjjsfsMc = yjjsfsMc;
	}
	public String getWfhDjxh() {
		return wfhDjxh;
	}
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getDdDjxh() {
		return ddDjxh;
	}
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public String getPcJgmc() {
		return pcJgmc;
	}
	public void setPcJgmc(String pcJgmc) {
		this.pcJgmc = pcJgmc;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}
	public String getPcfs() {
		return pcfs;
	}
	public void setPcfs(String pcfs) {
		this.pcfs = pcfs;
	}
	public String getZcHj() {
		return zcHj;
	}
	public void setZcHj(String zcHj) {
		this.zcHj = zcHj;
	}
	public String getZcXf() {
		return zcXf;
	}
	public void setZcXf(String zcXf) {
		this.zcXf = zcXf;
	}
}
