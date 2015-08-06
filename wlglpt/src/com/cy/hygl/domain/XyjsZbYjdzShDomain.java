package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The DOMAIN class FOR XYJS_ZB_YJDZ is created by tools.
 * @author XIAY
 */

public class XyjsZbYjdzShDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // ���ʵǼ����(SEQ_DZ_DJXH)
	private String ssJgbm;                           // ��������
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ������ϸ���
	private String zrbmDm;                           // ת�벿�Ŵ���
	private String zrbmDjxh;                         // ת�벿�ŵǼ����
	private Double jsYj;                             // ����_δ��
	private Double dzje;                             // ���˽��
	private String dzrCzyDjxh;                       // ������
	private String dzrq;                               // ��������
	private String dzJgbm;                           // ���ʲ���
	private String yxbz;                             // ��Ч��־(Y/N)
	private String dzbz;							  // ���˱�־
	private String dzCybz;                           // ���ʲ����־(Y/N)
	private Double dzcyje;                           // ���ʲ�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private String jgmc;   //��������
	private String zrbmmc; //ת�벿������
	private String fbsmc;  //�ְ�������
	private String dzrmc;  //����������
	private String dzjgmc;  //���˻�������
	private String pcdh;   //�ɳ�����
	private Date pcrq;		//�ɳ�����

	private String hwmc;  //��������
	private Double hwZl;  // ����_����
	private Double hwTj;   //����_���
	
	private String pcrqQ;  	//�ɳ�����
	private String pcrqZ;	//�ɳ�����
	
	private String pldzStr; //��������
	private String sfsp;  //�Ƿ�����
	private String zdsp;  //�Ƿ��Զ���������

	
	
	/***���***/
	private List<XyjsZbYjdzShDomain> shList;
	private String spxh;
	private String jdxh;
	private String fsrmc;
	private String fsrq;
	private String sqr;
	private String sqbm;
	private String sqdw;
	private String jzdw;
	private String jfdw;
	private String rqq;
	private String rqz;
	private String fsthbz;
	private String shbz;
	private String bz;
	
	public XyjsZbYjdzShDomain() {
	}

	//��ȡ���ʵǼ����(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//���ö��ʵǼ����(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	//��ȡ������ϸ���
	public String getXh() {
		return this.xh;
	}

	//���û�����ϸ���
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡת�벿�Ŵ���
	public String getZrbmDm() {
		return this.zrbmDm;
	}

	//����ת�벿�Ŵ���
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm=zrbmDm;
	}

	//��ȡת�벿�ŵǼ����
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//����ת�벿�ŵǼ����
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//��ȡ����_δ��
	public Double getJsYj() {
		return this.jsYj;
	}

	//���ý���_δ��
	public void setJsYj(Double jsYj) {
		this.jsYj=jsYj;
	}

	//��ȡ
	public Double getDzje() {
		return this.dzje;
	}

	//����
	public void setDzje(Double dzje) {
		this.dzje=dzje;
	}

	//��ȡ������
	public String getDzrCzyDjxh() {
		return this.dzrCzyDjxh;
	}

	//���ö�����
	public void setDzrCzyDjxh(String dzrCzyDjxh) {
		this.dzrCzyDjxh=dzrCzyDjxh;
	}

	//��ȡ��������
	public String getDzrq() {
		return this.dzrq;
	}

	//���ö�������
	public void setDzrq(String dzrq) {
		this.dzrq=dzrq;
	}

	//��ȡ���ʲ���
	public String getDzJgbm() {
		return this.dzJgbm;
	}

	//���ö��ʲ���
	public void setDzJgbm(String dzJgbm) {
		this.dzJgbm=dzJgbm;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ���ʲ����־(Y/N)
	public String getDzCybz() {
		return this.dzCybz;
	}

	//���ö��ʲ����־(Y/N)
	public void setDzCybz(String dzCybz) {
		this.dzCybz=dzCybz;
	}

	//��ȡ���˱�־
	public String getDzbz() {
		return dzbz;
	}

	//���ö��˱�־
	public void setDzbz(String dzbz) {
		this.dzbz = dzbz;
	}

	//��ȡ���ʲ�����
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//���ö��ʲ�����
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
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

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public List<XyjsZbYjdzShDomain> getShList() {
		if(shList==null){
			shList=new ArrayList<XyjsZbYjdzShDomain>();
		}
		return shList;
	}

	public void setShList(List<XyjsZbYjdzShDomain> shList) {
		this.shList = shList;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public Double getHwZl() {
		return hwZl;
	}

	public void setHwZl(Double hwZl) {
		this.hwZl = hwZl;
	}

	public Double getHwTj() {
		return hwTj;
	}

	public void setHwTj(Double hwTj) {
		this.hwTj = hwTj;
	}

	public String getPcrqQ() {
		return pcrqQ;
	}

	public void setPcrqQ(String pcrqQ) {
		this.pcrqQ = pcrqQ;
	}

	public String getPcrqZ() {
		return pcrqZ;
	}

	public void setPcrqZ(String pcrqZ) {
		this.pcrqZ = pcrqZ;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getZrbmmc() {
		return zrbmmc;
	}

	public void setZrbmmc(String zrbmmc) {
		this.zrbmmc = zrbmmc;
	}

	public String getFbsmc() {
		return fbsmc;
	}

	public void setFbsmc(String fbsmc) {
		this.fbsmc = fbsmc;
	}

	public String getDzrmc() {
		return dzrmc;
	}

	public void setDzrmc(String dzrmc) {
		this.dzrmc = dzrmc;
	}

	public String getDzjgmc() {
		return dzjgmc;
	}

	public void setDzjgmc(String dzjgmc) {
		this.dzjgmc = dzjgmc;
	}

	public String getPldzStr() {
		return pldzStr;
	}

	public void setPldzStr(String pldzStr) {
		this.pldzStr = pldzStr;
	}

	public String getSfsp() {
		return sfsp;
	}

	public void setSfsp(String sfsp) {
		this.sfsp = sfsp;
	}

	public String getZdsp() {
		return zdsp;
	}

	public void setZdsp(String zdsp) {
		this.zdsp = zdsp;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String getSqbm() {
		return sqbm;
	}

	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}

	public String getSqdw() {
		return sqdw;
	}

	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}

	public String getJzdw() {
		return jzdw;
	}

	public void setJzdw(String jzdw) {
		this.jzdw = jzdw;
	}

	public String getJfdw() {
		return jfdw;
	}

	public void setJfdw(String jfdw) {
		this.jfdw = jfdw;
	}

	public String getRqq() {
		return rqq;
	}

	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	public String getFsthbz() {
		return fsthbz;
	}

	public void setFsthbz(String fsthbz) {
		this.fsthbz = fsthbz;
	}

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}

	public String getRqz() {
		return rqz;
	}

	public void setRqz(String rqz) {
		this.rqz = rqz;
	}

	public String getSpxh() {
		return spxh;
	}

	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}

	public String getJdxh() {
		return jdxh;
	}

	public void setJdxh(String jdxh) {
		this.jdxh = jdxh;
	}

	public String getFsrmc() {
		return fsrmc;
	}

	public void setFsrmc(String fsrmc) {
		this.fsrmc = fsrmc;
	}

	public String getFsrq() {
		return fsrq;
	}

	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
