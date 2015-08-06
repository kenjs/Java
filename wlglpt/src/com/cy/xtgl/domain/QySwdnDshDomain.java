package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SWDN_DSH AND QY_SWDN
 * @author HaoY
 */

public class QySwdnDshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ggDjxh;                           // �����Ǽ����(SEQ_GG_DJXH)
	private String qyZcxh;                           // ��ҵע�����(GL_QYZC.QY_ZCXH)
	private String mac;                              // MAC��ַ
	private String bzsm;                             // ��ע˵��
	private String czyDjxh;                          // ����Ա����
	private String sqrCzyDjxh;                       // ����������
	private String sqrq;                             // ��������
	private String shrCzyDjxh;                       // �����
	private String shrq;                             // �������
	private String shjg;                             // ��˽��(1 ͬ�� ,2 ��ͬ��)
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String sqrMc;                            // ����������
	private String czyMc;                            // ����������
	private String shrMc;                            // ���������
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QySwdnDshDomain() {
	}

	//��ȡ�����Ǽ����(SEQ_GG_DJXH)
	public String getGgDjxh() {
		return this.ggDjxh;
	}

	//���ù����Ǽ����(SEQ_GG_DJXH)
	public void setGgDjxh(String ggDjxh) {
		this.ggDjxh=ggDjxh;
	}

	//��ȡ��ҵע�����(GL_QYZC.QY_ZCXH)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//������ҵע�����(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//��ȡMAC��ַ
	public String getMac() {
		return this.mac;
	}

	//����MAC��ַ
	public void setMac(String mac) {
		this.mac=mac;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����(QY_RYDJ.CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
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

	//��ȡ�����
	public String getShrCzyDjxh() {
		return this.shrCzyDjxh;
	}

	//���������
	public void setShrCzyDjxh(String shrCzyDjxh) {
		this.shrCzyDjxh=shrCzyDjxh;
	}

	//��ȡ�������
	public String getShrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.shrq);
		}
		catch(Exception e){
			return this.shrq;
		}
	}

	//�����������
	public void setShrq(String shrq) {
		this.shrq=shrq;
	}

	//��ȡ��˽��(1 ͬ�� ,2 ��ͬ��)
	public String getShjg() {
		return this.shjg;
	}

	//������˽��(1 ͬ�� ,2 ��ͬ��)
	public void setShjg(String shjg) {
		this.shjg=shjg;
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

	public String getCzyMc() {
		return czyMc;
	}

	public void setCzyMc(String czyMc) {
		this.czyMc = czyMc;
	}

	public String getSqrMc() {
		return sqrMc;
	}

	public void setSqrMc(String sqrMc) {
		this.sqrMc = sqrMc;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getQybz() {
		return qybz;
	}

	public void setQybz(String qybz) {
		this.qybz = qybz;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}
