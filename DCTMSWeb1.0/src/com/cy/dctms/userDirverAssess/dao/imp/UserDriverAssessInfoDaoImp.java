package com.cy.dctms.userDirverAssess.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.UserDriverAssessInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.userDirverAssess.dao.IUserDriverAssessInfoDao;


public class UserDriverAssessInfoDaoImp extends BaseDao implements IUserDriverAssessInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryUserDriverAssessInfoList(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", userDriverAssessInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (userDriverAssessInfoDomain.getPageInfo().getCurPage()-1)*userDriverAssessInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("driverId", userDriverAssessInfoDomain.getDriverId());
			queryMap.put("cargoId", userDriverAssessInfoDomain.getCargoId());
			queryMap.put("userId", userDriverAssessInfoDomain.getUserId());
			queryMap.put("transactionId", userDriverAssessInfoDomain.getTransactionId());
			queryMap.put("arriverEvaluateScore", userDriverAssessInfoDomain.getArriverEvaluateScore());
			queryMap.put("serveEvaluateScore", userDriverAssessInfoDomain.getServeEvaluateScore());
			queryMap.put("tradeEvaluateScore", userDriverAssessInfoDomain.getTradeEvaluateScore());
			queryMap.put("cargoName", java.net.URLDecoder.decode( userDriverAssessInfoDomain.getCargoName(),"utf-8"));
			queryMap.put("driverCode", userDriverAssessInfoDomain.getDriverCode());
			queryMap.put("driverName", userDriverAssessInfoDomain.getDriverName());
			queryMap.put("userCode", userDriverAssessInfoDomain.getUserCode());
			queryMap.put("userName", userDriverAssessInfoDomain.getUserName());
			queryMap.put("orderNumber", userDriverAssessInfoDomain.getOrderNumber());
			queryMap.put("queryTimeQ", userDriverAssessInfoDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", userDriverAssessInfoDomain.getQueryTimeZ());
			userDriverAssessInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_userDirverAssess_info_count",queryMap));// 总页数
			
			List<UserDriverAssessInfoDomain> dataList = (List<UserDriverAssessInfoDomain>) queryForList("query_userDirverAssess_info_by_page",queryMap);
			for (UserDriverAssessInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getArriverEvaluateScore())) {
					domain.setArriverEvaluateScore(FlagChangeName.assess(domain.getArriverEvaluateScore()));
				}
				if (StringUtils.isNotBlank(domain.getServeEvaluateScore())) {
					domain.setServeEvaluateScore(FlagChangeName.assess(domain.getServeEvaluateScore()));
				}
				if (StringUtils.isNotBlank(domain.getTradeEvaluateScore())) {
					domain.setTradeEvaluateScore(FlagChangeName.assess(domain.getTradeEvaluateScore()));
				}
			}
			userDriverAssessInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_userDriverAssessInfo_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("driverId", userDriverAssessInfoDomain.getDriverId());
			queryMap.put("cargoId", userDriverAssessInfoDomain.getCargoId());
			queryMap.put("userId", userDriverAssessInfoDomain.getUserId());
			queryMap.put("transactionId", userDriverAssessInfoDomain.getTransactionId());
			queryMap.put("arriverEvaluateScore", userDriverAssessInfoDomain.getArriverEvaluateScore());
			queryMap.put("serveEvaluateScore", userDriverAssessInfoDomain.getServeEvaluateScore());
			queryMap.put("tradeEvaluateScore", userDriverAssessInfoDomain.getTradeEvaluateScore());
			queryMap.put("cargoName", java.net.URLDecoder.decode( userDriverAssessInfoDomain.getCargoName(),"utf-8"));
			queryMap.put("driverCode", userDriverAssessInfoDomain.getDriverCode());
			queryMap.put("driverName", userDriverAssessInfoDomain.getDriverName());
			queryMap.put("userCode", userDriverAssessInfoDomain.getUserCode());
			queryMap.put("userName", userDriverAssessInfoDomain.getUserName());
			queryMap.put("orderNumber", userDriverAssessInfoDomain.getOrderNumber());
			queryMap.put("queryTimeQ", userDriverAssessInfoDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", userDriverAssessInfoDomain.getQueryTimeZ());
			List<UserDriverAssessInfoDomain> dataList = (List<UserDriverAssessInfoDomain>) queryForList("export_userDirverAssess_info",queryMap);
			userDriverAssessInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_userDriverAssessInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public UserDriverAssessInfoDomain queryUserDriverAssessInfoById(String id) {
		try {
			return (UserDriverAssessInfoDomain) queryForObject("query_userDirverAssess_info_by_id", id);
		} catch (Exception e) {
			logger.error("query_userDriverAssessInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain,String userId) {
		try {
			UserDriverAssessInfo bo = new UserDriverAssessInfo();
			bo.setDriverId(Long.valueOf(userDriverAssessInfoDomain.getDriverId()));
			bo.setCargoId(Long.valueOf(userDriverAssessInfoDomain.getCargoId()));
			bo.setUserId(Long.valueOf(userDriverAssessInfoDomain.getUserId()));
			bo.setTransactionId(Long.valueOf(userDriverAssessInfoDomain.getTransactionId()));
			bo.setArriverEvaluateScore(Long.valueOf(userDriverAssessInfoDomain.getArriverEvaluateScore()));
			bo.setServeEvaluateScore(Long.valueOf(userDriverAssessInfoDomain.getServeEvaluateScore()));
			bo.setTradeEvaluateScore(Long.valueOf(userDriverAssessInfoDomain.getTradeEvaluateScore()));
			bo.setAssess(java.net.URLDecoder.decode(userDriverAssessInfoDomain.getAssess(),"utf-8"));
			bo.setId(Long.valueOf(userDriverAssessInfoDomain.getId()));
			saveObject("modify_userDirverAssess_info", bo);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("修改企业对司机评价信息");
		 	MWLIbo.setColumnId(Long.valueOf(userDriverAssessInfoDomain.getId()));
		 	String content = "";
		 	content = content + "driverId:"+bo.getDriverId() +";";
		 	content = content + "cargoId:"+bo.getCargoId() +";";
		 	content = content + "userId:"+bo.getUserId() +";";
		 	content = content + "transactionId:"+bo.getTransactionId() +";";
		 	content = content + "arriverEvaluateScore:"+bo.getArriverEvaluateScore() +";";
		 	content = content + "serveEvaluateScore:"+bo.getServeEvaluateScore() +";";
		 	content = content + "tradeEvaluateScore:"+bo.getTradeEvaluateScore() +";";
		 	content = content + "assess:"+bo.getAssess() +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_user_driver_assess_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("save_userDriverAssessInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteUserDriverAssessInfo(String id ,String userId) {
		try {
			 deleteObject("delete_userDirverAssess_info_by_id", id);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("删除企业对司机评价信息");
		 	MWLIbo.setColumnId(Long.valueOf(id));
		 	MWLIbo.setContent("id:"+id);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_user_driver_assess_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_userDriverAssessInfo",e);
			throw new RuntimeException();
		}
		
	}
}
