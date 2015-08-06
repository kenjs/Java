package com.cy.driver.action;

import com.cy.driver.bo.DriverLineInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.DriverLineInfoDomain;
import com.cy.driver.service.DriverLineInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 新增营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverLineInfoAddAction")
public class DriverLineInfoAddAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverLineInfoService driverLineInfoService;

    @RequestMapping(value = "/driverLineInfoAdd")
    @ResponseBody
    @Log(type = 22)
	public JSonResponse execute(String driverId, DriverLineInfoBo bo) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			bo.setDriverId(driverId);

            String startProvince = bo.getStartProvince();
			if(StringUtils.isBlank(startProvince)) {
                if (log.isInfoEnabled()) {
                    log.info("请选择起始地-省");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始地-省.");
			}

            String startCity = bo.getStartCity();
//			if(StringUtils.isBlank(startCity)) {
//                if (log.isInfoEnabled()) {
//                    log.info("请选择起始地-市");
//                }
//				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始地-市.");
//			}

            String endProvince = bo.getEndProvince();
			if(StringUtils.isBlank(endProvince)) {
                if (log.isInfoEnabled()) {
                    log.info("请选择目的地-省");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择目的地-省.");
			}

            String endCity = bo.getEndCity();
//			if(StringUtils.isBlank(endCity)) {
//                if (log.isInfoEnabled()) {
//                    log.info("请选择目的地-市");
//                }
//				return JSonResponse.makeHasContentJSonRespone("-8", "请选择目的地-市.");
//			}
			
			if(StringUtils.isBlank(bo.getLineType())) {
                if (log.isInfoEnabled()) {
                    log.info("请选择线路类型");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择线路类型.");
			}

			List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
			for (DriverLineInfoDomain e : list) {
				if(startProvince.equals(e.getStartProvince()) && endProvince.equals(e.getEndProvince())) {
					return JSonResponse.makeHasContentJSonRespone("-8", "新增的线路与其他线路重复,请修改.");
				}
			}
			
			int count = driverLineInfoService.selectDriverLineInfoCount(bo.getDriverId());
			if(count >= 3) {
                if (log.isInfoEnabled()) {
                    log.info("最多只能添加3条");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "最多只能添加3条.");
			}
			int i = driverLineInfoService.insertDriverLineInfo(bo);
			if(i == 0){
                if (log.isInfoEnabled()) {
                    log.info("新增运营线路失败.");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "新增运营线路失败.");
			}

            if (log.isInfoEnabled()) {
                log.info("新增运营线路成功.");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "新增运营线路成功.");
		} catch (Exception e) {
			log.error("新增运营线路出错." + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "新增运营线路出错.");
		}

	}

}
