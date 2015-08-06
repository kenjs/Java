package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR XYJS_PC_HWXX is created by tools.
 * @author HJH
 */

public class XyjsPcHwxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDjxh;                           // ����Ǽ����(SEQ_JS_DJXH)
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String zrbmDjxh;                         // ת�벿�ŵǼ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ������ϸ���
	private String hwmc;                             // ��������
	private String hwDjxh;                           // ����Ǽ����
	private String hwxhDjxh;                         // �����ͺŵǼ����
	private String hwBzHldwDm;                       // ����_��װ_������λ
	private Double hwSl;                             // ����_����
	private String hwSlJldwDm;                       // ����_����_������λ
	private Double hwZl;                             // ����_����
	private String hwZlJldwDm;                       // ����_����_������λ
	private Double hwTj;                             // ����_���
	private String hwTjJldwDm;                       // ����_���_������λ
	private Double jsSl;                             // ��������
	private String jsJldwDm;                         // ���������λ
	private String jsJldwFlDm;                       // ���������λ�������
	private String hdbh;                             // �ص����
	private String sfdXzqhDm;                        // ʼ����_������������
	private String mddXzqhDm;                        // Ŀ�ĵ�_������������
	private Double zcPsf;                            // 
	private Double zcDf;                             // 
	private Double zcDshk;                           // 
	private String yxbz;                             // ��Ч��־(Y/N)
	private String dcjsbz;                           // ��ν����־(Y/N)
	private String qcDzDjxh;                         // ǰ�ζ��ʵǼ����
	private Double dzje;
	private Double dzcyje;
	
	private String ddbh;
	private Date pcrq;
	private String pcdh;
	private Date xdrq;
	private String sfdXzqhMc;
	private String mddXzqhMc;
	private String fhrLxr;
	private String shrMc;
	private String shrLxr;
	private String shrDz;

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public XyjsPcHwxxDomain() {
	}

	//��ȡ����Ǽ����(SEQ_JS_DJXH)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//���ý���Ǽ����(SEQ_JS_DJXH)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡת�벿�ŵǼ����
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//����ת�벿�ŵǼ����
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//��ȡ�ɳ��������(δ�����Ǽ����)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�����ɳ��������(δ�����Ǽ����)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ������ϸ���
	public String getXh() {
		return this.xh;
	}

	//���û�����ϸ���
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ��������
	public String getHwmc() {
		return this.hwmc;
	}

	//���û�������
	public void setHwmc(String hwmc) {
		this.hwmc=hwmc;
	}

	//��ȡ����Ǽ����
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//���û���Ǽ����
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
	}

	//��ȡ�����ͺŵǼ����
	public String getHwxhDjxh() {
		return this.hwxhDjxh;
	}

	//���û����ͺŵǼ����
	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh=hwxhDjxh;
	}

	//��ȡ����_��װ_������λ
	public String getHwBzHldwDm() {
		return this.hwBzHldwDm;
	}

	//���û���_��װ_������λ
	public void setHwBzHldwDm(String hwBzHldwDm) {
		this.hwBzHldwDm=hwBzHldwDm;
	}

	//��ȡ����_����
	public Double getHwSl() {
		return this.hwSl;
	}

	//���û���_����
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//��ȡ����_����_������λ
	public String getHwSlJldwDm() {
		return this.hwSlJldwDm;
	}

	//���û���_����_������λ
	public void setHwSlJldwDm(String hwSlJldwDm) {
		this.hwSlJldwDm=hwSlJldwDm;
	}

	//��ȡ����_����
	public Double getHwZl() {
		return this.hwZl;
	}

	//���û���_����
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//��ȡ����_����_������λ
	public String getHwZlJldwDm() {
		return this.hwZlJldwDm;
	}

	//���û���_����_������λ
	public void setHwZlJldwDm(String hwZlJldwDm) {
		this.hwZlJldwDm=hwZlJldwDm;
	}

	//��ȡ����_���
	public Double getHwTj() {
		return this.hwTj;
	}

	//���û���_���
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//��ȡ����_���_������λ
	public String getHwTjJldwDm() {
		return this.hwTjJldwDm;
	}

	//���û���_���_������λ
	public void setHwTjJldwDm(String hwTjJldwDm) {
		this.hwTjJldwDm=hwTjJldwDm;
	}

	//��ȡ��������
	public Double getJsSl() {
		return this.jsSl;
	}

	//���ý�������
	public void setJsSl(Double jsSl) {
		this.jsSl=jsSl;
	}

	//��ȡ���������λ
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//���ý��������λ
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//��ȡ���������λ�������
	public String getJsJldwFlDm() {
		return this.jsJldwFlDm;
	}

	//���ý��������λ�������
	public void setJsJldwFlDm(String jsJldwFlDm) {
		this.jsJldwFlDm=jsJldwFlDm;
	}

	//��ȡ�ص����
	public String getHdbh() {
		return this.hdbh;
	}

	//���ûص����
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	//��ȡʼ����_������������
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//����ʼ����_������������
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//��ȡĿ�ĵ�_������������
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//����Ŀ�ĵ�_������������
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//��ȡ
	public Double getZcPsf() {
		return this.zcPsf;
	}

	//����
	public void setZcPsf(Double zcPsf) {
		this.zcPsf=zcPsf;
	}

	//��ȡ
	public Double getZcDf() {
		return this.zcDf;
	}

	//����
	public void setZcDf(Double zcDf) {
		this.zcDf=zcDf;
	}

	//��ȡ
	public Double getZcDshk() {
		return this.zcDshk;
	}

	//����
	public void setZcDshk(Double zcDshk) {
		this.zcDshk=zcDshk;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ��ν����־(Y/N)
	public String getDcjsbz() {
		return this.dcjsbz;
	}

	//���ö�ν����־(Y/N)
	public void setDcjsbz(String dcjsbz) {
		this.dcjsbz=dcjsbz;
	}

	//��ȡǰ�ζ��ʵǼ����
	public String getQcDzDjxh() {
		return this.qcDzDjxh;
	}

	//����ǰ�ζ��ʵǼ����
	public void setQcDzDjxh(String qcDzDjxh) {
		this.qcDzDjxh=qcDzDjxh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public String getSfdXzqhMc() {
		return sfdXzqhMc;
	}

	public void setSfdXzqhMc(String sfdXzqhMc) {
		this.sfdXzqhMc = sfdXzqhMc;
	}

	public String getMddXzqhMc() {
		return mddXzqhMc;
	}

	public void setMddXzqhMc(String mddXzqhMc) {
		this.mddXzqhMc = mddXzqhMc;
	}

	public String getFhrLxr() {
		return fhrLxr;
	}

	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public Double getDzje() {
		return dzje;
	}

	public void setDzje(Double dzje) {
		this.dzje = dzje;
	}

	public Double getDzcyje() {
		return dzcyje;
	}

	public void setDzcyje(Double dzcyje) {
		this.dzcyje = dzcyje;
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
