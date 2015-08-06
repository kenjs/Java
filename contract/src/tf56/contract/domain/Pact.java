package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
public class Pact {
	 private String pactid; //编码
	 private String partyid;//总包会员id
	 private String pactnumber; //
	 private String type; //类型
	 private String frompartyid; //拥有者会员Id
	 private String frompartyrealname; //
	 private String frompartysignman; //
	 private String topartyid; //承接货盘的会员ID
	 private String topartyrealname; //承接人真实联系人
	 private String topartysignman; //
	 private String signdate; //签订日期
	 private String enddate;//到期日期
	 private String memo; //备注
	 private String inputdate; //输入日期
	 private String inputman; //输入人
	 private String state;//状态
	 private String limitDate;//过期日期
	 public String getPactid() {
	  if (pactid!=null) {if (new SysUtil().isInt(pactid)==false) pactid=null;}
	  return pactid;
	 }
	 public void setPactid(String pactid) {
	  if (pactid!=null) {if (new SysUtil().isInt(pactid)==false) pactid=null;}
	  this.pactid = pactid;
	 }
	   public String getPartyid() {
	       if (partyid!=null) {if (new SysUtil().isInt(partyid)==false) partyid=null;}
	       return partyid;
	      }
	      public void setPartyid(String partyid) {
	       if (partyid!=null) {if (new SysUtil().isInt(partyid)==false) partyid=null;}
	       this.partyid = partyid;
	      }
	 public String getPactnumber() {
	  if (pactnumber!=null) {if ("".equals(pactnumber)) pactnumber=null;}
	  return pactnumber;
	 }
	 public void setPactnumber(String pactnumber) {
	  if (pactnumber!=null) {if ("".equals(pactnumber)) pactnumber=null;}
	  this.pactnumber = pactnumber;
	 }
	 public String getState() {
		 if (state!=null) {if ("".equals(state)) state=null;}
		 return state;
	 }
	 public void setState(String state) {
		 if (state!=null) {if ("".equals(state)) state=null;}
		 this.state = state;
	 }
	 public String getType() {
	  if (type!=null) {if ("".equals(type)) type=null;}
	  return type;
	 }
	 public void setType(String type) {
	  if (type!=null) {if ("".equals(type)) type=null;}
	  this.type = type;
	 }
	 public String getFrompartyid() {
	  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
	  return frompartyid;
	 }
	 public void setFrompartyid(String frompartyid) {
	  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
	  this.frompartyid = frompartyid;
	 }
	 public String getFrompartyrealname() {
	  if (frompartyrealname!=null) {if ("".equals(frompartyrealname)) frompartyrealname=null;}
	  return frompartyrealname;
	 }
	 public void setFrompartyrealname(String frompartyrealname) {
	  if (frompartyrealname!=null) {if ("".equals(frompartyrealname)) frompartyrealname=null;}
	  this.frompartyrealname = frompartyrealname;
	 }
	 public String getFrompartysignman() {
	  if (frompartysignman!=null) {if ("".equals(frompartysignman)) frompartysignman=null;}
	  return frompartysignman;
	 }
	 public void setFrompartysignman(String frompartysignman) {
	  if (frompartysignman!=null) {if ("".equals(frompartysignman)) frompartysignman=null;}
	  this.frompartysignman = frompartysignman;
	 }
	 public String getTopartyid() {
	  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
	  return topartyid;
	 }
	 public void setTopartyid(String topartyid) {
	  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
	  this.topartyid = topartyid;
	 }
	 public String getTopartyrealname() {
	  if (topartyrealname!=null) {if ("".equals(topartyrealname)) topartyrealname=null;}
	  return topartyrealname;
	 }
	 public void setTopartyrealname(String topartyrealname) {
	  if (topartyrealname!=null) {if ("".equals(topartyrealname)) topartyrealname=null;}
	  this.topartyrealname = topartyrealname;
	 }
	 public String getTopartysignman() {
	  if (topartysignman!=null) {if ("".equals(topartysignman)) topartysignman=null;}
	  return topartysignman;
	 }
	 public void setTopartysignman(String topartysignman) {
	  if (topartysignman!=null) {if ("".equals(topartysignman)) topartysignman=null;}
	  this.topartysignman = topartysignman;
	 }
	 public String getSigndate() {
	  if (signdate!=null) {if ("".equals(signdate)) signdate=null;}
	  return signdate;
	 }
	 public void setSigndate(String signdate) {
	  if (signdate!=null) {if ("".equals(signdate)) signdate=null;}
	  this.signdate = signdate;
	 }
	 public String getEnddate() {
		 if (enddate!=null) {if ("".equals(enddate)) enddate=null;}
		 return enddate;
	 }
	 public void setEnddate(String enddate) {
		 if (enddate!=null) {if ("".equals(enddate)) enddate=null;}
		 this.enddate = enddate;
	 }
	 public String getMemo() {
	  if (memo!=null) {if ("".equals(memo)) memo=null;}
	  return memo;
	 }
	 public void setMemo(String memo) {
	  if (memo!=null) {if ("".equals(memo)) memo=null;}
	  this.memo = memo;
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
	 public String getLimitDate() {
		 if (limitDate!=null) {if ("".equals(limitDate)) limitDate=null;}
		 return limitDate;
	 }
	 public void setLimitDate(String limitDate) {
		 if (limitDate!=null) {if ("".equals(limitDate)) limitDate=null;}
		 this.limitDate = limitDate;
	 }
}
