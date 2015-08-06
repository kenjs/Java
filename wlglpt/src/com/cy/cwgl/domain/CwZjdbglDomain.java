package com.cy.cwgl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_ZJDBGL is created by tools.
 * @author HJH
 */

public class CwZjdbglDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zjdbDjxh;                         // 资金调拨登记序号(SEQ_CW_DJXH)
	private String rq;                               // 计划日期
	private String dcDwDjxh;                         // 调出单位登记序号
	private String zcflDm;
	private String yhCshDjxh;
	private Double je;                               // 调拨金额	
	private String bz;                               // 备注说明
	private String drDwDjxh;                         // 调入单位登记序号
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String rqQ;
	private String rqZ;

	private String dcDwMc;                         // 调出单位
	private String drDwMc;                         // 调入单位
	private String ysJe;                           // 接收金额
	private Date jsrq;
	private String yfJe;                           // 实际调拨金额
	private Date dbrq;
	private String ysRq;                           // 接收日期
	private String yfRq;                           // 实际调拨日期
	
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwZjdbglDomain() {
	}

	public String getZjdbDjxh() {
		return zjdbDjxh;
	}

	public void setZjdbDjxh(String zjdbDjxh) {
		this.zjdbDjxh = zjdbDjxh;
	}

	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}



	public void setRq(String rq) {
		this.rq = rq;
	}



	public String getDcDwDjxh() {
		return dcDwDjxh;
	}



	public void setDcDwDjxh(String dcDwDjxh) {
		this.dcDwDjxh = dcDwDjxh;
	}



	public String getZcflDm() {
		return zcflDm;
	}



	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}



	public String getYhCshDjxh() {
		return yhCshDjxh;
	}



	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}



	public Double getJe() {
		return je;
	}



	public void setJe(Double je) {
		this.je = je;
	}



	public String getBz() {
		return bz;
	}



	public void setBz(String bz) {
		this.bz = bz;
	}



	public String getDrDwDjxh() {
		return drDwDjxh;
	}



	public void setDrDwDjxh(String drDwDjxh) {
		this.drDwDjxh = drDwDjxh;
	}



	public String getYxbz() {
		return yxbz;
	}



	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}



	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}



	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}



	public String getDcDwMc() {
		return dcDwMc;
	}



	public void setDcDwMc(String dcDwMc) {
		this.dcDwMc = dcDwMc;
	}



	public String getDrDwMc() {
		return drDwMc;
	}



	public void setDrDwMc(String drDwMc) {
		this.drDwMc = drDwMc;
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

	//获取修改人
	public String getXgrCzyDjxh() {
		return this.xgrCzyDjxh;
	}

	//设置修改人
	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh=xgrCzyDjxh;
	}

	//获取修改日期
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}

	//获取需要审批标志(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//设置需要审批标志(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//获取文书审批状态代码
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//设置文书审批状态代码
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//获取文书审批序号
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//设置文书审批序号
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
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

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getYsJe() {
		return ysJe;
	}

	public void setYsJe(String ysJe) {
		this.ysJe = ysJe;
	}

	public String getYfJe() {
		return yfJe;
	}

	public void setYfJe(String yfJe) {
		this.yfJe = yfJe;
	}

	public String getYsRq() {
		return ysRq;
	}

	public void setYsRq(String ysRq) {
		this.ysRq = ysRq;
	}

	public String getYfRq() {
		return yfRq;
	}

	public void setYfRq(String yfRq) {
		this.yfRq = yfRq;
	}

	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}

	public Date getDbrq() {
		return dbrq;
	}

	public void setDbrq(Date dbrq) {
		this.dbrq = dbrq;
	}
}
