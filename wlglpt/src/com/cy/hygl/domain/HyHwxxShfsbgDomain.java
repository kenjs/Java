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
	private String shbgDjxh;                         // 送货变更-登记序号
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double srHj;                             // 收入_小计
	private Double srYj;                             // 收入_月结
	private Double srXf;                             // 收入_现付
	private Double srHdf;                            // 收入_货到付
	private Double srThf;                            // 收入_提货付
	private Double srHf;                             // 收入_回单付
	private Double srHk;                             // 收入_回扣
	private Double srBjf;                            // 收入_保价费
	private Double srPsf;                            // 收入_配送费
	private Double srYsf;                            // 收入_运输费
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期

	private String cjrMc;                            // 创建人名称
	
	
	private String shBz;//送货标志 1、自提 2、送货
	/*******子表********/
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private Double bspsf;                            // 补收配送费
	private String ssJgbm;                           // 所属机构
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String xgrMc;                            // 修改人名称
	private String bz;

	
	private HyPcxxglDomain pcxxDomain;
	private HyPcHwxxDomain pchwDomain;
	private HyTydHwmxDomain ddhwDomain;
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<HyPcxxglDomain> pcList; 		 //查询列表
	
	public HyHwxxShfsbgDomain() {
	}

	//获取送货变更-登记序号
	public String getShbgDjxh() {
		return this.shbgDjxh;
	}

	//设置送货变更-登记序号
	public void setShbgDjxh(String shbgDjxh) {
		this.shbgDjxh=shbgDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号(货物明细序号)
	public String getXh() {
		return this.xh;
	}

	//设置序号(货物明细序号)
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取收入_小计
	public Double getSrHj() {
		return this.srHj;
	}

	//设置收入_小计
	public void setSrHj(Double srHj) {
		this.srHj=srHj;
	}

	//获取收入_月结
	public Double getSrYj() {
		return this.srYj;
	}

	//设置收入_月结
	public void setSrYj(Double srYj) {
		this.srYj=srYj;
	}

	//获取收入_现付
	public Double getSrXf() {
		return this.srXf;
	}

	//设置收入_现付
	public void setSrXf(Double srXf) {
		this.srXf=srXf;
	}

	//获取收入_货到付
	public Double getSrHdf() {
		return this.srHdf;
	}

	//设置收入_货到付
	public void setSrHdf(Double srHdf) {
		this.srHdf=srHdf;
	}

	//获取收入_提货付
	public Double getSrThf() {
		return this.srThf;
	}

	//设置收入_提货付
	public void setSrThf(Double srThf) {
		this.srThf=srThf;
	}

	//获取收入_回单付
	public Double getSrHf() {
		return this.srHf;
	}

	//设置收入_回单付
	public void setSrHf(Double srHf) {
		this.srHf=srHf;
	}

	//获取收入_回扣
	public Double getSrHk() {
		return this.srHk;
	}

	//设置收入_回扣
	public void setSrHk(Double srHk) {
		this.srHk=srHk;
	}

	//获取收入_保价费
	public Double getSrBjf() {
		return this.srBjf;
	}

	//设置收入_保价费
	public void setSrBjf(Double srBjf) {
		this.srBjf=srBjf;
	}

	//获取收入_配送费
	public Double getSrPsf() {
		return this.srPsf;
	}

	//设置收入_配送费
	public void setSrPsf(Double srPsf) {
		this.srPsf=srPsf;
	}

	//获取收入_运输费
	public Double getSrYsf() {
		return this.srYsf;
	}

	//设置收入_运输费
	public void setSrYsf(Double srYsf) {
		this.srYsf=srYsf;
	}

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//获取创建日期
	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	//设置创建日期
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
