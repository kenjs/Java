package tf56.contract.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import tf56.contract.domain.BankFactoring;
import tf56.contract.domain.ShipperRelaSubContractor;
import tf56.contract.services.BankFactoringDao;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.services.WaybillService;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
public class Bankfactoringc extends RestController{
 //增 必须把获取的map转为bean对数据库进行操作
 @POST
 public void insert() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  BankFactoring bankFactoring = new BankFactoring();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  bankFactoring = (BankFactoring) pftb.parseToBean(formMap, bankFactoring);//将map转为bean对象
  BankFactoringDao bankFactoringDao = (BankFactoringDao) SofaSpringContext.getBean("bankFactoringDao"); //调用接口(
  String msgJson=bankFactoringDao.insert(bankFactoring);  //调用Dao同名方法
  this.renderPage("list.html");
 }

 //改
 @POST
 public void update() throws IOException{
  Map formMap =this.getParams();
  formMap = SysUtil.removeFilter(formMap);
  BankFactoring bankFactoring = new BankFactoring();  //把map转成bean对象
  ParseFormToBean pftb = new ParseFormToBean();
  bankFactoring = (BankFactoring) pftb.parseToBean(formMap, bankFactoring);//将map转为bean对象
  BankFactoringDao bankFactoringDao = (BankFactoringDao) SofaSpringContext.getBean("bankFactoringDao"); //调用接口(

  String msgJson=bankFactoringDao.update(bankFactoring);  
  this.renderPage("list.html");
 }

 /**
	 * 根据partyid和topartyid查询分包商银行保理发货方信息（修改 详情）
	 * @return
	 * @author hcm
	 * @date 2014-2-27
	 */
	@SuppressWarnings("unchecked")
	@POST
 public String factoringDetailFhf_json2() {
     Map params = SysUtil.removeFilter(this.getParams());
     BankFactoringDao bankFactoringDao = (BankFactoringDao) SofaSpringContext.getBean("bankFactoringDao"); //调用接口
     HttpServletResponse response = this.getResponseHelper().getResponse();
     HttpServletRequest request = this.getResponseHelper().getRequest();
     SessionBean sessionBean = SessionUtil.getSession(request);
     params.put("partyid", sessionBean.getPartyid());
     String msgJson=bankFactoringDao.selectList(params); //删除和查询的参数用map
     return new JsonResponse().responseJson(response, msgJson);
 }
	/**
	 * @author wei.huang
	 * @date 2014-3-5
	 * @function 获取分包商的保理资格及账期
	 * @return
	 */
	@POST
	public String selectConsigneeBusinessDays(){
		Map params = SysUtil.removeFilter(this.getParams());
	    BankFactoringDao bankFactoringDao = (BankFactoringDao) SofaSpringContext.getBean("bankFactoringDao"); //调用接口
	    String MsgJson=bankFactoringDao.selectConsigneeBusinessDays(params);
	    HttpServletResponse response = this.getResponseHelper().getResponse();
	    return new JsonResponse().responseJson(response, MsgJson);
	    
	}
}