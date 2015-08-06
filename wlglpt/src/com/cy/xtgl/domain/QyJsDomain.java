package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_JS is created by tools.
 * @author ylp
 * @since 2013-1-10 上午8:32:41 
 * @version
 */

public class QyJsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDjxh;                           // 角色编码(SEQ_JS_DJXH或DM_JS.JS_DM)
	private String jsMc;                             // 角色名称
	private String jsJc;                             // 角色简称
	private String jsDm;                             // 角色代码
	private String lybz;                             // 来源标志(Y企业创建/N角色代码)
	private String bzsm;                             // 备注说明
	private String ssJgbm;                           // 所属机构编码(总公司或分公司，非部门)
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String qystr;							 //显示启用标志
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String sjMc;    						 //上级名称
	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QyJsDomain() {
	}

	//获取角色编码(SEQ_JS_DJXH或DM_JS.JS_DM)
	public String getJsDjxh() {
		return this.jsDjxh;
	}

	//设置角色编码(SEQ_JS_DJXH或DM_JS.JS_DM)
	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh=jsDjxh;
	}

	//获取角色名称
	public String getJsMc() {
		return this.jsMc;
	}

	//设置角色名称
	public void setJsMc(String jsMc) {
		this.jsMc=jsMc;
	}

	//获取角色简称
	public String getJsJc() {
		return this.jsJc;
	}

	//设置角色简称
	public void setJsJc(String jsJc) {
		this.jsJc=jsJc;
	}

	//获取角色代码
	public String getJsDm() {
		return this.jsDm;
	}

	//设置角色代码
	public void setJsDm(String jsDm) {
		this.jsDm=jsDm;
	}

	//获取来源标志(Y企业创建/N角色代码)
	public String getLybz() {
		return this.lybz;
	}

	//设置来源标志(Y企业创建/N角色代码)
	public void setLybz(String lybz) {
		this.lybz=lybz;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取所属机构编码(总公司或分公司，非部门)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构编码(总公司或分公司，非部门)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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
	
	public String getQystr() {
		if(qybz!=null)
		{
			if(qybz.equals("Y"))
			{
				qystr="启用";
			}
			else {
				qystr="停用";
			}
		}
		return qystr;
	}

	public void setQystr(String qystr) {
		this.qystr = qystr;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	
	
}
