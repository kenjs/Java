package com.cy.swp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cy.swp.service.*;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.swp.bo.MarketingCargoAssist;
import com.cy.swp.bo.NoteSendRecord;
import com.cy.swp.bo.WebUserInfo;
import com.cy.swp.dao.MarketingCargoAssistDao;
import com.cy.swp.dao.WebUserInfoDao;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.common.util.ConvertOrderCargoInfoUtil;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.bo.OrderCargoInfo;

@Service("importOrderCargoService")
public class ImportOrderCargoServiceImpl implements ImportOrderCargoService {
	
	@Resource
	private OrderCargoInfoService OrderCargoInfoService;
	
	@Resource
	private DriverUserInfoService driverUserInfoService;
	
	@Resource
	private NoteSendRecoreService noteSendRecoreService;
	
	@Resource
	private MarketingNoteRecordService marketingNoteRecordService;

	@Resource
	private MarketingCompanyInfoService marketingCompanyInfoService;
	
	@Resource
	private WebUserInfoDao webUserInfoDao;
	
	@Resource
	private MarketingCargoAssistDao marketingCargoAssistDao;
	
	 //通过导入excel，批量新增保存入库   事务控制
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public List<OrderCargoInfo> batchAddOrderCargoInfo(List<OrderCargoInfoDomain> list,String id)throws Exception {
		List<OrderCargoInfo> listId = new ArrayList<OrderCargoInfo>();
		//导入货源获取企业客户信息
		if(!marketingCompanyInfoService.addMarketingCompanyInfo(list)) {
			System.out.println("插入企业客户资料错误!");
		}
		for (OrderCargoInfoDomain orderCargoInfoDomain : list) {
			OrderCargoInfo orderCargoInfo=ConvertOrderCargoInfoUtil.getOrderCargoInfoFromDomain(orderCargoInfoDomain);
			//添加货源信息
			orderCargoInfo.setCargoFlag(String.valueOf(Constants.CARGO_FLAG_PENDING_TRADE_KEY));//待交易
			orderCargoInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//未删除
			OrderCargoInfoService.addOrderCargoInfo(orderCargoInfo);//**
			listId.add(orderCargoInfo);
			
			//添加专员协助信息
			MarketingCargoAssist marketingCargoAssist=new MarketingCargoAssist();
			if(StringUtils.isNotEmpty(orderCargoInfo.getContactMobilephone())){//1.根据手机号去用户表查询（手机号是必填）
				WebUserInfo webUserInfo=webUserInfoDao.queryWebUserInfoByMobilephone(orderCargoInfo.getContactMobilephone());
				if(webUserInfo!=null){
					marketingCargoAssist.setRegCompanyId(webUserInfo.getCompanyId());
				}
			}
			//根据货物来源的value得到key
			String cargoFrom=this.getCargoFromKey(orderCargoInfoDomain.getCargoInfoFrom());
			marketingCargoAssist.setCargoInfoFrom(cargoFrom);//货源信息来源:1 一点通 2 物流之家
			marketingCargoAssist.setCargoId(String.valueOf(orderCargoInfo.getId()));
			marketingCargoAssist.setMarketUserId(id);
			marketingCargoAssist.setIsMatchSuccess(Constants.NOT_BRIDGING_KEY);
			marketingCargoAssist.setHasTransaction(Constants.TRANSACTION_FAIL_KEY);//“0”未达成交易
			marketingCargoAssistDao.addCargoAssist(marketingCargoAssist);
		}
		return listId;
	}

