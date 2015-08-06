package com.cy.dctms.timeTask;

import com.cy.dctms.service.MarketingDriverInfoLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by haoy on 2014/12/23.
 */
public class MarketingDriverInfoLevelTask {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private MarketingDriverInfoLevelService marketingDriverInfoLevelService;

    public void execute() {
        if (log.isInfoEnabled()) {
            log.info("开始司机等级统计...");
        }
        try {
            marketingDriverInfoLevelService.driverLevelUpdate();
        } catch (Exception e) {
            log.error("司机等级修改出错：" + e.getMessage());
        }
        if (log.isInfoEnabled()) {
            log.info("司机等级统计结束...");
        }
    }
}
