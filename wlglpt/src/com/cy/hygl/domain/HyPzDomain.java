package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PZ is created by tools.
 * @author HJH
 */

public class HyPzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pzDjxh;                           // 配载登记序号(SEQ_PZ_DJXH)
	private String hzJgbm;                           // 货站机构编码
	private String clxhwhDjxh;                       // 车辆型号维护序号
	private Double clCz;                             // 车辆_承重(吨)
	private Double clTj;                             // 车辆_体积(方)
	private Double clCd;                             // 车辆_长度(米)
	private Double clKd;                             // 车辆_宽度(米)
	private Double clGd;                             // 车辆_高度(米)
	private Double pzCz;                             // 配载_承重(吨)
	private Double pzTj;                             // 配载_体积(方)
	private Double pzCd;                             // 配载_长度(米)
	private Double pzKd;                             // 配载_宽度(米)
	private Double pzGd;                             // 配载_高度(米)
	private Double pzsr;                             // 配载收入
	private Double pzcb;                             // 配载预计成本
	private Double pzpsf;                            // 配载预计配送费
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private Date cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private Date xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private String listPc;					// 是否为列表页面点派车，列表页面点派车直接从数据库配载表中已有数据读取；若是mx页面中点派车，则从页面传数据过去
	
	/*******************检索列表****************************/
	private String fhrDjxh;
	private String hzmc;
	private String clxh;
	private String fhrXzqhDm;
	private String fhrXzqhMc;
	private String shrXzqhDm;
	private String shrXzqhMc;
	private String ddbh;							//订单编号
	private String hwMc;    //货物名称
	private String hwbz;    //包装
	private String sl;      //数量
	private String zl;      //重量
	private String tj;      //体积
	private String fhrMc;   // 发货人_名称
	private String fhrDz;   // 发货人_地址
	private Date yqFhrq;    // 要求发货日期
	private String shrMc;   //收货人
	private String shrDz;   // 收货人_地址
	private Date yqDdrq;    // 要求到达日期
	private Long pageXh;

	/*********************货物选择页面***********************/
	/**查询条件*****/
	private String dw4Query;
	private String djJgbm4Query;
	private String lb4Query;
	private String ddbh4Query;
	private String hwztDm4Query;
	private String fhrqQ;
	private String fhrqZ;
	
	private String pchwLsxh;							//配载时选择的货物保存到临时表，每一个配载单对应一个临时序号
	private String wfhXhs;
	private List<String> hwXh4PcDel;
	private List<String> tempBz;
	
	private List<String> wfhDjxhs;
	private List<Double> hwSls;
	private List<Double> hwZls;
	private List<Double> hwTjs;
	private List<Double> jssls;
	private List<String> bbhs;
	
	private List<HyTydWfhxxDomain> wfhList;
	private List<HyTydWfhxxDomain> pzHwxxList;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<BaseBusinessDomain> pzList;
	private PzQingdanDomain qingDan;
	public PzQingdanDomain getQingDan() {
		if(qingDan==null){
			qingDan=new PzQingdanDomain();
		}
		return qingDan;
	}

	public void setQingDan(PzQingdanDomain qingDan) {
		this.qingDan = qingDan;
	}

	public List<BaseBusinessDomain> getPzList() {
		if(pzList==null){
			pzList=new ArrayList<BaseBusinessDomain>();
		}
		return pzList;
	}

	public void setPzList(List<BaseBusinessDomain> pzList) {
		this.pzList = pzList;
	}

	public HyPzDomain() {
	}

	//获取配载登记序号(SEQ_PZ_DJXH)
	public String getPzDjxh() {
		return this.pzDjxh;
	}

	//设置配载登记序号(SEQ_PZ_DJXH)
	public void setPzDjxh(String pzDjxh) {
		this.pzDjxh=pzDjxh;
	}

	//获取货站机构编码
	public String getHzJgbm() {
		return this.hzJgbm;
	}

	//设置货站机构编码
	public void setHzJgbm(String hzJgbm) {
		this.hzJgbm=hzJgbm;
	}

	//获取车辆型号维护序号
	public String getClxhwhDjxh() {
		return this.clxhwhDjxh;
	}

	//设置车辆型号维护序号
	public void setClxhwhDjxh(String clxhwhDjxh) {
		this.clxhwhDjxh=clxhwhDjxh;
	}

	//获取车辆_承重(吨)
	public Double getClCz() {
		return this.clCz;
	}

	//设置车辆_承重(吨)
	public void setClCz(Double clCz) {
		this.clCz=clCz;
	}

	//获取车辆_体积(方)
	public Double getClTj() {
		return this.clTj;
	}

	//设置车辆_体积(方)
	public void setClTj(Double clTj) {
		this.clTj=clTj;
	}

	//获取车辆_长度(米)
	public Double getClCd() {
		return this.clCd;
	}

	//设置车辆_长度(米)
	public void setClCd(Double clCd) {
		this.clCd=clCd;
	}

	//获取车辆_宽度(米)
	public Double getClKd() {
		return this.clKd;
	}

	//设置车辆_宽度(米)
	public void setClKd(Double clKd) {
		this.clKd=clKd;
	}

	//获取车辆_高度(米)
	public Double getClGd() {
		return this.clGd;
	}

	//设置车辆_高度(米)
	public void setClGd(Double clGd) {
		this.clGd=clGd;
	}

	//获取配载_承重(吨)
	public Double getPzCz() {
		return this.pzCz;
	}

	//设置配载_承重(吨)
	public void setPzCz(Double pzCz) {
		this.pzCz=pzCz;
	}

	//获取配载_体积(方)
	public Double getPzTj() {
		return this.pzTj;
	}

	//设置配载_体积(方)
	public void setPzTj(Double pzTj) {
		this.pzTj=pzTj;
	}

	//获取配载_长度(米)
	public Double getPzCd() {
		return this.pzCd;
	}

	//设置配载_长度(米)
	public void setPzCd(Double pzCd) {
		this.pzCd=pzCd;
	}

	//获取配载_宽度(米)
	public Double getPzKd() {
		return this.pzKd;
	}

	//设置配载_宽度(米)
	public void setPzKd(Double pzKd) {
		this.pzKd=pzKd;
	}

	//获取配载_高度(米)
	public Double getPzGd() {
		return this.pzGd;
	}

	//设置配载_高度(米)
	public void setPzGd(Double pzGd) {
		this.pzGd=pzGd;
	}

	//获取配载收入
	public Double getPzsr() {
		return this.pzsr;
	}

	//设置配载收入
	public void setPzsr(Double pzsr) {
		this.pzsr=pzsr;
	}

	//获取配载预计成本
	public Double getPzcb() {
		return this.pzcb;
	}

	//设置配载预计成本
	public void setPzcb(Double pzcb) {
		this.pzcb=pzcb;
	}

	//获取配载预计配送费
	public Double getPzpsf() {
		return this.pzpsf;
	}

	//设置配载预计配送费
	public void setPzpsf(Double pzpsf) {
		this.pzpsf=pzpsf;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
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

	public String getDw4Query() {
		return dw4Query;
	}

	public void setDw4Query(String dw4Query) {
		this.dw4Query = dw4Query;
	}

	public String getDjJgbm4Query() {
		return djJgbm4Query;
	}

	public void setDjJgbm4Query(String djJgbm4Query) {
		this.djJgbm4Query = djJgbm4Query;
	}

	public String getLb4Query() {
		return lb4Query;
	}

	public void setLb4Query(String lb4Query) {
		this.lb4Query = lb4Query;
	}

	public String getDdbh4Query() {
		return ddbh4Query;
	}

	public void setDdbh4Query(String ddbh4Query) {
		this.ddbh4Query = ddbh4Query;
	}

	public String getHwztDm4Query() {
		return hwztDm4Query;
	}

	public void setHwztDm4Query(String hwztDm4Query) {
		this.hwztDm4Query = hwztDm4Query;
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

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getWfhXhs() {
		return wfhXhs;
	}

	public void setWfhXhs(String wfhXhs) {
		this.wfhXhs = wfhXhs;
	}

	public List<String> getHwXh4PcDel() {
		return hwXh4PcDel;
	}

	public void setHwXh4PcDel(List<String> hwXh4PcDel) {
		this.hwXh4PcDel = hwXh4PcDel;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getFhrXzqhDm() {
		return fhrXzqhDm;
	}

	public void setFhrXzqhDm(String fhrXzqhDm) {
		this.fhrXzqhDm = fhrXzqhDm;
	}

	public String getFhrXzqhMc() {
		return fhrXzqhMc;
	}

	public void setFhrXzqhMc(String fhrXzqhMc) {
		this.fhrXzqhMc = fhrXzqhMc;
	}

	public String getShrXzqhDm() {
		return shrXzqhDm;
	}

	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm = shrXzqhDm;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getHwMc() {
		return hwMc;
	}

	public void setHwMc(String hwMc) {
		this.hwMc = hwMc;
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

	public Date getYqFhrq() {
		return yqFhrq;
	}

	public void setYqFhrq(Date yqFhrq) {
		this.yqFhrq = yqFhrq;
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

	public Date getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(Date yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public Long getPageXh() {
		return pageXh;
	}

	public void setPageXh(Long pageXh) {
		this.pageXh = pageXh;
	}

	public String getHzmc() {
		return hzmc;
	}

	public void setHzmc(String hzmc) {
		this.hzmc = hzmc;
	}

	public List<String> getTempBz() {
		return tempBz;
	}

	public void setTempBz(List<String> tempBz) {
		this.tempBz = tempBz;
	}

	public List<String> getWfhDjxhs() {
		if (wfhDjxhs == null) {
			wfhDjxhs = new ArrayList<String>();
		}
		return wfhDjxhs;
	}

	public void setWfhDjxhs(List<String> wfhDjxhs) {
		this.wfhDjxhs = wfhDjxhs;
	}

	public List<Double> getHwSls() {
		if (hwSls == null) {
			hwSls = new ArrayList<Double>();
		}
		return hwSls;
	}

	public void setHwSls(List<Double> hwSls) {
		this.hwSls = hwSls;
	}

	public List<Double> getHwZls() {
		if (hwZls == null) {
			hwZls = new ArrayList<Double>();
		}
		return hwZls;
	}

	public void setHwZls(List<Double> hwZls) {
		this.hwZls = hwZls;
	}

	public List<Double> getHwTjs() {
		if (hwTjs == null) {
			hwTjs = new ArrayList<Double>();
		}
		return hwTjs;
	}

	public void setHwTjs(List<Double> hwTjs) {
		this.hwTjs = hwTjs;
	}

	public List<Double> getJssls() {
		if (jssls == null) {
			jssls = new ArrayList<Double>();
		}
		return jssls;
	}

	public void setJssls(List<Double> jssls) {
		this.jssls = jssls;
	}

	public String getListPc() {
		return listPc;
	}

	public void setListPc(String listPc) {
		this.listPc = listPc;
	}

	public List<String> getBbhs() {
		if (bbhs == null) {
			bbhs = new ArrayList<String>();
		}
		return bbhs;
	}

	public void setBbhs(List<String> bbhs) {
		this.bbhs = bbhs;
	}

	public List<HyTydWfhxxDomain> getWfhList() {
		if (wfhList == null) {
			wfhList = new ArrayList<HyTydWfhxxDomain>();
		}
		return wfhList;
	}

	public void setWfhList(List<HyTydWfhxxDomain> wfhList) {
		this.wfhList = wfhList;
	}

	public List<HyTydWfhxxDomain> getPzHwxxList() {
		if (pzHwxxList == null) {
			pzHwxxList = new ArrayList<HyTydWfhxxDomain>();
		}
		return pzHwxxList;
	}

	public void setPzHwxxList(List<HyTydWfhxxDomain> pzHwxxList) {
		this.pzHwxxList = pzHwxxList;
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
