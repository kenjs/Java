package com.cy.common.bo;
import java.io.Serializable;

/**
 * The persistent class For JS_KPSQ is created by tools.
 * @author HJH
 */

public class JsKpsq  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpsqDjxh;                         // 开票申请登记序号(SEQ_QD_DJXH)
	private String kpsqfsDm;                         // 开票申请方式代码
	private String khDjxh;                           // 客户登记序号
	private Double sqKpjeHj;                         // 申请开票金额合计
	private String sqKprq;                           // 申请开票日期
	private String bzsm;                             // 备注说明
	private String djrCzyDjxh;                       // 登记人
	private String djrq;                             // 登记日期
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String ykpQdhx;                          // 预开票清单核销标志(Y/N)
	private String kpDwJgbm;                         //  开票单位
	private String kpDwJgMc;                         //  开票单位名称
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
	
	public JsKpsq() {
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
		return this.sqKprq;
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
		return this.djrq;
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

	public String getKpDwJgbm() {
		return kpDwJgbm;
	}

	public void setKpDwJgbm(String kpDwJgbm) {
		this.kpDwJgbm = kpDwJgbm;
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

	public String getKpDwJgMc() {
		return kpDwJgMc;
	}

	public void setKpDwJgMc(String kpDwJgMc) {
		this.kpDwJgMc = kpDwJgMc;
	}

}