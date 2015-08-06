package com.cy.bggl.domain;
import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_RCAP is created by tools.
 * @author HJH
 */

public class BgRcapDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String bgDjxh;                           // �칫�Ǽ����(SEQ_BG_DJXH)
	private String czyDjxh;                          // ����Ա�Ǽ����
	private String rq;                               // ����(YYYY-MM-DD)
	private String nr;                               // ����
	private String dxFsxh;                           // ���ŷ������
	private String ckbz;                             // �鿴��־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	
	private String cxrq;
	private String changeDate;//��ǰ�û�ѡ�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public BgRcapDomain() {
	}

	//��ȡ�칫�Ǽ����(SEQ_BG_DJXH)
	public String getBgDjxh() {
		return this.bgDjxh;
	}

	//���ð칫�Ǽ����(SEQ_BG_DJXH)
	public void setBgDjxh(String bgDjxh) {
		this.bgDjxh=bgDjxh;
	}

	//��ȡ����Ա�Ǽ����
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ����(YYYY-MM-DD)
	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}

	//��������(YYYY-MM-DD)
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ����
	public String getNr() {
		return this.nr;
	}

	//��������
	public void setNr(String nr) {
		this.nr=nr;
	}

	//��ȡ���ŷ������
	public String getDxFsxh() {
		return this.dxFsxh;
	}

	//���ö��ŷ������
	public void setDxFsxh(String dxFsxh) {
		this.dxFsxh=dxFsxh;
	}

	//��ȡ�鿴��־(Y/N)
	public String getCkbz() {
		return this.ckbz;
	}

	//���ò鿴��־(Y/N)
	public void setCkbz(String ckbz) {
		this.ckbz=ckbz;
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

	public String getCxrq() {
		return cxrq;
	}

	public void setCxrq(String cxrq) {
		this.cxrq = cxrq;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

}
