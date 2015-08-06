package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_JCBM_JLDW is created by tools.
 * @author HaoY
 */

public class QyJcbmJldwDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String jldwDm;                           // 计量单位代码
	private String jbdwbz;                           // 
	private Double hsbl;                             // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String jbdwStr;
	private String jldwMc;
	private String jldwJc;
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String czzt;
	private String jldwFlDm;
	private String strObj;
	private String zt;
	
	private String jldwFlMc;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<QyJcbmJldwDomain> jldwList;
	
	public String getCzzt() {
		return czzt;
	}

	public void setCzzt(String czzt) {
		this.czzt = czzt;
	}

	public String getJldwMc() {
		return jldwMc;
	}

	public void setJldwMc(String jldwMc) {
		this.jldwMc = jldwMc;
	}

	public String getStrObj() {
		return strObj;
	}

	public void setStrObj(String strObj) {
		this.strObj = strObj;
	}

	public QyJcbmJldwDomain() {
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取计量单位代码
	public String getJldwDm() {
		return this.jldwDm;
	}

	//设置计量单位代码
	public void setJldwDm(String jldwDm) {
		this.jldwDm=jldwDm;
	}

	//获取
	public String getJbdwbz() {
		return this.jbdwbz;
	}

	//设置
	public void setJbdwbz(String jbdwbz) {
		this.jbdwbz=jbdwbz;
	}

	//获取
	public Double getHsbl() {
		return this.hsbl;
	}

	//设置
	public void setHsbl(Double hsbl) {
		this.hsbl=hsbl;
	}

	//获取
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//设置
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
	}

	public String getZt() {
		if("Y".equals(qybz)){
			zt = "已启用";
		}else{
			zt = "未启用";
		}
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
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

	public String getJldwFlDm() {
		return jldwFlDm;
	}

	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm = jldwFlDm;
	}

	public String getJbdwStr() {
		if("Y".equals(jbdwbz)){
			jbdwStr = "是";
		}else{
			jbdwStr = "否";
		}
		return jbdwStr;
	}

	public void setJbdwStr(String jbdwStr) {
		this.jbdwStr = jbdwStr;
	}

	public String getJldwJc() {
		return jldwJc;
	}

	public void setJldwJc(String jldwJc) {
		this.jldwJc = jldwJc;
	}

	public List<QyJcbmJldwDomain> getJldwList() {
		if(jldwList == null){
			jldwList = new ArrayList<QyJcbmJldwDomain>();
		}
		return jldwList;
	}

	public void setJldwList(List<QyJcbmJldwDomain> jldwList) {
		this.jldwList = jldwList;
	}

	public String getJldwFlMc() {
		return jldwFlMc;
	}

	public void setJldwFlMc(String jldwFlMc) {
		this.jldwFlMc = jldwFlMc;
	}

}
