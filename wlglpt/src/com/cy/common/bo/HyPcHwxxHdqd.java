package com.cy.common.bo;
import java.io.Serializable;
/**
 * The persistent class For HY_PC_HWXX_HDQD is created by tools.
 * @author HJH
 */

public class HyPcHwxxHdqd  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdqdDjxh;                         // 回单清单登记序号(SEQ_HD_DJXH)
	private String qdmc;                             // 清单名称
	private String jszt;                             // 接收状态(0 初始，1 发送，2 接收，3 退回)
	private String fsGsbm;                           // 发送公司编码
	private String jsGsbm;                           // 接收公司编码
	private String bz;                               // 备注
	private String ssJgbm;                           // 所属机构
	private String djJgbm;                           // 登记部门
	private String dbrCzyDjxh;                       // 打包人操作员登记序号
	private String dbrq;                             // 打包日期
	private String yxbz;                             // 有效标志(Y/N)

	public HyPcHwxxHdqd() {
	}

	//获取回单清单登记序号(SEQ_HD_DJXH)
	public String getHdqdDjxh() {
		return this.hdqdDjxh;
	}

	//设置回单清单登记序号(SEQ_HD_DJXH)
	public void setHdqdDjxh(String hdqdDjxh) {
		this.hdqdDjxh=hdqdDjxh;
	}

	//获取清单名称
	public String getQdmc() {
		return this.qdmc;
	}

	//设置清单名称
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//获取接收状态(0 初始，1 发送，2 接收，3 退回)
	public String getJszt() {
		return this.jszt;
	}

	//设置接收状态(0 初始，1 发送，2 接收，3 退回)
	public void setJszt(String jszt) {
		this.jszt=jszt;
	}

	//获取发送公司编码
	public String getFsGsbm() {
		return this.fsGsbm;
	}

	//设置发送公司编码
	public void setFsGsbm(String fsGsbm) {
		this.fsGsbm=fsGsbm;
	}

	//获取接收公司编码
	public String getJsGsbm() {
		return this.jsGsbm;
	}

	//设置接收公司编码
	public void setJsGsbm(String jsGsbm) {
		this.jsGsbm=jsGsbm;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取打包人操作员登记序号
	public String getDbrCzyDjxh() {
		return this.dbrCzyDjxh;
	}

	//设置打包人操作员登记序号
	public void setDbrCzyDjxh(String dbrCzyDjxh) {
		this.dbrCzyDjxh=dbrCzyDjxh;
	}



	public String getDbrq() {
		return dbrq;
	}

	public void setDbrq(String dbrq) {
		this.dbrq = dbrq;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}
}