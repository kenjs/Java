package tf56.contract.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.domain.ShipperRelaSubContractor;
import tf56.contract.services.AreaCityService;
import tf56.contract.services.ContractAttributeDao;
import tf56.contract.services.PartyService;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.services.WaybillService;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.MemCachedUtil;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Subcontractorc extends RestController {
	private final Logger log = Logger.getLogger("Subcontractorc.java");
	

	/***
	 * 分包商管理列表页面
	 * 
	 * @author lianggui.zhou
	 * @date 2013-09-09
	 */
	@GET
	public void subcontractorList() {

	}

	/***
	 * 分包商管理之增加页面
	 * 
	 * @author lianggui.zhou
	 * @date 2013-09-09
	 */
	@GET
	public void subcontractor_add() {
	}

	/***
	 * 分包商管理之详情页面
	 * 
	 * @author lianggui.zhou
	 * @date 2013-09-09
	 */
	@GET
	public void subcontractor_detail() {
	}

	/***
	 * 分包商link总包，总包link发货方or总包link发货方link分包商列表
	 * 
	 * @author lianggui.zhou
	 * @date 2013-09-10
	 * @param map
	 * @return
	 */
	@POST
	public String subcontractorList_json() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = SysUtil.removeFilter(this.getParams());
		String partyid = sessionBean.getPartyid();
		params.put("partyid", partyid);
		PartyService partyService = (PartyService) SofaSpringContext
				.getBean("partyService");
		String msg = partyService.subcontractorList(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}

	/***
	 * 分包商管理之详情页面
	 * 
	 * @author lianggui.zhou
	 * @date 2013-09-09
	 */
	@POST
	public String subcontractor_detail_json() {
		Map params = SysUtil.removeFilter(this.getParams());
		PartyService partyService = (PartyService) SofaSpringContext
				.getBean("partyService");
		String msg = partyService.querySubcontractorInfo(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}

	@GET
	public void subcontractor_edit() {

	}

	@GET
	public void party_import() {
	}

	@POST
	public String ddw_areacity() throws IOException {
		String json = "";
		String key = "json";
		AreaCityService areaCityService = (AreaCityService) SofaSpringContext
				.getBean("areaCityService");// 得到Dao bean

		if (MemCachedUtil.exists(key)) {
			log.debug("缓存已存在");
			System.out.println("缓存已存在");
			json = (String) MemCachedUtil.get(key);
		} else {
			log.debug("缓存不存在");
			System.out.println("缓存不存在");
			json = areaCityService.selectAreaCityList(new HashMap());
			MemCachedUtil.put(key, json);
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	@POST
    public String getPartyInfo() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
        String partyid = sessionBean.getPartyid();
        String partyname = sessionBean.getPartyname();
        String str = JsonGenerateUtil.getSelfDefinedJson(
                                                         "{\"partyid\":\"" + partyid + "\",\"username_all\":\""
                                                                 + partyname + "\"}").replace("\\", "");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, str);
    }

	/**
	 * 新增分包商时会员信息修改
	 * 
	 * @author lianggui.zhou
	 * @date 2013-10-16
	 * **/
	@POST
	public String updatePartyInfo() {
		Map params = SysUtil.removeFilter(this.getParams());
		PartyService partyService = (PartyService) SofaSpringContext
				.getBean("partyService");
		String msg = partyService.updatePartyInfo(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil
				.getMsgJson(msg));
	}

	/**
	 * 总包会员修改分包商基础信息或者总包修改发货方基础信息
	 * 
	 * @author lianggui.zhou
	 * @date 2013-10-21
	 * @return
	 */
	@POST
	public String conUpdateSubconBasicInfo() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = SysUtil.removeFilter(this.getParams());
		String partyid = sessionBean.getPartyid();;
		params.put("partyid", partyid);
		PartyService partyService = (PartyService) SofaSpringContext
				.getBean("partyService");
		String msg = partyService.conUpdateSubconBasicInfo(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil
				.getMsgJson(msg));
	}

	/*****************************************/
	/****
	 * 校验总包与分包是否已经关联，
	 * 
	 * @param partyid
	 *            总包partyid
	 * @param topartyid
	 *            分包商partyid
	 * @param frompartyid
	 *            发货方partyid
	 * @return ok or sorry
	 */
	@POST
	public String checkRealtionExsit() {
		Map params = SysUtil.removeFilter(this.getParams());
		System.out.println(params);
		String partyid = params.get("partyid") == null ? "" : params.get(
				"partyid").toString();
		String topartyid = params.get("topartyid") == null ? "" : params.get(
				"topartyid").toString();
		String frompartyid = params.get("frompartyid") == null ? "" : params
				.get("frompartyid").toString();
		params.put("partyid", partyid);
		params.put("topartyid", topartyid);
		params.put("frompartyid", frompartyid);
		ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext
				.getBean("shipperRelaSubContractorDao");
		String msg = shipperRelaSubContractorDao.checkRealtionExsit(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil
				.getMsgJson(msg));
	}

	/***
	 * 基础管理之分包商or发货方列表
	 * 
	 * @author lianggui.zhou
	 * @return
	 */
	@POST
	public String contractAndSubContractorList() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = SysUtil.removeFilter(this.getParams());
		String partyid = sessionBean.getPartyid();
		String partytype = params.get("partytype").toString();
		params.put("partyid", partyid);
		params.put("partytype", partytype);
		ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext
				.getBean("shipperRelaSubContractorDao");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		List<Map> list = shipperRelaSubContractorDao
				.contractAndSubcontractList(params.get("partyid").toString(),
						params.get("partytype").toString(), params
								.get("frompartyid") == null ? "" : params.get(
								"frompartyid").toString(),params);
		params.put("list", list);
		PartyService partyService = (PartyService) SofaSpringContext
				.getBean("partyService");
		String msg = partyService.contractAndSubcontractList(partyid,
				partytype, params);
		return new JsonResponse().responseJson(response, msg);
	}

	/****
	 * 总包导入发货方时，关联分包商
	 * 
	 * @author lianggui.zhou
	 * @date 2013-10-23
	 * @return
	 */
	@POST
	public String contractLinkSubContractor() {
		Map params = SysUtil.removeFilter(this.getParams());
		String partyid = params.get("partyid").toString();
		String topartyid = params.get("topartyid").toString();
		String frompartyid = params.get("frompartyid").toString();
		ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext
				.getBean("shipperRelaSubContractorDao");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		String msg = shipperRelaSubContractorDao
				.transactionContractLinkSubContractor(partyid, topartyid,
						frompartyid, params);
		return new JsonResponse().responseJson(response, msg);
	}

	/**
	 * 删除总包下的发货方
	 * 
	 * @author donghui.wang
	 * @date 2013-10-23
	 */
	@POST
	public String deleteConsigner() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map paramsMap = SysUtil.removeFilter(this.getParams());
		paramsMap.put("partyid", sessionBean.getPartyid());
		ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext
				.getBean("shipperRelaSubContractorDao");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		String msg = shipperRelaSubContractorDao.deleteConsigner(paramsMap);
		msg = JsonGenerateUtil.getMsgJson(msg);
		return new JsonResponse().responseJson(response, msg);
	}

	/**
	 * 删除总包下的分包商
	 * 
	 * @author donghui.wang
	 * @date 2013-10-23
	 */
	@POST
	public String deleteSubContract() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map paramsMap = SysUtil.removeFilter(this.getParams());
		paramsMap.put("partyid", sessionBean.getPartyid());
		ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext
				.getBean("shipperRelaSubContractorDao");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		String msg = shipperRelaSubContractorDao.deleteSubContract(paramsMap);
		msg = JsonGenerateUtil.getMsgJson(msg);
		return new JsonResponse().responseJson(response, msg);
	}

	/**
	 * 判断总包下的某一个分包商是否存在有发货方关联
	 * 
	 * @return
	 * @author donghui.wang
	 * @date 2013-10-23
	 */
	@POST
	public String isSubContractContainsSender() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map paramsMap = SysUtil.removeFilter(this.getParams());
		paramsMap.put("partyid", sessionBean.getPartyid());
		ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext
				.getBean("shipperRelaSubContractorDao");
		HttpServletResponse response = this.getResponseHelper().getResponse();
		String msg = shipperRelaSubContractorDao
				.isSubContractContainsSender(paramsMap);
		msg = JsonGenerateUtil.getMsgJson(msg);
		return new JsonResponse().responseJson(response, msg);
	}
	
	/***
	 * 分包商 银行保理列表
	 * 
	 * @author hcm
	 * @date 2014-2-27
	 * @param map
	 * @return
	 */
	@POST
	public String factoringList_json() {
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = SysUtil.removeFilter(this.getParams());
		String partyid = sessionBean.getPartyid();
		params.put("partyid", partyid);
		PartyService partyService = (PartyService) SofaSpringContext
				.getBean("partyService");
		String msg = partyService.factoringList(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * 根据frompartyid和topartyid查询分包商银行保理信息
	 * @return
	 * @author hcm
	 * @date 2014-2-27
	 */
	@SuppressWarnings("unchecked")
	@POST
    public String factoringDetail_json() {
        Map params = SysUtil.removeFilter(this.getParams());
        ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("partyid", sessionBean.getPartyid());
        List<Map> result=shipperRelaSubContractorDao.factoringDetail(params);        
        String msg = "";
        if(result.isEmpty() || result == null){
            msg = JsonGenerateUtil.getSelfDefinedJson("");;
        }else{
            msg = JsonGenerateUtil.list2json(result);
        }
        return new JsonResponse().responseJson(response, msg);
    }
	
	/**
	 * 新增银行保理
	 * @author haoy
	 * @date 14-3-3
	 */
	@GET
	public void factoring_add(){
		
	}
	
	/**
	 * 修改银行保理
	 * @author haoy
	 * @date 14-3-3
	 */
	@GET
	public void factoring_update(){
		
	}

	/**
	 * 根据partyid和topartyid查询分包商银行保理发货方信息（新增）
	 * @return
	 * @author hcm
	 * @date 2014-2-27
	 */
	@SuppressWarnings("unchecked")
	@POST
    public String factoringDetailFhf_json() {
        Map params = SysUtil.removeFilter(this.getParams());
        ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("partyid", sessionBean.getPartyid());
        List<ShipperRelaSubContractor> result=shipperRelaSubContractorDao.factoringDetailFhf(params);
        //取发货方名称
        WaybillService waybillService = (WaybillService) SofaSpringContext.getBean("waybillService");
        params.put("partytype", "发货方");
		String consignorStr = waybillService.combiWaybillList(params);
		Map consignorMap = Json2ObjectUtil.parseJSON2Map(consignorStr);
		List consignorList = (List) consignorMap.get("Data");
		
		List list1 = new ArrayList();
		ShipperRelaSubContractor dom = null;
		Map mapTemp = null;
		String partyId = "";
		for (ShipperRelaSubContractor obj:result) {
			dom  = obj;
			for (Object object : consignorList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				if(partyId.equals(dom.getFrompartyid())){
					dom.setFrompartyname((String)mapTemp.get("organization"));
				}
			}
			list1.add(dom);
		}
		
        String msg = "";
        if(list1.isEmpty() || list1 == null){
            msg = JsonGenerateUtil.getSelfDefinedJson("");;
        }else{
            msg = JsonGenerateUtil.list2json(list1);
        }
        return new JsonResponse().responseJson(response, msg);
    }
	/**
	 * 银行保理变更状态
	 * @return
	 * @author hcm
	 */
	@SuppressWarnings("unchecked")
	@POST
	public String updateStatus(){
		Map params = SysUtil.removeFilter(this.getParams());
        ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("partyid", sessionBean.getPartyid());
        String msg = contractAttributeDao.updateStatus(params);
		return new JsonResponse().responseJson(response, msg);
	}
	
	/**
	 * 新增银行保理
	 * @author haoy
	 * @date 14-3-3
	 * @return
	 */
	@POST
	public String do_save(){
		Map params = SysUtil.removeFilter(this.getParams());
		Map map2 = new HashMap();
		String msg = "ok";
        ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("partyid", sessionBean.getPartyid());
        String date = GetFormatDate.getCurrentDate();
        String data = params.get("jsonText").toString();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList = Json2ObjectUtil.parseJSON2List(data);
		map2.put("frompartyid", sessionBean.getPartyid());
		map2.put("shipperorsubcontractor", "0");
		map2.put("topartyid",params.get("topartyid").toString());
		//保存银行监管账号
		map2.put("attributename", "银行监管账号");
		map2.put("attributevalue", params.get("bankcount").toString());
		contractAttributeDao.saveContractAttribute(map2);
		
		//保存组织机构代码证
		map2.put("attributename", "组织机构代码证");
		map2.put("attributevalue", params.get("organicount").toString());
		contractAttributeDao.saveContractAttribute(map2);
		
		//保存状态
		map2.put("attributename", "银行保理状态");
		map2.put("attributevalue", "停用");
		contractAttributeDao.saveContractAttribute(map2);

		//map.put("updateMan", sessionBean.getOperator());
		//map.put("updateDate", sessionBean.getOperator())
		for (Map map : dataList) {
			map.put("topartyid", params.get("topartyid").toString());
			map.put("bankfactoringid", params.get("bankfactoringid").toString());
			map.put("partyid", sessionBean.getPartyid());
			map.put("inputman", sessionBean.getRealname());
			map.put("inputdate", date);
			msg = contractAttributeDao.save(map);
		}
		String msgJson=JsonGenerateUtil.getMsgJson(msg);
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 * 修改银行保理
	 * @author haoy
	 * @date 14-3-3
	 * @return
	 */
	@POST
	public String update_save(){
		Map params = SysUtil.removeFilter(this.getParams());
		Map map2 = new HashMap();
		String msg = "ok";
        ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("partyid", sessionBean.getPartyid());
        String date = GetFormatDate.getCurrentDate();
        String data = params.get("jsonText").toString();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList = Json2ObjectUtil.parseJSON2List(data);
		map2.put("frompartyid", sessionBean.getPartyid());
		map2.put("shipperorsubcontractor", "0");
		map2.put("topartyid",params.get("topartyid").toString());
		//修改银行监管账号
		map2.put("attributename", "银行监管账号");
		map2.put("attributevalue", params.get("bankcount").toString());
		contractAttributeDao.updateContractAttribute(map2);
		
		//修改组织机构代码证
		map2.put("attributename", "组织机构代码证");
		map2.put("attributevalue", params.get("organicount").toString());
		contractAttributeDao.updateContractAttribute(map2);
		
		for (Map map : dataList) {
			map.put("topartyid", params.get("topartyid").toString());
			map.put("partyid", sessionBean.getPartyid());
			map.put("updateman", sessionBean.getRealname());
			map.put("updatedate", date);			
			msg = contractAttributeDao.update(map);
			//判断发货方是否已加入银行保理，是->update，否->insert			
			if(! "ok".equals(msg)){
				map.remove("updateman");
				map.remove("updatedate");
				map.put("inputman", sessionBean.getRealname());
				map.put("inputdate", date);
				msg = contractAttributeDao.save(map);
			}
		}
		String msgJson=JsonGenerateUtil.getMsgJson(msg);
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 *  检验分包商是否已经加入银行保理
	 * @author haoy
	 * @date 2014-3-4
	 *
	 */
	@POST
	public String factoring_validate(){
		Map params = SysUtil.removeFilter(getParams());
		HttpServletResponse response = this.getResponseHelper().getResponse();
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
		String msg = "";
		SessionBean bean = SessionUtil.getSession(request);
		String partyid = bean.getPartyid();
		params.put("partyid", partyid);
		msg = contractAttributeDao.checkExists(params);
		return new JsonResponse().responseJson(response, msg);
	}
}