package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SPWS.
 * @author HaoY
 */

public class QySpwsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������
	private String wsDm;                             // �������
	private String xmflbz;                           // ��Ŀ�����־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private String sfxz;							//�Ƿ�ѡ��
	private String strObj;
	private String zt;
	private String ztStr;
	private String flbz;

	private String ywflMc;
	private String ywhjMc;
	private String ywflDm;
	private String ywhjDm;
	private String wsMc;
	private String wsJc;
	private String sm;
	private String dwMc;
	private String wsspmsDm;
	private String wsspmsMc;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QySpwsDomain() {
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ�������
	public String getWsDm() {
		return this.wsDm;
	}

	//�����������
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//��ȡ��Ŀ�����־(Y/N)
	public String getXmflbz() {
		return this.xmflbz;
	}

	//������Ŀ�����־(Y/N)
	public void setXmflbz(String xmflbz) {
		this.xmflbz=xmflbz;
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

	public String getSfxz() {
		return sfxz;
	}

	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}

	public String getStrObj() {
		return strObj;
	}

	public void setStrObj(String strObj) {
		this.strObj = strObj;
	}

	public String getYwflMc() {
		return ywflMc;
	}

	public void setYwflMc(String ywflMc) {
		this.ywflMc = ywflMc;
	}

	public String getYwhjMc() {
		return ywhjMc;
	}

	public void setYwhjMc(String ywhjMc) {
		this.ywhjMc = ywhjMc;
	}

	public String getYwflDm() {
		return ywflDm;
	}

	public void setYwflDm(String ywflDm) {
		this.ywflDm = ywflDm;
	}

	public String getYwhjDm() {
		return ywhjDm;
	}

	public void setYwhjDm(String ywhjDm) {
		this.ywhjDm = ywhjDm;
	}

	public String getWsMc() {
		return wsMc;
	}

	public void setWsMc(String wsMc) {
		this.wsMc = wsMc;
	}

	public String getFlbz() {
		if("Y".equals(xmflbz)){
			flbz = "��";
		}else if("N".equals(xmflbz)){
			flbz = "��";
		}else {
			flbz = "��";
		}
		return flbz;
	}

	public void setFlbz(String flbz) {
		this.flbz = flbz;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getZtStr() {
		if("Y".equals(zt)){
			ztStr = "����";
		}else{
			ztStr = "ͣ��";
		}
		return ztStr;
	}

	public void setZtStr(String ztStr) {
		this.ztStr = ztStr;
	}
	
	public String getWsJc() {
		return wsJc;
	}

	public void setWsJc(String wsJc) {
		this.wsJc = wsJc;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getWsspmsDm() {
		return wsspmsDm;
	}

	public void setWsspmsDm(String wsspmsDm) {
		this.wsspmsDm = wsspmsDm;
	}

	public String getWsspmsMc() {
		return wsspmsMc;
	}

	public void setWsspmsMc(String wsspmsMc) {
		this.wsspmsMc = wsspmsMc;
	}

}
