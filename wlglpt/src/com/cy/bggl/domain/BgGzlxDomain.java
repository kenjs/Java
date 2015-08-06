package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_GZLX is created by tools.
 * @author HJH
 */

public class BgGzlxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gzlxXh;                           // ������ϵ���(SEQ_GZLX_XH)
	private String zt;                               // ����
	private String nr;                               // ����
	private String bcbzDm;                           // �����־����
	private String yxbz;                             // ��Ч��־(Y/N)
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	

	private String cjrMc;                            // ����������
	private String xgrMc;                            // �޸�������
	
	private String ckbz;
	private String ckbzMc;
	private String ckrq;
	
	
	private String czyDjxh;//�����˵Ǽ����
	private String rqQ;//ҳ�����������
	private String rqZ;//ҳ���������ֹ
	
	private String jsrs;
	private String jsr;
	private String jsrMcs;
	private String jsrMc;
	private String xtyhflDm;
	private String xtyhflDms;
	
	

	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	
	private  List<BgGzlxDomain> fjList;
	
	private String fjmc;//��������
	private String xh;//�������
	private byte[] fjnr;//��������
	
	private String saveBz;
	
	//����	
	List<byte[]> uploadValueList;
	
	List<String> uploadNameList;

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

	public BgGzlxDomain() {
	}

	//��ȡ������ϵ���(SEQ_GZLX_XH)
	public String getGzlxXh() {
		return this.gzlxXh;
	}

	//���ù�����ϵ���(SEQ_GZLX_XH)
	public void setGzlxXh(String gzlxXh) {
		this.gzlxXh=gzlxXh;
	}

	//��ȡ����
	public String getZt() {
		return this.zt;
	}

	//��������
	public void setZt(String zt) {
		this.zt=zt;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	//��ȡ�����־����
	public String getBcbzDm() {
		return this.bcbzDm;
	}

	//���ñ����־����
	public void setBcbzDm(String bcbzDm) {
		this.bcbzDm=bcbzDm;
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

	//ȡ����ʱ���ǰ30��
	public String getRqQ() throws Exception{
		if(rqQ == null){
			rqQ = SysDateUtil.getIntervalDate(SysDateUtil.getCurrentDate(), Calendar.DATE, -30);
		}
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	//ȡ����ʱ��
	public String getRqZ() throws Exception {
		if(rqZ == null){
			rqZ = SysDateUtil.getCurrentDate();
		}
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getJsrs() {
		return jsrs;
	}

	public void setJsrs(String jsrs) {
		this.jsrs = jsrs;
	}

	public List<BgGzlxDomain> getFjList() {
		if(fjList == null){
			fjList = new ArrayList<BgGzlxDomain>();
		}
		return fjList;
	}

	public void setFjList(List<BgGzlxDomain> fjList) {
		this.fjList = fjList;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public byte[] getFjnr() {
		return fjnr;
	}

	public void setFjnr(byte[] fjnr) {
		this.fjnr = fjnr;
	}

	public String getCkbz() {
		return ckbz;
	}

	public void setCkbz(String ckbz) {
		this.ckbz = ckbz;
	}

	public String getCkbzMc() {
		if("Y".equals(ckbz)){
			ckbzMc = "�Ѳ鿴";
		}else{
			ckbzMc = "δ�鿴";
		}
		return ckbzMc;
	}

	public void setCkbzMc(String ckbzMc) {
		this.ckbzMc = ckbzMc;
	}

	public String getCkrq() {		
		try{
			return SysDateUtil.getYyyyMmdd(this.ckrq);
		}
		catch(Exception e){
			return this.ckrq;
		}

	}

	public void setCkrq(String ckrq) {
		this.ckrq = ckrq;
	}

	public String getCzyDjxh() {
		return czyDjxh;
	}

	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}

	public String getJsrMcs() {
		return jsrMcs;
	}

	public void setJsrMcs(String jsrMcs) {
		this.jsrMcs = jsrMcs;
	}


	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJsrMc() {
		return jsrMc;
	}

	public void setJsrMc(String jsrMc) {
		this.jsrMc = jsrMc;
	}

	public String getXtyhflDm() {
		return xtyhflDm;
	}

	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}

	public String getXtyhflDms() {
		return xtyhflDms;
	}

	public void setXtyhflDms(String xtyhflDms) {
		this.xtyhflDms = xtyhflDms;
	}

	public String getSaveBz() {
		return saveBz;
	}

	public void setSaveBz(String saveBz) {
		this.saveBz = saveBz;
	}

}
