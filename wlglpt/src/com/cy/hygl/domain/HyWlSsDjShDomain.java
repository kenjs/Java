package com.cy.hygl.domain;



import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����-���õǼ����.
 * @author HJH
 */

public class HyWlSsDjShDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xh;                           // ���
	private String wsspxh;                           // �����������(SEQ_WS_SPXH)
	private String spxh;                           // �������(��1��ʼ����)
	private Double fsthbz;                             // �����˻ر�־(1 ����,2 �˻�)
	private String fsrmc;                             // ����������
	private String fsrq;                         // ��������
	private String spjzsj;                           // ������ֹʱ��
	private String cqbz;                             // ���ڱ�־(Y/N)
	private String sprmc;                           // ����������
	private String sprq;                       // ��������
	private String spjg;                             // ������� 
	private String fyhj;                       // ���úϼ�
	private String pcdh;                             // �ɳ�����
	private String pcrq;                             // �ɳ�����
	private String pcfsDm;                             // �ɳ���ʽ����
	private String pcfsmc;                             // �ɳ���ʽ����
	private String zcfsDm;                             // װ����ʽ����
	private String zcfsmc;                             // װ����ʽ����
	private String clhm;                             // ��������
	private String gchm;                             // �ҳ�����
	private String sjxm;                             // ˾������
	private String yfhj;                             // ���˷�
	private String pcrmc;                             // �ɳ�������
	private String pcjgbm;                            // �ɳ�����
	private String pcbmmc;                            // �ɳ���������
	private String ssjgbm;                            // ��������
	private String ssjgmc;                            // ������������
	private String zrbmDm;                            // ת�벿�Ŵ���
	private String zrbmmc;                            // ת�벿������
	private String sfd;                            // ʼ����
	private String mdd;                            // Ŀ�ĵ�
	private String hwmc;                            // ��������
	private String bz;                            // ��װ
	private String sl;                            // ����
	private String zl;                            // ����
	private String tj;                            // ���
	private String jssl;                            // ��������
	private String fhrmc;                            // ����������
	private String fhrdz;                            // �����˵�ַ
	private String yqfhrq;                            // Ҫ�󷢻�����
	private String shrmc;                            // �ջ���_����
	private String shrdz;                            // �ջ���_��ַ
	private String yqddrq;                            // Ҫ�󵽴�����
	private String je;
	private String pcrCzyDjxh;
	private String fhrXzqhDm;
	private String shrXzqhDm;
	private String tager;
	public String getTager() {
		return tager;
	}


	public void setTager(String tager) {
		this.tager = tager;
	}


	public String getJe() {
		return je;
	}


	public void setJe(String je) {
		this.je = je;
	}


	public String getPcrCzyDjxh() {
		return pcrCzyDjxh;
	}


	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh = pcrCzyDjxh;
	}


	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}


	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}


	public String getShrXzqhDm() {
		return shrXzqhDm;
	}


	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}


	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private Map<String,List<HyWlSsDjShDomain>> map;
	
	private String rqQ;
	private String rqZ;
	private String shbz;//��˱�־
	private String pcrCzyDjxh4Query;
	public String getPcrCzyDjxh4Query() {
		return pcrCzyDjxh4Query;
	}


	public void setPcrCzyDjxh4Query(String pcrCzyDjxh4Query) {
		this.pcrCzyDjxh4Query = pcrCzyDjxh4Query;
	}


	public HyWlSsDjShDomain() {
	}


	public String getBz() {
		return bz;
	}



	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getClhm() {
		return clhm;
	}



	public void setClhm(String clhm) {
		this.clhm = clhm;
	}



	public String getCqbz() {
		return cqbz;
	}



	public void setCqbz(String cqbz) {
		this.cqbz = cqbz;
	}



	public String getFhrdz() {
		return fhrdz;
	}



	public void setFhrdz(String fhrdz) {
		this.fhrdz = fhrdz;
	}



	public String getFhrmc() {
		return fhrmc;
	}



	public void setFhrmc(String fhrmc) {
		this.fhrmc = fhrmc;
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



	public Double getFsthbz() {
		return fsthbz;
	}



	public void setFsthbz(Double fsthbz) {
		this.fsthbz = fsthbz;
	}



	public String getFyhj() {
		return fyhj;
	}



	public void setFyhj(String fyhj) {
		this.fyhj = fyhj;
	}



	public String getGchm() {
		return gchm;
	}



	public void setGchm(String gchm) {
		this.gchm = gchm;
	}



	public String getHwmc() {
		return hwmc;
	}



	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}



	public String getJssl() {
		return jssl;
	}



	public void setJssl(String jssl) {
		this.jssl = jssl;
	}



	public String getMdd() {
		return mdd;
	}



	public void setMdd(String mdd) {
		this.mdd = mdd;
	}



	public String getPcbmmc() {
		return pcbmmc;
	}



	public void setPcbmmc(String pcbmmc) {
		this.pcbmmc = pcbmmc;
	}



	public String getPcdh() {
		return pcdh;
	}



	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}



	public String getPcfsDm() {
		return pcfsDm;
	}



	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
	}



	public String getPcfsmc() {
		return pcfsmc;
	}



	public void setPcfsmc(String pcfsmc) {
		this.pcfsmc = pcfsmc;
	}



	public String getPcjgbm() {
		return pcjgbm;
	}



	public void setPcjgbm(String pcjgbm) {
		this.pcjgbm = pcjgbm;
	}



	public String getPcrmc() {
		return pcrmc;
	}



	public void setPcrmc(String pcrmc) {
		this.pcrmc = pcrmc;
	}



	public String getPcrq() {
		return pcrq;
	}



	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}



	public String getSfd() {
		return sfd;
	}



	public void setSfd(String sfd) {
		this.sfd = sfd;
	}



	public String getShrdz() {
		return shrdz;
	}



	public void setShrdz(String shrdz) {
		this.shrdz = shrdz;
	}



	public String getShrmc() {
		return shrmc;
	}



	public void setShrmc(String shrmc) {
		this.shrmc = shrmc;
	}



	public String getSjxm() {
		return sjxm;
	}



	public void setSjxm(String sjxm) {
		this.sjxm = sjxm;
	}



	public String getSl() {
		return sl;
	}



	public void setSl(String sl) {
		this.sl = sl;
	}



	public String getSpjg() {
		return spjg;
	}



	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}



	public String getSpjzsj() {
		return spjzsj;
	}



	public void setSpjzsj(String spjzsj) {
		this.spjzsj = spjzsj;
	}



	public String getSprmc() {
		return sprmc;
	}



	public void setSprmc(String sprmc) {
		this.sprmc = sprmc;
	}



	public String getSprq() {
		return sprq;
	}



	public void setSprq(String sprq) {
		this.sprq = sprq;
	}



	public String getSpxh() {
		return spxh;
	}



	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}



	public String getSsjgbm() {
		return ssjgbm;
	}



	public void setSsjgbm(String ssjgbm) {
		this.ssjgbm = ssjgbm;
	}



	public String getSsjgmc() {
		return ssjgmc;
	}



	public void setSsjgmc(String ssjgmc) {
		this.ssjgmc = ssjgmc;
	}



	public String getTj() {
		return tj;
	}



	public void setTj(String tj) {
		this.tj = tj;
	}



	public String getWsspxh() {
		return wsspxh;
	}



	public void setWsspxh(String wsspxh) {
		this.wsspxh = wsspxh;
	}



	public String getXh() {
		return xh;
	}



	public void setXh(String xh) {
		this.xh = xh;
	}



	public String getYfhj() {
		return yfhj;
	}



	public void setYfhj(String yfhj) {
		this.yfhj = yfhj;
	}



	public String getYqddrq() {
		return yqddrq;
	}



	public void setYqddrq(String yqddrq) {
		this.yqddrq = yqddrq;
	}



	public String getYqfhrq() {
		return yqfhrq;
	}



	public void setYqfhrq(String yqfhrq) {
		this.yqfhrq = yqfhrq;
	}



	public String getZcfsDm() {
		return zcfsDm;
	}



	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm = zcfsDm;
	}



	public String getZcfsmc() {
		return zcfsmc;
	}



	public void setZcfsmc(String zcfsmc) {
		this.zcfsmc = zcfsmc;
	}



	public String getZl() {
		return zl;
	}



	public void setZl(String zl) {
		this.zl = zl;
	}



	public String getZrbmDm() {
		return zrbmDm;
	}



	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm = zrbmDm;
	}



	public String getZrbmmc() {
		return zrbmmc;
	}



	public void setZrbmmc(String zrbmmc) {
		this.zrbmmc = zrbmmc;
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

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}


	public Map<String, List<HyWlSsDjShDomain>> getMap() {
		if(null==map)
			map=new HashMap<String, List<HyWlSsDjShDomain>>();
		return map;
	}


	public void setMap(Map<String, List<HyWlSsDjShDomain>> map) {
		this.map = map;
	}
}
