package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.zygl.domain.QyXxzjDjxxDomain;

/**
 * The DOMAIN class FOR HY_PC is created by tools.
 * @author HJH
 */

public class HyPcxxglDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private String clsxDm;                           // �������Դ���
	private String cyrClhmXh;						 // �����˳����������
	private String cyrCzxm;                          // ������_��������
	private String cyrClhm;                          // ������_��������
	private String cyrGchm;                          // ������_�ҳ�����
	private String cyrGchmXh;
	private String cyrSjxm;                          // ������_˾������
	private String cyrSjsfz;                         // ������_˾�����֤
	private String cyrSjsjhm;                        // ������_˾���ֻ�����
	private String cyrQtlxdh;                        // ������_������ϵ�绰
	private String cyrQtlxdh2;                        // ������_������ϵ�绰
	private Date dzrq;								 // ��վ����
	private Double yfHj;                             // �˷�_���˷�
	private Double yfYfyf;                           // �˷�_Ԥ���˷�
	private Double yfYj;                             // �˷�_Ѻ��
	private Double yfXxf;                            // �˷�_��Ϣ��
	private Double yfSjs;                            // �˷�_˾����
	private Double yfHdyf;                           // �˷�_�����˷�
	private Double yfHdf;                            // �˷�_�ص���
	private Double yfZjf;							 // �˷�_�н��
	private String yfhjyf;							 // �˷�/Ԥ��
	private String srhjdf;							 // ����/����
	private String yfjsfDm;                          // �˷ѽ��㷽����
	private String xxzjDjxh;                         // ��Ϣ�н�Ǽ����
	private String zrbmDm;                           // ת�벿�Ŵ���
	private String zrbmDjxh;                         // ת�벿�ŵǼ����
	private String bz;                               // ��ע
	private Date pcrq;                             // �ɳ�����
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����
	private String pcfsDm;                           // �ɳ���ʽ����
	private String ysfsDm;                           // ���䷽ʽ����
	private String zcfsDm;                           // װ����ʽ����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String zrbmMc;
	
	private String xtcs20004;                       //��������
	private String xtcs20030;                       //�����ɳ����ͷ��Զ��ۼ�
	private String xtcs20201;						//�ɳ����ȳɱ��Ƿ���Ҫ����
	private String xtcs20000;						//��˾ģʽ ���� ר��
	
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String pcrMc;
	private String pcJgbmMc;
	private String ssJgbmMc;
	private String dingdan;							//�������
	private String zhuangche;   //װ��
	private String zcfxMc;
	private String pcdh;                             // �ɳ�����
	private String pcfsMc;
	private String sfdMc;   //ʼ����
	private String mddMc;	//Ŀ�ĵ�
	private String ddDjxh;
	private String xh;
	private String hwMc;    //��������
	private String hwbz;    //��װ
	private String sl;      //����
	private String zl;      //����
	private String tj;      //���
	private String zcZl;	//װ������
	private String zcTj;	//װ�����
	private String zs;	 	// ֱ��
	private Double srHj;
	private String jssl;
	private String fhrMc;   // ������_����
	private String fhrDjxh; //�����˵Ǽ����
	private String fhrDz;   // ������_��ַ
	private String yqFhrq;    // Ҫ�󷢻�����
	private String shrMc;   //�ջ���
	private String shrDz;   // �ջ���_��ַ
	private String yqDdrq;    // Ҫ�󵽴�����
	private String pcrCzyDjxh;                       // �ɳ���
	private String pcrqq;                             // �ɳ�������
	private String pcrqz;                             // �ɳ�����ֹ
	private String pcJgbm;                           // �ɳ�����
	private Long pageXh;
	private String wsSpxh;
	private String wsspztDm;
	private String wsspztMc;						//��������״̬����
	private String spbz;
	
	private String xybz;//Э���־ Y �ѵǼ� N δ�Ǽ�
	
	private Double srHdfInit;
	private String callOpenWinFun ;
	
	private String pchwLsxh;							//�ɳ�ʱѡ��Ļ��ﱣ�浽��ʱ��ÿһ���ɳ�����Ӧһ����ʱ���
	private HyPcHwxxDomain pcHwxxDomain;
	private String wfhXhs;
	private String refreshBbhFlag;						// ������ѡ����ǰ���Ƿ���°汾��
	
	private List<String> wfhDjxhs;
	private List<Double> hwSls;
	private List<Double> hwZls;
	private List<Double> hwTjs;
	private List<Double> jssls;
	private List<String> bbhs;
	
	private List<String> hwXh4PcDel;
	private List<String> tempBz;
	private List<String> plqrXhs;
	
	private List<HyTydWfhxxDomain> pcHwxxList;
	private List<DmbGgDomain> pcfsList;
	private List<QyXxzjDjxxDomain> xxzjList;
	
	private String srPsf;
	private String sfQr;
	private String qrJg;
	private String clxx;
	private String plqrStr;
	private String psfDjxh;
	
	private String pchwClfsDm;                       //�ɳ����ﴦ��ʽ����
	private Double zcHj;                             // ֧��_�ϼ�
	private Double zcYj;                             // ֧��_�½�
	private Double zcXf;                             // ֧��_�ָ�
	private Double zcHdf;                            // ֧��_������
	private Double zcThf;                            // ֧��_�����
	private Double zcDf;                            // ֧��_�����
	private Double zcHf;                             // ֧��_�ص���
	private Double zcHk;                             // ֧��_�ؿ�
	
	private String qrPsf;                           //ȷ�Ϻ�����ͷ�
	
	private Integer shbgBz;                           //�ͻ������־ ���������Ѵ��ڱ��
	
	private String zrbmDmShow;
	private String zrbmDjxhShow;
	
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<HyQingDanDomain> qdList;
	private HyQingDanDomain qingDan;
	public HyQingDanDomain getQingDan() {
		if(qingDan==null){
			qingDan=new HyQingDanDomain();
		}
		return qingDan;
	}

	public void setQingDan(HyQingDanDomain qingDan) {
		this.qingDan = qingDan;
	}

	public List<HyQingDanDomain> getQdList() {
		if(qdList==null){
			qdList=new ArrayList<HyQingDanDomain>();
		}
		return qdList;
	}

	public void setQdList(List<HyQingDanDomain> qdList) {
		this.qdList = qdList;
	}

	/*********************����ѡ��ҳ��***********************/
	/**��ѯ����*****/
	private String dw4Query;
	private String djJgbm4Query;
	private String lb4Query;
	private String ddbh4Query;
	private String hwztDm4Query;
	private String fhrqQ;
	private String fhrqZ;
	private List<DmbGgDomain> pchwClfsList;
	private List<HyTydWfhxxDomain> wfhList;
	
	/********�ɳ�����ת�벿�ŵ�ַ����Ϣ����*******/
	private String tableName;
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;
	private String zrbmXzqhMc;
	
	/********************************Э��Ǽ�********************************/
	private Integer dzsl;
	private String xyh;
	private String wfhDjxh;
	private String bgbz;
	private String xyJsSl;
	private Double xyYfHj;
	private Double xyYfYfyf;
	/**************************�ص��Ǽ�**************************/
	private String hdDjxh;
	private String hdbh;

	public HyPcxxglDomain() {
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
	public String getPcdh() {
		return this.pcdh;
	}

	//�����ɳ�����
	public void setPcdh(String pcdh) {
		this.pcdh=pcdh;
	}

	//��ȡ������_��������
	public String getCyrClhm() {
		return this.cyrClhm;
	}

	//���ó�����_��������
	public void setCyrClhm(String cyrClhm) {
		this.cyrClhm=cyrClhm;
	}

	//��ȡ������_�ҳ�����
	public String getCyrGchm() {
		return this.cyrGchm;
	}

	//���ó�����_�ҳ�����
	public void setCyrGchm(String cyrGchm) {
		this.cyrGchm=cyrGchm;
	}

	//��ȡ������_˾������
	public String getCyrSjxm() {
		return this.cyrSjxm;
	}

	//���ó�����_˾������
	public void setCyrSjxm(String cyrSjxm) {
		this.cyrSjxm=cyrSjxm;
	}



	//��ȡ������_˾���ֻ�����
	public String getCyrSjsjhm() {
		return this.cyrSjsjhm;
	}

	//���ó�����_˾���ֻ�����
	public void setCyrSjsjhm(String cyrSjsjhm) {
		this.cyrSjsjhm=cyrSjsjhm;
	}

	//��ȡ������_������ϵ�绰
	public String getCyrQtlxdh() {
		return this.cyrQtlxdh;
	}

	//���ó�����_������ϵ�绰
	public void setCyrQtlxdh(String cyrQtlxdh) {
		this.cyrQtlxdh=cyrQtlxdh;
	}

	public String getClsxDm() {
		return clsxDm;
	}

	public void setClsxDm(String clsxDm) {
		this.clsxDm = clsxDm;
	}

	public String getCyrCzxm() {
		return cyrCzxm;
	}

	public void setCyrCzxm(String cyrCzxm) {
		this.cyrCzxm = cyrCzxm;
	}

	public String getCyrSjsfz() {
		return cyrSjsfz;
	}

	public void setCyrSjsfz(String cyrSjsfz) {
		this.cyrSjsfz = cyrSjsfz;
	}

	public Double getYfYfyf() {
		return yfYfyf;
	}

	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf = yfYfyf;
	}

	public Double getYfYj() {
		return yfYj;
	}

	public void setYfYj(Double yfYj) {
		this.yfYj = yfYj;
	}

	public Double getYfXxf() {
		return yfXxf;
	}

	public void setYfXxf(Double yfXxf) {
		this.yfXxf = yfXxf;
	}

	public Double getYfSjs() {
		return yfSjs;
	}

	public void setYfSjs(Double yfSjs) {
		this.yfSjs = yfSjs;
	}

	public Double getYfHdyf() {
		return yfHdyf;
	}

	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf = yfHdyf;
	}

	public Double getYfHdf() {
		return yfHdf;
	}

	public void setYfHdf(Double yfHdf) {
		this.yfHdf = yfHdf;
	}

	//��ȡ�˷�_���˷�
	public Double getYfHj() {
		return this.yfHj;
	}

	//�����˷�_���˷�
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	

	//��ȡ�˷ѽ��㷽����
	public String getYfjsfDm() {
		return this.yfjsfDm;
	}

	//�����˷ѽ��㷽����
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm=yfjsfDm;
	}

	public Double getYfZjf() {
		return yfZjf;
	}

	public void setYfZjf(Double yfZjf) {
		this.yfZjf = yfZjf;
	}

	//��ȡ��Ϣ�н�Ǽ����
	public String getXxzjDjxh() {
		return this.xxzjDjxh;
	}

	//������Ϣ�н�Ǽ����
	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh=xxzjDjxh;
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


	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//��ȡ�ɳ���ʽ����
	public String getPcfsDm() {
		return this.pcfsDm;
	}

	//�����ɳ���ʽ����
	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm=pcfsDm;
	}

	//��ȡ���䷽ʽ����
	public String getYsfsDm() {
		return this.ysfsDm;
	}

	//�������䷽ʽ����
	public void setYsfsDm(String ysfsDm) {
		this.ysfsDm=ysfsDm;
	}

	//��ȡװ����ʽ����
	public String getZcfsDm() {
		return this.zcfsDm;
	}

	//����װ����ʽ����
	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm=zcfsDm;
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

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public Date getXgrq() {
		return xgrq;
	}

	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
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

	public String getDingdan() {
		return dingdan;
	}

	public void setDingdan(String dingdan) {
		this.dingdan = dingdan;
	}

	public String getFhrDz() {
		return fhrDz;
	}

	public void setFhrDz(String fhrDz) {
		this.fhrDz = fhrDz;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getHwbz() {
		return hwbz;
	}

	public void setHwbz(String hwbz) {
		this.hwbz = hwbz;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}

	public String getMddMc() {
		return mddMc;
	}

	public void setMddMc(String mddMc) {
		this.mddMc = mddMc;
	}

	public String getSfdMc() {
		return sfdMc;
	}

	public void setSfdMc(String sfdMc) {
		this.sfdMc = sfdMc;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
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

	

	public String getJssl() {
		return jssl;
	}

	public void setJssl(String jssl) {
		this.jssl = jssl;
	}

	public String getYqDdrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yqDdrq);
		}
		catch(Exception e){
			return this.yqDdrq;
		}
	}

	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getYqFhrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yqFhrq);
		}
		catch(Exception e){
			return this.yqFhrq;
		}
		
	}

	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq = yqFhrq;
	}

	public String getZhuangche() {
		return zhuangche;
	}

	public void setZhuangche(String zhuangche) {
		this.zhuangche = zhuangche;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
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

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public Long getPageXh() {
		return pageXh;
	}

	public void setPageXh(Long pageXh) {
		this.pageXh = pageXh;
	}

	public String getPcfsMc() {
		return pcfsMc;
	}

	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}

	public String getPcJgbmMc() {
		return pcJgbmMc;
	}

	public void setPcJgbmMc(String pcJgbmMc) {
		this.pcJgbmMc = pcJgbmMc;
	}

	public String getPcrMc() {
		return pcrMc;
	}

	public void setPcrMc(String pcrMc) {
		this.pcrMc = pcrMc;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getSsJgbmMc() {
		return ssJgbmMc;
	}

	public void setSsJgbmMc(String ssJgbmMc) {
		this.ssJgbmMc = ssJgbmMc;
	}

	public String getZcfxMc() {
		return zcfxMc;
	}

	public void setZcfxMc(String zcfxMc) {
		this.zcfxMc = zcfxMc;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public HyPcHwxxDomain getPcHwxxDomain() {
		if (pcHwxxDomain == null) {
			pcHwxxDomain = new HyPcHwxxDomain();
		}
		return pcHwxxDomain;
	}

	public void setPcHwxxDomain(HyPcHwxxDomain pcHwxxDomain) {
		this.pcHwxxDomain = pcHwxxDomain;
	}

	public String getWfhXhs() {
		return wfhXhs;
	}

	public void setWfhXhs(String wfhXhs) {
		this.wfhXhs = wfhXhs;
	}

	public List<String> getWfhDjxhs() {
		if (wfhDjxhs == null) {
			wfhDjxhs = new ArrayList<String>();
		}
		return wfhDjxhs;
	}

	public void setWfhDjxhs(List<String> wfhDjxhs) {
		this.wfhDjxhs = wfhDjxhs;
	}

	public List<Double> getHwSls() {
		if (hwSls == null) {
			hwSls = new ArrayList<Double>();
		}
		return hwSls;
	}

	public void setHwSls(List<Double> hwSls) {
		this.hwSls = hwSls;
	}

	public List<Double> getHwZls() {
		if (hwZls == null) {
			hwZls = new ArrayList<Double>();
		}
		return hwZls;
	}

	public void setHwZls(List<Double> hwZls) {
		this.hwZls = hwZls;
	}

	public List<Double> getHwTjs() {
		if (hwTjs == null) {
			hwTjs = new ArrayList<Double>();
		}
		return hwTjs;
	}

	public void setHwTjs(List<Double> hwTjs) {
		this.hwTjs = hwTjs;
	}

	public List<Double> getJssls() {
		if (jssls == null) {
			jssls = new ArrayList<Double>();
		}
		return jssls;
	}

	public void setJssls(List<Double> jssls) {
		this.jssls = jssls;
	}

	public List<String> getHwXh4PcDel() {
		if (hwXh4PcDel == null) {
			hwXh4PcDel = new ArrayList<String>();
		}
		return hwXh4PcDel;
	}

	public void setHwXh4PcDel(List<String> hwXh4PcDel) {
		this.hwXh4PcDel = hwXh4PcDel;
	}

	public List<HyTydWfhxxDomain> getPcHwxxList() {
		if (pcHwxxList == null) {
			pcHwxxList = new ArrayList<HyTydWfhxxDomain>();
		}
		return pcHwxxList;
	}

	public void setPcHwxxList(List<HyTydWfhxxDomain> pcHwxxList) {
		this.pcHwxxList = pcHwxxList;
	}

	public List<DmbGgDomain> getPcfsList() {
		if (pcfsList == null) {
			pcfsList = new ArrayList<DmbGgDomain>();
		}
		return pcfsList;
	}

	public void setPcfsList(List<DmbGgDomain> pcfsList) {
		this.pcfsList = pcfsList;
	}

	public List<QyXxzjDjxxDomain> getXxzjList() {
		if (xxzjList == null) {
			xxzjList = new ArrayList<QyXxzjDjxxDomain>();
		}
		return xxzjList;
	}

	public void setXxzjList(List<QyXxzjDjxxDomain> xxzjList) {
		this.xxzjList = xxzjList;
	}

	public List<String> getBbhs() {
		if (bbhs == null) {
			bbhs = new ArrayList<String>();
		}
		return bbhs;
	}

	public void setBbhs(List<String> bbhs) {
		this.bbhs = bbhs;
	}

	public List<String> getTempBz() {
		if (tempBz == null) {
			tempBz = new ArrayList<String>();
		}
		return tempBz;
	}

	public void setTempBz(List<String> tempBz) {
		this.tempBz = tempBz;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public Integer getDzsl() {
		return dzsl;
	}

	public void setDzsl(Integer dzsl) {
		this.dzsl = dzsl;
	}

	public Double getSrHdfInit() {
		return srHdfInit;
	}

	public void setSrHdfInit(Double srHdfInit) {
		this.srHdfInit = srHdfInit;
	}

	public String getWsspztMc() {
		return wsspztMc;
	}

	public void setWsspztMc(String wsspztMc) {
		this.wsspztMc = wsspztMc;
	}

	public String getWsspztDm() {
		return wsspztDm;
	}

	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}

	public String getXtcs20201() {
		return xtcs20201;
	}

	public void setXtcs20201(String xtcs20201) {
		this.xtcs20201 = xtcs20201;
	}

	public String getXyJsSl() {
		return xyJsSl;
	}

	public void setXyJsSl(String xyJsSl) {
		this.xyJsSl = xyJsSl;
	}

	public Double getXyYfHj() {
		return xyYfHj;
	}

	public void setXyYfHj(Double xyYfHj) {
		this.xyYfHj = xyYfHj;
	}

	public Double getXyYfYfyf() {
		return xyYfYfyf;
	}

	public void setXyYfYfyf(Double xyYfYfyf) {
		this.xyYfYfyf = xyYfYfyf;
	}

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	public String getWsSpxh() {
		return wsSpxh;
	}

	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}

	public String getBgbz() {
		return bgbz;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public void setBgbz(String bgbz) {
		this.bgbz = bgbz;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getXyh() {
		return xyh;
	}

	public void setXyh(String xyh) {
		this.xyh = xyh;
	}

	public String getXtcs20004() {
		return xtcs20004;
	}

	public void setXtcs20004(String xtcs20004) {
		this.xtcs20004 = xtcs20004;
	}

	public String getCallOpenWinFun() {
		return callOpenWinFun;
	}

	public void setCallOpenWinFun(String callOpenWinFun) {
		this.callOpenWinFun = callOpenWinFun;
	}

	public String getCyrClhmXh() {
		return cyrClhmXh;
	}

	public void setCyrClhmXh(String cyrClhmXh) {
		this.cyrClhmXh = cyrClhmXh;
	}

	public List<DmbGgDomain> getPchwClfsList() {
		if (pchwClfsList == null) {
			pchwClfsList = new ArrayList<DmbGgDomain>();
		}
		return pchwClfsList;
	}

	public void setPchwClfsList(List<DmbGgDomain> pchwClfsList) {
		this.pchwClfsList = pchwClfsList;
	}

	public String getDw4Query() {
		return dw4Query;
	}

	public void setDw4Query(String dw4Query) {
		this.dw4Query = dw4Query;
	}

	public String getDjJgbm4Query() {
		return djJgbm4Query;
	}

	public void setDjJgbm4Query(String djJgbm4Query) {
		this.djJgbm4Query = djJgbm4Query;
	}

	public String getLb4Query() {
		return lb4Query;
	}

	public void setLb4Query(String lb4Query) {
		this.lb4Query = lb4Query;
	}

	public String getDdbh4Query() {
		return ddbh4Query;
	}

	public void setDdbh4Query(String ddbh4Query) {
		this.ddbh4Query = ddbh4Query;
	}

	public String getHwztDm4Query() {
		return hwztDm4Query;
	}

	public void setHwztDm4Query(String hwztDm4Query) {
		this.hwztDm4Query = hwztDm4Query;
	}

	public String getFhrqQ() {
		return fhrqQ;
	}

	public void setFhrqQ(String fhrqQ) {
		this.fhrqQ = fhrqQ;
	}

	public String getFhrqZ() {
		return fhrqZ;
	}

	public void setFhrqZ(String fhrqZ) {
		this.fhrqZ = fhrqZ;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZrbmDz() {
		return zrbmDz;
	}

	public void setZrbmDz(String zrbmDz) {
		this.zrbmDz = zrbmDz;
	}

	public String getZrbmLxr() {
		return zrbmLxr;
	}

	public void setZrbmLxr(String zrbmLxr) {
		this.zrbmLxr = zrbmLxr;
	}

	public String getZrbmLxdh() {
		return zrbmLxdh;
	}

	public void setZrbmLxdh(String zrbmLxdh) {
		this.zrbmLxdh = zrbmLxdh;
	}

	public String getZrbmXzqhDm() {
		return zrbmXzqhDm;
	}

	public void setZrbmXzqhDm(String zrbmXzqhDm) {
		this.zrbmXzqhDm = zrbmXzqhDm;
	}

	public String getZrbmXzqhMc() {
		return zrbmXzqhMc;
	}

	public void setZrbmXzqhMc(String zrbmXzqhMc) {
		this.zrbmXzqhMc = zrbmXzqhMc;
	}

	public String getCyrQtlxdh2() {
		return cyrQtlxdh2;
	}

	public void setCyrQtlxdh2(String cyrQtlxdh2) {
		this.cyrQtlxdh2 = cyrQtlxdh2;
	}

	public Date getDzrq() {
		return dzrq;
	}

	public void setDzrq(Date dzrq) {
		this.dzrq = dzrq;
	}

	public Double getSrHj() {
		return srHj;
	}

	public void setSrHj(Double srHj) {
		this.srHj = srHj;
	}

	public String getYfhjyf() {
		return yfhjyf;
	}

	public void setYfhjyf(String yfhjyf) {
		this.yfhjyf = yfhjyf;
	}

	public String getSrhjdf() {
		return srhjdf;
	}

	public void setSrhjdf(String srhjdf) {
		this.srhjdf = srhjdf;
	}

	public String getZcZl() {
		return zcZl;
	}

	public void setZcZl(String zcZl) {
		this.zcZl = zcZl;
	}

	public String getZcTj() {
		return zcTj;
	}

	public void setZcTj(String zcTj) {
		this.zcTj = zcTj;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getCyrGchmXh() {
		return cyrGchmXh;
	}

	public void setCyrGchmXh(String cyrGchmXh) {
		this.cyrGchmXh = cyrGchmXh;
	}

	public String getRefreshBbhFlag() {
		return refreshBbhFlag;
	}

	public void setRefreshBbhFlag(String refreshBbhFlag) {
		this.refreshBbhFlag = refreshBbhFlag;
	}

	public List<HyTydWfhxxDomain> getWfhList() {
		if (wfhList == null) {
			wfhList = new ArrayList<HyTydWfhxxDomain>();
		}
		return wfhList;
	}

	public void setWfhList(List<HyTydWfhxxDomain> wfhList) {
		this.wfhList = wfhList;
	}

	public String getSrPsf() {
		return srPsf;
	}

	public void setSrPsf(String srPsf) {
		this.srPsf = srPsf;
	}

	public String getSfQr() {
		return sfQr;
	}

	public void setSfQr(String sfQr) {
		this.sfQr = sfQr;
	}

	public String getQrJg() {
		return qrJg;
	}

	public void setQrJg(String qrJg) {
		this.qrJg = qrJg;
	}

	public String getPlqrStr() {
		return plqrStr;
	}

	public void setPlqrStr(String plqrStr) {
		this.plqrStr = plqrStr;
	}

	public String getClxx() {
		return clxx;
	}

	public void setClxx(String clxx) {
		this.clxx = clxx;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public Double getZcHj() {
		return zcHj;
	}

	public void setZcHj(Double zcHj) {
		this.zcHj = zcHj;
	}

	public Double getZcYj() {
		return zcYj;
	}

	public void setZcYj(Double zcYj) {
		this.zcYj = zcYj;
	}

	public Double getZcXf() {
		return zcXf;
	}

	public void setZcXf(Double zcXf) {
		this.zcXf = zcXf;
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

	public Double getZcHf() {
		return zcHf;
	}

	public void setZcHf(Double zcHf) {
		this.zcHf = zcHf;
	}

	public Double getZcHk() {
		return zcHk;
	}

	public void setZcHk(Double zcHk) {
		this.zcHk = zcHk;
	}

	public String getPchwClfsDm() {
		return pchwClfsDm;
	}

	public void setPchwClfsDm(String pchwClfsDm) {
		this.pchwClfsDm = pchwClfsDm;
	}

	public Double getZcDf() {
		return zcDf;
	}

	public void setZcDf(Double zcDf) {
		this.zcDf = zcDf;
	}

	public String getXtcs20030() {
		return xtcs20030;
	}

	public void setXtcs20030(String xtcs20030) {
		this.xtcs20030 = xtcs20030;
	}

	public String getQrPsf() {
		return qrPsf;
	}

	public void setQrPsf(String qrPsf) {
		this.qrPsf = qrPsf;
	}

	public String getPsfDjxh() {
		return psfDjxh;
	}

	public void setPsfDjxh(String psfDjxh) {
		this.psfDjxh = psfDjxh;
	}

	public List<String> getPlqrXhs() {
		if(plqrXhs == null){
			plqrXhs = new ArrayList<String>();
		}
		return plqrXhs;
	}

	public void setPlqrXhs(List<String> plqrXhs) {
		this.plqrXhs = plqrXhs;
	}

	public Integer getShbgBz() {
		return shbgBz;
	}

	public void setShbgBz(Integer shbgBz) {
		this.shbgBz = shbgBz;
	}

	public String getZrbmDmShow() {
		return zrbmDmShow;
	}

	public void setZrbmDmShow(String zrbmDmShow) {
		this.zrbmDmShow = zrbmDmShow;
	}

	public String getZrbmDjxhShow() {
		return zrbmDjxhShow;
	}

	public void setZrbmDjxhShow(String zrbmDjxhShow) {
		this.zrbmDjxhShow = zrbmDjxhShow;
	}

	public String getXtcs20000() {
		return xtcs20000;
	}

	public void setXtcs20000(String xtcs20000) {
		this.xtcs20000 = xtcs20000;
	}

	public String getXybz() {
		return xybz;
	}

	public void setXybz(String xybz) {
		this.xybz = xybz;
	}
	
}
