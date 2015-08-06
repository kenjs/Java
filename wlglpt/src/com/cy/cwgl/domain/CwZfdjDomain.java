package com.cy.cwgl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;


//财务发票开票登记
public class CwZfdjDomain extends BaseBusinessDomain{

	private static final long serialVersionUID = 1L;
	private String ysyfDjxh ;		//应收应付登记序号(SEQ_YSYF_DJXH)
	private String yfjsfDm ;		//运费结算方代码
	private String yfjsfDjxh ;		//运费结算方登记序号
	private String kmdlDm ;			//科目大类代码
	private String kmxlDm ;			//科目小类代码
	private String zdyKmzlDm ;		//自定义科目子类代码
	private String ysyflyDm ;		//应收应付来源代码
	private String ywDjxh ;			//业务登记序号(视具体业务对应不同表)
	private String csrq ;			//产生日期
	private String csrqQ ;			//产生日期起
	private String csrqZ ;			//产生日期止
	private String ysyfztDm ;		//应收应付状态代码
	private Double ysfJe ;			//应收付金额
	private Double yisfJe ;			//已收付金额
	private Double wsfJe ;			//未收付金额
	private String sm ;				//说明
	private String yxbz ;			//有效标志(Y/N)
	private String djJgbm ;			//登记部门
	private String ssJgbm ;			//所属机构
	private String zt ;				//支付
	private String zgsbm;
	
	private String zfDjxh ;				//支付登记序号(SEQ_ZF_DJXH)
	private String skfmc ;				//收款方名称
	private Double je ;					//金额
	private String rq ;					//日期
	private String zffsDm ;				//支付方式代码
	private String zcflDm ;				//资产分类代码
	private String yhCshDjxh ;			//银行初始化登记序号
	private String lkr ;				//领款人
	private String lkrZjlxDm ;			//领款人证件类型代码
	private String lkrZjhm ;			//领款人证件号码
	private String lkrq ;				//领款日期
	private String yhmc ;				//银行名称
	private String hm ;					//户名
	private String zh ;					//账号
	private String zzrq ;				//转账日期
	private String jbrCzyDjxh ;			//经办人
	private String shrCzyDjxh ;			//审核人
	private String shrq ;				//审核日期
	private String bz ;					//备注
	private String djrCzyDjxh ;			//登记人
	private String djrq ;				//登记日期
	
	private String rtnCode ;			//返回结果（0 ok，<> 0 error）
	private String errMesge ;			//错误时，返回的错误信息
	private String yfjsfMc ;			//运费结算方名称
	private String yfjsfDjmc ;			//运费结算方登记名称
	private String ysyfztMc ;			//应付应收状态名称
	private String kmdlMc ;				//科目大类名称
	private String kmxlMc ;				//科目小类名称
	private String ysyflyMc ;			//应收应付来源名称
	
	private String zffsMc ;				//支付方式名称
	private String zcflMc ;				//资产分类名称
	private String jbrCzyMc ;			//经办人操作员名称
	private String djrCzyMc ;			//登记人操作员名称
	private String djJgmc ;				//登记监管部门名称
	private String ssJgmc ;				//所属监管部门名称
	private List<String> zfDjxhs ;		
	
	private List<String> ysyfDjxhs ;
	private String mcStr;
	private String lbStr;
	private String ddbh;	
	private List<CwZfdjDomain> yfjsfDmList ;
	private List<CwZfdjDomain> yfjsfMcList;
	
