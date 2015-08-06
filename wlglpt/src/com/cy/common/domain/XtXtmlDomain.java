package com.cy.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR XT_XTML is created by tools.
 * @author HJH
 */

public class XtXtmlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long xtmlXh;                             // 系统目录序号(SEQ_XTML_XH)
	private String xtmlMc;                           // 系统目录名称
	private String xtflDm;                           // 系统分类代码
	private Long sjXtmlXh;                           // 上级目录序号
	private String pic;
	private Long sxh;                                // 顺序号
	private Long jcxh;                               // 级次序号
	private String pxxh;                             // 排序序号(12位长度，每2位为一级)
	private String yxbz;                             // 有效标志
	private String cjrDm;                            // 创建人
	private String cjrq;                             // 创建日期
	private String xgrDm;                            // 修改人
	private String xgrq;                             // 修改日期
	private List<XtGnmkDomain> gnList;
	private List<XtGnmkDomain> gnMKList;
	public List<XtGnmkDomain> getGnList() {
		if(gnList==null){
			gnList=new ArrayList<XtGnmkDomain>();
		}
		return gnList;
	}

	public List<XtGnmkDomain> getGnMKList() {
		if(gnMKList==null){
			gnMKList=new ArrayList<XtGnmkDomain>();
		}
		return gnMKList;
	}

	public void setGnMKList(List<XtGnmkDomain> gnMKList) {
		this.gnMKList = gnMKList;
	}

	public void setGnList(List<XtGnmkDomain> gnList) {
		this.gnList = gnList;
	}

	public XtXtmlDomain() {
	}

	//获取系统目录序号(SEQ_XTML_XH)
	public Long getXtmlXh() {
		return this.xtmlXh;
	}

	//设置系统目录序号(SEQ_XTML_XH)
	public void setXtmlXh(Long xtmlXh) {
		this.xtmlXh=xtmlXh;
	}

	//获取系统目录名称
	public String getXtmlMc() {
		return this.xtmlMc;
	}

	//设置系统目录名称
	public void setXtmlMc(String xtmlMc) {
		this.xtmlMc=xtmlMc;
	}

	//获取系统分类代码
	public String getXtflDm() {
		return this.xtflDm;
	}

	//设置系统分类代码
	public void setXtflDm(String xtflDm) {
		this.xtflDm=xtflDm;
	}

	//获取上级目录序号
	public Long getSjXtmlXh() {
		return this.sjXtmlXh;
	}

	//设置上级目录序号
	public void setSjXtmlXh(Long sjXtmlXh) {
		this.sjXtmlXh=sjXtmlXh;
	}

	//获取顺序号
	public Long getSxh() {
		return this.sxh;
	}

	//设置顺序号
	public void setSxh(Long sxh) {
		this.sxh=sxh;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	//获取级次序号
	public Long getJcxh() {
		return this.jcxh;
	}

	//设置级次序号
	public void setJcxh(Long jcxh) {
		this.jcxh=jcxh;
	}

	//获取排序序号(12位长度，每2位为一级)
	public String getPxxh() {
		return this.pxxh;
	}

	//设置排序序号(12位长度，每2位为一级)
	public void setPxxh(String pxxh) {
		this.pxxh=pxxh;
	}

	//获取有效标志
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志
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
		return this.cjrq;
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
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}
}