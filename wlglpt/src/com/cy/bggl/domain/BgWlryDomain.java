package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_WLRY.
 * @author HaoY
 */

public class BgWlryDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wlryDjxh;                         // 外联人员登记序号(SEQ_BG_DJXH)
	private String wlryFlxh;                         // 外联人员分类序号
	private String xm;                               // 姓名
	private String dz;                               // 地址
	private String dh;                               // 电话
	private String cz;                               // 传真
	private String sj;                               // 手机
	private String wz;                               // 网址
	private String yb;                               // 邮编
	private String dy;                               // 电邮
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	
	private String flmc;
	private String jgbm;
	
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public BgWlryDomain() {
	}

	//获取外联人员登记序号(SEQ_BG_DJXH)
	public String getWlryDjxh() {
		return this.wlryDjxh;
	}

	//设置外联人员登记序号(SEQ_BG_DJXH)
	public void setWlryDjxh(String wlryDjxh) {
		this.wlryDjxh=wlryDjxh;
	}

	//获取外联人员分类序号
	public String getWlryFlxh() {
		return this.wlryFlxh;
	}

	//设置外联人员分类序号
	public void setWlryFlxh(String wlryFlxh) {
		this.wlryFlxh=wlryFlxh;
	}

	//获取姓名
	public String getXm() {
		return this.xm;
	}

	//设置姓名
	public void setXm(String xm) {
		this.xm=xm;
	}

	//获取地址
	public String getDz() {
		return this.dz;
	}

	//设置地址
	public void setDz(String dz) {
		this.dz=dz;
	}

	//获取电话
	public String getDh() {
		return this.dh;
	}

	//设置电话
	public void setDh(String dh) {
		this.dh=dh;
	}

	//获取传真
	public String getCz() {
		return this.cz;
	}

	//设置传真
	public void setCz(String cz) {
		this.cz=cz;
	}

	//获取手机
	public String getSj() {
		return this.sj;
	}

	//设置手机
	public void setSj(String sj) {
		this.sj=sj;
	}

	//获取网址
	public String getWz() {
		return this.wz;
	}

	//设置网址
	public void setWz(String wz) {
		this.wz=wz;
	}

	//获取邮编
	public String getYb() {
		return this.yb;
	}

	//设置邮编
	public void setYb(String yb) {
		this.yb=yb;
	}

	//获取电邮
	public String getDy() {
		return this.dy;
	}

	//设置电邮
	public void setDy(String dy) {
		this.dy=dy;
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

	public String getFlmc() {
		return flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
}
