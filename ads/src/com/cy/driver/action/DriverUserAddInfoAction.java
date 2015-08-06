package com.cy.driver.action;

import com.cy.driver.bo.DriverLineInfoBo;
import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverLineInfoService;
import com.cy.driver.service.DriverUserCargoInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * 注册填写资料
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverUserAddInfoAction")
public class DriverUserAddInfoAction extends AuthenticationAction {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverLineInfoService driverLineInfoService;

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/driverUserAddInfoAction")
    @ResponseBody
    @Log(type = 5)
    public JSonResponse execute(String driverId, DriverUserInfoBo driverUserInfoBo) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			String lineJson = driverUserInfoBo.getDriverLineJSON();

			if(StringUtils.isBlank(driverUserInfoBo.getName())) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入司机姓名");
			}

			if(StringUtils.isBlank(driverUserInfoBo.getCarNumber())) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入车辆牌照号码");
			}
			
			if(StringUtils.isBlank(driverUserInfoBo.getCarPlateType())) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入车板类型");
			}
		
			if(StringUtils.isBlank(driverUserInfoBo.getCarLength())) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入车长");
			}
			
			if(StringUtils.isBlank(driverUserInfoBo.getCarWeight())) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入运力-吨位");
			}
			
			if(StringUtils.isBlank(driverUserInfoBo.getCarCubage())) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请输入运力-体积");
			}
			
			DriverLineInfoBo bo = null;
			List<DriverLineInfoBo> driverLineInfoBoList = new ArrayList<DriverLineInfoBo>();			
			JSONArray json = JSONArray.fromObject(lineJson);
			Object[] array = json.toArray();
			List<Map<String,Object>> listOb = parseJSONArray2List(array);		
			for (Map<String, Object> e : listOb) {
				bo = new DriverLineInfoBo(); 
				bo.setDriverId(driverId);
				if(e.get("startProvince") != null) {
					bo.setStartProvince(e.get("startProvince").toString());
				}
				if(e.get("startCity") != null) {
					bo.setStartCity(e.get("startCity").toString());
				}
				if(e.get("endProvince") != null) {
					bo.setEndProvince(e.get("endProvince").toString());			
				}
				if(e.get("endCity") != null) {
					bo.setEndCity(e.get("endCity").toString());
				}
				
				driverLineInfoBoList.add(bo);
			}
			
			int i1 = driverUserCargoInfoService.updateDriverUserInfo(driverUserInfoBo);
			int i2 = 0;
			for (DriverLineInfoBo driverLineInfoBo : driverLineInfoBoList) {
				i2 += driverLineInfoService.insertDriverLineInfo(driverLineInfoBo);
			}
			
			if(i1 == 0 || i2 == 0){
                if (log.isInfoEnabled()) {
                    log.info("用户注册填写资料失败");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "填写资料失败");
			}
			return JSonResponse.makeHasContentJSonRespone("1", "填写资料成功");
		} catch (Exception e) {
			log.error("DriverUserAddInfoAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常，请重试。");
		}

	}

	/**
	 * JSON数组转成List
	 * 	list内以map作为集合元素
	 * @param array
	 * @return
	 */
	private List<Map<String,Object>> parseJSONArray2List(Object[] array) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		JSONObject object = null;
		Iterator iterator = null;
		String str = null;
		String index = null;
		if(array.length > 0) {
			for(int i=0;i<array.length;i++) {
				map = new HashMap<String, Object>();
				object = JSONObject.fromObject(array[i]);
				iterator = object.keys();
				while(iterator.hasNext()) {
					index = iterator.next().toString();
					str = object.getString(index);
					if("start".equals(index)) {
						if(str.contains("-")) {
							map.put("startProvince", str.split("-")[0]);
							map.put("startCity", str.split("-")[1]);
						} else {
							map.put("startProvince", null);
							map.put("startCity", str);
						}
					} else {
						if(str.contains("-")) {
							map.put("endProvince", str.split("-")[0]);
							map.put("endCity", str.split("-")[1]);
						} else {
							map.put("endProvince", null);
							map.put("endCity", str);
						}
					}					
				}
				list.add(map);			
			}
		}
		
		return list;
	}

}
