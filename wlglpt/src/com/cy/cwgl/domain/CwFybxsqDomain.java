package com.cy.cwgl.domain;


import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR CW_FYBXSQ is created by tools.
 * @author HJH
 */

public class CwFybxsqDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String cwDjxh;                           // 财务登记序号(SEQ_CW_DJXH)
	private String ssJgbm;
	public String getSsJgbm() {
		return ssJgbm;
	}

	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm = ssJgbm;
	}

	private String sqrCzyDjxh;                       // 申请人
	private String sqrq;                             // 申请日期
	private String sqBmDjxh;                         // 申请部门
	private String sqDwDjxh;                         // 申请单位
	private String fyjzDwDjxh;                       // 费用记账单位
	private String fyzfDwDjxh;                       // 费用支付单位
	private String fybxje;                           // 费用报销金额合计
	private String bz;                               // 备注
	private String yxbz;                             // 有效标志(Y/N)
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	private String sqr;
	private String sqbm;
	private String sqdw;
	private String jzdw;
	private String jfdw;
	private String rqq;
	private String rqz;
	public String getRqz() {
		return rqz;
	}

	public void setRqz(String rqz) {
		this.rqz = rqz;
	}

	private String jsonStr;
	private String xhs;
	private String xtcsSfsp;	
	private String xmflDjxh;
	private String fylbMc;
	private String fyxmMc;
	private String tager;
	private boolean sendBz;
	private String spztMc;
	private String spxmFl;
	
	private List<String> fylbDjxh;
	private List<String> fyxmDjxh;
	private List<String> fyje;
	private List<String> bxje;
	private List<String> mxXh;
	private List<String> fybz;
	private String mxBz;
	private String bxdh;
	public String getBxdh() {
		return bxdh;
	}

	public void setBxdh(String bxdh) {
		this.bxdh = bxdh;
	}

	public String getMxBz() {
		return mxBz;
	}

	public void setMxBz(String mxBz) {
		this.mxBz = mxBz;
	}

	public List<String> getFylbDjxh() {
		return fylbDjxh;
	}

	public void setFylbDjxh(List<String> fylbDjxh) {
		this.fylbDjxh = fylbDjxh;
	}

	public List<String> getFyxmDjxh() {
		return fyxmDjxh;
	}

	public void setFyxmDjxh(List<String> fyxmDjxh) {
		this.fyxmDjxh = fyxmDjxh;
	}

	public List<String> getFyje() {
		return fyje;
	}

	public void setFyje(List<String> fyje) {
		this.fyje = fyje;
	}

	public List<String> getBxje() {
		return bxje;
	}

	public void setBxje(List<String> bxje) {
		this.bxje = bxje;
	}

	

	public List<String> getFybz() {
		return fybz;
	}

	public void setFybz(List<String> fybz) {
		this.fybz = fybz;
	}


	

	public String getSpxmFl() {
		return spxmFl;
	}

	public void setSpxmFl(String spxmFl) {
		this.spxmFl = spxmFl;
	}

	public String getSpztMc() {
		return spztMc;
	}

	public void setSpztMc(String spztMc) {
		this.spztMc = spztMc;
	}

	public boolean isSendBz() {
		return sendBz;
	}

	public void setSendBz(boolean sendBz) {
		this.sendBz = sendBz;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public String getXmflDjxh() {
		return xmflDjxh;
	}

	public void setXmflDjxh(String xmflDjxh) {
		this.xmflDjxh = xmflDjxh;
	}

	public String getFylbMc() {
		return fylbMc;
	}

	public void setFylbMc(String fylbMc) {
		this.fylbMc = fylbMc;
	}

	public String getFyxmMc() {
		return fyxmMc;
	}

	public void setFyxmMc(String fyxmMc) {
		this.fyxmMc = fyxmMc;
	}

	public String getXtcsSfsp() {
		return xtcsSfsp;
	}

	public void setXtcsSfsp(String xtcsSfsp) {
		this.xtcsSfsp = xtcsSfsp;
	}

	

	

	public String getXhs() {
		return xhs;
	}

	public void setXhs(String xhs) {
		this.xhs = xhs;
	}

	public List<String> getMxXh() {
		return mxXh;
	}

	public void setMxXh(List<String> mxXh) {
		this.mxXh = mxXh;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	private List<BaseBusinessDomain> fyList;
	public List<BaseBusinessDomain> getFyList() {
		if(fyList==null){
			fyList=new ArrayList<BaseBusinessDomain>();
		}
		return fyList;
	}

	public void setFyList(List<BaseBusinessDomain> fyList) {
		this.fyList = fyList;
	}

	public String getRqq() {
		return rqq;
	}

	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	public String getSqbm() {
		return sqbm;
	}

	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}

	public String getSqdw() {
		return sqdw;
	}

	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}

	public String getJzdw() {
		return jzdw;
	}

	public void setJzdw(String jzdw) {
		this.jzdw = jzdw;
	}

	public String getJfdw() {
		return jfdw;
	}

	public void setJfdw(String jfdw) {
		this.jfdw = jfdw;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public CwFybxsqDomain() {
	}

	//获取财务登记序号(SEQ_CW_DJXH)
	public String getCwDjxh() {
		return this.cwDjxh;
	}

	//设置财务登记序号(SEQ_CW_DJXH)
	public void setCwDjxh(String cwDjxh) {
		this.cwDjxh=cwDjxh;
	}

	//获取申请人
	public String getSqrCzyDjxh() {
		return this.sqrCzyDjxh;
	}

	//设置申请人
	public void setSqrCzyDjxh(String sqrCzyDjxh) {
		this.sqrCzyDjxh=sqrCzyDjxh;
	}

	//获取申请日期
	public String getSqrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.sqrq);
		}
		catch(Exception e){
			return this.sqrq;
		}
	}

	//设置申请日期
	public void setSqrq(String sqrq) {
		this.sqrq=sqrq;
	}

	//获取申请部门
	public String getSqBmDjxh() {
		return this.sqBmDjxh;
	}

	//设置申请部门
	public void setSqBmDjxh(String sqBmDjxh) {
		this.sqBmDjxh=sqBmDjxh;
	}

	//获取申请单位
	public String getSqDwDjxh() {
		return this.sqDwDjxh;
	}

	//设置申请单位
	public void setSqDwDjxh(String sqDwDjxh) {
		this.sqDwDjxh=sqDwDjxh;
	}

	//获取费用记账单位
	public String getFyjzDwDjxh() {
		return this.fyjzDwDjxh;
	}

	//设置费用记账单位
	public void setFyjzDwDjxh(String fyjzDwDjxh) {
		this.fyjzDwDjxh=fyjzDwDjxh;
	}

	//获取费用支付单位
	public String getFyzfDwDjxh() {
		return this.fyzfDwDjxh;
	}

	//设置费用支付单位
	public void setFyzfDwDjxh(String fyzfDwDjxh) {
		this.fyzfDwDjxh=fyzfDwDjxh;
	}

	//获取费用报销金额合计
	

	//获取备注
	public String getBz() {
		return this.bz;
	}

	public String getFybxje() {
		return fybxje;
	}

	public void setFybxje(String fybxje) {
		this.fybxje = fybxje;
	}

	//设置备注
	public void setBz(String bz) {
		this.bz=bz;
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
}
