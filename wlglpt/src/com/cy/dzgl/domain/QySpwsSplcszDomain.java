package com.cy.dzgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_SPWS_SPLCSZ is created by tools.
 * @author anq
 */

public class QySpwsSplcszDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long splcSzxh;                         // 审批流程设置序号(SEQ_SPLC_SZXH)
	private String ssJgbm;                           // 所属机构编码(部门)
	private String wsDm;                             // 文书代码
	private String xmflDjxh;                         // 项目分类登记序号
	private String splc;                             // 审批流程
	private Double zssx;                             // 终审时限(天)
	private String gzrbz;                            // 工作日标志(1 工作日，2 自然日)
	private String qzxsbz;                           // 权重系数标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String ssJgmc;//部门名称
	private String wsMc;
	private String xmflmc;
    
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	private QySpwsSplcszZbDomain spjcDomain;
	private List<QySpwsSplcszZbDomain> zbList;
	
	private String dwDm;//单位代码
	private String dwMc;//单位名称
	private String zgsbm;//总公司编码
	private String wsJc;//文书简称
	private String wsspmsDm;//文书审批模式代码
	private String wsspmsMc;//文书审批模式名称
	private String sm;//说明
	private String ywflMc;//业务分类名称
	private String ywhjMc;//业务环节名称
	private String xmflbz;//项目分类标志
	private String curDwbm;//当前单位或部门
	private String existsBz;//存在标志,用于是否已经已有审批流程设置Y
	private String saveBz;//保存标志Y
	private String cxszbz;//重新设置标志Y
	private String qxszbz;//取消设置标志Y
	

	public QySpwsSplcszDomain() {
	}

	//获取审批流程设置序号(SEQ_SPLC_SZXH)
	public Long getSplcSzxh() {
		return this.splcSzxh;
	}

	//设置审批流程设置序号(SEQ_SPLC_SZXH)
	public void setSplcSzxh(Long splcSzxh) {
		this.splcSzxh=splcSzxh;
	}

	//获取所属机构编码(部门)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构编码(部门)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取文书代码
	public String getWsDm() {
		return this.wsDm;
	}

	//设置文书代码
	public void setWsDm(String wsDm) {
		this.wsDm=wsDm;
	}

	//获取项目分类登记序号
	public String getXmflDjxh() {
		return this.xmflDjxh;
	}

	//设置项目分类登记序号
	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh=xmflDjxh;
	}

	//获取审批流程
	public String getSplc() {
		return this.splc;
	}

	//设置审批流程
	public void setSplc(String splc) {
		this.splc=splc;
	}

	//获取终审时限(天)
	public Double getZssx() {
		return this.zssx;
	}

	//设置终审时限(天)
	public void setZssx(Double zssx) {
		this.zssx=zssx;
	}

	//获取工作日标志(1 工作日，2 自然日)
	public String getGzrbz() {
		return this.gzrbz;
	}

	//设置工作日标志(1 工作日，2 自然日)
	public void setGzrbz(String gzrbz) {
		this.gzrbz=gzrbz;
	}

	//获取权重系数标志(Y/N)
	public String getQzxsbz() {
		return this.qzxsbz;
	}

	//设置权重系数标志(Y/N)
	public void setQzxsbz(String qzxsbz) {
		this.qzxsbz=qzxsbz;
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

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getWsMc() {
		return wsMc;
	}

	public void setWsMc(String wsMc) {
		this.wsMc = wsMc;
	}

	public String getXmflmc() {
		return xmflmc;
	}

	public void setXmflmc(String xmflmc) {
		this.xmflmc = xmflmc;
	}

	public String getXmflbz() {
		return xmflbz;
	}

	public void setXmflbz(String xmflbz) {
		this.xmflbz = xmflbz;
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

	public QySpwsSplcszZbDomain getSpjcDomain() {
		if (spjcDomain == null) {
			spjcDomain = new QySpwsSplcszZbDomain();
		}
		return spjcDomain;
	}

	public void setSpjcDomain(QySpwsSplcszZbDomain spjcDomain) {
		this.spjcDomain = spjcDomain;
	}

	public List<QySpwsSplcszZbDomain> getZbList() {
		if (zbList == null) {
			zbList = new ArrayList<QySpwsSplcszZbDomain>();
		}
		return zbList;
	}

	public void setZbList(List<QySpwsSplcszZbDomain> zbList) {
		this.zbList = zbList;
	}

	public String getDwDm() {
		return dwDm;
	}

	public void setDwDm(String dwDm) {
		this.dwDm = dwDm;
	}

	public String getZgsbm() {
		return zgsbm;
	}

	public void setZgsbm(String zgsbm) {
		this.zgsbm = zgsbm;
	}

	public String getWsJc() {
		return wsJc;
	}

	public void setWsJc(String wsJc) {
		this.wsJc = wsJc;
	}

	public String getWsspmsDm() {
		return wsspmsDm;
	}

	public void setWsspmsDm(String wsspmsDm) {
		this.wsspmsDm = wsspmsDm;
	}

	public String getWsspmsMc() {
		return wsspmsMc;
	}

	public void setWsspmsMc(String wsspmsMc) {
		this.wsspmsMc = wsspmsMc;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getYwflMc() {
		return ywflMc;
	}

	public void setYwflMc(String ywflMc) {
		this.ywflMc = ywflMc;
	}

	public String getYwhjMc() {
		return ywhjMc;
	}

	public void setYwhjMc(String ywhjMc) {
		this.ywhjMc = ywhjMc;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getCurDwbm() {
		return curDwbm;
	}

	public void setCurDwbm(String curDwbm) {
		this.curDwbm = curDwbm;
	}

	public String getExistsBz() {
		return existsBz;
	}

	public void setExistsBz(String existsBz) {
		this.existsBz = existsBz;
	}

	public String getCxszbz() {
		return cxszbz;
	}

	public void setCxszbz(String cxszbz) {
		this.cxszbz = cxszbz;
	}

	public String getSaveBz() {
		return saveBz;
	}

	public void setSaveBz(String saveBz) {
		this.saveBz = saveBz;
	}

	public String getQxszbz() {
		return qxszbz;
	}

	public void setQxszbz(String qxszbz) {
		this.qxszbz = qxszbz;
	}
}
