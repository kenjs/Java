package com.cy.swp.service.impl;

import com.cy.swp.bo.MarketingCompanyInfo;
import com.cy.swp.bo.WebUserInfo;
import com.cy.swp.dao.MarketingCompanyInfoDao;
import com.cy.swp.dao.WebUserInfoDao;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.MarketingCompanyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nixianjing on 14/12/11.
 */
@Service("marketingCompanyInfoServiceImpl")
public class MarketingCompanyInfoServiceImpl implements MarketingCompanyInfoService {

    @Resource
    private MarketingCompanyInfoDao marketingCompanyInfoDao;

    @Resource
    private WebUserInfoDao webUserInfoDao;


    @Override
    public boolean addMarketingCompanyInfo(List<OrderCargoInfoDomain> list) {
        boolean retuls = false;
        try{
            for (int i = 0; i<list.size();i++) {
                Integer companyCount = marketingCompanyInfoDao.queryMarketingCompanyInfoByContactMobiphone(list.get(i).getContactMobilephone());
                if(companyCount == 0) {
                    WebUserInfo webUserInfo = webUserInfoDao.queryWebUserInfoByMobilephone(list.get(i).getContactMobilephone());
                    MarketingCompanyInfo marketingCompanyInfo = new MarketingCompanyInfo();
                    marketingCompanyInfo.setContactMobiphone(list.get(i).getContactMobilephone());
                    marketingCompanyInfo.setName(list.get(i).getCompanyName());
                    marketingCompanyInfo.setContactName(list.get(i).getContactName());
                    marketingCompanyInfo.setContactTelephone(list.get(i).getContactTelephone());
                    if(webUserInfo != null) {
                        marketingCompanyInfo.setCompanyId(Integer.parseInt(webUserInfo.getCompanyId()));
                    }
                    marketingCompanyInfoDao.addMarketingCompanyInfo(marketingCompanyInfo);
                }
            }
            retuls = true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return retuls;
    }
}
