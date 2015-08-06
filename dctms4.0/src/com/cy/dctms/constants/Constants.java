package com.cy.dctms.constants;

public class Constants {

	/**SESSION中存储 当前登录系统帐号CODE*/
	public static final String SESSION_LOGIN_USER = "user";
	
	
	/**系统错误代码-参数错误*/
	public static final String EXCEPTION_CODE_PARAMETER_COMMON = "12000";
	/**系统错误代码-SESSION为空错误*/
	public static final String EXCEPTION_CODE_SESSION_EMPTY_COMMON = "12100";
	/**系统错误代码-业务逻辑错误*/
	public static final String EXCEPTION_CODE_BUSINESS_COMMON = "10000";
	/**系统错误代码-数据库错误*/
	public static final String EXCEPTION_CODE_DATABASE_COMMON = "11000";
	
	/**JSON系统错误代码-参数错误*/
	public static final String JSON_EXCEPTION_CODE_PARAMETER_COMMON = "-1";
	/**JSON系统错误代码-用户不存在错误*/
	public static final String JSON_EXCEPTION_CODE_USER_EMPTY_COMMON = "-9";
	/**JSON系统错误代码-业务逻辑错误*/
	public static final String JSON_EXCEPTION_CODE_BUSINESS_COMMON = "-8";
	
	
	/**系统短时间格式:  yyyy-MM-dd*/
	public static final String DATE_FORMATE_DAY = "yyyy-MM-dd";
	/**系统短时间格式:  yyyy-MM-dd HH*/
	public static final String DATE_FORMATE_HOUR = "yyyy-MM-dd HH";
	/**系统时间格式:  yyyy-MM-dd HH:mm:ss*/
	public static final String DATE_FORMATE_LONG = "yyyy-MM-dd HH:mm:ss";
	/**系统时间格式:  yyyyMMdd*/
	public static final String DATE_FORMATE_TIME = "yyyyMMdd";
	/**系统时间格式:  Y年m月d日 H时m分s秒*/
	public static final String DATE_FORMATE_CN_LONG = "yyyy年MM月dd日HH时mm分ss秒";
	
	
	/**列表页默认显示记录数——主页 10*/
	public static int PAGE_SIZE_INDEX = 10;
	
	/**列表页默认显示记录数——二级页 20*/
	public static int PAGE_SIZE_MAIN = 20;
	
	/**列表页默认显示记录数——位置信息页 10*/
	public static int PAGE_SIZE_LOCATION = 10;
	
	/**删除状态--未删除 -0*/
	public static final int DELETED_FLAG_FALSE = 0;
	
	/**删除状态--删除 -1*/
	public static final int DELETED_FLAG_TRUE = 1;
	
	
	/*****************WEB用户***********************/
	/**企业认证标识--未认证 -0*/
	public static final int ENTERPRISE_FLAG_NOT = 0;
	
	/**企业认证标识--已认证 -1*/
	public static final int ENTERPRISE_FLAG_END = 1;
	
	/**缴费认证标识--未认证 -0*/
	public static final int PANYMENT_FLAG_NOT = 0;
	
	/**缴费认证标识--已认证 -1*/
	public static final int PANYMENT_FLAG_END = 1;
	
	/**个人认证标识--未认证 -0*/
	public static final int PERSONAGE_FLAG_NOT = 0;
	
	/**个人认证标识--已认证 -1*/
	public static final int PERSONAGE_FLAG_END = 1;
	
	
	/**短信发送状态--发送成功 -0*/
	public static final int NOTE_SEND_START_SUCCESS = 0;
	
	/**短信发送状态--发送失败 -1*/
	public static final int NOTE_SEND_START_ERROR = 1;
	
	
//----------------------------用户来源（0自己注册，1导入的）------------------------
	/**用户来源-自己注册- 0*/
	public static final String 	USER_ORIGIN_REGISTRATION = "0";
	/**用户来源-导入的- 1*/
	public static final String USER_ORIGIN_IMPORT= "1";
	
//------------------------司机对货源的点评2------------------------
	/**司机对货源的点评-货已走- 0*/
	public static final String CARGO_EXPORTED_TYPE_KEY = "0";
	/**司机对货源的点评-货已走*/
	public static final String CARGO_EXPORTED_TYPE_VALUE = "货已走";
	/**司机对货源的点评-货还在- 1*/
	public static final String CARGO_EXIST_TYPE_KEY= "1";
	/**司机对货源的点评-货还在*/
	public static final String CARGO_EXIST_TYPE_VALUE= "货还在";
	
	
//------------------------货源装货时间是否过期（是否失效）----------------------------
	/**货源是否过期-未过期- 0*/
	public static final String IS_EXPIRE_FALSE = "0";
	/**货源是否过期-已过期- 1*/
	public static final String IS_EXPIRE_TRUE= "1";


