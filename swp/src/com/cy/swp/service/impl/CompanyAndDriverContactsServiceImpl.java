package com.cy.swp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.swp.bo.MarketingCargoAssist;
import com.cy.swp.bo.MarketingCargoCompanyContacts;
import com.cy.swp.bo.MarketingCargoDriverContacts;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.MarketingCargoAssistDao;
import com.cy.swp.dao.MarketingCargoCompanyContactsDao;
import com.cy.swp.dao.MarketingCargoDriverContactsDao;
import com.cy.swp.service.CompanyAndDriverContactsService;

@Service("companyAndDriverContactsService")
public class CompanyAndDriverContactsServiceImpl implements CompanyAndDriverContactsService{

	@Resource
	private MarketingCargoDriverContactsDao marketingCargoDriverContactsDao;
	@Resource
	private MarketingCargoCompanyContactsDao marketingCargoCompanyContactsDao;
	@Resource
	private MarketingCargoAssistDao marketingCargoAssistDao;
	
	public String addCargoDriverContacts(
			MarketingCargoDriverContacts marketingCargoDriverContacts) {
		
		//添加货源司机电话回访记录
		marketingCargoDriverContactsDao.addCargoDriverContacts(marketingCargoDriverContacts);
		
		//修改货源专员协助表信息(a.是否搭桥成功)
			//1.判断是否搭桥成功
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("assistId", marketingCargoDriverContacts.getAssistId()); 
		queryMap.put("replyResult", Constants.DRIVER_REPLYRESULT_INTENTION_KEY);//有意向
		int counts=marketingCargoDriverContactsDao.countDriverReplyByAssistIdAndResult(queryMap);
			//2.数据库中修改货源专员协助表信息
		MarketingCargoAssist marketingCargoAssist=new MarketingCargoAssist();
		marketingCargoAssist.setId(marketingCargoDriverContacts.getAssistId());
		if(counts>0){//存在司机有意向去拉这条货
			marketingCargoAssist.setIsMatchSuccess(Constants.BRIDGING_SUCCESS);//有意向
		}else{
			marketingCargoAssist.setIsMatchSuccess(Constants.BRIDGING_NOT_SUCCESS);
		}
		marketingCargoAssistDao.updateMarketingCargoAssist(marketingCargoAssist);
		return marketingCargoDriverContacts.getId();
	}

	public String addCargoCompanyContacts(
			MarketingCargoCompanyContacts marketingCargoCompanyContacts) {
		marketingCargoCompanyContactsDao.addCargoCompanyContacts(marketingCargoCompanyContacts);
		return marketingCargoCompanyContacts.getId();
	}

	public MarketingCargoCompanyContacts queryCompanyContactByTelephone(
			String contactTelephone) {
		return marketingCargoCompanyContactsDao.queryCompanyContactByTelephone(contactTelephone);
	}

	public List<MarketingCargoCompanyContacts> queryCompanyContactByTelephoneList(
			String contactMobilephone) {
		return marketingCargoCompanyContactsDao.queryCompanyContactByTelephoneList(contactMobilephone);
	}
	
}
