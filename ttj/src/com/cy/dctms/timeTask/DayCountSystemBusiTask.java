package com.cy.dctms.timeTask;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.service.DayCountSystemBusiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author haoy
 * @description 按天统计系统业务数
 */
public class DayCountSystemBusiTask {

    @Resource
    private DayCountSystemBusiService dayCountSystemBusiService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void execute() throws SQLException {
        String dataCountedDate = DateUtils.getBeforeDay();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "系统业务数据开始,当前时间：" + DateUtils.getCurrentDateTime());
        }
        dayCountSystemBusiService.countSystemBusiData();
        if (logger.isInfoEnabled()) {
            logger.info("统计" + dataCountedDate + "系统业务数据完成,当前时间：" + DateUtils.getCurrentDateTime());
        }
    }
}