    public void sendNoteBetweenCompanyAndDriver(List<OrderCargoInfo> list) throws Exception {
    	/**
    	 * 查询出当天已经发送3条短信司机手机号码
    	 * 
    	 * 循环动态sql语句后面配车的时候不要这些司机
    	 */
		List<NoteSendRecord> noteList = noteSendRecoreService.queryNoteSendRecoreList();
		StringBuilder noteString = new StringBuilder();
		for(int i = 0;i<noteList.size();i++) {
			noteString.append(" and a.code <> '"+noteList.get(i).getTelephone()+"'");
		}
		/**
		 * 对同一家物流公司的货物进行处理
		 * 
		 * 起始地相同的只要一条
		 */
		list = filterList(list);
		/**
		 * 循环货源匹配司机
		 */
		for (OrderCargoInfo orderCargoInfo : list) {
			//如果货源信息中，联系手机 为空，这条货源不会匹配司机
			if(StringUtils.isBlank(orderCargoInfo.getContactMobilephone())) {
				continue;
			}
			//发送给司机短信内容（开始）
			StringBuilder driverContent = new StringBuilder();
			String companyName = orderCargoInfo.getCompanyName() + "公司";
			driverContent.append("【快到网】");
			if(companyName.length() > 8) {
				companyName = companyName.substring(0,6) + "公司";
			}
			if(companyName.contains("公司公司")) {
				companyName = companyName.replace("公司公司", "公司");
			}
			driverContent.append(companyName);
			driverContent.append(orderCargoInfo.getStartCity().replaceAll("市","")).append("到").append(orderCargoInfo.getEndCity().replaceAll("市",""))
					     .append(orderCargoInfo.getCargoWeight()).append("吨，")
			             .append("符合您的运营线路,打开快到网查收,重新安装点击：")
			             .append("http://url.cn/N5evBk");
			//发送给司机短信内容（结束）
			
			/**
			 * 配车条件（开始）
			 */
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("startProvince", orderCargoInfo.getStartProvince());
			map.put("startCity", orderCargoInfo.getStartCity());
			map.put("endProvince", orderCargoInfo.getEndProvince());
			map.put("endCity", orderCargoInfo.getEndCity());
			map.put("requestStartTime", orderCargoInfo.getRequestStartTime());
			map.put("andNotCarNumber", "isNot");
			if(StringUtils.isNotEmpty(noteString.toString())) {
				map.put("andNotCode", noteString.toString());
			}else {
				map.put("andNotCode", "");
			}
			map.put("pageSize", "3");
			/**
			 * 配车条件（结束）
			 */
			/**
			 * 匹配出来的司机
			 */
			List<DriverUserInfoDomain> userList = driverUserInfoService.queryDriverMapList(map);
			/**
			 * 判断匹配司机
			 */
			if(userList != null && userList.size() > 0) {//匹配到司机给企业发送短信，一天发送一条短信
				/**
				 * 发给企业短信内容(开始)
				 */
				StringBuilder driverNumber = new StringBuilder();
				for(int i = 0;i<userList.size();i++) {
					driverNumber.append(userList.get(i).getCarNumber());
					if(i<(userList.size()-1)) {
						driverNumber.append("、");
					}
				}
				driverNumber.append("符合您的找车要求,")
				  			.append("详情请登录")
				  			.append("www.56top.cn").append("按车牌号查看联系方式【快到网】");
				/**
				 * 发给企业短信内容（结束）
				 */
				/**
				 * 查询物流企业当天发送短信的次数
				 * 
				 * 给企业发送短信（开始）
				 * 
				 * 当天一个物流企业只发送一条短信
				 */
				Map<String,String> companyMap = new HashMap<String, String>();
				companyMap.put("type", "0");
				companyMap.put("telephone", orderCargoInfo.getContactMobilephone());
				companyMap.put("useFor","2");
				List<NoteSendRecord> companyList = noteSendRecoreService.selectNoteSendRecore(companyMap);
				if(companyList.size() == 0) {
					/**
					 *发送短信，向企业
					 * @param type 发送对象类别 0企业 1司机
					 * @param remark 备注
					 * @param telephone 发送手机
					 * @param content 发送内容
					 * @param useFor
					 * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信
					 */
					String returnStatus = marketingNoteRecordService.setNoteSendRecordInfo("0", "导入货源匹配车源发送短信给企业", orderCargoInfo.getContactMobilephone(), driverContent.toString(), "2");
				}
				/**
				 * 循环司机信息
				 * 给司机发送短信
				 * 当天一个司机可以接收3条短信
				 */
				for(int i = 0;i < userList.size();i ++) {
					/**
					 * 查询当天向司机发送短信次数
					 */
					Map<String,String> driverMap = new HashMap<String, String>();
					driverMap.put("type", "1");
					driverMap.put("telephone", userList.get(i).getCode());
					List<NoteSendRecord> driverList = noteSendRecoreService.selectNoteSendRecore(driverMap);
					if (driverList.size() == 0) {
						/**
						 *发送短信，向司机
						 * @param type 发送对象类别 0企业 1司机
						 * @param remark 备注
						 * @param telephone 发送手机
						 * @param content 发送内容
						 * @param useFor
						 * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信
						 */
						String returnStatus = marketingNoteRecordService.setNoteSendRecordInfo("1",orderCargoInfo.getContactMobilephone(),userList.get(i).getCode(),driverContent.toString(),"1");
						
					} else if(driverList.size() < 3) {
						boolean flag = true;
						for(int j = 0; j < driverList.size(); j++ ) {
							if(driverList.get(j).getRemark().equals(orderCargoInfo.getContactMobilephone())) {
								flag = false;
								break;
							}
						}
						if(flag) {
							/**
							 *发送短信，向司机
							 * @param type 发送对象类别 0企业 1司机
							 * @param remark 备注
							 * @param telephone 发送手机
							 * @param content 发送内容
							 * @param useFor
							 * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信
							 */
							String returnStatus = marketingNoteRecordService.setNoteSendRecordInfo("1",orderCargoInfo.getContactMobilephone(),userList.get(i).getCode(),driverContent.toString(),"1");
						}
					}
				}
			}else {//没有匹配到车辆给企业发送短信，三天发送一次
				StringBuffer driverNumber = new StringBuffer();
				driverNumber.append("【快到网】您的司机朋友推荐您登录www.56top.cn发货配车,现在就去试试吧!");
				Map<String,String> companyMap = new HashMap<String, String>();
				companyMap.put("type", "0");
				companyMap.put("telephone", orderCargoInfo.getContactMobilephone());
				companyMap.put("useFor","2");
				List<NoteSendRecord> companyList = noteSendRecoreService.selectNoteSendRecoreUseFor(companyMap);
				if(companyList.size() > 0) {
					if(Integer.parseInt(companyList.get(0).getCreateTime()) < 3) {
					}else {
						String returnStatus = marketingNoteRecordService.setNoteSendRecordInfo("0", "【快到网】您的司机朋友推荐您登录www.56top.cn发货配车,现在就去试试吧!", orderCargoInfo.getContactMobilephone(), driverNumber.toString(), "2");
					}
				}else {
					String returnStatus = marketingNoteRecordService.setNoteSendRecordInfo("0", "【快到网】您的司机朋友推荐您登录www.56top.cn发货配车,现在就去试试吧!", orderCargoInfo.getContactMobilephone(), driverNumber.toString(), "2");
				}
			}
		}
	}


