package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For HY_TYD_HWMX is created by tools.
 * @author HJH
 */

public class HyTydHwmx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ������ϸ���
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
	private String yxbz;                             // ��Ч��־(Y/N)
	private String hwflDm;                           // ����������
	private Double jsSl;                             // ��������
	private String jsJldwDm;                         // ���������λ
	private String jsJldwFlDm;                       // ���������λ�������
	private String hdbh;                             // �ص����(����Ѷ��ŷָ�)
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
	private String fhrXzqhDm;                        // ������_������������
	private String shrDjxh;                          // �ջ���_�Ǽ����
	private String shrMc;                            // �ջ���_����
	private String shrDz;                            // �ջ���_��ַ
	private String shrLxr;                           // �ջ���_��ϵ��
	private String shrLxdh;                          // �ջ���_��ϵ�绰
	private String shrXzqhDm;                        // �ջ���_������������
	private String yqFhrq;                           // Ҫ�󷢻�����
	private String yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private String thflDm;                           // ����������
	private String ykjsfsDm;                         // �����㷽ʽ����
	private String yjjsfsDm;                         // �˼۽��㷽ʽ����
	private String psbz;                             // ���ͱ�־(Y/N)
	private String hwhh;                             // �������
	
	private Double srYsf; //�����
	private Double srBjf; //���۷�
	private Double srPsf; //���ͷ�
	private Double  fySmjz; //������ֵ
	private Double fyDshk;						//	���ջ���
	private Double sl;    //˰��
	private String kpbz;  //��Ʊ��־ 
	private String sfhdBz;  //�Ƿ�ص���־ Y/N
	
	public HyTydHwmx() {
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

	public String getHwflDm() {
		return hwflDm;
	}

	public void setHwflDm(String hwflDm) {
		this.hwflDm = hwflDm;
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

	public Double getFySmjz() {
		return fySmjz;
	}

	public void setFySmjz(Double fySmjz) {
		this.fySmjz = fySmjz;
	}

	public Double getFyDshk() {
		return fyDshk;
	}

	public void setFyDshk(Double fyDshk) {
		this.fyDshk = fyDshk;
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