package com.cy.dctms.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 标志转名称
 * @author WJL
 *
 */
public class FlagChangeName {
	//审核标志
	public static String auditFlag(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "审核中";
		}else if ("1".equals(src)) {
			result = "通过";
		}else if ("-1".equals(src)) {
			result ="未通过";
		}
		return result;
	}
	//企业认证标志
	public static String enterpriseFlag(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "未认证";
		}else if ("1".equals(src)) {
			result = "已认证";
		}
		return result;
	}
	//当前运营状态
	public static String carStateType(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "求货";
		}else if ("2".equals(src)) {
			result = "满载";
		}else if ("3".equals(src)) {
			result ="休息";
		}
		return result;
	}
	//新老用户标志
	public static String newOrOldAppUser(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "新用户";
		}else if ("1".equals(src)) {
			result = "老用户";
		}
		return result;
	}
	//用户来源USER_ORIGIN
	public static String userOrigin(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "自己注册";
		}else if ("1".equals(src)) {
			result = "导入的";
		}
		return result;
	}
	//评价
	public static String assess(String src){
		String result ="";
		if ("3".equals(src)) {
			result = "好评";
		}else if ("6".equals(src)) {
			result = "中评";
		}else if("9".equals(src)){
			result = "差评";
		}
		return result;
	}
	
	//货物状态
	public static String tradeState(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "等待司机确认";
		}else if ("3".equals(src)) {
			result = "运输跟踪";
		}else if ("5".equals(src)) {
			result = "订单完成";
		}else if ("6".equals(src)) {
			result = "交易取消";
		}else if ("7".equals(src)) {
			result = "司机已卸货";
		}
		return result;
	}
	
	//订单状太
	public static String orderState(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "有效";
		}else if ("1".equals(src)) {
			result = "无效";
		}
		return result;
	}
	//交易取消来源
	public static String orderCancelOrigin(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "司机取消";
		}else if ("2".equals(src)) {
			result = "货主取消";
		}
		else if ("3".equals(src)) {
			result = "全取消";
		}
		return result;
	}
	
	//货物状态
	@SuppressWarnings("deprecation")
	public static String cargoFlag(String src,String requestTime){
		String result ="";
		if ("1".equals(src)) {
			result = "交易中";
		}else if ("2".equals(src)) {
			result = "成功";
		}else if("0".equals(src)){
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date endTime = null;
			try {
				endTime = df.parse(requestTime);
			} catch (ParseException e) {
				return "";
			}
			//比较时间
			if (endTime.getTime()>new Date().getTime()) {
				result = "待交易";
			}else {
				result = "失效";
			}
			
		}
		return result;
	}
	
	//货物类型
	public static String cargoType(String src){
		String result ="";
		if ("1".equals(src)) {
			result = "重货";
		}else if ("2".equals(src)) {
			result = "泡货";
		}
		return result;
	}
	
	//审核结果
	public static String submitType(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "未提交";
		}else if ("1".equals(src)) {
			result = "已提交";
		}
		else if ("2".equals(src)) {
			result = "未通过";
		}
		else if ("3".equals(src)) {
			result = "已通过";
		}
		return result;
	}
	//'注册用户类型（物流企业0、发货方1、收货方2）',
	public static String userType(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "物流企业";
		}else if ("1".equals(src)) {
			result = "发货方";
		}
		else if ("2".equals(src)) {
			result = "收货方";
		}
		return result;
	}
	//'申请功能类型(0：货主版功能、1：身份证查询功能、2：VIP功能)',
	public static String applyType(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "货主版功能";
		}else if ("1".equals(src)) {
			result = "身份证查询";
		}
		else if ("2".equals(src)) {
			result = "VIP功能";
		}
		return result;
	}
	//'审核状态（0等待审核、-1审核失败、1审核成功）',
	public static String verifyStart(String src){
		String result ="";
		if ("0".equals(src)) {
			result = "等待审核";
		}else if ("-1".equals(src)) {
			result = "审核失败";
		}
		else if ("1".equals(src)) {
			result = "审核成功";
		}
		return result;
	}

}
