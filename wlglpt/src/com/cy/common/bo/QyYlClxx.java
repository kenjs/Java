package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For QY_YL_CLXX is created by tools.
 * @author HJH
 */

public class QyYlClxx  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String clDjxh;                           // 车辆登记序号(SEQ_ZY_DJXH)
	private String czXm;                             // 车主_姓名
	private String czZjlxDm;                         // 车主_证件类型代码
	private String czZjhm;                           // 车主_证件号码
	private String czLxdh;                           // 车主_联系电话
	private String gcbz;                             // 挂车标志（Y/N）
	private String clhm;                             // 车辆号码
	private String clsxDm;                           // 车辆属性代码	
	private String thclbz;                           // 提货车辆标志(Y/N)
	private String ysclbz;                           // 运输车辆标志(Y/N)
	private String psclbz;                           // 配送车辆标识(Y/N)
	private String clxhwhDjxh;                       // 车辆型号维护序号
	private String bz;                               // 备注
	private String djrCzyDjxh;                       // 登记人
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String xxgxfsDm;
	private String cz;
	private String tj;
	private String cd;
	private String kd;
	private String gd;
	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getKd() {
		return kd;
	}

	public void setKd(String kd) {
		this.kd = kd;
	}

	public String getGd() {
		return gd;
	}

	public void setGd(String gd) {
		this.gd = gd;
	}

	public QyYlClxx() {
	}

	//获取车辆登记序号(SEQ_ZY_DJXH)
	public String getClDjxh() {
		return this.clDjxh;
	}

	//设置车辆登记序号(SEQ_ZY_DJXH)
	public void setClDjxh(String clDjxh) {
		this.clDjxh=clDjxh;
	}

	//获取车主_姓名
	public String getCzXm() {
		return this.czXm;
	}

	//设置车主_姓名
	public void setCzXm(String czXm) {
		this.czXm=czXm;
	}

	//获取车主_证件类型代码
	public String getCzZjlxDm() {
		return this.czZjlxDm;
	}

	//设置车主_证件类型代码
	public void setCzZjlxDm(String czZjlxDm) {
		this.czZjlxDm=czZjlxDm;
	}

	//获取车主_证件号码
	public String getCzZjhm() {
		return this.czZjhm;
	}

	//设置车主_证件号码
	public void setCzZjhm(String czZjhm) {
		this.czZjhm=czZjhm;
	}

	//获取车主_联系电话
	public String getCzLxdh() {
		return this.czLxdh;
	}

	//设置车主_联系电话
	public void setCzLxdh(String czLxdh) {
		this.czLxdh=czLxdh;
	}

	//获取车辆号码
	public String getClhm() {
		return this.clhm;
	}

	//设置车辆号码
	public void setClhm(String clhm) {
		this.clhm=clhm;
	}

	//获取车辆属性代码
	public String getClsxDm() {
		return this.clsxDm;
	}

	//设置车辆属性代码
	public void setClsxDm(String clsxDm) {
		this.clsxDm=clsxDm;
	}

	//获取提货车辆标志(Y/N)
	public String getThclbz() {
		return this.thclbz;
	}

	//设置提货车辆标志(Y/N)
	public void setThclbz(String thclbz) {
		this.thclbz=thclbz;
	}

	//获取运输车辆标志(Y/N)
	public String getYsclbz() {
		return this.ysclbz;
	}

	//设置运输车辆标志(Y/N)
	public void setYsclbz(String ysclbz) {
		this.ysclbz=ysclbz;
	}

	//获取配送车辆标识(Y/N)
	public String getPsclbz() {
		return this.psclbz;
	}

	//设置配送车辆标识(Y/N)
	public void setPsclbz(String psclbz) {
		this.psclbz=psclbz;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
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
		return this.djrq;
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

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
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

	public String getXxgxfsDm() {
		return xxgxfsDm;
	}

	public void setXxgxfsDm(String xxgxfsDm) {
		this.xxgxfsDm = xxgxfsDm;
	}

	public String getGcbz() {
		return gcbz;
	}

	public void setGcbz(String gcbz) {
		this.gcbz = gcbz;
	}

	public String getClxhwhDjxh() {
		return clxhwhDjxh;
	}

	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh = clxhwhDjxh;
	}
}