	/**
	 * 去除货源集合中，同一家公司的起始地省市一样的货源
	 * @param list
	 * @return
	 */
	private List<OrderCargoInfo> filterList(List<OrderCargoInfo> list) {
		List<OrderCargoInfo> dataList = new ArrayList<OrderCargoInfo>();
		if(list == null || list.size() < 2) {
			return list;
		}
		OrderCargoInfo info = null;
		Iterator<OrderCargoInfo> it = list.iterator();
		while(it.hasNext()) {
			info = it.next();
			boolean flag = false;
			for (OrderCargoInfo orderCargoInfo : dataList) {
				if(orderCargoInfo.getCompanyId().equals(info.getCompanyId())) {
					if(orderCargoInfo.getStartProvince().equals(info.getStartProvince())
							&& orderCargoInfo.getEndProvince().equals(info.getEndProvince())) {
						if(orderCargoInfo.getEndCity().equals(info.getEndCity())
								&& orderCargoInfo.getStartCity().equals(info.getStartCity())) {
							flag = true;
							break;
						}
					}
				}
			}
			if(! flag) {
				dataList.add(info);
			}
		}

		return dataList;
	}
	
	private String getCargoFromKey(String cargoInfoFromVal){
		String cargoInfoFrom="";
		if(Constants.CARGO_FROM_LOGISTICS_QQ_VALUE.equals(cargoInfoFromVal)){
			cargoInfoFrom=Constants.CARGO_FROM_LOGISTICS_QQ_KEY;
		}else if(Constants.CARGO_FROM_LINAN_VALUE.equals(cargoInfoFromVal)){
			cargoInfoFrom=Constants.CARGO_FROM_LINAN_KEY;
		}else if(Constants.CARGO_FROM_TONG_NETWORK_VALUE.equals(cargoInfoFromVal)){
			cargoInfoFrom=Constants.CARGO_FROM_TONG_NETWORK_KEY;
		}else if(Constants.CARGO_FROM_WINBATCH_VALUE.equals(cargoInfoFromVal)){
			cargoInfoFrom=Constants.CARGO_FROM_WINBATCH_KEY;
		}
		return cargoInfoFrom;
	}

}
