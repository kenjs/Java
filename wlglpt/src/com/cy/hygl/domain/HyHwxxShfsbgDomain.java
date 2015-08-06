package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_HWXX_SHFSBG is created by tools.
 * @author HJH
 */

public class HyHwxxShfsbgDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shbgDjxh;                         // �ͻ����-�Ǽ����
	private String ddDjxh;                           // �����Ǽ����
	private String xh;                               // ���(������ϸ���)
	private Double srHj;                             // ����_С��
	private Double srYj;                             // ����_�½�
	private Double srXf;                             // ����_�ָ�
	private Double srHdf;                            // ����_������
	private Double srThf;                            // ����_�����
	private Double srHf;                             // ����_�ص���
	private Double srHk;                             // ����_�ؿ�
	private Double srBjf;                            // ����_���۷�
	private Double srPsf;                            // ����_���ͷ�
	private Double srYsf;                            // ����_�����
	private String cjrCzyDjxh;                       // ������
	private String cjrq;                             // ��������

	private String cjrMc;                            // ����������
	
	
	private String shBz;//�ͻ���־ 1������ 2���ͻ�
	/*******�ӱ�********/
	private String pcDjxh;                           // �ɳ��Ǽ����
	private String wfhDjxh;                          // �ɳ��������(δ�����Ǽ����)
	private Double bspsf;                            // �������ͷ�
	private String ssJgbm;                           // ��������
	private String xgrCzyDjxh;                       // �޸���
	private String xgrq;                             // �޸�����
	private String xgrMc;                            // �޸�������
	private String bz;

	
	private HyPcxxglDomain pcxxDomain;
	private HyPcHwxxDomain pchwDomain;
	private HyTydHwmxDomain ddhwDomain;
	private List<BaseBusinessDomain> dataList; 		 //��ѯ�б�
	private List<HyPcxxglDomain> pcList; 		 //��ѯ�б�
	
	public HyHwxxShfsbgDomain() {
	}

	//��ȡ�ͻ����-�Ǽ����
	public String getShbgDjxh() {
		return this.shbgDjxh;
	}

	//�����ͻ����-�Ǽ����
	public void setShbgDjxh(String shbgDjxh) {
		this.shbgDjxh=shbgDjxh;
	}

	//��ȡ�����Ǽ����
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//���ö����Ǽ����
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//��ȡ���(������ϸ���)
	public String getXh() {
		return this.xh;
	}

	//�������(������ϸ���)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//��ȡ����_С��
	public Double getSrHj() {
		return this.srHj;
	}

	//��������_С��
	public void setSrHj(Double srHj) {
		this.srHj=srHj;
	}

	//��ȡ����_�½�
	public Double getSrYj() {
		return this.srYj;
	}

	//��������_�½�
	public void setSrYj(Double srYj) {
		this.srYj=srYj;
	}

	//��ȡ����_�ָ�
	public Double getSrXf() {
		return this.srXf;
	}

	//��������_�ָ�
	public void setSrXf(Double srXf) {
		this.srXf=srXf;
	}

	//��ȡ����_������
	public Double getSrHdf() {
		return this.srHdf;
	}

	//��������_������
	public void setSrHdf(Double srHdf) {
		this.srHdf=srHdf;
	}

	//��ȡ����_�����
	public Double getSrThf() {
		return this.srThf;
	}

	//��������_�����
	public void setSrThf(Double srThf) {
		this.srThf=srThf;
	}

	//��ȡ����_�ص���
	public Double getSrHf() {
		return this.srHf;
	}

	//��������_�ص���
	public void setSrHf(Double srHf) {
		this.srHf=srHf;
	}

	//��ȡ����_�ؿ�
	public Double getSrHk() {
		return this.srHk;
	}

	//��������_�ؿ�
	public void setSrHk(Double srHk) {
		this.srHk=srHk;
	}

	//��ȡ����_���۷�
	public Double getSrBjf() {
		return this.srBjf;
	}

	//��������_���۷�
	public void setSrBjf(Double srBjf) {
		this.srBjf=srBjf;
	}

	//��ȡ����_���ͷ�
	public Double getSrPsf() {
		return this.srPsf;
	}

	//��������_���ͷ�
	public void setSrPsf(Double srPsf) {
		this.srPsf=srPsf;
	}

	//��ȡ����_�����
	public Double getSrYsf() {
		return this.srYsf;
	}

	//��������_�����
	public void setSrYsf(Double srYsf) {
		this.srYsf=srYsf;
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

	public String getCjrMc() {
		return this.cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
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

	public List<HyPcxxglDomain> getPcList() {
		if(pcList==null){
			pcList=new ArrayList<HyPcxxglDomain>();
		}
		return pcList;
	}

	public void setPcList(List<HyPcxxglDomain> pcList) {
		this.pcList = pcList;
	}

	public HyPcxxglDomain getPcxxDomain() {
		if(pcxxDomain==null){
			pcxxDomain=new HyPcxxglDomain();
		}
		return pcxxDomain;
	}

	public void setPcxxDomain(HyPcxxglDomain pcxxDomain) {
		this.pcxxDomain = pcxxDomain;
	}
	public HyPcHwxxDomain getPchwDomain() {
		if(pchwDomain==null){
			pchwDomain=new HyPcHwxxDomain();
		}
		return pchwDomain;
	}

	public void setPchwDomain(HyPcHwxxDomain pchwDomain) {
		this.pchwDomain = pchwDomain;
	}

	public HyTydHwmxDomain getDdhwDomain() {
		if(ddhwDomain==null){
			ddhwDomain=new HyTydHwmxDomain();
		}
		return ddhwDomain;
	}

	public void setDdhwDomain(HyTydHwmxDomain ddhwDomain) {
		this.ddhwDomain = ddhwDomain;
	}

	public String getShBz() {
		return shBz;
	}

	public void setShBz(String shBz) {
		this.shBz = shBz;
	}

	public String getPcDjxh() {
		return pcDjxh;
	}

	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public Double getBspsf() {
		return bspsf;
	}

	public void setBspsf(Double bspsf) {
		this.bspsf = bspsf;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}

	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}

	public String getXgrq() {
		return xgrq;
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
