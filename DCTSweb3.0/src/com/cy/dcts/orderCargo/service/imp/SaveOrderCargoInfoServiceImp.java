package com.cy.dcts.orderCargo.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.orderCargo.dao.IOrderCargoInfoDao;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;

public class SaveOrderCargoInfoServiceImp implements ISaveOrderCargoInfoService{
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    private IOrderCargoInfoDao orderCargoInfoDao; 
    

	public boolean modifyImportOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		return orderCargoInfoDao.modifyImportOrderCargoInfo(orderCargoInfo);
	}

	public String addOrderCargoInfo(OrderCargoInfo orderCargoInfo, WebUserInfo userInfo){
		orderCargoInfo.setCargoFlag(String.valueOf(Constants.CARGO_FLAG_PENDING_TRADE_KEY));//待交易
		orderCargoInfo.setCompanyId(userInfo.getCompanyId());
		orderCargoInfo.setDeployUserid(userInfo.getId());
		orderCargoInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//未删除
		orderCargoInfo.setCargoOrigin(Constants.CARGO_ORIGIN_PUBLISH);//发布货源
		return orderCargoInfoDao.addOrderCargoInfo(orderCargoInfo);
	}

	public boolean modifyOrderCargoInfo(OrderCargoInfo orderCargoInfo,
			WebUserInfo userInfo) {
		orderCargoInfo.setModifyUserid(userInfo.getId());
		return orderCargoInfoDao.modifyOrderCargoInfo(orderCargoInfo);
	}

	public boolean modifyOrderCargoFlag(String orderId, String webId, String cargoFlag) {
		Map<String, Object> modifyMap = new HashMap<String, Object>();
		modifyMap.put("id", orderId);
		modifyMap.put("cargoFlag",cargoFlag);
		modifyMap.put("modifyUserid", webId);
		return orderCargoInfoDao.modifyOrderCargoFlag(modifyMap);
	}

	public boolean modifyOrderDeleteFlag(String orderId, String webId,
			int deleteFlag) {
		Map<String, Object> modifyMap = new HashMap<String, Object>();
		modifyMap.put("id", orderId);
		modifyMap.put("deletedFlag",deleteFlag);
		modifyMap.put("modifyUserid", webId);
		return orderCargoInfoDao.modifyOrderDeleteFlag(modifyMap);
	}
	
	//通过导入excel，批量新增保存入库   事务控制
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName="Exception")
	public List<String> batchAddOrderCargoInfo(List<OrderCargoInfo> list, WebUserInfo userInfo) throws Exception {
		List<String> listId = new ArrayList<String>();
		for (OrderCargoInfo orderCargoInfo : list) {
			orderCargoInfo.setCargoFlag(String.valueOf(Constants.CARGO_FLAG_PENDING_TRADE_KEY));//待交易
			orderCargoInfo.setCompanyId(userInfo.getCompanyId());
			orderCargoInfo.setDeployUserid(userInfo.getId());
			orderCargoInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//未删除
			orderCargoInfo.setCargoOrigin(Constants.CARGO_ORIGIN_PUBLISH);//发布货源
			
			String id = orderCargoInfoDao.addOrderCargoInfo(orderCargoInfo);
			listId.add(id);
			logger.debug("batch add order cargo success. USERID=[{}], USERCODE=[{}], CARGOID=[{}]",
				new Object[]{userInfo.getId(), userInfo.getCode(), id});
		}
		return listId;
	}

	
	public IOrderCargoInfoDao getOrderCargoInfoDao() {
		return orderCargoInfoDao;
	}

	public void setOrderCargoInfoDao(IOrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}
	

}
