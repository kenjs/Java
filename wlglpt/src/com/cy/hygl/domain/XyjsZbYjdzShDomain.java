package com.cy.hygl.domain;
import com.cy.framework.domain.BaseBusinessDomain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The DOMAIN class FOR XYJS_ZB_YJDZ is created by tools.
 * @author XIAY
 */

public class XyjsZbYjdzShDomain  extends BaseBusinessDomain {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dzDjxh;                           // 对帐登记序号(SEQ_DZ_DJXH)
	private String ssJgbm;                           // 所属机构
	private String pcDjxh;                           // 派车登记序号
	private String wfhDjxh;                          // 派车货物序号(未发货登记序号)
	private String ddDjxh;                           // 订单登记序号
	private String xh;                               // 货物明细序号
	private String zrbmDm;                           // 转入部门代码
	private String zrbmDjxh;                         // 转入部门登记序号
	private Double jsYj;                             // 结算_未结
	private Double dzje;                             // 对账金额
	private String dzrCzyDjxh;                       // 对帐人
	private String dzrq;                               // 对帐日期
	private String dzJgbm;                           // 对帐部门
	private String yxbz;                             // 有效标志(Y/N)
	private String dzbz;							  // 对账标志
	private String dzCybz;                           // 对帐差异标志(Y/N)
	private Double dzcyje;                           // 对帐差异金额
	private String spbz;                             // 需要审批标志(Y/N)
	private String wsspztDm;                         // 文书审批状态代码
	private String wsSpxh;                           // 文书审批序号

	private List<BaseBusinessDomain> dataList; 		 //查询列表
	
	private String jgmc;   //机构名称
	private String zrbmmc; //转入部门名称
	private String fbsmc;  //分包商名称
	private String dzrmc;  //对账人名称
	private String dzjgmc;  //对账机构名称
	private String pcdh;   //派车单号
	private Date pcrq;		//派车日期

	private String hwmc;  //货物名称
	private Double hwZl;  // 货物_重量
	private Double hwTj;   //货物_体积
	
	private String pcrqQ;  	//派车日期
	private String pcrqZ;	//派车日期
	
	private String pldzStr; //批量对账
	private String sfsp;  //是否审批
	private String zdsp;  //是否自动发送审批

	
	
	/***审核***/
	private List<XyjsZbYjdzShDomain> shList;
	private String spxh;
	private String jdxh;
	private String fsrmc;
	private String fsrq;
	private String sqr;
	private String sqbm;
	private String sqdw;
	private String jzdw;
	private String jfdw;
	private String rqq;
	private String rqz;
	private String fsthbz;
	private String shbz;
	private String bz;
	
	public XyjsZbYjdzShDomain() {
	}

	//获取对帐登记序号(SEQ_DZ_DJXH)
	public String getDzDjxh() {
		return this.dzDjxh;
	}

	//设置对帐登记序号(SEQ_DZ_DJXH)
	public void setDzDjxh(String dzDjxh) {
		this.dzDjxh=dzDjxh;
	}

	//获取所属机构
	public String getSsJgbm() {
		return this.ssJgbm;
	}

	//设置所属机构
	public void setSsJgbm(String ssJgbm) {
		this.ssJgbm=ssJgbm;
	}

	//获取派车登记序号
	public String getPcDjxh() {
		return this.pcDjxh;
	}

	//设置派车登记序号
	public void setPcDjxh(String pcDjxh) {
		this.pcDjxh=pcDjxh;
	}

	//获取派车货物序号(未发货登记序号)
	public String getWfhDjxh() {
		return this.wfhDjxh;
	}

	//设置派车货物序号(未发货登记序号)
	public void setWfhDjxh(String wfhDjxh) {
		this.wfhDjxh=wfhDjxh;
	}

	//获取订单登记序号
	public String getDdDjxh() {
		return this.ddDjxh;
	}

	//设置订单登记序号
	public void setDdDjxh(String ddDjxh) {
		this.ddDjxh=ddDjxh;
	}

	//获取货物明细序号
	public String getXh() {
		return this.xh;
	}

	//设置货物明细序号
	public void setXh(String xh) {
		this.xh=xh;
	}

