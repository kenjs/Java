package com.cy.driver.user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverLineInfoBo;
import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.line.service.DriverLineInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;

/**
 * 注册填写资料
 * @author haoyong
 *
 */
public class DriverUserAddInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4674169050856215431L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverLineInfoService driverLineInfoService;
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			String lineJson = request.getParameter("driverLineJSON");
			
			DriverUserInfoBo driverUserInfoBo = new DriverUserInfoBo();
			if(StringUtils.isBlank(driverId)) {
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
			} else {
				driverUserInfoBo.setId(Integer.parseInt(driverId));
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			if(StringUtils.isBlank(request.getParameter("name"))) {
				sendResponseToJson("-8", "请输入司机姓名");
				return ERROR;
			} else {
				driverUserInfoBo.setName(request.getParameter("name"));
			}

			if(StringUtils.isBlank(request.getParameter("carNumber"))) {
				sendResponseToJson("-8", "请输入车辆牌照号码");
				return ERROR;
			} else {
				driverUserInfoBo.setCarNumber(request.getParameter("carNumber"));
			}
			
			if(StringUtils.isBlank(request.getParameter("carPlateType"))) {
				sendResponseToJson("-8", "请输入车板类型");
				return ERROR;
			} else {
				driverUserInfoBo.setCarPlateType(request.getParameter("carPlateType"));
			}
		
			if(StringUtils.isBlank(request.getParameter("carLength"))) {
				sendResponseToJson("-8", "请输入车长");
				return ERROR;
			} else {
				driverUserInfoBo.setCarLength(request.getParameter("carLength"));
			}
			
			if(StringUtils.isBlank(request.getParameter("carWeight"))) {
				sendResponseToJson("-8", "请输入运力-吨位");
				return ERROR;
			} else {
				driverUserInfoBo.setCarWeight(request.getParameter("carWeight"));
			}
			
			if(StringUtils.isBlank(request.getParameter("carCubage"))) {
				sendResponseToJson("-8", "请输入运力-体积");
				return ERROR;
			} else {
				driverUserInfoBo.setCarCubage(request.getParameter("carCubage"));
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
				log.info("用户注册填写资料失败");
				sendResponseToJson("0", "填写资料失败");
				return ERROR;
			}
			sendResponseToJson("1", "填写资料成功");
		} catch (IOException e) {
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
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
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("driverUserAddInfoAction");
		bo.setOperationType(5);
		bo.setRemark("填写资料");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
