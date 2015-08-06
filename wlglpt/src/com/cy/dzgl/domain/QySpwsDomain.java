package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SPWS.
 * @author HaoY
 */

public class QySpwsDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // 机构编码
	private String wsDm;                             // 文书代码
	private String xmflbz;                           // 项目分类标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private String sfxz;							//是否选中
	private String strObj;
	private String zt;
	private String ztStr;
	private String flbz;

	private String ywflMc;
	private String ywhjMc;
	private String ywflDm;
	private String ywhjDm;
	private String wsMc;
	private String wsJc;
	private String sm;
	private String dwMc;
	private String wsspmsDm;
	private String wsspmsMc;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QySpwsDomain() {
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

	//获取项目分类标志(Y/N)
	public String getXmflbz() {
		return this.xmflbz;
	}

	//设置项目分类标志(Y/N)
	public void setXmflbz(String xmflbz) {
		this.xmflbz=xmflbz;
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

	public String getSfxz() {
		return sfxz;
	}

	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}

	public String getStrObj() {
		return strObj;
	}

	public void setStrObj(String strObj) {
		this.strObj = strObj;
	}

	public String getYwflMc() {
		return ywflMc;
	}

	public void setYwflMc(String ywflMc) {
		this.ywflMc = ywflMc;
	}

	public String getYwhjMc() {
		return ywhjMc;
	}

	public void setYwhjMc(String ywhjMc) {
		this.ywhjMc = ywhjMc;
	}

	public String getYwflDm() {
		return ywflDm;
	}

	public void setYwflDm(String ywflDm) {
		this.ywflDm = ywflDm;
	}

	public String getYwhjDm() {
		return ywhjDm;
	}

	public void setYwhjDm(String ywhjDm) {
		this.ywhjDm = ywhjDm;
	}

	public String getWsMc() {
		return wsMc;
	}

	public void setWsMc(String wsMc) {
		this.wsMc = wsMc;
	}

	public String getFlbz() {
		if("Y".equals(xmflbz)){
			flbz = "是";
		}else if("N".equals(xmflbz)){
			flbz = "否";
		}else {
			flbz = "空";
		}
		return flbz;
	}

	public void setFlbz(String flbz) {
		this.flbz = flbz;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getZtStr() {
		if("Y".equals(zt)){
			ztStr = "启用";
		}else{
			ztStr = "停用";
		}
		return ztStr;
	}

	public void setZtStr(String ztStr) {
		this.ztStr = ztStr;
	}
	
	public String getWsJc() {
		return wsJc;
	}

	public void setWsJc(String wsJc) {
		this.wsJc = wsJc;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getWsspmsDm() {
		return wsspmsDm;
	}

	public void setWsspmsDm(String wsspmsDm) {
		this.wsspmsDm = wsspmsDm;
	}

	public String getWsspmsMc() {
		return wsspmsMc;
	}

	public void setWsspmsMc(String wsspmsMc) {
		this.wsspmsMc = wsspmsMc;
	}

}
