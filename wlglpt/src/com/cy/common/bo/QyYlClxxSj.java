package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_YL_CLXX_SJ is created by tools.
 * @author HJH
 */

public class QyYlClxxSj  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String clDjxh;                           // 车辆登记序号(SEQ_ZY_DJXH)
	private String xh;                               // 
	private String sjXm;                             // 司机姓名
	private String sjZjhm;                           // 司机身份证号码
	private String sjSjhm;                           // 司机手机号码
	private String sjLxdh;                           // 司机其它联系电话
	private String jszhm;                            // 司机驾驶证号码
	private String whbz;                             // 维护标志(H手工维护，Z自动维护)
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)

	public QyYlClxxSj() {
	}

	//获取车辆登记序号(SEQ_ZY_DJXH)
	public String getClDjxh() {
		return this.clDjxh;
	}

	//设置车辆登记序号(SEQ_ZY_DJXH)
	public void setClDjxh(String clDjxh) {
		this.clDjxh=clDjxh;
	}

	//获取
	public String getXh() {
		return this.xh;
	}

	//设置
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取司机姓名
	public String getSjXm() {
		return this.sjXm;
	}

	//设置司机姓名
	public void setSjXm(String sjXm) {
		this.sjXm=sjXm;
	}

	//获取司机身份证号码
	public String getSjZjhm() {
		return this.sjZjhm;
	}

	//设置司机身份证号码
	public void setSjZjhm(String sjZjhm) {
		this.sjZjhm=sjZjhm;
	}

	//获取司机手机号码
	public String getSjSjhm() {
		return this.sjSjhm;
	}

	//设置司机手机号码
	public void setSjSjhm(String sjSjhm) {
		this.sjSjhm=sjSjhm;
	}

	//获取司机其它联系电话
	public String getSjLxdh() {
		return this.sjLxdh;
	}

	//设置司机其它联系电话
	public void setSjLxdh(String sjLxdh) {
		this.sjLxdh=sjLxdh;
	}

	//获取司机驾驶证号码
	public String getJszhm() {
		return this.jszhm;
	}

	//设置司机驾驶证号码
	public void setJszhm(String jszhm) {
		this.jszhm=jszhm;
	}

	//获取维护标志(H手工维护，Z自动维护)
	public String getWhbz() {
		return this.whbz;
	}

	//设置维护标志(H手工维护，Z自动维护)
	public void setWhbz(String whbz) {
		this.whbz=whbz;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
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