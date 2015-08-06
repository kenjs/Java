package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * @author HJH
 */

public class DdcbshDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String xh;                           // 序号
	private String wsspxh;                           // 文书审批序号(SEQ_WS_SPXH)
	private String spxh;                           // 审批序号(从1开始递增)
	private String jdxh;							//节点序号
	private Integer fsthbz;                             // 发送退回标志(1 发送,2 退回)
	private String fsrmc;                             // 发送人名称
	private String fsrq;                         // 发送日期
	private String spjzsj;                           // 审批截止时间
	private String cqbz;                             // 超期标志(Y/N)
	private String sprmc;                           // 审批人名称
	private String sprq;                       // 审批日期
	private String spjg;                             // 审批结果 
	private Double zsr;
	private Double yfHj;                             // 总运费
	private Double yfYfyf;
	private String pcdh;                             // 派车单号
	private String pcrq;                             // 派车日期
	private String pcfsMc;								//派车方式
	private String pcfsDm;                             // 派车方式代码
	private String pcfsmc;                             // 派车方式名称
	private String zcfsDm;                             // 装车方式代码
	private String zcfsmc;                             // 装车方式名称
	private String clhm;                             // 车辆号码
	private String gchm;                             // 挂车号码
	private String sjxm;                             // 司机姓名
	private String pcrmc;                             // 派车人名称
	private String pcjgbm;                            // 派车部门
	private String pcbmmc;                            // 派车部门名称
	private String ssjgbm;                            // 所属机构
	private String ssjgmc;                            // 所属机构名称
	private String zrbmDm;                            // 转入部门代码
	private String zrbmmc;                            // 转入部门名称
	private String ddbh;							  //订单编号
	private String ddDjxh;
	private String sfd;                            // 始发地
	private String mdd;                            // 目的地
	private String hwmc;                            // 货物名称
	private String bz;                            // 包装
	private String sl;                            // 数量
	private String zl;                            // 重量
	private String tj;                            // 体积
	private String jssl;                            // 结算数量
	private String fhrmc;                            // 发货人名称
	private String fhrdz;                            // 发货人地址
	private String yqfhrq;                            // 要求发货日期
	private String shrmc;                            // 收货人_名称
	private String shrdz;                            // 收货人_地址
	private String yqddrq;                            // 要求到达日期
	private String pcDjxh;

	private String pchwClfsDm;                       //派车货物处理方式代码
	private Double zcHj;                             // 支出_合计
	private Double zcYj;                             // 支出_月结
	private Double zcXf;                             // 支出_现付
	private Double zcHdf;                            // 支出_货到付
	private Double zcThf;                            // 支出_提货付
	private Double zcDf;                            // 支出_提货付
	private Double zcHf;                             // 支出_回单付
	private Double zcHk;                             // 支出_回扣
	
	private List<DdcbshDomain> dataList; 		 //查询列表
	
	private String rqQ;//派车日期起
	private String rqZ;//派车日期止
	private String shbz;//发送退回标志

	public DdcbshDomain() {
	}

	

	public String getRqQ() {
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	public String getRqZ() {
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}



	public String getXh() {
		return xh;
	}



	public void setXh(String xh) {
		this.xh = xh;
	}



	public String getWsspxh() {
		return wsspxh;
	}



	public void setWsspxh(String wsspxh) {
		this.wsspxh = wsspxh;
	}



	public String getSpxh() {
		return spxh;
	}



	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}



	public String getJdxh() {
		return jdxh;
	}



	public void setJdxh(String jdxh) {
		this.jdxh = jdxh;
	}



	public Integer getFsthbz() {
		return fsthbz;
	}



	public void setFsthbz(Integer fsthbz) {
		this.fsthbz = fsthbz;
	}



	public String getFsrmc() {
		return fsrmc;
	}



	public void setFsrmc(String fsrmc) {
		this.fsrmc = fsrmc;
	}



	public String getFsrq() {
		return fsrq;
	}



	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}



	public String getSpjzsj() {
		return spjzsj;
	}



	public void setSpjzsj(String spjzsj) {
		this.spjzsj = spjzsj;
	}



	public String getCqbz() {
		return cqbz;
	}



	public void setCqbz(String cqbz) {
		this.cqbz = cqbz;
	}



	public String getSprmc() {
		return sprmc;
	}



	public void setSprmc(String sprmc) {
		this.sprmc = sprmc;
	}



	public String getSprq() {
		return sprq;
	}



	public void setSprq(String sprq) {
		this.sprq = sprq;
	}



	public String getSpjg() {
		return spjg;
	}



	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}



	public Double getZsr() {
		return zsr;
	}



	public void setZsr(Double zsr) {
		this.zsr = zsr;
	}

	public Double getYfYfyf() {
		return yfYfyf;
	}



	public Double getYfHj() {
		return yfHj;
	}



	public void setYfHj(Double yfHj) {
		this.yfHj = yfHj;
	}



	public void setYfYfyf(Double yfYfyf) {
		this.yfYfyf = yfYfyf;
	}



	public String getPcdh() {
		return pcdh;
	}



	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}



	public String getPcrq() {
		return pcrq;
	}



	public void setPcrq(String pcrq) {
		this.pcrq = pcrq;
	}



	public String getPcfsDm() {
		return pcfsDm;
	}



	public void setPcfsDm(String pcfsDm) {
		this.pcfsDm = pcfsDm;
	}



	public String getPcfsmc() {
		return pcfsmc;
	}



	public void setPcfsmc(String pcfsmc) {
		this.pcfsmc = pcfsmc;
	}



	public String getZcfsDm() {
		return zcfsDm;
	}



	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm = zcfsDm;
	}



	public String getZcfsmc() {
		return zcfsmc;
	}



	public void setZcfsmc(String zcfsmc) {
		this.zcfsmc = zcfsmc;
	}



	public String getClhm() {
		return clhm;
	}



	public void setClhm(String clhm) {
		this.clhm = clhm;
	}



	public String getGchm() {
		return gchm;
	}



	public void setGchm(String gchm) {
		this.gchm = gchm;
	}



	public String getSjxm() {
		return sjxm;
	}



	public void setSjxm(String sjxm) {
		this.sjxm = sjxm;
	}



	public String getPcrmc() {
		return pcrmc;
	}



	public void setPcrmc(String pcrmc) {
		this.pcrmc = pcrmc;
	}



	public String getPcjgbm() {
		return pcjgbm;
	}



	public void setPcjgbm(String pcjgbm) {
		this.pcjgbm = pcjgbm;
	}



	public String getPcbmmc() {
		return pcbmmc;
	}



	public void setPcbmmc(String pcbmmc) {
		this.pcbmmc = pcbmmc;
	}



	public String getSsjgbm() {
		return ssjgbm;
	}



	public void setSsjgbm(String ssjgbm) {
		this.ssjgbm = ssjgbm;
	}



	public String getSsjgmc() {
		return ssjgmc;
	}



	public void setSsjgmc(String ssjgmc) {
		this.ssjgmc = ssjgmc;
	}



	public String getZrbmDm() {
		return zrbmDm;
	}



	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm = zrbmDm;
	}



	public String getZrbmmc() {
		return zrbmmc;
	}



	public void setZrbmmc(String zrbmmc) {
		this.zrbmmc = zrbmmc;
	}



	public String getDdbh() {
		return ddbh;
	}



	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}



	public String getSfd() {
		return sfd;
	}



	public void setSfd(String sfd) {
		this.sfd = sfd;
	}



	public String getMdd() {
		return mdd;
	}



	public void setMdd(String mdd) {
		this.mdd = mdd;
	}



	public String getHwmc() {
		return hwmc;
	}



	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}



	public String getBz() {
		return bz;
	}



	public void setBz(String bz) {
		this.bz = bz;
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



	public String getFhrmc() {
		return fhrmc;
	}



	public void setFhrmc(String fhrmc) {
		this.fhrmc = fhrmc;
	}



	public String getFhrdz() {
		return fhrdz;
	}



	public void setFhrdz(String fhrdz) {
		this.fhrdz = fhrdz;
	}



	public String getYqfhrq() {
		return yqfhrq;
	}



	public void setYqfhrq(String yqfhrq) {
		this.yqfhrq = yqfhrq;
	}



	public String getShrmc() {
		return shrmc;
	}



	public void setShrmc(String shrmc) {
		this.shrmc = shrmc;
	}



	public String getShrdz() {
		return shrdz;
	}



	public void setShrdz(String shrdz) {
		this.shrdz = shrdz;
	}



	public String getPcfsMc() {
		return pcfsMc;
	}



	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}



	public String getYqddrq() {
		return yqddrq;
	}



	public void setYqddrq(String yqddrq) {
		this.yqddrq = yqddrq;
	}



	public String getDdDjxh() {
		return ddDjxh;
	}



	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh = ddDjxh;
	}



	public String getPcDjxh() {
		return pcDjxh;
	}



	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}



	public List<DdcbshDomain> getDataList() {
		if (dataList == null) {
			dataList = new ArrayList<DdcbshDomain>();
		}
		return dataList;
	}



	public void setDataList(List<DdcbshDomain> dataList) {
		this.dataList = dataList;
	}



	public String getPchwClfsDm() {
		return pchwClfsDm;
	}



	public void setPchwClfsDm(String pchwClfsDm) {
		this.pchwClfsDm = pchwClfsDm;
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
