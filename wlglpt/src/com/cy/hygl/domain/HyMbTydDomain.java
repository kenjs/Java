package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_HW_DDXX is created by tools.
 * @author HJH
 */

public class HyMbTydDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String mbDjxh;     
	private String mbmc;   
	private boolean mcUseable;
	private Long ddDjxh;                             // 订单登记序号(SEQ_DD_DJXH)
	private String ddbh;                             // 订单编号
	private String bz;                               // 备注
	private String hwztDm;                           // 货物状态代码
	
	private String fhrDjxh;                          // 发货人_登记序号
	private String fhrMc;                            // 发货人_名称
	private String fhrDz;                            // 发货人_地址
	private String fhrLxr;                           // 发货人_联系人
	private String fhrLxdh;                          // 发货人_联系电话
	private String fhrXzqhDm;                        // 发货人_行政区划代码
	private String shrDjxh;							 //收货人登记序号
	private String shrMc;                            // 收货人_名称
	private String shrDz;                            // 收货人_地址
	private String shrLxr;                           // 收货人_联系人
	private String shrLxdh;                          // 收货人_联系电话
	private String shrXzqhDm;                        // 收货人_行政区划代码
	private Date xdrq;								//	下单日期
	private int yqFhrq;                           // 要求发货日期
	private int yqDdrq;                           // 要求到达日期
	private String hdbh;                             // 回单编号
	private String psbz;							 // 配送标志
	private String hwlyDm;                           // 货物来源代码
	private String shfsDm;                           // 收货方式代码
	private String ykjsfsDm;                         // 余款结算方式代码
	private String yjjsfsDm;                         // 运价结算方式代码
	private String yxbz;                             // 有效标志(Y/N)
	private String djrCzyDjxh;                       // 登记人
	private Date djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期
	private Long thflDm;

	private String djrMc;
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String fhrXzqhMc;
	private String shrXzqhMc;
	private String djJgmc;
	private String ssJgmc;
	private String hwztMc;
	private String shfsMc;
	
	private Double srHj;                             // 运价_总运价
	private Double srYj;                             // 运价_月结
	private Double srXf;                             // 运价_现付
	private Double srHdf;                            // 运价_货到付
	private Double srThf;                            // 运价_提货付
	private Double srHf;                             // 运价_回单付
	private Double srHk;                             // 运价_回扣
	
	private String hwmc;                             // 货物名称
	private String hwbz;
	private String sl;
	private String zl;
	private String tj;
	
	private String khlxDm4js;
	private String ykjsfsDm4js;
	
	/*************货物信息***********/
	private List<String> hwmcs;
	private List<String> hwDjxhs;
	private List<String> hwxhDjxhs;
	private List<String> hwflDms;
	private List<String> hwBzHldwDm;
	private List<Double> hwsls;
	private List<String> hwSlJldwDm;
	private List<Double> hwzls;
	private List<String> hwZlJldwDm;
	private List<Double> hwtjs;
	private List<String> hwTjJldwDm;
	
	private List<String> hwXhs;
	
	/*************检索条件***********/
	private String djJgbm4Query;
	private String djrCzyDjxh4Query;
	private String fhrDjxh4Query;
	private String fhrMc4Query;
	private String shrDjxh4Query;
	private String shrMc4Query;
	private String hwztDm4Query;
	private String ddbh4Query;
	private String djrqQ;
	private String djrqZ;
	private String fhrqQ;
	private String fhrqZ;
	private String shrqQ;
	private String shrqZ;
	private int pageXh;
	
	private String yqFhrqDate;
	private String yqDdrqDate;
	
	/*************copy页面检索条件***********/
	private String shDw;//收货单位
	private String xdrqQ;//下单日期起
	private String xdrqZ;//下单日期止
	private HyMbTydHwmxDomain hwmxDomain;
	
	private List<HyMbTydHwmxDomain> hwList;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<BaseBusinessDomain> copyList;
	public List<BaseBusinessDomain> getCopyList() {
		if(copyList==null){
			copyList=new ArrayList<BaseBusinessDomain>();
		}
		return copyList;
	}


	public void setCopyList(List<BaseBusinessDomain> copyList) {
		this.copyList = copyList;
	}


	public HyMbTydDomain() {
	}





	public HyMbTydHwmxDomain getHwmxDomain() {
		if(hwmxDomain==null){
			hwmxDomain=new HyMbTydHwmxDomain();
		}
		return hwmxDomain;
	}


	public void setHwmxDomain(HyMbTydHwmxDomain hwmxDomain) {
		this.hwmxDomain = hwmxDomain;
	}


	


	public List<HyMbTydHwmxDomain> getHwList() {
		if(hwList==null){
			hwList = new ArrayList<HyMbTydHwmxDomain>();
		}
		
		return hwList;
	}


	public void setHwList(List<HyMbTydHwmxDomain> hwList) {
		this.hwList = hwList;
	}


	public Long getDdDjxh() {
		return ddDjxh;
	}


	public void setDdDjxh(Long ddDjxh) {
		this.ddDjxh = ddDjxh;
	}


	public String getDdbh() {
		return ddbh;
	}


	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
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


	public String getFhrDz() {
		return fhrDz;
	}


	public void setFhrDz(String fhrDz) {
		this.fhrDz = fhrDz;
	}


	public String getFhrLxr() {
		return fhrLxr;
	}


	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}


	public String getFhrLxdh() {
		return fhrLxdh;
	}


	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh = fhrLxdh;
	}


	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}


	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
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


	public Date getXdrq() {
		return xdrq;
	}


	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}


	


	


	public String getHdbh() {
		return hdbh;
	}


	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getHwlyDm() {
		return hwlyDm;
	}


	public void setHwlyDm(String hwlyDm) {
		this.hwlyDm = hwlyDm;
	}


	public String getHwztDm() {
		return hwztDm;
	}


	public void setHwztDm(String hwztDm) {
		this.hwztDm = hwztDm;
	}


	public String getShfsDm() {
		return shfsDm;
	}


	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}


	public String getYkjsfsDm() {
		return ykjsfsDm;
	}


	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}


	public String getYjjsfsDm() {
		return yjjsfsDm;
	}


	public void setYjjsfsDm(String yjjsfsDm) {
		this.yjjsfsDm = yjjsfsDm;
	}


	public String getYxbz() {
		return yxbz;
	}


	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}


	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}


	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}


	public Date getDjrq() {
		return djrq;
	}


	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}


	public String getDjJgbm() {
		return djJgbm;
	}


	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}


	public String getSsJgbm() {
		return ssJgbm;
	}


	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}


	public String getCjrCzyDjxh() {
		return cjrCzyDjxh;
	}


	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh = cjrCzyDjxh;
	}


	public Date getCjrq() {
		return cjrq;
	}


	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}


	public String getXgrCzyDjxh() {
		return xgrCzyDjxh;
	}


	public void setXgrCzyDjxh(String xgrCzyDjxh) {
		this.xgrCzyDjxh = xgrCzyDjxh;
	}


	public Date getXgrq() {
		return xgrq;
	}


	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}


	public String getCjrMc() {
		return cjrMc;
	}


	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}


	public String getXgrMc() {
		return xgrMc;
	}


	public void setXgrMc(String xgrMc) {
		this.xgrMc = xgrMc;
	}


	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}


	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}


	public String getShrXzqhMc() {
		return shrXzqhMc;
	}


	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}


	public String getDjJgmc() {
		return djJgmc;
	}


	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}

	public Double getSrHj() {
		return srHj;
	}


	public void setSrHj(Double srHj) {
		this.srHj = srHj;
	}


	public Double getSrYj() {
		return srYj;
	}


	public void setSrYj(Double srYj) {
		this.srYj = srYj;
	}


	public Double getSrXf() {
		return srXf;
	}


	public void setSrXf(Double srXf) {
		this.srXf = srXf;
	}


	public Double getSrHdf() {
		return srHdf;
	}


	public void setSrHdf(Double srHdf) {
		this.srHdf = srHdf;
	}


	public Double getSrThf() {
		return srThf;
	}


	public void setSrThf(Double srThf) {
		this.srThf = srThf;
	}


	public Double getSrHf() {
		return srHf;
	}


	public void setSrHf(Double srHf) {
		this.srHf = srHf;
	}


	public Double getSrHk() {
		return srHk;
	}


	public void setSrHk(Double srHk) {
		this.srHk = srHk;
	}


	public String getDjrMc() {
		return djrMc;
	}


	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}


	public String getSsJgmc() {
		return ssJgmc;
	}


	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}


	public String getHwztMc() {
		return hwztMc;
	}


	public void setHwztMc(String hwztMc) {
		this.hwztMc = hwztMc;
	}


	public String getShfsMc() {
		return shfsMc;
	}


	public void setShfsMc(String shfsMc) {
		this.shfsMc = shfsMc;
	}


	public String getHwmc() {
		return hwmc;
	}


	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}


	public String getHwbz() {
		return hwbz;
	}


	public void setHwbz(String hwbz) {
		this.hwbz = hwbz;
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


	public Long getThflDm() {
		return thflDm;
	}


	public void setThflDm(Long thflDm) {
		this.thflDm = thflDm;
	}


	public List<String> getHwmcs() {
		if (hwmcs == null) {
			hwmcs = new ArrayList<String>(); 
		}
		return hwmcs;
	}


	public void setHwmcs(List<String> hwmcs) {
		this.hwmcs = hwmcs;
	}


	public List<String> getHwDjxhs() {
		if (hwDjxhs == null) {
			hwDjxhs = new ArrayList<String>(); 
		}
		return hwDjxhs;
	}


	public void setHwDjxhs(List<String> hwDjxhs) {
		this.hwDjxhs = hwDjxhs;
	}


	public List<String> getHwxhDjxhs() {
		if (hwxhDjxhs == null) {
			hwxhDjxhs = new ArrayList<String>(); 
		}
		return hwxhDjxhs;
	}


	public void setHwxhDjxhs(List<String> hwxhDjxhs) {
		this.hwxhDjxhs = hwxhDjxhs;
	}


	public List<String> getHwflDms() {
		if (hwflDms == null) {
			hwflDms = new ArrayList<String>(); 
		}
		return hwflDms;
	}


	public void setHwflDms(List<String> hwflDms) {
		this.hwflDms = hwflDms;
	}


	public List<String> getHwBzHldwDm() {
		if (hwBzHldwDm == null) {
			hwBzHldwDm = new ArrayList<String>(); 
		}
		return hwBzHldwDm;
	}


	public void setHwBzHldwDm(List<String> hwBzHldwDm) {
		this.hwBzHldwDm = hwBzHldwDm;
	}


	public List<Double> getHwsls() {
		if (hwsls == null) {
			hwsls = new ArrayList<Double>(); 
		}
		return hwsls;
	}


	public void setHwsls(List<Double> hwsls) {
		this.hwsls = hwsls;
	}


	public List<String> getHwSlJldwDm() {
		if (hwSlJldwDm == null) {
			hwSlJldwDm = new ArrayList<String>(); 
		}
		return hwSlJldwDm;
	}


	public void setHwSlJldwDm(List<String> hwSlJldwDm) {
		this.hwSlJldwDm = hwSlJldwDm;
	}


	public List<Double> getHwzls() {
		if (hwzls == null) {
			hwzls = new ArrayList<Double>(); 
		}
		return hwzls;
	}


	public void setHwzls(List<Double> hwzls) {
		this.hwzls = hwzls;
	}


	public List<String> getHwZlJldwDm() {
		if (hwZlJldwDm == null) {
			hwZlJldwDm = new ArrayList<String>(); 
		}
		return hwZlJldwDm;
	}


	public void setHwZlJldwDm(List<String> hwZlJldwDm) {
		this.hwZlJldwDm = hwZlJldwDm;
	}


	public List<Double> getHwtjs() {
		if (hwtjs == null) {
			hwtjs = new ArrayList<Double>(); 
		}
		return hwtjs;
	}


	public void setHwtjs(List<Double> hwtjs) {
		this.hwtjs = hwtjs;
	}


	public List<String> getHwTjJldwDm() {
		if (hwTjJldwDm == null) {
			hwTjJldwDm = new ArrayList<String>(); 
		}
		return hwTjJldwDm;
	}


	public void setHwTjJldwDm(List<String> hwTjJldwDm) {
		this.hwTjJldwDm = hwTjJldwDm;
	}


	public List<String> getHwXhs() {
		if (hwXhs == null) {
			hwXhs = new ArrayList<String>();
		}
		return hwXhs;
	}


	public void setHwXhs(List<String> hwXhs) {
		this.hwXhs = hwXhs;
	}


	public String getDjJgbm4Query() {
		return djJgbm4Query;
	}


	public void setDjJgbm4Query(String djJgbm4Query) {
		this.djJgbm4Query = djJgbm4Query;
	}


	public String getDjrCzyDjxh4Query() {
		return djrCzyDjxh4Query;
	}


	public void setDjrCzyDjxh4Query(String djrCzyDjxh4Query) {
		this.djrCzyDjxh4Query = djrCzyDjxh4Query;
	}


	public String getFhrMc4Query() {
		return fhrMc4Query;
	}


	public void setFhrMc4Query(String fhrMc4Query) {
		this.fhrMc4Query = fhrMc4Query;
	}


	public String getShrMc4Query() {
		return shrMc4Query;
	}


	public void setShrMc4Query(String shrMc4Query) {
		this.shrMc4Query = shrMc4Query;
	}


	public String getHwztDm4Query() {
		return hwztDm4Query;
	}


	public void setHwztDm4Query(String hwztDm4Query) {
		this.hwztDm4Query = hwztDm4Query;
	}


	public String getDdbh4Query() {
		return ddbh4Query;
	}


	public void setDdbh4Query(String ddbh4Query) {
		this.ddbh4Query = ddbh4Query;
	}

	public String getFhrDjxh4Query() {
		return fhrDjxh4Query;
	}


	public void setFhrDjxh4Query(String fhrDjxh4Query) {
		this.fhrDjxh4Query = fhrDjxh4Query;
	}


	public String getShrDjxh4Query() {
		return shrDjxh4Query;
	}


	public void setShrDjxh4Query(String shrDjxh4Query) {
		this.shrDjxh4Query = shrDjxh4Query;
	}


	public String getDjrqQ() {
		return djrqQ;
	}


	public void setDjrqQ(String djrqQ) {
		this.djrqQ = djrqQ;
	}


	public String getDjrqZ() {
		return djrqZ;
	}


	public void setDjrqZ(String djrqZ) {
		this.djrqZ = djrqZ;
	}


	public String getFhrqQ() {
		return fhrqQ;
	}


	public void setFhrqQ(String fhrqQ) {
		this.fhrqQ = fhrqQ;
	}


	public String getFhrqZ() {
		return fhrqZ;
	}


	public void setFhrqZ(String fhrqZ) {
		this.fhrqZ = fhrqZ;
	}


	public String getShrqQ() {
		return shrqQ;
	}


	public void setShrqQ(String shrqQ) {
		this.shrqQ = shrqQ;
	}


	public String getShrqZ() {
		return shrqZ;
	}


	public void setShrqZ(String shrqZ) {
		this.shrqZ = shrqZ;
	}

	public int getPageXh() {
		return pageXh;
	}


	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
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


	public String getShDw() {
		return shDw;
	}


	public void setShDw(String shDw) {
		this.shDw = shDw;
	}


	public String getXdrqQ() {
		return xdrqQ;
	}


	public void setXdrqQ(String xdrqQ) {
		this.xdrqQ = xdrqQ;
	}


	public String getXdrqZ() {
		return xdrqZ;
	}


	public void setXdrqZ(String xdrqZ) {
		this.xdrqZ = xdrqZ;
	}


	public String getMbDjxh() {
		return mbDjxh;
	}


	public void setMbDjxh(String mbDjxh) {
		this.mbDjxh = mbDjxh;
	}


	public String getMbmc() {
		return mbmc;
	}


	public void setMbmc(String mbmc) {
		this.mbmc = mbmc;
	}


	public String getYqFhrqDate() {
		return yqFhrqDate;
	}


	public void setYqFhrqDate(String yqFhrqDate) {
		this.yqFhrqDate = yqFhrqDate;
	}


	public String getYqDdrqDate() {
		return yqDdrqDate;
	}


	public void setYqDdrqDate(String yqDdrqDate) {
		this.yqDdrqDate = yqDdrqDate;
	}


	public boolean isMcUseable() {
		return mcUseable;
	}


	public void setMcUseable(boolean mcUseable) {
		this.mcUseable = mcUseable;
	}


	public int getYqDdrq() {
		return yqDdrq;
	}


	public void setYqDdrq(int yqDdrq) {
		this.yqDdrq = yqDdrq;
	}


	public int getYqFhrq() {
		return yqFhrq;
	}


	public void setYqFhrq(int yqFhrq) {
		this.yqFhrq = yqFhrq;
	}


	public String getYkjsfsDm4js() {
		return ykjsfsDm4js;
	}


	public void setYkjsfsDm4js(String ykjsfsDm4js) {
		this.ykjsfsDm4js = ykjsfsDm4js;
	}


	public String getKhlxDm4js() {
		return khlxDm4js;
	}


	public void setKhlxDm4js(String khlxDm4js) {
		this.khlxDm4js = khlxDm4js;
	}


	public String getPsbz() {
		return psbz;
	}


	public void setPsbz(String psbz) {
		this.psbz = psbz;
	}
}
