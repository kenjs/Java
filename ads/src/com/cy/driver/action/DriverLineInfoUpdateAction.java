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
 * 修改营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverLineInfoUpdateAction")
public class DriverLineInfoUpdateAction extends AuthenticationAction{

	
	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverLineInfoService driverLineInfoService;

    @RequestMapping(value = "/driverLineInfoUpdate")
    @ResponseBody
    @Log(type = 24)
	public JSonResponse execute(String driverId,DriverLineInfoBo bo) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			String id = bo.getId() + "";
			if(StringUtils.isBlank(id)){
                if (log.isInfoEnabled()) {
                    log.info("没有找到要修改的运营路线");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "没有找到要修改的运营路线.");
			}
			
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
                    log.info("请选择起始地-省");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始地-省.");
			}
			
			String endCity = bo.getEndCity();
//			if(StringUtils.isBlank(endCity)) {
//                if (log.isInfoEnabled()) {
//                    log.info("请选择起始地-市");
//                }
//				return JSonResponse.makeHasContentJSonRespone("-8", "请选择起始地-市.");
//			}

			List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
			for (DriverLineInfoDomain e : list) {
				if(! id.equals(e.getId())) {
					if(startProvince.equals(e.getStartProvince()) && endProvince.equals(e.getEndProvince())) {
						return JSonResponse.makeHasContentJSonRespone("-8", "修改后的线路与其他线路重复,请修改.");
					}
				}
			}
			
			int i = driverLineInfoService.updateDriverLineInfo(bo);
			if(i != 1){
                if (log.isInfoEnabled()) {
                    log.info("修改运营线路失败.");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "修改运营线路失败.");
			}

            if (log.isInfoEnabled()) {
                log.info("修改运营线路成功.");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "修改运营线路成功.");
		} catch (Exception e) {
			log.error("修改运营线路出错." + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "修改运营线路出错.");
		}

	}

}
