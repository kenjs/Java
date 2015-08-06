package tf56.contract.controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.omg.CORBA.Context;

import tf56.contract.domain.ContractAppendix;
import tf56.contract.services.ContractAppendixDao;
import tf56.contract.services.AreaCityService;
import tf56.contract.services.OrganizationService;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.MemCachedUtil;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Consignorc extends RestController {
	private static Logger log=Logger.getLogger(Consignorc.class); 
	/**
	 * @author wei.huang
	 * @return 发货方信息
	 * @since 2013.9.30
	 */
	@POST
	public String selectConsignorList(){  //控制器 查询:获取客户端提交条件数据到map, 调用实现类同名的方法,返回List类型的json数据
	  Map paramsMap=this.getParams();
	  paramsMap=SysUtil.removeFilter(paramsMap);
	  OrganizationService organizationservice=(OrganizationService) SofaSpringContext.getBean("organizationService");
	  String msgJson=organizationservice.getConsignorInf(paramsMap); //调用本外包services.impl的实现类 返回json,格式: 自定义
	  HttpServletResponse response=this.getResponseHelper().getResponse();
	  return new JsonResponse().responseJson(response, msgJson);
	 }
	/**
	 * wei.huang
	 * 2013-10-21
	 * 跳转到发货方会员导入
	 */
	@GET
	public void consignor_import(){
		
	}
	/**
	 * wei.huang
	 * 2013-10-23
	 * 跳转到修改发货方
	 */
	@GET
	public void consignor_edit(){
		
	}
	/**
	 * wei.huang
	 * 2013-10-22
	 * 跳转到发货方详情
	 */
	@GET
	public void consignor_detail(){
		
	}
	/**
	  * wei.huang
	  * @since 2013-10-21
	  * @function 通过缓存获取地址信息
	  * @throws IOException
	  */
	 @POST
	 public String ddw_areacity() throws IOException{
		String json = "";
		String key = "party-areacity-json";
		AreaCityService areaCityService = (AreaCityService) SofaSpringContext.getBean("areaCityService");//得到Dao bean
			
		if(MemCachedUtil.exists(key)&&MemCachedUtil.get(key)!=null&&MemCachedUtil.get(key)!=""){
			log.debug("缓存已存在");
			System.out.println("缓存已存在");
			json = (String) MemCachedUtil.get(key);
		}else{
			log.debug("缓存不存在");
			System.out.println("缓存不存在");
			json = areaCityService.selectAreaCityList(new HashMap());
			MemCachedUtil.put(key, json);
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}
	 @GET
	 public void consignor_link_subcontractor(){
			 
	 }
	 /**
	  * wei.huang
	  * 2013-10-24
	  * 附件上传/过滤重复重复文件
	  */
	/* @POST
	 public String consignor_upload(){
		 String workPath = System.getProperty("myappfuse.root.contract");
	     String savePath = workPath + "attachment" + File.separator;
		 
	     System.err.println(savePath);
	     File f1 = new File(savePath);
	     String tablename="";
	     String tableid="";
	     if (!f1.exists()) {
	         f1.mkdirs();
	     }
	     DiskFileItemFactory fac = new DiskFileItemFactory();
	     ServletFileUpload upload = new ServletFileUpload(fac);
	     upload.setHeaderEncoding("utf-8");
	     List fileList = null;
	     try {
	         HttpServletRequest request = this.getResponseHelper().getRequest();
			 fileList = upload.parseRequest(request);
	     } catch (FileUploadException ex) {
	    	 System.err.println(ex.getMessage());
	     }
	     Iterator<FileItem> it = fileList.iterator();
	     String name = "";
	     String extName = "";
	     String msgJson="";
	     Map map=new HashMap();
	     HttpServletResponse response = this.getResponseHelper().getResponse();
	     while (it.hasNext()){
	         FileItem item = it.next();
	         String filedName = item.getFieldName();
	         if(filedName.equals("tablename")){
	        	 tablename=item.getString();
	         }
	         if(filedName.equals("tableid")){
	        	 tableid=item.getString();
	         }
	         if (!item.isFormField()) {
	             name = item.getName();
	             long size = item.getSize();
	             String type = item.getContentType();
	             System.out.println(size + " " + type);
	             if (name == null || name.trim().equals("")) {
	                 continue;
	             }
	             File file = new File(savePath + name);
	             if(file.exists()){
	            	 return new JsonResponse().responseJson(response, "existed");
	             }
	             File saveFile = new File(savePath + name);
	             if (name.lastIndexOf(".") >= 0) {
	                 extName = name.substring(name.lastIndexOf(".")+1);
	             }
	             map.put("type", extName);
	             map.put("tablename", tablename);
	             map.put("tableid", tableid);
	             map.put("filename", name);
	             map.put("url", savePath+name);
	             String temp_str="";   
	             Date dt = new Date();
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	             temp_str=sdf.format(dt); 
	             map.put("inputdate", temp_str);
	             ContractAppendixDao contractAppendixDao=(ContractAppendixDao) SofaSpringContext.getBean("contractAppendixDao");
	       	  	 //将记录插入数据库
	             ContractAppendix contractAppendix = new ContractAppendix();  //把map转成bean对象
	             ParseFormToBean pftb = new ParseFormToBean();
	             contractAppendix = (ContractAppendix) pftb.parseToBean(map, contractAppendix);
	             msgJson=contractAppendixDao.insert(contractAppendix);
	       	  	 System.err.println(msgJson);
	             try {
	                 item.write(saveFile);
	             } catch (Exception e) {
	                 e.printStackTrace();
	                 break;
	             }
	         }
	     }
		 return new JsonResponse().responseJson(response, msgJson);
}*/	 
	 /**
	  * wei.huang
	  * 2013-10-24
	  * 附件上传/过滤重复重复文件
	  */
	 @POST
	 public String consignor_upload(){
		 String workPath = System.getProperty("myappfuse.root.contract");
	    // String savePath = workPath + "attachment" + File.separator;
			String localHostIp=tf56.contract.util.ReadPropertiesFile.getString("config/serviceHost", "localHostIp");
			Calendar calendar=Calendar.getInstance();
			String imagePath="/data/httx/pic/contract/"+String.valueOf(calendar.get(Calendar.YEAR))+File.separator
							+String.valueOf(calendar.get(Calendar.MONTH)+1)+File.separator+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
			
			
			String savePath="/pic/contract/"+String.valueOf(calendar.get(Calendar.YEAR))+File.separator
	        +String.valueOf(calendar.get(Calendar.MONTH)+1)+File.separator+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+File.separator;
	     File f1 = new File(imagePath);
	     String tablename="";
	     String tableid="";
	     if (!f1.exists()) {
	         f1.mkdirs();
	     }
	     DiskFileItemFactory fac = new DiskFileItemFactory();
	     ServletFileUpload upload = new ServletFileUpload(fac);
	     upload.setHeaderEncoding("utf-8");
	     List fileList = null;
	     try {
	         HttpServletRequest request = this.getResponseHelper().getRequest();
			 fileList = upload.parseRequest(request);
	     } catch (FileUploadException ex) {
	    	 System.err.println(ex.getMessage());
	     }
	     Iterator<FileItem> it = fileList.iterator();
	     String name = "";
	     String extName = "";
	     String msgJson="";
	     Map map=new HashMap();
	     HttpServletResponse response = this.getResponseHelper().getResponse();
	     while (it.hasNext()){
	         FileItem item = it.next();
	         String filedName = item.getFieldName();
	         if(filedName.equals("tablename")){
	        	 tablename=item.getString();
	         }
	         if(filedName.equals("tableid")){
	        	 tableid=item.getString();
	         }
	         if (!item.isFormField()) {
	             name = item.getName();
	             long size = item.getSize();
	             String type = item.getContentType();
	             System.out.println(size + " " + type);
	             if (name == null || name.trim().equals("")) {
	                 continue;
	             }
	             File file = new File(imagePath+ File.separator + name);
	             if(file.exists()){
	            	 return new JsonResponse().responseJson(response, "existed");
	             }
	             File saveFile = new File(imagePath + File.separator + name);
	             if (name.lastIndexOf(".") >= 0) {
	                 extName = name.substring(name.lastIndexOf(".")+1);
	             }
	             map.put("type", extName);
	             map.put("tablename", tablename);
	             map.put("tableid", tableid);
	             map.put("filename", name);
	             map.put("url", localHostIp+savePath+name);
	             String temp_str="";   
	             Date dt = new Date();
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	             temp_str=sdf.format(dt); 
	             map.put("inputdate", temp_str);
	             ContractAppendixDao contractAppendixDao=(ContractAppendixDao) SofaSpringContext.getBean("contractAppendixDao");
	       	  	 //将记录插入数据库
	             ContractAppendix contractAppendix = new ContractAppendix();  //把map转成bean对象
	             ParseFormToBean pftb = new ParseFormToBean();
	             contractAppendix = (ContractAppendix) pftb.parseToBean(map, contractAppendix);
	             msgJson=contractAppendixDao.insert(contractAppendix);
	             try {
	                 item.write(saveFile);
	             } catch (Exception e) {
	                 e.printStackTrace();
	                 break;
	             }
	         }
	     }
		 return new JsonResponse().responseJson(response, msgJson);
}
}
