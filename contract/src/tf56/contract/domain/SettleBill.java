package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
public class SettleBill {  //结算单
 private String settlebillid; //编码Id
 private String settlebillnumber; //结算单编号
 private String partyid;//总包会员id
 private String status; //状态
 private String startdate; //开始时间
 private String enddate; //结束时间
 private String inorout; //应收或应付
 private String inoutpartyid; //应收或应付会员Id
 private String inoutpartyidsec;//应付发货方id
 private String needinoutallamount; //应收应付总额
 private String factamount; //实际发生额
 private String needinoutremainamount; //未收未付金额
 private String inputdate; //输入日期
 private String inputman; //输入人
 public String getPartyid() {
     if (partyid!=null) {if (new SysUtil().isInt(partyid)==false) partyid=null;}
     return partyid;
    }
    public void setPartyid(String settlebillid) {
     if (partyid!=null) {if (new SysUtil().isInt(partyid)==false) partyid=null;}
     this.partyid = settlebillid;
    }
 public String getSettlebillid() {
  if (settlebillid!=null) {if (new SysUtil().isInt(settlebillid)==false) settlebillid=null;}
  return settlebillid;
 }
 public void setSettlebillid(String settlebillid) {
  if (settlebillid!=null) {if (new SysUtil().isInt(settlebillid)==false) settlebillid=null;}
  this.settlebillid = settlebillid;
 }
 public String getSettlebillnumber() {
  if (settlebillnumber!=null) {if ("".equals(settlebillnumber)) settlebillnumber=null;}
  return settlebillnumber;
 }
 public void setSettlebillnumber(String settlebillnumber) {
  if (settlebillnumber!=null) {if ("".equals(settlebillnumber)) settlebillnumber=null;}
  this.settlebillnumber = settlebillnumber;
 }
 public String getStatus() {
  if (status!=null) {if ("".equals(status)) status=null;}
  return status;
 }
 public void setStatus(String status) {
  if (status!=null) {if ("".equals(status)) status=null;}
  this.status = status;
 }
 public String getStartdate() {
  if (startdate!=null) {if ("".equals(startdate)) startdate=null;}
  return startdate;
 }
 public void setStartdate(String startdate) {
  if (startdate!=null) {if ("".equals(startdate)) startdate=null;}
  this.startdate = startdate;
 }
 public String getEnddate() {
  if (enddate!=null) {if ("".equals(enddate)) enddate=null;}
  return enddate;
 }
 public void setEnddate(String enddate) {
  if (enddate!=null) {if ("".equals(enddate)) enddate=null;}
  this.enddate = enddate;
 }
 public String getInorout() {
  if (inorout!=null) {if ("".equals(inorout)) inorout=null;}
  return inorout;
 }
 public void setInorout(String inorout) {
  if (inorout!=null) {if ("".equals(inorout)) inorout=null;}
  this.inorout = inorout;
 }
 public String getInoutpartyid() {
  if (inoutpartyid!=null) {if (new SysUtil().isInt(inoutpartyid)==false) inoutpartyid=null;}
  return inoutpartyid;
 }
 public void setInoutpartyid(String inoutpartyid) {
  if (inoutpartyid!=null) {if (new SysUtil().isInt(inoutpartyid)==false) inoutpartyid=null;}
  this.inoutpartyid = inoutpartyid;
 }
 public String getInoutpartyidsec() {
	  if (inoutpartyidsec!=null) {if (new SysUtil().isInt(inoutpartyidsec)==false) inoutpartyidsec=null;}
	  return inoutpartyidsec;
	 }
	 public void setInoutpartyidsec(String inoutpartyidsec) {
	  if (inoutpartyidsec!=null) {if (new SysUtil().isInt(inoutpartyidsec)==false) inoutpartyidsec=null;}
	  this.inoutpartyidsec = inoutpartyidsec;
	 }
 public String getNeedinoutallamount() {
  if (needinoutallamount!=null) {if (new SysUtil().isNum(needinoutallamount)==false) needinoutallamount=null;}
  return needinoutallamount;
 }
 public void setNeedinoutallamount(String needinoutallamount) {
  if (needinoutallamount!=null) {if (new SysUtil().isNum(needinoutallamount)==false) needinoutallamount=null;}
  this.needinoutallamount = needinoutallamount;
 }
 public String getFactamount() {
  if (factamount!=null) {if (new SysUtil().isNum(factamount)==false) factamount=null;}
  return factamount;
 }
 public void setFactamount(String factamount) {
  if (factamount!=null) {if (new SysUtil().isNum(factamount)==false) factamount=null;}
  this.factamount = factamount;
 }
 public String getNeedinoutremainamount() {
  if (needinoutremainamount!=null) {if (new SysUtil().isNum(needinoutremainamount)==false) needinoutremainamount=null;}
  return needinoutremainamount;
 }
 public void setNeedinoutremainamount(String needinoutremainamount) {
  if (needinoutremainamount!=null) {if (new SysUtil().isNum(needinoutremainamount)==false) needinoutremainamount=null;}
  this.needinoutremainamount = needinoutremainamount;
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