package com.cy.xtgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR XT_GNMK is created by tools.
 * @author HJH
 */

public class XtGnmkDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gnmkDm;                           // 功能模块代码(SEQ_GNMK_XH)
	private String gnmkMc;                           // 功能模块名称
	private String gnmkBz;                           // 功能模块备注
	private String url;                              // URL
	private String urlHelp;                          // URL_HELP
	private String parmbz;                           // 参数标志(Y/N)
	private String xybz;                             // 选用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrDm;                            // 创建人
	private String cjrq;                             // 创建日期
	private String xgrDm;                            // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public XtGnmkDomain() {
	}

	//获取功能模块代码(SEQ_GNMK_XH)
	public String getGnmkDm() {
		return this.gnmkDm;
	}

	//设置功能模块代码(SEQ_GNMK_XH)
	public void setGnmkDm(String gnmkDm) {
		this.gnmkDm=gnmkDm;
	}

	//获取功能模块名称
	public String getGnmkMc() {
		return this.gnmkMc;
	}

	//设置功能模块名称
	public void setGnmkMc(String gnmkMc) {
		this.gnmkMc=gnmkMc;
	}

	//获取功能模块备注
	public String getGnmkBz() {
		return this.gnmkBz;
	}

	//设置功能模块备注
	public void setGnmkBz(String gnmkBz) {
		this.gnmkBz=gnmkBz;
	}

	//获取URL
	public String getUrl() {
		return this.url;
	}

	//设置URL
	public void setUrl(String url) {
		this.url=url;
	}

	//获取URL_HELP
	public String getUrlHelp() {
		return this.urlHelp;
	}

	//设置URL_HELP
	public void setUrlHelp(String urlHelp) {
		this.urlHelp=urlHelp;
	}

	//获取参数标志(Y/N)
	public String getParmbz() {
		return this.parmbz;
	}

	//设置参数标志(Y/N)
	public void setParmbz(String parmbz) {
		this.parmbz=parmbz;
	}

	//获取选用标志(Y/N)
	public String getXybz() {
		return this.xybz;
	}

	//设置选用标志(Y/N)
	public void setXybz(String xybz) {
		this.xybz=xybz;
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
	public String getCjrDm() {
		return this.cjrDm;
	}

	//设置创建人
	public void setCjrDm(String cjrDm) {
		this.cjrDm=cjrDm;
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
	public String getXgrDm() {
		return this.xgrDm;
	}

	//设置修改人
	public void setXgrDm(String xgrDm) {
		this.xgrDm=xgrDm;
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
