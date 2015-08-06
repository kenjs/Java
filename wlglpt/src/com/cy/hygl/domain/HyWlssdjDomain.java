package com.cy.hygl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_WLSSDJ is created by tools.
 * @author HJH
 */

public class HyWlssdjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlssDjxh;                         // ������ʧ�Ǽ����(SEQ_WLSS_DJXH)
	private String wlssyyWhXh;                         // ������ʧԭ�����
	private String wlssclfsDm;                       // ������ʧ����ʽ����
	private Double hjSr;                             // ���
	private String khDjxh;                           // �ͻ��Ǽ����
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������
	private String ddDjxh;                           // �����Ǽ����
	private String hwmxxh;                           // ������ϸ���
	private String bz;                               // ��ע
	private String pcygCzyDjxh;                      // �⳥Ա���Ǽ����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String ssJgbm;                           // ��������
	private String djJgbm;                           // �Ǽǲ���
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String fhrDjxh;
	private String sl;
	private String zl;
	private String tj;
	private String jsSl;
	private String wlssLybz;
	private String spbcbz;
	public String getSpbcbz() {
		return spbcbz;
	}

	public void setSpbcbz(String spbcbz) {
		this.spbcbz = spbcbz;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
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

	public String getJsSl() {
		return jsSl;
	}

	public void setJsSl(String jsSl) {
		this.jsSl = jsSl;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyWlssdjDomain() {
	}

	//��ȡ������ʧ�Ǽ����(SEQ_WLSS_DJXH)
	public String getWlssDjxh() {
		return this.wlssDjxh;
	}

	//����������ʧ�Ǽ����(SEQ_WLSS_DJXH)
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh=wlssDjxh;
	}
	
	public String getWlssyyWhXh() {
		return wlssyyWhXh;
	}

	public void setWlssyyWhXh(String wlssyyWhXh) {
		this.wlssyyWhXh = wlssyyWhXh;
	}

	//��ȡ������ʧ����ʽ����
	public String getWlssclfsDm() {
		return this.wlssclfsDm;
	}

	//����������ʧ����ʽ����
	public void setWlssclfsDm(String wlssclfsDm) {
		this.wlssclfsDm=wlssclfsDm;
	}

	//��ȡ���
	public Double getHjSr() {
		return this.hjSr;
	}

	//���ý��
	public void setHjSr(Double hjSr) {
		this.hjSr=hjSr;
	}

	//��ȡ�ͻ��Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ��������
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�����ɳ��������
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
	public String getHwmxxh() {
		return this.hwmxxh;
	}

	//���û�����ϸ���
	public void setHwmxxh(String hwmxxh) {
		this.hwmxxh=hwmxxh;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�⳥Ա���Ǽ����
	public String getPcygCzyDjxh() {
		return this.pcygCzyDjxh;
	}

	//�����⳥Ա���Ǽ����
	public void setPcygCzyDjxh(String pcygCzyDjxh) {
		this.pcygCzyDjxh=pcygCzyDjxh;
	}

	//��ȡ��Ҫ������־(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//������Ҫ������־(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//��ȡ��������״̬����
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//������������״̬����
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//��ȡ�����������
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//���������������
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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

	public String getWlssLybz() {
		return wlssLybz;
	}

	public void setWlssLybz(String wlssLybz) {
		this.wlssLybz = wlssLybz;
	}
}
