package com.cy.driver.action;

import com.cy.driver.bo.DriverBusinessLineInfo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.service.DriverBusinessLineInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 新增预约
 * @date 2014-5-27
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverBusinessLineInfoAddAction")
public class DriverBusinessLineInfoAddAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());
    
    @Resource
	private DriverBusinessLineInfoService driverBusinessLineInfoService;

    @RequestMapping(value = "/driverBusinessLineInfoAddAction")
    @ResponseBody
    @Log(type = 7)
	public JSonResponse execute(String driverId, DriverBusinessLineInfo domain) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			String startProvince = domain.getStartProvince();
			String startCity = domain.getStartCity();
			String endProvince = domain.getEndProvince();
			String endCity = domain.getEndCity();

            String startTime = domain.getStartTime();
            String endTime = domain.getEndTime();
			if(StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                if (log.isInfoEnabled()) {
                    log.info("请选择起始时间");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始时间.");
			} else {
				try {
					Date currenDate = DateUtil.getNow();
					Date startDate = DateUtil.formatDate(domain.getStartTime());
					if(DateUtil.isEarly(startDate,currenDate)) {
                        if (log.isInfoEnabled()) {
                            log.info("起始时间不能晚于当前时间.");
                        }
						return JSonResponse.makeHasContentJSonRespone("-8", "起始时间不能晚于当前时间.");
					}
					Date endDate = DateUtil.formatDate(domain.getEndTime());
					if(DateUtil.isEarly(endDate,startDate)) {
                        if (log.isInfoEnabled()) {
                            log.info("结束时间不能晚于起始时间.");
                        }
						return JSonResponse.makeHasContentJSonRespone("-8", "结束时间不能晚于起始时间.");
					}
					Date futureDate = DateUtil.getFurturDate(31);
					if(DateUtil.isEarly(futureDate, endDate)) {
                        if (log.isInfoEnabled()) {
                            log.info("结束时间必须在一个月之内.");
                        }
						return JSonResponse.makeHasContentJSonRespone("-8", "结束时间必须在一个月之内.");
					}
				} catch (ParseException e) {
					e.printStackTrace();
					log.error(e.getMessage());
					return JSonResponse.makeHasContentJSonRespone("-8", "日期转换出现异常");
				}
			}
			if(StringUtils.isBlank(startProvince)) {
                if (log.isInfoEnabled()) {
                    log.info("请选择起始地-省份");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始地-省份.");
			}
//			if(StringUtils.isBlank(startCity)) {
//                if (log.isInfoEnabled()) {
//                    log.info("请选择起始地-城市");
//                }
//				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始地-城市.");
//			}
			if(StringUtils.isBlank(endProvince)) {
                if (log.isInfoEnabled()) {
                    log.info("请选择目的地-省份");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择目的地-省份.");
			}
//			if(StringUtils.isBlank(endCity)) {
//                if (log.isInfoEnabled()) {
//                    log.info("请选择目的地-城市");
//                }
//				return JSonResponse.makeHasContentJSonRespone("-8", "请选择目的地-城市.");
//			}

            if (StringUtils.isBlank(domain.getQuoteFair())) {
                domain.setQuoteFair("0");
            }

			//判断添加的预约路线是否重复
			List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);
			for (DriverBusinessLineInfoDomain e : list) {
				if(startProvince.equals(e.getStartProvince()) && endProvince.equals(e.getEndProvince())) {
                        if (log.isInfoEnabled()) {
                            log.info("预约线路重复添加, 请修改. ");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "预约线路重复添加, 请修改. ");
				}
			}
			
			int count = driverBusinessLineInfoService.
							selectDriverBusinessLineInfoCount(domain.getDriverId()+"");
			if(count >= 3){
                if (log.isInfoEnabled()) {
                    log.info("最多只能添加3条");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "最多只能添加3条.");
			}
			int i = driverBusinessLineInfoService.insertDriverBusinessLineInfo(domain);
			if(i != 0){
                if (log.isInfoEnabled()) {
                    log.info("新增预约成功");
                }
               return JSonResponse.makeHasContentJSonRespone("1", "新增预约成功.");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("新增预约失败");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "新增预约失败.");
			}
		} catch (Exception e) {
			log.error("新增预约发生错误" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "新增预约发生错误.");
		}

	}

}
