package com.cy.common.bo;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class For HY_PC is created by tools.
 * @author HJH
 */

public class HyPc  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private String pcdh;                             // 派车单号
	private String clsxDm;                           // 车辆属性代码
	private String cyrClhmXh ;						 //车辆登记序号
	private String cyrCzxm;                          // 承运人_车主姓名
	private String cyrClhm;                          // 承运人_车辆号码
	private String cyrGchm;                          // 承运人_挂车号码
	private String cyrSjxm;                          // 承运人_司机姓名
	private String cyrSjsfz;                         // 承运人_司机身份证
	private String cyrSjsjhm;                        // 承运人_司机手机号码
	private String cyrQtlxdh;                        // 承运人_其它联系电话
	private String cyrQtlxdh2;                        // 承运人_其它联系电话
	private Double yfHj;                             // 运费_总运费
	private Double yfYfyf;                           // 运费_预付运费
	private Double yfYj;                             // 运费_押金
	private Double yfXxf;                            // 运费_信息费
	private Double yfSjs;                            // 运费_司机收
	private Double yfHdyf;                           // 运费_货到运费
	private Double yfHdf;                            // 运费_回单付
	private Double yfZjf;							 // 运费_中介费
	private String yfjsfDm;                          // 运费结算方代码
	private String xxzjDjxh;                         // 信息中介登记序号
	private String zrbmDm;                           // 转入部门代码
	private String zrbmDjxh;                         // 转入部门登记序号
	private String bz;                               // 备注
	private String pcrCzyDjxh;                       // 派车人
	private Date pcrq;                             // 派车日期
	private String pcJgbm;                           // 派车部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期
	private String pcfsDm;                           // 派车方式代码
	private String ysfsDm;                           // 运输方式代码
	private String zcfsDm;                           // 装车方式代码
	private String spbz;
	private Date dzrq;								 // 到站日期

	public HyPc() {
	}

	//获取派车登记序号(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车单号
	public String getPcdh() {
		return this.pcdh;
	}

	//设置派车单号
	public void setPcdh(String pcdh) {
		this.pcdh=pcdh;
	}

	//获取车辆属性代码
	public String getClsxDm() {
		return this.clsxDm;
	}

	//设置车辆属性代码
	public void setClsxDm(String clsxDm) {
		this.clsxDm=clsxDm;
	}

	//获取承运人_车主姓名
	public String getCyrCzxm() {
		return this.cyrCzxm;
	}

	//设置承运人_车主姓名
	public void setCyrCzxm(String cyrCzxm) {
		this.cyrCzxm=cyrCzxm;
	}

	//获取承运人_车辆号码
	public String getCyrClhm() {
		return this.cyrClhm;
	}

	//设置承运人_车辆号码
	public void setCyrClhm(String cyrClhm) {
		this.cyrClhm=cyrClhm;
	}

	//获取承运人_挂车号码
	public String getCyrGchm() {
		return this.cyrGchm;
	}

	//设置承运人_挂车号码
	public void setCyrGchm(String cyrGchm) {
		this.cyrGchm=cyrGchm;
	}

	//获取承运人_司机姓名
	public String getCyrSjxm() {
		return this.cyrSjxm;
	}

	//设置承运人_司机姓名
	public void setCyrSjxm(String cyrSjxm) {
		this.cyrSjxm=cyrSjxm;
	}

	//获取承运人_司机身份证
	public String getCyrSjsfz() {
		return this.cyrSjsfz;
	}

	//设置承运人_司机身份证
	public void setCyrSjsfz(String cyrSjsfz) {
		this.cyrSjsfz=cyrSjsfz;
	}

	//获取承运人_司机手机号码
	public String getCyrSjsjhm() {
		return this.cyrSjsjhm;
	}

	//设置承运人_司机手机号码
	public void setCyrSjsjhm(String cyrSjsjhm) {
		this.cyrSjsjhm=cyrSjsjhm;
	}

	//获取承运人_其它联系电话
	public String getCyrQtlxdh() {
		return this.cyrQtlxdh;
	}

	//设置承运人_其它联系电话
	public void setCyrQtlxdh(String cyrQtlxdh) {
		this.cyrQtlxdh=cyrQtlxdh;
	}

	//获取运费_总运费
	public Double getYfHj() {
		return this.yfHj;
	}

	//设置运费_总运费
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	//获取运费_预付运费
	public Double getYfYfyf() {
		return this.yfYfyf;
	}

	//设置运费_预付运费
	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf=yfYfyf;
	}

	//获取运费_押金
	public Double getYfYj() {
		return this.yfYj;
	}

	//设置运费_押金
	public void setYfYj(Double yfYj) {
		this.yfYj=yfYj;
	}

	//获取运费_信息费
	public Double getYfXxf() {
		return this.yfXxf;
	}

	//设置运费_信息费
	public void setYfXxf(Double yfXxf) {
		this.yfXxf=yfXxf;
	}

	//获取运费_司机收
	public Double getYfSjs() {
		return this.yfSjs;
	}

	//设置运费_司机收
	public void setYfSjs(Double yfSjs) {
		this.yfSjs=yfSjs;
	}

	//获取运费_货到运费
	public Double getYfHdyf() {
		return this.yfHdyf;
	}

	//设置运费_货到运费
	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf=yfHdyf;
	}

	//获取运费_回单付
	public Double getYfHdf() {
		return this.yfHdf;
	}

	//设置运费_回单付
	public void setYfHdf(Double yfHdf) {
		this.yfHdf=yfHdf;
	}

	public Double getYfZjf() {
		return yfZjf;
	}

	public void setYfZjf(Double yfZjf) {
		this.yfZjf = yfZjf;
	}

	//获取运费结算方代码
	public String getYfjsfDm() {
		return this.yfjsfDm;
	}

	//设置运费结算方代码
	public void setYfjsfDm(String yfjsfDm) {
		this.yfjsfDm=yfjsfDm;
	}

	//获取信息中介登记序号
	public String getXxzjDjxh() {
		return this.xxzjDjxh;
	}

	//设置信息中介登记序号
	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh=xxzjDjxh;
	}

	//获取转入部门代码
	public String getZrbmDm() {
		return this.zrbmDm;
	}

	//设置转入部门代码
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm=zrbmDm;
	}

	//获取转入部门登记序号
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//设置转入部门登记序号
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取派车人
	public String getPcrCzyDjxh() {
		return this.pcrCzyDjxh;
	}

	//设置派车人
	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh=pcrCzyDjxh;
	}

	//获取派车日期
	public Date getPcrq() {
		return this.pcrq;
	}

	//设置派车日期
	public void setPcrq(Date pcrq) {
		this.pcrq=pcrq;
	}

	//获取派车部门
	public String getPcJgbm() {
		return this.pcJgbm;
	}

	//设置派车部门
	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm=pcJgbm;
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
	public Date getCjrq() {
		return this.cjrq;
	}

	//设置创建日期
	public void setCjrq(Date cjrq) {
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
	public Date getXgrq() {
		return this.xgrq;
	}

	//设置修改日期
	public void setXgrq(Date xgrq) {
		this.xgrq=xgrq;
	}

	//获取派车方式代码
	public String getPcfsDm() {
		return this.pcfsDm;
	}

	//设置派车方式代码
	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm=pcfsDm;
	}

	//获取运输方式代码
	public String getYsfsDm() {
		return this.ysfsDm;
	}

	//设置运输方式代码
	public void setYsfsDm(String ysfsDm) {
		this.ysfsDm=ysfsDm;
	}

	//获取装车方式代码
	public String getZcfsDm() {
		return this.zcfsDm;
	}

	//设置装车方式代码
	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm=zcfsDm;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getCyrClhmXh() {
		return cyrClhmXh;
	}

	public void setCyrClhmXh(String cyrClhmXh) {
		this.cyrClhmXh = cyrClhmXh;
	}

	public String getCyrQtlxdh2() {
		return cyrQtlxdh2;
	}

	public void setCyrQtlxdh2(String cyrQtlxdh2) {
		this.cyrQtlxdh2 = cyrQtlxdh2;
	}

	public Date getDzrq() {
		return dzrq;
	}

	public void setDzrq(Date dzrq) {
		this.dzrq = dzrq;
	}

	
}