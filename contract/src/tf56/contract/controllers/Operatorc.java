package tf56.contract.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.contract.services.OperatorService;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.MD5;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Operatorc extends RestController{
	
	
	/**
	 * @author hkf
	 * 修改页面
	 */
	@GET
	public void edit(){
		
	}
	@GET
	public void index_edit(){
		
	}
	@GET
	public void list(){
		
	}
	@GET
	public void index_list(){
		
	}
	
	/**
	 * @author hkf
	 * 更新密码 
	 */
	@POST
	public String update(){
		Map paramsMap = this.getParams();
		paramsMap = SysUtil.removeFilter(paramsMap);
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
	 
	   OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
	   paramsMap.put("partyid", sessionBean.getPartyid());
	   paramsMap.put("operator", sessionBean.getOperator());
	   HttpServletResponse response = this.getResponseHelper().getResponse();
       String msg=operatorService.update(paramsMap);
	   return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 子账号列表
	 * 2013.1.9
	 */
	@POST
	public String list_json(){
        Map paramsMap = this.getParams();
        paramsMap = SysUtil.removeFilter(paramsMap);
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        paramsMap.put("partyid", sessionBean.getPartyid());
        OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
        String backlist=operatorService.selectPageByKey(paramsMap);
   
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, backlist);
    }
	/**
	 * hkf
	 * 删除子账号
	 * 2013.1.9
	 */
	@GET
	public String delete(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		map.put("partyid", sessionBean.getPartyid());
		String msg=operatorService.delete(map);
		String jsonmsg=JsonGenerateUtil.getMsgJson(msg);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		   
		return new JsonResponse().responseJson(response, jsonmsg);
	}
	/**
	 * hkf
	 * 添加子账号界面
	 */
	@GET
	public void add(){
	}
	@GET
	public void index_add(){
	}
	/**
	 * hkf
	 *获取admin的partyname
	 *2013.1.31 
	 */
	@POST
	public String addPartyName(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyname=sessionBean.getPartyname();
		partyname=JsonGenerateUtil.getMsgJson(partyname);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, partyname);
	}
	
	
	
	/**
	 * hkf
	 * 验证子账号名
	 * 2013.1.9
	 */
	@GET
	public String checkoperator(){
		String operator=SysUtil.getDecode(this.getParam("operator").toString());
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		HttpServletResponse response = this.getResponseHelper().getResponse();	
		if(operator.equals("admin")){
			String jsonmsg=JsonGenerateUtil.getMsgJson("sorry");		   
			return new JsonResponse().responseJson(response, jsonmsg);
		}

		Map map=new HashMap();
		map.put("operator", operator);
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		   map.put("partyid", sessionBean.getPartyid());
		String msg=operatorService.checkoperator(map);
		msg=JsonGenerateUtil.getMsgJson(msg);
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 插入操作员
	 * 2013.1.10
	 */
	@POST
	public String save(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		Map mymap=new HashMap();
		mymap.put("operator", map.get("username"));
		mymap.put("realname", map.get("realname"));
		mymap.put("mobilenumber", map.get("mobileInfo"));
		mymap.put("telephonenumber", map.get("tel"));
		mymap.put("password", MD5.md5(map.get("pwd").toString()));
		mymap.put("securitypermission", map.get("securitypermission"));
//		mymap.put("partyid", "1");
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		mymap.put("partyid", sessionBean.getPartyid());
		
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		String msg=operatorService.save(mymap);
		msg=JsonGenerateUtil.getMsgJson(msg);
		
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * 详情
	 */
	@GET
	public void listdetail(){
		
	}
	@GET
	public void index_listdetail(){
		
	}
	/**
	 * hkf
	 * 根据operatorid查询操作员
	 */
	@GET
	public String list_query_detail_json(){
		String id=this.getParam("id").toString();
	
		Map map=new HashMap();
		
		if(id.contains("/")){
			map.put("operatorid", id.split("/")[0]);
		}else{
			map.put("operatorid", id);
		}
		//map.put("operator", id.split("/")[1]);
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		   
		map.put("partyid", sessionBean.getPartyid());
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		String msg=operatorService.selectbyid(map);	
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);

	}
	/**
	 * hkf
	 * 更新操作员信息
	 * 
	 * 2013.1.15
	 */
	@POST
	public String updateoperatormsg(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		//String operator=SysUtil.getDecode(this.getParam("operator").toString());
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		map.put("partyid", sessionBean.getPartyid());
	//	map.put("partyid", "1");
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		String msg=operatorService.updateoperatormsg(map);
		msg=JsonGenerateUtil.getMsgJson(msg);
		
		HttpServletResponse response=this.getResponseHelper().getResponse();		
		
		return new JsonResponse().responseJson(response, msg);
		
	}
	/**
	 * hkf
	 * 重置密码
	 * 2013.1.15
	 */
	@POST
	public String updateoldpassword(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		map.put("password", MD5.md5("123456s"));
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		String msg=operatorService.updateoldpassword(map);
		msg=JsonGenerateUtil.getMsgJson(msg);
		
        HttpServletResponse response=this.getResponseHelper().getResponse();		
		
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 判断是否为admin账户
	 * 2013.5.20
	 */
	@GET
	public String checkOperatocPermission(){
		String per="";
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		String oper=(String) sessionBean.getOperator();
		if(oper.equals("admin")){
			per="ok";
		}
		per=JsonGenerateUtil.getMsgJson(per);
		
		 HttpServletResponse response=this.getResponseHelper().getResponse();		
			
			return new JsonResponse().responseJson(response, per);
		
	}
	/**
	 * hkf
	 * 司机修改密码页面
	 * 2013.7.1
	 */
	@GET
	public void edit_driverpwd(){}
	

	@GET
	public void editmobilenumber(){}
	
	/**
	 * hkf
	 * 用partyid查询admin操作员信息
	 * 2013.7.11
	 * @return
	 */
	@GET
	public String selectMsgByPartyid(){
		 Map paramsMap=new HashMap();
		 HttpServletRequest request = this.getResponseHelper().getRequest();
	     SessionBean sessionBean = SessionUtil.getSession(request);

		 paramsMap.put("partyid", sessionBean.getPartyid());
		 OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		 String str=operatorService.selectAdminMsgByPartyid(paramsMap);
		 
		 HttpServletResponse response=this.getResponseHelper().getResponse();		
			
		 return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 验证手机号码唯一
	 * 2013.1.22
	 */
	@POST
	public String validateMobileNumber(){
		String mobilenumber=this.getParam("mobilenumber").toString();
		Map map=new HashMap();
		map.put("mobilenumber", mobilenumber);
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		String msg=operatorService.checkMobileNumber(map);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(msg.equals("ok")){
			 
				try {
					PrintWriter pw=response.getWriter();
					pw.print(true);
					pw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} 

			}
			else{
				try {
					PrintWriter pw=response.getWriter();
					pw.print(false);
					pw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} 

			}
		return null;
	}
	
	/**
	 * hkf
	 * 更新操作员电话号码
	 * 
	 * 2013.7.11
	 */
	@POST
	public String updateMobileNumber(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		map.put("partyid", sessionBean.getPartyid());
		OperatorService operatorService=(OperatorService) SofaSpringContext.getBean("operatorService");
		String msg=operatorService.updateMobileNumber(map);
		msg=JsonGenerateUtil.getMsgJson(msg);
		
		HttpServletResponse response=this.getResponseHelper().getResponse();		
		
		return new JsonResponse().responseJson(response, msg);
		
	}
	
}
