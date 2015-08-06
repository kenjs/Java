package com.cy.swp.common.util;

import java.util.ArrayList;

import java.util.List;

import com.cy.swp.bo.DictInfo;

public class DataDictUtil {
	/**
	 * 货物来源
	 * @return
	 */
	public static List<DictInfo> cargoInfoFromData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "物流QQ"));
		list.add(new DictInfo("1", "一点通"));
		list.add(new DictInfo("2", "林安物流网"));
		list.add(new DictInfo("3", "物通网"));
		return list;
	}
	
	/**
	 * 数据字典-车辆-板
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> carPlateTypeData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("1", "平板"));
		list.add(new DictInfo("2", "高板"));
		list.add(new DictInfo("3", "低板"));
		list.add(new DictInfo("4", "高低板"));
		return list;
	}
	
	/**
	 * 数据字典-车辆-栏
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> carBarTypeData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("1", "箱式"));
		list.add(new DictInfo("2", "高栏"));
		list.add(new DictInfo("3", "半栏"));
		list.add(new DictInfo("4", "无栏"));
		return list;
	}
	
	/**
	 * 数据字典-车辆-运营状态
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> carStateTypeData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("1", "空车"));
		list.add(new DictInfo("2", "满载"));
		return list;
	}
	
	/**
	 * 数据字典-车辆-车长
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> carLengthData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("4", "4.2 米以下"));
		list.add(new DictInfo("4.2", "4.2 米"));
		list.add(new DictInfo("5", "5 米"));
		list.add(new DictInfo("6.2", "6.2 米"));
		list.add(new DictInfo("6.3", "6.3 米"));
		list.add(new DictInfo("6.8", "6.8 米"));
		list.add(new DictInfo("7.2", "7.2 米"));
		list.add(new DictInfo("7.5", "7.5 米"));
		list.add(new DictInfo("7.7", "7.7 米"));
		list.add(new DictInfo("7.8", "7.8 米"));
		list.add(new DictInfo("8", "8 米"));
		list.add(new DictInfo("8.7", "8.7 米"));
		list.add(new DictInfo("9.6", "9.6 米"));
		list.add(new DictInfo("12", "12 米"));
		list.add(new DictInfo("12.5", "12.5 米"));
		list.add(new DictInfo("13", "13 米"));
		list.add(new DictInfo("13.5", "13.5 米"));
		list.add(new DictInfo("16", "16 米"));
		list.add(new DictInfo("17.5", "17.5 米"));
		list.add(new DictInfo("18", "17.5 米以上"));
		return list;
	}
	
	/**
	 * 数据字典-车辆-运力-吨位
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> carWeightData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("5", "5吨以下"));
		list.add(new DictInfo("10", "6~10吨"));
		list.add(new DictInfo("15", "11~15吨"));
		list.add(new DictInfo("20", "16~20吨"));
		list.add(new DictInfo("30", "21~30吨"));
		list.add(new DictInfo("40", "31~40吨"));
		list.add(new DictInfo("50", "41~50吨"));
		list.add(new DictInfo("51", "50吨以上"));
		return list;
	}
	
	/**
	 * 数据字典-车辆-运力-体积
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> carCubageData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("10", "10方以下"));
		list.add(new DictInfo("20", "11~20方"));
		list.add(new DictInfo("30", "21~30方"));
		list.add(new DictInfo("50", "31~50方"));
		list.add(new DictInfo("80", "51~80方"));
		list.add(new DictInfo("100", "81~100方"));
		list.add(new DictInfo("150", "101~150方"));
		list.add(new DictInfo("151", "150方以上"));
		return list;
	}
	
	
	/**
	 * 数据字典-货物-货物类型
	 * @author hayden   2013-12-31
	 * */
	public static List<DictInfo> cargoTypeData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "请选择"));
		list.add(new DictInfo("1", "重货"));
		list.add(new DictInfo("2", "泡货"));
		return list;
	}
	
	/**
	 * 数据字典-货物-货物状态
	 * @author zdy 2014-06-10
	 */
	public static List<DictInfo> cargoFlagData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("-1", "货源状态"));
		list.add(new DictInfo("0", "待交易"));
		list.add(new DictInfo("1", "交易中"));
		list.add(new DictInfo("2", "交易成功"));
		list.add(new DictInfo("3", "失效"));
		return list;
	}
	
	/**
	 * 数据字典-评价
	 *@author zdy 2014-06-24
	 */
	public static List<DictInfo> tradeEvaluateScoreData(){
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("", "评价"));
		list.add(new DictInfo("3", "好评"));
		list.add(new DictInfo("6", "中评"));
		list.add(new DictInfo("9", "差评"));
		return list;
	}

	public static  List<DictInfo> businessLineQuoteType() {
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("", "请选择报价"));
		list.add(new DictInfo("1", "整车报价"));
		list.add(new DictInfo("2", "按吨报价"));
		list.add(new DictInfo("3", "按方报价"));
		return list;
	}

	public static  List<DictInfo> driverInfoCategory() {
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("", "请选择"));
		list.add(new DictInfo("1", "1类:经常使用,已认证"));
		list.add(new DictInfo("2", "2类:信息完善,已认证"));
		list.add(new DictInfo("3", "3类:信息完善,未认证"));
		list.add(new DictInfo("4", "4类:信息未完善,未认证"));
		list.add(new DictInfo("5", "5类:信息完善,未安装"));
		list.add(new DictInfo("6", "6类:信息未完善,未安装"));
		return list;
	}

	public static List<DictInfo> userPosition() {
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("", "请选择"));
		list.add(new DictInfo("0", "高管"));
		list.add(new DictInfo("1", "经理"));
		list.add(new DictInfo("2", "主管"));
		list.add(new DictInfo("3", "组长"));
		list.add(new DictInfo("4", "专员"));
		return list;
	}


	public static List<DictInfo> userJoinGroup() {
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "未分组"));
		list.add(new DictInfo("1", "营销一组"));
		list.add(new DictInfo("2", "营销二组"));
		list.add(new DictInfo("3", "营销三组"));
		return list;
	}

	public static List<DictInfo> userSex() {
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("1", "男"));
		list.add(new DictInfo("2", "女"));
		return list;
	}

	public static List<DictInfo> userDelete() {
		List<DictInfo> list = new ArrayList<DictInfo>();
		list.add(new DictInfo("0", "启用"));
		list.add(new DictInfo("1", "禁用"));
		return list;
	}

}
