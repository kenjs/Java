package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cy.framework.util.SysDateUtil;

/**
 * The DOMAIN class FOR XYJS_SRDZ is created by tools.
 * @author HJH
 */

public class XyjsSrdzDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // 对帐登记序号(SEQ_DZ_DJXH)
	private String ywDjxh;                           // 结算登记序号
	private String ywMxXh;                           // 业务明细序号
	private String ssJgbm;                           // 所属机构
	private Double jsJe;                             // 结算_未结
	private Double dzje;                             // 
	private String dzrCzyDjxh;                       // 对帐人
	private Date dzrq;                             // 对帐日期
	private String dzJgbm;                           // 对帐部门
	private String yxbz;                             // 有效标志(Y/N)
	private String dzCybz;                           // 对帐差异标志(Y/N)
	private Double dzcyje;                           // 对帐差异金额
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号
	private String qdDjxh;                           // 清单登记序号
	private String bz;
	
	private String tempFlag;
	
	private String fylbDm;
	private String dzztDm;
	private String xyDjxh;
	private Date pcrqQ;
	private Date pcrqZ;
	private Date xdrqQ;
	private Date xdrqZ;
	private String pcdh;
	private String ddbh;
	private Double hwSl;
	private String hdbh;
	
	private Date pcrq;
	private Date xdrq;
	private String hwmc; 
	private Double hwZl;                             // 货物_重量
	private Double hwTj;    
	private String sfdXzqhMc;
	private String mddXzqhMc;
	private String fhrLxr;
	private String shrMc;
	private String shrLxr;
	private String shrDz;
	private Double zcPsf;                            // 
	private Double zcDf;                             // 
	private Double zcDshk;
	private String xyMc;
	private String ssJgmc;
	
	private List<String> ywDjxhs;
	private List<XyjsSrdzQdDomain> dzqdList;

	private List<BaseBusinessDomain> dataList; 		 //查询列表

	public XyjsSrdzDomain() {
	}

	//获取对帐登记序号(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//设置对帐登记序号(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//获取结算登记序号
	public String getYwDjxh() {
		return this.ywDjxh;
	}

	//设置结算登记序号
	public void setYwDjxh(String ywDjxh) {
		this.ywDjxh=ywDjxh;
	}

	//获取业务明细序号
	public String getYwMxXh() {
		return this.ywMxXh;
	}

	//设置业务明细序号
	public void setYwMxXh(String ywMxXh) {
		this.ywMxXh=ywMxXh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取结算_未结
	public Double getJsJe() {
		return this.jsJe;
	}

	//设置结算_未结
	public void setJsJe(Double jsJe) {
		this.jsJe=jsJe;
	}

	//获取
	public Double getDzje() {
		return this.dzje;
	}

	//设置
	public void setDzje(Double dzje) {
		this.dzje=dzje;
	}

	//获取对帐人
	public String getDzrCzyDjxh() {
		return this.dzrCzyDjxh;
	}

	//设置对帐人
	public void setDzrCzyDjxh(String dzrCzyDjxh) {
		this.dzrCzyDjxh=dzrCzyDjxh;
	}

	//获取对帐日期
	public Date getDzrq() {
			return this.dzrq;
	}

	//设置对帐日期
	public void setDzrq(Date dzrq) {
		this.dzrq=dzrq;
	}

	//获取对帐部门
	public String getDzJgbm() {
		return this.dzJgbm;
	}

	//设置对帐部门
	public void setDzJgbm(String dzJgbm) {
		this.dzJgbm=dzJgbm;
	}

	//获取有效标志(Y/N)
	public String getYxbz() {
		return this.yxbz;
	}

	//设置有效标志(Y/N)
	public void setYxbz(String yxbz) {
		this.yxbz=yxbz;
	}

	//获取对帐差异标志(Y/N)
	public String getDzCybz() {
		return this.dzCybz;
	}

	//设置对帐差异标志(Y/N)
	public void setDzCybz(String dzCybz) {
		this.dzCybz=dzCybz;
	}

	//获取对帐差异金额
	public Double getDzcyje() {
		return this.dzcyje;
	}

	//设置对帐差异金额
	public void setDzcyje(Double dzcyje) {
		this.dzcyje=dzcyje;
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

	//获取清单登记序号
	public String getQdDjxh() {
		return this.qdDjxh;
	}

	//设置清单登记序号
	public void setQdDjxh(String qdDjxh) {
		this.qdDjxh=qdDjxh;
	}

	public String getFylbDm() {
		return fylbDm;
	}

	public void setFylbDm(String fylbDm) {
		this.fylbDm = fylbDm;
	}

	public String getDzztDm() {
		return dzztDm;
	}

	public void setDzztDm(String dzztDm) {
		this.dzztDm = dzztDm;
	}

	public String getXyDjxh() {
		return xyDjxh;
	}

	public void setXyDjxh(String xyDjxh) {
		this.xyDjxh = xyDjxh;
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

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
	}

	public Date getXdrq() {
		return xdrq;
	}

	public void setXdrq(Date xdrq) {
		this.xdrq = xdrq;
	}

	public String getHwmc() {
		return hwmc;
	}

	public void setHwmc(String hwmc) {
		this.hwmc = hwmc;
	}

	public Double getHwZl() {
		return hwZl;
	}

	public void setHwZl(Double hwZl) {
		this.hwZl = hwZl;
	}

	public Double getHwTj() {
		return hwTj;
	}

	public void setHwTj(Double hwTj) {
		this.hwTj = hwTj;
	}

	public String getSfdXzqhMc() {
		return sfdXzqhMc;
	}

	public void setSfdXzqhMc(String sfdXzqhMc) {
		this.sfdXzqhMc = sfdXzqhMc;
	}

	public String getMddXzqhMc() {
		return mddXzqhMc;
	}

	public void setMddXzqhMc(String mddXzqhMc) {
		this.mddXzqhMc = mddXzqhMc;
	}

	public String getFhrLxr() {
		return fhrLxr;
	}

	public void setFhrLxr(String fhrLxr) {
		this.fhrLxr = fhrLxr;
	}

	public String getShrMc() {
		return shrMc;
	}

	public void setShrMc(String shrMc) {
		this.shrMc = shrMc;
	}

	public String getShrLxr() {
		return shrLxr;
	}

	public void setShrLxr(String shrLxr) {
		this.shrLxr = shrLxr;
	}

	public String getShrDz() {
		return shrDz;
	}

	public void setShrDz(String shrDz) {
		this.shrDz = shrDz;
	}

	public Double getZcPsf() {
		return zcPsf;
	}

	public void setZcPsf(Double zcPsf) {
		this.zcPsf = zcPsf;
	}

	public Double getZcDf() {
		return zcDf;
	}

	public void setZcDf(Double zcDf) {
		this.zcDf = zcDf;
	}

	public Double getZcDshk() {
		return zcDshk;
	}

	public void setZcDshk(Double zcDshk) {
		this.zcDshk = zcDshk;
	}

	public String getXyMc() {
		return xyMc;
	}

	public void setXyMc(String xyMc) {
		this.xyMc = xyMc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getTempFlag() {
		return tempFlag;
	}

	public void setTempFlag(String tempFlag) {
		this.tempFlag = tempFlag;
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

	public String getSsJgmc() {
		return ssJgmc;
	}

	public void setSsJgmc(String ssJgmc) {
		this.ssJgmc = ssJgmc;
	}

	public List<XyjsSrdzQdDomain> getDzqdList() {
		if (dzqdList == null) {
			dzqdList = new ArrayList<XyjsSrdzQdDomain>();
		}
		return dzqdList;
	}

	public void setDzqdList(List<XyjsSrdzQdDomain> dzqdList) {
		this.dzqdList = dzqdList;
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
