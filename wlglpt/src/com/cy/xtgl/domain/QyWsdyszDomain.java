package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_WSDYSZ is created by tools.
 * @author HJH
 */

public class QyWsdyszDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                             // 维护序号(SEQ_ZY_DJXH)
	private String ssJgbm;                           // 所属机构(当前所属公司)
	private String wsDm;                             // 文书代码，1001:托运单
	private Double leftMargin;                       // 左边距
	private Double topMargin;                        // 上边距
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QyWsdyszDomain() {
	}

	//获取维护序号(SEQ_ZY_DJXH)
	public String getWhXh() {
		return this.whXh;
	}

	//设置维护序号(SEQ_ZY_DJXH)
	public void setWhXh(String whXh) {
		this.whXh=whXh;
	}

	//获取所属机构(当前所属公司)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构(当前所属公司)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取文书代码，1001:托运单
	public String getWsDm() {
		return this.wsDm;
	}

	//设置文书代码，1001:托运单
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//获取左边距
	public Double getLeftMargin() {
		return this.leftMargin;
	}

	//设置左边距
	public void setLeftMargin(Double leftMargin) {
		this.leftMargin=leftMargin;
	}

	//获取上边距
	public Double getTopMargin() {
		return this.topMargin;
	}

	//设置上边距
	public void setTopMargin(Double topMargin) {
		this.topMargin=topMargin;
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
}
