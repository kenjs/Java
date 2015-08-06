package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_DDDL.
 * @author HaoY
 */

public class BgDddlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dddlDjxh;                         // 单点登录登记序号(SEQ_BG_DJXH)
	private String jgbm;                             // 机构编码
	private String mc;                               // 名称
	private String url;                              // 网址(启动地址)
	private String dlfsDm;                           // 登录方式代码
	private String xjgxbz;                           // 下级共享标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String dlfsMc;
	private String xjgxStr;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public BgDddlDomain() {
	}

	//获取单点登录登记序号(SEQ_BG_DJXH)
	public String getDddlDjxh() {
		return this.dddlDjxh;
	}

	//设置单点登录登记序号(SEQ_BG_DJXH)
	public void setDddlDjxh(String dddlDjxh) {
		this.dddlDjxh=dddlDjxh;
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取名称
	public String getMc() {
		return this.mc;
	}

	//设置名称
	public void setMc(String mc) {
		this.mc=mc;
	}

	//获取网址(启动地址)
	public String getUrl() {
		return this.url;
	}

	//设置网址(启动地址)
	public void setUrl(String url) {
		this.url=url;
	}

	//获取登录方式代码
	public String getDlfsDm() {
		return this.dlfsDm;
	}

	//设置登录方式代码
	public void setDlfsDm(String dlfsDm) {
		this.dlfsDm=dlfsDm;
	}

	//获取下级共享标志(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//设置下级共享标志(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
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

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getDlfsMc() {
		return dlfsMc;
	}

	public void setDlfsMc(String dlfsMc) {
		this.dlfsMc = dlfsMc;
	}

	public String getXjgxStr() {
		if("Y".equals(xjgxbz)){
			xjgxStr = "是";
		}else {
			xjgxStr = "否";
		}
		return xjgxStr;
	}

	public void setXjgxStr(String xjgxStr) {
		this.xjgxStr = xjgxStr;
	}
}
