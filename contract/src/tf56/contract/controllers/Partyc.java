package tf56.contract.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.contract.services.PartyService;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.MD5;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Partyc extends RestController{
    
    
	/**
	 * @author ws
	 * @function 查看信息
	 */
	@GET
	public void detail() throws IOException{
		
	}
	@GET
	public void index_detail() throws IOException{
		
	}
	/**
	 * hkf
	 * 查询会员基本信息
	 * 2013.1.16
	 */
	@GET
	public String query_detail(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		Map map=new HashMap();
		map.put("partyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String str=partyservice.selectByPartyid(map);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 查询个人信息
	 * 2013.1.16
	 */
	@GET
	public String querypersondetail(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		Map map=new HashMap();
		map.put("partyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String str=partyservice.selectpersonByPartyid(map);
		
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 查询企业信息
	 * 2013.1.16
	 */
	@GET
	public String queryorganizationdetail(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		Map map=new HashMap();
		map.put("partyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String str=partyservice.selectorganizationByPartyid(map);
		
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 修改邮箱
	 * 2013.1.17
	 */
	@POST
	public String updateemail(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		String email=this.getParam("email").toString();
		Map map=new HashMap();
		map.put("partyid", partyid);
		map.put("email", email);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.updateemail(map);
		if(msg.equals("ok")){
			//session.setAttribute("email", email);
		    sessionBean.setEmail(email);
		}
		msg=JsonGenerateUtil.getMsgJson(msg);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 修改手机号码
	 * 2013.1.17
	 */
	@POST
	public String updatemobilenumber(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		String mobilenumber=this.getParam("mobilenumber").toString();
		Map map=new HashMap();
		map.put("partyid", partyid);
		map.put("mobilenumber", mobilenumber);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.updatemobilenumber(map);
		if(msg.equals("ok")){
			//session.setAttribute("mobilenumber", mobilenumber);
		    sessionBean.setMobilenumber(mobilenumber);
		}
		
		msg=JsonGenerateUtil.getMsgJson(msg);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 修改邮箱页面
	 * 2013.1.22
	 */
	@GET
	public void editemail(){
		
	}
	/**
	 * hkf
	 * 获取原来的邮箱
	 * 2013.1.22
	 */
	@POST
	public String getOldEmail(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		String email = sessionBean.getEmail();
        String str = JsonGenerateUtil.getSelfDefinedJson("{\"email\":\""+email+"\"}").replace("\\", "");
		
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 验证邮箱
	 * 2013.1.22
	 */
	@POST
	public String checkEmail(){
		String email=this.getParam("email").toString();
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.checkEmail(email);
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
	 * 验证手机号码唯一
	 * 2013.1.22
	 */
	@POST
	public String validateMobileNumber(){
		String mobilenumber=this.getParam("mobilenumber").toString();
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.checkMobilenumber(mobilenumber);
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
	 * 验证手机号码唯一
	 * 2013.1.22
	 */
	@POST
	public String validateDriverMobileNumber(){
		String mobilenumber=this.getParam("mobilenumber").toString();
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.checkDriverMobileNumber(mobilenumber);
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
	 * 修改手机号码页面
	 * 2013.1.22
	 */
	@GET
	public void editmobilenumber(){
		
	}
	@GET
	public void editdrivermobile(){
		
	}
	@GET
	public void editdrivertelephone(){
		
	}

	
	/**
	 * hkf
	 * 获取原来的手机号码
	 * 2013.1.22
	 */
	@POST
	public String getOldMobileNumber(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		String mobilenumber = sessionBean.getMobilenumber();
        String str = JsonGenerateUtil.getSelfDefinedJson("{\"mobilenumber\":\""+mobilenumber+"\"}").replace("\\", "");
		
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 修改密码
	 * 2013.1.29
	 */
	@GET
	public void editpassword(){
		
	}
	/**
	 * hkf
	 * 验证密码是否跟原密码相同
	 * 2013.1.29
	 */
	@POST
	public String checkPassword(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
	    String password = sessionBean.getPassword();
        String pass=(String) this.getParam("password");
        pass=MD5.md5(pass);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(password.endsWith(pass)){
			 
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
	 * 更新密码
	 * 2013.1.29
	 */
	@POST
	public String updatePassword(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		String password=this.getParam("password").toString();
		Map map=new HashMap();
		map.put("partyid", partyid);
		map.put("password", password);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.updatePassword(map);
		Map mymap=Json2ObjectUtil.parseJSON2Map(msg);
		if(mymap.get("msg").equals("ok")){
		    sessionBean.setPassword(MD5.md5(password));
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 查询父会员的公司名
	 * 2013.1.31
	 */
	@POST
	public String selectFromOrganization(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		Map map=new HashMap();
		map.put("topartyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String str=partyservice.selectFromOrganization(map);
		str=JsonGenerateUtil.getMsgJson(str);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 验证用户权限
	 * 2013.4.23
	 */
	@POST
	public String checkPermission(){
		String str="";
		String premission = SysUtil.getDecode(this.getParam("premission").toString());
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String prelist=sessionBean.getSecuritypermission();;
		
		if(!prelist.equals("")){
	
			List list=Json2ObjectUtil.parseJSON2List(prelist);
			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				if((map.get("SecurityPermission").toString()).equals(premission)){
					str="ok";
					break;
				}
			}
			
			
		}
		String msg=JsonGenerateUtil.getMsgJson(str);
		
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 司机信息页面
	 * 2013.7.1
	 */
	@GET
	public void driverdetail(){}
	
	/**
	 * hkf
	 * 司机详细信息
	 * 2013.7.3
	 */
	@GET
	public String queryCarDetail(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		Map map=new HashMap();
		map.put("partyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String str=partyservice.selectCarDriverMsgByPartyId(map);
		
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, str);
	}
	/**
	 * hkf
	 * 更新司机手机号码
	 * 2013.7.3
	 */
	@POST
	public String updateDriverMobileNumber(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		map.put("partyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.updateDriverMobileNumber(map);
		
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * hkf
	 * 更新司机电话号码
	 * 2013.7.3
	 */
	@POST
	public String updateDriverTelephoneNumber(){
		Map map=this.getParams();
		map=SysUtil.removeFilter(map);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String partyid = sessionBean.getPartyid();
		map.put("partyid", partyid);
		PartyService partyservice=(PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyservice.updateDriverTelephoneNumber(map);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
}
