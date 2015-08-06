package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_HW_DDXX is created by tools.
 * @author HJH
 */

public class HyTydglDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ddDjxh;                             // 订单登记序号(SEQ_DD_DJXH)
	private String ddbh;                             // 订单编号
	private Date xdrq;								//	下单日期
	private String ddflDm;							 // 订单分类代码
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)
	private String djrCzyDjxh;                       // 登记人
	private Date djrq;                               // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                               // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                               // 修改日期
	private Double srHj;                             // 收入_合计
	private Double srYj;                             // 收入_月结
	private Double srXf;                             // 收入_现付
	private Double srHdf;                            // 收入_货到付
	private Double srThf;                            // 收入_提货付
	private Double srHf;                             // 收入_回单付
	private Double srHk;                             // 收入_回扣
	private Double srDf;							 // 收入_到付
	private String dzztDm;							 // 对账状态代码
	private String hwztDm;
	private String xtcs20002;                       //订单规则
	private String xtcs20014;
	private String xtcs20000;//公司模式
	
	/************************** 该托单已派车的货物数量，若已派车，则不允许删除或修改 ***************************/
	private Integer ypcHwNum;
	/**************************专线 保存货物表里begin********************************/
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
	private Date yqFhrq;                           // 要求发货日期
	private Date yqDdrq;                           // 要求到达日期
	
	private String shfsDm;                           // 收货方式代码
	private Long thflDm;							 // 提货分类代码
	private String ykjsfsDm;                         // 余款结算方式代码
	private String yjjsfsDm;                         // 运价结算方式代码
	private String psbz;							 // 配送标志
	
	
	private Double srYsf; //运输费
	private Double srBjf; //保价费
	private Double srPsf; //配送费
	private Double fySmjz; //声明价值
	private String yjWjBz;                           //已结未结标志
	private Double fyDshk;						//	代收货款
	/**************************专线 保存货物表里end********************************/
	
	/**************************代码对应的名称********************************/
	private String djrMc;
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String fhrXzqhMc;
	private String shrXzqhMc;
	private String djJgmc;
	private String ssJgmc;
	private String hwztMc;
	private String shfsMc;
	/**************************代码对应的名称********************************/
	private String xh;
	private String hwmc;                             // 货物名称
	private String hwbz;
	private String sl;
	private String zl;
	private String tj;
	private String jsSl;
	
	private Double hwSl;
	private Double hwZl;
	private Double hwTj;
	
	private String zhdzDjxh;
	private String khlxDm4js;
	private String ykjsfsDm4js;
	/*************货物信息***********/
	
	private List<String> hwXhs;
	private List<String> hwXhsCopy;
	
	/*************检索条件***********/
	private String ssJgbm4Query;
	private String djJgbm4Query;
	private String djrCzyDjxh4Query;
	private String fhrDjxh4Query;
	private String fhrMc4Query;
	private String shrDjxh4Query;
	private String shrMc4Query;
	private String ddbh4Query;
	private int pageXh;
	
	private String ddbhQ;
	private String ddbhZ;
	private String fhrMc4;
	private String shrMc4;
	private String hwMc4;
	private Double hwSl4;
	private Double hwZl4;
	private Double hwTj4;
	private Double zsr4;
	private String xjBz4;
	private String dfBz4;
	private String yjBz4;
	private String shfsDm4;
	private String yjWjBz4;
	/*************copy页面检索条件***********/
	private String shDw;//收货单位
	private String xdrqQ;//下单日期起
	private String xdrqZ;//下单日期止
	
	private String ddDjxhCopy;					//用来复制的订单序号
	private HyTydHwmxDomain hwmxDomain;
	
	private List<HyTydHwmxDomain> hwList;
	
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


	public HyTydglDomain() {
	}


	public HyTydHwmxDomain getHwmxDomain() {
		if (hwmxDomain == null) {
			hwmxDomain = new HyTydHwmxDomain();
		}
		return hwmxDomain;
	}


	public void setHwmxDomain(HyTydHwmxDomain hwmxDomain) {
		this.hwmxDomain = hwmxDomain;
	}


	public List<HyTydHwmxDomain> getHwList() {
		if (hwList == null) {
			hwList = new ArrayList<HyTydHwmxDomain>();
		}
		return hwList;
	}


	public void setHwList(List<HyTydHwmxDomain> hwList) {
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


	public String getDdflDm() {
		return ddflDm;
	}


	public void setDdflDm(String ddflDm) {
		this.ddflDm = ddflDm;
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


	public Date getYqFhrq() {
		return yqFhrq;
	}


	public void setYqFhrq(Date yqFhrq) {
		this.yqFhrq = yqFhrq;
	}


	public Date getYqDdrq() {
		return yqDdrq;
	}


	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
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


	public Integer getYpcHwNum() {
		return ypcHwNum;
	}


	public void setYpcHwNum(Integer ypcHwNum) {
		this.ypcHwNum = ypcHwNum;
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


	public Double getSrDf() {
		return srDf;
	}


	public void setSrDf(Double srDf) {
		this.srDf = srDf;
	}


	public String getHwztDm() {
		return hwztDm;
	}


	public void setHwztDm(String hwztDm) {
		this.hwztDm = hwztDm;
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


	public String getJsSl() {
		return jsSl;
	}


	public void setJsSl(String jsSl) {
		this.jsSl = jsSl;
	}


	public Long getThflDm() {
		return thflDm;
	}


	public void setThflDm(Long thflDm) {
		this.thflDm = thflDm;
	}

	public String getPsbz() {
		return psbz;
	}


	public void setPsbz(String psbz) {
		this.psbz = psbz;
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


	public String getSsJgbm4Query() {
		return ssJgbm4Query;
	}


	public void setSsJgbm4Query(String ssJgbm4Query) {
		this.ssJgbm4Query = ssJgbm4Query;
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


	public String getZhdzDjxh() {
		return zhdzDjxh;
	}


	public void setZhdzDjxh(String zhdzDjxh) {
		this.zhdzDjxh = zhdzDjxh;
	}


	public String getKhlxDm4js() {
		return khlxDm4js;
	}


	public void setKhlxDm4js(String khlxDm4js) {
		this.khlxDm4js = khlxDm4js;
	}


	public String getYkjsfsDm4js() {
		return ykjsfsDm4js;
	}


	public void setYkjsfsDm4js(String ykjsfsDm4js) {
		this.ykjsfsDm4js = ykjsfsDm4js;
	}


	public String getDdDjxhCopy() {
		return ddDjxhCopy;
	}


	public void setDdDjxhCopy(String ddDjxhCopy) {
		this.ddDjxhCopy = ddDjxhCopy;
	}


	public String getDzztDm() {
		return dzztDm;
	}


	public void setDzztDm(String dzztDm) {
		this.dzztDm = dzztDm;
	}

	public List<String> getHwXhsCopy() {
		if (hwXhsCopy == null) {
			hwXhsCopy = new ArrayList<String>();
		}
		return hwXhsCopy;
	}


	public void setHwXhsCopy(List<String> hwXhsCopy) {
		this.hwXhsCopy = hwXhsCopy;
	}

	public String getXtcs20002() {
		return xtcs20002;
	}


	public void setXtcs20002(String xtcs20002) {
		this.xtcs20002 = xtcs20002;
	}


	public String getXtcs20014() {
		return xtcs20014;
	}


	public void setXtcs20014(String xtcs20014) {
		this.xtcs20014 = xtcs20014;
	}


	public String getXh() {
		return xh;
	}


	public void setXh(String xh) {
		this.xh = xh;
	}


	public Double getSrYsf() {
		return srYsf;
	}


	public void setSrYsf(Double srYsf) {
		this.srYsf = srYsf;
	}


	public Double getSrBjf() {
		return srBjf;
	}


	public void setSrBjf(Double srBjf) {
		this.srBjf = srBjf;
	}


	public Double getSrPsf() {
		return srPsf;
	}


	public void setSrPsf(Double srPsf) {
		this.srPsf = srPsf;
	}


	public Double getFySmjz() {
		return fySmjz;
	}


	public void setFySmjz(Double fySmjz) {
		this.fySmjz = fySmjz;
	}


	public Double getFyDshk() {
		return fyDshk;
	}


	public void setFyDshk(Double fyDshk) {
		this.fyDshk = fyDshk;
	}


	public String getYjWjBz() {
		return yjWjBz;
	}


	public void setYjWjBz(String yjWjBz) {
		this.yjWjBz = yjWjBz;
	}


	public String getFhrMc4() {
		return fhrMc4;
	}


	public void setFhrMc4(String fhrMc4) {
		this.fhrMc4 = fhrMc4;
	}


	public String getShrMc4() {
		return shrMc4;
	}


	public void setShrMc4(String shrMc4) {
		this.shrMc4 = shrMc4;
	}


	public String getHwMc4() {
		return hwMc4;
	}


	public void setHwMc4(String hwMc4) {
		this.hwMc4 = hwMc4;
	}

	public Double getHwSl4() {
		return hwSl4;
	}


	public void setHwSl4(Double hwSl4) {
		this.hwSl4 = hwSl4;
	}


	public Double getHwZl4() {
		return hwZl4;
	}


	public void setHwZl4(Double hwZl4) {
		this.hwZl4 = hwZl4;
	}


	public Double getHwTj4() {
		return hwTj4;
	}


	public void setHwTj4(Double hwTj4) {
		this.hwTj4 = hwTj4;
	}


	public Double getZsr4() {
		return zsr4;
	}


	public void setZsr4(Double zsr4) {
		this.zsr4 = zsr4;
	}


	public String getXjBz4() {
		return xjBz4;
	}


	public void setXjBz4(String xjBz4) {
		this.xjBz4 = xjBz4;
	}


	public String getDfBz4() {
		return dfBz4;
	}


	public void setDfBz4(String dfBz4) {
		this.dfBz4 = dfBz4;
	}


	public String getYjBz4() {
		return yjBz4;
	}


	public void setYjBz4(String yjBz4) {
		this.yjBz4 = yjBz4;
	}


	public String getShfsDm4() {
		return shfsDm4;
	}


	public void setShfsDm4(String shfsDm4) {
		this.shfsDm4 = shfsDm4;
	}


	public String getDdbhQ() {
		return ddbhQ;
	}


	public void setDdbhQ(String ddbhQ) {
		this.ddbhQ = ddbhQ;
	}


	public String getDdbhZ() {
		return ddbhZ;
	}


	public void setDdbhZ(String ddbhZ) {
		this.ddbhZ = ddbhZ;
	}


	public String getYjWjBz4() {
		return yjWjBz4;
	}


	public void setYjWjBz4(String yjWjBz4) {
		this.yjWjBz4 = yjWjBz4;
	}


	public Double getHwSl() {
		return hwSl;
	}


	public void setHwSl(Double hwSl) {
		this.hwSl = hwSl;
	}


	public Double getHwZl() {
		return hwZl;
	}


	public void setHwZl(Double hwZl) {
		this.hwZl = hwZl;
	}


	public Double getHwTj() {
		return hwTj;
	}


	public void setHwTj(Double hwTj) {
		this.hwTj = hwTj;
	}


	public String getXtcs20000() {
		return xtcs20000;
	}


	public void setXtcs20000(String xtcs20000) {
		this.xtcs20000 = xtcs20000;
	}


}
