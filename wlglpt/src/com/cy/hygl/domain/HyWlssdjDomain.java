package com.cy.hygl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_WLSSDJ is created by tools.
 * @author HJH
 */

public class HyWlssdjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlssDjxh;                         // 物流损失登记序号(SEQ_WLSS_DJXH)
	private String wlssyyWhXh;                         // 物流损失原因代码
	private String wlssclfsDm;                       // 物流损失处理方式代码
	private Double hjSr;                             // 金额
	private String khDjxh;                           // 客户登记序号
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号
	private String ddDjxh;                           // 订单登记序号
	private String hwmxxh;                           // 货物明细序号
	private String bz;                               // 备注
	private String pcygCzyDjxh;                      // 赔偿员工登记序号
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String yxbz;                             // 有效标志(Y/N)
	private String ssJgbm;                           // 所属机构
	private String djJgbm;                           // 登记部门
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String fhrDjxh;
	private String sl;
	private String zl;
	private String tj;
	private String jsSl;
	private String wlssLybz;
	private String spbcbz;
	public String getSpbcbz() {
		return spbcbz;
	}

	public void setSpbcbz(String spbcbz) {
		this.spbcbz = spbcbz;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getJsSl() {
		return jsSl;
	}

	public void setJsSl(String jsSl) {
		this.jsSl = jsSl;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyWlssdjDomain() {
	}

	//获取物流损失登记序号(SEQ_WLSS_DJXH)
	public String getWlssDjxh() {
		return this.wlssDjxh;
	}

	//设置物流损失登记序号(SEQ_WLSS_DJXH)
	public void setWlssDjxh(String wlssDjxh) {
		this.wlssDjxh=wlssDjxh;
	}
	
	public String getWlssyyWhXh() {
		return wlssyyWhXh;
	}

	public void setWlssyyWhXh(String wlssyyWhXh) {
		this.wlssyyWhXh = wlssyyWhXh;
	}

	//获取物流损失处理方式代码
	public String getWlssclfsDm() {
		return this.wlssclfsDm;
	}

	//设置物流损失处理方式代码
	public void setWlssclfsDm(String wlssclfsDm) {
		this.wlssclfsDm=wlssclfsDm;
	}

	//获取金额
	public Double getHjSr() {
		return this.hjSr;
	}

	//设置金额
	public void setHjSr(Double hjSr) {
		this.hjSr=hjSr;
	}

	//获取客户登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置客户登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车货物序号
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置派车货物序号
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取货物明细序号
	public String getHwmxxh() {
		return this.hwmxxh;
	}

	//设置货物明细序号
	public void setHwmxxh(String hwmxxh) {
		this.hwmxxh=hwmxxh;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取赔偿员工登记序号
	public String getPcygCzyDjxh() {
		return this.pcygCzyDjxh;
	}

	//设置赔偿员工登记序号
	public void setPcygCzyDjxh(String pcygCzyDjxh) {
		this.pcygCzyDjxh=pcygCzyDjxh;
	}

	//获取需要审批标志(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//设置需要审批标志(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//获取文书审批状态代码
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//设置文书审批状态代码
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//获取文书审批序号
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//设置文书审批序号
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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

	public String getWlssLybz() {
		return wlssLybz;
	}

	public void setWlssLybz(String wlssLybz) {
		this.wlssLybz = wlssLybz;
	}
}
