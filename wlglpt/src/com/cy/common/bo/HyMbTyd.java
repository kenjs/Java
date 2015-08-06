package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_THD is created by tools.
 * @author HJH
 */

public class HyMbTyd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String mbDjxh;                           // ģ��Ǽ����(SEQ_MB_DJXH)
	private String ssJgbm;                           // ��������
	private String mbmc;                             // ģ������
	private String khDjxh;                           // �ͻ��Ǽ����
	private String khmc;                             // 
	private String ddflDm;                           // �����������
	private Date xdrq;                             // �µ�����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String djrCzyDjxh;                       // �Ǽ���
	private Date djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
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
	private String bz;                               // ��ע

	private Long ddDjxh;                           // �����Ǽ����(SEQ_DD_DJXH)

	public HyMbTyd() {
	}

	//��ȡ�����Ǽ����(SEQ_DD_DJXH)
	public Long getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����(SEQ_DD_DJXH)
	public void setDdDjxh(Long ddDjxh) {
		this.ddDjxh=ddDjxh;
	}


	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
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

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	public String getMbDjxh() {
		return mbDjxh;
	}

	public void setMbDjxh(String mbDjxh) {
		this.mbDjxh = mbDjxh;
	}

	public String getMbmc() {
		return mbmc;
	}

	public void setMbmc(String mbmc) {
		this.mbmc = mbmc;
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

	public String getDdflDm() {
		return ddflDm;
	}

	public void setDdflDm(String ddflDm) {
		this.ddflDm = ddflDm;
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

	public Date getDjrq() {
		return djrq;
	}

	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public Date getXgrq() {
		return xgrq;
	}

	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}

	
}