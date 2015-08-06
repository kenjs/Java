package com.cy.hygl.domain;
import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR JS_KPSQ is created by tools.
 * @author HJH
 */

public class JsKpsqDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqDjxh;                         // ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	private String kpsqfsDm;                         // ��Ʊ���뷽ʽ����
	private String kpsqfsMc;
	private String khDjxh;                           // �ͻ��Ǽ����
	private String khMc;
	private Double sqKpjeHj;                         // ���뿪Ʊ���ϼ�
	private String sqKprq;                           // ���뿪Ʊ����
	private String bzsm;                             // ��ע˵��
	private String djrCzyDjxh;                       // �Ǽ���
	private String cjrMc;
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String bmMc;
	private String ssJgbm;                           // ��������
	private String dwMc;
	private String yxbz;                             // ��Ч��־(Y/N)
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsspztMc;
	private String wsSpxh;                           // �����������
	private String ykpQdhx;                          // Ԥ��Ʊ�嵥������־(Y/N)
	private String kpDwJgbm;                         //  ��Ʊ��λ
	private String kpDwJgMc;
	private String fpkjbz;							//��Ʊ���߱�־
	private Double fpkjJe;                         // ��Ʊ���߽��
	
	private String shf;								//�ջ���
	private String shfSbh;							//�ջ���ʶ���
	private String fhf;								//������
	private String fhfSbh;							//������ʶ���
	private String ydrq;							//Ԥ������
	private String dj;								//����
	private String mc;								//����			
	private String sl;								//����		
	private String dkf;								//�ֿ۷�
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private String rqQ;
	private String rqZ;
	
	private String existBz;
	
	private String kpsqmxDjxh;  //��Ʊ������ϸ�Ǽ����(SEQ_KPSQMX_DJXH)
	private String qdDjxh;                           // �嵥�Ǽ����(SEQ_QD_DJXH) ҵ��Ǽ���ţ��Ӿ���ҵ��)
	private String kpsqMxflDm;                       // ��Ʊ������ϸ�������
	private String qdmc;                             // �嵥����
	private Double heJe;                             // �ϼƽ��
	private Double ysqKpJe;                             // �����뿪Ʊ���
	private Double wsqKpJe;                             // δ���뿪Ʊ���
	private Double sqKpJe;                             // ���뿪Ʊ���
	
	private String xtcsSfsp;
	
	private List<DmbGgDomain> qdList;
	
	private List<String> kpsqmxDjxhs;
	
	private String dzQdXgbz;//����-��Ʊ����-�����嵥�޸ı�־
	private List<JsKpsqDomain> srKpList;
	private String ysyfDjxh;
	private String ysfJe;
	private String yfjsfDjxh;
	private String kmxlMc;
	private String ysyflyMc;
	private String csrq;
	private String sm;
	private List<String> ywDjxhStr;
	private List<String> jeStr;
	private String ywDjxhs;
	private String flag;
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getYwDjxhs() {
		return ywDjxhs;
	}

	public void setYwDjxhs(String ywDjxhs) {
		this.ywDjxhs = ywDjxhs;
	}

	public List<String> getYwDjxhStr() {
		return ywDjxhStr;
	}

	public void setYwDjxhStr(List<String> ywDjxhStr) {
		this.ywDjxhStr = ywDjxhStr;
	}

	public List<String> getJeStr() {
		return jeStr;
	}

	public void setJeStr(List<String> jeStr) {
		this.jeStr = jeStr;
	}

	public String getYsyfDjxh() {
		return ysyfDjxh;
	}

	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}

	public String getYsfJe() {
		return ysfJe;
	}

	public void setYsfJe(String ysfJe) {
		this.ysfJe = ysfJe;
	}

	public String getYfjsfDjxh() {
		return yfjsfDjxh;
	}

	public void setYfjsfDjxh(String yfjsfDjxh) {
		this.yfjsfDjxh = yfjsfDjxh;
	}

	public String getKmxlMc() {
		return kmxlMc;
	}

	public void setKmxlMc(String kmxlMc) {
		this.kmxlMc = kmxlMc;
	}

	public String getYsyflyMc() {
		return ysyflyMc;
	}

	public void setYsyflyMc(String ysyflyMc) {
		this.ysyflyMc = ysyflyMc;
	}

	public String getCsrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.csrq);
		}
		catch(Exception e){
			return this.csrq;
		}
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public List<JsKpsqDomain> getSrKpList() {
		if(srKpList==null){
			srKpList=new ArrayList<JsKpsqDomain>();
		}
		return srKpList;
	}

	public void setSrKpList(List<JsKpsqDomain> srKpList) {
		this.srKpList = srKpList;
	}

	public JsKpsqDomain() {
	}

	//��ȡ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//���ÿ�Ʊ����Ǽ����(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//��ȡ��Ʊ���뷽ʽ����
	public String getKpsqfsDm() {
		return this.kpsqfsDm;
	}

	//���ÿ�Ʊ���뷽ʽ����
	public void setKpsqfsDm(String kpsqfsDm) {
		this.kpsqfsDm=kpsqfsDm;
	}

	//��ȡ�ͻ��Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ���뿪Ʊ���ϼ�
	public Double getSqKpjeHj() {
		return this.sqKpjeHj;
	}

	//�������뿪Ʊ���ϼ�
	public void setSqKpjeHj(Double sqKpjeHj) {
		this.sqKpjeHj=sqKpjeHj;
	}

	//��ȡ���뿪Ʊ����
	public String getSqKprq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sqKprq);
		}
		catch(Exception e){
			return this.sqKprq;
		}
	}

	//�������뿪Ʊ����
	public void setSqKprq(String sqKprq) {
		this.sqKprq=sqKprq;
	}

	//��ȡ��ע˵��
	public String getBzsm() {
		return this.bzsm;
	}

	//���ñ�ע˵��
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
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

	//��ȡԤ��Ʊ�嵥������־(Y/N)
	public String getYkpQdhx() {
		return this.ykpQdhx;
	}

	//����Ԥ��Ʊ�嵥������־(Y/N)
	public void setYkpQdhx(String ykpQdhx) {
		this.ykpQdhx=ykpQdhx;
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

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getKpsqfsMc() {
		return kpsqfsMc;
	}

	public void setKpsqfsMc(String kpsqfsMc) {
		this.kpsqfsMc = kpsqfsMc;
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

	public String getExistBz() {
		return existBz;
	}

	public void setExistBz(String existBz) {
		this.existBz = existBz;
	}

	public String getKpDwJgbm() {
		return kpDwJgbm;
	}

	public void setKpDwJgbm(String kpDwJgbm) {
		this.kpDwJgbm = kpDwJgbm;
	}

	public String getKpDwJgMc() {
		return kpDwJgMc;
	}

	public void setKpDwJgMc(String kpDwJgMc) {
		this.kpDwJgMc = kpDwJgMc;
	}

	public String getFpkjbz() {
		return fpkjbz;
	}

	public void setFpkjbz(String fpkjbz) {
		this.fpkjbz = fpkjbz;
	}

	public Double getFpkjJe() {
		return fpkjJe;
	}

	public void setFpkjJe(Double fpkjJe) {
		this.fpkjJe = fpkjJe;
	}

	public Double getHeJe() {
		return heJe;
	}

	public void setHeJe(Double heJe) {
		this.heJe = heJe;
	}

	public String getQdDjxh() {
		return qdDjxh;
	}

	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh = qdDjxh;
	}

	public Double getSqKpJe() {
		return sqKpJe;
	}

	public void setSqKpJe(Double sqKpJe) {
		this.sqKpJe = sqKpJe;
	}

	public Double getWsqKpJe() {
		return wsqKpJe;
	}

	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}

	public Double getYsqKpJe() {
		return ysqKpJe;
	}

	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}

	public String getQdmc() {
		return qdmc;
	}

	public void setQdmc(String qdmc) {
		this.qdmc = qdmc;
	}

	public List<DmbGgDomain> getQdList() {
		if(null==qdList)
			qdList=new ArrayList<DmbGgDomain>();
		return qdList;
	}

	public void setQdList(List<DmbGgDomain> qdList) {
		this.qdList = qdList;
	}

	public String getDzQdXgbz() {
		return dzQdXgbz;
	}

	public void setDzQdXgbz(String dzQdXgbz) {
		this.dzQdXgbz = dzQdXgbz;
	}

	public String getKpsqmxDjxh() {
		return kpsqmxDjxh;
	}

	public void setKpsqmxDjxh(String kpsqmxDjxh) {
		this.kpsqmxDjxh = kpsqmxDjxh;
	}

	public String getKpsqMxflDm() {
		return kpsqMxflDm;
	}

	public void setKpsqMxflDm(String kpsqMxflDm) {
		this.kpsqMxflDm = kpsqMxflDm;
	}

	public List<String> getKpsqmxDjxhs() {
		if(null==kpsqmxDjxhs)
			kpsqmxDjxhs=new ArrayList<String>();
		return kpsqmxDjxhs;
	}

	public void setKpsqmxDjxhs(List<String> kpsqmxDjxhs) {
		this.kpsqmxDjxhs = kpsqmxDjxhs;
	}

	public String getWsspztMc() {
		return wsspztMc;
	}

	public void setWsspztMc(String wsspztMc) {
		this.wsspztMc = wsspztMc;
	}

	public String getXtcsSfsp() {
		return xtcsSfsp;
	}

	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}

	public String getShf() {
		return shf;
	}

	public void setShf(String shf) {
		this.shf = shf;
	}

	public String getShfSbh() {
		return shfSbh;
	}

	public void setShfSbh(String shfSbh) {
		this.shfSbh = shfSbh;
	}

	public String getFhf() {
		return fhf;
	}

	public void setFhf(String fhf) {
		this.fhf = fhf;
	}

	public String getFhfSbh() {
		return fhfSbh;
	}

	public void setFhfSbh(String fhfSbh) {
		this.fhfSbh = fhfSbh;
	}

	public String getYdrq() {
		return ydrq;
	}

	public void setYdrq(String ydrq) {
		this.ydrq = ydrq;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getDkf() {
		return dkf;
	}

	public void setDkf(String dkf) {
		this.dkf = dkf;
	}
}
