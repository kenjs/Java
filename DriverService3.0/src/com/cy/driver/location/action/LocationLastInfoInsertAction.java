package com.cy.driver.location.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.LocationCollectLastInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.location.service.LocationLastInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 位置信息上传
 * @date 2014-6-6
 * @author haoyong
 *
 */
public class LocationLastInfoInsertAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1635889709564705719L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private LocationLastInfoService locationLastInfoService;
	
	public void setLocationLastInfoService(
			LocationLastInfoService locationLastInfoService) {
		this.locationLastInfoService = locationLastInfoService;
	}

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			LocationCollectLastInfoBo bo = getBo();
						
			if(StringUtils.isBlank(bo.getDriverId())){
				log.info("找不到司机");
				sendResponseToJson("-9", "找不到司机");
				return ERROR;
			}
			int accFlag = operationLogService.checkUser(bo.getDriverId());
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(bo.getDriverId());
			
			int checkRes = 0;
			int i = locationLastInfoService.insertLocation(bo);
			if(i == 0){
				log.info("上传位置信息上传失败");
				sendResponseToJson("-8", "上传位置信息上传失败");
			}else{
				//如果最新位置信息已经有就更新
				checkRes = locationLastInfoService.checkLocationExist(bo.getDriverId());
				if(checkRes != 0) {
					locationLastInfoService.updateLastLocation(bo);
				} else {
					locationLastInfoService.insertLastLocation(bo);
				}
				log.info("上传位置信息上传成功");
				sendResponseToJson("1", "上传位置信息上传成功");
			}
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

	private LocationCollectLastInfoBo getBo(){
		LocationCollectLastInfoBo bo = new LocationCollectLastInfoBo();
		
		bo.setDriverId(request.getParameter("driverId"));
		bo.setLongitude(request.getParameter("longitude"));
		bo.setLatitude(request.getParameter("latitude"));
		bo.setProvince(request.getParameter("province"));
		bo.setCity(request.getParameter("city"));
		bo.setCounty(request.getParameter("county"));
		bo.setTown(request.getParameter("town"));
		bo.setLocation(bo.getProvince()+bo.getCity()+bo.getCounty()+bo.getTown());
		
		return bo;
	}
	
	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("locationLastInfoInsertAction");
		bo.setOperationType(41);
		bo.setRemark("位置信息上传");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
