package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_WLRY.
 * @author HaoY
 */

public class BgWlryDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlryDjxh;                         // ������Ա�Ǽ����(SEQ_BG_DJXH)
	private String wlryFlxh;                         // ������Ա�������
	private String xm;                               // ����
	private String dz;                               // ��ַ
	private String dh;                               // �绰
	private String cz;                               // ����
	private String sj;                               // �ֻ�
	private String wz;                               // ��ַ
	private String yb;                               // �ʱ�
	private String dy;                               // ����
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	
	private String flmc;
	private String jgbm;
	
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public BgWlryDomain() {
	}

	//��ȡ������Ա�Ǽ����(SEQ_BG_DJXH)
	public String getWlryDjxh() {
		return this.wlryDjxh;
	}

	//����������Ա�Ǽ����(SEQ_BG_DJXH)
	public void setWlryDjxh(String wlryDjxh) {
		this.wlryDjxh=wlryDjxh;
	}

	//��ȡ������Ա�������
	public String getWlryFlxh() {
		return this.wlryFlxh;
	}

	//����������Ա�������
	public void setWlryFlxh(String wlryFlxh) {
		this.wlryFlxh=wlryFlxh;
	}

	//��ȡ����
	public String getXm() {
		return this.xm;
	}

	//��������
	public void setXm(String xm) {
		this.xm=xm;
	}

	//��ȡ��ַ
	public String getDz() {
		return this.dz;
	}

	//���õ�ַ
	public void setDz(String dz) {
		this.dz=dz;
	}

	//��ȡ�绰
	public String getDh() {
		return this.dh;
	}

	//���õ绰
	public void setDh(String dh) {
		this.dh=dh;
	}

	//��ȡ����
	public String getCz() {
		return this.cz;
	}

	//���ô���
	public void setCz(String cz) {
		this.cz=cz;
	}

	//��ȡ�ֻ�
	public String getSj() {
		return this.sj;
	}

	//�����ֻ�
	public void setSj(String sj) {
		this.sj=sj;
	}

	//��ȡ��ַ
	public String getWz() {
		return this.wz;
	}

	//������ַ
	public void setWz(String wz) {
		this.wz=wz;
	}

	//��ȡ�ʱ�
	public String getYb() {
		return this.yb;
	}

	//�����ʱ�
	public void setYb(String yb) {
		this.yb=yb;
	}

	//��ȡ����
	public String getDy() {
		return this.dy;
	}

	//���õ���
	public void setDy(String dy) {
		this.dy=dy;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
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

	public String getFlmc() {
		return flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
}
