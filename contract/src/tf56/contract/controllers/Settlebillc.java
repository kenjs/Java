package tf56.contract.controllers;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.contract.domain.SettleBill;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.PartyService;
import tf56.contract.services.SettleBillDao;
import tf56.contract.services.SettleBillService;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**结算单表，类Settlebillc.java @author lianggui.zhou @date 2013-11-13**/
public class Settlebillc extends RestController{
    
	/**
	 * @author wei.huang
	 * @date 2013-11-13
	 * @function 跳转到应收核销页面
	 */
	@GET
	public void verificationReceivable(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-14
	 * @function 跳转到(应收核销)结算单详情页面
	 */
	@GET
	public void receivableSettlebillDetail(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-15
	 * @function 跳转到应付核销页面
	 */
	@GET
	public void verificationPayable(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-15
	 * @function 跳转到(应付核销)结算单详情页面
	 */
	@GET
	public void payableSettlebillDetail(){
		
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	 * @function 结算单列表
	 */
	@POST
	public String settleBillList(){
		Map paramsMap=this.getParams();
		paramsMap=SysUtil.removeFilter(paramsMap);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		paramsMap.put("partyid", sessionBean.getPartyid());
		PartyService partyService=(PartyService)SofaSpringContext.getBean("partyService");
		String msgJson=partyService.settleBillList(paramsMap);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-20
	 * @function 根据organization名获取对应的partyid
	 * @param map(organization,inorout)inorout=1表示发货方,inorout=0表示分包商
	 */
	@POST
	public String selectPartyIdByOrganization(){
		Map params=SysUtil.removeFilter(this.getParams());
		params.put("word",params.get("organization").toString());
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		Map myMap=new HashMap();
		myMap.put("inorout",params.get("inorout").toString());
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		myMap.put("partyid",sessionBean.getPartyid());
		List<Map> inOutPartyIdList=settleBillDao.selectInOutPartyIdList(myMap);
		//将inOutPartyIdList转换为指定格式的String
		int count=inOutPartyIdList.size();
		StringBuffer sInOutPartyid=new StringBuffer();
		for(int i=0;i<count;i++){
			sInOutPartyid.append(inOutPartyIdList.get(i).get("inoutpartyid").toString());
			if(i+1<count){
				sInOutPartyid.append("-");
			}
		}
		params.put("partyidlist", sInOutPartyid.toString());
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String msg=organizationService.queryOrgName(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-20
	 * @function 审核结算单
	 */
	@POST
	public String updateStatusBySettleBillId(){
		Map params=SysUtil.removeFilter(this.getParams());
		params.put("status", "已审核");
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		String msgJson=settleBillDao.updateStatusBySettleBillId(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @function 更新实收未收金额
	 * @param map(settlebillid,billamount,status)
	 */
	@POST
	public String updateAmountBySettleBillId(){
		Map params=SysUtil.removeFilter(this.getParams());
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		String msgJson=settleBillDao.updateAmountBySettleBillId(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 * 应收结算单管理列表
	 * @author lianggui.zhou
	 * @date 2013-11-13
	 * **/
	@GET
	public void inList(){
		
	}
	/**
	 * 应收结算单管理新增页面
	 * @author lianggui.zhou
	 * @date 2013-11-13
	 * **/
	@GET
	public void inAdd(){
		
	}
	/**
	 * 运单选择添加列表
	 * @author lianggui.zhou
	 * @date 2013-11-18
	 */
	@POST
	public String addList(){
		Map params=SysUtil.removeFilter(this.getParams());
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String partyId=sessionBean.getPartyid();
		params.put("partyid", partyId);
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		List<Map<String, Object>> list=waybillDao.addList(params);
		String partyid=params.get("frompartyid").toString();
		params.clear();
		params.put("partyid",partyid);
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String result=organizationService.selectOrganizationNameByPartyId(params);
		List<Map<String, Object>> listOrg = Json2ObjectUtil.parseJSON2List(result);
		String organization="";
		for(Map org:listOrg){
			organization=org.get("name").toString();
		}
		for(Map org:list){
			org.put("organization", organization);
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.list2json(list));
	}
	/**
	 * 应付结算单管理列表
	 * @author lianggui.zhou
	 * @date 2013-11-13
	 * **/
	@GET
	public void outList(){
		
	}
	/**
	 * 应付结算单管理新增页面
	 * @author lianggui.zhou
	 * @date 2013-11-13
	 * **/
	@GET
	public void outAdd(){
		
	}
	/**
	 * 应收添加托运单页面
	 * @author lianggui.zhou
	 * @date 2013-11-13
	 * **/
	@GET
	public void inAddWayBill(){
		
		
	}
	@GET
	public void outAddWayBill(){
		
	}
	@POST
	public String insert(){
		String waybillIds=this.getParam("waybillids").toString();
		String inorout=this.getParam("inorout").toString();
		String frompartyid=this.getParam("frompartyid").toString();
		//应付保存发货方，应收没有此参数 Update by yao.xia 3014-3-12
		String inoutpartyidsec=null;
		if(this.getParam("inoutpartyidsec")!=null && 
				!this.getParam("inoutpartyidsec").equals("")){
			inoutpartyidsec = (String)this.getParam("inoutpartyidsec");//应付发货方
		}
		List wId=new ArrayList(); 
		for(Map map:Json2ObjectUtil.parseJSON2List(waybillIds)){
			wId.add(map.get("waybillid"));
		}
		Map params=new HashMap();
		params.put("inorout",inorout );
		params.put("waybillids", wId);
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		Map waybillData=waybillDao.statisticsWaybillAmount(params);
		waybillData.put("inoutpartyid", frompartyid);
		if(inorout.equals("0")){//应付结算单才保存发货方信息
			waybillData.put("inoutpartyidsec", inoutpartyidsec);//发货方id
		}
		waybillData.put("inorout", inorout);
		HttpServletRequest request = this.getResponseHelper().getRequest();
	    SessionBean sessionBean = SessionUtil.getSession(request);
		String inputman=sessionBean.getRealname();
		String partyId=sessionBean.getPartyid();
		waybillData.put("inputman", inputman);
		waybillData.put("partyid", partyId);
		SettleBill settleBill=insertSettleBill(waybillData);
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		String msg=settleBillDao.insert(settleBill);
		Map result=Json2ObjectUtil.parseJSON2Map(msg);
		String jsonMsg=null;
		if(result.get("msg").toString().equals("ok")){
			jsonMsg=updateWayBillSettleBillNumber(wId,result.get("id").toString(),inorout);
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgIdJson(jsonMsg,result.get("id").toString()));	
	}
	/***
	 *  结算单字段构造
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param map
	 * @return
	 */
	private SettleBill insertSettleBill(Map map){
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		String lastSettleBillNumber=settleBillDao.selectLastSettleBillNumber(map.get("inorout").toString());
		SettleBill settleBill=new SettleBill();
		ParseFormToBean pftb=new ParseFormToBean();
		settleBill=(SettleBill)pftb.parseToBean(map, settleBill);
		settleBill.setFactamount(null);
		Date date=new Date();
		DateFormat format= new java.text.SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");  
		settleBill.setInputdate(format.format(date));
		settleBill.setNeedinoutremainamount(map.get("needinoutallamount").toString());
		settleBill.setFactamount("0");
		settleBill.setStatus("未审核");
		settleBill.setSettlebillnumber(getWaybillNumber(lastSettleBillNumber,map.get("inorout").toString()));
		return settleBill;
	}
	/***
	 * @author lianggui.zhou
	 * 结算单号生成方法
	 * @param str：最后一条结算单号
	 * @param type：应收or应付
	 * @return
	 */
	public String getWaybillNumber(String str,String type){
		Date date =new Date();
		DateFormat format= new java.text.SimpleDateFormat("yyMMdd"); 
		String ymd= format.format(date); 
		String wnm=str.subSequence(8, str.length()).toString().replaceFirst("^0*", "");;
		String inorout=null;
		if(type.equals("1")){
			inorout="YS";
		}else{
			inorout="YF";
		}
		if(wnm.length()==0){
		    wnm="0";
		}
		int number=Integer.parseInt(wnm)+1;
		String settleBillNumber="";
		if(number/10==0){
			settleBillNumber=inorout+ymd+"00000"+number;
			return settleBillNumber;
		}
		if(number/100==0){
			settleBillNumber=inorout+ymd+"0000"+number;
			return settleBillNumber;
		}
		if(number/1000==0){
			settleBillNumber=inorout+ymd+"000"+number;
			return settleBillNumber;
		}
		if(number/10000==0){
			settleBillNumber=inorout+ymd+"00"+number;
			return settleBillNumber;
		}
		if(number/100000==0){
			settleBillNumber=inorout+ymd+"0"+number;
			return settleBillNumber;
		}
		if(number/1000000==0){
			settleBillNumber=inorout+String.valueOf(number);
			return settleBillNumber;
		}
		return settleBillNumber;
	}
	/****
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * 结算单增加成功后更新运单中结算单Id
	 * @param wayBillIdList 运单id集合
	 * @param settleBillId 结算单号
	 * @return
	 */
	private String updateWayBillSettleBillNumber(List wayBillIdList,String settleBillId,String inorout){
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		Map map=new HashMap();
		map.put("waybillids", wayBillIdList);
		map.put("settlebillid", settleBillId);
		map.put("inorout",inorout);
		String msg=waybillDao.updateWayBillSettleBillNumber(map);
		return msg;
	}
	/***
	 * 根据结算单查询已加入结算管理的运单
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param settleBillId：结算单Id
	 * @return
	 */
	@POST
	public String selectAddedList(){
		Map params=SysUtil.removeFilter(this.getParams());
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		List<Map> list=waybillDao.selectAddedList(params);
	      if(params.get("inorout").equals("0")){
	            for(Map map:list){
	                map.remove("insettlebillid");
	                map.put("settlebillid", map.get("outsettbillid"));
	            }
	        }
	       if(params.get("inorout").equals("1")){
               for(Map map:list){
                   map.remove("outsettbillid");
                   map.put("settlebillid", map.get("insettlebillid"));
               }
           }
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	/***
	 * 删除结算单下的相关运单
	 * @author lianggui.zhou
	 * @date 2013-11-20
	 * @param settleBillId：结算单Id,waybillId:运单id
	 * @return
	 */
	@POST
	public String deleteWayBillOfSettleBill(){
		Map params=SysUtil.removeFilter(this.getParams());
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		String msg=waybillDao.deleteWayBillOfSettleBill(params);
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		Map result=null;
		String jsonMsg="sorry";
		if(msg.equals("ok")){
		    if(params.get("action").equals("delSettleBill")){
		        settleBillDao.delete(params.get("settlebillid").toString());
		    }else{
		           result=waybillDao.statisticsAmountBySettleBillId(params);
		            result.put("settlebillid", params.get("settlebillid"));
		            jsonMsg=settleBillDao.update(result); 
		    }
		}
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(jsonMsg));
	}
	
	@GET
	public void outAddWaybill(){
		
	}
	@GET
	public void inUpdate(){
		
	}
	@POST
	public String wayBillOfSettleBillList(){
		Map params=SysUtil.removeFilter(this.getParams());
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		List list=waybillDao.wayBillOfSettleBillList(params);
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	@POST
	public String wayBillOfSettleBillListWithCondition(){
		Map params=SysUtil.removeFilter(this.getParams());
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		List list=waybillDao.wayBillOfSettleBillListWithCondition(params);
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	/**
	 * 更新结算单
	 * @author lianggui.zhou
	 * @date 2013-11-22
	 * @return
	 */
	@POST
	public String update(){
		String waybillIds=this.getParam("waybillids").toString();
		String inorout=this.getParam("inorout").toString();
		String settleBillId=this.getParam("settlebillid").toString();
		List wId=new ArrayList(); 
		for(Map map:Json2ObjectUtil.parseJSON2List(waybillIds)){
			wId.add(map.get("waybillid"));
		}
		Map params=new HashMap();
		params.put("inorout",inorout );
		params.put("waybillids", wId);
		params.put("settlebillid", settleBillId);
		String msg=updateWayBillSettleBillNumber(wId,settleBillId,inorout);//更新运单结算单号，若成功则更新结算单相关信息
		Map result=null;
		String jsonMsg="sorry";
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		if(msg.equals("ok")){
			result=waybillDao.statisticsAmountBySettleBillId(params);
			result.put("settlebillid", params.get("settlebillid"));
			jsonMsg=settleBillDao.update(result);
		}
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(jsonMsg));
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-22
	 * @function 根据settlebillid查询包括Waybill,Goods,WaybillAmount三张表的联合信息,主要展示运单信息
	 * @param settlebillid
	 * @return Json字符串
	 */
	@POST
	public String selectMixedWaybillBySettleBillId(){
		Map params=SysUtil.removeFilter(this.getParams());
		WaybillDao wayBillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		String msgJson=wayBillDao.selectMixedInfBySettleBillId(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	   /**选择发货方或者是分包商
     * @author lianggui.zhou
     * @date 2013-11-05
     */
    @POST
    public String selectConsignorOrConsignee() {
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        Map params = SysUtil.removeFilter(this.getParams());
        String partyId = sessionBean.getPartyid();
        String partyType=params.get("partytype").toString();
        params.put("partyid", partyId);
        ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext.getBean("shipperRelaSubContractorDao");
        List<Map> consignorIdList = shipperRelaSubContractorDao.shipperRelaSubContractorList(partyId);
        StringBuffer sb = new StringBuffer();
        int j = 0;
        for (Map map1 : consignorIdList) {// 组合总包下面的分包商partyid带入Party查询分包商信息
            if ("分包".equals(partyType)) {
                String topartyid = map1.get("topartyid") == null ? "" : map1.get("topartyid").toString();
                j++;
                if (j == consignorIdList.size() && !topartyid.equals("")) {
                    sb.append(map1.get("topartyid").toString());
                } else if (j < consignorIdList.size() && !topartyid.equals("")) {
                    sb.append(map1.get("topartyid").toString() + "-");
                }
            }if("发货方".equals(partyType)){
                String frompartyid = map1.get("frompartyid") == null ? "" : map1.get("frompartyid").toString();
                j++;
                if (j == consignorIdList.size() && !frompartyid.equals("")) {
                    sb.append(map1.get("frompartyid").toString());
                } else if (j < consignorIdList.size() && !frompartyid.equals("")) {
                    sb.append(map1.get("frompartyid").toString() + "-");
                }
            }
        }
        params.put("partyidlist", sb.toString());
        OrganizationService organizationService = (OrganizationService) SofaSpringContext.getBean("organizationService");
        String msg = organizationService.queryOrgName(params);
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, msg);
    }
    @GET
    public void outUpdate(){
        
    }
    @POST
    public String delete(){
        String settleBillId=this.getParam("settlebillid").toString();
        String inOrOut=this.getParam("inorout").toString();
        HttpServletResponse response=this.getResponseHelper().getResponse();
        SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
        String msg=settleBillDao.delete(settleBillId);
        WaybillDao wayBillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
        if(msg.equals("ok")){
            msg=wayBillDao.updateWayBillOfSettleBill(settleBillId,inOrOut);
        }
        return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(msg));
    }
    
    /**应收结算导出excel
     * @author yao.xia
     * @date 2013-12-18
     */
    /*@GET
	public void exportInExcel(){
    	HttpServletResponse response=this.getResponseHelper().getResponse();
        String title = "应收结算报表";
        SettleBillService settleBillService = (SettleBillService)SofaSpringContext.getBean("settleBillService");
        Map params = SysUtil.removeFilter(this.getParams());
        
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String partyid = sessionBean.getPartyid();
        
		params.put("partyid", partyid);
        String[] headers = { "客户订单号","运单编号", "托运日期", "提货地址","到货区域", "里程", "结算方式", "重量（吨）","体积（方）", "运费", "提货费", "送货费", "包装费","其他费用","费用合计","差异备注"};
        try{
        	settleBillService.exportInExcel(title, headers, response,params);
        }catch(IOException ex){
        	ex.printStackTrace();
        }
    }*/
    
    /**应付结算导出excel
     * @author yao.xia
     * @date 2013-12-18
     */
    /*@GET
	public void exportOutExcel(){
    	HttpServletResponse response=this.getResponseHelper().getResponse();
        String title = "应付结算报表";
        SettleBillService settleBillService = (SettleBillService)SofaSpringContext.getBean("settleBillService");
        Map params=SysUtil.removeFilter(this.getParams());
        
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String partyid = sessionBean.getPartyid();
        
		params.put("partyid", partyid);
        String[] headers = { "运单编号", "调度单号", "托运日期", "提货地址" ,"到货区域", "里程", "结算方式", "数量", "重量（吨）","体积（方）", "运费", "提货费", "送货费", "包装费","其他费用","费用合计","差异备注"};
        try{
        	settleBillService.exportOutExcel(title, headers, response,params);
        }catch(IOException ex){
        	ex.printStackTrace();
        }
    }*/
    
    /**应付结算导出excel
     * @author yao.xia
     * @date 2013-12-26
     */
    @GET
    public void exportExcel(){
    	HttpServletResponse response=this.getResponseHelper().getResponse();
    	Map params = SysUtil.removeFilter(this.getParams());
    	//params=SysUtil.getIsoDecodeMap(params);
    	//
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        String partyid = sessionBean.getPartyid();
		params.put("partyid", partyid);
		SettleBillService settleBillService = (SettleBillService)SofaSpringContext.getBean("settleBillService");
		//
		String title="";
		try{
			if(params.get("inorout").equals("0")){//付
				title = "应付结算报表";
				String[] headers = { "客户订单号","运单编号", "调度单号", "托运日期", "提货地址" ,"到货区域", "里程", "结算方式","货物名称","数量", "重量（公斤）","体积（方）", "运费", "提货费", "送货费", "包装费","其他费用","费用合计","差异备注"};
				settleBillService.exportOutExcel(title, headers, response,params);
			}else if(params.get("inorout").equals("1")){//收
				title = "应收结算报表";
				String[] headers = { "客户订单号","运单编号", "托运日期", "提货地址","到货区域", "里程", "结算方式","货物名称","重量（公斤）","体积（方）", "运费", "提货费", "送货费", "包装费","其他费用","费用合计","差异备注"};
				settleBillService.exportInExcel(title, headers, response,params);
			}
        }catch(IOException ex){
        	ex.printStackTrace();
        }
    }
    
/*    @GET
    public void exportFile(){
        HttpServletResponse response=this.getResponseHelper().getResponse();
        String filename="结算单数据导出";
       // response.setContentType("application/octet-stream;charset=GB2312"); // the encoding of this example is GB2312
        response.setContentType("application/csv;charset=gbk");
        try {
            response.setHeader("Content-Disposition","attachment; filename=\"" + new String( filename.getBytes("gb2312"), "ISO8859-1" ) + ".csv\"" );
            PrintWriter out;
            StringBuffer exportFile =new StringBuffer();
            List<Map> dateMapperList=getData();
            for(Map map:dateMapperList){
                Iterator it=map.keySet().iterator();
                StringBuffer row = new StringBuffer();
                while(it.hasNext()){
                    Object o = it.next();
                    String key1 = o.toString();
                    row.append(map.get(key1).toString()+",");
                }
                row.append("\n");
                exportFile.append(row);
            }
            try {
                out = response.getWriter();
                out.write(exportFile.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public List<Map> getData(){
        List<Map> list=new ArrayList<Map>();
        Map map= null;
        for(int i=0;i<11;i++){
            map = new HashMap();
            map.put("key1", "向晚意不适");
            map.put("key2", "驱车古道边");
            map.put("key3", "夕阳无限好");
            map.put("key4", "只是近黄昏");
            list.add(map);
        }
        return list;
    }
    public static void main(String[] args) {
        Date date=new Date();
        DateFormat format= new java.text.SimpleDateFormat("yyMMdd"); 
        System.err.println(format.format(date));
    }*/
    /**
     * @author haoyong
     * @date 13-12-26
     * 取消审核结算单
     */
    @POST
    public String cancel(){
    	Map params=SysUtil.removeFilter(this.getParams());
    	params.put("status", "未审核");
		SettleBillDao settleBillDao=(SettleBillDao)SofaSpringContext.getBean("settleBillDao");
		String msgJson=settleBillDao.updateStatusBySettleBillId(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
    }
}
