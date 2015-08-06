package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_HWXX is created by tools.
 * @author HJH
 */

public class HyPcHwxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // δ�����Ǽ����
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(�����ƻ�����ϸ���)
	private String fhrMc;                            // ������_����
	private String fhrDz;                            // ������_��ַ
	private String fhrLxr;                           // ������_��ϵ��
	private String fhrLxdh;                          // ������_��ϵ�绰
	private String fhrXzqhDm;                        // ������_������������
	private String shrMc;                            // �ջ���_����
	private String shrDz;                            // �ջ���_��ַ
	private String shrLxr;                           // �ջ���_��ϵ��
	private String shrLxdh;                          // �ջ���_��ϵ�绰
	private String shrXzqhDm;                        // �ջ���_������������
	private String hwmc;                             // ��������
	private String hwDjxh;                           // ����Ǽ����
	private String hwxhDjxh;                         // �����ͺŵǼ����
	private String hwBzHldwDm;                       // ����_��װ_������λ
	private Double hwSl;                             // ����_����
	private String hwSlJldwDm;                       // ����_����_������λ
	private Double hwZl;                             // ����_����
	private String hwZlJldwDm;                       // ����_����_������λ
	private Double hwTj;                             // ����_���
	private String hwTjJldwDm;                       // ����_���_������λ
	private String yqFhrq;                           // Ҫ�󷢻�����
	private String yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private String bz;                               // ��ע
	private String hdbh;                             // �ص����
	private Double jsSl;
	private String jsJldwDm;
	private String jldwFlDm;
	private Double sr;
	private Double cbft;
	private String pchwClfsDm;					
	private String zrbmDm;
	private String zrbmDjxh;
	private Double zcHj;                             // ֧��_�ϼ�
	private Double zcYj;                             // ֧��_�½�
	private Double zcXf;                             // ֧��_�ָ�
	private Double zcHdf;                            // ֧��_������
	private Double zcThf;                            // ֧��_�����
	private Double zcHf;                             // ֧��_�ص���
	private Double zcHk;                             // ֧��_�ؿ�
	private String ykjsfsDm;
	private String bbh;
	private String yxbz;
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;
	
	private Double srdf;
	private Double yfSjs;
	private String pcfsDm;
	
	private String hwBzJldwMc;
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	private String jsJldwMc;
	private String shrXzqhMc;
	private String shrDjxh;
	private String pchwLsxh;							// �ɳ�ʱѡ��Ļ��ﱣ�浽��ʱ��ÿһ���ɳ�����Ӧһ����ʱ���
	private Double kfHwsl;								// �ɷ���������

	private String bgDf;//�������
	
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyPcHwxxDomain() {
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ���(�����ƻ�����ϸ���)
	public String getXh() {
		return this.xh;
	}

	//�������(�����ƻ�����ϸ���)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ������_����
	public String getFhrMc() {
		return this.fhrMc;
	}

	//���÷�����_����
	public void setFhrMc(String fhrMc) {
		this.fhrMc=fhrMc;
	}

	//��ȡ������_��ַ
	public String getFhrDz() {
		return this.fhrDz;
	}

	//���÷�����_��ַ
	public void setFhrDz(String fhrDz) {
		this.fhrDz=fhrDz;
	}

	//��ȡ������_��ϵ��
	public String getFhrLxr() {
		return this.fhrLxr;
	}

	//���÷�����_��ϵ��
	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr=fhrLxr;
	}

	//��ȡ������_��ϵ�绰
	public String getFhrLxdh() {
		return this.fhrLxdh;
	}

	//���÷�����_��ϵ�绰
	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh=fhrLxdh;
	}

	//��ȡ������_������������
	public String getFhrXzqhDm() {
		return this.fhrXzqhDm;
	}

	//���÷�����_������������
	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm=fhrXzqhDm;
	}

	//��ȡ�ջ���_����
	public String getShrMc() {
		return this.shrMc;
	}

	//�����ջ���_����
	public void setShrMc(String shrMc) {
		this.shrMc=shrMc;
	}

	//��ȡ�ջ���_��ַ
	public String getShrDz() {
		return this.shrDz;
	}

	//�����ջ���_��ַ
	public void setShrDz(String shrDz) {
		this.shrDz=shrDz;
	}

	//��ȡ�ջ���_��ϵ��
	public String getShrLxr() {
		return this.shrLxr;
	}

	//�����ջ���_��ϵ��
	public void setShrLxr(String shrLxr) {
		this.shrLxr=shrLxr;
	}

	//��ȡ�ջ���_��ϵ�绰
	public String getShrLxdh() {
		return this.shrLxdh;
	}

	//�����ջ���_��ϵ�绰
	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh=shrLxdh;
	}

	//��ȡ�ջ���_������������
	public String getShrXzqhDm() {
		return this.shrXzqhDm;
	}

	//�����ջ���_������������
	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm=shrXzqhDm;
	}

	//��ȡ��������
	public String getHwmc() {
		return this.hwmc;
	}

	//���û�������
	public void setHwmc(String hwmc) {
		this.hwmc=hwmc;
	}

	//��ȡ����Ǽ����
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//���û���Ǽ����
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
	}

	//��ȡ�����ͺŵǼ����
	public String getHwxhDjxh() {
		return this.hwxhDjxh;
	}

	//���û����ͺŵǼ����
	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh=hwxhDjxh;
	}

	//��ȡ����_��װ_������λ
	public String getHwBzHldwDm() {
		return this.hwBzHldwDm;
	}

	//���û���_��װ_������λ
	public void setHwBzHldwDm(String hwBzHldwDm) {
		this.hwBzHldwDm=hwBzHldwDm;
	}

	//��ȡ����_����
	public Double getHwSl() {
		return this.hwSl;
	}

	//���û���_����
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//��ȡ����_����_������λ
	public String getHwSlJldwDm() {
		return this.hwSlJldwDm;
	}

	//���û���_����_������λ
	public void setHwSlJldwDm(String hwSlJldwDm) {
		this.hwSlJldwDm=hwSlJldwDm;
	}

	//��ȡ����_����
	public Double getHwZl() {
		return this.hwZl;
	}

	//���û���_����
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//��ȡ����_����_������λ
	public String getHwZlJldwDm() {
		return this.hwZlJldwDm;
	}

	//���û���_����_������λ
	public void setHwZlJldwDm(String hwZlJldwDm) {
		this.hwZlJldwDm=hwZlJldwDm;
	}

	//��ȡ����_���
	public Double getHwTj() {
		return this.hwTj;
	}

	//���û���_���
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//��ȡ����_���_������λ
	public String getHwTjJldwDm() {
		return this.hwTjJldwDm;
	}

	//���û���_���_������λ
	public void setHwTjJldwDm(String hwTjJldwDm) {
		this.hwTjJldwDm=hwTjJldwDm;
	}

	//��ȡҪ�󷢻�����
	public String getYqFhrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yqFhrq);
		}
		catch(Exception e){
			return this.yqFhrq;
		}
	}

	//����Ҫ�󷢻�����
	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq=yqFhrq;
	}

	//��ȡҪ�󵽴�����
	public String getYqDdrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yqDdrq);
		}
		catch(Exception e){
			return this.yqDdrq;
		}
	}

	//����Ҫ�󵽴�����
	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq=yqDdrq;
	}

	//��ȡ�ջ���ʽ����
	public String getShfsDm() {
		return this.shfsDm;
	}

	//�����ջ���ʽ����
	public void setShfsDm(String shfsDm) {
		this.shfsDm=shfsDm;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡδ�����Ǽ����
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//����δ�����Ǽ����
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//��ȡ�ص����
	public String getHdbh() {
		return this.hdbh;
	}

	//���ûص����
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	public String getHwBzJldwMc() {
		return hwBzJldwMc;
	}

	public void setHwBzJldwMc(String hwBzJldwMc) {
		this.hwBzJldwMc = hwBzJldwMc;
	}

	public String getHwSlJldwMc() {
		return hwSlJldwMc;
	}

	public void setHwSlJldwMc(String hwSlJldwMc) {
		this.hwSlJldwMc = hwSlJldwMc;
	}

	public String getHwZlJldwMc() {
		return hwZlJldwMc;
	}

	public void setHwZlJldwMc(String hwZlJldwMc) {
		this.hwZlJldwMc = hwZlJldwMc;
	}

	public String getHwTjJldwMc() {
		return hwTjJldwMc;
	}

	public void setHwTjJldwMc(String hwTjJldwMc) {
		this.hwTjJldwMc = hwTjJldwMc;
	}

	public Double getJsSl() {
		return jsSl;
	}

	public void setJsSl(Double jsSl) {
		this.jsSl = jsSl;
	}

	public String getJsJldwDm() {
		return jsJldwDm;
	}

	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm = jsJldwDm;
	}

	public String getJldwFlDm() {
		return jldwFlDm;
	}

	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm = jldwFlDm;
	}

	public Double getSr() {
		return sr;
	}

	public void setSr(Double sr) {
		this.sr = sr;
	}

	public Double getCbft() {
		return cbft;
	}

	public void setCbft(Double cbft) {
		this.cbft = cbft;
	}

	public String getPchwClfsDm() {
		return pchwClfsDm;
	}

	public void setPchwClfsDm(String pchwClfsDm) {
		this.pchwClfsDm = pchwClfsDm;
	}

	public String getZrbmDm() {
		return zrbmDm;
	}

	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm = zrbmDm;
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
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

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
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

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getShrDjxh() {
		return shrDjxh;
	}

	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh = shrDjxh;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public Double getKfHwsl() {
		return kfHwsl;
	}

	public void setKfHwsl(Double kfHwsl) {
		this.kfHwsl = kfHwsl;
	}

	public Double getYfSjs() {
		return yfSjs;
	}

	public void setYfSjs(Double yfSjs) {
		this.yfSjs = yfSjs;
	}

	public Double getSrdf() {
		return srdf;
	}

	public void setSrdf(Double srdf) {
		this.srdf = srdf;
	}

	public String getPcfsDm() {
		return pcfsDm;
	}

	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
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

	public String getBgDf() {
		return bgDf;
	}

	public void setBgDf(String bgDf) {
		this.bgDf = bgDf;
	}
}
