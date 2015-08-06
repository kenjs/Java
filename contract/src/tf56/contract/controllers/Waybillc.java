package tf56.contract.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.domain.Goods;
import tf56.contract.domain.Waybill;
import tf56.contract.domain.WaybillStowage;
import tf56.contract.domain.WaybillStowageLog;
import tf56.contract.services.CityDistanceDao;
import tf56.contract.services.ConsigneeConsignorAddressDao;
import tf56.contract.services.ContractDictionaryDao;
import tf56.contract.services.GoodsDao;
import tf56.contract.services.GoodsTypeDao;
import tf56.contract.services.SettleBillService;
import tf56.contract.services.WaybillAmountDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillLogDao;
import tf56.contract.services.WaybillService;
import tf56.contract.services.WaybillStowageDao;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Waybillc extends RestController {
	private final Logger log = Logger.getLogger("Waybillc.java");

	// 增 必须把获取的map转为bean对数据库进行操作
	@POST
	public void insert() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		Waybill waybill = new Waybill(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		waybill = (Waybill) pftb.parseToBean(formMap, waybill);// 将map转为bean对象
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String msgJson = waybillDao.insert(waybill); // 调用Dao同名方法
		this.renderPage("list.html");
	}

	// 接单管理新增
	@GET
	public void waybillAdd() {

	}

	// 接单管理修改
	@GET
	public void waybillEdit() {

	}

	/**
	 * @author haoyong
	 * @throws IOException
	 * @date 2013-11-19
	 * @function 作废运单
	 */
	@POST
	public String delete() throws IOException {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		String currrentDate = GetFormatDate.getCurrentDate();
		String inputMan = sessionBean.getRealname();
		formMap.put("status", "已作废");
		formMap.put("trace", "运单已作废");
		formMap.put("inputDate", currrentDate);
		formMap.put("inputMan", inputMan);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao");
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext
				.getBean("waybillLogDao"); // 调用接口(实现类)
		waybillLogDao.insert(formMap); // 保存日志操作记录
		String msgJson = waybillDao.delete(formMap); // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}

	// 改
	@POST
	public void update() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		Waybill waybill = new Waybill(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		waybill = (Waybill) pftb.parseToBean(formMap, waybill);// 将map转为bean对象
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String msgJson = waybillDao.update(waybill);
		this.renderPage("list.html");
	}

	/**
	 * @author yao.xia
	 * @throws IOException
	 * @date 2013-11-27
	 * @function 查一条 运单详情
	 */
	@POST
	public String selectById() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService"); // 调用接口(实现类)
		String msgJson = waybillService.selectWaybillById(formMap); // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回 Json格式:
	}

	// 查多条
	@POST
	public String selectList() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String msgJson = ""; // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给
	}

	/**
	 * 发货方 运单我得运单接口
	 * 
	 * @author hcm
	 * @date 2013-11-18
	 * @function 查询待分派运单列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@POST
	public String waybillListForFhf() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String msgJson = waybillService.selectWaybillListForFhf(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给
		// Json格式:
		// 自定义
	}
	
	/**
	 * yao.xia 运单修改
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@POST
	public String updateWaybill(){
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		Map logMap = new HashMap();
		Map amountMap = new HashMap();
		params = SysUtil.removeFilter(params);
		amountMap.put("waybillid", params.get("waybillid"));
		amountMap.put("topartyid", params.get("topartyid"));
		amountMap.put("partyid", params.get("partyid"));
		Waybill waybill = new Waybill();// 运单实体类
		ParseFormToBean pftb = new ParseFormToBean();
		waybill = (Waybill) pftb.parseToBean(params, waybill);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao"); // 调用接口(实现类)
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); // 调用接口(实现类)
		String currrentDate = GetFormatDate.getCurrentDate();
		String inputMan = sessionBean.getRealname();
		logMap.put("inputDate", currrentDate);
		logMap.put("inputMan", inputMan);
		String msg = null;
		String waybillid = null;
		msg = waybillDao.updateWaybill(waybill);// （修改）保存运单
		logMap.put("status", waybill.getStatus());
		logMap.put("trace", "运单已修改");
		logMap.put("waybillid", params.get("waybillid").toString());
		waybillLogDao.insert(logMap);// 保存日志操作记录
		//修改waybillamount topartyid
		int i = -1;
		if(!"".equals(amountMap.get("topartyid"))){
			WaybillAmountDao waybillAmountDao = (WaybillAmountDao) SofaSpringContext.getBean("waybillAmountDao"); // 调用接口(实现类)
			i = waybillAmountDao.updateTopartyid(amountMap);
		}
		
		String data = params.get("jsonText").toString();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList = Json2ObjectUtil.parseJSON2List(data);
		Goods goods = new Goods();// 物品实体类
		GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); // 调用接口(实现类)
		List list = new ArrayList();
		Map mapRe = null;
		String msges = "ok";
		for (Map map : dataList) {
			if (map.get("goodsid").equals("")) {
				ParseFormToBean pftbg = new ParseFormToBean();
				goods = (Goods) pftbg.parseToBean(map, goods);
				msges = goodsDao.insert(goods);
				mapRe = Json2ObjectUtil.parseJSON2Map(msges);
				list.add(mapRe);
			}
			if (!map.get("goodsid").equals("")) {
				ParseFormToBean pftbg = new ParseFormToBean();
				goods = (Goods) pftbg.parseToBean(map, goods);
				msges = goodsDao.update(goods);
			}
		}
		String json = null;
		Map maps = new HashMap();
		maps = Json2ObjectUtil.parseJSON2Map(msg);
		maps.put("goodsList", list);
		maps.put("msg", "update");
		maps.put("listSize", dataList.size());
		json = JsonGenerateUtil.map2json(maps).toString();
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/**
	 * nixianjing 接单新增保存
	 * 
	 * @return
	 */
	@POST
	public String insertWaybill() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		Map logMap = new HashMap();
		params = SysUtil.removeFilter(params);
		SimpleDateFormat deteTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		if (params.get("waybillid").toString() == null
				|| "".equals(params.get("waybillid").toString())) {
			WaybillService waybillService = (WaybillService) SofaSpringContext
					.getBean("waybillService");
			Map msgMap = new HashMap();
			msgMap.put("partyid", sessionBean.getPartyid());
			msgMap.put("frompartyid", params.get("frompartyid").toString());
			String waybillNumber = waybillService.getWaybillNumberes(msgMap);// 获取运单号
			params.put("partyid", sessionBean.getPartyid());// 当前总包会员id
			params.put("inputman", sessionBean.getRealname());
			params.put("operatorid", sessionBean.getOperatorid());
			params.put("waybillnumber", waybillNumber.toString());// 运单号
			params.put("inputdate", deteTime.format(new Date()));// 录入时间
			params.put("billstatus", "未计费");
			params.put("consigndate", params.get("consigndate").toString()
					+ " " + time.format(new Date()));
			if (params.get("topartyid").toString() == "") {
				// params.put("topartyid", "");
				params.put("status", "待分派");// 运单状态
				logMap.put("status", "待分派");
			} else {
				// params.put("topartyid", "");
				params.put("status", "待配载");// 运单状态
				logMap.put("status", "待配载");
			}
		} else {
			params.put("consigndate", params.get("consigndate").toString()
					+ " " + time.format(new Date()));
			if (params.get("topartyid").toString() == "") {
				// params.put("topartyid", "");
				params.put("status", "待分派");// 运单状态
				logMap.put("status", "待分派");
			} else {
				// params.put("topartyid", "");
				params.put("status", "待配载");// 运单状态
				logMap.put("status", "待配载");
			}
		}

		Waybill waybill = new Waybill();// 运单实体类
		ParseFormToBean pftb = new ParseFormToBean();
		waybill = (Waybill) pftb.parseToBean(params, waybill);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext
				.getBean("waybillLogDao"); // 调用接口(实现类)
		String currrentDate = GetFormatDate.getCurrentDate();
		String inputMan = sessionBean.getRealname();
		logMap.put("inputDate", currrentDate);
		logMap.put("inputMan", inputMan);
		String msg = null;
		String waybillid = null;
		if (params.get("waybillid").toString() == null
				|| "".equals(params.get("waybillid").toString())) {// 判断新增或是修改
			msg = waybillDao.insert(waybill);// (新增)保存运单
		} else {
			msg = waybillDao.updateWaybill(waybill);// （修改）保存运单
			logMap.put("trace", "运单已修改");
			logMap.put("waybillid", params.get("waybillid").toString());
			waybillLogDao.insert(logMap);// 保存日志操作记录
		}
		if (params.get("waybillid").toString() == null
				|| "".equals(params.get("waybillid").toString())) {
			Map countmap = Json2ObjectUtil.parseJSON2Map(msg);// json数据转Map
			waybillid = countmap.get("id").toString();// 得到接单主键id
			logMap.put("waybillid", waybillid);
			logMap.put("trace", "运单已受理");
			waybillLogDao.insert(logMap);// 保存日志操作记录
		}
		String data = params.get("jsonText").toString();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList = Json2ObjectUtil.parseJSON2List(data);
		Goods goods = new Goods();// 物品实体类
		GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao"); // 调用接口(实现类)
		List list = new ArrayList();
		Map mapRe = null;
		String msges = "ok";
		for (Map map : dataList) {
			if (map.get("goodsid").equals("")) {
				ParseFormToBean pftbg = new ParseFormToBean();
				if (waybillid == null) {
				} else {
					map.put("waybillid", waybillid);// 运单id
				}
				goods = (Goods) pftbg.parseToBean(map, goods);
				msges = goodsDao.insert(goods);
				mapRe = Json2ObjectUtil.parseJSON2Map(msges);
				list.add(mapRe);
			}
			if (!map.get("goodsid").equals("")) {
				ParseFormToBean pftbg = new ParseFormToBean();
				goods = (Goods) pftbg.parseToBean(map, goods);
				msges = goodsDao.update(goods);
			}
		}
		String json = null;
		Map maps = new HashMap();
		;
		if (params.get("waybillid").toString() == null
				|| "".equals(params.get("waybillid").toString())) {
			maps = Json2ObjectUtil.parseJSON2Map(msg);
			maps.put("goodsList", list);
			maps.put("waybillnumber", params.get("waybillnumber").toString());// 运单号
			maps.put("partyid", params.get("partyid").toString());// 总包id
			maps.put("inputman", params.get("inputman").toString());// 录单人
			maps.put("inputdate", params.get("inputdate").toString());// 录单时间
			maps.put("operatorid", params.get("operatorid").toString());// 录单时间
		} else {
			maps = Json2ObjectUtil.parseJSON2Map(msg);
			maps.put("goodsList", list);
			maps.put("msg", "update");
			maps.put("listSize", dataList.size());
		}
		json = JsonGenerateUtil.map2json(maps).toString();
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/**
	 * nixianjing
	 * 
	 * 根据物品id删除物品
	 * 
	 * @return
	 */
	@POST
	public String deteleGoods() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		GoodsDao goodsDao = (GoodsDao) SofaSpringContext.getBean("goodsDao");
		String msgJson = goodsDao.delete(formMap); // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}

	/**
	 * nixianjing
	 * 
	 * 工具类型查询数据字典
	 * 
	 * @return
	 */
	@POST
	public String selectListByType() {
		Map params = SysUtil.removeFilter(this.getParams());
		String type = params.get("type").toString();
		List list = null;
		String json = null;
		ContractDictionaryDao contractDictionaryDao = (ContractDictionaryDao) SofaSpringContext
				.getBean("contractDictionaryDao");
		list = contractDictionaryDao.selectListByType(type);
		json = JsonGenerateUtil.getListJson(list);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/**
	 * nixianjing
	 * 
	 * 
	 * 查询发货方集合
	 * 
	 * @return
	 */
	@POST
	public String frompartyNameList() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("partyid", sessionBean.getPartyid());// 当前总包会员id
		params.put("frompartyid", "");
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String json = waybillService.FrompartyIdList(params).toString();
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/**
	 * nixianjing 查询分包商集合
	 * 
	 * @return
	 */
	@POST
	public String topartyNameList() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("partyid", sessionBean.getPartyid());// 当前总包会员id
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String json = waybillService.FrompartyIdList(params).toString();
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/***
	 * 
	 * nixianjing
	 * 
	 * 
	 * 根据fromPartyId,toPartyId,consigneeOrConsignor,
	 * checked查询发货地址和收货地址表ConsigneeConsignorAddress
	 * 
	 * @return
	 */
	@POST
	public String selectfrompartyParty() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("frompartyid", sessionBean.getPartyid());
		params.put("skipCount", "0");
		params.put("pageSize", "1");
		ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext
				.getBean("consigneeConsignorAddressDao"); // 调用接口(实现类)
		List list = consigneeConsignorAddressDao.selectList(params);
		String json = JsonGenerateUtil.getListJson(list);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/**
	 * nixianjing
	 * 
	 * 根据fromPartyId,toPartyId,consigneeOrConsignor查询所有联系人或收货方详细地址
	 * 
	 * @return
	 */
	@POST
	public String selectLinkname() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("frompartyid", sessionBean.getPartyid());
		params.put("skipCount", "0");
		params.put("pageSize", "10000000000");
		ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext
				.getBean("consigneeConsignorAddressDao"); // 调用接口(实现类)
		String json = null;
		List list = consigneeConsignorAddressDao.selectList(params);
		if (list.size() > 0) {
			json = JsonGenerateUtil.getListJson(list);
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/***
	 * 
	 * nixianjing
	 * 
	 * 根据ConsigneeConsignorAddressId查询联系人或收货方对象
	 * 
	 * @return
	 */
	@POST
	public String selectConsigneeConsignorAddressId() {
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		ConsigneeConsignorAddressDao consigneeConsignorAddressDao = (ConsigneeConsignorAddressDao) SofaSpringContext
				.getBean("consigneeConsignorAddressDao"); // 调用接口(实现类)
		String json = consigneeConsignorAddressDao.selectById(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/***
	 * nixianjing
	 * 
	 * 根据会员frompartyid、发货地fromaddress、目的地toaddress查询城区间距离
	 * 
	 * @return
	 */
	@POST
	public String selectCityDistanceId() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("partyid", sessionBean.getPartyid());// 当前总包会员id
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext
				.getBean("cityDistanceDao"); // 调用接口(实现类)
		List list = cityDistanceDao.selectByFromAddressAndToAddressList(params);
		String json = null;
		if (list.size() > 0) {
			json = JsonGenerateUtil.getListJson(list);
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/****
	 * nixianjin 根据fromPartyId,toPartyId,checked查询发货方货物GoodsType
	 * 
	 * @return
	 */
	@POST
	public String goodsnameList() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("frompartyid", sessionBean.getPartyid());
		params.put("skipCount", "0");
		params.put("pageSize", "10000000000");
		GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext
				.getBean("goodsTypeDao"); // 调用接口(实现类)
		List list = goodsTypeDao.selectList(params);
		String json = JsonGenerateUtil.getListJson(list);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/****
	 * nixianjing
	 * 
	 * 根据货物id查询货物GoodsType
	 * 
	 * @return
	 */
	@POST
	public String selectGoodId() {
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		GoodsTypeDao goodsTypeDao = (GoodsTypeDao) SofaSpringContext
				.getBean("goodsTypeDao"); // 调用接口(实现类)
		String json = goodsTypeDao.selectById(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, json);
	}

	/**
	 * xiayao 运单管理查询
	 * 
	 * @return
	 */
	@POST
	public String waybillcManager() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		if (formMap.get("status").toString().equals("全部运单")) {
			formMap.put("status", "");
			formMap.put("menuindex", "1");
		}
		String partyid = sessionBean.getPartyid();
		formMap.put("partyid", partyid);
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String msgJson = waybillService.selectWaybillList(formMap);

		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给//
		// Json格式://
		// 自定义
	}

	/**
	 * xiayao 运单修改查询
	 * 
	 * @return
	 */
	@POST
	public String waybillUpdateList() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		if (formMap.get("status").toString().equals("全部运单")) {
			formMap.put("status", "");
		}
		String partyid = sessionBean.getPartyid();
		formMap.put("partyid", partyid);
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String msgJson = waybillService.selectWaybillList(formMap);

		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给//
		// Json格式://
		// 自定义
	}

	/**
	 * xiayao 运单总数目查询
	 * 
	 * @return
	 */
	@POST
	public String selectCount() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		if (formMap.get("status").toString().equals("全部运单")) {
			formMap.put("status", "");
			formMap.put("menuindex", "1");
		}
		String partyid = sessionBean.getPartyid();
		formMap.put("partyid", partyid);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String msgJson = waybillDao.selectCount(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给//
		// Json格式://
		// 自定义
	}

	/**
	 * xiayao 配载运单界面跳转
	 * 
	 * @return
	 */
	@GET
	public void waybillStowageAdd() {
	}

	/**
	 * xiayao 配载运单查询
	 * 
	 * @return
	 */
	@POST
	public String stowage() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		String partyid = sessionBean.getPartyid();
		formMap.put("partyid", partyid);
		//
		String[] waybillids = formMap.get("waybillid").toString().split("#");
		List waybillIdList = new ArrayList<String>();
		for (String waybillid : waybillids) {
			waybillIdList.add(waybillid);
		}
		formMap.put("pageSize", waybillids.length);// 每页行数=选中行数
		formMap.put("skipCount", "0");//
		formMap.put("waybillid", "");
		formMap.put("waybillIdList", waybillIdList);// 选中运单
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String msgJson = waybillService.selectWaybillList(formMap);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给//
		// Json格式://
		// 自定义
	}

	/**
	 * xiayao 装车确认界面跳转
	 * 
	 * @return
	 */
	@GET
	public void waybillLoadSure() {
	}

	/**
	 * xiayao 装车确认
	 * 
	 * @return
	 */
	@POST
	public String load() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		Map waybillStowageLogMap = new HashMap();
		//
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String sureman = sessionBean.getRealname();
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao");
		String currrentDate = GetFormatDate.getCurrentDate();
		formMap.put("sureman", sureman);
		String msgJson = waybillDao.load(formMap);
		// 判断同一调度单的运单状态是否一致
		Map parMap = new HashMap();
		List list = new ArrayList();
		list.add("已配载");
		parMap.put("waybillid", formMap.get("waybillid").toString());
		parMap.put("statuslist", list);
		Map checkMap = waybillDao.checkWaybillStatus(parMap);
		String waybillstowageid = "";
		if ("0".equals(checkMap.get("waybillnum").toString())) {
			parMap.put("status", "已确认");
			parMap.put("inputman", sureman);
			parMap.put("inputdate", currrentDate);
			parMap.put("carloaddate", currrentDate);
			waybillStowageDao.updateWaybillStowage(parMap);
			// 记录调度单操作日志
			WaybillStowage ws = waybillStowageDao
					.selectWaybillStowageBywaybillid(parMap);
			waybillStowageLogMap.put("waybillstowageid", ws
					.getWaybillstowageid());
			waybillStowageLogMap.put("systemdispatchnumber", ws
					.getSystemdispatchnumber());
			waybillStowageLogMap.put("status", "已确认");
			waybillStowageLogMap.put("trace", "");
			waybillStowageLogMap.put("inputdate", currrentDate);
			waybillStowageLogMap.put("inputman", sureman);
			WaybillStowageLog bean = new WaybillStowageLog();
			ParseFormToBean pft = new ParseFormToBean();
			bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap,
					bean);
			waybillStowageDao.insertWaybillStowageLog(bean);
		}
		// 添加日志操作记录
		formMap.put("status", "已装车");
		formMap.put("trace", "运单已装车");
		formMap.put("inputDate", currrentDate);
		formMap.put("inputMan", sureman);
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext
				.getBean("waybillLogDao"); // 调用接口(实现类)
		waybillLogDao.insert(formMap); // 保存日志操作记录

		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回 Json格式:
	}

	/**
	 * xiayao 发车确认界面跳转
	 * 
	 * @return
	 */
	@GET
	public void waybillStartSure() {
	}

	/**
	 * xiayao 发车确认
	 * 
	 * @return
	 */
	@POST
	public String start() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		String currrentDate = GetFormatDate.getCurrentDate();
		String inputMan = sessionBean.getRealname();
		formMap = SysUtil.removeFilter(formMap);
		Map waybillStowageLogMap = new HashMap();
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao");
		String msgJson = waybillDao.start(formMap); // 删除和查询的参数用map
		// 判断同一调度单的运单状态是否一致
		Map parMap = new HashMap();
		List list = new ArrayList();
		list.add("已装车");
		list.add("已配载");
		parMap.put("waybillid", formMap.get("waybillid").toString());
		parMap.put("statuslist", list);
		Map checkMap = waybillDao.checkWaybillStatus(parMap);
		String waybillstowageid = "";
		if ("0".equals(checkMap.get("waybillnum").toString())) {
			parMap.put("status", "已发车");
			parMap.put("inputman", inputMan);
			parMap.put("inputdate", currrentDate);
			parMap.put("carbegindate", formMap.get("carbegindate").toString());
			waybillStowageDao.updateWaybillStowage(parMap);
			// 记录调度单操作日志
			WaybillStowage ws = waybillStowageDao
					.selectWaybillStowageBywaybillid(parMap);
			waybillStowageLogMap.put("waybillstowageid", ws
					.getWaybillstowageid());
			waybillStowageLogMap.put("systemdispatchnumber", ws
					.getSystemdispatchnumber());
			waybillStowageLogMap.put("status", "已发车");
			waybillStowageLogMap.put("trace", "");
			waybillStowageLogMap.put("inputdate", currrentDate);
			waybillStowageLogMap.put("inputman", inputMan);
			WaybillStowageLog bean = new WaybillStowageLog();
			ParseFormToBean pft = new ParseFormToBean();
			bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap,
					bean);
			waybillStowageDao.insertWaybillStowageLog(bean);
		}
		// 添加日志操作记录
		formMap.put("status", "已发车");
		formMap.put("trace", "运单已发车");
		formMap.put("inputDate", currrentDate);
		formMap.put("inputMan", inputMan);
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext
				.getBean("waybillLogDao"); // 调用接口(实现类)
		waybillLogDao.insert(formMap); // 保存日志操作记录

		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回 Json格式:
	}

	/**
	 * xiayao 到车确认界面跳转
	 * 
	 * @return
	 */
	@GET
	public void waybillArriveSure() {
	}

	/**
	 * xiayao 到车确认
	 * 
	 * @return
	 */
	@POST
	public String arrive() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map formMap = this.getParams();
		String currrentDate = GetFormatDate.getCurrentDate();
		String inputMan = sessionBean.getRealname();
		formMap = SysUtil.removeFilter(formMap);
		Map waybillStowageLogMap = new HashMap();
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext
				.getBean("waybillStowageDao");
		String msgJson = waybillDao.arrive(formMap); // 删除和查询的参数用map
		// 判断同一调度单的运单状态是否一致
		Map parMap = new HashMap();
		List list = new ArrayList();
		list.add("已装车");
		list.add("已配载");
		list.add("已发车");
		parMap.put("waybillid", formMap.get("waybillid").toString());
		parMap.put("statuslist", list);
		Map checkMap = waybillDao.checkWaybillStatus(parMap);
		String waybillstowageid = "";
		if ("0".equals(checkMap.get("waybillnum").toString())) {
			parMap.put("status", "已到车");
			parMap.put("inputman", inputMan);
			parMap.put("inputdate", currrentDate);
			parMap.put("carenddate", formMap.get("carenddate").toString());
			waybillStowageDao.updateWaybillStowage(parMap);
			// 记录调度单操作日志
			WaybillStowage ws = waybillStowageDao
					.selectWaybillStowageBywaybillid(parMap);
			waybillStowageLogMap.put("waybillstowageid", ws
					.getWaybillstowageid());
			waybillStowageLogMap.put("systemdispatchnumber", ws
					.getSystemdispatchnumber());
			waybillStowageLogMap.put("status", "已到车");
			waybillStowageLogMap.put("trace", "");
			waybillStowageLogMap.put("inputdate", currrentDate);
			waybillStowageLogMap.put("inputman", inputMan);
			WaybillStowageLog bean = new WaybillStowageLog();
			ParseFormToBean pft = new ParseFormToBean();
			bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap,
					bean);
			waybillStowageDao.insertWaybillStowageLog(bean);
		}
		// 添加日志操作记录
		formMap.put("status", "已到车");
		formMap.put("trace", "运单已到车");
		formMap.put("inputDate", currrentDate);
		formMap.put("inputMan", inputMan);
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext
				.getBean("waybillLogDao"); // 调用接口(实现类)
		waybillLogDao.insert(formMap); // 保存日志操作记录

		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回 Json格式:
	}

	/**
	 * xiayao 运单签收界面跳转
	 * 
	 * @return
	 */
	@GET
	public void waybillSignAdd() {
	}

	/**
	 * 运单详情页面
	 * 
	 * @author haoyong
	 * @date 2013-11-26
	 */
	@GET
	public void waybill_detail() {

	}

	/**
	 * nixianjing
	 * 
	 * 校验发货方是否存在自动补全
	 * 
	 * @return
	 */
	@POST
	public String checkFrompartyName() {
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String jsonmsg = waybillService.getCheckFrompartyid(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, jsonmsg); // 返回 Json格式:
	}

	/**
	 * nixianjing
	 * 
	 * 校验分包商是否存在自动补全
	 * 
	 * @return
	 */
	@POST
	public String checkTopartyName() {
		HttpServletRequest request = this.getResponseHelper().getRequest();
		SessionBean sessionBean = SessionUtil.getSession(request);
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		params.put("partyid", sessionBean.getPartyid());
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String jsonmsg = waybillService.getCheckTopartyid(params);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, jsonmsg); // 返回 Json格式:
	}

	/**
	 * nixianjing
	 * 
	 * 首页根据运单号查询订单
	 * 
	 * @return
	 */
	@POST
	public String selectWaybillNumber() {
		Map params = this.getParams();
		params = SysUtil.removeFilter(params);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String json = waybillDao.selectWaybillNumber(params
				.get("waybillnumber").toString());
		Map msgMap = new HashMap();
		msgMap.put("id", json);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil
				.map2json(msgMap)); // 返回 Json格式:
	}

	/**
	 * @author yao.xia
	 * @return 统计昨日发货数(票)
	 * @since 2014.1.08
	 */
	@POST
	public String selectYestodayCount() {
		Map paramsMap = this.getParams();
		paramsMap = SysUtil.removeFilter(paramsMap);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String msgJson = waybillDao.selectYestodayCount(paramsMap); // 调用本外包services.impl的实现类
		// 返回json,格式: // 自定义
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}

	/**
	 * @author yao.xia
	 * @return 统计本月发货数(票)
	 * @since 2014.1.08
	 */
	@POST
	public String selectMouthCount() {
		Map paramsMap = this.getParams();
		paramsMap = SysUtil.removeFilter(paramsMap);
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext
				.getBean("waybillDao"); // 调用接口(实现类)
		String msgJson = waybillDao.selectMouthCount(paramsMap); // 调用本外包services.impl的实现类
		// 返回json,格式: // 自定义
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}

	/**
	 * 查询我的运单导出数据
	 * 
	 * @author yaoyan.lin
	 * @date 2014-02-18
	 */
	@POST
	public String exportExcel() {
		HttpServletResponse response = this.getResponseHelper().getResponse();
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		String waybillnumber = "", consigneelinkman = "", consigneemobilenumber = "", clientnumber = "", status = "";
		try {
			waybillnumber = URLDecoder.decode((formMap.get("waybillnumber")
					.toString()), "UTF-8");
			consigneelinkman = URLDecoder.decode((formMap
					.get("consigneelinkman").toString()), "UTF-8");
			consigneemobilenumber = URLDecoder.decode((formMap
					.get("consigneemobilenumber").toString()), "UTF-8");
			clientnumber = URLDecoder.decode((formMap.get("clientnumber")
					.toString()), "UTF-8");
			status = URLDecoder.decode((formMap.get("status").toString()),
					"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formMap.put("waybillnumber", waybillnumber);
		formMap.put("consigneelinkman", consigneelinkman);
		formMap.put("consigneemobilenumber", consigneemobilenumber);
		formMap.put("clientnumber", clientnumber);
		formMap.put("status", status);
		WaybillService waybillService = (WaybillService) SofaSpringContext
				.getBean("waybillService");
		String msgJson = waybillService.selectAllForExportExcel(formMap);
		return new JsonResponse().responseJson(response, msgJson);

	}
}