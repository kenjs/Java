package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
public class SettleStepSet {  //
 private String settlestepsetid; //
 private String settlesetid; //编码Id
 private String startvalue; //
 private String endvalue; //
 private String step; //阶梯
 private String unitprice; //报价
 public String getSettlestepsetid() {
  if (settlestepsetid!=null) {if (new SysUtil().isInt(settlestepsetid)==false) settlestepsetid=null;}
  return settlestepsetid;
 }
 public void setSettlestepsetid(String settlestepsetid) {
  if (settlestepsetid!=null) {if (new SysUtil().isInt(settlestepsetid)==false) settlestepsetid=null;}
  this.settlestepsetid = settlestepsetid;
 }
 public String getSettlesetid() {
  if (settlesetid!=null) {if (new SysUtil().isInt(settlesetid)==false) settlesetid=null;}
  return settlesetid;
 }
 public void setSettlesetid(String settlesetid) {
  if (settlesetid!=null) {if (new SysUtil().isInt(settlesetid)==false) settlesetid=null;}
  this.settlesetid = settlesetid;
 }
 public String getStartvalue() {
  if (startvalue!=null) {if ("".equals(startvalue)) startvalue=null;}
  return startvalue;
 }
 public void setStartvalue(String startvalue) {
  if (startvalue!=null) {if ("".equals(startvalue)) startvalue=null;}
  this.startvalue = startvalue;
 }
 public String getEndvalue() {
  if (endvalue!=null) {if ("".equals(endvalue)) endvalue=null;}
  return endvalue;
 }
 public void setEndvalue(String endvalue) {
  if (endvalue!=null) {if ("".equals(endvalue)) endvalue=null;}
  this.endvalue = endvalue;
 }
 public String getStep() {
  if (step!=null) {if ("".equals(step)) step=null;}
  return step;
 }
 public void setStep(String step) {
  if (step!=null) {if ("".equals(step)) step=null;}
  this.step = step;
 }
 public String getUnitprice() {
  if (unitprice!=null) {if (new SysUtil().isNum(unitprice)==false) unitprice=null;}
  return unitprice;
 }
 public void setUnitprice(String unitprice) {
  if (unitprice!=null) {if (new SysUtil().isNum(unitprice)==false) unitprice=null;}
  this.unitprice = unitprice;
 }
}