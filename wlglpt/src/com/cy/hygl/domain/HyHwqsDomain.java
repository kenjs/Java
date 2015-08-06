package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_HWQS is created by tools.
 * @author HJH
 */

public class HyHwqsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwqsDjxh;                         // ����ǩ�յǼ����
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhdjxh;                          // δ�����Ǽ����
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ��ţ�������ϸ��ţ�
	private String qsrq;                             // ǩ������
	private String qsrmc;                            // ǩ��������
	private String lxdh;                             // ��ϵ�绰
	private String sfzh;                             // ���֤��
	private String bz;                               // ��ע˵��
	private String yxbz;                             // ��Ч��־
	private String cjr;                              // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String xgrMc;                            // �޸�������
	private String ssJgbm;
	private String pcrqq;
	private String pcrqz;
	private String hdbh;


	private String pcdh;
	private String hwMc;
	private String hwSl;
	private String shrMc;
	private String zt;
	private String sfd;
	private String mdd;
	private String shrDz;
	private String sjXm;
	private String pcrq;
	private String fhrMc;
	private String ddbh;
	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getHwSl() {
		return hwSl;
	}

	public void setHwSl(String hwSl) {
		this.hwSl = hwSl;
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

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getSjXm() {
		return sjXm;
	}

	public void setSjXm(String sjXm) {
		this.sjXm = sjXm;
	}

	public String getPcrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.pcrq);
		}
		catch(Exception e){
			return this.pcrq;
		}
	}

	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getPcrqq() {
		return pcrqq;
	}

	public void setPcrqq(String pcrqq) {
		this.pcrqq = pcrqq;
	}

	public String getPcrqz() {
		return pcrqz;
	}

	public void setPcrqz(String pcrqz) {
		this.pcrqz = pcrqz;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyHwqsDomain() {
	}

	//��ȡ����ǩ�յǼ����
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//���û���ǩ�յǼ����
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡδ�����Ǽ����
	public String getWfhdjxh() {
		return this.wfhdjxh;
	}

	//����δ�����Ǽ����
	public void setWfhdjxh(String wfhdjxh) {
		this.wfhdjxh=wfhdjxh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ��ţ�������ϸ��ţ�
	public String getXh() {
		return this.xh;
	}

	//������ţ�������ϸ��ţ�
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡǩ������
	public String getQsrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.qsrq);
		}
		catch(Exception e){
			return this.qsrq;
		}
	}

	//����ǩ������
	public void setQsrq(String qsrq) {
		this.qsrq=qsrq;
	}

	//��ȡǩ��������
	public String getQsrmc() {
		return this.qsrmc;
	}

	//����ǩ��������
	public void setQsrmc(String qsrmc) {
		this.qsrmc=qsrmc;
	}

	//��ȡ��ϵ�绰
	public String getLxdh() {
		return this.lxdh;
	}

	//������ϵ�绰
	public void setLxdh(String lxdh) {
		this.lxdh=lxdh;
	}

	//��ȡ���֤��
	public String getSfzh() {
		return this.sfzh;
	}

	//�������֤��
	public void setSfzh(String sfzh) {
		this.sfzh=sfzh;
	}

	//��ȡ��ע˵��
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע˵��
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��Ч��־
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ������
	public String getCjr() {
		return this.cjr;
	}

	//���ô�����
	public void setCjr(String cjr) {
		this.cjr=cjr;
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
