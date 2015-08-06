package com.cy.swp.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cy.swp.bo.MarketingCargoAssist;
import com.cy.swp.bo.MarketingCargoCompanyContacts;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.dao.MarketingCargoAssistDao;
import com.cy.swp.dao.MarketingCargoCompanyContactsDao;
import com.cy.swp.domain.MarketingCargoAssistDomain;
import com.cy.swp.service.MarketingCargoAssistService;
import com.cy.swp.service.OrderCargoInfoService;

@Service("marketingCargoAssistService")
public class MarketingCargoAssistServiceImpl implements MarketingCargoAssistService{
	@Resource
	private MarketingCargoAssistDao marketingCargoAssistDao;
	@Resource
	private MarketingCargoCompanyContactsDao marketingCargoCompanyContactsDao;
	@Resource
	private OrderCargoInfoService orderCargoInfoService;
	
	//2.循环修改专员协助表中的货源回复记录（导入的货源和专员协助表是一对一的关系）
	public String saveCompAndCargoContacts(MarketingCargoAssist marketingCargoAssist,String userId) {
		try {
			if(marketingCargoAssist!=null){
				if(StringUtils.isNotEmpty(marketingCargoAssist.getId())&& StringUtils.isNotEmpty(marketingCargoAssist.getCargoResult())){//id不为空且回复不为空
					//若企业反馈的是没有该条货物或货已经走了（删除此货物）
					if(Constants.CARGO_REPLYRESULT_NONENTITY_KEY.equals(marketingCargoAssist.getCargoResult())|| 
							Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY.equals(marketingCargoAssist.getCargoResult())){
						marketingCargoAssistDao.updateMarketingCargoAssist(marketingCargoAssist);
						orderCargoInfoService.modifyOrderDeleteFlagById(marketingCargoAssist.getCargoId(), userId);
						return "1";
					}else{
						marketingCargoAssistDao.updateMarketingCargoAssist(marketingCargoAssist);
						return "2";
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateMarketingCargoAssist(
			MarketingCargoAssist marketingCargoAssist) {
		return marketingCargoAssistDao.updateMarketingCargoAssist(marketingCargoAssist);
	}
	
	//添加公司回复记录(允许公司记录为空)------多条数据
	public void saveCompContacts(MarketingCargoCompanyContacts marketingCargoCompanyContacts,String userId) {
		if(marketingCargoCompanyContacts!=null){
			if(Constants.COMP_REPLYRESULT_INVALID_KEY.equals(marketingCargoCompanyContacts.getReplyResult())){
				//若公司联系人的号码无效,删除该公司下的所以货源
				orderCargoInfoService.modifyDeletedFlagByPhone(marketingCargoCompanyContacts.getContactMobilephone(),userId);
			}
		}
		marketingCargoCompanyContactsDao.addCargoCompanyContacts(marketingCargoCompanyContacts);
	}

}
