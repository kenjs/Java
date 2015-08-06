package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_FYBXSQ is created by tools.
 * @author HJH
 */

public class CwFybxsqDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // ����Ǽ����(SEQ_CW_DJXH)
	private String ssJgbm;
	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	private String sqrCzyDjxh;                       // ������
	private String sqrq;                             // ��������
	private String sqBmDjxh;                         // ���벿��
	private String sqDwDjxh;                         // ���뵥λ
	private String fyjzDwDjxh;                       // ���ü��˵�λ
	private String fyzfDwDjxh;                       // ����֧����λ
	private String fybxje;                           // ���ñ������ϼ�
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private String sqr;
	private String sqbm;
	private String sqdw;
	private String jzdw;
	private String jfdw;
	private String rqq;
	private String rqz;
	public String getRqz() {
		return rqz;
	}

	public void setRqz(String rqz) {
		this.rqz = rqz;
	}

	private String jsonStr;
	private String xhs;
	private String xtcsSfsp;	
	private String xmflDjxh;
	private String fylbMc;
	private String fyxmMc;
	private String tager;
	private boolean sendBz;
	private String spztMc;
	private String spxmFl;
	
	private List<String> fylbDjxh;
	private List<String> fyxmDjxh;
	private List<String> fyje;
	private List<String> bxje;
	private List<String> mxXh;
	private List<String> fybz;
	private String mxBz;
	private String bxdh;
	public String getBxdh() {
		return bxdh;
	}

	public void setBxdh(String bxdh) {
		this.bxdh = bxdh;
	}

	public String getMxBz() {
		return mxBz;
	}

	public void setMxBz(String mxBz) {
		this.mxBz = mxBz;
	}

	public List<String> getFylbDjxh() {
		return fylbDjxh;
	}

	public void setFylbDjxh(List<String> fylbDjxh) {
		this.fylbDjxh = fylbDjxh;
	}

	public List<String> getFyxmDjxh() {
		return fyxmDjxh;
	}

	public void setFyxmDjxh(List<String> fyxmDjxh) {
		this.fyxmDjxh = fyxmDjxh;
	}

	public List<String> getFyje() {
		return fyje;
	}

	public void setFyje(List<String> fyje) {
		this.fyje = fyje;
	}

	public List<String> getBxje() {
		return bxje;
	}

	public void setBxje(List<String> bxje) {
		this.bxje = bxje;
	}

	

	public List<String> getFybz() {
		return fybz;
	}

	public void setFybz(List<String> fybz) {
		this.fybz = fybz;
	}


	

	public String getSpxmFl() {
		return spxmFl;
	}

	public void setSpxmFl(String spxmFl) {
		this.spxmFl = spxmFl;
	}

	public String getSpztMc() {
		return spztMc;
	}

	public void setSpztMc(String spztMc) {
		this.spztMc = spztMc;
	}

	public boolean isSendBz() {
		return sendBz;
	}

	public void setSendBz(boolean sendBz) {
		this.sendBz = sendBz;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getXmflDjxh() {
		return xmflDjxh;
	}

	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh = xmflDjxh;
	}

	public String getFylbMc() {
		return fylbMc;
	}

	public void setFylbMc(String fylbMc) {
		this.fylbMc = fylbMc;
	}

	public String getFyxmMc() {
		return fyxmMc;
	}

	public void setFyxmMc(String fyxmMc) {
		this.fyxmMc = fyxmMc;
	}

	public String getXtcsSfsp() {
		return xtcsSfsp;
	}

	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}

	

	

	public String getXhs() {
		return xhs;
	}

	public void setXhs(String xhs) {
		this.xhs = xhs;
	}

	public List<String> getMxXh() {
		return mxXh;
	}

	public void setMxXh(List<String> mxXh) {
		this.mxXh = mxXh;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	private List<BaseBusinessDomain> fyList;
	public List<BaseBusinessDomain> getFyList() {
		if(fyList==null){
			fyList=new ArrayList<BaseBusinessDomain>();
		}
		return fyList;
	}

	public void setFyList(List<BaseBusinessDomain> fyList) {
		this.fyList = fyList;
	}

	public String getRqq() {
		return rqq;
	}

	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	public String getSqbm() {
		return sqbm;
	}

	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}

	public String getSqdw() {
		return sqdw;
	}

	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}

	public String getJzdw() {
		return jzdw;
	}

	public void setJzdw(String jzdw) {
		this.jzdw = jzdw;
	}

	public String getJfdw() {
		return jfdw;
	}

	public void setJfdw(String jfdw) {
		this.jfdw = jfdw;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public CwFybxsqDomain() {
	}

	//��ȡ����Ǽ����(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//���ò���Ǽ����(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//��ȡ������
	public String getSqrCzyDjxh() {
		return this.sqrCzyDjxh;
	}

	//����������
	public void setSqrCzyDjxh(String sqrCzyDjxh) {
		this.sqrCzyDjxh=sqrCzyDjxh;
	}

	//��ȡ��������
	public String getSqrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sqrq);
		}
		catch(Exception e){
			return this.sqrq;
		}
	}

	//������������
	public void setSqrq(String sqrq) {
		this.sqrq=sqrq;
	}

	//��ȡ���벿��
	public String getSqBmDjxh() {
		return this.sqBmDjxh;
	}

	//�������벿��
	public void setSqBmDjxh(String sqBmDjxh) {
		this.sqBmDjxh=sqBmDjxh;
	}

	//��ȡ���뵥λ
	public String getSqDwDjxh() {
		return this.sqDwDjxh;
	}

	//�������뵥λ
	public void setSqDwDjxh(String sqDwDjxh) {
		this.sqDwDjxh=sqDwDjxh;
	}

	//��ȡ���ü��˵�λ
	public String getFyjzDwDjxh() {
		return this.fyjzDwDjxh;
	}

	//���÷��ü��˵�λ
	public void setFyjzDwDjxh(String fyjzDwDjxh) {
		this.fyjzDwDjxh=fyjzDwDjxh;
	}

	//��ȡ����֧����λ
	public String getFyzfDwDjxh() {
		return this.fyzfDwDjxh;
	}

	//���÷���֧����λ
	public void setFyzfDwDjxh(String fyzfDwDjxh) {
		this.fyzfDwDjxh=fyzfDwDjxh;
	}

	//��ȡ���ñ������ϼ�
	

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	public String getFybxje() {
		return fybxje;
	}

	public void setFybxje(String fybxje) {
		this.fybxje = fybxje;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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
