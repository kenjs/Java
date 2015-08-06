package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_ZPAJ is created by tools.
 * @author HJH
 */

public class HyZpajDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String ajDjxh;                           // 
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private byte[] ajzp;                             // 派车单号
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
	private String clHm;  //车辆号码
	private String sjXm;  //司机姓名
	private String fhr;   //发货人
	private String pcrXm; //派车人姓名
	private String pcDh;  //派车单号
	private String pcrqq; //派车日期起
	private String pcrqz; //派车日期止
	private String czxm;  //车主姓名
	private String gcHm;  //挂车号码
	private String sjHm;  //手机号码
	private String dianhua;  //电话
	private String fhRq;   //发货日期
	private Long pageXh;
	private int ajCs; //安检次数
	private byte[] fj;
	private String fjmcSave;
	private String tage;
	private String pcXm;  //派车部门
	private String ssXm;  //所属部门
	private String sfz;
	private String clsxMc;
	private String yfHj;  //运费
	private String yfYfyf;
	private String yfYj;
	private String yfSjs;
	private String yfHdyf;
	private String yfHdf;
	private String yfjsfMc;
	private String xxzjDjxh;
	private String yfXxf;
	private String zrbmDjxh;
	private String zrbmMc;
	private String pcrCzyMc;
	private String pcfsMc;
	private String ysfsMc;
	private String zcfsMc;
	private String pccgbzStr;
	private String pccgbz;
	private String pcBz;
	private String fhrMc;
	private String fhrDjxh;
	private List<HyPcDhajDomain> ajdhList;
	private List<HyPcDhajDomain> ajzpList;
	private String sjMc;
	private String bmMc;
	private String clhm4Query;
	
	private String zpdz;                             //照片地址

	public String getZpdz() {
		return zpdz;
	}

	public void setZpdz(String zpdz) {
		this.zpdz = zpdz;
	}
	
	public String getClhm4Query() {
		return clhm4Query;
	}

	public void setClhm4Query(String clhm4Query) {
		this.clhm4Query = clhm4Query;
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

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyZpajDomain() {
	}

	//获取
	public String getAjDjxh() {
		return this.ajDjxh;
	}

	//设置
	public void setAjDjxh(String ajDjxh) {
		this.ajDjxh=ajDjxh;
	}

	public String getPccgbzStr() {
		if("Y".equals(pccgbz)){
			pccgbzStr = "成功";
		}else{
			pccgbzStr = "失败";
		}
		return pccgbzStr;
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
	public byte[] getAjzp() {
		return this.ajzp;
	}

	//设置派车单号
	public void setAjzp(byte[] ajzp) {
		this.ajzp=ajzp;
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

	public String getClHm() {
		return clHm;
	}

	public void setClHm(String clHm) {
		this.clHm = clHm;
	}

	public String getSjXm() {
		return sjXm;
	}

	public void setSjXm(String sjXm) {
		this.sjXm = sjXm;
	}



	

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getFhRq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.fhRq);
		}
		catch(Exception e){
			return this.fhRq;
		}
	}

	public void setFhRq(String fhRq) {
		this.fhRq = fhRq;
	}

	public String getGcHm() {
		return gcHm;
	}

	public void setGcHm(String gcHm) {
		this.gcHm = gcHm;
	}

	public String getSjHm() {
		return sjHm;
	}

	public void setSjHm(String sjHm) {
		this.sjHm = sjHm;
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

	public String getPcDh() {
		return pcDh;
	}

	public void setPcDh(String pcDh) {
		this.pcDh = pcDh;
	}

	public String getPcrXm() {
		return pcrXm;
	}

	public void setPcrXm(String pcrXm) {
		this.pcrXm = pcrXm;
	}

	public Long getPageXh() {
		return pageXh;
	}

	public void setPageXh(Long pageXh) {
		this.pageXh = pageXh;
	}

	public int getAjCs() {
		return ajCs;
	}

	public void setAjCs(int ajCs) {
		this.ajCs = ajCs;
	}

	

	

	public byte[] getFj() {
		return fj;
	}

	public void setFj(byte[] fj) {
		this.fj = fj;
	}

	public String getFjmcSave() {
		return fjmcSave;
	}

	public void setFjmcSave(String fjmcSave) {
		this.fjmcSave = fjmcSave;
	}

	public String getTage() {
		return tage;
	}

	public void setTage(String tage) {
		this.tage = tage;
	}

	public String getPcXm() {
		return pcXm;
	}

	public void setPcXm(String pcXm) {
		this.pcXm = pcXm;
	}

	public String getSsXm() {
		return ssXm;
	}

	public void setSsXm(String ssXm) {
		this.ssXm = ssXm;
	}

	public List<HyPcDhajDomain> getAjdhList() {
		if(ajdhList==null){
			ajdhList=new ArrayList<HyPcDhajDomain>();
		}
		return ajdhList;
	}

	public void setAjdhList(List<HyPcDhajDomain> ajdhList) {
		this.ajdhList = ajdhList;
	}

	public List<HyPcDhajDomain> getAjzpList() {
		if(ajzpList==null){
			ajzpList=new ArrayList<HyPcDhajDomain>();
		}
		return ajzpList;
	}

	public void setAjzpList(List<HyPcDhajDomain> ajzpList) {
		this.ajzpList = ajzpList;
	}

	public String getClsxMc() {
		return clsxMc;
	}

	public void setClsxMc(String clsxMc) {
		this.clsxMc = clsxMc;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getPcBz() {
		return pcBz;
	}

	public void setPcBz(String pcBz) {
		this.pcBz = pcBz;
	}

	public String getPccgbz() {
		return pccgbz;
	}

	public void setPccgbz(String pccgbz) {
		this.pccgbz = pccgbz;
	}

	public String getPcfsMc() {
		return pcfsMc;
	}

	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}

	public String getPcrCzyMc() {
		return pcrCzyMc;
	}

	public void setPcrCzyMc(String pcrCzyMc) {
		this.pcrCzyMc = pcrCzyMc;
	}

	public String getXxzjDjxh() {
		return xxzjDjxh;
	}

	public void setXxzjDjxh(String xxzjDjxh) {
		this.xxzjDjxh = xxzjDjxh;
	}

	public String getYfHdf() {
		return yfHdf;
	}

	public void setYfHdf(String yfHdf) {
		this.yfHdf = yfHdf;
	}

	public String getYfHdyf() {
		return yfHdyf;
	}

	public void setYfHdyf(String yfHdyf) {
		this.yfHdyf = yfHdyf;
	}

	public String getYfHj() {
		return yfHj;
	}

	public void setYfHj(String yfHj) {
		this.yfHj = yfHj;
	}

	public String getYfjsfMc() {
		return yfjsfMc;
	}

	public void setYfjsfMc(String yfjsfMc) {
		this.yfjsfMc = yfjsfMc;
	}

	public String getYfSjs() {
		return yfSjs;
	}

	public void setYfSjs(String yfSjs) {
		this.yfSjs = yfSjs;
	}

	public String getYfXxf() {
		return yfXxf;
	}

	public void setYfXxf(String yfXxf) {
		this.yfXxf = yfXxf;
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

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public void setPccgbzStr(String pccgbzStr) {
		this.pccgbzStr = pccgbzStr;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}


}
