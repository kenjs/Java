package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_THD is created by tools.
 * @author HJH
 */

public class HyTyd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private Long ddDjxh;                           // �����Ǽ����(SEQ_DD_DJXH)
	private String ddbh;                             // �������
	private String khDjxh;                           // �ͻ��Ǽ����
	private String khmc;                             // 
	private Date xdrq;									//�µ�����
	private String ddflDm;                           // �����������
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)
	private String djrCzyDjxh;                       // �Ǽ���
	private Date djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����
	private Double srHj;                             // ����_�ϼ�
	private Double srYj;                             // ����_�½�
	private Double srXf;                             // ����_�ָ�
	private Double srHdf;                            // ����_������
	private Double srThf;                            // ����_�����
	private Double srHf;                             // ����_�ص���
	private Double srHk;                             // ����_�ؿ�
	private String dzztDm;                           // ����״̬����
	private String hwztDm;                           // ����״̬����
	
	public HyTyd() {
	}

	public Long getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(Long ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getKhDjxh() {
		return khDjxh;
	}

	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public String getDdflDm() {
		return ddflDm;
	}

	public void setDdflDm(String ddflDm) {
		this.ddflDm = ddflDm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public Date getDjrq() {
		return djrq;
	}

	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}

	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}

	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}

	public Date getXgrq() {
		return xgrq;
	}

	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
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

	public String getDzztDm() {
		return dzztDm;
	}

	public void setDzztDm(String dzztDm) {
		this.dzztDm = dzztDm;
	}

	public String getHwztDm() {
		return hwztDm;
	}

	public void setHwztDm(String hwztDm) {
		this.hwztDm = hwztDm;
	}

	
}