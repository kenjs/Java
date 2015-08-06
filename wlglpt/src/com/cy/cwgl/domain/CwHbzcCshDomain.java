package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_HBZC_CSH is created by tools.
 * @author HJH
 */

public class CwHbzcCshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cshDjxh;                          // 初始化登记序号(SEQ_CW_DJXH)
	private String ssJgbm;                           // 所属机构
	private String zcflDm;                           // 资产分类代码
	private String yhmc;                             // 银行名称
	private String hm;                               // 用户名
	private String zh;                               // 账号
	private Double csje;                             // 初始金额
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String qyStr;    //启用标志
	private String error;
	private String bz;
	private String ywxh;
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private String yhTree;
	private String flTree;
	public String getYhTree() {
		return yhTree;
	}

	public void setYhTree(String yhTree) {
		this.yhTree = yhTree;
	}

	public String getFlTree() {
		return flTree;
	}

	public void setFlTree(String flTree) {
		this.flTree = flTree;
	}

	public String getQyStr() {
		if(qybz!=null){
			if(qybz.equals("Y")){
				qyStr="启用";
			}
			else {
				qyStr="停用";
			}
		}
		
		return qyStr;
	}

	public void setQyStr(String qyStr) {
		this.qyStr = qyStr;
	}

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String sjMc;
	private String flMc;
	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getFlMc() {
		return flMc;
	}

	public void setFlMc(String flMc) {
		this.flMc = flMc;
	}

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public CwHbzcCshDomain() {
	}

	//获取初始化登记序号(SEQ_CW_DJXH)
	public String getCshDjxh() {
		return this.cshDjxh;
	}

	//设置初始化登记序号(SEQ_CW_DJXH)
	public void setCshDjxh(String cshDjxh) {
		this.cshDjxh=cshDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取资产分类代码
	public String getZcflDm() {
		return this.zcflDm;
	}

	//设置资产分类代码
	public void setZcflDm(String zcflDm) {
		this.zcflDm=zcflDm;
	}

	//获取银行名称
	public String getYhmc() {
		return this.yhmc;
	}

	//设置银行名称
	public void setYhmc(String yhmc) {
		this.yhmc=yhmc;
	}

	//获取用户名
	public String getHm() {
		return this.hm;
	}

	//设置用户名
	public void setHm(String hm) {
		this.hm=hm;
	}

	//获取账号
	public String getZh() {
		return this.zh;
	}

	//设置账号
	public void setZh(String zh) {
		this.zh=zh;
	}

	//获取初始金额
	public Double getCsje() {
		return this.csje;
	}

	//设置初始金额
	public void setCsje(Double csje) {
		this.csje=csje;
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
}
