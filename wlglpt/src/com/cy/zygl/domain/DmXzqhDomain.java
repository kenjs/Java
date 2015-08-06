package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR DM_XZQH is created by tools.
 * @author HJH
 */

public class DmXzqhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xzqhDm;                           // 行政区划代码
	private String xzqhMc;                           // 行政区划名称
	private String xzqhJc;                           // 行政区划简称
	private String xzqhQc;                           // 行政区划全称
	private String xzqhjbDm;                         // 行政区划级别代码
	private String sjXzqhDm;                         // 上级行政区划代码
	private String jbdm;                             // 级别代码
	private String yxbz;                             // 有效标志(Y/N)
	private String xzdqDm;                           // 行政大区代码
	private String xzdqMc;							 //	行政大区名称
	private String xzqhjbFlBm;                       // 行政区划级别分类编码
	private String pyqc;                             // 拼音全称
	private String pyjc;                             // 拼音简称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public DmXzqhDomain() {
	}

	//获取行政区划代码
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//设置行政区划代码
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//获取行政区划名称
	public String getXzqhMc() {
		return this.xzqhMc;
	}

	//设置行政区划名称
	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc=xzqhMc;
	}

	//获取行政区划简称
	public String getXzqhJc() {
		return this.xzqhJc;
	}

	//设置行政区划简称
	public void setXzqhJc(String xzqhJc) {
		this.xzqhJc=xzqhJc;
	}

	//获取行政区划全称
	public String getXzqhQc() {
		return this.xzqhQc;
	}

	//设置行政区划全称
	public void setXzqhQc(String xzqhQc) {
		this.xzqhQc=xzqhQc;
	}

	//获取行政区划级别代码
	public String getXzqhjbDm() {
		return this.xzqhjbDm;
	}

	//设置行政区划级别代码
	public void setXzqhjbDm(String xzqhjbDm) {
		this.xzqhjbDm=xzqhjbDm;
	}

	//获取上级行政区划代码
	public String getSjXzqhDm() {
		return this.sjXzqhDm;
	}

	//设置上级行政区划代码
	public void setSjXzqhDm(String sjXzqhDm) {
		this.sjXzqhDm=sjXzqhDm;
	}

	//获取级别代码
	public String getJbdm() {
		return this.jbdm;
	}

	//设置级别代码
	public void setJbdm(String jbdm) {
		this.jbdm=jbdm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取行政大区代码
	public String getXzdqDm() {
		return this.xzdqDm;
	}

	//设置行政大区代码
	public void setXzdqDm(String xzdqDm) {
		this.xzdqDm=xzdqDm;
	}

	public String getXzdqMc() {
		return xzdqMc;
	}

	public void setXzdqMc(String xzdqMc) {
		this.xzdqMc = xzdqMc;
	}

	//获取行政区划级别分类编码
	public String getXzqhjbFlBm() {
		return this.xzqhjbFlBm;
	}

	//设置行政区划级别分类编码
	public void setXzqhjbFlBm(String xzqhjbFlBm) {
		this.xzqhjbFlBm=xzqhjbFlBm;
	}

	//获取拼音全称
	public String getPyqc() {
		return this.pyqc;
	}

	//设置拼音全称
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//获取拼音简称
	public String getPyjc() {
		return this.pyjc;
	}

	//设置拼音简称
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
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
