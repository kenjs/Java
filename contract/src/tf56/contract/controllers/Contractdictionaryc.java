package tf56.contract.controllers;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.contract.domain.ContractDictionary;
import tf56.contract.services.ContractDictionaryDao;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Contractdictionaryc extends RestController{
 //增 必须把获取的map转为bean对数据库进行操作
 @POST
 public String insert(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ContractDictionary contractDictionary = new ContractDictionary();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  contractDictionary = (ContractDictionary) pftb.parseToBean(formMap, contractDictionary);//将map转为bean对象
  ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao"); //调用接口(实现类)
  String msgJson=contractDictionaryDao.insert(contractDictionary);  //调用Dao同名方法
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //删 
 @POST
 public String delete(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao");
  String msgJson=contractDictionaryDao.delete(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //改
 @POST
 public String update(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ContractDictionary contractDictionary = new ContractDictionary();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  contractDictionary = (ContractDictionary) pftb.parseToBean(formMap, contractDictionary);//将map转为bean对象
  ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao"); //调用接口(实现类)
  String msgJson=contractDictionaryDao.update(contractDictionary);  
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //查一条
 @POST
 public String selectById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao"); //调用接口(实现类)
  String msgJson=contractDictionaryDao.selectById(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }

 //查多条
 @POST
 public String selectList(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao"); //调用接口(实现类)
  List list=contractDictionaryDao.selectList(formMap); //删除和查询的参数用map
  String count=contractDictionaryDao.getCount(formMap);
  String msgJson=JsonGenerateUtil.getPageListJson(list, count);
  HttpServletResponse response=this.getResponseHelper().getResponse();
  //System.out.println(new JsonResponse().responseJson(response, msgJson));
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }
 /**
  * wei.huang
  * 验证字典是否存在
  * 2013.9.21
  * @return
  */
 @POST
 public String isExist(){
	  Map formMap =this.getParams();
	  formMap = SysUtil.removeFilter(formMap);
	  ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao"); //调用接口(实现类)
	  String msgJson=contractDictionaryDao.isExist(formMap); //删除和查询的参数用map
	  HttpServletResponse response=this.getResponseHelper().getResponse();
	  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
	 }
/**
 * wei.huang
 * 2013-10-16
 * 获取Text列表
 * @return
 */
 @POST
 public String selectTextList(){
	 Map formMap =this.getParams();
	 formMap = SysUtil.removeFilter(formMap);
	 ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext.getBean("contractDictionaryDao"); //调用接口(实现类)
	 List list=contractDictionaryDao.selectTextList(formMap); //删除和查询的参数用map
	 String msgJson=JsonGenerateUtil.list2json(list);
	 HttpServletResponse response=this.getResponseHelper().getResponse();
	 //System.out.println(new JsonResponse().responseJson(response, msgJson));
	 return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }
 /**
  * wei.huang
  * 2013-10-30
  * 字典列表
  */
 @GET
 public void contractdictionary_list(){
	 
 }
}
