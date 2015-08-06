package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_TYD_HWMX is created by tools.
 * @author HJH
 */

public class HyTydHwmxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ddDjxh;                           // �����Ǽ����(SEQ_DD_DJXH)
	private String xh;                               // ������ϸ���
	private String hwmc;                             // ��������
	private String hwDjxh;                           // ����Ǽ����
	private String hwxhDjxh;                         // �����ͺŵǼ����
	private String hwBzHldwDm;                       // ����_��װ_������λ
	private String bz;
	private Double hwSl;                             // ����_����
	private String hwSlJldwDm;                       // ����_����_������λ
	private Double hwZl;                             // ����_����
	private String hwZlJldwDm;                       // ����_����_������λ
	private Double hwTj;                             // ����_���
	private String hwTjJldwDm;                       // ����_���_������λ
	private String yxbz;                             // ��Ч��־(Y/N)
	private String hwflDm;							 // ���������� �ػ�/�ݻ�
	private Double jsSl;							 // ��������
	private String jsJldwDm;						 // ���������λ����
	private String jsJldwFlDm;						 // ���������λ�������
	private String hdbh;							 // �ص����
	private Double srHj;                             // ����_С��
	private Double srYj;                             // ����_�½�
	private Double srXf;                             // ����_�ָ�
	private Double srHdf;                            // ����_������
	private Double srThf;                            // ����_�����
	private Double srHf;                             // ����_�ص���
	private Double srHk;                             // ����_�ؿ�
	private String fhrDjxh;                          // ������_�Ǽ����
	private String fhrMc;                            // ������_����
	private String fhrDz;                            // ������_��ַ
	private String fhrLxr;                           // ������_��ϵ��
	private String fhrLxdh;                          // ������_��ϵ�绰
	private String fhrYddh;                          // ������_�ƶ��绰
	private String fhrXzqhDm;                        // ������_������������
	private String shrDjxh;                          // �ջ���_�Ǽ����
	private String shrMc;                            // �ջ���_����
	private String shrDz;                            // �ջ���_��ַ
	private String shrLxr;                           // �ջ���_��ϵ��
	private String shrLxdh;                          // �ջ���_��ϵ�绰
	private String shrYddh;                          // �ջ���_�ƶ��绰
	private String shrXzqhDm;                        // �ջ���_������������
	private String yqFhrq;                           // Ҫ�󷢻�����
	private String yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private String thflDm;                           // ����������
	private String ykjsfsDm;                         // �����㷽ʽ����
	private String yjjsfsDm;                         // �˼۽��㷽ʽ����
	private String psbz;                             // ���ͱ�־(Y/N)
	private String hwhh;                             // �������
	
	private String fhrXzqhMc;
	private String shrXzqhMc;
	private String hwbzHldwMc;
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	private String jsJldwMc;
	private String shfsMc;
	private String dxSrHj;
	
	private String qyHwBzJldwDm;
	private String qyHwSlJldwDm;
	private String qyHwZlJldwDm;
	private String qyHwTjJldwDm;	
	private Double ZlTjProportion;
	
	private Date xdrq;
	private Double srYsf; //�����
	private Double srBjf; //���۷�
	private Double srPsf; //���ͷ�
	private Double  fySmjz; //������ֵ
	private Double fyDshk;						//	���ջ���
	private Double sl;    //˰��
	private String kpbz;  //��Ʊ��־ 
	private String sfhdBz;  //�Ƿ�ǩ�ջص���־ Y/N
	
	private Double srHjMz; //���ڴ�ӡ�ϼ�
	private String srHjMzDx; //��д
	private String srhjDx;						// ����ϼƴ�д���
	private String fyDshkDx;
	private String ddbh;
	
	private String bgPsf;//������ͷ�
	private String bgHj;//����ϼ�
	private String bgDf;//�������
	
	private String tempFlag;					//�Ƿ�����ʱ���ݣ�'Y':��  'N':��

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyTydHwmxDomain() {
	}

	//��ȡ�����Ǽ����(SEQ_DD_DJXH)
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����(SEQ_DD_DJXH)
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

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	public Double getJsSl() {
		return jsSl;
	}

	public void setJsSl(Double jsSl) {
		this.jsSl = jsSl;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public Double getSrHj() {
		return srHj;
	}

	public void setSrHj(Double srHj) {
		this.srHj = srHj;
	}

	public Double getSrYj() {
		return srYj;
	}

	public void setSrYj(Double srYj) {
		this.srYj = srYj;
	}

	public Double getSrXf() {
		return srXf;
	}

	public void setSrXf(Double srXf) {
		this.srXf = srXf;
	}

	public Double getSrHdf() {
		return srHdf;
	}

	public void setSrHdf(Double srHdf) {
		this.srHdf = srHdf;
	}

	public Double getSrThf() {
		return srThf;
	}

	public void setSrThf(Double srThf) {
		this.srThf = srThf;
	}

	public Double getSrHf() {
		return srHf;
	}

	public void setSrHf(Double srHf) {
		this.srHf = srHf;
	}

	public Double getSrHk() {
		return srHk;
	}

	public void setSrHk(Double srHk) {
		this.srHk = srHk;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
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

	public String getFhrLxr() {
		return fhrLxr;
	}

	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}

	public String getFhrLxdh() {
		return fhrLxdh;
	}

	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh = fhrLxdh;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
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

	public String getYqFhrq() {
		return yqFhrq;
	}

	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq = yqFhrq;
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

	public String getThflDm() {
		return thflDm;
	}

	public void setThflDm(String thflDm) {
		this.thflDm = thflDm;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public String getYjjsfsDm() {
		return yjjsfsDm;
	}

	public void setYjjsfsDm(String yjjsfsDm) {
		this.yjjsfsDm = yjjsfsDm;
	}

	public String getPsbz() {
		return psbz;
	}

	public void setPsbz(String psbz) {
		this.psbz = psbz;
	}

	public String getJsJldwDm() {
		return jsJldwDm;
	}

	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm = jsJldwDm;
	}

	public String getJsJldwFlDm() {
		return jsJldwFlDm;
	}

	public void setJsJldwFlDm(String jsJldwFlDm) {
		this.jsJldwFlDm = jsJldwFlDm;
	}

	public String getHwbzHldwMc() {
		return hwbzHldwMc;
	}

	public void setHwbzHldwMc(String hwbzHldwMc) {
		this.hwbzHldwMc = hwbzHldwMc;
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

	public String getQyHwBzJldwDm() {
		return qyHwBzJldwDm;
	}

	public void setQyHwBzJldwDm(String qyHwBzJldwDm) {
		this.qyHwBzJldwDm = qyHwBzJldwDm;
	}

	public String getQyHwSlJldwDm() {
		return qyHwSlJldwDm;
	}

	public void setQyHwSlJldwDm(String qyHwSlJldwDm) {
		this.qyHwSlJldwDm = qyHwSlJldwDm;
	}

	public String getQyHwZlJldwDm() {
		return qyHwZlJldwDm;
	}

	public void setQyHwZlJldwDm(String qyHwZlJldwDm) {
		this.qyHwZlJldwDm = qyHwZlJldwDm;
	}

	public String getQyHwTjJldwDm() {
		return qyHwTjJldwDm;
	}

	public void setQyHwTjJldwDm(String qyHwTjJldwDm) {
		this.qyHwTjJldwDm = qyHwTjJldwDm;
	}

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public Double getZlTjProportion() {
		return ZlTjProportion;
	}

	public void setZlTjProportion(Double zlTjProportion) {
		ZlTjProportion = zlTjProportion;
	}

	public String getHwflDm() {
		return hwflDm;
	}

	public void setHwflDm(String hwflDm) {
		this.hwflDm = hwflDm;
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

	public String getTempFlag() {
		return tempFlag;
	}

	public void setTempFlag(String tempFlag) {
		this.tempFlag = tempFlag;
	}

	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getShfsMc() {
		return shfsMc;
	}

	public void setShfsMc(String shfsMc) {
		this.shfsMc = shfsMc;
	}

	public String getDxSrHj() {
		return dxSrHj;
	}

	public void setDxSrHj(String dxSrHj) {
		this.dxSrHj = dxSrHj;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public Double getSrYsf() {
		return srYsf;
	}

	public void setSrYsf(Double srYsf) {
		this.srYsf = srYsf;
	}

	public Double getSrBjf() {
		return srBjf;
	}

	public void setSrBjf(Double srBjf) {
		this.srBjf = srBjf;
	}

	public Double getSrPsf() {
		return srPsf;
	}

	public void setSrPsf(Double srPsf) {
		this.srPsf = srPsf;
	}

	public Double getFyDshk() {
		return fyDshk;
	}

	public void setFyDshk(Double fyDshk) {
		this.fyDshk = fyDshk;
	}

	public String getSrhjDx() {
		return srhjDx;
	}

	public void setSrhjDx(String srhjDx) {
		this.srhjDx = srhjDx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Double getFySmjz() {
		return fySmjz;
	}

	public void setFySmjz(Double fySmjz) {
		this.fySmjz = fySmjz;
	}

	public String getFyDshkDx() {
		return fyDshkDx;
	}

	public void setFyDshkDx(String fyDshkDx) {
		this.fyDshkDx = fyDshkDx;
	}

	public String getFhrYddh() {
		return fhrYddh;
	}

	public void setFhrYddh(String fhrYddh) {
		this.fhrYddh = fhrYddh;
	}

	public String getShrYddh() {
		return shrYddh;
	}

	public void setShrYddh(String shrYddh) {
		this.shrYddh = shrYddh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	public String getKpbz() {
		return kpbz;
	}

	public void setKpbz(String kpbz) {
		this.kpbz = kpbz;
	}
	

	public Double getSrHjMz() {
		return srHjMz;
	}

	public void setSrHjMz(Double srHjMz) {
		this.srHjMz = srHjMz;
	}

	public String getSrHjMzDx() {
		return srHjMzDx;
	}

	public void setSrHjMzDx(String srHjMzDx) {
		this.srHjMzDx = srHjMzDx;
	}

	public String getBgPsf() {
		return bgPsf;
	}

	public void setBgPsf(String bgPsf) {
		this.bgPsf = bgPsf;
	}

	public String getBgHj() {
		return bgHj;
	}

	public void setBgHj(String bgHj) {
		this.bgHj = bgHj;
	}

	public String getBgDf() {
		return bgDf;
	}

	public void setBgDf(String bgDf) {
		this.bgDf = bgDf;
	}

	public String getSfhdBz() {
		return sfhdBz;
	}

	public void setSfhdBz(String sfhdBz) {
		this.sfhdBz = sfhdBz;
	}

	public String getHwhh() {
		return hwhh;
	}

	public void setHwhh(String hwhh) {
		this.hwhh = hwhh;
	}
}
