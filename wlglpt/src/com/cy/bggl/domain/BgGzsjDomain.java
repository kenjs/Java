package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_GZSJ is created by tools.
 * @author HJH
 */

public class BgGzsjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������
	private String yxqQ;                             // ��Ч����
	private String yxqZ;                             // ��Ч��ֹ
	private String amSbsjS;                          // �����ϰ�ʱ��-ʱ
	private String amSbsjF;                          // �����ϰ�ʱ��-��
	private String pmSbsjS;                          // �����°�ʱ��-ʱ
	private String pmSbsjF;                          // �����°�ʱ��-��
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private String sbsj;
	private String xbsj;
	private String zt;//1ʧЧ 2��Ч
	
	private String[] xsList;
	private String[] fzList;
	
	private String count;
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public BgGzsjDomain() {
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
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

	//��ȡ�����ϰ�ʱ��-ʱ
	public String getAmSbsjS() {
		return this.amSbsjS;
	}

	//���������ϰ�ʱ��-ʱ
	public void setAmSbsjS(String amSbsjS) {
		this.amSbsjS=amSbsjS;
	}

	//��ȡ�����ϰ�ʱ��-��
	public String getAmSbsjF() {
		return this.amSbsjF;
	}

	//���������ϰ�ʱ��-��
	public void setAmSbsjF(String amSbsjF) {
		this.amSbsjF=amSbsjF;
	}

	//��ȡ�����°�ʱ��-ʱ
	public String getPmSbsjS() {
		return this.pmSbsjS;
	}

	//���������°�ʱ��-ʱ
	public void setPmSbsjS(String pmSbsjS) {
		this.pmSbsjS=pmSbsjS;
	}

	//��ȡ�����°�ʱ��-��
	public String getPmSbsjF() {
		return this.pmSbsjF;
	}

	//���������°�ʱ��-��
	public void setPmSbsjF(String pmSbsjF) {
		this.pmSbsjF=pmSbsjF;
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

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getXbsj() {
		return xbsj;
	}

	public void setXbsj(String xbsj) {
		this.xbsj = xbsj;
	}

	public String[] getFzList() {
		if(fzList == null){
			fzList = new String[60];
			for (int i = 0; i < fzList.length; i++) {
				fzList[i] = i + "";
			}
		}
		return fzList;
	}

	public void setFzList(String[] fzList) {
		this.fzList = fzList;
	}

	public String[] getXsList() {
		if(xsList == null){
			xsList = new String[24];
			for (int i = 0; i < xsList.length; i++) {
				xsList[i] = i + "";
			}
		}
		return xsList;
	}

	public void setXsList(String[] xsList) {
		this.xsList = xsList;
	}
}