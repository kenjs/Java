package com.cy.hygl.domain;

import java.util.ArrayList;
import java.util.List;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * 收入对账差异审核
 * @author HJH
 */

public class HyJsglSrdzcyshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private String xh;                           // 序号
	private String wsspxh;                           // 文书审批序号(SEQ_WS_SPXH)
	private String spxh;                           // 审批序号(从1开始递增)
	private String jdxh;//节点序号
	private Double fsthbz;                             // 发送退回标志(1 发送,2 退回)
	private String fsrmc;                             // 发送人名称
	private String fsrq;                         // 发送日期
	private String spjzsj;                           // 审批截止时间
	private String cqbz;                             // 超期标志(Y/N)
	private String sprmc;                           // 审批人名称
	private String sprq;                       // 审批日期
	private String spjg;                             // 审批结果 
	private String dzcyje;//对账差异金额
	private String sfd;                            // 始发地
	private String mdd;                            // 目的地
	private String hwmc;                            // 货物名称
	private String bz;                            // 包装
	private String sl;                            // 数量
	private String zl;                            // 重量
	private String tj;                            // 体积
	private String jssl;                            // 结算数量
	private String fhrmc;                            // 发货人名称
	private String fhrdz;                            // 发货人地址
	private String yqfhrq;                            // 要求发货日期
	private String shrmc;                            // 收货人_名称
	private String shrdz;                            // 收货人_地址
	private String yqddrq;                            // 要求到达日期
	private String ddbh;//订单编号
	private String ddDjxh;
	private String ssjgbm;                            // 所属机构
	private String ssjgmc;                            // 所属机构名称
	
	
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	
	private List<String> xhs;
	
	private String rqQ;                          
	private String rqZ;                        
	private String shbz;


	public HyJsglSrdzcyshDomain(){
		
	}


	public String getDzcyje() {
		return dzcyje;
	}

	public void setDzcyje(String dzcyje) {
		this.dzcyje = dzcyje;
	}

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCqbz() {
		return cqbz;
	}


	public void setCqbz(String cqbz) {
		this.cqbz = cqbz;
	}


	public List<BaseBusinessDomain> getDataList() {
		if(null==dataList)
			dataList=new ArrayList<BaseBusinessDomain>();
		return dataList;
	}


	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}


	public String getDdbh() {
		return ddbh;
	}


	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}


	public String getFhrdz() {
		return fhrdz;
	}


	public void setFhrdz(String fhrdz) {
		this.fhrdz = fhrdz;
	}


	public String getFhrmc() {
		return fhrmc;
	}


	public void setFhrmc(String fhrmc) {
		this.fhrmc = fhrmc;
	}


	public String getFsrmc() {
		return fsrmc;
	}


	public void setFsrmc(String fsrmc) {
		this.fsrmc = fsrmc;
	}


	public String getFsrq() {
		return fsrq;
	}


	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}


	public Double getFsthbz() {
		return fsthbz;
	}


	public void setFsthbz(Double fsthbz) {
		this.fsthbz = fsthbz;
	}


	public String getHwmc() {
		return hwmc;
	}


	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}


	public String getJdxh() {
		return jdxh;
	}


	public void setJdxh(String jdxh) {
		this.jdxh = jdxh;
	}


	public String getJssl() {
		return jssl;
	}


	public void setJssl(String jssl) {
		this.jssl = jssl;
	}


	public String getMdd() {
		return mdd;
	}


	public void setMdd(String mdd) {
		this.mdd = mdd;
	}


	public String getRqQ() {
		return rqQ;
	}


	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}


	public String getRqZ() {
		return rqZ;
	}


	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}


	public String getSfd() {
		return sfd;
	}


	public void setSfd(String sfd) {
		this.sfd = sfd;
	}


	public String getShrdz() {
		return shrdz;
	}


	public void setShrdz(String shrdz) {
		this.shrdz = shrdz;
	}


	public String getShrmc() {
		return shrmc;
	}


	public void setShrmc(String shrmc) {
		this.shrmc = shrmc;
	}


	public String getSl() {
		return sl;
	}


	public void setSl(String sl) {
		this.sl = sl;
	}


	public String getSpjg() {
		return spjg;
	}


	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}


	public String getSpjzsj() {
		return spjzsj;
	}


	public void setSpjzsj(String spjzsj) {
		this.spjzsj = spjzsj;
	}


	public String getSprmc() {
		return sprmc;
	}


	public void setSprmc(String sprmc) {
		this.sprmc = sprmc;
	}


	public String getSprq() {
		return sprq;
	}


	public void setSprq(String sprq) {
		this.sprq = sprq;
	}


	public String getSpxh() {
		return spxh;
	}


	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}


	public String getSsjgbm() {
		return ssjgbm;
	}


	public void setSsjgbm(String ssjgbm) {
		this.ssjgbm = ssjgbm;
	}


	public String getSsjgmc() {
		return ssjgmc;
	}


	public void setSsjgmc(String ssjgmc) {
		this.ssjgmc = ssjgmc;
	}


	public String getTj() {
		return tj;
	}


	public void setTj(String tj) {
		this.tj = tj;
	}


	public String getWsspxh() {
		return wsspxh;
	}


	public void setWsspxh(String wsspxh) {
		this.wsspxh = wsspxh;
	}


	public String getXh() {
		return xh;
	}


	public void setXh(String xh) {
		this.xh = xh;
	}


	public List<String> getXhs() {
		return xhs;
	}


	public void setXhs(List<String> xhs) {
		this.xhs = xhs;
	}


	public String getYqddrq() {
		return yqddrq;
	}


	public void setYqddrq(String yqddrq) {
		this.yqddrq = yqddrq;
	}


	public String getYqfhrq() {
		return yqfhrq;
	}


	public void setYqfhrq(String yqfhrq) {
		this.yqfhrq = yqfhrq;
	}


	public String getZl() {
		return zl;
	}


	public void setZl(String zl) {
		this.zl = zl;
	}


	public String getDdDjxh() {
		return ddDjxh;
	}


	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}


}
