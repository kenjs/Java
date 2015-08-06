package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_HWQS is created by tools.
 * @author HJH
 */

public class HyPcHwqs  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hwqsDjxh;                         // ����ǩ�յǼ����(SEQ_HWQS_DJXH)
	private String pcDjxh;                           // �ɳ��Ǽ����
	private Date qsrq;                             // ǩ������
	private String qsrCzyDjxh;                       // ǩ����
	private String bz;                               // ��ע
	private String newDdDjxh;                        // �¶����Ǽ����
	private String djJgbm;                           // �ɳ�����
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����

	public HyPcHwqs() {
	}

	//��ȡ����ǩ�յǼ����(SEQ_HWQS_DJXH)
	public String getHwqsDjxh() {
		return this.hwqsDjxh;
	}

	//��������ǩ�յǼ����(SEQ_HWQS_DJXH)
	public void setHwqsDjxh(String hwqsDjxh) {
		this.hwqsDjxh=hwqsDjxh;
	}

	//��ȡ�ɳ��Ǽ����
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡǩ������
	public Date getQsrq() {
		return this.qsrq;
	}

	//����ǩ������
	public void setQsrq(Date qsrq) {
		this.qsrq=qsrq;
	}

	//��ȡǩ����
	public String getQsrCzyDjxh() {
		return this.qsrCzyDjxh;
	}

	//����ǩ����
	public void setQsrCzyDjxh(String qsrCzyDjxh) {
		this.qsrCzyDjxh=qsrCzyDjxh;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�¶����Ǽ����
	public String getNewDdDjxh() {
		return this.newDdDjxh;
	}

	//�����¶����Ǽ����
	public void setNewDdDjxh(String newDdDjxh) {
		this.newDdDjxh=newDdDjxh;
	}

	//��ȡ�ɳ�����
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//�����ɳ�����
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

	//��ȡ������
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//���ô�����
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//��ȡ��������
	public Date getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(Date cjrq) {
		this.cjrq=cjrq;
	}

	//��ȡ�޸���
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//�����޸���
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//��ȡ�޸�����
	public Date getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
	}
}