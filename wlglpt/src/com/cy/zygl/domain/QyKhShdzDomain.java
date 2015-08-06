package com.cy.zygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR QY_KH_SHDZ is created by tools.
 * @author HJH
 */

public class QyKhShdzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String shdzDjxh;                         // 收货地址登记序号
	private String ssJgbm;                           // 机构编码(SEQ_JG_DJXH)
	private String khDjxh;                           // 
	private String xzqhDm;                           // 行政区划代码
	private String xzqhMc;                           // 行政区划名称
	private String xxdz;                             // 详细地址名称
	private String pyqc;                             // 地址拼音全称
	private String pyjc;                             // 地址拼音简称
	private String yb;                               // 邮编
	private String lxr;                              // 联系人
	private String lxrYddh;                          // 联系人移动电话
	private String lxrGddh;                          // 联系人固定电话
	private String qtlxdh;                           // 其他联系电话
	private String bz;                               // 备注
	private String djJgbm;                           // 
	private String djrCzyDjxh;                       // 
	private String djrq;                             // 登记日期
	private String qybz;                             // 启用标志(Y/N)
	private String yxbz;                             // 有效标志(Y/N)
	private String cjrCzyDjxh;                       // 创建人
	private String cjrq;                             // 创建日期
	private String xgrCzyDjxh;                       // 修改人
	private String xgrq;                             // 修改日期
	private String fhrMc;
	private String fhrDjxh;
	private String bmDjxh;
	private String shdwMc;
	private String cjrMc;                            // 创建人名称
	private String xgrMc;                            // 修改人名称
	private String khmc;

	private String ssJgmc;
	private String djJgmc;
	
	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public QyKhShdzDomain() {
	}

	//获取
	public String getShdzDjxh() {
		return this.shdzDjxh;
	}

	//设置
	public void setShdzDjxh(String shdzDjxh) {
		this.shdzDjxh=shdzDjxh;
	}

	//获取机构编码(SEQ_JG_DJXH)
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置机构编码(SEQ_JG_DJXH)
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取
	public String getKhDjxh() {
		return this.khDjxh;
	}

	//设置
	public void setKhDjxh(String khDjxh) {
		this.khDjxh=khDjxh;
	}

	//获取行政区划代码
	public String getXzqhDm() {
		return this.xzqhDm;
	}

	//设置行政区划代码
	public void setXzqhDm(String xzqhDm) {
		this.xzqhDm=xzqhDm;
	}

	//获取名称
	public String getXxdz() {
		return this.xxdz;
	}

	//设置名称
	public void setXxdz(String xxdz) {
		this.xxdz=xxdz;
	}

	//获取拼音全称
	public String getPyqc() {
		return this.pyqc;
	}

	//设置拼音全称
	public void setPyqc(String pyqc) {
		this.pyqc=pyqc;
	}

	//获取拼音简称
	public String getPyjc() {
		return this.pyjc;
	}

	//设置拼音简称
	public void setPyjc(String pyjc) {
		this.pyjc=pyjc;
	}

	//获取邮编
	public String getYb() {
		return this.yb;
	}

	//设置邮编
	public void setYb(String yb) {
		this.yb=yb;
	}

	//获取简称
	public String getLxr() {
		return this.lxr;
	}

	//设置简称
	public void setLxr(String lxr) {
		this.lxr=lxr;
	}

	//获取拼音全称
	public String getLxrYddh() {
		return this.lxrYddh;
	}

	//设置拼音全称
	public void setLxrYddh(String lxrYddh) {
		this.lxrYddh=lxrYddh;
	}

	//获取拼音简称
	public String getLxrGddh() {
		return this.lxrGddh;
	}

	//设置拼音简称
	public void setLxrGddh(String lxrGddh) {
		this.lxrGddh=lxrGddh;
	}

	//获取地址
	public String getQtlxdh() {
		return this.qtlxdh;
	}

	//设置地址
	public void setQtlxdh(String qtlxdh) {
		this.qtlxdh=qtlxdh;
	}

	//获取
	public String getBz() {
		return this.bz;
	}

	//设置
	public void setBz(String bz) {
		this.bz=bz;
	}

	//获取
	public String getDjJgbm() {
		return this.djJgbm;
	}

	//设置
	public void setDjJgbm(String djJgbm) {
		this.djJgbm=djJgbm;
	}

	//获取
	public String getDjrCzyDjxh() {
		return this.djrCzyDjxh;
	}

	//设置
	public void setDjrCzyDjxh(String djrCzyDjxh) {
		this.djrCzyDjxh=djrCzyDjxh;
	}

	//获取
	public String getDjrq() {
		try{
			return SysDateUtil.getYyyyMmdd(this.djrq);
		}
		catch(Exception e){
			return this.djrq;
		}
	}

	//设置
	public void setDjrq(String djrq) {
		this.djrq=djrq;
	}

	//获取启用标志(Y/N)
	public String getQybz() {
		return this.qybz;
	}

	//设置启用标志(Y/N)
	public void setQybz(String qybz) {
		this.qybz=qybz;
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

	public String getXzqhMc() {
		return xzqhMc;
	}

	public void setXzqhMc(String xzqhMc) {
		this.xzqhMc = xzqhMc;
	}

	public String getShdwMc() {
		return shdwMc;
	}

	public void setShdwMc(String shdwMc) {
		this.shdwMc = shdwMc;
	}

	public String getFhrMc() {
		return fhrMc;
	}

	public void setFhrMc(String fhrMc) {
		this.fhrMc = fhrMc;
	}

	public String getFhrDjxh() {
		return fhrDjxh;
	}

	public void setFhrDjxh(String fhrDjxh) {
		this.fhrDjxh = fhrDjxh;
	}

	public String getBmDjxh() {
		return bmDjxh;
	}

	public void setBmDjxh(String bmDjxh) {
		this.bmDjxh = bmDjxh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
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
	
	
}
