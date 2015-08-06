package tf56.contract.controllers;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.contract.domain.WaybillAmount;
import tf56.contract.services.SettleBillDao;
import tf56.contract.services.WaybillAmountDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillService;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Waybillamountc extends RestController{
 //增 必须把获取的map转为bean对数据库进行操作
 //改
 @POST
 public void update() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillAmount waybillAmount = new WaybillAmount();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  waybillAmount = (WaybillAmount) pftb.parseToBean(formMap, waybillAmount);//将map转为bean对象
  WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); //调用接口(实现类)
  String msgJson=waybillAmountDao.update(waybillAmount);  
  this.renderPage("list.html");
 }

 //查一条
 @POST
 public String selectById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); //调用接口(实现类)
  String msgJson=waybillAmountDao.selectById(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回 Json格式: 自定义
 }
 /**
  * hcm
  * 保存费用
  * @throws IOException
  */
 @SuppressWarnings("unchecked")
@POST
 public String saveWaybillamount() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  formMap.put("jfzt", "已计费");
  WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); //调用接口(实现类)
  String msgJson="";
  try {
	  msgJson=waybillAmountDao.transactionSaveWaybillamount(formMap);  //调用Dao同名方法
	} catch (Exception e) {
	  msgJson=JsonGenerateUtil.getMsgIdJson("fales", "");
  }
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
 }
 /**
  * hcm
  * 结算管理中修改的保存费用
  * @throws IOException
  */
 @SuppressWarnings("unchecked")
@POST
 public String saveWaybillamountWhenSettleBill() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  //formMap.put("jfzt", "已结算");无已结算状态
  formMap.put("jfzt", "已确认");
  WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); //调用接口(实现类)
  WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
  SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
  String msgJson="",jsonMsg="";
  try {
	  msgJson=waybillAmountDao.transactionSaveWaybillamountWhenSettleBill(formMap);
	  Map  result=waybillDao.statisticsAmountBySettleBillId(formMap);
      result.put("settlebillid", formMap.get("settlebillid"));
      jsonMsg=settleBillDao.update(result);
      if(!("ok".equals(jsonMsg))){
    	  msgJson=JsonGenerateUtil.getMsgIdJson("fales", "");
      }
	} catch (Exception e) {
	  msgJson=JsonGenerateUtil.getMsgIdJson("fales", "");
  }
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
 }
/**
 * 查运单详情
 * hcm
 * @return
 */
 @SuppressWarnings("unchecked")
@POST
 public String selectWaybillById(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillService waybillService = (WaybillService)SofaSpringContext.getBean("waybillService");
  String msgJson = waybillService.selectWaybillById(formMap);
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回 Json格式: 自定义
 }
 //查多条
 @POST
 public String selectList(){
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); //调用接口(实现类)
  String msgJson=waybillAmountDao.selectList(formMap); //删除和查询的参数用map
  HttpServletResponse response=this.getResponseHelper().getResponse();
  return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式: 自定义
 }
 
 
 /**
  * 打开费用确认页面
  * @author donghui.wang
  * @date 2013-11-23
  */
 @GET
 public void waybillamountconfirmlist(){
     
 }
 
 /**
  * 根据waybillid更新运单的计费状态
  * @author donghui.wang
  * @date 2013-11-23
  * @return 更新是否成功
  */
 @POST
 public String updateBillStatusByWaybillId(){
     Map formMap =this.getParams();
     formMap = SysUtil.removeFilter(formMap);
     WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); //调用接口(实现类)
     
     String waybillId = formMap.get("waybillid").toString();
     String billStatus = formMap.get("billstatus").toString();
     String result = waybillAmountDao.updateBillStatusByWaybillId(waybillId, billStatus);
     HttpServletResponse response=this.getResponseHelper().getResponse();
     return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(result)); //返回给 Json格式: 自定义
 }
 
 /**
  * 费用录入页面
  * @author donghui.wang
  * @date 2013-11-23
  */
 @GET
 public void waybillamountList(){
     
 }
}