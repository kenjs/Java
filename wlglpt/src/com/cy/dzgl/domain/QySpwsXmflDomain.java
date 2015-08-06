package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SPWS_XMFL is created by tools.
 * @author HJH
 */

public class QySpwsXmflDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xmflDjxh;                         // 项目分类登记序号(SEQ_XMFL_DJXH)
	private String jgbm;                             // 机构编码
	private String wsDm;                             // 文书代码
	private String xmflmc;                           // 项目分类名称
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String wsMc;							//文书名称
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QySpwsXmflDomain() {
	}

	//获取项目分类登记序号(SEQ_XMFL_DJXH)
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//设置项目分类登记序号(SEQ_XMFL_DJXH)
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取文书代码
	public String getWsDm() {
		return this.wsDm;
	}

	//设置文书代码
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//获取项目分类名称
	public String getXmflmc() {
		return this.xmflmc;
	}

	//设置项目分类名称
	public void setXmflmc(String xmflmc) {
		this.xmflmc=xmflmc;
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

	public String getWsMc() {
		return wsMc;
	}

	public void setWsMc(String wsMc) {
		this.wsMc = wsMc;
	}
}
