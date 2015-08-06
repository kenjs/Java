package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR DM_XZQH is created by tools.
 * @author HJH
 */

public class WlSsYyDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                           // ������������
	private String ssJgbm;                           // ������������
	private String ssJgbmMc;                           // �����������
	private String ssyy;                           // ��������ȫ��
	private String sm;                         // ���������������
	private String yxbz;                         // �ϼ�������������
	                            // ��Ч��־(Y/N)
                       // ������������
	private String cjrMc;							 //	������������
	private String cjrq;                       // ������������������
	private String xgrMc;                             // ƴ��ȫ��
	private String xgrq;                             // ƴ�����
	private String cjrCzyDjxh;
	private String xgrCzyDjxh;
	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}

	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}

	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}

	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}

	public String getWhXh() {
		return whXh;
	}

	public void setWhXh(String whXh) {
		this.whXh = whXh;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getSsJgbmMc() {
		return ssJgbmMc;
	}

	public void setSsJgbmMc(String ssJgbmMc) {
		this.ssJgbmMc = ssJgbmMc;
	}

	public String getSsyy() {
		return ssyy;
	}

	public void setSsyy(String ssyy) {
		this.ssyy = ssyy;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
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

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	
}
