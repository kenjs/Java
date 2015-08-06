package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR DM_XZQH is created by tools.
 * @author HJH
 */

public class WlSsYyDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String whXh;                           // 行政区划代码
	private String ssJgbm;                           // 行政区划名称
	private String ssJgbmMc;                           // 行政区划简称
	private String ssyy;                           // 行政区划全称
	private String sm;                         // 行政区划级别代码
	private String yxbz;                         // 上级行政区划代码
	                            // 有效标志(Y/N)
                       // 行政大区代码
	private String cjrMc;							 //	行政大区名称
	private String cjrq;                       // 行政区划级别分类编码
	private String xgrMc;                             // 拼音全称
	private String xgrq;                             // 拼音简称
	private String cjrCzyDjxh;
	private String xgrCzyDjxh;
	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}

	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}

	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}

	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}

	public String getWhXh() {
		return whXh;
	}

	public void setWhXh(String whXh) {
		this.whXh = whXh;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getSsJgbmMc() {
		return ssJgbmMc;
	}

	public void setSsJgbmMc(String ssJgbmMc) {
		this.ssJgbmMc = ssJgbmMc;
	}

	public String getSsyy() {
		return ssyy;
	}

	public void setSsyy(String ssyy) {
		this.ssyy = ssyy;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getXgrMc() {
		return xgrMc;
	}

	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}

	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
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

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	
}
