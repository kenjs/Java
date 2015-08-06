package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For CW_ZZJXB is created by tools.
 * @author HJH
 */

public class CwZzjxb  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zjdbDjxh;                         // �ʽ�����Ǽ����(SEQ_CW_DJXH)
	private String rq;                               // ����
	private String jsDwDjxh;                         // ���յ�λ
	private Double jcHj;                             // ���_�ϼ�
	private Double jcXj;                             // ���_�ֽ�
	private Double jcYk;                             // ���_�Ϳ�
	private Double jcCk;                             // ���_���
	private Double zfHj;                             // ֧��_�ϼ�
	private Double zfYfk;                            // ֧��_Ԥ����
	private Double zfYk;                             // ֧��_���
	private Double zfBxfy;                           // ֧��_��������
	private Double zfQt;                             // ֧��_���
	private Double byj;                              // ���ý�
	private Double zjxq;                             // �ʽ�����
	private String xbDwDjxh;                         // �²���λ
	private Double xbje;                             // �²����
	private String bz;                               // ��ע
	private String yxbz;                             // ��Ч��־(Y/N)
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	public CwZzjxb() {
	}

	//��ȡ�ʽ�����Ǽ����(SEQ_CW_DJXH)
	public String getZjdbDjxh() {
		return this.zjdbDjxh;
	}

	//�����ʽ�����Ǽ����(SEQ_CW_DJXH)
	public void setZjdbDjxh(String zjdbDjxh) {
		this.zjdbDjxh=zjdbDjxh;
	}

	//��ȡ����
	public String getRq() {
		return this.rq;
	}

	//��������
	public void setRq(String rq) {
		this.rq=rq;
	}

	//��ȡ���յ�λ
	public String getJsDwDjxh() {
		return this.jsDwDjxh;
	}

	//���ý��յ�λ
	public void setJsDwDjxh(String jsDwDjxh) {
		this.jsDwDjxh=jsDwDjxh;
	}

	//��ȡ���_�ϼ�
	public Double getJcHj() {
		return this.jcHj;
	}

	//���ý��_�ϼ�
	public void setJcHj(Double jcHj) {
		this.jcHj=jcHj;
	}

	//��ȡ���_�ֽ�
	public Double getJcXj() {
		return this.jcXj;
	}

	//���ý��_�ֽ�
	public void setJcXj(Double jcXj) {
		this.jcXj=jcXj;
	}

	//��ȡ���_�Ϳ�
	public Double getJcYk() {
		return this.jcYk;
	}

	//���ý��_�Ϳ�
	public void setJcYk(Double jcYk) {
		this.jcYk=jcYk;
	}

	//��ȡ���_���
	public Double getJcCk() {
		return this.jcCk;
	}

	//���ý��_���
	public void setJcCk(Double jcCk) {
		this.jcCk=jcCk;
	}

	//��ȡ֧��_�ϼ�
	public Double getZfHj() {
		return this.zfHj;
	}

	//����֧��_�ϼ�
	public void setZfHj(Double zfHj) {
		this.zfHj=zfHj;
	}

	//��ȡ֧��_Ԥ����
	public Double getZfYfk() {
		return this.zfYfk;
	}

	//����֧��_Ԥ����
	public void setZfYfk(Double zfYfk) {
		this.zfYfk=zfYfk;
	}

	//��ȡ֧��_���
	public Double getZfYk() {
		return this.zfYk;
	}

	//����֧��_���
	public void setZfYk(Double zfYk) {
		this.zfYk=zfYk;
	}

	//��ȡ֧��_��������
	public Double getZfBxfy() {
		return this.zfBxfy;
	}

	//����֧��_��������
	public void setZfBxfy(Double zfBxfy) {
		this.zfBxfy=zfBxfy;
	}

	//��ȡ���ý�
	public Double getByj() {
		return this.byj;
	}

	//���ñ��ý�
	public void setByj(Double byj) {
		this.byj=byj;
	}

	//��ȡ�ʽ�����
	public Double getZjxq() {
		return this.zjxq;
	}

	//�����ʽ�����
	public void setZjxq(Double zjxq) {
		this.zjxq=zjxq;
	}

	//��ȡ�²���λ
	public String getXbDwDjxh() {
		return this.xbDwDjxh;
	}

	//�����²���λ
	public void setXbDwDjxh(String xbDwDjxh) {
		this.xbDwDjxh=xbDwDjxh;
	}

	//��ȡ�²����
	public Double getXbje() {
		return this.xbje;
	}

	//�����²����
	public void setXbje(Double xbje) {
		this.xbje=xbje;
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

	//��ȡ��Ҫ������־(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//������Ҫ������־(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//��ȡ��������״̬����
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//������������״̬����
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//��ȡ�����������
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//���������������
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
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

	public Double getZfQt() {
		return zfQt;
	}

	public void setZfQt(Double zfQt) {
		this.zfQt = zfQt;
	}
}