 //----------------------货源状态4(数据库中3个值，页面显示有4个值多一个失效,页面上的待交易查询：待交易0且未过期的，数据库中的待交易0包含：过期和未过期的)-------------------------
	/**货源状态-待交易- 0*/
	public static final String CARGO_FLAG_PENDING_TRADE_KEY = "0";
	/**货源状态-待交易*/
	public static final String CARGO_FLAG_PENDING_TRADE_VALUE = "待交易";
	/**货源状态-交易中- 1*/
	public static final String CARGO_FLAG_TRADING_KEY = "1";
	/**货源状态-交易中*/
	public static final String CARGO_FLAG_TRADING_VALUE = "交易中";
	/**货源状态-交易成功- 2*/
	public static final String CARGO_FLAG_SUCCESS_KEY = "2";
	/**货源状态-交易成功*/
	public static final String CARGO_FLAG_SUCCESS_VALUE = "交易成功";
	/**货源状态-失效- 2*/
	public static final String CARGO_FLAG_EXPRICE_KEY = "3";
	/**货源状态-失效*/
	public static final String CARGO_FLAG_EXPRICE_VALUE = "失效";
	 //----------------------货物类型（2种）-------------------------
	/**货物类型-重货- 1*/
	public static final String CARGO_TYPE_HEVAY_KEY = "1";
	/**货物类型-重货*/
	public static final String CARGO_TYPE_HEVAY_VALUE = "重货";
	/**货物类型-泡货- 2*/
	public static final String CARGO_TYPE_BULKY_KEY = "2";
	/**货物类型-泡货*/
	public static final String CARGO_TYPE_BULKY_VALUE = "泡货";
	
	//---------------------交易状态7(当前改为5)---------------------------
	//1.货主对交易的操作：状态的改变
	/**交易状态--等待司机确认- 1*/
	public static final String TRADE_START_WAITING_DRIVER_CONFIRM_KEY = "1";
	/**交易状态--等待司机确认*/
	public static final String TRADE_START_WAITING_DRIVER_CONFIRM_VALUE = "等待司机确认";
	/**交易状态--待装货- 2*/
//	public static final String TRADE_START_AFTER_LOADING_KEY = "2";
    /**交易状态--待装货*/
//	public static final String TRADE_START_AFTER_LOADING_VALUE = "待装货";
	/**交易状态-运输跟踪- 3*/
	public static final String TRADE_START_IN_TRANSIT_KEY = "3";
	/**交易状态--运输跟踪*/
	public static final String TRADE_START_IN_TRANSIT_VALUE = "运输跟踪";
	/**交易状态-已送达- 4*/
	//public static final String TRADE_START_ARRIVED_KEY = "4";
	/**交易状态--已送达*/
	//public static final String TRADE_START_ARRIVED_VALUE = "已送达";
	/**交易状态-订单完成- 5*/
	public static final String TRADE_START_SUCCESS_KEY = "5";
	/**交易状态--订单完成*/
	public static final String TRADE_START_SUCCESS_VALUE = "订单完成";
	/**交易状态-交易取消- 6*/
	public static final String TRADE_START_CLOSE_KEY = "6";
	/**交易状态--交易取消*/
	public static final String TRADE_START_CLOSE_VALUE = "交易取消";
	
	//2.司机对交易的操作：状态的改变
	/**交易状态-司机已卸货- 7*/
	public static final String TRADE_START_DISBURDEN_KEY = "7";
	/**交易状态--司机已卸货*/
	public static final String TRADE_START_DISBURDEN_VALUE = "司机已卸货";
   //---------------------总评价得分-----------------------------
	/**好评-3*/
	public static final String EVALUATE_SATISFACTORY_KEY  = "3";
	/**好评*/
	public static final String EVALUATE_SATISFACTORY_VALUE = "好评";
	/**一般-6*/
	public static final String EVALUATE_ARIAL_KEY  = "6";
	/**一般*/
	public static final String EVALUATE_ARIAL_VALUE = "中评";
	/**差评-9*/
	public static final String EVALUATE_NOSATISFACTORY_KEY  = "9";
	/**差评*/
	public static final String EVALUATE_NOSATISFACTORY_VALUE = "差评";
	
