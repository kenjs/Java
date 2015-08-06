package tf56.contract.domain;
import tf56.sofa.util.SysUtil;
public class ContractAppendix {
	 private String contractappendixid; //
	 private String tablename; //
	 private String tableid; //
	 private String type; //
	 private String filename; //
	 private String url; //
	 private String inputdate; //
	 public String getContractappendixid() {
	  if (contractappendixid!=null) {if (new SysUtil().isInt(contractappendixid)==false) contractappendixid=null;}
	  return contractappendixid;
	 }
	 public void setContractappendixid(String contractappendixid) {
	  if (contractappendixid!=null) {if (new SysUtil().isInt(contractappendixid)==false) contractappendixid=null;}
	  this.contractappendixid = contractappendixid;
	 }
	 public String getTablename() {
	  if (tablename!=null) {if ("".equals(tablename)) tablename=null;}
	  return tablename;
	 }
	 public void setTablename(String tablename) {
	  if (tablename!=null) {if ("".equals(tablename)) tablename=null;}
	  this.tablename = tablename;
	 }
	 public String getTableid() {
	  if (tableid!=null) {if ("".equals(tableid)) tableid=null;}
	  return tableid;
	 }
	 public void setTableid(String tableid) {
	  if (tableid!=null) {if ("".equals(tableid)) tableid=null;}
	  this.tableid = tableid;
	 }
	 public String getType() {
	  if (type!=null) {if ("".equals(type)) type=null;}
	  return type;
	 }
	 public void setType(String type) {
	  if (type!=null) {if ("".equals(type)) type=null;}
	  this.type = type;
	 }
	 public String getFilename() {
	  if (filename!=null) {if ("".equals(filename)) filename=null;}
	  return filename;
	 }
	 public void setFilename(String filename) {
	  if (filename!=null) {if ("".equals(filename)) filename=null;}
	  this.filename = filename;
	 }
	 public String getUrl() {
	  if (url!=null) {if ("".equals(url)) url=null;}
	  return url;
	 }
	 public void setUrl(String url) {
	  if (url!=null) {if ("".equals(url)) url=null;}
	  this.url = url;
	 }
	 public String getInputdate() {
	  if (inputdate!=null) {if ("".equals(inputdate)) inputdate=null;}
	  return inputdate;
	 }
	 public void setInputdate(String inputdate) {
	  if (inputdate!=null) {if ("".equals(inputdate)) inputdate=null;}
	  this.inputdate = inputdate;
	 }
	}
