package com.cy.zygl.domain;

import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_YL_CLXX is created by tools.
 * @author HJH
 */

public class QyYlClxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String clDjxh;                           // 车辆登记序号(SEQ_ZY_DJXH)
	private String czXm;                             // 车主_姓名
	private String czZjlxDm;                         // 车主_证件类型代码
	private String czZjhm;                           // 车主_证件号码
	private String czLxdh;                           // 车主_联系电话
	private String gcbz;                             // 挂车标志（Y/N）
	private String cyrClDjxh ;						 // 车辆登记序号
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

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String zjlxMc;

	private String xh;                               // 
	private String sjXm;                             // 司机姓名
	private String sjZjhm;                           // 司机身份证号码
	private String sjSjhm;                           // 司机手机号码
	private String sjLxdh;                           // 司机其它联系电话
	private String jszhm;                            // 司机驾驶证号码
	private String whbz;                             // 维护标志(H手工维护，Z自动维护)
	private List<String> xhs;
	private String xxgxfsDm;
	private String xxgxfsMc;
	
	
	private List<String> sjXms;                             // 司机姓名
	private List<String> sjZjhms;                           // 司机身份证号码
	private List<String> sjSjhms;                           // 司机手机号码
	private List<String> sjLxdhs;                           // 司机其它联系电话
	private List<String> jszhms;                            // 司机驾驶证号码
	
	private String sjInfo;
	private String callOpenWinFun ;
	
	private String pcfsDm;
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

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<QyYlClxxDomain> sjList;
	
	public QyYlClxxDomain() {
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
		return clhm;
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

	public String getZjlxMc() {
		return zjlxMc;
	}

	public void setZjlxMc(String zjlxMc) {
		this.zjlxMc = zjlxMc;
	}

	public List<QyYlClxxDomain> getSjList() {
		if(sjList == null){
			sjList = new ArrayList<QyYlClxxDomain>();
		}
		return sjList;
	}

	public void setSjList(List<QyYlClxxDomain> sjList) {
		this.sjList = sjList;
	}

	public String getJszhm() {
		return jszhm;
	}

	public void setJszhm(String jszhm) {
		this.jszhm = jszhm;
	}

	public String getSjLxdh() {
		return sjLxdh;
	}

	public void setSjLxdh(String sjLxdh) {
		this.sjLxdh = sjLxdh;
	}

	public String getSjSjhm() {
		return sjSjhm;
	}

	public void setSjSjhm(String sjSjhm) {
		this.sjSjhm = sjSjhm;
	}

	public String getSjXm() {
		return sjXm;
	}

	public void setSjXm(String sjXm) {
		this.sjXm = sjXm;
	}

	public String getSjZjhm() {
		return sjZjhm;
	}

	public void setSjZjhm(String sjZjhm) {
		this.sjZjhm = sjZjhm;
	}

	public String getWhbz() {
		return whbz;
	}

	public void setWhbz(String whbz) {
		this.whbz = whbz;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSjInfo() {
		return sjInfo;
	}

	public void setSjInfo(String sjInfo) {
		this.sjInfo = sjInfo;
	}

	public String getXxgxfsDm() {
		return xxgxfsDm;
	}

	public void setXxgxfsDm(String xxgxfsDm) {
		this.xxgxfsDm = xxgxfsDm;
	}

	public String getXxgxfsMc() {
		return xxgxfsMc;
	}

	public void setXxgxfsMc(String xxgxfsMc) {
		this.xxgxfsMc = xxgxfsMc;
	}

	public String getPcfsDm() {
		return pcfsDm;
	}

	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
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

	public List<String> getXhs() {
		return xhs;
	}

	public void setXhs(List<String> xhs) {
		this.xhs = xhs;
	}

	public String getCallOpenWinFun() {
		return callOpenWinFun;
	}

	public void setCallOpenWinFun(String callOpenWinFun) {
		this.callOpenWinFun = callOpenWinFun;
	}

	public String getCyrClDjxh() {
		return cyrClDjxh;
	}

	public void setCyrClDjxh(String cyrClDjxh) {
		this.cyrClDjxh = cyrClDjxh;
	}

	public List<String> getSjXms() {
		return sjXms;
	}

	public void setSjXms(List<String> sjXms) {
		this.sjXms = sjXms;
	}

	public List<String> getSjZjhms() {
		return sjZjhms;
	}

	public void setSjZjhms(List<String> sjZjhms) {
		this.sjZjhms = sjZjhms;
	}

	public List<String> getSjSjhms() {
		return sjSjhms;
	}

	public void setSjSjhms(List<String> sjSjhms) {
		this.sjSjhms = sjSjhms;
	}

	public List<String> getSjLxdhs() {
		return sjLxdhs;
	}

	public void setSjLxdhs(List<String> sjLxdhs) {
		this.sjLxdhs = sjLxdhs;
	}

	public List<String> getJszhms() {
		return jszhms;
	}

	public void setJszhms(List<String> jszhms) {
		this.jszhms = jszhms;
	}

}
