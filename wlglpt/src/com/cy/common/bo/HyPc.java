package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC is created by tools.
 * @author HJH
 */

public class HyPc  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // �ɳ��Ǽ����(SEQ_PC_DJXH)
	private String pcdh;                             // �ɳ�����
	private String clsxDm;                           // �������Դ���
	private String cyrClhmXh ;						 //�����Ǽ����
	private String cyrCzxm;                          // ������_��������
	private String cyrClhm;                          // ������_��������
	private String cyrGchm;                          // ������_�ҳ�����
	private String cyrSjxm;                          // ������_˾������
	private String cyrSjsfz;                         // ������_˾�����֤
	private String cyrSjsjhm;                        // ������_˾���ֻ�����
	private String cyrQtlxdh;                        // ������_������ϵ�绰
	private String cyrQtlxdh2;                        // ������_������ϵ�绰
	private Double yfHj;                             // �˷�_���˷�
	private Double yfYfyf;                           // �˷�_Ԥ���˷�
	private Double yfYj;                             // �˷�_Ѻ��
	private Double yfXxf;                            // �˷�_��Ϣ��
	private Double yfSjs;                            // �˷�_˾����
	private Double yfHdyf;                           // �˷�_�����˷�
	private Double yfHdf;                            // �˷�_�ص���
	private Double yfZjf;							 // �˷�_�н��
	private String yfjsfDm;                          // �˷ѽ��㷽����
	private String xxzjDjxh;                         // ��Ϣ�н�Ǽ����
	private String zrbmDm;                           // ת�벿�Ŵ���
	private String zrbmDjxh;                         // ת�벿�ŵǼ����
	private String bz;                               // ��ע
	private String pcrCzyDjxh;                       // �ɳ���
	private Date pcrq;                             // �ɳ�����
	private String pcJgbm;                           // �ɳ�����
	private String ssJgbm;                           // ��������
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private Date cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private Date xgrq;                             // �޸�����
	private String pcfsDm;                           // �ɳ���ʽ����
	private String ysfsDm;                           // ���䷽ʽ����
	private String zcfsDm;                           // װ����ʽ����
	private String spbz;
	private Date dzrq;								 // ��վ����

	public HyPc() {
	}

	//��ȡ�ɳ��Ǽ����(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//�����ɳ��Ǽ����(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//��ȡ�ɳ�����
	public String getPcdh() {
		return this.pcdh;
	}

	//�����ɳ�����
	public void setPcdh(String pcdh) {
		this.pcdh=pcdh;
	}

	//��ȡ�������Դ���
	public String getClsxDm() {
		return this.clsxDm;
	}

	//���ó������Դ���
	public void setClsxDm(String clsxDm) {
		this.clsxDm=clsxDm;
	}

	//��ȡ������_��������
	public String getCyrCzxm() {
		return this.cyrCzxm;
	}

	//���ó�����_��������
	public void setCyrCzxm(String cyrCzxm) {
		this.cyrCzxm=cyrCzxm;
	}

	//��ȡ������_��������
	public String getCyrClhm() {
		return this.cyrClhm;
	}

	//���ó�����_��������
	public void setCyrClhm(String cyrClhm) {
		this.cyrClhm=cyrClhm;
	}

	//��ȡ������_�ҳ�����
	public String getCyrGchm() {
		return this.cyrGchm;
	}

	//���ó�����_�ҳ�����
	public void setCyrGchm(String cyrGchm) {
		this.cyrGchm=cyrGchm;
	}

	//��ȡ������_˾������
	public String getCyrSjxm() {
		return this.cyrSjxm;
	}

	//���ó�����_˾������
	public void setCyrSjxm(String cyrSjxm) {
		this.cyrSjxm=cyrSjxm;
	}

	//��ȡ������_˾�����֤
	public String getCyrSjsfz() {
		return this.cyrSjsfz;
	}

	//���ó�����_˾�����֤
	public void setCyrSjsfz(String cyrSjsfz) {
		this.cyrSjsfz=cyrSjsfz;
	}

	//��ȡ������_˾���ֻ�����
	public String getCyrSjsjhm() {
		return this.cyrSjsjhm;
	}

	//���ó�����_˾���ֻ�����
	public void setCyrSjsjhm(String cyrSjsjhm) {
		this.cyrSjsjhm=cyrSjsjhm;
	}

	//��ȡ������_������ϵ�绰
	public String getCyrQtlxdh() {
		return this.cyrQtlxdh;
	}

	//���ó�����_������ϵ�绰
	public void setCyrQtlxdh(String cyrQtlxdh) {
		this.cyrQtlxdh=cyrQtlxdh;
	}

	//��ȡ�˷�_���˷�
	public Double getYfHj() {
		return this.yfHj;
	}

	//�����˷�_���˷�
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	//��ȡ�˷�_Ԥ���˷�
	public Double getYfYfyf() {
		return this.yfYfyf;
	}

	//�����˷�_Ԥ���˷�
	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf=yfYfyf;
	}

	//��ȡ�˷�_Ѻ��
	public Double getYfYj() {
		return this.yfYj;
	}

	//�����˷�_Ѻ��
	public void setYfYj(Double yfYj) {
		this.yfYj=yfYj;
	}

	//��ȡ�˷�_��Ϣ��
	public Double getYfXxf() {
		return this.yfXxf;
	}

	//�����˷�_��Ϣ��
	public void setYfXxf(Double yfXxf) {
		this.yfXxf=yfXxf;
	}

	//��ȡ�˷�_˾����
	public Double getYfSjs() {
		return this.yfSjs;
	}

	//�����˷�_˾����
	public void setYfSjs(Double yfSjs) {
		this.yfSjs=yfSjs;
	}

	//��ȡ�˷�_�����˷�
	public Double getYfHdyf() {
		return this.yfHdyf;
	}

	//�����˷�_�����˷�
	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf=yfHdyf;
	}

	//��ȡ�˷�_�ص���
	public Double getYfHdf() {
		return this.yfHdf;
	}

	//�����˷�_�ص���
	public void setYfHdf(Double yfHdf) {
		this.yfHdf=yfHdf;
	}

	public Double getYfZjf() {
		return yfZjf;
	}

	public void setYfZjf(Double yfZjf) {
		this.yfZjf = yfZjf;
	}

	//��ȡ�˷ѽ��㷽����
	public String getYfjsfDm() {
		return this.yfjsfDm;
	}

	//�����˷ѽ��㷽����
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm=yfjsfDm;
	}

	//��ȡ��Ϣ�н�Ǽ����
	public String getXxzjDjxh() {
		return this.xxzjDjxh;
	}

	//������Ϣ�н�Ǽ����
	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh=xxzjDjxh;
	}

	//��ȡת�벿�Ŵ���
	public String getZrbmDm() {
		return this.zrbmDm;
	}

	//����ת�벿�Ŵ���
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm=zrbmDm;
	}

	//��ȡת�벿�ŵǼ����
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//����ת�벿�ŵǼ����
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//��ȡ��ע
	public String getBz() {
		return this.bz;
	}

	//���ñ�ע
	public void setBz(String bz) {
		this.bz=bz;
	}

	//��ȡ�ɳ���
	public String getPcrCzyDjxh() {
		return this.pcrCzyDjxh;
	}

	//�����ɳ���
	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh=pcrCzyDjxh;
	}

	//��ȡ�ɳ�����
	public Date getPcrq() {
		return this.pcrq;
	}

	//�����ɳ�����
	public void setPcrq(Date pcrq) {
		this.pcrq=pcrq;
	}

	//��ȡ�ɳ�����
	public String getPcJgbm() {
		return this.pcJgbm;
	}

	//�����ɳ�����
	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm=pcJgbm;
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

	//��ȡ�ɳ���ʽ����
	public String getPcfsDm() {
		return this.pcfsDm;
	}

	//�����ɳ���ʽ����
	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm=pcfsDm;
	}

	//��ȡ���䷽ʽ����
	public String getYsfsDm() {
		return this.ysfsDm;
	}

	//�������䷽ʽ����
	public void setYsfsDm(String ysfsDm) {
		this.ysfsDm=ysfsDm;
	}

	//��ȡװ����ʽ����
	public String getZcfsDm() {
		return this.zcfsDm;
	}

	//����װ����ʽ����
	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm=zcfsDm;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getCyrClhmXh() {
		return cyrClhmXh;
	}

	public void setCyrClhmXh(String cyrClhmXh) {
		this.cyrClhmXh = cyrClhmXh;
	}

	public String getCyrQtlxdh2() {
		return cyrQtlxdh2;
	}

	public void setCyrQtlxdh2(String cyrQtlxdh2) {
		this.cyrQtlxdh2 = cyrQtlxdh2;
	}

	public Date getDzrq() {
		return dzrq;
	}

	public void setDzrq(Date dzrq) {
		this.dzrq = dzrq;
	}

	
}