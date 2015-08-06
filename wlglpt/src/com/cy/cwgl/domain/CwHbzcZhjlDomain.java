package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_HBZC_ZHJL is created by tools.
 * @author HJH
 */

public class CwHbzcZhjlDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private String oldZcflDm;                        // 原-资产分类代码
	private String oldYhCshDjxh;                     // 原-银行初始化登记序号
	private String newZcflDm;                        // 目标-资产分类代码
	private String newYhCshDjxh;                     // 目标-银行初始化登记序号
	private String zhje;                             // 转换金额
	private String pzh;                              // 凭证号
	private String bzsm;                             // 备注说明
	private String yxbz;                             // 有效标志(Y/N)
	private String djrCzyDjxh;                       // 登记人
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	
	private String oldZcflMc;
	private String oldYhCshMc;
	private String oldYe;
	private String newZcflMc;
	private String newYhCshMc;
	private String newYe;
	
	private String cwbdDjxh;                         // 财务变动登记序号(SEQ_CW_DJXH)
	private String sm;                               // 说明
	private String jbrCzyDjxh;                       // 经办人
	private String rq;                               // 日期   
	private String jbrCzyMc;
	private String yhDjxh;
	private String zcflDm;                           // 资产分类代码
	private String yhCshDjxh;                        // 银行初始化登记序号
	private String je;                               // 金额
	private String bdJe;
	private String bz;
	private String bzmc;
	private String ywxh;

	
	private String oldHm;
	private String oldZh;
	private String newHm;
	
	/********查询条件*********/
	private String lb;
	private String yhzh;
	private String rqQ;
	private String rqZ;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwHbzcZhjlDomain() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取原-资产分类代码
	public String getOldZcflDm() {
		return this.oldZcflDm;
	}

	//设置原-资产分类代码
	public void setOldZcflDm(String oldZcflDm) {
		this.oldZcflDm=oldZcflDm;
	}

	//获取原-银行初始化登记序号
	public String getOldYhCshDjxh() {
		return this.oldYhCshDjxh;
	}

	//设置原-银行初始化登记序号
	public void setOldYhCshDjxh(String oldYhCshDjxh) {
		this.oldYhCshDjxh=oldYhCshDjxh;
	}

	//获取目标-资产分类代码
	public String getNewZcflDm() {
		return this.newZcflDm;
	}

	//设置目标-资产分类代码
	public void setNewZcflDm(String newZcflDm) {
		this.newZcflDm=newZcflDm;
	}

	//获取目标-银行初始化登记序号
	public String getNewYhCshDjxh() {
		return this.newYhCshDjxh;
	}

	//设置目标-银行初始化登记序号
	public void setNewYhCshDjxh(String newYhCshDjxh) {
		this.newYhCshDjxh=newYhCshDjxh;
	}

	//获取转换金额
	public String getZhje() {
		return this.zhje;
	}

	//设置转换金额
	public void setZhje(String zhje) {
		this.zhje=zhje;
	}

	//获取凭证号
	public String getPzh() {
		return this.pzh;
	}

	//设置凭证号
	public void setPzh(String pzh) {
		this.pzh=pzh;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取登记人
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置登记人
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取登记日期
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//设置登记日期
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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

	public String getOldZcflMc() {
		return oldZcflMc;
	}

	public void setOldZcflMc(String oldZcflMc) {
		this.oldZcflMc = oldZcflMc;
	}

	public String getOldYhCshMc() {
		return oldYhCshMc;
	}

	public void setOldYhCshMc(String oldYhCshMc) {
		this.oldYhCshMc = oldYhCshMc;
	}

	public String getOldYe() {
		return oldYe;
	}

	public void setOldYe(String oldYe) {
		this.oldYe = oldYe;
	}

	public String getNewZcflMc() {
		return newZcflMc;
	}

	public void setNewZcflMc(String newZcflMc) {
		this.newZcflMc = newZcflMc;
	}

	public String getNewYhCshMc() {
		return newYhCshMc;
	}

	public void setNewYhCshMc(String newYhCshMc) {
		this.newYhCshMc = newYhCshMc;
	}

	public String getNewYe() {
		return newYe;
	}

	public void setNewYe(String newYe) {
		this.newYe = newYe;
	}

	public String getCwbdDjxh() {
		return cwbdDjxh;
	}

	public void setCwbdDjxh(String cwbdDjxh) {
		this.cwbdDjxh = cwbdDjxh;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}

	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getJbrCzyMc() {
		return jbrCzyMc;
	}

	public void setJbrCzyMc(String jbrCzyMc) {
		this.jbrCzyMc = jbrCzyMc;
	}

	public String getZcflDm() {
		return zcflDm;
	}

	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}

	public String getYhCshDjxh() {
		return yhCshDjxh;
	}

	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getRqQ() {
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getYhDjxh() {
		return yhDjxh;
	}

	public void setYhDjxh(String yhDjxh) {
		this.yhDjxh = yhDjxh;
	}

	public String getBdJe() {
		return bdJe;
	}

	public void setBdJe(String bdJe) {
		this.bdJe = bdJe;
	}

	public String getOldHm() {
		return oldHm;
	}

	public void setOldHm(String oldHm) {
		this.oldHm = oldHm;
	}

	public String getOldZh() {
		return oldZh;
	}

	public void setOldZh(String oldZh) {
		this.oldZh = oldZh;
	}

	public String getNewHm() {
		return newHm;
	}

	public void setNewHm(String newHm) {
		this.newHm = newHm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

}
