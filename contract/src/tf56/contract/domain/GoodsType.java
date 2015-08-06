package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
/**
 * @author wei.huang
 * @date 2013-10-15
 */
public class GoodsType {  //
 private String goodstypeid; //
 private String frompartyid; //父会员ID
 private String topartyid; //承接货盘的会员ID
 private String checked; //
 private String goodsname; //货物名称
 private String helpcode; //
 private String packAge; //
 private String unit;
 private String unitweight; //
 private String unitvolume; //
 private String chargetype; //
 private String goodstype;
 private String spec;
 private String model;
 private String inputdate; //
 private String inputman; //
 
public String getUnit() {
    if (unit!=null) {if (new SysUtil().isInt(unit)==false) unit=null;}
    return unit;
}

public void setUnit(String unit) {
    if (unit!=null) {if (new SysUtil().isInt(unit)==false) unit=null;}
    this.unit = unit;
}
public String getGoodstypeid() {
  if (goodstypeid!=null) {if (new SysUtil().isInt(goodstypeid)==false) goodstypeid=null;}
  return goodstypeid;
 }
 public void setGoodstypeid(String goodstypeid) {
  if (goodstypeid!=null) {if (new SysUtil().isInt(goodstypeid)==false) goodstypeid=null;}
  this.goodstypeid = goodstypeid;
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
 public String getChecked() {
  if (checked!=null) {if ("".equals(checked)) checked=null;}
  return checked;
 }
 public void setChecked(String checked) {
  if (checked!=null) {if ("".equals(checked)) checked=null;}
  this.checked = checked;
 }
 public String getGoodsname() {
  if (goodsname!=null) {if ("".equals(goodsname)) goodsname=null;}
  return goodsname;
 }
 public void setGoodsname(String goodsname) {
  if (goodsname!=null) {if ("".equals(goodsname)) goodsname=null;}
  this.goodsname = goodsname;
 }
 public String getHelpcode() {
  if (helpcode!=null) {if ("".equals(helpcode)) helpcode=null;}
  return helpcode;
 }
 public void setHelpcode(String helpcode) {
  if (helpcode!=null) {if ("".equals(helpcode)) helpcode=null;}
  this.helpcode = helpcode;
 }
 public String getPackage() {
  if (packAge!=null) {if ("".equals(packAge)) packAge=null;}
  return packAge;
 }
 public void setPackage(String packAge) {
  if (packAge!=null) {if ("".equals(packAge)) packAge=null;}
  this.packAge = packAge;
 }
 public String getUnitweight() {
  if (unitweight!=null) {if (new SysUtil().isNum(unitweight)==false) unitweight=null;}
  return unitweight;
 }
 public void setUnitweight(String unitweight) {
  if (unitweight!=null) {if (new SysUtil().isNum(unitweight)==false) unitweight=null;}
  this.unitweight = unitweight;
 }
 public String getUnitvolume() {
  if (unitvolume!=null) {if (new SysUtil().isNum(unitvolume)==false) unitvolume=null;}
  return unitvolume;
 }
 public void setUnitvolume(String unitvolume) {
  if (unitvolume!=null) {if (new SysUtil().isNum(unitvolume)==false) unitvolume=null;}
  this.unitvolume = unitvolume;
 }
 public String getChargetype() {
  if (chargetype!=null) {if ("".equals(chargetype)) chargetype=null;}
  return chargetype;
 }
 public void setChargetype(String chargetype) {
  if (chargetype!=null) {if ("".equals(chargetype)) chargetype=null;}
  this.chargetype = chargetype;
 }
 public String getGoodstype() {
	  if (goodstype!=null) {if ("".equals(goodstype)) goodstype=null;}
	  return goodstype;
 }
 public void setGoodstype(String goodstype) {
	  if (goodstype!=null) {if ("".equals(goodstype)) goodstype=null;}
	  this.goodstype = goodstype;
 }
 public String getSpec() {
	  if (spec!=null) {if ("".equals(spec)) spec=null;}
	  return spec;
}
public void setSpec(String spec) {
	  if (spec!=null) {if ("".equals(spec)) spec=null;}
	  this.spec = spec;
}
public String getModel() {
	  if (model!=null) {if ("".equals(model)) model=null;}
	  return model;
}
public void setModel(String model) {
	  if (model!=null) {if ("".equals(model)) model=null;}
	  this.model = model;
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
