package com.cy.zyegl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_PC_HWXX_XYDJ is created by tools.
 * @author HJH
 */

public class HyPcHwxxXydjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
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
	private String bz;                               // ��ע
	private String hdbh;
	
	private String hwmc;
	private String shrXzqhMc;
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	
	private String editFlag;						//���޸ı�־

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyPcHwxxXydjDomain() {
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ��������(δ�����Ǽ����)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�����ɳ��������(δ�����Ǽ����)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ���(������ϸ���)
	public String getXh() {
		return this.xh;
	}

	//�������(������ϸ���)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ�ջ���_�Ǽ����
	public String getShrDjxh() {
		return this.shrDjxh;
	}

	//�����ջ���_�Ǽ����
	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh=shrDjxh;
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

	//��ȡʵװ_����_��װ_������λ
	public String getSzHwBzHldwDm() {
		return this.szHwBzHldwDm;
	}

	//����ʵװ_����_��װ_������λ
	public void setSzHwBzHldwDm(String szHwBzHldwDm) {
		this.szHwBzHldwDm=szHwBzHldwDm;
	}

	//��ȡʵװ_����_����
	public Double getSzHwSl() {
		return this.szHwSl;
	}

	//����ʵװ_����_����
	public void setSzHwSl(Double szHwSl) {
		this.szHwSl=szHwSl;
	}

	//��ȡʵװ_����_����
	public Double getSzHwZl() {
		return this.szHwZl;
	}

	//����ʵװ_����_����
	public void setSzHwZl(Double szHwZl) {
		this.szHwZl=szHwZl;
	}

	//��ȡʵװ_����_���
	public Double getSzHwTj() {
		return this.szHwTj;
	}

	//����ʵװ_����_���
	public void setSzHwTj(Double szHwTj) {
		this.szHwTj=szHwTj;
	}

	//��ȡҪ�󵽴�����
	public Date getYqDdrq() {
		return this.yqDdrq;
	}

	//����Ҫ�󵽴�����
	public void setYqDdrq(Date yqDdrq) {
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

	//��ȡʵװ_��������
	public Double getSzJsSl() {
		return this.szJsSl;
	}

	//����ʵװ_��������
	public void setSzJsSl(Double szJsSl) {
		this.szJsSl=szJsSl;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
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

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
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
