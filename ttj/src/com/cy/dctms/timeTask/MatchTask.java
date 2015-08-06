package com.cy.dctms.timeTask;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.service.MatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by Haoyong on 2015/1/14.
 */
public class MatchTask {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private MatchingService matchingService;

    public void execute() {
        if (log.isInfoEnabled()) {
            log.info("本次网络爬虫货源匹配短信或PUSH发送开始......当前时间：" + DateUtils.getCurrentDateTime());
        }

        matchingService.matchingDriverNote();

        if (log.isInfoEnabled()) {
            log.info("本次网络爬虫爬取货源匹配短信或PUSH结束......当前时间：" + DateUtils.getCurrentDateTime());
        }
    }
}
