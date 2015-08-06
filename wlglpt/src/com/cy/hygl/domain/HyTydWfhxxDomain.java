package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DOMAIN class FOR HY_TYD_WFHXX is created by tools.
 * @author HJH
 */

public class HyTydWfhxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wfhDjxh;                          // 未发货登记序号(SEQ_FHMX_DJXH)
	private String hwztDm;                           // 货物状态代码(仅包括未提/未发)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 货物明细序号
	private Double hwSl;                             // 货物_数量
	private Double hwZl;                             // 货物_重量
	private Double hwTj;                             // 货物_体积
	private Double hwJssl;
	private String fhrDjxh;                          // 发货人_登记序号
	private String fhrMc;                            // 发货人_名称
	private String fhrDz;                            // 发货人_地址
	private String fhrLxr;                           // 发货人_联系人
	private String fhrLxdh;                          // 发货人_联系电话
	private String fhrXzqhDm;                        // 发货人_行政区划代码
	private String pcbz;                             // 派车标志(Y/N)
	private String pcDjxh;                           // 派车登记序号
	private String yxbz;                             // 有效标志(Y/N)

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	/***********/
	private String shrXzqhDm;
	private String djrCzyDjxh;
	private String djJgbm;
	private String ssJgbm;
	private int pageXh;
	private String ddbh;
	private String hwztMc;
	private String zrbmMc;
	private String fhrXzqhMc;

	private String shrXzqhMc;
	private String hwmc;
	private String bz;
	private String sl;
	private String zl;
	private String tj;
	private String jssl;
	private Double kcsl;			// 库存数量
	private String lb;
	private String fhRq;
	private String shrMc;
	private String shDz;
	private String yqDdrq;
	private String djrMc;
	private String djRq;
	private String djJgmc;
	private String ssJgmc;
	private String shfsMc;
	private String shfsDm;
	private Double df;
	private Double thf;
	private Double hdf;
	private Double srHj;
	private Double srXf;
	private Double srYj;
	private Double srHk;
	private String pchwClfsDm;
    
	private String mswz;   //描述文字
	private String ywId;   //业务ID
	private String lx;     //类型
	private String mkmc;   //模块名称
	private String czrCzyDjxh;//操作人
	private String czrq;   //操作日期
	
	/**查询条件*****/
	private String dw4Query;
	private String fhrXzqhMc4Query;
	private String shrXzqhMc4Query;
	private String ddbh4Query;
	private String khMc4Query;
	private String hwztDm4Query;
	private String lb4Query;
	private String djJgbm4Query;
	private String djrCzyDjxh4Query;
	private String djrqQ;
	private String djrqZ;
	private String fhrqQ;
	private String fhrqZ;
	private String xdrqQ;
	private String xdrqZ;
	
	private Double zcHj;                             // 支出_合计
	private Double zcYj;                             // 支出_月结
	private Double zcXf;                             // 支出_现付
	private Double zcHdf;                            // 支出_货到付
	private Double zcThf;                            // 支出_提货付
	private Double zcDf;                            // 支出_提货付
	private Double zcHf;                             // 支出_回单付
	private Double zcHk;                             // 支出_回扣
	
	/***************派车检索时用到的信息***************/
	private String pcOpenFlag; 										//是否从派车页面的选择货物打开
	private String pcfsDm;
	private List<DmbGgDomain> pchwClfsList;
	private String pchwLsxh;							//派车时选择的货物保存到临时表，每一个派车单对应一个临时序号
	/********派车检索转入部门地址等信息条件*******/
	private String zrbmDjxh;						
	private String tableName;
	
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;
	private String zrbmXzqhMc;
	
	public HyTydWfhxxDomain() {
	}

	//获取未发货登记序号(SEQ_FHMX_DJXH)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置未发货登记序号(SEQ_FHMX_DJXH)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取货物状态代码(仅包括未提/未发)
	public String getHwztDm() {
		return this.hwztDm;
	}

	//设置货物状态代码(仅包括未提/未发)
	public void setHwztDm(String hwztDm) {
		this.hwztDm=hwztDm;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取货物明细序号
	public String getXh() {
		return this.xh;
	}

	//设置货物明细序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取货物_数量
	public Double getHwSl() {
		return this.hwSl;
	}

	//设置货物_数量
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//获取货物_重量
	public Double getHwZl() {
		return this.hwZl;
	}

	//设置货物_重量
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//获取货物_体积
	public Double getHwTj() {
		return this.hwTj;
	}

	//设置货物_体积
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	public Double getHwJssl() {
		return hwJssl;
	}

	public void setHwJssl(Double hwJssl) {
		this.hwJssl = hwJssl;
	}

	//获取发货人_登记序号
	public String getFhrDjxh() {
		return this.fhrDjxh;
	}

	//设置发货人_登记序号
	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh=fhrDjxh;
	}

	//获取发货人_名称
	public String getFhrMc() {
		return this.fhrMc;
	}

	//设置发货人_名称
	public void setFhrMc(String fhrMc) {
		this.fhrMc=fhrMc;
	}

	//获取发货人_地址
	public String getFhrDz() {
		return this.fhrDz;
	}

	//设置发货人_地址
	public void setFhrDz(String fhrDz) {
		this.fhrDz=fhrDz;
	}

	//获取发货人_联系人
	public String getFhrLxr() {
		return this.fhrLxr;
	}

	//设置发货人_联系人
	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr=fhrLxr;
	}

	//获取发货人_联系电话
	public String getFhrLxdh() {
		return this.fhrLxdh;
	}

	//设置发货人_联系电话
	public void setFhrLxdh(String fhrLxdh) {
		this.fhrLxdh=fhrLxdh;
	}

	//获取发货人_行政区划代码
	public String getFhrXzqhDm() {
		return this.fhrXzqhDm;
	}

	//设置发货人_行政区划代码
	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm=fhrXzqhDm;
	}

	//获取派车标志(Y/N)
	public String getPcbz() {
		return this.pcbz;
	}

	//设置派车标志(Y/N)
	public void setPcbz(String pcbz) {
		this.pcbz=pcbz;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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

	public String getDdbh4Query() {
		return ddbh4Query;
	}

	public void setDdbh4Query(String ddbh4Query) {
		this.ddbh4Query = ddbh4Query;
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

	public String getDw4Query() {
		return dw4Query;
	}

	public void setDw4Query(String dw4Query) {
		this.dw4Query = dw4Query;
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

	public String getFhrXzqhMc4Query() {
		return fhrXzqhMc4Query;
	}

	public void setFhrXzqhMc4Query(String fhrXzqhMc4Query) {
		this.fhrXzqhMc4Query = fhrXzqhMc4Query;
	}

	public String getHwztDm4Query() {
		return hwztDm4Query;
	}

	public void setHwztDm4Query(String hwztDm4Query) {
		this.hwztDm4Query = hwztDm4Query;
	}

	public String getKhMc4Query() {
		return khMc4Query;
	}

	public void setKhMc4Query(String khMc4Query) {
		this.khMc4Query = khMc4Query;
	}

	public String getLb4Query() {
		return lb4Query;
	}

	public void setLb4Query(String lb4Query) {
		this.lb4Query = lb4Query;
	}

	public String getShrXzqhMc4Query() {
		return shrXzqhMc4Query;
	}

	public void setShrXzqhMc4Query(String shrXzqhMc4Query) {
		this.shrXzqhMc4Query = shrXzqhMc4Query;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}

	public String getDjrMc() {
		return djrMc;
	}

	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}

	public String getDjRq() {
		return djRq;
	}

	public void setDjRq(String djRq) {
		this.djRq = djRq;
	}

	public String getFhRq() {
		return fhRq;
	}

	public void setFhRq(String fhRq) {
		this.fhRq = fhRq;
	}

	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
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

	public String getJssl() {
		return jssl;
	}

	public void setJssl(String jssl) {
		this.jssl = jssl;
	}

	public String getHwztMc() {
		return hwztMc;
	}

	public void setHwztMc(String hwztMc) {
		this.hwztMc = hwztMc;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
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

	public String getShDz() {
		return shDz;
	}

	public void setShDz(String shDz) {
		this.shDz = shDz;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getDjJgbm() {
		return djJgbm;
	}

	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}

	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}

	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}

	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	public int getPageXh() {
		return pageXh;
	}

	public void setPageXh(int pageXh) {
		this.pageXh = pageXh;
	}

	public String getPcOpenFlag() {
		return pcOpenFlag;
	}

	public void setPcOpenFlag(String pcOpenFlag) {
		this.pcOpenFlag = pcOpenFlag;
	}

	public String getPcfsDm() {
		return pcfsDm;
	}

	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
	}

	public List<DmbGgDomain> getPchwClfsList() {
		if (pchwClfsList == null) {
			pchwClfsList = new ArrayList<DmbGgDomain>();
		}
		return pchwClfsList;
	}

	public void setPchwClfsList(List<DmbGgDomain> pchwClfsList) {
		this.pchwClfsList = pchwClfsList;
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZrbmDz() {
		return zrbmDz;
	}

	public void setZrbmDz(String zrbmDz) {
		this.zrbmDz = zrbmDz;
	}

	public String getZrbmLxr() {
		return zrbmLxr;
	}

	public void setZrbmLxr(String zrbmLxr) {
		this.zrbmLxr = zrbmLxr;
	}

	public String getZrbmLxdh() {
		return zrbmLxdh;
	}

	public void setZrbmLxdh(String zrbmLxdh) {
		this.zrbmLxdh = zrbmLxdh;
	}

	public String getZrbmXzqhDm() {
		return zrbmXzqhDm;
	}

	public void setZrbmXzqhDm(String zrbmXzqhDm) {
		this.zrbmXzqhDm = zrbmXzqhDm;
	}

	public String getZrbmXzqhMc() {
		return zrbmXzqhMc;
	}

	public void setZrbmXzqhMc(String zrbmXzqhMc) {
		this.zrbmXzqhMc = zrbmXzqhMc;
	}

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getShfsMc() {
		return shfsMc;
	}

	public void setShfsMc(String shfsMc) {
		this.shfsMc = shfsMc;
	}

	public String getZrbmMc() {
		return zrbmMc;
	}

	public void setZrbmMc(String zrbmMc) {
		this.zrbmMc = zrbmMc;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public Double getDf() {
		return df;
	}

	public void setDf(Double df) {
		this.df = df;
	}

	public Double getThf() {
		return thf;
	}

	public void setThf(Double thf) {
		this.thf = thf;
	}

	public Double getHdf() {
		return hdf;
	}

	public void setHdf(Double hdf) {
		this.hdf = hdf;
	}

	public Double getSrHj() {
		return srHj;
	}

	public void setSrHj(Double srHj) {
		this.srHj = srHj;
	}

	public String getPchwClfsDm() {
		return pchwClfsDm;
	}

	public void setPchwClfsDm(String pchwClfsDm) {
		this.pchwClfsDm = pchwClfsDm;
	}

	public Double getKcsl() {
		return kcsl;
	}

	public void setKcsl(Double kcsl) {
		this.kcsl = kcsl;
	}

	public Double getSrXf() {
		return srXf;
	}

	public void setSrXf(Double srXf) {
		this.srXf = srXf;
	}

	public Double getSrYj() {
		return srYj;
	}

	public void setSrYj(Double srYj) {
		this.srYj = srYj;
	}

	public Double getSrHk() {
		return srHk;
	}

	public void setSrHk(Double srHk) {
		this.srHk = srHk;
	}

	public String getMswz() {
		return mswz;
	}

	public void setMswz(String mswz) {
		this.mswz = mswz;
	}

	public String getYwId() {
		return ywId;
	}

	public void setYwId(String ywId) {
		this.ywId = ywId;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getCzrCzyDjxh() {
		return czrCzyDjxh;
	}

	public void setCzrCzyDjxh(String czrCzyDjxh) {
		this.czrCzyDjxh = czrCzyDjxh;
	}

	public String getCzrq() {
		return czrq;
	}

	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}

	public Double getZcHj() {
		return zcHj;
	}

	public void setZcHj(Double zcHj) {
		this.zcHj = zcHj;
	}

	public Double getZcYj() {
		return zcYj;
	}

	public void setZcYj(Double zcYj) {
		this.zcYj = zcYj;
	}

	public Double getZcXf() {
		return zcXf;
	}

	public void setZcXf(Double zcXf) {
		this.zcXf = zcXf;
	}

	public Double getZcHdf() {
		return zcHdf;
	}

	public void setZcHdf(Double zcHdf) {
		this.zcHdf = zcHdf;
	}

	public Double getZcThf() {
		return zcThf;
	}

	public void setZcThf(Double zcThf) {
		this.zcThf = zcThf;
	}

	public Double getZcDf() {
		return zcDf;
	}

	public void setZcDf(Double zcDf) {
		this.zcDf = zcDf;
	}

	public Double getZcHf() {
		return zcHf;
	}

	public void setZcHf(Double zcHf) {
		this.zcHf = zcHf;
	}

	public Double getZcHk() {
		return zcHk;
	}

	public void setZcHk(Double zcHk) {
		this.zcHk = zcHk;
	}
}
