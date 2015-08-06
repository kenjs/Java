package com.cy.dctms.webUser.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.CompanyInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.bo.ReasonInfo;
import com.cy.dctms.common.bo.WebUserInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;
import com.cy.dctms.common.util.MathUtil;
import com.cy.dctms.common.util.TimeDealUtil;
import com.cy.dctms.webUser.dao.IWebUserInfoDao;


public class WebUserInfoDaoImp extends BaseDao implements IWebUserInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryWebUserInfoList(WebUserInfoDomain webUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", webUserInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (webUserInfoDomain.getPageInfo().getCurPage()-1)*webUserInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("code", webUserInfoDomain.getCode());
			queryMap.put("name",java.net.URLDecoder.decode( webUserInfoDomain.getName(),"utf-8"));
			queryMap.put("mobilephone", webUserInfoDomain.getMobilephone());
			queryMap.put("companyName", java.net.URLDecoder.decode( webUserInfoDomain.getCompanyName(),"utf-8"));
			queryMap.put("freezeFlag", webUserInfoDomain.getFreezeFlag());
			queryMap.put("registerTimeQ", webUserInfoDomain.getRegisterTimeQ());
			queryMap.put("registerTimeZ", webUserInfoDomain.getRegisterTimeZ());
			webUserInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_webUser_info_count",queryMap));// 总页数
			
			List<WebUserInfoDomain> dataList = (List<WebUserInfoDomain>) queryForList("query_webUser_info_by_page",queryMap);
			//标志转换为名称
			for (WebUserInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getSubmitType())) {
					domain.setSubmitType(FlagChangeName.submitType(domain.getSubmitType()));
				}
			}
			webUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_webUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void auditWebUserInfoList(WebUserInfoDomain webUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if (webUserInfoDomain.getPageInfo()!=null) {
				queryMap.put("pageSize", webUserInfoDomain.getPageInfo().getPageSize());//每页记录数
				queryMap.put("start", (webUserInfoDomain.getPageInfo().getCurPage()-1)*webUserInfoDomain.getPageInfo().getPageSize());
			}
				//检索条件
			if ("0".equals(webUserInfoDomain.getIsPushAll())) {
				queryMap.put("idList", webUserInfoDomain.getIdList());
				queryMap.put("isPushAll", "0");
				queryMap.put("submitType", webUserInfoDomain.getSubmitType());
			}else {
				queryMap.put("submitType", webUserInfoDomain.getSubmitType());
				queryMap.put("code", webUserInfoDomain.getCode());
				queryMap.put("name",java.net.URLDecoder.decode( webUserInfoDomain.getName(),"utf-8"));
				queryMap.put("mobilephone", webUserInfoDomain.getMobilephone());
				queryMap.put("companyName", java.net.URLDecoder.decode( webUserInfoDomain.getCompanyName(),"utf-8"));
				queryMap.put("enterpriseTimeQ", webUserInfoDomain.getEnterpriseTimeQ());
				queryMap.put("enterpriseTimeZ", webUserInfoDomain.getEnterpriseTimeZ());
			}
			if (webUserInfoDomain.getPageInfo()!=null) {
				webUserInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("audit_webUser_info_count",queryMap));// 总页数
			}
			List<WebUserInfoDomain> dataList = (List<WebUserInfoDomain>) queryForList("audit_webUser_info_by_page",queryMap);
			//标志转换为名称
			for (WebUserInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getSubmitType())) {
					domain.setSubmitType(FlagChangeName.submitType(domain.getSubmitType()));
				}
			}
			webUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("audit_webUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryWebUserInfoTransactionList(WebUserInfoDomain webUserInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("companyName", java.net.URLDecoder.decode( webUserInfoDomain.getCompanyName(),"utf-8"));
			queryMap.put("registerTimeQ", webUserInfoDomain.getRegisterTimeQ());
			queryMap.put("registerTimeZ", webUserInfoDomain.getRegisterTimeZ());
			List<WebUserInfoDomain> dataList = (List<WebUserInfoDomain>) queryForList("query_webUser_transaction_info_by_page",queryMap);
			//标志转换为名称
			int accumulateCargoCount = 0;
			int orderCargoCount = 0;
			int orderCargoSuccessCount = 0;
			int accumulateTransactionCount = 0;
			for (WebUserInfoDomain domain : dataList) {
//				if (StringUtils.isNotBlank(domain.getUserType())) {
//					domain.setUserType(FlagChangeName.userType(domain.getUserType()));
//				}
				if(StringUtils.isNotBlank(domain.getAccumulateCargoCount()))accumulateCargoCount += Integer.valueOf(domain.getAccumulateCargoCount());
				if(StringUtils.isNotBlank(domain.getOrderCargoCount()))orderCargoCount += Integer.valueOf(domain.getOrderCargoCount());
				if(StringUtils.isNotBlank(domain.getOrderCargoSuccessCount()))orderCargoSuccessCount += Integer.valueOf(domain.getOrderCargoSuccessCount());
				if(StringUtils.isNotBlank(domain.getAccumulateTransactionCount()))accumulateTransactionCount += Integer.valueOf(domain.getAccumulateTransactionCount());
				domain.setCreateTime(TimeDealUtil.TimeToYyyyMMDd(domain.getCreateTime()));
				//计算百分数
				domain.setOrderCargoRate(
						MathUtil.decimalChangePercent(
								MathUtil.subDecimalNum(domain.getOrderCargoSuccessCount(),domain.getAccumulateCargoCount())
								));
				domain.setAccumulateTransactionRate(
						MathUtil.decimalChangePercent(
								MathUtil.subDecimalNum(domain.getAccumulateTransactionCount(),domain.getOrderCargoCount())
								));
			}
			webUserInfoDomain.setAccumulateCargoCount(String.valueOf(accumulateCargoCount));
			webUserInfoDomain.setOrderCargoCount(String.valueOf(orderCargoCount));
			webUserInfoDomain.setAccumulateTransactionCount(String.valueOf(accumulateTransactionCount));
			webUserInfoDomain.setOrderCargoRate(
					MathUtil.decimalChangePercent(
							MathUtil.subDecimalNum(String.valueOf(orderCargoSuccessCount), String.valueOf(accumulateCargoCount))));
			webUserInfoDomain.setAccumulateTransactionRate(
					MathUtil.decimalChangePercent(
							MathUtil.subDecimalNum(String.valueOf(accumulateTransactionCount), String.valueOf(orderCargoCount))));
			webUserInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_webUserInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public WebUserInfoDomain queryWebUserInfoById(String id) {
		try {
			WebUserInfoDomain domain = new WebUserInfoDomain();
			domain =  (WebUserInfoDomain) queryForObject("query_webUser_info_by_id", id);
			if (StringUtils.isNotBlank(domain.getSubmitType())) {
				domain.setSubmitType(FlagChangeName.submitType(domain.getSubmitType()));
			}
			return domain;
		} catch (Exception e) {
			logger.error("query_webUserInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId) {
		try {
			CompanyInfo bo = new CompanyInfo();
			bo.setCompanyName(java.net.URLDecoder.decode(webUserInfoDomain.getCompanyName(),"utf-8"));
			bo.setBusinessLicence(java.net.URLDecoder.decode(webUserInfoDomain.getBusinessLicence(),"utf-8"));
			bo.setOrganizationCode(java.net.URLDecoder.decode(webUserInfoDomain.getOrganizationCode(),"utf-8"));
			bo.setId(Long.valueOf(webUserInfoDomain.getCompanyId()));
			saveObject("modify_webUser_info", bo);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("修改企业信息");
		 	MWLIbo.setColumnId(Long.valueOf(webUserInfoDomain.getCompanyId()));
		 	String content = "";
		 	content = content + "企业名称:"+bo.getCompanyName() +";";
		 	content = content + "营业执照注册号:"+bo.getBusinessLicence() +";";
		 	content = content + "组织机构代码:"+bo.getOrganizationCode() +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_company_info");
			addObject("add_workLog_info", MWLIbo);
			
		} catch (Exception e) {
			logger.error("save_webUserInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteWebUserInfo(WebUserInfoDomain webUserInfoDomain ,String userId) {
		try {
			if ("1".equals(webUserInfoDomain.getFreezeFlag())) {
				deleteObject("unfreeze_webUser_info_by_id", webUserInfoDomain.getId());
			}else {
				deleteObject("delete_webUser_info_by_id", webUserInfoDomain.getId());
				 //添加冻结理由
				 ReasonInfo bo = new ReasonInfo();
				 bo.setDriverUserId(Long.valueOf(webUserInfoDomain.getId()));
				 bo.setReason(java.net.URLDecoder.decode(webUserInfoDomain.getDeleteReason(),"utf-8"));
				 bo.setType(2);
				 addObject("add_reason_info", bo);
			}
		 	
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	if ("1".equals(webUserInfoDomain.getFreezeFlag())) {
		 		MWLIbo.setName("解冻司机信息");
			}else {
				MWLIbo.setName("冻结司机信息");
			}
		 	MWLIbo.setColumnId(Long.valueOf(webUserInfoDomain.getId()));
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_web_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_webUserInfo",e);
			throw new RuntimeException();
		}
		
	}

	@Override
	public void auditWebUserInfo(WebUserInfoDomain webUserInfoDomain,
			String userId) {
		try {
			WebUserInfo bo = new WebUserInfo();
			bo.setId(Long.valueOf(webUserInfoDomain.getId()));
			bo.setSubmitType(Long.valueOf(webUserInfoDomain.getSubmitType()));
			if ("3".equals(webUserInfoDomain.getSubmitType())) {
				bo.setEnterpriseFlag(Long.valueOf(1));
			}else {
				bo.setEnterpriseFlag(Long.valueOf(0));
			}
			saveObject("audit_webUser_info", bo);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("审核企业信息");
		 	MWLIbo.setColumnId(Long.valueOf(webUserInfoDomain.getId()));
		 	String content = "";
		 	content +=  "审核结果:"+FlagChangeName.submitType(webUserInfoDomain.getSubmitType()) +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_web_user_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("save_webUserInfo",e);
			throw new RuntimeException();
		}
		
	}
}
