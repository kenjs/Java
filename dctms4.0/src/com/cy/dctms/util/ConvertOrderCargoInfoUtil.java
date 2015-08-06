package com.cy.dctms.util;

import java.text.ParseException;

import com.cy.dctms.entity.OrderCargoInfo;
import com.cy.dctms.entity.OrderCargoInfoDomain;

public class ConvertOrderCargoInfoUtil {

	
	/**
	 * 对象类型转换
	 * @author hayden 20140117
	 * @throws ParseException 
	 * */
	public static OrderCargoInfo getOrderCargoInfoFromDomain(
			final OrderCargoInfoDomain orderCargoInfoDomain) throws ParseException{
		
		if(orderCargoInfoDomain == null){
			return null;
		}
		OrderCargoInfo orderCargoInfo = new OrderCargoInfo();

		orderCargoInfo.setId(orderCargoInfoDomain.getId());
		orderCargoInfo.setCargoName(orderCargoInfoDomain.getCargoName());
		orderCargoInfo.setCargoType(orderCargoInfoDomain.getCargoType());
		orderCargoInfo.setCargoCubage(orderCargoInfoDomain.getCargoCubage());
		orderCargoInfo.setCargoWeight(orderCargoInfoDomain.getCargoWeight());
		orderCargoInfo.setRequestCarLength(orderCargoInfoDomain.getRequestCarLength());
		orderCargoInfo.setRequestCarBarType(orderCargoInfoDomain.getRequestCarBarType());
		orderCargoInfo.setRequestCarPlateType(orderCargoInfoDomain.getRequestCarPlateType());
		orderCargoInfo.setFreight(orderCargoInfoDomain.getFreight());
		orderCargoInfo.setRequestStartTime(DateUtil.parseDayDataFromStr(orderCargoInfoDomain.getRequestStartTime()));
		orderCargoInfo.setRequestEndTime(DateUtil.parseDayDataFromStr(orderCargoInfoDomain.getRequestEndTime()));
		orderCargoInfo.setStartProvince(orderCargoInfoDomain.getStartProvince());
		orderCargoInfo.setStartCity(orderCargoInfoDomain.getStartCity());
		orderCargoInfo.setStartCounty(orderCargoInfoDomain.getStartCounty());
		orderCargoInfo.setStartTown(orderCargoInfoDomain.getStartTown());
		orderCargoInfo.setEndProvince(orderCargoInfoDomain.getEndProvince());
		orderCargoInfo.setEndCity(orderCargoInfoDomain.getEndCity());
		orderCargoInfo.setEndCounty(orderCargoInfoDomain.getEndCounty());
		orderCargoInfo.setEndTown(orderCargoInfoDomain.getEndTown());
		orderCargoInfo.setContactName(orderCargoInfoDomain.getContactName());
		orderCargoInfo.setContactMobilephone(orderCargoInfoDomain.getContactMobilephone());
		orderCargoInfo.setContactTelephone(orderCargoInfoDomain.getContactTelephone());
		orderCargoInfo.setRemark(orderCargoInfoDomain.getRemark());
		orderCargoInfo.setDeployUserid(orderCargoInfoDomain.getDeployUserid());
		orderCargoInfo.setModifyUserid(orderCargoInfoDomain.getModifyUserid());
		orderCargoInfo.setCompanyId(orderCargoInfoDomain.getCompanyId());
		orderCargoInfo.setCargoFlag(orderCargoInfoDomain.getCargoFlag());
		orderCargoInfo.setCargoFlagTime(DateUtil.parseDayDataFromStr(orderCargoInfoDomain.getCargoFlagTime()));
		orderCargoInfo.setCompanyName(orderCargoInfoDomain.getCompanyName());
		//private Date createTime;// 创建时间
		//private Date modifyTime;// 修改时间
		return orderCargoInfo;
		
	}
	
}
