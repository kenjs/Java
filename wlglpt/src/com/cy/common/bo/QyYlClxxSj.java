package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_YL_CLXX_SJ is created by tools.
 * @author HJH
 */

public class QyYlClxxSj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String clDjxh;                           // �����Ǽ����(SEQ_ZY_DJXH)
	private String xh;                               // 
	private String sjXm;                             // ˾������
	private String sjZjhm;                           // ˾�����֤����
	private String sjSjhm;                           // ˾���ֻ�����
	private String sjLxdh;                           // ˾��������ϵ�绰
	private String jszhm;                            // ˾����ʻ֤����
	private String whbz;                             // ά����־(H�ֹ�ά����Z�Զ�ά��)
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)

	public QyYlClxxSj() {
	}

	//��ȡ�����Ǽ����(SEQ_ZY_DJXH)
	public String getClDjxh() {
		return this.clDjxh;
	}

	//���ó����Ǽ����(SEQ_ZY_DJXH)
	public void setClDjxh(String clDjxh) {
		this.clDjxh=clDjxh;
	}

	//��ȡ
	public String getXh() {
		return this.xh;
	}

	//����
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ˾������
	public String getSjXm() {
		return this.sjXm;
	}

	//����˾������
	public void setSjXm(String sjXm) {
		this.sjXm=sjXm;
	}

	//��ȡ˾�����֤����
	public String getSjZjhm() {
		return this.sjZjhm;
	}

	//����˾�����֤����
	public void setSjZjhm(String sjZjhm) {
		this.sjZjhm=sjZjhm;
	}

	//��ȡ˾���ֻ�����
	public String getSjSjhm() {
		return this.sjSjhm;
	}

	//����˾���ֻ�����
	public void setSjSjhm(String sjSjhm) {
		this.sjSjhm=sjSjhm;
	}

	//��ȡ˾��������ϵ�绰
	public String getSjLxdh() {
		return this.sjLxdh;
	}

	//����˾��������ϵ�绰
	public void setSjLxdh(String sjLxdh) {
		this.sjLxdh=sjLxdh;
	}

	//��ȡ˾����ʻ֤����
	public String getJszhm() {
		return this.jszhm;
	}

	//����˾����ʻ֤����
	public void setJszhm(String jszhm) {
		this.jszhm=jszhm;
	}

	//��ȡά����־(H�ֹ�ά����Z�Զ�ά��)
	public String getWhbz() {
		return this.whbz;
	}

	//����ά����־(H�ֹ�ά����Z�Զ�ά��)
	public void setWhbz(String whbz) {
		this.whbz=whbz;
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
}