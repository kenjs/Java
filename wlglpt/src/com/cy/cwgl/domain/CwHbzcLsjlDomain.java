package com.cy.cwgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysEncodeUtil;
/**
 * THE DOMAIN FOR 财务-货币资产流水记录
 * @author HCM
 *
 */
public class CwHbzcLsjlDomain extends BaseBusinessDomain{
	private static final long serialVersionUID = 1L;
	private String cwbdDjxh;        //财务变动登记序号(SEQ_CW_DJXH)
	private String zcflDm;          //资产分类代码
	private String zcflMc;          //资产分类名称
	private String bdje;            //变动金额
	private String ssJgbm;          //所属部门
	private String yhCshDjxh;       //银行初始化登记序号
	private String bmmc;            //部门名称
	private String yhmc;            //银行名称
	private String zh;              //账号
	private String hm;              //用户名
	private String jbrCzyDjxh;      //经办人
	private String jbrCzyMc;        //经办人名称
	private String rq;              //日期
	private String djJgbm;          //部门
	private String sm;              //说明
	private String bz;
	private String bzmc;
	private String ywxh;
	private String yxbz;
	private String szDjrq;              //收支登记日期
	private String rqQ;
	private String rqZ;
	
	private List<BaseBusinessDomain> dataList; 		//查询列表
	
	public String getCwbdDjxh() {
		return cwbdDjxh;
	}
	public void setCwbdDjxh(String cwbdDjxh) {
		this.cwbdDjxh = cwbdDjxh;
	}	
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}
	public String getZcflMc() {
		return zcflMc;
	}
	public void setZcflMc(String zcflMc) {
		this.zcflMc = zcflMc;
	}
	public String getBdje() {
		return bdje;
	}
	public void setBdje(String bdje) {
		this.bdje = bdje;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getYhCshDjxh() {
		return yhCshDjxh;
	}
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
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
	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}
	
	public String getJbrCzyMc() {
		return jbrCzyMc;
	}
	public void setJbrCzyMc(String jbrCzyMc) {
		this.jbrCzyMc = jbrCzyMc;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBzmc() {
		if("1".equals(this.bz)){
			this.bzmc = "收入";
		}else{
			this.bzmc = "支出";
		}
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
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getSzDjrq() {
		return szDjrq;
	}
	public void setSzDjrq(String szDjrq) {
		this.szDjrq = szDjrq;
	}
	
}
