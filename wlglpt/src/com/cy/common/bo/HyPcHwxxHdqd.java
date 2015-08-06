package com.cy.common.bo;
import java.io.Serializable;
/**
 * The persistent class For HY_PC_HWXX_HDQD is created by tools.
 * @author HJH
 */

public class HyPcHwxxHdqd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdqdDjxh;                         // �ص��嵥�Ǽ����(SEQ_HD_DJXH)
	private String qdmc;                             // �嵥����
	private String jszt;                             // ����״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	private String fsGsbm;                           // ���͹�˾����
	private String jsGsbm;                           // ���չ�˾����
	private String bz;                               // ��ע
	private String ssJgbm;                           // ��������
	private String djJgbm;                           // �Ǽǲ���
	private String dbrCzyDjxh;                       // ����˲���Ա�Ǽ����
	private String dbrq;                             // �������
	private String yxbz;                             // ��Ч��־(Y/N)

	public HyPcHwxxHdqd() {
	}

	//��ȡ�ص��嵥�Ǽ����(SEQ_HD_DJXH)
	public String getHdqdDjxh() {
		return this.hdqdDjxh;
	}

	//���ûص��嵥�Ǽ����(SEQ_HD_DJXH)
	public void setHdqdDjxh(String hdqdDjxh) {
		this.hdqdDjxh=hdqdDjxh;
	}

	//��ȡ�嵥����
	public String getQdmc() {
		return this.qdmc;
	}

	//�����嵥����
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//��ȡ����״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	public String getJszt() {
		return this.jszt;
	}

	//���ý���״̬(0 ��ʼ��1 ���ͣ�2 ���գ�3 �˻�)
	public void setJszt(String jszt) {
		this.jszt=jszt;
	}

	//��ȡ���͹�˾����
	public String getFsGsbm() {
		return this.fsGsbm;
	}

	//���÷��͹�˾����
	public void setFsGsbm(String fsGsbm) {
		this.fsGsbm=fsGsbm;
	}

	//��ȡ���չ�˾����
	public String getJsGsbm() {
		return this.jsGsbm;
	}

	//���ý��չ�˾����
	public void setJsGsbm(String jsGsbm) {
		this.jsGsbm=jsGsbm;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ��������
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//������������
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//��ȡ�Ǽǲ���
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//���õǼǲ���
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//��ȡ����˲���Ա�Ǽ����
	public String getDbrCzyDjxh() {
		return this.dbrCzyDjxh;
	}

	//���ô���˲���Ա�Ǽ����
	public void setDbrCzyDjxh(String dbrCzyDjxh) {
		this.dbrCzyDjxh=dbrCzyDjxh;
	}



	public String getDbrq() {
		return dbrq;
	}

	public void setDbrq(String dbrq) {
		this.dbrq = dbrq;
	}

	//��ȡ��Ч��־(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//������Ч��־(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}
}