package com.cy.bggl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR BG_GZLX is created by tools.
 * @author HJH
 */

public class BgGzlxDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String gzlxXh;                           // 工作联系序号(SEQ_GZLX_XH)
	private String zt;                               // 主题
	private String nr;                               // 内容
	private String bcbzDm;                           // 保存标志代码
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	

	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	
	private String ckbz;
	private String ckbzMc;
	private String ckrq;
	
	
	private String czyDjxh;//操作人登记序号
	private String rqQ;//页面检索日期起
	private String rqZ;//页面检索日期止
	
	private String jsrs;
	private String jsr;
	private String jsrMcs;
	private String jsrMc;
	private String xtyhflDm;
	private String xtyhflDms;
	
	

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	private  List<BgGzlxDomain> fjList;
	
	private String fjmc;//附件名称
	private String xh;//附件序号
	private byte[] fjnr;//附件内容
	
	private String saveBz;
	
	//附件	
	List<byte[]> uploadValueList;
	
	List<String> uploadNameList;

	public List<String> getUploadNameList() {
		if(uploadValueList == null){
			uploadValueList = new ArrayList<byte[]>();
		}
		return uploadNameList;
	}

	public void setUploadNameList(List<String> uploadNameList) {
		this.uploadNameList = uploadNameList;
	}

	public List<byte[]> getUploadValueList() {
		if(uploadNameList == null){
			uploadNameList = new ArrayList<String>();
		}
		return uploadValueList;
	}

	public void setUploadValueList(List<byte[]> uploadValueList) {
		this.uploadValueList = uploadValueList;
	}

	public BgGzlxDomain() {
	}

	//获取工作联系序号(SEQ_GZLX_XH)
	public String getGzlxXh() {
		return this.gzlxXh;
	}

	//设置工作联系序号(SEQ_GZLX_XH)
	public void setGzlxXh(String gzlxXh) {
		this.gzlxXh=gzlxXh;
	}

	//获取主题
	public String getZt() {
		return this.zt;
	}

	//设置主题
	public void setZt(String zt) {
		this.zt=zt;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	//获取保存标志代码
	public String getBcbzDm() {
		return this.bcbzDm;
	}

	//设置保存标志代码
	public void setBcbzDm(String bcbzDm) {
		this.bcbzDm=bcbzDm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
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

	//取当天时间的前30天
	public String getRqQ() throws Exception{
		if(rqQ == null){
			rqQ = SysDateUtil.getIntervalDate(SysDateUtil.getCurrentDate(), Calendar.DATE, -30);
		}
		return rqQ;
	}

	public void setRqQ(String rqQ) {
		this.rqQ = rqQ;
	}

	//取当天时间
	public String getRqZ() throws Exception {
		if(rqZ == null){
			rqZ = SysDateUtil.getCurrentDate();
		}
		return rqZ;
	}

	public void setRqZ(String rqZ) {
		this.rqZ = rqZ;
	}

	public String getJsrs() {
		return jsrs;
	}

	public void setJsrs(String jsrs) {
		this.jsrs = jsrs;
	}

	public List<BgGzlxDomain> getFjList() {
		if(fjList == null){
			fjList = new ArrayList<BgGzlxDomain>();
		}
		return fjList;
	}

	public void setFjList(List<BgGzlxDomain> fjList) {
		this.fjList = fjList;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public byte[] getFjnr() {
		return fjnr;
	}

	public void setFjnr(byte[] fjnr) {
		this.fjnr = fjnr;
	}

	public String getCkbz() {
		return ckbz;
	}

	public void setCkbz(String ckbz) {
		this.ckbz = ckbz;
	}

	public String getCkbzMc() {
		if("Y".equals(ckbz)){
			ckbzMc = "已查看";
		}else{
			ckbzMc = "未查看";
		}
		return ckbzMc;
	}

	public void setCkbzMc(String ckbzMc) {
		this.ckbzMc = ckbzMc;
	}

	public String getCkrq() {		
		try{
			return SysDateUtil.getYyyyMmdd(this.ckrq);
		}
		catch(Exception e){
			return this.ckrq;
		}

	}

	public void setCkrq(String ckrq) {
		this.ckrq = ckrq;
	}

	public String getCzyDjxh() {
		return czyDjxh;
	}

	public void setCzyDjxh(String czyDjxh) {
		this.czyDjxh = czyDjxh;
	}

	public String getJsrMcs() {
		return jsrMcs;
	}

	public void setJsrMcs(String jsrMcs) {
		this.jsrMcs = jsrMcs;
	}


	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJsrMc() {
		return jsrMc;
	}

	public void setJsrMc(String jsrMc) {
		this.jsrMc = jsrMc;
	}

	public String getXtyhflDm() {
		return xtyhflDm;
	}

	public void setXtyhflDm(String xtyhflDm) {
		this.xtyhflDm = xtyhflDm;
	}

	public String getXtyhflDms() {
		return xtyhflDms;
	}

	public void setXtyhflDms(String xtyhflDms) {
		this.xtyhflDms = xtyhflDms;
	}

	public String getSaveBz() {
		return saveBz;
	}

	public void setSaveBz(String saveBz) {
		this.saveBz = saveBz;
	}

}
