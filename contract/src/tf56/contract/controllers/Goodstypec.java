package tf56.contract.controllers;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.contract.domain.GoodsType;
import tf56.contract.services.GoodsTypeDao;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Goodstypec extends RestController{
 //增 必须把获取的map转为bean对数据库进行操作
 @POST
 public String insert(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsType goodsType = new GoodsType();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  goodsType = (GoodsType) pftb.parseToBean(formMap, goodsType);//将map转为bean对象
  GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext.getBean("goodsTypeDao"); //调用接口(实现类)
  String msgJson=goodsTypeDao.insert(goodsType);  //调用Dao同名方法
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //删 
 @POST
 public String delete(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext.getBean("goodsTypeDao");
  String msgJson=goodsTypeDao.delete(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //改
 @POST
 public String update(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsType goodsType = new GoodsType();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  goodsType = (GoodsType) pftb.parseToBean(formMap, goodsType);//将map转为bean对象
  GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext.getBean("goodsTypeDao"); //调用接口(实现类)
  String msgJson=goodsTypeDao.update(goodsType);  
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
 }

 //查一条
 @POST
 public String selectById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext.getBean("goodsTypeDao"); //调用接口(实现类)
  String msgJson=goodsTypeDao.selectById(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }

 /**
  * wei.huang
  * 2013-10-15
  * 获取货物列表
  * @return
  */
 @POST
 public String selectList(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext.getBean("goodsTypeDao"); //调用接口(实现类)
  List list=goodsTypeDao.selectList(formMap); //删除和查询的参数用map
  String count=goodsTypeDao.getCount(formMap);
  String msgJson=JsonGenerateUtil.getPageListJson(list, count);
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }
 /**
  * wei.huang
  * 2013-10-16
  * 修改默认货物
  * @return
  */
 @POST
 public String updateChecked(){
	 Map formMap =this.getParams();
	 formMap = SysUtil.removeFilter(formMap);
	 GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext.getBean("goodsTypeDao"); //调用接口(实现类)
	 String msgJson=goodsTypeDao.updateChecked(formMap); //删除和查询的参数用map
	 HttpServletResponse response=this.getResponseHelper().getResponse();
	 return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: 自定义
 }
}
