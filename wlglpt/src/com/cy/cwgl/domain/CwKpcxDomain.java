package com.cy.cwgl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_KPDJ is created by tools.
 * @author HJH
 */

public class CwKpcxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String kpDjxh;                           // 开票登记序号(SEQ_KP_DJXH)
	private String kpsqDjxh;                         // 开票申请登记序号(SEQ_QD_DJXH)
	private String khDjxh;                           // 客户登记序号
	private String fpdm;                             // 备注
	private String fphm;                             // 需要审批标志(Y/N)
	private String fphmQ;                             // 需要审批标志(Y/N)
	private String fphmZ;                             // 需要审批标志(Y/N)
	private String kprCzyDjxh;                       // 
	private String kprq;                             // 开票方式代码
	private String kprqQ;
	private String kprqZ;
	private Double kpje;                             // 已开发票号码
	private Double sl;                               // 
	private Double se;                               // 税额
	private String zfbz;                             // 
	private String hzbz;                             // 
	private String lzFpdm;                           // 
	private String lzFphm;                           // 
	private String djJgbm;                           // 登记部门
	private String ssJgbm;                           // 所属机构
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private String zfStr;
	private String hzStr;
	
	private String khmc;
	private String kprMc;
	private String ssJgmc;
	private String djJgmc;
	private String rqQ;
	private String rqZ;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	private String fhrDjxh;
	private String fhrMc;
	public CwKpcxDomain() {
	}

	//获取开票登记序号(SEQ_KP_DJXH)
	public String getKpDjxh() {
		return this.kpDjxh;
	}

	//设置开票登记序号(SEQ_KP_DJXH)
	public void setKpDjxh(String kpDjxh) {
		this.kpDjxh=kpDjxh;
	}

	//获取开票申请登记序号(SEQ_QD_DJXH)
	public String getKpsqDjxh() {
		return this.kpsqDjxh;
	}

	//设置开票申请登记序号(SEQ_QD_DJXH)
	public void setKpsqDjxh(String kpsqDjxh) {
		this.kpsqDjxh=kpsqDjxh;
	}

	//获取客户登记序号
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置客户登记序号
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取备注
	public String getFpdm() {
		return this.fpdm;
	}

	//设置备注
	public void setFpdm(String fpdm) {
		this.fpdm=fpdm;
	}

	//获取
	public String getKprCzyDjxh() {
		return this.kprCzyDjxh;
	}

	//设置
	public void setKprCzyDjxh(String kprCzyDjxh) {
		this.kprCzyDjxh=kprCzyDjxh;
	}

	//获取开票方式代码
	public String getKprq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.kprq);
		}
		catch(Exception e){
			return this.kprq;
		}
	}

	//设置开票方式代码
	public void setKprq(String kprq) {
		this.kprq=kprq;
	}

	//获取已开发票号码
	public Double getKpje() {
		return this.kpje;
	}

	//设置已开发票号码
	public void setKpje(Double kpje) {
		this.kpje=kpje;
	}

	//获取
	public Double getSl() {
		return this.sl;
	}

	//设置
	public void setSl(Double sl) {
		this.sl=sl;
	}

	//获取税额
	public Double getSe() {
		return this.se;
	}

	//设置税额
	public void setSe(Double se) {
		this.se=se;
	}

	//获取
	public String getZfbz() {
		return this.zfbz;
	}

	//设置
	public void setZfbz(String zfbz) {
		this.zfbz=zfbz;
	}

	//获取
	public String getHzbz() {
		return this.hzbz;
	}

	//设置
	public void setHzbz(String hzbz) {
		this.hzbz=hzbz;
	}

	//获取
	public String getLzFpdm() {
		return this.lzFpdm;
	}

	//设置
	public void setLzFpdm(String lzFpdm) {
		this.lzFpdm=lzFpdm;
	}

	//获取
	public String getLzFphm() {
		return this.lzFphm;
	}

	//设置
	public void setLzFphm(String lzFphm) {
		this.lzFphm=lzFphm;
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

	//获取创建人
	public String getCjrCzyDjxh() {
		return this.cjrCzyDjxh;
	}

	//设置创建人
	public void setCjrCzyDjxh(String cjrCzyDjxh) {
		this.cjrCzyDjxh=cjrCzyDjxh;
	}

	//获取创建日期
	public String getCjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.cjrq);
		}
		catch(Exception e){
			return this.cjrq;
		}
	}

	//设置创建日期
	public void setCjrq(String cjrq) {
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
	public String getXgrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.xgrq);
		}
		catch(Exception e){
			return this.xgrq;
		}
	}

	//设置修改日期
	public void setXgrq(String xgrq) {
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

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getKprMc() {
		return kprMc;
	}

	public void setKprMc(String kprMc) {
		this.kprMc = kprMc;
	}

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public String getDjJgmc() {
		return djJgmc;
	}

	public void setDjJgmc(String djJgmc) {
		this.djJgmc = djJgmc;
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

	public String getZfStr() {
		if("Y".equals(zfbz)){
			zfStr = "是";
		}else{
			zfStr = "<font color=\"red\">否</font>";
		}
		return zfStr;
	}

	public void setZfStr(String zfStr) {
		this.zfStr = zfStr;
	}

	public String getHzStr() {
		if("Y".equals(hzbz)){
			hzStr = "是";
		}else{
			hzStr = "<font color=\"red\">否</font>";
		}
		return hzStr;
	}

	public void setHzStr(String hzStr) {
		this.hzStr = hzStr;
	}

	public String getFphmQ() {
		return fphmQ;
	}

	public void setFphmQ(String fphmQ) {
		this.fphmQ = fphmQ;
	}

	public String getFphmZ() {
		return fphmZ;
	}

	public void setFphmZ(String fphmZ) {
		this.fphmZ = fphmZ;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public String getKprqQ() {
		return kprqQ;
	}

	public void setKprqQ(String kprqQ) {
		this.kprqQ = kprqQ;
	}

	public String getKprqZ() {
		return kprqZ;
	}

	public void setKprqZ(String kprqZ) {
		this.kprqZ = kprqZ;
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
	
}
