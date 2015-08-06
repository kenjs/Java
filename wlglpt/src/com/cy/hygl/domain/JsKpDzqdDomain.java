package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * The DOMAIN class FOR JS_KPSQ is created by tools.
 * @author HCM
 */

public class JsKpDzqdDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqDjxh;                         // 开票申请登记序号(SEQ_QD_DJXH)
	private String khDjxh;                           // 客户登记序号
	private String khmc;
	private Double sqKpjeHj;                         // 申请开票金额合计
	private String sqKprq;                           // 申请开票日期
	private String fpkjbz;							 // 发票开具标志
	private Double fpkjje;                           // 发票开具金额
	private String fpkjrq;                           // 发票开具日期
	private String ykpQdhx;                          // 预开票清单核销标志(Y/N)
	private Double yhxje;                            // 已核销金额
	private String djrCzyDjxh;                       //登记人操作员登记序号
	private String djrMc;                            //登记人名称
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String djJgmc;                           // 登记部门
	private String ssJgbm;                           // 所属单位
	private String ssJgmc;                           // 所属单位
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private List<BaseBusinessDomain> yhxList; 		 //查询列表
	
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH) 
	private String qdmc;                             // 清单名称
	private Double heJe;                             // 合计金额
	private Double ysqKpJe;                          // 已申请开票金额
	private Double wsqKpJe;                          // 未申请开票金额
	private String queryName;                        //下拉查询名  qdDjxh+qdmc
	private String xhStr;                            //所有qdDjxh组成的字符串
	
	private List<String> kpsqmxDjxhs;                //序号数组
	
	private String kpsqmxDjxh;                       // 开票申请登记序号
	private String kpsqMxflDm;                       // 开票申请明细分类代码
	private String ywDjxh;                           // 清单登记序号
	private Double sqKpje;                           // 申请开票金额
	private String bzsm;                             // 备注说明
	private String yxbz;                             // 有效标志(Y/N)
	
	
	private String djrqQ;
	private String djrqZ;
	
	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}
	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public List<BaseBusinessDomain> getYhxList() {
		if(yhxList==null){
			yhxList=new ArrayList<BaseBusinessDomain>();
		}
		return yhxList;
	}
	public void setYhxList(List<BaseBusinessDomain> yhxList) {
		this.yhxList = yhxList;
	}
	public String getKpsqDjxh() {
		return kpsqDjxh;
	}
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh = kpsqDjxh;
	}
	public String getKhDjxh() {
		return khDjxh;
	}
	public void setKhDjxh(String khDjxh) {
		this.khDjxh = khDjxh;
	}
	public Double getSqKpjeHj() {
		return sqKpjeHj;
	}
	public void setSqKpjeHj(Double sqKpjeHj) {
		this.sqKpjeHj = sqKpjeHj;
	}
	public String getSqKprq() {
		return sqKprq;
	}
	public void setSqKprq(String sqKprq) {
		this.sqKprq = sqKprq;
	}
	public String getFpkjbz() {
		return fpkjbz;
	}
	public void setFpkjbz(String fpkjbz) {
		this.fpkjbz = fpkjbz;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public Double getFpkjje() {
		return fpkjje;
	}
	public void setFpkjje(Double fpkjje) {
		this.fpkjje = fpkjje;
	}
	public String getFpkjrq() {
		return fpkjrq;
	}
	public void setFpkjrq(String fpkjrq) {
		this.fpkjrq = fpkjrq;
	}
	public String getYkpQdhx() {
		return ykpQdhx;
	}
	public void setYkpQdhx(String ykpQdhx) {
		this.ykpQdhx = ykpQdhx;
	}
	public Double getYhxje() {
		return yhxje;
	}
	public void setYhxje(Double yhxje) {
		this.yhxje = yhxje;
	}
	public String getDjrCzyDjxh() {
		return djrCzyDjxh;
	}
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh = djrCzyDjxh;
	}
	public String getDjrMc() {
		return djrMc;
	}
	public void setDjrMc(String djrMc) {
		this.djrMc = djrMc;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getDjJgbm() {
		return djJgbm;
	}
	public void setDjJgbm(String djJgbm) {
		this.djJgbm = djJgbm;
	}
	public String getDjJgmc() {
		return djJgmc;
	}
	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
	}
	public String getSsJgbm() {
		return ssJgbm;
	}
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}
	public String getSsJgmc() {
		return ssJgmc;
	}
	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
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
	public String getQdDjxh() {
		return qdDjxh;
	}
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh = qdDjxh;
	}
	public String getQdmc() {
		return qdmc;
	}
	public void setQdmc(String qdmc) {
		this.qdmc = qdmc;
	}
	public Double getHeJe() {
		return heJe;
	}
	public void setHeJe(Double heJe) {
		this.heJe = heJe;
	}
	public Double getYsqKpJe() {
		return ysqKpJe;
	}
	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}
	public Double getWsqKpJe() {
		return wsqKpJe;
	}
	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getXhStr() {
		return xhStr;
	}
	public void setXhStr(String xhStr) {
		this.xhStr = xhStr;
	}
	public Double getSqKpje() {
		return sqKpje;
	}
	public void setSqKpje(Double sqKpje) {
		this.sqKpje = sqKpje;
	}
	public String getBzsm() {
		return bzsm;
	}
	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}
	public String getKpsqmxDjxh() {
		return kpsqmxDjxh;
	}
	public void setKpsqmxDjxh(String kpsqmxDjxh) {
		this.kpsqmxDjxh = kpsqmxDjxh;
	}
	public String getKpsqMxflDm() {
		return kpsqMxflDm;
	}
	public void setKpsqMxflDm(String kpsqMxflDm) {
		this.kpsqMxflDm = kpsqMxflDm;
	}
	public String getYwDjxh() {
		return ywDjxh;
	}
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh = ywDjxh;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public List<String> getKpsqmxDjxhs() {
		return kpsqmxDjxhs;
	}
	public void setKpsqmxDjxhs(List<String> kpsqmxDjxhs) {
		this.kpsqmxDjxhs = kpsqmxDjxhs;
	}
	
}