	//获取转入部门代码
	public String getZrbmDm() {
		return this.zrbmDm;
	}

	//设置转入部门代码
	public void setZrbmDm(String zrbmDm) {
		this.zrbmDm=zrbmDm;
	}

	//获取转入部门登记序号
	public String getZrbmDjxh() {
		return this.zrbmDjxh;
	}

	//设置转入部门登记序号
	public void setZrbmDjxh(String zrbmDjxh) {
		this.zrbmDjxh=zrbmDjxh;
	}

	//获取结算_未结
	public Double getJsYj() {
		return this.jsYj;
	}

	//设置结算_未结
	public void setJsYj(Double jsYj) {
		this.jsYj=jsYj;
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
	public String getDzrq() {
		return this.dzrq;
	}

	//设置对帐日期
	public void setDzrq(String dzrq) {
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

	//获取对账标志
	public String getDzbz() {
		return dzbz;
	}

	//设置对账标志
	public void setDzbz(String dzbz) {
		this.dzbz = dzbz;
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

	public List<BaseBusinessDomain> getDataList() {
		if(dataList==null){
			dataList=new ArrayList<BaseBusinessDomain>();
		}
		return dataList;
	}

	public void setDataList(List<BaseBusinessDomain> dataList) {
		this.dataList = dataList;
	}
	public List<XyjsZbYjdzShDomain> getShList() {
		if(shList==null){
			shList=new ArrayList<XyjsZbYjdzShDomain>();
		}
		return shList;
	}

	public void setShList(List<XyjsZbYjdzShDomain> shList) {
		this.shList = shList;
	}

	public String getPcdh() {
		return pcdh;
	}

	public void setPcdh(String pcdh) {
		this.pcdh = pcdh;
	}

	public Date getPcrq() {
		return pcrq;
	}

	public void setPcrq(Date pcrq) {
		this.pcrq = pcrq;
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

	public String getPcrqQ() {
		return pcrqQ;
	}

	public void setPcrqQ(String pcrqQ) {
		this.pcrqQ = pcrqQ;
	}

	public String getPcrqZ() {
		return pcrqZ;
	}

	public void setPcrqZ(String pcrqZ) {
		this.pcrqZ = pcrqZ;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getZrbmmc() {
		return zrbmmc;
	}

	public void setZrbmmc(String zrbmmc) {
		this.zrbmmc = zrbmmc;
	}

	public String getFbsmc() {
		return fbsmc;
	}

	public void setFbsmc(String fbsmc) {
		this.fbsmc = fbsmc;
	}

	public String getDzrmc() {
		return dzrmc;
	}

	public void setDzrmc(String dzrmc) {
		this.dzrmc = dzrmc;
	}

	public String getDzjgmc() {
		return dzjgmc;
	}

	public void setDzjgmc(String dzjgmc) {
		this.dzjgmc = dzjgmc;
	}

	public String getPldzStr() {
		return pldzStr;
	}

	public void setPldzStr(String pldzStr) {
		this.pldzStr = pldzStr;
	}

	public String getSfsp() {
		return sfsp;
	}

	public void setSfsp(String sfsp) {
		this.sfsp = sfsp;
	}

	public String getZdsp() {
		return zdsp;
	}

	public void setZdsp(String zdsp) {
		this.zdsp = zdsp;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
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

	public String getRqq() {
		return rqq;
	}

	public void setRqq(String rqq) {
		this.rqq = rqq;
	}

	public String getFsthbz() {
		return fsthbz;
	}

	public void setFsthbz(String fsthbz) {
		this.fsthbz = fsthbz;
	}

	public String getShbz() {
		return shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
	}

	public String getRqz() {
		return rqz;
	}

	public void setRqz(String rqz) {
		this.rqz = rqz;
	}

	public String getSpxh() {
		return spxh;
	}

	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}

	public String getJdxh() {
		return jdxh;
	}

	public void setJdxh(String jdxh) {
		this.jdxh = jdxh;
	}

	public String getFsrmc() {
		return fsrmc;
	}

	public void setFsrmc(String fsrmc) {
		this.fsrmc = fsrmc;
	}

	public String getFsrq() {
		return fsrq;
	}

	public void setFsrq(String fsrq) {
		this.fsrq = fsrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
