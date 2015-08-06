package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_FBS_JSJG is created by tools.
 * @author HJH
 */

public class QyFbsJsjgDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsjgDjxh;                         // ����۸�Ǽ����(SEQ_ZY_DJXH)
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String fbsDjxh;                          // 
	private String lxDjxh;                           // 
	private String jsJldwDm;                         // ��ַ
	private Double dj;                               // ����
	private String yxqQ;                             // 
	private String yxqZ;                             // ƴ��ȫ��
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	
	private String jsJldwMc;
	private String lxDjmc;    
	private String zt;
	
	private String count;

	private String sfdXzqhDm;                        // ʼ����������������
	private String mddXzqhDm;                        // Ŀ�ĵ�������������
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

	private String jldwFlMc;
	private String sfdXzqhMc;                      
	private String mddXzqhMc; 
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyFbsJsjgDomain() {
	}

	//��ȡ����۸�Ǽ����(SEQ_ZY_DJXH)
	public String getJsjgDjxh() {
		return this.jsjgDjxh;
	}

	//���ý���۸�Ǽ����(SEQ_ZY_DJXH)
	public void setJsjgDjxh(String jsjgDjxh) {
		this.jsjgDjxh=jsjgDjxh;
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ
	public String getFbsDjxh() {
		return this.fbsDjxh;
	}

	//����
	public void setFbsDjxh(String fbsDjxh) {
		this.fbsDjxh=fbsDjxh;
	}

	//��ȡ
	public String getLxDjxh() {
		return this.lxDjxh;
	}

	//����
	public void setLxDjxh(String lxDjxh) {
		this.lxDjxh=lxDjxh;
	}

	//��ȡ��ַ
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//���õ�ַ
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//��ȡ����
	public Double getDj() {
		return this.dj;
	}

	//��������
	public void setDj(Double dj) {
		this.dj=dj;
	}

	//��ȡ
	public String getYxqQ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqQ);
		}
		catch(Exception e){
			return this.yxqQ;
		}
	}

	//����
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//��ȡƴ��ȫ��
	public String getYxqZ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqZ);
		}
		catch(Exception e){
			return this.yxqZ;
		}
	}

	//����ƴ��ȫ��
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
	}

	//��ȡ
	public String getBz() {
		return this.bz;
	}

	//����
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//����
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//����
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
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

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public String getLxDjmc() {
		return lxDjmc;
	}

	public void setLxDjmc(String lxDjmc) {
		this.lxDjmc = lxDjmc;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSfdXzqhDm() {
		return sfdXzqhDm;
	}

	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm = sfdXzqhDm;
	}

	public String getMddXzqhDm() {
		return mddXzqhDm;
	}

	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm = mddXzqhDm;
	}

	public Double getLcs() {
		return lcs;
	}

	public void setLcs(Double lcs) {
		this.lcs = lcs;
	}

	public Double getDdts() {
		return ddts;
	}

	public void setDdts(Double ddts) {
		this.ddts = ddts;
	}

	public String getSyfw() {
		return syfw;
	}

	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}

	public String getHwDjxh() {
		return hwDjxh;
	}

	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh = hwDjxh;
	}

	public String getHwxhDjxh() {
		return hwxhDjxh;
	}

	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh = hwxhDjxh;
	}

	public String getJldwFlDm() {
		return jldwFlDm;
	}

	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm = jldwFlDm;
	}

	public String getJldwDm() {
		return jldwDm;
	}

	public void setJldwDm(String jldwDm) {
		this.jldwDm = jldwDm;
	}

	public String getJgjsgs() {
		return jgjsgs;
	}

	public void setJgjsgs(String jgjsgs) {
		this.jgjsgs = jgjsgs;
	}

	public String getXtgs() {
		return xtgs;
	}

	public void setXtgs(String xtgs) {
		this.xtgs = xtgs;
	}

	public String getJgsm() {
		return jgsm;
	}

	public void setJgsm(String jgsm) {
		this.jgsm = jgsm;
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

	public String getJldwFlMc() {
		return jldwFlMc;
	}

	public void setJldwFlMc(String jldwFlMc) {
		this.jldwFlMc = jldwFlMc;
	}
}
