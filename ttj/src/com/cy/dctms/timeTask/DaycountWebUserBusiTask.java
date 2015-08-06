package com.cy.dctms.timeTask;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.service.DaycountWebUserBusiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 按天统计货源企业业务
 * Created by haoy on 2014/9/10.
 */
public class DaycountWebUserBusiTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DaycountWebUserBusiService daycountWebUserBusiService;

    public void execute() throws Exception {
        try {
            String dataCountedDate = DateUtils.getBeforeDay();
            if (logger.isInfoEnabled()) {
                logger.info("统计" + dataCountedDate + "货源企业业务数开始,当前时间：" + DateUtils.getCurrentDateTime());
            }
            daycountWebUserBusiService.countWebUserBusiData();
            if (logger.isInfoEnabled()) {
                logger.info("统计" + dataCountedDate + "货源企业业务数完成,当前时间：" + DateUtils.getCurrentDateTime());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
