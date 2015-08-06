package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
public class ConsigneeConsignorAddress {  //
 private String consigneeconsignoraddressid; //
 private String frompartyid; //会员ID
 private String topartyid; //子会员ID
 private String consigneeorconsignor; //
 private String checked; //
 private String consignee; //
 private String linkman; //
 private String mobilenumber; //手机号
 private String telephonenumber; //
 private String helpcode;
 private String province; //
 private String city; //市
 private String region; //区县
 private String town; //
 private String inputdate; //输入日期
 private String inputman; //输入人
 public String getConsigneeconsignoraddressid() {
  if (consigneeconsignoraddressid!=null) {if (new SysUtil().isInt(consigneeconsignoraddressid)==false) consigneeconsignoraddressid=null;}
  return consigneeconsignoraddressid;
 }
 public void setConsigneeconsignoraddressid(String consigneeconsignoraddressid) {
  if (consigneeconsignoraddressid!=null) {if (new SysUtil().isInt(consigneeconsignoraddressid)==false) consigneeconsignoraddressid=null;}
  this.consigneeconsignoraddressid = consigneeconsignoraddressid;
 }
 public String getFrompartyid() {
  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
  return frompartyid;
 }
 public void setFrompartyid(String frompartyid) {
  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
  this.frompartyid = frompartyid;
 }
 public String getTopartyid() {
  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
  return topartyid;
 }
 public void setTopartyid(String topartyid) {
  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
  this.topartyid = topartyid;
 }
 public String getConsigneeorconsignor() {
  if (consigneeorconsignor!=null) {if ("".equals(consigneeorconsignor)) consigneeorconsignor=null;}
  return consigneeorconsignor;
 }
 public void setConsigneeorconsignor(String consigneeorconsignor) {
  if (consigneeorconsignor!=null) {if ("".equals(consigneeorconsignor)) consigneeorconsignor=null;}
  this.consigneeorconsignor = consigneeorconsignor;
 }
 public String getChecked() {
  if (checked!=null) {if ("".equals(checked)) checked=null;}
  return checked;
 }
 public void setChecked(String checked) {
  if (checked!=null) {if ("".equals(checked)) checked=null;}
  this.checked = checked;
 }
 public String getConsignee() {
  if (consignee!=null) {if ("".equals(consignee)) consignee=null;}
  return consignee;
 }
 public void setConsignee(String consignee) {
  if (consignee!=null) {if ("".equals(consignee)) consignee=null;}
  this.consignee = consignee;
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
 public String getHelpcode() {
  if (helpcode!=null) {if ("".equals(helpcode)) helpcode=null;}
  return helpcode;
}
public void setHelpcode(String helpcode) {
  if (helpcode!=null) {if ("".equals(helpcode)) helpcode=null;}
  this.helpcode = helpcode;
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
 public String getTown() {
  if (town!=null) {if ("".equals(town)) town=null;}
  return town;
 }
 public void setTown(String town) {
  if (town!=null) {if ("".equals(town)) town=null;}
  this.town = town;
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
