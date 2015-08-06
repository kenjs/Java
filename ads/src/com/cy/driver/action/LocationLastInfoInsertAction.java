package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.LocationCollectLastInfoBo;
import com.cy.driver.service.LocationLastInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 位置信息上传
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("locationLastInfoInsertAction")
public class LocationLastInfoInsertAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private LocationLastInfoService locationLastInfoService;

    @RequestMapping(value = "/locationLastInfoInsertAction")
    @ResponseBody
	public JSonResponse execute(String driverId, LocationCollectLastInfoBo bo) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            //如果最新位置信息已经有就更新
            int checkRes = locationLastInfoService.checkLocationExist(bo.getDriverId());
            if(checkRes != 0) {
                locationLastInfoService.updateLastLocation(bo);
            } else {
                locationLastInfoService.insertLastLocation(bo);
            }
            locationLastInfoService.insertLocation(bo);
            if (log.isDebugEnabled()) {
                log.debug("上传位置信息上传成功");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "上传位置信息上传成功");
		} catch (Exception e) {
			log.error("上传位置信息出错 - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "上传位置信息出错.");
		}

	}

}
