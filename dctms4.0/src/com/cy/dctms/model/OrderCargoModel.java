package com.cy.dctms.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dctms.constants.Constants;
import com.cy.dctms.entity.OrderCargoInfo;
import com.cy.dctms.orm.MybatisDaoSurrport;

@Service
public class OrderCargoModel {

	@Autowired
	private MybatisDaoSurrport mybatisDaoSurrport;
	
	public void addCargo(List<OrderCargoInfo> list) throws Exception {		
		for (OrderCargoInfo orderCargoInfo : list) {
			orderCargoInfo.setCargoFlag(String.valueOf(Constants.CARGO_FLAG_PENDING_TRADE_KEY));//待交易			
			orderCargoInfo.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));//未删除
			
			mybatisDaoSurrport.add("com.cy.dctms.entity.OrderCargoInfo.insertOrderCargoInfo", orderCargoInfo);			
		}
		
	}

}
