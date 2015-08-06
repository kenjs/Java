package com.cy.cwgl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_ZJDBGL is created by tools.
 * @author HJH
 */

public class CwZjdbglDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zjdbDjxh;                         // �ʽ�����Ǽ����(SEQ_CW_DJXH)
	private String rq;                               // �ƻ�����
	private String dcDwDjxh;                         // ������λ�Ǽ����
	private String zcflDm;
	private String yhCshDjxh;
	private Double je;                               // �������	
	private String bz;                               // ��ע˵��
	private String drDwDjxh;                         // ���뵥λ�Ǽ����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String rqQ;
	private String rqZ;

	private String dcDwMc;                         // ������λ
	private String drDwMc;                         // ���뵥λ
	private String ysJe;                           // ���ս��
	private Date jsrq;
	private String yfJe;                           // ʵ�ʵ������
	private Date dbrq;
	private String ysRq;                           // ��������
	private String yfRq;                           // ʵ�ʵ�������
	
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public CwZjdbglDomain() {
	}

	public String getZjdbDjxh() {
		return zjdbDjxh;
	}

	public void setZjdbDjxh(String zjdbDjxh) {
		this.zjdbDjxh = zjdbDjxh;
	}

	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}



	public void setRq(String rq) {
		this.rq = rq;
	}



	public String getDcDwDjxh() {
		return dcDwDjxh;
	}



	public void setDcDwDjxh(String dcDwDjxh) {
		this.dcDwDjxh = dcDwDjxh;
	}



	public String getZcflDm() {
		return zcflDm;
	}



	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}



	public String getYhCshDjxh() {
		return yhCshDjxh;
	}



	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}



	public Double getJe() {
		return je;
	}



	public void setJe(Double je) {
		this.je = je;
	}



	public String getBz() {
		return bz;
	}



	public void setBz(String bz) {
		this.bz = bz;
	}



	public String getDrDwDjxh() {
		return drDwDjxh;
	}



	public void setDrDwDjxh(String drDwDjxh) {
		this.drDwDjxh = drDwDjxh;
	}



	public String getYxbz() {
		return yxbz;
	}



	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}



	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}



	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}



	public String getDcDwMc() {
		return dcDwMc;
	}



	public void setDcDwMc(String dcDwMc) {
		this.dcDwMc = dcDwMc;
	}



	public String getDrDwMc() {
		return drDwMc;
	}



	public void setDrDwMc(String drDwMc) {
		this.drDwMc = drDwMc;
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

	public String getRqQ() {
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getYsJe() {
		return ysJe;
	}

	public void setYsJe(String ysJe) {
		this.ysJe = ysJe;
	}

	public String getYfJe() {
		return yfJe;
	}

	public void setYfJe(String yfJe) {
		this.yfJe = yfJe;
	}

	public String getYsRq() {
		return ysRq;
	}

	public void setYsRq(String ysRq) {
		this.ysRq = ysRq;
	}

	public String getYfRq() {
		return yfRq;
	}

	public void setYfRq(String yfRq) {
		this.yfRq = yfRq;
	}

	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}

	public Date getDbrq() {
		return dbrq;
	}

	public void setDbrq(Date dbrq) {
		this.dbrq = dbrq;
	}
}
