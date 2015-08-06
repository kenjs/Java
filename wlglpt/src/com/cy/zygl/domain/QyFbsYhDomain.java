package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_FBS_YH is created by tools.
 * @author HJH
 */

public class QyFbsYhDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String yhDjxh;                           // 主键
	private String qybm;                             // 企业编码
	private String ssJgbm;                           // 所属公司
	private String fbsDjxh;                          // 所属分包商
	private String mc;                               // 名称
	private String zh;                               // 账号
	private String pwd;                              // 密码
	private String dlyzfsDm;                         // 登录验证方式代码
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrq;                             // 创建日期
	private String cjrCzyDjxh;                       // 创建人
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String qybzStr;                          // 启用标志显示列（即状态列）
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String dlyzfsMc;                         // 登录验证方式名称
	private String pwd1;                             // 确认密码

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QyFbsYhDomain() {
	}

	//获取
	public String getYhDjxh() {
		return this.yhDjxh;
	}

	//设置
	public void setYhDjxh(String yhDjxh) {
		this.yhDjxh=yhDjxh;
	}

	//获取企业编码
	public String getQybm() {
		return this.qybm;
	}

	//设置企业编码
	public void setQybm(String qybm) {
		this.qybm=qybm;
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

	//获取名称
	public String getMc() {
		return this.mc;
	}

	//设置名称
	public void setMc(String mc) {
		this.mc=mc;
	}

	//获取账号
	public String getZh() {
		return this.zh;
	}

	//设置账号
	public void setZh(String zh) {
		this.zh=zh;
	}

	//获取密码
	public String getPwd() {
		return this.pwd;
	}

	//设置密码
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	//获取登录验证方式代码
	public String getDlyzfsDm() {
		return this.dlyzfsDm;
	}

	//设置登录验证方式代码
	public void setDlyzfsDm(String dlyzfsDm) {
		this.dlyzfsDm=dlyzfsDm;
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

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
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

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getDlyzfsMc() {
		return dlyzfsMc;
	}

	public void setDlyzfsMc(String dlyzfsMc) {
		this.dlyzfsMc = dlyzfsMc;
	}

	public String getQybzStr() {
		if ("Y".equals(qybz))
			qybzStr = "启用";
		else
			qybzStr = "停用";
		return qybzStr;
	}

	public void setQybzStr(String qybzStr) {
		this.qybzStr = qybzStr;
	}
}
