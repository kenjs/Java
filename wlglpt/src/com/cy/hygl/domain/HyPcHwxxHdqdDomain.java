package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR HY_PC_HWXX_HDQD is created by tools.
 * @author HJH
 */

public class HyPcHwxxHdqdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String hdqdDjxh;                         // 回单清单登记序号(SEQ_HD_DJXH)
	private String qdmc;                             // 清单名称
	private String jszt;                             // 接收状态(0 初始，1 发送，2 接收，3 退回)
	private String fsGsbm;                           // 发送公司编码
	private String jsGsbm;                           // 接收公司编码
	private String bz;                               // 备注
	private String ssJgbm;                           // 所属机构
	private String djJgbm;                           // 登记部门
	private String dbrCzyDjxh;                       // 打包人操作员登记序号
	private String dbrq;                             // 打包日期
	private String yxbz;                             // 有效标志(Y/N)

	private String ssJgmc;
	private String fsGsmc;                           // 发送公司
	private String jsGsmc;                           // 接收公司
	private String djJgmc;                           // 登记部门
	private String dbrMc;                            // 打包人
	private String rqQ;
	private String rqZ;
	/**********回单************/
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 序号(货物明细序号)
	private Double szHwSl;                           // 实装_货物_数量
	private Double szHwZl;                           // 实装_货物_重量
	private Double szHwTj;                           // 实装_货物_体积
	private String yqDdrq;                           // 要求到达日期
	private String shfsDm;                           // 收货方式代码
	private Double szJsSl;                           // 实装_结算数量
	private String hdbh;                             // 回单编号(多个已逗号分隔)
	private String hdjsrq;                           // 回单接收日期

	private String spbz;
	private String hdDjxh;                           // 回单登记序号
	
	
	private String pcdh;
	private String pcrq;
	private String pcfsDm;
	private String pcfsMc;
	private String zcfsDm;
	private String zcfsMc;
	private String cyrClhm;
	
	private String cyrGchm;
	private String cyrSjxm;
	private String yfHj;
	private String yfYfyf;
	private String pcCzyDjxh;
	private String pcrMc;
	private String pcJgbm;
	private String pcJgmc;
	private String dqztbz;//当前状态标志
	
	private String qdCxBz;//清单查询标志 0 全部 1发送的 2 接收的

	private List<String> hdDjxhs;
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public HyPcHwxxHdqdDomain() {
	}

	//获取回单清单登记序号(SEQ_HD_DJXH)
	public String getHdqdDjxh() {
		return this.hdqdDjxh;
	}

	//设置回单清单登记序号(SEQ_HD_DJXH)
	public void setHdqdDjxh(String hdqdDjxh) {
		this.hdqdDjxh=hdqdDjxh;
	}

	//获取清单名称
	public String getQdmc() {
		return this.qdmc;
	}

	//设置清单名称
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//获取接收状态(0 初始，1 发送，2 接收，3 退回)
	public String getJszt() {
		return this.jszt;
	}

	//设置接收状态(0 初始，1 发送，2 接收，3 退回)
	public void setJszt(String jszt) {
		this.jszt=jszt;
	}

	//获取发送公司编码
	public String getFsGsbm() {
		return this.fsGsbm;
	}

	//设置发送公司编码
	public void setFsGsbm(String fsGsbm) {
		this.fsGsbm=fsGsbm;
	}

	//获取接收公司编码
	public String getJsGsbm() {
		return this.jsGsbm;
	}

	//设置接收公司编码
	public void setJsGsbm(String jsGsbm) {
		this.jsGsbm=jsGsbm;
	}

	//获取备注
	public String getBz() {
		return this.bz;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取登记部门
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置登记部门
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取打包人操作员登记序号
	public String getDbrCzyDjxh() {
		return this.dbrCzyDjxh;
	}

	//设置打包人操作员登记序号
	public void setDbrCzyDjxh(String dbrCzyDjxh) {
		this.dbrCzyDjxh=dbrCzyDjxh;
	}

	//获取打包日期
	public String getDbrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.dbrq);
		}
		catch(Exception e){
			return this.dbrq;
		}
	}

	//设置打包日期
	public void setDbrq(String dbrq) {
		this.dbrq=dbrq;
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

	public String getHdDjxh() {
		return hdDjxh;
	}

	public void setHdDjxh(String hdDjxh) {
		this.hdDjxh = hdDjxh;
	}

	public String getPcDjxh() {
		return pcDjxh;
	}

	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh = pcDjxh;
	}

	public String getWfhDjxh() {
		return wfhDjxh;
	}

	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh = wfhDjxh;
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


	public String getHdjsrq() {
		return hdjsrq;
	}

	public void setHdjsrq(String hdjsrq) {
		this.hdjsrq = hdjsrq;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
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

	public String getPcfsMc() {
		return pcfsMc;
	}

	public void setPcfsMc(String pcfsMc) {
		this.pcfsMc = pcfsMc;
	}

	public String getZcfsDm() {
		return zcfsDm;
	}

	public void setZcfsDm(String zcfsDm) {
		this.zcfsDm = zcfsDm;
	}

	public String getZcfsMc() {
		return zcfsMc;
	}

	public void setZcfsMc(String zcfsMc) {
		this.zcfsMc = zcfsMc;
	}

	public String getCyrClhm() {
		return cyrClhm;
	}

	public void setCyrClhm(String cyrClhm) {
		this.cyrClhm = cyrClhm;
	}

	public String getCyrGchm() {
		return cyrGchm;
	}

	public void setCyrGchm(String cyrGchm) {
		this.cyrGchm = cyrGchm;
	}

	public String getCyrSjxm() {
		return cyrSjxm;
	}

	public void setCyrSjxm(String cyrSjxm) {
		this.cyrSjxm = cyrSjxm;
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

	public String getPcCzyDjxh() {
		return pcCzyDjxh;
	}

	public void setPcCzyDjxh(String pcCzyDjxh) {
		this.pcCzyDjxh = pcCzyDjxh;
	}

	public String getPcrMc() {
		return pcrMc;
	}

	public void setPcrMc(String pcrMc) {
		this.pcrMc = pcrMc;
	}

	public String getPcJgbm() {
		return pcJgbm;
	}

	public void setPcJgbm(String pcJgbm) {
		this.pcJgbm = pcJgbm;
	}

	public String getPcJgmc() {
		return pcJgmc;
	}

	public void setPcJgmc(String pcJgmc) {
		this.pcJgmc = pcJgmc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public List<String> getHdDjxhs() {
		return hdDjxhs;
	}

	public void setHdDjxhs(List<String> hdDjxhs) {
		this.hdDjxhs = hdDjxhs;
	}

	public String getDqztbz() {
		return dqztbz;
	}

	public void setDqztbz(String dqztbz) {
		this.dqztbz = dqztbz;
	}

	public String getFsGsmc() {
		return fsGsmc;
	}

	public void setFsGsmc(String fsGsmc) {
		this.fsGsmc = fsGsmc;
	}

	public String getJsGsmc() {
		return jsGsmc;
	}

	public void setJsGsmc(String jsGsmc) {
		this.jsGsmc = jsGsmc;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}

	public String getDbrMc() {
		return dbrMc;
	}

	public void setDbrMc(String dbrMc) {
		this.dbrMc = dbrMc;
	}

	public String getQdCxBz() {
		return qdCxBz;
	}

	public void setQdCxBz(String qdCxBz) {
		this.qdCxBz = qdCxBz;
	}
	
}
