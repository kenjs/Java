package com.cy.dctms.timeTask;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.service.DayCountSystemBusiService;
import com.cy.dctms.service.DaycountDriverActiveService;
import com.cy.dctms.service.DaycountDriverUserBusiService;
import com.cy.dctms.service.DaycountWebUserBusiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by haoy on 2014/9/23.
 */
public class DaycountDriverActiveTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DaycountDriverActiveService daycountDriverActiveService;

    @Resource
    private DaycountDriverUserBusiService daycountDriverUserBusiService;

    @Resource
    private DaycountWebUserBusiService daycountWebUserBusiService;

    @Resource
    private DayCountSystemBusiService dayCountSystemBusiService;

    public void execute() {
        try {
            String dataCountedDate = DateUtils.getBeforeDay();
            if (logger.isInfoEnabled()) {
                logger.info("计算" + dataCountedDate + "司机存活活跃数据开始,当前时间：" + DateUtils.getCurrentDateTime());
            }
            daycountDriverActiveService.countDriverActiveData();
            if (logger.isInfoEnabled()) {
                logger.info("计算" + dataCountedDate + "司机存活活跃数据完成,当前时间：" + DateUtils.getCurrentDateTime());
            }

            daycountDriverUserBusiTask();
            daycountWebUserBusiTask();
            daycountSystemBusiTask();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void daycountDriverUserBusiTask() throws Exception {
        String dataCountedDate = DateUtils.getBeforeDay();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "司机业务数据开始,当前时间：" + DateUtils.getCurrentDateTime());
        }
        daycountDriverUserBusiService.countDriverUserBusiData();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "司机业务数据完成,当前时间：" + DateUtils.getCurrentDateTime());
        }
    }

    private void daycountWebUserBusiTask() throws Exception {
        String dataCountedDate = DateUtils.getBeforeDay();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "货源企业业务数开始,当前时间：" + DateUtils.getCurrentDateTime());
        }
        daycountWebUserBusiService.countWebUserBusiData();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "货源企业业务数完成,当前时间：" + DateUtils.getCurrentDateTime());
        }
    }

    private void daycountSystemBusiTask() throws Exception {
        String dataCountedDate = DateUtils.getBeforeDay();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "系统业务数开始,当前时间：" + DateUtils.getCurrentDateTime());
        }
        dayCountSystemBusiService.countSystemBusiData();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "系统业务数完成,当前时间：" + DateUtils.getCurrentDateTime());
        }
    }
}
