package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_ZZJG is created by tools.
 * @author HJH
 */

public class QyZzjgDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // ��������(SEQ_JG_DJXH)
	private String mc;                               // ����
	private String jc;                               // ���
	private String jglbDm;                           // ����������
	private String jcbm;                             // ���α���
	private String jbdm;                             // �������
	private String sjJgbm;                           // �ϼ���������
	private String qyZcxh;                           // ��ҵע�����(�ܹ�˾ʱ����Ϊ��)
	private String dz;                               // ��ַ
	private String dh;                               // �绰
	private String yb;                               // �ʱ�
	private String fzr;                              // ������
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String pyjx;                             // ƴ����д
	private String pyqp;                             // ƴ��ȫƴ
	private String qystr;							 //��ʾ���ñ�־
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String zt;                    			//״̬
	private String qybm;
	private String xzqhMc;
	private String jglbMc;
	private String sjMc;
	private String lbMc;
	private String fhrXzqhDm;
	private String	fhrXzqhMc;
	private String byj;
	
	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getLbMc() {
		return lbMc;
	}

	public void setLbMc(String lbMc) {
		this.lbMc = lbMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�

	public QyZzjgDomain() {
	}

	//��ȡ��������(SEQ_JG_DJXH)
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������(SEQ_JG_DJXH)
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ����
	public String getMc() {
		return this.mc;
	}

	//��������
	public void setMc(String mc) {
		this.mc=mc;
	}

	//��ȡ���
	public String getJc() {
		return this.jc;
	}

	//���ü��
	public void setJc(String jc) {
		this.jc=jc;
	}

	//��ȡ����������
	public String getJglbDm() {
		return this.jglbDm;
	}

	//���û���������
	public void setJglbDm(String jglbDm) {
		this.jglbDm=jglbDm;
	}

	//��ȡ���α���
	public String getJcbm() {
		return this.jcbm;
	}

	//���ü��α���
	public void setJcbm(String jcbm) {
		this.jcbm=jcbm;
	}

	//��ȡ�������
	public String getJbdm() {
		return this.jbdm;
	}

	//���ü������
	public void setJbdm(String jbdm) {
		this.jbdm=jbdm;
	}

	//��ȡ�ϼ���������
	public String getSjJgbm() {
		return this.sjJgbm;
	}

	//�����ϼ���������
	public void setSjJgbm(String sjJgbm) {
		this.sjJgbm=sjJgbm;
	}

	//��ȡ��ҵע�����(�ܹ�˾ʱ����Ϊ��)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//������ҵע�����(�ܹ�˾ʱ����Ϊ��)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//��ȡ��ַ
	public String getDz() {
		return this.dz;
	}

	//���õ�ַ
	public void setDz(String dz) {
		this.dz=dz;
	}

	//��ȡ�绰
	public String getDh() {
		return this.dh;
	}

	//���õ绰
	public void setDh(String dh) {
		this.dh=dh;
	}

	//��ȡ�ʱ�
	public String getYb() {
		return this.yb;
	}

	//�����ʱ�
	public void setYb(String yb) {
		this.yb=yb;
	}

	//��ȡ������
	public String getFzr() {
		return this.fzr;
	}

	//���ø�����
	public void setFzr(String fzr) {
		this.fzr=fzr;
	}

	//��ȡ���ñ�־(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//�������ñ�־(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
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
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//�����޸�����
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}

	//��ȡ
	public String getPyjx() {
		return this.pyjx;
	}

	//����
	public void setPyjx(String pyjx) {
		this.pyjx=pyjx;
	}

	//��ȡ
	public String getPyqp() {
		return this.pyqp;
	}

	//����
	public void setPyqp(String pyqp) {
		this.pyqp=pyqp;
	}

	public String getCjrMc() {
		return this.cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getXgrMc() {
		return this.xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getQystr() {
		if(qybz!=null)
		{
			if(qybz.equals("Y"))
			{
				qystr="����";
			}
			else {
				qystr="ͣ��";
			}
		}
		return qystr;
	}

	public void setQystr(String qystr) {
		this.qystr = qystr;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getQybm() {
		return qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}

	public String getXzqhMc() {
		return xzqhMc;
	}

	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc = xzqhMc;
	}

	public String getJglbMc() {
		return jglbMc;
	}

	public void setJglbMc(String jglbMc) {
		this.jglbMc = jglbMc;
	}

	public String getByj() {
		return byj;
	}

	public void setByj(String byj) {
		this.byj = byj;
	}

	
}
