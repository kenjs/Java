package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_GZSJ is created by tools.
 * @author HJH
 */

public class BgGzsjDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String jgbm;                             // 机构编码
	private String yxqQ;                             // 有效期起
	private String yxqZ;                             // 有效期止
	private String amSbsjS;                          // 上午上班时间-时
	private String amSbsjF;                          // 上午上班时间-分
	private String pmSbsjS;                          // 下午下班时间-时
	private String pmSbsjF;                          // 下午下班时间-分
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	private String sbsj;
	private String xbsj;
	private String zt;//1失效 2有效
	
	private String[] xsList;
	private String[] fzList;
	
	private String count;
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public BgGzsjDomain() {
	}

	//获取机构编码
	public String getJgbm() {
		return this.jgbm;
	}

	//设置机构编码
	public void setJgbm(String jgbm) {
		this.jgbm=jgbm;
	}

	//获取有效期起
	public String getYxqQ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqQ);
		}
		catch(Exception e){
			return this.yxqQ;
		}
	}

	//设置有效期起
	public void setYxqQ(String yxqQ) {
		this.yxqQ=yxqQ;
	}

	//获取有效期止
	public String getYxqZ() {
		try{
			return SysDateUtil.getYyyyMmdd(this.yxqZ);
		}
		catch(Exception e){
			return this.yxqZ;
		}
	}

	//设置有效期止
	public void setYxqZ(String yxqZ) {
		this.yxqZ=yxqZ;
	}

	//获取上午上班时间-时
	public String getAmSbsjS() {
		return this.amSbsjS;
	}

	//设置上午上班时间-时
	public void setAmSbsjS(String amSbsjS) {
		this.amSbsjS=amSbsjS;
	}

	//获取上午上班时间-分
	public String getAmSbsjF() {
		return this.amSbsjF;
	}

	//设置上午上班时间-分
	public void setAmSbsjF(String amSbsjF) {
		this.amSbsjF=amSbsjF;
	}

	//获取下午下班时间-时
	public String getPmSbsjS() {
		return this.pmSbsjS;
	}

	//设置下午下班时间-时
	public void setPmSbsjS(String pmSbsjS) {
		this.pmSbsjS=pmSbsjS;
	}

	//获取下午下班时间-分
	public String getPmSbsjF() {
		return this.pmSbsjF;
	}

	//设置下午下班时间-分
	public void setPmSbsjF(String pmSbsjF) {
		this.pmSbsjF=pmSbsjF;
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

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getXbsj() {
		return xbsj;
	}

	public void setXbsj(String xbsj) {
		this.xbsj = xbsj;
	}

	public String[] getFzList() {
		if(fzList == null){
			fzList = new String[60];
			for (int i = 0; i < fzList.length; i++) {
				fzList[i] = i + "";
			}
		}
		return fzList;
	}

	public void setFzList(String[] fzList) {
		this.fzList = fzList;
	}

	public String[] getXsList() {
		if(xsList == null){
			xsList = new String[24];
			for (int i = 0; i < xsList.length; i++) {
				xsList[i] = i + "";
			}
		}
		return xsList;
	}

	public void setXsList(String[] xsList) {
		this.xsList = xsList;
	}
}