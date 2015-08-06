package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC_XYDJ is created by tools.
 * @author HJH
 */

public class HyPcXydj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private String xyh;                              // Э���
	private Double yfHj;                             // �˷ѱ��_���˷�
	private Double yfYfyf;                           // �˷ѱ��_Ԥ���˷�
	private Double yfYj;                             // �˷ѱ��_Ѻ��
	private Double yfXxf;                            // �˷ѱ��_��Ϣ��
	private Double yfSjs;                            // �˷ѱ��_˾����
	private Double yfHdyf;                           // �˷ѱ��_�����˷�
	private Double yfHdf;                            // �˷ѱ��_�ص���
	private String bz;                               // ��ע
	private String ywyCzyDjxh;                       // ҵ��Ա
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����
	private String spbz;                             // ��Ҫ������־(Y/N)
	private String wsspztDm;                         // ��������״̬����
	private String wsSpxh;                           // �����������

	public HyPcXydj() {
	}

	//��ȡ�ɳ��Ǽ����(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡЭ���
	public String getXyh() {
		return this.xyh;
	}

	//����Э���
	public void setXyh(String xyh) {
		this.xyh=xyh;
	}

	//��ȡ�˷ѱ��_���˷�
	public Double getYfHj() {
		return this.yfHj;
	}

	//�����˷ѱ��_���˷�
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	//��ȡ�˷ѱ��_Ԥ���˷�
	public Double getYfYfyf() {
		return this.yfYfyf;
	}

	//�����˷ѱ��_Ԥ���˷�
	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf=yfYfyf;
	}

	//��ȡ�˷ѱ��_Ѻ��
	public Double getYfYj() {
		return this.yfYj;
	}

	//�����˷ѱ��_Ѻ��
	public void setYfYj(Double yfYj) {
		this.yfYj=yfYj;
	}

	//��ȡ�˷ѱ��_��Ϣ��
	public Double getYfXxf() {
		return this.yfXxf;
	}

	//�����˷ѱ��_��Ϣ��
	public void setYfXxf(Double yfXxf) {
		this.yfXxf=yfXxf;
	}

	//��ȡ�˷ѱ��_˾����
	public Double getYfSjs() {
		return this.yfSjs;
	}

	//�����˷ѱ��_˾����
	public void setYfSjs(Double yfSjs) {
		this.yfSjs=yfSjs;
	}

	//��ȡ�˷ѱ��_�����˷�
	public Double getYfHdyf() {
		return this.yfHdyf;
	}

	//�����˷ѱ��_�����˷�
	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf=yfHdyf;
	}

	//��ȡ�˷ѱ��_�ص���
	public Double getYfHdf() {
		return this.yfHdf;
	}

	//�����˷ѱ��_�ص���
	public void setYfHdf(Double yfHdf) {
		this.yfHdf=yfHdf;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡҵ��Ա
	public String getYwyCzyDjxh() {
		return this.ywyCzyDjxh;
	}

	//����ҵ��Ա
	public void setYwyCzyDjxh(String ywyCzyDjxh) {
		this.ywyCzyDjxh=ywyCzyDjxh;
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
}