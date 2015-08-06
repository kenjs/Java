package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_KH_JSJG is created by tools.
 * @author HJH
 */

public class QyKhJsjgDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsjgDjxh;                         // ����۸�Ǽ����(SEQ_ZY_DJXH)
	private String ssJgbm;                           // ��������
	private String khDjxh;                           // �ͻ��Ǽ����
	private String sfdXzqhDm;                        // ʼ����_������������
	private String mddXzqhDm;                        // Ŀ�ĵ�_������������
	private Double lcs;                              // �����
	private Double ddts;                             // �ﵽ����
	private String syfw;                             // �Ƿ�����ȫ������(Y/N)
	private String hwDjxh;                           // ����Ǽ����
	private String hwxhDjxh;                         // �����ͺŵǼ����
	private String jldwFlDm;                         // ������λ�������
	private String jldwDm;                           // ������λ
	private String jgjsgs;                           // �۸���㹫ʽ(�������Ĺ�ʽ)
	private String xtgs;                             // �۸����ϵͳ��ʽ
	private String jgsm;                             // �۸�˵��
	private String yxqQ;                             // ��Ч����
	private String yxqZ;                             // ��Ч��ֹ
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private String sjMc;
	private String khMc;
	private String hwMc;
	
	private String fhrXzqhDm;
	private String shrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhMc;
	private String tager;
	private String bmDjxh;
	private String fhrDjxh;
	private String fhrMc;
	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getBmDjxh() {
		return bmDjxh;
	}

	public void setBmDjxh(String bmDjxh) {
		this.bmDjxh = bmDjxh;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyKhJsjgDomain() {
	}

	//��ȡ����۸�Ǽ����(SEQ_ZY_DJXH)
	public String getJsjgDjxh() {
		return this.jsjgDjxh;
	}

	//���ý���۸�Ǽ����(SEQ_ZY_DJXH)
	public void setJsjgDjxh(String jsjgDjxh) {
		this.jsjgDjxh=jsjgDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�ͻ��Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
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

	//��ȡ�����
	public Double getLcs() {
		return this.lcs;
	}

	//���������
	public void setLcs(Double lcs) {
		this.lcs=lcs;
	}

	//��ȡ�ﵽ����
	public Double getDdts() {
		return this.ddts;
	}

	//���ôﵽ����
	public void setDdts(Double ddts) {
		this.ddts=ddts;
	}

	//��ȡ�Ƿ�����ȫ������(Y/N)
	public String getSyfw() {
		return this.syfw;
	}

	//�����Ƿ�����ȫ������(Y/N)
	public void setSyfw(String syfw) {
		this.syfw=syfw;
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

	//��ȡ������λ�������
	public String getJldwFlDm() {
		return this.jldwFlDm;
	}

	//���ü�����λ�������
	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm=jldwFlDm;
	}

	//��ȡ������λ
	public String getJldwDm() {
		return this.jldwDm;
	}

	//���ü�����λ
	public void setJldwDm(String jldwDm) {
		this.jldwDm=jldwDm;
	}

	//��ȡ�۸���㹫ʽ(�������Ĺ�ʽ)
	public String getJgjsgs() {
		return this.jgjsgs;
	}

	//���ü۸���㹫ʽ(�������Ĺ�ʽ)
	public void setJgjsgs(String jgjsgs) {
		this.jgjsgs=jgjsgs;
	}

	//��ȡ�۸����ϵͳ��ʽ
	public String getXtgs() {
		return this.xtgs;
	}

	//���ü۸����ϵͳ��ʽ
	public void setXtgs(String xtgs) {
		this.xtgs=xtgs;
	}

	//��ȡ�۸�˵��
	public String getJgsm() {
		return this.jgsm;
	}

	//���ü۸�˵��
	public void setJgsm(String jgsm) {
		this.jgsm=jgsm;
	}

	//��ȡ��Ч����
	public String getYxqQ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqQ);
		}
		catch(Exception e){
			return this.yxqQ;
		}
	}

	//������Ч����
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//��ȡ��Ч��ֹ
	public String getYxqZ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqZ);
		}
		catch(Exception e){
			return this.yxqZ;
		}
	}

	//������Ч��ֹ
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
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
}
