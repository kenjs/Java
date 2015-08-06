package tf56.contract.domain;

import tf56.sofa.util.SysUtil;
public class ContractAttribute {  //
 private String contractattributeid; //
 private String frompartyid; //父会员ID
 private String shipperorsubcontractor; //
 private String topartyid; //承接货盘的会员ID
 private String attributename; //属性名
 private String attributevalue; //属性值
 public String getContractattributeid() {
  if (contractattributeid!=null) {if (new SysUtil().isInt(contractattributeid)==false) contractattributeid=null;}
  return contractattributeid;
 }
 public void setContractattributeid(String contractattributeid) {
  if (contractattributeid!=null) {if (new SysUtil().isInt(contractattributeid)==false) contractattributeid=null;}
  this.contractattributeid = contractattributeid;
 }
 public String getFrompartyid() {
  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
  return frompartyid;
 }
 public void setFrompartyid(String frompartyid) {
  if (frompartyid!=null) {if (new SysUtil().isInt(frompartyid)==false) frompartyid=null;}
  this.frompartyid = frompartyid;
 }
 public String getShipperorsubcontractor() {
  if (shipperorsubcontractor!=null) {if ("".equals(shipperorsubcontractor)) shipperorsubcontractor=null;}
  return shipperorsubcontractor;
 }
 public void setShipperorsubcontractor(String shipperorsubcontractor) {
  if (shipperorsubcontractor!=null) {if ("".equals(shipperorsubcontractor)) shipperorsubcontractor=null;}
  this.shipperorsubcontractor = shipperorsubcontractor;
 }
 public String getTopartyid() {
  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
  return topartyid;
 }
 public void setTopartyid(String topartyid) {
  if (topartyid!=null) {if (new SysUtil().isInt(topartyid)==false) topartyid=null;}
  this.topartyid = topartyid;
 }
 public String getAttributename() {
  if (attributename!=null) {if ("".equals(attributename)) attributename=null;}
  return attributename;
 }
 public void setAttributename(String attributename) {
  if (attributename!=null) {if ("".equals(attributename)) attributename=null;}
  this.attributename = attributename;
 }
 public String getAttributevalue() {
  if (attributevalue!=null) {if ("".equals(attributevalue)) attributevalue=null;}
  return attributevalue;
 }
 public void setAttributevalue(String attributevalue) {
  if (attributevalue!=null) {if ("".equals(attributevalue)) attributevalue=null;}
  this.attributevalue = attributevalue;
 }
}
