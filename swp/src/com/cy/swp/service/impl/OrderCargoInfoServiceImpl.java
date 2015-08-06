package com.cy.swp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cy.swp.bo.DriverLineInfo;
import com.cy.swp.bo.DriverUserInfo;
import com.cy.swp.bo.MarketingDriverLine;
import com.cy.swp.domain.MarketingDriverBusinessLineDomain;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.service.MarketingDriverBusinessLineService;
import com.cy.swp.service.MarketingDriverInfoService;
import com.cy.swp.service.MarketingDriverLineService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.DriverUserInfoDao;
import com.cy.swp.dao.OrderCargoInfoDao;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.OrderCargoInfoService;
@Service("orderCargoInfoService")
public class OrderCargoInfoServiceImpl implements OrderCargoInfoService {

	@Resource
	private OrderCargoInfoDao orderCargoInfoDao;
	
	@Resource
	private DriverUserInfoDao driverUserInfoDao;

	@Resource
	private MarketingDriverInfoService driverInfoService;

	@Resource
	private MarketingDriverLineService marketingDriverLineService;

	@Resource
	private MarketingDriverBusinessLineService marketingDriverBusinessLineService;
	
	
	public Integer addOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		return orderCargoInfoDao.addOrderCargoInfo(orderCargoInfo);
	}

	public OrderCargoInfo queryOrderCargoInfoById(String id) {
		return orderCargoInfoDao.queryOrderCargoInfoById(id);
	}
	
	public List<OrderCargoInfoDomain> queryTodayImportInfo(String userId,OrderCargoInfoDomain orderCargoInfoDomain) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("mark", orderCargoInfoDomain.getMark());//标记：0查询今天导入，1查询历史导入
		queryMap.put("marketUserId", userId);//当前登陆者的Id
		queryMap.put("cargoOrigin", Constants.MARKETING_IMPORT_CARGO_KEY);//营销平台导入的货源
		queryMap.put("deletedFlag", Constants.DELETED_FLAG_FALSE);//有效的即未删除的
		queryMap.put("contactMobilephone", orderCargoInfoDomain.getContactMobilephone());
		return orderCargoInfoDao.queryTodayImportInfo(queryMap);
	}

	public List<OrderCargoInfoDomain> queryTodayImportCargoInfoByPhone(String userId,OrderCargoInfoDomain orderCargoInfoDomain) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>(); 
		queryMap.put("mark", orderCargoInfoDomain.getMark());//标记：0查询今天导入，1查询历史导入
		queryMap.put("marketUserId", userId);//当前登陆者的Id
		queryMap.put("cargoOrigin", Constants.MARKETING_IMPORT_CARGO_KEY);//营销平台导入的货源
		queryMap.put("deletedFlag", Constants.DELETED_FLAG_FALSE);//有效的即未删除的
		if(orderCargoInfoDomain!=null){
			queryMap.put("contactMobilephone", orderCargoInfoDomain.getContactMobilephone());//联系人电话
			//查询历史导入的未匹配成功的有效货源公司信息所需条件
			//queryMap.put("isMatchSuccess",Constants.BRIDGING_SUCCESS);//没有匹配成功
		}
		
		
		List<OrderCargoInfoDomain> list = new ArrayList<OrderCargoInfoDomain>();
		list = orderCargoInfoDao.queryTodayImportCargoInfoByPhone(queryMap);
		for(int i = 0;i < list.size();i++) {
			HashMap<String, Object> queryMap1 = new HashMap<String, Object>();
			queryMap1.put("startProvince", list.get(i).getStartProvince());
			queryMap1.put("startCity", list.get(i).getStartCity());
			queryMap1.put("endProvince", list.get(i).getEndProvince());
			queryMap1.put("endCity", list.get(i).getEndCity());
			queryMap1.put("requestStartTime", list.get(i).getRequestStartTime());
			queryMap1.put("andNotCarNumber", "");
			queryMap1.put("andNotCode", "");
			queryMap1.put("pageSize", "3");
			
            List<DriverUserInfoDomain> list2 = new ArrayList<DriverUserInfoDomain>();
            list2 = driverUserInfoDao.queryDriverMapPageSizeThree(queryMap1);
            if(list2.size()>0) {
            	list.get(i).setMatchDriverCount("有");
            }else {
            	list2 = driverUserInfoDao.queryDriverMapPageSizeFour(queryMap1);
            	if(list2.size()>0) {
                	list.get(i).setMatchDriverCount("有");
                }else {
                	list2 = driverUserInfoDao.queryDriverMapPageSizeTow(queryMap1);
                	if(list2.size()>0) {
                    	list.get(i).setMatchDriverCount("有");
                    }else {
                    	list2 = driverUserInfoDao.queryDriverMapPageSizeOne(queryMap1);
                    	if(list2.size()>0) {
                        	list.get(i).setMatchDriverCount("有");
                        }else {
                        	list.get(i).setMatchDriverCount("无");
                        }
                    }
                }
            } 
		}
		return list;
	}

	public Integer queryTodayImportCargoCount(String userId,String deletedFlag) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();   
		queryMap.put("marketUserId", userId);//当前登陆者的Id
		queryMap.put("cargoOrigin", Constants.MARKETING_IMPORT_CARGO_KEY);//营销平台导入的货源
		queryMap.put("deletedFlag", deletedFlag);//有效的即未删除的
		if(StringUtils.isNotEmpty(deletedFlag)) {
			queryMap.put("cargoResult", Constants.BRIDGING_SUCCESS);
			queryMap.put("isMatchSuccess", " is not null ");
		}
		return orderCargoInfoDao.queryTodayImportCargoCount(queryMap);
	}

	public boolean modifyDeletedFlagByPhone(String contactMobilephone,
			String userId) {
		HashMap<String, Object> modifyMap = new HashMap<String, Object>(); 
		modifyMap.put("contactMobilephone", contactMobilephone);
		modifyMap.put("modifyUserid", userId);
		modifyMap.put("deletedFlag", Constants.DELETED_FLAG_TRUE);
		modifyMap.put("cargoResultthree", Constants.CARGO_REPLYRESULT_HAD_THERR_COVERED_KEY);
		modifyMap.put("cargoResultisnull", " is null ");
		modifyMap.put("cargoResultone", Constants.CARGO_REPLYRESULT_EXIST_KEY);
		modifyMap.put("isMatchSuccess", Constants.NOT_BRIDGING_KEY);
		return orderCargoInfoDao.modifyDeletedFlagByPhone(modifyMap);
	}

	public boolean modifyOrderDeleteFlagById(String id, String userId) {
		HashMap<String, Object> modifyMap = new HashMap<String, Object>(); 
		modifyMap.put("id", id);
		modifyMap.put("modifyUserid", userId);
		modifyMap.put("deletedFlag", Constants.DELETED_FLAG_TRUE);
		return orderCargoInfoDao.modifyOrderDeleteFlagById(modifyMap);
	}

	@Override
	public String getOrderCargoInfoString(List<OrderCargoInfoDomain> list,String start) {
		StringBuffer cargoString = new StringBuffer();
		cargoString.append("<tr style=\"font-weight:bold;\">");
		cargoString.append("<td>装货时间</td>");
		cargoString.append("<td>装货地</td>");
		cargoString.append("<td>卸货地</td>");
		cargoString.append("<td>货物类型</td>");
		cargoString.append("<td>发布企业</td>");
		cargoString.append("<td>规则</td>");
		if("N".equals(start)) {
			cargoString.append("<td><a href=\"javascript:queryMarketingOrderCargoById('N');\">匹配货源>></a></td>");
		}else {
			cargoString.append("<td><a href=\"javascript:queryMarketingOrderCargoById('Y');\">匹配货源>></a></td>");
		}
		cargoString.append("</tr>");
		for(int i = 0;i < list.size();i++) {
			cargoString.append("<tr>");
			cargoString.append("<td>"+list.get(i).getRequestStartTime()+"</td>");
			cargoString.append("<td>");
			if(StringUtils.isNotEmpty(list.get(i).getStartProvince())) {
				cargoString.append(list.get(i).getStartProvince());
			}
			if(StringUtils.isNotEmpty(list.get(i).getStartCity())) {
				cargoString.append(list.get(i).getStartCity());
			}
			if(StringUtils.isNotEmpty(list.get(i).getStartCounty())) {
				cargoString.append(list.get(i).getStartCounty());
			}
			cargoString.append("</td>");
			cargoString.append("<td>");
			if(StringUtils.isNotEmpty(list.get(i).getEndProvince())) {
				cargoString.append(list.get(i).getEndProvince());
			}
			if(StringUtils.isNotEmpty(list.get(i).getEndCity())) {
				cargoString.append(list.get(i).getEndCity());
			}
			if(StringUtils.isNotEmpty(list.get(i).getEndCounty())) {
				cargoString.append(list.get(i).getEndCounty());
			}
			cargoString.append("</td>");
			cargoString.append("<td>");
			if("1".equals(list.get(i).getCargoType())) {
				cargoString.append("重货");
			}else if("2".equals(list.get(i).getCargoType())){
				cargoString.append("泡货");
			}
			cargoString.append("</td>");
			cargoString.append("<td>"+list.get(i).getCompanyName()+"</td>");
			cargoString.append("<td>");
			if("1".equals(list.get(i).getRemark())) {
				cargoString.append("预约线路");
			}else if("2".equals(list.get(i).getRemark())){
				cargoString.append("经营线路");
			}else if("3".equals(list.get(i).getRemark())){
				cargoString.append("当前位置");
			}else if("4".equals(list.get(i).getRemark())){
				cargoString.append("常跑线路");
			}
			cargoString.append("</td>");
			if("N".equals(start)) {
				cargoString.append("<td><a href=\"javascript:setCargoNoteInfo('"+list.get(i).getId()+"');\">发送货源</a></td>");
			}else {
				cargoString.append("<td><a href=\"javascript:setCargoPushInfo('"+list.get(i).getId()+"');\">推送货源</a></td>");
			}
			cargoString.append("</tr>");
		}

		return cargoString.toString();
	}

	@Override
	public List<OrderCargoInfoDomain> queryOrderCargoInfoDomainDriverInfoHsql(String id, String start) {
		//货源List
		List<OrderCargoInfoDomain> list = new ArrayList<OrderCargoInfoDomain>();
		//司机客户
		MarketingDriverInfoDomain marketingDriverInfoDomain = new MarketingDriverInfoDomain();
		//预约线路
		List<MarketingDriverBusinessLineDomain> businessLineDomainList = new ArrayList<MarketingDriverBusinessLineDomain>();
		//动态sql
		StringBuffer hsql = new StringBuffer();
		Map map = new HashMap<String,String>();
		if("N".equals(start)) {//未安装
			//获取司机客户信息（未安装）
			marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainNoById(Integer.parseInt(id));
			//获取预约线路List
			businessLineDomainList  = marketingDriverBusinessLineService.queryMarketingDriverBusinessLineByCustomerDriverIdList(marketingDriverInfoDomain.getId());
			//优先级1(预约线路 单向)
			hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
			hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'1' as REMARK ");
			hsql.append(" from t_order_cargo_info t where ");
			hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
			hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
			hsql.append("and ( ");
			for(int i = 0;i<businessLineDomainList.size();i++){
				if(i == 0) {
					hsql.append("(");
					hsql.append("t.start_province = '"+businessLineDomainList.get(i).getStartProvince()+"' ");
					hsql.append(" and t.start_city = '"+businessLineDomainList.get(i).getStartCity()+"' ");
					hsql.append(" and t.end_province = '"+businessLineDomainList.get(i).getEndProvince()+"' ");
					hsql.append(" and t.end_city = '"+businessLineDomainList.get(i).getEndCity()+"' ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') >= '"+businessLineDomainList.get(i).getStartTime()+"' ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') <= '"+businessLineDomainList.get(i).getEndTime()+"' ");
					hsql.append(") ");
				}else {
					hsql.append(" or ( ");
					hsql.append("t.start_province = '"+businessLineDomainList.get(i).getStartProvince()+"' ");
					hsql.append(" and t.start_city = '"+businessLineDomainList.get(i).getStartCity()+"' ");
					hsql.append(" and t.end_province = '"+businessLineDomainList.get(i).getEndProvince()+"' ");
					hsql.append(" and t.end_city = '"+businessLineDomainList.get(i).getEndCity()+"' ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') >= '"+businessLineDomainList.get(i).getStartTime()+"' ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') <= '"+businessLineDomainList.get(i).getEndTime()+"' ");
					hsql.append(" ) ");
				}
			}
			hsql.append(") ");
			hsql.append(" order by t.id asc LIMIT 0,20");
			map.put("hsql",hsql.toString());
			//根据预约查询货源
			if(businessLineDomainList.size() != 0) {
				list = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
			}
			String sql1 = getQueryHqlId(list);
			//优先级2(运营线路 双向)
			if(list.size()<20) {
				//获取运营线路
				List<MarketingDriverLine> marketingDriverLineList = marketingDriverLineService.queryMarketingDriverLineByCustomerDriverId(id);
				hsql.setLength(0);
				hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
				hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'2' as REMARK ");
				hsql.append(" from t_order_cargo_info t where ");
				hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
				hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
				hsql.append("and ( ");
				for(int i = 0;i<marketingDriverLineList.size();i++) {
					if(i == 0) {
						hsql.append("(");
						hsql.append("t.start_province = '"+marketingDriverLineList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.start_city = '"+marketingDriverLineList.get(i).getStartCity()+"' ");
						hsql.append(" and t.end_province = '"+marketingDriverLineList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.end_city = '"+marketingDriverLineList.get(i).getEndCity()+"' ");
						hsql.append(") or (");
						hsql.append("t.start_province = '"+marketingDriverLineList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.start_city = '"+marketingDriverLineList.get(i).getEndCity()+"' ");
						hsql.append(" and t.end_province = '"+marketingDriverLineList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.end_city = '"+marketingDriverLineList.get(i).getStartCity()+"' ");
						hsql.append(") ");
					}else {
						hsql.append(" or (");
						hsql.append("t.start_province = '"+marketingDriverLineList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.start_city = '"+marketingDriverLineList.get(i).getStartCity()+"' ");
						hsql.append(" and t.end_province = '"+marketingDriverLineList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.end_city = '"+marketingDriverLineList.get(i).getEndCity()+"' ");
						hsql.append(") or (");
						hsql.append("t.start_province = '"+marketingDriverLineList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.start_city = '"+marketingDriverLineList.get(i).getEndCity()+"' ");
						hsql.append(" and t.end_province = '"+marketingDriverLineList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.end_city = '"+marketingDriverLineList.get(i).getStartCity()+"' ");
						hsql.append(") ");
					}
				}
				hsql.append(") ");
				if(StringUtils.isNotEmpty(sql1)) {
					hsql.append(" and t.id not in ("+sql1+") ");
				}
				hsql.append(" order by t.id asc LIMIT 0,"+(20-list.size()));
				map.put("hsql",hsql.toString());
				if(marketingDriverLineList.size() != 0) {
					List<OrderCargoInfoDomain> listLine = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
					//合并list
					list = setOrderCargoInfoDomainList(list,listLine);
				}
			}
			String sql2 = getQueryHqlId(list);
			//优先级3(常跑城市)
			if(list.size()<20) {
				hsql.setLength(0);
				hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
				hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'4' as REMARK ");
				hsql.append(" from t_order_cargo_info t where ");
				hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
				hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
				hsql.append("and ( ");
				int j = 0;
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity1())) {
					j = 1;
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity1()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity2())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity2()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity3())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity3()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity4())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity4()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity5())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity5()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity6())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity6()));
				}
				hsql.append(") ");
				if(StringUtils.isNotEmpty(sql2)) {
					hsql.append(" and t.id not in ("+sql2+") ");
				}
				hsql.append(" order by t.id asc LIMIT 0,"+(20-list.size()));
				map.put("hsql",hsql.toString());
				if(j == 1) {
					List<OrderCargoInfoDomain> listoftenCity = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
					//合并list
					list = setOrderCargoInfoDomainList(list,listoftenCity);
				}
			}
		}else{//已经安装
			//获取司机客户信息（已安装）
			marketingDriverInfoDomain = driverInfoService.queryMarketingDriverInfoDomainById(Integer.parseInt(id));
			//获取预约线路List
			businessLineDomainList  = marketingDriverBusinessLineService.queryDriverBusinessLineInfoByIdList(marketingDriverInfoDomain.getDriverId());
			//优先级1(预约线路 单向)
			hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
			hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'1' as REMARK ");
			hsql.append(" from t_order_cargo_info t where ");
			hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
			hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
			hsql.append("and ( ");
			for(int i = 0;i<businessLineDomainList.size();i++){
				if(i == 0) {
					hsql.append("(");
					hsql.append("t.start_province = '"+businessLineDomainList.get(i).getStartProvince()+"' ");
					hsql.append(" and t.start_city = '"+businessLineDomainList.get(i).getStartCity()+"' ");
					hsql.append(" and t.end_province = '"+businessLineDomainList.get(i).getEndProvince()+"' ");
					hsql.append(" and t.end_city = '"+businessLineDomainList.get(i).getEndCity()+"' ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') >= DATE_FORMAT('"+businessLineDomainList.get(i).getStartTime()+"','%Y%m%d') ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') <= DATE_FORMAT('"+businessLineDomainList.get(i).getEndTime()+"','%Y%m%d') ");
					hsql.append(") ");
				}else {
					hsql.append(" or ( ");
					hsql.append("t.start_province = '"+businessLineDomainList.get(i).getStartProvince()+"' ");
					hsql.append(" and t.start_city = '"+businessLineDomainList.get(i).getStartCity()+"' ");
					hsql.append(" and t.end_province = '"+businessLineDomainList.get(i).getEndProvince()+"' ");
					hsql.append(" and t.end_city = '"+businessLineDomainList.get(i).getEndCity()+"' ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') >= DATE_FORMAT('"+businessLineDomainList.get(i).getStartTime()+"','%Y%m%d') ");
					hsql.append(" and DATE_FORMAT(t.request_start_time,'%Y%m%d') <= DATE_FORMAT('"+businessLineDomainList.get(i).getEndTime()+"','%Y%m%d') ");
					hsql.append(" ) ");
				}
			}
			hsql.append(") ");
			hsql.append(" order by t.id asc LIMIT 0,20");
			map.put("hsql",hsql.toString());
			//根据预约查询货源
			if(businessLineDomainList.size() != 0) {
				list = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
			}
			String sql1 = getQueryHqlId(list);
			//优先级2(运营线路 双向)
			if(list.size()<20) {
				//运营线路（已经安装）
				List<DriverLineInfo> driverLineInfoList = marketingDriverLineService.queryDriverLineInfoByDriverId(String.valueOf(marketingDriverInfoDomain.getDriverId()));
				hsql.setLength(0);
				hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
				hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'2' as REMARK ");
				hsql.append(" from t_order_cargo_info t where ");
				hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
				hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
				if(driverLineInfoList.size()>0) {
					hsql.append("and ( ");
				}
				for(int i = 0;i<driverLineInfoList.size();i++) {
					if(i == 0) {
						hsql.append("(");
						hsql.append("t.start_province = '"+driverLineInfoList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.start_city = '"+driverLineInfoList.get(i).getStartCity()+"' ");
						hsql.append(" and t.end_province = '"+driverLineInfoList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.end_city = '"+driverLineInfoList.get(i).getEndCity()+"' ");
						hsql.append(") or (");
						hsql.append("t.start_province = '"+driverLineInfoList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.start_city = '"+driverLineInfoList.get(i).getEndCity()+"' ");
						hsql.append(" and t.end_province = '"+driverLineInfoList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.end_city = '"+driverLineInfoList.get(i).getStartCity()+"' ");
						hsql.append(") ");
					}else {
						hsql.append(" or (");
						hsql.append("t.start_province = '"+driverLineInfoList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.start_city = '"+driverLineInfoList.get(i).getStartCity()+"' ");
						hsql.append(" and t.end_province = '"+driverLineInfoList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.end_city = '"+driverLineInfoList.get(i).getEndCity()+"' ");
						hsql.append(") or (");
						hsql.append("t.start_province = '"+driverLineInfoList.get(i).getEndProvince()+"' ");
						hsql.append(" and t.start_city = '"+driverLineInfoList.get(i).getEndCity()+"' ");
						hsql.append(" and t.end_province = '"+driverLineInfoList.get(i).getStartProvince()+"' ");
						hsql.append(" and t.end_city = '"+driverLineInfoList.get(i).getStartCity()+"' ");
						hsql.append(") ");
					}
				}
				if(driverLineInfoList.size()>0) {
					hsql.append(") ");
				}
				if(StringUtils.isNotEmpty(sql1)) {
					hsql.append(" and t.id not in ("+sql1+") ");
				}
				hsql.append(" order by t.id asc LIMIT 0,"+(20-list.size()));
				map.put("hsql",hsql.toString());
				if(driverLineInfoList.size() != 0) {
					List<OrderCargoInfoDomain> listLine = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
					//合并list
					list = setOrderCargoInfoDomainList(list,listLine);
				}
			}
			String sql2 = getQueryHqlId(list);
			//优先级3(当前位置24小时有效)
			if(list.size()<20) {
				DriverUserInfo driverUserInfo = driverUserInfoDao.queryDriverUserInfoLocation(marketingDriverInfoDomain.getDriverId());
				if(driverUserInfo != null) {
					hsql.setLength(0);
					hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
					hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'3' as REMARK ");
					hsql.append(" from t_order_cargo_info t where ");
					hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
					hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
					if(StringUtils.isNotEmpty(driverUserInfo.getProvince())) {
						hsql.append("and t.start_province = '"+driverUserInfo.getProvince()+"' ");
					}
					if(StringUtils.isNotEmpty(driverUserInfo.getProvince())) {
						hsql.append("and t.start_city = '"+driverUserInfo.getCity()+"' ");
					}
					if(StringUtils.isNotEmpty(sql2)) {
						hsql.append(" and t.id not in ("+sql2+") ");
					}
					hsql.append(" order by t.id asc LIMIT 0,"+(20-list.size()));
					map.put("hsql",hsql.toString());
					if(StringUtils.isNotEmpty(driverUserInfo.getProvince())) {
						List<OrderCargoInfoDomain> listoftenCity = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
						//合并list
						list = setOrderCargoInfoDomainList(list,listoftenCity);
					}
				}
			}
			String sql3 = getQueryHqlId(list);
			//优先级4(常跑城市)
			if(list.size()<20) {
				hsql.setLength(0);
				hsql.append("select t.id,t.cargo_name,DATE_FORMAT(t.request_start_time,'%Y-%m-%d') as request_start_time,t.start_province,t.start_city,t.start_county,t.end_province,");
				hsql.append("t.end_city,t.end_county,t.cargo_type,t.company_name,t.contact_mobilephone,t.contact_telephone,'4' as REMARK ");
				hsql.append(" from t_order_cargo_info t where ");
				hsql.append("DATE_FORMAT(t.request_start_time,'%Y%m%d')>=DATE_FORMAT(SYSDATE(),'%Y%m%d') ");
				hsql.append("and t.cargo_flag = 0 and t.deleted_flag = 0 ");
				hsql.append("and ( ");
				int j = 0;
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity1())) {
					j = 1;
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity1()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity2())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity2()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity3())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity3()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity4())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity4()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity5())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity5()));
				}
				if(StringUtils.isNotEmpty(marketingDriverInfoDomain.getOftenCity6())) {
					if(j != 0) {
						hsql.append(" or ");
					}else {
						j = 1;
					}
					hsql.append(getOftenCityHsql(marketingDriverInfoDomain.getOftenCity6()));
				}
				hsql.append(") ");
				if(StringUtils.isNotEmpty(sql3)) {
					hsql.append(" and t.id not in ("+sql3+") ");
				}
				hsql.append(" order by t.id asc LIMIT 0,"+(20-list.size()));
				map.put("hsql",hsql.toString());
				if(j == 1) {
					List<OrderCargoInfoDomain> listoftenCity = orderCargoInfoDao.queryOrderCargoInfoDomainDriverInfoHsql(map);
					//合并list
					list = setOrderCargoInfoDomainList(list,listoftenCity);
				}
			}
		}
		return list;
	}

	/**
	 * 合并list
	 * @param list
	 * @param list1
	 * @return
	 */
	public List<OrderCargoInfoDomain> setOrderCargoInfoDomainList(List<OrderCargoInfoDomain> list,List<OrderCargoInfoDomain> list1) {
		for(int i = 0;i<list1.size();i++) {
			list.add(list1.get(i));
		}
		return list;
	}

	/**
	 *  拼装id
	 * @param list
	 * @return
	 */
	public String getQueryHqlId(List<OrderCargoInfoDomain> list) {
		StringBuffer idHsql = new StringBuffer();
		for(int i = 0;i<list.size();i++) {
			if(i == 0) {
				idHsql.append(list.get(i).getId());
			}else {
				idHsql.append(","+list.get(i).getId());
			}
		}
		return idHsql.toString();
	}

	public String getOftenCityHsql(String oftenCity) {
		StringBuffer hsql = new StringBuffer();
		String[] oftenCitysplit = oftenCity.split("-");
		hsql.append(" ( ");
		for(int i = 0;i<oftenCitysplit.length;i++) {
			if(i == 0) {
				hsql.append(" t.start_province =  '"+oftenCitysplit[0]+"' ");
			}else if(i == 1) {
				hsql.append(" and t.start_city = '"+oftenCitysplit[1]+"' ");
			}
		}
		hsql.append(" ) ");
		return hsql.toString();
	}


}
