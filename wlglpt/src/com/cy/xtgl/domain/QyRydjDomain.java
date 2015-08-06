package com.cy.xtgl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_RYDJ is created by tools.
 * @author yu huan
 * Date 2013-1-9
 */

public class QyRydjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;                          // ����Ա�Ǽ����(SEQ_CZY_DJXH)
	private String mc;                               // ����
	private String qyZcxh;                           // ��ҵע�����(GL_QYZC.QY_ZCXH)
	private String qybm;                             // ��ҵ����(GL_QYZC.QYBM)
	private String zh;                               // �˺�
	private String pwd;                              // ����
	private String sjhm;                             // �ֻ�����
	private String sjdh;                             // �ֻ��̺�
	private String bgdh;                             // �칫�绰
	private String bgdhao;                           // �칫�̺�
	private String jtdh;                             // ��ͥ�绰
	private String qq;                               // QQ����
	private String msn;                              // MSN����
	private String email;                            // EMAIL��ַ
	private String xtglybz;                          // ϵͳ����Ա��־(Y/N)(��ΪY������ɾ��)
	private String rylbDm;                           // ��Ա������
	private String dlyzfsDm;                         // ��¼��֤��ʽ����
	private String qxJgbm;                           // Ȩ�޻�������(����Ȩ��)
	private String qybz;                             // ���ñ�־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String ssJgbm;                           // ��˾/����
	private String qystr;                            // ��ʾ���ñ�־
	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	private String jc;		     					 // ��˾���	 
	private String pwdd;                             // ȷ������	
	private String gsbm;                             // editҳ�������ڱ�������ѡ���Ĺ�˾ֵ
	private String gw;                               // editҳ�������ڱ�������ѡ���ĸ�λֵ
	private String gwDjxh;                           // ��λ����
	private String sjjgbm;                           // ������������(����)
	private String oldGwdjxh ;                        //�޸�ʱ���ڱ���ɵĸ�λ�Ǽ����	                             
	private String zjbz;                             // �����־(Y��Ҫ����/N��ְ����)	
	private List<BaseBusinessDomain> dataList; 		 // ��ѯ�б�	
	private String jbdm;                             // �����б�ҳ��ѡ��Ļ�������ȡ�ļ����������ڸñ�����
	private String jgbm;
	
	private String gwMc;
	
	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}

	public QyRydjDomain() {
	}

	//��ȡ����Ա�Ǽ����(SEQ_CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//���ò���Ա�Ǽ����(SEQ_CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//��ȡ����
	public String getMc() {
		return this.mc;
	}

	//��������
	public void setMc(String mc) {
		this.mc=mc;
	}

	//��ȡ��ҵע�����(GL_QYZC.QY_ZCXH)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//������ҵע�����(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//��ȡ��ҵ����(GL_QYZC.QYBM)
	public String getQybm() {
		return this.qybm;
	}

	//������ҵ����(GL_QYZC.QYBM)
	public void setQybm(String qybm) {
		this.qybm=qybm;
	}

	//��ȡ�˺�
	public String getZh() {
		return this.zh;
	}

	//�����˺�
	public void setZh(String zh) {
		this.zh=zh;
	}

	//��ȡ����
	public String getPwd() {
		return this.pwd;
	}

	//��������
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	//��ȡ�ֻ�����
	public String getSjhm() {
		return this.sjhm;
	}

	//�����ֻ�����
	public void setSjhm(String sjhm) {
		this.sjhm=sjhm;
	}

	//��ȡ�ֻ��̺�
	public String getSjdh() {
		return this.sjdh;
	}

	//�����ֻ��̺�
	public void setSjdh(String sjdh) {
		this.sjdh=sjdh;
	}

	//��ȡ�칫�绰
	public String getBgdh() {
		return this.bgdh;
	}

	//���ð칫�绰
	public void setBgdh(String bgdh) {
		this.bgdh=bgdh;
	}

	//��ȡ�칫�̺�
	public String getBgdhao() {
		return this.bgdhao;
	}

	//���ð칫�̺�
	public void setBgdhao(String bgdhao) {
		this.bgdhao=bgdhao;
	}

	//��ȡ��ͥ�绰
	public String getJtdh() {
		return this.jtdh;
	}

	//���ü�ͥ�绰
	public void setJtdh(String jtdh) {
		this.jtdh=jtdh;
	}

	//��ȡQQ����
	public String getQq() {
		return this.qq;
	}

	//����QQ����
	public void setQq(String qq) {
		this.qq=qq;
	}

	//��ȡMSN����
	public String getMsn() {
		return this.msn;
	}

	//����MSN����
	public void setMsn(String msn) {
		this.msn=msn;
	}

	//��ȡEMAIL��ַ
	public String getEmail() {
		return this.email;
	}

	//����EMAIL��ַ
	public void setEmail(String email) {
		this.email=email;
	}

	//��ȡϵͳ����Ա��־(Y/N)(��ΪY������ɾ��)
	public String getXtglybz() {
		return this.xtglybz;
	}

	//����ϵͳ����Ա��־(Y/N)(��ΪY������ɾ��)
	public void setXtglybz(String xtglybz) {
		this.xtglybz=xtglybz;
	}

	//��ȡ��Ա������
	public String getRylbDm() {
		return this.rylbDm;
	}

	//������Ա������
	public void setRylbDm(String rylbDm) {
		this.rylbDm=rylbDm;
	}

	//��ȡ��¼��֤��ʽ����
	public String getDlyzfsDm() {
		return this.dlyzfsDm;
	}

	//���õ�¼��֤��ʽ����
	public void setDlyzfsDm(String dlyzfsDm) {
		this.dlyzfsDm=dlyzfsDm;
	}

	//��ȡȨ�޻�������(����Ȩ��)
	public String getQxJgbm() {
		return this.qxJgbm;
	}

	//����Ȩ�޻�������(����Ȩ��)
	public void setQxJgbm(String qxJgbm) {
		this.qxJgbm=qxJgbm;
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

	//��ȡ��˾/����


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

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

 

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}

	public String getGwDjxh() {
		return gwDjxh;
	}

	public void setGwDjxh(String gwDjxh) {
		this.gwDjxh = gwDjxh;
	}

	

	public String getZjbz() {
		return zjbz;
	}

	public void setZjbz(String zjbz) {
		this.zjbz = zjbz;
	}

	public String getPwdd() {
		return pwdd;
	}

	public void setPwdd(String pwdd) {
		this.pwdd = pwdd;
	}

	public String getJbdm() {
		return jbdm;
	}

	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getSjjgbm() {
		return sjjgbm;
	}

	public void setSjjgbm(String sjjgbm) {
		this.sjjgbm = sjjgbm;
	}

	public String getOldGwdjxh() {
		return oldGwdjxh;
	}

	public void setOldGwdjxh(String oldGwdjxh) {
		this.oldGwdjxh = oldGwdjxh;
	}

	public String getGwMc() {
		return gwMc;
	}

	public void setGwMc(String gwMc) {
		this.gwMc = gwMc;
	}
}
