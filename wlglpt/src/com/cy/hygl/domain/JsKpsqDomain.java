package com.cy.hygl.domain;
import com.cy.common.domain.DmbGgDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR JS_KPSQ is created by tools.
 * @author HJH
 */

public class JsKpsqDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqDjxh;                         // 开票申请登记序号(SEQ_QD_DJXH)
	private String kpsqfsDm;                         // 开票申请方式代码
	private String kpsqfsMc;
	private String khDjxh;                           // 客户登记序号
	private String khMc;
	private Double sqKpjeHj;                         // 申请开票金额合计
	private String sqKprq;                           // 申请开票日期
	private String bzsm;                             // 备注说明
	private String djrCzyDjxh;                       // 登记人
	private String cjrMc;
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String bmMc;
	private String ssJgbm;                           // 所属机构
	private String dwMc;
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsspztMc;
	private String wsSpxh;                           // 文书审批序号
	private String ykpQdhx;                          // 预开票清单核销标志(Y/N)
	private String kpDwJgbm;                         //  开票单位
	private String kpDwJgMc;
	private String fpkjbz;							//发票开具标志
	private Double fpkjJe;                         // 发票开具金额
	
	private String shf;								//收货方
	private String shfSbh;							//收货方识别号
	private String fhf;								//发货方
	private String fhfSbh;							//发货方识别号
	private String ydrq;							//预达日期
	private String dj;								//单价
	private String mc;								//名称			
	private String sl;								//数量		
	private String dkf;								//抵扣方
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	private String rqQ;
	private String rqZ;
	
	private String existBz;
	
	private String kpsqmxDjxh;  //开票申请明细登记序号(SEQ_KPSQMX_DJXH)
	private String qdDjxh;                           // 清单登记序号(SEQ_QD_DJXH) 业务登记序号（视具体业务)
	private String kpsqMxflDm;                       // 开票申请明细分类代码
	private String qdmc;                             // 清单名称
	private Double heJe;                             // 合计金额
	private Double ysqKpJe;                             // 已申请开票金额
	private Double wsqKpJe;                             // 未申请开票金额
	private Double sqKpJe;                             // 申请开票金额
	
	private String xtcsSfsp;
	
	private List<DmbGgDomain> qdList;
	
	private List<String> kpsqmxDjxhs;
	
	private String dzQdXgbz;//结算-开票申请-对帐清单修改标志
	private List<JsKpsqDomain> srKpList;
	private String ysyfDjxh;
	private String ysfJe;
	private String yfjsfDjxh;
	private String kmxlMc;
	private String ysyflyMc;
	private String csrq;
	private String sm;
	private List<String> ywDjxhStr;
	private List<String> jeStr;
	private String ywDjxhs;
	private String flag;
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getYwDjxhs() {
		return ywDjxhs;
	}

	public void setYwDjxhs(String ywDjxhs) {
		this.ywDjxhs = ywDjxhs;
	}

	public List<String> getYwDjxhStr() {
		return ywDjxhStr;
	}

	public void setYwDjxhStr(List<String> ywDjxhStr) {
		this.ywDjxhStr = ywDjxhStr;
	}

	public List<String> getJeStr() {
		return jeStr;
	}

	public void setJeStr(List<String> jeStr) {
		this.jeStr = jeStr;
	}

	public String getYsyfDjxh() {
		return ysyfDjxh;
	}

	public void setYsyfDjxh(String ysyfDjxh) {
		this.ysyfDjxh = ysyfDjxh;
	}

	public String getYsfJe() {
		return ysfJe;
	}

	public void setYsfJe(String ysfJe) {
		this.ysfJe = ysfJe;
	}

	public String getYfjsfDjxh() {
		return yfjsfDjxh;
	}

	public void setYfjsfDjxh(String yfjsfDjxh) {
		this.yfjsfDjxh = yfjsfDjxh;
	}

	public String getKmxlMc() {
		return kmxlMc;
	}

	public void setKmxlMc(String kmxlMc) {
		this.kmxlMc = kmxlMc;
	}

	public String getYsyflyMc() {
		return ysyflyMc;
	}

	public void setYsyflyMc(String ysyflyMc) {
		this.ysyflyMc = ysyflyMc;
	}

	public String getCsrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.csrq);
		}
		catch(Exception e){
			return this.csrq;
		}
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public List<JsKpsqDomain> getSrKpList() {
		if(srKpList==null){
			srKpList=new ArrayList<JsKpsqDomain>();
		}
		return srKpList;
	}

	public void setSrKpList(List<JsKpsqDomain> srKpList) {
		this.srKpList = srKpList;
	}

	public JsKpsqDomain() {
	}

	//获取开票申请登记序号(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//设置开票申请登记序号(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//获取开票申请方式代码
	public String getKpsqfsDm() {
		return this.kpsqfsDm;
	}

	//设置开票申请方式代码
	public void setKpsqfsDm(String kpsqfsDm) {
		this.kpsqfsDm=kpsqfsDm;
	}

	//获取客户登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置客户登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取申请开票金额合计
	public Double getSqKpjeHj() {
		return this.sqKpjeHj;
	}

	//设置申请开票金额合计
	public void setSqKpjeHj(Double sqKpjeHj) {
		this.sqKpjeHj=sqKpjeHj;
	}

	//获取申请开票日期
	public String getSqKprq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sqKprq);
		}
		catch(Exception e){
			return this.sqKprq;
		}
	}

	//设置申请开票日期
	public void setSqKprq(String sqKprq) {
		this.sqKprq=sqKprq;
	}

	//获取备注说明
	public String getBzsm() {
		return this.bzsm;
	}

	//设置备注说明
	public void setBzsm(String bzsm) {
		this.bzsm=bzsm;
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
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//设置登记日期
	public void setDjrq(String djrq) {
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

	//获取需要审批标志(Y/N)
	public String getSpbz() {
		return this.spbz;
	}

	//设置需要审批标志(Y/N)
	public void setSpbz(String spbz) {
		this.spbz=spbz;
	}

	//获取文书审批状态代码
	public String getWsspztDm() {
		return this.wsspztDm;
	}

	//设置文书审批状态代码
	public void setWsspztDm(String wsspztDm) {
		this.wsspztDm=wsspztDm;
	}

	//获取文书审批序号
	public String getWsSpxh() {
		return this.wsSpxh;
	}

	//设置文书审批序号
	public void setWsSpxh(String wsSpxh) {
		this.wsSpxh=wsSpxh;
	}

	//获取预开票清单核销标志(Y/N)
	public String getYkpQdhx() {
		return this.ykpQdhx;
	}

	//设置预开票清单核销标志(Y/N)
	public void setYkpQdhx(String ykpQdhx) {
		this.ykpQdhx=ykpQdhx;
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

	public String getBmMc() {
		return bmMc;
	}

	public void setBmMc(String bmMc) {
		this.bmMc = bmMc;
	}

	public String getCjrMc() {
		return cjrMc;
	}

	public void setCjrMc(String cjrMc) {
		this.cjrMc = cjrMc;
	}

	public String getDwMc() {
		return dwMc;
	}

	public void setDwMc(String dwMc) {
		this.dwMc = dwMc;
	}

	public String getKhMc() {
		return khMc;
	}

	public void setKhMc(String khMc) {
		this.khMc = khMc;
	}

	public String getKpsqfsMc() {
		return kpsqfsMc;
	}

	public void setKpsqfsMc(String kpsqfsMc) {
		this.kpsqfsMc = kpsqfsMc;
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

	public String getExistBz() {
		return existBz;
	}

	public void setExistBz(String existBz) {
		this.existBz = existBz;
	}

	public String getKpDwJgbm() {
		return kpDwJgbm;
	}

	public void setKpDwJgbm(String kpDwJgbm) {
		this.kpDwJgbm = kpDwJgbm;
	}

	public String getKpDwJgMc() {
		return kpDwJgMc;
	}

	public void setKpDwJgMc(String kpDwJgMc) {
		this.kpDwJgMc = kpDwJgMc;
	}

	public String getFpkjbz() {
		return fpkjbz;
	}

	public void setFpkjbz(String fpkjbz) {
		this.fpkjbz = fpkjbz;
	}

	public Double getFpkjJe() {
		return fpkjJe;
	}

	public void setFpkjJe(Double fpkjJe) {
		this.fpkjJe = fpkjJe;
	}

	public Double getHeJe() {
		return heJe;
	}

	public void setHeJe(Double heJe) {
		this.heJe = heJe;
	}

	public String getQdDjxh() {
		return qdDjxh;
	}

	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh = qdDjxh;
	}

	public Double getSqKpJe() {
		return sqKpJe;
	}

	public void setSqKpJe(Double sqKpJe) {
		this.sqKpJe = sqKpJe;
	}

	public Double getWsqKpJe() {
		return wsqKpJe;
	}

	public void setWsqKpJe(Double wsqKpJe) {
		this.wsqKpJe = wsqKpJe;
	}

	public Double getYsqKpJe() {
		return ysqKpJe;
	}

	public void setYsqKpJe(Double ysqKpJe) {
		this.ysqKpJe = ysqKpJe;
	}

	public String getQdmc() {
		return qdmc;
	}

	public void setQdmc(String qdmc) {
		this.qdmc = qdmc;
	}

	public List<DmbGgDomain> getQdList() {
		if(null==qdList)
			qdList=new ArrayList<DmbGgDomain>();
		return qdList;
	}

	public void setQdList(List<DmbGgDomain> qdList) {
		this.qdList = qdList;
	}

	public String getDzQdXgbz() {
		return dzQdXgbz;
	}

	public void setDzQdXgbz(String dzQdXgbz) {
		this.dzQdXgbz = dzQdXgbz;
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

	public List<String> getKpsqmxDjxhs() {
		if(null==kpsqmxDjxhs)
			kpsqmxDjxhs=new ArrayList<String>();
		return kpsqmxDjxhs;
	}

	public void setKpsqmxDjxhs(List<String> kpsqmxDjxhs) {
		this.kpsqmxDjxhs = kpsqmxDjxhs;
	}

	public String getWsspztMc() {
		return wsspztMc;
	}

	public void setWsspztMc(String wsspztMc) {
		this.wsspztMc = wsspztMc;
	}

	public String getXtcsSfsp() {
		return xtcsSfsp;
	}

	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}

	public String getShf() {
		return shf;
	}

	public void setShf(String shf) {
		this.shf = shf;
	}

	public String getShfSbh() {
		return shfSbh;
	}

	public void setShfSbh(String shfSbh) {
		this.shfSbh = shfSbh;
	}

	public String getFhf() {
		return fhf;
	}

	public void setFhf(String fhf) {
		this.fhf = fhf;
	}

	public String getFhfSbh() {
		return fhfSbh;
	}

	public void setFhfSbh(String fhfSbh) {
		this.fhfSbh = fhfSbh;
	}

	public String getYdrq() {
		return ydrq;
	}

	public void setYdrq(String ydrq) {
		this.ydrq = ydrq;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getDkf() {
		return dkf;
	}

	public void setDkf(String dkf) {
		this.dkf = dkf;
	}
}
