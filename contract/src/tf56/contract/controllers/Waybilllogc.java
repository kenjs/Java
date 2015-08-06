package tf56.contract.controllers;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.contract.domain.WaybillLog;
import tf56.contract.services.WaybillLogDao;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Waybilllogc extends RestController{
 //增 必须把获取的map转为bean对数据库进行操作
 @POST
 public void insert() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
  String msgJson=waybillLogDao.insert(formMap);  //调用Dao同名方法
  this.renderPage("list.html");
 }

 //删 
 @POST
 public void delete() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao");
  String msgJson=waybillLogDao.delete(formMap); //删除和查询的参数用map
  this.renderPage("list.html");
 }

 //改
 @POST
 public void update() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillLog waybillLog = new WaybillLog();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  waybillLog = (WaybillLog) pftb.parseToBean(formMap, waybillLog);//将map转为bean对象
  WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
  String msgJson=waybillLogDao.update(waybillLog);  
  this.renderPage("list.html");
 }

 //查一条
 @POST
 public String selectById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
  String msgJson=waybillLogDao.selectById(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回 Json格式: 自定义
 }

 //查多条
 @POST
 public String selectList(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
  String msgJson=waybillLogDao.selectList(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
 }

 /**
  * 根据运单id查找运单日志信息
  * @author haoyong
  * @date 2013-11-25
  * @return
  */
 @POST
 public String selectWaybillLogList(){
	 Map map = SysUtil.removeFilter(getParams());
	 String msgJson = "";
	 WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
	 List waybillloglist = waybillLogDao.selectWaybillLogList(map.get("waybillid").toString());
	 msgJson = JsonGenerateUtil.list2json(waybillloglist);
	 HttpServletResponse response = this.getResponseHelper().getResponse();
	 return new JsonResponse().responseJson(response, msgJson);
 }
}