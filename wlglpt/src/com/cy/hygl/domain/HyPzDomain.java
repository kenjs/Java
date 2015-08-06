package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PZ is created by tools.
 * @author HJH
 */

public class HyPzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // ���صǼ����(SEQ_PZ_DJXH)
	private String hzJgbm;                           // ��վ��������
	private String clxhwhDjxh;                       // �����ͺ�ά�����
	private Double clCz;                             // ����_����(��)
	private Double clTj;                             // ����_���(��)
	private Double clCd;                             // ����_����(��)
	private Double clKd;                             // ����_���(��)
	private Double clGd;                             // ����_�߶�(��)
	private Double pzCz;                             // ����_����(��)
	private Double pzTj;                             // ����_���(��)
	private Double pzCd;                             // ����_����(��)
	private Double pzKd;                             // ����_���(��)
	private Double pzGd;                             // ����_�߶�(��)
	private Double pzsr;                             // ��������
	private Double pzcb;                             // ����Ԥ�Ƴɱ�
	private Double pzpsf;                            // ����Ԥ�����ͷ�
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	
	private String listPc;					// �Ƿ�Ϊ�б�ҳ����ɳ����б�ҳ����ɳ�ֱ�Ӵ����ݿ����ر����������ݶ�ȡ������mxҳ���е��ɳ������ҳ�洫���ݹ�ȥ
	
	/*******************�����б�****************************/
	private String fhrDjxh;
	private String hzmc;
	private String clxh;
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String ddbh;							//�������
	private String hwMc;    //��������
	private String hwbz;    //��װ
	private String sl;      //����
	private String zl;      //����
	private String tj;      //���
	private String fhrMc;   // ������_����
	private String fhrDz;   // ������_��ַ
	private Date yqFhrq;    // Ҫ�󷢻�����
	private String shrMc;   //�ջ���
	private String shrDz;   // �ջ���_��ַ
	private Date yqDdrq;    // Ҫ�󵽴�����
	private Long pageXh;

	/*********************����ѡ��ҳ��***********************/
	/**��ѯ����*****/
	private String dw4Query;
	private String djJgbm4Query;
	private String lb4Query;
	private String ddbh4Query;
	private String hwztDm4Query;
	private String fhrqQ;
	private String fhrqZ;
	
	private String pchwLsxh;							//����ʱѡ��Ļ��ﱣ�浽��ʱ��ÿһ�����ص���Ӧһ����ʱ���
	private String wfhXhs;
	private List<String> hwXh4PcDel;
	private List<String> tempBz;
	
	private List<String> wfhDjxhs;
	private List<Double> hwSls;
	private List<Double> hwZls;
	private List<Double> hwTjs;
	private List<Double> jssls;
	private List<String> bbhs;
	
	private List<HyTydWfhxxDomain> wfhList;
	private List<HyTydWfhxxDomain> pzHwxxList;
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<BaseBusinessDomain> pzList;
	private PzQingdanDomain qingDan;
	public PzQingdanDomain getQingDan() {
		if(qingDan==null){
			qingDan=new PzQingdanDomain();
		}
		return qingDan;
	}

	public void setQingDan(PzQingdanDomain qingDan) {
		this.qingDan = qingDan;
	}

	public List<BaseBusinessDomain> getPzList() {
		if(pzList==null){
			pzList=new ArrayList<BaseBusinessDomain>();
		}
		return pzList;
	}

	public void setPzList(List<BaseBusinessDomain> pzList) {
		this.pzList = pzList;
	}

	public HyPzDomain() {
	}

	//��ȡ���صǼ����(SEQ_PZ_DJXH)
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//�������صǼ����(SEQ_PZ_DJXH)
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//��ȡ��վ��������
	public String getHzJgbm() {
		return this.hzJgbm;
	}

	//���û�վ��������
	public void setHzJgbm(String hzJgbm) {
		this.hzJgbm=hzJgbm;
	}

	//��ȡ�����ͺ�ά�����
	public String getClxhwhDjxh() {
		return this.clxhwhDjxh;
	}

	//���ó����ͺ�ά�����
	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh=clxhwhDjxh;
	}

	//��ȡ����_����(��)
	public Double getClCz() {
		return this.clCz;
	}

	//���ó���_����(��)
	public void setClCz(Double clCz) {
		this.clCz=clCz;
	}

	//��ȡ����_���(��)
	public Double getClTj() {
		return this.clTj;
	}

	//���ó���_���(��)
	public void setClTj(Double clTj) {
		this.clTj=clTj;
	}

	//��ȡ����_����(��)
	public Double getClCd() {
		return this.clCd;
	}

	//���ó���_����(��)
	public void setClCd(Double clCd) {
		this.clCd=clCd;
	}

	//��ȡ����_���(��)
	public Double getClKd() {
		return this.clKd;
	}

	//���ó���_���(��)
	public void setClKd(Double clKd) {
		this.clKd=clKd;
	}

	//��ȡ����_�߶�(��)
	public Double getClGd() {
		return this.clGd;
	}

	//���ó���_�߶�(��)
	public void setClGd(Double clGd) {
		this.clGd=clGd;
	}

	//��ȡ����_����(��)
	public Double getPzCz() {
		return this.pzCz;
	}

	//��������_����(��)
	public void setPzCz(Double pzCz) {
		this.pzCz=pzCz;
	}

	//��ȡ����_���(��)
	public Double getPzTj() {
		return this.pzTj;
	}

	//��������_���(��)
	public void setPzTj(Double pzTj) {
		this.pzTj=pzTj;
	}

	//��ȡ����_����(��)
	public Double getPzCd() {
		return this.pzCd;
	}

	//��������_����(��)
	public void setPzCd(Double pzCd) {
		this.pzCd=pzCd;
	}

	//��ȡ����_���(��)
	public Double getPzKd() {
		return this.pzKd;
	}

	//��������_���(��)
	public void setPzKd(Double pzKd) {
		this.pzKd=pzKd;
	}

	//��ȡ����_�߶�(��)
	public Double getPzGd() {
		return this.pzGd;
	}

	//��������_�߶�(��)
	public void setPzGd(Double pzGd) {
		this.pzGd=pzGd;
	}

	//��ȡ��������
	public Double getPzsr() {
		return this.pzsr;
	}

	//������������
	public void setPzsr(Double pzsr) {
		this.pzsr=pzsr;
	}

	//��ȡ����Ԥ�Ƴɱ�
	public Double getPzcb() {
		return this.pzcb;
	}

	//��������Ԥ�Ƴɱ�
	public void setPzcb(Double pzcb) {
		this.pzcb=pzcb;
	}

	//��ȡ����Ԥ�����ͷ�
	public Double getPzpsf() {
		return this.pzpsf;
	}

	//��������Ԥ�����ͷ�
	public void setPzpsf(Double pzpsf) {
		this.pzpsf=pzpsf;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getWfhXhs() {
		return wfhXhs;
	}

	public void setWfhXhs(String wfhXhs) {
		this.wfhXhs = wfhXhs;
	}

	public List<String> getHwXh4PcDel() {
		return hwXh4PcDel;
	}

	public void setHwXh4PcDel(List<String> hwXh4PcDel) {
		this.hwXh4PcDel = hwXh4PcDel;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
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

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}

	public String getHwbz() {
		return hwbz;
	}

	public void setHwbz(String hwbz) {
		this.hwbz = hwbz;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDz() {
		return fhrDz;
	}

	public void setFhrDz(String fhrDz) {
		this.fhrDz = fhrDz;
	}

	public Date getYqFhrq() {
		return yqFhrq;
	}

	public void setYqFhrq(Date yqFhrq) {
		this.yqFhrq = yqFhrq;
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

	public Date getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public Long getPageXh() {
		return pageXh;
	}

	public void setPageXh(Long pageXh) {
		this.pageXh = pageXh;
	}

	public String getHzmc() {
		return hzmc;
	}

	public void setHzmc(String hzmc) {
		this.hzmc = hzmc;
	}

	public List<String> getTempBz() {
		return tempBz;
	}

	public void setTempBz(List<String> tempBz) {
		this.tempBz = tempBz;
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

	public String getListPc() {
		return listPc;
	}

	public void setListPc(String listPc) {
		this.listPc = listPc;
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

	public List<HyTydWfhxxDomain> getWfhList() {
		if (wfhList == null) {
			wfhList = new ArrayList<HyTydWfhxxDomain>();
		}
		return wfhList;
	}

	public void setWfhList(List<HyTydWfhxxDomain> wfhList) {
		this.wfhList = wfhList;
	}

	public List<HyTydWfhxxDomain> getPzHwxxList() {
		if (pzHwxxList == null) {
			pzHwxxList = new ArrayList<HyTydWfhxxDomain>();
		}
		return pzHwxxList;
	}

	public void setPzHwxxList(List<HyTydWfhxxDomain> pzHwxxList) {
		this.pzHwxxList = pzHwxxList;
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
}
