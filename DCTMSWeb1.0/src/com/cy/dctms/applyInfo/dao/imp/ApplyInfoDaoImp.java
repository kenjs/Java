package com.cy.dctms.applyInfo.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.applyInfo.dao.IApplyInfoDao;
import com.cy.dctms.common.bo.ApplyInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.bo.WebUserInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.ApplyInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;


public class ApplyInfoDaoImp extends BaseDao implements IApplyInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryApplyInfoList(ApplyInfoDomain applyInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", applyInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (applyInfoDomain.getPageInfo().getCurPage()-1)*applyInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("companyId", java.net.URLDecoder.decode(applyInfoDomain.getCompanyId(),"utf-8"));
			queryMap.put("applyType", applyInfoDomain.getApplyType());
			queryMap.put("applyTimeQ", applyInfoDomain.getApplyTimeQ());
			queryMap.put("applyTimeZ", applyInfoDomain.getApplyTimeZ());
			queryMap.put("contactName", java.net.URLDecoder.decode(applyInfoDomain.getContactName(), "utf-8"));
			queryMap.put("verifyStart", applyInfoDomain.getVerifyStart());
			applyInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_applyInfo_info_count",queryMap));// 总页数
			
			List<ApplyInfoDomain> dataList = (List<ApplyInfoDomain>) queryForList("query_applyInfo_info_by_page",queryMap);
			for (ApplyInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getApplyType())) {
					domain.setApplyType(FlagChangeName.applyType(domain.getApplyType()));
				}
				if (StringUtils.isNotBlank(domain.getVerifyStart())) {
					domain.setVerifyStart(FlagChangeName.verifyStart(domain.getVerifyStart()));
				}
			}
			applyInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_applyInfo_list",e);
			throw new RuntimeException();
		}
	}

	@Override
	public ApplyInfoDomain queryApplyInfoById(String id) {
		try {
			ApplyInfoDomain domain = (ApplyInfoDomain) queryForObject("query_applyInfo_info_by_id", id);
			if (StringUtils.isNotBlank(domain.getVerifyStart())) {
				domain.setVerifyStart(FlagChangeName.verifyStart(domain.getVerifyStart()));
			}
			return domain;
		} catch (Exception e) {
			logger.error("query_applyInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveApplyInfo(ApplyInfoDomain applyInfoDomain,String userId) {
		try {
			ApplyInfo bo = new ApplyInfo();
			bo.setVerifyStart(Long.valueOf(applyInfoDomain.getVerifyStart()));
			bo.setVerifyComment(java.net.URLDecoder.decode(applyInfoDomain.getVerifyComment(),"utf-8"));
			bo.setOperatorId(Long.valueOf(userId));
			bo.setId(Long.valueOf(applyInfoDomain.getId()));
			
			saveObject("modify_applyInfo_info", bo);
			//对于通过的修改用户表
			if("1".equals(applyInfoDomain.getVerifyStart())){
				WebUserInfo webUserInfo = new WebUserInfo();
				webUserInfo.setId(Long.valueOf(applyInfoDomain.getUserId()));
				if ("0".equals(applyInfoDomain.getApplyType())) {
					webUserInfo.setPactCargoFlag(1);
				}else if ("1".equals(applyInfoDomain.getApplyType())) {
					webUserInfo.setPactCardFlag(1);
				}else if ("2".equals(applyInfoDomain.getApplyType())) {
					webUserInfo.setPactCarDriverFlag(1);
				}
				saveObject("modify_web_user_apply__info", webUserInfo);
			}
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("审核身份验证申请");
		 	MWLIbo.setColumnId(Long.valueOf(applyInfoDomain.getId()));
		 	String content = "";
		 	if (bo.getVerifyStart()==1) {
		 		content = content + "审核结果:通过;";
			}else {
				content = content + "审核结果:不通过;";
			}
		 	content = content + "回复内容:"+bo.getVerifyComment() +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_apply_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("save_applyInfo",e);
			throw new RuntimeException();
		}
	}
}
