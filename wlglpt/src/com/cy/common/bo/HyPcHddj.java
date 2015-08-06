package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_HD is created by tools.
 * @author HJH
 */

public class HyPcHddj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdDjxh;
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double szHwSl;                           // ʵװ_����_����
	private Double szHwZl;                           // ʵװ_����_����
	private Double szHwTj;                           // ʵװ_����_���
	private Date yqDdrq;                           // Ҫ�󵽴�����
	private String shfsDm;                           // �ջ���ʽ����
	private Double szJsSl;                           // ʵװ_��������
	private String hdbh;                             // �ص����(����Ѷ��ŷָ�)
	private Date hdjsrq;                           // �ص���������
	private String bz;                               // ��ע
	private String spbz;
	private String yxbz;
	private String ssJgbm;
	private String djrCzyDjxh;
	private String djrq;
	public HyPcHddj() {
	}

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
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

	public Date getHdjsrq() {
		return hdjsrq;
	}

	public void setHdjsrq(Date hdjsrq) {
		this.hdjsrq = hdjsrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

}