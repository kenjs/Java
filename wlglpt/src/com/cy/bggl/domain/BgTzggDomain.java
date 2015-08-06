package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_TZGG is created by tools.
 * @author HJH
 */

public class BgTzggDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // ֪ͨ�������(SEQ_TZGG_XH)
	private String jgbm;                             // ��������
	private String jgmc;                             // ����
	private String fbrq;                             // ��������(YYYY-MM-DD)
	private String bcztDm;                           // ����״̬����
	private String zt;                               // ����
	private String xjgxbz;                           // �¼������־(Y/N)
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private List<BgTzggDomain> fjList; 		 //�����б�
	
	//����	
	List<byte[]> uploadValueList;
	
	List<String> uploadNameList;
	
	private String nr;//����
	private String rqQ;//ҳ�����������
	private String rqZ;//ҳ���������ֹ 
	
	private String saveBz;//�����־1Ϊ�ݴ棬2Ϊ����
	private String fjmc;//��������
	private String xh;//�������
	private byte[] fjnr;//��������

	public BgTzggDomain() {
	}

	//��ȡ֪ͨ�������(SEQ_TZGG_XH)
	public String getTzggXh() {
		return this.tzggXh;
	}

	//����֪ͨ�������(SEQ_TZGG_XH)
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//��ȡ��������
	public String getJgbm() {
		return this.jgbm;
	}

	//���û�������
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//��ȡ��������(YYYY-MM-DD)
	public String getFbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.fbrq);
		}
		catch(Exception e){
			return this.fbrq;
		}
	}

	//���÷�������(YYYY-MM-DD)
	public void setFbrq(String fbrq) {
		this.fbrq=fbrq;
	}

	//��ȡ����״̬����
	public String getBcztDm() {
		return this.bcztDm;
	}

	//���ñ���״̬����
	public void setBcztDm(String bcztDm) {
		this.bcztDm=bcztDm;
	}

	//��ȡ����
	public String getZt() {
		return this.zt;
	}

	//��������
	public void setZt(String zt) {
		this.zt=zt;
	}

	//��ȡ����
	public String getNr() {
		return this.nr;
	}

	//��������
	public void setNr(String nr) {
		this.nr=nr;
	}

	//��ȡ�¼������־(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//�����¼������־(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
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

	public String getRqQ() {
		return rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}


	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public List<BgTzggDomain> getFjList() {
		if(fjList==null){
			fjList=new ArrayList<BgTzggDomain>();
		}
		return fjList;
	}

	public String getFjmc() {
		return fjmc;
	}

	public byte[] getFjnr() {
		return fjnr;
	}

	public String getSaveBz() {
		return saveBz;
	}

	public String getXh() {
		return xh;
	}

	public void setFjList(List<BgTzggDomain> fjList) {
		this.fjList = fjList;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public void setFjnr(byte[] fjnr) {
		this.fjnr = fjnr;
	}

	public void setSaveBz(String saveBz) {
		this.saveBz = saveBz;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public List<String> getUploadNameList() {
		if(uploadValueList == null){
			uploadValueList = new ArrayList<byte[]>();
		}
		return uploadNameList;
	}

	public void setUploadNameList(List<String> uploadNameList) {
		this.uploadNameList = uploadNameList;
	}

	public List<byte[]> getUploadValueList() {
		if(uploadNameList == null){
			uploadNameList = new ArrayList<String>();
		}
		return uploadValueList;
	}

	public void setUploadValueList(List<byte[]> uploadValueList) {
		this.uploadValueList = uploadValueList;
	}

}
