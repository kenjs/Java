package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_HWXX_HDQD is created by tools.
 * @author HJH
 */

public class HyPcHwxxHdqdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdqdDjxh;                         // �ص��嵥�Ǽ����(SEQ_HD_DJXH)
	private String qdmc;                             // �嵥����
	private String jszt;                             // ����״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	private String fsGsbm;                           // ���͹�˾����
	private String jsGsbm;                           // ���չ�˾����
	private String bz;                               // ��ע
	private String ssJgbm;                           // ��������
	private String djJgbm;                           // �Ǽǲ���
	private String dbrCzyDjxh;                       // ����˲���Ա�Ǽ����
	private String dbrq;                             // �������
	private String yxbz;                             // ��Ч��־(Y/N)

	private String ssJgmc;
	private String fsGsmc;                           // ���͹�˾
	private String jsGsmc;                           // ���չ�˾
	private String djJgmc;                           // �Ǽǲ���
	private String dbrMc;                            // �����
	private String rqQ;
	private String rqZ;
	/**********�ص�************/
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double szHwSl;                           // ʵװ_����_����
	private Double szHwZl;                           // ʵװ_����_����
	private Double szHwTj;                           // ʵװ_����_���
	private String yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private Double szJsSl;                           // ʵװ_��������
	private String hdbh;                             // �ص����(����Ѷ��ŷָ�)
	private String hdjsrq;                           // �ص���������

	private String spbz;
	private String hdDjxh;                           // �ص��Ǽ����
	
	
	private String pcdh;
	private String pcrq;
	private String pcfsDm;
	private String pcfsMc;
	private String zcfsDm;
	private String zcfsMc;
	private String cyrClhm;
	
	private String cyrGchm;
	private String cyrSjxm;
	private String yfHj;
	private String yfYfyf;
	private String pcCzyDjxh;
	private String pcrMc;
	private String pcJgbm;
	private String pcJgmc;
	private String dqztbz;//��ǰ״̬��־
	
	private String qdCxBz;//�嵥��ѯ��־ 0 ȫ�� 1���͵� 2 ���յ�

	private List<String> hdDjxhs;
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyPcHwxxHdqdDomain() {
	}

	//��ȡ�ص��嵥�Ǽ����(SEQ_HD_DJXH)
	public String getHdqdDjxh() {
		return this.hdqdDjxh;
	}

	//���ûص��嵥�Ǽ����(SEQ_HD_DJXH)
	public void setHdqdDjxh(String hdqdDjxh) {
		this.hdqdDjxh=hdqdDjxh;
	}

	//��ȡ�嵥����
	public String getQdmc() {
		return this.qdmc;
	}

	//�����嵥����
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//��ȡ����״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	public String getJszt() {
		return this.jszt;
	}

	//���ý���״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	public void setJszt(String jszt) {
		this.jszt=jszt;
	}

	//��ȡ���͹�˾����
	public String getFsGsbm() {
		return this.fsGsbm;
	}

	//���÷��͹�˾����
	public void setFsGsbm(String fsGsbm) {
		this.fsGsbm=fsGsbm;
	}

	//��ȡ���չ�˾����
	public String getJsGsbm() {
		return this.jsGsbm;
	}

	//���ý��չ�˾����
	public void setJsGsbm(String jsGsbm) {
		this.jsGsbm=jsGsbm;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ����˲���Ա�Ǽ����
	public String getDbrCzyDjxh() {
		return this.dbrCzyDjxh;
	}

	//���ô���˲���Ա�Ǽ����
	public void setDbrCzyDjxh(String dbrCzyDjxh) {
		this.dbrCzyDjxh=dbrCzyDjxh;
	}

	//��ȡ�������
	public String getDbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.dbrq);
		}
		catch(Exception e){
			return this.dbrq;
		}
	}

	//���ô������
	public void setDbrq(String dbrq) {
		this.dbrq=dbrq;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	public String getPcDjxh() {
		return pcDjxh;
	}

	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
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

	public String getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(String yqDdrq) {
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


	public String getHdjsrq() {
		return hdjsrq;
	}

	public void setHdjsrq(String hdjsrq) {
		this.hdjsrq = hdjsrq;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
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

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getPcrq() {
		return pcrq;
	}

	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}

	public String getPcfsDm() {
		return pcfsDm;
	}

	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
	}

	public String getPcfsMc() {
		return pcfsMc;
	}

	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}

	public String getZcfsDm() {
		return zcfsDm;
	}

	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm = zcfsDm;
	}

	public String getZcfsMc() {
		return zcfsMc;
	}

	public void setZcfsMc(String zcfsMc) {
		this.zcfsMc = zcfsMc;
	}

	public String getCyrClhm() {
		return cyrClhm;
	}

	public void setCyrClhm(String cyrClhm) {
		this.cyrClhm = cyrClhm;
	}

	public String getCyrGchm() {
		return cyrGchm;
	}

	public void setCyrGchm(String cyrGchm) {
		this.cyrGchm = cyrGchm;
	}

	public String getCyrSjxm() {
		return cyrSjxm;
	}

	public void setCyrSjxm(String cyrSjxm) {
		this.cyrSjxm = cyrSjxm;
	}

	public String getYfHj() {
		return yfHj;
	}

	public void setYfHj(String yfHj) {
		this.yfHj = yfHj;
	}

	public String getYfYfyf() {
		return yfYfyf;
	}

	public void setYfYfyf(String yfYfyf) {
		this.yfYfyf = yfYfyf;
	}

	public String getPcCzyDjxh() {
		return pcCzyDjxh;
	}

	public void setPcCzyDjxh(String pcCzyDjxh) {
		this.pcCzyDjxh = pcCzyDjxh;
	}

	public String getPcrMc() {
		return pcrMc;
	}

	public void setPcrMc(String pcrMc) {
		this.pcrMc = pcrMc;
	}

	public String getPcJgbm() {
		return pcJgbm;
	}

	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm = pcJgbm;
	}

	public String getPcJgmc() {
		return pcJgmc;
	}

	public void setPcJgmc(String pcJgmc) {
		this.pcJgmc = pcJgmc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public List<String> getHdDjxhs() {
		return hdDjxhs;
	}

	public void setHdDjxhs(List<String> hdDjxhs) {
		this.hdDjxhs = hdDjxhs;
	}

	public String getDqztbz() {
		return dqztbz;
	}

	public void setDqztbz(String dqztbz) {
		this.dqztbz = dqztbz;
	}

	public String getFsGsmc() {
		return fsGsmc;
	}

	public void setFsGsmc(String fsGsmc) {
		this.fsGsmc = fsGsmc;
	}

	public String getJsGsmc() {
		return jsGsmc;
	}

	public void setJsGsmc(String jsGsmc) {
		this.jsGsmc = jsGsmc;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}

	public String getDbrMc() {
		return dbrMc;
	}

	public void setDbrMc(String dbrMc) {
		this.dbrMc = dbrMc;
	}

	public String getQdCxBz() {
		return qdCxBz;
	}

	public void setQdCxBz(String qdCxBz) {
		this.qdCxBz = qdCxBz;
	}
	
}
