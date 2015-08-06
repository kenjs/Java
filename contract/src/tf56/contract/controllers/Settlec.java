package tf56.contract.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.domain.SettleSet;
import tf56.contract.domain.SettleStepSet;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.PartyService;
import tf56.contract.services.SettleDao;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Settlec extends RestController {

	private final Logger log = Logger.getLogger("Settlec.java");


	@GET
	public void settle_add() throws IOException {

	}
	
	@GET
	public void settle_add_jt() throws IOException {

	}

	@GET
	public void settle_set() throws IOException {

	}

	@GET
	public void settle_detail() throws IOException {
		
	}
	
	@GET
	public void settle_detail_jt() throws IOException{
		
	}

	/**
	 * @author yaoyan.lin
	 * @date 2013-11-20
	 * @function 新增记录
	 * @param frompartyid,topartyid,fromaddress,toaddress,eachweightprice,eachvolumeprice,eachtonkilometerprice
	 */
	@POST
	public String insert() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		formMap.put("frompartyid",sessionBean.getPartyid());//当前总包会员id
		formMap.put("inputdate", GetFormatDate.getCurrentDate());
		formMap.put("updatedate", GetFormatDate.getCurrentDate());
		SettleSet settleSet = new SettleSet();
		ParseFormToBean pftb = new ParseFormToBean();
		settleSet = (SettleSet) pftb.parseToBean(formMap, settleSet);// 将map转为bean对象
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao"); // 调用接口(实现类)
		String msgJson="";
		if(formMap.get("settlesetid").toString().equals("")){
			msgJson = settleDao.insert(settleSet); // 调用Dao同名方法
		}else{
			msgJson = settleDao.update(settleSet);
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2014-03-17
	 * @function 阶梯报价 新增记录
	 * @param frompartyid,topartyid,fromaddress,toaddress,startvalue,endvalue,unitprice
	 */
	@POST
	public String insert_jt() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		formMap.put("frompartyid",sessionBean.getPartyid());//当前总包会员id
		formMap.put("inputdate", GetFormatDate.getCurrentDate());
		formMap.put("updatedate", GetFormatDate.getCurrentDate());
		SettleSet settleSet = new SettleSet();
		ParseFormToBean pftb = new ParseFormToBean();
		settleSet = (SettleSet) pftb.parseToBean(formMap, settleSet);// 将map转为bean对象
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao"); // 调用接口(实现类)
		String msgJson="";
		String settleStepSetId="";
		if("".equals(formMap.get("settlesetid").toString())){
			msgJson = settleDao.insert(settleSet); // 调用Dao同名方法
			String settlesetid = (String) JSONObject.fromObject(msgJson).get("id");
			String[] settlestepsetidArr = formMap.get("settlestepsetid").toString().split(",");
			String[] startvalueArr = formMap.get("startvalue").toString().split(",");
			String[] endvalueArr = formMap.get("endvalue").toString().split(",");
			String[] unitpriceArr = formMap.get("unitprice").toString().split(",");
			String[] stepArr = formMap.get("step").toString().split(",");
			for(int i=0;i<startvalueArr.length;i++){
				SettleStepSet settleStepSet = new SettleStepSet();
				settleStepSet.setSettlesetid(settlesetid);
				settleStepSet.setStartvalue(startvalueArr[i]);
				if(endvalueArr[i]==null||"".equals(endvalueArr[i].trim())){
					settleStepSet.setEndvalue(null);
				}else{
					settleStepSet.setEndvalue(endvalueArr[i].trim());
				}
				settleStepSet.setStep(stepArr[i]);
				settleStepSet.setUnitprice(unitpriceArr[i]);
				if(settlestepsetidArr[i]==null||"".equals(settlestepsetidArr[i].trim())){
					String insert = settleDao.insert(settleStepSet);
					settleStepSetId=settleStepSetId+insert+",";
				}else{
					settleStepSet.setSettlestepsetid(settlestepsetidArr[i].trim());
					settleDao.update(settleStepSet);
				}
			}
		}else{
			msgJson = settleDao.update(settleSet);
		}
		Map<String, Object> map = Json2ObjectUtil.parseJSON2Map(msgJson);
		if(settleStepSetId.length()>0){
			map.put("settlestepsetid", settleStepSetId.substring(0, settleStepSetId.length()-1));
			HttpServletResponse response=this.getResponseHelper().getResponse();
			return new JsonResponse().responseJson(response, JsonGenerateUtil.map2json(map));
		}else{
			map.put("settlestepsetid", "update");
			HttpServletResponse response=this.getResponseHelper().getResponse();
			return new JsonResponse().responseJson(response, JsonGenerateUtil.map2json(map));
		}
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2013-11-15
	 * @function 获取结算设置列表
	 */
	@SuppressWarnings("unchecked")
	@POST
	public String selectList(){
		Map params=SysUtil.removeFilter(this.getParams());
//		SettleDao settleDao=(SettleDao)SofaSpringContext.getBean("settleDao");
//		String msg=settleDao.settleSetList(params);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		params.put("frompartyid", sessionBean.getPartyid());
		PartyService partyService = (PartyService) SofaSpringContext.getBean("partyService");//调用接口（根据发货方/分包商id获取名称）
		String msg = partyService.settleSetList(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * 结算设置添加时选择发货方
	 * @author yaoyan.lin
	 * @date 2013-11-20
	 */
	@POST
	public String selectToPartyId(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String organization=this.getParam("topartyid").toString();
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		Random random=new Random();
		String organizationid=organizationService.selecteOrganizationIdByName(organization+"-"+random.nextInt(10000000));
		List list=Json2ObjectUtil.parseJSON2List(organizationid);
		String fromPartyId="";
		String msg="0";
		String partyId=sessionBean.getPartyid();
		String partyType=this.getParam("partyType").toString();
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		List<Map> consignorIdList=shipperRelaSubContractorDao.contractAndSubcontractList(partyId, partyType, "",new HashMap());
		if(list.size()>0){
			Map fromPartyMap = null;
			for(int i=0;i<list.size();i++){
				fromPartyMap=(Map)list.get(i);
				fromPartyId=fromPartyMap.get("partyid").toString();
				for(Map consignorIdMap:consignorIdList){
					if("分包".equals(partyType)){
						if(consignorIdMap.get("topartyid").toString().equals(fromPartyId)){
							msg="1";
							break;
						}
					}else{
						if(consignorIdMap.get("frompartyid").toString().equals(fromPartyId)){
							msg="1";
							break;
						}
					}
				}
			}
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgIdJson(msg, fromPartyId));
	}
	@POST
	public String isExist(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		params.put("frompartyid",sessionBean.getPartyid());//当前总包会员id
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String msg=settleDao.isExist(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msg));
	}

	@POST
	public String isExist_jt(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		params.put("frompartyid",sessionBean.getPartyid());//当前总包会员id
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String msg=settleDao.isExist_jt(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msg));
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2013-11-20
	 * @function 删除记录
	 * @param settleSetId
	 */
	@POST
	public String delete() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String msgJson = settleDao.delete(formMap); // 删除和查询的参数用map
		settleDao.delete(formMap);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2013-03-18
	 * @function 删除记录
	 * @param settleSetId
	 */
	@POST
	public String delete_jt() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		settleDao.delete_jt(formMap);
		String result = settleDao.selectJtCount(formMap);
		String msgJson = "ok";
		if("0".equals(result)){
			msgJson = settleDao.delete(formMap); // 删除和查询的参数用map
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2013-03-19
	 * @function 删除记录
	 * @param settleSetId
	 */
	@POST
	public String save_delete() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String settlestepsetids = formMap.get("deleteFlag").toString();
		String[] settlestepsetid = settlestepsetids.split(",");
		String msgJson = "";
		for(String str:settlestepsetid){
			if(!"".equals(str.trim())){
				formMap.put("settlestepsetid", str);
				msgJson = settleDao.save_delete(formMap);
			}
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	@POST
	public String insertSettleSet(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		String data=params.get("jsonText").toString();
		String topartyid=params.get("topartyid").toString();
		String type=params.get("type").toString();
		String reporttype=params.get("reporttype").toString();
		System.err.println(Json2ObjectUtil.parseJSON2List(data));
		List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
		dataList=Json2ObjectUtil.parseJSON2List(data);
		SettleSet settleSet=new SettleSet();
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String msg="ok";
		List list=new ArrayList();
		Map mapRe=null;
		for(Map map:dataList){
			if("".equals(map.get("settlesetid"))){
				ParseFormToBean pftb=new ParseFormToBean();
				map.put("frompartyid", sessionBean.getPartyid());
				map.put("topartyid", topartyid);
				map.put("type", type);
				map.put("reporttype", reporttype);
				map.put("inputdate", GetFormatDate.getCurrentDate());
				map.put("updatedate", GetFormatDate.getCurrentDate());
				settleSet=(SettleSet)pftb.parseToBean(map, settleSet);
				msg=settleDao.insert(settleSet);
				mapRe=Json2ObjectUtil.parseJSON2Map(msg);
				list.add(mapRe);
			}else{
				ParseFormToBean pftb=new ParseFormToBean();
				map.put("frompartyid", sessionBean.getPartyid());
				map.put("topartyid", topartyid);
				map.put("type", type);
				map.put("updatedate", GetFormatDate.getCurrentDate());
				settleSet=(SettleSet)pftb.parseToBean(map, settleSet);
				msg=settleDao.update(settleSet);
				mapRe=Json2ObjectUtil.parseJSON2Map(msg);
				mapRe.put("id", map.get("settlesetid"));
				list.add(mapRe);
				
			}
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	
	@POST
	public String insertSettleSet_jt(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		String data=params.get("jsonText").toString();
		String topartyid=params.get("topartyid").toString();
		String type=params.get("type").toString();
		String reporttype=params.get("reporttype").toString();
		System.err.println(Json2ObjectUtil.parseJSON2List(data));
		List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
		dataList=Json2ObjectUtil.parseJSON2List(data);
		SettleSet settleSet=new SettleSet();
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String msg="ok";
		List list=new ArrayList();
		Map mapRe=null;
		for(Map map:dataList){
			if("".equals(map.get("settlesetid"))){
				ParseFormToBean pftb=new ParseFormToBean();
				map.put("frompartyid", sessionBean.getPartyid());
				map.put("topartyid", topartyid);
				map.put("type", type);
				map.put("reporttype", reporttype);
				map.put("inputdate", GetFormatDate.getCurrentDate());
				map.put("updatedate", GetFormatDate.getCurrentDate());
				settleSet=(SettleSet)pftb.parseToBean(map, settleSet);
				msg=settleDao.insert(settleSet);
				String settlesetid = JSONObject.fromObject(msg).getString("id");
				String[] settlestepsetidArr = map.get("settlestepsetid").toString().split(",");
				String[] startvalueArr = map.get("startvalue").toString().split(",");
				String[] endvalueArr = map.get("endvalue").toString().split(",");
				String[] unitpriceArr = map.get("unitprice").toString().split(",");
				String[] stepArr = map.get("step").toString().split(",");
				for(int i=0;i<startvalueArr.length;i++){
					SettleStepSet settleStepSet = new SettleStepSet();
					settleStepSet.setSettlesetid(settlesetid);
					settleStepSet.setStartvalue(startvalueArr[i]);
					if(endvalueArr[i]==null||"".equals(endvalueArr[i].trim())){
						settleStepSet.setEndvalue(null);
					}else{
						settleStepSet.setEndvalue(endvalueArr[i].trim());
					}
					settleStepSet.setStep(stepArr[i]);
					settleStepSet.setUnitprice(unitpriceArr[i]);
					if(settlestepsetidArr[i]==null||"".equals(settlestepsetidArr[i].trim())){
						settleDao.insert(settleStepSet);
					}
				}
				mapRe=Json2ObjectUtil.parseJSON2Map(msg);
				list.add(mapRe);
			}else{
				ParseFormToBean pftb=new ParseFormToBean();
				map.put("frompartyid", sessionBean.getPartyid());
				map.put("topartyid", topartyid);
				map.put("type", type);
				map.put("updatedate", GetFormatDate.getCurrentDate());
				settleSet=(SettleSet)pftb.parseToBean(map, settleSet);
				msg=settleDao.update(settleSet);
				String settlestepsetid = map.get("settlestepsetid").toString();
				String settlesetid = map.get("settlesetid").toString();
				String[] settlestepsetidArr = map.get("settlestepsetid").toString().split(",");
				String[] startvalueArr = map.get("startvalue").toString().split(",");
				String[] endvalueArr = map.get("endvalue").toString().split(",");
				String[] unitpriceArr = map.get("unitprice").toString().split(",");
				String[] stepArr = map.get("step").toString().split(",");
				for(int i=0;i<startvalueArr.length;i++){
					SettleStepSet settleStepSet = new SettleStepSet();
					settleStepSet.setSettlestepsetid(settlestepsetid);
					settleStepSet.setSettlesetid(settlesetid);
					settleStepSet.setStartvalue(startvalueArr[i]);
					if(endvalueArr[i]==null||"".equals(endvalueArr[i].trim())){
						settleStepSet.setEndvalue(null);
					}else{
						settleStepSet.setEndvalue(endvalueArr[i].trim());
					}
					settleStepSet.setStep(stepArr[i]);
					settleStepSet.setUnitprice(unitpriceArr[i]);
					if(settlestepsetidArr[i]==null||"".equals(settlestepsetidArr[i].trim())){
						settleDao.insert(settleStepSet);
					}else{
						settleStepSet.setSettlestepsetid(settlestepsetidArr[i].trim());
						settleDao.update(settleStepSet);
					}
				}
				mapRe=Json2ObjectUtil.parseJSON2Map(msg);
				mapRe.put("id", map.get("settlesetid"));
				list.add(mapRe);
				
			}
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	
	/**
	 * 查询制定发货方/分包商下的结算设置列表
	 * @author yaoyan.lin
	 * 
	 * **/
	@POST
	public String selectListOfSettle(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		params.put("frompartyid", sessionBean.getPartyid());
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		String msg=settleDao.settleSetListByTopartyidAndType(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg);
	}
	
	/**
	 * 查询制定发货方/分包商下的结算设置列表
	 * @author yaoyan.lin
	 * 
	 * **/
	@POST
	public String selectListOfSettle_jt(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		params.put("frompartyid", sessionBean.getPartyid());
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		List<SettleSet> list = settleDao.settleSetListByTopartyidAndType_jt(params);
		String count = settleDao.settleSetListByTopartyidAndType_jtCount(params);
		for(SettleSet s:list){
			params.put("settlesetid", s.getSettlesetid());
			List<SettleStepSet> setList = settleDao.settleStepSetList(params);
			String in_count=settleDao.settleStepSetListCount(params);
			if("0".equals(in_count)){
				break;
			}
			String settlesetid = s.getSettlesetid();
			if(settlesetid.equals(setList.get(0).getSettlesetid())){
				s.setSettlestepsetlist(setList);
			}
		}
		String msg = JsonGenerateUtil.getPageListJson(list, count);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg);
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2013-11-21
	 * @function 删除记录
	 * @param frompartyid,topartyid
	 */
	@POST
	public String deleteBySettleSetId() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		if("标准报价".equals(formMap.get("reporttype"))){
			String msgJson = settleDao.deleteByTopartyidAndType(formMap); // 删除和查询的参数用map
			HttpServletResponse response=this.getResponseHelper().getResponse();
			return new JsonResponse().responseJson(response, msgJson);
		}else{
			settleDao.deleteStep(formMap);
			String msgJson = settleDao.deleteByTopartyidAndType(formMap); // 删除和查询的参数用map
			HttpServletResponse response=this.getResponseHelper().getResponse();
			return new JsonResponse().responseJson(response, msgJson);
		}
	}
    
    /**
     * 结算设置详情json结果
     * @author yaoyan.lin
     * @date 2013-11-25
     * @return 结算设置详情
     */
    @POST
    public String settle_detail_json() {
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String frompartyid = "";
        String topartyid = "";
        String type = "";
//        Map org_info_map = null;
        List<SettleSet> result = new ArrayList<SettleSet>();
        SettleSet settleSet = null;
        Map partyMap = new HashMap();

        Map map = SysUtil.removeFilter(this.getParams());
        topartyid = map.get("topartyid").toString();
        type = map.get("type").toString();
        frompartyid = sessionBean.getPartyid();
        
        SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
        PartyService partyservice = (PartyService) SofaSpringContext.getBean("partyService");

        List<SettleSet> settleSetList = settleDao.selectList(frompartyid, topartyid, type);
        Integer count = settleDao.countSettleSetList(frompartyid, topartyid, type);
        for (int i = 0; i < settleSetList.size(); i++) {
        	settleSet = new SettleSet();
            settleSet.setFromaddress(settleSetList.get(i).getFromaddress());
            settleSet.setToaddress(settleSetList.get(i).getToaddress());
            settleSet.setEachweightprice(settleSetList.get(i).getEachweightprice());
            settleSet.setEachvolumeprice(settleSetList.get(i).getEachvolumeprice());
            settleSet.setEachtonkilometerprice(settleSetList.get(i).getEachtonkilometerprice());
            settleSet.setEachcubekilometerprice(settleSetList.get(i).getEachcubekilometerprice());
            settleSet.setEachcarprice(settleSetList.get(i).getEachcarprice());

            partyMap.clear();
            result.add(settleSet);
        }

        String str = JsonGenerateUtil.getPageListJson(result, count + "");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, str);
    }
    
    /**
     * 结算设置（阶梯）详情json结果
     * @author yao.xia
     * @date 2014-03-17
     * @return 结算设置(阶梯)详情
     */
    @POST
    public String settle_detail_jt_json() {
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String frompartyid = "";
        String topartyid = "";
        String type = "";
//        Map org_info_map = null;
        List<SettleSet> result = new ArrayList<SettleSet>();
        SettleSet settleSet = null;
        Map partyMap = new HashMap();

        Map map = SysUtil.removeFilter(this.getParams());
        topartyid = map.get("topartyid").toString();
        type = map.get("type").toString();
        frompartyid = sessionBean.getPartyid();
        
        SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
        PartyService partyservice = (PartyService) SofaSpringContext.getBean("partyService");

        List<SettleSet> settleSetList = settleDao.selectJtList(frompartyid, topartyid, type);
        Integer count = settleDao.countSettleSetList(frompartyid, topartyid, type);
        for (int i = 0; i < settleSetList.size(); i++) {
        	settleSet = new SettleSet();
        	settleSet.setSettlesetid(settleSetList.get(i).getSettlesetid());
            settleSet.setFromaddress(settleSetList.get(i).getFromaddress());
            settleSet.setToaddress(settleSetList.get(i).getToaddress());
            settleSet.setBilltype(settleSetList.get(i).getBilltype());
            settleSet.setStartvalue(settleSetList.get(i).getStartvalue());
            settleSet.setEndvalue(settleSetList.get(i).getEndvalue());
            settleSet.setUnitprice(settleSetList.get(i).getUnitprice());

            partyMap.clear();
            result.add(settleSet);
        }
        String str = JsonGenerateUtil.getPageListJson(result, count + "");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, str);
    }
    
	@POST
	public String frompartyNameList(){
	    HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		Map params=SysUtil.removeFilter(this.getParams());
		//params.put("partytype","linked");
		String partyType=params.get("partytype").toString();
		String partyId=sessionBean.getPartyid();
		params.put("partyid", partyId);
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		List<Map> consignorIdList=shipperRelaSubContractorDao.contractAndSubcontractList(partyId, partyType, params.get("frompartyid").toString(),new HashMap());
		StringBuffer sb=new StringBuffer();
		int j=0;
		for(Map map1:consignorIdList){//组合总包下面的分包商partyid带入Party查询分包商信息
			if("linked".equals(partyType)){
				String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
				j++;
				if(j==consignorIdList.size()&&!topartyid.equals("")){
					sb.append(map1.get("topartyid").toString());
				}else if(j<consignorIdList.size()&&!topartyid.equals("")){
					sb.append(map1.get("topartyid").toString()+"-");
				}
			}
			if("发货方".equals(partyType)){
                String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
                j++;
                if(j==consignorIdList.size()&&!topartyid.equals("")){
                    sb.append(map1.get("topartyid").toString());
                }else if(j<consignorIdList.size()&&!topartyid.equals("")){
                    sb.append(map1.get("topartyid").toString()+"-");
                }
            }
		}
		params.put("partyidlist", sb.toString());
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String msg=organizationService.queryOrgName(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	
}
