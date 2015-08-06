package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_KH_SHDZ is created by tools.
 * @author HJH
 */

public class QyKhShdzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shdzDjxh;                         // �ջ���ַ�Ǽ����
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String khDjxh;                           // 
	private String xzqhDm;                           // ������������
	private String xzqhMc;                           // ������������
	private String xxdz;                             // ��ϸ��ַ����
	private String pyqc;                             // ��ַƴ��ȫ��
	private String pyjc;                             // ��ַƴ�����
	private String yb;                               // �ʱ�
	private String lxr;                              // ��ϵ��
	private String lxrYddh;                          // ��ϵ���ƶ��绰
	private String lxrGddh;                          // ��ϵ�˹̶��绰
	private String qtlxdh;                           // ������ϵ�绰
	private String bz;                               // ��ע
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // �Ǽ�����
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String fhrMc;
	private String fhrDjxh;
	private String bmDjxh;
	private String shdwMc;
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String khmc;

	private String ssJgmc;
	private String djJgmc;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyKhShdzDomain() {
	}

	//��ȡ
	public String getShdzDjxh() {
		return this.shdzDjxh;
	}

	//����
	public void setShdzDjxh(String shdzDjxh) {
		this.shdzDjxh=shdzDjxh;
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ������������
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//����������������
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//��ȡ����
	public String getXxdz() {
		return this.xxdz;
	}

	//��������
	public void setXxdz(String xxdz) {
		this.xxdz=xxdz;
	}

	//��ȡƴ��ȫ��
	public String getPyqc() {
		return this.pyqc;
	}

	//����ƴ��ȫ��
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//��ȡƴ�����
	public String getPyjc() {
		return this.pyjc;
	}

	//����ƴ�����
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
	}

	//��ȡ�ʱ�
	public String getYb() {
		return this.yb;
	}

	//�����ʱ�
	public void setYb(String yb) {
		this.yb=yb;
	}

	//��ȡ���
	public String getLxr() {
		return this.lxr;
	}

	//���ü��
	public void setLxr(String lxr) {
		this.lxr=lxr;
	}

	//��ȡƴ��ȫ��
	public String getLxrYddh() {
		return this.lxrYddh;
	}

	//����ƴ��ȫ��
	public void setLxrYddh(String lxrYddh) {
		this.lxrYddh=lxrYddh;
	}

	//��ȡƴ�����
	public String getLxrGddh() {
		return this.lxrGddh;
	}

	//����ƴ�����
	public void setLxrGddh(String lxrGddh) {
		this.lxrGddh=lxrGddh;
	}

	//��ȡ��ַ
	public String getQtlxdh() {
		return this.qtlxdh;
	}

	//���õ�ַ
	public void setQtlxdh(String qtlxdh) {
		this.qtlxdh=qtlxdh;
	}

	//��ȡ
	public String getBz() {
		return this.bz;
	}

	//����
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//����
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//����
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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

	public String getXzqhMc() {
		return xzqhMc;
	}

	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc = xzqhMc;
	}

	public String getShdwMc() {
		return shdwMc;
	}

	public void setShdwMc(String shdwMc) {
		this.shdwMc = shdwMc;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getBmDjxh() {
		return bmDjxh;
	}

	public void setBmDjxh(String bmDjxh) {
		this.bmDjxh = bmDjxh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}
	
	
}
