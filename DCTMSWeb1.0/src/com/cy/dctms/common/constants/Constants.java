package com.cy.dctms.common.constants;


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
	
	
	/**订单状态-删除- -1*/
	public static final int ORDER_CARGO_DEPLOY_DELETED_KEY = -1;
	/**订单状态-删除*/
	public static final String ORDER_CARGO_DEPLOY_DELETED_VALUE = "删除";
	/**订单状态-未发布-0*/
	public static final int ORDER_CARGO_DEPLOY_UNDEPLOY_KEY = 0;
	/**订单状态-未发布*/
	public static final String ORDER_CARGO_DEPLOY_UNDEPLOY_VALUE = "未发布";
	/**订单状态--进行中-1*/
	public static final int ORDER_CARGO_DEPLOY_START_KEY = 1;
	/**订单状态--进行中*/
	public static final String ORDER_CARGO_DEPLOY_START_VALUE = "进行中";
	/**订单状态--异常结束-7*/
	public static final int ORDER_CARGO_DEPLOY_UNNORMAL_END_KEY = 7;
	/**订单状态--异常结束*/
	public static final String ORDER_CARGO_DEPLOY_UNNORMAL_END_VALUE = "异常结束";
	/**订单状态--作废-8*/
	public static final int ORDER_CARGO_DEPLOY_CANCAL_KEY = 8;
	/**订单状态--作废*/
	public static final String ORDER_CARGO_DEPLOY_CANCAL_VALUE = "作废";
	/**订单状态--结束-9*/
	public static final int ORDER_CARGO_DEPLOY_END_KEY = 9;
	/**订单状态--结束*/
	public static final String ORDER_CARGO_DEPLOY_END_VALUE = "结束";
	
	/**运营状态-空车- 1*/
	public static final int CAR_STATE_TYPE_NULL_KEY = 1;
	/**运营状态-空车*/
	public static final String CAR_STATE_TYPE_NULL_VALUE = "空车";
	/**运营状态-满载- 2*/
	public static final int CAR_STATE_TYPE_FULL_KEY  = 2;
	/**运营状态-满载*/
	public static final String CAR_STATE_TYPE_FULL_VALUE = "满载";
	
	
	/**司机得分*/
	/**满意-3*/
	public static final int DRIVER_EVALUATE_SATISFACTORY_KEY  = 3;
	/**满意*/
	public static final String DRIVER_EVALUATE_SATISFACTORY_VALUE = "满意";
	/**一般-6*/
	public static final int DRIVER_EVALUATE_ARIAL_KEY  = 6;
	/**一般*/
	public static final String DRIVER_EVALUATE_ARIAL_VALUE = "一般";
	/**不满意-9*/
	public static final int DRIVER_EVALUATE_NOSATISFACTORY_KEY  = 9;
	/**不满意*/
	public static final String DRIVER_EVALUATE_NOSATISFACTORY_VALUE = "不满意";
	
	
	/***用户未登录显示***/
	public static final String WEB_USER_INFO_VALUE = "登录后查看";
	
	/**删除状态--未删除 -0*/
	public static final int DELETED_FLAG_FALSE = 0;
	
	/**删除状态--删除 -1*/
	public static final int DELETED_FLAG_TRUE = 1;
	
	/**货物签收标志--未卸货 -0*/
	public static final int SIGN_FLAG_FALSE = 0;
	
	/**货物签收标志--已卸货 -1*/
	public static final int SIGN_FLAG_TRUE = 1;
	
	/**android手机系统*/
	public static final String OS_ANDROID="android";
	/**IOS手机系统*/
	public static final String OS_IOS="ios";
	
	/**excel列分隔符  !@!*/
	public static final String CELL_SPLIT_STR = "!@!";
	
	/**车型分隔符  &nbsp;*/
	public static final String TYPES_SPLIT_STR = " ";
	
	
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
	
	
	//log记录开始，统一在DCTMS中维护。。。
	
	//public static final String DRIVER_APP_ANDROID_ACTIVE_PER_DAY_TAG = "";
	//public static final String DRIVER_APP_ANDROID_ACTIVE_PER_MONTH_TAG = "";
	
	/**app 新下载安装标记 DRIVER_APP_DOWNLOAD_TAG，需记录字段及值：OS=android,ios,wp、TIME*/
	public static final String DRIVER_APP_DOWNLOAD_TAG = "DRIVER_APP_DOWNLOAD_TAG";

	/**app 用户登录标记 DRIVER_APP_LOGIN_TAG，需记录字段及值：OS、DRIVERID、DRIVERCODE、REGISTERFLAG=0,1、TIME*/
	public static final String DRIVER_APP_LOGIN_TAG = "DRIVER_APP_LOGIN_TAG";
	
	/**app 用户启动标记 DRIVER_APP_RUN_TAG，需记录字段及值：OS、CARGOID、DRIVERID、DRIVERCODE、TIME*/
	public static final String DRIVER_APP_RUN_TAG = "DRIVER_APP_RUN_TAG";
	
	/**app 用户发布预约线路标记 DRIVER_APP_DEPLOY_CAR_LINE_TAG，需记录字段及值：OS、DEPLOYCARLINE、DEPLOYCARLINETIME、DRIVERID、DRIVERCODE、TIME*/
	public static final String DRIVER_APP_DEPLOY_CAR_LINE_TAG = "DRIVER_APP_DEPLOY_CAR_LINE_TAG";

	/** web 用户注册标记 WEB_USER_REGISTER_TAG，需记录字段及值：USERID、USERCODE、TIME*/
	public static final String WEB_USER_REGISTER_TAG = "WEB_USER_REGISTER_TAG";
	
	/** web用户发布货源标记 WEB_USER_ADD_ORDER_CARGO_TAG，需记录字段及值：USERID、USERCODE、CARGOID、REDEPLOYFLAG=0、TIME*/
	public static final String WEB_USER_ADD_ORDER_CARGO_TAG = "WEB_USER_ADD_ORDER_CARGO_TAG";
	
	/** web用户重新发布货源标记 WEB_USER_RE_ADD_ORDER_CARGO_TAG，需记录字段及值：USERID、USERCODE、CARGOID、REDEPLOYFLAG=1、TIME*/
	public static final String WEB_USER_RE_ADD_ORDER_CARGO_TAG = "WEB_USER_RE_ADD_ORDER_CARGO_TAG";
	
	/** web用户修改货源发货时间标记 WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG，需记录字段及值：USERID、USERCODE、CARGOID、REDEPLOYFLAG=1、TIME*/
	public static final String WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG = "WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG";
	
	/** web用户新增交易记录标记 WEB_USER_ADD_TRANSACTION_TAG，需记录字段及值：USERID、USERCODE、CARGOID、DRIVERID、DRIVERCODE、TRANSACTIONID、TIME*/
	public static final String WEB_USER_ADD_TRANSACTION_TAG = "WEB_USER_ADD_TRANSACTION_TAG";
	
	/**app 用户上传地址完整信息标记 DRIVER_APP_LOCATION_COLLECT_TAG，需记录字段及值：OS、LOCATIONID,DRIVERID、DRIVERCODE、TIME*/
	public static final String DRIVER_APP_LOCATION_COLLECT_TAG = "DRIVER_APP_LOCATION_COLLECT_TAG";
	
	
	/**app 用户手机信息标记 DRIVER_APP_MOBILE_TAG，需记录字段及值：OS、DRIVERID、DRIVERCODE、MTYPE、BRAND、OSRELEASE、TIME*/
	public static final String DRIVER_APP_MOBILE_TAG = "DRIVER_APP_MOBILE_TAG";
	
	/** 百度云推送测试apiKey*/
    //public static final String apiKey = "VoAvvLZ69qyFKmOGSe3bmKez";
	/** 百度云推送测试secretKey*/
	//public static final String secretKey = "Q9qAcjwcbCe7uHOt2kZGjMcmyIInAhkv";
	/** 百度云推送生产apiKey*/
	public static final String apiKey = "9vAr0wp7pNEGlzzr8qGkjkUw";
	/** 百度云推送生产apiKey*/
	public static final String secretKey = "eVQAomws0wcWDYrI8n5KmCCONf54eGWD";
	//log记录结束，统一在DCTMS中维护。。。
	/** 审核推送标题*/
    public static final String auditTitle = "审核认证";
    /** 小数点保留位数*/
    public static final int decimalNum = 2;
}
