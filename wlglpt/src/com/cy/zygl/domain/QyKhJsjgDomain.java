package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_KH_JSJG is created by tools.
 * @author HJH
 */

public class QyKhJsjgDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jsjgDjxh;                         // 结算价格登记序号(SEQ_ZY_DJXH)
	private String ssJgbm;                           // 所属机构
	private String khDjxh;                           // 客户登记序号
	private String sfdXzqhDm;                        // 始发地_行政区划代码
	private String mddXzqhDm;                        // 目的地_行政区划代码
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
	private String yxqQ;                             // 有效期起
	private String yxqZ;                             // 有效期止
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private String sjMc;
	private String khMc;
	private String hwMc;
	
	private String fhrXzqhDm;
	private String shrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhMc;
	private String tager;
	private String bmDjxh;
	private String fhrDjxh;
	private String fhrMc;
	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getBmDjxh() {
		return bmDjxh;
	}

	public void setBmDjxh(String bmDjxh) {
		this.bmDjxh = bmDjxh;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QyKhJsjgDomain() {
	}

	//获取结算价格登记序号(SEQ_ZY_DJXH)
	public String getJsjgDjxh() {
		return this.jsjgDjxh;
	}

	//设置结算价格登记序号(SEQ_ZY_DJXH)
	public void setJsjgDjxh(String jsjgDjxh) {
		this.jsjgDjxh=jsjgDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取客户登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置客户登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取始发地_行政区划代码
	public String getSfdXzqhDm() {
		return this.sfdXzqhDm;
	}

	//设置始发地_行政区划代码
	public void setSfdXzqhDm(String sfdXzqhDm) {
		this.sfdXzqhDm=sfdXzqhDm;
	}

	//获取目的地_行政区划代码
	public String getMddXzqhDm() {
		return this.mddXzqhDm;
	}

	//设置目的地_行政区划代码
	public void setMddXzqhDm(String mddXzqhDm) {
		this.mddXzqhDm=mddXzqhDm;
	}

	//获取里程数
	public Double getLcs() {
		return this.lcs;
	}

	//设置里程数
	public void setLcs(Double lcs) {
		this.lcs=lcs;
	}

	//获取达到天数
	public Double getDdts() {
		return this.ddts;
	}

	//设置达到天数
	public void setDdts(Double ddts) {
		this.ddts=ddts;
	}

	//获取是否适用全部货物(Y/N)
	public String getSyfw() {
		return this.syfw;
	}

	//设置是否适用全部货物(Y/N)
	public void setSyfw(String syfw) {
		this.syfw=syfw;
	}

	//获取货物登记序号
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//设置货物登记序号
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
	}

	//获取货物型号登记序号
	public String getHwxhDjxh() {
		return this.hwxhDjxh;
	}

	//设置货物型号登记序号
	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh=hwxhDjxh;
	}

	//获取计量单位分类代码
	public String getJldwFlDm() {
		return this.jldwFlDm;
	}

	//设置计量单位分类代码
	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm=jldwFlDm;
	}

	//获取计量单位
	public String getJldwDm() {
		return this.jldwDm;
	}

	//设置计量单位
	public void setJldwDm(String jldwDm) {
		this.jldwDm=jldwDm;
	}

	//获取价格计算公式(带参数的公式)
	public String getJgjsgs() {
		return this.jgjsgs;
	}

	//设置价格计算公式(带参数的公式)
	public void setJgjsgs(String jgjsgs) {
		this.jgjsgs=jgjsgs;
	}

	//获取价格计算系统公式
	public String getXtgs() {
		return this.xtgs;
	}

	//设置价格计算系统公式
	public void setXtgs(String xtgs) {
		this.xtgs=xtgs;
	}

	//获取价格说明
	public String getJgsm() {
		return this.jgsm;
	}

	//设置价格说明
	public void setJgsm(String jgsm) {
		this.jgsm=jgsm;
	}

	//获取有效期起
	public String getYxqQ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqQ);
		}
		catch(Exception e){
			return this.yxqQ;
		}
	}

	//设置有效期起
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//获取有效期止
	public String getYxqZ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqZ);
		}
		catch(Exception e){
			return this.yxqZ;
		}
	}

	//设置有效期止
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
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
}
