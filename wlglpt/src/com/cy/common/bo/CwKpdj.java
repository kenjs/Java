package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_KPDJ is created by tools.
 * @author HJH
 */

public class CwKpdj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpDjxh;                           // ��Ʊ�Ǽ����(SEQ_KP_DJXH)
	private String kpsqDjxh;                         // ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	private String khDjxh;                           // �ͻ��Ǽ����
	private String fpdm;                             // ��ע
	private String fphm;                             // ��Ҫ������־(Y/N)
	private String kprCzyDjxh;                       // 
	private String kprq;                             // ��Ʊ��ʽ����
	private Double kpje;                             // �ѿ���Ʊ����
	private Double sl;                               // 
	private Double se;                               // ˰��
	private String zfbz;                             // 
	private String hzbz;                             // 
	private String lzFpdm;                           // 
	private String lzFphm;                           // 
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public CwKpdj() {
	}

	//��ȡ��Ʊ�Ǽ����(SEQ_KP_DJXH)
	public String getKpDjxh() {
		return this.kpDjxh;
	}

	//���ÿ�Ʊ�Ǽ����(SEQ_KP_DJXH)
	public void setKpDjxh(String kpDjxh) {
		this.kpDjxh=kpDjxh;
	}

	//��ȡ��Ʊ����Ǽ����(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//���ÿ�Ʊ����Ǽ����(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//��ȡ�ͻ��Ǽ����
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//���ÿͻ��Ǽ����
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//��ȡ��ע
	public String getFpdm() {
		return this.fpdm;
	}

	//���ñ�ע
	public void setFpdm(String fpdm) {
		this.fpdm=fpdm;
	}

	//��ȡ��Ҫ������־(Y/N)
	public String getFphm() {
		return this.fphm;
	}

	//������Ҫ������־(Y/N)
	public void setFphm(String fphm) {
		this.fphm=fphm;
	}

	//��ȡ
	public String getKprCzyDjxh() {
		return this.kprCzyDjxh;
	}

	//����
	public void setKprCzyDjxh(String kprCzyDjxh) {
		this.kprCzyDjxh=kprCzyDjxh;
	}

	//��ȡ��Ʊ��ʽ����
	public String getKprq() {
		return this.kprq;
	}

	//���ÿ�Ʊ��ʽ����
	public void setKprq(String kprq) {
		this.kprq=kprq;
	}

	//��ȡ�ѿ���Ʊ����
	public Double getKpje() {
		return this.kpje;
	}

	//�����ѿ���Ʊ����
	public void setKpje(Double kpje) {
		this.kpje=kpje;
	}

	//��ȡ
	public Double getSl() {
		return this.sl;
	}

	//����
	public void setSl(Double sl) {
		this.sl=sl;
	}

	//��ȡ˰��
	public Double getSe() {
		return this.se;
	}

	//����˰��
	public void setSe(Double se) {
		this.se=se;
	}

	//��ȡ
	public String getZfbz() {
		return this.zfbz;
	}

	//����
	public void setZfbz(String zfbz) {
		this.zfbz=zfbz;
	}

	//��ȡ
	public String getHzbz() {
		return this.hzbz;
	}

	//����
	public void setHzbz(String hzbz) {
		this.hzbz=hzbz;
	}

	//��ȡ
	public String getLzFpdm() {
		return this.lzFpdm;
	}

	//����
	public void setLzFpdm(String lzFpdm) {
		this.lzFpdm=lzFpdm;
	}

	//��ȡ
	public String getLzFphm() {
		return this.lzFphm;
	}

	//����
	public void setLzFphm(String lzFphm) {
		this.lzFphm=lzFphm;
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

	//��ȡ��������
	public String getCjrq() {
		return this.cjrq;
	}

	//���ô�������
	public void setCjrq(String cjrq) {
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
	public String getXgrq() {
		return this.xgrq;
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}