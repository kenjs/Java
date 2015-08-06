package com.cy.cwgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;


//����Ʊ��Ʊ�Ǽ�
public class CwFpKpdjDomain extends BaseBusinessDomain{

	private static final long serialVersionUID = 1L;
	private String kpsqDjxh ;				//��Ʊ����Ǽ����
	private String kpsqfsDm ;				//��Ʊ���뷽ʽ����
	private String khDjxh ;					//�ͻ��Ǽ����
	private String khmc ;					//�ͻ�����
	private String sqKpjeHj ;				//���뿪Ʊ���ϼ�
	private String sqKprq ;					//���뿪Ʊ����
	private String fpkjje ;					//��Ʊ���߽��
	private String sqrMc ;					//����������
	private String dwMc ;					//��λ����
	private String bmMc ;					//��������
	private String fpkjbz ;					//��Ʊ���߱�־(Y/N)
	private String spbz ;					//��Ҫ������־(Y/N)
	private String wsspztDm ;				//��������״̬����
	private String kpdwJgbm ;				//��Ʊ��λ
	private String djrq ;
	private String bzsm ;
	private String djrCzyDjxh ;
	private String djJgbm ;
	private String ssJgbm ;
	private String wsSpxh ;
	private String ykpQdhx ;
	private String yhxje ;
	private String kpje ;					//��Ʊ���
	private String kpsqfs ;					//��Ʊ���뷽ʽ
	private String wkjje ;					//δ���߽��
	
	private String fpdm ;					//��Ʊ����
	private String fphm ;					//��Ʊ����
	private String kprCzyDjxh ;				//��Ʊ�˵Ǽ����
	private String sl ;						//˰��
	private String kprq ;
	private String kpr ;
	
	private String ssJgbm4Query ;
	private String kpDjxh ;					//��Ʊ�Ǽ����
	private String rtnCode ;
	private String errMesge ;
	
	private String fpdmAndFphm ;			//�жϷ�Ʊ����ͺ����Ƿ�ȫһ�£�һ�·���true

	private String fhrDjxh;
	private String fhrMc;
	private List<BaseBusinessDomain> dataList ;
	public String getBmMc() {
		return bmMc;
	}
	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}
	public List<BaseBusinessDomain> getDataList() {
		if(dataList == null){
			dataList = new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public String getDwMc() {
		return dwMc;
	}
	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}
	public String getFpkjje() {
		return fpkjje;
	}
	public void setFpkjje(String fpkjje) {
		this.fpkjje = fpkjje;
	}
	public String getKhDjxh() {
		return khDjxh;
	}
	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public String getKpsqDjxh() {
		return kpsqDjxh;
	}
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh = kpsqDjxh;
	}
	public String getSqKpjeHj() {
		return sqKpjeHj;
	}
	public void setSqKpjeHj(String sqKpjeHj) {
		this.sqKpjeHj = sqKpjeHj;
	}
	public String getSqKprq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sqKprq);
		}
		catch(Exception e){
			return this.sqKprq;
		}
	}
	public void setSqKprq(String sqKprq) {
		this.sqKprq = sqKprq;
	}
	public String getSqrMc() {
		return sqrMc;
	}
	public void setSqrMc(String sqrMc) {
		this.sqrMc = sqrMc;
	}
	public String getFpkjbz() {
		return fpkjbz;
	}
	public void setFpkjbz(String fpkjbz) {
		this.fpkjbz = fpkjbz;
	}
	public String getKpdwJgbm() {
		return kpdwJgbm;
	}
	public void setKpdwJgbm(String kpdwJgbm) {
		this.kpdwJgbm = kpdwJgbm;
	}
	public String getSpbz() {
		if(spbz == null || "".equals(spbz)){
			spbz = "Y";
		}
		return spbz;
	}
	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
	public String getWsspztDm() {
		return wsspztDm;
	}
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}
	public String getFhrMc() {
		return fhrMc;
	}
	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getKpsqfsDm() {
		return kpsqfsDm;
	}
	public void setKpsqfsDm(String kpsqfsDm) {
		this.kpsqfsDm = kpsqfsDm;
	}
	public String getBzsm() {
		return bzsm;
	}
	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getWsSpxh() {
		return wsSpxh;
	}
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}
	public String getYkpQdhx() {
		return ykpQdhx;
	}
	public void setYkpQdhx(String ykpQdhx) {
		this.ykpQdhx = ykpQdhx;
	}
	public String getYhxje() {
		return yhxje;
	}
	public void setYhxje(String yhxje) {
		this.yhxje = yhxje;
	}
	public String getFpdm() {
		return fpdm;
	}
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getKprCzyDjxh() {
		return kprCzyDjxh;
	}
	public void setKprCzyDjxh(String kprCzyDjxh) {
		this.kprCzyDjxh = kprCzyDjxh;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getKpje() {
		return kpje;
	}
	public void setKpje(String kpje) {
		this.kpje = kpje;
	}
	public String getKpsqfs() {
		return kpsqfs;
	}
	public void setKpsqfs(String kpsqfs) {
		this.kpsqfs = kpsqfs;
	}
	public String getWkjje() {
		return wkjje;
	}
	public void setWkjje(String wkjje) {
		this.wkjje = wkjje;
	}
	public String getKprq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.kprq);
		}
		catch(Exception e){
			return this.kprq;
		}
	}
	public void setKprq(String kprq) {
		this.kprq = kprq;
	}
	public String getKpr() {
		return kpr;
	}
	public void setKpr(String kpr) {
		this.kpr = kpr;
	}
	public String getFpdmAndFphm() {
		return fpdmAndFphm;
	}
	public void setFpdmAndFphm(String fpdmAndFphm) {
		this.fpdmAndFphm = fpdmAndFphm;
	}
	public String getKpDjxh() {
		return kpDjxh;
	}
	public void setKpDjxh(String kpDjxh) {
		this.kpDjxh = kpDjxh;
	}
	public String getErrMesge() {
		return errMesge;
	}
	public void setErrMesge(String errMesge) {
		this.errMesge = errMesge;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getSsJgbm4Query() {
		return ssJgbm4Query;
	}
	public void setSsJgbm4Query(String ssJgbm4Query) {
		this.ssJgbm4Query = ssJgbm4Query;
	}
	public String getFhrDjxh() {
		return fhrDjxh;
	}
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}
	
}
