package com.cy.hygl.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR XYJS_SRDZ_QD is created by tools.
 * @author HJH
 */

public class XyjsSrdzQdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH)
	private String xyDjxh;                           // 客户登记序号
	private String qdmc;                             // 清单名称
	private String dzqdHzfsDm;                       // 对帐清单汇总方式代码
	private String fylbDm;
	private Double heJe;                             // 合计金额
	private Double dzJe;
	private Double dzcyJe;
	private Double yfJe;                             // 已收金额
	private Double wfJe;                             // 未收金额
	private Double ysqKpje;                          // 已申请开票金额
	private Double wsqKpje;                          // 未申请开票金额
	private String djrCzyDjxh;                       // 登记人
	private Date djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String zt;
	private String bz;
	
	private String ztStr;
	
	private Date rqQ;
	private Date rqZ;
	private String djrMc;
	private String djJgmc;
	private String ssJgmc;
	private String xyMc;
	
	private String tempFlag;
	private Date pcrqQ;
	private Date pcrqZ;
	private Date xdrqQ;
	private Date xdrqZ;
	private String pcdh;
	private String ddbh;
	private Double hwSl;
	private String hdbh;
	
	private List<String> ywDjxhs;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<XyjsPcHwxxDomain> jsxxList;
	private List<XyjsPcHwxxDomain> qdmxList;

	public XyjsSrdzQdDomain() {
	}

	//获取清单登记序号(SEQ_QD_DJXH)
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//设置清单登记序号(SEQ_QD_DJXH)
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	//获取客户登记序号
	public String getXyDjxh() {
		return this.xyDjxh;
	}

	//设置客户登记序号
	public void setXyDjxh(String xyDjxh) {
		this.xyDjxh=xyDjxh;
	}

	//获取清单名称
	public String getQdmc() {
		return this.qdmc;
	}

	//设置清单名称
	public void setQdmc(String qdmc) {
		this.qdmc=qdmc;
	}

	//获取对帐清单汇总方式代码
	public String getDzqdHzfsDm() {
		return this.dzqdHzfsDm;
	}

	//设置对帐清单汇总方式代码
	public void setDzqdHzfsDm(String dzqdHzfsDm) {
		this.dzqdHzfsDm=dzqdHzfsDm;
	}

	//获取合计金额
	public Double getHeJe() {
		return this.heJe;
	}

	//设置合计金额
	public void setHeJe(Double heJe) {
		this.heJe=heJe;
	}

	//获取已收金额
	public Double getYfJe() {
		return this.yfJe;
	}

	//设置已收金额
	public void setYfJe(Double yfJe) {
		this.yfJe=yfJe;
	}

	//获取未收金额
	public Double getWfJe() {
		return this.wfJe;
	}

	//设置未收金额
	public void setWfJe(Double wfJe) {
		this.wfJe=wfJe;
	}

	//获取已申请开票金额
	public Double getYsqKpje() {
		return this.ysqKpje;
	}

	//设置已申请开票金额
	public void setYsqKpje(Double ysqKpje) {
		this.ysqKpje=ysqKpje;
	}

	//获取未申请开票金额
	public Double getWsqKpje() {
		return this.wsqKpje;
	}

	//设置未申请开票金额
	public void setWsqKpje(Double wsqKpje) {
		this.wsqKpje=wsqKpje;
	}

	//获取登记人
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置登记人
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取登记日期
	public Date getDjrq() {
			return this.djrq;
	}

	//设置登记日期
	public void setDjrq(Date djrq) {
		this.djrq=djrq;
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

	public Date getRqQ() {
		return rqQ;
	}

	public void setRqQ(Date rqQ) {
		this.rqQ = rqQ;
	}

	public Date getRqZ() {
		return rqZ;
	}

	public void setRqZ(Date rqZ) {
		this.rqZ = rqZ;
	}

	public String getDjrMc() {
		return djrMc;
	}

	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getXyMc() {
		return xyMc;
	}

	public void setXyMc(String xyMc) {
		this.xyMc = xyMc;
	}

	public String getTempFlag() {
		return tempFlag;
	}

	public void setTempFlag(String tempFlag) {
		this.tempFlag = tempFlag;
	}

	public Date getPcrqQ() {
		return pcrqQ;
	}

	public void setPcrqQ(Date pcrqQ) {
		this.pcrqQ = pcrqQ;
	}

	public Date getPcrqZ() {
		return pcrqZ;
	}

	public void setPcrqZ(Date pcrqZ) {
		this.pcrqZ = pcrqZ;
	}

	public Date getXdrqQ() {
		return xdrqQ;
	}

	public void setXdrqQ(Date xdrqQ) {
		this.xdrqQ = xdrqQ;
	}

	public Date getXdrqZ() {
		return xdrqZ;
	}

	public void setXdrqZ(Date xdrqZ) {
		this.xdrqZ = xdrqZ;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public Double getHwSl() {
		return hwSl;
	}

	public void setHwSl(Double hwSl) {
		this.hwSl = hwSl;
	}

	public String getHdbh() {
		return hdbh;
	}

	public void setHdbh(String hdbh) {
		this.hdbh = hdbh;
	}

	public String getFylbDm() {
		return fylbDm;
	}

	public void setFylbDm(String fylbDm) {
		this.fylbDm = fylbDm;
	}

	public Double getDzJe() {
		return dzJe;
	}

	public void setDzJe(Double dzJe) {
		this.dzJe = dzJe;
	}

	public Double getDzcyJe() {
		return dzcyJe;
	}

	public void setDzcyJe(Double dzcyJe) {
		this.dzcyJe = dzcyJe;
	}

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}

	public String getWsspztDm() {
		return wsspztDm;
	}

	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm = wsspztDm;
	}

	public String getWsSpxh() {
		return wsSpxh;
	}

	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh = wsSpxh;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public List<String> getYwDjxhs() {
		if (ywDjxhs == null) {
			ywDjxhs = new ArrayList<String>();
		}
		return ywDjxhs;
	}

	public void setYwDjxhs(List<String> ywDjxhs) {
		this.ywDjxhs = ywDjxhs;
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

	public List<XyjsPcHwxxDomain> getJsxxList() {
		if (jsxxList == null) {
			jsxxList = new ArrayList<XyjsPcHwxxDomain>();
		}
		return jsxxList;
	}

	public void setJsxxList(List<XyjsPcHwxxDomain> jsxxList) {
		this.jsxxList = jsxxList;
	}

	public List<XyjsPcHwxxDomain> getQdmxList() {
		if (qdmxList == null) {
			qdmxList = new ArrayList<XyjsPcHwxxDomain>();
		}
		return qdmxList;
	}

	public void setQdmxList(List<XyjsPcHwxxDomain> qdmxList) {
		this.qdmxList = qdmxList;
	}

	public String getZtStr() {
		if("1".equals(zt)){
			ztStr = "初始";
		}else if("2".equals(zt)){
			ztStr = "已发送";
		}else if("3".equals(zt)){
			ztStr = "已确认";
		}else if("4".equals(zt)){
			ztStr = "退回";
		}
		return ztStr;
	}

	public void setZtStr(String ztStr) {
		this.ztStr = ztStr;
	}
}
