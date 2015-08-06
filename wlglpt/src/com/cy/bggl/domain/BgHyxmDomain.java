package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_HYXM is created by tools.
 * @author HJH
 */

public class BgHyxmDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hyxmDjxh;                         // 行业新闻登记序号(SEQ_BG_DJXH)
	private String jgbm;                             // 机构编码
	private String jgmc;                             // 机构
	private String fbrq;                             // 发布日期(YYYY-MM-DD)
	private String bcztDm;                           // 保存状态代码
	private String ly;                               // 来源
	private String zt;                               // 主题
	private String nr;                               // 内容
	private String xjgxbz;                           // 下级共享标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
private List<BgHyxmDomain> fjList; 		 //附件列表
	
	//附件	
	List<byte[]> uploadValueList;
	
	List<String> uploadNameList;
	
	private String rqQ;//页面检索日期起
	private String rqZ;//页面检索日期止 
	
	private String saveBz;//保存标志1为暂存，2为发布
	private String fjmc;//附件名称
	private String xh;//附件序号
	private byte[] fjnr;//附件内容

	public BgHyxmDomain() {
	}

	//获取行业新闻登记序号(SEQ_BG_DJXH)
	public String getHyxmDjxh() {
		return this.hyxmDjxh;
	}

	//设置行业新闻登记序号(SEQ_BG_DJXH)
	public void setHyxmDjxh(String hyxmDjxh) {
		this.hyxmDjxh=hyxmDjxh;
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取发布日期(YYYY-MM-DD)
	public String getFbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.fbrq);
		}
		catch(Exception e){
			return this.fbrq;
		}
	}

	//设置发布日期(YYYY-MM-DD)
	public void setFbrq(String fbrq) {
		this.fbrq=fbrq;
	}

	//获取保存状态代码
	public String getBcztDm() {
		return this.bcztDm;
	}

	//设置保存状态代码
	public void setBcztDm(String bcztDm) {
		this.bcztDm=bcztDm;
	}

	//获取来源
	public String getLy() {
		return this.ly;
	}

	//设置来源
	public void setLy(String ly) {
		this.ly=ly;
	}

	//获取主题
	public String getZt() {
		return this.zt;
	}

	//设置主题
	public void setZt(String zt) {
		this.zt=zt;
	}

	//获取内容
	public String getNr() {
		return this.nr;
	}

	//设置内容
	public void setNr(String nr) {
		this.nr=nr;
	}

	//获取下级共享标志(Y/N)
	public String getXjgxbz() {
		return this.xjgxbz;
	}

	//设置下级共享标志(Y/N)
	public void setXjgxbz(String xjgxbz) {
		this.xjgxbz=xjgxbz;
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

	public List<BgHyxmDomain> getFjList() {
		if(fjList==null){
			fjList=new ArrayList<BgHyxmDomain>();
		}
		return fjList;
	}

	public String getFjmc() {
		return fjmc;
	}

	public byte[] getFjnr() {
		return fjnr;
	}

	public String getJgmc() {
		return jgmc;
	}

	public String getRqQ() {
		return rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public String getSaveBz() {
		return saveBz;
	}

	public List<String> getUploadNameList() {
		if(uploadNameList == null){
			uploadNameList = new ArrayList<String>();
		}
		return uploadNameList;
	}

	public List<byte[]> getUploadValueList() {
		if(uploadValueList == null){
			uploadValueList = new ArrayList<byte[]>();
		}
		return uploadValueList;
	}

	public String getXh() {
		return xh;
	}

	public void setFjList(List<BgHyxmDomain> fjList) {
		this.fjList = fjList;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public void setFjnr(byte[] fjnr) {
		this.fjnr = fjnr;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public void setSaveBz(String saveBz) {
		this.saveBz = saveBz;
	}

	public void setUploadNameList(List<String> uploadNameList) {
		this.uploadNameList = uploadNameList;
	}

	public void setUploadValueList(List<byte[]> uploadValueList) {
		this.uploadValueList = uploadValueList;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
}
