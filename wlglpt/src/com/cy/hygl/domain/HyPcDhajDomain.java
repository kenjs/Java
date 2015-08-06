package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_DHAJ is created by tools.
 * @author HJH
 */

public class HyPcDhajDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ajDjxh;                           // 
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private String fhrDjxh;
	private String pcdh;
	private String ajDhhm;                           // 
	private String ajQkms;                           // 车辆属性代码
	private String ajjgDm;                           // 安检结果代码
	private String bz;                               // 备注
	private String pcrCzyDjxh;                       // 派车人
	private String pcrq;                             // 派车日期
	private String pcJgbm;                           // 派车部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String pcrCzyMc;
	private String zt;
	private String ajjg;
	private String ssJgmc;
	private String pcJgmc;
	private byte[] ajzp;
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<HyPcDhajDomain> ajdhList;
	private List<HyPcDhajDomain> ajzpList;
	
	private int pageXh;
	private int ajcs;
	private String czXm;
	private String sjXm;
	private String sjSjHm;
	private String sjsfz;
	private String clhm;
	private String gchm;
	private String qtLxDh;
	
	private String clsxMc;
	private String yfHj;
	private String yfYfyf;
	private String yfYj;
	private String yfXxf;
	private String yfSjs;
	private String yfHdyf;
	private String yfHdf;
	private String yfjsfMc;
	private String zrbmMc;
	private String xxzjDjxh;
	private String zrbmDjxh;
	private String pccgbz;
	private String pccgbzStr;
	private String pcfsMc;
	private String ysfsMc;
	private String zcfsMc;
	private String pcBz;
	//派车部门、派车人、派车日期（起止）、车辆号码、司机姓名、发货人、派车单号
	/**********检索条件************/
	private String pcbm4Query;
	private String pcrMc4Query;
	private String pcrqQ;
	private String pcrqZ;
	private String clhm4Query;
	private String sjxm4Query;
	private String fhrMc;
	private String pcdh4Qyuery;
	private String sjMc;
	private String bmMc;
	private String pcrqq;
	private String pcrqz;
	private String pcDh;
	public String getPcDh() {
		return pcDh;
	}

	public void setPcDh(String pcDh) {
		this.pcDh = pcDh;
	}

	public String getPcrqq() {
		return pcrqq;
	}

	public void setPcrqq(String pcrqq) {
		this.pcrqq = pcrqq;
	}

	public String getPcrqz() {
		return pcrqz;
	}

	public void setPcrqz(String pcrqz) {
		this.pcrqz = pcrqz;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}

	public HyPcDhajDomain() {
	}

	//获取
	public String getAjDjxh() {
		return this.ajDjxh;
	}

	//设置
	public void setAjDjxh(String ajDjxh) {
		this.ajDjxh=ajDjxh;
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
	public String getAjDhhm() {
		return this.ajDhhm;
	}

	//设置派车单号
	public void setAjDhhm(String ajDhhm) {
		this.ajDhhm=ajDhhm;
	}

	//获取车辆属性代码
	public String getAjQkms() {
		return this.ajQkms;
	}

	//设置车辆属性代码
	public void setAjQkms(String ajQkms) {
		this.ajQkms=ajQkms;
	}

	//获取安检结果代码
	public String getAjjgDm() {
		return this.ajjgDm;
	}

	//设置安检结果代码
	public void setAjjgDm(String ajjgDm) {
		this.ajjgDm=ajjgDm;
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
	public String getPcrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.pcrq);
		}
		catch(Exception e){
			return this.pcrq;
		}
	}

	//设置派车日期
	public void setPcrq(String pcrq) {
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

	public int getAjcs() {
		return ajcs;
	}

	public void setAjcs(int ajcs) {
		this.ajcs = ajcs;
	}

	public String getClhm() {
		return clhm;
	}

	public void setClhm(String clhm) {
		this.clhm = clhm;
	}

	public String getClhm4Query() {
		return clhm4Query;
	}

	public void setClhm4Query(String clhm4Query) {
		this.clhm4Query = clhm4Query;
	}

	public String getCzXm() {
		return czXm;
	}

	public void setCzXm(String czXm) {
		this.czXm = czXm;
	}

	public String getGchm() {
		return gchm;
	}

	public void setGchm(String gchm) {
		this.gchm = gchm;
	}

	public int getPageXh() {
		return pageXh;
	}

	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}

	public String getPcbm4Query() {
		return pcbm4Query;
	}

	public void setPcbm4Query(String pcbm4Query) {
		this.pcbm4Query = pcbm4Query;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getPcdh4Qyuery() {
		return pcdh4Qyuery;
	}

	public void setPcdh4Qyuery(String pcdh4Qyuery) {
		this.pcdh4Qyuery = pcdh4Qyuery;
	}

	public String getPcrCzyMc() {
		return pcrCzyMc;
	}

	public void setPcrCzyMc(String pcrCzyMc) {
		this.pcrCzyMc = pcrCzyMc;
	}

	public String getPcrMc4Query() {
		return pcrMc4Query;
	}

	public void setPcrMc4Query(String pcrMc4Query) {
		this.pcrMc4Query = pcrMc4Query;
	}

	public String getPcrqQ() {
		return pcrqQ;
	}

	public void setPcrqQ(String pcrqQ) {
		this.pcrqQ = pcrqQ;
	}

	public String getPcrqZ() {
		return pcrqZ;
	}

	public void setPcrqZ(String pcrqZ) {
		this.pcrqZ = pcrqZ;
	}

	public String getQtLxDh() {
		return qtLxDh;
	}

	public void setQtLxDh(String qtLxDh) {
		this.qtLxDh = qtLxDh;
	}

	public String getSjSjHm() {
		return sjSjHm;
	}

	public void setSjSjHm(String sjSjHm) {
		this.sjSjHm = sjSjHm;
	}

	public String getSjXm() {
		return sjXm;
	}

	public void setSjXm(String sjXm) {
		this.sjXm = sjXm;
	}

	public String getSjxm4Query() {
		return sjxm4Query;
	}

	public void setSjxm4Query(String sjxm4Query) {
		this.sjxm4Query = sjxm4Query;
	}

	public String getZt() {
		if(ajcs > 0){
			zt = "已安检";
		}else{
			zt = "未安检";
		}
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public List<HyPcDhajDomain> getAjdhList() {
		if(ajdhList == null){
			ajdhList = new ArrayList<HyPcDhajDomain>();
		}
		return ajdhList;
	}

	public void setAjdhList(List<HyPcDhajDomain> ajdhList) {
		this.ajdhList = ajdhList;
	}

	public String getAjjg() {
		return ajjg;
	}

	public byte[] getAjzp() {
		return ajzp;
	}

	public void setAjzp(byte[] ajzp) {
		this.ajzp = ajzp;
	}

	public void setAjjg(String ajjg) {
		this.ajjg = ajjg;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getPcJgmc() {
		return pcJgmc;
	}

	public void setPcJgmc(String pcJgmc) {
		this.pcJgmc = pcJgmc;
	}

	public List<HyPcDhajDomain> getAjzpList() {
		if(ajzpList == null){
			ajzpList = new ArrayList<HyPcDhajDomain>();
		}
		return ajzpList;
	}

	public void setAjzpList(List<HyPcDhajDomain> ajzpList) {
		this.ajzpList = ajzpList;
	}

	public String getSjsfz() {
		return sjsfz;
	}

	public void setSjsfz(String sjsfz) {
		this.sjsfz = sjsfz;
	}

	public String getClsxMc() {
		return clsxMc;
	}

	public void setClsxMc(String clsxMc) {
		this.clsxMc = clsxMc;
	}

	public String getYfHj() {
		return yfHj;
	}

	public void setYfHj(String yfHj) {
		this.yfHj = yfHj;
	}

	public String getYfYfyf() {
		return yfYfyf;
	}

	public void setYfYfyf(String yfYfyf) {
		this.yfYfyf = yfYfyf;
	}

	public String getYfYj() {
		return yfYj;
	}

	public void setYfYj(String yfYj) {
		this.yfYj = yfYj;
	}

	public String getYfXxf() {
		return yfXxf;
	}

	public void setYfXxf(String yfXxf) {
		this.yfXxf = yfXxf;
	}

	public String getYfSjs() {
		return yfSjs;
	}

	public void setYfSjs(String yfSjs) {
		this.yfSjs = yfSjs;
	}

	public String getYfHdyf() {
		return yfHdyf;
	}

	public void setYfHdyf(String yfHdyf) {
		this.yfHdyf = yfHdyf;
	}

	public String getYfHdf() {
		return yfHdf;
	}

	public void setYfHdf(String yfHdf) {
		this.yfHdf = yfHdf;
	}

	public String getYfjsfMc() {
		return yfjsfMc;
	}

	public void setYfjsfMc(String yfjsfMc) {
		this.yfjsfMc = yfjsfMc;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public String getXxzjDjxh() {
		return xxzjDjxh;
	}

	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh = xxzjDjxh;
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
	}

	public String getPccgbz() {
		return pccgbz;
	}

	public void setPccgbz(String pccgbz) {
		this.pccgbz = pccgbz;
	}

	public String getPccgbzStr() {
		if("Y".equals(pccgbz)){
			pccgbzStr = "成功";
		}else if("N".equals(pccgbz)){
			pccgbzStr = "失败";
		}
		return pccgbzStr;
	}

	public void setPccgbzStr(String pccgbzStr) {
		this.pccgbzStr = pccgbzStr;
	}

	public String getPcfsMc() {
		return pcfsMc;
	}

	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}

	public String getYsfsMc() {
		return ysfsMc;
	}

	public void setYsfsMc(String ysfsMc) {
		this.ysfsMc = ysfsMc;
	}

	public String getZcfsMc() {
		return zcfsMc;
	}

	public void setZcfsMc(String zcfsMc) {
		this.zcfsMc = zcfsMc;
	}

	public String getPcBz() {
		return pcBz;
	}

	public void setPcBz(String pcBz) {
		this.pcBz = pcBz;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

}
