package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_KH_HWXX is created by tools.
 * @author HJH
 */

public class QyKhHwxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwDjxh;                           // 
	private String ssJgbm;                           // ��������(SEQ_JG_DJXH)
	private String khDjxh;                           // 
	private String hwmc;                             // ����
	private String hwjc;                             // ���
	private String pyqc;                             // ƴ��ȫ��
	private String pyjc;                             // ƴ�����
	private String cdJldwDm;                         // 
	private Double cd;                               // �绰
	private Double kd;                               // �ʱ�
	private Double gd;                               // ������
	private String bzJldwDm;                         // ��ַ
	private Double bzJsHsbl;                         // 
	private Double bzCbHsbl;                         // 
	private String jsJldwDm;                         // ��ַ
	private String cbJldwDm;                         // ��ַ
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String slJldwDm;						//����
	private String zlJldwDm;  						//����		
	private String tjJldwDm;                        //���
	private String sl;
	private String zl;
	private String tj;
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String khjc;
	private String fhrMc;
	private String fhrDjxh;
	private String bmDjxh;
	private String khmc;
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private String cdJldwMc;
	private String bzJldwMc;
	private String jsJldwMc;
	private String cbJldwMc;

	public String getBzJldwMc() {
		return bzJldwMc;
	}

	public void setBzJldwMc(String bzJldwMc) {
		this.bzJldwMc = bzJldwMc;
	}

	public String getCbJldwMc() {
		return cbJldwMc;
	}

	public void setCbJldwMc(String cbJldwMc) {
		this.cbJldwMc = cbJldwMc;
	}

	public String getCdJldwMc() {
		return cdJldwMc;
	}

	public void setCdJldwMc(String cdJldwMc) {
		this.cdJldwMc = cdJldwMc;
	}

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public QyKhHwxxDomain() {
	}

	//��ȡ
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//����
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
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

	//��ȡ����
	public String getHwmc() {
		return this.hwmc;
	}

	//��������
	public void setHwmc(String hwmc) {
		this.hwmc=hwmc;
	}

	//��ȡ���
	public String getHwjc() {
		return this.hwjc;
	}

	//���ü��
	public void setHwjc(String hwjc) {
		this.hwjc=hwjc;
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

	//��ȡ
	public String getCdJldwDm() {
		return this.cdJldwDm;
	}

	//����
	public void setCdJldwDm(String cdJldwDm) {
		this.cdJldwDm=cdJldwDm;
	}

	//��ȡ�绰
	public Double getCd() {
		return this.cd;
	}

	//���õ绰
	public void setCd(Double cd) {
		this.cd=cd;
	}

	//��ȡ�ʱ�
	public Double getKd() {
		return this.kd;
	}

	//�����ʱ�
	public void setKd(Double kd) {
		this.kd=kd;
	}

	//��ȡ������
	public Double getGd() {
		return this.gd;
	}

	//���ø�����
	public void setGd(Double gd) {
		this.gd=gd;
	}

	//��ȡ��ַ
	public String getBzJldwDm() {
		return this.bzJldwDm;
	}

	//���õ�ַ
	public void setBzJldwDm(String bzJldwDm) {
		this.bzJldwDm=bzJldwDm;
	}

	//��ȡ
	public Double getBzJsHsbl() {
		return this.bzJsHsbl;
	}

	//����
	public void setBzJsHsbl(Double bzJsHsbl) {
		this.bzJsHsbl=bzJsHsbl;
	}

	//��ȡ
	public Double getBzCbHsbl() {
		return this.bzCbHsbl;
	}

	//����
	public void setBzCbHsbl(Double bzCbHsbl) {
		this.bzCbHsbl=bzCbHsbl;
	}

	//��ȡ��ַ
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//���õ�ַ
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//��ȡ��ַ
	public String getCbJldwDm() {
		return this.cbJldwDm;
	}

	//���õ�ַ
	public void setCbJldwDm(String cbJldwDm) {
		this.cbJldwDm=cbJldwDm;
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

	public String getSlJldwDm() {
		return slJldwDm;
	}

	public void setSlJldwDm(String slJldwDm) {
		this.slJldwDm = slJldwDm;
	}

	public String getTjJldwDm() {
		return tjJldwDm;
	}

	public void setTjJldwDm(String tjJldwDm) {
		this.tjJldwDm = tjJldwDm;
	}

	public String getZlJldwDm() {
		return zlJldwDm;
	}

	public void setZlJldwDm(String zlJldwDm) {
		this.zlJldwDm = zlJldwDm;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getKhjc() {
		return khjc;
	}

	public void setKhjc(String khjc) {
		this.khjc = khjc;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getBmDjxh() {
		return bmDjxh;
	}

	public void setBmDjxh(String bmDjxh) {
		this.bmDjxh = bmDjxh;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
}
