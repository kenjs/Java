package tf56.contract.controllers;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.contract.domain.Goods;
import tf56.contract.services.GoodsDao;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Goodsc extends RestController{
 //增 必须把获取的map转为bean对数据库进行操作
 @POST
 public void insert() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  Goods goods = new Goods();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  goods = (Goods) pftb.parseToBean(formMap, goods);//将map转为bean对象
  GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); //调用接口(实现类)
  String msgJson=goodsDao.insert(goods);  //调用Dao同名方法
  this.renderPage("list.html");
 }

 //删 
 @POST
 public void delete() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao");
  String msgJson=goodsDao.delete(formMap); //删除和查询的参数用map
  this.renderPage("list.html");
 }

 //改
 @POST
 public void update() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  Goods goods = new Goods();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  goods = (Goods) pftb.parseToBean(formMap, goods);//将map转为bean对象
  GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); //调用接口(实现类)
  String msgJson=goodsDao.update(goods);  
  this.renderPage("list.html");
 }

 //查一条
 @POST
 public String selectById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); //调用接口(实现类)
  String msgJson=goodsDao.selectById(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回 Json格式: 自定义
 }

 //查多条
 @POST
 public String selectList(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); //调用接口(实现类)
  String msgJson=goodsDao.selectList(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
 }

 /**
	 * @author yao.xia
	 * @throws IOException
	 * @date 2013-12-03
	 * @function 根据运单查货物总数
	 */
 @POST
 public String selectNumSumByWaybillId(){
	 Map formMap =this.getParams();
	  formMap = SysUtil.removeFilter(formMap);
	  GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); //调用接口(实现类)
	  String msgJson=goodsDao.selectNumSumByWaybillId(formMap); //删除和查询的参数用map
	  HttpServletResponse response=this.getResponseHelper().getResponse();
	  return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
 }
 
}