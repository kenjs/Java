package tf56.contract.domain;

import tf56.sofa.util.SysUtil;
public class SettleBillVerification {  //结算核销
 private String settlebillverificationid; //编码Id
 private String settlebillid; //结算单Id
 private String billcode;  //发票代码
 private String billnumber; //发票号
 private String billamount; //发票金额
 private String billman; //开票人
 private String billdate; //开票日期
 private String planpaydate;  //计划付款日期
 private String inputdate; //输入日期
 private String inputman; //输入人
 public String getSettlebillverificationid() {
  if (settlebillverificationid!=null) {if (new SysUtil().isInt(settlebillverificationid)==false) settlebillverificationid=null;}
  return settlebillverificationid;
 }
 public void setSettlebillverificationid(String settlebillverificationid) {
  if (settlebillverificationid!=null) {if (new SysUtil().isInt(settlebillverificationid)==false) settlebillverificationid=null;}
  this.settlebillverificationid = settlebillverificationid;
 }
 public String getSettlebillid() {
  if (settlebillid!=null) {if (new SysUtil().isInt(settlebillid)==false) settlebillid=null;}
  return settlebillid;
 }
 public void setSettlebillid(String settlebillid) {
  if (settlebillid!=null) {if (new SysUtil().isInt(settlebillid)==false) settlebillid=null;}
  this.settlebillid = settlebillid;
 }
 
 public String getBillcode() {
	if (billcode!=null) {if ("".equals(billcode)) billcode=null;}
	return billcode;
}
public void setBillcode(String billcode) {
	if (billcode!=null) {if ("".equals(billcode)) billcode=null;}
	this.billcode = billcode;
}
 
 public String getBillnumber() {
  if (billnumber!=null) {if ("".equals(billnumber)) billnumber=null;}
  return billnumber;
 }
 public void setBillnumber(String billnumber) {
  if (billnumber!=null) {if ("".equals(billnumber)) billnumber=null;}
  this.billnumber = billnumber;
 }
 public String getBillamount() {
  if (billamount!=null) {if (new SysUtil().isNum(billamount)==false) billamount=null;}
  return billamount;
 }
 public void setBillamount(String billamount) {
  if (billamount!=null) {if (new SysUtil().isNum(billamount)==false) billamount=null;}
  this.billamount = billamount;
 }
 public String getBillman() {
  if (billman!=null) {if ("".equals(billman)) billman=null;}
  return billman;
 }
 public void setBillman(String billman) {
  if (billman!=null) {if ("".equals(billman)) billman=null;}
  this.billman = billman;
 }
 public String getBilldate() {
  if (billdate!=null) {if ("".equals(billdate)) billdate=null;}
  return billdate;
 }
 public void setBilldate(String billdate) {
  if (billdate!=null) {if ("".equals(billdate)) billdate=null;}
  this.billdate = billdate;
 }
 
 public String getPlanpaydate() {
	if (planpaydate!=null) {if ("".equals(planpaydate)) planpaydate=null;}
	return planpaydate;
}
public void setPlanpaydate(String planpaydate) {
	if (planpaydate!=null) {if ("".equals(planpaydate)) planpaydate=null;}
	this.planpaydate = planpaydate;
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
