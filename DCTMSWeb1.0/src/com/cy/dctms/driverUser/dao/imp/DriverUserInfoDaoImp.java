package com.cy.dctms.driverUser.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.DriverUserInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.bo.ReasonInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;
import com.cy.dctms.common.util.MathUtil;
import com.cy.dctms.common.util.TimeDealUtil;
import com.cy.dctms.driverUser.dao.IDriverUserInfoDao;


public class DriverUserInfoDaoImp extends BaseDao implements IDriverUserInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if (driverUserInfoDomain.getPageInfo()!=null) {
				queryMap.put("pageSize", driverUserInfoDomain.getPageInfo().getPageSize());//每页记录数
				queryMap.put("start", (driverUserInfoDomain.getPageInfo().getCurPage()-1)*driverUserInfoDomain.getPageInfo().getPageSize());
			}
			
			//检索条件
			queryMap.put("code", driverUserInfoDomain.getCode());
			queryMap.put("name", java.net.URLDecoder.decode(driverUserInfoDomain.getName(),"utf-8"));
			queryMap.put("carNumber", java.net.URLDecoder.decode(driverUserInfoDomain.getCarNumber(),"utf-8"));
			queryMap.put("freezeFlag", driverUserInfoDomain.getFreezeFlag());
			queryMap.put("registerTimeQ", driverUserInfoDomain.getRegisterTimeQ());
			queryMap.put("registerTimeZ", driverUserInfoDomain.getRegisterTimeZ());
			if (driverUserInfoDomain.getPageInfo()!=null) {
				driverUserInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_driverUser_info_count",queryMap));// 总页数
			}
			List<DriverUserInfoDomain> dataList = (List<DriverUserInfoDomain>) queryForList("query_driverUser_info_by_page",queryMap);
			//标志转换为名称
	
			for (DriverUserInfoDomain domain : dataList) {
				
				if (StringUtils.isNotBlank(domain.getSubmitType())) {
					domain.setSubmitType(FlagChangeName.submitType(domain.getSubmitType()));
				}
				domain.setCreateTime(TimeDealUtil.TimeToYyyyMMDd(domain.getCreateTime()));
			
			}
			driverUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_driverUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void auditDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if (driverUserInfoDomain.getPageInfo()!=null) {
				queryMap.put("pageSize", driverUserInfoDomain.getPageInfo().getPageSize());//每页记录数
				queryMap.put("start", (driverUserInfoDomain.getPageInfo().getCurPage()-1)*driverUserInfoDomain.getPageInfo().getPageSize());
			}
			//检索条件
			if ("0".equals(driverUserInfoDomain.getIsPushAll())) {
				queryMap.put("idList", driverUserInfoDomain.getIdList());
				queryMap.put("isPushAll", "0");
			}else {
				queryMap.put("submitType", driverUserInfoDomain.getSubmitType());
				queryMap.put("code", driverUserInfoDomain.getCode());
				queryMap.put("name", java.net.URLDecoder.decode(driverUserInfoDomain.getName(),"utf-8"));
				queryMap.put("carNumber", java.net.URLDecoder.decode(driverUserInfoDomain.getCarNumber(),"utf-8"));
				queryMap.put("submitType", driverUserInfoDomain.getSubmitType());
				queryMap.put("commitAuditTimeQ", driverUserInfoDomain.getCommitAuditTimeQ());
				queryMap.put("commitAuditTimeZ", driverUserInfoDomain.getCommitAuditTimeZ());
			}
			if (driverUserInfoDomain.getPageInfo()!=null) {
				driverUserInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("audit_driverUser_info_count",queryMap));// 总页数
			}
			List<DriverUserInfoDomain> dataList = (List<DriverUserInfoDomain>) queryForList("audit_driverUser_info_by_page",queryMap);
			//标志转换为名称
	
			for (DriverUserInfoDomain domain : dataList) {
				
				if (StringUtils.isNotBlank(domain.getSubmitType())) {
					domain.setSubmitType(FlagChangeName.submitType(domain.getSubmitType()));
				}
				domain.setCreateTime(TimeDealUtil.TimeToYyyyMMDd(domain.getCreateTime()));
			
			}
			driverUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_driverUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryDriverUserTransactionInfoList(DriverUserInfoDomain driverUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if (driverUserInfoDomain.getPageInfo()!=null) {
				queryMap.put("pageSize", driverUserInfoDomain.getPageInfo().getPageSize());//每页记录数
				queryMap.put("start", (driverUserInfoDomain.getPageInfo().getCurPage()-1)*driverUserInfoDomain.getPageInfo().getPageSize());
			}
			//检索条件
			queryMap.put("code", driverUserInfoDomain.getCode());
			queryMap.put("name", java.net.URLDecoder.decode(driverUserInfoDomain.getName(),"utf-8"));
			queryMap.put("carNumber", java.net.URLDecoder.decode(driverUserInfoDomain.getCarNumber(),"utf-8"));
			queryMap.put("registerTimeQ", driverUserInfoDomain.getRegisterTimeQ());
			queryMap.put("registerTimeZ", driverUserInfoDomain.getRegisterTimeZ());
			queryMap.put("currentLocationTimeQ", driverUserInfoDomain.getCurrentLocationTimeQ());
			queryMap.put("currentLocationTimeZ", driverUserInfoDomain.getCurrentLocationTimeZ());
			if (driverUserInfoDomain.getPageInfo()!=null) {
				driverUserInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_driverUser_transaction_info_count",queryMap));// 总页数
			}
			List<DriverUserInfoDomain> dataList = (List<DriverUserInfoDomain>) queryForList("query_driverUser_transaction_info_by_page",queryMap);
			//标志转换为名称
			for (DriverUserInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getSubmitType())) {
					domain.setSubmitType(FlagChangeName.submitType(domain.getSubmitType()));
				}
			}
			driverUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_driverUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void driverUserTotalDataList(DriverUserInfoDomain driverUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
//			queryMap.put("code", driverUserInfoDomain.getCode());
//			queryMap.put("name", java.net.URLDecoder.decode(driverUserInfoDomain.getName(),"utf-8"));
//			queryMap.put("carNumber", java.net.URLDecoder.decode(driverUserInfoDomain.getCarNumber(),"utf-8"));
			queryMap.put("registerTimeQ", driverUserInfoDomain.getRegisterTimeQ());
			queryMap.put("registerTimeZ", driverUserInfoDomain.getRegisterTimeZ());
			List<DriverUserInfoDomain> dataList = (List<DriverUserInfoDomain>) queryForList("query_driverUser_total_data",queryMap);
			int orderingCount =0;//总订车数量
			int passOrderCount = 0;//总成交订单数量
			int goodFindNum = 0;
			int phoneCallNum = 0;
			int quoteCount = 0;
			for (DriverUserInfoDomain domain : dataList) {
				domain.setCreateTime(TimeDealUtil.TimeToYyyyMMDd(domain.getCreateTime()));
				//计算总订车数量
				if(StringUtils.isNotBlank(domain.getOrderingCount())&&!domain.getOrderingCount().equals("0"))
					orderingCount += Integer.valueOf(domain.getOrderingCount());
				else 
					domain.setOrderingCount("");
				if(StringUtils.isNotBlank(domain.getPassOrderCount())&&!domain.getPassOrderCount().equals("0"))passOrderCount += Integer.valueOf(domain.getPassOrderCount());
				else domain.setPassOrderCount("");
				if(StringUtils.isNotBlank(domain.getGoodFindNum())&&!domain.getGoodFindNum().equals("0"))goodFindNum += Integer.valueOf(domain.getGoodFindNum());
				else domain.setGoodFindNum("");
				if(StringUtils.isNotBlank(domain.getPhoneCallNum())&&!domain.getPhoneCallNum().equals("0"))phoneCallNum += Integer.valueOf(domain.getPhoneCallNum());
				else domain.setPhoneCallNum("");
				if(StringUtils.isNotBlank(domain.getQuoteCount())&&!domain.getQuoteCount().equals("0"))
					quoteCount += Integer.valueOf(domain.getQuoteCount());
				else 
					domain.setQuoteCount("");
				//转换为百分率
				domain.setOrderingRate(
						MathUtil.decimalChangePercent(
								MathUtil.subDecimalNum(domain.getOrderingCount(),domain.getGoodFindNum())));
				domain.setPassOrderRate(
						MathUtil.decimalChangePercent(
								MathUtil.subDecimalNum(domain.getPassOrderCount(), domain.getOrderingCount())));
			}
			driverUserInfoDomain.setOrderingCount(String.valueOf(orderingCount));
			driverUserInfoDomain.setPassOrderCount(String.valueOf(passOrderCount));
			driverUserInfoDomain.setGoodFindNum(String.valueOf(goodFindNum));
			driverUserInfoDomain.setPhoneCallNum(String.valueOf(phoneCallNum));
			driverUserInfoDomain.setQuoteCount(String.valueOf(quoteCount));
			driverUserInfoDomain.setOrderingRate(
					MathUtil.decimalChangePercent(
							MathUtil.subDecimalNum(String.valueOf(orderingCount),String.valueOf( goodFindNum))));
			driverUserInfoDomain.setPassOrderRate(
					MathUtil.decimalChangePercent(
							MathUtil.subDecimalNum(String.valueOf(passOrderCount), String.valueOf(orderingCount))));
			driverUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_driverUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public DriverUserInfoDomain queryDriverUserInfoById(String id) {
		try {
			DriverUserInfoDomain driverUserInfoDomain = new DriverUserInfoDomain();
			driverUserInfoDomain = (DriverUserInfoDomain)queryForObject("query_driverUser_info_by_id",id);
			if (StringUtils.isNotBlank(driverUserInfoDomain.getSubmitType())) {
				driverUserInfoDomain.setSubmitType(FlagChangeName.submitType(driverUserInfoDomain.getSubmitType()));
			}
			return driverUserInfoDomain;
		} catch (Exception e) {
			logger.error("query_driverUserInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public DriverUserInfoDomain queryDriverUserInfoByCode(String code) {
		try {
			DriverUserInfoDomain driverUserInfoDomain = (DriverUserInfoDomain)queryForObject("query_driverUser_info_by_code", code);
			return driverUserInfoDomain;
		} catch (Exception e) {
			logger.error("query_driverUserInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId) {
		try {
			DriverUserInfo bo = new DriverUserInfo();
			bo.setName(java.net.URLDecoder.decode(driverUserInfoDomain.getName(),"utf-8"));
			bo.setCarNumber(java.net.URLDecoder.decode(driverUserInfoDomain.getCarNumber(),"utf-8"));
			bo.setIdentityLicenseNum(java.net.URLDecoder.decode(driverUserInfoDomain.getIdentityLicenseNum(),"utf-8"));
			bo.setId(Long.valueOf(driverUserInfoDomain.getId()));
			saveObject("modify_driverUser_info", bo);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("修改司机信息");
		 	MWLIbo.setColumnId(Long.valueOf(driverUserInfoDomain.getId()));
		 	String content = "";
		 	content = content + "name:"+bo.getName() +";";
		 	content = content + "carNumber:"+bo.getCarNumber() +";";
		 	content = content + "identityLicenseNum:"+bo.getIdentityLicenseNum() +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_driver_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("save_driverUserInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId) {
		try {
			if ("1".equals(driverUserInfoDomain.getFreezeFlag())) {
				deleteObject("unfreeze_driverUser_info_by_id", driverUserInfoDomain.getId());
			}else {
				 deleteObject("delete_driverUser_info_by_id", driverUserInfoDomain.getId());
				 //添加冻结理由
				 ReasonInfo bo = new ReasonInfo();
				 bo.setDriverUserId(Long.valueOf(driverUserInfoDomain.getId()));
				 bo.setReason(java.net.URLDecoder.decode(driverUserInfoDomain.getDeleteReason(),"utf-8"));
				 bo.setType(1);
				 addObject("add_reason_info", bo);
			}
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	if ("1".equals(driverUserInfoDomain.getFreezeFlag())) {
		 		MWLIbo.setName("解冻司机信息");
			}else {
				MWLIbo.setName("冻结司机信息");
			}
		 	MWLIbo.setColumnId(Long.valueOf(driverUserInfoDomain.getId()));
		 	MWLIbo.setContent("");
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_driver_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_driverUserInfo",e);
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public void auditDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId) {
		try {
			DriverUserInfo bo = new DriverUserInfo();
			bo.setSubmitType(Long.valueOf(driverUserInfoDomain.getSubmitType()));
			if ("3".equals(driverUserInfoDomain.getSubmitType())) {
				bo.setAuditFlag(Long.valueOf(1));
			}else {
				bo.setAuditFlag(Long.valueOf(0));
			}
			bo.setId(Long.valueOf(driverUserInfoDomain.getId()));
			//修改审核标志
			saveObject("audit_driverUser_info", bo);
			String content = "";
		 	content =  "审核结果:"+FlagChangeName.submitType(driverUserInfoDomain.getSubmitType())+";";
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("审核司机信息");
		 	MWLIbo.setColumnId(Long.valueOf(driverUserInfoDomain.getId()));
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_driver_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("audit_driverUserInfo",e);
			throw new RuntimeException();
		}
	}
}
