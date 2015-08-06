package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_YL_CLXX is created by tools.
 * @author HJH
 */

public class QyYlClxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String clDjxh;                           // �����Ǽ����(SEQ_ZY_DJXH)
	private String czXm;                             // ����_����
	private String czZjlxDm;                         // ����_֤�����ʹ���
	private String czZjhm;                           // ����_֤������
	private String czLxdh;                           // ����_��ϵ�绰
	private String gcbz;                             // �ҳ���־��Y/N��
	private String clhm;                             // ��������
	private String clsxDm;                           // �������Դ���	
	private String thclbz;                           // ���������־(Y/N)
	private String ysclbz;                           // ���䳵����־(Y/N)
	private String psclbz;                           // ���ͳ�����ʶ(Y/N)
	private String clxhwhDjxh;                       // �����ͺ�ά�����
	private String bz;                               // ��ע
	private String djrCzyDjxh;                       // �Ǽ���
	private String djrq;                             // �Ǽ�����
	private String djJgbm;                           // �Ǽǲ���
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String xxgxfsDm;
	private String cz;
	private String tj;
	private String cd;
	private String kd;
	private String gd;
	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getKd() {
		return kd;
	}

	public void setKd(String kd) {
		this.kd = kd;
	}

	public String getGd() {
		return gd;
	}

	public void setGd(String gd) {
		this.gd = gd;
	}

	public QyYlClxx() {
	}

	//��ȡ�����Ǽ����(SEQ_ZY_DJXH)
	public String getClDjxh() {
		return this.clDjxh;
	}

	//���ó����Ǽ����(SEQ_ZY_DJXH)
	public void setClDjxh(String clDjxh) {
		this.clDjxh=clDjxh;
	}

	//��ȡ����_����
	public String getCzXm() {
		return this.czXm;
	}

	//���ó���_����
	public void setCzXm(String czXm) {
		this.czXm=czXm;
	}

	//��ȡ����_֤�����ʹ���
	public String getCzZjlxDm() {
		return this.czZjlxDm;
	}

	//���ó���_֤�����ʹ���
	public void setCzZjlxDm(String czZjlxDm) {
		this.czZjlxDm=czZjlxDm;
	}

	//��ȡ����_֤������
	public String getCzZjhm() {
		return this.czZjhm;
	}

	//���ó���_֤������
	public void setCzZjhm(String czZjhm) {
		this.czZjhm=czZjhm;
	}

	//��ȡ����_��ϵ�绰
	public String getCzLxdh() {
		return this.czLxdh;
	}

	//���ó���_��ϵ�绰
	public void setCzLxdh(String czLxdh) {
		this.czLxdh=czLxdh;
	}

	//��ȡ��������
	public String getClhm() {
		return this.clhm;
	}

	//���ó�������
	public void setClhm(String clhm) {
		this.clhm=clhm;
	}

	//��ȡ�������Դ���
	public String getClsxDm() {
		return this.clsxDm;
	}

	//���ó������Դ���
	public void setClsxDm(String clsxDm) {
		this.clsxDm=clsxDm;
	}

	//��ȡ���������־(Y/N)
	public String getThclbz() {
		return this.thclbz;
	}

	//�������������־(Y/N)
	public void setThclbz(String thclbz) {
		this.thclbz=thclbz;
	}

	//��ȡ���䳵����־(Y/N)
	public String getYsclbz() {
		return this.ysclbz;
	}

	//�������䳵����־(Y/N)
	public void setYsclbz(String ysclbz) {
		this.ysclbz=ysclbz;
	}

	//��ȡ���ͳ�����ʶ(Y/N)
	public String getPsclbz() {
		return this.psclbz;
	}

	//�������ͳ�����ʶ(Y/N)
	public void setPsclbz(String psclbz) {
		this.psclbz=psclbz;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�Ǽ���
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//���õǼ���
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//��ȡ�Ǽ�����
	public String getDjrq() {
		return this.djrq;
	}

	//���õǼ�����
	public void setDjrq(String djrq) {
		this.djrq=djrq;
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

	public String getXxgxfsDm() {
		return xxgxfsDm;
	}

	public void setXxgxfsDm(String xxgxfsDm) {
		this.xxgxfsDm = xxgxfsDm;
	}

	public String getGcbz() {
		return gcbz;
	}

	public void setGcbz(String gcbz) {
		this.gcbz = gcbz;
	}

	public String getClxhwhDjxh() {
		return clxhwhDjxh;
	}

	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh = clxhwhDjxh;
	}
}