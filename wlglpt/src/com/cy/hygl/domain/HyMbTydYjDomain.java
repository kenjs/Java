package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_MB_TYD_YJ is created by tools.
 * @author HJH
 */

public class HyMbTydYjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String mbDjxh;                           // 模版登记序号
	private Double yjHj;                             // 运价_总运价
	private Double yjYj;                             // 运价_月结
	private Double yjXf;                             // 运价_现付
	private Double yjHdf;                            // 运价_货到付
	private Double yjThf;                            // 运价_提货付
	private Double yjHf;                             // 运价_回单付
	private Double yjHk;                             // 运价_回扣

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyMbTydYjDomain() {
	}

	//获取模版登记序号
	public String getMbDjxh() {
		return this.mbDjxh;
	}

	//设置模版登记序号
	public void setMbDjxh(String mbDjxh) {
		this.mbDjxh=mbDjxh;
	}

	//获取运价_总运价
	public Double getYjHj() {
		return this.yjHj;
	}

	//设置运价_总运价
	public void setYjHj(Double yjHj) {
		this.yjHj=yjHj;
	}

	//获取运价_月结
	public Double getYjYj() {
		return this.yjYj;
	}

	//设置运价_月结
	public void setYjYj(Double yjYj) {
		this.yjYj=yjYj;
	}

	//获取运价_现付
	public Double getYjXf() {
		return this.yjXf;
	}

	//设置运价_现付
	public void setYjXf(Double yjXf) {
		this.yjXf=yjXf;
	}

	//获取运价_货到付
	public Double getYjHdf() {
		return this.yjHdf;
	}

	//设置运价_货到付
	public void setYjHdf(Double yjHdf) {
		this.yjHdf=yjHdf;
	}

	//获取运价_提货付
	public Double getYjThf() {
		return this.yjThf;
	}

	//设置运价_提货付
	public void setYjThf(Double yjThf) {
		this.yjThf=yjThf;
	}

	//获取运价_回单付
	public Double getYjHf() {
		return this.yjHf;
	}

	//设置运价_回单付
	public void setYjHf(Double yjHf) {
		this.yjHf=yjHf;
	}

	//获取运价_回扣
	public Double getYjHk() {
		return this.yjHk;
	}

	//设置运价_回扣
	public void setYjHk(Double yjHk) {
		this.yjHk=yjHk;
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
