package com.cy.dctms.driverUserAssess.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.DriverUserAssessInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;
import com.cy.dctms.driverUserAssess.dao.IDriverUserAssessInfoDao;


public class DriverUserAssessInfoDaoImp extends BaseDao implements IDriverUserAssessInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryDriverUserAssessInfoList(DriverUserAssessInfoDomain driverUserAssessInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", driverUserAssessInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (driverUserAssessInfoDomain.getPageInfo().getCurPage()-1)*driverUserAssessInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("cargoId", driverUserAssessInfoDomain.getCargoId());
			queryMap.put("cargoName", java.net.URLDecoder.decode( driverUserAssessInfoDomain.getCargoName(),"utf-8"));
			queryMap.put("driverId", driverUserAssessInfoDomain.getDriverId());
			queryMap.put("driverCode", driverUserAssessInfoDomain.getDriverCode());
			queryMap.put("driverName", driverUserAssessInfoDomain.getDriverName());
			queryMap.put("userId", driverUserAssessInfoDomain.getUserId());
			queryMap.put("userCode", driverUserAssessInfoDomain.getUserCode());
			queryMap.put("userName", driverUserAssessInfoDomain.getUserName());
			queryMap.put("transactionId", driverUserAssessInfoDomain.getTransactionId());
			queryMap.put("orderNumber", driverUserAssessInfoDomain.getOrderNumber());
			queryMap.put("assessEvaluateScore", driverUserAssessInfoDomain.getAssessEvaluateScore());
			queryMap.put("queryTimeQ", driverUserAssessInfoDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", driverUserAssessInfoDomain.getQueryTimeZ());
			driverUserAssessInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_driverUserAssess_info_count",queryMap));// 总页数
			
			List<DriverUserAssessInfoDomain> dataList = (List<DriverUserAssessInfoDomain>) queryForList("query_driverUserAssess_info_by_page",queryMap);
			for (DriverUserAssessInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getAssessEvaluateScore())) {
					domain.setAssessEvaluateScore(FlagChangeName.assess(domain.getAssessEvaluateScore()));
				}
			}
			driverUserAssessInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_driverUserAssessInfo_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("cargoId", driverUserAssessInfoDomain.getCargoId());
			queryMap.put("cargoName", java.net.URLDecoder.decode( driverUserAssessInfoDomain.getCargoName(),"utf-8"));
			queryMap.put("driverId", driverUserAssessInfoDomain.getDriverId());
			queryMap.put("driverCode", driverUserAssessInfoDomain.getDriverCode());
			queryMap.put("driverName", driverUserAssessInfoDomain.getDriverName());
			queryMap.put("userId", driverUserAssessInfoDomain.getUserId());
			queryMap.put("userCode", driverUserAssessInfoDomain.getUserCode());
			queryMap.put("userName", driverUserAssessInfoDomain.getUserName());
			queryMap.put("transactionId", driverUserAssessInfoDomain.getTransactionId());
			queryMap.put("orderNumber", driverUserAssessInfoDomain.getOrderNumber());
			queryMap.put("assessEvaluateScore", driverUserAssessInfoDomain.getAssessEvaluateScore());
			queryMap.put("queryTimeQ", driverUserAssessInfoDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", driverUserAssessInfoDomain.getQueryTimeZ());
			List<DriverUserAssessInfoDomain> dataList = (List<DriverUserAssessInfoDomain>) queryForList("export_driverUserAssess_info",queryMap);
			driverUserAssessInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_driverUserAssessInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public DriverUserAssessInfoDomain queryDriverUserAssessInfoById(String id) {
		try {
			return (DriverUserAssessInfoDomain) queryForObject("query_driverUserAssess_info_by_id", id);
		} catch (Exception e) {
			logger.error("query_driverUserAssessInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain,String userId) {
		try {
			DriverUserAssessInfo bo = new DriverUserAssessInfo();
			bo.setCargoId(Long.valueOf(driverUserAssessInfoDomain.getCargoId()));
			bo.setDriverId(Long.valueOf(driverUserAssessInfoDomain.getDriverId()));
			bo.setUserId(Long.valueOf(driverUserAssessInfoDomain.getUserId()));
			bo.setTransactionId(Long.valueOf(driverUserAssessInfoDomain.getTransactionId()));
			bo.setAssessEvaluateScore(Long.valueOf(driverUserAssessInfoDomain.getAssessEvaluateScore()));
			bo.setAssess(java.net.URLDecoder.decode(driverUserAssessInfoDomain.getAssess(),"utf-8"));
			bo.setId(Long.valueOf(driverUserAssessInfoDomain.getId()));
			saveObject("modify_driverUserAssess_info", bo);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("修改司机对企业评价信息");
		 	MWLIbo.setColumnId(Long.valueOf(driverUserAssessInfoDomain.getId()));
		 	String content = "";
		 	content = content + "cargoId:"+bo.getCargoId() +";";
		 	content = content + "driverId:"+bo.getDriverId() +";";
		 	content = content + "userId:"+bo.getUserId() +";";
		 	content = content + "transactionId:"+bo.getTransactionId() +";";
		 	content = content + "assessEvaluateScore:"+bo.getAssessEvaluateScore() +";";
		 	content = content + "assess:"+bo.getAssess() +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_driver_user_assess_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("save_driverUserAssessInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteDriverUserAssessInfo(String id ,String userId) {
		try {
			 deleteObject("delete_driverUserAssess_info_by_id", id);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("删除司机对企业评价信息");
		 	MWLIbo.setColumnId(Long.valueOf(id));
		 	MWLIbo.setContent("id:"+id);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_driver_user_assess_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_driverUserAssessInfo",e);
			throw new RuntimeException();
		}
		
	}
}
