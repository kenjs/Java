package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_FBS_JSJG is created by tools.
 * @author HJH
 */

public class QyFbsJsjgDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsjgDjxh;                         // 结算价格登记序号(SEQ_ZY_DJXH)
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String fbsDjxh;                          // 
	private String lxDjxh;                           // 
	private String jsJldwDm;                         // 地址
	private Double dj;                               // 名称
	private String yxqQ;                             // 
	private String yxqZ;                             // 拼音全称
	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private String jsJldwMc;
	private String lxDjmc;    
	private String zt;
	
	private String count;

	private String sfdXzqhDm;                        // 始发地行政区划代码
	private String mddXzqhDm;                        // 目的地行政区划代码
	private Double lcs;                              // 里程数
	private Double ddts;                             // 达到天数
	private String syfw;                             // 是否适用全部货物(Y/N)
	private String hwDjxh;                           // 货物登记序号
	private String hwxhDjxh;                         // 货物型号登记序号
	private String jldwFlDm;                         // 计量单位分类代码
	private String jldwDm;                           // 计量单位
	private String jgjsgs;                           // 价格计算公式(带参数的公式)
	private String xtgs;                             // 价格计算系统公式
	private String jgsm;                             // 价格说明

	private String jldwFlMc;
	private String sfdXzqhMc;                      
	private String mddXzqhMc; 
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QyFbsJsjgDomain() {
	}

	//获取结算价格登记序号(SEQ_ZY_DJXH)
	public String getJsjgDjxh() {
		return this.jsjgDjxh;
	}

	//设置结算价格登记序号(SEQ_ZY_DJXH)
	public void setJsjgDjxh(String jsjgDjxh) {
		this.jsjgDjxh=jsjgDjxh;
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取
	public String getFbsDjxh() {
		return this.fbsDjxh;
	}

	//设置
	public void setFbsDjxh(String fbsDjxh) {
		this.fbsDjxh=fbsDjxh;
	}

	//获取
	public String getLxDjxh() {
		return this.lxDjxh;
	}

	//设置
	public void setLxDjxh(String lxDjxh) {
		this.lxDjxh=lxDjxh;
	}

	//获取地址
	public String getJsJldwDm() {
		return this.jsJldwDm;
	}

	//设置地址
	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm=jsJldwDm;
	}

	//获取名称
	public Double getDj() {
		return this.dj;
	}

	//设置名称
	public void setDj(Double dj) {
		this.dj=dj;
	}

	//获取
	public String getYxqQ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqQ);
		}
		catch(Exception e){
			return this.yxqQ;
		}
	}

	//设置
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//获取拼音全称
	public String getYxqZ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqZ);
		}
		catch(Exception e){
			return this.yxqZ;
		}
	}

	//设置拼音全称
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
	}

	//获取
	public String getBz() {
		return this.bz;
	}

	//设置
	public void setBz(String bz) {
		this.bz=bz;
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

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public String getLxDjmc() {
		return lxDjmc;
	}

	public void setLxDjmc(String lxDjmc) {
		this.lxDjmc = lxDjmc;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSfdXzqhDm() {
		return sfdXzqhDm;
	}

	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm = sfdXzqhDm;
	}

	public String getMddXzqhDm() {
		return mddXzqhDm;
	}

	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm = mddXzqhDm;
	}

	public Double getLcs() {
		return lcs;
	}

	public void setLcs(Double lcs) {
		this.lcs = lcs;
	}

	public Double getDdts() {
		return ddts;
	}

	public void setDdts(Double ddts) {
		this.ddts = ddts;
	}

	public String getSyfw() {
		return syfw;
	}

	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}

	public String getHwDjxh() {
		return hwDjxh;
	}

	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh = hwDjxh;
	}

	public String getHwxhDjxh() {
		return hwxhDjxh;
	}

	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh = hwxhDjxh;
	}

	public String getJldwFlDm() {
		return jldwFlDm;
	}

	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm = jldwFlDm;
	}

	public String getJldwDm() {
		return jldwDm;
	}

	public void setJldwDm(String jldwDm) {
		this.jldwDm = jldwDm;
	}

	public String getJgjsgs() {
		return jgjsgs;
	}

	public void setJgjsgs(String jgjsgs) {
		this.jgjsgs = jgjsgs;
	}

	public String getXtgs() {
		return xtgs;
	}

	public void setXtgs(String xtgs) {
		this.xtgs = xtgs;
	}

	public String getJgsm() {
		return jgsm;
	}

	public void setJgsm(String jgsm) {
		this.jgsm = jgsm;
	}

	public String getSfdXzqhMc() {
		return sfdXzqhMc;
	}

	public void setSfdXzqhMc(String sfdXzqhMc) {
		this.sfdXzqhMc = sfdXzqhMc;
	}

	public String getMddXzqhMc() {
		return mddXzqhMc;
	}

	public void setMddXzqhMc(String mddXzqhMc) {
		this.mddXzqhMc = mddXzqhMc;
	}

	public String getJldwFlMc() {
		return jldwFlMc;
	}

	public void setJldwFlMc(String jldwFlMc) {
		this.jldwFlMc = jldwFlMc;
	}
}
