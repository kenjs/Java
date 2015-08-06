package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SPWS_SPLCSZ is created by tools.
 * @author anq
 */

public class QySpwsSplcszDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // ���������������(SEQ_SPLC_SZXH)
	private String ssJgbm;                           // ������������(����)
	private String wsDm;                             // �������
	private String xmflDjxh;                         // ��Ŀ����Ǽ����
	private String splc;                             // ��������
	private Double zssx;                             // ����ʱ��(��)
	private String gzrbz;                            // �����ձ�־(1 �����գ�2 ��Ȼ��)
	private String qzxsbz;                           // Ȩ��ϵ����־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String ssJgmc;//��������
	private String wsMc;
	private String xmflmc;
    
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private QySpwsSplcszZbDomain spjcDomain;
	private List<QySpwsSplcszZbDomain> zbList;
	
	private String dwDm;//��λ����
	private String dwMc;//��λ����
	private String zgsbm;//�ܹ�˾����
	private String wsJc;//������
	private String wsspmsDm;//��������ģʽ����
	private String wsspmsMc;//��������ģʽ����
	private String sm;//˵��
	private String ywflMc;//ҵ���������
	private String ywhjMc;//ҵ�񻷽�����
	private String xmflbz;//��Ŀ�����־
	private String curDwbm;//��ǰ��λ����
	private String existsBz;//���ڱ�־,�����Ƿ��Ѿ�����������������Y
	private String saveBz;//�����־Y
	private String cxszbz;//�������ñ�־Y
	private String qxszbz;//ȡ�����ñ�־Y
	

	public QySpwsSplcszDomain() {
	}

	//��ȡ���������������(SEQ_SPLC_SZXH)
	public Long getSplcSzxh() {
		return this.splcSzxh;
	}

	//�������������������(SEQ_SPLC_SZXH)
	public void setSplcSzxh(Long splcSzxh) {
		this.splcSzxh=splcSzxh;
	}

	//��ȡ������������(����)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//����������������(����)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�������
	public String getWsDm() {
		return this.wsDm;
	}

	//�����������
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//��ȡ��Ŀ����Ǽ����
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//������Ŀ����Ǽ����
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//��ȡ��������
	public String getSplc() {
		return this.splc;
	}

	//������������
	public void setSplc(String splc) {
		this.splc=splc;
	}

	//��ȡ����ʱ��(��)
	public Double getZssx() {
		return this.zssx;
	}

	//��������ʱ��(��)
	public void setZssx(Double zssx) {
		this.zssx=zssx;
	}

	//��ȡ�����ձ�־(1 �����գ�2 ��Ȼ��)
	public String getGzrbz() {
		return this.gzrbz;
	}

	//���ù����ձ�־(1 �����գ�2 ��Ȼ��)
	public void setGzrbz(String gzrbz) {
		this.gzrbz=gzrbz;
	}

	//��ȡȨ��ϵ����־(Y/N)
	public String getQzxsbz() {
		return this.qzxsbz;
	}

	//����Ȩ��ϵ����־(Y/N)
	public void setQzxsbz(String qzxsbz) {
		this.qzxsbz=qzxsbz;
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

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getWsMc() {
		return wsMc;
	}

	public void setWsMc(String wsMc) {
		this.wsMc = wsMc;
	}

	public String getXmflmc() {
		return xmflmc;
	}

	public void setXmflmc(String xmflmc) {
		this.xmflmc = xmflmc;
	}

	public String getXmflbz() {
		return xmflbz;
	}

	public void setXmflbz(String xmflbz) {
		this.xmflbz = xmflbz;
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

	public QySpwsSplcszZbDomain getSpjcDomain() {
		if (spjcDomain == null) {
			spjcDomain = new QySpwsSplcszZbDomain();
		}
		return spjcDomain;
	}

	public void setSpjcDomain(QySpwsSplcszZbDomain spjcDomain) {
		this.spjcDomain = spjcDomain;
	}

	public List<QySpwsSplcszZbDomain> getZbList() {
		if (zbList == null) {
			zbList = new ArrayList<QySpwsSplcszZbDomain>();
		}
		return zbList;
	}

	public void setZbList(List<QySpwsSplcszZbDomain> zbList) {
		this.zbList = zbList;
	}

	public String getDwDm() {
		return dwDm;
	}

	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}

	public String getZgsbm() {
		return zgsbm;
	}

	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}

	public String getWsJc() {
		return wsJc;
	}

	public void setWsJc(String wsJc) {
		this.wsJc = wsJc;
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

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
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

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getCurDwbm() {
		return curDwbm;
	}

	public void setCurDwbm(String curDwbm) {
		this.curDwbm = curDwbm;
	}

	public String getExistsBz() {
		return existsBz;
	}

	public void setExistsBz(String existsBz) {
		this.existsBz = existsBz;
	}

	public String getCxszbz() {
		return cxszbz;
	}

	public void setCxszbz(String cxszbz) {
		this.cxszbz = cxszbz;
	}

	public String getSaveBz() {
		return saveBz;
	}

	public void setSaveBz(String saveBz) {
		this.saveBz = saveBz;
	}

	public String getQxszbz() {
		return qxszbz;
	}

	public void setQxszbz(String qxszbz) {
		this.qxszbz = qxszbz;
	}
}
