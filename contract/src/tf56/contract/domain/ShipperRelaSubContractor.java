package tf56.contract.domain;

import tf56.sofa.util.SysUtil;

public class ShipperRelaSubContractor {
	private String shipperrelasubcontractorid; //
	 private String frompartyid; //父会员ID
	 private String frompartyname;
	 private String partyid; //会员Id
	 private String topartyid; //子会员ID
	 private String topartyname; //
	 private String torealname; //
	 private String helpcode; //
	 private String linkman; //
	 private String mobilenumber; //手机号
	 private String telephonenumber; //公司电话
	 private String email; //邮箱地址
	 private String province; //
	 private String city; //市
	 private String region; //区县
	 private String officeaddress; //办公地址
	 private String inputdate; //输入日期
	 private String inputman; //输入人
	 public String getShipperrelasubcontractorid() {
	  if (shipperrelasubcontractorid!=null) {if (new SysUtil().isInt(shipperrelasubcontractorid)==false) shipperrelasubcontractorid=null;}
	  return shipperrelasubcontractorid;
	 }
	 public void setShipperrelasubcontractorid(String shipperrelasubcontractorid) {
	  if (shipperrelasubcontractorid!=null) {if (new SysUtil().isInt(shipperrelasubcontractorid)==false) shipperrelasubcontractorid=null;}
	  this.shipperrelasubcontractorid = shipperrelasubcontractorid;
	 }
	 public String getFrompartyid() {
	  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
	  return frompartyid;
	 }
	 public void setFrompartyid(String frompartyid) {
	  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
	  this.frompartyid = frompartyid;
	 }
	 public String getPartyid() {
	  if (partyid!=null) {if (new SysUtil().isInt(partyid)==false) partyid=null;}
	  return partyid;
	 }
	 public void setPartyid(String partyid) {
	  if (partyid!=null) {if (new SysUtil().isInt(partyid)==false) partyid=null;}
	  this.partyid = partyid;
	 }
	 public String getTopartyid() {
	  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
	  return topartyid;
	 }
	 public void setTopartyid(String topartyid) {
	  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
	  this.topartyid = topartyid;
	 }
	 public String getFrompartyname() {
		 if (frompartyname!=null) {if ("".equals(frompartyname)) frompartyname=null;}
		 return frompartyname;
	}
	public void setFrompartyname(String frompartyname) {
		if (frompartyname!=null) {if ("".equals(frompartyname)) frompartyname=null;}
		this.frompartyname = frompartyname;
	}
	public String getTopartyname() {
	  if (topartyname!=null) {if ("".equals(topartyname)) topartyname=null;}
	  return topartyname;
	 }
	 public void setTopartyname(String topartyname) {
	  if (topartyname!=null) {if ("".equals(topartyname)) topartyname=null;}
	  this.topartyname = topartyname;
	 }
	 public String getTorealname() {
	  if (torealname!=null) {if ("".equals(torealname)) torealname=null;}
	  return torealname;
	 }
	 public void setTorealname(String torealname) {
	  if (torealname!=null) {if ("".equals(torealname)) torealname=null;}
	  this.torealname = torealname;
	 }
	 public String getHelpcode() {
	  if (helpcode!=null) {if ("".equals(helpcode)) helpcode=null;}
	  return helpcode;
	 }
	 public void setHelpcode(String helpcode) {
	  if (helpcode!=null) {if ("".equals(helpcode)) helpcode=null;}
	  this.helpcode = helpcode;
	 }
	 public String getLinkman() {
	  if (linkman!=null) {if ("".equals(linkman)) linkman=null;}
	  return linkman;
	 }
	 public void setLinkman(String linkman) {
	  if (linkman!=null) {if ("".equals(linkman)) linkman=null;}
	  this.linkman = linkman;
	 }
	 public String getMobilenumber() {
	  if (mobilenumber!=null) {if ("".equals(mobilenumber)) mobilenumber=null;}
	  return mobilenumber;
	 }
	 public void setMobilenumber(String mobilenumber) {
	  if (mobilenumber!=null) {if ("".equals(mobilenumber)) mobilenumber=null;}
	  this.mobilenumber = mobilenumber;
	 }
	 public String getTelephonenumber() {
	  if (telephonenumber!=null) {if ("".equals(telephonenumber)) telephonenumber=null;}
	  return telephonenumber;
	 }
	 public void setTelephonenumber(String telephonenumber) {
	  if (telephonenumber!=null) {if ("".equals(telephonenumber)) telephonenumber=null;}
	  this.telephonenumber = telephonenumber;
	 }
	 public String getEmail() {
	  if (email!=null) {if ("".equals(email)) email=null;}
	  return email;
	 }
	 public void setEmail(String email) {
	  if (email!=null) {if ("".equals(email)) email=null;}
	  this.email = email;
	 }
	 public String getProvince() {
	  if (province!=null) {if ("".equals(province)) province=null;}
	  return province;
	 }
	 public void setProvince(String province) {
	  if (province!=null) {if ("".equals(province)) province=null;}
	  this.province = province;
	 }
	 public String getCity() {
	  if (city!=null) {if ("".equals(city)) city=null;}
	  return city;
	 }
	 public void setCity(String city) {
	  if (city!=null) {if ("".equals(city)) city=null;}
	  this.city = city;
	 }
	 public String getRegion() {
	  if (region!=null) {if ("".equals(region)) region=null;}
	  return region;
	 }
	 public void setRegion(String region) {
	  if (region!=null) {if ("".equals(region)) region=null;}
	  this.region = region;
	 }
	 public String getOfficeaddress() {
	  if (officeaddress!=null) {if ("".equals(officeaddress)) officeaddress=null;}
	  return officeaddress;
	 }
	 public void setOfficeaddress(String officeaddress) {
	  if (officeaddress!=null) {if ("".equals(officeaddress)) officeaddress=null;}
	  this.officeaddress = officeaddress;
	 }
	 public String getInputdate() {
	  if (inputdate!=null) {if ("".equals(inputdate)) inputdate=null;}
	  return inputdate;
	 }
	 public void setInputdate(String inputdate) {
	  if (inputdate!=null) {if ("".equals(inputdate)) inputdate=null;}
	  this.inputdate = inputdate;
	 }
	 public String getInputman() {
	  if (inputman!=null) {if ("".equals(inputman)) inputman=null;}
	  return inputman;
	 }
	 public void setInputman(String inputman) {
	  if (inputman!=null) {if ("".equals(inputman)) inputman=null;}
	  this.inputman = inputman;
	 }
}
