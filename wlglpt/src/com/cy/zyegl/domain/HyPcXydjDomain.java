package com.cy.zyegl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;

/**
 * The DOMAIN class FOR HY_PC_XYDJ is created by tools.
 * @author HJH
 */

public class HyPcXydjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private String xyh;                              // Э���
	private Double yfHj;                             // �˷ѱ��_���˷�
	private Double yfYfyf;                           // �˷ѱ��_Ԥ���˷�
	private Double yfYj;                             // �˷ѱ��_Ѻ��
	private Double yfXxf;                            // �˷ѱ��_��Ϣ��
	private Double yfSjs;                            // �˷ѱ��_˾����
	private Double yfHdyf;                           // �˷ѱ��_�����˷�
	private Double yfHdf;                            // �˷ѱ��_�ص���
	private String bz;                               // ��ע
	private String ywyCzyDjxh;                       // ҵ��Ա
	private String ywyCzyMc;                       // ҵ��Ա����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String yfbgbz;
	private String slbgbz;

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	
	
	private String yxyfHj;                             // ԭ���˷ѱ��_���˷�
	private String yxyfYfyf;                           // ԭ���˷ѱ��_Ԥ���˷�
	private String yxyfYj;                             // ԭ���˷ѱ��_Ѻ��
	private String yxyfXxf;                            // ԭ���˷ѱ��_��Ϣ��
	private String yxyfSjs;                            // ԭ���˷ѱ��_˾����
	private String yxyfHdyf;                           // ԭ���˷ѱ��_�����˷�
	private String yxyfHdf;                            // ԭ���˷ѱ��_�ص���
	
	private String bmbm;
	private HyPcxxglDomain pcxxDomain;
	
	/***Э��Ǽ� ����䶯��Ϣ****/
	private String wfhDjxh;
	private String ddbh;
	private String khmc;
	private String hwmc;
	private String zrbmMc;
	private String bdHwSl;
	private String bdHwZl;
	private String bdHwTj;
	private String bdJsSl;
	
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private String shrDjxh;                          // �ջ���_�Ǽ����
	private String shrMc;                            // �ջ���_����
	private String shrDz;                            // �ջ���_��ַ
	private String shrLxr;                           // �ջ���_��ϵ��
	private String shrLxdh;                          // �ջ���_��ϵ�绰
	private String shrXzqhDm;                        // �ջ���_������������
	private String szHwBzHldwDm;                     // ʵװ_����_��װ_������λ
	private Double szHwSl;                           // ʵװ_����_����
	private Double szHwZl;                           // ʵװ_����_����
	private Double szHwTj;                           // ʵװ_����_���
	private Date yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private Double szJsSl;                           // ʵװ_��������
	private String hdbh;
	
	private String shrXzqhMc;
	/*****************��������*************/
	private String ssJgbm;
	private String pcJgbm;
	private String pcrCzyDjxh;
	private String pcrqq;
	private String pcrqz;
	private String fhrMc;
	private String fhrDjxh;
	private String pcdh;
	private String zt;
	
	/**********************ϵͳ����*********************/
	private String xtcs20016;					//Э��Ǽ��Ƿ���Ҫ
	private String xtcs20206;					//Э��ǼǱ䶯�Ƿ���Ҫ����
	
	private String addFlag;						//������־
	private String kfsFlag;

	private HyPcHwxxXydjDomain hwmxDomain;
	private HyPcHwxxDomain pcHwDomain;
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyPcXydjDomain() {
	}

	//��ȡ�ɳ��Ǽ����(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡЭ���
	public String getXyh() {
		return this.xyh;
	}

	//����Э���
	public void setXyh(String xyh) {
		this.xyh=xyh;
	}

	//��ȡ�˷ѱ��_���˷�
	public Double getYfHj() {
		return this.yfHj;
	}

	//�����˷ѱ��_���˷�
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	//��ȡ�˷ѱ��_Ԥ���˷�
	public Double getYfYfyf() {
		return this.yfYfyf;
	}

	//�����˷ѱ��_Ԥ���˷�
	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf=yfYfyf;
	}

	//��ȡ�˷ѱ��_Ѻ��
	public Double getYfYj() {
		return this.yfYj;
	}

	//�����˷ѱ��_Ѻ��
	public void setYfYj(Double yfYj) {
		this.yfYj=yfYj;
	}

	//��ȡ�˷ѱ��_��Ϣ��
	public Double getYfXxf() {
		return this.yfXxf;
	}

	//�����˷ѱ��_��Ϣ��
	public void setYfXxf(Double yfXxf) {
		this.yfXxf=yfXxf;
	}

	//��ȡ�˷ѱ��_˾����
	public Double getYfSjs() {
		return this.yfSjs;
	}

	//�����˷ѱ��_˾����
	public void setYfSjs(Double yfSjs) {
		this.yfSjs=yfSjs;
	}

	//��ȡ�˷ѱ��_�����˷�
	public Double getYfHdyf() {
		return this.yfHdyf;
	}

	//�����˷ѱ��_�����˷�
	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf=yfHdyf;
	}

	//��ȡ�˷ѱ��_�ص���
	public Double getYfHdf() {
		return this.yfHdf;
	}

	//�����˷ѱ��_�ص���
	public void setYfHdf(Double yfHdf) {
		this.yfHdf=yfHdf;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡҵ��Ա
	public String getYwyCzyDjxh() {
		return this.ywyCzyDjxh;
	}

	//����ҵ��Ա
	public void setYwyCzyDjxh(String ywyCzyDjxh) {
		this.ywyCzyDjxh=ywyCzyDjxh;
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
	public Date getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(Date cjrq) {
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
	public Date getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
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

	public String getBmbm() {
		return bmbm;
	}

	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}

	public HyPcxxglDomain getPcxxDomain() {
		if (pcxxDomain == null) {
			pcxxDomain = new HyPcxxglDomain();
		}
		return pcxxDomain;
	}

	public void setPcxxDomain(HyPcxxglDomain pcxxDomain) {
		this.pcxxDomain = pcxxDomain;
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

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getPcJgbm() {
		return pcJgbm;
	}

	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm = pcJgbm;
	}

	public String getPcrCzyDjxh() {
		return pcrCzyDjxh;
	}

	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh = pcrCzyDjxh;
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

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getYfbgbz() {
		return yfbgbz;
	}

	public void setYfbgbz(String yfbgbz) {
		this.yfbgbz = yfbgbz;
	}

	public String getSlbgbz() {
		return slbgbz;
	}

	public void setSlbgbz(String slbgbz) {
		this.slbgbz = slbgbz;
	}

	public HyPcHwxxXydjDomain getHwmxDomain() {
		if (hwmxDomain == null) {
			hwmxDomain = new HyPcHwxxXydjDomain();
		}
		return hwmxDomain;
	}

	public void setHwmxDomain(HyPcHwxxXydjDomain hwmxDomain) {
		this.hwmxDomain = hwmxDomain;
	}

	public HyPcHwxxDomain getPcHwDomain() {
		if (pcHwDomain == null) {
			pcHwDomain = new HyPcHwxxDomain();
		}
		return pcHwDomain;
	}

	public void setPcHwDomain(HyPcHwxxDomain pcHwDomain) {
		this.pcHwDomain = pcHwDomain;
	}

	public String getXtcs20206() {
		return xtcs20206;
	}

	public void setXtcs20206(String xtcs20206) {
		this.xtcs20206 = xtcs20206;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getKfsFlag() {
		return kfsFlag;
	}

	public void setKfsFlag(String kfsFlag) {
		this.kfsFlag = kfsFlag;
	}

	public String getYxyfHj() {
		return yxyfHj;
	}

	public void setYxyfHj(String yxyfHj) {
		this.yxyfHj = yxyfHj;
	}

	public String getYxyfYfyf() {
		return yxyfYfyf;
	}

	public void setYxyfYfyf(String yxyfYfyf) {
		this.yxyfYfyf = yxyfYfyf;
	}

	public String getYxyfYj() {
		return yxyfYj;
	}

	public void setYxyfYj(String yxyfYj) {
		this.yxyfYj = yxyfYj;
	}

	public String getYxyfXxf() {
		return yxyfXxf;
	}

	public void setYxyfXxf(String yxyfXxf) {
		this.yxyfXxf = yxyfXxf;
	}

	public String getYxyfSjs() {
		return yxyfSjs;
	}

	public void setYxyfSjs(String yxyfSjs) {
		this.yxyfSjs = yxyfSjs;
	}

	public String getYxyfHdyf() {
		return yxyfHdyf;
	}

	public void setYxyfHdyf(String yxyfHdyf) {
		this.yxyfHdyf = yxyfHdyf;
	}

	public String getYxyfHdf() {
		return yxyfHdf;
	}

	public void setYxyfHdf(String yxyfHdf) {
		this.yxyfHdf = yxyfHdf;
	}

	public String getYwyCzyMc() {
		return ywyCzyMc;
	}

	public void setYwyCzyMc(String ywyCzyMc) {
		this.ywyCzyMc = ywyCzyMc;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getBdHwSl() {
		return bdHwSl;
	}

	public void setBdHwSl(String bdHwSl) {
		this.bdHwSl = bdHwSl;
	}

	public String getBdHwZl() {
		return bdHwZl;
	}

	public void setBdHwZl(String bdHwZl) {
		this.bdHwZl = bdHwZl;
	}

	public String getBdHwTj() {
		return bdHwTj;
	}

	public void setBdHwTj(String bdHwTj) {
		this.bdHwTj = bdHwTj;
	}

	public String getBdJsSl() {
		return bdJsSl;
	}

	public void setBdJsSl(String bdJsSl) {
		this.bdJsSl = bdJsSl;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getShrDjxh() {
		return shrDjxh;
	}

	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh = shrDjxh;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getSzHwBzHldwDm() {
		return szHwBzHldwDm;
	}

	public void setSzHwBzHldwDm(String szHwBzHldwDm) {
		this.szHwBzHldwDm = szHwBzHldwDm;
	}

	public Double getSzHwSl() {
		return szHwSl;
	}

	public void setSzHwSl(Double szHwSl) {
		this.szHwSl = szHwSl;
	}

	public Double getSzHwZl() {
		return szHwZl;
	}

	public void setSzHwZl(Double szHwZl) {
		this.szHwZl = szHwZl;
	}

	public Double getSzHwTj() {
		return szHwTj;
	}

	public void setSzHwTj(Double szHwTj) {
		this.szHwTj = szHwTj;
	}

	public Date getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public Double getSzJsSl() {
		return szJsSl;
	}

	public void setSzJsSl(Double szJsSl) {
		this.szJsSl = szJsSl;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getXtcs20016() {
		return xtcs20016;
	}

	public void setXtcs20016(String xtcs20016) {
		this.xtcs20016 = xtcs20016;
	}

}
