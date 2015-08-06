package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PZ_HWXX is created by tools.
 * @author HJH
 */

public class HyPzHwxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // ���صǼ����
	private String wfhDjxh;                          // ���ػ������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double hwSl;                             // ����_����
	private Double hwZl;                             // ����_����
	private Double hwTj;                             // ����_���
	private Double sr;                               // ����
	private Double yjPsf;                            // Ԥ�����ͷ�
	private String bz;                         // ��ע
	private String hzJgbm;
	
	private String pchwLsxh;
	
	private String zrbmDm;
	private String zrbmDjxh;
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public HyPzHwxxDomain() {
	}

	//��ȡ���صǼ����
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//�������صǼ����
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//��ȡ���ػ������(δ�����Ǽ����)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//�������ػ������(δ�����Ǽ����)
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

	//��ȡ����_����
	public Double getHwSl() {
		return this.hwSl;
	}

	//���û���_����
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//��ȡ����_����
	public Double getHwZl() {
		return this.hwZl;
	}

	//���û���_����
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//��ȡ����_���
	public Double getHwTj() {
		return this.hwTj;
	}

	//���û���_���
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//��ȡ����
	public Double getSr() {
		return this.sr;
	}

	//��������
	public void setSr(Double sr) {
		this.sr=sr;
	}

	//��ȡԤ�����ͷ�
	public Double getYjPsf() {
		return this.yjPsf;
	}

	//����Ԥ�����ͷ�
	public void setYjPsf(Double yjPsf) {
		this.yjPsf=yjPsf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getHzJgbm() {
		return hzJgbm;
	}

	public void setHzJgbm(String hzJgbm) {
		this.hzJgbm = hzJgbm;
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
