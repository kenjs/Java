package com.cy.zyegl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.domain.HyPcHwxxDomain;
import com.cy.hygl.domain.HyPcxxglDomain;

/**
 * The DOMAIN class FOR HY_PC_XYDJ is created by tools.
 * @author HJH
 */

public class HyPcXydjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // 派车登记序号(SEQ_PC_DJXH)
	private String xyh;                              // 协议号
	private Double yfHj;                             // 运费变更_总运费
	private Double yfYfyf;                           // 运费变更_预付运费
	private Double yfYj;                             // 运费变更_押金
	private Double yfXxf;                            // 运费变更_信息费
	private Double yfSjs;                            // 运费变更_司机收
	private Double yfHdyf;                           // 运费变更_货到运费
	private Double yfHdf;                            // 运费变更_回单付
	private String bz;                               // 备注
	private String ywyCzyDjxh;                       // 业务员
	private String ywyCzyMc;                       // 业务员名称
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String yfbgbz;
	private String slbgbz;

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	
	private String yxyfHj;                             // 原现运费变更_总运费
	private String yxyfYfyf;                           // 原现运费变更_预付运费
	private String yxyfYj;                             // 原现运费变更_押金
	private String yxyfXxf;                            // 原现运费变更_信息费
	private String yxyfSjs;                            // 原现运费变更_司机收
	private String yxyfHdyf;                           // 原现运费变更_货到运费
	private String yxyfHdf;                            // 原现运费变更_回单付
	
	private String bmbm;
	private HyPcxxglDomain pcxxDomain;
	
	/***协议登记 货物变动信息****/
	private String wfhDjxh;
	private String ddbh;
	private String khmc;
	private String hwmc;
	private String zrbmMc;
	private String bdHwSl;
	private String bdHwZl;
	private String bdHwTj;
	private String bdJsSl;
	
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private String shrDjxh;                          // 收货人_登记序号
	private String shrMc;                            // 收货人_名称
	private String shrDz;                            // 收货人_地址
	private String shrLxr;                           // 收货人_联系人
	private String shrLxdh;                          // 收货人_联系电话
	private String shrXzqhDm;                        // 收货人_行政区划代码
	private String szHwBzHldwDm;                     // 实装_货物_包装_计量单位
	private Double szHwSl;                           // 实装_货物_数量
	private Double szHwZl;                           // 实装_货物_重量
	private Double szHwTj;                           // 实装_货物_体积
	private Date yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private Double szJsSl;                           // 实装_结算数量
	private String hdbh;
	
	private String shrXzqhMc;
	/*****************检索条件*************/
	private String ssJgbm;
	private String pcJgbm;
	private String pcrCzyDjxh;
	private String pcrqq;
	private String pcrqz;
	private String fhrMc;
	private String fhrDjxh;
	private String pcdh;
	private String zt;
	
	/**********************系统参数*********************/
	private String xtcs20016;					//协议登记是否需要
	private String xtcs20206;					//协议登记变动是否需要审批
	
	private String addFlag;						//新增标志
	private String kfsFlag;

	private HyPcHwxxXydjDomain hwmxDomain;
	private HyPcHwxxDomain pcHwDomain;
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyPcXydjDomain() {
	}

	//获取派车登记序号(SEQ_PC_DJXH)
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号(SEQ_PC_DJXH)
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取协议号
	public String getXyh() {
		return this.xyh;
	}

	//设置协议号
	public void setXyh(String xyh) {
		this.xyh=xyh;
	}

	//获取运费变更_总运费
	public Double getYfHj() {
		return this.yfHj;
	}

	//设置运费变更_总运费
	public void setYfHj(Double yfHj) {
		this.yfHj=yfHj;
	}

	//获取运费变更_预付运费
	public Double getYfYfyf() {
		return this.yfYfyf;
	}

	//设置运费变更_预付运费
	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf=yfYfyf;
	}

	//获取运费变更_押金
	public Double getYfYj() {
		return this.yfYj;
	}

	//设置运费变更_押金
	public void setYfYj(Double yfYj) {
		this.yfYj=yfYj;
	}

	//获取运费变更_信息费
	public Double getYfXxf() {
		return this.yfXxf;
	}

	//设置运费变更_信息费
	public void setYfXxf(Double yfXxf) {
		this.yfXxf=yfXxf;
	}

	//获取运费变更_司机收
	public Double getYfSjs() {
		return this.yfSjs;
	}

	//设置运费变更_司机收
	public void setYfSjs(Double yfSjs) {
		this.yfSjs=yfSjs;
	}

	//获取运费变更_货到运费
	public Double getYfHdyf() {
		return this.yfHdyf;
	}

	//设置运费变更_货到运费
	public void setYfHdyf(Double yfHdyf) {
		this.yfHdyf=yfHdyf;
	}

	//获取运费变更_回单付
	public Double getYfHdf() {
		return this.yfHdf;
	}

	//设置运费变更_回单付
	public void setYfHdf(Double yfHdf) {
		this.yfHdf=yfHdf;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取业务员
	public String getYwyCzyDjxh() {
		return this.ywyCzyDjxh;
	}

	//设置业务员
	public void setYwyCzyDjxh(String ywyCzyDjxh) {
		this.ywyCzyDjxh=ywyCzyDjxh;
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

	public String getBmbm() {
		return bmbm;
	}

	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}

	public HyPcxxglDomain getPcxxDomain() {
		if (pcxxDomain == null) {
			pcxxDomain = new HyPcxxglDomain();
		}
		return pcxxDomain;
	}

	public void setPcxxDomain(HyPcxxglDomain pcxxDomain) {
		this.pcxxDomain = pcxxDomain;
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

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public String getPcJgbm() {
		return pcJgbm;
	}

	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm = pcJgbm;
	}

	public String getPcrCzyDjxh() {
		return pcrCzyDjxh;
	}

	public void setPcrCzyDjxh(String pcrCzyDjxh) {
		this.pcrCzyDjxh = pcrCzyDjxh;
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

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getYfbgbz() {
		return yfbgbz;
	}

	public void setYfbgbz(String yfbgbz) {
		this.yfbgbz = yfbgbz;
	}

	public String getSlbgbz() {
		return slbgbz;
	}

	public void setSlbgbz(String slbgbz) {
		this.slbgbz = slbgbz;
	}

	public HyPcHwxxXydjDomain getHwmxDomain() {
		if (hwmxDomain == null) {
			hwmxDomain = new HyPcHwxxXydjDomain();
		}
		return hwmxDomain;
	}

	public void setHwmxDomain(HyPcHwxxXydjDomain hwmxDomain) {
		this.hwmxDomain = hwmxDomain;
	}

	public HyPcHwxxDomain getPcHwDomain() {
		if (pcHwDomain == null) {
			pcHwDomain = new HyPcHwxxDomain();
		}
		return pcHwDomain;
	}

	public void setPcHwDomain(HyPcHwxxDomain pcHwDomain) {
		this.pcHwDomain = pcHwDomain;
	}

	public String getXtcs20206() {
		return xtcs20206;
	}

	public void setXtcs20206(String xtcs20206) {
		this.xtcs20206 = xtcs20206;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getKfsFlag() {
		return kfsFlag;
	}

	public void setKfsFlag(String kfsFlag) {
		this.kfsFlag = kfsFlag;
	}

	public String getYxyfHj() {
		return yxyfHj;
	}

	public void setYxyfHj(String yxyfHj) {
		this.yxyfHj = yxyfHj;
	}

	public String getYxyfYfyf() {
		return yxyfYfyf;
	}

	public void setYxyfYfyf(String yxyfYfyf) {
		this.yxyfYfyf = yxyfYfyf;
	}

	public String getYxyfYj() {
		return yxyfYj;
	}

	public void setYxyfYj(String yxyfYj) {
		this.yxyfYj = yxyfYj;
	}

	public String getYxyfXxf() {
		return yxyfXxf;
	}

	public void setYxyfXxf(String yxyfXxf) {
		this.yxyfXxf = yxyfXxf;
	}

	public String getYxyfSjs() {
		return yxyfSjs;
	}

	public void setYxyfSjs(String yxyfSjs) {
		this.yxyfSjs = yxyfSjs;
	}

	public String getYxyfHdyf() {
		return yxyfHdyf;
	}

	public void setYxyfHdyf(String yxyfHdyf) {
		this.yxyfHdyf = yxyfHdyf;
	}

	public String getYxyfHdf() {
		return yxyfHdf;
	}

	public void setYxyfHdf(String yxyfHdf) {
		this.yxyfHdf = yxyfHdf;
	}

	public String getYwyCzyMc() {
		return ywyCzyMc;
	}

	public void setYwyCzyMc(String ywyCzyMc) {
		this.ywyCzyMc = ywyCzyMc;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public String getBdHwSl() {
		return bdHwSl;
	}

	public void setBdHwSl(String bdHwSl) {
		this.bdHwSl = bdHwSl;
	}

	public String getBdHwZl() {
		return bdHwZl;
	}

	public void setBdHwZl(String bdHwZl) {
		this.bdHwZl = bdHwZl;
	}

	public String getBdHwTj() {
		return bdHwTj;
	}

	public void setBdHwTj(String bdHwTj) {
		this.bdHwTj = bdHwTj;
	}

	public String getBdJsSl() {
		return bdJsSl;
	}

	public void setBdJsSl(String bdJsSl) {
		this.bdJsSl = bdJsSl;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public String getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getShrDjxh() {
		return shrDjxh;
	}

	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh = shrDjxh;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrLxdh() {
		return shrLxdh;
	}

	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh = shrLxdh;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getSzHwBzHldwDm() {
		return szHwBzHldwDm;
	}

	public void setSzHwBzHldwDm(String szHwBzHldwDm) {
		this.szHwBzHldwDm = szHwBzHldwDm;
	}

	public Double getSzHwSl() {
		return szHwSl;
	}

	public void setSzHwSl(Double szHwSl) {
		this.szHwSl = szHwSl;
	}

	public Double getSzHwZl() {
		return szHwZl;
	}

	public void setSzHwZl(Double szHwZl) {
		this.szHwZl = szHwZl;
	}

	public Double getSzHwTj() {
		return szHwTj;
	}

	public void setSzHwTj(Double szHwTj) {
		this.szHwTj = szHwTj;
	}

	public Date getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public Double getSzJsSl() {
		return szJsSl;
	}

	public void setSzJsSl(Double szJsSl) {
		this.szJsSl = szJsSl;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getXtcs20016() {
		return xtcs20016;
	}

	public void setXtcs20016(String xtcs20016) {
		this.xtcs20016 = xtcs20016;
	}

}
