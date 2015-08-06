package com.cy.zyegl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_PC_HWXX_XYDJ is created by tools.
 * @author HJH
 */

public class HyPcHwxxXydjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private String shrDjxh;                          // 收货人_登记序号
	private String shrMc;                            // 收货人_名称
	private String shrDz;                            // 收货人_地址
	private String shrLxr;                           // 收货人_联系人
	private String shrLxdh;                          // 收货人_联系电话
	private String shrXzqhDm;                        // 收货人_行政区划代码
	private String szHwBzHldwDm;                     // 实装_货物_包装_计量单位
	private Double szHwSl;                           // 实装_货物_数量
	private Double szHwZl;                           // 实装_货物_重量
	private Double szHwTj;                           // 实装_货物_体积
	private Date yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private Double szJsSl;                           // 实装_结算数量
	private String bz;                               // 备注
	private String hdbh;
	
	private String hwmc;
	private String shrXzqhMc;
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	
	private String editFlag;						//可修改标志

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyPcHwxxXydjDomain() {
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车货物序号(未发货登记序号)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置派车货物序号(未发货登记序号)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
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

	//获取收货人_登记序号
	public String getShrDjxh() {
		return this.shrDjxh;
	}

	//设置收货人_登记序号
	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh=shrDjxh;
	}

	//获取收货人_名称
	public String getShrMc() {
		return this.shrMc;
	}

	//设置收货人_名称
	public void setShrMc(String shrMc) {
		this.shrMc=shrMc;
	}

	//获取收货人_地址
	public String getShrDz() {
		return this.shrDz;
	}

	//设置收货人_地址
	public void setShrDz(String shrDz) {
		this.shrDz=shrDz;
	}

	//获取收货人_联系人
	public String getShrLxr() {
		return this.shrLxr;
	}

	//设置收货人_联系人
	public void setShrLxr(String shrLxr) {
		this.shrLxr=shrLxr;
	}

	//获取收货人_联系电话
	public String getShrLxdh() {
		return this.shrLxdh;
	}

	//设置收货人_联系电话
	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh=shrLxdh;
	}

	//获取收货人_行政区划代码
	public String getShrXzqhDm() {
		return this.shrXzqhDm;
	}

	//设置收货人_行政区划代码
	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm=shrXzqhDm;
	}

	//获取实装_货物_包装_计量单位
	public String getSzHwBzHldwDm() {
		return this.szHwBzHldwDm;
	}

	//设置实装_货物_包装_计量单位
	public void setSzHwBzHldwDm(String szHwBzHldwDm) {
		this.szHwBzHldwDm=szHwBzHldwDm;
	}

	//获取实装_货物_数量
	public Double getSzHwSl() {
		return this.szHwSl;
	}

	//设置实装_货物_数量
	public void setSzHwSl(Double szHwSl) {
		this.szHwSl=szHwSl;
	}

	//获取实装_货物_重量
	public Double getSzHwZl() {
		return this.szHwZl;
	}

	//设置实装_货物_重量
	public void setSzHwZl(Double szHwZl) {
		this.szHwZl=szHwZl;
	}

	//获取实装_货物_体积
	public Double getSzHwTj() {
		return this.szHwTj;
	}

	//设置实装_货物_体积
	public void setSzHwTj(Double szHwTj) {
		this.szHwTj=szHwTj;
	}

	//获取要求到达日期
	public Date getYqDdrq() {
		return this.yqDdrq;
	}

	//设置要求到达日期
	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq=yqDdrq;
	}

	//获取收货方式代码
	public String getShfsDm() {
		return this.shfsDm;
	}

	//设置收货方式代码
	public void setShfsDm(String shfsDm) {
		this.shfsDm=shfsDm;
	}

	//获取实装_结算数量
	public Double getSzJsSl() {
		return this.szJsSl;
	}

	//设置实装_结算数量
	public void setSzJsSl(Double szJsSl) {
		this.szJsSl=szJsSl;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public String getHwSlJldwMc() {
		return hwSlJldwMc;
	}

	public void setHwSlJldwMc(String hwSlJldwMc) {
		this.hwSlJldwMc = hwSlJldwMc;
	}

	public String getHwZlJldwMc() {
		return hwZlJldwMc;
	}

	public void setHwZlJldwMc(String hwZlJldwMc) {
		this.hwZlJldwMc = hwZlJldwMc;
	}

	public String getHwTjJldwMc() {
		return hwTjJldwMc;
	}

	public void setHwTjJldwMc(String hwTjJldwMc) {
		this.hwTjJldwMc = hwTjJldwMc;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
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
}
