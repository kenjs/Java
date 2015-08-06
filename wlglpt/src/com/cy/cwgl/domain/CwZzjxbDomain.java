package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_ZZJXB is created by tools.
 * @author HJH
 */

public class CwZzjxbDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String zjdbDjxh;                         // 资金调拨登记序号(SEQ_CW_DJXH)
	private String rq;                               // 日期
	private String jsDwDjxh;                         // 接收单位
	private Double jcHj;                             // 结存_合计
	private Double jcXj;                             // 结存_现金
	private Double jcYk;                             // 结存_油卡
	private Double jcCk;                             // 结存_存款
	private Double zfHj;                             // 支付_合计
	private Double zfYfk;                            // 支付_预付款
	private Double zfYk;                             // 支付_余款
	private Double zfBxfy;                           // 支付_报销费用
	private Double byj;                              // 备用金
	private Double zjxq;                             // 资金需求
	private String xbDwDjxh;                         // 下拨单位
	private Double xbje;                             // 下拨金额
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String zfQt;								//支付_其他
	
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String zgsbm;
	
	private String xbDwMc;                         // 下拨单位
	private String jsDwMc;
	private String pageXh;
	private String xbrMc;
	private String xbrq;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwZzjxbDomain() {
	}

	//获取资金调拨登记序号(SEQ_CW_DJXH)
	public String getZjdbDjxh() {
		return this.zjdbDjxh;
	}

	//设置资金调拨登记序号(SEQ_CW_DJXH)
	public void setZjdbDjxh(String zjdbDjxh) {
		this.zjdbDjxh=zjdbDjxh;
	}

	//获取日期
	public String getRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.rq);
		}
		catch(Exception e){
			return this.rq;
		}
	}

	//设置日期
	public void setRq(String rq) {
		this.rq=rq;
	}

	//获取接收单位
	public String getJsDwDjxh() {
		return this.jsDwDjxh;
	}

	//设置接收单位
	public void setJsDwDjxh(String jsDwDjxh) {
		this.jsDwDjxh=jsDwDjxh;
	}

	//获取结存_合计
	public Double getJcHj() {
		return this.jcHj;
	}

	//设置结存_合计
	public void setJcHj(Double jcHj) {
		this.jcHj=jcHj;
	}

	//获取结存_现金
	public Double getJcXj() {
		return this.jcXj;
	}

	//设置结存_现金
	public void setJcXj(Double jcXj) {
		this.jcXj=jcXj;
	}

	//获取结存_油卡
	public Double getJcYk() {
		return this.jcYk;
	}

	//设置结存_油卡
	public void setJcYk(Double jcYk) {
		this.jcYk=jcYk;
	}

	//获取结存_存款
	public Double getJcCk() {
		return this.jcCk;
	}

	//设置结存_存款
	public void setJcCk(Double jcCk) {
		this.jcCk=jcCk;
	}

	//获取支付_合计
	public Double getZfHj() {
		return this.zfHj;
	}

	//设置支付_合计
	public void setZfHj(Double zfHj) {
		this.zfHj=zfHj;
	}

	//获取支付_预付款
	public Double getZfYfk() {
		return this.zfYfk;
	}

	//设置支付_预付款
	public void setZfYfk(Double zfYfk) {
		this.zfYfk=zfYfk;
	}

	//获取支付_余款
	public Double getZfYk() {
		return this.zfYk;
	}

	//设置支付_余款
	public void setZfYk(Double zfYk) {
		this.zfYk=zfYk;
	}

	//获取支付_报销费用
	public Double getZfBxfy() {
		return this.zfBxfy;
	}

	//设置支付_报销费用
	public void setZfBxfy(Double zfBxfy) {
		this.zfBxfy=zfBxfy;
	}

	//获取备用金
	public Double getByj() {
		return this.byj;
	}

	//设置备用金
	public void setByj(Double byj) {
		this.byj=byj;
	}

	//获取资金需求
	public Double getZjxq() {
		return this.zjxq;
	}

	//设置资金需求
	public void setZjxq(Double zjxq) {
		this.zjxq=zjxq;
	}

	//获取下拨单位
	public String getXbDwDjxh() {
		return this.xbDwDjxh;
	}

	//设置下拨单位
	public void setXbDwDjxh(String xbDwDjxh) {
		this.xbDwDjxh=xbDwDjxh;
	}

	//获取下拨金额
	public Double getXbje() {
		return this.xbje;
	}

	//设置下拨金额
	public void setXbje(Double xbje) {
		this.xbje=xbje;
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

	public String getXbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xbrq);
		}
		catch(Exception e){
			return this.xbrq;
		}
	}

	public void setXbrq(String xbrq) {
		this.xbrq=xbrq;
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

	public String getXbrMc() {
		return xbrMc;
	}

	public void setXbrMc(String xbrMc) {
		this.xbrMc = xbrMc;
	}

	public String getPageXh() {
		return pageXh;
	}

	public void setPageXh(String pageXh) {
		this.pageXh = pageXh;
	}

	public String getJsDwMc() {
		return jsDwMc;
	}

	public void setJsDwMc(String jsDwMc) {
		this.jsDwMc = jsDwMc;
	}

	public String getZgsbm() {
		return zgsbm;
	}

	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}

	public String getZfQt() {
		return zfQt;
	}

	public void setZfQt(String zfQt) {
		this.zfQt = zfQt;
	}

	public String getXbDwMc() {
		return xbDwMc;
	}

	public void setXbDwMc(String xbDwMc) {
		this.xbDwMc = xbDwMc;
	}
}