	//--------------------报价类型---------------------------------
	 /**报价类型-整车报价-1*/ 
	public static final String QUOTE_TYPE_VEHICLES_KEY  = "1";
	/**报价类型-整车报价*/
	public static final String QUOTE_TYPE_VEHICLES_VALUE = "整车报价";
	/**报价类型-按吨报价-2*/
	public static final String QUOTE_TYPE_BY_TON_KEY  = "2";
	/**报价类型-按吨报价*/
	public static final String QUOTE_TYPE_BY_TON_VALUE  = "按吨报价";
	/**报价类型-按方报价-3*/
	public static final String QUOTE_TYPE_PRESS_SQUARE_KEY  = "3";
	/**报价类型-按方报价*/
	public static final String QUOTE_TYPE_PRESS_SQUARE_VALUE  = "按方报价";
	
	//--------------------交易取消的来源---------------------------------
		 /**交易取消的来源-正常交易，任何一方都为取消(默认值)-0*/ 
		public static final String TRADE_CANCEL_ORIGIN_DEFAULT_KEY  = "0";
		/**交易取消的来源-正常交易，任何一方都为取消(默认值)*/
		public static final String TRADE_CANCEL_ORIGIN_DEFAULT_VALUE = "";
		/**交易取消的来源-司机关闭-1*/
		public static final String TRADE_CANCEL_ORIGIN_OPPOSITE_KEY  = "1";
		/**交易取消的来源-司机关闭*/
		public static final String TRADE_CANCEL_ORIGIN_OPPOSITE_VALUE  = "对方取消";
		/**交易取消的来源-货主关闭-2*/
		public static final String TRADE_CANCEL_ORIGIN_OUR_KEY  = "2";
		/**交易取消的来源-货主关闭*/
		public static final String TRADE_CANCEL_ORIGIN_OUR_VALUE  = "我方取消";
	
	/**运营状态-空车- 1*/
	public static final int CAR_STATE_TYPE_NULL_KEY = 1;
	/**运营状态-空车*/
	public static final String CAR_STATE_TYPE_NULL_VALUE = "空车";
	/**运营状态-满载- 2*/
	public static final int CAR_STATE_TYPE_FULL_KEY  = 2;
	/**运营状态-满载*/
	public static final String CAR_STATE_TYPE_FULL_VALUE = "满载";
	
	
	
	
	/**货物签收标志--未卸货 -0*/
	public static final int SIGN_FLAG_FALSE = 0;
	
	/**货物签收标志--已卸货 -1*/
	public static final int SIGN_FLAG_TRUE = 1;
	
	
	/**excel列分隔符  !@!*/
	public static final String CELL_SPLIT_STR = "!@!";
	
	/**车型分隔符  &nbsp;*/
	public static final String TYPES_SPLIT_STR = " ";
	
	
	//----------------------------个人中心左边菜单超链接Id（9个）-----------------------------------
	/**账号管理-*/
	public static final String ACCOUNT_MANAGEMENT="a_id_1";
	/**企业认证*/
	public static final String COMPANY_AUTHENTICATION="a_id_2";
	/**发布货源*/
	public static final String PUBLISH_CARGO="a_id_3";
	/**我的货源*/
	public static final String MY_CARGO="a_id_4";
	/**我的订单*/
	public static final String MY_ORDER="a_id_5";
	/**交易记录*/
	public static final String MY_TRANSACTION="a_id_6";
	/**身份证查询*/
	public static final String IDENTITY_CARD_QUERY="a_id_7";
	/**常用车辆*/
	public static final String COMMON_CAR="a_id_8";
	/**评价管理*/
	public static final String EVALUATE_MANAGEMENT="a_id_9";
	
	//---------------------------------百度云推送的两个常量 apiKey secretKey-----------------------------------------
	/** 百度云推送apiKey*/
	public static final String apiKey = "9vAr0wp7pNEGlzzr8qGkjkUw";
	/** 百度云推送secretKey*/
	public static final String secretKey = "eVQAomws0wcWDYrI8n5KmCCONf54eGWD";
}
