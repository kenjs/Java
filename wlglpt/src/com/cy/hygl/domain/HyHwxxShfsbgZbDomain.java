package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_HWXX_SHFSBG_ZB is created by tools.
 * @author HJH
 */

public class HyHwxxShfsbgZbDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shbgDjxh;                         // �ͻ����-�Ǽ����
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double bspsf;                            // ����_�����
	private Double zcHdf;                            // ������
	private Double zcThf;                            // �����
	private String ssJgbm;                           // ��������
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String bz;
	
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyHwxxShfsbgZbDomain() {
	}

	//��ȡ�ͻ����-�Ǽ����
	public String getShbgDjxh() {
		return this.shbgDjxh;
	}

	//�����ͻ����-�Ǽ����
	public void setShbgDjxh(String shbgDjxh) {
		this.shbgDjxh=shbgDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ��������(δ�����Ǽ����)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�����ɳ��������(δ�����Ǽ����)
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

	//��ȡ���(������ϸ���)
	public String getXh() {
		return this.xh;
	}

	//�������(������ϸ���)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ����_�����
	public Double getBspsf() {
		return this.bspsf;
	}

	//��������_�����
	public void setBspsf(Double bspsf) {
		this.bspsf=bspsf;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Double getZcHdf() {
		return zcHdf;
	}

	public void setZcHdf(Double zcHdf) {
		this.zcHdf = zcHdf;
	}

	public Double getZcThf() {
		return zcThf;
	}

	public void setZcThf(Double zcThf) {
		this.zcThf = zcThf;
	}
}
