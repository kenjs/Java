package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_HWXX is created by tools.
 * @author HJH
 */

public class HyPcHwxxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 未发货登记序号
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物或计划单明细序号)
	private String fhrMc;                            // 发货人_名称
	private String fhrDz;                            // 发货人_地址
	private String fhrLxr;                           // 发货人_联系人
	private String fhrLxdh;                          // 发货人_联系电话
	private String fhrXzqhDm;                        // 发货人_行政区划代码
	private String shrMc;                            // 收货人_名称
	private String shrDz;                            // 收货人_地址
	private String shrLxr;                           // 收货人_联系人
	private String shrLxdh;                          // 收货人_联系电话
	private String shrXzqhDm;                        // 收货人_行政区划代码
	private String hwmc;                             // 货物名称
	private String hwDjxh;                           // 货物登记序号
	private String hwxhDjxh;                         // 货物型号登记序号
	private String hwBzHldwDm;                       // 货物_包装_计量单位
	private Double hwSl;                             // 货物_数量
	private String hwSlJldwDm;                       // 货物_数量_计量单位
	private Double hwZl;                             // 货物_重量
	private String hwZlJldwDm;                       // 货物_重量_计量单位
	private Double hwTj;                             // 货物_体积
	private String hwTjJldwDm;                       // 货物_体积_计量单位
	private String yqFhrq;                           // 要求发货日期
	private String yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private String bz;                               // 备注
	private String hdbh;                             // 回单编号
	private Double jsSl;
	private String jsJldwDm;
	private String jldwFlDm;
	private Double sr;
	private Double cbft;
	private String pchwClfsDm;					
	private String zrbmDm;
	private String zrbmDjxh;
	private Double zcHj;                             // 支出_合计
	private Double zcYj;                             // 支出_月结
	private Double zcXf;                             // 支出_现付
	private Double zcHdf;                            // 支出_货到付
	private Double zcThf;                            // 支出_提货付
	private Double zcHf;                             // 支出_回单付
	private Double zcHk;                             // 支出_回扣
	private String ykjsfsDm;
	private String bbh;
	private String yxbz;
	private String zrbmDz;
	private String zrbmLxr;
	private String zrbmLxdh;
	private String zrbmXzqhDm;
	
	private Double srdf;
	private Double yfSjs;
	private String pcfsDm;
	
	private String hwBzJldwMc;
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	private String jsJldwMc;
	private String shrXzqhMc;
	private String shrDjxh;
	private String pchwLsxh;							// 派车时选择的货物保存到临时表，每一个派车单对应一个临时序号
	private Double kfHwsl;								// 可发货物数量

	private String bgDf;//变更到付
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyPcHwxxDomain() {
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取序号(货物或计划单明细序号)
	public String getXh() {
		return this.xh;
	}

	//设置序号(货物或计划单明细序号)
	public void setXh(String xh) {
		this.xh=xh;
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

	//获取收货人_名称
	public String getShrMc() {
		return this.shrMc;
	}

	//设置收货人_名称
	public void setShrMc(String shrMc) {
		this.shrMc=shrMc;
	}

	//获取收货人_地址
	public String getShrDz() {
		return this.shrDz;
	}

	//设置收货人_地址
	public void setShrDz(String shrDz) {
		this.shrDz=shrDz;
	}

	//获取收货人_联系人
	public String getShrLxr() {
		return this.shrLxr;
	}

	//设置收货人_联系人
	public void setShrLxr(String shrLxr) {
		this.shrLxr=shrLxr;
	}

	//获取收货人_联系电话
	public String getShrLxdh() {
		return this.shrLxdh;
	}

	//设置收货人_联系电话
	public void setShrLxdh(String shrLxdh) {
		this.shrLxdh=shrLxdh;
	}

	//获取收货人_行政区划代码
	public String getShrXzqhDm() {
		return this.shrXzqhDm;
	}

	//设置收货人_行政区划代码
	public void setShrXzqhDm(String shrXzqhDm) {
		this.shrXzqhDm=shrXzqhDm;
	}

	//获取货物名称
	public String getHwmc() {
		return this.hwmc;
	}

	//设置货物名称
	public void setHwmc(String hwmc) {
		this.hwmc=hwmc;
	}

	//获取货物登记序号
	public String getHwDjxh() {
		return this.hwDjxh;
	}

	//设置货物登记序号
	public void setHwDjxh(String hwDjxh) {
		this.hwDjxh=hwDjxh;
	}

	//获取货物型号登记序号
	public String getHwxhDjxh() {
		return this.hwxhDjxh;
	}

	//设置货物型号登记序号
	public void setHwxhDjxh(String hwxhDjxh) {
		this.hwxhDjxh=hwxhDjxh;
	}

	//获取货物_包装_计量单位
	public String getHwBzHldwDm() {
		return this.hwBzHldwDm;
	}

	//设置货物_包装_计量单位
	public void setHwBzHldwDm(String hwBzHldwDm) {
		this.hwBzHldwDm=hwBzHldwDm;
	}

	//获取货物_数量
	public Double getHwSl() {
		return this.hwSl;
	}

	//设置货物_数量
	public void setHwSl(Double hwSl) {
		this.hwSl=hwSl;
	}

	//获取货物_数量_计量单位
	public String getHwSlJldwDm() {
		return this.hwSlJldwDm;
	}

	//设置货物_数量_计量单位
	public void setHwSlJldwDm(String hwSlJldwDm) {
		this.hwSlJldwDm=hwSlJldwDm;
	}

	//获取货物_重量
	public Double getHwZl() {
		return this.hwZl;
	}

	//设置货物_重量
	public void setHwZl(Double hwZl) {
		this.hwZl=hwZl;
	}

	//获取货物_重量_计量单位
	public String getHwZlJldwDm() {
		return this.hwZlJldwDm;
	}

	//设置货物_重量_计量单位
	public void setHwZlJldwDm(String hwZlJldwDm) {
		this.hwZlJldwDm=hwZlJldwDm;
	}

	//获取货物_体积
	public Double getHwTj() {
		return this.hwTj;
	}

	//设置货物_体积
	public void setHwTj(Double hwTj) {
		this.hwTj=hwTj;
	}

	//获取货物_体积_计量单位
	public String getHwTjJldwDm() {
		return this.hwTjJldwDm;
	}

	//设置货物_体积_计量单位
	public void setHwTjJldwDm(String hwTjJldwDm) {
		this.hwTjJldwDm=hwTjJldwDm;
	}

	//获取要求发货日期
	public String getYqFhrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yqFhrq);
		}
		catch(Exception e){
			return this.yqFhrq;
		}
	}

	//设置要求发货日期
	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq=yqFhrq;
	}

	//获取要求到达日期
	public String getYqDdrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yqDdrq);
		}
		catch(Exception e){
			return this.yqDdrq;
		}
	}

	//设置要求到达日期
	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq=yqDdrq;
	}

	//获取收货方式代码
	public String getShfsDm() {
		return this.shfsDm;
	}

	//设置收货方式代码
	public void setShfsDm(String shfsDm) {
		this.shfsDm=shfsDm;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取未发货登记序号
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置未发货登记序号
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取回单编号
	public String getHdbh() {
		return this.hdbh;
	}

	//设置回单编号
	public void setHdbh(String hdbh) {
		this.hdbh=hdbh;
	}

	public String getHwBzJldwMc() {
		return hwBzJldwMc;
	}

	public void setHwBzJldwMc(String hwBzJldwMc) {
		this.hwBzJldwMc = hwBzJldwMc;
	}

	public String getHwSlJldwMc() {
		return hwSlJldwMc;
	}

	public void setHwSlJldwMc(String hwSlJldwMc) {
		this.hwSlJldwMc = hwSlJldwMc;
	}

	public String getHwZlJldwMc() {
		return hwZlJldwMc;
	}

	public void setHwZlJldwMc(String hwZlJldwMc) {
		this.hwZlJldwMc = hwZlJldwMc;
	}

	public String getHwTjJldwMc() {
		return hwTjJldwMc;
	}

	public void setHwTjJldwMc(String hwTjJldwMc) {
		this.hwTjJldwMc = hwTjJldwMc;
	}

	public Double getJsSl() {
		return jsSl;
	}

	public void setJsSl(Double jsSl) {
		this.jsSl = jsSl;
	}

	public String getJsJldwDm() {
		return jsJldwDm;
	}

	public void setJsJldwDm(String jsJldwDm) {
		this.jsJldwDm = jsJldwDm;
	}

	public String getJldwFlDm() {
		return jldwFlDm;
	}

	public void setJldwFlDm(String jldwFlDm) {
		this.jldwFlDm = jldwFlDm;
	}

	public Double getSr() {
		return sr;
	}

	public void setSr(Double sr) {
		this.sr = sr;
	}

	public Double getCbft() {
		return cbft;
	}

	public void setCbft(Double cbft) {
		this.cbft = cbft;
	}

	public String getPchwClfsDm() {
		return pchwClfsDm;
	}

	public void setPchwClfsDm(String pchwClfsDm) {
		this.pchwClfsDm = pchwClfsDm;
	}

	public String getZrbmDm() {
		return zrbmDm;
	}

	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm = zrbmDm;
	}

	public String getZrbmDjxh() {
		return zrbmDjxh;
	}

	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh = zrbmDjxh;
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

	public String getPchwLsxh() {
		return pchwLsxh;
	}

	public void setPchwLsxh(String pchwLsxh) {
		this.pchwLsxh = pchwLsxh;
	}

	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
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

	public String getJsJldwMc() {
		return jsJldwMc;
	}

	public void setJsJldwMc(String jsJldwMc) {
		this.jsJldwMc = jsJldwMc;
	}

	public String getShrXzqhMc() {
		return shrXzqhMc;
	}

	public void setShrXzqhMc(String shrXzqhMc) {
		this.shrXzqhMc = shrXzqhMc;
	}

	public String getShrDjxh() {
		return shrDjxh;
	}

	public void setShrDjxh(String shrDjxh) {
		this.shrDjxh = shrDjxh;
	}

	public String getYkjsfsDm() {
		return ykjsfsDm;
	}

	public void setYkjsfsDm(String ykjsfsDm) {
		this.ykjsfsDm = ykjsfsDm;
	}

	public Double getKfHwsl() {
		return kfHwsl;
	}

	public void setKfHwsl(Double kfHwsl) {
		this.kfHwsl = kfHwsl;
	}

	public Double getYfSjs() {
		return yfSjs;
	}

	public void setYfSjs(Double yfSjs) {
		this.yfSjs = yfSjs;
	}

	public Double getSrdf() {
		return srdf;
	}

	public void setSrdf(Double srdf) {
		this.srdf = srdf;
	}

	public String getPcfsDm() {
		return pcfsDm;
	}

	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
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

	public String getBgDf() {
		return bgDf;
	}

	public void setBgDf(String bgDf) {
		this.bgDf = bgDf;
	}
}
