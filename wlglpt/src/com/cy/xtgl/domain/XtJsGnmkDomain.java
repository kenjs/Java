package com.cy.xtgl.domain;


import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DOMAIN class FOR XT_JS_GNMK is created by tools.
 * @author HJH
 */

public class XtJsGnmkDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsDm;                             // 角色代码
	private String gnmkDm;                           // 功能模块代码
	private String jsDjxh;                           // 岗位编码(SEQ_GW_DJXH或DM_GW.GW_DM)
	private String jsMc;                            //角色名称
	private String xtml;                             //保存系统目录
	private String tag;                              //标签tag=‘1’是角色-权限，否则是权限-角色
	private String xtflDm;                            //系统分类代码
	private String ssJgbm;                           //机构编码
	private String treeStr;                          //通过action中getTreeStr的方法获取
	private String gnmkDmStr;                         //通过action中queryGnmkDmsByJsDm的方法获取的
	private String jsStr;							  //通过action中queryJsInnerHtmlByGnmkDm的方法获取
	private String selDmsStr;						  //tab1中使用，获取选中的复选框(对应的菜单为非目录结点)对应的value
	private String allQueryNoneNodeGnmkDms ;//tab1获取权限树所有node=N的菜单（即非目录结点）
	private String selJsDm;							 //角色-权限选择的角色代码
	private String selGnmkDm;						//权限-角色选择的功能模块代码
	private String selJsDmsStr;						//权限-角色选择中的角色代码str，可能包含多个，用，隔开
	private String listStr;
	private String flag;   							//用来判断，是保存角色权限，还是权限角色
	private String jsList; 							//用来保存角色
	private List<BaseBusinessDomain> dataListGw;  //角色列表
	public String getListStr() {
		return listStr;
	}



	public void setListStr(String listStr) {
		this.listStr = listStr;
	}



	public XtJsGnmkDomain() {
	}
	
	

	public List<BaseBusinessDomain> getDataListGw() {
		if(dataListGw==null)
		{
			dataListGw=new ArrayList<BaseBusinessDomain>();
		}
		return dataListGw;
	}



	public void setDataListGw(List<BaseBusinessDomain> dataListGw) {
		this.dataListGw = dataListGw;
	}



	public String getXtflDm() {
		return xtflDm;
	}

	public void setXtflDm(String xtflDm) {
		this.xtflDm = xtflDm;
	}


	//获取角色代码
	public String getJsDm() {
		return this.jsDm;
	}

	//设置角色代码
	public void setJsDm(String jsDm) {
		this.jsDm=jsDm;
	}

	//获取功能模块代码
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//设置功能模块代码
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getGnmkDmStr() {
		return gnmkDmStr;
	}

	public void setGnmkDmStr(String gnmkDmStr) {
		this.gnmkDmStr = gnmkDmStr;
	}

	public String getJsStr() {
		return jsStr;
	}

	public void setJsStr(String jsStr) {
		this.jsStr = jsStr;
	}

	public String getTreeStr() {
		return treeStr;
	}

	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	public String getAllQueryNoneNodeGnmkDms() {
		return allQueryNoneNodeGnmkDms;
	}

	public void setAllQueryNoneNodeGnmkDms(String allQueryNoneNodeGnmkDms) {
		this.allQueryNoneNodeGnmkDms = allQueryNoneNodeGnmkDms;
	}

	public String getSelDmsStr() {
		return selDmsStr;
	}

	public void setSelDmsStr(String selDmsStr) {
		this.selDmsStr = selDmsStr;
	}

	public String getSelJsDm() {
		return selJsDm;
	}

	public void setSelJsDm(String selJsDm) {
		this.selJsDm = selJsDm;
	}

	public String getSelGnmkDm() {
		return selGnmkDm;
	}

	public void setSelGnmkDm(String selGnmkDm) {
		this.selGnmkDm = selGnmkDm;
	}

	public String getSelJsDmsStr() {
		return selJsDmsStr;
	}

	public void setSelJsDmsStr(String selJsDmsStr) {
		this.selJsDmsStr = selJsDmsStr;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}






	public String getJsDjxh() {
		return jsDjxh;
	}



	public void setJsDjxh(String jsDjxh) {
		this.jsDjxh = jsDjxh;
	}



	public String getJsMc() {
		return jsMc;
	}



	public void setJsMc(String jsMc) {
		this.jsMc = jsMc;
	}



	public String getXtml() {
		return xtml;
	}



	public void setXtml(String xtml) {
		this.xtml = xtml;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getJsList() {
		return jsList;
	}



	public void setJsList(String jsList) {
		this.jsList = jsList;
	}





}