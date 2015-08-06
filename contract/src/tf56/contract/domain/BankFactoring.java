package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
public class BankFactoring {  //
 private String bankfactoringid; //
 private String frompartyid; //发货方会员Id
 private String frompartyname; //发货方名称
 private String partyid; //会员ID
 private String topartyid; //乙方会员Id
 private String isenabled; //
 private String businessdays; //
 private String inputdate; //输入日期
 private String inputman; //输入人
 private String updateman; //修改人
 private String updatedate; //修改日期
 public String getBankfactoringid() {
  if (bankfactoringid!=null) {if (new SysUtil().isInt(bankfactoringid)==false) bankfactoringid=null;}
  return bankfactoringid;
 }
 public void setBankfactoringid(String bankfactoringid) {
  if (bankfactoringid!=null) {if (new SysUtil().isInt(bankfactoringid)==false) bankfactoringid=null;}
  this.bankfactoringid = bankfactoringid;
 }
 public String getFrompartyid() {
  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
  return frompartyid;
 }
 public void setFrompartyid(String frompartyid) {
  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
  this.frompartyid = frompartyid;
 }
 
 public String getFrompartyname() {
	 if (frompartyname!=null) {if ("".equals(frompartyname)) frompartyname=null;}
	 return frompartyname;
}
public void setFrompartyname(String frompartyname) {
	if (frompartyname!=null) {if ("".equals(frompartyname)) frompartyname=null;}
	this.frompartyname = frompartyname;
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
 public String getIsenabled() {
  if (isenabled!=null) {if (new SysUtil().isInt(isenabled)==false) isenabled=null;}
  return isenabled;
 }
 public void setIsenabled(String isenabled) {
  if (isenabled!=null) {if (new SysUtil().isInt(isenabled)==false) isenabled=null;}
  this.isenabled = isenabled;
 }
 public String getBusinessdays() {
  if (businessdays!=null) {if (new SysUtil().isInt(businessdays)==false) businessdays=null;}
  return businessdays;
 }
 public void setBusinessdays(String businessdays) {
  if (businessdays!=null) {if (new SysUtil().isInt(businessdays)==false) businessdays=null;}
  this.businessdays = businessdays;
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
 public String getUpdateman() {
  if (updateman!=null) {if ("".equals(updateman)) updateman=null;}
  return updateman;
 }
 public void setUpdateman(String updateman) {
  if (updateman!=null) {if ("".equals(updateman)) updateman=null;}
  this.updateman = updateman;
 }
 public String getUpdatedate() {
  if (updatedate!=null) {if ("".equals(updatedate)) updatedate=null;}
  return updatedate;
 }
 public void setUpdatedate(String updatedate) {
  if (updatedate!=null) {if ("".equals(updatedate)) updatedate=null;}
  this.updatedate = updatedate;
 }
}