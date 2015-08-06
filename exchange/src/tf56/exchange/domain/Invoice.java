package tf56.exchange.domain;
import tf56.sofa.util.SysUtil;
public class Invoice {  //停车场发票明细
	private String iparkinvlistid; //编码
	private String ccomputer; //计算机名
	private String cinvtype; //发票类别
	private String iinvcode; //发票号码
	private String ddate; //使用时间
	private String cinputman; //收款人
	private String nmoney; //金额
	private String ccarname; //车号
	private String ckpitem; //收费项目名称
	private String dcanceldate; //作废日期
	private String ccancelman; //作废人
	private String csource; //来源
	private String iid; //编码
	private String cuploadcancelstate; //上传发票作废返回的状态  00 作废成功 01 作废失败 
	private String cuploadstate; //发票上传状态 00 上传成功空 01 上传失败 
	private String duploaddate; //认证日期
	private String cnetworkacceptnumber; //网络受理号 或错误
	public String getIparkinvlistid() {
		if (iparkinvlistid!=null) {if (new SysUtil().isInt(iparkinvlistid)==false) iparkinvlistid=null;}
		return iparkinvlistid;
	}
	public void setIparkinvlistid(String iparkinvlistid) {
		if (iparkinvlistid!=null) {if (new SysUtil().isInt(iparkinvlistid)==false) iparkinvlistid=null;}
		this.iparkinvlistid = iparkinvlistid;
	}
	public String getCcomputer() {
		if (ccomputer!=null) {if ("".equals(ccomputer)) ccomputer=null;}
		return ccomputer;
	}
	public void setCcomputer(String ccomputer) {
		if (ccomputer!=null) {if ("".equals(ccomputer)) ccomputer=null;}
		this.ccomputer = ccomputer;
	}
	public String getCinvtype() {
		if (cinvtype!=null) {if ("".equals(cinvtype)) cinvtype=null;}
		return cinvtype;
	}
	public void setCinvtype(String cinvtype) {
		if (cinvtype!=null) {if ("".equals(cinvtype)) cinvtype=null;}
		this.cinvtype = cinvtype;
	}
	public String getIinvcode() {
		if (iinvcode!=null) {if (new SysUtil().isInt(iinvcode)==false) iinvcode=null;}
		return iinvcode;
	}
	public void setIinvcode(String iinvcode) {
		if (iinvcode!=null) {if (new SysUtil().isInt(iinvcode)==false) iinvcode=null;}
		this.iinvcode = iinvcode;
	}
	public String getDdate() {
		if (ddate!=null) {if ("".equals(ddate)) ddate=null;}
		return ddate;
	}
	public void setDdate(String ddate) {
		if (ddate!=null) {if ("".equals(ddate)) ddate=null;}
		this.ddate = ddate;
	}
	public String getCinputman() {
		if (cinputman!=null) {if ("".equals(cinputman)) cinputman=null;}
		return cinputman;
	}
	public void setCinputman(String cinputman) {
		if (cinputman!=null) {if ("".equals(cinputman)) cinputman=null;}
		this.cinputman = cinputman;
	}
	public String getNmoney() {
		if (nmoney!=null) {if (new SysUtil().isNum(nmoney)==false) nmoney=null;}
		return nmoney;
	}
	public void setNmoney(String nmoney) {
		if (nmoney!=null) {if (new SysUtil().isNum(nmoney)==false) nmoney=null;}
		this.nmoney = nmoney;
	}
	public String getCcarname() {
		if (ccarname!=null) {if ("".equals(ccarname)) ccarname=null;}
		return ccarname;
	}
	public void setCcarname(String ccarname) {
		if (ccarname!=null) {if ("".equals(ccarname)) ccarname=null;}
		this.ccarname = ccarname;
	}
	public String getCkpitem() {
		if (ckpitem!=null) {if ("".equals(ckpitem)) ckpitem=null;}
		return ckpitem;
	}
	public void setCkpitem(String ckpitem) {
		if (ckpitem!=null) {if ("".equals(ckpitem)) ckpitem=null;}
		this.ckpitem = ckpitem;
	}
	public String getDcanceldate() {
		if (dcanceldate!=null) {if ("".equals(dcanceldate)) dcanceldate=null;}
		return dcanceldate;
	}
	public void setDcanceldate(String dcanceldate) {
		if (dcanceldate!=null) {if ("".equals(dcanceldate)) dcanceldate=null;}
		this.dcanceldate = dcanceldate;
	}
	public String getCcancelman() {
		if (ccancelman!=null) {if ("".equals(ccancelman)) ccancelman=null;}
		return ccancelman;
	}
	public void setCcancelman(String ccancelman) {
		if (ccancelman!=null) {if ("".equals(ccancelman)) ccancelman=null;}
		this.ccancelman = ccancelman;
	}
	public String getCsource() {
		if (csource!=null) {if ("".equals(csource)) csource=null;}
		return csource;
	}
	public void setCsource(String csource) {
		if (csource!=null) {if ("".equals(csource)) csource=null;}
		this.csource = csource;
	}
	public String getIid() {
		if (iid!=null) {if (new SysUtil().isInt(iid)==false) iid=null;}
		return iid;
	}
	public void setIid(String iid) {
		if (iid!=null) {if (new SysUtil().isInt(iid)==false) iid=null;}
		this.iid = iid;
	}
	public String getCuploadcancelstate() {
		if (cuploadcancelstate!=null) {if ("".equals(cuploadcancelstate)) cuploadcancelstate=null;}
		return cuploadcancelstate;
	}
	public void setCuploadcancelstate(String cuploadcancelstate) {
		if (cuploadcancelstate!=null) {if ("".equals(cuploadcancelstate)) cuploadcancelstate=null;}
		this.cuploadcancelstate = cuploadcancelstate;
	}
	public String getCuploadstate() {
		if (cuploadstate!=null) {if ("".equals(cuploadstate)) cuploadstate=null;}
		return cuploadstate;
	}
	public void setCuploadstate(String cuploadstate) {
		if (cuploadstate!=null) {if ("".equals(cuploadstate)) cuploadstate=null;}
		this.cuploadstate = cuploadstate;
	}
	public String getDuploaddate() {
		if (duploaddate!=null) {if ("".equals(duploaddate)) duploaddate=null;}
		return duploaddate;
	}
	public void setDuploaddate(String duploaddate) {
		if (duploaddate!=null) {if ("".equals(duploaddate)) duploaddate=null;}
		this.duploaddate = duploaddate;
	}
	public String getCnetworkacceptnumber() {
		if (cnetworkacceptnumber!=null) {if ("".equals(cnetworkacceptnumber)) cnetworkacceptnumber=null;}
		return cnetworkacceptnumber;
	}
	public void setCnetworkacceptnumber(String cnetworkacceptnumber) {
		if (cnetworkacceptnumber!=null) {if ("".equals(cnetworkacceptnumber)) cnetworkacceptnumber=null;}
		this.cnetworkacceptnumber = cnetworkacceptnumber;
	}
}