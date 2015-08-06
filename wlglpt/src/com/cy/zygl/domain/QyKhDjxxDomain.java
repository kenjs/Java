package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_KH_DJXX is created by tools.
 * @author HJH
 */

public class QyKhDjxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String khDjxh;                           // 
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String khmc;                             // 名称
	private String khjc;                             // 简称
	private String pyqc;                             // 拼音全称
	private String pyjc;                             // 拼音简称
	private String xzqhDm;                           // 行政区划代码
	private String xzqhMc;                           // 行政区划
	private String xzqhJc;                           // 行政区划简称
	private String dz;                               // 地址
	private String dh;                               // 电话
	private String yb;                               // 邮编
	private String fzr;                              // 负责人

	private String bz;                               // 
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrmc;
	private String djrq;                             // 
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String khlxDm;							 //客户类型代码
	private String ykjsfsDm;                         //余款结算方式代码
	private String khlxMc;                            //客户类型名称
	private String ykjsfsMc;						//余款结算方式名称
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private String ssjgmc;
	private String djjgmc;
	
	private String xxgxfsDm;						//信息共享方式代码
	private String bmDjxh;
	private String fhrMc;
	private String fhrDjxh;
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	private String xzqhQc;//行政区划全称
	
	private String callOpenWinFun;
	private String khbm;
	public String getKhbm() {
		return khbm;
	}

	public void setKhbm(String khbm) {
		this.khbm = khbm;
	}

	public QyKhDjxxDomain() {
	}

	//获取
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取名称
	public String getKhmc() {
		return this.khmc;
	}

	//设置名称
	public void setKhmc(String khmc) {
		this.khmc=khmc;
	}

	//获取简称
	public String getKhjc() {
		return this.khjc;
	}

	//设置简称
	public void setKhjc(String khjc) {
		this.khjc=khjc;
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

	//获取行政区划代码
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//设置行政区划代码
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//获取地址
	public String getDz() {
		return this.dz;
	}

	//设置地址
	public void setDz(String dz) {
		this.dz=dz;
	}

	//获取电话
	public String getDh() {
		return this.dh;
	}

	//设置电话
	public void setDh(String dh) {
		this.dh=dh;
	}

	//获取邮编
	public String getYb() {
		return this.yb;
	}

	//设置邮编
	public void setYb(String yb) {
		this.yb=yb;
	}

	//获取负责人
	public String getFzr() {
		return this.fzr;
	}

	//设置负责人
	public void setFzr(String fzr) {
		this.fzr=fzr;
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

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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

	public String getXzqhMc() {
		return xzqhMc;
	}

	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc = xzqhMc;
	}

	public String getXzqhQc() {
		return xzqhQc;
	}

	public void setXzqhQc(String xzqhQc) {
		this.xzqhQc = xzqhQc;
	}

	public String getKhlxDm() {
		return khlxDm;
	}

	public void setKhlxDm(String khlxDm) {
		this.khlxDm = khlxDm;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public String getKhlxMc() {
		return khlxMc;
	}

	public void setKhlxMc(String khlxMc) {
		this.khlxMc = khlxMc;
	}

	public String getYkjsfsMc() {
		return ykjsfsMc;
	}

	public void setYkjsfsMc(String ykjsfsMc) {
		this.ykjsfsMc = ykjsfsMc;
	}

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

	public String getSsjgmc() {
		return ssjgmc;
	}

	public String getCallOpenWinFun() {
		return callOpenWinFun;
	}

	public void setSsjgmc(String ssjgmc) {
		this.ssjgmc = ssjgmc;
	}

	public String getDjjgmc() {
		return djjgmc;
	}

	public void setDjjgmc(String djjgmc) {
		this.djjgmc = djjgmc;
	}

	public String getDjrmc() {
		return djrmc;
	}

	public void setDjrmc(String djrmc) {
		this.djrmc = djrmc;
	}

	public void setCallOpenWinFun(String callOpenWinFun) {
		this.callOpenWinFun = callOpenWinFun;
	}

	public String getXxgxfsDm() {
		return xxgxfsDm;
	}

	public void setXxgxfsDm(String xxgxfsDm) {
		this.xxgxfsDm = xxgxfsDm;
	}

	public String getBmDjxh() {
		return bmDjxh;
	}

	public void setBmDjxh(String bmDjxh) {
		this.bmDjxh = bmDjxh;
	}

	public String getXzqhJc() {
		return xzqhJc;
	}

	public void setXzqhJc(String xzqhJc) {
		this.xzqhJc = xzqhJc;
	}

}
