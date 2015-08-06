package tf56.contract.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.contract.services.ContractAppendixDao;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.PactDao;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Pactc extends RestController{
/*	*//**合同管理列表 
	 * @author lianggui.zhou
	 * @date 2013-09-16
	 * ***//*
	@POST
	public String selectPactsList(){
		Map map=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		String msg=pactDao.selectPactsList(map);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	*//**
	 * 增加合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 *//*
	@POST
	public String addPact(){
		Map map=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		String msg=pactDao.transactionAddPact(map);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	*//**
	 * 修改合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 *//*
	@POST
	public String editPact(){
		Map params=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		String msg=pactDao.editPact(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	*//**
	 * 根据pactid查询pact记录详情
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 *//*
	@POST
	public String queryPactDetailById(){
		Map map=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		ContractAppendixDao contractAppendixDao=(ContractAppendixDao)SofaSpringContext.getBean("contractAppendixDao");
		Map map1=pactDao.queryPactDetailById(map);
		List list=contractAppendixDao.selectList(map);
		map1.put("list",list);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.map2json(map1));
	}
	*//****
	 * 合同删除，把并删除合同附件
	 * @author lianggui.zhou
	 * @param pactid 合同id
	 * @date 2013-10-22
	 *//*
	@POST
	public String pactDel(){
		Map map=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		String msg=pactDao.pactDel(map);
		return new JsonResponse().responseJson(response, msg);
	}
	*//**
	 * 合同管理下附件删除
	 * @author lianggui.zhou
	 * @date 2013-10-22
	 * **//*
	@POST
	public String pact_under_contractappendix_delete(){
		Map params=SysUtil.removeFilter(this.getParams());
		ContractAppendixDao contractAppendixDao=(ContractAppendixDao)SofaSpringContext.getBean("contractAppendixDao");
		String msg=contractAppendixDao.delete(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}*/
	@GET
	public void pactsList(){
		
	}
	@GET
	public void pactsEdit(){
		
	}
	@POST
	public String pactsList_data(){
		Map params=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

	    params.put("partyid", sessionBean.getPartyid());
		String msg=pactDao.selectPactsList(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	@GET
	public void pactsAdd(){
		
	}
	@POST
	public String pactsAdd_data(){
		Map params=SysUtil.removeFilter(this.getParams());
		String fromto=params.get("frompartyrealname").toString()+"-"+params.get("topartyrealname");
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		params.put("partyid", sessionBean.getPartyid());
		params.put("inputman", sessionBean.getRealname());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		String msg=pactDao.transactionAddPact(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		//return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msg));
		return new JsonResponse().responseJson(response, msg);
	}	 
	@POST
	public String autoCompleteServlet(){
		Map params=SysUtil.removeFilter(this.getParams());
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String msg=organizationService.queryOrgName(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg);
	}
	@POST
	/***
	 * 根据pactid查询pact记录
	 * @author lianggui.zhou
	 * @date 2013-09-22
	 */
	public String pactDetail(){
		Map map=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		ContractAppendixDao contractAppendixDao=(ContractAppendixDao)SofaSpringContext.getBean("contractAppendixDao");
		Map map1=pactDao.queryPactDetailById(map);
		List list=contractAppendixDao.selectList(map);
		map1.put("list",list);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.map2json(map1));
	}
	/**
	 * 修改合同
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	@POST
	public String pactsEdit_data(){
		Map params=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		params.put("inputman", sessionBean.getRealname());
		String msg=pactDao.editPact(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg);
	}
	/**
	 * 根据pactid删除pact记录
	 * @author lianggui.zhou @date 2013-09-17
	 * @param map
	 * @return
	 */
	@POST
	public String pactDel(){
		Map map=SysUtil.removeFilter(this.getParams());
		PactDao pactDao=(PactDao)SofaSpringContext.getBean("pactDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		String msg=pactDao.pactDel(map);
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msg));
	}
	/**
	 * 合同管理下附件删除
	 * @author lianggui.zhou
	 * @date 2013-10-22
	 * **/
	@POST
	public String pact_under_contractappendix_delete(){
		Map params=SysUtil.removeFilter(this.getParams());
		ContractAppendixDao contractAppendixDao=(ContractAppendixDao)SofaSpringContext.getBean("contractAppendixDao");
		String msg=contractAppendixDao.delete(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg);
	}
	@GET
	public void pactsDetail(){
	    
	}
}