	private List<BaseBusinessDomain> dataList ;
	public List<BaseBusinessDomain> getDataList() {
		if(dataList == null){
			dataList = new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getKmdlDm() {
		return kmdlDm;
	}
	public void setKmdlDm(String kmdlDm) {
		this.kmdlDm = kmdlDm;
	}
	public String getKmxlDm() {
		return kmxlDm;
	}
	public void setKmxlDm(String kmxlDm) {
		this.kmxlDm = kmxlDm;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getYfjsfDjxh() {
		return yfjsfDjxh;
	}
	public void setYfjsfDjxh(String yfjsfDjxh) {
		this.yfjsfDjxh = yfjsfDjxh;
	}
	public String getYfjsfDm() {
		return yfjsfDm;
	}
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm = yfjsfDm;
	}

	public String getYsyfDjxh() {
		return ysyfDjxh;
	}
	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}
	public String getYsyflyDm() {
		return ysyflyDm;
	}
	public void setYsyflyDm(String ysyflyDm) {
		this.ysyflyDm = ysyflyDm;
	}
	public String getYsyfztDm() {
		return ysyfztDm;
	}
	public void setYsyfztDm(String ysyfztDm) {
		this.ysyfztDm = ysyfztDm;
	}
	public String getYwDjxh() {
		return ywDjxh;
	}
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getZdyKmzlDm() {
		return zdyKmzlDm;
	}
	public void setZdyKmzlDm(String zdyKmzlDm) {
		this.zdyKmzlDm = zdyKmzlDm;
	}
	public String getCsrqQ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.csrqQ);
		}
		catch(Exception e){
			return this.csrqQ;
		}
	}
	public void setCsrqQ(String csrqQ) {
		this.csrqQ = csrqQ;
	}
	public String getCsrqZ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.csrqZ);
		}
		catch(Exception e){
			return this.csrqZ;
		}
	}
	public void setCsrqZ(String csrqZ) {
		this.csrqZ = csrqZ;
	}
	public String getCsrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.csrq);
		}
		catch(Exception e){
			return this.csrq;
		}
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZfDjxh() {
		return zfDjxh;
	}
	public void setZfDjxh(String zfDjxh) {
		this.zfDjxh = zfDjxh;
	}
	public String getSkfmc() {
		return skfmc;
	}
	public void setSkfmc(String skfmc) {
		this.skfmc = skfmc;
	}
	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
	}
	public String getJbrCzyDjxh() {
		return jbrCzyDjxh;
	}
	public void setJbrCzyDjxh(String jbrCzyDjxh) {
		this.jbrCzyDjxh = jbrCzyDjxh;
	}
	public String getLkr() {
		return lkr;
	}
	public void setLkr(String lkr) {
		this.lkr = lkr;
	}
	public String getLkrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.lkrq);
		}
		catch(Exception e){
			return this.lkrq;
		}
	}
	public void setLkrq(String lkrq) {
		this.lkrq = lkrq;
	}
	public String getLkrZjhm() {
		return lkrZjhm;
	}
	public void setLkrZjhm(String lkrZjhm) {
		this.lkrZjhm = lkrZjhm;
	}
	public String getLkrZjlxDm() {
		return lkrZjlxDm;
	}
	public void setLkrZjlxDm(String lkrZjlxDm) {
		this.lkrZjlxDm = lkrZjlxDm;
	}
	public String getShrCzyDjxh() {
		return shrCzyDjxh;
	}
	public void setShrCzyDjxh(String shrCzyDjxh) {
		this.shrCzyDjxh = shrCzyDjxh;
	}
	public String getShrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.shrq);
		}
		catch(Exception e){
			return this.shrq;
		}
	}
	public void setShrq(String shrq) {
		this.shrq = shrq;
	}
	public String getYhCshDjxh() {
		return yhCshDjxh;
	}
	public void setYhCshDjxh(String yhCshDjxh) {
		this.yhCshDjxh = yhCshDjxh;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}
	public String getZffsDm() {
		return zffsDm;
	}
	public void setZffsDm(String zffsDm) {
		this.zffsDm = zffsDm;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getZzrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.zzrq);
		}
		catch(Exception e){
			return this.zzrq;
		}
	}
	public void setZzrq(String zzrq) {
		this.zzrq = zzrq;
	}
	public String getErrMesge() {
		return errMesge;
	}
	public void setErrMesge(String errMesge) {
		this.errMesge = errMesge;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getYfjsfMc() {
		return yfjsfMc;
	}
	public void setYfjsfMc(String yfjsfMc) {
		this.yfjsfMc = yfjsfMc;
	}
	public String getYfjsfDjmc() {
		return yfjsfDjmc;
	}
	public void setYfjsfDjmc(String yfjsfDjmc) {
		this.yfjsfDjmc = yfjsfDjmc;
	}
	public String getYsyfztMc() {
		return ysyfztMc;
	}
	public void setYsyfztMc(String ysyfztMc) {
		this.ysyfztMc = ysyfztMc;
	}
	public String getKmdlMc() {
		return kmdlMc;
	}
	public void setKmdlMc(String kmdlMc) {
		this.kmdlMc = kmdlMc;
	}
	public String getKmxlMc() {
		return kmxlMc;
	}
	public void setKmxlMc(String kmxlMc) {
		this.kmxlMc = kmxlMc;
	}
	public List<String> getZfDjxhs() {
		return zfDjxhs;
	}
	public void setZfDjxhs(List<String> zfDjxhs) {
		this.zfDjxhs = zfDjxhs;
	}
	public String getYsyflyMc() {
		return ysyflyMc;
	}
	public void setYsyflyMc(String ysyflyMc) {
		this.ysyflyMc = ysyflyMc;
	}
	public String getZcflMc() {
		return zcflMc;
	}
	public void setZcflMc(String zcflMc) {
		this.zcflMc = zcflMc;
	}
	public String getZffsMc() {
		return zffsMc;
	}
	public void setZffsMc(String zffsMc) {
		this.zffsMc = zffsMc;
	}
	public String getDjrCzyMc() {
		return djrCzyMc;
	}
	public void setDjrCzyMc(String djrCzyMc) {
		this.djrCzyMc = djrCzyMc;
	}
	public String getJbrCzyMc() {
		return jbrCzyMc;
	}
	public void setJbrCzyMc(String jbrCzyMc) {
		this.jbrCzyMc = jbrCzyMc;
	}
	public String getDjJgmc() {
		return djJgmc;
	}
	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}
	public List<CwZfdjDomain> getYfjsfDmList() {
		if(yfjsfDmList == null){
			yfjsfDmList = new ArrayList<CwZfdjDomain>();
		}
		return yfjsfDmList;
	}
	public void setYfjsfDmList(List<CwZfdjDomain> yfjsfDmList) {
		this.yfjsfDmList = yfjsfDmList;
	}
	public List<CwZfdjDomain> getYfjsfMcList() {
		if(yfjsfMcList == null){
			yfjsfMcList = new ArrayList<CwZfdjDomain>();
		}
		return yfjsfMcList;
	}
	public void setYfjsfMcList(List<CwZfdjDomain> yfjsfMcList) {
		this.yfjsfMcList = yfjsfMcList;
	}
	public String getMcStr() {
		return mcStr;
	}
	public void setMcStr(String mcStr) {
		this.mcStr = mcStr;
	}
	public String getLbStr() {
		return lbStr;
	}
	public void setLbStr(String lbStr) {
		this.lbStr = lbStr;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Double getYsfJe() {
		return ysfJe;
	}
	public void setYsfJe(Double ysfJe) {
		this.ysfJe = ysfJe;
	}
	public Double getYisfJe() {
		return yisfJe;
	}
	public void setYisfJe(Double yisfJe) {
		this.yisfJe = yisfJe;
	}
	public Double getWsfJe() {
		return wsfJe;
	}
	public void setWsfJe(Double wsfJe) {
		this.wsfJe = wsfJe;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public List<String> getYsyfDjxhs() {
		return ysyfDjxhs;
	}
	public void setYsyfDjxhs(List<String> ysyfDjxhs) {
		this.ysyfDjxhs = ysyfDjxhs;
	}
	public String getZgsbm() {
		return zgsbm;
	}
	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}
	
}
