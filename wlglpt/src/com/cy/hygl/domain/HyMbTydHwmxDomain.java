package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_MB_TYD_HWMX is created by tools.
 * @author HJH
 */

public class HyMbTydHwmxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String mbDjxh;                           // 模版登记序号
	private String xh;                               // 货物明细序号
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
	private String yxbz;                             // 有效标志(Y/N)
	private String hwflDm;                           // 货物分类代码
	private Double jsSl;                             // 结算数量
	private String jsJldwDm;                         // 结算计量单位
	private String jsJldwFlDm;                       // 结算计量单位分类代码
	private String hdbh;                             // 回单编号(多个已逗号分隔)
	private Double srHj;                             // 收入_小计
	private Double srYj;                             // 收入_月结
	private Double srXf;                             // 收入_现付
	private Double srHdf;                            // 收入_货到付
	private Double srThf;                            // 收入_提货付
	private Double srHf;                             // 收入_回单付
	private Double srHk;                             // 收入_回扣
	private String fhrDjxh;                          // 发货人_登记序号
	private String fhrMc;                            // 发货人_名称
	private String fhrDz;                            // 发货人_地址
	private String fhrLxr;                           // 发货人_联系人
	private String fhrLxdh;                          // 发货人_联系电话
	private String fhrXzqhDm;                        // 发货人_行政区划代码
	private String shrDjxh;                          // 收货人_登记序号
	private String shrMc;                            // 收货人_名称
	private String shrDz;                            // 收货人_地址
	private String shrLxr;                           // 收货人_联系人
	private String shrLxdh;                          // 收货人_联系电话
	private String shrXzqhDm;                        // 收货人_行政区划代码
	private String yqFhrq;                           // 要求发货日期
	private String yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private String thflDm;                           // 提货分类代码
	private String ykjsfsDm;                         // 余款结算方式代码
	private String yjjsfsDm;                         // 运价结算方式代码
	private String psbz;                             // 配送标志(Y/N)
	
	private Long ddDjxh;
	
	private String hwbzHldwMc;
	private String hwSlJldwMc;
	private String hwZlJldwMc;
	private String hwTjJldwMc;
	private String fhrXzqhMc;                        
	private String shrXzqhMc;                        
	private String shfsMc;
	
	private String qyHwBzJldwDm;
	private String qyHwSlJldwDm;
	private String qyHwZlJldwDm;
	private String qyHwTjJldwDm;
	private Double ZlTjProportion;
	
	private String tempFlag;					//是否是临时数据，'Y':是  'N':否
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyMbTydHwmxDomain() {
	}

	//获取模版登记序号
	public String getMbDjxh() {
		return this.mbDjxh;
	}

	//设置模版登记序号
	public void setMbDjxh(String mbDjxh) {
		this.mbDjxh=mbDjxh;
	}

	//获取货物明细序号
	public String getXh() {
		return this.xh;
	}

	//设置货物明细序号
	public void setXh(String xh) {
		this.xh=xh;
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

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取货物分类代码
	public String getHwflDm() {
		return this.hwflDm;
	}

	//设置货物分类代码
	public void setHwflDm(String hwflDm) {
		this.hwflDm=hwflDm;
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

	public String getJsJldwFlDm() {
		return jsJldwFlDm;
	}

	public void setJsJldwFlDm(String jsJldwFlDm) {
		this.jsJldwFlDm = jsJldwFlDm;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
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

	public String getYqFhrq() {
		return yqFhrq;
	}

	public void setYqFhrq(String yqFhrq) {
		this.yqFhrq = yqFhrq;
	}

	public String getYqDdrq() {
		return yqDdrq;
	}

	public void setYqDdrq(String yqDdrq) {
		this.yqDdrq = yqDdrq;
	}

	public String getShfsDm() {
		return shfsDm;
	}

	public void setShfsDm(String shfsDm) {
		this.shfsDm = shfsDm;
	}

	public String getThflDm() {
		return thflDm;
	}

	public void setThflDm(String thflDm) {
		this.thflDm = thflDm;
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

	public String getPsbz() {
		return psbz;
	}

	public void setPsbz(String psbz) {
		this.psbz = psbz;
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

	public String getHwbzHldwMc() {
		return hwbzHldwMc;
	}

	public void setHwbzHldwMc(String hwbzHldwMc) {
		this.hwbzHldwMc = hwbzHldwMc;
	}

	public String getHwSlJldwMc() {
		return hwSlJldwMc;
	}

	public void setHwSlJldwMc(String hwSlJldwMc) {
		this.hwSlJldwMc = hwSlJldwMc;
	}

	public String getHwTjJldwMc() {
		return hwTjJldwMc;
	}

	public void setHwTjJldwMc(String hwTjJldwMc) {
		this.hwTjJldwMc = hwTjJldwMc;
	}

	public String getHwZlJldwMc() {
		return hwZlJldwMc;
	}

	public void setHwZlJldwMc(String hwZlJldwMc) {
		this.hwZlJldwMc = hwZlJldwMc;
	}

	public String getQyHwBzJldwDm() {
		return qyHwBzJldwDm;
	}

	public void setQyHwBzJldwDm(String qyHwBzJldwDm) {
		this.qyHwBzJldwDm = qyHwBzJldwDm;
	}

	public String getQyHwSlJldwDm() {
		return qyHwSlJldwDm;
	}

	public void setQyHwSlJldwDm(String qyHwSlJldwDm) {
		this.qyHwSlJldwDm = qyHwSlJldwDm;
	}

	public String getQyHwZlJldwDm() {
		return qyHwZlJldwDm;
	}

	public void setQyHwZlJldwDm(String qyHwZlJldwDm) {
		this.qyHwZlJldwDm = qyHwZlJldwDm;
	}

	public String getQyHwTjJldwDm() {
		return qyHwTjJldwDm;
	}

	public void setQyHwTjJldwDm(String qyHwTjJldwDm) {
		this.qyHwTjJldwDm = qyHwTjJldwDm;
	}

	public Double getZlTjProportion() {
		return ZlTjProportion;
	}

	public void setZlTjProportion(Double zlTjProportion) {
		ZlTjProportion = zlTjProportion;
	}

	public String getTempFlag() {
		return tempFlag;
	}

	public void setTempFlag(String tempFlag) {
		this.tempFlag = tempFlag;
	}

	public Long getDdDjxh() {
		return ddDjxh;
	}

	public void setDdDjxh(Long ddDjxh) {
		this.ddDjxh = ddDjxh;
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

	public String getShfsMc() {
		return shfsMc;
	}

	public void setShfsMc(String shfsMc) {
		this.shfsMc = shfsMc;
	}
}
