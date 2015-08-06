package com.cy.common.bo;

import java.io.Serializable;

/**
 * 财务支付登记
 * @author LYY
 *
 */
public class CwZfdj  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String zfDjxh ;				//支付登记序号(SEQ_ZF_DJXH)
	private String ysyfDjxh ;			//应收应付登记序号
	private String yfjsfDm ;			//运费结算方代码
	private String yfjsfDjxh ;			//运费结算方登记序号
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
	private String yxbz ;				//有效标志(Y/N)
	private String bz ;					//备注
	private String djrCzyDjxh ;			//登记人
	private String djrq ;				//登记日期
	private String djJgbm ;				//登记部门
	private String ssJgbm ;				//所属机构
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getDjrq() {
		return djrq;
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
		return lkrq;
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
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getShrCzyDjxh() {
		return shrCzyDjxh;
	}
	public void setShrCzyDjxh(String shrCzyDjxh) {
		this.shrCzyDjxh = shrCzyDjxh;
	}
	public String getShrq() {
		return shrq;
	}
	public void setShrq(String shrq) {
		this.shrq = shrq;
	}
	public String getSkfmc() {
		return skfmc;
	}
	public void setSkfmc(String skfmc) {
		this.skfmc = skfmc;
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
	public String getYsyfDjxh() {
		return ysyfDjxh;
	}
	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getZcflDm() {
		return zcflDm;
	}
	public void setZcflDm(String zcflDm) {
		this.zcflDm = zcflDm;
	}
	public String getZfDjxh() {
		return zfDjxh;
	}
	public void setZfDjxh(String zfDjxh) {
		this.zfDjxh = zfDjxh;
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
		return zzrq;
	}
	public void setZzrq(String zzrq) {
		this.zzrq = zzrq;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	
	
}
