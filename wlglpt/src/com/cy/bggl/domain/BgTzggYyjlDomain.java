package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_TZGG_YYJL is created by tools.
 * @author HJH
 */

public class BgTzggYyjlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String tzggXh;                           // 通知公告序号
	private String czyDjxh;                          // 操作员登记序号
	private String ckrq;                             // 查看日期(到时分秒)

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public BgTzggYyjlDomain() {
	}

	//获取通知公告序号
	public String getTzggXh() {
		return this.tzggXh;
	}

	//设置通知公告序号
	public void setTzggXh(String tzggXh) {
		this.tzggXh=tzggXh;
	}

	//获取操作员登记序号
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取查看日期(到时分秒)
	public String getCkrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.ckrq);
		}
		catch(Exception e){
			return this.ckrq;
		}
	}

	//设置查看日期(到时分秒)
	public void setCkrq(String ckrq) {
		this.ckrq=ckrq;
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
