package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_ZPAJ is created by tools.
 * @author HJH
 */

public class HyZpajDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ajDjxh;                           // 
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private byte[] ajzp;                             // �ɳ�����
	private String bz;                               // ��ע
	private String pcrCzyDjxh;                       // �ɳ���
	private String pcrq;                             // �ɳ�����
	private String pcJgbm;                           // �ɳ�����
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String clHm;  //��������
	private String sjXm;  //˾������
	private String fhr;   //������
	private String pcrXm; //�ɳ�������
	private String pcDh;  //�ɳ�����
	private String pcrqq; //�ɳ�������
	private String pcrqz; //�ɳ�����ֹ
	private String czxm;  //��������
	private String gcHm;  //�ҳ�����
	private String sjHm;  //�ֻ�����
	private String dianhua;  //�绰
	private String fhRq;   //��������
	private Long pageXh;
	private int ajCs; //�������
	private byte[] fj;
	private String fjmcSave;
	private String tage;
	private String pcXm;  //�ɳ�����
	private String ssXm;  //��������
	private String sfz;
	private String clsxMc;
	private String yfHj;  //�˷�
	private String yfYfyf;
	private String yfYj;
	private String yfSjs;
	private String yfHdyf;
	private String yfHdf;
	private String yfjsfMc;
	private String xxzjDjxh;
	private String yfXxf;
	private String zrbmDjxh;
	private String zrbmMc;
	private String pcrCzyMc;
	private String pcfsMc;
	private String ysfsMc;
	private String zcfsMc;
	private String pccgbzStr;
	private String pccgbz;
	private String pcBz;
	private String fhrMc;
	private String fhrDjxh;
	private List<HyPcDhajDomain> ajdhList;
	private List<HyPcDhajDomain> ajzpList;
	private String sjMc;
	private String bmMc;
	private String clhm4Query;
	
	private String zpdz;                             //��Ƭ��ַ

	public String getZpdz() {
		return zpdz;
	}

	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}
	
	public String getClhm4Query() {
		return clhm4Query;
	}

	public void setClhm4Query(String clhm4Query) {
		this.clhm4Query = clhm4Query;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyZpajDomain() {
	}

	//��ȡ
	public String getAjDjxh() {
		return this.ajDjxh;
	}

	//����
	public void setAjDjxh(String ajDjxh) {
		this.ajDjxh=ajDjxh;
	}

	public String getPccgbzStr() {
		if("Y".equals(pccgbz)){
			pccgbzStr = "�ɹ�";
		}else{
			pccgbzStr = "ʧ��";
		}
		return pccgbzStr;
	}
	
	//��ȡ�ɳ��Ǽ����(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ�����
	public byte[] getAjzp() {
		return this.ajzp;
	}

	//�����ɳ�����
	public void setAjzp(byte[] ajzp) {
		this.ajzp=ajzp;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�ɳ���
	public String getPcrCzyDjxh() {
		return this.pcrCzyDjxh;
	}

	//�����ɳ���
	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh=pcrCzyDjxh;
	}

	//��ȡ�ɳ�����
	public String getPcrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.pcrq);
		}
		catch(Exception e){
			return this.pcrq;
		}
	}

	//�����ɳ�����
	public void setPcrq(String pcrq) {
		this.pcrq=pcrq;
	}

	//��ȡ�ɳ�����
	public String getPcJgbm() {
		return this.pcJgbm;
	}

	//�����ɳ�����
	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm=pcJgbm;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	public String getClHm() {
		return clHm;
	}

	public void setClHm(String clHm) {
		this.clHm = clHm;
	}

	public String getSjXm() {
		return sjXm;
	}

	public void setSjXm(String sjXm) {
		this.sjXm = sjXm;
	}



	

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getFhRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.fhRq);
		}
		catch(Exception e){
			return this.fhRq;
		}
	}

	public void setFhRq(String fhRq) {
		this.fhRq = fhRq;
	}

	public String getGcHm() {
		return gcHm;
	}

	public void setGcHm(String gcHm) {
		this.gcHm = gcHm;
	}

	public String getSjHm() {
		return sjHm;
	}

	public void setSjHm(String sjHm) {
		this.sjHm = sjHm;
	}

	public String getPcrqq() {
		return pcrqq;
	}

	public void setPcrqq(String pcrqq) {
		this.pcrqq = pcrqq;
	}

	public String getPcrqz() {
		return pcrqz;
	}

	public void setPcrqz(String pcrqz) {
		this.pcrqz = pcrqz;
	}

	public String getPcDh() {
		return pcDh;
	}

	public void setPcDh(String pcDh) {
		this.pcDh = pcDh;
	}

	public String getPcrXm() {
		return pcrXm;
	}

	public void setPcrXm(String pcrXm) {
		this.pcrXm = pcrXm;
	}

	public Long getPageXh() {
		return pageXh;
	}

	public void setPageXh(Long pageXh) {
		this.pageXh = pageXh;
	}

	public int getAjCs() {
		return ajCs;
	}

	public void setAjCs(int ajCs) {
		this.ajCs = ajCs;
	}

	

	

	public byte[] getFj() {
		return fj;
	}

	public void setFj(byte[] fj) {
		this.fj = fj;
	}

	public String getFjmcSave() {
		return fjmcSave;
	}

	public void setFjmcSave(String fjmcSave) {
		this.fjmcSave = fjmcSave;
	}

	public String getTage() {
		return tage;
	}

	public void setTage(String tage) {
		this.tage = tage;
	}

	public String getPcXm() {
		return pcXm;
	}

	public void setPcXm(String pcXm) {
		this.pcXm = pcXm;
	}

	public String getSsXm() {
		return ssXm;
	}

	public void setSsXm(String ssXm) {
		this.ssXm = ssXm;
	}

	public List<HyPcDhajDomain> getAjdhList() {
		if(ajdhList==null){
			ajdhList=new ArrayList<HyPcDhajDomain>();
		}
		return ajdhList;
	}

	public void setAjdhList(List<HyPcDhajDomain> ajdhList) {
		this.ajdhList = ajdhList;
	}

	public List<HyPcDhajDomain> getAjzpList() {
		if(ajzpList==null){
			ajzpList=new ArrayList<HyPcDhajDomain>();
		}
		return ajzpList;
	}

	public void setAjzpList(List<HyPcDhajDomain> ajzpList) {
		this.ajzpList = ajzpList;
	}

	public String getClsxMc() {
		return clsxMc;
	}

	public void setClsxMc(String clsxMc) {
		this.clsxMc = clsxMc;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getPcBz() {
		return pcBz;
	}

	public void setPcBz(String pcBz) {
		this.pcBz = pcBz;
	}

	public String getPccgbz() {
		return pccgbz;
	}

	public void setPccgbz(String pccgbz) {
		this.pccgbz = pccgbz;
	}

	public String getPcfsMc() {
		return pcfsMc;
	}

	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}

	public String getPcrCzyMc() {
		return pcrCzyMc;
	}

	public void setPcrCzyMc(String pcrCzyMc) {
		this.pcrCzyMc = pcrCzyMc;
	}

	public String getXxzjDjxh() {
		return xxzjDjxh;
	}

	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh = xxzjDjxh;
	}

	public String getYfHdf() {
		return yfHdf;
	}

	public void setYfHdf(String yfHdf) {
		this.yfHdf = yfHdf;
	}

	public String getYfHdyf() {
		return yfHdyf;
	}

	public void setYfHdyf(String yfHdyf) {
		this.yfHdyf = yfHdyf;
	}

	public String getYfHj() {
		return yfHj;
	}

	public void setYfHj(String yfHj) {
		this.yfHj = yfHj;
	}

	public String getYfjsfMc() {
		return yfjsfMc;
	}

	public void setYfjsfMc(String yfjsfMc) {
		this.yfjsfMc = yfjsfMc;
	}

	public String getYfSjs() {
		return yfSjs;
	}

	public void setYfSjs(String yfSjs) {
		this.yfSjs = yfSjs;
	}

	public String getYfXxf() {
		return yfXxf;
	}

	public void setYfXxf(String yfXxf) {
		this.yfXxf = yfXxf;
	}

	public String getYfYfyf() {
		return yfYfyf;
	}

	public void setYfYfyf(String yfYfyf) {
		this.yfYfyf = yfYfyf;
	}

	public String getYfYj() {
		return yfYj;
	}

	public void setYfYj(String yfYj) {
		this.yfYj = yfYj;
	}

	public String getYsfsMc() {
		return ysfsMc;
	}

	public void setYsfsMc(String ysfsMc) {
		this.ysfsMc = ysfsMc;
	}

	public String getZcfsMc() {
		return zcfsMc;
	}

	public void setZcfsMc(String zcfsMc) {
		this.zcfsMc = zcfsMc;
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public void setPccgbzStr(String pccgbzStr) {
		this.pccgbzStr = pccgbzStr;
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


}
