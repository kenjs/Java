package tf56.contract.controllers;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.util.WebUtils;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import tf56.contract.domain.ConsigneeConsignorAddress;
import tf56.contract.services.ConsigneeConsignorAddressDao;
import tf56.contract.services.AreaCityService;
import tf56.contract.controllers.Consigneeconsignoraddressc;
import tf56.sofa.util.MemCachedUtil;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Consigneeconsignoraddressc extends RestController{
	 private static Logger log = Logger.getLogger(Consigneeconsignoraddressc.class);
 //增 必须把获取的map转为bean对数据库进行操作
 @POST
 public String insert(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ConsigneeConsignorAddress consigneeConsignorAddress = new ConsigneeConsignorAddress();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  consigneeConsignorAddress = (ConsigneeConsignorAddress) pftb.parseToBean(formMap, consigneeConsignorAddress);//将map转为bean对象
  ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao"); //调用接口(实现类)
  String msgJson=consigneeConsignorAddressDao.insert(consigneeConsignorAddress);  //调用Dao同名方法
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //删 
 @POST
 public String delete(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao");
  String msgJson=consigneeConsignorAddressDao.delete(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //改
 @POST
 public String update(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ConsigneeConsignorAddress consigneeConsignorAddress = new ConsigneeConsignorAddress();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  consigneeConsignorAddress = (ConsigneeConsignorAddress) pftb.parseToBean(formMap, consigneeConsignorAddress);//将map转为bean对象
  ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao"); //调用接口(实现类)
  String msgJson=consigneeConsignorAddressDao.update(consigneeConsignorAddress);  
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 /**
  *@author wei.huang
  *@since 2013-10-9
  *修改指定记录的checked的值
  */
 @POST
 public String updateChecked(){
	 Map formMap =this.getParams();
	 formMap = SysUtil.removeFilter(formMap);
	 ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao"); //调用接口(实现类)
	 String msgJson=consigneeConsignorAddressDao.updateChecked(formMap); 
	 HttpServletResponse response=this.getResponseHelper().getResponse();
	 return new JsonResponse().responseJson(response, msgJson);
 }
 //查一条
 @POST
 public String selectById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao"); //调用接口(实现类)
  String msgJson=consigneeConsignorAddressDao.selectById(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }
 /**
  * @author wei.huang
  * @return 获取查询列表
  * @since 2013-10-7
  */
 @POST
 public String selectList(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext.getBean("consigneeConsignorAddressDao"); //调用接口(实现类)
  List list=consigneeConsignorAddressDao.selectList(formMap); //删除和查询的参数用map
  String count=consigneeConsignorAddressDao.selectCount(formMap);
  String msgJson=JsonGenerateUtil.getPageListJson(list, count);
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }
 
}
