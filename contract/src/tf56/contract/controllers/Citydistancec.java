package tf56.contract.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import tf56.contract.domain.CityDistance;
import tf56.contract.services.AreaCityService;
import tf56.contract.services.CityDistanceDao;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.PartyService;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.MemCachedUtil;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**
 * 
 * 类Citydistancec.java的实现描述：城区距离管理
 * @author donghui.wang 2013-11-5 下午01:19:40
 */
public class Citydistancec extends RestController {
	private final Logger log = Logger.getLogger("Citydistancec.java");
    
    /**
     * 城区距离详情
     * @author donghui.wang
     * @date 2013-11-5
     */
    @GET
    public void citydistance_detail(){
        
    }
    @GET
    public void citydistance_edit(){
        
    }
	/**
	 * @author wei.huang
	 * @date 2013-11-07
	 * @function 新增记录
	 * @param frompartyid,topartyid,fromaddress,toaddress,distance,inputman,updateman
	 */
	@POST
	public String insert() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		
		formMap.put("partyid",sessionBean.getPartyid().toString());//当前总包会员id
		formMap.put("inputdate", GetFormatDate.getCurrentDate());
		formMap.put("updatedate", GetFormatDate.getCurrentDate());
		formMap.put("inputman", sessionBean.getRealname().toString());
		CityDistance cityDistance = new CityDistance(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		cityDistance = (CityDistance) pftb.parseToBean(formMap, cityDistance);// 将map转为bean对象
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao"); // 调用接口(实现类)
		String msgJson="";
		if(formMap.get("citydistanceid").toString().equals("")){
			msgJson = cityDistanceDao.insert(cityDistance); // 调用Dao同名方法
		}else{
			msgJson = cityDistanceDao.update(cityDistance);
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-07
	 * @function 删除记录
	 * @param citydistanceid
	 */
	@POST
	public String delete() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
		String msgJson = cityDistanceDao.delete(formMap); // 删除和查询的参数用map
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-07
	 * @function 删除记录
	 * @param frompartyid,partyid
	 */
	@POST
	public String deleteByFromPartyIdAndPartyId() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
		String msgJson = cityDistanceDao.deleteByFromPartyIdAndPartyId(formMap); // 删除和查询的参数用map
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-07
	 * @function 修改记录
	 * @param citydistanceid,topartyid,fromaddress,toaddress,distance,updateman
	 */
	@POST
	public void update() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		formMap.put("updatedate", GetFormatDate.getCurrentDate());
		CityDistance cityDistance = new CityDistance(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		cityDistance = (CityDistance) pftb.parseToBean(formMap, cityDistance);// 将map转为bean对象
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao"); // 调用接口(实现类)
		String msgJson = cityDistanceDao.update(cityDistance);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-06
	 * @param map(partyid,skipCount,pageSize,[organization],[partyname])
	 * @function 获取城区间距离列表
	 */
	@POST
	public String selectList() throws IOException{
	Map formMap =this.getParams();
	formMap = SysUtil.removeFilter(formMap);
	HttpServletRequest request = this.getResponseHelper().getRequest();
    SessionBean sessionBean = SessionUtil.getSession(request);
	
	formMap.put("partyid",sessionBean.getPartyid().toString());//当前总包会员id
	PartyService partyService = (PartyService)SofaSpringContext.getBean("partyService"); //调用接口(实现类)
	String msgJson=partyService.cityDistanceList(formMap); 
	HttpServletResponse response=this.getResponseHelper().getResponse();
	return new JsonResponse().responseJson(response, msgJson); //返回给 Json格式:
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-07
	 * @function 跳转至城区距离列表
	 */
	@GET
	public void list(){
		
	}
    
    /**
     * 城区距离详情json结果
     * @author donghui.wang
     * @date 2013-11-5
     * @return 城区距离详情
     */
    @POST
    public String citydistance_detail_json() {
        String fromPartyId = "";
        String partyId = "";
        String org_info = "";
        Map org_info_map = null;
        List<CityDistance> result = new ArrayList<CityDistance>();
        CityDistance cityDistance = null;
        Map partyMap = new HashMap();

        Map map = SysUtil.removeFilter(this.getParams());
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        fromPartyId = map.get("frompartyid").toString();
       
        partyId = sessionBean.getPartyid().toString();
        map.put("partyid", partyId);
        CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
        PartyService partyservice = (PartyService) SofaSpringContext.getBean("partyService");

        List<CityDistance> cityDistanceList = cityDistanceDao.selectDetailList(map);
        Integer count = cityDistanceDao.countCityDistanceList(map);
        for (int i = 0; i < cityDistanceList.size(); i++) {
            cityDistance = new CityDistance();
            cityDistance.setRownum((i + 1) + "");
            cityDistance.setFromaddress(cityDistanceList.get(i).getFromaddress());
            cityDistance.setToaddress(cityDistanceList.get(i).getToaddress());
            cityDistance.setDistance(cityDistanceList.get(i).getDistance());
            cityDistance.setFrompartyid(cityDistanceList.get(i).getFrompartyid());
            cityDistance.setTopartyid(cityDistanceList.get(i).getTopartyid());
            cityDistance.setCitydistanceid(cityDistanceList.get(i).getCitydistanceid());
            partyMap.put("partyid", cityDistanceList.get(i).getTopartyid());
            org_info = partyservice.selectorganizationByPartyid(partyMap);
            if(StringUtils.isBlank(org_info)){
                cityDistance.setOrganization("");
            }else{
                org_info_map = Json2ObjectUtil.parseJSON2Map(org_info);
                cityDistance.setOrganization(org_info_map.get("organization").toString());
            }

            partyMap.clear();
            result.add(cityDistance);
        }

        String str = JsonGenerateUtil.getPageListJson(result, count + "");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, str);
    }
    @GET
	public void citydistance_add(){
		
	}
	/**城区距离添加时选择发货方
	 * @author lianggui.zhou
	 * @date 2013-11-05
	 */
	@POST
	public String selectConsignor(){
		String organization=this.getParam("organization").toString();
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		Random random=new Random();
		String organizationid=organizationService.selecteOrganizationIdByName(organization+"-"+random.nextInt(10000000));
		List list=Json2ObjectUtil.parseJSON2List(organizationid);
		String fromPartyId="";
		String msg="0";
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String partyId = sessionBean.getPartyid();
		String partyType="发货方";
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		List<Map> consignorIdList=shipperRelaSubContractorDao.contractAndSubcontractList(partyId, partyType, "",new HashMap());
		if(list.size()>0){
			Map fromPartyMap = null;
			for(int i=0;i<list.size();i++){
				fromPartyMap=(Map)list.get(i);
				fromPartyId=fromPartyMap.get("partyid").toString();
				for(Map consignorIdMap:consignorIdList){
					if(consignorIdMap.get("frompartyid").toString().equals(fromPartyId)){
						msg="1";
						break;
					}
				}
			}
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgIdJson(msg, fromPartyId));
	}
	@POST
	public String autoCompleteServlet(){
		Map params=SysUtil.removeFilter(this.getParams());
		//params.put("partytype","linked");
		String partyType=params.get("partytype").toString();
		
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String partyId = sessionBean.getPartyid();
		
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
                String frompartyid=map1.get("frompartyid")==null?"":map1.get("frompartyid").toString();
                j++;
                if(j==consignorIdList.size()&&!frompartyid.equals("")){
                    sb.append(map1.get("frompartyid").toString());
                }else if(j<consignorIdList.size()&&!frompartyid.equals("")){
                    sb.append(map1.get("frompartyid").toString()+"-");
                }
            }
		}
		params.put("partyidlist", sb.toString());
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String msg=organizationService.queryOrgName(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
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
	public String isExist(){
		Map params=SysUtil.removeFilter(this.getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		params.put("partyid",sessionBean.getPartyid().toString());//当前总包会员id
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
		String msg=cityDistanceDao.isExist(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msg));
	}
	@POST
	public String insertCityDistance(){
		Map params=SysUtil.removeFilter(this.getParams());
		String data=params.get("jsonText").toString();
		String frompartyid=params.get("frompartyid").toString();
		List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
		dataList=Json2ObjectUtil.parseJSON2List(data);
		CityDistance cityDistance=new CityDistance();
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
		String msg="ok";
		List list=new ArrayList();
		Map mapRe=null;
		for(Map map:dataList){
			if(map.get("citydistanceid").equals("")){
				ParseFormToBean pftb=new ParseFormToBean();
				map.put("frompartyid", frompartyid);
				map.put("partyid", sessionBean.getPartyid());
				map.put("inputdate", GetFormatDate.getCurrentDate());
				map.put("updatedate", GetFormatDate.getCurrentDate());
				map.put("inputman", sessionBean.getRealname());
				cityDistance=(CityDistance)pftb.parseToBean(map, cityDistance);
				msg=cityDistanceDao.insert(cityDistance);
				mapRe=Json2ObjectUtil.parseJSON2Map(msg);
				list.add(mapRe);
			}if(!map.get("citydistanceid").equals("")){
				ParseFormToBean pftb=new ParseFormToBean();
				map.put("frompartyid", frompartyid);
				map.put("partyid", sessionBean.getPartyid());
                map.put("updatedate", GetFormatDate.getCurrentDate());
                map.put("updateman", sessionBean.getRealname());
				cityDistance=(CityDistance)pftb.parseToBean(map, cityDistance);
				msg=cityDistanceDao.update(cityDistance);
				mapRe=Json2ObjectUtil.parseJSON2Map(msg);
				mapRe.put("id", map.get("citydistanceid"));
				list.add(mapRe);
				
			}
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	/**
	 * 查询制定发货方下的城区间距离列表
	 * @author lianggui.zhou
	 * 
	 * **/
	@POST
	public String selectListOfConsignor(){
		Map params=SysUtil.removeFilter(this.getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
		params.put("partyid", sessionBean.getPartyid());
		PartyService partyService = (PartyService) SofaSpringContext.getBean("partyService");
		String msg=partyService.cityDistanceListByFromAddressAndToAddress(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response,msg );
	}
	/***
	 * 检测此发货方是否已经进行过城区间距离设置，若有则返回1，无则返回0
	 * @return
	 */
	@POST
	public String checkConsignorExist(){
	    Map params=SysUtil.removeFilter(this.getParams());
	    HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("partyid", sessionBean.getPartyid());
        CityDistanceDao cityDistanceDao=(CityDistanceDao)SofaSpringContext.getBean("cityDistanceDao"); 
	    HttpServletResponse response = this.getResponseHelper().getResponse();
	    String msg=cityDistanceDao.checkConsignorExist(params);
        return new JsonResponse().responseJson(response,JsonGenerateUtil.getMsgJson(msg) );
	}
	   @POST
	    public String checkSubcontract(){
	        Map  params=SysUtil.removeFilter(this.getParams());
	        String organization=this.getParam("organization").toString();
	        OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
	        Random random=new Random();
	        String organizationid=organizationService.selecteOrganizationIdByName(organization+"-"+random.nextInt(10000000));
	        List list=Json2ObjectUtil.parseJSON2List(organizationid);
	        HttpServletRequest request = this.getResponseHelper().getRequest();
	        SessionBean sessionBean = SessionUtil.getSession(request);
	        String toPartyId="";
	        String msg="0";
	        if(list.size()>0){
	            Map fromPartyMap = null;
	            for(int i=0;i<list.size();i++){
	                fromPartyMap=(Map)list.get(i);
	                toPartyId=fromPartyMap.get("partyid").toString();
	            }
	            params.put("topartyid", toPartyId);
	            params.put("partyid", sessionBean.getPartyid());
	            ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
	            String result=shipperRelaSubContractorDao.checkRealtionExsit(params);
	            if("ok".equals(result)){
	                msg="0";toPartyId="";
	            }else{
	                msg="1";
	            }
	        }
	        HttpServletResponse response=this.getResponseHelper().getResponse();
	        return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgIdJson(msg, toPartyId));
	    }
}
