package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_RYDJ is created by tools.
 * @author HJH
 */

public class XtglXtsz  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String czyDjxh;                          // 操作员登记序号(SEQ_CZY_DJXH)
	private String mc;                               // 名称
	private String qyZcxh;                           // 企业注册序号(GL_QYZC.QY_ZCXH)
	private String qybm;                             // 企业编码(GL_QYZC.QYBM)
	private String zh;                               // 账号
	private String pwd;                              // 密码
	private String sjhm;                             // 手机号码
	private String sjdh;                             // 手机短号
	private String bgdh;                             // 办公电话
	private String bgdhao;                           // 办公短号
	private String jtdh;                             // 家庭电话
	private String qq;                               // QQ号码
	private String msn;                              // MSN号码
	private String email;                            // EMAIL地址
	private String xtglybz;                          // 系统管理员标志(Y/N)(若为Y则不允许删除)
	private String rylbDm;                           // 人员类别代码
	private String dlyzfsDm;                         // 登录验证方式代码
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String qxJgbm;                           // 

	public XtglXtsz() {
	}

	//获取操作员登记序号(SEQ_CZY_DJXH)
	public String getCzyDjxh() {
		return this.czyDjxh;
	}

	//设置操作员登记序号(SEQ_CZY_DJXH)
	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh=czyDjxh;
	}

	//获取名称
	public String getMc() {
		return this.mc;
	}

	//设置名称
	public void setMc(String mc) {
		this.mc=mc;
	}

	//获取企业注册序号(GL_QYZC.QY_ZCXH)
	public String getQyZcxh() {
		return this.qyZcxh;
	}

	//设置企业注册序号(GL_QYZC.QY_ZCXH)
	public void setQyZcxh(String qyZcxh) {
		this.qyZcxh=qyZcxh;
	}

	//获取企业编码(GL_QYZC.QYBM)
	public String getQybm() {
		return this.qybm;
	}

	//设置企业编码(GL_QYZC.QYBM)
	public void setQybm(String qybm) {
		this.qybm=qybm;
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

	//获取手机号码
	public String getSjhm() {
		return this.sjhm;
	}

	//设置手机号码
	public void setSjhm(String sjhm) {
		this.sjhm=sjhm;
	}

	//获取手机短号
	public String getSjdh() {
		return this.sjdh;
	}

	//设置手机短号
	public void setSjdh(String sjdh) {
		this.sjdh=sjdh;
	}

	//获取办公电话
	public String getBgdh() {
		return this.bgdh;
	}

	//设置办公电话
	public void setBgdh(String bgdh) {
		this.bgdh=bgdh;
	}

	//获取办公短号
	public String getBgdhao() {
		return this.bgdhao;
	}

	//设置办公短号
	public void setBgdhao(String bgdhao) {
		this.bgdhao=bgdhao;
	}

	//获取家庭电话
	public String getJtdh() {
		return this.jtdh;
	}

	//设置家庭电话
	public void setJtdh(String jtdh) {
		this.jtdh=jtdh;
	}

	//获取QQ号码
	public String getQq() {
		return this.qq;
	}

	//设置QQ号码
	public void setQq(String qq) {
		this.qq=qq;
	}

	//获取MSN号码
	public String getMsn() {
		return this.msn;
	}

	//设置MSN号码
	public void setMsn(String msn) {
		this.msn=msn;
	}

	//获取EMAIL地址
	public String getEmail() {
		return this.email;
	}

	//设置EMAIL地址
	public void setEmail(String email) {
		this.email=email;
	}

	//获取系统管理员标志(Y/N)(若为Y则不允许删除)
	public String getXtglybz() {
		return this.xtglybz;
	}

	//设置系统管理员标志(Y/N)(若为Y则不允许删除)
	public void setXtglybz(String xtglybz) {
		this.xtglybz=xtglybz;
	}

	//获取人员类别代码
	public String getRylbDm() {
		return this.rylbDm;
	}

	//设置人员类别代码
	public void setRylbDm(String rylbDm) {
		this.rylbDm=rylbDm;
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
		return this.cjrq;
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
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
		this.xgrq=xgrq;
	}

	//获取
	public String getQxJgbm() {
		return this.qxJgbm;
	}

	//设置
	public void setQxJgbm(String qxJgbm) {
		this.qxJgbm=qxJgbm;
	}
}