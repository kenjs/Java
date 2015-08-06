package tf56.contract.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.client.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import tf56.contract.domain.CityDistance;
import tf56.contract.domain.Waybill;
import tf56.contract.domain.WaybillStowage;
import tf56.contract.services.ContractAttributeDao;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillService;
import tf56.contract.services.WaybillStowageDao;
import tf56.contract.util.PrimaryGenerater;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.SofaSpringContext;

public class WaybillServiceImpl implements WaybillService {

	/***
	 * 查询发货方或分包商List
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String FrompartyIdList(Map map) {
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=
						(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		String url="party/organizationcs/queryOrgName";
		ClientUtil cu=new ClientUtil(url);
		StringBuffer sb=new StringBuffer();
		String msg="";
		String partytype=map.get("partytype").toString();
		try {
			List<Map> list = shipperRelaSubContractorDao.contractAndSubcontractList(map.get("partyid").toString(),map.get("partytype").toString(),map.get("frompartyid")==null?"":map.get("frompartyid").toString(),new HashMap());
			@SuppressWarnings("unused")
			int i=list.size()-1;
			int j=0;
			for(Map map1:list){//组合总包下面的分包商partyid带入Party查询分包商信息
				if("分包".equals(partytype)||"总分发".equals(partytype)||"linked".equals(partytype)){
					String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
					j++;
					if(j==list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString());
					}else if(j<list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString()+"-");
					}
				}
				if("发货方".equals(partytype)){
					String frompartyid=map1.get("frompartyid")==null?"":map1.get("frompartyid").toString();
					j++;
					if(j==list.size()&&!frompartyid.equals("")){
						sb.append(map1.get("frompartyid").toString());
					}else if(j<list.size()&&!frompartyid.equals("")){
						sb.append(map1.get("frompartyid").toString()+"-");
					}
				}
			}
			map.put("partyidlist", sb.toString());//分包商或发货方id字符串集合
			map.put("skipCount", "0");//页数
			map.put("pageSize", "100000000000");//每页 显示多少条
			//map.put("organization", "物流帝国");//查询名称
			map.put("word", map.get("organization").toString());//查询名称
			msg=cu.post(url, map).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/***
	 * 查询发货方list
	 *
	 */
	@Override
	public String FrompartyIdName(Map map) {
		String url="party/subcontractorcs/contractAndSubcontractorList";
		ClientUtil cu=new ClientUtil(url);
		StringBuffer sb=new StringBuffer();
		String msg="";
		String partytype=map.get("partytype").toString();
		try {
			map.put("partystrs", map.get("frompartyid").toString());//分包商或发货方id字符串集合
			map.put("skipCount", "0");//页数
			map.put("pageSize", "100000");//每页 显示多少条
			msg=cu.post(url, map).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 根据partyid查询发货方或者分包商详细信息
	 */
	@Override
	public String FrompartyName(Map map) {
		String url="party/subcontractorcs/querySubcontractorInfo";
		ClientUtil cu=new ClientUtil(url);
		String msg="";
		map.put("partyid", map.get("partyid").toString());//查询名称
		try {
			msg = cu.post(url, map).toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	

	@SuppressWarnings("unchecked")
	public String combiWaybillList(Map map) {
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=
			(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		String url="party/subcontractorcs/contractAndSubcontractorList";
		ClientUtil cu=new ClientUtil(url);
		StringBuffer sb=new StringBuffer();
		String msg="";
		String partytype=map.get("partytype").toString();
		try {
		List<Map> list = shipperRelaSubContractorDao.contractAndSubcontractList(map.get("partyid").toString(),map.get("partytype").toString(),
					map.get("frompartyid")==null?"":map.get("frompartyid").toString(),new HashMap());
		@SuppressWarnings("unused")
		int i=list.size()-1;
		int j=0;
		for(Map map1:list){//组合总包下面的分包商partyid带入Party查询分包商信息
			if("分包".equals(partytype)||"总分发".equals(partytype)||"linked".equals(partytype)){
				String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
				j++;
				if(j==list.size()&&!topartyid.equals("")){
					sb.append(map1.get("topartyid").toString());
				}else if(j<list.size()&&!topartyid.equals("")){
					sb.append(map1.get("topartyid").toString()+"-");
				}
			}
			if("发货方".equals(partytype)){
				String frompartyid=map1.get("frompartyid")==null?"":map1.get("frompartyid").toString();
				j++;
				if(j==list.size()&&!frompartyid.equals("")){
					sb.append(map1.get("frompartyid").toString());
				}else if(j<list.size()&&!frompartyid.equals("")){
					sb.append(map1.get("frompartyid").toString()+"-");
				}
			}
		}
		map.put("partystrs", sb.toString());//分包商或发货方id字符串集合
		map.put("skipCount", "0");//页数
		map.put("pageSize", "1000000");//每页 显示多少条
		//map.put("organization", "物流帝国");//查询名称
		//map.put("organization", map.get("organization").toString());//查询名称
		msg=cu.post(url, map).toString();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (WebServiceException e) {
		e.printStackTrace();
		}
		return msg;
	}
	public String combiWaybillListForFhf(Map map) {
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=
			(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		String url="party/subcontractorcs/contractAndSubcontractorList";
		ClientUtil cu=new ClientUtil(url);
		StringBuffer sb=new StringBuffer();
		String msg="";
		String partytype=map.get("partytype").toString();
		String frompartyid=map.get("frompartyid").toString();
		try {
		//取分包商
		List<Map> list = shipperRelaSubContractorDao.subcontractList(frompartyid);
		int i=list.size()-1;
		int j=0;
		for(Map map1:list){//组合总包下面的分包商partyid带入Party查询分包商信息
			if("分包".equals(partytype)||"总分发".equals(partytype)||"linked".equals(partytype)){
				String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
				j++;
				if(j==list.size()&&!topartyid.equals("")){
					sb.append(map1.get("topartyid").toString());
				}else if(j<list.size()&&!topartyid.equals("")){
					sb.append(map1.get("topartyid").toString()+"-");
				}
			}
		}
		map.put("partystrs", sb.toString());//分包商或发货方id字符串集合
		map.put("skipCount", "0");//页数
		map.put("pageSize", "100000");//每页 显示多少条
		//map.put("organization", "物流帝国");//查询名称
		//map.put("organization", map.get("organization").toString());//查询名称
		msg=cu.post(url, map).toString();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (WebServiceException e) {
		e.printStackTrace();
		}
		return msg;
	}
	public String selectWaybillById(Map formMap){
		  WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao"); //调用接口(实现类)
		  Waybill waybill=waybillDao.selectWaybillById(formMap); //删除和查询的参数用map
		  OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		  String frompartyname="";
		  String topartyname="";
		  if(StringUtils.isNotBlank(waybill.getFrompartyid())){
			  formMap.clear();
			  formMap.put("partyid",waybill.getFrompartyid());
			  String result=organizationService.selectOrganizationNameByPartyId(formMap);
			  if(StringUtils.isNotBlank(result)) {
				  List<Map<String, Object>> listOrg = Json2ObjectUtil.parseJSON2List(result);
				  for(Map org:listOrg){
					  frompartyname=org.get("name").toString();
				  }
			  }
		  }
		  if(StringUtils.isNotBlank(waybill.getTopartyid())){
			  formMap.clear();
			  formMap.put("partyid",waybill.getTopartyid());
			  String result1=organizationService.selectOrganizationNameByPartyId(formMap);
			  if(StringUtils.isNotBlank(result1)) {
				  List<Map<String, Object>> listOrg1 = Json2ObjectUtil.parseJSON2List(result1);
				  for(Map org:listOrg1){
					  topartyname=org.get("name").toString();
				  }
			  }
		  }
		  waybill.setFrompartyname(frompartyname);
		  waybill.setTopartyname(topartyname);
		  String msgJson="";
		  if (waybill!=null) {
			    msgJson=JsonGenerateUtil.bean2json(waybill);
		  }
		  return msgJson;
	}
	
	@SuppressWarnings("unchecked")
	public String selectWaybillList(Map map){
		Map newMap = new HashMap();
		newMap.put("partyid",map.get("partyid"));
		String frompartyname="",topartyname="";
		if(map.get("frompartyname") != null && !map.get("frompartyname").equals("")){
			frompartyname = map.get("frompartyname").toString();
		}
		if(map.get("topartyname") != null && !map.get("topartyname").equals("")){
			topartyname = map.get("topartyname").toString();
		}
		//取该总包所有发货方
		newMap.put("partytype", "发货方");
		String consignorStr = this.combiWaybillList(newMap);
		Map consignorMap = Json2ObjectUtil.parseJSON2Map(consignorStr);
		List consignorList = (List) consignorMap.get("Data");
		//取该总包所有分包商
		newMap.put("partytype", "分包");
		String subcontractorStr = this.combiWaybillList(newMap);
		Map subcontractorMap = Json2ObjectUtil.parseJSON2Map(subcontractorStr);
		List subcontractorList = (List) subcontractorMap.get("Data");
		//取名字匹配的发货方idList
		Map mapTemp = null;
		String partyId = "";
		List fromIdList = new ArrayList<String>();
		if(StringUtils.isNotBlank(frompartyname)){
			for (Object object : consignorList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				String organizationName = (String)mapTemp.get("organization");
				if(organizationName.contains(frompartyname)){
					fromIdList.add(partyId);
				}
			}
			if(fromIdList.size()<1){
				fromIdList.add("999999999");
			}
		}
		map.put("fromIdList", fromIdList);
		//取名字匹配的分包商idList
		mapTemp = null;
		partyId = "";
		List toIdList = new ArrayList<String>();
		if(StringUtils.isNotBlank(topartyname)){
			for (Object object : subcontractorList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				String organizationName = (String)mapTemp.get("organization");
				if(organizationName.contains(topartyname)){
					toIdList.add(partyId);
				}
			}
			if(toIdList.size()<1){
				toIdList.add("999999999");
			}
		}
		map.put("toIdList", toIdList);
		
		//时间加默认值
		String fromdate="",todate="";
		if(map.get("fromdate")!=null){
			fromdate = map.get("fromdate").toString();
		}
		if(map.get("todate")!=null){
			todate = map.get("todate").toString();
		}
		if(StringUtils.isNotBlank(fromdate)){
			map.put("fromdate", fromdate+" 00:00:00");
		}
		if(StringUtils.isNotBlank(todate)){
			map.put("todate", todate+" 23:59:59");
		}
		//查数据
		WaybillDao waybillDao = (WaybillDao)  SofaSpringContext.getBean("waybillDao");//调用接口(实现类)
		List list = waybillDao.selectList(map);
		int count=0;
		if(list != null && list.size() > 0){
			count = Integer.parseInt(waybillDao.selectCount(map));
		}
		//取发货方名称
		List list1 = new ArrayList();
		Waybill dom = null;
		for (Object obj:list) {
			dom  = (Waybill) obj;
			for (Object object : consignorList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				if(partyId.equals(dom.getFrompartyid())){
					dom.setFrompartyname((String)mapTemp.get("organization"));
				}
			}
			list1.add(dom);
		}
		//取分包商名称
		List list2 = new ArrayList();
		mapTemp = null;
		dom = null;
		partyId = "";
		for (Object obj:list) {
			dom  = (Waybill) obj;
			for (Object object : subcontractorList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				if(partyId.equals(dom.getTopartyid())){
					dom.setTopartyname((String)mapTemp.get("organization"));
				}				
			}
			list2.add(dom);
		}
		String msgJson = JsonGenerateUtil.getPageListJson(list2, count+"");
		return msgJson;
	}
	@SuppressWarnings("unchecked")
	public String selectWaybillListForFhf(Map map){
		Map newMap = new HashMap();
		newMap.put("frompartyid",map.get("frompartyid"));
		//取发货方名称
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService"); 
		Map formMap = new HashMap();
		formMap.put("partyid",map.get("frompartyid"));
		String result=organizationService.selectOrganizationNameByPartyId(formMap);
		String frompartyname="";
		List<Map<String, Object>> listOrg = Json2ObjectUtil.parseJSON2List(result);
		for(Map org:listOrg){
			 frompartyname=org.get("name").toString();
		}		
		//取该总包所有分包商
		newMap.put("partytype", "分包");
		String subcontractorStr = this.combiWaybillListForFhf(newMap);
		Map subcontractorMap = Json2ObjectUtil.parseJSON2Map(subcontractorStr);
		List subcontractorList = (List) subcontractorMap.get("Data");
		
		//时间加默认值
		String fromdate="",todate="";
		if(map.get("fromdate")!=null){
			fromdate = map.get("fromdate").toString();
		}
		if(map.get("todate")!=null){
			todate = map.get("todate").toString();
		}
		if(StringUtils.isNotBlank(fromdate)){
			map.put("fromdate", fromdate+" 00:00:00");
		}
		if(StringUtils.isNotBlank(todate)){
			map.put("todate", todate+" 23:59:59");
		}
		//查数据
		WaybillDao waybillDao = (WaybillDao)  SofaSpringContext.getBean("waybillDao");//调用接口(实现类)
		List list = waybillDao.selectList(map);
		Map goodgMap = waybillDao.selectGoodsCount(map);
		int count=0;
		if(list != null && list.size() > 0){
			count = Integer.parseInt(waybillDao.selectCount(map));
		}
	
		//取分包商名称
		List list2 = new ArrayList();
		Waybill dom = null;
		Map mapTemp;
		String partyId = "";
		for (Object obj:list) {
			dom  = (Waybill) obj;
			for (Object object : subcontractorList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				if(partyId.equals(dom.getTopartyid())){
					dom.setTopartyname((String)mapTemp.get("organization"));
				}				
			}
			//发货方也赋值
			dom.setFrompartyname(frompartyname);
			list2.add(dom);
		}
		String msgJson = JsonGenerateUtil.getPageListJson(list2, count+"");
		//重新组合要返回的json,包含要返回的订单的货物的总数量、重量和体积
		msgJson = msgJson.substring(0, msgJson.lastIndexOf("}")).concat(",\"num\":"+goodgMap.get("num").toString()
				+",\"weight\":"+goodgMap.get("weight").toString()+",\"volume\":"+goodgMap.get("volume").toString()+"}");
		return msgJson;
	}
	/**
	 * 查询发货方关联的分包商名称
	 * @author haoyong
	 * @date 2013-11-25
	 * @param map
	 * @return
	 */
	public String queyConsignorInfo(Map map){
		String msgJson = "";
		//发货方关联的分包商
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao");
		List<CityDistance> list = waybillDao.selectSubContractorList(map);
		String count = "";
		List<CityDistance> datalist = new ArrayList<CityDistance>();
		List<CityDistance> datalist2 = new ArrayList<CityDistance>();
		StringBuffer sb = new StringBuffer();
		List<Map<String, Object>>  subMap = new ArrayList<Map<String,Object>>();
		CityDistance e = null;
		try {
			if(list.size() > 0){
				int j = 0;
				for (CityDistance cityDistance : list) {
					e = cityDistance ;
					String topartyid = e.getTopartyid() == null ? "": e.getTopartyid().toString();
					j++;
					if (j == list.size() && !topartyid.equals("")) {
						sb.append(topartyid);
					} else if (j < list.size() && !topartyid.equals("")) {
						sb.append(topartyid + "-");
					}
				}
				String msg = "";
				String url = "party/organizationcs/queryOrgName";
				ClientUtil client = new ClientUtil(url);
				map.put("partyidlist", sb.toString());
				msg = client.post(url, map).toString();
				subMap = Json2ObjectUtil.parseJSON2List(msg);
				for(int i=0;i<list.size();i++){
						e = list.get(i);
						int k =0;
						for(int y = 0;y<subMap.size();y++){
							String partyid = subMap.get(y).get("partyid").toString();
							if((e.getTopartyid()==null ? "":e.getTopartyid()).equals(partyid)){
								e.setOrganization(subMap.get(y).get("organization").toString());
								e.setDistance(subMap.get(y).get("description").toString());
								e.setFromaddress(e.getFromaddress());
								e.setToaddress(e.getToaddress());
							}
							if(partyid.equals(e.getTopartyid())){
								k++;
							}
						}
						if(k<=0){
							continue;
						}
						datalist.add(e);
				}	
			}
			count = datalist.size()+"";
			int pagesize = Integer.parseInt(map.get("pageSize").toString());
			int skipcount = Integer.parseInt(map.get("skipCount").toString());
			if(datalist.size() - skipcount < pagesize){
				pagesize = datalist.size() % pagesize;
			}
			for(int index=skipcount;index<skipcount+pagesize;index++){
				datalist2.add(datalist.get(index));
			}
			msgJson = JsonGenerateUtil.getPageListJson(datalist2, count);			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (WebServiceException e1) {
			e1.printStackTrace();
		}
		return msgJson;
	}


	/**
	 * 获取运单号
	 * 
	 * nixianjing
	 */
	@Override
	public String getWaybillNumberes(Map map) {
		ContractAttributeDao contractAttributeDao = (ContractAttributeDao) SofaSpringContext
															.getBean("contractAttributeDao"); // 调用接口(实现类)
		String waybillnumber = "";
		Map msgMap = new HashMap();
		msgMap.put("frompartyid", map.get("partyid").toString());
		msgMap.put("shipperorsubcontractor", "1");
		msgMap.put("topartyid", map.get("frompartyid").toString());
		msgMap.put("attributename", "客户号");
		List valulist = contractAttributeDao.selectContractAttributeList(msgMap);
		Map contractAttribute = (Map)valulist.get(0);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao"); //调用接口(实现类)
		Map msMap = new HashMap();
		msMap.put("waybillnumber", contractAttribute.get("attributeValue").toString());
		List list = waybillDao.selectWaybillNumberList(msMap);
		Waybill waybill = new Waybill();
		if(list.size()>0) {
			waybill = (Waybill)list.get(0);
			String waybNumber = waybill.getWaybillnumber();
			String number = waybNumber.substring((waybNumber.length()-6),waybNumber.length());
			String newNumber = PrimaryGenerater.getInstance().generaterNextNumber(number);
			waybillnumber = contractAttribute.get("attributeValue").toString()+newNumber;
		}else {
			waybillnumber = contractAttribute.get("attributeValue").toString()+"000001";
		}
		return waybillnumber;
	}


	/**
	 * nixianjing
	 * 
	 * 
	 * 根据发货方用户名补全
	 */
	@Override
	public String getCheckFrompartyid(Map map) {
		OrganizationService organizationService = (OrganizationService)SofaSpringContext
													.getBean("organizationService"); // 调用接口(实现类)
		Map msgMap = new HashMap();
		Map shippMap = new HashMap();
		String jsonName = null;
		String msg = null;
		Map msges = new HashMap();
		jsonName = organizationService.selecteOrganizationIdByName(map.get("organizationName").toString()+"-001555555555555553333333112253");
		List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
		dataList=Json2ObjectUtil.parseJSON2List(jsonName);
		if(dataList.size()>0) {
			msgMap = dataList.get(0);
			shippMap.put("frompartid", msgMap.get("partyid").toString());
			ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao)SofaSpringContext
															.getBean("shipperRelaSubContractorDao"); // 调用接口(实现类)
			msg = shipperRelaSubContractorDao.checkRealtionExsit(shippMap);
			if(msg == "sorry") {
				return JsonGenerateUtil.map2json(msgMap);
			}else {
				msges.put("organization", "sorry");
				msges.put("partyid", "sorry");
			}
		}else {
			msges.put("organization","NO");
			msges.put("partyid","NO");
		}
		return JsonGenerateUtil.map2json(msges);
	}



	@Override
	public String getCheckTopartyid(Map map) {
		OrganizationService organizationService = (OrganizationService)SofaSpringContext
									.getBean("organizationService"); // 调用接口(实现类)
		Map msgMap = new HashMap();
		Map shippMap = new HashMap();
		String jsonName = null;
		String msg = null;
		Map msges = new HashMap();
		jsonName = organizationService.selecteOrganizationIdByName(map.get("organizationName").toString()+"-001555555555555522233233112253");
		List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
		dataList=Json2ObjectUtil.parseJSON2List(jsonName);
		if(dataList.size()>0) {
			msgMap = dataList.get(0);
			shippMap.put("frompartid", map.get("frompartid").toString());
			shippMap.put("partyid", map.get("partyid").toString());
			shippMap.put("topartyid", msgMap.get("partyid").toString());
			ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao)SofaSpringContext
							.getBean("shipperRelaSubContractorDao"); // 调用接口(实现类)
			msg = shipperRelaSubContractorDao.checkRealtionExsit(shippMap);
			if(msg == "sorry") {
				return JsonGenerateUtil.map2json(msgMap);
			}else {
				msges.put("organization", "sorry");//不是发货方下的关联分包商
				msges.put("partyid", "sorry");
			}
			
		}else {
			msges.put("organization","NO");//不存在
			msges.put("partyid","NO");
		}
		return JsonGenerateUtil.map2json(msges);
	}



	/**
	 *  nixianjing
	 *  
	 *  获取调度单号
	 */
	public String getSystemdispatchnumber() {
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
										.getBean("waybillStowageDao"); // 调用接口(实现类)
		String systemdispatchnumber = "";//调度单号
		SimpleDateFormat deteTime = new SimpleDateFormat("yyMMdd");
		String dete = "DDH"+deteTime.format(new Date());
		Map map = new HashMap();
		map.put("systemdispatchnumber", dete);
		List list = waybillStowageDao.selectSystemdispatchnumberList(map);
		WaybillStowage waybillStowage = new WaybillStowage();
		if(list.size()>0) {
			waybillStowage = (WaybillStowage)list.get(0);
			String spatchnumber = waybillStowage.getSystemdispatchnumber();
			String number = spatchnumber.substring((spatchnumber.length()-6),spatchnumber.length());
			String newNumber = PrimaryGenerater.getInstance().generaterNextNumber(number);
			systemdispatchnumber = dete+newNumber;
		}else {
			systemdispatchnumber = dete+"000001";
		}
		return systemdispatchnumber;
	}



	/**
	 * xianjing.ni
	 */
	public List selectWaybillGoodsByStowageId(Map map) {
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao"); //调用接口(实现类)
		List list = waybillDao.selectWaybillGoodsByStowageId(map);
		if(list.size()>0) {
			for(int i = 0;i<list.size();i++) {
				Waybill waybill = (Waybill) list.get(i); 
				Map newMap = new HashMap();
				//取该总包所有发货方
				newMap.put("partytype", "发货方");
				newMap.put("partyid", waybill.getPartyid());
				newMap.put("frompartyid", waybill.getFrompartyid());
				newMap.put("organization", "");
				String consignorStr = this.FrompartyIdName(newMap);
				Map consignorMap = Json2ObjectUtil.parseJSON2Map(consignorStr);
				List consignorList = (List) consignorMap.get("Data");;
				Object object = consignorList.get(0);
				Map mapTemp = (Map) object;
				waybill.setFrompartyname(mapTemp.get("organization").toString());
				list.set(i, waybill);
			}
		}
		return list;
	}
	
	/**
	 * @author yaoyan.lin
	 * @date 2014-02-18
	 * @function 获取导出Excel表所需的全部信息
	 * @return List<Map>
	 */
	public String selectAllForExportExcel(Map map){
		String partyid = map.get("frompartyid").toString();
		WaybillDao waybillDao = (WaybillDao)SofaSpringContext.getBean("waybillDao");
		List<Waybill> list = waybillDao.selectExportList(map);
		return JsonGenerateUtil.list2json(list);
	}

}
